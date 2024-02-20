/*
 * Created on Jul 20, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.referencemapping.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.referencemapping.vo.ReferencemappingVO;

/**
 * @author U14659
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SearchReferenceMappingRequest extends WPDRequest{
	
	private ReferencemappingVO referencemappingVO;
	
	private List  referenceList;
	private List  typeList;
	private List  termList;      
	private List  qualifierList;   
	private List  pvaList;       
	private int action;
	/**
	 * @return Returns the action.
	 */
	public int getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(int action) {
		this.action = action;
	}
	/**
	 * @return Returns the dataTypeList.
	 */
	public List getDataTypeList() {
		return dataTypeList;
	}
	/**
	 * @param dataTypeList The dataTypeList to set.
	 */
	public void setDataTypeList(List dataTypeList) {
		this.dataTypeList = dataTypeList;
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
	 * @return Returns the referenceList.
	 */
	public List getReferenceList() {
		return referenceList;
	}
	/**
	 * @param referenceList The referenceList to set.
	 */
	public void setReferenceList(List referenceList) {
		this.referenceList = referenceList;
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
	 * @return Returns the typeList.
	 */
	public List getTypeList() {
		return typeList;
	}
	/**
	 * @param typeList The typeList to set.
	 */
	public void setTypeList(List typeList) {
		this.typeList = typeList;
	}
	private List  dataTypeList;

	
	
	/**
	 * @return Returns the referencemappingVO.
	 */
	public ReferencemappingVO getReferencemappingVO() {
		return referencemappingVO;
	}
	/**
	 * @param referencemappingVO The referencemappingVO to set.
	 */
	public void setReferencemappingVO(ReferencemappingVO referencemappingVO) {
		this.referencemappingVO = referencemappingVO;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}

}
