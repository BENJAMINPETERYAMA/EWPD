/*
 * Created on Oct 4, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethodmapping.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author U17066
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodMappingSearchRequest extends WPDRequest {
	

	private List termList;

	private List qualifierList;

	private List pvaList;
	
	private List processMethodList;
	
	private boolean searchFlag;

	
	private String adminMethodSysId;
	
	

	private boolean editFlag;
	private boolean deleteFlag;
	
	private List adminMethodSysIdList;
	

	private String descriptionCriteria; 
	
	private boolean editRetireve=false;
	
	private String adminMethodNo;


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
	public List getTermList() {
		return termList;
	}
	/**
	 * @param termList The termList to set.
	 */
	public void setTermList(List termList) {
		this.termList = termList;
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
	

	
	
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

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
	 * @return Returns the searchDuplicateFlag.
	 */
	
	/**
	 * @return Returns the searchFlag.
	 */
	public boolean isSearchFlag() {
		return searchFlag;
	}
	/**
	 * @param searchFlag The searchFlag to set.
	 */
	public void setSearchFlag(boolean searchFlag) {
		this.searchFlag = searchFlag;
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
	 * @return Returns the deleteFlag.
	 */
	public boolean isDeleteFlag() {
		return deleteFlag;
	}
	/**
	 * @param deleteFlag The deleteFlag to set.
	 */
	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	/**
	 * @return Returns the editRetireve.
	 */
	public boolean isEditRetireve() {
		return editRetireve;
	}
	/**
	 * @param editRetireve The editRetireve to set.
	 */
	public void setEditRetireve(boolean editRetireve) {
		this.editRetireve = editRetireve;
	}
}
