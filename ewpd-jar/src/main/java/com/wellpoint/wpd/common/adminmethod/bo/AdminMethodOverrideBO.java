/*
 * Created on Sep 13, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethod.bo;


import com.wellpoint.wpd.common.bo.BusinessObject;
import java.util.List;
/**
 * @author U15701
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodOverrideBO extends BusinessObject{
	
	
	private String spsName;
	private String reference;
	private int spsId;
	private int adminMethodNumber;
	private String entityType;
	private int entitySysId;
	private int benefitCompSysId;
	private int bnftAdmnId;
	private int adminMethodSysId;
	private String adminMethodDesc;
	private String adminMethod;
	private int referenceId;
	private int validationId;
	private int contractSysId;
	private int tierSysId; //CARS:AM2
	private List validationBOList;
	private int benefitSysId;

	private String termQuery ="";
	private String tierCode; //CARS:AM2

	private String productFamily="";	
	private boolean posProductFamily ;
	private String benefitComponentName;
	
	private int tierIndicator;//CARS:AM DF
	private String tierTypeIndicator;

	

	
	/**
	 * @return Returns the termQuery.
	 */
	public String getTermQuery() {
		return termQuery;
	}
	/**
	 * @param termQuery The termQuery to set.
	 */
	public void setTermQuery(String termQuery) {
		this.termQuery = termQuery;
	}
	/**
	 * @return Returns the tierId.
	 */
	public int getTierSysId() {
		return tierSysId;
	}
	/**
	 * @param tierId The tierId to set.
	 */
	public void setTierSysId(int tierSysId) {
		this.tierSysId = tierSysId;
	}
	/**
	 * @return Returns the productFamily.
	 */
	public String getProductFamily() {
		return productFamily;
	}
	/**
	 * @param productFamily The productFamily to set.
	 */
	public void setProductFamily(String productFamily) {
		this.productFamily = productFamily;
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
	 * @return Returns the validationBOList.
	 */
	public List getValidationBOList() {
		return validationBOList;
	}
	/**
	 * @param validationBOList The validationBOList to set.
	 */
	public void setValidationBOList(List validationBOList) {
		this.validationBOList = validationBOList;
	}
    /**
     * @return validationId
     * 
     * Returns the validationId.
     */
    public int getValidationId() {
        return validationId;
    }
    /**
     * @param validationId
     * 
     * Sets the validationId.
     */
    public void setValidationId(int validationId) {
        this.validationId = validationId;
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
	/**
	 * @return Returns the spsName.
	 */
	public String getSpsName() {
		return spsName;
	}
	/**
	 * @param spsName The spsName to set.
	 */
	public void setSpsName(String srsName) {
		this.spsName = srsName;
	}
	

    /**
     * Returns the spsId
     * @return int spsId.
     */

    public int getSpsId() {
        return spsId;
    }
    /**
     * Sets the spsId
     * @param spsId.
     */

    public void setSpsId(int spsId) {
        this.spsId = spsId;
    }
   
    /**
     * @return adminMethodSysId
     * Returns the adminMethodSysId.
     */
    public int getAdminMethodSysId() {
        return adminMethodSysId;
    }
    /**
     * @param adminMethodSysId
     * Sets the adminMethodSysId.
     */
    public void setAdminMethodSysId(int adminMethodSysId) {
        this.adminMethodSysId = adminMethodSysId;
    }
    /**
     * @return benefitCompSysId
     * Returns the benefitCompSysId.
     */
    public int getBenefitCompSysId() {
        return benefitCompSysId;
    }
    /**
     * @param benefitCompSysId
     * Sets the benefitCompSysId.
     */
    public void setBenefitCompSysId(int benefitCompSysId) {
        this.benefitCompSysId = benefitCompSysId;
    }
 
    /**
     * @return entitySysId
     * Returns the entitySysId.
     */
    public int getEntitySysId() {
        return entitySysId;
    }
    /**
     * @param entitySysId
     * Sets the entitySysId.
     */
    public void setEntitySysId(int entitySysId) {
        this.entitySysId = entitySysId;
    }
    /**
     * @return entityType
     * Returns the entityType.
     */
    public String getEntityType() {
        return entityType;
    }
    /**
     * @param entityType
     * Sets the entityType.
     */
    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }
    /**
     * @return adminMethodNumber
     * Returns the adminMethodNumber.
     */
    public int getAdminMethodNumber() {
        return adminMethodNumber;
    }
    /**
     * @param adminMethodNumber
     * Sets the adminMethodNumber.
     */
    public void setAdminMethodNumber(int adminMethodNumber) {
        this.adminMethodNumber = adminMethodNumber;
    }
   
 

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
	 */
	public boolean equals(BusinessObject object) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	    /**
     * Returns the adminMethod
     * @return String adminMethod.
     */

    public String getAdminMethod() {
        return adminMethod;
    }
    /**
     * Sets the adminMethod
     * @param adminMethod.
     */

    public void setAdminMethod(String adminMethod) {
        this.adminMethod = adminMethod;
    }
    /**
     * Returns the adminMethodDesc
     * @return String adminMethodDesc.
     */

    public String getAdminMethodDesc() {
        return adminMethodDesc;
    }
    /**
     * Sets the adminMethodDesc
     * @param adminMethodDesc.
     */

    public void setAdminMethodDesc(String adminMethodDesc) {
        this.adminMethodDesc = adminMethodDesc;
    }
	
	/**
	 * @return Returns the bnftAdmnId.
	 */
	public int getBnftAdmnId() {
		return bnftAdmnId;
	}
	/**
	 * @param bnftAdmnId The bnftAdmnId to set.
	 */
	public void setBnftAdmnId(int bnftAdmnId) {
		this.bnftAdmnId = bnftAdmnId;
	}
    /**
     * Returns the referenceId
     * @return int referenceId.
     */
    public int getReferenceId() {
        return referenceId;
    }
    /**
     * Sets the referenceId
     * @param referenceId.
     */
    public void setReferenceId(int referenceId) {
        this.referenceId = referenceId;
    }
    /**
     * Returns the contractSysId
     * @return int contractSysId.
     */

    public int getContractSysId() {
        return contractSysId;
    }
    /**
     * Sets the contractSysId
     * @param contractSysId.
     */

    public void setContractSysId(int contractSysId) {
        this.contractSysId = contractSysId;
    }
	/**
	 * @return Returns the tierCode.
	 */
	public String getTierCode() {
		return tierCode;
	}
	/**
	 * @param tierCode The tierCode to set.
	 */
	public void setTierCode(String tierCode) {
		this.tierCode = tierCode;
	}
    /**
	 * @return Returns the posProductFamily.
	 */
	public boolean isPosProductFamily() {
		return posProductFamily;
	}
	/**
	 * @param posProductFamily The posProductFamily to set.
	 */
	public void setPosProductFamily(boolean posProductFamily) {
		this.posProductFamily = posProductFamily;
	}
	/**
	 * @return Returns the benefitComponentName.
	 */
	public String getBenefitComponentName() {
		return benefitComponentName;
	}
	/**
	 * @param benefitComponentName The benefitComponentName to set.
	 */
	public void setBenefitComponentName(String benefitComponentName) {
		this.benefitComponentName = benefitComponentName;
	}
	/**
	 * @return Returns the tierIndicator.
	 */
	public int getTierIndicator() {
		return tierIndicator;
	}
	/**
	 * @param tierIndicator The tierIndicator to set.
	 */
	public void setTierIndicator(int tierIndicator) {
		this.tierIndicator = tierIndicator;
	}
	/**
	 * @return Returns the tierTypeIndicator.
	 */
	public String getTierTypeIndicator() {
		return tierTypeIndicator;
	}
	/**
	 * @param tierTypeIndicator The tierTypeIndicator to set.
	 */
	public void setTierTypeIndicator(String tierTypeIndicator) {
		this.tierTypeIndicator = tierTypeIndicator;
	}
}
