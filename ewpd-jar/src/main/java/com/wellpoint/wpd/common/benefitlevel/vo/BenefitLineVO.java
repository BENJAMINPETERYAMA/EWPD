/*
 * Created on Mar 9, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.benefitlevel.vo;



/**
 * @author u12322
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitLineVO{
	
	private String pvaCode; //Added By Arun 4/7/2007 to save pva code 
	
	private String PVA;
	
	private int benefitLineId;
	
	private int benefitLevelId;
	
	private int dataTypeId;
	
	private String benefitValue;	
	
	private String dataTypeName;
// **Change**	
	private String reference;
	
	private String referenceCode;
	
	private String bnftLineNotesExist;
// **End**

// ** Enhancement **
	
	private int benefitDefenitionId;
//End
//	 Change for performance on 12th Dec 2007
	private boolean isModified;
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
	/**
	 * @return Returns the pvaCode.
	 */
	public String getPvaCode() {
		return pvaCode;
	}
	/**
	 * @param pvaCode The pvaCode to set.
	 */
	public void setPvaCode(String pvaCode) {
		this.pvaCode = pvaCode;
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

/**
 * @return isModified
 * 
 * Returns the isModified.
 */
public boolean isModified() {
    return isModified;
}
/**
 * @param isModified
 * 
 * Sets the isModified.
 */
public void setModified(boolean isModified) {
    this.isModified = isModified;
}
}
