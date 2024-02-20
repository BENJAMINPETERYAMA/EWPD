/*
 * ContractBenefitGeneralInfo.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.contract;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import com.wellpoint.wpd.business.refdata.bo.AssignedRuleIdBO;
import com.wellpoint.wpd.common.contract.request.RetrieveBenefitGeneralInfoRequest;
import com.wellpoint.wpd.common.contract.request.SaveRuleInformationRequest;
import com.wellpoint.wpd.common.contract.response.RetrieveBenefitGeneralInfoResponse;
import com.wellpoint.wpd.common.contract.response.SaveRuleInformationResponse;
import com.wellpoint.wpd.common.contract.vo.ContractVO;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitDatatypeBO;
import com.wellpoint.wpd.common.standardbenefit.bo.UniverseBO;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractBenefitGeneralInfoBackingBean  extends ContractBackingBean {
    
    private String lob;

    private String businessEntity;

    private String businessGroup;

    private String effectiveDate;

    private String expiryDate;

    private String minorHeading;

    private String description;

    private String createdUser;

    private Date createdTimestamp;

    private String lastUpdatedUser;

    private Date lastUpdatedTimestamp;

    private String term;

    private String qualifier;

    private String providerArrangement;

    private String dataType;

    private String ruleType;
    
    private String benefitId;
    
    private String benefitMeaning;
     
    private String benefitCategory;
    
    private String ruleIDFetched;
    
    private String benefitMeaningFetched;
    
    private String blzeRuleType;    
	/**
	 * @return Returns the blzeRuleType.
	 */
	
//    private List benefitGeneralInfoList = null;
    
    private String ruleInfoChanged;
    
    private int action;
    
    private String valueForPrint;
    
    private String defaultRuleID;
    
    private String defaultRuleDesc;
    
    public static final int RULE_BENEFIT_DEFINITION = 3; 
    
    protected boolean requiredBenefitMeaning;
    
    protected boolean requiredBenefitRule;
    
    protected String tempRuleType;
    
    private String benefitType;
    
    private String pageLoadBasedOnContractType;
    
    private String benefitCategoryDesc;
    
    //CR - added benefit version 
    
    private int benefitVersion;
    
    private String tierDefinition;
    
    private String tierDefinitionForprint;
    //CARS START
    private String marketBusinessUnit;
    //CARS END
    //ICD10 --getting BC ID from session
    private int benefitCompntId;
	

	public int getBenefitCompntId() {
		return benefitCompntId;
	}

	public void setBenefitCompntId(int benefitCompntId) {
		this.benefitCompntId = benefitCompntId;
	}
    
    public ContractBenefitGeneralInfoBackingBean(){
        super();
        this.setBreadCrumbText();
    	if(0 != getBenefitComponentIdFromSession()){
      		this.benefitCompntId = getBenefitComponentIdFromSession();
      	}
    }
    
    /**
     * @return Returns the benefitDefinitonsList.
     */
    public String displayStandardBenefitGeneralInfo() {
        Logger.logInfo("Entering the method for getting benefitGeneral Info"
                + " list values");
        
        RetrieveBenefitGeneralInfoResponse retrieveBenefitGeneralInfoResponse = null;
        RetrieveBenefitGeneralInfoRequest retrieveBenefitGeneralInfoRequest = (RetrieveBenefitGeneralInfoRequest) this
        .getServiceRequest(ServiceManager.RETRIEVE_BENEFIT_GENRALINFO);
        retrieveBenefitGeneralInfoRequest.setAction(this.action);
     
        int standardKey = getContractSession().getBenefitId();
        int bnftCompId = getContractSession().getBenefitComponentId();
        retrieveBenefitGeneralInfoRequest.setBenefitComponentId(bnftCompId);
        retrieveBenefitGeneralInfoRequest.setStandardBenefitKey(standardKey);
        retrieveBenefitGeneralInfoRequest.setDateSegmentId(getContractSession().getContractKeyObject().getDateSegmentId());
//        ContractVO contractVO = new ContractVO();
        
        retrieveBenefitGeneralInfoResponse = (RetrieveBenefitGeneralInfoResponse) this
        .executeService(retrieveBenefitGeneralInfoRequest);
        if (null != retrieveBenefitGeneralInfoResponse) {
            setValuesToBackingBeanForView(retrieveBenefitGeneralInfoResponse);
        }
        if(isViewMode()){
            return "displayStandardBenefitGeneralInfoView";
        }else{
            return "displayStandardBenefitGeneralInfo";
        }
    }
    /**
     * To set the values to backing bean for view
     * 
     * @param ruleTypeItems
     * @return String
     */
    public static String getTildaStringFromRuleTypeList(List ruleTypeItems){
        
        if(ruleTypeItems == null)
            return "";
        
        StringBuffer buffer = new StringBuffer();
        for (int i=0; i<ruleTypeItems.size(); i++) {
            AssignedRuleIdBO element = (AssignedRuleIdBO) ruleTypeItems.get(i);
            if(i!=0){
                buffer.append("~");
            }
            buffer.append(element.getPrimaryCode());
            buffer.append("~" + element.getCodeDescText());
        }
        return buffer.toString();
    }
    
    private void setValuesToBackingBeanForView(
            RetrieveBenefitGeneralInfoResponse retrieveBenefitGeneralInfoResponse ) {

        if (retrieveBenefitGeneralInfoResponse != null
                && retrieveBenefitGeneralInfoResponse.getStandardBenefitBO() != null) {
        	
        	this.defaultRuleID = retrieveBenefitGeneralInfoResponse.getDefaultRuleID();
			this.defaultRuleDesc = retrieveBenefitGeneralInfoResponse.getDefaultRuleDesc();
			
            DomainDetail domainDetail = retrieveBenefitGeneralInfoResponse
                    .getDomainDetail();
            if (domainDetail != null) {
                this.lob = WPDStringUtil.getTildaString(domainDetail
                        .getLineOfBusiness());
                this.businessEntity = WPDStringUtil.getTildaString(domainDetail
                        .getBusinessEntity());
                this.businessGroup = WPDStringUtil.getTildaString(domainDetail
                        .getBusinessGroup());
                //CARS START
                this.marketBusinessUnit = WPDStringUtil.getTildaString(domainDetail
                        .getMarketBusinessUnit());
                //CARS END
            }
            this.minorHeading = retrieveBenefitGeneralInfoResponse
                    .getStandardBenefitBO().getBenefitIdentifier();
            this.description = retrieveBenefitGeneralInfoResponse
                    .getStandardBenefitBO().getDescription();
            List terms = retrieveBenefitGeneralInfoResponse
                    .getStandardBenefitBO().getTermList();
            this.term = getTildaStringFromUniverseList(terms);
            List qualifiers = retrieveBenefitGeneralInfoResponse
                    .getStandardBenefitBO().getQualifierList();
            this.qualifier = getTildaStringFromUniverseList(qualifiers);
            List pva = retrieveBenefitGeneralInfoResponse.getStandardBenefitBO()
                    .getPVAList();
            this.providerArrangement = getTildaStringFromUniverseList(pva);
            List dataType = retrieveBenefitGeneralInfoResponse
                    .getStandardBenefitBO().getDataTypeList();
            this.dataType = getTildaStringFromDataTypeList(dataType);
            this.setRuleType(retrieveBenefitGeneralInfoResponse.getStandardBenefitBO().getRuleId());
            // New code added for fetching the RuleType
            this.setBlzeRuleType(retrieveBenefitGeneralInfoResponse.getStandardBenefitBO().getStrRuleType());
            this.setBenefitMeaning(retrieveBenefitGeneralInfoResponse.getStandardBenefitBO().getBenefitMeaning());
            this.setBenefitMeaningFetched(retrieveBenefitGeneralInfoResponse.getStandardBenefitBO().getBenefitMeaning());
            this.setRuleIDFetched(retrieveBenefitGeneralInfoResponse.getStandardBenefitBO().getRuleId());
            this.setBenefitCategory(retrieveBenefitGeneralInfoResponse.getStandardBenefitBO().getBenefitCategoryDesc());
            this.benefitId = retrieveBenefitGeneralInfoResponse.getStandardBenefitBO().getBenefitIdentifier();
            this.benefitType = retrieveBenefitGeneralInfoResponse.getStandardBenefitBO().getBenefitType();
            this.createdUser = retrieveBenefitGeneralInfoResponse
                    .getStandardBenefitBO().getCreatedUser();
            Date createdDate = retrieveBenefitGeneralInfoResponse
                    .getStandardBenefitBO().getCreatedTimestamp();
            if (createdDate != null) {
//                String creationDateString = WPDStringUtil
  //                      .getStringDate(createdDate);
                this.createdTimestamp = createdDate;
            }
            this.lastUpdatedUser = retrieveBenefitGeneralInfoResponse
                    .getStandardBenefitBO().getLastUpdatedUser();
            Date updatedDate = retrieveBenefitGeneralInfoResponse
                    .getStandardBenefitBO().getLastUpdatedTimestamp();
            if (updatedDate != null) {
//                String updationDateString = WPDStringUtil
//                        .getStringDate(updatedDate);
                this.lastUpdatedTimestamp = updatedDate;
            }
            //CR - benefit version added
            this.benefitVersion = retrieveBenefitGeneralInfoResponse.getStandardBenefitBO().getVersion();
            if(null != retrieveBenefitGeneralInfoResponse.getStandardBenefitBO().getTierDefinitionList()){
            	tierDefinition = WPDStringUtil.getTildaStringFromList (retrieveBenefitGeneralInfoResponse.
            			getStandardBenefitBO().getTierDefinitionList());
            	if(null!=tierDefinition && !("").equals(tierDefinition)){
            	this.tierDefinitionForprint =tierDefinition.replace('~',',');
            	}
            }
        }
    }
    private String getTildaStringFromUniverseList(List universeItems) {

        if (universeItems == null)
            return "";

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < universeItems.size(); i++) {
            UniverseBO element = (UniverseBO) universeItems.get(i);
            if (i != 0) {
                buffer.append("~");
            }
            buffer.append(element.getCodeDescText());
            buffer.append("~" + element.getUniverseCode());
        }
        return buffer.toString();
    }
    
    private String getTildaStringFromDataTypeList(List dataTypeItems) {

        if (dataTypeItems == null)
            return "";

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < dataTypeItems.size(); i++) {
            StandardBenefitDatatypeBO element = (StandardBenefitDatatypeBO) dataTypeItems
                    .get(i);
            if (i != 0) {
                buffer.append("~");
            }
            buffer.append(element.getDataTypeName());
            buffer.append("~" + element.getSelectedItemCode());

        }
        return buffer.toString();
    }
    
    public String saveRuleInfo(){
        SaveRuleInformationRequest saveRuleInformationRequest = (SaveRuleInformationRequest) this
        .getServiceRequest(ServiceManager.SAVE_RULEID_CONTRACTBEN_REQUEST);
        
        List validationMessages = new ArrayList();
        
        //checks for mandatory fields...  have to make it as a single method
        if(!isValid()){
            validationMessages.add(new ErrorMessage(
                    WebConstants.MANDATORY_FIELDS_REQUIRED));
		 	addAllMessagesToRequest(validationMessages);
            return "";
        }
        
        StringTokenizer tokenizer = new StringTokenizer(this.ruleType,"~");
     /*   // while(tokenizer.hasMoreTokens()){
        	//this.ruleType = tokenizer.nextToken();
        	this.tempRuleType=tokenizer.nextToken();
       // }*/
       
        
        //rule id is default
        if(!(this.defaultRuleDesc==null ||"".equals(this.defaultRuleDesc))){	
        if(!this.benefitMeaning.equalsIgnoreCase(this.benefitId) && this.ruleType.equals(this.defaultRuleDesc+'~'+this.defaultRuleID)){
			validationMessages.add(new ErrorMessage("contract.rule.default.benefitmeaning"));
			addAllMessagesToRequest(validationMessages);
			return "";
        }
       }
        //benefit meaning is default    
        if(!(this.defaultRuleDesc==null ||"".equals(this.defaultRuleDesc))){
        if( !this.ruleType.equalsIgnoreCase(this.defaultRuleDesc+'~'+this.defaultRuleID) && this.benefitMeaning.equals(this.benefitId)){
			validationMessages.add(new ErrorMessage("contract.rule.default.ruleid"));
			addAllMessagesToRequest(validationMessages);
			return "";
        }
        }
        
        if(this.validateRuleId()){
			validationMessages.add(new ErrorMessage("contract.rule.validate"));	        	
			addAllMessagesToRequest(validationMessages);
			return "";
        }
	     while(tokenizer.hasMoreTokens()){
	     	 tokenizer.nextToken();
	         String ruleCode = tokenizer.nextToken();
	         saveRuleInformationRequest.setRuleId(ruleCode);
	     }      

        //saveRuleInformationRequest.setRuleId("BADS");
        saveRuleInformationRequest.setBenefitMeaning(this.benefitMeaning.trim().toUpperCase());
        //saveRuleInformationRequest.setBenefitMeaning("deeee");
        saveRuleInformationRequest.setBenefitId(getContractSession().getBenefitId());
        saveRuleInformationRequest.setBenefitComponentId(getBenefitComponentIdFromSession());
        saveRuleInformationRequest.setDateSegmentId(getContractKeyObject().getDateSegmentId());
        saveRuleInformationRequest.setLastUpdatesUser(this.lastUpdatedUser);
        SaveRuleInformationResponse saveRuleInformationResponse  = (SaveRuleInformationResponse) this
        .executeService(saveRuleInformationRequest);
        this.action = 3;
        displayStandardBenefitGeneralInfo();
        getRequest().setAttribute("RETAIN_Value", "");
    	return "";
    }

    private boolean validateRuleId(){
    	if(!this.benefitMeaning.equals(this.benefitMeaningFetched)){
    		if(this.ruleType.equals(this.ruleIDFetched)){    			
    			return true;
    		}else{
    			return false;
    		}
    	}
    	return false;
    }
    /**
	 * 
	 * @param contractVO
	 * @return contractVO
	 */
    protected ContractVO getContractFromSession(
            ContractVO contractVO)  {
    	
        contractVO.setContractSysId(70);
        contractVO.setContractId("B093");
        contractVO.setStatus("NEW");
        contractVO.setVersion(0);
       
	        return contractVO;
	        
   }
    
    /**
     * @return pageLoadBasedOnContractType
     * 
     * Returns the pageLoadBasedOnContractType.
     */
    public String getPageLoadBasedOnContractType() {
        pageLoadBasedOnContractType = getContractSession().getContractKeyObject().contractType;
        return pageLoadBasedOnContractType;
    }
    /**
     * @param pageLoadBasedOnContractType
     * 
     * Sets the pageLoadBasedOnContractType.
     */
    public void setPageLoadBasedOnContractType(
            String pageLoadBasedOnContractType) {
        this.pageLoadBasedOnContractType = pageLoadBasedOnContractType;
    }
    /**
     * Returns the valueForPrint
     * @return String valueForPrint.
     */
    public String getValueForPrint() {
        this.displayStandardBenefitGeneralInfo();
        return valueForPrint;
    }
    /**
     * Sets the valueForPrint
     * @param valueForPrint.
     */
    public void setValueForPrint(String valueForPrint) {
        this.valueForPrint = valueForPrint;
    }
   
    /**
     * Returns the businessEntity
     * @return String businessEntity.
     */
    public String getBusinessEntity() {
        return businessEntity;
    }
    /**
     * Sets the businessEntity
     * @param businessEntity.
     */
    public void setBusinessEntity(String businessEntity) {
        this.businessEntity = businessEntity;
    }
    /**
     * Returns the businessGroup
     * @return String businessGroup.
     */
    public String getBusinessGroup() {
        return businessGroup;
    }
    /**
     * Sets the businessGroup
     * @param businessGroup.
     */
    public void setBusinessGroup(String businessGroup) {
        this.businessGroup = businessGroup;
    }
    /**
     * Returns the createdTimestamp
     * @return String createdTimestamp.
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
     * @return String lastUpdatedTimestamp.
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
     * Returns the dataType
     * @return String dataType.
     */
    public String getDataType() {
        return dataType;
    }
    /**
     * Sets the dataType
     * @param dataType.
     */
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
    /**
     * Returns the description
     * @return String description.
     */
    public String getBlzeRuleType() {
		return blzeRuleType;
	}
	/**
	 * @param blzeRuleType The blzeRuleType to set.
	 */
	public void setBlzeRuleType(String blzeRuleType) {
		this.blzeRuleType = blzeRuleType;
	}
    
    
    
    public String getDescription() {
        return description;
    }
    /**
     * Sets the description
     * @param description.
     */
    public void setDescription(String description) {
        this.description = description;
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
     * Returns the lob
     * @return String lob.
     */
    public String getLob() {
        return lob;
    }
    /**
     * Sets the lob
     * @param lob.
     */
    public void setLob(String lob) {
        this.lob = lob;
    }
    /**
     * Returns the minorHeading
     * @return String minorHeading.
     */
    public String getMinorHeading() {
        return minorHeading;
    }
    /**
     * Sets the minorHeading
     * @param minorHeading.
     */
    public void setMinorHeading(String minorHeading) {
        this.minorHeading = minorHeading;
    }
    /**
     * Returns the providerArrangement
     * @return String providerArrangement.
     */
    public String getProviderArrangement() {
        return providerArrangement;
    }
    /**
     * Sets the providerArrangement
     * @param providerArrangement.
     */
    public void setProviderArrangement(String providerArrangement) {
        this.providerArrangement = providerArrangement;
    }
    /**
     * Returns the qualifier
     * @return String qualifier.
     */
    public String getQualifier() {
        return qualifier;
    }
    /**
     * Sets the qualifier
     * @param qualifier.
     */
    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }
    /**
     * Returns the term
     * @return String term.
     */
    public String getTerm() {
        return term;
    }
    /**
     * Sets the term
     * @param term.
     */
    public void setTerm(String term) {
        this.term = term;
    }
    
	/**
	 * @return Returns the benefitId.
	 */
	public String getBenefitId() {
		return benefitId;
	}
	/**
	 * @param benefitId The benefitId to set.
	 */
	public void setBenefitId(String benefitId) {
		this.benefitId = benefitId;
	}
	/**
	 * @return Returns the ruleType.
	 */
	public String getRuleType() {
		return ruleType;
	}
	/**
	 * @param ruleType The ruleType to set.
	 */
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	/**
	 * @return Returns the benefitMeaning.
	 */
	public String getBenefitMeaning() {
		return benefitMeaning;
	}
	/**
	 * @param benefitMeaning The benefitMeaning to set.
	 */
	public void setBenefitMeaning(String benefitMeaning) {
		this.benefitMeaning = benefitMeaning;
	}
	/**
	 * @return Returns the benefitMeaningFetched.
	 */
	public String getBenefitMeaningFetched() {
		return benefitMeaningFetched;
	}
	/**
	 * @param benefitMeaningFetched The benefitMeaningFetched to set.
	 */
	public void setBenefitMeaningFetched(String benefitMeaningFetched) {
		this.benefitMeaningFetched = benefitMeaningFetched;
	}
	/**
	 * @return Returns the ruleIDFetched.
	 */
	public String getRuleIDFetched() {
		return ruleIDFetched;
	}
	/**
	 * @param ruleIDFetched The ruleIDFetched to set.
	 */
	public void setRuleIDFetched(String ruleIDFetched) {
		this.ruleIDFetched = ruleIDFetched;
	}
	/**
	 * @return Returns the ruleInfoChanged.
	 */
	public String getRuleInfoChanged() {
		return ruleInfoChanged;
	}
	/**
	 * @param ruleInfoChanged The ruleInfoChanged to set.
	 */
	public void setRuleInfoChanged(String ruleInfoChanged) {
		this.ruleInfoChanged = ruleInfoChanged;
	}
	/**
	 * Returns the defaultRuleDesc
	 * @return String defaultRuleDesc.
	 */
	public String getDefaultRuleDesc() {
		return defaultRuleDesc;
	}
	/**
	 * Sets the defaultRuleDesc
	 * @param defaultRuleDesc.
	 */
	public void setDefaultRuleDesc(String defaultRuleDesc) {
		this.defaultRuleDesc = defaultRuleDesc;
	}
	/**
	 * Returns the defaultRuleID
	 * @return String defaultRuleID.
	 */
	public String getDefaultRuleID() {
		return defaultRuleID;
	}
	/**
	 * Sets the defaultRuleID
	 * @param defaultRuleID.
	 */
	public void setDefaultRuleID(String defaultRuleID) {
		this.defaultRuleID = defaultRuleID;
	}
    /**
     * Returns the requiredBenefitMeaning
     * @return boolean requiredBenefitMeaning.
     */
    public boolean isRequiredBenefitMeaning() {
        return requiredBenefitMeaning;
    }
    /**
     * Sets the requiredBenefitMeaning
     * @param requiredBenefitMeaning.
     */
    public void setRequiredBenefitMeaning(boolean requiredBenefitMeaning) {
        this.requiredBenefitMeaning = requiredBenefitMeaning;
    }
    /**
     * Returns the requiredBenefitRule
     * @return boolean requiredBenefitRule.
     */
    public boolean isRequiredBenefitRule() {
        return requiredBenefitRule;
    }
    /**
     * Sets the requiredBenefitRule
     * @param requiredBenefitRule.
     */
    public void setRequiredBenefitRule(boolean requiredBenefitRule) {
        this.requiredBenefitRule = requiredBenefitRule;
    }
    
//  checks for the validation of the fields in the page	
    public boolean isValid(){
       
        boolean requiredField = false;
        this.requiredBenefitMeaning = false;
        this.requiredBenefitRule = false;
       
      
        
        
        if (this.benefitMeaning == null || "".equals(this.benefitMeaning.trim())) {
		 	this.requiredBenefitMeaning=true;
		 	requiredField=true;
           
		 }
        
        if (this.ruleType == null || "".equals(this.ruleType.trim())) {
		 	this.requiredBenefitRule=true;
		 	requiredField=true;
           
		 }
       
		if(requiredField){
			
			return false;
		}
	
		
		return true;
	}
    /**
     * Returns the tempRuleType
     * @return String tempRuleType.
     */
    public String getTempRuleType() {
        return tempRuleType;
    }
    /**
     * Sets the tempRuleType
     * @param tempRuleType.
     */
    public void setTempRuleType(String tempRuleType) {
        this.tempRuleType = tempRuleType;
    }
    
    /**
     * Returns the benefitType
     * @return String benefitType.
     */
    public String getBenefitType() {
        return benefitType;
    }
    /**
     * Sets the benefitType
     * @param benefitType.
     */
    public void setBenefitType(String benefitType) {
        this.benefitType = benefitType;
    }
	/**
	 * @return Returns the benefitCategory.
	 */
	public String getBenefitCategory() {
		return benefitCategory;
	}
	/**
	 * @param benefitCategory The benefitCategory to set.
	 */
	public void setBenefitCategory(String benefitCategory) {
		this.benefitCategory = benefitCategory;
	}
	
	/**
	 * @return Returns the benefitCategoryDesc.
	 */
	public String getBenefitCategoryDesc() {
		return benefitCategoryDesc;
	}
	/**
	 * @param benefitCategoryDesc The benefitCategoryDesc to set.
	 */
	public void setBenefitCategoryDesc(String benefitCategoryDesc) {
		this.benefitCategoryDesc = benefitCategoryDesc;
	}
	/**
	 * @return Returns the benefitVersion.
	 */
	public int getBenefitVersion() {
		return benefitVersion;
	}
	/**
	 * @param benefitVersion The benefitVersion to set.
	 */
	public void setBenefitVersion(int benefitVersion) {
		this.benefitVersion = benefitVersion;
	}

	public String getTierDefinition() {
		return tierDefinition;
	}

	public void setTierDefinition(String tierDefinition) {
		this.tierDefinition = tierDefinition;
	}
	/**
	 * @return Returns the tierDefinitionForprint.
	 */
	public String getTierDefinitionForprint() {
		return tierDefinitionForprint;
	}
	/**
	 * @param tierDefinitionForprint The tierDefinitionForprint to set.
	 */
	public void setTierDefinitionForprint(String tierDefinitionForprint) {
		this.tierDefinitionForprint = tierDefinitionForprint;
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
