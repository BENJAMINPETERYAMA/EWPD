/*
 * ContractProductGeneralInfoBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.contract;

import java.util.Date;
import java.util.List;

import com.wellpoint.wpd.common.contract.request.RetrieveContractProductRequest;
import com.wellpoint.wpd.common.contract.response.RetrieveContractProductResponse;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.product.bo.ProductBO;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractProductGeneralInfoBackingBean extends ContractBackingBean {
   
    private String  lineOfBusinessDiv = "ALL~ALL";
    
    private String businessEntityDiv = "ALL~ALL";
    
    private String  businessGroupDiv = "ALL~ALL";
	/*START CARS*/
	private String marketBusinessUnit = "ALL~ALL";
	/*END CARS*/
    private String productFamilyDiv;
    
    private String productName;
    
    private String productDescription;
    
    private String productStructDiv;

    private String effectiveDate;

    private String expiryDate = WebConstants.DEFAULT_EXP_DATE;

    private Date creationDate;

    private String createdBy;

    private Date updationDate;

    private String updatedBy;

    private String printGeneralInfo;

   private boolean printMode;
   
   private String printValue;
   
   private String productStructureVersion;
    
	/**
	 * @return Returns the productType.
	 */
	public String getProductType() {
		return productType;
	}
	/**
	 * @param productType The productType to set.
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}
    private String productType;

//  private String productKey;
	
//  private String businessDomDiv;

//  private String businessDomain;	

//  private String productStructure;
    
//  private List productStructureList;
    
//    private String status;
//    
//    private String state;
//    
//    private String version;
    
    private List validationMessages;	
    //CR added product version
    
    private int productVersion;
	
	/**
	 * 
	 */
	public ContractProductGeneralInfoBackingBean() {
		super();
		super.setBreadCrumbText();
	}

	
//  product General Information
    public String displayProductGeneralInfo(){
    	int productKey = super.getContractSession().getProductId();

      	 //gets request
    	RetrieveContractProductRequest request = (RetrieveContractProductRequest)getServiceRequest(ServiceManager.RETRIEVE_CONTRACT_PRODUCT);
        request.setProductKey(productKey);
        //calls the service
        RetrieveContractProductResponse response = (RetrieveContractProductResponse)executeService(request);
        if(response != null && response.getProductBO() != null) {
            setValuesToBackingBean(productKey, response.getProductBO(), response.getDomainDetail());
        }
    	
		if (super.isViewMode())
			return "displayProductGeneralInfoView";
		else if(this.isPrintMode())
			return "";
		else
    	return "displayProductGeneralInfo";
    }    
       
   /**
     * This method sets the method back to backing bean
     * @param productBO
     * @param domainDetail
     */
   private void setValuesToBackingBean(int productKey, ProductBO productBO, DomainDetail domainDetail){
   	this.productStructureVersion =String.valueOf(productBO.getProductStructureVersion());
   	this.productName = productBO.getProductName();
   	this.effectiveDate = WPDStringUtil.getStringDate(productBO.getEffectiveDate());
   	this.expiryDate = WPDStringUtil.getStringDate(productBO.getExpiryDate());
   	this.productDescription = productBO.getProductDesc();
   	if(productBO.getProductStructureName() != null) {
   	    this.productStructDiv = ""+productBO.getProductStructureName();//+"~"+productBO.getProductStructureKey();
   	}
   	if(productBO.getProductName() != null) {
   	    this.productFamilyDiv = productBO.getProductFamilyId();//productBO.getProductFamilyDesc();// + "~" + productBO.getProductFamilyId();
   	}
   	this.lineOfBusinessDiv = WPDStringUtil.getTildaString(domainDetail.getLineOfBusiness());
   	this.businessEntityDiv = WPDStringUtil.getTildaString(domainDetail.getBusinessEntity());
   	this.businessGroupDiv = WPDStringUtil.getTildaString(domainDetail.getBusinessGroup());
	/*START CARS*/
   	this.marketBusinessUnit = WPDStringUtil.getTildaString(domainDetail.getMarketBusinessUnit());
	/*END CARS*/
	
   	this.createdBy = productBO.getCreatedUser();	
	this.updatedBy = productBO.getLastUpdatedUser();
	this.productType = productBO.getProductType();
	
		Date createdDate=productBO.getCreatedTimestamp();
		this.creationDate = createdDate;	
	
		Date updatedDate = productBO.getLastUpdatedTimestamp();
		this.updationDate = updatedDate;
		// CR -- added product version 
		
		this.productVersion = productBO.getVersion();
	
//	this.version=String.valueOf(productBO.getVersion()); //not req
//	if(productBO.getState() != null) {
//	    this.state = productBO.getState().getState();
//	}
//	this.status = productBO.getStatus();
//
//	//hidden field mapping
//	this.productKey = Integer.toString(productKey);
   	
   }
   
// ---------------------------------getters/setters-----------------------	
 
	/**
	 * Returns the businessEntityDiv
	 * @return String businessEntityDiv.
	 */
	public String getBusinessEntityDiv() {
		return businessEntityDiv;
	}
	/**
	 * Sets the businessEntityDiv
	 * @param businessEntityDiv.
	 */
	public void setBusinessEntityDiv(String businessEntityDiv) {
		this.businessEntityDiv = businessEntityDiv;
	}
	/**
	 * Returns the businessGroupDiv
	 * @return String businessGroupDiv.
	 */
	public String getBusinessGroupDiv() {
		return businessGroupDiv;
	}
	/**
	 * Sets the businessGroupDiv
	 * @param businessGroupDiv.
	 */
	public void setBusinessGroupDiv(String businessGroupDiv) {
		this.businessGroupDiv = businessGroupDiv;
	}
	/**
	 * Returns the createdBy
	 * @return String createdBy.
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * Sets the createdBy
	 * @param createdBy.
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * Returns the creationDate
	 * @return String creationDate.
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	/**
	 * Sets the creationDate
	 * @param creationDate.
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	/**
	 * Returns the effectiveDate
	 * @return String effectiveDate.
	 */
	public String getEffectiveDate() {
		return effectiveDate;
	}
	/**
	 * Sets the effectiveDate
	 * @param effectiveDate.
	 */
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	/**
	 * Returns the expiryDate
	 * @return String expiryDate.
	 */
	public String getExpiryDate() {
		return expiryDate;
	}
	/**
	 * Sets the expiryDate
	 * @param expiryDate.
	 */
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	/**
	 * Returns the lineOfBusinessDiv
	 * @return String lineOfBusinessDiv.
	 */
	public String getLineOfBusinessDiv() {
		return lineOfBusinessDiv;
	}
	/**
	 * Sets the lineOfBusinessDiv
	 * @param lineOfBusinessDiv.
	 */
	public void setLineOfBusinessDiv(String lineOfBusinessDiv) {
		this.lineOfBusinessDiv = lineOfBusinessDiv;
	}
	/**
	 * Returns the productDescription
	 * @return String productDescription.
	 */
	public String getProductDescription() {
		return productDescription;
	}
	/**
	 * Sets the productDescription
	 * @param productDescription.
	 */
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	/**
	 * Returns the productFamilyDiv
	 * @return String productFamilyDiv.
	 */
	public String getProductFamilyDiv() {
		return productFamilyDiv;
	}
	/**
	 * Sets the productFamilyDiv
	 * @param productFamilyDiv.
	 */
	public void setProductFamilyDiv(String productFamilyDiv) {
		this.productFamilyDiv = productFamilyDiv;
	}
	/**
	 * Returns the productName
	 * @return String productName.
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * Sets the productName
	 * @param productName.
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * Returns the productStructDiv
	 * @return String productStructDiv.
	 */
	public String getProductStructDiv() {
		return productStructDiv;
	}
	/**
	 * Sets the productStructDiv
	 * @param productStructDiv.
	 */
	public void setProductStructDiv(String productStructDiv) {
		this.productStructDiv = productStructDiv;
	}
	/**
	 * Returns the updatedBy
	 * @return String updatedBy.
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}
	/**
	 * Sets the updatedBy
	 * @param updatedBy.
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	/**
	 * Returns the updationDate
	 * @return String updationDate.
	 */
	public Date getUpdationDate() {
		return updationDate;
	}
	/**
	 * Sets the updationDate
	 * @param updationDate.
	 */
	public void setUpdationDate(Date updationDate) {
		this.updationDate = updationDate;
	}
/**
 * Returns the validationMessages
 * @return List validationMessages.
 */
public List getValidationMessages() {
	return validationMessages;
}
/**
 * Sets the validationMessages
 * @param validationMessages.
 */
public void setValidationMessages(List validationMessages) {
	this.validationMessages = validationMessages;
}

/**
 * @return Returns the printGeneralInfo.
 */
public String getPrintGeneralInfo() {
	displayProductGeneralInfo();
	this.printMode = true;
	return "";
}
/**
 * @param printGeneralInfo The printGeneralInfo to set.
 */
public void setPrintGeneralInfo(String printGeneralInfo) {
	this.printGeneralInfo = printGeneralInfo;
}

/**
 * @return Returns the printMode.
 */
public boolean isPrintMode() {
	return printMode;
}
/**
 * @param printMode The printMode to set.
 */
public void setPrintMode(boolean printMode) {
	this.printMode = printMode;
}

/**
 * Returns the printValue
 * @return String printValue.
 */
public String getPrintValue() {
	
    String requestForPrint = getRequest().getParameter("printValueForGeneralInfo");
    if(null != requestForPrint && !requestForPrint.equals(WebConstants.EMPTY_STRING)){
        printValue = requestForPrint;
    }else{
        printValue = WebConstants.EMPTY_STRING;
    }
    return printValue;
}
/**
 * Sets the printValue
 * @param printValue.
 */
public void setPrintValue(String printValue) {
	this.printValue = printValue;
}
	/**
	 * @return Returns the productVersion.
	 */
	public int getProductVersion() {
		return productVersion;
	}
	/**
	 * @param productVersion The productVersion to set.
	 */
	public void setProductVersion(int productVersion) {
		this.productVersion = productVersion;
	}
/**
 * @return Returns the productStructureVersion.
 */
public String getProductStructureVersion() {
	return productStructureVersion;
}
/**
 * @param productStructureVersion The productStructureVersion to set.
 */
public void setProductStructureVersion(String productStructureVersion) {
	this.productStructureVersion = productStructureVersion;
}
	/**
	 * @return Returns the marketBusinessUnit.
	 */
	public String getMarketBusinessUnit() {
		return marketBusinessUnit;
	}
	/**
	 * @param marketBusinessUnit The marketBusinessUnit to set.
	 */
	public void setMarketBusinessUnit(String marketBusinessUnit) {
		this.marketBusinessUnit = marketBusinessUnit;
	}
}
