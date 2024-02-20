/*
 * Created on Mar 9, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.benefitlevel.bo;

import java.util.Date;
import java.util.List;

import com.wellpoint.wpd.common.bo.BusinessObject;


/**
 * @author u12322
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitLevelBO implements Comparable{
	
	private String benefitTerm;
	
	private String benefitQualifier;
	
	private List benefitLines;
	
	private int benefitDefinitionId;
	
	private int benefitLevelId;
	
	private String benefitLevelDesc;
	
	private String reference;	
	
	private String referenceCode;
	
	private int benefitLevelSeq;
	
	private String benefitTermCode;
	
	private String benefitQualifierCode;
	
	private String createdUser;

	private Date createdTimestamp;

    private String lastUpdatedUser;
    
    private int benefitSysId;
    
    private List referenceList;

//  Change for performance
 	private boolean modified;
// 	end	
 	
 	private String benefitLevels = null;
	private String benefitLineIds = null;
    
    //CARS START
    //DESCRIPTION : Created integer variable for Frequncy
    private int benefitFrequency;
    //DESCRIPTION : Frequency required string.
    private String frequencyRequired;	
	//CARS END
    /**
     * Returns the createdTimestamp
     * @return Date createdTimestamp.
     */

    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }
    /**
     * Sets the createdTimestamp
     * @param createdTimestamp.
     */

    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }
    /**
     * Returns the createdUser
     * @return String createdUser.
     */

    public String getCreatedUser() {
        return createdUser;
    }
    /**
     * Sets the createdUser
     * @param createdUser.
     */

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }
    /**
     * Returns the lastUpdatedTimestamp
     * @return Date lastUpdatedTimestamp.
     */

    public Date getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }
    /**
     * Sets the lastUpdatedTimestamp
     * @param lastUpdatedTimestamp.
     */

    public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }
    /**
     * Returns the lastUpdatedUser
     * @return String lastUpdatedUser.
     */

    public String getLastUpdatedUser() {
        return lastUpdatedUser;
    }
    /**
     * Sets the lastUpdatedUser
     * @param lastUpdatedUser.
     */

    public void setLastUpdatedUser(String lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
    }
    private Date lastUpdatedTimestamp;
	
// **Change**
	private List benefitTerms;
	/**
	 * Returns the benefitTerms
	 * @return List benefitTerms.
	 */
	
	public List getBenefitTerms() {
	    return benefitTerms;
	}
	/**
	 * Sets the benefitTerms
	 * @param benefitTerms.
	 */
	
	public void setBenefitTerms(List benefitTerms) {
	    this.benefitTerms = benefitTerms;
	}
// **End**	
	
//Change: Aggregate qualifier
	private List benefitQualifiers;

	/**
	 * @return Returns the benefitQualifiers.
	 */
	public List getBenefitQualifiers() {
		return benefitQualifiers;
	}
	/**
	 * @param benefitQualifiers The benefitQualifiers to set.
	 */
	public void setBenefitQualifiers(List benefitQualifiers) {
		this.benefitQualifiers = benefitQualifiers;
	}
// ** End	
	/**
	 * @return Returns the benefitLevelSeq.
	 */
	public int getBenefitLevelSeq() {
		return benefitLevelSeq;
	}
	/**
	 * @param benefitLevelSeq The benefitLevelSeq to set.
	 */
	public void setBenefitLevelSeq(int benefitLevelSeq) {
		this.benefitLevelSeq = benefitLevelSeq;
	}

	/**
	 * @return Returns the benefitDefinitionId.
	 */
	public int getBenefitDefinitionId() {
		return benefitDefinitionId;
	}
	/**
	 * @param benefitDefinitionId The benefitDefinitionId to set.
	 */
	public void setBenefitDefinitionId(int benefitDefinitionId) {
		this.benefitDefinitionId = benefitDefinitionId;
	}
	/**
	 * @return Returns the benefitQualifier.
	 */
	public String getBenefitQualifier() {
		return benefitQualifier;
	}
	/**
	 * @param benefitQualifier The benefitQualifier to set.
	 */
	public void setBenefitQualifier(String benefitQualifier) {
		this.benefitQualifier = benefitQualifier;
	}
	/**
	 * @return Returns the benefitTerm.
	 */
	public String getBenefitTerm() {
		return benefitTerm;
	}
	/**
	 * @param benefitTerm The benefitTerm to set.
	 */
	public void setBenefitTerm(String benefitTerm) {
		this.benefitTerm = benefitTerm;
	}
	
	/**
	 * @return Returns the benefitLines.
	 */
	public List getBenefitLines() {
		return benefitLines;
	}
	/**
	 * @param benefitLines The benefitLines to set.
	 */
	public void setBenefitLines(List benefitLines) {
		this.benefitLines = benefitLines;
	}
	/**
	 * @return Returns the benefitLevelDesc.
	 */
	public String getBenefitLevelDesc() {
		return benefitLevelDesc;
	}
	/**
	 * @param benefitLevelDesc The benefitLevelDesc to set.
	 */
	public void setBenefitLevelDesc(String benefitLevelDesc) {
		this.benefitLevelDesc = benefitLevelDesc;
	}
	/**
	 * @return Returns the benefitLevelId.
	 */
	public int getBenefitLevelId() {
		return benefitLevelId;
	}
	/**
	 * @param benefitLevelId The benefitLevelId to set.
	 */
	public void setBenefitLevelId(int benefitLevelId) {
		this.benefitLevelId = benefitLevelId;
	}
	/**
	 * @return Returns the reference.
	 */
	public String getReference() {
		return reference;
	}
	/**
	 * @param reference The reference to set.
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
	 */
	public boolean equals(BusinessObject object) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return Returns the benefitQualifierCode.
	 */
	public String getBenefitQualifierCode() {
		return benefitQualifierCode;
	}
	/**
	 * @param benefitQualifierCode The benefitQualifierCode to set.
	 */
	public void setBenefitQualifierCode(String benefitQualifierCode) {
		this.benefitQualifierCode = benefitQualifierCode;
	}
	/**
	 * @return Returns the benefitTermCode.
	 */
	public String getBenefitTermCode() {
		return benefitTermCode;
	}
	/**
	 * @param benefitTermCode The benefitTermCode to set.
	 */
	public void setBenefitTermCode(String benefitTermCode) {
		this.benefitTermCode = benefitTermCode;
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		int result = 0;
		BenefitLevelBO benefitLevelBO = (BenefitLevelBO)o;
		if(this.getBenefitLevelSeq() > benefitLevelBO.getBenefitLevelSeq()){
			result = 1;
		}
		if(this.getBenefitLevelSeq() < benefitLevelBO.getBenefitLevelSeq()){
			result = -1;
		}
		if(this.getBenefitLevelSeq() == benefitLevelBO.getBenefitLevelSeq()){
			result = 0;
		}
		return result;
	}
	/**
	 * @return Returns the referenceCode.
	 */
	public String getReferenceCode() {
		return referenceCode;
	}
	/**
	 * @param referenceCode The referenceCode to set.
	 */
	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}
	
	/**
	 * @return Returns the benefitSysId.
	 */
	public int getBenefitSysId() {
		return benefitSysId;
	}
	/**
	 * @param benefitSysId The benefitSysId to set.
	 */
	public void setBenefitSysId(int benefitSysId) {
		this.benefitSysId = benefitSysId;
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
//	Change for Performance
	/**
	 * @return Returns the modified.
	 */
	public boolean isModified() {
		return modified;
	}
	/**
	 * @param modified The modified to set.
	 */
	public void setModified(boolean modified) {
		this.modified = modified;
	}
//	end
	
/**
 * @return Returns the benefitLevels.
 */
public String getBenefitLevels() {
	return benefitLevels;
}
/**
 * @param benefitLevels The benefitLevels to set.
 */
public void setBenefitLevels(String benefitLevels) {
	this.benefitLevels = benefitLevels;
}
	/**
	 * @return Returns the benefitLineIds.
	 */
	public String getBenefitLineIds() {
		return benefitLineIds;
	}
	/**
	 * @param benefitLineIds The benefitLineIds to set.
	 */
	public void setBenefitLineIds(String benefitLineIds) {
		this.benefitLineIds = benefitLineIds;
	}
	/**
	 * @return Returns the benefitFrequency.
	 */
	public int getBenefitFrequency() {
		return benefitFrequency;
	}
	/**
	 * @param benefitFrequency The benefitFrequency to set.
	 */
	public void setBenefitFrequency(int benefitFrequency) {
		this.benefitFrequency = benefitFrequency;
	}
	/**
	 * @return Returns the frequencyRequired.
	 */
	public String getFrequencyRequired() {
		return frequencyRequired;
	}
	/**
	 * @param frequencyRequired The frequencyRequired to set.
	 */
	public void setFrequencyRequired(String frequencyRequired) {
		this.frequencyRequired = frequencyRequired;
	}
}
