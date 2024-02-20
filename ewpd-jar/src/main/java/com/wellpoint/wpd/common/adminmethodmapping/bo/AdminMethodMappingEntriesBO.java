/*
 * Created on Oct 18, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethodmapping.bo;

import java.util.Date;
import java.util.List;

import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * @author U17066
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodMappingEntriesBO extends BusinessObject {

	
	private String processMethod;
	private  String adminMethodSysId;
	private String term;
	private String qualifier;
	private String pva;
	private String datatype;
	private String comments;
	private int filterCriteriaSysId;
	private List questionsAnswerList;
	private String possibleAnswer;
	private List questionNbrList;
	private int questionNumber;
	private String createDate;
	
	/**
	 * @return Returns the questionNumber.
	 */
	public int getQuestionNumber() {
		return questionNumber;
	}
	/**
	 * @param questionNumber The questionNumber to set.
	 */
	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}
	/**
	 * @return Returns the questionsAnswerList.
	 */
	public List getQuestionsAnswerList() {
		return questionsAnswerList;
	}
	/**
	 * @param questionsAnswerList The questionsAnswerList to set.
	 */
	public void setQuestionsAnswerList(List questionsAnswerList) {
		this.questionsAnswerList = questionsAnswerList;
	}
	/**
	 * @return Returns the createdDate.
	 */
	public Date getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate The createdDate to set.
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @return Returns the createdUser.
	 */
	public String getCreatedUser() {
		return createdUser;
	}
	/**
	 * @param createdUser The createdUser to set.
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	/**
	 * @return Returns the lastUpdatedDate.
	 */
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	/**
	 * @param lastUpdatedDate The lastUpdatedDate to set.
	 */
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	/**
	 * @return Returns the lastUpdatedUser.
	 */
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}
	/**
	 * @param lastUpdatedUser The lastUpdatedUser to set.
	 */
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}
	private String createdUser;
	private String lastUpdatedUser;
	private Date createdDate;
	private Date lastUpdatedDate;	
	
	
	
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
	 */
	public boolean equals(BusinessObject object) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return Returns the adminMethodSysId.
	 */
	
	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return Returns the datatype.
	 */
	public String getDatatype() {
		return datatype;
	}
	/**
	 * @param datatype The datatype to set.
	 */
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	/**
	 * @return Returns the filterCriteriaSysId.
	 */
	public int getFilterCriteriaSysId() {
		return filterCriteriaSysId;
	}
	/**
	 * @param filterCriteriaSysId The filterCriteriaSysId to set.
	 */
	public void setFilterCriteriaSysId(int filterCriteriaSysId) {
		this.filterCriteriaSysId = filterCriteriaSysId;
	}
	/**
	 * @return Returns the processMethod.
	 */
	
	/**
	 * @return Returns the pva.
	 */
	public String getPva() {
		return pva;
	}
	/**
	 * @param pva The pva to set.
	 */
	public void setPva(String pva) {
		this.pva = pva;
	}
	/**
	 * @return Returns the qualifier.
	 */
	public String getQualifier() {
		return qualifier;
	}
	/**
	 * @param qualifier The qualifier to set.
	 */
	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}
	/**
	 * @return Returns the term.
	 */
	public String getTerm() {
		return term;
	}
	/**
	 * @param term The term to set.
	 */
	public void setTerm(String term) {
		this.term = term;
	}
	/**
	 * @return Returns the adminMethodSysId.
	 */
	public String getAdminMethodSysId() {
		return adminMethodSysId;
	}
	/**
	 * @param adminMethodSysId The adminMethodSysId to set.
	 */
	public void setAdminMethodSysId(String adminMethodSysId) {
		this.adminMethodSysId = adminMethodSysId;
	}
	/**
	 * @return Returns the processMethod.
	 */
	public String getProcessMethod() {
		return processMethod;
	}
	/**
	 * @param processMethod The processMethod to set.
	 */
	public void setProcessMethod(String processMethod) {
		this.processMethod = processMethod;
	}
	/**
	 * @return Returns the possibleAnswer.
	 */
	public String getPossibleAnswer() {
		return possibleAnswer;
	}
	/**
	 * @param possibleAnswer The possibleAnswer to set.
	 */
	public void setPossibleAnswer(String possibleAnswer) {
		this.possibleAnswer = possibleAnswer;
	}
	/**
	 * @return Returns the questionNbrList.
	 */
	public List getQuestionNbrList() {
		return questionNbrList;
	}
	/**
	 * @param questionNbrList The questionNbrList to set.
	 */
	public void setQuestionNbrList(List questionNbrList) {
		this.questionNbrList = questionNbrList;
	}
	/**
	 * @return Returns the createDate.
	 */
	public String getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate The createDate to set.
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
}
