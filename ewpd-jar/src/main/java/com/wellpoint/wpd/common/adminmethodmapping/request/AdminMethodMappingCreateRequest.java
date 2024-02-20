/*
 * Created on Oct 4, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethodmapping.request;

import java.util.Date;
import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author U17066
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodMappingCreateRequest extends WPDRequest {

	
	private String processingMethod;

	private String adminMethodNo;

	private String processMethodDesc;

	private List processMethodList;

	private String description;

	private String term;

	private List qualifierList;

	private List pvaList;

	private String comments;
	
	private String adminMethodsysID;

	private List datatypeList;
	private List adminMethodSysIdList;
	
	private List quesntionIdList;
	private  List questionNbrList;
	private String possibleAnswer;
	
	private String descriptionCriteria;
	private Date createdDate;
	private Date lastUpdatedDate;
	

	
	
	/**
	 * @return Returns the adminMethodNo.
	 */
	public String getAdminMethodNo() {
		return adminMethodNo;
	}
	/**
	 * @param adminMethodNo The adminMethodNo to set.
	 */
	public void setAdminMethodNo(String adminMethodNo) {
		this.adminMethodNo = adminMethodNo;
	}
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
	 * @return Returns the datatypeList.
	 */
	public List getDatatypeList() {
		return datatypeList;
	}
	/**
	 * @param datatypeList The datatypeList to set.
	 */
	public void setDatatypeList(List datatypeList) {
		this.datatypeList = datatypeList;
	}
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return Returns the processingMethod.
	 */
	public String getProcessingMethod() {
		return processingMethod;
	}
	/**
	 * @param processingMethod The processingMethod to set.
	 */
	public void setProcessingMethod(String processingMethod) {
		this.processingMethod = processingMethod;
	}
	
	/**
	 * @return Returns the processMethodList.
	 */
	public List getProcessMethodList() {
		return processMethodList;
	}
	/**
	 * @param processMethodList The processMethodList to set.
	 */
	public void setProcessMethodList(List processMethodList) {
		this.processMethodList = processMethodList;
	}
	/**
	 * @return Returns the processMethodDesc.
	 */
	public String getProcessMethodDesc() {
		return processMethodDesc;
	}
	/**
	 * @param processMethodDesc The processMethodDesc to set.
	 */
	public void setProcessMethodDesc(String processMethodDesc) {
		this.processMethodDesc = processMethodDesc;
	}
	/**
	 * @return Returns the pvaList.
	 */
	public List getPvaList() {
		return pvaList;
	}
	/**
	 * @param pvaList The pvaList to set.
	 */
	public void setPvaList(List pvaList) {
		this.pvaList = pvaList;
	}
	/**
	 * @return Returns the qualifierList.
	 */
	public List getQualifierList() {
		return qualifierList;
	}
	/**
	 * @param qualifierList The qualifierList to set.
	 */
	public void setQualifierList(List qualifierList) {
		this.qualifierList = qualifierList;
	}
	/**
	 * @return Returns the termList.
	 */
	public String getTerm() {
		return term;
	}
	/**
	 * @param termList The termList to set.
	 */
	public void setTerm(String termList) {
		this.term = termList;
	}

	
	/**
	 * @return Returns the adminMethodsysID.
	 */
	public String getAdminMethodsysID() {
		return adminMethodsysID;
	}
	/**
	 * @param adminMethodsysID The adminMethodsysID to set.
	 */
	public void setAdminMethodsysID(String adminMethodsysID) {
		this.adminMethodsysID = adminMethodsysID;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

	/**
	 * @return Returns the adminMethodSysIdList.
	 */
	public List getAdminMethodSysIdList() {
		return adminMethodSysIdList;
	}
	/**
	 * @param adminMethodSysIdList The adminMethodSysIdList to set.
	 */
	public void setAdminMethodSysIdList(List adminMethodSysIdList) {
		this.adminMethodSysIdList = adminMethodSysIdList;
	}
	/**
	 * @return Returns the quesntionIdList.
	 */
	public List getQuesntionIdList() {
		return quesntionIdList;
	}
	/**
	 * @param quesntionIdList The quesntionIdList to set.
	 */
	public void setQuesntionIdList(List quesntionIdList) {
		this.quesntionIdList = quesntionIdList;
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
	 * @return Returns the descriptionCriteria.
	 */
	public String getDescriptionCriteria() {
		return descriptionCriteria;
	}
	/**
	 * @param descriptionCriteria The descriptionCriteria to set.
	 */
	public void setDescriptionCriteria(String descriptionCriteria) {
		this.descriptionCriteria = descriptionCriteria;
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
}
