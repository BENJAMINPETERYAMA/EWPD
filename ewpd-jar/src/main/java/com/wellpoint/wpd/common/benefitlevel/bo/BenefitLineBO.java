/*
 * Created on Mar 9, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.benefitlevel.bo;

import java.util.Date;

import com.wellpoint.wpd.common.bo.BusinessObject;


/**
 * @author u12322
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitLineBO{
	

	private String PVA;
	
	private int benefitLineId;
	
	private int benefitLevelId;
	
	private int dataTypeId;
	
	private String dataTypeName;
	
	private String benefitValue;
	
	private String pvaCode; //Added By Deepa 4/7/2007 to save pva code 
	
	private String createdUser;

    private Date createdTimestamp;

    private String lastUpdatedUser;

    private Date lastUpdatedTimestamp;
// **Change**    
    private String reference;	
	
	private String referenceCode;
	
	private String bnftLineNotesExist;
	
	private String benefitLevelDesc;
// **End**

// Enchancement
	private int benefitDefenitionId;
//Enchancement Ends
//	 Change for performance
	private boolean modified;
//	end	
	/**
	 * @return Returns the dataTypeName.
	 */
	public String getDataTypeName() {
		return dataTypeName;
	}
	/**
	 * @param dataTypeName The dataTypeName to set.
	 */
	public void setDataTypeName(String dataTypeName) {
		this.dataTypeName = dataTypeName;
	}
	/**
	 * @return Returns the pVA.
	 */
	public String getPVA() {
		return PVA;
	}
	/**
	 * @param pva The pVA to set.
	 */
	public void setPVA(String pva) {
		PVA = pva;
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
	 * @return Returns the benefitLineId.
	 */
	public int getBenefitLineId() {
		return benefitLineId;
	}
	/**
	 * @param benefitLineId The benefitLineId to set.
	 */
	public void setBenefitLineId(int benefitLineId) {
		this.benefitLineId = benefitLineId;
	}
	
	/**
	 * @return Returns the dataTypeId.
	 */
	public int getDataTypeId() {
		return dataTypeId;
	}
	/**
	 * @param dataTypeId The dataTypeId to set.
	 */
	public void setDataTypeId(int dataTypeId) {
		this.dataTypeId = dataTypeId;
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
	 * @return Returns the benefitValue.
	 */
	public String getBenefitValue() {
		return benefitValue;
	}
	/**
	 * @param benefitValue The benefitValue to set.
	 */
	public void setBenefitValue(String benefitValue) {
		this.benefitValue = benefitValue;
	}
    /**
     * Returns the pvaCode
     * @return String pvaCode.
     */

    public String getPvaCode() {
        return pvaCode;
    }
    /**
     * Sets the pvaCode
     * @param pvaCode.
     */

    public void setPvaCode(String pvaCode) {
        this.pvaCode = pvaCode;
    }
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
    /**
     * Returns the reference
     * @return String reference.
     */

    public String getReference() {
        return reference;
    }
    /**
     * Sets the reference
     * @param reference.
     */

    public void setReference(String reference) {
        this.reference = reference;
    }
    /**
     * Returns the referenceCode
     * @return String referenceCode.
     */

    public String getReferenceCode() {
        return referenceCode;
    }
    /**
     * Sets the referenceCode
     * @param referenceCode.
     */

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }
   
    /**
     * Returns the bnftLineNotesExist
     * @return String bnftLineNotesExist.
     */

    public String getBnftLineNotesExist() {
        return bnftLineNotesExist;
    }
    /**
     * Sets the bnftLineNotesExist
     * @param bnftLineNotesExist.
     */

    public void setBnftLineNotesExist(String bnftLineNotesExist) {
        this.bnftLineNotesExist = bnftLineNotesExist;
    }
    public String getBenefitLevelDesc() {
        return benefitLevelDesc;
    }
    public void setBenefitLevelDesc(String benfitLevelDesc) {
        this.benefitLevelDesc = benfitLevelDesc;
    }

/**
 * @return Returns the benefitDefenitionId.
 */
public int getBenefitDefenitionId() {
	return benefitDefenitionId;
}
/**
 * @param benefitDefenitionId The benefitDefenitionId to set.
 */
public void setBenefitDefenitionId(int benefitDefenitionId) {
	this.benefitDefenitionId = benefitDefenitionId;
}
//Change for Performance
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
//end
}
