/*
 * <LocateRuleIdServiceImpl.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
*/
package com.wellpoint.ets.ebx.mapping.domain.service;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wellpoint.ets.bx.mapping.domain.entity.Catalog;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.Item;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Page;
import com.wellpoint.ets.bx.mapping.domain.exception.MappingLockedByAnotherUserException;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.service.LockService;
import com.wellpoint.ets.bx.mapping.domain.vo.MappingResult;
import com.wellpoint.ets.bx.mapping.repository.HippaSegmentRepositoryImpl;
import com.wellpoint.ets.ebx.mapping.repository.ItemRepository;
import com.wellpoint.ets.ebx.mapping.repository.LocateRepository;
import com.wellpoint.ets.ebx.mapping.repository.MappingRuleIdRepositoryImpl;


/**
 * @author UST-GLOBAL
 * This is an implementation class for locating or searching a Rule id 
 * or multiple Rule id, based on certain search criteria
 */
public class LocateRuleIdServiceImpl implements LocateService {
	private LocateRepository locateRuleIdRepository;
	private MappingRuleIdRepositoryImpl mappingRuleIdRepository;
	private ItemRepository itemRepository;
	private LockService lockRuleIdService;
	// Reference Data Values -  START
	private HippaSegmentRepositoryImpl hippaSegmentRepository;
	// Reference Data Values -  END
	/**
	 * for retrieving all hipaa code possible values 
	 * @param catalog
	 * @return List
	 */

	/**
	 * for retrieving hipaa code values starting with the searchString. 
	 * Flag can be used to indicate whether to retrieve all the records or limit it to 50
	 * (depends on how many records we need to display)
	 * @param catalog
	 * @param searchString
	 * @param flag
	 * @return List
	 */
	public List getItems(Catalog catalog, String searchString, boolean limitFlag,List formats) {
		/* Reference Data Values -  START*/
				
		List items = null;
		int noOfRecords = 0;
		if(limitFlag) {
			noOfRecords =  20;
		}
				
		if (null != catalog && null != catalog.getCatalogName()) {
			String hippaSegmentName = catalog.getCatalogName();
			if(null != searchString) {
				searchString = searchString.trim();
			}
			if (catalog.getCatalogName().equals(DomainConstants.EB01_NAME)
					&& (null != formats)) {
				items = hippaSegmentRepository
						.getAllEB01ForAutocomplete(
								searchString, noOfRecords);
			} else if (catalog.getCatalogName().equals(
					DomainConstants.ACCUM_REF_NAME)) {
				items = itemRepository.getItems(catalog, searchString,
						limitFlag, formats);
				List hippacodePossibleList = new ArrayList();
				 if(null != items) {
					for(Iterator rsltIterator = items.iterator();rsltIterator.hasNext();){
						HippaCodeValue hippaCodeValue = new HippaCodeValue();
						Item item = (Item)rsltIterator.next();
						hippaCodeValue.setValue(item.getPrimaryCode());
						hippaCodeValue.setDescription(item.getSecondaryCode());
						hippacodePossibleList.add(hippaCodeValue);
					}
					items = hippacodePossibleList;
				}
			} else {
				items = hippaSegmentRepository
						.getMatchingHippaCodeValuesFromDataDictionaryForAutoComplete(
								hippaSegmentName, searchString, noOfRecords);
			}
		} 
		return items;
	}
		
	public HippaSegmentRepositoryImpl getHippaSegmentRepository() {
		return hippaSegmentRepository;
	}

	public void setHippaSegmentRepository(
			HippaSegmentRepositoryImpl hippaSegmentRepository) {
		this.hippaSegmentRepository = hippaSegmentRepository;
	}
	/* Reference Data Values -  END*/
	
	/**
	 * For getting exact match/retrieving records based on the status and logged in user
	 * @param Mapping
	 * @param status
	 * @param loggedInUser
	 * @return List
	 * 
	 */
	public List getRecords(Mapping mapping, List status, String loggedInUser,int noOfRecords, int auditTrailCount, Page page) {

		return locateRuleIdRepository.getRecords(mapping, status, loggedInUser, noOfRecords, auditTrailCount, page);
	}
	/**
	 * After the mapping is in published status, when the user clicks on edit, 
	 * this method will insert record in temp table and used for updating the temp
	 * @param mapping
	 * @return Mapping
	 */
	public MappingResult getRecordsForUpdate(Mapping mapping) {
		MappingResult result = new MappingResult();
		try{
			lockRuleIdService.validateUserLock(mapping);
		 }catch (MappingLockedByAnotherUserException e){			 	
	        List errorMessages = new ArrayList();	
	        errorMessages.add(e.getMessage());
	        result.setErrorMsgsList(errorMessages);
	        result.setStatus(false);
	        result.setMapping(mapping);
			return result;
		 }
		boolean status = mappingRuleIdRepository.update(mapping, "getRecordsForUpdate",DomainConstants.INSERT_TEMP_OPERATION);
		result.setStatus(status);
		return result;
	}
	/**
	 * 
	 * @return RuleId
	 */
	public LocateRepository getLocateRuleIdRepository() {
		return locateRuleIdRepository;
	}
	/**
	 * 
	 * @param locateRuleIdRepository
	 */
	public void setLocateRuleIdRepository(LocateRepository locateRuleIdRepository) {
		this.locateRuleIdRepository = locateRuleIdRepository;
	}
	/**
	 * 
	 * @return MappingObject
	 */
	public MappingRuleIdRepositoryImpl getMappingRuleIdRepository() {
		return mappingRuleIdRepository;
	}
	
	/**
	 * 
	 * @param mappingRuleIdRepository
	 */
	public void setMappingRuleIdRepository(
			MappingRuleIdRepositoryImpl mappingRuleIdRepository) {
		this.mappingRuleIdRepository = mappingRuleIdRepository;
	}
	/**
	 * 
	 * @return itemRepository
	 */
	public ItemRepository getItemRepository() {
		return itemRepository;
	}
	/**
	 * 
	 * @param itemRepository
	 */
	public void setItemRepository(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}
	public int getRecordCount(Mapping mapping, List status) {
		
		return locateRuleIdRepository.getRecordCount(mapping, status);
	}
	public LockService getLockRuleIdService() {
		return lockRuleIdService;
	}
	public void setLockRuleIdService(LockService lockRuleIdService) {
		this.lockRuleIdService = lockRuleIdService;
	}
	public MappingResult getRecordsForUpdate(Mapping mapping, List status, String loggedInUser, int noOfRecords, int auditTrailCount, Page page) {
		MappingResult result = new MappingResult();
		Mapping mappingForUpdate = null;
		try {
			lockRuleIdService.validateUserLock(mapping);
		}
		catch (MappingLockedByAnotherUserException e) {
			 List errorMessages = new ArrayList();	
		        errorMessages.add(e.getMessage());
		        result.setErrorMsgsList(errorMessages);
		        result.setStatus(false);
		        result.setMapping(mapping);
				return result;
		}
		ArrayList mappingList = (ArrayList)locateRuleIdRepository.getRecords(mapping,status,loggedInUser,noOfRecords, auditTrailCount, page);
		if(!mappingList.isEmpty()) { 
			mappingForUpdate = (Mapping)mappingList.get(0);
		}
		result.setStatus(true);
		result.setMapping(mappingForUpdate);
		return  result;
	}
	/**
	 * for generating reports from locate
	 * @param mapping
	 * @return List
	 */
	public List getRecordsForReport(Mapping mapping, List status) {
		return locateRuleIdRepository.getRecordsForReport(mapping, status);
	}
	/**Autopopulating HippaSegments in Rule
	 * 
	 */
	public Mapping autopopulateBySPSFormat(HippaSegment hippaSegment, Mapping mapping){		
		return mapping;
	}
	
	/* (non-Javadoc)
	 * @see com.wellpoint.ets.ebx.mapping.domain.service.LocateService#fetchHippaSegmentDescription(java.lang.String)
	 */
	public Map<String,String> fetchHippaSegmentDescription(String hippaSegmentName){
		return hippaSegmentRepository.fetchHippaSegmentDescription(hippaSegmentName);
	}
}
