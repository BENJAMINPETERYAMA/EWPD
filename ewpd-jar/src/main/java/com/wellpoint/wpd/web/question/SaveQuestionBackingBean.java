/*
 * SaveQuestionBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.question;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.wpd.business.datatype.locatecriteria.DataTypeLocateResult;
import com.wellpoint.wpd.common.bo.State;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.Message;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.popup.bo.PopupFilterBO;
import com.wellpoint.wpd.common.popup.request.PopupRequest;
import com.wellpoint.wpd.common.popup.response.PopupResponse;
import com.wellpoint.wpd.common.question.bo.FunctionalDomainBO;
import com.wellpoint.wpd.common.question.request.RetrieveQuestionRequest;
import com.wellpoint.wpd.common.question.request.SaveQuestionRequest;
import com.wellpoint.wpd.common.question.response.RetrieveQuestionResponse;
import com.wellpoint.wpd.common.question.response.SaveQuestionResponse;
import com.wellpoint.wpd.common.question.vo.PossibleAnswerVO;
import com.wellpoint.wpd.common.question.vo.QuestionVO;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * Backing Bean for Questions creation.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveQuestionBackingBean extends WPDBackingBean {

    private int questionNumber;

    private String question;

    private String dataType;

    private String answer;

    private String possibleAnswer;

    private int deletedAnswer;

    List answerList = null;

    List possibleAnswerList = null;

    private List validationMessages = null;

    private boolean requiredQuestion = false;

    private boolean requiredDataType = false;

    private boolean requiredAnswers = false;

    private boolean updateFlag = false;

    private int dataTypeId = -1;

    private int version = -1;

    private String createdUserId;

    private Date createdDate;

    private String updatedUserId;

    private Date updatedDate;

    private String state = "NEW";

    private String status;

    private State stateObj;

    private boolean errorFlag = false;

    private boolean checkInFlag = false;

    private boolean checkOutFlag = false;

    private boolean validationFlag = false;

    private String breadCrumpCreate;
    
    private static final String VIEW = "view";

    private int valueChanged = 0;

    private String deleteAnswerIds;

    private String providerArrangement;

    private boolean domainInvalid = false;

    private String functionalDomain;

    List functionalDomainList = null;

    private boolean referenceInvalid = false;

    private boolean requiredReferenceDataType = false;

    private boolean requiredTerm= false;
    private boolean requiredQualifier= false;
    private boolean requiredPVA= false;
    private boolean requiredBenefitLineDataType= false;
    private boolean aggregateQualifier;

    private boolean aggregateTerm;

    private String spsRefernceId;

    private String qualifier;
    
    private String term;
    
    private String spsDataType;
    
    private String benefitLineDataType;
    
    private boolean adjudAccumSelected = false;

    /**
     * Constructor
     */

    public SaveQuestionBackingBean() {
        answerList = new ArrayList();
        possibleAnswerList = new ArrayList();
        functionalDomainList = new ArrayList();
    }


    /**
     * Method for create/Save Question
     * 
     * @return String
     *  
     */
    public String create() {
  	
        if (!validateField()) {
            addAllMessagesToRequest(validationMessages);
            this.answerList = (List) this.getSession().getAttribute(
                    "possibleAnswerList");
        } else {

            SaveQuestionRequest saveQuestionRequest = null;
            SaveQuestionResponse saveQuestionResponse = null;
            List possibleAnswerList = (List) this.getSession().getAttribute(
                    "possibleAnswerList");
            if (null == possibleAnswerList || possibleAnswerList.size() == 0) {
                this.addAnswer();
                if (errorFlag) {
                    this
                            .setBreadCrumbText("Administration >> Question ("
                                    + this.question + ") >> Edit");
                    return "";
                }
            } else
                this.setAnswerList(possibleAnswerList);
            
           
            // Create the Request Object
            saveQuestionRequest = this.getSaveQuestionRequest();
           
            filterResult(saveQuestionRequest);
            
            String[] spsRef=this.spsRefernceId.split("~");
            
            saveQuestionRequest.getQuestionVO().setSpsReference(spsRef[0]);
            
            if(this.validationFlag==true){
            	List funcValidationList=WPDStringUtil.getListFromTildaString(
    				this.functionalDomain, 3, 3, 2);
            	if(funcValidationList.contains("Y"))
            		  if(!validateCheckIn(saveQuestionRequest)){            		  	
            		  	ErrorMessage errorMessage = new ErrorMessage("question.sps.value.valid");
                        validationMessages.add(errorMessage);
                        addAllMessagesToRequest(validationMessages);
                        this
                        .setBreadCrumbText("Administration >> Question ("
                                + this.question + ") >> Edit");
            		  	return "questionEditPage";
            		  }
            }
                        
            // Call the service method
            saveQuestionResponse = (SaveQuestionResponse) this
                    .executeService(saveQuestionRequest);
            List msgRemList = (ArrayList)getRequest().getAttribute("messages");
            if(msgRemList != null && msgRemList.size()!=0){
            	Iterator it = msgRemList.iterator();
            	while(it.hasNext()){
            		Message msg = (Message)it.next();
            		if(msg.getId().equals("Filter.results.zero")){
            			it.remove();
            		}
            	}
            }
            if (null != saveQuestionResponse) {
                // Fill the backing bean attributes with updated information
                if (saveQuestionResponse.isCheckInSuccessFlag()) {
                    this.clearForm();
                    return "questionCreatePage";
                }
                if (null != saveQuestionResponse.getQuestionVO()) {
                    if (!saveQuestionResponse.isErrorFlag()) {
                        this.updateFlag = true;
                    }
                    if (saveQuestionResponse.isCheckInErrorFlag()) {
                        //this.copyResponseValuesToBackingBean(saveQuestionResponse.getQuestionVO());
                        this.checkInFlag = false;
                        //return "questionEditPage";
                    }

                    this
                            .setBreadCrumbText("Administration >> Question ("
                                    + this.question + ") >> Edit");
                    this.copyResponseValuesToBackingBean(saveQuestionResponse
                            .getQuestionVO());
                    String printAction = VIEW;
                    this.getSession().setAttribute("questionNumber",
                            new Integer(this.getQuestionNumber()).toString());
                    this.getSession().setAttribute("question", (this.question));
                    this.getSession().setAttribute("version",
                            new Integer(this.version).toString());
                    this.getSession().setAttribute("action", printAction);
                    this.setValueChanged(0);
                    return "questionEditPage";
                }
            }
        }
        
        this.setBreadCrumbText("Administration >> Question ("
                + this.question + ") >> Edit");
        return "";
    }
    private boolean validateCheckIn(WPDRequest request) {

		SaveQuestionRequest saveQuestionRequest = (SaveQuestionRequest) request;
		String ref = saveQuestionRequest.getQuestionVO().getSpsReference();
		if (null != ref && !"".equals(ref))
			return true;
		return false;

	}
 
    	/**
		 * 
		 * 
		 * this method call at the time of filter Search. Methos crate the
		 * Request PopupRequest and using this request call the business service
		 * retrieve List of record for popUp and set backing bean values
		 */
    	public void filterResult(WPDRequest saveQuestionRequest) {
    		PopupRequest request = getPopupRequest(saveQuestionRequest);
    		request.setPopAction(WebConstants.SEARCH_ACTION);
    		//this.popAction = WebConstants.SEARCH_ACTION;
    		PopupResponse response = null;
    		response = (PopupResponse) executeService(request);
    		if (null != response) {
    			validateSPS(response);
    		}

    	}
    	private void validateSPS(PopupResponse response){
    		
    		
    		List funcValidationList=WPDStringUtil.getListFromTildaString(
    				this.functionalDomain, 3, 3, 2);
    		
    		if(response.getResultList().size()==1){
    			PopupFilterBO filterBO=(PopupFilterBO)response.getResultList().get(0);
    			if(funcValidationList.contains("Y"))
    				this.setSpsRefernceId(filterBO.getSpsId());
    
    		}
    		else if(response.getResultList().size()>1){
    			if(null!=this.spsRefernceId && !"".equals(this.spsRefernceId)&&!"null".equalsIgnoreCase(this.spsRefernceId)){
    				boolean spsMatch=false;
    				String ref="";
    				String[] reference=this.spsRefernceId.split("~");
    				for (Iterator iter = response.getResultList().iterator(); iter.hasNext();) {
						PopupFilterBO element = (PopupFilterBO) iter.next();
						if(element.getSpsId().equals(reference[0])){
							ref=element.getSpsId();
							spsMatch=true;
							break;
						}
    				}
					if(spsMatch)
							this.spsRefernceId=ref;
					else
						this.spsRefernceId="";
    			}
    		}
    	}
    	/*
    	 * @ return PopupRequest
    	 * this methode create and return a request object for calling business service action 
    	 * In this methode we are settign values for poup loading and filtering the records 
    	 * 
    	 */
   	private PopupRequest getPopupRequest(WPDRequest saveQuestionRequest) {
   		
		PopupRequest request = (PopupRequest) this
				.getServiceRequest(ServiceManager.POPUP_REQUEST);

		SaveQuestionRequest obj=(SaveQuestionRequest)saveQuestionRequest;
		// Bug Fix. searchSPSForMapping1 takes all the values.It doesnt have rownum < 50.
		String queryName = "searchSPSForMapping1";
		HashMap hashMap = new HashMap();

		String spsType = "QUESTION";
		hashMap.put("spsType", spsType);

		if (obj.getQuestionVO().getTerm() != null && !"".equalsIgnoreCase(obj.getQuestionVO().getTerm())) {
			hashMap.put("term", obj.getQuestionVO().getTerm());
		}
		if (obj.getQuestionVO().getPva() != null
				&& !"".equalsIgnoreCase(obj.getQuestionVO().getPva() )) {
			hashMap.put("pva", obj.getQuestionVO().getPva() );
		}
		if (null != obj.getQuestionVO().getQualifier()  && !"".equalsIgnoreCase(obj.getQuestionVO().getQualifier().trim()))
			hashMap.put("qualifier", obj.getQuestionVO().getQualifier() );

		if (obj.getQuestionVO().getDataTypeId() != -1 ) {
			hashMap.put("dataType", new Integer(obj.getQuestionVO().getDataTypeId()));
		}

		request.setQueryName(queryName);
		request.setHashMap(hashMap);

		return request;
		
	}
    

    /**
	 * Method for check in Question
	 * 
	 * @return String
	 *  
	 */
    public String checkIn() {
        this.setValidationFlag(true);
        return this.create();
    }


    /**
     * To clear the field values
     *  
     */
    public void clearForm() {
        this.questionNumber = -1;
        this.question = null;
        this.dataTypeId = -1;
        this.answerList = null;
        this.updateFlag = false;
        this.getSession().setAttribute("possibleAnswerList", null);
        this.possibleAnswer = null;
        this.providerArrangement = null;
        this.functionalDomain=null;
        this.functionalDomainList=null;
        this.term=null;
        this.qualifier=null;
        this.spsRefernceId=null;
    }    


    /**
     * Method for retrieve Question
     *  
     */
    public String retriveQuestion() {
        RetrieveQuestionRequest retrieveQuestionRequest = null;
        RetrieveQuestionResponse retrieveQuestionResponse = null;
        // Create the Request Object
        retrieveQuestionRequest = this.getRetrieveQuestionRequest();
        String printAction = VIEW;
        this.getSession().setAttribute("questionNumber",
                new Integer(this.getQuestionNumber()).toString());
        this.getSession().setAttribute("question", (this.question));
        this.getSession().setAttribute("version",
                new Integer(this.version).toString());
        this.getSession().setAttribute("action", printAction);
        // Call the service method
        retrieveQuestionResponse = (RetrieveQuestionResponse) this
                .executeService(retrieveQuestionRequest);
        if (null != retrieveQuestionResponse) {
        	
        	if(retrieveQuestionResponse.isUnlockFlag()){
        		return "questionEditPage";
        	}
        	else{
	            // Fill the backing bean attributes with updated information
	            if (null != retrieveQuestionResponse.getQuestionVO()) {
	                this.copyResponseValuesToBackingBean(retrieveQuestionResponse
	                        .getQuestionVO());
	                this.updateFlag = true;
	                this.checkOutFlag = false;
	            }
        	}
        }
        this.setBreadCrumbText("Administration >> Question ("
                + this.question + ") >> Edit");
        return "questionEditPage";
    }


    /**
     * Copying the return values in the Response object to the Backing Bean.
     * 
     * @param questionVO
     */
    private void copyResponseValuesToBackingBean(QuestionVO questionVO) {
        if (null != questionVO) {
            this.questionNumber = questionVO.getQuestionNumber();
            this.question = questionVO.getQuestionDesc();
            this.dataTypeId = questionVO.getDataTypeId();
            this.providerArrangement = questionVO.getPva();
            this.answerList = questionVO.getAnswerList();
            this.version = questionVO.getVersion();
            this.status = questionVO.getStatus();
            this.state = questionVO.getState().getState();
            this.createdUserId = questionVO.getCreatedUser();
            this.createdDate = questionVO.getCreatedTimestamp();
            this.updatedUserId = questionVO.getLastUpdatedUser();
            this.updatedDate = questionVO.getLastUpdatedTimestamp();
            this.functionalDomain = getTildaStringFumctionalDomain(questionVO.getFunctionalDomainCDList());
            this.setSpsRefernceId(questionVO.getSpsReference());
            this.setTerm(questionVO.getTerm());
            this.setQualifier(questionVO.getQualifier());
            this.setBenefitLineDataType(questionVO.getBenefitLineDataType());
            if(this.getTerm().split("~").length>2)
            	this.aggregateTerm=true;
           
            if(this.getQualifier().split("~").length>2)
            	this.aggregateQualifier=true;
            
            
            this.getSession().setAttribute("possibleAnswerList", answerList); 
        }
    }
	
    /**Method for getting tilda seperated code and description from functionalDomainBo
	 * @param functionalDomain
	 * @return String
	 */
    public static String getTildaStringFumctionalDomain(List functionalDomain){
        
        if(functionalDomain == null)
            return "";
        
        StringBuffer buffer = new StringBuffer();
        for (int i=0; i<functionalDomain.size(); i++) {
        	FunctionalDomainBO element = (FunctionalDomainBO) functionalDomain.get(i);
            if(i!=0){
                buffer.append("~");
            }
            buffer.append(element.getFunctionalDomainDesc());
            buffer.append("~" + element.getFunctionalDomainCD());
            buffer.append("~" + element.getSecondaryCode());
        }
        return buffer.toString();
    }
    
    /**
     * Method for create and populate RetrieveQuestionRequest
     * 
     * @return RetrieveQuestionRequest retrieveQuestionRequest
     *  
     */
    private RetrieveQuestionRequest getRetrieveQuestionRequest() {
        RetrieveQuestionRequest retrieveQuestionRequest = (RetrieveQuestionRequest) this
                .getServiceRequest(ServiceManager.RETRIEVE_QUESTION_REQUEST);
        retrieveQuestionRequest.getQuestionVO().setQuestionNumber(
                this.getQuestionNumber());
        retrieveQuestionRequest.getQuestionVO().setQuestionDesc(
                this.getQuestion());
        retrieveQuestionRequest.getQuestionVO().setVersion(this.getVersion());
        retrieveQuestionRequest.getQuestionVO().setStatus(this.getStatus());
        retrieveQuestionRequest.setCheckOutFlag(this.checkOutFlag);
        return retrieveQuestionRequest;
    }


    /**
     * Method for create and populate SaveQuestionRequest
     * 
     * @return SaveQuestionRequest saveQuestionRequest
     *  
     */
    private SaveQuestionRequest getSaveQuestionRequest() {
    	this.functionalDomainList = WPDStringUtil.getListFromTildaString(
				this.functionalDomain, 3, 2, 2);
        SaveQuestionRequest saveQuestionRequest = (SaveQuestionRequest) this
                .getServiceRequest(ServiceManager.SAVE_QUESTION_REQUEST);
        saveQuestionRequest.getQuestionVO().setQuestionNumber(
                this.getQuestionNumber());
        saveQuestionRequest.getQuestionVO().setQuestionDesc(
                this.getQuestion() != null ? this.getQuestion().toUpperCase().trim() : this
                        .getQuestion().toUpperCase());
        saveQuestionRequest.getQuestionVO().setDataTypeId(this.getDataTypeId());
        saveQuestionRequest.getQuestionVO().setVersion(this.getVersion());
        saveQuestionRequest.getQuestionVO().setStatus(this.getStatus());
        saveQuestionRequest.getQuestionVO().setAnswerList(this.getAnswerList());
        saveQuestionRequest.setUpdateFlag(this.isUpdateFlag());
        saveQuestionRequest.setCheckInFlag(this.checkInFlag);
        saveQuestionRequest.setValidationFlag(this.validationFlag);
        saveQuestionRequest.getQuestionVO().setPva(this.providerArrangement);
        saveQuestionRequest.getQuestionVO().setFunctionalDomainCDList(this.functionalDomainList);
        
        String[] terms = (this.getTerm()).split("~");
		String[] qualifiers = (this.getQualifier()).split("~");
				
		String termValue=terms[0];
		String qualifierValue=qualifiers[0];
		
		if(terms.length>2 && this.aggregateTerm==true)
			termValue=termValue+","+terms[2];
		
		if(qualifiers.length>2 && this.aggregateQualifier==true)
			qualifierValue=qualifierValue+","+qualifiers[2];
		
		String[] spsRef=this.spsRefernceId.split("~");
		
		String[] benefitLineDataTypes = (this.getBenefitLineDataType()).split("~");
		String bLineDataTypes="";
		if(benefitLineDataTypes.length > 1)
			bLineDataTypes=benefitLineDataTypes[1];
		for(int i=3;i<=benefitLineDataTypes.length;i=i+2){
			bLineDataTypes = bLineDataTypes +","+ benefitLineDataTypes[i];
		}
		saveQuestionRequest.getQuestionVO().setBenefitLineDataType(bLineDataTypes);
        saveQuestionRequest.getQuestionVO().setTerm(termValue);
        saveQuestionRequest.getQuestionVO().setQualifier(qualifierValue);
        saveQuestionRequest.getQuestionVO().setSpsReference(spsRef[0]);
        
        
        return saveQuestionRequest;
    }


    /**
     * Method for adding possible answer
     * 
     * @return String
     *  
     */
    public String addAnswer() {
    	       
        validationMessages = new ArrayList();
        boolean requiredField = false;
        boolean answerExistFlag = false;
        String possibleSelectedAnswer = null;
        if (this.updateFlag) {
            this.setBreadCrumbText("Administration >> Question ("
                    + this.question + ") >> Edit");
        }
        if (null != this.getPossibleAnswer())
            possibleSelectedAnswer = this.getPossibleAnswer().trim();
        if (null == possibleSelectedAnswer || "".equals(possibleSelectedAnswer)) {
            this.requiredAnswers = true;
            requiredField = true;
        }
        if (this.getDataTypeId() == 0 || this.getDataTypeId() == -1) {
            this.requiredDataType = true;
            requiredField = true;
        }
        List universeDataTypeList = null;
        String sysDataType = null;
        String dataTypeName = null;
        if (!requiredField) {
            universeDataTypeList = WPDStringUtil.getUniverseDataTypeList();
            DataTypeLocateResult dataTypeDetails = null;
            dataTypeDetails = WPDStringUtil.getDataTypeDetails(
                    universeDataTypeList, this.getDataTypeId());
            if (null != dataTypeDetails) {
                sysDataType = dataTypeDetails.getDataTypeDesc().toUpperCase()
                        .trim();
                dataTypeName = dataTypeDetails.getDataTypeName().toUpperCase()
                        .trim();
            }
            boolean isNumber = true;
            boolean isDecimalNumber = true;
            boolean isGreaterThanHundred = true;
            boolean isBooleanFlag = true;
            ErrorMessage errorMessage = null;
            boolean isMaxInteger = false;
            boolean isValidPrecision = true;

            if (null != possibleSelectedAnswer
                    && !"".equals(possibleSelectedAnswer)) {
                if (null != sysDataType) {
                    if ("BOOLEAN".equals(sysDataType.toUpperCase()))
                        isBooleanFlag = WPDStringUtil
                                .isValidBoolean(possibleSelectedAnswer);
                    else if ("DOLLAR".equals(sysDataType.toUpperCase())){
                        isNumber = WPDStringUtil
                                .isNumber(possibleSelectedAnswer);
                        if(isNumber){
                        	this.setPossibleAnswer(
								(WPDStringUtil.removeUnwantedZeroes
									(possibleSelectedAnswer)));
                        	possibleSelectedAnswer = this.getPossibleAnswer();
                        }
                    }
                    else if ("PERCENTAGE".equals(sysDataType.toUpperCase())) {
                    	
                    	
                    	if (possibleSelectedAnswer.trim().equals(".")) {
							isDecimalNumber = false;
						}
                    	else{
                    		
                    		if (possibleSelectedAnswer.charAt(0) == '.') {
								possibleSelectedAnswer = "0".concat(possibleSelectedAnswer);
							}
	                        isDecimalNumber = WPDStringUtil
	                                .isDecimalNumber(possibleSelectedAnswer);
	                        if (isDecimalNumber) {
	                            isValidPrecision = WPDStringUtil.isValidPrecision(
	                                    possibleSelectedAnswer,
	                                    WebConstants.ALLOWED_NUMBER_OF_PRECISION);
	                            possibleSelectedAnswer = WPDStringUtil
								.compareDecimal(possibleSelectedAnswer);
	                            if (isValidPrecision) {
	                                isGreaterThanHundred = WPDStringUtil
	                                        .isGreaterThanHundred(possibleSelectedAnswer);
	                                if(isGreaterThanHundred){
	                                	this.setPossibleAnswer(
	        								(WPDStringUtil.removeUnwantedZeroes
	        									(possibleSelectedAnswer)));
	                                	possibleSelectedAnswer = this.getPossibleAnswer();
	                                }
	                            }
	                        }
                    	}
                    } else if ("INTEGER".equals(sysDataType.toUpperCase())) {
                        isNumber = WPDStringUtil
                                .isNumber(possibleSelectedAnswer);
                        if (isNumber) {
                            isMaxInteger = WPDStringUtil
                                    .isMaxInteger(possibleSelectedAnswer);
                            if(!isMaxInteger){
                            	this.setPossibleAnswer(
    								(WPDStringUtil.removeUnwantedZeroes
    									(possibleSelectedAnswer)));
                            	possibleSelectedAnswer = this.getPossibleAnswer();
                            }
                        }
                    } else if ("FLOAT".equals(sysDataType.toUpperCase())) {
                        isDecimalNumber = WPDStringUtil
                                .isDecimalNumber(possibleSelectedAnswer);
                        if (isDecimalNumber) {
                            isValidPrecision = WPDStringUtil.isValidPrecision(
                                    possibleSelectedAnswer,
                                    WebConstants.ALLOWED_NUMBER_OF_PRECISION);
                            if(isValidPrecision){
                            	this.setPossibleAnswer(
    								(WPDStringUtil.removeUnwantedZeroes
    									(possibleSelectedAnswer)));
                            	possibleSelectedAnswer = this.getPossibleAnswer();
                            }
                        }
                    }
                }
            }

            if (!isBooleanFlag) {
                validationMessages.add(new ErrorMessage(
                        "dataType.mismatch.error.question"));
                addAllMessagesToRequest(validationMessages);
            }
            if (!isNumber) {
                errorMessage = new ErrorMessage("question.value.not.number");
                errorMessage.setParameters(new String[] { dataTypeName });
                validationMessages.add(errorMessage);
                addAllMessagesToRequest(validationMessages);
            }
            if (isMaxInteger) {
                errorMessage = new ErrorMessage("question.value.max.integer");
                if (null != dataTypeName)
                    errorMessage.setParameters(new String[] { dataTypeName });
                validationMessages.add(errorMessage);
                addAllMessagesToRequest(validationMessages);
            }
            if (!isDecimalNumber) {
                errorMessage = new ErrorMessage(
                        "question.value.not.decimalnumber");
                errorMessage.setParameters(new String[] { dataTypeName });
                validationMessages.add(errorMessage);
                addAllMessagesToRequest(validationMessages);
            }
            if (!isValidPrecision) {
                errorMessage = new ErrorMessage(
                        "question.value.valid.precision");
                if (null != dataTypeName)
                    errorMessage
                            .setParameters(new String[] {
                                    String
                                            .valueOf(WebConstants.ALLOWED_NUMBER_OF_PRECISION),
                                    dataTypeName });
                validationMessages.add(errorMessage);
                addAllMessagesToRequest(validationMessages);
            }
            if (!isGreaterThanHundred) {
                errorMessage = new ErrorMessage(
                        "question.value.greater.hundred");
                errorMessage.setParameters(new String[] { dataTypeName });
                validationMessages.add(errorMessage);
                addAllMessagesToRequest(validationMessages);
            }
            if (!isBooleanFlag || !isNumber || !isDecimalNumber
                    || !isGreaterThanHundred || !isValidPrecision
                    || isMaxInteger) {
                this.answerList = (List) this.getSession().getAttribute(
                        "possibleAnswerList");
                this.errorFlag = true;
                return "";
            }
        }

        if (requiredField) {
            validationMessages.add(new ErrorMessage(
                    WebConstants.MANDATORY_FIELDS_REQUIRED));
            addAllMessagesToRequest(validationMessages);
            this.answerList = (List) this.getSession().getAttribute(
                    "possibleAnswerList");
        } else {
            List possibleAnswerList = (List) this.getSession().getAttribute(
                    "possibleAnswerList");
            if (null == possibleAnswerList) {
                possibleAnswerList = new ArrayList();
                this.getSession().setAttribute("possibleAnswerList",
                        possibleAnswerList);
            }

            Iterator possibleAnswerListIterator = possibleAnswerList.iterator();

            while (possibleAnswerListIterator.hasNext()) {
                PossibleAnswerVO possibleAnswerVO = (PossibleAnswerVO) possibleAnswerListIterator
                        .next();
                if (null != sysDataType) {
                    if ("BOOLEAN".equals(sysDataType)) {
                        if (possibleSelectedAnswer.equalsIgnoreCase("y")
                                || possibleSelectedAnswer
                                        .equalsIgnoreCase("yes")) {
                            if ("yes".equalsIgnoreCase(possibleAnswerVO
                                    .getPossibleAnswerDesc())
                                    || "y".equalsIgnoreCase(possibleAnswerVO
                                            .getPossibleAnswerDesc())) {
                                answerExistFlag = true;
                            }
                        } else if (possibleSelectedAnswer.equalsIgnoreCase("n")
                                || possibleSelectedAnswer
                                        .equalsIgnoreCase("no")) {
                            if ("no".equalsIgnoreCase(possibleAnswerVO
                                    .getPossibleAnswerDesc())
                                    || "n".equalsIgnoreCase(possibleAnswerVO
                                            .getPossibleAnswerDesc())) {
                                answerExistFlag = true;
                            }
                        }
                    } else if (possibleSelectedAnswer
                            .equalsIgnoreCase(possibleAnswerVO
                                    .getPossibleAnswerDesc())) {
                        answerExistFlag = true;
                    }
                }
                if (answerExistFlag) {
                    validationMessages.add(new ErrorMessage(
                            WebConstants.ANSWER_EXIST));
                    addAllMessagesToRequest(validationMessages);
                    this.answerList = possibleAnswerList;
                    this.setPossibleAnswer("");
                    return "";
                }
            }
            PossibleAnswerVO possibleAnswerVO = new PossibleAnswerVO();
            possibleAnswerVO
                    .setPossibleAnswerDesc(this.getPossibleAnswer() != null ? this
                            .getPossibleAnswer().trim().toUpperCase()
                            : this.getPossibleAnswer());
            
            possibleAnswerList.add(possibleAnswerVO);
            this.answerList = possibleAnswerList;
            this.setPossibleAnswer("");
            valueChanged = 1;
        }
        
        return "";
    }


    /**
     * Method for deleting the possible answers
     * 
     * @return String
     *  
     */
    public String deleteAnswer() {
    	List AnswerList = new ArrayList();
    	String [] selectedAns = deleteAnswerIds.split("~");
    	if(selectedAns != null && selectedAns.length > 0) {
    		for(int i=0; i<selectedAns.length; i++) {
    			AnswerList.add(selectedAns[i]);
    		}
    	}
    	 valueChanged = 1;
        List possibleAnswerList = (List) this.getSession().getAttribute(
                "possibleAnswerList");
 
        if (null == possibleAnswerList) {
            possibleAnswerList = new ArrayList();
            this.getSession().setAttribute("possibleAnswerList",
                    possibleAnswerList);
        }
        if (this.updateFlag) {
            this.setBreadCrumbText("Product Configuration >> Question ("
                    + this.question + ") >> Edit");
        } 
       
        if(null != possibleAnswerList && !possibleAnswerList.isEmpty()){
        	//removing the answer from list 
        	for(int j=0;j<possibleAnswerList.size();j++){
        		PossibleAnswerVO possibleAnswerVO = (PossibleAnswerVO)possibleAnswerList.get(j);
        	 for(int i=0;i<AnswerList.size();i++){
        		if(AnswerList.get(i).equals(possibleAnswerVO.getPossibleAnswerDesc())){
        			possibleAnswerList.remove(j);
        			j--;
        			break;
        			
        		}
        	  }
        	}
        }
        this.getSession().setAttribute("possibleAnswerList",possibleAnswerList);
        this.answerList = possibleAnswerList;
        return "";
    }


    /**
     * Method for validating the fields
     * 
     * @return String
     *  
     */
    private boolean validateField() {
       
        validationMessages = new ArrayList();
        boolean flag = false;
        List possibleAnswersList = (List) (this.getSession()
                .getAttribute("possibleAnswerList"));
        boolean requiredField = false;
        String possibleSelectedAnswer = null;
        
        if (null != this.getPossibleAnswer())
            possibleSelectedAnswer = this.getPossibleAnswer().trim();
        if (this.getQuestion() == null || this.getQuestion().trim().equals("")) {
            requiredQuestion = true;
            requiredField = true;
        }
        if (this.getDataTypeId() == -1) {
            requiredDataType = true;
            requiredField = true;
        }
        if (possibleSelectedAnswer == null || "".equals(possibleSelectedAnswer)) {
            if (null != possibleAnswersList && possibleAnswersList.size() == 0
                    || null == possibleAnswersList) {
                requiredAnswers = true;
                requiredField = true;
            }
        }
        
        if (this.getQuestion() != null && !this.getQuestion().trim().equals("")) {
            if(this.getQuestion().trim().length() > 250){
            	validationMessages.add(new ErrorMessage(
                        WebConstants.QUESTIONS_LENGTH_INVALID));
            	return false;
            }
            
        }
        if (this.getFunctionalDomain() == null || this.functionalDomain.trim().equals("")) {
            domainInvalid = true;
            requiredField = true;
        }else {
        	this.functionalDomainList = WPDStringUtil.getListFromTildaString(
    				this.functionalDomain, 3, 2, 2);
        	Iterator itr = functionalDomainList.iterator();
        	while(itr.hasNext()){
        		String fDomain = itr.next().toString();
        		if(fDomain.equalsIgnoreCase("C-ACCUM")){
        			flag = true;
        			adjudAccumSelected = true;
        		}
        	}
        }
        if(flag){
        	if (this.getTerm() == null || this.getTerm().trim().equals("")) {
                requiredTerm = true;
                requiredField = true;
            }
        	
        	if (this.getQualifier() == null || this.getQualifier().trim().equals("")) {
                requiredQualifier = true;
                requiredField = true;
            }
        	
        	if (this.getProviderArrangement() == null || this.getProviderArrangement().trim().equals("")) {
                requiredPVA = true;
                requiredField = true;
            }
        	
        	if (this.getBenefitLineDataType() == null || this.getBenefitLineDataType().trim().equals("")) {
        		requiredBenefitLineDataType = true;
                requiredField = true;
            }
        }
        if (requiredField) {
            validationMessages.add(new ErrorMessage(
                    WebConstants.MANDATORY_FIELDS_REQUIRED));
            return false;
        }
        return true;
    }


    /**
     * Method for clearing the answer list
     * 
     * @return String
     *  
     */
    public String clearAnswersList() {
        this.possibleAnswerList = null;
        this.getSession().setAttribute("possibleAnswerList", null);
        this.setValueChanged(1);
        setBreadCrumbText("Product Configuration >> Question (" + this.question
                + ") >> Edit");
        return "";
    }


    /**
     * Returns the answerList
     * 
     * @return List answerList.
     */
    public List getAnswerList() {
        if (this.answerList != null && this.answerList.size() == 0)
            return null;

        return answerList;
    }


    /**
     * Sets the answerList
     * 
     * @param answerList.
     */
    public void setAnswerList(List answerList) {
        this.answerList = answerList;
    }


    /**
     * Returns the validationMessages
     * 
     * @return List validationMessages.
     */
    public List getValidationMessages() {
        return validationMessages;
    }


    /**
     * Sets the validationMessages
     * 
     * @param validationMessages.
     */
    public void setValidationMessages(List validationMessages) {
        this.validationMessages = validationMessages;
    }


    /**
     * Returns the answer
     * 
     * @return String answer.
     */
    public String getAnswer() {
        return answer;
    }


    /**
     * Sets the answer
     * 
     * @param answer.
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }


    /**
     * Returns the dataType
     * 
     * @return String dataType.
     */
    public String getDataType() {
        return dataType;
    }


    /**
     * Sets the dataType
     * 
     * @param dataType.
     */
    public void setDataType(String dataType) {
        this.dataType = dataType;
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
     * Returns the requiredAnswers
     * 
     * @return boolean requiredAnswers.
     */
    public boolean isRequiredAnswers() {
        return requiredAnswers;
    }


    /**
     * Sets the requiredAnswers
     * 
     * @param requiredAnswers.
     */
    public void setRequiredAnswers(boolean requiredAnswers) {
        this.requiredAnswers = requiredAnswers;
    }


    /**
     * Returns the requiredDataType
     * 
     * @return boolean requiredDataType.
     */
    public boolean isRequiredDataType() {
        return requiredDataType;
    }


    /**
     * Sets the requiredDataType
     * 
     * @param requiredDataType.
     */
    public void setRequiredDataType(boolean requiredDataType) {
        this.requiredDataType = requiredDataType;
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
     * Returns the possibleAnswer
     * 
     * @return String possibleAnswer.
     */
    public String getPossibleAnswer() {
        return possibleAnswer;
    }


    /**
     * Sets the possibleAnswer
     * 
     * @param possibleAnswer.
     */
    public void setPossibleAnswer(String possibleAnswer) {
        this.possibleAnswer = possibleAnswer;
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
     * Returns the deletedAnswer
     * 
     * @return String deletedAnswer.
     */
    public int getDeletedAnswer() {
        return deletedAnswer;
    }


    /**
     * Sets the deletedAnswer
     * 
     * @param deletedAnswer.
     */
    public void setDeletedAnswer(int deletedAnswer) {
        this.deletedAnswer = deletedAnswer;
    }


    /**
     * Returns the updateFlag
     * 
     * @return boolean updateFlag.
     */
    public boolean isUpdateFlag() {
        return updateFlag;
    }


    /**
     * Sets the updateFlag
     * 
     * @param updateFlag.
     */
    public void setUpdateFlag(boolean updateFlag) {
        this.updateFlag = updateFlag;
    }


    /**
     * Returns the dataTypeId.
     * 
     * @return
     */
    public int getDataTypeId() {
        return dataTypeId;
    }


    /**
     * Sets the dataTypeId
     * 
     * @param dataTypeId
     */
    public void setDataTypeId(int dataTypeId) {
        this.dataTypeId = dataTypeId;
    }


    /**
     * Returns the possibleAnswerList
     * 
     * @return List possibleAnswerList.
     */
    public List getPossibleAnswerList() {
        return possibleAnswerList;
    }


    /**
     * Sets the possibleAnswerList
     * 
     * @param possibleAnswerList.
     */
    public void setPossibleAnswerList(List possibleAnswerList) {
        this.possibleAnswerList = possibleAnswerList;
    }


    /**
     * Returns the version
     * 
     * @return int version.
     */
    public int getVersion() {
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
     * Returns the createdDate
     * 
     * @return String createdDate.
     */
    public Date getCreatedDate() {
        return createdDate;
    }


    /**
     * Sets the createdDate
     * 
     * @param createdDate.
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


    /**
     * Returns the createdUserId
     * 
     * @return String createdUserId.
     */
    public String getCreatedUserId() {
        return createdUserId;
    }


    /**
     * Sets the createdUserId
     * 
     * @param createdUserId.
     */
    public void setCreatedUserId(String createdUserId) {
        this.createdUserId = createdUserId;
    }


    /**
     * Returns the updatedDate
     * 
     * @return String updatedDate.
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }


    /**
     * Sets the updatedDate
     * 
     * @param updatedDate.
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }


    /**
     * Returns the updatedUserId
     * 
     * @return String updatedUserId.
     */
    public String getUpdatedUserId() {
        return updatedUserId;
    }


    /**
     * Sets the updatedUserId
     * 
     * @param updatedUserId.
     */
    public void setUpdatedUserId(String updatedUserId) {
        this.updatedUserId = updatedUserId;
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
     * Returns the checkInFlag
     * 
     * @return boolean checkInFlag.
     */
    public boolean isCheckInFlag() {
        return checkInFlag;
    }


    /**
     * Sets the checkInFlag
     * 
     * @param checkInFlag.
     */
    public void setCheckInFlag(boolean checkInFlag) {
        this.checkInFlag = checkInFlag;
    }


    /**
     * Returns the checkOutFlag
     * 
     * @return boolean checkOutFlag.
     */
    public boolean isCheckOutFlag() {
        return checkOutFlag;
    }


    /**
     * Sets the checkOutFlag
     * 
     * @param checkOutFlag.
     */
    public void setCheckOutFlag(boolean checkOutFlag) {
        this.checkOutFlag = checkOutFlag;
    }


    /**
     * Returns the stateObj
     * 
     * @return State stateObj.
     */
    public State getStateObj() {
        return stateObj;
    }


    /**
     * Sets the stateObj
     * 
     * @param stateObj.
     */
    public void setStateObj(State stateObj) {
        this.stateObj = stateObj;
    }


    /**
     * Returns the validationFlag
     * 
     * @return boolean validationFlag.
     */
    public boolean isValidationFlag() {
        return validationFlag;
    }


    /**
     * Sets the validationFlag
     * 
     * @param validationFlag.
     */
    public void setValidationFlag(boolean validationFlag) {
        this.validationFlag = validationFlag;
    }


    /**
     * Returns the breadCrumpCreate
     * 
     * @return String breadCrumpCreate.
     */
    public String getBreadCrumpCreate() {
        this.setBreadCrumbText("Administration >> Question >> Create");
        return breadCrumpCreate;
    }


    /**
     * Sets the breadCrumpCreate
     * 
     * @param breadCrumpCreate.
     */
    public void setBreadCrumpCreate(String breadCrumpCreate) {
        this.breadCrumpCreate = breadCrumpCreate;
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
	 * @return Returns the deleteAnswerIds.
	 */
	public String getDeleteAnswerIds() {
		return deleteAnswerIds;
	}
	
	
	/**
	 * @param deleteAnswerIds The deleteAnswerIds to set.
	 */
	public void setDeleteAnswerIds(String deleteAnswerIds) {
		this.deleteAnswerIds = deleteAnswerIds;
	}
	/**
	 * @return Returns the providerArrangement.
	 */
	public String getProviderArrangement() {
		return providerArrangement;
	}
	/**
	 * @param providerArrangement The providerArrangement to set.
	 */
	public void setProviderArrangement(String providerArrangement) {
		this.providerArrangement = providerArrangement;
	}
	/**
	 * @return Returns the domainInvalid.
	 */
	public boolean isDomainInvalid() {
		return domainInvalid;
	}
	/**
	 * @param domainInvalid The domainInvalid to set.
	 */
	public void setDomainInvalid(boolean domainInvalid) {
		this.domainInvalid = domainInvalid;
	}


	/**
	 * @return Returns the functionalDomain.
	 */
	public String getFunctionalDomain() {
		return functionalDomain;
	}
	/**
	 * @param functionalDomain The functionalDomain to set.
	 */
	public void setFunctionalDomain(String functionalDomain) {
		this.functionalDomain = functionalDomain;
	}
	/**
	 * @return Returns the functionalDomainList.
	 */
	public List getFunctionalDomainList() {
		return functionalDomainList;
	}
	/**
	 * @param functionalDomainList The functionalDomainList to set.
	 */
	public void setFunctionalDomainList(List functionalDomainList) {
		this.functionalDomainList = functionalDomainList;
	}
	public boolean isAggregateQualifier() {
		return aggregateQualifier;
	}
	public void setAggregateQualifier(boolean aggregateQualifier) {
		this.aggregateQualifier = aggregateQualifier;
	}
	public boolean isAggregateTerm() {
		return aggregateTerm;
	}
	public void setAggregateTerm(boolean aggregateTerm) {
		this.aggregateTerm = aggregateTerm;
	}
	public String getSpsRefernceId() {
		return spsRefernceId;
	}
	public void setSpsRefernceId(String spsRefernceId) {
		this.spsRefernceId = spsRefernceId;
	}
	public boolean isReferenceInvalid() {
		return referenceInvalid;
	}
	public void setReferenceInvalid(boolean referenceInvalid) {
		this.referenceInvalid = referenceInvalid;
	}
	public boolean isRequiredReferenceDataType() {
		return requiredReferenceDataType;
	}
	public void setRequiredReferenceDataType(boolean requiredReferenceDataType) {
		this.requiredReferenceDataType = requiredReferenceDataType;
	}
	public String getQualifier() {
		return qualifier;
	}
	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}
	public String getSpsDataType() {
		return spsDataType;
	}
	public void setSpsDataType(String spsDataType) {
		this.spsDataType = spsDataType;
	}
	public String getTerm() {
		
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public boolean isRequiredQualifier() {
		return requiredQualifier;
	}
	public void setRequiredQualifier(boolean requiredQualifier) {
		this.requiredQualifier = requiredQualifier;
	}
	public boolean isRequiredTerm() {
		return requiredTerm;
	}
	public void setRequiredTerm(boolean requiredTerm) {
		this.requiredTerm = requiredTerm;
	}
	public String getBenefitLineDataType() {
		return benefitLineDataType;
	}
	public void setBenefitLineDataType(String benefitLineDataType) {
		this.benefitLineDataType = benefitLineDataType;
	}


	public boolean isRequiredPVA() {
		return requiredPVA;
	}


	public void setRequiredPVA(boolean requiredPVA) {
		this.requiredPVA = requiredPVA;
	}
	
	public boolean isRequiredBenefitLineDataType() {
		return requiredBenefitLineDataType;
	}

	public void setRequiredBenefitLineDataType(boolean requiredBenefitLineDataType) {
		this.requiredBenefitLineDataType = requiredBenefitLineDataType;
	}


	public boolean isAdjudAccumSelected() {
		return adjudAccumSelected;
	}


	public void setAdjudAccumSelected(boolean adjudAccumSelected) {
		this.adjudAccumSelected = adjudAccumSelected;
	}
	
	
}