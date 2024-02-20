/*
 * CommandFactoryImpl.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.framework.bo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.wpd.business.search.command.Command;
import com.wellpoint.wpd.business.search.command.DetailCommand;
import com.wellpoint.wpd.business.search.command.SummaryCommand;
import com.wellpoint.wpd.common.search.criteria.AdvancedSearchCriteria;
import com.wellpoint.wpd.common.search.criteria.AdvancedSearchObject;
import com.wellpoint.wpd.common.search.criteria.BasicAttribute;
import com.wellpoint.wpd.common.search.criteria.BasicSearchCriteria;
import com.wellpoint.wpd.common.search.criteria.BasicSearchObject;
import com.wellpoint.wpd.common.search.result.BenefitComponentIdentifier;
import com.wellpoint.wpd.common.search.result.BenefitIdentifier;
import com.wellpoint.wpd.common.search.result.BenefitLevelIdentifier;
import com.wellpoint.wpd.common.search.result.BenefitLineIdentifier;
import com.wellpoint.wpd.common.search.result.ContractIdentifier;
import com.wellpoint.wpd.common.search.result.NotesDetail;
import com.wellpoint.wpd.common.search.result.NotesIdentifier;
import com.wellpoint.wpd.common.search.result.ProductIdentifier;
import com.wellpoint.wpd.common.search.result.ProductStructureIdentifier;
import com.wellpoint.wpd.common.search.util.SearchConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class CommandFactoryImpl extends ObjectFactory implements CommandFactory {

	/**
	 * @see com.wellpoint.wpd.business.framework.bo.CommandFactory#getCommand(com.wellpoint.wpd.common.search.criteria.BasicSearchCriteria)
	 * @param basicSearchCriteria
	 * @return
	 */
	public Command[] getCommands(BasicSearchCriteria basicSearchCriteria) {
		List commands = new ArrayList();	
		int index=0;
		SummaryCommand command= null;
		List searchObjList = basicSearchCriteria.getBasicSearchObjects();
		if(searchObjList == null || searchObjList.size() == 0){
			//TODO throw exception 
		}	
		List attributes;
		BasicAttribute attribute = null;
		List queries = null;
		Iterator searchObjIterator = searchObjList.iterator();
		while(searchObjIterator.hasNext()){
			BasicSearchObject searchObject = (BasicSearchObject)searchObjIterator.next();
			if(searchObject.isChecked()){
				command = new SummaryCommand();	
				command.setLimitedTo(basicSearchCriteria.getLimitedTo());
				command.setObjectType(searchObject.getType());
				command.setSearchType(SearchConstants.BASIC_SEARCH);
				command.setResultCountLimit(basicSearchCriteria.getResultCountLimit());
				attribute = basicSearchCriteria.getBasicAttribute();
				
				attributes = new ArrayList();
				attributes.add(attribute);
				command.setAttributes(attributes);
				
				commands.add(command);
				index++;
			}
		}
		return  (Command[])commands.toArray(new Command[0]);
	}
	
	public Command[] getCommands(List objectIdentifiers){
		if( objectIdentifiers != null &&
				objectIdentifiers.size() > 0){
			Command[] commands = new Command[1];
			
			DetailCommand command = new DetailCommand();
			command.setObjectIdentifiers(objectIdentifiers);
			command.setObjectType(getTypeOfObject(objectIdentifiers.get(0)));			
			commands[0]=command;
			return commands;
		}
		
		return null;
	}

	/**
	 * @param objectIdentifier
	 * @return
	 */
	private String getTypeOfObject(Object objectIdentifier) {
		if(objectIdentifier instanceof ProductIdentifier)
			return SearchConstants.PRODUCT;
		else if(objectIdentifier instanceof ContractIdentifier)
			return SearchConstants.CONTRACT;
		else if(objectIdentifier instanceof BenefitComponentIdentifier)
			return SearchConstants.BENEFIT_COMPONENTS;
		else if(objectIdentifier instanceof NotesDetail)
			return SearchConstants.NOTES;
		else if(objectIdentifier instanceof ProductStructureIdentifier)
			return SearchConstants.PRODUCT_STRUCTURES;
		else if(objectIdentifier instanceof BenefitIdentifier)
			return SearchConstants.BENEFIT;
		else if(objectIdentifier instanceof BenefitLineIdentifier)
			return SearchConstants.BENEFIT_LINE;
		else if(objectIdentifier instanceof BenefitLevelIdentifier)
			return SearchConstants.BENEFIT_LEVEL;
		else if(objectIdentifier instanceof NotesIdentifier)
				return SearchConstants.NOTES;
		return null;
	}
	public Command[] getCommands(AdvancedSearchCriteria advancedSearchCriteria) {
		List commands = new ArrayList();	
		int index=0;
		SummaryCommand command= null;
		List searchObjList = advancedSearchCriteria.getAdvancedSearchObjects();
		if(searchObjList == null || searchObjList.size() == 0){
			//TODO throw exception 
		}	
		List attributes;
		BasicAttribute attribute = null;
		List queries = null;
		Iterator searchObjIterator = searchObjList.iterator();
		while(searchObjIterator.hasNext()){
			AdvancedSearchObject searchObject = (AdvancedSearchObject)searchObjIterator.next();
			if(searchObject.isChecked()){
				command = new SummaryCommand();	
				command.setLimitedTo(advancedSearchCriteria.getLimitedTo());
				command.setObjectType(searchObject.getType());
				command.setSearchType(SearchConstants.ADVANCED_SEARCH);
				command.setResultCountLimit(advancedSearchCriteria.getResultCountLimit());
				attributes = new ArrayList();
				attributes.addAll(searchObject.getAdvancedAttributes());
				command.setAttributes(attributes);
				commands.add(command);
				index++;
			}
		}
		return  (Command[])commands.toArray(new Command[0]);
	}
	
	/**
	 * Gets the command array for Notes
	 * @param identifier
	 * @return
	 */
	public Command[] getCommands(NotesIdentifier identifier,List authModules) {
		
		if(null != identifier && null != authModules && authModules.size() > 0){
			List identifierList = new ArrayList();
			identifierList.add(identifier);
			Command[] commands = new Command[authModules.size()];
			DetailCommand command = null;
			Iterator iterator = authModules.iterator();
			int i=0;
			while(iterator.hasNext()){
				String objectType = (String)iterator.next();
				
				command = new DetailCommand();
				command.setObjectType(objectType);
				command.setObjectIdentifiers(identifierList);
				command.setAttachment(true);
				commands[i] = command;
				i++;
			}
			return commands;
		}
		
		return null;
	}
	

	public List getSearchKeysForAttachments(){
		List keys = new ArrayList();
		keys.add(SearchConstants.CONTRACT);
		keys.add(SearchConstants.PRODUCT);
		keys.add(SearchConstants.BENEFIT_COMPONENTS);
		keys.add(SearchConstants.BENEFIT);
		keys.add(SearchConstants.BENEFIT_LEVEL);
		
		return keys;
	}
}
