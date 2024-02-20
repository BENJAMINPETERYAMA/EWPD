/*
 * Created on Mar 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.standardbenefit.locateresult;

import com.wellpoint.wpd.common.bo.LocateResult;

import java.util.List;


/**
 * @author u13154
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StandardBenefitLocateResult extends LocateResult{
	
	private int standardBenefitKey;
    private String benefitIdentifier;
    private String description;
    private int parentSystemId;
/*
 * BusinessObject Base class Attributes.  
 * private int version;
 * private String status;
 * private String createdUser;
 * private Date createdTimestamp;
 * private String lastUpdatedUser;
 * private Date lastUpdatedTimestamp;
 */    
    private List lobList = null;
    private List businessEntityList = null;
    private List businessGroupList = null;
    private List termList = null;
    private List qualifierList = null;
    private List PVAList = null;
    private List dataTypeList = null;
    
    private boolean versionFlag = false;

	/**
	 * 
	 */
	public StandardBenefitLocateResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return Returns the benefitIdentifier.
	 */
	public String getBenefitIdentifier() {
		return benefitIdentifier;
	}
	/**
	 * @param benefitIdentifier The benefitIdentifier to set.
	 */
	public void setBenefitIdentifier(String benefitIdentifier) {
		this.benefitIdentifier = benefitIdentifier;
	}
	/**
	 * @return Returns the businessEntityList.
	 */
	public List getBusinessEntityList() {
		return businessEntityList;
	}
	/**
	 * @param businessEntityList The businessEntityList to set.
	 */
	public void setBusinessEntityList(List businessEntityList) {
		this.businessEntityList = businessEntityList;
	}
	/**
	 * @return Returns the businessGroupList.
	 */
	public List getBusinessGroupList() {
		return businessGroupList;
	}
	/**
	 * @param businessGroupList The businessGroupList to set.
	 */
	public void setBusinessGroupList(List businessGroupList) {
		this.businessGroupList = businessGroupList;
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
 * @return Returns the lobList.
 */
public List getLobList() {
	return lobList;
}
/**
 * @param lobList The lobList to set.
 */
public void setLobList(List lobList) {
	this.lobList = lobList;
}
	/**
	 * @return Returns the pVAList.
	 */
	public List getPVAList() {
		return PVAList;
	}
	/**
	 * @param list The pVAList to set.
	 */
	public void setPVAList(List list) {
		PVAList = list;
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
	 * @return Returns the standardBenefitKey.
	 */
	public int getStandardBenefitKey() {
		return standardBenefitKey;
	}
	/**
	 * @param standardBenefitKey The standardBenefitKey to set.
	 */
	public void setStandardBenefitKey(int standardBenefitKey) {
		this.standardBenefitKey = standardBenefitKey;
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
     * @return Returns the versionFlag.
     */
    public boolean isVersionFlag() {
        return versionFlag;
    }
    /**
     * @param versionFlag The versionFlag to set.
     */
    public void setVersionFlag(boolean versionFlag) {
        this.versionFlag = versionFlag;
    }
    /**
     * @return Returns the parentSystemId.
     */
    public int getParentSystemId() {
        return parentSystemId;
    }
    /**
     * @param parentSystemId The parentSystemId to set.
     */
    public void setParentSystemId(int parentSystemId) {
        this.parentSystemId = parentSystemId;
    }
}
