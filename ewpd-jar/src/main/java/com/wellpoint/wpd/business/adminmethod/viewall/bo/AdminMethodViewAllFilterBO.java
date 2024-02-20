/*
 * Created on Oct 23, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.adminmethod.viewall.bo;

import java.util.List;


/**
 * @author u20622
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodViewAllFilterBO implements Comparable{
	
	private int adminMethodSysId;
	
	private String adminMethodDescription;
	
	private String adminMethodNo;
	
	private String term;
	
	private String qualifier;
	
	private String pva;
	
	private String dataType;
	
	private String answers;
		
	private String queryName;
	
	private String processingMethod;
	
	private String filterCriteriaSysId;
	
	private List questionAnswerList;
	
	public String getAdminMethodDescription() {
		return adminMethodDescription;
	}
	public void setAdminMethodDescription(String adminMethodDescription) {
		this.adminMethodDescription = adminMethodDescription;
	}
	public String getAdminMethodNo() {
		return adminMethodNo;
	}
	public void setAdminMethodNo(String adminMethodNo) {
		this.adminMethodNo = adminMethodNo;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getFilterCriteriaSysId() {
		return filterCriteriaSysId;
	}
	public void setFilterCriteriaSysId(String filterCriteriaSysId) {
		this.filterCriteriaSysId = filterCriteriaSysId;
	}
	public String getProcessingMethod() {
		return processingMethod;
	}
	public void setProcessingMethod(String processingMethod) {
		this.processingMethod = processingMethod;
	}
	public String getPva() {
		return pva;
	}
	public void setPva(String pva) {
		this.pva = pva;
	}
	public String getQualifier() {
		return qualifier;
	}
	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}
	public String getQueryName() {
		return queryName;
	}
	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}
	public List getQuestionAnswerList() {
		return questionAnswerList;
	}
	public void setQuestionAnswerList(List questionAnswerList) {
		this.questionAnswerList = questionAnswerList;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public int getAdminMethodSysId() {
		return adminMethodSysId;
	}
	public void setAdminMethodSysId(int adminMethodSysId) {
		this.adminMethodSysId = adminMethodSysId;
	}
	public String getAnswers() {
		return answers;
	}
	public void setAnswers(String answers) {
		this.answers = answers;
	}
	public int compareTo(Object obj) {
		
		AdminMethodViewAllFilterBO adminMethodViewAllFilterBO= (AdminMethodViewAllFilterBO)obj;
		
		if(Integer.parseInt(this.getAdminMethodNo())>Integer.parseInt(adminMethodViewAllFilterBO.getAdminMethodNo()))
		 return 1;
		else 
		return 0;
			
	}
}
