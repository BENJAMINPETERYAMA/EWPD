/*
 * RuleTypeValidationBuilder.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.common.builder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.wpd.business.common.adapter.RuleTypeValidationAdapterManager;
import com.wellpoint.wpd.business.contract.adapter.ContractAdapterManager;
import com.wellpoint.wpd.business.framework.bo.BusinessObjectManagerFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitHierarchyAssociationBO;
import com.wellpoint.wpd.common.bo.RuleValidationBO;
import com.wellpoint.wpd.common.contract.bo.DateSegment;
import com.wellpoint.wpd.common.contract.bo.ProviderSpecialityCodeBO;
import com.wellpoint.wpd.common.contract.vo.RuleTypeValidateOutputVO;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.util.TimeHandler;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RuleTypeValidationBuilder {
	
	/**
	 * 
	 * @param benefitList
	 * @param benCompId
	 * @return
	 * @throws SevereException
	 * 
	 *  Retrieves the Rule Meta Data (Rule Id,Rule Type and Rule Descriptiion)of Benefits associated with a particular Benefit 
	 *  Component Type and validates whether the Benefits and Benefit 
	 *  Component Typeare of same Rule Type (WPDAUTOBG or BLZWPDAB) .First it checks if the benefits are of same type.If so,
	 *  it checkes the ruleType of benefit component against the Benefits' RuleType. 
	 */
	
	public static Object[] validateBenefitComponentRuleType(List benefitList,int benCompId)
	throws SevereException{
		
		List benefitRulesList=new ArrayList();
		List benCompRuleList=new ArrayList();
		boolean isBenefitRuleTypeSame=true;
		String firstRuleType="";  // String to store the ruleType of firstRule in the ruleList
		Object[] ruleInfo=new Object[2];
		
		
		List benefitIdlist=getBenefitIds(benefitList);
		RuleTypeValidationAdapterManager ruleTypeValidationAdapterManager=new RuleTypeValidationAdapterManager();
		try{
			benefitRulesList=ruleTypeValidationAdapterManager.locateRules(benefitIdlist,"BENEFIT",BusinessConstants.FIND_BENEFIT_RULES);
		}catch(Exception e){
			throw new SevereException(
					"Exception occured in locate Benefit Rules in locateRules method in RuleTypeValidationAdapterManager",
					null,e);
		}
		
		// checking if the retrieved benefit Rule Ids are of same type.
		if(!isListEmptyOrNull(benefitRulesList)){
			RuleValidationBO rulesBO = (RuleValidationBO)benefitRulesList.get(0);
			firstRuleType=rulesBO.getRuleType();
			isBenefitRuleTypeSame=checkRuleTypesSame(benefitRulesList);		
		}
		else{
			throwException();
		}
		
		/*  if all the Benefits are of same Type or if there is only one benefit associated with the Benefit ComponentId
		 the isRuleTypeSame flag will be true.If the flag is True,check whether the BenefitComponent is also of same Type
		 */ 
		
		if(isBenefitRuleTypeSame){
			Logger.logDebug("isRuleTypeSame is true in Benefit level");
			try{
				List benefitCompIdlist=new ArrayList();
				Logger.logDebug("Benefit Component Id="+benCompId);
				benefitCompIdlist.add(new Integer(benCompId));
				benCompRuleList=ruleTypeValidationAdapterManager.locateRules(benefitCompIdlist,"BENEFITCOMP",BusinessConstants.FIND_BENEFIT_RULES);
			}catch(Exception e){
				throw new SevereException(
						"Exception occured in locate Benefit Rules in locateRules method in RuleTypeValidationAdapterManager",
						null,e);
			}
			if(!isListEmptyOrNull(benCompRuleList)){
				//need to iterate the list?? expect only one BO Object??
				RuleValidationBO ruleValBO_ben_comp=(RuleValidationBO)benCompRuleList.get(0);
				Logger.logDebug("Benefit Comp Rule Type=="+ruleValBO_ben_comp.getRuleType());
				if(ruleValBO_ben_comp.getRuleType().equalsIgnoreCase(firstRuleType)){
					Logger.logDebug("isRuleTypeSame is true in Benefit Comp level");
					isBenefitRuleTypeSame=true;
				}
				else{
					Logger.logDebug("isRuleTypeSame is false in Benefit Comp level");
					isBenefitRuleTypeSame=false;
					ruleInfo[0]=BusinessConstants.BNFT_COMP_SHOULD_BE_OF_SAME_RULE_TYPE_AS_BNFTS;
				}
			}
			else{
				throwException();
			}
		}
		else{
			ruleInfo[0]=BusinessConstants.BNFTS_SHOULD_BE_OF_SAME_RULE_TYPE;
		}
		ruleInfo[1]=new Boolean(isBenefitRuleTypeSame);
		Logger.logDebug("isRuleTypeSame=="+isBenefitRuleTypeSame);
		return ruleInfo;
	}
	
	/**
	 * 
	 * @param productKey
	 * @return
	 * @throws SevereException
	 * 
	 *  Retrieves the Rule Meta Data (Rule Id,Rule Type and Rule Descriptiion)of Benefits associated with a particular Product
	 *  and checks of the retieved rules are of same RuleType (WPDAUTOBG or BLZWPDAB)
	 *  
	 */
	public static boolean validateProductRuleTypes(int productKey)
	throws SevereException{
		
		Logger.logDebug("Inside validateProductRuleTypes(productKey) of RuleTypeValidationBuilder");
		List productIdList=new ArrayList();
		List productRules=new ArrayList();
		boolean isProdRuleTypesSame=true;
		
		productIdList.add(new Integer(productKey));
		
		RuleTypeValidationAdapterManager ruleTypeValidationAdapterManager=new RuleTypeValidationAdapterManager();
		try{
			productRules=ruleTypeValidationAdapterManager.locateRules(productIdList,null,BusinessConstants.FIND_PROD_RULES);
		}catch(Exception e){
			throw new SevereException(
					"Exception occured in locate Benefit Rules in locateRules method in RuleTypeValidationAdapterManager",
					null,e);
		}
		/* if productRules is not empty,calls the "common method "checkRuleTypesSame" to 
		check if the all the associated rules of product are of same type.*/
		
		if(!isListEmptyOrNull(productRules)){
			Logger.logDebug("Checking Rule Types for Product starts here");
			isProdRuleTypesSame=checkRuleTypesSame(productRules);
			Logger.logDebug("isProdRuleTypesSame===" + isProdRuleTypesSame);
		}
		else{
			throwException();
		}
		
		return isProdRuleTypesSame;
	}
	
	
	/**
	 * 
	 * @param chekinDateSegmentList
	 * @return ruleTypeValidateOutput
	 * @throws SevereException
	 * 
	 * This method retrieves the rules associated with  a particular dateSegment by calling  the method 
	 * locateRules()in RuleTypeValidationAdapterManager and validates if the rules associated with 
	 * a particular segment are of same type.
	 * The method returns ruleTypeValidateOutput object which holds the dateSegmentList and 
	 * errorMessagesListinstead of errorMessages as part of eWPD Stabilization Release. 
	 * The method is also made non static
	 */
	public RuleTypeValidateOutputVO validateDateSegmentRuleTypes(List  chekinDateSegmentList)
	throws SevereException{
		RuleTypeValidateOutputVO ruleTypeValidateOutput = new RuleTypeValidateOutputVO();
		List dateSegmntIdList=new ArrayList();
		List contractRules=new ArrayList();
		boolean isCntrctRuleTypesSame =true;
		List errorMessages=new ArrayList();
		RuleTypeValidationAdapterManager ruleTypeValidationAdapterManager=new RuleTypeValidationAdapterManager();
		
		
		Logger.logDebug("Inside method --> validateDateSegmentRuleTypes(chekinDateSegmentList)in RuleTypeValidationAdapterManager ");
		
		for(int i=0;i<chekinDateSegmentList.size();i++){
				dateSegmntIdList.clear();
				DateSegment dtSegmnt=(DateSegment)chekinDateSegmentList.get(i);
				dateSegmntIdList.add(new Integer(dtSegmnt.getDateSegmentSysId()));
				Logger.logDebug("DateSegmentID ==="+ i+ "--->  "+dtSegmnt.getDateSegmentSysId() );
				
				// locates Rules associated with a particular datesegmentId
				try{
					contractRules=ruleTypeValidationAdapterManager.locateRules(dateSegmntIdList,null,BusinessConstants.FIND_CONTRCT_RULES);
				}catch(Exception e){
					throw new SevereException(
							"Exception occured in locate DateSegment Rules in locateRules method in RuleTypeValidationAdapterManager",
							null,e);
				}
				if(!isListEmptyOrNull(contractRules)){
					isCntrctRuleTypesSame=checkRuleTypesSame(contractRules); //checks if the retrieved rules are of same type
					if(isCntrctRuleTypesSame){
						
						String ruleType=((RuleValidationBO)contractRules.get(0)).getRuleType();
						Logger.logDebug("Rule Type =  "+ruleType+"  For Data Segment--->" + dtSegmnt.getDateSegmentSysId());
						
						/*
						 * if rules are of sameType,sets the "BlzRuleIndicator" in each dateSegment based on the ruleType
						 */
						if(ruleType.equalsIgnoreCase("BLZWPDAB")){
							((DateSegment)chekinDateSegmentList.get(i)).setBlzRuleIndicator("Y");
						}
						else{
							((DateSegment)chekinDateSegmentList.get(i)).setBlzRuleIndicator("N");
						}
					}	//isCntrctRuleTypesSame
					else{
						break;
					}
				} // isListEmptyOrNull(contractRules)
				else{
					throwException();
				}
		}
		
		if(!isCntrctRuleTypesSame){
			ErrorMessage errorMsg=new ErrorMessage(BusinessConstants.CONTRACT_RULE_TYPE_VALIDATION);
			errorMsg.setLink(BusinessConstants.RULE_TYPE_VALIDATION_LINK_CONTRACT);
			errorMessages.add(errorMsg);
		}
		ruleTypeValidateOutput.setDateSegment(chekinDateSegmentList);
		ruleTypeValidateOutput.setErrorMessages(errorMessages);
		return ruleTypeValidateOutput;
	}
	
	
	/**
	 * 
	 * @param chekinDateSegmentList
	 * @param bom
	 * @param user
	 * @throws SevereException
	 * @throws WPDException
	 * 
	 * This method calls searchDateSegmentLatestVersion method in ContractAdapterManager to retrieve 
	 * the latest version of datesegments to be checked in and sets the BlzRuleIndicator in each retrieved datesegment
	 * and persist.
	 */	
	public static void updateBlzIndicatorInLatestVersion(List chekinDateSegmentList,User user)
		throws SevereException,WPDException{
		SearchResults searchResults= null; 	
		ContractAdapterManager contractAdapterManager=new ContractAdapterManager();
		BusinessObjectManager bom=getBusinessObjectManager();
			for(int i=0;i<chekinDateSegmentList.size();i++){
					DateSegment dtSegmnt=(DateSegment)chekinDateSegmentList.get(i);
					String blzRuleInd=dtSegmnt.getBlzRuleIndicator();
					
				try{
					searchResults=contractAdapterManager.searchDateSegmentLatestVersion(dtSegmnt);
				}catch(Exception e){
					throw new SevereException(
							"Exception occured in locate Latest DateSegment Version",
							null,e);
				}
				List retrievedDateSegments = searchResults.getSearchResults();
				try{
				if(null != retrievedDateSegments && !retrievedDateSegments.isEmpty()){
					DateSegment retievedDateSeg = (DateSegment)retrievedDateSegments.get(0);

					/*Code to retrieve the provider speciality codes while checkin.
					 * If this is not set in the datesegment BO, then this value will become null after checkin
					 */				
					ProviderSpecialityCodeBO providerSpecialityCodeBO = new ProviderSpecialityCodeBO();
					providerSpecialityCodeBO.setDateSegmentSysId(retievedDateSeg.getDateSegmentSysId());
					List providerSpecialityCodesObjectList = contractAdapterManager.getProviderSpecialityCodes(providerSpecialityCodeBO);
					List providerSpecialityCodesStringList = new ArrayList();
					if(providerSpecialityCodesObjectList!=null && providerSpecialityCodesObjectList.size()>0){
						Iterator iter = providerSpecialityCodesObjectList.iterator();
						while(iter.hasNext()){
							providerSpecialityCodeBO = (ProviderSpecialityCodeBO)iter.next();
							providerSpecialityCodesStringList.add(providerSpecialityCodeBO.getSpecialityCode().toString());
						}
					}
					retievedDateSeg.setProviderSpecCodeList(providerSpecialityCodesStringList);
					
					retievedDateSeg.setBlzRuleIndicator(blzRuleInd);
					bom.persist(retievedDateSeg,user,false);
				}
				}catch(Exception e){
					throw new SevereException(
							"Exception occured in Persisting data segments in updateBlzIndicatorInLatestVersion method in RuleTypeValidationBuilder",
							null,e);
				}
			}
			
		}
	/**
	 * 
	 * @param ruleBOList
	 * @return true if all the items(benefits/products/contract) in the input list are of same RuleType,
	 * else returns false. 
	 */
	
	public static boolean checkRuleTypesSame(List ruleBOList){
		String firstRuleType="";
		boolean isRuleTypeSame=true;
		
		RuleValidationBO rulesBO = (RuleValidationBO)ruleBOList.get(0);
		firstRuleType=rulesBO.getRuleType();
		
		Logger.logDebug("Inside checkRuleTypesSame(ruleBOList) method in RuleTypeValidationBuilder:  firstRuleType = "+ firstRuleType);
		
		if(ruleBOList.size()==1){
			Logger.logDebug("RuleList size is one,So no comparsion Required since there is only one benefit");
			isRuleTypeSame=true;
		}
		else{
			Logger.logDebug("inside else loop to compare benefit Rule Type");					
			Logger.logDebug("firstRuleType=="+ firstRuleType);
			for (int i=1;i<ruleBOList.size();i++){
				
				RuleValidationBO rulesBO_i = (RuleValidationBO)ruleBOList.get(i);
				String ruleType_i=rulesBO_i.getRuleType();
				Logger.logDebug("in the loop ruleType_i=="+ i+ "  "+ruleType_i);
				if(!ruleType_i.equalsIgnoreCase(firstRuleType)){
					isRuleTypeSame=false;
					break;
				}
				else{
					isRuleTypeSame=true;
				}
			}
		}
		
		return isRuleTypeSame;
	}
	
	
	
	// Helper Methods
	
	/**
	 * 
	 */
	private static void throwException() {
		
		throw new NullPointerException(
		"The list of Rules returned is Empty at the Benefit Component Level/Product/Contract Level in : RuleTypeValidationBuilder");
		
	}
	
	/**
	 * @param benefitList
	 * @return list of benefitIds to do RuleId Search.Using the benefitId list,the query will be executed. 
	 */
	private static List getBenefitIds(List benefitList) {
		
		List benefitIdlist=new ArrayList();
		for(int index=0;index<benefitList.size();index++){
			
			BenefitHierarchyAssociationBO bhAssnBO = (BenefitHierarchyAssociationBO) benefitList.get(index);
			int benefitId=bhAssnBO.getBenefitId();
			Logger.logDebug("Benefit Id="+ index+ " "+ benefitId);
			benefitIdlist.add(new Integer(benefitId));
		}
		return benefitIdlist;
	}
	private static boolean isListEmptyOrNull(List list){
		if (null!=list && !list.isEmpty()){
			return false;
		}
		else{
			return true;
		}
	}
	
	private static BusinessObjectManager getBusinessObjectManager() {
		BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory) ObjectFactory
		.getFactory(ObjectFactory.BOM);
		BusinessObjectManager bom = bomf.getBusinessObjectManager();
		return bom;
	}
}
