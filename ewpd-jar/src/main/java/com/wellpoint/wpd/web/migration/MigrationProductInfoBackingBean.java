/*
 * MigrationProductInfoBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.migration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;

import com.wellpoint.wpd.business.migration.util.MigrationContractUtil;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.contract.request.RetrieveReservedContractRequest;
import com.wellpoint.wpd.common.contract.response.RetrieveReservedContractResponse;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.util.StringUtil;
import com.wellpoint.wpd.common.migration.bo.MigrationContract;
import com.wellpoint.wpd.common.migration.bo.MigrationDateSegment;
import com.wellpoint.wpd.common.migration.bo.MigrationDomainInfo;
import com.wellpoint.wpd.common.migration.request.MigrationProductInfoRequest;
import com.wellpoint.wpd.common.migration.request.SaveMigGeneralInfoRequest;
import com.wellpoint.wpd.common.migration.response.MigrationProductInfoResponse;
import com.wellpoint.wpd.common.migration.response.SaveMigGeneralInfoResponse;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class MigrationProductInfoBackingBean extends MigrationBaseBackingBean {

    private String contractID;

    private String contractType = "";

    private int migratedContractSysID;

    private int migratedProdStructureMappingSysID;

    private String lineOfBusiness;

    private String businessEntity;

    private String businessGroup;

    private String effectiveDate;

    private String expiryDate;

    private String legacyStructure;

    private String productName;

    private String oldProductName;

    private String productFamily;

    private String lockStatusFlag;

    private String legacySourceSystem;

    private String product;

    private int ewpdProductSysId;
    
    private int ewpdContractSysID;
    
    private List validationMessages = null;

    private List productInformationList = null;

    private boolean productInvalid = false;

    private boolean custom = true;

    private boolean persistStatus;

    private String option;

    private String system;

    private List validProducts;

    private boolean productsRetrievedForPopup = false;
    
    private boolean disableField = true;
    
    private boolean hideProductPopUp = false;
    
    private boolean hideReplaceProductPopup;

    private static String BREAD_CRUMB_TEXT_STEP5 = "Administration >> Contract Migration Wizard >> Product Information (Step 5)";

	private String benefitYrIndWarningMessage;
	
	private int productParentSysId;


    public MigrationProductInfoBackingBean() {
        super();
        this.setBreadCrumbTextLeft(BREAD_CRUMB_TEXT_STEP5);
        String contractId = getMigrationContractSession()
                .getMigrationContract().getContractId();
        String startDate = WPDStringUtil.convertDateToString(this
                .getMigrationContractSession().getStartDateEwpd());
        String endDate = WPDStringUtil.convertDateToString(this
                .getMigrationContractSession().getEndDate());
        this.setBreadCrumbTextRight(WebConstants.BREAD_CRUMB_CONTRACT
                + contractId + " (" + startDate + " - " + endDate + ")");
    }


    private void setDomainValue(List migrationDomainInfoList) {
        List lineOfBusinessList = new ArrayList();
        List businessEntityList = new ArrayList();
        List businessGroupList = new ArrayList();
        MigrationDomainInfo domain = null;
        if (migrationDomainInfoList != null
                    && !migrationDomainInfoList.isEmpty()) {    
            for (Iterator iter = migrationDomainInfoList.iterator(); iter
                    .hasNext();) {
                domain = (MigrationDomainInfo) iter.next();
                lineOfBusinessList.add(domain.getLobDesc().equals(
                        WebConstants.ALL) ? WebConstants.ALL : domain
                        .getLobDesc());
                lineOfBusinessList.add(domain.getLobId());
                businessEntityList.add(domain.getBusinessEntityDesc().equals(
                        WebConstants.ALL) ? WebConstants.ALL : domain
                        .getBusinessEntityDesc());
                businessEntityList.add(domain.getBusinessEntityId());
                businessGroupList.add(domain.getBusinessGroupDesc().equals(
                        WebConstants.ALL) ? WebConstants.ALL : domain
                        .getBusinessGroupDesc());
                businessGroupList.add(domain.getBusinessGroupId());
            }
            WPDStringUtil.removeDuplicateWithOrder(lineOfBusinessList);
            WPDStringUtil.removeDuplicateWithOrder(businessEntityList);
            WPDStringUtil.removeDuplicateWithOrder(businessGroupList);
            this.businessEntity = WPDStringUtil
                    .getTildaStringFromList(lineOfBusinessList);
            this.lineOfBusiness = WPDStringUtil
                    .getTildaStringFromList(businessEntityList);
            this.businessGroup = WPDStringUtil
                    .getTildaStringFromList(businessGroupList);
        }
    }
    public String retrieveBenefitYearConflictMessage(){
        MigrationProductInfoRequest saveRequest = (MigrationProductInfoRequest) this.getServiceRequest(ServiceManager.MIGRATION_PRODUCT_INFO_REQUEST);
    	saveRequest.setAction(MigrationProductInfoRequest.RETRIEVE_BY_CY_CONFLICT_MESSAGE);
    	if(!StringUtil.isEmpty(this.productName)){
    		if(!this.productName.equals("~undefined")){
		    	int productSysId = Integer.parseInt(WPDStringUtil.getCodeFromTildaString(this.productName, 2, 1, 1));
		    	saveRequest.setEwpdProductSysId(productSysId);
		    	saveRequest.setMigrationContractSession(this.getMigrationContractSession());
		    	MigrationProductInfoResponse response = (MigrationProductInfoResponse)this.executeService(saveRequest);
		    	if(response.isBYCYConflict()) {
			    	String msgId = "migration.bnftYrIndConflict.product";
			        ResourceBundle myResources = ResourceBundle.getBundle("com.wellpoint.wpd.common.resources.UserMessages");
			        String returnMsg = myResources.getString(msgId);
			        this.benefitYrIndWarningMessage  = returnMsg;
		    	} else {
		    		this.benefitYrIndWarningMessage = "";
		    	}
    		}else {
	    		this.benefitYrIndWarningMessage = "";
	    		this.productName = "";
	    		this.productFamily = "";
	    	}
    	}else {
    		this.benefitYrIndWarningMessage = "";
    		this.productName = "";
    		this.productFamily = "";
    	}
    	return "";
    }

    /**
     * Function to retrieve Product information from the repository..
     * 
     * @param
     * @param
     * @param
     * @return a String productInformation
     * @throws
     */
    public String showProductInfo() {
        HttpSession session = getSession();
        Logger
                .logInfo("Entering the method for displaying Migration Product Information Tab");
        session.setAttribute(BusinessConstants.SESSION_MIGRATION_PERSIST_FLAG,
                WebConstants.FLAG_Y);
        //obtain request
        MigrationProductInfoRequest migrationProductInfoRequest = (MigrationProductInfoRequest) this
                .getServiceRequest(ServiceManager.MIGRATION_PRODUCT_INFO_REQUEST);
        migrationProductInfoRequest
                .setAction(BusinessConstants.MIGRATION_RETRIEVE_PRODUCT_INFO);
        migrationProductInfoRequest.setMigratedDateSegmentId(this
                .getMigrationContractSession().getDateSegmentId());
        this.getMigrationContractSession().getNavigationInfo()
                .setUpdateLastAccessedPageOnly(true);
        this.setToRequest(migrationProductInfoRequest);
        // obtain the response
        MigrationProductInfoResponse migrationProductInfoResponse = (MigrationProductInfoResponse) executeService(migrationProductInfoRequest);
        MigrationContract migrationContract = this
                .getMigrationContractSession().getMigrationContract();
        MigrationDateSegment migrationDateSegment;
        if (null != migrationProductInfoResponse
                && migrationProductInfoResponse.isSuccess()) {

            this.persistStatus = migrationProductInfoResponse.isPersistStatus();

            if (migrationProductInfoResponse.getMigrationDomainInfoList()
                    .size() > 0) {
                migrationDateSegment = migrationProductInfoResponse
                        .getMigDateSegment();
                this.setDomainValue(migrationProductInfoResponse
                        .getMigrationDomainInfoList());
                SimpleDateFormat dateFormat = new SimpleDateFormat(
                        WebConstants.DATE_FORMAT_STRING);
                this.effectiveDate = dateFormat.format(migrationDateSegment
                        .getEffectiveDate());
                this.expiryDate = dateFormat.format(migrationDateSegment
                        .getExpiryDate());
            }
            if (persistStatus) {
                this.setToSession(migrationProductInfoResponse);
                this.custom = true;
                this.legacyStructure = migrationProductInfoResponse
                        .getLegacyStructure();
                this.productName = migrationProductInfoResponse
                        .getEwpdProdSysId()
                        + "~" + migrationProductInfoResponse.getProductName();
                this.oldProductName = this.productName;
                this.productFamily = migrationProductInfoResponse
                        .getProductFamily();
                this.ewpdProductSysId = migrationProductInfoResponse
                        .getEwpdProdSysId();
                session.setAttribute(
                        BusinessConstants.SESSION_MIGRATION_PROD_SYS_ID,
                        (Object) String.valueOf(ewpdProductSysId));
            } else {
                session.setAttribute(
                        BusinessConstants.SESSION_MIGRATION_PERSIST_FLAG,
                        WebConstants.FLAG_Y);
                this.legacyStructure = migrationContract
                        .getStructreProductMappingId();
                this.productName = WebConstants.EMPTY_STRING;
                this.oldProductName = this.productName;
                this.productFamily = WebConstants.EMPTY_STRING;
            }
        }
        return WebConstants.MIG_CONTRACT_STEP5;
    }


    public String done() {
        validationMessages = new ArrayList();
       
        if (processSaveAction(BusinessConstants.MIGRATION_NAVIGATION_FLAG_FALSE)){ 
	        if (getMigrationContractSession().getNavigationInfo()
	                .getStepCompleted() >= BusinessConstants.STEP7) {
	            return goToNextPage(WebConstants.MIG_CONTRACT_STEP9);
	        } else {
	
	            this.validationMessages = validateStepNumber();
	            showProductInfo();
		        addAllMessagesToRequest(this.validationMessages);
		       // return WebConstants.MIG_CONTRACT_STEP5;
		        getRequest().setAttribute("RETAIN_Value", "");
	        }	         
        }
        return WebConstants.EMPTY_STRING; 
    }


    /**
     * Function to Save Product information from the repository..
     * 
     * @param
     * @param
     * @param
     * @return a String productInformation
     * @throws
     */
    public String saveProductInfo() {        
       
        if (processSaveAction(BusinessConstants.MIGRATION_NAVIGATION_FLAG_FALSE)){          
            
           getSession().setAttribute(
                    WebConstants.MESSAGE_LIST_STEP3,new ArrayList());
            return goToNextPage(WebConstants.MIG_CONTRACT_STEP6);
        }else{
            return WebConstants.EMPTY_STRING;
        }
    }


    public String back() {
//        if (processSaveAction(BusinessConstants.MIGRATION_NAVIGATION_FLAG_TRUE))
            return goToNextPage(WebConstants.MIG_CONTRACT_STEP4);
//        else
//            return WebConstants.EMPTY_STRING;
    }


    /**
     * @param navigationFlag
     * @return boolean
     */
    public boolean processSaveAction(boolean navigationFlag) {

        HttpSession session = getSession();
        
		//hide popup that used for to replace current product with latest product in second time migration. 
		//session.setAttribute("hideReplaceProductPopup", "true");

        boolean isSuccess = true;
        Logger
                .logInfo("Entering the method for persisting Migration Product Information");
        MigrationProductInfoRequest migrationProductInfoRequest = null;
        MigrationProductInfoResponse migrationProductInfoResponse = null;
        if (!validateRequiredFields()) {
            addAllMessagesToRequest(validationMessages);
            return isSuccess = false;
        }
        int ewpdProdSysCode = 0;
        boolean isProductChanged = false;
        String persistFlag = String
                .valueOf(session
                        .getAttribute(BusinessConstants.SESSION_MIGRATION_PERSIST_FLAG));
        MigrationContract migrationContract = this
                .getMigrationContractSession().getMigrationContract();
        List productCodeList = WPDStringUtil.getListFromTildaString(
                this.productName, 2, 1, 1);
        if (null != productCodeList && productCodeList.size() > 0
                && null != productCodeList.get(0))
            ewpdProdSysCode = ((Integer) (productCodeList.get(0))).intValue(); //value from frontend
        if (null != migrationContract.getEwpdProductSystemId()
                && !(migrationContract.getEwpdProductSystemId().equals(String
                        .valueOf(ewpdProdSysCode))))
            isProductChanged = true;
        migrationContract.setEwpdProductSystemId(String
                .valueOf(ewpdProdSysCode));
        if ("Y".equals(persistFlag) || isProductChanged) {
            migrationProductInfoRequest = (MigrationProductInfoRequest) this
                    .getServiceRequest(ServiceManager.MIGRATION_PRODUCT_INFO_REQUEST);
            migrationProductInfoRequest
                    .setAction(BusinessConstants.MIGRATION_PERSIST_PRODUCT_INFO);
            migrationProductInfoRequest.setEwpdProductSysId(ewpdProdSysCode);
            migrationProductInfoRequest.setMigratedDateSegmentId(this
                    .getMigrationContractSession().getDateSegmentId());

            this.getMigrationContractSession().getNavigationInfo()
                    .setNavigationFlag(navigationFlag);
            this.setToRequest(migrationProductInfoRequest);
            // obtain the response
            migrationProductInfoResponse = (MigrationProductInfoResponse) executeService(migrationProductInfoRequest);
            migrationProductInfoResponse
                    .setMigrationContractSession(getMigrationContractSession());
            if (null != migrationProductInfoResponse
                    && migrationProductInfoResponse.isSuccess()) {
                this.setToSession(migrationProductInfoResponse);
            }
        } else {
            String prodSysId = String
                    .valueOf(session
                            .getAttribute(BusinessConstants.SESSION_MIGRATION_PROD_SYS_ID));
            this.ewpdProductSysId = Integer.parseInt(prodSysId);
            migrationContract.setEwpdProductSystemId(String
                    .valueOf(this.ewpdProductSysId));
            
            this.getMigrationContractSession().setMigrationContract(
                    migrationContract);
            migrationProductInfoRequest = (MigrationProductInfoRequest) this
                    .getServiceRequest(ServiceManager.MIGRATION_PRODUCT_INFO_REQUEST);
            this.setToRequest(migrationProductInfoRequest);
            migrationProductInfoRequest.setEwpdProductSysId(Integer.parseInt(migrationContract.getEwpdProductSystemId()));
            migrationProductInfoRequest
                    .setAction(BusinessConstants.PRODUCT_UPDATE_STEP_COMPLETED);
            migrationProductInfoResponse = (MigrationProductInfoResponse) this
                    .executeService(migrationProductInfoRequest);

            if (null != migrationProductInfoResponse
                    && migrationProductInfoResponse.isSuccess()) {
                this.setToSession(migrationProductInfoResponse);
            }
        }
        return isSuccess;

    }


    /**
     * Validates the fields in the form.
     * 
     * @param
     * @param
     * @return void
     */
    private boolean validateRequiredFields() {
        validationMessages = new ArrayList();

        if (this.productName == null || "".equals(this.productName)) {
        	this.productInvalid =true;
            this.validationMessages.add(new ErrorMessage(
                    WebConstants.MANDATORY_FIELDS_MIGRATION_PRODUCT_REQUIRED));
            return false;
        }
        return true;
    }

    
    /**
     * @return disableField
     * 
     * Returns the disableField.
     */
    public boolean getDisableField() {
        this.disableField = getMigrationContractSession()
								.getMigrationContract()
								.getDoneFlag()
								.equals(MigrationContractUtil.DONE_FLAG_SECOND);  
        return this.disableField;
    }
    /**
     * @param disableField
     * 
     * Sets the disableField.
     */
    public void setDisableField(boolean disableField) {
        this.disableField = disableField;
    }
    /**
     * Returns the businessEntity
     * 
     * @return String businessEntity.
     */
    public String getBusinessEntity() {
        return businessEntity;
    }


    /**
     * Sets the businessEntity
     * 
     * @param businessEntity.
     */
    public void setBusinessEntity(String businessEntity) {
        this.businessEntity = businessEntity;
    }


    /**
     * Returns the businessGroup
     * 
     * @return String businessGroup.
     */
    public String getBusinessGroup() {
        return businessGroup;
    }


    /**
     * Sets the businessGroup
     * 
     * @param businessGroup.
     */
    public void setBusinessGroup(String businessGroup) {
        this.businessGroup = businessGroup;
    }


    /**
     * Returns the effectiveDate
     * 
     * @return String effectiveDate.
     */
    public String getEffectiveDate() {
        return effectiveDate;
    }


    /**
     * Sets the effectiveDate
     * 
     * @param effectiveDate.
     */
    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }


    /**
     * Returns the expiryDate
     * 
     * @return String expiryDate.
     */
    public String getExpiryDate() {
        return expiryDate;
    }


    /**
     * Sets the expiryDate
     * 
     * @param expiryDate.
     */
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }


    /**
     * Returns the legacyStructure
     * 
     * @return String legacyStructure.
     */
    public String getLegacyStructure() {
        return legacyStructure;
    }


    /**
     * Sets the legacyStructure
     * 
     * @param legacyStructure.
     */
    public void setLegacyStructure(String legacyStructure) {
        this.legacyStructure = legacyStructure;
    }


    /**
     * Returns the lineOfBusiness
     * 
     * @return String lineOfBusiness.
     */
    public String getLineOfBusiness() {
        return lineOfBusiness;
    }


    /**
     * Sets the lineOfBusiness
     * 
     * @param lineOfBusiness.
     */
    public void setLineOfBusiness(String lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
    }


    /**
     * Returns the productFamily
     * 
     * @return String productFamily.
     */
    public String getProductFamily() {
        return productFamily;
    }


    /**
     * Sets the productFamily
     * 
     * @param productFamily.
     */
    public void setProductFamily(String productFamily) {
        this.productFamily = productFamily;
    }


    /**
     * Returns the productInformationList
     * 
     * @return List productInformationList.
     */
    public List getProductInformationList() {
        return productInformationList;
    }


    /**
     * Sets the productInformationList
     * 
     * @param productInformationList.
     */
    public void setProductInformationList(List productInformationList) {
        this.productInformationList = productInformationList;
    }


    /**
     * Returns the validationMessages
     * 
     * @return List validationMessages.
     */
    public List getValidationMessages() {
        return validationMessages;
    }


    /**
     * Sets the validationMessages
     * 
     * @param validationMessages.
     */
    public void setValidationMessages(List validationMessages) {
        this.validationMessages = validationMessages;
    }


    /**
     * Returns the productInvalid
     * 
     * @return boolean productInvalid.
     */
    public boolean isProductInvalid() {
        return productInvalid;
    }


    /**
     * Sets the productInvalid
     * 
     * @param productInvalid.
     */
    public void setProductInvalid(boolean productInvalid) {
        this.productInvalid = productInvalid;
    }


    /**
     * Returns the productName
     * 
     * @return String productName.
     */
    public String getProductName() {
        return productName;
    }


    /**
     * Sets the productName
     * 
     * @param productName.
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }


    /**
     * Returns the migratedContractSysID
     * 
     * @return String migratedContractSysID.
     */
    public int getMigratedContractSysID() {
        return migratedContractSysID;
    }


    /**
     * Sets the migratedContractSysID
     * 
     * @param migratedContractSysID.
     */
    public void setMigratedContractSysID(int migratedContractSysID) {
        this.migratedContractSysID = migratedContractSysID;
    }


    /**
     * Returns the migratedProdStructureMappingSysID
     * 
     * @return int migratedProdStructureMappingSysID.
     */
    public int getMigratedProdStructureMappingSysID() {
        return migratedProdStructureMappingSysID;
    }


    /**
     * Sets the migratedProdStructureMappingSysID
     * 
     * @param migratedProdStructureMappingSysID.
     */
    public void setMigratedProdStructureMappingSysID(
            int migratedProdStructureMappingSysID) {
        this.migratedProdStructureMappingSysID = migratedProdStructureMappingSysID;
    }


    /**
     * Returns the custom
     * 
     * @return boolean custom.
     */
    public boolean isCustom() {
        return custom;
    }


    /**
     * Sets the custom
     * 
     * @param custom.
     */
    public void setCustom(boolean custom) {
        this.custom = custom;
    }


    /**
     * Returns the legacySourceSystem
     * 
     * @return String legacySourceSystem.
     */
    public String getLegacySourceSystem() {
        return legacySourceSystem;
    }


    /**
     * Sets the legacySourceSystem
     * 
     * @param legacySourceSystem.
     */
    public void setLegacySourceSystem(String legacySourceSystem) {
        this.legacySourceSystem = legacySourceSystem;
    }


    /**
     * Returns the lockStatusFlag
     * 
     * @return String lockStatusFlag.
     */
    public String getLockStatusFlag() {
        return lockStatusFlag;
    }


    /**
     * Sets the lockStatusFlag
     * 
     * @param lockStatusFlag.
     */
    public void setLockStatusFlag(String lockStatusFlag) {
        this.lockStatusFlag = lockStatusFlag;
    }


    /**
     * Returns the persistStatus
     * 
     * @return boolean persistStatus.
     */
    public boolean isPersistStatus() {
        return persistStatus;
    }


    /**
     * Sets the persistStatus
     * 
     * @param persistStatus.
     */
    public void setPersistStatus(boolean persistStatus) {
        this.persistStatus = persistStatus;
    }


    /**
     * Sets the ewpdProductSysId
     * 
     * @param ewpdProductSysId.
     */
    public void setEwpdProductSysId(int ewpdProductSysId) {
        this.ewpdProductSysId = ewpdProductSysId;
    }


    /**
     * Returns the ewpdProductSysId
     * 
     * @return int ewpdProductSysId.
     */
    public int getEwpdProductSysId() {
        return ewpdProductSysId;
    }


    /**
     * Returns the product
     * 
     * @return String product.
     */
    public String getProduct() {
        return product;
    }


    /**
     * Sets the product
     * 
     * @param product.
     */
    public void setProduct(String product) {
        this.product = product;
    }


    /**
     * @return Returns the contractType.
     */
    public String getContractType() {
        contractType = this.getMigrationContractSession()
                .getMigrationContract().getContractType();
        return contractType;
    }


    /**
     * @param contractType
     *            The contractType to set.
     */
    public void setContractType(String contractType) {
        this.contractType = contractType;
    }


    public String getOldProductName() {
        return oldProductName;
    }


    public void setOldProductName(String oldProductName) {
        this.oldProductName = oldProductName;
    }


    /**
     * Returns the option
     * 
     * @return String option.
     */
    public String getOption() {
        return super.getMigrationContractSession().getMigrationContract()
                .getOption();
    }


    /**
     * Sets the option
     * 
     * @param option.
     */
    public void setOption(String option) {
        this.option = option;
    }


    /**
     * Returns the contractID
     * 
     * @return String contractID.
     */
    public String getContractID() {
        return super.getMigrationContractSession().getMigrationContract()
                .getContractId();
    }


    /**
     * Sets the contractID
     * 
     * @param contractID.
     */
    public void setContractID(String contractID) {
        this.contractID = contractID;
    }


    /**
     * Returns the system
     * 
     * @return String system.
     */
    public String getSystem() {
        return super.getMigrationContractSession().getMigrationContract()
                .getSystem();
    }


    /**
     * Sets the system
     * 
     * @param system.
     */
    public void setSystem(String system) {
        this.system = system;
    }


	/**
	 * Returns the ewpdContractSysID
	 * @return int ewpdContractSysID.
	 */
	public int getEwpdContractSysID() {
		if(this.disableField){ //true only in second time migration
			RetrieveReservedContractRequest retrieveReservedContractRequest;
			retrieveReservedContractRequest = (RetrieveReservedContractRequest) this
				.getServiceRequest(ServiceManager.RETRIEVE__RESERVED_CONTRACT);
			retrieveReservedContractRequest.setAction(RetrieveReservedContractRequest.RETRIEVE_CONTRACT_SYS_ID);
			retrieveReservedContractRequest.setContractId(super.getMigrationContractSession()
																.getMigrationContract()
																.getContractId());
			RetrieveReservedContractResponse searchResponse = (RetrieveReservedContractResponse) executeService(
			 		retrieveReservedContractRequest);		       
		        if(null != searchResponse &&  searchResponse.isSuccess()){
		        	this.ewpdContractSysID = searchResponse.getContractSysId();			    	
		        }			
		}
		return ewpdContractSysID;
	}
	/**
	 * Sets the ewpdContractSysID
	 * @param ewpdContractSysID.
	 */
	public void setEwpdContractSysID(int ewpdContractSysID) {
		this.ewpdContractSysID = ewpdContractSysID;
	}
    /**
     * @return list
     */
    public List getValidProducts() {
        if (!this.productsRetrievedForPopup) {
            MigrationProductInfoRequest migrationProductInfoRequest = null;
            MigrationProductInfoResponse migrationProductInfoResponse = null;
            migrationProductInfoRequest = (MigrationProductInfoRequest) this
                    .getServiceRequest(ServiceManager.MIGRATION_PRODUCT_INFO_REQUEST);
            migrationProductInfoRequest
                    .setAction(MigrationProductInfoRequest.PRODUCT_POPUP);
            migrationProductInfoRequest.setProductParentSysId(this.getProductParentSysId());
            this.setToRequest(migrationProductInfoRequest);
            migrationProductInfoResponse = (MigrationProductInfoResponse) executeService(migrationProductInfoRequest);
            this.validProducts = migrationProductInfoResponse
                    .getValidProductList();
            this.productsRetrievedForPopup = true;
        }
        return validProducts;
    }


    /**
     * @param validProducts
     */
    public void setValidProducts(List validProducts) {
        this.validProducts = validProducts;
    }
    /**
     * @return hideProductPopUp
     * 
     * Returns the hideProductPopUp.
     */
    public boolean getHideProductPopUp() {
        
//         return getMigrationContractSession()
//				.getMigrationContract().getMappedProductFlag() ;
    	String baseDS = getMigrationContractSession()
						.getMigrationContract()
						.getBaseDateSegmentId();
    	boolean temp = false;
    	//checking whether a contract has a base date segment id
    	//for custom contract it is manditory for standard contract it might or might not manditory  
    	if((null != baseDS) && (!baseDS.equals(WebConstants.EMPTY_STRING)) && (!baseDS.equals("0"))){
    		temp = true;
    	}
    	/*if(null != contractType){	    	
	    		temp = (getMigrationContractSession()
								.getMigrationContract()
								.getContractType()
								.equals("CUSTOM") 
										&& baseDS != null 
										&& !baseDS.equals(WebConstants.EMPTY_STRING)
										&& !baseDS.equals("0"));
	    	
    	}*/
    	return temp;
    }
    
    /**
     * @param hideProductPopUp
     * 
     * Sets the hideProductPopUp.
     */
    public void setHideProductPopUp(boolean hideProductPopUp) {
        this.hideProductPopUp = hideProductPopUp;
    }
    
    
	/**
	 * @return Returns the hideReplaceProductPopup.
	 */
	public boolean isHideReplaceProductPopup() {
		String hide = (String)super.getSession().getAttribute("hideReplaceProductPopup");
		if(null !=hide && !hide.equals(WebConstants.EMPTY_STRING) && hide.equals("true")){
			this.hideReplaceProductPopup = true;//Boolean.getBoolean(hide);			
		}else{
			this.hideReplaceProductPopup = false;
		}
		return hideReplaceProductPopup;
	}
	/**
	 * @param hideReplaceProductPopup The hideReplaceProductPopup to set.
	 */
	public void setHideReplaceProductPopup(boolean hideReplaceProductPopup) {
		this.hideReplaceProductPopup = hideReplaceProductPopup;
	}
	/**
	 * @return Returns the benefitYrIndWarningMessage.
	 */
	public String getBenefitYrIndWarningMessage() {
		return benefitYrIndWarningMessage;
	}
	/**
	 * @param benefitYrIndWarningMessage The benefitYrIndWarningMessage to set.
	 */
	public void setBenefitYrIndWarningMessage(String benefitYrIndWarningMessage) {
		this.benefitYrIndWarningMessage = benefitYrIndWarningMessage;
	}
	/**
	 * @return Returns the productParentSysId.
	 */
	public int getProductParentSysId() {

		migratedContractSysID = Integer.parseInt(getMigrationContractSession().getMigrationContract().getMigrationSystemId());
    	if(0 != migratedContractSysID){
		SaveMigGeneralInfoRequest saveRequest = (SaveMigGeneralInfoRequest)this.getServiceRequest(ServiceManager.SAVE_MIG_GENINFO_REQUEST);
		saveRequest.setContractSysId(this.migratedContractSysID);
		saveRequest.setAction(SaveMigGeneralInfoRequest.RETRIEVE_PRODUCT_PARENT_SYS_ID);
		SaveMigGeneralInfoResponse saveResponse = (SaveMigGeneralInfoResponse)executeService(saveRequest);
		if(null != saveResponse){
			if (0 != saveResponse.getMigDateSegment().getProductParentSysId()) {
					return saveResponse.getMigDateSegment().getProductParentSysId();
				}
       		}		
    	}
		return 0;
	}
	/**
	 * @param productParentSysId The productParentSysId to set.
	 */
	public void setProductParentSysId(int productParentSysId) {
		this.productParentSysId = productParentSysId;
	}
}