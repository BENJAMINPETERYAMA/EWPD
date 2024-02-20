/*
 * BusinessUtil.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.PropertyAccessException;

import com.wellpoint.wpd.business.common.util.DomainUtil;
import com.wellpoint.wpd.business.contract.builder.ContractBusinessObjectBuilder;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.framework.bo.ReferenceDataFactory;
import com.wellpoint.wpd.common.contract.bo.Contract;
import com.wellpoint.wpd.common.contract.bo.IMAGEReadyBusinessDomainBO;
import com.wellpoint.wpd.common.domain.bo.Domain;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.refdata.domain.ReferenceData;
import com.wellpoint.wpd.common.framework.refdata.domain.ReferenceDataSet;
import com.wellpoint.wpd.common.framework.util.StringUtil;
import com.wellpoint.wpd.common.override.benefit.bo.Questionnaire;
import com.wellpoint.wpd.common.question.bo.PossibleAnswerBO;
import com.wellpoint.wpd.web.util.WPDStringUtil;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BusinessUtil {
    
	public static List getQuestionareHierarchy(List questionsList) {
		final int ROOT_LEVEL = 1;
		List questionsListCpy = new ArrayList();
		if(questionsList == null || questionsList.size() == 0)
			return questionsListCpy;
		validate(questionsList);
		questionsListCpy.addAll(questionsList);
		List rootQuestions = getRootQuestions(questionsListCpy);
		List orderedList = new ArrayList();
		
		for(int i=0,size = rootQuestions.size(); i<size; i++) {
			Questionnaire rootQuestion = (Questionnaire)rootQuestions.get(i);
			rootQuestion.setLevel(ROOT_LEVEL);
			loadQuestionareHrchy(orderedList,rootQuestion,questionsListCpy);
		}
		return orderedList;
	}
	
	private static void loadQuestionareHrchy(List orderedList, Questionnaire parent, List questionsList) {
		orderedList.add(parent);
		
		List childs = getChilds(parent, questionsList);
		parent.setChildCount(childs.size());
		
		for(int i=0, size = childs.size(); i<size; i++) {
			Questionnaire child = (Questionnaire)childs.get(i);
			child.setLevel(parent.getLevel()+1);
			loadQuestionareHrchy(orderedList,child,questionsList);
		}
	}
	
	private static List getRootQuestions(List questionsList) {
		List rootQuestions = new ArrayList();
		
		for(int i=0, size = questionsList.size(); i<size; i++) {
			Questionnaire questionnaire = (Questionnaire)questionsList.get(i);
			if(questionnaire.getQuestionnaireId() == questionnaire.getParentQuestionnaireId()) {
				rootQuestions.add(questionnaire);
			}
		}
		return rootQuestions;
	}
	
	private static List getChilds( Questionnaire parent, List questions) {
		List childs = new ArrayList();
		for(int i=0,size = questions.size(); i<size; i++) {
			Questionnaire question = (Questionnaire)questions.get(i);
			if(question.getParentQuestionnaireId() == parent.getQuestionnaireId() && (question.getParentQuestionnaireId() != question.getQuestionnaireId()))
				childs.add(question);
		}
		return childs;
	}
	
	private static void validate(List list) {
		for(int i=0, size = list.size(); i < size; i++) {
			if(! (list.get(i) instanceof Questionnaire) )
				throw new IllegalArgumentException("The objects in the List for creating Questionnaire hierarchy should extend from Questionnaire");
		}
	}
	
    public static String escpeSpecialCharacters(String value){
        value = value.replaceAll("`","``");
        value = value.replaceAll("_","`_");
        value = value.replaceAll("%","`%");
        return value;
    }
  
    public static List convertToDomains(List lineOfBusiness, List businessEntity, List businessGroup, List marketBusinessUnit) 
    {
    	return convertToDomains( lineOfBusiness,  businessEntity,  businessGroup, marketBusinessUnit, true); 
    }
    
    
    
    public static List convertToDomains(List lineOfBusiness, List businessEntity, List businessGroup, List marketBusinessUnit, boolean checkUniversal) {

        Iterator iter1, iter2, iter3, iter4;
        String lineOfBusinessId, businessEntityId, businessGroupId, marketBusinessUnitId;
        List domainList = new ArrayList();
        Domain domainBO;

        if(lineOfBusiness == null || businessEntity == null || businessGroup == null || marketBusinessUnit == null)
            return domainList;
        
        modifyDomainValues(lineOfBusiness, businessEntity, businessGroup, marketBusinessUnit, checkUniversal);
        
        for (iter1 = lineOfBusiness.iterator(); iter1.hasNext();) {
            lineOfBusinessId = (String) iter1.next();
            for (iter2 = businessEntity.iterator(); iter2.hasNext();) {
                businessEntityId = (String) iter2.next();
                for (iter3 = businessGroup.iterator(); iter3.hasNext();) {
                    businessGroupId = (String) iter3.next();
                    for(iter4 = marketBusinessUnit.iterator(); iter4.hasNext(); ){
                    	marketBusinessUnitId = (String)iter4.next();
	                    domainBO = new Domain();
	                    domainBO.setLineOfBusiness(lineOfBusinessId);
	                    domainBO.setBusinessEntity(businessEntityId);
	                    domainBO.setBusinessGroup(businessGroupId);
	                    domainBO.setMarketBusinessUnit(marketBusinessUnitId);
	                    domainList.add(domainBO);
                    }
                }               
            }            
        }
        Collections.sort(domainList);
        return domainList;
    }
    
   
  /*  private static void modifyDomainValues(List lineOfBusiness, List businessEntity, List businessGroup)
    {
    	modifyDomainValues( lineOfBusiness,  businessEntity,  businessGroup, true);
    }*/
	private static void modifyDomainValues(List lineOfBusiness, List businessEntity, List businessGroup, List marketBusinessUnit, boolean checkUniversal) {
		Iterator iter = null;
		String item = null;
		for (iter = lineOfBusiness.iterator(); iter.hasNext();) {
			item = (String) iter.next();
			if (checkUniversal && BusinessConstants.UNIVERSAL_DOMAIN.equals(item)) {
				lineOfBusiness.clear();
				lineOfBusiness.add(BusinessConstants.UNIVERSAL_DOMAIN);
				break;
			}
		}
		for (iter = businessEntity.iterator(); iter.hasNext();) {
			item = (String) iter.next();
			if (checkUniversal && BusinessConstants.UNIVERSAL_DOMAIN.equals(item)) {
				businessEntity.clear();
				businessEntity.add(BusinessConstants.UNIVERSAL_DOMAIN);
				break;
			}
		}
		for (iter = businessGroup.iterator(); iter.hasNext();) {
			item = (String) iter.next();
			if (checkUniversal && BusinessConstants.UNIVERSAL_DOMAIN.equals(item)) {
				businessGroup.clear();
				businessGroup.add(BusinessConstants.UNIVERSAL_DOMAIN);
				break;
			}
		}
		for (iter = marketBusinessUnit.iterator(); iter.hasNext();) {
			item = (String) iter.next();
			if (checkUniversal && BusinessConstants.UNIVERSAL_DOMAIN.equals(item)) {
				marketBusinessUnit.clear();
				marketBusinessUnit.add(BusinessConstants.UNIVERSAL_DOMAIN);
				break;
			}
		}
	}
	
	public static List getLobList(List domains){
	    List lobList = new ArrayList();
	    if(null != domains && !domains.isEmpty()){
		    for (Iterator iter = domains.iterator(); iter.hasNext();) {
	            Domain element = (Domain) iter.next();
	            lobList.add(element.getLineOfBusiness());
		    }
	    }
	    return lobList;
	}
	public static List getbusinessEntityList(List domains){
	    List businessEntityList = new ArrayList();
	    if(null != domains && !domains.isEmpty()){
		    for (Iterator iter = domains.iterator(); iter.hasNext();) {
	            Domain element = (Domain) iter.next();
	            businessEntityList.add(element.getBusinessEntity());
	        }
	    }
	    return businessEntityList;
	}
	public static List getBusinessGroupList(List domains){
	    List businessGroupList = new ArrayList();
	    if(null != domains && !domains.isEmpty()){
		    for (Iterator iter = domains.iterator(); iter.hasNext();) {
	            Domain element = (Domain) iter.next();
	            businessGroupList.add(element.getBusinessGroup());
	        }
	    }
	    return businessGroupList;
	}
	public static List getMarketBusinessUnitList(List domains){
	    List marketBusinessUnitList = new ArrayList();
	    if(null != domains && !domains.isEmpty()){
		    for (Iterator iter = domains.iterator(); iter.hasNext();) {
	            Domain element = (Domain) iter.next();
	            marketBusinessUnitList.add(element.getMarketBusinessUnit());
	        }
	    }
	    return marketBusinessUnitList;
	}

    /**
     * @param businessDomains
     * @param businessDomains2
     * @return
     */
    public static boolean isBuisnessDomainsEqual(List businessDomains, List oldBusinessDomains) {
       if(businessDomains != null && oldBusinessDomains!= null ){
           if(businessDomains.size() != oldBusinessDomains.size())
               return false;
           Collections.sort(businessDomains);
           Collections.sort(oldBusinessDomains);
           Iterator keyIter = businessDomains.iterator();
           Iterator oldKeyIter = oldBusinessDomains.iterator();
           while(keyIter.hasNext()){
               Domain domainBO = (Domain) keyIter.next(); 
               Domain oldDomain = (Domain) oldKeyIter.next();
               if(domainBO.compareTo(oldDomain) != 0)
                   return false;
           }
       }
        return true;
    }
    
    public static List getRearrangedQuestionnareList(List childList,List oldQuestionnareList,int reaArrangedQuestIndex){
    	List finalList = new ArrayList();
    	Questionnaire parentQuesitionnaireBO = 
			(Questionnaire)oldQuestionnareList.get(reaArrangedQuestIndex);
    	parentQuesitionnaireBO.setChildCount(childList.size());
    	int tierSysId =parentQuesitionnaireBO.getTierSysId();
    	int parentLevel =parentQuesitionnaireBO.getLevel();
    	int indexStart = reaArrangedQuestIndex+1;
    	for(int i=indexStart;i<oldQuestionnareList.size();i++){
    		Questionnaire oldQuesitionnaireBO = 
    						(Questionnaire)oldQuestionnareList.get(i);
    		if(oldQuesitionnaireBO.getLevel()>parentQuesitionnaireBO.getLevel()){
    			oldQuestionnareList.remove(i);
    			i--;
    		}else{
    			break;
    		}
    	}
    	for(int j=0;j<childList.size();j++){
    		Questionnaire childQuesitionnaireBO = 
    			(Questionnaire)childList.get(j);
    	childQuesitionnaireBO.setLevel(parentLevel+1);
    	if(0!=tierSysId){
    		childQuesitionnaireBO.setTierSysId(tierSysId);
    	}
    	oldQuestionnareList.add(indexStart,childQuesitionnaireBO);
    	indexStart++;
    	}
    	return oldQuestionnareList;
    }
    /**
     * This method will rearrange the possible answer list and make "Not Answered" as first answer.
     * 
     * @ param possibleAnsweList
     * @ return  arrangedList
     * 
     */
    public static List getRearrangedPossibleAnswerList(List possibleAnswerList){
    	
    	List arrangedList = new ArrayList();
    	
    	if(null!=possibleAnswerList ){
    		int listSize = possibleAnswerList.size();
    		
    		List tempAnswerList = new ArrayList();
    		Iterator it = possibleAnswerList.iterator();
    		while(it.hasNext()){
    			PossibleAnswerBO possibleAnswerBO = (PossibleAnswerBO)it.next();
    			tempAnswerList.add(possibleAnswerBO);
    		}

    		for (int i=0;i<listSize;i++)
    		{
    			PossibleAnswerBO answerBO =(PossibleAnswerBO)tempAnswerList.get(i);
    			if(answerBO.getPossibleAnswerDesc().equals(BusinessConstants.NOT_ANSWERED)&& arrangedList.size()==0){
    				arrangedList.add(answerBO);
    				tempAnswerList.remove(i);
    				arrangedList.addAll(tempAnswerList);
    				break;
    			}
    			
    		}
    	}
    	return arrangedList;
    }
    /**
     * This method forms benefit level description for a level
     * Description is a combination of term frequency and qualifier.
     * @param term
     * @param qualifier
     * @param frequency
     * @return description
     */
    public static String formDescription(String term,String qualifier,int frequency){
    	String description = null;
    	
    	if(!StringUtil.isEmpty(term))
    		term = WPDStringUtil.commaSeparatedString(term);    		
    	
    	if(!StringUtil.isEmpty(qualifier)){//Null check for qualifier
    		
    		qualifier = WPDStringUtil.commaSeparatedString(qualifier);
    		
			if(frequency == BusinessConstants.INT_1){
				description = term +" PER "+ qualifier;//Combining term frequency and qualifer
			}else if(frequency != BusinessConstants.VALUE_ZERO){//Checking frequency value not 0
				description = term +" PER "+ frequency + BusinessConstants.SPACE_STRING + qualifier;//Combining term frequency and qualifer
			}else{
				if(qualifier.startsWith("PER "))
					description = term +BusinessConstants.SPACE_STRING+ qualifier;//Combining term and qualifier
				else
					description = term +" PER "+ qualifier;//Combining term and qualifier
			}
		}else{
			description = term;
		}
    	
		if(description.length() > 32)//checking for description length greater than 32 bytes
			description = description.substring(0,32).trim();
		
    	return description;
    }
    /**
     * Method checks whether the description is system generated or not
     * @param description
     * @param term
     * @param qualifier
     * @param frequency
     * @return boolean
     */
	public static boolean isSystemGeneratedDescription(String description, String term,String qualifier, int frequency){
		String formedDescriptionValue;
		formedDescriptionValue = formDescription(term,qualifier,frequency);
		if(formedDescriptionValue.equalsIgnoreCase(description))
			return true;
		return false;
	}
	
	/**
	 * Method for populating the list with values as group rule ids
	 * @param groupRuleId Group rule id to store
	 * @param groupRuleIds List to hold the group rule details
	 */
	public static void populateGroupRuleList(String groupRuleId,List groupRuleIds){
		
		if(null != groupRuleId&& null != groupRuleIds && !groupRuleIds.contains(groupRuleId)){
			groupRuleIds.add(groupRuleId);
		}
		
	}
	
	public static boolean isReadyForImageRewrite(Contract contract) throws SevereException{
		List busDomainLst = contract.getBusinessDomains();
		List imageReadyDomains;

		ContractBusinessObjectBuilder businessObjectBuilder = new ContractBusinessObjectBuilder();

		imageReadyDomains = businessObjectBuilder.getIMAGEReadyBusinessDomains();
		Iterator domainItr = busDomainLst.iterator();
		if(imageReadyDomains != null && !imageReadyDomains.isEmpty()){
			while(domainItr.hasNext()){
				Domain domain = (Domain) domainItr.next();
				domain = DomainUtil.retrieveDomain(domain);
				Iterator imageDomainItr = imageReadyDomains.iterator();
				while (imageDomainItr.hasNext()){
					IMAGEReadyBusinessDomainBO domainBO = (IMAGEReadyBusinessDomainBO)imageDomainItr.next();
					if (domainBO.getDomainSysID()==domain.getDomainSysId())
						return true;
				}
			}
		}
		return false;
	}
}
