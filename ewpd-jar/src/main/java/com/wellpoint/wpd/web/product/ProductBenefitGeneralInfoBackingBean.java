/*
 * ProductBenefitGeneralInfoBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.product;

import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.product.bo.ProductRuleIdBO;
import com.wellpoint.wpd.common.product.request.CheckProductTierDefnRequest;
import com.wellpoint.wpd.common.product.request.RetrieveProductBenefitRequest;
import com.wellpoint.wpd.common.product.request.RetrieveProductTierDefnRequest;
import com.wellpoint.wpd.common.product.request.UpdateProductBenefitGeneralInformationRequest;
import com.wellpoint.wpd.common.product.response.ProductBenefitTierValidationResponse;
import com.wellpoint.wpd.common.product.response.RetrieveProductBenefitResponse;
import com.wellpoint.wpd.common.product.response.RetrieveProductTierDefnResponse;
import com.wellpoint.wpd.common.product.response.UpdateProductBenefitGeneralInformationResponse;
import com.wellpoint.wpd.common.product.vo.ProductBenefitGeneralInformationVO;
import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitBO;
import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitDatatypeBO;
import com.wellpoint.wpd.common.standardbenefit.bo.UniverseBO;
import com.wellpoint.wpd.common.standardbenefit.request.TierLookUpRequest;
import com.wellpoint.wpd.common.standardbenefit.response.TierLookUpResponse;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductBenefitGeneralInfoBackingBean extends ProductBackingBean {	
	
	   	private String businessDomain;

	    private String effectiveDate;

	    private String expiryDate;
	    
	    private String term;
	    
	    private String qualifier;
	    
	    private String providerArrangement;
	    
	    private String dataType;

	    private String name;

	    private String description;

	    private String createdBy;

	    private Date creationDate;

	    private String updatedBy;

	    private Date updationDate;

	    private String lineOfBusiness;

	    private String businessEntity;

	    private String businessGroup;
	    
	    private String varPageLoad;
	    
	    private String printValue;
	    
	    private String dummyVar;
	    
	    private String loadViewForPrint;
	    
	    private String benefitType;
	    
	    private String benefitCategory;
	    
	    private String mandateType;
	    
	    private String stateId;   
	    
	    private String ruleId;
	    
	    private String stateDesc;
	    
	    private String productType;
	    
	    private String printBreadCrumbText;
	    
	    // CR added version
	    
	    private int bnftVersion;
	    
	    private String tier;
	    
	    private List tierLookUpList;
	    
	    private String tierDefinitionsInPopup;
	    
	    private List tierCodeList;
	    //CARS START
	    private String marketBusinessUnit;
	    //CARS END
	    private boolean requiredRule = false;
	    
	    private String rule;
	    
	    private String strRuleType;
	    
	    private List validationMessages;
	    
	  //ICD10 --getting BC ID from session
	    private int benefitCompntId;
		
		/**
		 * @return Returns the requiredRule.
		 */
		public boolean isRequiredRule() {
			return requiredRule;
		}
		/**
		 * @param requiredRule The requiredRule to set.
		 */
		public void setRequiredRule(boolean requiredRule) {
			this.requiredRule = requiredRule;
		}
		/**
		 * @return Returns the rule.
		 */
		public String getRule() {
			return rule;
		}
		/**
		 * @param rule The rule to set.
		 */
		public void setRule(String rule) {
			this.rule = rule;
		}
		/**
		 * @return Returns the strRuleType.
		 */
		public String getStrRuleType() {
			return strRuleType;
		}
		/**
		 * @param strRuleType The strRuleType to set.
		 */
		public void setStrRuleType(String strRuleType) {
			this.strRuleType = strRuleType;
		}
		/**
		 * @return Returns the loadViewForPrint.
		 */
		public String getLoadViewForPrint() {

	        RetrieveProductBenefitRequest retrieveProductBenefitRequest = 
	        	(RetrieveProductBenefitRequest) this
	                .getServiceRequest(ServiceManager.RETRIEVE_PRODUCT_BENEFIT);
	        retrieveProductBenefitRequest
	                .setAction(RetrieveProductBenefitRequest.RETRIEVE_GENERAL_INFO);
	       /* retrieveProductBenefitRequest.setBenefitKey(getProductSessionObject()
	                .getBenefitId());*/	        
	        int productId = getProductSessionObject().getProductKeyObject().getProductId();	
	        int benefitComponentId = getProductSessionObject().getBenefitComponentId();	
	        int benefitId = getProductSessionObject().getBenefitId();
	        
	        ProductBenefitGeneralInformationVO productBnftGenInfoVO = new 
				ProductBenefitGeneralInformationVO();        
	        productBnftGenInfoVO.setProductId(productId);             	
	        productBnftGenInfoVO.setBenefitComponentId(benefitComponentId);
	        productBnftGenInfoVO.setBenefitId(benefitId);	        
	        retrieveProductBenefitRequest.setProductBenefitGeneralInformationVO(productBnftGenInfoVO);
	        
	        RetrieveProductBenefitResponse retrieveProductBenefitResponse = 
	            (RetrieveProductBenefitResponse) executeService(retrieveProductBenefitRequest);
	        if (retrieveProductBenefitResponse != null) {
	            setValuesToBackingBean(retrieveProductBenefitResponse);
	        }
	        setTier(getTierDefnInProduct());
	        return null;
		}
		/**
		 * @param loadViewForPrint The loadViewForPrint to set.
		 */
		public void setLoadViewForPrint(String loadViewForPrint) {
			this.loadViewForPrint = loadViewForPrint;
		}
        /**
         * @return Returns the printValue.
         */
		public String getPrintValue() {
			String requestForPrint = getRequest().getAttribute("printValueForGenInfo").toString();
			if (null != requestForPrint && !requestForPrint.equals("")) {
				printValue = requestForPrint;
			} else {
				printValue = "";
			}
			return printValue;
		}

        /**
         * @param printValue The printValue to set.
         */
        public void setPrintValue(String printValue) {
            this.printValue = printValue;
        }
	    public ProductBenefitGeneralInfoBackingBean() {
	    	if(getProductSessionObject().getMode() == ProductSessionObject.VIEW_MODE){
	    		this.setBreadCumbTextForView();
	    	}else{
	    		this.setBreadCumbTextForEdit();
	    	}
	    	if(0 != getBenefitComponentIdFromSession()){
	    		this.benefitCompntId =  getBenefitComponentIdFromSession();
	    	}
	    }
	    
    /**
     * This method fetches the Benefit General Information for the Product using the benefit key.     	
     * @return String
     */

    public String getProductBenefitGenaralInfo() {
        RetrieveProductBenefitRequest retrieveProductBenefitRequest = (RetrieveProductBenefitRequest) this
                .getServiceRequest(ServiceManager.RETRIEVE_PRODUCT_BENEFIT);
        retrieveProductBenefitRequest
                .setAction(RetrieveProductBenefitRequest.RETRIEVE_GENERAL_INFO);
        retrieveProductBenefitRequest.setBenefitKey(getProductSessionObject()
                .getBenefitId());
        
        int productId = getProductSessionObject().getProductKeyObject().getProductId();	
        int benefitComponentId = getProductSessionObject().getBenefitComponentId();	
        int benefitId = getProductSessionObject().getBenefitId();
        
        ProductBenefitGeneralInformationVO productBnftGenInfoVO = new ProductBenefitGeneralInformationVO();        
        productBnftGenInfoVO.setProductId(productId);             	
        productBnftGenInfoVO.setBenefitComponentId(benefitComponentId);
        productBnftGenInfoVO.setBenefitId(benefitId);
        
        retrieveProductBenefitRequest.setProductBenefitGeneralInformationVO(productBnftGenInfoVO);
        
        RetrieveProductBenefitResponse retrieveProductBenefitResponse = 
            (RetrieveProductBenefitResponse) executeService(retrieveProductBenefitRequest);
        this.productType = super.getProductTypeFromSession();
        if (retrieveProductBenefitResponse != null) {
            setValuesToBackingBean(retrieveProductBenefitResponse);
        }
        setTier(getTierDefnInProduct());
        if (getProductSessionObject().getMode() == ProductSessionObject.VIEW_MODE) {
            return "productBenefitgeneralinfoview";
        } else {
            return "productBenefitgeneralinfo";
        }
    }
    
    /**
     * This method fetches the Benefit General Information for the Product using the benefit key
     * @param   RetrieveProductBenefitResponse    	
     */
    public void setValuesToBackingBean(RetrieveProductBenefitResponse response) {
        StandardBenefitBO standardBenefitBO = response.getStandardBenefitBO();
        DomainDetail domainDetail = response.getDomainDetail();
        if (domainDetail != null) {
            this.lineOfBusiness = WPDStringUtil.getTildaString(domainDetail
                    .getLineOfBusiness());
            this.businessEntity = WPDStringUtil.getTildaString(domainDetail
                    .getBusinessEntity());
            this.businessGroup = WPDStringUtil
                    .getTildaString(domainDetail.getBusinessGroup());
            //CARS START
            this.marketBusinessUnit = WPDStringUtil
            .getTildaString(domainDetail.getMarketBusinessUnit());
            //CATS END
        }

        this.description = standardBenefitBO.getDescription();
        this.name = standardBenefitBO.getBenefitIdentifier();
        List terms = response.getStandardBenefitBO().getTermList();
        this.term = getTildaStringFromUniverseList(terms);
        List qualifiers = response.getStandardBenefitBO().getQualifierList();
        this.qualifier = getTildaStringFromUniverseList(qualifiers);
        List pva = response.getStandardBenefitBO().getPVAList();
        this.providerArrangement = getTildaStringFromUniverseList(pva);
        List dataType = response.getStandardBenefitBO().getDataTypeList();
        this.dataType = getTildaStringFromDataTypeList(dataType);
        this.createdBy = response.getStandardBenefitBO().getCreatedUser();
        Date createdDate = response.getStandardBenefitBO()
                .getCreatedTimestamp();
        if (createdDate != null) {
            this.creationDate = createdDate;
        }
        this.updatedBy = response.getStandardBenefitBO().getLastUpdatedUser();
        Date updatedDate = response.getStandardBenefitBO()
                .getLastUpdatedTimestamp();
        if (updatedDate != null) {
            this.updationDate = updatedDate;
        }        
        //new fields for enhancement        
        this.benefitType = standardBenefitBO.getBenefitType();
        this.benefitCategory = standardBenefitBO.getBenefitCategoryDesc();
        this.mandateType = standardBenefitBO.getMandateType();
        this.stateId = standardBenefitBO.getStateCode();
        this.stateDesc = standardBenefitBO.getStateDesc();
        List ruleList= standardBenefitBO.getRuleIdList();
       
        if(null!=ruleList && ruleList.size()>0){  
         ProductRuleIdBO productRuleIdBO = (ProductRuleIdBO)ruleList.get(0);	         	
	     this.setStrRuleType(productRuleIdBO.getRuleType());
	     this.setRule(getTildaStringFromRuleTypeList(ruleList));
	     this.ruleId = productRuleIdBO.getRuleId()+"-"+productRuleIdBO.getRuleDesc();
        }
         // CR added version 
         this.bnftVersion = standardBenefitBO.getVersion();
    }
	    //this method will be removed to util.
    public static String getTildaStringFromDataTypeList(List dataTypeItems) {

        if (dataTypeItems == null)
            return "";

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < dataTypeItems.size(); i++) {
            StandardBenefitDatatypeBO element = (StandardBenefitDatatypeBO) dataTypeItems
                    .get(i);
            if (i != 0) {
                buffer.append("~");
            }
            buffer.append(element.getSelectedItemCode());
            buffer.append("~" + element.getDataTypeName());
        }
        return buffer.toString();
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
	    
	/**
     * Returns the businessDomain
     * 
     * @return String businessDomain.
     */

	public String getBusinessDomain() {
		return businessDomain;
	}
	/**
	 * Sets the businessDomain
	 * @param businessDomain.
	 */

	public void setBusinessDomain(String businessDomain) {
		this.businessDomain = businessDomain;
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
	 * Returns the lineOfBusiness
	 * @return String lineOfBusiness.
	 */

	public String getLineOfBusiness() {
		return lineOfBusiness;
	}
	/**
	 * Sets the lineOfBusiness
	 * @param lineOfBusiness.
	 */

	public void setLineOfBusiness(String lineOfBusiness) {
		this.lineOfBusiness = lineOfBusiness;
	}
	/**
	 * Returns the name
	 * @return String name.
	 */

	public String getName() {
		return name;
	}
	/**
	 * Sets the name
	 * @param name.
	 */

	public void setName(String name) {
		this.name = name;
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
	 * Returns the varPageLoad
	 * @return String varPageLoad.
	 */

	public String getVarPageLoad() {
		return varPageLoad;
	}
	/**
	 * Sets the varPageLoad
	 * @param varPageLoad.
	 */

	public void setVarPageLoad(String varPageLoad) {
		this.varPageLoad = varPageLoad;
	}
 
	/**
	 * Returns the dummyVar
	 * @return String dummyVar.
	 */

	public String getDummyVar() {
		return dummyVar;
	}
	/**
	 * Sets the dummyVar
	 * @param dummyVar.
	 */

	public void setDummyVar(String dummyVar) {
		this.dummyVar = dummyVar;
	}
	
    
		/**
		 * @return Returns the benefitType.
		 */
		public String getBenefitType() {
			return benefitType;
		}
		/**
		 * @param benefitType The benefitType to set.
		 */
		public void setBenefitType(String benefitType) {
			this.benefitType = benefitType;
		}
		/**
		 * @return Returns the mandateType.
		 */
		public String getMandateType() {
			return mandateType;
		}
		/**
		 * @param mandateType The mandateType to set.
		 */
		public void setMandateType(String mandateType) {
			this.mandateType = mandateType;
		}
		/**
		 * @return Returns the ruleId.
		 */
		public String getRuleId() {
			return ruleId;
		}
		/**
		 * @param ruleId The ruleId to set.
		 */
		public void setRuleId(String ruleId) {
			this.ruleId = ruleId;
		}
		/**
		 * @return Returns the stateDesc.
		 */
		public String getStateDesc() {
			return stateDesc;
		}
		/**
		 * @param stateDesc The stateDesc to set.
		 */
		public void setStateDesc(String stateDesc) {
			this.stateDesc = stateDesc;
		}
		/**
		 * @return Returns the stateId.
		 */
		public String getStateId() {
			return stateId;
		}
		/**
		 * @param stateId The stateId to set.
		 */
		public void setStateId(String stateId) {
			this.stateId = stateId;
		}
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
        /**
         * @return printBreadCrumbText
         * 
         * Returns the printBreadCrumbText.
         */
        public String getPrintBreadCrumbText() {
            printBreadCrumbText = "Product Configuration >> Product ("+getProductSessionObject().getProductKeyObject().getProductName()+") >> Print";
            return printBreadCrumbText;
        }
        /**
         * @param printBreadCrumbText
         * 
         * Sets the printBreadCrumbText.
         */
        public void setPrintBreadCrumbText(String printBreadCrumbText) {
            this.printBreadCrumbText = printBreadCrumbText;
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
		 * @return Returns the bnftVersion.
		 */
		public int getBnftVersion() {
			return bnftVersion;
		}
		/**
		 * @param bnftVersion The bnftVersion to set.
		 */
		public void setBnftVersion(int bnftVersion) {
			this.bnftVersion = bnftVersion;
		}
		
		
		/**
		 * The method will validate whether an already used Tier Definition
		 * is tried to remove by the User.
		 * This is invoked when the SAve button is clicked from 
		 * the Product Benefit General Information page
		 * @return
		 */
		private ProductBenefitTierValidationResponse getProductBenefitTierValidationResponse() {	
		    this.tierCodeList = WPDStringUtil.getListFromTildaString(
                    this.getTier(), 2, 2, 2);        
            List intList = convertStringToIntList(tierCodeList); 
            ProductBenefitGeneralInformationVO productTierDefinitionVO = new ProductBenefitGeneralInformationVO();
            productTierDefinitionVO.setTierDefinitionsList(intList); 
            int productId = getProductSessionObject().getProductKeyObject().getProductId();	
            productTierDefinitionVO.setProductId(productId); 
            int benefitComponentId = getProductSessionObject().getBenefitComponentId();		
            productTierDefinitionVO.setBenefitComponentId(benefitComponentId);             
            int productStructureId = getProductSessionObject().getProductStructKey();
            productTierDefinitionVO.setProductStructureId(productStructureId);
            int benefitId = getProductSessionObject().getBenefitId();
            productTierDefinitionVO.setBenefitId(benefitId);
		    CheckProductTierDefnRequest checkProductTierDefnRequest = 
		        (CheckProductTierDefnRequest) this
            .getServiceRequest(ServiceManager.CHECK_PRODUCT_BENEFIT_TIER);	
		    checkProductTierDefnRequest.setProductTierDefinitionVO(productTierDefinitionVO);
		    ProductBenefitTierValidationResponse checkProductTierDefnResponse = 
		        (ProductBenefitTierValidationResponse) executeService(checkProductTierDefnRequest);		    
		    return checkProductTierDefnResponse;
	   }
		
		/**
		 * The method will fetch the Tier Definitions overridden by the Product
		 * from the PROD_TIER_DEFN_OVRD table
		 * @return
		 */
		public String getTierDefnInProduct() {		    
		    RetrieveProductTierDefnRequest retrieveProductTierDefnRequest = 
		        (RetrieveProductTierDefnRequest) this
            .getServiceRequest(ServiceManager.RETRIEVE_PRODUCT_BENEFIT_TIER);
		    
		    int productId = getProductSessionObject().getProductKeyObject().getProductId();			    
		    retrieveProductTierDefnRequest.setProductId(productId);
		    int benefitComponentId = getProductSessionObject().getBenefitComponentId();			    
		    retrieveProductTierDefnRequest.setBenefitComponentId(benefitComponentId);		    
		    int benefitId =  getProductSessionObject().getBenefitId();			   
		    retrieveProductTierDefnRequest.setBenefitId(benefitId);			    
		    RetrieveProductTierDefnResponse retrieveProductTierDefnResponse = 
		        (RetrieveProductTierDefnResponse) executeService(retrieveProductTierDefnRequest);  		    
		    return(retrieveProductTierDefnResponse
                    .getBenefitTierDefinitionVO().getTierDescription());//contains desc and ID  
		   
	    }
        /**
         * @return Returns the tier.
         */
        public String getTier() {
            return tier;
        }
        /**
         * @param tier The tier to set.
         */
        public void setTier(String tier) {
            this.tier = tier;
        }
        /**
         * @return Returns the tierLookUpList.
         */
        public List getTierLookUpList() {
            return tierLookUpList;
        }
        /**
         * @param tierLookUpList The tierLookUpList to set.
         */
        public void setTierLookUpList(List tierLookUpList) {
            this.tierLookUpList = tierLookUpList;
        } 
       
        /**
         * The method will fetch the Benefit Tier definitions
         * to be displayed in the Popup for Tier definitions
         * @return Returns the tierDefinitionsInPopup.
         */
        public String getTierDefinitionsInPopup() {            
            TierLookUpRequest tierLookupRequest = (TierLookUpRequest) 
					this.getServiceRequest(ServiceManager.TIER_LOOKUP_REQUEST);	
            tierLookupRequest.setAction("product");
            /*Benefit Defn Id is taken from the session.It is set when the 
		    Benefit is clicked in the Product Tree.This is set in ProductBenefitDetailBackingBean.*/
            int benefitDefinitionId = ((Integer)getSession()
		            .getAttribute("SESSION_BNFTDEFNID_PRODUCT")).intValue();		    
            tierLookupRequest.setBenefitDefinitionId(benefitDefinitionId);	
            
            TierLookUpResponse tierLookupResponse = (TierLookUpResponse) this
            	.executeService(tierLookupRequest);

            this.tierLookUpList = tierLookupResponse
            	.getTierList();	            
            return WebConstants.EMPTY_STRING;
        }
        /**
         * @param tierDefinitionsInPopup The tierDefinitionsInPopup to set.
         */
        public void setTierDefinitionsInPopup(String tierDefinitionsInPopup) {
            this.tierDefinitionsInPopup = tierDefinitionsInPopup;
        }
        
        /**
         * The method will be invoked when the Save button
         * is clicked from the Product Benefit General Information page
         * @return
         */
        public String saveBenefitGeneralInfo(){  
        	Logger.logInfo("Saving Product Benefit General Information");
        	if (!validateRequiredFields()) { //mandatory check for Rule Field
        	   Logger.logInfo("Saving Product Benefit General Information,Validation " +
        	   		"for mandatory check failed");
               addAllMessagesToRequest(this.validationMessages);
               return "";
            }        	
            ProductBenefitTierValidationResponse productBenefitTierValidnResponse 
            								= getProductBenefitTierValidationResponse();
            if(!productBenefitTierValidnResponse.isValidationSuccess()){
            	Logger.logDebug("Saving Product Benefit General Information," +
            				"Validation for removing tier definitions for which tiers exist failed");
		        List messages = productBenefitTierValidnResponse.getMessages();
		    	addAllMessagesToRequest(messages);  
		    }else{
		    	Logger.logDebug("Saving Product Benefit General Information," +
				"Validation successful");
		    	Logger.logDebug("tier is" + this.getTier());
	            this.tierCodeList = WPDStringUtil.getListFromTildaString(
	                    this.getTier(), 2, 2, 2);        
	            List intList = convertStringToIntList(tierCodeList);    
	            UpdateProductBenefitGeneralInformationRequest updatePdktBnftGenInformationRequest = 
	                getUpdatePdktBnftGenInformationRequest(intList);             
	            UpdateProductBenefitGeneralInformationResponse updatePdktBnftGenInformationResponse = 
	                (UpdateProductBenefitGeneralInformationResponse) executeService
	                	(updatePdktBnftGenInformationRequest);  
	            addAllMessagesToRequest(updatePdktBnftGenInformationResponse.getMessages());
	            Logger.logInfo("Exiting from saveBenefitGeneralInfo");
		    } 
            getRequest().setAttribute("RETAIN_Value", ""); 
            return WebConstants.EMPTY_STRING;
        }
        
        /**
         * The method creates a UpdateProductBenefitGeneralInformationRequest object,sets all
         * the necessary parameters to be taken to the service layer , 
         * and returns the same.
         * @param definitionList
         * @return
         */
        private UpdateProductBenefitGeneralInformationRequest getUpdatePdktBnftGenInformationRequest
        (List definitionList) {       
            UpdateProductBenefitGeneralInformationRequest updatePdktBnftGenInformationRequest = 
                (UpdateProductBenefitGeneralInformationRequest) this
                    .getServiceRequest(ServiceManager.UPDATE_PRODUCT_BNFT_GENINFO_REQUEST);
            
            int benefitDefinitionId = ((Integer)getSession()
		            .getAttribute("SESSION_BNFTDEFNID_PRODUCT")).intValue();
            int productId = getProductSessionObject().getProductKeyObject().getProductId();	
            int benefitComponentId = getProductSessionObject().getBenefitComponentId();	
            int benefitId = getProductSessionObject().getBenefitId();
            
            ProductBenefitGeneralInformationVO productBnftGenInfoVO = new 
				ProductBenefitGeneralInformationVO();
            productBnftGenInfoVO.setTierDefinitionsList(definitionList);
            productBnftGenInfoVO.setBenefitDefinitionId(benefitDefinitionId);  
            productBnftGenInfoVO.setProductId(productId);             	
            productBnftGenInfoVO.setBenefitComponentId(benefitComponentId);
            productBnftGenInfoVO.setBenefitId(benefitId);
            String rule = getRule();
            String ruleId = null;
            // rule will be RuleDesc~RuleId
            if(null != rule && !"".equals(rule)){            	
            	String[] ruleDetailArray = rule.split("~"); 
            	ruleId = ruleDetailArray[1];//This will give RuleId
            }
            productBnftGenInfoVO.setRuleId(ruleId);            
            updatePdktBnftGenInformationRequest.setProductBenefitGeneralInformationVO(
            		productBnftGenInfoVO);        
            return updatePdktBnftGenInformationRequest;
        }
        /**
         * @return Returns the tierCodeList.
         */
        public List getTierCodeList() {
            return tierCodeList;
        }
        /**
         * @param tierCodeList The tierCodeList to set.
         */
        public void setTierCodeList(List tierCodeList) {
            this.tierCodeList = tierCodeList;
        }
        
        /**
         * The method converts a List containing String objects 
         * to a List of Integers.
         * @param stringList
         * @return
         */
        private List convertStringToIntList(List stringList){
            List intList = new ArrayList();
            for(int i=0;i<stringList.size();i++){
               String strValue = (String) stringList.get(i);
               int intValue = Integer.parseInt(strValue);
               intList.add(new Integer(intValue));
            }
            return intList;
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
        
        /**
         * The method returns the Rule Desc and Rule Id as a 
         * String separated by ~ from a list containing ProductRuleIdBO objects
         * @param ruleTypeItems
         * @return
         */
        public static String getTildaStringFromRuleTypeList(List ruleTypeItems) {
            if (ruleTypeItems == null)
                return "";
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < ruleTypeItems.size(); i++) {
            	ProductRuleIdBO element = (ProductRuleIdBO) ruleTypeItems.get(i);
                if (i != 0) {
                    buffer.append("~");
                }
                buffer.append(element.getRuleDesc());
                buffer.append("~" + element.getRuleId());
            }
            return buffer.toString();
        }
        
        
        /**
         * The method checks whether the rule field is empty
         * If it is empty ,the validation message is set.
         * @return
         */
        private boolean validateRequiredFields() {
        	validationMessages = new ArrayList();
            if (null == this.rule|| "".equals(this.rule)) {
                requiredRule = true;  
                this.validationMessages.add(new ErrorMessage(
                        WebConstants.MANDATORY_FIELDS_REQUIRED));
                return false;
            }           
            return true;       
        }
       
		/**
		 * @return Returns the validationMessages.
		 */
		public List getValidationMessages() {
			return validationMessages;
		}
		/**
		 * @param validationMessages The validationMessages to set.
		 */
		public void setValidationMessages(List validationMessages) {
			this.validationMessages = validationMessages;
		}
		public int getBenefitCompntId() {
			return benefitCompntId;
		}
		public void setBenefitCompntId(int benefitCompntId) {
			this.benefitCompntId = benefitCompntId;
		}
   }
