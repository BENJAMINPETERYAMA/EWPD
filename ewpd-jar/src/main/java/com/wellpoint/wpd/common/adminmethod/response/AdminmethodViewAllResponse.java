/*
 * Created on Oct 5, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethod.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author u18739
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminmethodViewAllResponse extends WPDResponse {

	private List resultList;	
	private String adminMethodNo;	
	private String adminMethodDescription;	
	private String term;
	private String qualifier;	
	private String pva;	
	private String dataType;
	private String questionAnswers;		
	private String queryName;
	
	
	/**
	 * @return Returns the resultList.
	 */
	public List getResultList() {
		return resultList;
	}
	/**
	 * @param resultList The resultList to set.
	 */
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}
	 
	/**
	 * @return Returns the queryName.
	 */
	public String getQueryName() {
		return queryName;
	}
	/**
	 * @param queryName The queryName to set.
	 */
	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}
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
	 * @return Returns the dataType.
	 */
	public String getDataType() {
		return dataType;
	}
	/**
	 * @param dataType The dataType to set.
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
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
	 * @return Returns the questionAnswers.
	 */
	public String getQuestionAnswers() {
		return questionAnswers;
	}
	/**
	 * @param questionAnswers The questionAnswers to set.
	 */
	public void setQuestionAnswers(String questionAnswers) {
		this.questionAnswers = questionAnswers;
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
	 * @return Returns the adminMethodDescription.
	 */
	public String getAdminMethodDescription() {
		return adminMethodDescription;
	}
	/**
	 * @param adminMethodDescription The adminMethodDescription to set.
	 */
	public void setAdminMethodDescription(String adminMethodDescription) {
		this.adminMethodDescription = adminMethodDescription;
	}
}
