/*
 * Created on Oct 4, 2009
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
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodMappingBO extends BusinessObject implements Comparable{


	private String processMethod;
	
    private String processingMethod;
    
	private String adminMethodNo;
	
	private List questionAnswerList;

	private String description;	
	
	private String adminMethodSysId;
	
    private String processMethodDesc;  
    
	private String term;
	
	private String termid;
	
	private String 	datatypeid;
	
	private String qualifierid;
	
	private String pvaid;
	
	private String qualifier;
	
	private String pva;
	
	private String datatype;
	
	private int filterCriteriaSysId;
	
	private List qualifierList;

	private List pvaList;

	private List termList;

	private List datatypeList;

	private String comments;	

	private String createdUser;

	private String lastUpdatedUser;

	private Date createdDate;

	private Date lastUpdatedDate;
	
	private String answers;
	
	private int rownum;
	
	private List adminMethodSysIdList;
	
	private List processMethodList;
	
	private boolean searchDuplicateFlag;

	private List quesntionIdList;
	private  List questionNbrList;
	
	/**
	 * @return Returns the rownum.
	 */
	public int getRownum() {
		return rownum;
	}
	/**
	 * @param rownum The rownum to set.
	 */
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	// To identify if its a edit request.
	boolean editFlag;
	
	/**
	 * @return Returns the answers.
	 */
	public String getAnswers() {
		return answers;
	}
	/**
	 * @param answers The answers to set.
	 */
	public void setAnswers(String answers) {
		this.answers = answers;
	}
	/**
	 * @return Returns the termList.
	 */
	public List getTermList() {
		return termList;
	}

	/**
	 * @param termList
	 *            The termList to set.
	 */
	public void setTermList(List termList) {
		this.termList = termList;
	}

	

	/**
	 * @return Returns the createdUser.
	 */
	public String getCreatedUser() {
		return createdUser;
	}

	/**
	 * @param createdUser
	 *            The createdUser to set.
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	
	/**
	 * @return Returns the lastUpdatedUser.
	 */
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}

	/**
	 * @param lastUpdatedUser
	 *            The lastUpdatedUser to set.
	 */
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
	 */
	public boolean equals(BusinessObject object) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return Returns the adminMethodNo.
	 */
	public String getAdminMethodNo() {
		return adminMethodNo;
	}

	/**
	 * @param adminMethodNo
	 *            The adminMethodNo to set.
	 */
	public void setAdminMethodNo(String adminMethodNo) {
		this.adminMethodNo = adminMethodNo;
	}

	public String getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            The comments to set.
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
	 * @param datatypeList
	 *            The datatypeList to set.
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
	 * @param description
	 *            The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	

	/**
	 * @return Returns the pvaList.
	 */
	public List getPvaList() {
		return pvaList;
	}

	/**
	 * @param pvaList
	 *            The pvaList to set.
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
	 * @param qualifierList
	 *            The qualifierList to set.
	 */
	public void setQualifierList(List qualifierList) {
		this.qualifierList = qualifierList;
	}

	

	/**
	 * @return Returns the term.
	 */
	public String getTerm() {
		return term;
	}

	/**
	 * @param term
	 *            The term to set.
	 */
	public void setTerm(String term) {
		this.term = term;
	}

	/**
	
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
	 * @return Returns the questionAnswerList.
	 */
	public List getQuestionAnswerList() {
		return questionAnswerList;
	}
	/**
	 * @param questionAnswerList The questionAnswerList to set.
	 */
	public void setQuestionAnswerList(List questionAnswerList) {
		this.questionAnswerList = questionAnswerList;
	}

	/**
	 * @return Returns the editFlag.
	 */
	public boolean isEditFlag() {
		return editFlag;
	}
	/**
	 * @param editFlag The editFlag to set.
	 */
	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}
	/**
	 * @return Returns the datatypeid.
	 */
	public String getDatatypeid() {
		return datatypeid;
	}
	/**
	 * @param datatypeid The datatypeid to set.
	 */
	public void setDatatypeid(String datatypeid) {
		this.datatypeid = datatypeid;
	}
	/**
	 * @return Returns the pvaid.
	 */
	public String getPvaid() {
		return pvaid;
	}
	/**
	 * @param pvaid The pvaid to set.
	 */
	public void setPvaid(String pvaid) {
		this.pvaid = pvaid;
	}
	/**
	 * @return Returns the qualifierid.
	 */
	public String getQualifierid() {
		return qualifierid;
	}
	/**
	 * @param qualifierid The qualifierid to set.
	 */
	public void setQualifierid(String qualifierid) {
		this.qualifierid = qualifierid;
	}
	/**
	 * @return Returns the termid.
	 */
	public String getTermid() {
		return termid;
	}
	/**
	 * @param termid The termid to set.
	 */
	public void setTermid(String termid) {
		this.termid = termid;
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
	 * @return Returns the searchDuplicateFlag.
	 */
	public boolean isSearchDuplicateFlag() {
		return searchDuplicateFlag;
	}
	/**
	 * @param searchDuplicateFlag The searchDuplicateFlag to set.
	 */
	public void setSearchDuplicateFlag(boolean searchDuplicateFlag) {
		this.searchDuplicateFlag = searchDuplicateFlag;
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
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object obj) {
		
		AdminMethodMappingBO adminMethodMappingBO = (AdminMethodMappingBO)obj;
		int result=0;
		if(this.getAdminMethodNo()!= adminMethodMappingBO.getAdminMethodNo()){
			if(Integer.parseInt(this.getAdminMethodNo())>Integer.parseInt(adminMethodMappingBO.getAdminMethodNo())){
			result=0;
			}
			if(Integer.parseInt(this.getAdminMethodNo())>Integer.parseInt(adminMethodMappingBO.getAdminMethodNo())){
				result=1;
			}
			if(Integer.parseInt(this.getAdminMethodNo())<Integer.parseInt(adminMethodMappingBO.getAdminMethodNo())){
				result=-1;
			}
		}
	     if(result==0){
			result=(this.getDescription().compareTo(adminMethodMappingBO.getDescription()));
			if(result==0){
				result=(this.getProcessMethodDesc().compareTo(adminMethodMappingBO.getProcessMethodDesc()));	
			}
		}
		
	
			
		
		return result;
			
		}
	
}

