/*
 * QuestionViewBackingBean.java
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
import java.util.Iterator;
import java.util.List;

import com.wellpoint.wpd.business.question.locateCriteria.QuestionLocateResult;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.question.bo.FunctionalDomainBO;
import com.wellpoint.wpd.common.question.bo.QuestionBO;
import com.wellpoint.wpd.common.question.request.QuestionViewRequest;
import com.wellpoint.wpd.common.question.response.QuestionViewResponse;
import com.wellpoint.wpd.common.question.vo.QuestionVO;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * Bean to view Question
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class QuestionViewBackingBean extends WPDBackingBean {

	private int questionNumber;

	private int version;

	private String questionDesc;

	private String dataType;

	private String answer;

	private List answerList;

	private List questionsList;

	private static String VIEW = "view";

	private static String VIEW_ALL = "viewAll";

	private String createdUser;

	private Date createdDate;

	private String updatedUserId;

	private Date updatedDate;

	private String state = "New";

	private String status;

	private String breadCrumbPrint;

	private String providerArrangement;

	private String functionalDomain;

	private List functioanlDomainList;

    private String qualifier;
    
    private String term;
    
    private String spsReference;
    

	/**
	 * @return Returns the functioanlDomainList.
	 */
	public List getFunctioanlDomainList() {
		return functioanlDomainList;
	}

	/**
	 * @param functioanlDomainList The functioanlDomainList to set.
	 */
	public void setFunctioanlDomainList(List functioanlDomainList) {
		this.functioanlDomainList = functioanlDomainList;
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
	 * The createdDate to set.
	 * 
	 * @param createdDate
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * Returns the createdUserId.
	 * 
	 * @return createdUserId
	 */
	public String getCreatedUser() {
		return createdUser;
	}

	/**
	 * The createdUserId to set.
	 * 
	 * @param createdUserId
	 */
	public void setCreatedUser(String createdUserId) {
		this.createdUser = createdUserId;
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
	 * The state to set.
	 * 
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Returns the status.
	 * 
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * The status to set.
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * The updatedDate to set.
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
	 * The updatedUserId to set.
	 * 
	 * @param updatedUserId
	 */
	public void setUpdatedUserId(String updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	/**
	 * Returns the questionList.
	 * 
	 * @return questionList
	 */
	public List getQuestionsList() {
		return questionsList;
	}

	/**
	 * The questionList to set.
	 * 
	 * @param questionList
	 */
	public void setQuestionsList(List questionsList) {
		this.questionsList = questionsList;
	}

	/**
	 * Returns the questionDesc.
	 * 
	 * @return questionDesc
	 */
	public String getQuestionDesc() {
		return questionDesc;
	}

	/**
	 * The questionDesc to set.
	 * 
	 * @param questionDesc
	 */
	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}

	/**
	 * Returns the questionNumber.
	 * 
	 * @return questionNumber
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
	 * Method to view Question
	 * 
	 * @return String
	 */
	public String getView() {

		String action = (String) (getRequest().getParameter("action"));
		QuestionViewRequest questionViewRequest = (QuestionViewRequest) this
				.getServiceRequest(ServiceManager.QUESTION_VIEW_REQUEST);
		QuestionVO questionVO = new QuestionVO();
		if (VIEW.equals(action)) {
			String questionNumberFromRequest = (String) (getRequest()
					.getParameter("questionNumber"));
			if(null!=questionNumberFromRequest && questionNumberFromRequest.matches("^[0-9a-zA-Z_]+$")){
				questionNumberFromRequest=questionNumberFromRequest;
			}else{
				questionNumberFromRequest=null;
			}
			String versionNumberFromRequest = (String) (getRequest()
					.getParameter("version"));
			if(null!=versionNumberFromRequest && versionNumberFromRequest.matches("^[0-9a-zA-Z_]+$")){
				versionNumberFromRequest=versionNumberFromRequest;
			}else{
				versionNumberFromRequest=null;
			}
			int questionNumber = Integer.parseInt(questionNumberFromRequest);
			int versioNumber = Integer.parseInt(versionNumberFromRequest);
			questionVO.setQuestionNumber(questionNumber);
			questionVO.setVersion(versioNumber);
			if(null!=getRequest().getParameter("description") && getRequest().getParameter("description").matches("^[0-9a-zA-Z_]+$")){
			questionVO.setQuestionDesc((String) (getRequest()
					.getParameter("description")));
			}
			this.getSession().setAttribute("questionNumber",
					questionNumberFromRequest);
			this.getSession().setAttribute("version", versionNumberFromRequest);
			this.getSession().setAttribute("question",
					getRequest().getParameter("description"));
		}
		if (action == null) {
			questionVO.setQuestionNumber(Integer.parseInt((String) this
					.getSession().getAttribute("questionNumber")));
			questionVO.setQuestionDesc((String) this.getSession().getAttribute(
					"question"));
			questionVO.setVersion(Integer.parseInt((String) this.getSession()
					.getAttribute("version")));
			action = (String) this.getSession().getAttribute("action");
		} else if (VIEW_ALL.equals(action)) {
			questionVO.setQuestionDesc((String) (getRequest()
					.getParameter("description")));
		}
		questionViewRequest.setAction(action);
		questionViewRequest.setQuestionVO(questionVO);

		ArrayList validationMessages = new ArrayList();
		QuestionViewResponse questionViewResponse = (QuestionViewResponse) this
				.executeService(questionViewRequest);
		if (null != questionViewResponse) {
			if (null != questionViewResponse.getQuestionResultList()
					&& questionViewResponse.getQuestionResultList().size() > 0) {
				if (VIEW.equals(action)) {
					QuestionBO questionBO = (QuestionBO) questionViewResponse
							.getQuestionResultList().get(0);
					questionBO.setState(questionViewResponse.getQuestion()
							.getState());
					copyResponseValuesToBackingBean(questionBO);
					setBreadCrumbText("Administration >> Question ("
							+ this.questionDesc + ") >> View");
				} else if (VIEW_ALL.equals(action)) {
					List locateList = questionViewResponse
							.getQuestionResultList();
					setLatestVersionFlag(locateList);
					this.setQuestionsList(locateList);
					setBreadCrumbText("Administration >> Question >> View All Versions");
				}
			} else {
				validationMessages.add(new ErrorMessage(
						WebConstants.SEARCH_RESULT_NOT_FOUND));
				addAllMessagesToRequest(validationMessages);
			}
		}

		return "";
	}

	/**
	 * Sets the Latest version flag.
	 * 
	 * @param locateResultList.
	 */

	private void setLatestVersionFlag(List locateResultList) {

		int count = -1;
		int index = 0;
		int latestVersion = 0;
		int version = 0;
		Iterator iterator = locateResultList.iterator();
		while (iterator.hasNext()) {
			++count;
			version = ((QuestionLocateResult) iterator.next()).getVersion();
			((QuestionLocateResult) locateResultList.get(count))
					.setVersionFlag(false);
			if (latestVersion < version) {
				latestVersion = version;
				index = count;
			}
		}
		((QuestionLocateResult) locateResultList.get(index))
				.setVersionFlag(true);
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
			this.setSpsReference(questionBO.getDescription());
			this.setTerm(questionBO.getTerm());
			this.setQualifier(questionBO.getQualifier());
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
	 * Returns the answer.
	 * 
	 * @return answer
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * The answer to set.
	 * 
	 * @param answer
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
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
	 * The dataType to set.
	 * 
	 * @param dataType
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
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
	 * The answerList to set.
	 * 
	 * @param answerList
	 */
	public void setAnswerList(List answerList) {
		this.answerList = answerList;
	}

	/**
	 * Returns the version.
	 * 
	 * @return version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * The version to set.
	 * 
	 * @param version
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * Returns the breadCrumbPrint
	 * @return String breadCrumbPrint.
	 */
	public String getBreadCrumbPrint() {

		String question = (String) this.getSession().getAttribute("question");
		breadCrumbPrint = "Administration  >> Question (" + question
				+ ") >> Print";
		return breadCrumbPrint;
	}

	/**
	 * Sets the breadCrumbPrint
	 * @param breadCrumbPrint.
	 */
	public void setBreadCrumbPrint(String breadCrumbPrint) {
		this.breadCrumbPrint = breadCrumbPrint;
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
	public String getQualifier() {
		return qualifier;
	}
	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}
	public String getSpsReference() {
		return spsReference;
	}
	public void setSpsReference(String spsReference) {
		this.spsReference = spsReference;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
}