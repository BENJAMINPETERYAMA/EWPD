/*
 * ProductStructureBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.productstructure.bo;

import com.wellpoint.wpd.common.bo.BusinessObject;

import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductStructureBO extends BusinessObject {

    private String productStructureName;

    private int productStructureId;

    private int productStructureParentId;

    private String description;

    private String structureType;

    private String mandateType;

    private String stateId;

    private String stateDesc;

    private String mandateDesc;

    private List associatedBenefitComponentList;

    private Date effectiveDate;

    private Date expiryDate;

    private List businessDomains;

    private int version;
    
    private int benifitComponentId;
    
    private int productSysId;
    
    private String statusFlag;
    
    private List invalidComponentList;

    private String lineOfBusiness; 
    
//  September release change added new field 'Product Family'
    
    private String productFamilyId;
    
    private String productFamilyDesc;
    

    /**
     * Returns the associatedBenefitComponentList
     * 
     * @return List associatedBenefitComponentList.
     */

    public List getAssociatedBenefitComponentList() {
        return associatedBenefitComponentList;
    }


    /**
     * Sets the associatedBenefitComponentList
     * 
     * @param associatedBenefitComponentList.
     */

    public void setAssociatedBenefitComponentList(
            List associatedBenefitComponentList) {
        this.associatedBenefitComponentList = associatedBenefitComponentList;
    }


    /**
     * Returns the description
     * 
     * @return String description.
     */

    public String getDescription() {
        return description;
    }


    /**
     * Sets the description
     * 
     * @param description.
     */

    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * Returns the effectiveDate
     * 
     * @return Date effectiveDate.
     */

    public Date getEffectiveDate() {
        return effectiveDate;
    }


    /**
     * Sets the effectiveDate
     * 
     * @param effectiveDate.
     */

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }


    /**
     * Returns the expiryDate
     * 
     * @return Date expiryDate.
     */

    public Date getExpiryDate() {
        return expiryDate;
    }


    /**
     * Sets the expiryDate
     * 
     * @param expiryDate.
     */

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }


    /**
     * Returns the productStructureId
     * 
     * @return int productStructureId.
     */

    public int getProductStructureId() {
        return productStructureId;
    }


    /**
     * Sets the productStructureId
     * 
     * @param productStructureId.
     */

    public void setProductStructureId(int productStructureId) {
        this.productStructureId = productStructureId;
    }


    /**
     * Returns the productStructureName
     * 
     * @return String productStructureName.
     */

    public String getProductStructureName() {
        return productStructureName;
    }


    /**
     * Sets the productStructureName
     * 
     * @param productStructureName.
     */

    public void setProductStructureName(String productStructureName) {
        this.productStructureName = productStructureName;
    }


    /**
     * Returns the productStructureParentId
     * 
     * @return int productStructureParentId.
     */

    public int getProductStructureParentId() {
        return productStructureParentId;
    }


    /**
     * Sets the productStructureParentId
     * 
     * @param productStructureParentId.
     */

    public void setProductStructureParentId(int productStructureParentId) {
        this.productStructureParentId = productStructureParentId;
    }


    /**
     * Returns the businessDomains
     * 
     * @return List businessDomains.
     */

    public List getBusinessDomains() {
        return businessDomains;
    }


    /**
     * Sets the businessDomains
     * 
     * @param businessDomains.
     */

    public void setBusinessDomains(List businessDomains) {
        this.businessDomains = businessDomains;
    }


    /**
     * Returns the version
     * 
     * @return int version.
     */

    public int getVersion() {
        return version;
    }


    /**
     * Sets the version
     * 
     * @param version.
     */

    public void setVersion(int version) {
        this.version = version;
    }


    /**
     * @return Returns the mandateType.
     */
    public String getMandateType() {
        return mandateType;
    }


    /**
     * @param mandateType
     *            The mandateType to set.
     */
    public void setMandateType(String mandateType) {
        this.mandateType = mandateType;
    }


    /**
     * @return Returns the structureType.
     */
    public String getStructureType() {
        return structureType;
    }


    /**
     * @param structureType
     *            The structureType to set.
     */
    public void setStructureType(String structureType) {
        this.structureType = structureType;
    }


    /**
     * @return stateId
     * 
     * Returns the stateId.
     */
    public String getStateId() {
        return stateId;
    }


    /**
     * @param stateId
     * 
     * Sets the stateId.
     */
    public void setStateId(String stateId) {
        this.stateId = stateId;
    }


    /**
     * Overriding equals method
     * 
     * @return boolean.
     */
    public boolean equals(BusinessObject object) {
        if (object instanceof ProductStructureBO) {
            ProductStructureBO productStructure = (ProductStructureBO) object;
            if (this.productStructureId == productStructure
                    .getProductStructureId())
                return true;
        }
        return false;
    }


    /**
     * Overriding hashCode method
     * 
     * @return int.
     */
    public int hashCode() {
        return 0;
    }


    /**
     * @return stateDesc
     * 
     * Returns the stateDesc.
     */
    public String getStateDesc() {
        return stateDesc;
    }


    /**
     * @param stateDesc
     * 
     * Sets the stateDesc.
     */
    public void setStateDesc(String stateDesc) {
        this.stateDesc = stateDesc;
    }


    /**
     * Overriding toString method
     * 
     * @return String.
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("ProductStructureId = ").append(
                this.getProductStructureId());
        buffer.append("ProductStructureParentId = ").append(
                this.getProductStructureParentId());
        buffer.append(", productStructureName = ").append(productStructureName);
        buffer.append(", description = ").append(description);
        buffer.append(", effectiveDate = ").append(effectiveDate);
        buffer.append(", expiryDate = ").append(expiryDate);
        buffer.append(", version = ").append(this.getVersion());
        buffer.append(", status = ").append(this.getStatus());
        buffer.append(", domainValue = ").append(businessDomains);
        buffer.append(", createdUser = ").append(this.getCreatedUser());
        buffer.append(", createdTimestamp = ").append(
                this.getCreatedTimestamp());
        buffer.append(", lastUpdatedUser = ").append(this.getLastUpdatedUser());
        buffer.append(", lastUpdatedTimestamp = ").append(
                this.getLastUpdatedTimestamp());
        return buffer.toString();

    }


    /**
     * @return mandateDesc
     * 
     * Returns the mandateDesc.
     */
    public String getMandateDesc() {
        return mandateDesc;
    }


    /**
     * @param mandateDesc
     * 
     * Sets the mandateDesc.
     */
    public void setMandateDesc(String mandateDesc) {
        this.mandateDesc = mandateDesc;
    }
	/**
	 * @return Returns the benifitComponentId.
	 */
	public int getBenifitComponentId() {
		return benifitComponentId;
	}
	/**
	 * @param benifitComponentId The benifitComponentId to set.
	 */
	public void setBenifitComponentId(int benifitComponentId) {
		this.benifitComponentId = benifitComponentId;
	}
	/**
	 * @return Returns the productSysId.
	 */
	public int getProductSysId() {
		return productSysId;
	}
	/**
	 * @param productSysId The productSysId to set.
	 */
	public void setProductSysId(int productSysId) {
		this.productSysId = productSysId;
	}
	/**
	 * @return Returns the statusFlag.
	 */
	public String getStatusFlag() {
		return statusFlag;
	}
	/**
	 * @param statusFlag The statusFlag to set.
	 */
	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}
	/**
	 * @return Returns the invalidComponentList.
	 */
	public List getInvalidComponentList() {
		return invalidComponentList;
	}
	/**
	 * @param invalidComponentList The invalidComponentList to set.
	 */
	public void setInvalidComponentList(List invalidComponentList) {
		this.invalidComponentList = invalidComponentList;
	}
	 
	
	
	/**
	 * @param lineOfBusiness The lineOfBusiness to set.
	 */
	public void setLineOfBusiness(String lineOfBusiness) {
		 int flag = 0;
		 String stringVal = null;
	        StringBuffer stringBuffer = new StringBuffer();
	        if (null != lineOfBusiness) {
	            StringTokenizer stringTokenizer = new StringTokenizer(
	            		lineOfBusiness, "~");
	            while (stringTokenizer.hasMoreElements()) {
	                stringTokenizer.nextToken();
	               if(stringTokenizer.hasMoreElements()){
	                    stringVal = stringTokenizer.nextToken();
	                }
	               
	                if (flag != 0) {
	                    stringBuffer.append(", ");
	                } else {
	                    flag = 1;
	                }
	                stringBuffer.append(stringVal);
	               // stringTokenizer.nextToken();
	            }
	        }
	        String lobDesc = stringBuffer.toString();
		this.lineOfBusiness = lobDesc;
	}
	/**
	 * @return Returns the lineOfBusiness.
	 */
	public String getLineOfBusiness() {
		return lineOfBusiness;
	}
	/**
	 * @return Returns the productFamilyDesc.
	 */
	public String getProductFamilyDesc() {
		return productFamilyDesc;
	}
	/**
	 * @param productFamilyDesc The productFamilyDesc to set.
	 */
	public void setProductFamilyDesc(String productFamilyDesc) {
		this.productFamilyDesc = productFamilyDesc;
	}
/**
 * @return Returns the productFamilyId.
 */
public String getProductFamilyId() {
	return productFamilyId;
}
/**
 * @param productFamilyId The productFamilyId to set.
 */
public void setProductFamilyId(String productFamilyId) {
	this.productFamilyId = productFamilyId;
}
}