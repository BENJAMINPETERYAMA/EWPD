/*
 * MigrationGeneralInfoBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.migration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.model.SelectItem;

import com.wellpoint.wpd.business.migration.util.MigrationContractUtil;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.refdata.domain.ReferenceData;
import com.wellpoint.wpd.common.framework.util.StringUtil;
import com.wellpoint.wpd.common.migration.bo.MigrationContract;
import com.wellpoint.wpd.common.migration.bo.MigrationDateSegment;
import com.wellpoint.wpd.common.migration.bo.MigrationDomainInfo;
import com.wellpoint.wpd.common.migration.bo.MigrationProduct;
import com.wellpoint.wpd.common.migration.request.CancelMigrationRequest;
import com.wellpoint.wpd.common.migration.request.MigrationProductInfoRequest;
import com.wellpoint.wpd.common.migration.request.RetrieveMigGeneralInfoRequest;
import com.wellpoint.wpd.common.migration.request.SaveMigGeneralInfoRequest;
import com.wellpoint.wpd.common.migration.response.MigrationProductInfoResponse;
import com.wellpoint.wpd.common.migration.response.RetrieveMigGeneralInfoResponse;
import com.wellpoint.wpd.common.migration.response.SaveMigGeneralInfoResponse;
import com.wellpoint.wpd.common.refdata.request.RefDataRequest;
import com.wellpoint.wpd.common.refdata.response.RefDataResponse;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class MigrationGeneralInfoBackingBean extends MigrationBaseBackingBean{

    private List validationMessages = new ArrayList();
    
	private List headQuartersStateList;
	
    private String  lineOfBusinessDiv;
    
    private String businessEntityDiv;
    
    private String businessGroupDiv;
    /*START CARS*/
	private String marketBusinessUnit;
    /*END CARS*/
    private String baseContractIdDiv;
    
    private String baseContractIdStandardDiv;
    
    private String baseContractDtDiv;
    
    private String baseContractDtStandardDiv;
    
    private String contractId; 
    
    private String startDate;
    
    private String legacyStartDate;
    
    private String endDate;
    
    private String contractType;
    
    private String groupSizeDiv;
    
    private String groupSize;
    
    private String headQuartersState;
    
	private String productCode;
	
	private String standardPlanCode;
	
	private String benefitPlan;
	
	private String groupName;
	
	private String corporatePlanCode;
	
	private String fundingArrangement;
	
	private String brandName;
	
	    
    private boolean lobInvalid = false;
    
    private boolean entityInvalid = false;
    
    private boolean groupInvalid = false;
    
    private boolean baseContractIdInvalid = false;
    
    private boolean baseContractDtInvalid = false;
    
    private boolean baseContractDtForStandardInvalid = false;
    
    private boolean contractTypeInvalid  = false;
    
    private boolean startDateInvalid = false;
    
    private boolean endDateInvalid = false;
    
    private boolean groupSizeInvalid = false;
    
    private boolean validation = false;
    
    boolean requiredProductCode = false;
    
    boolean requiredBenefitPlan = false;
    
    boolean requiredCorporateCode = false;
    
    boolean requiredHeadQuarters = false;
    
    boolean mandate=true;
    
    private int migDateSegmentSysId;
    
    private int migContractSysId;
    
    private String doneFlag;
    
    private List contractTypeList;

    private int action = 0;
    private String selectBaseContractFlag;
    
    private String dateSegment;
    
    private boolean disableField = true;
    private String baseContractId ;
    private boolean confirmProductFlag = false;
    private boolean saveProductFlag = false;
	private boolean salesMarketDateInvalid;
	private boolean contractTermDateInvalid;

    private int ewpdProductSysId ;
    private String  cobIndicator;
    private String  medIndicator;
    private String  itsIndicator;
    
	private String regulatoryAgency;
	private String complianceStatus;
	private String prodProjNameCode;
	private String contractTermDate;
	private String multiplanIndicator = "N";
	private String performanceGuarantee = "N";
	private String salesMarketDate;
   
    private static String BREAD_CRUMB_TEXT_STEP3 = "Administration >> Contract Migration Wizard >> General Information (Step 3)";
    private static String BREAD_CRUMB_TEXT_STEP2 = "Administration >> Contract Migration Wizard >> Date Segment (Step 2)";
    
    private String benefitYrIndWarningMessage;
    
    private String modelContractURL;
    
    private int  productParentSysId;
    
    private boolean autoPopulate = false;
   
    
	/**
	 * @return Returns the prodParSysId.
	 */
	public int getProductParentSysId() {
		return productParentSysId;
	}
	/**
	 * @param prodParSysId The prodParSysId to set.
	 */
	public void setProductParentSysId(int  productParentSysId) {
		this. productParentSysId =  productParentSysId;
	}
	/**
	 * @returns String.
	 * This method is used to get Product Parent System Id ,which is used for 
	 * getting base contracts.
	 */
	public String getModelContractURL() {
		migContractSysId = Integer.parseInt(getMigrationContractSession().getMigrationContract().getMigrationSystemId());
    	if(0 != migContractSysId){
		SaveMigGeneralInfoRequest saveRequest = (SaveMigGeneralInfoRequest)this.getServiceRequest(ServiceManager.SAVE_MIG_GENINFO_REQUEST);
		saveRequest.setContractSysId(this.migContractSysId);
		saveRequest.setAction(SaveMigGeneralInfoRequest.RETRIEVE_PRODUCT_PARENT_SYS_ID);
		SaveMigGeneralInfoResponse saveResponse = (SaveMigGeneralInfoResponse)executeService(saveRequest);		
		if(null != saveResponse){
			if (0 != saveResponse.getMigDateSegment().getProductParentSysId()) {
				this.setProductParentSysId(saveResponse.getMigDateSegment().getProductParentSysId());
				}
       		}		
    	}
    	addAllMessagesToRequest(this.validationMessages);
    	return "";
	}
	/**
	 * @param modelContractURL The modelContractURL to set.
	 */
	public void setModelContractURL(String modelContractURL) {
		this.modelContractURL = modelContractURL;
	}
    /**
     * cONSTRUCTOR
     */
    public MigrationGeneralInfoBackingBean(){
        super();
        this.setBreadCrumbTextLeft(BREAD_CRUMB_TEXT_STEP3);
        contractId = getMigrationContractSession().getMigrationContract().getContractId();
        String startDate = WPDStringUtil.convertDateToString(this.getMigrationContractSession().getStartDateEwpd());
        String endDate = WPDStringUtil.convertDateToString(this.getMigrationContractSession().getEndDate());
        this.setBreadCrumbTextRight(WebConstants.BREAD_CRUMB_CONTRACT+contractId+" ("+startDate+" - "+endDate+")");
         doneFlag = getMigrationContractSession().getMigrationContract().getDoneFlag();
        migDateSegmentSysId = Integer.parseInt(this.getMigrationContractSession().getDateSegmentId());
        migContractSysId = Integer.parseInt(getMigrationContractSession().getMigrationContract().getMigrationSystemId());
        if((getMigrationContractSession().getMigrationContract()).getDoneFlag().equals("S")){
        	setDisableField(false);
        }
        else if((getMigrationContractSession().getMigrationContract()).getDoneFlag().equals("Y")){
            setDisableField(true);
        }
        else if((getMigrationContractSession().getMigrationContract()).getDoneFlag().equals("N")){
            setDisableField(true);
        }
        this.getMigrationContractSession().getMigrationContract().setMappedProductFlag(false);
    }
    
  
    /**
     * @return String
     */
    public String back(){
        if (isRequiredFieldsValid()){
           SaveMigGeneralInfoRequest saveRequest = (SaveMigGeneralInfoRequest)this.getServiceRequest(ServiceManager.SAVE_MIG_GENINFO_REQUEST);
            setBackingBeanValuesToRequest(saveRequest);
			SaveMigGeneralInfoResponse saveResponse = (SaveMigGeneralInfoResponse)executeService(saveRequest);
 			
			if(null != saveResponse && saveResponse.isSuccess()){
				this.setToSession(saveResponse);
				MigrationContract migrationContract = super.getMigrationContractSession().getMigrationContract();
				if(null !=migrationContract){
					if(migrationContract.getOption().equals("1")){
						 this.setBreadCrumbTextLeft(BREAD_CRUMB_TEXT_STEP2);
						return WebConstants.MIG_CONTRACT_STEP_ALL_DS;
					}else{
						return this.goToNextPage(WebConstants.MIG_CONTRACT_STEP1);
					}
				}
			}
        }
        else{
            addAllMessagesToRequest(this.validationMessages);
        }
        return WebConstants.EMPTY_STRING;
    }

    public String validationCheck(){
	    if(isRequiredFieldsValid()){
	    	validation = true;
	    }
	    else {
	    	 addAllMessagesToRequest(this.validationMessages);
	    }
	    return WebConstants.EMPTY_STRING;   
    }    
    /**
     * @return String
     */
    public String saveMigGeneralInfo(){
        if (isRequiredFieldsValid()){
           SaveMigGeneralInfoRequest saveRequest = (SaveMigGeneralInfoRequest)this.getServiceRequest(ServiceManager.SAVE_MIG_GENINFO_REQUEST);
           setBackingBeanValuesToRequest(saveRequest);
          // saveRequest.setAction(this.getAction());
           saveRequest.setAction(SaveMigGeneralInfoRequest.SAVE_MIG_GENERAL_INFO);
           
		   SaveMigGeneralInfoResponse saveResponse = (SaveMigGeneralInfoResponse)executeService(saveRequest);
		   
           if(null != saveResponse && saveResponse.isSuccess()){
           		validationMessages = saveResponse.getMessages();
           		//addAllMessagesToRequest(this.validationMessages);
           		if(null != validationMessages && (validationMessages.size() > 0)){
           			getSession().setAttribute("VALIDATION_MESSAGE", saveResponse.getMessages());
           		}
           		this.setToSession(saveResponse);
           		return goToNextPage(WebConstants.MIG_CONTRACT_STEP4);
           }					
        }
        else {
        	 addAllMessagesToRequest(this.validationMessages);
        }
        return WebConstants.EMPTY_STRING;
    }
    
    
    public String getProductSysId(){        
        String baseContractId = this.getBaseContractId();        
        if(null!=baseContractId){            
            mapDefaultProducttoLegacyStructure(baseContractId);        
        }
        return "";
    }
    
    public String saveProductInfo(){
	    if (isRequiredFieldsValid()) {
			int baseContrProductSysID = this.getMigrationContractSession()
					.getMigrationContract().getBaseContrProductSysID();
			MigrationProductInfoRequest migrationProductInfoRequest = (MigrationProductInfoRequest) this
					.getServiceRequest(ServiceManager.MIGRATION_PRODUCT_INFO_REQUEST);
			migrationProductInfoRequest
					.setAction(BusinessConstants.MIGRATION_PERSIST_PRODUCT_INFO);
			MigrationContract migrationContract = this
					.getMigrationContractSession().getMigrationContract();
			this.getMigrationContractSession().setMigrationContract(
					migrationContract);
			migrationContract.setEwpdProductSystemId(String
					.valueOf(baseContrProductSysID));
			migrationContract.setMgrtdDatesegmentId(Integer.parseInt(this.getMigrationContractSession().getDateSegmentId()));
			migrationProductInfoRequest.setMigrationContractSession(this
					.getMigrationContractSession());
			migrationProductInfoRequest
					.setEwpdProductSysId(baseContrProductSysID);
	        migrationProductInfoRequest.setMigratedDateSegmentId(this
	                .getMigrationContractSession().getDateSegmentId());

			this.setToRequest(migrationProductInfoRequest);
			SaveMigGeneralInfoRequest saveRequest = (SaveMigGeneralInfoRequest) this
					.getServiceRequest(ServiceManager.SAVE_MIG_GENINFO_REQUEST);
			saveRequest.setBaseContractProductSysId(baseContrProductSysID);
			setBackingBeanValuesToRequest(saveRequest);
			// saveRequest.setAction(this.getAction());
			saveRequest
					.setAction(SaveMigGeneralInfoRequest.SAVE_MIG_GENERAL_INFO);

			SaveMigGeneralInfoResponse saveResponse = (SaveMigGeneralInfoResponse) executeService(saveRequest);

			if (null != saveResponse && saveResponse.isSuccess()) {
				this.setToSession(saveResponse);
				MigrationProductInfoResponse migrationProductInfoResponse = (MigrationProductInfoResponse) executeService(migrationProductInfoRequest);
				if (migrationProductInfoResponse.isSuccess()) {
					this.getMigrationContractSession().getMigrationContract()
							.setMappedProductFlag(true);
				}

				return goToNextPage(WebConstants.MIG_CONTRACT_STEP4);
			}
		}
         else {
         	 addAllMessagesToRequest(this.validationMessages);
         }        
//        saveMigGeneralInfo();
        
        // obtain the response
        
        
//        return saveMigGeneralInfo();
        return WebConstants.EMPTY_STRING;
    
    }
    public String retrieveBenefitYearConflictMessageForStandard(){
    	SaveMigGeneralInfoRequest saveRequest = (SaveMigGeneralInfoRequest)this.getServiceRequest(ServiceManager.SAVE_MIG_GENINFO_REQUEST);
    	saveRequest.setAction(SaveMigGeneralInfoRequest.RETRIEVE_BY_CY_CONFLICT_MESSAGE);
    	saveRequest.setBaseDateSegmentSysId(Integer.parseInt(WPDStringUtil.getCodeFromTildaString(this.baseContractDtStandardDiv,2,2,2) ));
    	saveRequest.setMigrationContractSession(this.getMigrationContractSession());
    	SaveMigGeneralInfoResponse response = (SaveMigGeneralInfoResponse)this.executeService(saveRequest);
    	if(response.isBYCYConflict()) {
	    	String msgId = "migration.bnftYrIndConflict.model";
	        ResourceBundle myResources = ResourceBundle.getBundle("com.wellpoint.wpd.common.resources.UserMessages");
	        String returnMsg = myResources.getString(msgId);
	        this.benefitYrIndWarningMessage  = returnMsg;
    	} else {
    		this.benefitYrIndWarningMessage = "";
    	}
    	return "";
    }
    
    public String retrieveBenefitYearConflictMessage(){
    	SaveMigGeneralInfoRequest saveRequest = (SaveMigGeneralInfoRequest)this.getServiceRequest(ServiceManager.SAVE_MIG_GENINFO_REQUEST);
    	saveRequest.setAction(SaveMigGeneralInfoRequest.RETRIEVE_BY_CY_CONFLICT_MESSAGE);
    	saveRequest.setBaseDateSegmentSysId(Integer.parseInt(WPDStringUtil.getCodeFromTildaString(this.baseContractDtDiv,2,2,2) ));
    	saveRequest.setMigrationContractSession(this.getMigrationContractSession());
    	SaveMigGeneralInfoResponse response = (SaveMigGeneralInfoResponse)this.executeService(saveRequest);
    	if(response.isBYCYConflict()) {
	    	String msgId = "migration.bnftYrIndConflict.model";
	        ResourceBundle myResources = ResourceBundle.getBundle("com.wellpoint.wpd.common.resources.UserMessages");
	        String returnMsg = myResources.getString(msgId);
	        this.benefitYrIndWarningMessage  = returnMsg;
    	} else {
    		this.benefitYrIndWarningMessage = "";
    	}
    	return "";
    }
    /**
     * @param saveRequest
     */
    private void setBackingBeanValuesToRequest(SaveMigGeneralInfoRequest saveRequest){
        List lobCodeList;
		List businessEntityCodeList;
		List businessGroupCodeList;
        /*START CARS*/
		List marketBusinessUnitCodeList;
        /*END CARS*/
        lobCodeList = WPDStringUtil.getListFromTildaString(this.getLineOfBusinessDiv(), 2, 2, 2);
        businessEntityCodeList = WPDStringUtil.getListFromTildaString(this.getBusinessEntityDiv(), 2, 2, 2);
        businessGroupCodeList = WPDStringUtil.getListFromTildaString(this.getBusinessGroupDiv(), 2, 2, 2);
        /*START CARS*/
        marketBusinessUnitCodeList = WPDStringUtil.getListFromTildaString(this.getMarketBusinessUnit(), 2, 2, 2);
        List domainList = MigrationContractUtil.convertToMigDomains(lobCodeList,businessEntityCodeList,businessGroupCodeList,marketBusinessUnitCodeList);
        /*END CARS*/
        saveRequest.setLineOfBusinessList(lobCodeList);
        saveRequest.setBusinessEntityList(businessEntityCodeList);
        saveRequest.setBusinessGroupList(businessGroupCodeList);
        /*START CARS*/
        saveRequest.setBusinessGroupList(marketBusinessUnitCodeList);
        /*END CARS*/
        saveRequest.setDomainList(domainList);
        saveRequest.setContractId(this.contractId);
        saveRequest.setContractSysId(this.migContractSysId);
        saveRequest.setDateSegmentSysId(this.migDateSegmentSysId);
        saveRequest.setContractType(this.contractType);
        //; added for step number complete update
        this.setToRequest(saveRequest);
    	saveRequest.setGroupSize(WPDStringUtil.getCodeFromTildaString(this.groupSize,2,1,2));
    	saveRequest.setGroupSizeDesc(WPDStringUtil.getCodeFromTildaString(this.groupSize,2,2,2));
    	saveRequest.setProductCode(WPDStringUtil.getCodeFromTildaString(this.productCode,2,1,2));
	    saveRequest.setStandardPlanCode(WPDStringUtil.getCodeFromTildaString(this.standardPlanCode,2,1,2));
	    if((null!=this.benefitPlan) && !("".equalsIgnoreCase(this.benefitPlan))){
	    	saveRequest.setBenefitPlan(this.benefitPlan.trim().toUpperCase());
	    }
        saveRequest.setCorporatePlanCode(WPDStringUtil.getCodeFromTildaString(this.corporatePlanCode,2,1,2));
	    saveRequest.setFundingArrangement(WPDStringUtil.getCodeFromTildaString(this.fundingArrangement,2,1,2));
	    saveRequest.setHeadQuartersState(WPDStringUtil.getCodeFromTildaString(this.headQuartersState,2,1,2));
	    if((null!=this.getBrandName()) && !("".equalsIgnoreCase(this.getBrandName()))){
	    	saveRequest.setBrandName(this.getBrandName().trim().toUpperCase());
	    }
		saveRequest.setStartDate(WPDStringUtil.getDateFromString(this.startDate));
		saveRequest.setLegacyStartDate(WPDStringUtil.getDateFromString(this.legacyStartDate));
		saveRequest.setEndDate(WPDStringUtil.getDateFromString(this.endDate));
		saveRequest.setCobAdjudInd(this.cobIndicator);
		saveRequest.setItsHomeAdjInd(this.itsIndicator);
		saveRequest.setMedicareAdjudInd(this.medIndicator);

		if("".equals(this.regulatoryAgency) || "~".equals(this.regulatoryAgency) || this.regulatoryAgency==null){
			saveRequest.setRegulatoryAgency(WebConstants.EMPTY_STRING);
		}else{
			List regulatoryAgencyList = WPDStringUtil.getListFromTildaString(this.regulatoryAgency,2,1,2);
			saveRequest.setRegulatoryAgency((String)regulatoryAgencyList.get(0));		    
		}

		saveRequest.setComplianceStatus(this.complianceStatus);
		
		if("".equals(this.prodProjNameCode) || "~".equals(this.prodProjNameCode) ||this.prodProjNameCode==null){
			saveRequest.setProdProjNameCode(WebConstants.EMPTY_STRING);
		}else{
			List nameCodeList = WPDStringUtil.getListFromTildaString(this.prodProjNameCode,2,1,2);
			saveRequest.setProdProjNameCode((String)nameCodeList.get(0));		    
		}
		saveRequest.setContractTermDate(this.contractTermDate);
		saveRequest.setMultiplanIndicator(this.multiplanIndicator);
		saveRequest.setPerformanceGuarantee(this.performanceGuarantee);
		saveRequest.setSalesMarketDate(this.salesMarketDate);
		
		saveRequest.setSystem(getMigrationContractSession().getMigrationContract().getSystem());
		saveRequest.setDoneFlag(this.doneFlag);
		saveRequest.setOption(super.getMigrationContractSession()
        							.getMigrationContract()
									.getOption());
		
        if(this.contractType.equals(WebConstants.CUSTOM) && !isFieldEmpty(this.baseContractDtDiv)){
		  	saveRequest.setBaseContractId(WPDStringUtil.getCodeFromTildaString(this.baseContractIdDiv,2,1,2));
		  	saveRequest.setBaseDateSegmentSysId(Integer.parseInt(WPDStringUtil.getCodeFromTildaString(this.baseContractDtDiv,2,2,2) ));
		  	
		  	//Save the product attached to the base contract  as the product mapped to the structure of migrated contract.
		  	//Enhancement 39382
		  	//Get the base contract id & base contract effective datevalues from page if the contract type is STANDARD 
		}else if(this.contractType.equals(WebConstants.STANDAR) && !isFieldEmpty(this.baseContractDtStandardDiv)){
		  	saveRequest.setBaseContractId(WPDStringUtil.getCodeFromTildaString(this.baseContractIdStandardDiv,2,1,2));
		  	saveRequest.setBaseDateSegmentSysId(Integer.parseInt(WPDStringUtil.getCodeFromTildaString(this.baseContractDtStandardDiv,2,2,2) ));
		} else {
        	saveRequest.setBaseContractId(WebConstants.EMPTY_STRING);
        	saveRequest.setBaseDateSegmentSysId(0);
        }
        	
    }   
   
    /**
     * @param baseContractId
     */
    private void mapDefaultProducttoLegacyStructure(String baseContractId) {       
        SaveMigGeneralInfoRequest saveRequest = (SaveMigGeneralInfoRequest)this.getServiceRequest(ServiceManager.SAVE_MIG_GENINFO_REQUEST);
        saveRequest.setBaseContractId(baseContractId);
        saveRequest.setStartDate(WPDStringUtil.getDateFromString(this.startDate));
		saveRequest.setEndDate(WPDStringUtil.getDateFromString(this.endDate));
        saveRequest.setAction(SaveMigGeneralInfoRequest.RETRIEVE_PRODUCT_ATTACHED);
		SaveMigGeneralInfoResponse saveResponse = (SaveMigGeneralInfoResponse)executeService(saveRequest);
		List productIdList = saveResponse.getProductIdList();
		int baseContrProductSysID =0;
		int mappedProductSysID =0;
		if(null!=productIdList){
		   if(productIdList.size()>0){		   
		       MigrationProduct productBO = (MigrationProduct)productIdList.get(0); 
		       baseContrProductSysID = productBO.getBaseContrProductSysID();
		   }		
		}
		
		// Get the mapped product Id ;		
		String mappingSysId = this.getMigrationContractSession().getMigrationContract().getMigratedProdStructureMappingSysID();		
		if(!StringUtil.isEmpty(mappingSysId)){
		    saveRequest.setMigrationContractSession(this.getMigrationContractSession());
		    saveRequest.setAction(SaveMigGeneralInfoRequest.RETRIEVE_MAPPED_PRODUCT_ID)  ; 
		    SaveMigGeneralInfoResponse newProductResponse = (SaveMigGeneralInfoResponse)executeService(saveRequest);
		    List mappedproductIdList = newProductResponse.getProductIdList();
			if(null!=mappedproductIdList){
			   if(mappedproductIdList.size()>0){		   
			       MigrationProduct productBO = (MigrationProduct)mappedproductIdList.get(0);   
			       mappedProductSysID = productBO.getBaseContrProductSysID();
			   }		
			}
		}
		 setToRequest(saveRequest);
		if(baseContrProductSysID == 0){
		    this.setConfirmProductFlag(false);
		    this.setSaveProductFlag(false);
		
		}else if(mappedProductSysID!=0){
		    if(baseContrProductSysID!=mappedProductSysID){
			    this.setConfirmProductFlag(true);		    
			}
		}else if(mappedProductSysID==0){
		        this.setSaveProductFlag(true);	    
		    
		}
		this.getMigrationContractSession().getMigrationContract().setBaseContrProductSysID(baseContrProductSysID);
		
    }


    /**
     * @return String
     */
    public String retrieveMigContractDetails(){
        RetrieveMigGeneralInfoRequest retrieveMigGenInfoRequest = (RetrieveMigGeneralInfoRequest)this.getServiceRequest(ServiceManager.RETREIVE_MIG_GENINFO_REQUEST);
        retrieveMigGenInfoRequest.setMigDateSegmentSysId(this.migDateSegmentSysId);
        retrieveMigGenInfoRequest.setMigContractSysId(this.migContractSysId);
        this.getMigrationContractSession().getNavigationInfo().setUpdateLastAccessedPageOnly(true);
        setToRequest(retrieveMigGenInfoRequest);
        RetrieveMigGeneralInfoResponse retrieveMigGenInfoResponse = (RetrieveMigGeneralInfoResponse)executeService(retrieveMigGenInfoRequest);
        setGenInfoValuesToBackingBean(retrieveMigGenInfoResponse.getMigDateSegment());
        if(null!=retrieveMigGenInfoResponse.getMigrationDomainInfoList())
            setDomainInfoToBackingBean(retrieveMigGenInfoResponse.getMigrationDomainInfoList());
        this.getMigrationContractSession().getMigrationContract()
				.setBaseContrProductSysID(
						retrieveMigGenInfoResponse.getMigDateSegment()
								.getEwpdProductSystemId());
        
        return WebConstants.MIG_CONTRACT_STEP3;
        
     }
    
    /**
     * @return String
     */
    public String cancelMigration(){
    	//hide popup that used for to replace current product with latest product in second time migration. 
		//this.getSession().setAttribute(WebConstants.HIDE_PRODUCT_POPUP, WebConstants.FALSE);
		
        CancelMigrationRequest cancelRequest = (CancelMigrationRequest) this.getServiceRequest(ServiceManager.CANCEL_MIG_REQUEST);
        cancelRequest.setMigContractSysId(this.migContractSysId);
        cancelRequest.setMigDateSegmentSysId(this.migDateSegmentSysId);
        cancelRequest.setMigContractId(this.contractId);
        cancelRequest.setSystem(getMigrationContractSession().getMigrationContract().getSystem());
        cancelRequest.setDoneFlag(this.doneFlag);
        cancelRequest.setOption(super.getMigrationContractSession()
        							.getMigrationContract()
									.getOption());
        this.executeService(cancelRequest);
        return this.goToNextPage(WebConstants.MIG_CONTRACT_STEP1);
        
    }
    /**
     * @param migDomainInfoList
     */
    private void setDomainInfoToBackingBean(List migDomainInfoList){
       
        List templistLob = new ArrayList();
        List templistBe = new ArrayList();
        List templistBg = new ArrayList();
        /*START CARS*/
        List templistMbu = new ArrayList();
        /*END CARS*/
         MigrationDomainInfo tempdomain = new MigrationDomainInfo();
         StringBuffer bufferLob = new StringBuffer();
         StringBuffer bufferbe = new StringBuffer();
         StringBuffer bufferbg = new StringBuffer();
         /*START CARS*/
         StringBuffer buffermbu = new StringBuffer();
         /*END CARS*/
         for (Iterator iter = migDomainInfoList.iterator(); iter.hasNext();) {
	         tempdomain = (MigrationDomainInfo) iter.next();
	         if(null!=tempdomain.getLobId()&&!(WebConstants.EMPTY_STRING.equals(tempdomain.getLobId()))){
	             if(templistLob.indexOf(tempdomain.getLobId()) == -1){
		             
			             templistLob.add(tempdomain.getLobId());
			             if(bufferLob.length() > 0 ) {
			                 	bufferLob.append("~");
			             }
			             bufferLob.append(tempdomain.getLobDesc());
						 bufferLob.append("~");
						 bufferLob.append(tempdomain.getLobId());
		             }
	         }
	         if(null!=tempdomain.getBusinessEntityId()&&!("".equals(tempdomain.getBusinessEntityId()))){
		         if(templistBe.indexOf(tempdomain.getBusinessEntityId()) == -1){
		             
		             templistBe.add(tempdomain.getBusinessEntityId());
		             if(bufferbe.length() > 0 ) {
		                 bufferbe.append("~");
		             }
		             bufferbe.append(tempdomain.getBusinessEntityDesc());
		             bufferbe.append("~");
		             bufferbe.append(tempdomain.getBusinessEntityId());
		         }
	         } 
	         
	         if(null!=tempdomain.getBusinessGroupId()&&!((WebConstants.EMPTY_STRING).equals(tempdomain.getBusinessGroupId()))){
		         if(templistBg.indexOf(tempdomain.getBusinessGroupId()) == -1){
		             templistBg.add(tempdomain.getBusinessGroupId());
		             if(bufferbg.length() > 0 ) {
		                 bufferbg.append("~");
		             }
		             bufferbg.append(tempdomain.getBusinessGroupDesc());
		             bufferbg.append("~");
		             bufferbg.append(tempdomain.getBusinessGroupId());
		         }
	         } 
	         /*START CARS*/
	         if(null!=tempdomain.getMarketBusinessUnitId()&&!((WebConstants.EMPTY_STRING).equals(tempdomain.getMarketBusinessUnitId()))){
		         if(templistMbu.indexOf(tempdomain.getMarketBusinessUnitId()) == -1){
		         	templistMbu.add(tempdomain.getMarketBusinessUnitId());
		             if(buffermbu.length() > 0 ) {
		             	buffermbu.append("~");
		             }
		             buffermbu.append(tempdomain.getMarketBusinessUnitDesc());
		             buffermbu.append("~");
		             buffermbu.append(tempdomain.getMarketBusinessUnitId());
		         }
	         } 
	         /*END CARS*/
         }
         this.setLineOfBusinessDiv(bufferLob.toString());
         this.setBusinessEntityDiv(bufferbe.toString());
         this.setBusinessGroupDiv(bufferbg.toString());
         /*START CARS*/
         this.setBusinessGroupDiv(buffermbu.toString());
         /*END CARS*/
     }
    
    private void setGenInfoValuesToBackingBean(MigrationDateSegment migDateSegment){
        if( !isFieldEmpty(migDateSegment.getProductCode()))
			this.setProductCode(migDateSegment.getProductCode()+'~'+migDateSegment.getProductCodeDesc());
        
        if(!isFieldEmpty(migDateSegment.getStandardPlanCode()))
	    	this.setStandardPlanCode(migDateSegment.getStandardPlanCode()+'~'+migDateSegment.getStandardPlanCodeDesc());
        

	    this.setBenefitPlan(migDateSegment.getBenefitPlan());
			
		if(!isFieldEmpty(migDateSegment.getCorporatePlanCode()))
			this.setCorporatePlanCode(migDateSegment.getCorporatePlanCode()+'~'+migDateSegment.getCorporatePlanCodeDesc());

		
		if(!isFieldEmpty(migDateSegment.getFundingArrangement()))
			this.setFundingArrangement(migDateSegment.getFundingArrangement()+'~'+migDateSegment.getFundingArrangementDesc());

	    this.setBrandName(migDateSegment.getBrandName());
			
		if(!isFieldEmpty((migDateSegment.getGroupSize())))
			this.setGroupSize(migDateSegment.getGroupSize()+'~'+migDateSegment.getGroupSizeDesc());
	
		this.startDate = WPDStringUtil.convertDateToString(migDateSegment.getEffectiveDate());
		this.legacyStartDate = WPDStringUtil.convertDateToString(migDateSegment.getLegacyStartDate());
	
		this.endDate=WPDStringUtil.convertDateToString(migDateSegment.getExpiryDate());
			
		if(!isFieldEmpty(migDateSegment.getHeadQuartersState()))
			this.setHeadQuartersState(migDateSegment.getHeadQuartersState()+'~'+migDateSegment.getHeadQuartersStateDesc());
			
		//Contract type comes from the contract master table
		this.setContractType(migDateSegment.getContractType());
		
		this.contractId = migDateSegment.getContractId();
		
		if((WebConstants.CUSTOM).equals(migDateSegment.getContractType())){
			// If base exists.. base is option even if the contract is custom
			if(migDateSegment.getBaseDateSegmentSysId() != 0) {
				this.baseContractIdDiv = migDateSegment.getBaseContractId() + "~" + migDateSegment.getBaseContractSysId();
				this.baseContractDtDiv = WPDStringUtil.convertDateToString(migDateSegment.getBaseDateSegmentEffectiveDate())+'~'+migDateSegment.getBaseDateSegmentSysId();
			}
		}else if((WebConstants.STANDAR).equals(migDateSegment.getContractType())){
			// If base exists.. base is option even if the contract is custom
			if(migDateSegment.getBaseDateSegmentSysId() != 0) {
				this.baseContractIdStandardDiv = migDateSegment.getBaseContractId() + "~" + migDateSegment.getBaseContractSysId();
				this.baseContractDtStandardDiv = WPDStringUtil.convertDateToString(migDateSegment.getBaseDateSegmentEffectiveDate())+'~'+migDateSegment.getBaseDateSegmentSysId();
			}
		}
		
		this.setMedIndicator(migDateSegment.getMedicareAdjudInd());
		this.setItsIndicator(migDateSegment.getItsHomeAdjInd());
		this.setCobIndicator(migDateSegment.getCobAdjudInd());
		if(null == migDateSegment.getRegulatoryAgency())
			migDateSegment.setRegulatoryAgency(WebConstants.EMPTY_STRING);
		this.setRegulatoryAgency(migDateSegment.getRegulatoryAgency()+"~"+migDateSegment.getRegulatoryAgencyDesc());
		this.setComplianceStatus(migDateSegment.getComplianceStatus());
		if(null == migDateSegment.getProdProjNameCode())
			migDateSegment.setProdProjNameCode(WebConstants.EMPTY_STRING);
		this.setProdProjNameCode(migDateSegment.getProdProjNameCode()+"~"+migDateSegment.getProdProjNameCodeDesc());	
		this.setContractTermDate(WPDStringUtil.convertDateToString(migDateSegment.getContractTermDate()));
		if(null == migDateSegment.getMultiplanIndicator()
				||migDateSegment.getMultiplanIndicator().equals(WebConstants.EMPTY_STRING)){
			migDateSegment.setMultiplanIndicator("N");}
		this.setMultiplanIndicator(migDateSegment.getMultiplanIndicator());
		if(null == migDateSegment.getPerformanceGuarantee()
				||migDateSegment.getPerformanceGuarantee().equals(WebConstants.EMPTY_STRING)){
			migDateSegment.setPerformanceGuarantee("N");}
		this.setPerformanceGuarantee(migDateSegment.getPerformanceGuarantee());
		this.setSalesMarketDate(WPDStringUtil.convertDateToString(migDateSegment.getSalesMarketDate()));
		
		this.setAutoPopulate(migDateSegment.isAutoPopulateValues());
      }
    
    /*
     * Function to validate all the form fields 
     */
    private boolean isRequiredFieldsValid() {
        boolean requiredField = false;
        boolean dateNotValid = false;
        this.lobInvalid = false;
        this.entityInvalid = false;
        this.groupInvalid = false;
        this.startDateInvalid = false;
        this.endDateInvalid = false;
        this.contractTypeInvalid = false;
        this.baseContractIdInvalid = false;
        this.baseContractDtInvalid = false;
        this.baseContractDtForStandardInvalid = false;
        this.groupSizeInvalid = false;
        this.requiredHeadQuarters = false;
        this.requiredBenefitPlan = false;
        this.requiredProductCode = false;
        this.requiredCorporateCode = false;
        this.contractTermDateInvalid = false;
        this.salesMarketDateInvalid = false;
     
        if (!((WebConstants.SHELL).equals(this.contractType)) && isFieldEmpty(this.lineOfBusinessDiv)){
            lobInvalid = true;
            requiredField = true;
        }
        if (!((WebConstants.SHELL).equals(this.contractType)) && isFieldEmpty(this.businessEntityDiv)) {
            entityInvalid = true;
            requiredField = true;
        }
        if (!((WebConstants.SHELL).equals(this.contractType)) && isFieldEmpty(this.businessGroupDiv)) {
            groupInvalid = true;
            requiredField = true;
        }                     
        if (isFieldEmpty(this.contractType)) {
            contractTypeInvalid = true;
            requiredField = true;
        }
        if (!((WebConstants.SHELL).equals(this.contractType)) && isFieldEmpty(this.groupSize)) {
            groupSizeInvalid = true;
            requiredField = true;
        }
        
		if(!((WebConstants.SHELL).equals(this.contractType)) && isFieldEmpty(this.getProductCode())){
			requiredProductCode = true;
			requiredField = true;
		}


		if(!((WebConstants.SHELL).equals(this.contractType)) && isFieldEmpty(this.getBenefitPlan().trim())){
			requiredBenefitPlan = true;
			requiredField = true;
		}

		if(isFieldEmpty(this.contractType)){
		    this.contractTypeInvalid = true;
		    requiredField = true;
		}
		
		// Base contract effective date required if base contract id selected for custom contract.
		if((WebConstants.CUSTOM).equals(this.contractType) && !isFieldEmpty(this.baseContractIdDiv) && isFieldEmpty(this.baseContractDtDiv)) {
			this.baseContractDtInvalid = true;
			requiredField = true;
		}
		
		// Base contract effective date required if base contract id selected for standard contract.
		if((WebConstants.STANDAR).equals(this.contractType) && !isFieldEmpty(this.baseContractIdStandardDiv) && isFieldEmpty(this.baseContractDtStandardDiv)) {
			this.baseContractDtForStandardInvalid = true;
			requiredField = true;
		}
			
        if (!(this.contractTermDate == null || WebConstants.EMPTY_STRING.equals(this.contractTermDate.trim()))
        		&& !(WPDStringUtil.isValidDate(this.contractTermDate))) {
            
			ErrorMessage errorMessage = new ErrorMessage(WebConstants.INPUT_FORMAT_INVALID);
			errorMessage.setParameters(new String[] { WebConstants.CONTRACT_TERM_DATE });
			validationMessages.add(errorMessage);
			dateNotValid = true;
			contractTermDateInvalid = true;
		}
        if (!(this.salesMarketDate == null || WebConstants.EMPTY_STRING.equals(this.salesMarketDate.trim()))
        		&& !(WPDStringUtil.isValidDate(this.salesMarketDate))) {
            
			ErrorMessage errorMessage = new ErrorMessage(WebConstants.INPUT_FORMAT_INVALID);
			errorMessage.setParameters(new String[] { WebConstants.SALES_MARKET_DATE });
			validationMessages.add(errorMessage);
			dateNotValid = true;
			salesMarketDateInvalid = true;
		}
	        if((null!=this.getBrandName()) && !("".equalsIgnoreCase(this.getBrandName()))){
				    if(this.getBrandName().trim().length() > 30){				    	
				    	validationMessages.add(new ErrorMessage(WebConstants.INVALID_BRAND_NAME));
				    	return false;
				    }
	        }	
	        if((null!=this.benefitPlan) && !("".equalsIgnoreCase(this.benefitPlan))){
		    	 if(this.benefitPlan.trim().length() > 15){					    	
				    	validationMessages.add(new ErrorMessage(WebConstants.INVALID_BENEFIT_PLAN_NAME));
				    	return false;
				    }
   	}	
        if (requiredField) {
            this.validationMessages.add(new ErrorMessage(
                    WebConstants.MANDATORY_FIELDS_REQUIRED));
            return false;
        }else if(dateNotValid){
        	return false;
        }
        return true;
    }
    
    private boolean isFieldEmpty(String fieldText){
    	if(fieldText == null || fieldText.trim().equals(WebConstants.EMPTY_STRING)) {
    		return true;
    	}
    	return false;
    }
    
    public void setBreadCrumbText(){
                       
    }
    
    public String done(){
    	
    	if (isRequiredFieldsValid()){
            SaveMigGeneralInfoRequest saveRequest = (SaveMigGeneralInfoRequest)this.getServiceRequest(ServiceManager.SAVE_MIG_GENINFO_REQUEST);
            setBackingBeanValuesToRequest(saveRequest);
           
            saveRequest.setAction(SaveMigGeneralInfoRequest.SAVE_MIG_GENERAL_INFO);            
 		   SaveMigGeneralInfoResponse saveResponse = (SaveMigGeneralInfoResponse)executeService(saveRequest);
            if(null != saveResponse && saveResponse.isSuccess()){
            		this.setToSession(saveResponse);
            		
            }					
         }
         else {
         	 addAllMessagesToRequest(this.validationMessages);
         }        	
    	
    	if(this.validationMessages.size()==0){
    	
			if(getMigrationContractSession().getNavigationInfo().getStepCompleted() >= BusinessConstants.STEP7){
				
				return goToNextPage(WebConstants.MIG_CONTRACT_STEP9);
			}
			else {
				this.validationMessages = validateStepNumber();		
				getRequest().setAttribute("RETAIN_Value", "");
			}				 
    	}
		addAllMessagesToRequest(this.validationMessages);  
		return WebConstants.MIG_CONTRACT_STEP3;
	}
    
    
	/**
	 * Returns the disableField
	 * @return boolean disableField.
	 */
	public boolean getDisableField() {
		return disableField;
	}
	/**
	 * Sets the disableField
	 * @param disableField.
	 */
	public void setDisableField(boolean disableField) {
		this.disableField = disableField;
	}
    /**
     * Returns the brandName
     * @return String brandName.
     */
    public String getBrandName() {
        return brandName;
    }
    /**
     * Sets the brandName
     * @param brandName.
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    /**
     * Returns the mandate
     * @return boolean mandate.
     */
    public boolean isMandate() {
        return mandate;
    }
    /**
     * Sets the mandate
     * @param mandate.
     */
    public void setMandate(boolean mandate) {
        this.mandate = mandate;
    }
    /**
     * Returns the requiredCorporateCode
     * @return boolean requiredCorporateCode.
     */
    public boolean isRequiredCorporateCode() {
        return requiredCorporateCode;
    }
    /**
     * Sets the requiredCorporateCode
     * @param requiredCorporateCode.
     */
    public void setRequiredCorporateCode(boolean requiredCorporateCode) {
        this.requiredCorporateCode = requiredCorporateCode;
    }
    /**
     * Returns the contractTypeInvalid
     * @return boolean contractTypeInvalid.
     */
    public boolean isContractTypeInvalid() {
        return contractTypeInvalid;
    }
    /**
     * Sets the contractTypeInvalid
     * @param contractTypeInvalid.
     */
    public void setContractTypeInvalid(boolean contractTypeInvalid) {
        this.contractTypeInvalid = contractTypeInvalid;
    }
    /**
     * Returns the baseContractDtDiv
     * @return String baseContractDtDiv.
     */
    public String getBaseContractDtDiv() {
        return baseContractDtDiv;
    }
    /**
     * Sets the baseContractDtDiv
     * @param baseContractDtDiv.
     */
    public void setBaseContractDtDiv(String baseContractDtDiv) {
        this.baseContractDtDiv = baseContractDtDiv;
    }
    /**
     * Returns the baseContractDtInvalid
     * @return boolean baseContractDtInvalid.
     */
    public boolean isBaseContractDtInvalid() {
        return baseContractDtInvalid;
    }
    /**
     * Sets the baseContractDtInvalid
     * @param baseContractDtInvalid.
     */
    public void setBaseContractDtInvalid(boolean baseContractDtInvalid) {
        this.baseContractDtInvalid = baseContractDtInvalid;
    }
    /**
     * Returns the baseContractIdDiv
     * @return String baseContractIdDiv.
     */
    public String getBaseContractIdDiv() {
        return baseContractIdDiv;
    }
    /**
     * Sets the baseContractIdDiv
     * @param baseContractIdDiv.
     */
    public void setBaseContractIdDiv(String baseContractIdDiv) {
        this.baseContractIdDiv = baseContractIdDiv;
    }
    /**
     * Returns the baseContractIdInvalid
     * @return boolean baseContractIdInvalid.
     */
    public boolean isBaseContractIdInvalid() {
        return baseContractIdInvalid;
    }
    /**
     * Sets the baseContractIdInvalid
     * @param baseContractIdInvalid.
     */
    public void setBaseContractIdInvalid(boolean baseContractIdInvalid) {
        this.baseContractIdInvalid = baseContractIdInvalid;
    }
    /**
     * Returns the headQuartersStateList
     * @return List headQuartersStateList.
     */
    public List getHeadQuartersStateList() {
        return headQuartersStateList;
    }
    /**
     * Sets the headQuartersStateList
     * @param headQuartersStateList.
     */
    public void setHeadQuartersStateList(List headQuartersStateList) {
        this.headQuartersStateList = headQuartersStateList;
    }
    /**
     * Returns the requiredBenefitPlan
     * @return boolean requiredBenefitPlan.
     */
    public boolean isRequiredBenefitPlan() {
        return requiredBenefitPlan;
    }
    /**
     * Sets the requiredBenefitPlan
     * @param requiredBenefitPlan.
     */
    public void setRequiredBenefitPlan(boolean requiredBenefitPlan) {
        this.requiredBenefitPlan = requiredBenefitPlan;
    }
    /**
     * Returns the requiredProductCode
     * @return boolean requiredProductCode.
     */
    public boolean isRequiredProductCode() {
        return requiredProductCode;
    }
    /**
     * Sets the requiredProductCode
     * @param requiredProductCode.
     */
    public void setRequiredProductCode(boolean requiredProductCode) {
        this.requiredProductCode = requiredProductCode;
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
     * Returns the benefitPlan
     * @return String benefitPlan.
     */
    public String getBenefitPlan() {
        return benefitPlan;
    }
    /**
     * Sets the benefitPlan
     * @param benefitPlan.
     */
    public void setBenefitPlan(String benefitPlan) {
        this.benefitPlan = benefitPlan;
    }
    /**
     * Returns the corporatePlanCode
     * @return String corporatePlanCode.
     */
    public String getCorporatePlanCode() {
        return corporatePlanCode;
    }
    /**
     * Sets the corporatePlanCode
     * @param corporatePlanCode.
     */
    public void setCorporatePlanCode(String corporatePlanCode) {
        this.corporatePlanCode = corporatePlanCode;
    }
    /**
     * Returns the fundingArrangement
     * @return String fundingArrangement.
     */
    public String getFundingArrangement() {
        return fundingArrangement;
    }
    /**
     * Sets the fundingArrangement
     * @param fundingArrangement.
     */
    public void setFundingArrangement(String fundingArrangement) {
        this.fundingArrangement = fundingArrangement;
    }
    /**
     * Returns the groupName
     * @return String groupName.
     */
    public String getGroupName() {
        return groupName;
    }
    /**
     * Sets the groupName
     * @param groupName.
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    /**
     * Returns the productCode
     * @return String productCode.
     */
    public String getProductCode() {
        return productCode;
    }
    /**
     * Sets the productCode
     * @param productCode.
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    /**
     * Returns the standardPlanCode
     * @return String standardPlanCode.
     */
    public String getStandardPlanCode() {
        return standardPlanCode;
    }
    /**
     * Sets the standardPlanCode
     * @param standardPlanCode.
     */
    public void setStandardPlanCode(String standardPlanCode) {
        this.standardPlanCode = standardPlanCode;
    }
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
     * Returns the contractId
     * @return String contractId.
     */
    public String getContractId() {
        return contractId;
    }
    /**
     * Sets the contractId
     * @param contractId.
     */
    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
    /**
     * Returns the contractType
     * @return String contractType.
     */
    public String getContractType() {
        return contractType;
    }
    /**
     * Sets the contractType
     * @param contractType.
     */
    public void setContractType(String contractType) {
        this.contractType = contractType;
    }
    /**
     * Returns the endDate
     * @return String endDate.
     */
    public String getEndDate() {
        return endDate;
    }
    /**
     * Sets the endDate
     * @param endDate.
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
     /**
     * Returns the headQuartersState
     * @return String headQuartersState.
     */
    public String getHeadQuartersState() {
        return headQuartersState;
    }
    /**
     * Sets the headQuartersState
     * @param headQuartersState.
     */
    public void setHeadQuartersState(String headQuartersState) {
        this.headQuartersState = headQuartersState;
    }
    /**
     * Returns the endDateInvalid
     * @return boolean endDateInvalid.
     */
    public boolean isEndDateInvalid() {
        return endDateInvalid;
    }
    /**
     * Sets the endDateInvalid
     * @param endDateInvalid.
     */
    public void setEndDateInvalid(boolean endDateInvalid) {
        this.endDateInvalid = endDateInvalid;
    }
    /**
     * Returns the entityInvalid
     * @return boolean entityInvalid.
     */
    public boolean isEntityInvalid() {
        return entityInvalid;
    }
    /**
     * Sets the entityInvalid
     * @param entityInvalid.
     */
    public void setEntityInvalid(boolean entityInvalid) {
        this.entityInvalid = entityInvalid;
    }
    /**
     * Returns the groupInvalid
     * @return boolean groupInvalid.
     */
    public boolean isGroupInvalid() {
        return groupInvalid;
    }
    /**
     * Sets the groupInvalid
     * @param groupInvalid.
     */
    public void setGroupInvalid(boolean groupInvalid) {
        this.groupInvalid = groupInvalid;
    }
    /**
     * Returns the groupSizeDiv
     * @return String groupSizeDiv.
     */
    public String getGroupSizeDiv() {
        return groupSizeDiv;
    }
    /**
     * Sets the groupSizeDiv
     * @param groupSizeDiv.
     */
    public void setGroupSizeDiv(String groupSizeDiv) {
        this.groupSizeDiv = groupSizeDiv;
    }
    /**
     * Returns the groupSizeInvalid
     * @return boolean groupSizeInvalid.
     */
    public boolean isGroupSizeInvalid() {
        return groupSizeInvalid;
    }
    /**
     * Sets the groupSizeInvalid
     * @param groupSizeInvalid.
     */
    public void setGroupSizeInvalid(boolean groupSizeInvalid) {
        this.groupSizeInvalid = groupSizeInvalid;
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
     * Returns the lobInvalid
     * @return boolean lobInvalid.
     */
    public boolean isLobInvalid() {
        return lobInvalid;
    }
    /**
     * Sets the lobInvalid
     * @param lobInvalid.
     */
    public void setLobInvalid(boolean lobInvalid) {
        this.lobInvalid = lobInvalid;
    }
    /**
     * Returns the startDate
     * @return String startDate.
     */
    public String getStartDate() {
        return startDate;
    }
    /**
     * Sets the startDate
     * @param startDate.
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    /**
     * Returns the startDateInvalid
     * @return boolean startDateInvalid.
     */
    public boolean isStartDateInvalid() {
        return startDateInvalid;
    }
    /**
     * Sets the startDateInvalid
     * @param startDateInvalid.
     */
    public void setStartDateInvalid(boolean startDateInvalid) {
        this.startDateInvalid = startDateInvalid;
    }
    
    
    /**
     * Returns the contractTypeList
     * @return List contractTypeList.
     */
    public List getContractTypeList() {
        List contractTypeListForCombo = new ArrayList();
        List contractTypeList = new ArrayList();
        RefDataRequest refdataRequest = (RefDataRequest) this
        	.getServiceRequest(ServiceManager.REF_DATA_REQUEST);
        refdataRequest.setPopupId(1946);
        RefDataResponse refDataResponse = (RefDataResponse) this
                .executeService(refdataRequest);
        contractTypeList = refDataResponse.getList();
        
        Iterator contractTypeListIterator = contractTypeList.iterator();
        contractTypeListForCombo.add(new SelectItem(WebConstants.EMPTY_STRING));
        while(contractTypeListIterator.hasNext()){
            ReferenceData contractTypeResult = (ReferenceData)contractTypeListIterator.next();
            
            // Dont set the unwanted code here. Check which is not needed.
            if(!contractTypeResult.getCode().equals(WebConstants.MANDATE_TYPE)){
                contractTypeListForCombo.add(new SelectItem(contractTypeResult.getCode(),contractTypeResult.getDescription()));
            }
            
        }
    	return contractTypeListForCombo;
    }

    /**
     * Sets the contractTypeList
     * @param contractTypeList.
     */
    public void setContractTypeList(List contractTypeList) {
        this.contractTypeList = contractTypeList;
    }
    /**
     * Returns the migContractSysId
     * @return int migContractSysId.
     */
    public int getMigContractSysId() {
        return migContractSysId;
    }
    /**
     * Sets the migContractSysId
     * @param migContractSysId.
     */
    public void setMigContractSysId(int migContractSysId) {
        this.migContractSysId = migContractSysId;
    }
    /**
     * Returns the migDateSegmentSysId
     * @return int migDateSegmentSysId.
     */
    public int getMigDateSegmentSysId() {
        return migDateSegmentSysId;
    }
    /**
     * Sets the migDateSegmentSysId
     * @param migDateSegmentSysId.
     */
    public void setMigDateSegmentSysId(int migDateSegmentSysId) {
        this.migDateSegmentSysId = migDateSegmentSysId;
    }
    /**
     * Returns the requiredHeadQuarters
     * @return boolean requiredHeadQuarters.
     */
    public boolean isRequiredHeadQuarters() {
        return requiredHeadQuarters;
    }
    /**
     * Sets the requiredHeadQuarters
     * @param requiredHeadQuarters.
     */
    public void setRequiredHeadQuarters(boolean requiredHeadQuarters) {
        this.requiredHeadQuarters = requiredHeadQuarters;
    }      
    /**
     * Returns the groupSize
     * @return String groupSize.
     */
    public String getGroupSize() {
        return groupSize;
    }
    /**
     * Sets the groupSize
     * @param groupSize.
     */
    public void setGroupSize(String groupSize) {
        this.groupSize = groupSize;
    }
	/**
	 * @return Returns the legacyStartDate.
	 */
	public String getLegacyStartDate() {
		return legacyStartDate;
	}
	/**
	 * @param legacyStartDate The legacyStartDate to set.
	 */
	public void setLegacyStartDate(String legacyStartDate) {
		this.legacyStartDate = legacyStartDate;
	}
	/**
	 * Returns the action
	 * @return int action.
	 */
	public int getAction() {
		return action;
	}
	/**
	 * Sets the action
	 * @param action.
	 */
	public void setAction(int action) {
		this.action = action;
	}
	
	
   
   
    /**
     * Returns the dateSegment
     * @return String dateSegment.
     */
    public String getDateSegment() {
      return retrieveMigContractDetails();
    }
    /**
     * Sets the dateSegment
     * @param dateSegment.
     */
    public void setDateSegment(String dateSegment) {
        this.dateSegment = dateSegment;
    }

    /**
     * @return baseContractId
     * 
     * Returns the baseContractId.
     */
    public String getBaseContractId() {
        return baseContractId;
    }
    /**
     * @param baseContractId
     * 
     * Sets the baseContractId.
     */
    public void setBaseContractId(String baseContractId) {
        this.baseContractId = baseContractId;
    }
    /**
     * @return selectBaseContractFlag
     * 
     * Returns the selectBaseContractFlag.
     */
    public String getSelectBaseContractFlag() {
        return selectBaseContractFlag;
    }
    /**
     * @param selectBaseContractFlag
     * 
     * Sets the selectBaseContractFlag.
     */
    public void setSelectBaseContractFlag(String selectBaseContractFlag) {
        this.selectBaseContractFlag = "N";
    }
    /**
     * @return confirmProductFlag
     * 
     * Returns the confirmProductFlag.
     */
    public boolean isConfirmProductFlag() {
        return confirmProductFlag;
    }
    /**
     * @param confirmProductFlag
     * 
     * Sets the confirmProductFlag.
     */
    public void setConfirmProductFlag(boolean confirmProductFlag) {
        this.confirmProductFlag = confirmProductFlag;
    }
    /**
     * @return ewpdProductSysId
     * 
     * Returns the ewpdProductSysId.
     */
    public int getEwpdProductSysId() {
        return ewpdProductSysId;
    }
    /**
     * @param ewpdProductSysId
     * 
     * Sets the ewpdProductSysId.
     */
    public void setEwpdProductSysId(int ewpdProductSysId) {
        this.ewpdProductSysId = ewpdProductSysId;
    }
    /**
     * @return saveProductFlag
     * 
     * Returns the saveProductFlag.
     */
    public boolean isSaveProductFlag() {
        return saveProductFlag;
    }
    /**
     * @param saveProductFlag
     * 
     * Sets the saveProductFlag.
     */
    public void setSaveProductFlag(boolean saveProductFlag) {
        this.saveProductFlag = saveProductFlag;
    }
	/**
	 * @return Returns the cobIndicator.
	 */
	public String getCobIndicator() {
		return cobIndicator;
	}
	/**
	 * @param cobIndicator The cobIndicator to set.
	 */
	public void setCobIndicator(String cobIndicator) {
		this.cobIndicator = cobIndicator;
	}
	/**
	 * @return Returns the itsIndicator.
	 */
	public String getItsIndicator() {
		return itsIndicator;
	}
	/**
	 * @param itsIndicator The itsIndicator to set.
	 */
	public void setItsIndicator(String itsIndicator) {
		this.itsIndicator = itsIndicator;
	}
	/**
	 * @return Returns the medIndicator.
	 */
	public String getMedIndicator() {
		return medIndicator;
	}
	/**
	 * @param medIndicator The medIndicator to set.
	 */
	public void setMedIndicator(String medIndicator) {
		this.medIndicator = medIndicator;
	}
	/**
	 * @return Returns the complianceStatus.
	 */
	public String getComplianceStatus() {
		return complianceStatus;
	}
	/**
	 * @param complianceStatus The complianceStatus to set.
	 */
	public void setComplianceStatus(String complianceStatus) {
		this.complianceStatus = complianceStatus;
	}
	/**
	 * @return Returns the contractTermDate.
	 */
	public String getContractTermDate() {
		return contractTermDate;
	}
	/**
	 * @param contractTermDate The contractTermDate to set.
	 */
	public void setContractTermDate(String contractTermDate) {
		this.contractTermDate = contractTermDate;
	}
	/**
	 * @return Returns the multiplanIndicator.
	 */
	public String getMultiplanIndicator() {
		return multiplanIndicator;
	}
	/**
	 * @param multiplanIndicator The multiplanIndicator to set.
	 */
	public void setMultiplanIndicator(String multiplanIndicator) {
		this.multiplanIndicator = multiplanIndicator;
	}
	/**
	 * @return Returns the performanceGuarantee.
	 */
	public String getPerformanceGuarantee() {
		return performanceGuarantee;
	}
	/**
	 * @param performanceGuarantee The performanceGuarantee to set.
	 */
	public void setPerformanceGuarantee(String performanceGuarantee) {
		this.performanceGuarantee = performanceGuarantee;
	}
	/**
	 * @return Returns the prodProjNameCode.
	 */
	public String getProdProjNameCode() {
		return prodProjNameCode;
	}
	/**
	 * @param prodProjNameCode The prodProjNameCode to set.
	 */
	public void setProdProjNameCode(String prodProjNameCode) {
		this.prodProjNameCode = prodProjNameCode;
	}
	/**
	 * @return Returns the regulatoryAgency.
	 */
	public String getRegulatoryAgency() {
		return regulatoryAgency;
	}
	/**
	 * @param regulatoryAgency The regulatoryAgency to set.
	 */
	public void setRegulatoryAgency(String regulatoryAgency) {
		this.regulatoryAgency = regulatoryAgency;
	}
	/**
	 * @return Returns the salesMarketDate.
	 */
	public String getSalesMarketDate() {
		return salesMarketDate;
	}
	/**
	 * @param salesMarketDate The salesMarketDate to set.
	 */
	public void setSalesMarketDate(String salesMarketDate) {
		this.salesMarketDate = salesMarketDate;
	}
	/**
	 * @return Returns the contractTermDateInvalid.
	 */
	public boolean isContractTermDateInvalid() {
		return contractTermDateInvalid;
	}
	/**
	 * @param contractTermDateInvalid The contractTermDateInvalid to set.
	 */
	public void setContractTermDateInvalid(boolean contractTermDateInvalid) {
		this.contractTermDateInvalid = contractTermDateInvalid;
	}
	/**
	 * @return Returns the salesMarketDateInvalid.
	 */
	public boolean isSalesMarketDateInvalid() {
		return salesMarketDateInvalid;
	}
	/**
	 * @param salesMarketDateInvalid The salesMarketDateInvalid to set.
	 */
	public void setSalesMarketDateInvalid(boolean salesMarketDateInvalid) {
		this.salesMarketDateInvalid = salesMarketDateInvalid;
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
	 * @return Returns the baseContractIdStandardDiv.
	 */
	public String getBaseContractIdStandardDiv() {
		return baseContractIdStandardDiv;
	}
	/**
	 * @param baseContractIdStandardDiv The baseContractIdStandardDiv to set.
	 */
	public void setBaseContractIdStandardDiv(String baseContractIdStandardDiv) {
		this.baseContractIdStandardDiv = baseContractIdStandardDiv;
	}
	/**
	 * @return Returns the baseContractDtStandardDiv.
	 */
	public String getBaseContractDtStandardDiv() {
		return baseContractDtStandardDiv;
	}
	/**
	 * @param baseContractDtStandardDiv The baseContractDtStandardDiv to set.
	 */
	public void setBaseContractDtStandardDiv(String baseContractDtStandardDiv) {
		this.baseContractDtStandardDiv = baseContractDtStandardDiv;
	}
	/**
	 * @return Returns the baseContractDtForStandardInvalid.
	 */
	public boolean isBaseContractDtForStandardInvalid() {
		return baseContractDtForStandardInvalid;
	}
	/**
	 * @param baseContractDtForStandardInvalid The baseContractDtForStandardInvalid to set.
	 */
	public void setBaseContractDtForStandardInvalid(
			boolean baseContractDtForStandardInvalid) {
		this.baseContractDtForStandardInvalid = baseContractDtForStandardInvalid;
	}
	
	public String removeProductInfo() {
		if (isRequiredFieldsValid()) {
			MigrationProductInfoRequest migrationProductInfoRequest = (MigrationProductInfoRequest) this
					.getServiceRequest(ServiceManager.MIGRATION_PRODUCT_INFO_REQUEST);
			migrationProductInfoRequest
					.setAction(BusinessConstants.REMOVE_PRODUCT_INFO);
			migrationProductInfoRequest.setMigrationContractSession(this
					.getMigrationContractSession());
			migrationProductInfoRequest.setMigratedDateSegmentId(this
					.getMigrationContractSession().getDateSegmentId());

			this.setToRequest(migrationProductInfoRequest);
			SaveMigGeneralInfoRequest saveRequest = (SaveMigGeneralInfoRequest) this
					.getServiceRequest(ServiceManager.SAVE_MIG_GENINFO_REQUEST);
			
			setBackingBeanValuesToRequest(saveRequest);
			// saveRequest.setAction(this.getAction());
			saveRequest
					.setAction(SaveMigGeneralInfoRequest.SAVE_MIG_GENERAL_INFO);

			SaveMigGeneralInfoResponse saveResponse = (SaveMigGeneralInfoResponse) executeService(saveRequest);

			if (null != saveResponse && saveResponse.isSuccess()) {
				this.setToSession(saveResponse);
				MigrationProductInfoResponse migrationProductInfoResponse = (MigrationProductInfoResponse) executeService(migrationProductInfoRequest);
				if (migrationProductInfoResponse.isSuccess()) {
					this.getMigrationContractSession().getMigrationContract()
							.setMappedProductFlag(false);
				}
				this.getMigrationContractSession().getMigrationContract().setEwpdProductSystemId(WebConstants.EMPTY_STRING);
				this.getMigrationContractSession().getMigrationContract().setMigratedProdStructureMappingSysID(WebConstants.EMPTY_STRING);
				this.getMigrationContractSession().getNavigationInfo().setStepCompleted(3);
				return goToNextPage(WebConstants.MIG_CONTRACT_STEP4);
			}
		} else {
			addAllMessagesToRequest(this.validationMessages);
		}
		return WebConstants.EMPTY_STRING;
	}
	/**
	 * @return Returns the autoPopulate.
	 */
	public boolean isAutoPopulate() {
		return autoPopulate;
	}
	/**
	 * @param autoPopulate The autoPopulate to set.
	 */
	public void setAutoPopulate(boolean autoPopulate) {
		this.autoPopulate = autoPopulate;
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
