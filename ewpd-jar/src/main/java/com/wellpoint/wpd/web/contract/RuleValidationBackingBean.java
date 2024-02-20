/*
 * ContractBasicInfoBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.contract;


import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.common.contract.request.RetreiveContractRuleTypeRequest;
import com.wellpoint.wpd.common.contract.response.RetreiveContractRuleTypeResponse;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.product.bo.RuleDetailBO;
import com.wellpoint.wpd.common.product.request.RetreiveProductRuleTypeRequest;
import com.wellpoint.wpd.common.product.response.RetreiveProductRuleTypeResponse;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.product.ProductBackingBean;
import com.wellpoint.wpd.web.product.ProductSessionObject;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id:
 * 
 * Backing bean for Benefit Rule Type Check in Validation in Contract and Product.
 * Retreives the Benefit Component Rule Types and Benefit Rule Types when Check in Rule Type Validation for 
 * Contract and Product fails.
 */
public class RuleValidationBackingBean extends WPDBackingBean{
	
	private List ruleList; 
	
	private List ruleListForDisplay;	
		
	private String entityName;	
	
	private int version;	
	
	private String loadRuleTypes = WebConstants.EMPTY_STRING; 

	/**
	 * @return Returns the ruleList.
	 */
	public List getRuleList() {
		return ruleList;
	}
	/**
	 * List with BC Rule Types and Benefit Rule Types.
	 * @param ruleList The ruleList to set.
	 */
	public void setRuleList(List ruleList) {
		this.ruleList = ruleList;
	}
	/**
	 * Displays Product Name or Contract Name in JSP
	 * @return Returns the entityName.
	 */
	public String getEntityName() {
		return entityName;
	}
	/**
	 * @param entityName The entityName to set.
	 */
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	/**
	 * @return Returns the version.
	 */
	public int getVersion() {
		return version;
	}
	/**
	 * Displays version of Product or Contract in JSP
	 * @param version The version to set.
	 */
	public void setVersion(int version) {
		this.version = version;
	}
	/** Load the Benefit Component Rule Types and Benefit Rule Types 
	 * on the click click of Check Validation failure of Contract and Product.    
	 * @return Returns the loadRuleTypes.
	 */
	public String getLoadRuleTypes() {
		
		Logger.logInfo("Load the Benefit Component Rule Types and Benefit Rule Types on the click click of Check Validation failure of Contract and Product.    ");
		Logger.logDebug("Entered method getLoadRuleTypes");
		
		String module = getRequest().getParameter(WebConstants.MODULE);
		
		if(module.equals(WebConstants.PROD_TYPE)){
			
			ProductSessionObject productSessionObject = (ProductSessionObject)getSession().getAttribute(ProductBackingBean.PRODUCT_SESSION_KEY);
			if(null != productSessionObject){
				int productSysId = productSessionObject.getProductKeyObject().getProductId();
				entityName = productSessionObject.getProductKeyObject().getProductName();
				version = productSessionObject.getProductKeyObject().getVersion();
				
				ruleList = getProductRuleDetails(productSysId);
			}
		}else if(module.equals(WebConstants.CONTRACT)){
			
			ContractSession contractSessionObject = (ContractSession)getSession().getAttribute(ContractBackingBean.CONTRACT_SESSION_KEY);
			if(null != contractSessionObject){
				entityName = contractSessionObject.contractKeyObject.getContractId();
				version = contractSessionObject.contractKeyObject.getVersion(); 
				ruleList = getContractRuleDetails(entityName);
			}
		}
		
		if(null!=ruleList && !ruleList.isEmpty()){
			prepareListForDisplay(ruleList,module);
		}
		return WebConstants.EMPTY_STRING;
	}
	/**
	 * @param loadRuleTypes The loadRuleTypes to set.
	 */
	public void setLoadRuleTypes(String loadRuleTypes) {
		this.loadRuleTypes = loadRuleTypes;
	}
	
	/**
	 * Retreive the Product Rule Type information.
	 * @param productSysId
	 */
	private List getProductRuleDetails(int productSysId){
		List ruleList = null;
		
		RetreiveProductRuleTypeRequest retreiveProductRuleTypeRequest = (RetreiveProductRuleTypeRequest) this
		.getServiceRequest(ServiceManager.RETRIEVE_PRODUCT_RULE_TYPE);
		retreiveProductRuleTypeRequest.setProductSysId(productSysId);
				
		RetreiveProductRuleTypeResponse retreiveProductRuleTypeResponse = (RetreiveProductRuleTypeResponse)this.
							executeService(retreiveProductRuleTypeRequest);
		
		if(null != retreiveProductRuleTypeResponse){
			ruleList =retreiveProductRuleTypeResponse.getProductRuleList();
			addAllMessagesToRequest(retreiveProductRuleTypeResponse.getMessages());
		}
		
		return ruleList;
	}
	
	/**
	 * Retrives Contract rule type infomration.
	 * @param contractId
	 */
	private List getContractRuleDetails(String contractId){
		List ruleList = null;
		
		RetreiveContractRuleTypeRequest retreiveContractRuleTypeRequest = (RetreiveContractRuleTypeRequest) this
		.getServiceRequest(ServiceManager.RETRIEVE_CONTRACT_RULE_TYPE);
		retreiveContractRuleTypeRequest.setContractId(contractId);
		
		
		RetreiveContractRuleTypeResponse retreiveContractRuleTypeResponse = (RetreiveContractRuleTypeResponse)this.
							executeService(retreiveContractRuleTypeRequest);
		
		if(null != retreiveContractRuleTypeResponse){
			ruleList =retreiveContractRuleTypeResponse.getContractRuleList();
			addAllMessagesToRequest(retreiveContractRuleTypeResponse.getMessages());
		}
		
	
		
		return ruleList;
	}
	/**
	 * @return Returns the ruleListForDisplay.
	 */
	public List getRuleListForDisplay() {
		return ruleListForDisplay;
	}
	/**
	 * List used in valdation popup to display Benefit RUle Types 
	 * @param ruleListForDisplay The ruleListForDisplay to set.
	 */
	public void setRuleListForDisplay(List ruleListForDisplay) {
		this.ruleListForDisplay = ruleListForDisplay;
	}
	
	/**
	 * Method which calls the appropriate function to prepare the Rule List for
	 * Product and Contract.
	 * @param ruleList
	 * @param module
	 */
	public void prepareListForDisplay(List ruleList,String module){
		
		RuleDetailBO bcRuleDetail = null;
		RuleDetailBO benefitRuleDetail = null;
				
		List bcRuleList = (List)ruleList.get(0);
		List benefitRuleList = (List)ruleList.get(1);
		
		if(WebConstants.ENTITY_TYPE_CONTRACT.equals(module)){
			if(null!= bcRuleList && !bcRuleList.isEmpty()){
				if(null!= benefitRuleList && !benefitRuleList.isEmpty()){
					processRuleListContract(bcRuleList, benefitRuleList);
				}
			}
		}else if(WebConstants.PROD_TYPE.equals(module)){
			if(null!= bcRuleList && !bcRuleList.isEmpty()){
				if(null!= benefitRuleList && !benefitRuleList.isEmpty()){
					processRuleListProduct(bcRuleList, benefitRuleList);
				}
			}
		}
	}
	
	/**
	 * This method prepares the Rule List for Product.
	 * @param bcRuleList
	 * @param benefitRuleList
	 */
	public void processRuleListProduct(List bcRuleList, List benefitRuleList){
		
		ruleListForDisplay = new ArrayList();
		for(int bcRuleSize=0; bcRuleSize<bcRuleList.size(); bcRuleSize++){
			
			RuleDetailBO ruleDetails = (RuleDetailBO)bcRuleList.get(bcRuleSize);
			ruleListForDisplay.add(ruleDetails);
						
			for(int benefitRuleSize=0; benefitRuleSize< benefitRuleList.size(); benefitRuleSize++){
				
				RuleDetailBO bnftRuleDetails = (RuleDetailBO)benefitRuleList.get(benefitRuleSize);
				
				if(ruleDetails.getBenefitComponentName().equals(bnftRuleDetails.getBenefitComponentName())){
					bnftRuleDetails.setBenefitComponentName(WebConstants.EMPTY_STRING);
					ruleListForDisplay.add(bnftRuleDetails);
				}
			}
		}
	}
	
	/**
	 * This method prepares the Rule List for Contract. 
	 * @param bcRuleList
	 * @param benefitRuleList
	 */
	public void processRuleListContract(List bcRuleList, List benefitRuleList){
		
		ruleListForDisplay = new ArrayList();
		List ruleList = new ArrayList();
		
		for(int bcSize=0; bcSize<bcRuleList.size(); bcSize++){
			
			List bcRuleDetails = (List)bcRuleList.get(bcSize);
			
			for(int benefitSize = 0; benefitSize < benefitRuleList.size(); benefitSize++){
				
				List benefitRuleDetails = (List)benefitRuleList.get(benefitSize);
				
				if(bcSize == benefitSize){
					for(int bcRuleSize = 0; bcRuleSize < bcRuleDetails.size();bcRuleSize++){
						
						RuleDetailBO ruleDetailBO = (RuleDetailBO)bcRuleDetails.get(bcRuleSize);
						ruleList.add(ruleDetailBO);
						
						for(int benefitRuleSize=0; benefitRuleSize< benefitRuleDetails.size(); benefitRuleSize++){
									
							RuleDetailBO bnftRuleDetails = (RuleDetailBO)benefitRuleDetails.get(benefitRuleSize);
							
							if(ruleDetailBO.getBenefitComponentName().equals(bnftRuleDetails.getBenefitComponentName())){
								bnftRuleDetails.setBenefitComponentName(WebConstants.EMPTY_STRING);
								ruleList.add(bnftRuleDetails);
							}
						}
					}
				}
			}
		}
		
		String oldDateSeg;
		String newDateSeg;
		String oldPrdName;
		String newPrdName;
		
		RuleDetailBO rule = (RuleDetailBO)ruleList.get(0);
		ruleListForDisplay.add(rule);
				
		oldDateSeg = rule.getDateSegment();
		oldPrdName = rule.getProductName();
		
		for(int size=1; size<ruleList.size();size++){
			RuleDetailBO ruleDetail = (RuleDetailBO)ruleList.get(size);
			
			newDateSeg = ruleDetail.getDateSegment();
			newPrdName = ruleDetail.getProductName();
			
			if(oldDateSeg.equals(newDateSeg)){
				ruleDetail.setDateSegment(WebConstants.EMPTY_STRING);
				if(oldPrdName.equals(newPrdName)){
					ruleDetail.setProductName(WebConstants.EMPTY_STRING);
				}
			}
			
			ruleListForDisplay.add(ruleDetail);
			oldDateSeg = newDateSeg;
			oldPrdName = newPrdName;
		}
	}

}
