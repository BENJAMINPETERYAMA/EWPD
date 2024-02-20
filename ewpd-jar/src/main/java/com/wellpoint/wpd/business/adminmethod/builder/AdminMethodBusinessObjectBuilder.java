/**
 * AdminMethodBusinessObjectBuilder.java 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 * Created on Sep 12, 2007
 */
package com.wellpoint.wpd.business.adminmethod.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.adminmethod.adapter.AdminMethodAdapterManager;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.contract.adapter.ContractAdapterManager;
import com.wellpoint.wpd.business.framework.bo.AuditFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.product.adapter.ProductAdapterManager;
import com.wellpoint.wpd.business.standardbenefit.adapter.StandardBenefitAdapterManager;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodAnswerOverrideBO;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodBO;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodOverrideBO;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodSPSValidationBO;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodTierOverrideBO;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodValidationBO;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodsPopupBO;
import com.wellpoint.wpd.common.adminmethod.bo.SPSParameterBO;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.override.benefit.bo.TierDefinitionBO;
import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitBO;
import com.wellpoint.wpd.web.search.util.SearchUtil;

/**
 * Builder for Admin Method.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AdminMethodBusinessObjectBuilder {

    
	
	 /**
     * Retrieves Sps names for override
     * 
     * @param adminMethodOverrideBO
     * @return List
     * @throws SevereException
     * @throws AdapterException
     */
    public List getSPSNamesForAdminMethodValidation(
    		AdminMethodOverrideBO methodOverrideBO)
            throws SevereException, AdapterException {
        List adminMethodList = null;

        AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();
        try {
            adminMethodList = adapterManager
                    .getSPSNamesListForAdminMethodValidation(methodOverrideBO);
        } catch (SevereException ex) {
            List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in getSPSNamesForAdminMethodOverride method, in AdminMethodBusinessObjectBuilder",
                    errorParams, ex);
        } catch (Exception e) {
            throw new SevereException("Unhandled exception is caught", null, e);
        }

        return adminMethodList;
    }
    
    
	
	public List getAdminMethodsForValidation(AdminMethodValidationBO adminMethodValidationBO)
    throws SevereException, AdapterException {
		
		AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();
		List validationSPSList ;
		
		try{
			validationSPSList = adapterManager.getAdminMethodsForValidation(adminMethodValidationBO);
			
		}   catch (SevereException ex) {
            List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in getListAfterFilter method, in AdminMethodBusinessObjectBuilder",
                    errorParams, ex);
        } catch (Exception e) {
            throw new SevereException("Unhandled exception is caught", null, e);
        }
		
		return validationSPSList;
		
	}
//	CARS START AM1
	/**
	 * This method returns the invalid SPS List of the Admin Method Tiers for the selected Product or Contract.
	 * @param adminMethodValidationBO
	 * @return validationSPSList
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public List getTierAdminMethodsForValidation(AdminMethodValidationBO adminMethodValidationBO)
	throws SevereException, AdapterException {		
		AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();
		List validationSPSList ;		
		try{
			validationSPSList = adapterManager.getTierAdminMethodsForValidation(adminMethodValidationBO);
			
		}   catch (SevereException ex) {
			List errorParams = new ArrayList();
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in getListAfterFilter method, in AdminMethodBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception e) {
			throw new SevereException("Unhandled exception is caught", null, e);
		}		
		return validationSPSList;		
	}	
//	CARS END AM1	
    /**
     * Retrieves Admin Methods for Popup
     * 
     * @param adminMethodsPopupBO
     * @return List
     * @throws SevereException
     * @throws AdapterException
     */
/*
 * code revert after enhancement 
 * 	method commented
 */
/*  
    public List getAdminMethodsForPopup(AdminMethodsPopupBO adminMethodsPopupBO)
            throws SevereException, AdapterException {
        AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();
        List allAdminMethod = new ArrayList();
        allAdminMethod = getListAfterFilter(adminMethodsPopupBO);
        if (null == allAdminMethod || allAdminMethod.size() == 0) {
		    try {
		        return adapterManager.getAdminMethodList(adminMethodsPopupBO
		                .getSpsId());
		    } catch (SevereException ex) {
		        List errorParams = new ArrayList();
		        String obj = ex.getClass().getName();
		        errorParams.add(obj);
		        errorParams.add(obj.getClass().getName());
		        throw new SevereException(
		                "Exception occured in getAdminMethodsForPopup method, in AdminMethodBusinessObjectBuilder",
		            errorParams, ex);
		} catch (Exception e) {
		    throw new SevereException("Unhandled exception is caught",
		                null, e);
		    }
		} else {
//		    return sortAdminMethodBasedOnRank(allAdminMethod);
		    return allAdminMethod;
		}
    }
*/
	/*
	 * code reverted after enhancement 
	 * new method added 
	 */
    public List getAdminMethodsForPopup(AdminMethodsPopupBO adminMethodsPopupBO)
    throws SevereException, AdapterException {
		AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();
		List allAdminMethod = new ArrayList();
		//CARS AM1 START
		//verify whether the adminmethods request is for tiered one or not.
		if(adminMethodsPopupBO.getBenefitTierSysId() != -1)
			allAdminMethod = getListAfterFilterForTier(adminMethodsPopupBO);
		else 
			allAdminMethod = getListAfterFilter(adminMethodsPopupBO);
		//CARS AM1 END
		allAdminMethod = sortAdminMethodPopupBOs(allAdminMethod);
		if (null == allAdminMethod || allAdminMethod.size() == 0) {
		    try {
		        return adapterManager.getAdminMethodList(adminMethodsPopupBO
		                .getSpsId());
		    } catch (SevereException ex) {
		        List errorParams = new ArrayList();
		        String obj = ex.getClass().getName();
		        errorParams.add(obj);
		        errorParams.add(obj.getClass().getName());
		        throw new SevereException(
		                "Exception occured in getAdminMethodsForPopup method, in AdminMethodBusinessObjectBuilder",
		            errorParams, ex);
		} catch (Exception e) {
		    throw new SevereException("Unhandled exception is caught",
		                null, e);
		    }
		} else {
//		    return sortAdminMethodBasedOnRank(allAdminMethod);
		    return allAdminMethod;
		}

}
	
/*            try {									//FIX ME Remove commented code
                return adapterManager.getAdminMethodList(adminMethodsPopupBO
                        .getSpsId());
            } catch (SevereException ex) {
                List errorParams = new ArrayList();
                String obj = ex.getClass().getName();
                errorParams.add(obj);
                errorParams.add(obj.getClass().getName());
                throw new SevereException(
                        "Exception occured in getAdminMethodsForPopup method, in AdminMethodBusinessObjectBuilder",
                        errorParams, ex);
            } catch (Exception e) {
                throw new SevereException("Unhandled exception is caught",
                        null, e);
            }
        } else {
            return sortAdminMethodBasedOnRank(allAdminMethod);
        }

    }
*/
    /**
     * Retrieves Admin Methods after the filterationbased on the 5 filter
     * criteria.
     * 
     * @param adminMethodsPopupBO
     * @return List
     * @throws SevereException
     * @throws AdapterException
     */
    public List getListAfterFilter(AdminMethodsPopupBO adminMethodsPopupBO)
            throws SevereException, AdapterException {

        AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();
        List allAdminMethod = new ArrayList(); //FIX ME Move initialization where elements adding.
        List listOfPossibleEntriesWithoutRepetation = new ArrayList(); //FIX ME Remove unwanted
        int benDefnId = adminMethodsPopupBO.getStdDefId();
        List termsFromEntity = getTermValuesForEntity(adapterManager.getTermsFromEntity(adminMethodsPopupBO));
        try {
            List terms = getTermValues(adapterManager
                    .getTerms(adminMethodsPopupBO.getSpsId()));

            if((null != termsFromEntity && !termsFromEntity.isEmpty()) && (termsFromEntity.containsAll(terms) || null == terms || "".equals(terms))){
               // if(termsFromEntity.containsAll(terms) || null == terms || "".equals(terms)){
                    String termvalue = SearchUtil
                    .createQueryStringForAdminMethodsTerm(terms);
                    List qualifiers = null;
                    qualifiers = getAggregatedValues(adapterManager.getQualifiers(
                             adminMethodsPopupBO, termvalue, benDefnId));
                    if (qualifiers.size() == 0 && qualifiers.isEmpty()) {
                        qualifiers.add("-1");
                    }
                    allAdminMethod.addAll(adapterManager.getAdminMethods(
                        adminMethodsPopupBO, qualifiers, termvalue));
                  //}
            }
           
            List answerList  = adapterManager.getQuestionAnswerList(
                    adminMethodsPopupBO);
            List commonAnsList = new ArrayList();//FIX ME Move to where elememts adding.
            //Retrieving the answerList and storing the answerIds to
            // answerValList
            List answerValList = new ArrayList();
            if (null != answerList && answerList.size() > 0) {
                for (int i = 0; i < answerList.size(); i++) {
                    AdminMethodsPopupBO adminMethods = (AdminMethodsPopupBO) answerList
                            .get(i);
                    answerValList.add(String
                            .valueOf(adminMethods.getAnswerId()));
                }
                // Retrieving the posssibeAnswerList and storing the answerIds
                // to posAnswerValList
                List posAnswerValList = new ArrayList();
                HashMap quesMap=new HashMap();
                List posAnswerList = adapterManager
                        .getPossibleAnswers(adminMethodsPopupBO.getSpsId());//FIX ME Use local variable
                if (null != posAnswerList) {
                    for (int i = 0; i < posAnswerList.size(); i++) {
                        AdminMethodsPopupBO adminMethods = (AdminMethodsPopupBO) posAnswerList
                                .get(i);
                        posAnswerValList.add(String.valueOf(adminMethods
                                .getAnswerId()));
                        List ansList = null;
                        int questionId = adminMethods.getAdminMethodId();
                        
                        if(!quesMap.containsKey(new Integer(questionId))){
                        	ansList = new ArrayList();
                        }else{
                        	ansList = (List)quesMap.get(new Integer(questionId));
                        }
                        // Get the question answers for the possible answers
                        ansList.add(String.valueOf(adminMethods
                                    .getAnswerId()));
                        quesMap.put(new Integer(questionId), ansList);
                    }
                }
                //Comparing both posAnswerValList and answerValList and storing
                // the common Objects to finalList         //FIX ME Null check
              
                for (int i = 0; i < answerValList.size(); i++) {
                    if (posAnswerValList.contains(answerValList.get(i)) && !commonAnsList.contains(answerValList.get(i))) {
                    	commonAnsList.add(answerValList.get(i));
                    }
                }
                if (null == allAdminMethod || allAdminMethod.size() == 0) {
                //Retrieving the possible answer list from ADMN_MTHD_FLTR_CRITR
                // table
                List finalAnswerListCriterial = new ArrayList();
                if (null != commonAnsList && commonAnsList.size() > 0) {
                    for (int i = 0; i < commonAnsList.size(); i++) {
                        String answer = commonAnsList.get(i).toString();
                        List answerListCriterial = adapterManager
                                .getAnswerListFromCriteria(answer,           //FIX ME Remove commented
                                        adminMethodsPopupBO.getSpsId());
                        finalAnswerListCriterial.add(answerListCriterial);
                    }
                }
                //Storing all the possible answerlist as one list named
                // listOfPossibleEntriesWithRepetation where
                // repetitions may occur
                List listOfPossibleEntriesWithRepetation = new ArrayList();
                if (null != finalAnswerListCriterial
                        && finalAnswerListCriterial.size() > 0) {
                    for (int i = 0; i < finalAnswerListCriterial.size(); i++) {
                        if (null != finalAnswerListCriterial.get(i)
                                && !((List) finalAnswerListCriterial.get(i))
                                        .isEmpty()) {
                            listOfPossibleEntriesWithRepetation
                                    .addAll((List) finalAnswerListCriterial
                                            .get(i));
                        }
                    }
                }
                
                // All repeated objects gets removed.
                if (null != listOfPossibleEntriesWithRepetation
                        && listOfPossibleEntriesWithRepetation.size() > 0) {
                    int listSize = listOfPossibleEntriesWithRepetation.size();
                    for (int i = 0; i < listSize; i++) {
                        AdminMethodsPopupBO adminMethodPopUpBO = (AdminMethodsPopupBO) listOfPossibleEntriesWithRepetation
                                .get(i);
                        int filterCriteriaSysId = adminMethodPopUpBO
                                .getAdminMethodFilterCriteriaSysId();
                        int adminMethodSysId = adminMethodPopUpBO.getAdminMethodId();
                        int count = 1;
                        // search for the filterCriteriaSysId in the whole list.
                        // if exist delete the repeted entries
                        for (int j = i + 1; j < listSize; j++) {
                            AdminMethodsPopupBO tempAdminMethodPopUpBO = (AdminMethodsPopupBO) listOfPossibleEntriesWithRepetation
                                    .get(j);
                            if (filterCriteriaSysId == tempAdminMethodPopUpBO
                                    .getAdminMethodFilterCriteriaSysId()) {
                            	
	                            	if(!"null".equalsIgnoreCase(tempAdminMethodPopUpBO
	                                    .getAnswerList()))
	                                count++;
	                                listOfPossibleEntriesWithRepetation.remove(j);
	                                listSize--;
	                                j--;
                            	
                            }else if(adminMethodSysId == tempAdminMethodPopUpBO.getAdminMethodId()){
                            	listOfPossibleEntriesWithRepetation.remove(j);
                                listSize--;
                                j--;
                            }
                            
                        }
//                        for( int k=0; k < listSize;k++){
//                        	AdminMethodsPopupBO tempAdminMethodPopUpBO = (AdminMethodsPopupBO) listOfPossibleEntriesWithRepetation
//                            .get(k);
//                        	
//                        	if (adminMethodSysId == tempAdminMethodPopUpBO.getAdminMethodId()) {
//	                                listOfPossibleEntriesWithRepetation.remove(k);
//	                                listSize--;
//	                                
//                            }
//                        }
                        
                        
                        if (null != adminMethodPopUpBO.getAnswerList()
                                && !(adminMethodPopUpBO.getAnswerList()
                                        .equals(""))) {
                            StringTokenizer answerIDList = new StringTokenizer(
                                    adminMethodPopUpBO.getAnswerList(), ",");
                            int noOfTokens = answerIDList.countTokens();
                            if (count == noOfTokens) {
                                adminMethodPopUpBO.setRank(noOfTokens);
                                listOfPossibleEntriesWithoutRepetation
                                        .add(adminMethodPopUpBO);

                            }
                        }
                    }
                    HashMap hashMap=new HashMap();
                    List highestRankList=getfilterListbasedOnQuestion(listOfPossibleEntriesWithoutRepetation,quesMap, false, commonAnsList);

                	

//                	 Adding the listOfPossibleEntriesWithoutRepetation list to
                    // allAdminMethod list
                    if (null != highestRankList
                            && !highestRankList.isEmpty()) {
                        allAdminMethod
                                .addAll(highestRankList);
                    }
                }
                
            }else{
        	    //      	  if commonAnswerList is null then no need to rank the question
            	//if(commonAnsList.size()>0){
            		allAdminMethod = getAdminMethodBasedOnQuestion(allAdminMethod,commonAnsList,quesMap);
            	//}
            	//else
//            		allAdminMethod = getAdminMethodBasedOnRank(allAdminMethod);
//            		allAdminMethod = eliminateDuplicateRecord(allAdminMethod);
              }
            }else{
            	HashMap hashMap = new HashMap();
            	allAdminMethod = getAdminMethodBasedOnQuestion(allAdminMethod,new ArrayList(),hashMap);
//            	allAdminMethod = getAdminMethodBasedOnRank(allAdminMethod);
//            	allAdminMethod = eliminateDuplicateRecord(allAdminMethod);
            }
        
            
        } catch (SevereException ex) {
            List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in getListAfterFilter method, in AdminMethodBusinessObjectBuilder",
                    errorParams, ex);
        } catch (Exception e) {
            throw new SevereException("Unhandled exception is caught", null, e);
        }

        return allAdminMethod;
    }
    
    //CARS AM1 START
    /**
     * Retrieves Admin Methods for a benefit tier after the filteration based on the 5 filter
     * criteria.
     * 
     * @param adminMethodsPopupBO
     * @return List
     * @throws SevereException
     * @throws AdapterException
     */
    public List getListAfterFilterForTier(AdminMethodsPopupBO adminMethodsPopupBO)
            throws SevereException, AdapterException {
        
        AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();
        List allAdminMethod = new ArrayList();
        List listOfPossibleEntriesWithoutRepetation = new ArrayList();
        int benDefnId = adminMethodsPopupBO.getStdDefId();             
        List termsFromEntity = getTermValuesForEntity(adapterManager.getTermsFromTierEntity(adminMethodsPopupBO));
        try {
            List terms = getTermValues(adapterManager
                    .getTerms(adminMethodsPopupBO.getSpsId()));
            if((null != termsFromEntity && !termsFromEntity.isEmpty())){
                if(termsFromEntity.containsAll(terms) || null == terms || "".equals(terms)){
                    String termvalue = SearchUtil
                    .createQueryStringForAdminMethodsTerm(terms);
                    List qualifiers = null;                                                
                    qualifiers = getAggregatedValues(adapterManager.getTierQualifiers(adminMethodsPopupBO, termvalue, benDefnId));
                    if (qualifiers.size() == 0 && qualifiers.isEmpty()) {
                        qualifiers.add("-1");
                    }                             
                    allAdminMethod.addAll(adapterManager.getTierAdminMethods(adminMethodsPopupBO, qualifiers, termvalue));
                }
            }
            if (BusinessConstants.PRODUCT.equalsIgnoreCase(adminMethodsPopupBO.getEntityType())) {
                ProductAdapterManager productAdapterManager = new ProductAdapterManager();
                
                //Get tier criteria for selected sps
                List tierCriteriaList = productAdapterManager.getTierCriteriaForProduct(adminMethodsPopupBO.getEntityId(),
                        adminMethodsPopupBO.getBenefitCompId(),adminMethodsPopupBO.getBenftId());
                
                List tierCriteriaForCurrentSPS = new ArrayList();
                for (int m=0;m<tierCriteriaList.size();m++){
                    TierDefinitionBO tierDefinitionB0 = (TierDefinitionBO)tierCriteriaList.get(m);
                    if(adminMethodsPopupBO.getBenefitTierSysId() == tierDefinitionB0.getTierSysId()){
                        tierCriteriaForCurrentSPS.add(tierDefinitionB0);
                    }
                }
                //Get tier criteria for selected sps from General Benefits
                /*List generalBenefitsTierCriteriaList = productAdapterManager.
                	getTierCriteriaForGeneralBenefitsInProduct(adminMethodsPopupBO.getEntityId());
                //Check for criteria match on the selected sps from General Benefits
                if(null != generalBenefitsTierCriteriaList && generalBenefitsTierCriteriaList.size() > 0){   
                    int countOfTiersInCommon = 0;
                    int generalBenefitInheritedTierId = -1;
                    //commented for removing general benefit inheritance
        	 		for (int k=0;k<tierCriteriaForCurrentSPS.size();k++){
						TierDefinitionBO tierDefinitionBo = (TierDefinitionBO)tierCriteriaForCurrentSPS.get(k);
						for (int j=0;j<generalBenefitsTierCriteriaList.size();j++){
						    TierDefinitionBO generalBenefitsTierDefinitionBo = (TierDefinitionBO)generalBenefitsTierCriteriaList.get(j);
						    if(generalBenefitsTierDefinitionBo.getTierDefId() == tierDefinitionBo.getTierDefId() && 
						            generalBenefitsTierDefinitionBo.getTierCriteriaSysId() == tierDefinitionBo.getTierCriteriaSysId() &&
						            generalBenefitsTierDefinitionBo.getCriteriaVal().equalsIgnoreCase(tierDefinitionBo.getCriteriaVal())){
						        countOfTiersInCommon++;	
						        generalBenefitInheritedTierId = generalBenefitsTierDefinitionBo.getTierSysId();
						    }
						}
        	 		} 
        	 		if(countOfTiersInCommon == tierCriteriaForCurrentSPS.size()){
        	 		   adminMethodsPopupBO.setEntityTierSysId(generalBenefitInheritedTierId);
        	 		}else{
        	 		   adminMethodsPopupBO.setEntityTierSysId(-1);
        	 		}
                }else{
                    adminMethodsPopupBO.setEntityTierSysId(-1);
                }*/
            }else if(BusinessConstants.CONTRACT.equalsIgnoreCase(adminMethodsPopupBO.getEntityType())){
               ContractAdapterManager contractAdapterManager = new ContractAdapterManager();
               //Get tier criteria for selected sps
               List tierCriteriaList = contractAdapterManager.getTierCriteriaForBenefitInContract(adminMethodsPopupBO.getEntityId(),
                       adminMethodsPopupBO.getBenefitCompId(),adminMethodsPopupBO.getBenftId()); 
               List tierCriteriaForCurrentSPS = new ArrayList();
               for (int m=0;m<tierCriteriaList.size();m++){
                   TierDefinitionBO tierDefinitionB0 = (TierDefinitionBO)tierCriteriaList.get(m);
                   if(adminMethodsPopupBO.getBenefitTierSysId() == tierDefinitionB0.getTierSysId()){
                       tierCriteriaForCurrentSPS.add(tierDefinitionB0);
                   }
               }
               //Get tier criteria for selected sps from General Benefits
              /* List generalBenefitsTierCriteriaList = contractAdapterManager.
               			getTierCriteriaForGeneralBenefitsInContract(adminMethodsPopupBO.getEntityId());
               //Check for criteria match on the selected sps from General Benefits
               if(null != generalBenefitsTierCriteriaList && generalBenefitsTierCriteriaList.size() > 0){   
                   int countOfTiersInCommon = 0;
                   int generalBenefitInheritedTierId = -1;
                   //commented for removing general benefit inheritance
       	 		for (int k=0;k<tierCriteriaForCurrentSPS.size();k++){
						TierDefinitionBO tierDefinitionBo = (TierDefinitionBO)tierCriteriaForCurrentSPS.get(k);
						for (int j=0;j<generalBenefitsTierCriteriaList.size();j++){
						    TierDefinitionBO generalBenefitsTierDefinitionBo = (TierDefinitionBO)generalBenefitsTierCriteriaList.get(j);
						    if(generalBenefitsTierDefinitionBo.getTierDefId() == tierDefinitionBo.getTierDefId() && 
						            generalBenefitsTierDefinitionBo.getTierCriteriaSysId() == tierDefinitionBo.getTierCriteriaSysId() &&
						            generalBenefitsTierDefinitionBo.getCriteriaVal().equalsIgnoreCase(tierDefinitionBo.getCriteriaVal())){
						        countOfTiersInCommon++;	
						        generalBenefitInheritedTierId = generalBenefitsTierDefinitionBo.getTierSysId();
						    }
						}
       	 		} 
       	 		if(countOfTiersInCommon == tierCriteriaForCurrentSPS.size()){
       	 		   adminMethodsPopupBO.setEntityTierSysId(generalBenefitInheritedTierId);
       	 		}else{
       	 		   adminMethodsPopupBO.setEntityTierSysId(-1);
       	 		}
               }else{
                   adminMethodsPopupBO.setEntityTierSysId(-1);
               }*/
            }
            List answerList  = adapterManager.getTierQuestionAnswerList(adminMethodsPopupBO);
            List commonAnsList = new ArrayList();
            //Retrieving the answerList and storing the answerIds to answerValList
            List answerValList = new ArrayList();
            if (null != answerList && answerList.size() > 0) {
                for (int i = 0; i < answerList.size(); i++) {
                    AdminMethodsPopupBO adminMethods = (AdminMethodsPopupBO) answerList
                            .get(i);
                    answerValList.add(String
                            .valueOf(adminMethods.getAnswerId()));
                }
                // Retrieving the posssibeAnswerList and storing the answerIds to posAnswerValList
                List posAnswerValList = new ArrayList();
                HashMap quesMap=new HashMap();
                List posAnswerList = adapterManager
                        .getPossibleAnswers(adminMethodsPopupBO.getSpsId());
                if (null != posAnswerList) {
                    for (int i = 0; i < posAnswerList.size(); i++) {
                        AdminMethodsPopupBO adminMethods = (AdminMethodsPopupBO) posAnswerList
                                .get(i);
                        posAnswerValList.add(String.valueOf(adminMethods
                                .getAnswerId()));
                        List ansList = null;
                        int questionId = adminMethods.getAdminMethodId();
                        
                        if(!quesMap.containsKey(new Integer(questionId))){
                        	ansList = new ArrayList();
                        }else{
                        	ansList = (List)quesMap.get(new Integer(questionId));
                        }
                        // Get the question answers for the possible answers
                        ansList.add(String.valueOf(adminMethods
                                    .getAnswerId()));
                        quesMap.put(new Integer(questionId), ansList);
                    }
                }
                //Comparing both posAnswerValList and answerValList and storing the common Objects to finalList       
                if (null != answerValList && answerValList.size() > 0) {
                    for (int i = 0; i < answerValList.size(); i++) {
                        if (null != posAnswerValList && posAnswerValList.size() > 0) {
                            if (posAnswerValList.contains(answerValList.get(i)) && !commonAnsList.contains(answerValList.get(i))) {
                            	commonAnsList.add(answerValList.get(i));
                            }
                        }                       
                    }
                }                
                if (null == allAdminMethod || allAdminMethod.size() == 0) {
                //Retrieving the possible answer list from ADMN_MTHD_FLTR_CRITR table
                List finalAnswerListCriterial = new ArrayList();
                if (null != commonAnsList && commonAnsList.size() > 0) {
                    for (int i = 0; i < commonAnsList.size(); i++) {
                        String answer = commonAnsList.get(i).toString();
                        List answerListCriterial = adapterManager
                                .getAnswerListFromCriteria(answer,          
                                        adminMethodsPopupBO.getSpsId());
                        finalAnswerListCriterial.add(answerListCriterial);
                    }
                }
                /*Storing all the possible answerlist as one list named
                  listOfPossibleEntriesWithRepetation where repetitions may occur*/
                List listOfPossibleEntriesWithRepetation = new ArrayList();
                if (null != finalAnswerListCriterial
                        && finalAnswerListCriterial.size() > 0) {
                    for (int i = 0; i < finalAnswerListCriterial.size(); i++) {
                        if (null != finalAnswerListCriterial.get(i)
                                && !((List) finalAnswerListCriterial.get(i))
                                        .isEmpty()) {
                            listOfPossibleEntriesWithRepetation
                                    .addAll((List) finalAnswerListCriterial
                                            .get(i));
                        }
                    }
                }
                
                // All repeated objects gets removed.
                if (null != listOfPossibleEntriesWithRepetation
                        && listOfPossibleEntriesWithRepetation.size() > 0) {
                    int listSize = listOfPossibleEntriesWithRepetation.size();
                    for (int i = 0; i < listSize; i++) {
                        AdminMethodsPopupBO adminMethodPopUpBO = (AdminMethodsPopupBO) listOfPossibleEntriesWithRepetation
                                .get(i);
                        int filterCriteriaSysId = adminMethodPopUpBO
                                .getAdminMethodFilterCriteriaSysId();
                        int adminMethodSysId = adminMethodPopUpBO.getAdminMethodId();
                        int count = 1;
                        // search for the filterCriteriaSysId in the whole list,if exist delete the repeated entries                        
                        for (int j = i + 1; j < listSize; j++) {
                            AdminMethodsPopupBO tempAdminMethodPopUpBO = (AdminMethodsPopupBO) listOfPossibleEntriesWithRepetation
                                    .get(j);
                            if (filterCriteriaSysId == tempAdminMethodPopUpBO
                                    .getAdminMethodFilterCriteriaSysId()) {
                            	
	                            	if(!"null".equalsIgnoreCase(tempAdminMethodPopUpBO
	                                    .getAnswerList()))
	                                count++;
	                                listOfPossibleEntriesWithRepetation.remove(j);
	                                listSize--;
	                                j--;
                            	
                            }else if(adminMethodSysId == tempAdminMethodPopUpBO.getAdminMethodId()){
                            	listOfPossibleEntriesWithRepetation.remove(j);
                                listSize--;
                                j--;
                            }
                            
                        }
                        if (null != adminMethodPopUpBO.getAnswerList()
                                && !(adminMethodPopUpBO.getAnswerList()
                                        .equals(""))) {
                            StringTokenizer answerIDList = new StringTokenizer(
                                    adminMethodPopUpBO.getAnswerList(), ",");
                            int noOfTokens = answerIDList.countTokens();
                            if (count == noOfTokens) {
                                adminMethodPopUpBO.setRank(noOfTokens);
                                listOfPossibleEntriesWithoutRepetation
                                        .add(adminMethodPopUpBO);

                            }
                        }
                    }
                    HashMap hashMap=new HashMap();
                    List highestRankList=getfilterListbasedOnQuestion(listOfPossibleEntriesWithoutRepetation,quesMap, false, commonAnsList);   
                    // Adding the listOfPossibleEntriesWithoutRepetation list to allAdminMethod list
                    if (null != highestRankList
                            && !highestRankList.isEmpty()) {
                        allAdminMethod
                                .addAll(highestRankList);
                    }
                }
                
            }else{
                	//if commonAnswerList is null then no need to rank the question            	
            		allAdminMethod = getAdminMethodBasedOnQuestion(allAdminMethod,commonAnsList,quesMap);            	
              }
            }else{
            	HashMap hashMap = new HashMap();
            	allAdminMethod = getAdminMethodBasedOnQuestion(allAdminMethod,new ArrayList(),hashMap);
            }
        
            
        } catch (SevereException ex) {
            List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in getListAfterFilter method, in AdminMethodBusinessObjectBuilder",
                    errorParams, ex);
        } catch (Exception e) {
            throw new SevereException("Unhandled exception is caught", null, e);
        }
        return allAdminMethod;
    }
    //CARS AM1 END
    
    private List getfilterListbasedOnQuestion(List listOfPossibleEntriesWithoutRepetation,HashMap quesMap, boolean termFlag, List commonAnsList){
        double rank = 0;
        List highestRankList = new ArrayList();
        HashMap map=new HashMap();
    	for(int i=0;i<listOfPossibleEntriesWithoutRepetation.size();i++){
    	AdminMethodsPopupBO adminMethodsPopup = (AdminMethodsPopupBO)listOfPossibleEntriesWithoutRepetation.get(i);
		if(map.size()==0){
			
			// If Answer id is null or "null"
			 if((adminMethodsPopup.getAnswerList()==null || 
					"null".equalsIgnoreCase(adminMethodsPopup.getAnswerList())) && !termFlag){
				// If case when no SPS term selected and 
				// filtering based on questions selected only, 
				// then no need to consider null valued criteria
//				if(!termFlag){
					continue;
//				}		
			}
			map.put(new Integer(adminMethodsPopup.getAdminMethodFilterCriteriaSysId()),adminMethodsPopup);
		}else{
			List aggList=new ArrayList();
			String arr[]=null;
			if(adminMethodsPopup.getAnswerList()!=null&&!"null".equalsIgnoreCase(adminMethodsPopup.getAnswerList()))
				arr=adminMethodsPopup.getAnswerList().split(",");
			else{
				if(termFlag)
				map.put(new Integer(adminMethodsPopup.getAdminMethodFilterCriteriaSysId()),adminMethodsPopup);
				continue;
			}
			for(int j=0;j<arr.length;j++)
				aggList.add(arr[j]);
			Set set=map.keySet();
			Iterator iterator=set.iterator();boolean add=true;List removeList=new ArrayList();
			while(iterator.hasNext()){
				Integer key=(Integer)iterator.next();
				AdminMethodsPopupBO methodsPopupBO=(AdminMethodsPopupBO)map.get(key);
				List tmpList=new ArrayList();String tmparr[]=null;
				
				if(methodsPopupBO.getAnswerList()==null||"null".equalsIgnoreCase(methodsPopupBO.getAnswerList()))
					continue;
				else{
					if(null != commonAnsList && !commonAnsList.isEmpty()){
					tmparr=methodsPopupBO.getAnswerList().split(",");
					for(int k=0;k<tmparr.length;k++)
						tmpList.add(tmparr[k]);
					
					
					if(tmpList.size()>=aggList.size()){
						boolean contains=false;
						for(int j=0;j<tmpList.size();j++)
						 if(containsAnswerOfSameQuestion(aggList,""+tmpList.get(j),quesMap)){
						 	contains=true;
						 	break;
						 }
						 if(contains){
						 	if(tmpList.size()>aggList.size()){
						 		add=false;break;
						 	}else
						 		add=true;
						 }else{
						 	add=true;
						 }
						 	
						
					}else{
						if(aggList.containsAll(tmpList))
							removeList.add(key);
						else{
							boolean flg=false;
							for(int j=0;j<aggList.size();j++){
								if(containsAnswerOfSameQuestion(tmpList,""+aggList.get(j),quesMap)){
									flg=true;break;
								}
								if(flg)
									removeList.add(key);
								else
									add=true;
									
							}
							
						}
						
					}
					}else{
						add=true;
					}
				}
			}
			for(int j=0;j<removeList.size();j++)
				map.remove((Integer)removeList.get(j));
			if(add)
			map.put(new Integer(adminMethodsPopup.getAdminMethodFilterCriteriaSysId()),adminMethodsPopup);
			
		}
    	}
//    	if(map.size()==0 && termFlag==false){
//            return (listOfPossibleEntriesWithoutRepetation);
//      }
    	
		Set set=map.keySet();
		Iterator iterator=set.iterator();
		while(iterator.hasNext()){
			highestRankList.add(map.get((Integer)iterator.next()));
		}
		for(int j=0;j<highestRankList.size();j++){
			AdminMethodsPopupBO adminMethodsPopup = (AdminMethodsPopupBO)highestRankList.get(j);
			if((adminMethodsPopup.getRank()>1))
				return highestRankList;
		}

		for(int j=0;j<highestRankList.size();j++){
			AdminMethodsPopupBO adminMethodsPopup = (AdminMethodsPopupBO)highestRankList.get(j);
			if((adminMethodsPopup.getAnswerList()!=null&&!"null".equalsIgnoreCase(adminMethodsPopup.getAnswerList())))
				return highestRankList;
		}
		highestRankList=new ArrayList();
		return (highestRankList);
    }
    
    private boolean containsAnswerOfSameQuestion(List list,String check,HashMap quesMap){
    	
    	List answerList=null;
    	Set set= quesMap.keySet();
    	Iterator iterator=set.iterator();
    	while(iterator.hasNext()){
    		Integer key=(Integer)iterator.next();
    		List tmpList=(List)quesMap.get(key);
    		if(tmpList.contains(check)){
    			answerList=tmpList;
    			break;
    		}		
    	}
    	if(answerList==null)
    		return false;
    	for(int i=0;i<list.size();i++){
    		if(answerList.contains(list.get(i)))
    		return true;
    	}
    	return false;
    	
    }
    public List getAdminMethodBasedOnQuestion(List adminMethodList ,List commonAnsList,HashMap quesMap)
    throws SevereException, AdapterException {
    	
    	double higestRank = 0;
    	List adminMethodQuestionRankList = new ArrayList();
    	List adminMethodQuestionNullList = new ArrayList();
    	AdminMethodsPopupBO adminMethodsPopupBO; 
    	for(int i=0;i<adminMethodList.size();i++){   //FIX ME Null check
    		
    		adminMethodsPopupBO = (AdminMethodsPopupBO)adminMethodList.get(i);
    		adminMethodsPopupBO.setRank(adminMethodsPopupBO.getCount());
    		//setting the higest rank
    		if(higestRank == 0)
    			higestRank = adminMethodsPopupBO.getRank();
    		
    		if(higestRank == adminMethodsPopupBO.getRank()){
    			List rankList = getQuestionRank(adminMethodsPopupBO,commonAnsList);
    			if(null != rankList && rankList.size()>0){
    				adminMethodQuestionRankList.add(rankList.get(0));
    			}else{
    				adminMethodQuestionNullList.add(adminMethodsPopupBO);
    			}
    			
    		}else{
    			
    			if(higestRank == (adminMethodsPopupBO.getRank()+1)){
    			
	    			if(adminMethodQuestionRankList.size() == 0){
	    				List rankList = getQuestionRank(adminMethodsPopupBO,commonAnsList);
	    				if(null != rankList && rankList.size()>0){
	    					adminMethodQuestionNullList.add(rankList.get(0));
	    				}
	    			}else {
	    			    break;
	    			}
    			}else{
    			    break;
    			}
    			
    			
    		}
    	}
    	
    	List finalRankList = new ArrayList();
    	if(adminMethodQuestionRankList.size()>0){
    		finalRankList = adminMethodQuestionRankList;
    	}else{
    		finalRankList = adminMethodQuestionNullList;
    	}
    	
    	
//    	List highestRankList = new ArrayList();
//    	double rank = 0;
//    	for(int i=0;i<finalRankList.size();i++){
//    		AdminMethodsPopupBO adminMethodsPopup = (AdminMethodsPopupBO)finalRankList.get(i);
//    		
//    		 
//    		
//    		if(adminMethodsPopup.getRank() >= rank ){
//    			
//    			double BORank = adminMethodsPopup.getRank();//FIX ME Use local variable
//    			if(BORank == rank){
//    			    highestRankList.add(adminMethodsPopup);
//    			}else{
//    			    highestRankList = new ArrayList(); //FIX ME Unwanted object
//    			    highestRankList.add(adminMethodsPopup);
//    			    rank = adminMethodsPopup.getRank();
//    			}
//    			
//    		}
//    		
//    	}
    	List highestRankList=getfilterListbasedOnQuestion(finalRankList,quesMap, true, commonAnsList);
    	highestRankList = eliminateDuplicateRecord(highestRankList);
    	return highestRankList;
    }
    public List getQuestionRank(AdminMethodsPopupBO adminMethodsPopupBO,List commonAnsList)
    throws SevereException, AdapterException {
    	
    	List answers = new ArrayList();
    	
 
    	List questionRankList = new ArrayList(); //FIX ME Move to where elements adding.
    	if(null != adminMethodsPopupBO.getAnswerList() && !adminMethodsPopupBO.getAnswerList().equals("null")){
	    	if(adminMethodsPopupBO.getAnswerList().length()>0  ){
	    		
	    		StringTokenizer answerIDToken = new StringTokenizer(
	    				adminMethodsPopupBO.getAnswerList(), ",");
	    		int countToken =0;
	    		 if(null != answerIDToken){
			        while(answerIDToken.hasMoreElements()){
			            String answerID = answerIDToken.nextToken();
			            answers.add(answerID);
			            countToken++;
			        }
	    		 }
	    		if(commonAnsList.containsAll(answers)){
	    		   	
	    			String token ="."+countToken;
	    			
	    		   	double rank = adminMethodsPopupBO.getRank()+1+Double.parseDouble(token);
	    		   	adminMethodsPopupBO.setRank(rank);
	    		   	questionRankList.add(adminMethodsPopupBO);
	    		}
	    	}
    	}else{
		   	double rank = adminMethodsPopupBO.getRank()+1+Double.parseDouble(".0");
		   	adminMethodsPopupBO.setRank(rank);
		   	questionRankList.add(adminMethodsPopupBO);
    	}
    	
    	
    	return questionRankList;
    }


    /**
     * Retrieves Sps names
     * 
     * @param adminMethodBO
     * @return List
     * @throws SevereException
     * @throws AdapterException
     */
    public List getSPSNames(AdminMethodBO adminMethodBO)
            throws SevereException, AdapterException {
        List adminMethodList = null;

        AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();
        try {
            adminMethodList = adapterManager.getSPSNamesList(adminMethodBO,
                    true);
        } catch (SevereException ex) {
            List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in getSPSNames method, in AdminMethodBusinessObjectBuilder",
                    errorParams, ex);
        } catch (Exception e) {
            throw new SevereException("Unhandled exception is caught", null, e);
        }

        return adminMethodList;
    }

    //CARS START 
    /**
     * Retrieves Sps names for override for Datafeed
     * 
     * @param adminMethodOverrideBO
     * @return List
     * @throws SevereException
     * @throws AdapterException
     */
    public HashMap getSPSNamesForAdminMethodOverrideForDatafeed(
            AdminMethodOverrideBO adminMethodOverrideBO)
            throws SevereException, AdapterException {
        HashMap spsAdminMethodListMap = null;

        AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();
        try {
        	spsAdminMethodListMap = adapterManager
                    .getSPSNamesListForAdminMethodOverrideForDatafeed(adminMethodOverrideBO);
        } catch (SevereException ex) {
            List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in getSPSNamesForAdminMethodOverride method, in AdminMethodBusinessObjectBuilder",
                    errorParams, ex);
        } catch (Exception e) {
            throw new SevereException("Unhandled exception is caught", null, e);
        }

        return spsAdminMethodListMap;
    }
    //CARS END
    /**
     * Retrieves Sps names for override
     * 
     * @param adminMethodOverrideBO
     * @return List
     * @throws SevereException
     * @throws AdapterException
     */
    public List getSPSNamesForAdminMethodOverride(
            AdminMethodOverrideBO adminMethodOverrideBO)
            throws SevereException, AdapterException {
        List adminMethodList = null;

        AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();
        try {
            adminMethodList = adapterManager
                    .getSPSNamesListForAdminMethodOverride(adminMethodOverrideBO);
        } catch (SevereException ex) {
            List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in getSPSNamesForAdminMethodOverride method, in AdminMethodBusinessObjectBuilder",
                    errorParams, ex);
        } catch (Exception e) {
            throw new SevereException("Unhandled exception is caught", null, e);
        }

        return adminMethodList;
    }
    //CARS:AM2:START{
    public List getSPSNamesForAdminMethodOverrideInTiers(AdminMethodTierOverrideBO adminMethodOverrideBO)throws SevereException, AdapterException 
	{
        List adminMethodList = null;
        AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();
        try {
            adminMethodList = adapterManager.fetchSPS_AM_MappingListForOverridenTier(adminMethodOverrideBO);
        } catch (SevereException ex) {
            List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in getSPSNamesForAdminMethodOverrideInTiers method, in AdminMethodBusinessObjectBuilder",
                    errorParams, ex);
        } catch (Exception e) {
            throw new SevereException("Unhandled exception is caught", null, e);
        }

        return adminMethodList;
    }
    
    //CARS:AM2:END}
    
    /*Method added to fetch tiered AM's for PRODUCT as part of Stabilization 2011*/
    
    public List getTieredAMsForProduct(AdminMethodTierOverrideBO adminMethodOverrideBO)throws SevereException, AdapterException 
	{
        List adminMethodList = null;
        AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();
        try {
            adminMethodList = adapterManager.fetchTieredAMsForProduct(adminMethodOverrideBO);
        } catch (SevereException ex) {
            List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in getSPSNamesForAdminMethodOverrideInTiers method, in AdminMethodBusinessObjectBuilder",
                    errorParams, ex);
        } catch (Exception e) {
            throw new SevereException("Unhandled exception is caught", null, e);
        }

        return adminMethodList;
    }
    /*Method added to fetch tiered AM's for PRODUCT as part of Stabilization 2011 - END */
   
    /**
     * Persists the AdminMethodBO
     * 
     * @param adminMethodBO
     * @param user
     * @return boolean
     * @throws SevereException
     * @throws AdapterException
     */
    public boolean persist(List orgAdminMethodLst, List adminMethodBO, User user, boolean insertFlag)
            throws SevereException, AdapterException {
        AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager(); //FIX ME This can give as global since using in all methods
        AuditFactory auditFactory = (AuditFactory) ObjectFactory
                .getFactory(ObjectFactory.AUDIT);
        Audit audit = auditFactory.getAudit(user);
        AdminMethodBO individualAdminMethodBO = new AdminMethodBO();//FIXME Creating object unnecessarily
        AdapterServicesAccess adapterServiceAccess = AdapterUtil
                .getAdapterService();
        long transactionId = AdapterUtil.getTransactionId();
        try {
            AdapterUtil.beginTransaction(adapterServiceAccess);
            AdapterUtil.logBeginTranstn(transactionId, this, "persist(List orgAdminMethodLst, List adminMethodBO, User user, boolean insertFlag)");
            if (!adminMethodBO.isEmpty()) {
            	/** Get the list of SPS Admin Methods already selected from db **/
            	List adminList = orgAdminMethodLst;
                /** If adminList is not empty **/
                if (null != adminList && !adminList.isEmpty()) {
                    /** Loop through the two lists **/
                    for (int i = 0; i < adminList.size(); i++) {
                        /** Get the retrieved admnBO and the selectedAdminBO **/
                        AdminMethodBO retrievedAdminBO = (AdminMethodBO) adminList.get(i);
                        individualAdminMethodBO = (AdminMethodBO) adminMethodBO.get(i);  //FIX ME Repeating the above code
                        individualAdminMethodBO = (AdminMethodBO) adminMethodBO.get(i);
                        individualAdminMethodBO.setCreatedUser(audit.getUser());
                        individualAdminMethodBO.setCreatedTimestamp(audit.getTime());
                        individualAdminMethodBO.setLastUpdatedTimestamp(audit.getTime());
                        individualAdminMethodBO.setLastUpdatedUser(audit.getUser());
                        /** If the spsid are the same
                          * Check whether the rBO adminId is empty and sBO
                          * adminId
                          * has value**/
                        if (retrievedAdminBO.getAdminMethodSysId() == 0
                                && individualAdminMethodBO.getAdminMethodSysId() != 0) {
                            /** Perform insert **/
                            adapterManager.createAdminMethod(
                                    individualAdminMethodBO, user.getUserId(),
                                    adapterServiceAccess);
                        } else if (retrievedAdminBO.getAdminMethodSysId() != 0
                                && individualAdminMethodBO.getAdminMethodSysId() != 0) {
                            /** Check whether the rBO adminId has value and sBO
                              * adminId has value
                              * Perform update **/
                            adapterManager.updateAdminMethod(
                                    individualAdminMethodBO, user.getUserId(),
                                    adapterServiceAccess);
                        } else if (retrievedAdminBO.getAdminMethodSysId() != 0
                                && individualAdminMethodBO.getAdminMethodSysId() == 0) {
                            /** Check whether the rBO adminId is value and sBO
                              * adminId has empty
                              * Perform delete **/
                            adapterManager.deleteAdminMethods(
                                    individualAdminMethodBO, user.getUserId(),
                                    adapterServiceAccess);
                        }
                    }
                }
                StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
                AdapterServicesAccess standardBenefitAdapterServiceAccess = AdapterUtil.getAdapterService();
                standardBenefitBO.setStandardBenefitKey(((AdminMethodBO) adminMethodBO.get(0)).getBenSysId());
                StandardBenefitAdapterManager standardBenefitAdapterManager = new StandardBenefitAdapterManager();
                standardBenefitBO = (StandardBenefitBO) standardBenefitAdapterManager.retrieveSB(standardBenefitBO);
                standardBenefitBO.setLastUpdatedTimestamp(audit.getTime());
                standardBenefitBO.setLastUpdatedUser(audit.getUser());
                standardBenefitAdapterManager.updateStandardBenefit(
                        standardBenefitBO, audit, true,
                        standardBenefitAdapterServiceAccess);
            }
            AdapterUtil.endTransaction(adapterServiceAccess);
            AdapterUtil.logTheEndTransaction(transactionId, this, "persist(List orgAdminMethodLst,List adminMethodBO, User user, boolean insertFlag)");
        } catch (AdapterException ex) {
            AdapterUtil.abortTransaction(adapterServiceAccess);
            AdapterUtil.logAbortTxn(transactionId, this, "persist(List orgAdminMethodLst,List adminMethodBO, User user, boolean insertFlag)");
            throw new SevereException(null, null, ex);
        } catch (Exception ex) {
            AdapterUtil.abortTransaction(adapterServiceAccess);
            AdapterUtil.logAbortTxn(transactionId, this, "persist(List orgAdminMethodLst,List adminMethodBO, User user, boolean insertFlag)");
            throw new SevereException("Unknown exception found.", null, ex);
        }
        return true;
    }
    
    /* CARS START */
    public boolean validateChangedAdminMethods(AdminMethodBO methodBO, List changedIds, List changedTiers, 
    		List changedTierSysIds, String processType) throws SevereException{ 
        List tierList = new ArrayList();
    	if((null != methodBO && changedIds != null && changedIds.size() > 0) ||
    			(null != methodBO && changedTiers != null && changedTiers.size() > 0)){ 
    		AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();
    		if(null != methodBO && null != methodBO.getChangeType() && BusinessConstants.ADMIN_OPTION_TYPE.equalsIgnoreCase(methodBO.getChangeType())
    		        && (changedTiers == null || changedTiers.size() <1) && BusinessConstants.PRODUCT.equalsIgnoreCase(methodBO.getEntityType())){
    		    ProductAdapterManager productAdapterManager = new ProductAdapterManager();
    		    List tierCriteriaList = productAdapterManager.getTierCriteriaForProduct(methodBO.getEntitySysId(),methodBO.getBenefitCompSysId(),methodBO.getBenSysId());
    		    if(null != tierCriteriaList && tierCriteriaList.size() > 0){
    		        int tierSize = tierCriteriaList.size();
    		        for(int i=0;i<tierSize;i++){
    		            TierDefinitionBO tierDefinitionB0 = (TierDefinitionBO)tierCriteriaList.get(i);                      
    		            tierList.add(new Integer(tierDefinitionB0.getTierSysId()));
                        }    		           
    		    } 
    		    try {
        			adapterManager.validateChangedAdminMethods(methodBO, changedIds, tierList, 
        			        tierList, processType);
    			} catch (AdapterException e) {
    	            throw new SevereException("Error in Validation", null, e);
    			}
    		}else{
    		    try {
        			adapterManager.validateChangedAdminMethods(methodBO, changedIds, changedTiers, 
    						changedTierSysIds, processType);
    			} catch (AdapterException e) {
    	            throw new SevereException("Error in Validation", null, e);
    			}
    		}    		
    	}  		
    	return true;
    }
    /* CARS END */
    
    /**
     * 
     * @param contractSysId
     * @return
     * @throws SevereException
     */
    public boolean validateChangedAdminMethodsForContract(int contractSysId) throws SevereException{
    		AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();
    		try {
				adapterManager.validateChangedAdminMethods(contractSysId);
			} catch (AdapterException e) {
	            throw new SevereException("Error in Validation", null, e);
			}
    	return true;
    }

    /**
     * Method to get benefit administration for check in validation .
     * 
     * @param benefitSysId
     * @return List
     * @throws SevereException
     * @throws AdapterException
     */
    public List getAdminstationId(int benefitSysId) throws SevereException,
            AdapterException {

        List adminList = null;

        AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();
        try {
            adminList = adapterManager.getBenefitAdministration(benefitSysId);
        } catch (SevereException ex) {
            List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in getAdminstationId method, in AdminMethodBusinessObjectBuilder",
                    errorParams, ex);
        } catch (Exception e) {
            throw new SevereException("Unhandled exception is caught", null, e);
        }

        return adminList;
    }

    /**
     * Method to get benefit administration method for benefit for check in
     * validation .
     * 
     * @param benefitAdminId
     * @return List
     * @throws SevereException
     * @throws AdapterException
     */
    public List getAssociatedAdminMethod(int benefitAdminId)
            throws SevereException, AdapterException {
        List adminList = null;

        AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();
        try {
            adminList = adapterManager.getAssociatedAdminMethod(benefitAdminId);
        } catch (SevereException ex) {
            List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in getAssociatedAdminMethod method, in AdminMethodBusinessObjectBuilder",
                    errorParams, ex);
        } catch (Exception e) {
            throw new SevereException("Unhandled exception is caught", null, e);
        }

        return adminList;
    }

    /**
     * Method to get benefit Component for check in validation .
     * 
     * @param benefitComponentId
     * @return List
     * @throws SevereException
     * @throws AdapterException
     */
    public List getBenefitIds(int benefitComponentId) throws SevereException,
            AdapterException {
        List adminList = null;

        AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();
        try {
            adminList = adapterManager.getBenefitIds(benefitComponentId);
        } catch (SevereException ex) {
            List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in getBenefitIds method, in AdminMethodBusinessObjectBuilder",
                    errorParams, ex);
        } catch (Exception e) {
            throw new SevereException("Unhandled exception is caught", null, e);
        }

        return adminList;
    }

    /**
     * Method to get count of admin process in benefit for validation
     * 
     * @param adminMethodOverrideBO
     * @return List
     * @throws SevereException
     * @throws AdapterException
     */
    public List getSPSNamesForAdminMethodOverrideValidation(
            AdminMethodOverrideBO adminMethodOverrideBO)
            throws SevereException, AdapterException {
        AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();
        
        try {
        	return adapterManager
                    .getSPSNamesListForAdminMethodOverrideVAlidation(adminMethodOverrideBO);
        } catch (SevereException ex) {
            List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in getSPSNamesForAdminMethodOverrideValidation method, in AdminMethodBusinessObjectBuilder",
                    errorParams, ex);
        } catch (Exception e) {
            throw new SevereException("Unhandled exception is caught", null, e);
        }

    }
    /**
     * Method to sort admin method based on rank
     * 
     * @param allAdminMethod
     * @return List
     */
    public List eliminateDuplicateRecord(List allAdminMethod) {
        Map objectMap = new HashMap();
        Map rankMap = new HashMap();
        Set set = objectMap.keySet();
        Iterator iterator = set.iterator();
        List adminmethodList = null; //FIX ME Remove unwanted
        /*  ArrayList allAdminMethodfinal = new ArrayList();//FIX ME Move to where elements adding.
        if (null != allAdminMethod) {
            for (int i = 0; i < allAdminMethod.size(); i++) {
                AdminMethodsPopupBO adminMethodsPopupBO = (AdminMethodsPopupBO) allAdminMethod
                        .get(i);
                if (!objectMap.containsKey(String.valueOf(adminMethodsPopupBO
                        .getAdminMethodFilterCriteriaSysId()))) {
                    List objList = new ArrayList();
                    objList.add(adminMethodsPopupBO);
                    objectMap.put(String.valueOf(adminMethodsPopupBO
                            .getAdminMethodFilterCriteriaSysId()), objList); //FIX ME Use local variable
                } else {
                    List objList = (List) objectMap.get(String
                            .valueOf(adminMethodsPopupBO
                                    .getAdminMethodFilterCriteriaSysId()));
                    objList.add(adminMethodsPopupBO);
                }
            }

            Set set = objectMap.keySet();
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                if (rankMap.containsKey(String
                        .valueOf(rankAdminMethod(((List) objectMap.get(key)))))) {
                    List rankList = (List) rankMap
                            .get(String
                                    .valueOf(rankAdminMethod(((List) objectMap
                                            .get(key)))));
                    rankList.add(((List) objectMap.get(key)).get(0));
                } else {
                    List list = new ArrayList();
                    list.add(((List) objectMap.get(key)).get(0));
                    rankMap.put(
                            String.valueOf(rankAdminMethod(((List) objectMap
                                    .get(key)))), list);
                }
            }
            allAdminMethod = new ArrayList();
            
            
            for (int i = 5; i >= 0; i--) {
            	
                if (rankMap.containsKey(String.valueOf(i))) {
                    if (null != rankMap.get(String.valueOf(i))) {
                       allAdminMethod.addAll(((List) rankMap.get(String
                                .valueOf(i))));
                       List allAdminMethodRank = (List) rankMap.get(String
                            .valueOf(i));
                       for(int k=0;k<allAdminMethodRank.size();k++){ //FIX ME Null check
                       	AdminMethodsPopupBO adminMethodPop = new AdminMethodsPopupBO();//FIX ME Object craeted unnecessarily
                       	 adminMethodPop = 	(AdminMethodsPopupBO) allAdminMethodRank.get(k);
                       	 adminMethodPop.setRank(i);
                       	 allAdminMethodfinal.add(adminMethodPop);
                       	
                       	
                       }
                   //     break;
                    }
                }
            }*/
            Map hashMap = new HashMap();
            adminmethodList = new ArrayList();
            for (int i = 0; i < allAdminMethod.size(); i++)
                hashMap.put(new Integer(((AdminMethodsPopupBO) allAdminMethod
                        .get(i)).getAdminMethodId()), allAdminMethod.get(i));   //FIX ME Remove commented
            set = hashMap.keySet();
            iterator = set.iterator();
            while (iterator.hasNext()) {
                adminmethodList.add(hashMap.get((Integer) iterator.next()));
            }
        return adminmethodList;
    }
    /**
     * Method to get highest admin method based on rank
     * 
     * @param allAdminMethod
     * @return List
     */
    public List getAdminMethodBasedOnRank(List allAdminMethod) {
    	
    	  int highestRank = 0;
    	  List adminmethodList = new ArrayList();
    	  if (null != allAdminMethod) {
            for (int i = 0; i < allAdminMethod.size(); i++) {
            	AdminMethodsPopupBO adminMethodsPopupBO = (AdminMethodsPopupBO) allAdminMethod
                .get(i);
            	if(i==0)
            		highestRank = adminMethodsPopupBO.getCount();
            	if(adminMethodsPopupBO.getCount() == highestRank){
            		adminmethodList.add(adminMethodsPopupBO);
            	}else{
            		break;
            	}
            	
            }
    	  }
    	  adminmethodList = eliminateDuplicateRecord(adminmethodList);
    	  return adminmethodList;
    }
    /**
     * Method to sort admin method based on rank
     * 
     * @param allAdminMethod
     * @return List
     */
    public List sortAdminMethodBasedOnRank(List allAdminMethod) {
    	 Map objectMap = new HashMap();
         Map rankMap = new HashMap();
         List adminmethodList = null; //FIX ME Move to place where elements adding.
         if (null != allAdminMethod) {
             for (int i = 0; i < allAdminMethod.size(); i++) {
                 AdminMethodsPopupBO adminMethodsPopupBO = (AdminMethodsPopupBO) allAdminMethod
                         .get(i);
                 if (!objectMap.containsKey(String.valueOf(adminMethodsPopupBO
                         .getAdminMethodFilterCriteriaSysId()))) {
                     List objList = new ArrayList();
                     objList.add(adminMethodsPopupBO);
                     objectMap.put(String.valueOf(adminMethodsPopupBO
                             .getAdminMethodFilterCriteriaSysId()), objList);
                 } else {
                     List objList = (List) objectMap.get(String
                             .valueOf(adminMethodsPopupBO
                                     .getAdminMethodFilterCriteriaSysId())); //FIX ME Use local variable
                     objList.add(adminMethodsPopupBO);
                 }
             }

             Set set = objectMap.keySet();
             Iterator iterator = set.iterator();
             while (iterator.hasNext()) {
                 String key = (String) iterator.next();
                 if (rankMap.containsKey(String
                         .valueOf(rankAdminMethod(((List) objectMap.get(key)))))) {
                     List rankList = (List) rankMap
                             .get(String
                                     .valueOf(rankAdminMethod(((List) objectMap
                                             .get(key)))));
                     rankList.add(((List) objectMap.get(key)).get(0));
                 } else {
                     List list = new ArrayList();
                     list.add(((List) objectMap.get(key)).get(0));
                     rankMap.put(
                             String.valueOf(rankAdminMethod(((List) objectMap
                                     .get(key)))), list);
                 }
             }
             allAdminMethod = new ArrayList();
             for (int i = 5; i >= 0; i--) {
                 if (rankMap.containsKey(String.valueOf(i)) && null != rankMap.get(String.valueOf(i))) {
                     //if (null != rankMap.get(String.valueOf(i))) {
                         allAdminMethod.addAll(((List) rankMap.get(String
                                 .valueOf(i))));
                         break;
                     //}
                 }
             }
             Map hashMap = new HashMap();
             adminmethodList = new ArrayList();
             for (int i = 0; i < allAdminMethod.size(); i++) //FIX ME Null check
                 hashMap.put(new Integer(((AdminMethodsPopupBO) allAdminMethod
                         .get(i)).getAdminMethodId()), allAdminMethod.get(i));
             set = hashMap.keySet();
             iterator = set.iterator();
             while (iterator.hasNext()) {
                 adminmethodList.add(hashMap.get((Integer) iterator.next()));
             }

         }
         return adminmethodList;
    }

    /**
     * Method to rank Admin Method.
     * 
     * @param objList
     * @return int
     */
    private int rankAdminMethod(List objList) {

        return objList.size();
     }

    /**
     * Method to get benefit administration from benefit component for check in
     * validation .
     * 
     * @param benefitSysId
     * @param benefitComponentId
     * @return List
     * @throws SevereException
     * @throws AdapterException
     */
    public List getAdminstationIdFromBC(int benefitSysId, int benefitComponentId)
            throws SevereException, AdapterException {
        List adminList = null;

        AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();
        try {
            adminList = adapterManager.getBenefitAdministrationFromBC(
                    benefitSysId, benefitComponentId);
        } catch (SevereException ex) {
            List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in getAdminstationIdFromBC method, in AdminMethodBusinessObjectBuilder",
                    errorParams, ex);
        } catch (Exception e) {
            throw new SevereException("Unhandled exception is caught", null, e);
        }
        return adminList;
    }

    /**
     * Method to sps name for check in validation .
     * 
     * @param spsId
     * @return String
     * @throws SevereException
     * @throws AdapterException
     */
    public String getSpsName(int spsId) throws SevereException,
            AdapterException {

        AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();
        try {
            return adapterManager.getSpsName(spsId);
        } catch (SevereException ex) {
            List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in getSpsName method, in AdminMethodBusinessObjectBuilder",
                    errorParams, ex);
        } catch (Exception e) {
            throw new SevereException("Unhandled exception is caught", null, e);
        }
    }

    /**
     * Method to aggregated values
     * 
     * @param Qualifiers
     * @return list
     */
    public List getAggregatedValues(List Qualifiers) {
        List newList = new ArrayList();
        Iterator iterator = Qualifiers.iterator();
        while (iterator.hasNext()) {
            String value = ((AdminMethodsPopupBO) iterator.next())
                    .getQualifierCode();
            if (null == value || "".equalsIgnoreCase(value))
                continue;
            for (int i = 0; i < value.split(",").length; i++) {
                if (!newList.contains(value.split(",")[i]))
                    newList.add(value.split(",")[i]);
            }
        }
        return newList;
    }
    /**
     * Method to aggregated values
     * 
     * @param Qualifiers
     * @return list
     */
    public List getTermValues(List Qualifiers) {
        List newList = new ArrayList();
        Iterator iterator = Qualifiers.iterator();
        while (iterator.hasNext()) {
            String value = ((AdminMethodsPopupBO) iterator.next())
                    .getQualifierCode();
            if (null == value || "".equalsIgnoreCase(value))
                continue;
            newList.add(value);
        }
        return newList;
    }
    /**
     * Method to aggregated values
     * 
     * @param Qualifiers
     * @return list
     */
    public List getTermValuesForEntity(List terms) {
        List newList = new ArrayList();
        Iterator iterator = terms.iterator();
        while (iterator.hasNext()) {
            String value = ((AdminMethodsPopupBO) iterator.next())
                    .getTermCode();
            if (null == value || "".equalsIgnoreCase(value))
                continue;
            newList.add(value);
        }
        return newList;
    }

    /**
     * Method to aggregated values
     * 
     * @param Qualifiers
     * @return list
     */
    public List getAggregatedValueTerms(List Terms) {
        List newList = new ArrayList();
        Iterator iterator = Terms.iterator();
        while (iterator.hasNext()) {
            String value = ((AdminMethodsPopupBO) iterator.next())
                    .getTermCode();
            if (null == value || "".equalsIgnoreCase(value))
                continue;
            for (int i = 0; i < value.split(",").length; i++) {
                if (!newList.contains(value.split(",")[i]))
                    newList.add(value.split(",")[i]);
            }
            newList.add(value);
        }
        return newList;
    }

    
    /**
     * Method to retrieve answer override value
     * 
     * @param adminMethodOverrideBO
     * @return list
     * @throws SevereException
     * @throws AdapterException
     */
    public List getAnswerOverrideValue(
            AdminMethodAnswerOverrideBO adminMethodOverrideBO)
            throws SevereException, AdapterException {
        List answerOverrideList = null;

        AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();
        try {
            answerOverrideList = adapterManager
                    .getAnswerOverrideValue(adminMethodOverrideBO);
        } catch (SevereException ex) {
            List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in getAnswerOverrideValue method, in AdminMethodBusinessObjectBuilder",
                    errorParams, ex);
        } catch (Exception e) {
            throw new SevereException("Unhandled exception is caught", null, e);
        }
        return answerOverrideList;

    }

    /**
     * Method to retrieve answer override value for benefit
     * 
     * @param adminMethodOverrideBO
     * @return list
     * @throws SevereException
     * @throws AdapterException
     */
    public List getAnswerOverrideValueForBenefit(
            AdminMethodAnswerOverrideBO adminMethodOverrideBO)
            throws SevereException, AdapterException {
        List answerOverrideList = null;

        AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();
        try {
            answerOverrideList = adapterManager
                    .getAnswerOverrideValueForBenefit(adminMethodOverrideBO);
        } catch (SevereException ex) {
            List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in getAnswerOverrideValueForBenefit method, in AdminMethodBusinessObjectBuilder",
                    errorParams, ex);
        } catch (Exception e) {
            throw new SevereException("Unhandled exception is caught", null, e);
        }

        return answerOverrideList;

    }

    /**
     * Method to retrieve sps ids
     * 
     * @param answerList
     * @return list
     * @throws SevereException
     * @throws AdapterException
     */
    public List getSpsids(List answerList) throws SevereException,
            AdapterException {
        List spsIds = null;

        AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();
        try {
            spsIds = adapterManager.getspsIds(answerList);
        } catch (SevereException ex) {
            List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in getSpsids method, in AdminMethodBusinessObjectBuilder",
                    errorParams, ex);
        } catch (Exception e) {
            throw new SevereException("Unhandled exception is caught", null, e);
        }
        return spsIds;
    }

    /**
     * Method to retrieve admin methods
     * 
     * @param spsInfoList
     * @param spsIds
     * @param benComp
     * @param adminId
     * @param entityId
     * @return String
     * @throws SevereException
     * @throws AdapterException
     */
    public String getAdminMethods(List spsInfoList, List spsIds, int benComp,
            int adminId, int entityId) throws SevereException, AdapterException {
        List adminList = null;

        AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();
        try {
            adminList = adapterManager.getAdminMethods(spsIds, adminId,
                    benComp, entityId);
        } catch (SevereException ex) {
            List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in getAdminMethods method, in AdminMethodBusinessObjectBuilder",
                    errorParams, ex);
        } catch (Exception e) {
            throw new SevereException("Unhandled exception is caught", null, e);
        }
        List newSpsIdList = new ArrayList(); 
        for (int i = 0; i < adminList.size(); i++) { //FIX ME Null check
            AdminMethodAnswerOverrideBO adminMethodAnswerOverrideBO = (AdminMethodAnswerOverrideBO) adminList
                    .get(i);
            newSpsIdList.add(new Integer(adminMethodAnswerOverrideBO
                    .getAnswerId()));
        }
        for (int i = 0; i < spsIds.size(); i++) {//FIX ME Null check
            if (!newSpsIdList.contains(spsIds.get(i)))
                return ((AdminMethodAnswerOverrideBO) spsInfoList.get(i))
                        .getSpsName();
        }

        return null;
    }
    private List sortAdminMethodPopupBOs(List unsortedBos){
    	List sortedBOs = new ArrayList();
    	
    	if(null != unsortedBos && unsortedBos.size() > 0){
    		Iterator iterator = unsortedBos.iterator();
    		List comparableList = new ArrayList();
    		while(iterator.hasNext()){
    			comparableList.add(new AdminMethodPopupBOComaparable(
    					(AdminMethodsPopupBO)iterator.next() ));
    		}
    		Collections.sort(comparableList);
    		iterator = comparableList.iterator();
    		while(iterator.hasNext()){
    			sortedBOs.add(((AdminMethodPopupBOComaparable)iterator.next()).bo);
    		}
    		
    	}
    	return sortedBOs;
    }
    
    private class AdminMethodPopupBOComaparable implements Comparable{
    	
    	private AdminMethodsPopupBO bo;
    	
    	public AdminMethodPopupBOComaparable(AdminMethodsPopupBO bo){
    		this.bo = bo;
    	}
    

		/* (non-Javadoc)
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		public int compareTo(Object o) {
			AdminMethodPopupBOComaparable temp = (AdminMethodPopupBOComaparable)o;
			if(bo.getAdminMethodNumber() == temp.bo.getAdminMethodNumber()){
				return 0;
			}else if (bo.getAdminMethodNumber() > temp.bo.getAdminMethodNumber()){
				return 1;
			}
			return -1;
		}
    	
    }
    /**
     * 
     * @param entitySysId
     * @return
     * @throws SevereException
     * @throws AdapterException
     */
    public List validateAdminMethod(int entitySysId) throws SevereException, AdapterException {
    	
    	List messageList = null;		
    	
        AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();      
		
        try {
        	messageList = adapterManager.validateAdminMethod(entitySysId);
        } catch (SevereException ex) {
            List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in getAdminMethods method, in AdminMethodBusinessObjectBuilder",
                    errorParams, ex);
        } catch (Exception e) {
            throw new SevereException("Unhandled exception is caught", null, e);
        }		



        return messageList;
    }
    /**
     * Method to get the major headings i.e the benefit components list
     * @param productTreeBenefitComponents
     * @return
     * @throws WPDException
     */
    public List getBenefitComponents(AdminMethodValidationBO adminMethodValidationBO)  throws SevereException, AdapterException
    {
    	AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();
      return adapterManager.getBenefitComponents(adminMethodValidationBO);
    }
    
    /**
     * 
     * @param standardBenefitsDetails
     * @return
     * @throws WPDException
     */
    public List getStandardBenefits(AdminMethodValidationBO adminMethodValidationBO) throws SevereException, AdapterException
    {
    	AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();
      return adapterManager.getStandardBenefit(adminMethodValidationBO);
    }
    /**
     * 
     * @param standardBenefitsDetails
     * @return
     * @throws WPDException
     */
    public List getDateSegments(AdminMethodValidationBO adminMethodValidationBO) throws SevereException, AdapterException
    {
    	AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();
      return adapterManager.getDateSegments(adminMethodValidationBO);
    }
    
	/**
	 * @param entityType
	 * @param entityId
	 * @return
	 * @throws SevereException
	 */
    /**
	 * Name of the method is changed from getInvalidSPSListAndAsynchronousThreadFlag() to getInvalidSPSList()
	 */
	public List getInvalidSPSList(int entityId, String entityType,int contractId) throws SevereException {
		AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();  
		
		List invalidSPSList = null;
		// Call the adapter manager
		try {
			invalidSPSList = adapterManager.getInvalidSPSListAndAsynchronousThreadFlag(entityId, entityType, contractId);
		} catch (SevereException ex) {
            List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in getAdminMethods method, in AdminMethodBusinessObjectBuilder",
                    errorParams, ex);
        } catch (Exception e) {
            throw new SevereException("Unhandled exception is caught", null, e);
        }		
		return invalidSPSList;
	}
	




	/**
	 * @param adminMethodsPopupBO
	 * @return List
	 * @throws SevereException, AdapterException
	 */
	public List getAdminMethodsView(AdminMethodsPopupBO adminMethodsPopupBO)
				throws SevereException, AdapterException {
		AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();
		List adminMethods;
		try{
			adminMethods = adapterManager.getAdminMethodsView(adminMethodsPopupBO);
		}catch(SevereException ex){
			List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in getAdminMethodsView method, in AdminMethodBusinessObjectBuilder",
                    errorParams, ex);
		}catch (AdapterException e) {
            throw new SevereException("Unhandled exception is caught", null, e);
        }
		return adminMethods;
	}
//	private List getHightestRankedSubsetOfAdminMethods(List adminMethodList,HashMap quesMap){
//		List highestRankedList=new ArrayList();
//		AdminMethodsPopupBO adminMethodsPopupBOarr[]=new AdminMethodsPopupBO[adminMethodList.size()];
//		
//		for(int i=0;i<adminMethodsPopupBOarr.length;i++)
//			for(int j=0;j<adminMethodsPopupBOarr.length;j++){
//				if(adminMethodsPopupBOarr[i].getRank()<adminMethodsPopupBOarr[j].getRank()){
//					Object tmp=adminMethodsPopupBOarr[i];
//					adminMethodsPopupBOarr[i]=adminMethodsPopupBOarr[j];
//					adminMethodsPopupBOarr[j]=(AdminMethodsPopupBO)tmp;
//				}
//	    }
//		
//		for(int i=0;i<adminMethodsPopupBOarr.length;i++){
//			if(checkSubSet(quesMap,adminMethodsPopupBOarr[i].getAnswerList()))
//				highestRankedList.add(adminMethodsPopupBOarr[i]);
//			
//		}
//
//	}
//	private boolean checkSubSet(HashMap hashMap,String answerList){
//		
//		List aggList=new ArrayList();
//		for(int i=0;i<answerList.split(",").length;i++)
//			aggList.add(answerList.split(",")[i]);
//		
//		Set quesSet=hashMap.keySet();
//		Iterator iterator=quesSet.iterator();
//		while(iterator.hasNext()){
//			List possList=(List)hashMap.get(""+iterator.next());
//			
//			for(int i=0;i<aggList.size();i++){
//				if(possList.contains(aggList))		
//			}
//			
//		}
//		
//	}
	
	
	
	/**
	 *  To get the Admin Method validation status
	 * @param contractId
	 * @return
	 * @throws SevereException
	 */
	public int getAdminMethodSPSValidationStatus(int contractId) throws SevereException {
		AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();  
		List invalidSPS = null;
		int invalidSPSCnt=0;
		try {
			invalidSPS = adapterManager.getAdminMethodSPSValidationStatus(contractId);
			if(invalidSPS!=null && invalidSPS.size()>0){
				AdminMethodValidationBO adminMethodValidationBO=(AdminMethodValidationBO)invalidSPS.get(0);
				invalidSPSCnt=adminMethodValidationBO.getEntitySysId();
			}
		} catch (AdapterException ex) {
            throw new SevereException(
                    "Exception occured in getAdminMethods method, in AdminMethodBusinessObjectBuilder",
                    null, ex);
        } 		
		return invalidSPSCnt;
	}
	/**
	 *  To get the benefit Component information for the Tree
	 * @param adminMethodValidationBO
	 * @return
	 * @throws SevereException
	 */
	 public List getBenefitComponentsCodedSPS(AdminMethodValidationBO adminMethodValidationBO)  throws SevereException
	    {
	    	AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();
	      try {
			return adapterManager.getBenefitComponentsCodedSPS(adminMethodValidationBO);
	      }catch (AdapterException e) {
			throw new SevereException("SevereException occured at AdminMethodBusinessObjectBuilder", null, e);
		  }
	    }
	    /**
	     * To get the Standard Benefit information for the Tree
	     * @param standardBenefitsDetails
	     * @return
	     * @throws WPDException
	     */
	    public List getStandardBenefitsCodedSPS(AdminMethodValidationBO adminMethodValidationBO) throws SevereException
	    {
	    	AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();
	      try {
			return adapterManager.getStandardBenefitCodedSPS(adminMethodValidationBO);
	      } catch (AdapterException e) {
			   throw new SevereException("SevereException occured at AdminMethodBusinessObjectBuilder", null, e);
		  }
	    }
	    /**
	     * To get the Date Segment information for the Tree
	     * @param standardBenefitsDetails
	     * @return
	     * @throws WPDException
	     */
	   
	    public List getDateSegmentsForCodedSPS(AdminMethodValidationBO adminMethodValidationBO) throws SevereException
	    {
	    	AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();
			  try {
				return adapterManager.getDateSegmentsForCodedSPS(adminMethodValidationBO);
			  } catch (AdapterException e) {
			   throw new SevereException("SevereException occured at AdminMethodBusinessObjectBuilder", null, e);
			  }
	    }
	    
	    
	    /**
	     * This method returns the list of invalid SPS references-grouped by the SPS names
	     * @param adminMethodSPSValidationBO
	     * @return
	     * @throws SevereException
	     */
	    public List getAdminMethodSPSParameters(AdminMethodSPSValidationBO adminMethodSPSValidationBO) throws SevereException{
			AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();  
		
			List amParameterList;
			try {
				amParameterList = adapterManager.getAdminMethodSPSParameters(adminMethodSPSValidationBO);
				if(null!= amParameterList)
					amParameterList=orderSPSReference(amParameterList);
				
			} catch (AdapterException e) {
				throw new SevereException("SevereException occured at AdminMethodBusinessObjectBuilder", null, e);
			}
		return amParameterList;
		}
		/**
		 * Method is used to group the list of invalid SPS into groups based on the SPS names
		 * Accepts a list of AdminMethodSPSValidationBO
		 * Returns a list of BO which contains the value of SPS id, SPS Name, Admin method Description and admin method id
		 * The Bo also contains a Map whose key is AdmnMthdSpsGrpId and the value is a list of SPSParameterBO 
		 * SPSParameterBO contains the value of reference desciption, Type, value AdmnMthdReqSps and boolean to indicate whether it is coded or not. 
		 * @param adminMethodsPopupBO
		 * @return
		 */
	    /*CARS START 23/3/2010*/
	    public Map getTieredAdminMethodSPSParameters(AdminMethodSPSValidationBO adminMethodSPSValidationBO,List criteriaList) throws SevereException{
			AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();  
		    
			Map tierIdSPSSetMap = new HashMap();
			List spsListForTiers = new ArrayList(0); 
			List spsListForSpecificTier = new ArrayList(0);
			try {
				spsListForTiers= adapterManager.getTieredAdminMethodSPSParameters(adminMethodSPSValidationBO);
				Set tierSet = new HashSet(0);
				if(null!= spsListForTiers)
				{
					for(int i = 0 ; i< criteriaList.size() ; i++)
					{ 
						TierDefinitionBO definitionBO = (TierDefinitionBO)criteriaList.get(i);
						tierSet.add(String.valueOf(definitionBO.getTierSysId()));					  
					}
				}
				Iterator iter = tierSet.iterator();
				while (iter.hasNext())
				{
				   String tierSysIdStr = String.valueOf(iter.next());	
				   int tierSysId = new Integer(tierSysIdStr).intValue();	
				   List listForTier = orderSPSReference(spsListForTiers,tierSysId);
				   tierIdSPSSetMap.put(tierSysIdStr,listForTier);				   
				}
			} catch (AdapterException e) {
				throw new SevereException("SevereException occured at AdminMethodBusinessObjectBuilder", null, e);
			}
		return tierIdSPSSetMap;
		}
	    /*cars end 23/3/2010*/
	    private List orderSPSReference(List amParameterList){
			
			List fList= new ArrayList();

			List tempList= new ArrayList();
			int listSize=amParameterList.size();
			for(int listCount=0;listCount<listSize;listCount++){ // Iterate through the list of BO
				
				// Get the first object from the list
				AdminMethodSPSValidationBO adminMethodSPSValidationBO=(AdminMethodSPSValidationBO)amParameterList.get(listCount);
				
				// Checks whether this object is in the tempList
				if(!tempList.contains(new Integer(adminMethodSPSValidationBO.getSprPrcssStpSysId()))){
				
					tempList.add(new Integer(adminMethodSPSValidationBO.getSprPrcssStpSysId()));
				
				AdminMethodSPSValidationBO newAdminMethodSPSValidationBO= new AdminMethodSPSValidationBO();
				newAdminMethodSPSValidationBO.setSprPrcssStpNm(adminMethodSPSValidationBO.getSprPrcssStpNm());
				newAdminMethodSPSValidationBO.setAdmnMthdSysId(adminMethodSPSValidationBO.getAdmnMthdSysId());
				newAdminMethodSPSValidationBO.setAdmnMthdDesc(adminMethodSPSValidationBO.getAdmnMthdDesc());
				newAdminMethodSPSValidationBO.setSprPrcssStpSysId(adminMethodSPSValidationBO.getSprPrcssStpSysId());
				newAdminMethodSPSValidationBO.setProductFamily(adminMethodSPSValidationBO.getProductFamily());
				//	CARS START
				newAdminMethodSPSValidationBO.setMethodType(adminMethodSPSValidationBO.getMethodType());
				//	CARS END
				Map spsRefMap= new HashMap();
				
				SPSParameterBO spsParameterBO = new SPSParameterBO();
				spsParameterBO.setAdmnMthdReqSps(adminMethodSPSValidationBO.getAdmnMthdReqSps());
				spsParameterBO.setReferenceDesc(adminMethodSPSValidationBO.getReferenceDesc());
				spsParameterBO.setType(adminMethodSPSValidationBO.getType());
				spsParameterBO.setValue(adminMethodSPSValidationBO.getValue());			
				spsParameterBO.setCoded(checkCoded(spsParameterBO.getType(),spsParameterBO.getValue()));
				
				
				List mapList= new ArrayList();
				mapList.add(spsParameterBO);
				
				spsRefMap.put(new Integer(adminMethodSPSValidationBO.getAdmnMthdSpsGrpId()),mapList);
				
				newAdminMethodSPSValidationBO.setAmRefParamMap(spsRefMap);
				fList.add(newAdminMethodSPSValidationBO);
				}else{
					AdminMethodSPSValidationBO newAdminMethodSPSValidationBO= null;
					int returnListSize=fList.size();
					for(int tempCount=0;tempCount<returnListSize;tempCount++){
						newAdminMethodSPSValidationBO=(AdminMethodSPSValidationBO)fList.get(tempCount);
						if(newAdminMethodSPSValidationBO.getSprPrcssStpSysId()==adminMethodSPSValidationBO.getSprPrcssStpSysId()){
							break;
						}
					}
					Map spsRefMap= newAdminMethodSPSValidationBO.getAmRefParamMap();
					
						// Checks whether the group is already present or not
						if(spsRefMap.containsKey(new Integer(adminMethodSPSValidationBO.getAdmnMthdSpsGrpId()))){
							List list= (List)spsRefMap.get(new Integer(adminMethodSPSValidationBO.getAdmnMthdSpsGrpId()));
							boolean isPresent =false;
							int mapListSize=list.size();
							for(int j=0;j<mapListSize;j++){
								SPSParameterBO spsParameterBO = (SPSParameterBO)list.get(j);
								if(spsParameterBO.getAdmnMthdReqSps().equals(adminMethodSPSValidationBO.getAdmnMthdReqSps())){
									isPresent=true;
									if(!"ALL".equalsIgnoreCase(adminMethodSPSValidationBO.getType())
											&&checkCoded(adminMethodSPSValidationBO.getType(),adminMethodSPSValidationBO.getValue())){
										spsParameterBO.setType(adminMethodSPSValidationBO.getType());
										spsParameterBO.setValue(adminMethodSPSValidationBO.getValue());
										spsParameterBO.setCoded(checkCoded(adminMethodSPSValidationBO.getType(), adminMethodSPSValidationBO.getValue()));
									}
									break;
								}
							}
							if(!isPresent){
								
								SPSParameterBO spsParameterBO = new SPSParameterBO();
								spsParameterBO.setAdmnMthdReqSps(adminMethodSPSValidationBO.getAdmnMthdReqSps());
								spsParameterBO.setCoded(checkCoded(adminMethodSPSValidationBO.getType(), adminMethodSPSValidationBO.getValue()));
								spsParameterBO.setReferenceDesc(adminMethodSPSValidationBO.getReferenceDesc());
								spsParameterBO.setType(adminMethodSPSValidationBO.getType());
								spsParameterBO.setValue(adminMethodSPSValidationBO.getValue());
								list.add(spsParameterBO);				
							}
						}else{
							// The group is not present
							SPSParameterBO spsParameterBO = new SPSParameterBO();
							spsParameterBO.setAdmnMthdReqSps(adminMethodSPSValidationBO.getAdmnMthdReqSps());
							spsParameterBO.setReferenceDesc(adminMethodSPSValidationBO.getReferenceDesc());
							spsParameterBO.setType(adminMethodSPSValidationBO.getType());
							spsParameterBO.setValue(adminMethodSPSValidationBO.getValue());			
							spsParameterBO.setCoded(checkCoded(spsParameterBO.getType(),spsParameterBO.getValue()));
							
							List mapList= new ArrayList();
							mapList.add(spsParameterBO);
							
							spsRefMap.put(new Integer(adminMethodSPSValidationBO.getAdmnMthdSpsGrpId()),mapList);
							
						
						}
				}
			
			}
			
		return fList;
		}
	    /**/
	    private List orderSPSReference(List amParameterList,int tierSysId){
			
			List fList= new ArrayList();

			List tempList= new ArrayList();
			int listSize=amParameterList.size();
			for(int listCount=0;listCount<listSize;listCount++){ // Iterate through the list of BO
				
				// Get the first object from the list
				AdminMethodSPSValidationBO adminMethodSPSValidationBO=(AdminMethodSPSValidationBO)amParameterList.get(listCount);
				if(adminMethodSPSValidationBO.getTierSysId() == tierSysId)
				{
					// Checks whether this object is in the tempList
					if(!tempList.contains(new Integer(adminMethodSPSValidationBO.getSprPrcssStpSysId()))){
					
						tempList.add(new Integer(adminMethodSPSValidationBO.getSprPrcssStpSysId()));
					
					AdminMethodSPSValidationBO newAdminMethodSPSValidationBO= new AdminMethodSPSValidationBO();
					newAdminMethodSPSValidationBO.setSprPrcssStpNm(adminMethodSPSValidationBO.getSprPrcssStpNm());
					newAdminMethodSPSValidationBO.setAdmnMthdSysId(adminMethodSPSValidationBO.getAdmnMthdSysId());
					newAdminMethodSPSValidationBO.setAdmnMthdDesc(adminMethodSPSValidationBO.getAdmnMthdDesc());
					newAdminMethodSPSValidationBO.setSprPrcssStpSysId(adminMethodSPSValidationBO.getSprPrcssStpSysId());
					newAdminMethodSPSValidationBO.setProductFamily(adminMethodSPSValidationBO.getProductFamily());
					newAdminMethodSPSValidationBO.setTierSysId(adminMethodSPSValidationBO.getTierSysId());
					//	CARS START
					newAdminMethodSPSValidationBO.setMethodType(adminMethodSPSValidationBO.getMethodType());
					//	CARS END
					Map spsRefMap= new HashMap();
					
					SPSParameterBO spsParameterBO = new SPSParameterBO();
					spsParameterBO.setAdmnMthdReqSps(adminMethodSPSValidationBO.getAdmnMthdReqSps());
					spsParameterBO.setReferenceDesc(adminMethodSPSValidationBO.getReferenceDesc());
					spsParameterBO.setType(adminMethodSPSValidationBO.getType());
					spsParameterBO.setValue(adminMethodSPSValidationBO.getValue());			
					spsParameterBO.setCoded(checkCoded(spsParameterBO.getType(),spsParameterBO.getValue()));
					
					
					List mapList= new ArrayList();
					mapList.add(spsParameterBO);
					
					spsRefMap.put(new Integer(adminMethodSPSValidationBO.getAdmnMthdSpsGrpId()),mapList);
					
					newAdminMethodSPSValidationBO.setAmRefParamMap(spsRefMap);
					fList.add(newAdminMethodSPSValidationBO);
					}else{
						AdminMethodSPSValidationBO newAdminMethodSPSValidationBO= null;
						int returnListSize=fList.size();
						for(int tempCount=0;tempCount<returnListSize;tempCount++){
							newAdminMethodSPSValidationBO=(AdminMethodSPSValidationBO)fList.get(tempCount);
							if(newAdminMethodSPSValidationBO.getSprPrcssStpSysId()==adminMethodSPSValidationBO.getSprPrcssStpSysId()){
								break;
							}
						}
						Map spsRefMap= newAdminMethodSPSValidationBO.getAmRefParamMap();
						
							// Checks whether the group is already present or not
							if(spsRefMap.containsKey(new Integer(adminMethodSPSValidationBO.getAdmnMthdSpsGrpId()))){
								List list= (List)spsRefMap.get(new Integer(adminMethodSPSValidationBO.getAdmnMthdSpsGrpId()));
								boolean isPresent =false;
								int mapListSize=list.size();
								for(int j=0;j<mapListSize;j++){
									SPSParameterBO spsParameterBO = (SPSParameterBO)list.get(j);
									if(spsParameterBO.getAdmnMthdReqSps().equals(adminMethodSPSValidationBO.getAdmnMthdReqSps())){
										isPresent=true;
										if(!"ALL".equalsIgnoreCase(adminMethodSPSValidationBO.getType())
												&&checkCoded(adminMethodSPSValidationBO.getType(),adminMethodSPSValidationBO.getValue())){
											spsParameterBO.setType(adminMethodSPSValidationBO.getType());
											spsParameterBO.setValue(adminMethodSPSValidationBO.getValue());
											spsParameterBO.setCoded(checkCoded(adminMethodSPSValidationBO.getType(), adminMethodSPSValidationBO.getValue()));
										}
										break;
									}
								}
								if(!isPresent){
									
									SPSParameterBO spsParameterBO = new SPSParameterBO();
									spsParameterBO.setAdmnMthdReqSps(adminMethodSPSValidationBO.getAdmnMthdReqSps());
									spsParameterBO.setCoded(checkCoded(adminMethodSPSValidationBO.getType(), adminMethodSPSValidationBO.getValue()));
									spsParameterBO.setReferenceDesc(adminMethodSPSValidationBO.getReferenceDesc());
									spsParameterBO.setType(adminMethodSPSValidationBO.getType());
									spsParameterBO.setValue(adminMethodSPSValidationBO.getValue());
									list.add(spsParameterBO);				
								}
							}else{
								// The group is not present
								SPSParameterBO spsParameterBO = new SPSParameterBO();
								spsParameterBO.setAdmnMthdReqSps(adminMethodSPSValidationBO.getAdmnMthdReqSps());
								spsParameterBO.setReferenceDesc(adminMethodSPSValidationBO.getReferenceDesc());
								spsParameterBO.setType(adminMethodSPSValidationBO.getType());
								spsParameterBO.setValue(adminMethodSPSValidationBO.getValue());			
								spsParameterBO.setCoded(checkCoded(spsParameterBO.getType(),spsParameterBO.getValue()));
								
								List mapList= new ArrayList();
								mapList.add(spsParameterBO);
								
								spsRefMap.put(new Integer(adminMethodSPSValidationBO.getAdmnMthdSpsGrpId()),mapList);
								
							
							}
					}
				}
			}
			
		return fList;
		}
	    /**/
		
		
		/**
		 * Checks whether the reference attached to the benefit line or question is coded or not
		 * @param type
		 * @param value
		 * @return
		 */
		private boolean checkCoded(String type,String value){
			
			if("Benefit Line".equalsIgnoreCase(type)&& value!=null&&!"".equalsIgnoreCase(value.trim())){
				return true;
			}else if("Question".equalsIgnoreCase(type)&& value!=null&&!"".equalsIgnoreCase(value.trim())
					&& ! "NOT ANSWERED".equalsIgnoreCase(value.trim())){
				return true;
			}
			return false;
		}
	    
   
}