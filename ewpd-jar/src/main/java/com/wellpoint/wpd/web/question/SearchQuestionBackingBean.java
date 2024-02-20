/*
 * SearchQuestionBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.web.question;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;

import org.owasp.esapi.ESAPI;

import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.question.bo.FunctionalDomainBO;
import com.wellpoint.wpd.common.question.bo.QuestionBO;
import com.wellpoint.wpd.common.question.request.CheckOutQuestionRequest;
import com.wellpoint.wpd.common.question.request.DeleteQuestionRequest;
import com.wellpoint.wpd.common.question.request.PublishQuestionRequest;
import com.wellpoint.wpd.common.question.request.QuestionViewRequest;
import com.wellpoint.wpd.common.question.request.ScheduleForTestQuestionRequest;
import com.wellpoint.wpd.common.question.request.SearchQuestionRequest;
import com.wellpoint.wpd.common.question.request.TestFailQuestionRequest;
import com.wellpoint.wpd.common.question.request.TestPassQuestionRequest;
import com.wellpoint.wpd.common.question.request.UnlockQuestionRequest;
import com.wellpoint.wpd.common.question.response.CheckOutQuestionResponse;
import com.wellpoint.wpd.common.question.response.DeleteQuestionResponse;
import com.wellpoint.wpd.common.question.response.PublishQuestionResponse;
import com.wellpoint.wpd.common.question.response.QuestionViewResponse;
import com.wellpoint.wpd.common.question.response.ScheduleForTestQuestionResponse;
import com.wellpoint.wpd.common.question.response.SearchQuestionResponse;
import com.wellpoint.wpd.common.question.response.TestFailQuestionResponse;
import com.wellpoint.wpd.common.question.response.TestPassQuestionResponse;
import com.wellpoint.wpd.common.question.response.UnlockQuestionResponse;
import com.wellpoint.wpd.common.question.vo.QuestionSearchCriteriaVO;
import com.wellpoint.wpd.common.question.vo.QuestionVO;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * Backing bean for Question Search and related functions
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SearchQuestionBackingBean extends WPDBackingBean {

	private String criteriaQuestion = null;

	private int questionNumber;

	private String questionDesc;

	private String status;

	List messages = null;

	private String criteriaDataType = null;

	private List questionSearchResultList = null;

	private List validationMessages = null;

	private boolean requiredField = false;

	private int dataTypeId;

	private String dataType;
	
	private String benefitLineDataType;

	private String answer;

	private List answerList;

	private List questionsList;

	private static String VIEW_PAGE = "view";

	private static String VIEW_ALL = "viewAll";

	private int version = -1;

	private String createdUser;

	private Date createdDate;

	private String updatedUserId;

	private Date updatedDate;

	private String state;

	private static final String VIEW_ALL_PAGE = "viewAllPage";

	private String lastPage;

	private static final int MAX_SEARCH_RESULT_SIZE = 50;

	private String questionSearchPrint;

	private boolean isQuestionPrint;

	private String breadCrumbPrint;

	private int questionNumberParentSysId;

	private String providerArrangement;

	private String functionalDomain;

	private List functionalDomainList;

	private String spsRefernceId;

	private String qualifier;

	private String term;

	private String spsDataType;

	/**
	 * @return isQuestionPrint
	 * 
	 * Returns the isQuestionPrint.
	 */
	public boolean isQuestionPrint() {
		return isQuestionPrint;
	}

	/**
	 * @param isQuestionPrint
	 * 
	 * Sets the isQuestionPrint.
	 */
	public void setQuestionPrint(boolean isQuestionPrint) {
		this.isQuestionPrint = isQuestionPrint;
	}

	/**
	 * @return questionSearchPrint
	 * 
	 * Returns the questionSearchPrint.
	 */
	public String getQuestionSearchPrint() {
		this.setQuestionPrint(true);
		SearchQuestionRequest searchQuestionRequest = null;
		SearchQuestionResponse searchQuestionResponse = null;
		// Create the Request Object
		searchQuestionRequest = getSearchQuestionRequest();
		List questionSearchList = null;
		// Call the service method
		searchQuestionResponse = (SearchQuestionResponse) this
				.executeService(searchQuestionRequest);
		if (null != searchQuestionResponse) {

			questionSearchList = searchQuestionResponse
					.getQuestionSearchResultsList();
			if (null != questionSearchList) {
				Iterator iterator = questionSearchList.iterator();
				List newQuestionSearchList = new ArrayList();
				while (iterator.hasNext()) {
					QuestionBO questionBO = (QuestionBO) iterator.next();
					List functionalDomainList = questionBO
							.getFunctionalDomainList();
					questionBO
							.setDomainDesc(WPDStringUtil
									.getCommaSeperatedValuesFromList(functionalDomainList));
					String dunctionalDomainDesc = null;
					// getting the functional domain corresponding to each question
				    if (functionalDomainList != null
								&& !functionalDomainList.isEmpty()) {	    
						Iterator domainListIterator = functionalDomainList
								.iterator();
						List domainList = new ArrayList();
						while (domainListIterator.hasNext()) {
							FunctionalDomainBO domainBO = (FunctionalDomainBO) domainListIterator
									.next();
							dunctionalDomainDesc = domainBO
									.getFunctionalDomainDesc();
							domainList.add(dunctionalDomainDesc);
						}
						this.functionalDomain = WPDStringUtil
								.getCommaSeperatedValuesFromList(domainList);
						questionBO.setDomainDesc(this.functionalDomain);
						domainList.clear();
					}

					if ((!"".equals(questionBO.getQuestionDesc()))
							|| null != questionBO.getQuestionDesc()) {
						if (questionBO.getQuestionDesc().length() > 30) {
							questionBO.setDisplayQuestion(questionBO
									.getQuestionDesc().substring(0, 30)
									+ "...");
						} else
							questionBO.setDisplayQuestion(questionBO
									.getQuestionDesc());
					}

					newQuestionSearchList.add(questionBO);
				}
				if (newQuestionSearchList.size() > 0)
					this.setQuestionSearchResultList(newQuestionSearchList);
				else
					this.setQuestionSearchResultList(questionSearchList);
			}
		}
		return questionSearchPrint;
	}

	/**
	 * @param questionSearchPrint
	 * 
	 * Sets the questionSearchPrint.
	 */
	public void setQuestionSearchPrint(String questionSearchPrint) {
		this.questionSearchPrint = questionSearchPrint;
	}

	/**
	 * Returns the messages.
	 * 
	 * @return messages
	 */
	public List getMessages() {
		return messages;
	}

	/**
	 * The messages to set.
	 * 
	 * @param messages
	 */
	public void setMessages(List messages) {
		this.messages = messages;
	}

	/**
	 * Returns the questionNumber.
	 * 
	 * @return
	 */
	public int getQuestionNumber() {
		return questionNumber;
	}

	/**
	 * The questionNumber to set.
	 * 
	 * @param questionNumber
	 */
	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}

	/**
	 * Returns the dataTypeId.
	 * 
	 * @return dataTypeId
	 */
	public int getDataTypeId() {
		return dataTypeId;
	}

	/**
	 * The dataTypeId to set.
	 * 
	 * @param dataTypeId
	 */
	public void setDataTypeId(int dataTypeId) {
		this.dataTypeId = dataTypeId;
	}

	/**
	 * Constructor
	 */
	public SearchQuestionBackingBean() {
		this.setBreadCrump();
	}

	/**
	 * Sets the breadcrump
	 *  
	 */
	protected void setBreadCrump() {
		this.setBreadCrumbText("Administration >> Question >> Locate");
	}

	/**
	 * Returns the criteriaQuestion.
	 * 
	 * @return criteriaQuestion
	 */
	public String getCriteriaQuestion() {
		return criteriaQuestion;
	}

	/**
	 * The criteriaQuestion to set.
	 * 
	 * @param criteriaQuestion
	 */
	public void setCriteriaQuestion(String criteriaQuestion) {
		this.criteriaQuestion = criteriaQuestion;
	}

	/**
	 * Returns the questionSearchResultList.
	 * 
	 * @return questionSearchResultList
	 */
	public List getQuestionSearchResultList() {
		if (this.questionSearchResultList != null
				&& this.questionSearchResultList.size() == 0)
			return null;
		return questionSearchResultList;
	}

	/**
	 * The questionSearchResultList to set.
	 * 
	 * @param questionSearchResultList
	 */
	public void setQuestionSearchResultList(List questionSearchResultList) {
		this.questionSearchResultList = questionSearchResultList;
	}

	/**
	 * @return String
	 */
	public String editQuestion() {
		return "";
	}

	/**
	 * Method for publish
	 * 
	 * @return String
	 */
	public String publish() {
		String returnMessage = null;
		InformationalMessage informationalMessage = null;
		PublishQuestionRequest publishQuestionRequest = null;
		PublishQuestionResponse publishQuestionResponse = null;
		// Create the Request Object
		publishQuestionRequest = getPublishQuestionRequest();
		// Call the service method
		publishQuestionResponse = (PublishQuestionResponse) this
				.executeService(publishQuestionRequest);
		if (null != publishQuestionResponse) {
			this.setMessages(publishQuestionResponse.getMessages());
			if (VIEW_ALL_PAGE.equals(this.lastPage)) {
				returnMessage = this.getViewQuestion();
			} else {
				returnMessage = this.searchQuestion();
			}
			addAllMessagesToRequest(this.messages);
		}
		return returnMessage;
	}

	/**
	 * Method for unlock
	 * 
	 * @return String
	 */
	public String unlock() {
		String returnMessage = null;
		InformationalMessage informationalMessage = null;
		UnlockQuestionRequest unlockQuestionRequest = null;
		UnlockQuestionResponse unlockQuestionResponse = null;
		// Create the Request Object
		unlockQuestionRequest = getUnlockQuestionRequest();
		// Call the service method
		unlockQuestionResponse = (UnlockQuestionResponse) this
				.executeService(unlockQuestionRequest);
		if (null != unlockQuestionResponse) {
			this.setMessages(unlockQuestionResponse.getMessages());
			if (VIEW_ALL_PAGE.equals(this.lastPage)) {
				returnMessage = this.getViewQuestion();
			} else {
				returnMessage = this.searchQuestion();
			}
			addAllMessagesToRequest(this.messages);
		}
		return returnMessage;
	}

	/**
	 * 
	 * @return PublishQuestionRequest
	 */
	private PublishQuestionRequest getPublishQuestionRequest() {
		PublishQuestionRequest publishQuestionRequest = (PublishQuestionRequest) this
				.getServiceRequest(ServiceManager.PUBLISH_QUESTION_REQUEST);
		publishQuestionRequest.getQuestionVO().setQuestionNumber(
				this.getQuestionNumber());
		publishQuestionRequest.getQuestionVO().setQuestionDesc(
				this.getQuestionDesc());
		publishQuestionRequest.getQuestionVO().setVersion(this.getVersion());
		return publishQuestionRequest;
	}

	/**
	 * 
	 * @return UnlockQuestionRequest
	 */
	private UnlockQuestionRequest getUnlockQuestionRequest() {
		UnlockQuestionRequest unlockQuestionRequest = (UnlockQuestionRequest) this
				.getServiceRequest(ServiceManager.UNLOCK_QUESTION_REQUEST);
		unlockQuestionRequest.getQuestionVO().setQuestionNumber(
				this.getQuestionNumber());
		unlockQuestionRequest.getQuestionVO().setQuestionDesc(
				this.getQuestionDesc());
		unlockQuestionRequest.getQuestionVO().setVersion(this.getVersion());
		return unlockQuestionRequest;
	}

	/**
	 * Method to schedule for test
	 * 
	 * @return String
	 */
	public String scheduleTest() {
		String returnMessage = null;
		InformationalMessage informationalMessage = null;
		ScheduleForTestQuestionRequest scheduleForTestQuestionRequest = null;
		ScheduleForTestQuestionResponse scheduleForTestQuestionResponse = null;
		// Create the Request Object
		scheduleForTestQuestionRequest = getScheduleForTestQuestionRequest();
		// Call the service method
		scheduleForTestQuestionResponse = (ScheduleForTestQuestionResponse) this
				.executeService(scheduleForTestQuestionRequest);
		if (null != scheduleForTestQuestionResponse) {
			this.setMessages(scheduleForTestQuestionResponse.getMessages());
			if (VIEW_ALL_PAGE.equals(this.lastPage)) {
				returnMessage = this.getViewQuestion();
			} else {
				returnMessage = this.searchQuestion();
			}
			addAllMessagesToRequest(messages);
		}
		return returnMessage;
	}

	/**
	 * Method for getting request for schedule test question
	 * 
	 * @return ScheduleForTestQuestionRequest
	 */
	private ScheduleForTestQuestionRequest getScheduleForTestQuestionRequest() {
		ScheduleForTestQuestionRequest scheduleForTestQuestionRequest = (ScheduleForTestQuestionRequest) this
				.getServiceRequest(ServiceManager.SCHEDULEFORTEST_QUESTION_REQUEST);
		scheduleForTestQuestionRequest.getQuestionVO().setQuestionNumber(
				this.getQuestionNumber());
		scheduleForTestQuestionRequest.getQuestionVO().setQuestionDesc(
				this.getQuestionDesc());
		scheduleForTestQuestionRequest.getQuestionVO().setVersion(
				this.getVersion());
		return scheduleForTestQuestionRequest;
	}

	/**
	 * Method for test pass
	 * 
	 * @return String
	 */
	public String testPass() {
		String returnMessage = null;
		InformationalMessage informationalMessage = null;
		TestPassQuestionRequest testPassQuestionRequest = null;
		TestPassQuestionResponse testPassQuestionResponse = null;
		// Create the Request Object
		testPassQuestionRequest = getTestPassQuestionRequest();
		// Call the service method
		testPassQuestionResponse = (TestPassQuestionResponse) this
				.executeService(testPassQuestionRequest);
		if (null != testPassQuestionResponse) {
			this.setMessages(testPassQuestionResponse.getMessages());
			if (VIEW_ALL_PAGE.equals(this.lastPage)) {
				returnMessage = this.getViewQuestion();
			} else {
				returnMessage = this.searchQuestion();
			}
			addAllMessagesToRequest(this.messages);
		}
		return returnMessage;
	}

	/**
	 * Method for test fail
	 * 
	 * @return String
	 */
	public String testFail() {
		String returnMessage = null;
		InformationalMessage informationalMessage = null;
		TestFailQuestionRequest testFailQuestionRequest = null;
		TestFailQuestionResponse testFailQuestionResponse = null;
		// Create the Request Object
		testFailQuestionRequest = getTestFailQuestionRequest();
		// Call the service method
		testFailQuestionResponse = (TestFailQuestionResponse) this
				.executeService(testFailQuestionRequest);
		if (null != testFailQuestionResponse) {
			this.setMessages(testFailQuestionResponse.getMessages());
			if (VIEW_ALL_PAGE.equals(this.lastPage)) {
				returnMessage = this.getViewQuestion();
			} else {
				returnMessage = this.searchQuestion();
			}
			addAllMessagesToRequest(messages);
		}
		return returnMessage;
	}

	/**
	 * 
	 * @return TestPassQuestionRequest
	 */
	private TestPassQuestionRequest getTestPassQuestionRequest() {
		TestPassQuestionRequest testPassQuestionRequest = (TestPassQuestionRequest) this
				.getServiceRequest(ServiceManager.TEST_PASS_QUESTION_REQUEST);
		testPassQuestionRequest.getQuestionVO().setQuestionNumber(
				this.getQuestionNumber());
		testPassQuestionRequest.getQuestionVO().setQuestionDesc(
				this.getQuestionDesc());
		testPassQuestionRequest.getQuestionVO().setVersion(this.getVersion());
		return testPassQuestionRequest;
	}

	/**
	 * 
	 * @return TestFailQuestionRequest
	 */
	private TestFailQuestionRequest getTestFailQuestionRequest() {
		TestFailQuestionRequest testFailQuestionRequest = (TestFailQuestionRequest) this
				.getServiceRequest(ServiceManager.TEST_FAIL_QUESTION_REQUEST);
		testFailQuestionRequest.getQuestionVO().setQuestionNumber(
				this.getQuestionNumber());
		testFailQuestionRequest.getQuestionVO().setQuestionDesc(
				this.getQuestionDesc());
		testFailQuestionRequest.getQuestionVO().setVersion(this.getVersion());
		testFailQuestionRequest.getQuestionVO().setStatus(this.getStatus());
		return testFailQuestionRequest;
	}

	/**
	 * Method to delete Question
	 * 
	 * @return String
	 */
	public String deleteQuestion() {
		String returnMessage = null;
		InformationalMessage informationalMessage = null;
		DeleteQuestionRequest deleteQuestionRequest = null;
		DeleteQuestionResponse deleteQuestionResponse = null;
		// Create the Request Object
		deleteQuestionRequest = getDeleteQuestionRequest();
		// Call the service method
		deleteQuestionResponse = (DeleteQuestionResponse) this
				.executeService(deleteQuestionRequest);
		if (null != deleteQuestionResponse) {
			this.setMessages(deleteQuestionResponse.getMessages());
			if (VIEW_ALL_PAGE.equals(this.lastPage)) {
				returnMessage = this.getViewQuestion();
			} else {
				returnMessage = this.searchQuestion();
			}
			addAllMessagesToRequest(messages);
		}
		return returnMessage;
	}

	/**
	 * Returns the DeleteQuestionRequest
	 * 
	 * @return DeleteQuestionRequest
	 */
	private DeleteQuestionRequest getDeleteQuestionRequest() {
		DeleteQuestionRequest deleteQuestionRequest = (DeleteQuestionRequest) this
				.getServiceRequest(ServiceManager.DELETE_QUESTION_REQUEST);
		deleteQuestionRequest.getQuestionVO().setQuestionNumber(
				this.getQuestionNumber());
		deleteQuestionRequest.getQuestionVO().setQuestionDesc(
				this.getQuestionDesc());
		deleteQuestionRequest.getQuestionVO().setVersion(this.getVersion());
		return deleteQuestionRequest;
	}

	/**
	 * Method to search the Question
	 * 
	 * @return String to navigate to another page
	 */
	public String searchQuestion() {
		if (validateRequiredFields()) {
			validationMessages = new ArrayList();
			validationMessages.add(new ErrorMessage(
					WebConstants.ATLEAST_ONE_SEARCH));
			addAllMessagesToRequest(validationMessages);
			return "";
		}
		List questionSearchList = null;
		SearchQuestionRequest searchQuestionRequest = null;
		SearchQuestionResponse searchQuestionResponse = null;
		// Create the Request Object
		searchQuestionRequest = getSearchQuestionRequest();
		// Call the service method
		searchQuestionResponse = (SearchQuestionResponse) this
				.executeService(searchQuestionRequest);
		if (null != searchQuestionResponse) {

			questionSearchList = searchQuestionResponse
					.getQuestionSearchResultsList();
			if (null != questionSearchList) {
				Iterator iterator = questionSearchList.iterator();
				List newQuestionSearchList = new ArrayList();
				List newFunctionalDomainList = new ArrayList();
				String dunctionalDomainDesc = null;
				while (iterator.hasNext()) {
					QuestionBO questionBO = (QuestionBO) iterator.next();
					List functionalDomainList = questionBO
							.getFunctionalDomainList();
					/*questionBO
							.setDomainDesc(WPDStringUtil
									.getCommaSeperatedValuesFromList(functionalDomainList));*/
					// getting the functional domain corresponding to each question
					if (null != functionalDomainList
							&& !functionalDomainList.isEmpty()) {
						Iterator domainListIterator = functionalDomainList
								.iterator();
						List domainList = new ArrayList();
						while (domainListIterator.hasNext()) {
							FunctionalDomainBO domainBO = (FunctionalDomainBO) domainListIterator
									.next();
							dunctionalDomainDesc = domainBO
									.getFunctionalDomainDesc();
							domainList.add(dunctionalDomainDesc);
						}
						questionBO.setDomainDesc(WPDStringUtil
								.getCommaSeperatedValuesFromList(domainList));
						domainList.clear();
					}
					if(null != questionBO.getBenefitLineDataType())
					{
						this.benefitLineDataType=questionBO.getBenefitLineDataType();
					}

					if ((!"".equals(questionBO.getQuestionDesc()))
							|| null != questionBO.getQuestionDesc()) {
						if (questionBO.getQuestionDesc().length() > 30) {
							questionBO.setDisplayQuestion(questionBO
									.getQuestionDesc().substring(0, 30)
									+ "...");
						} else
							questionBO.setDisplayQuestion(questionBO
									.getQuestionDesc());
					}

					newQuestionSearchList.add(questionBO);
				}
				if (newQuestionSearchList.size() > 0)
					this.setQuestionSearchResultList(newQuestionSearchList);
				else
					this.setQuestionSearchResultList(questionSearchList);
			}
		}
		return "searchQuestionPage";
	}

	/**
	 * method for getting request for question search
	 * 
	 * @return SearchQuestionRequest
	 */
	private SearchQuestionRequest getSearchQuestionRequest() {
		List dataTypeList = null;
		List functionalDomainList = null;
		List spsRefList = null;
		SearchQuestionRequest searchQuestionRequest = (SearchQuestionRequest) this
				.getServiceRequest(ServiceManager.SEARCH_QUESTION_REQUEST);
		searchQuestionRequest.getQuestionSearchCriteriaVO()
				.setMaxLocateResultSize(MAX_SEARCH_RESULT_SIZE);
		if (!isQuestionPrint()) {
			dataTypeList = getDataTypeList();
			functionalDomainList = WPDStringUtil.getListFromTildaString(
					this.functionalDomain, 3, 2, 2);
			spsRefList = WPDStringUtil.getListFromTildaString(
					this.spsRefernceId, 2, 1, 2);
			searchQuestionRequest.getQuestionSearchCriteriaVO()
					.setDataTypeList(dataTypeList);
			searchQuestionRequest.getQuestionSearchCriteriaVO()
					.setQuestionDesc(this.getCriteriaQuestion().trim());
			searchQuestionRequest.getQuestionSearchCriteriaVO()
					.setFunctioanlDomainList(functionalDomainList);

			searchQuestionRequest.getQuestionSearchCriteriaVO()
					.setSpsReference(spsRefList);

			getRequest().getSession().removeAttribute(
					"QuestionSearchCriteriaVO");
			getRequest().getSession().setAttribute("QuestionSearchCriteriaVO",
					searchQuestionRequest.getQuestionSearchCriteriaVO());

		} else {
			if (null != getRequest().getSession().getAttribute(
					"QuestionSearchCriteriaVO")) {
				searchQuestionRequest
						.setQuestionSearchCriteriaVO((QuestionSearchCriteriaVO) getRequest()
								.getSession().getAttribute(
										"QuestionSearchCriteriaVO"));
			}
		}

		return searchQuestionRequest;
	}

	/**
	 * Method for check ot Question
	 * 
	 * @return String
	 *  
	 */
	public String checkOut() {
		CheckOutQuestionRequest checkOutQuestionRequest = null;
		CheckOutQuestionResponse checkOutQuestionResponse = null;
		String returnMessage;
		// Create the Request Object
		checkOutQuestionRequest = this.getCheckOutQuestionRequest();
		checkOutQuestionResponse = (CheckOutQuestionResponse) this
				.executeService(checkOutQuestionRequest);
		if (null != checkOutQuestionResponse) {
			if (checkOutQuestionResponse.isCheckOutErrorFlag()) {
				this.setMessages(checkOutQuestionResponse.getMessages());
				returnMessage = this.searchQuestion();
				addAllMessagesToRequest(messages);
				return returnMessage;
			} else {
				QuestionVO questionVO = checkOutQuestionResponse
						.getQuestionVO();
				Application application = FacesContext.getCurrentInstance()
						.getApplication();
				SaveQuestionBackingBean saveQuestionBackingBean = ((SaveQuestionBackingBean) application
						.createValueBinding("#{saveQuestionBackingBean}")
						.getValue(FacesContext.getCurrentInstance()));
				saveQuestionBackingBean.setQuestion(questionVO
						.getQuestionDesc());
				saveQuestionBackingBean.setQuestionNumber(questionVO
						.getQuestionNumber());
				saveQuestionBackingBean.setVersion(questionVO.getVersion());
				return saveQuestionBackingBean.retriveQuestion();
			}
		}
		return this.searchQuestion();
	}

	/**
	 * @return CheckOutQuestionRequest
	 */
	private CheckOutQuestionRequest getCheckOutQuestionRequest() {
		CheckOutQuestionRequest checkOutQuestionRequest = (CheckOutQuestionRequest) this
				.getServiceRequest(ServiceManager.CHECKOUT_QUESTION_REQUEST);
		checkOutQuestionRequest.getQuestionVO().setQuestionNumber(
				this.getQuestionNumber());
		checkOutQuestionRequest.getQuestionVO().setQuestionNumberParentSysId(
				this.questionNumberParentSysId);
		checkOutQuestionRequest.getQuestionVO().setQuestionDesc(
				this.getQuestionDesc());
		checkOutQuestionRequest.getQuestionVO().setVersion(this.getVersion());

		return checkOutQuestionRequest;
	}

	public void setViewQuestion(String view) {
		//ignored
	}

	/**
	 * Method to View and ViewAll functionalities of Questions
	 * 
	 * @return
	 */
	public String getViewQuestion() {

		String action = (String) (getRequest().getParameter("action"));
		QuestionViewRequest questionViewRequest = (QuestionViewRequest) this
				.getServiceRequest(ServiceManager.QUESTION_VIEW_REQUEST);
		QuestionVO questionVO = new QuestionVO();
		if (VIEW_PAGE.equals(action)) {
			String questionNumberFromRequest = (String) (getRequest()
					.getParameter("questionNumber"));
			if(null!=getRequest().getParameter("questionNumber") && getRequest().getParameter("questionNumber").matches("^[0-9a-zA-Z_]+$")){
				questionNumberFromRequest=questionNumberFromRequest;
			}else{
				questionNumberFromRequest=null;
			}
			String versionNumberFromRequest = (String) (getRequest()
					.getParameter("version"));
			if(null!=getRequest().getParameter("version") && getRequest().getParameter("version").matches("^[0-9a-zA-Z_]+$")){
				versionNumberFromRequest=versionNumberFromRequest;
			}else{
				versionNumberFromRequest=null;
			}
			int questionNumber = Integer.parseInt(questionNumberFromRequest);
			int versioNumber = Integer.parseInt(versionNumberFromRequest);
			questionVO.setQuestionNumber(questionNumber);
			questionVO.setVersion(versioNumber);
			questionVO.setQuestionDesc((String) (getRequest()
					.getParameter("description")));
			this.getSession().setAttribute("questionNumber",
					questionNumberFromRequest);
			this.getSession().setAttribute("version", versionNumberFromRequest);
			this.getSession().setAttribute("action", "view");
			if(null!=getRequest().getParameter("description") && getRequest().getParameter("description").matches("^[0-9a-zA-Z_]+$")){
			String description = (String) ESAPI.encoder().encodeForHTML(getRequest().getParameter("description"));
			this.getSession().setAttribute("question",description);
			}
		}
		if (action == null) {
			if (VIEW_ALL_PAGE.equals(lastPage)) {
				questionVO.setQuestionNumber(this.questionNumber);
				questionVO.setQuestionDesc(this.questionDesc);
				questionVO.setVersion(this.version);
				action = VIEW_ALL;
			}

		} else if (VIEW_ALL.equals(action)) {
			questionVO.setQuestionDesc((String) (getRequest()
					.getParameter("description")));
		}
		questionViewRequest.setAction(action);
		questionViewRequest.setQuestionVO(questionVO);

		QuestionViewResponse questionViewResponse = (QuestionViewResponse) this
				.executeService(questionViewRequest);
		if (null != questionViewResponse) {
			if (null != questionViewResponse.getQuestionResultList()
					&& questionViewResponse.getQuestionResultList().size() > 0) {
				if ("view".equals(action)) {
					QuestionBO questionBO = (QuestionBO) questionViewResponse
							.getQuestionResultList().get(0);
					copyResponseValuesToBackingBean(questionBO);

					this.state = questionViewResponse.getQuestion().getState()
							.getState();
					setBreadCrumbText("Administration >> Question ("
							+ this.questionDesc + ") >> View");
				} else if ("viewAll".equals(action)) {

					List locateList = questionViewResponse
							.getQuestionResultList();
					List newFunctionalDomainList = new ArrayList();
					if (null != locateList) {
						Iterator iterator = locateList.iterator();
						List newQuestionSearchList = new ArrayList();
						String functionalDomainDesc = null;
						while (iterator.hasNext()) {
							QuestionBO questionBO = (QuestionBO) iterator
									.next();
							List functionalDomainList = questionBO
									.getFunctionalDomainList();
							questionBO
									.setDomainDesc(WPDStringUtil
											.getCommaSeperatedValuesFromList(functionalDomainList));
							// getting the functional domain corresponding to each question
						    if (functionalDomainList != null
										&& !functionalDomainList.isEmpty()) {    
								Iterator domainListIterator = functionalDomainList
										.iterator();
								List domainList = new ArrayList();
								while (domainListIterator.hasNext()) {
									FunctionalDomainBO domainBO = (FunctionalDomainBO) domainListIterator
											.next();
									functionalDomainDesc = domainBO
											.getFunctionalDomainDesc();
									domainList.add(functionalDomainDesc);
								}
								questionBO
										.setDomainDesc(WPDStringUtil
												.getCommaSeperatedValuesFromList(domainList));
							}
							newFunctionalDomainList.add(questionBO);
						}
					}
					this.setQuestionsList(newFunctionalDomainList);
					setBreadCrumbText("Administration >> Question >> View All Versions");
				}
			}
		}
		addAllMessagesToRequest(this.messages);
		return "";
	}

	/**
	 * Copying the return values in the Response object to the Backing Bean.
	 * 
	 * @param questionBO
	 */
	private void copyResponseValuesToBackingBean(QuestionBO questionBO) {
		if (null != questionBO) {
			this.questionNumber = questionBO.getQuestionNumber();
			this.questionDesc = questionBO.getQuestionDesc();
			this.answerList = questionBO.getAnswerList();
			this.version = questionBO.getVersion();
			this.status = questionBO.getStatus();
			this.state = questionBO.getState().getState();
			this.createdUser = questionBO.getCreatedUser();
			this.createdDate = questionBO.getCreatedTimestamp();
			this.updatedUserId = questionBO.getLastUpdatedUser();
			this.updatedDate = questionBO.getLastUpdatedTimestamp();
			this.dataType = questionBO.getDataTypeName();
			this.answerList = questionBO.getAnswerList();
			this.providerArrangement = questionBO.getProviderArrangement();
			this.benefitLineDataType=questionBO.getBenefitLineDataType();
			
			this.functionalDomain = WPDStringUtil
					.getCommaSeperatedValuesFromList(questionBO
							.getFunctionalDomainList());
			this.setTerm(questionBO.getTerm());
			this.setQualifier(questionBO.getQualifier());
			this.setSpsRefernceId(questionBO.getDescription());

			// getting the functional domain description form questionBO
			if (null != questionBO.getFunctionalDomainList()) {
				List funDomainList = questionBO.getFunctionalDomainList();
				Iterator iterator = questionBO.getFunctionalDomainList()
						.iterator();
				List domainDescList = new ArrayList();
				String domainDesc = null;
				while (iterator.hasNext()) {
					FunctionalDomainBO domainBO = (FunctionalDomainBO) iterator
							.next();
					domainDesc = domainBO.getFunctionalDomainDesc();
					domainDescList.add(domainDesc);
				}
				this.functionalDomain = WPDStringUtil
						.getCommaSeperatedValuesFromList(domainDescList);
			}
		}
	}

	/**
	 * @param criteriaDataType2
	 * @return List dataTypeList
	 */
	private List getDataTypeList() {
		List dataTypeList = new ArrayList();
		StringTokenizer tokenizer = new StringTokenizer(this
				.getCriteriaDataType(), "~");
		int tokenCount = tokenizer.countTokens();
		for (int i = 0; i < tokenCount; i++) {
			String dataTypeName = ((String) tokenizer.nextToken());
			if (null != dataTypeName && (((i % 2) == 0) || i == 0))
				dataTypeList.add(new Integer(dataTypeName));
		}
		return dataTypeList;
	}

	/**
	 * Method to validate required fields
	 * 
	 * @return boolean requiredField
	 */
	private boolean validateRequiredFields() {
		requiredField = false;
		if ((null == this.criteriaQuestion || "".equals(this.criteriaQuestion
				.trim()))
				&& (null == this.criteriaDataType || ""
						.equals(this.criteriaDataType.trim()))
				&& (null == this.functionalDomain || ""
						.equals(this.functionalDomain.trim()))
				&& (null == this.spsRefernceId || "".equals(this.spsRefernceId
						.trim()))) {
			requiredField = true;
		} else {
			requiredField = false;
		}
		return requiredField;
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
	 * Returns the criteriaDataType.
	 * 
	 * @return criteriaDataType
	 */
	public String getCriteriaDataType() {
		return criteriaDataType;
	}

	/**
	 * The criteriaDataType to set.
	 * 
	 * @param criteriaDataType
	 */
	public void setCriteriaDataType(String criteriaDataType) {
		this.criteriaDataType = criteriaDataType;
	}

	/**
	 * Returns the questionDesc
	 * 
	 * @return String questionDesc.
	 */
	public String getQuestionDesc() {
		return questionDesc;
	}

	/**
	 * Sets the questionDesc
	 * 
	 * @param questionDesc.
	 */
	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
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
	 * Returns the answer.
	 * 
	 * @return answer
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * Sets the answer.
	 * 
	 * @param answer
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
	 * Returns the answerList.
	 * 
	 * @return answerList
	 */
	public List getAnswerList() {
		return answerList;
	}

	/**
	 * Sets the answerList.
	 * 
	 * @param answerList
	 */
	public void setAnswerList(List answerList) {
		this.answerList = answerList;
	}

	/**
	 * Returns the createdDate.
	 * 
	 * @return createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * Sets the createdDate.
	 * 
	 * @param createdDate
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * Returns the createdUser.
	 * 
	 * @return createdUser
	 */
	public String getCreatedUser() {
		return createdUser;
	}

	/**
	 * Sets the createdUser.
	 * 
	 * @param createdUser
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	/**
	 * Returns the dataType.
	 * 
	 * @return dataType
	 */
	public String getDataType() {
		return dataType;
	}

	/**
	 * Sets the dataType.
	 * 
	 * @param dataType
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	/**
	 * Returns the questionsList.
	 * 
	 * @return questionsList
	 */
	public List getQuestionsList() {
		return questionsList;
	}

	/**
	 * Sets the questionsList.
	 * 
	 * @param questionsList
	 */
	public void setQuestionsList(List questionsList) {
		this.questionsList = questionsList;
	}

	/**
	 * Returns the requiredField.
	 * 
	 * @return requiredField
	 */
	public boolean isRequiredField() {
		return requiredField;
	}

	/**
	 * Sets the requiredField.
	 * 
	 * @param requiredField
	 */
	public void setRequiredField(boolean requiredField) {
		this.requiredField = requiredField;
	}

	/**
	 * Returns the state.
	 * 
	 * @return state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the state.
	 * 
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Returns the updatedDate.
	 * 
	 * @return updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * Sets the updatedDate.
	 * 
	 * @param updatedDate
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * Returns the updatedUserId.
	 * 
	 * @return updatedUserId
	 */
	public String getUpdatedUserId() {
		return updatedUserId;
	}

	/**
	 * Sets the updatedUserId.
	 * 
	 * @param updatedUserId
	 */
	public void setUpdatedUserId(String updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	/**
	 * Returns the lastPage.
	 * 
	 * @return lastPage
	 */
	public String getLastPage() {
		return lastPage;
	}

	/**
	 * Sets the lastPage.
	 * 
	 * @param lastPage
	 */
	public void setLastPage(String lastPage) {
		this.lastPage = lastPage;
	}

	/**
	 * @return breadCrumbPrint
	 * 
	 * Returns the breadCrumbPrint.
	 */
	public String getBreadCrumbPrint() {
		breadCrumbPrint = "Administration >> Question >> Locate >> Print";
		return breadCrumbPrint;
	}

	/**
	 * @param breadCrumbPrint
	 * 
	 * Sets the breadCrumbPrint.
	 */
	public void setBreadCrumbPrint(String breadCrumbPrint) {
		this.breadCrumbPrint = breadCrumbPrint;
	}

	/**
	 * @return Returns the questionNumberParentSysId.
	 */
	public int getQuestionNumberParentSysId() {
		return questionNumberParentSysId;
	}

	/**
	 * @param questionNumberParentSysId The questionNumberParentSysId to set.
	 */
	public void setQuestionNumberParentSysId(int questionNumberParentSysId) {
		this.questionNumberParentSysId = questionNumberParentSysId;
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

	public String getSpsRefernceId() {
		return spsRefernceId;
	}

	public void setSpsRefernceId(String spsRefernceId) {
		this.spsRefernceId = spsRefernceId;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public void setBenefitLineDataType(String benefitLineDataType) {
		this.benefitLineDataType = benefitLineDataType;
	}

	public String getBenefitLineDataType() {
		return benefitLineDataType;
	}
}