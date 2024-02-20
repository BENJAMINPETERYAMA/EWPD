/*
 * Created on Jul 20, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.referencemapping.bo;


import java.util.List;

import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * @author U14659
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ReferenceMappingBO extends BusinessObject {

	private String referenceId;

	private String keyValue;

	private String cobolValue;

	/**
	 * @return Returns the cobolValue.
	 */
	public String getCobolValue() {
		return cobolValue;
	}

	/**
	 * @param cobolValue
	 *            The cobolValue to set.
	 */
	public void setCobolValue(String cobolValue) {
		this.cobolValue = cobolValue;
	}

	private String description;

	private String type;

	private String term;

	private String qualifier;

	private String pva;

	private int datatype;

	private String lastUpdatedUser;

	private String createdUser;

	private String createdTime;

	private String changeTime;

	private String prevReferenceCriteria;

	private String prevType;

	private String prevTerm;

	private String prevQualifier;

	private String prevpva;

	private int prevDataType;

	private List referenceList;

	private List typeList;

	private List termList;

	private List qualifierList;

	private List pvaList;

	private List dataTypeList;

	private String termDesc;

	private String typeDesc;

	private String pvaDesc;

	private String qualDesc;

	private String datatypeDesc;

	private String referenceCriteriaDelete;

	private String typeCriteriaDelete;

	private String termCriteriaDelete;

	private String qualifierCriteriaDelete;

	private String pvaCriteriaDelete;

	private String dataTypeCriteriaDelete;

	private String itemCode;

	/**
	 * @return Returns the changeTime.
	 */
	public String getChangeTime() {
		return changeTime;
	}

	/**
	 * @param changeTime
	 *            The changeTime to set.
	 */
	public void setChangeTime(String changeTime) {
		this.changeTime = changeTime;
	}

	/**
	 * @return Returns the createdTime.
	 */
	public String getCreatedTime() {
		return createdTime;
	}

	/**
	 * @param createdTime
	 *            The createdTime to set.
	 */
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * @return Returns the itemCode.
	 */
	public String getItemCode() {
		return itemCode;
	}

	/**
	 * @param itemCode
	 *            The itemCode to set.
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	/**
	 * @return Returns the keyValue.
	 */
	public String getKeyValue() {
		return keyValue;
	}

	/**
	 * @param keyValue
	 *            The keyValue to set.
	 */
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

	/**
	 * @return Returns the dataTypeList.
	 */
	public List getDataTypeList() {
		return dataTypeList;
	}

	/**
	 * @param dataTypeList
	 *            The dataTypeList to set.
	 */
	public void setDataTypeList(List dataTypeList) {
		this.dataTypeList = dataTypeList;
	}

	/**
	 * @return Returns the dataTypeCriteriaDelete.
	 */
	public String getDataTypeCriteriaDelete() {
		return dataTypeCriteriaDelete;
	}

	/**
	 * @param dataTypeCriteriaDelete
	 *            The dataTypeCriteriaDelete to set.
	 */
	public void setDataTypeCriteriaDelete(String dataTypeCriteriaDelete) {
		this.dataTypeCriteriaDelete = dataTypeCriteriaDelete;
	}

	/**
	 * @return Returns the pvaCriteriaDelete.
	 */
	public String getPvaCriteriaDelete() {
		return pvaCriteriaDelete;
	}

	/**
	 * @param pvaCriteriaDelete
	 *            The pvaCriteriaDelete to set.
	 */
	public void setPvaCriteriaDelete(String pvaCriteriaDelete) {
		this.pvaCriteriaDelete = pvaCriteriaDelete;
	}

	/**
	 * @return Returns the qualifierCriteriaDelete.
	 */
	public String getQualifierCriteriaDelete() {
		return qualifierCriteriaDelete;
	}

	/**
	 * @param qualifierCriteriaDelete
	 *            The qualifierCriteriaDelete to set.
	 */
	public void setQualifierCriteriaDelete(String qualifierCriteriaDelete) {
		this.qualifierCriteriaDelete = qualifierCriteriaDelete;
	}

	/**
	 * @return Returns the referenceCriteriaDelete.
	 */
	public String getReferenceCriteriaDelete() {
		return referenceCriteriaDelete;
	}

	/**
	 * @param referenceCriteriaDelete
	 *            The referenceCriteriaDelete to set.
	 */
	public void setReferenceCriteriaDelete(String referenceCriteriaDelete) {
		this.referenceCriteriaDelete = referenceCriteriaDelete;
	}

	/**
	 * @return Returns the termCriteriaDelete.
	 */
	public String getTermCriteriaDelete() {
		return termCriteriaDelete;
	}

	/**
	 * @param termCriteriaDelete
	 *            The termCriteriaDelete to set.
	 */
	public void setTermCriteriaDelete(String termCriteriaDelete) {
		this.termCriteriaDelete = termCriteriaDelete;
	}

	/**
	 * @return Returns the typeCriteriaDelete.
	 */
	public String getTypeCriteriaDelete() {
		return typeCriteriaDelete;
	}

	/**
	 * @param typeCriteriaDelete
	 *            The typeCriteriaDelete to set.
	 */
	public void setTypeCriteriaDelete(String typeCriteriaDelete) {
		this.typeCriteriaDelete = typeCriteriaDelete;
	}

	/**
	 * @return Returns the datatypeDesc.
	 */
	public String getDatatypeDesc() {
		return datatypeDesc;
	}

	/**
	 * @param datatypeDesc
	 *            The datatypeDesc to set.
	 */
	public void setDatatypeDesc(String datatypeDesc) {
		this.datatypeDesc = datatypeDesc;
	}

	/**
	 * @return Returns the pvaDesc.
	 */
	public String getPvaDesc() {
		return pvaDesc;
	}

	/**
	 * @param pvaDesc
	 *            The pvaDesc to set.
	 */
	public void setPvaDesc(String pvaDesc) {
		this.pvaDesc = pvaDesc;
	}

	/**
	 * @return Returns the qualDesc.
	 */
	public String getQualDesc() {
		return qualDesc;
	}

	/**
	 * @param qualDesc
	 *            The qualDesc to set.
	 */
	public void setQualDesc(String qualDesc) {
		this.qualDesc = qualDesc;
	}

	/**
	 * @return Returns the termDesc.
	 */
	public String getTermDesc() {
		return termDesc;
	}

	/**
	 * @param termDesc
	 *            The termDesc to set.
	 */
	public void setTermDesc(String termDesc) {
		this.termDesc = termDesc;
	}

	/**
	 * @return Returns the typeDesc.
	 */
	public String getTypeDesc() {
		return typeDesc;
	}

	/**
	 * @param typeDesc
	 *            The typeDesc to set.
	 */
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

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
	 * @return Returns the referenceList.
	 */
	public List getReferenceList() {
		return referenceList;
	}

	/**
	 * @param referenceList
	 *            The referenceList to set.
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
	 * @param termList
	 *            The termList to set.
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
	 * @param typeList
	 *            The typeList to set.
	 */
	public void setTypeList(List typeList) {
		this.typeList = typeList;
	}

	/**
	 * @return Returns the prevDataType.
	 */
	public int getPrevDataType() {
		return prevDataType;
	}

	/**
	 * @param prevDataType
	 *            The prevDataType to set.
	 */
	public void setPrevDataType(int prevDataType) {
		this.prevDataType = prevDataType;
	}

	/**
	 * @return Returns the prevpva.
	 */
	public String getPrevpva() {
		return prevpva;
	}

	/**
	 * @param prevpva
	 *            The prevpva to set.
	 */
	public void setPrevpva(String prevpva) {
		this.prevpva = prevpva;
	}

	/**
	 * @return Returns the prevQualifier.
	 */
	public String getPrevQualifier() {
		return prevQualifier;
	}

	/**
	 * @param prevQualifier
	 *            The prevQualifier to set.
	 */
	public void setPrevQualifier(String prevQualifier) {
		this.prevQualifier = prevQualifier;
	}

	/**
	 * @return Returns the prevReferenceCriteria.
	 */
	public String getPrevReferenceCriteria() {
		return prevReferenceCriteria;
	}

	/**
	 * @param prevReferenceCriteria
	 *            The prevReferenceCriteria to set.
	 */
	public void setPrevReferenceCriteria(String prevReferenceCriteria) {
		this.prevReferenceCriteria = prevReferenceCriteria;
	}

	/**
	 * @return Returns the prevTerm.
	 */
	public String getPrevTerm() {
		return prevTerm;
	}

	/**
	 * @param prevTerm
	 *            The prevTerm to set.
	 */
	public void setPrevTerm(String prevTerm) {
		this.prevTerm = prevTerm;
	}

	/**
	 * @return Returns the prevType.
	 */
	public String getPrevType() {
		return prevType;
	}

	/**
	 * @param prevType
	 *            The prevType to set.
	 */
	public void setPrevType(String prevType) {
		this.prevType = prevType;
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

	/**
	 * @return Returns the datatype.
	 */
	public int getDatatype() {
		return datatype;
	}

	/**
	 * @param datatype
	 *            The datatype to set.
	 */
	public void setDatatype(int datatype) {
		this.datatype = datatype;
	}

	/**
	 * @return Returns the pva.
	 */
	public String getPva() {
		return pva;
	}

	/**
	 * @param pva
	 *            The pva to set.
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
	 * @param qualifier
	 *            The qualifier to set.
	 */
	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	/**
	 * @return Returns the referenceId.
	 */
	public String getReferenceId() {
		return referenceId;
	}

	/**
	 * @param referenceId
	 *            The referenceId to set.
	 */
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
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
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            The type to set.
	 */
	public void setType(String type) {
		this.type = type;
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
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
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
}