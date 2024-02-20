/*
 * <LocateSpsIdServiceImpl.java>
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
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;
import com.wellpoint.ets.bx.mapping.domain.exception.MappingLockedByAnotherUserException;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.service.LockService;
import com.wellpoint.ets.bx.mapping.domain.service.ValidationUtility;
import com.wellpoint.ets.bx.mapping.domain.vo.MappingResult;
import com.wellpoint.ets.bx.mapping.repository.HippaSegmentRepositoryImpl;
import com.wellpoint.ets.ebx.mapping.repository.ItemRepository;
import com.wellpoint.ets.ebx.mapping.repository.LocateSpsIdRepositoryImpl;
import com.wellpoint.ets.ebx.mapping.repository.MappingSpsIdRepositoryImpl;
/**
 * @author UST-GLOBAL
 * This is an implementation class for locating or searching a SPS id 
 * or multiple SPS id, based on certain search criteria
 */
public class LocateSpsIdServiceImpl implements LocateService {
	
	private LocateSpsIdRepositoryImpl  locateSpsIdRepository;
	
	private MappingSpsIdRepositoryImpl mappingSpsIdRepository;
	
	private ItemRepository itemRepository;
	
	private LockService lockSpsIdService;
	
	// Reference Data Values -  START
	private HippaSegmentRepositoryImpl hippaSegmentRepository;
	// Reference Data Values -  END
	
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
	 */

	public List getRecords(Mapping mapping, List status, String loggedInUser,int noOfRecords, int auditTrailCount, Page page) {
		
		return locateSpsIdRepository.getRecords(mapping, status, loggedInUser, noOfRecords, auditTrailCount, page);

	}
	/**
	 * After the mapping is in published status, when the user clicks on edit, 
	 * this method will insert record in temp table and used for updating the temp
	 * We are not calling the LocateRepository's getRecordForUpdate.
	 * @param mapping
	 * @return Mapping
	 */
	public MappingResult getRecordsForUpdate(Mapping mapping) {
		
		Mapping mappingForUpdate = null;
		MappingResult result = new MappingResult();
		try{
			lockSpsIdService.validateUserLock(mapping);
		 }catch (MappingLockedByAnotherUserException e){			 	
	        List errorMessages = new ArrayList();	
	        errorMessages.add(e.getMessage());
	        result.setErrorMsgsList(errorMessages);
	        result.setStatus(false);
	        result.setMapping(mapping);
			return result;
		 }
		mappingSpsIdRepository.update(mapping, "getRecordsForUpdate",DomainConstants.INSERT_TEMP_OPERATION);
		ArrayList mappingList = (ArrayList)locateSpsIdRepository.getRecords(mapping,null,mapping.getUser().getLastUpdatedUserName(),-1, 21, null);
		if(!mappingList.isEmpty()) { 
			mappingForUpdate = (Mapping)mappingList.get(0);
		}
		result.setStatus(true);
		result.setMapping(mappingForUpdate);
		return  result;
	}
	/**
	 * @return LocateSpsIdRepositoryImpl
	 */
	public LocateSpsIdRepositoryImpl getLocateSpsIdRepository() {
		return locateSpsIdRepository;
	}
	/**
	 * @param locateSpsIdRepository
	 */
	public void setLocateSpsIdRepository(
			LocateSpsIdRepositoryImpl locateSpsIdRepository) {
		this.locateSpsIdRepository = locateSpsIdRepository;
	}
	/**
	 * @return MappingSpsIdRepositoryImpl
	 */
	public MappingSpsIdRepositoryImpl getMappingSpsIdRepository() {
		return mappingSpsIdRepository;
	}
	/**
	 *  @param mappingSpsIdRepositoryImpl
	 */
	public void setMappingSpsIdRepository(
			MappingSpsIdRepositoryImpl mappingSpsIdRepository) {
		this.mappingSpsIdRepository = mappingSpsIdRepository;
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
		return locateSpsIdRepository.getRecordCount(mapping, status);
	}
	public LockService getLockSpsIdService() {
		return lockSpsIdService;
	}
	public void setLockSpsIdService(LockService lockSpsIdService) {
		this.lockSpsIdService = lockSpsIdService;
	}
	public MappingResult getRecordsForUpdate(Mapping mapping, List status, String loggedInUser, int noOfRecords, int auditTrailCount, Page page) {
		
		MappingResult result = new MappingResult();
		Mapping mappingForUpdate = null;
		try {
		lockSpsIdService.validateUserLock(mapping);
		}
		catch (MappingLockedByAnotherUserException e) {
			 List errorMessages = new ArrayList();	
		        errorMessages.add(e.getMessage());
		        result.setErrorMsgsList(errorMessages);
		        result.setStatus(false);
		        result.setMapping(mapping);
				return result;
		}
		
		ArrayList mappingList = (ArrayList)locateSpsIdRepository.getRecords(mapping,status,loggedInUser,noOfRecords, auditTrailCount, page);
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
		return locateSpsIdRepository.getRecordsForReport(mapping, status);
	}

	public Mapping autopopulateBySPSFormat(HippaSegment hippaSegment,
			Mapping mapping) {

		List spsFormatsList = new ArrayList();
		List hippaSegmentDescription = new ArrayList();
		Catalog catalog = new Catalog();
		String spsFormat = "";
		String hippaCodeValue = "";
		String hippaVal = "";
		String hippaDesc = "";
		HippaCodeValue hippaCodeValueFromResult = new HippaCodeValue();
		HippaSegment eb09 = new HippaSegment();
		List hippaCodeSelectedValue = new ArrayList();
		ValidationUtility validationUtility = new ValidationUtility();
		if (null != mapping && null != mapping.getSpsId()
				&& null != mapping.getSpsId().getSpsDetail()) {
			if (null != hippaSegment && null != hippaSegment.getName()) {
				catalog.setCatalogName(hippaSegment.getName());
				spsFormatsList = validationUtility
						.getSpsFormatsFromSPSDetail(mapping.getSpsId()
								.getSpsDetail());
				hippaSegmentDescription = getItems(catalog, null, false,
						spsFormatsList);
				Iterator spsFormatsIterator = spsFormatsList.iterator();
				while (spsFormatsIterator.hasNext()) {
					spsFormat = (String) spsFormatsIterator.next();
					if (spsFormat.equalsIgnoreCase(DomainConstants.VST)
							|| spsFormat
									.equalsIgnoreCase(DomainConstants.VISIT)
							|| spsFormat
									.equalsIgnoreCase(DomainConstants.VISITS)) {
						hippaCodeValue = DomainConstants.VS;
						break;
					} else if (spsFormat
							.equalsIgnoreCase(DomainConstants.HOUR_HR)
							|| spsFormat.equalsIgnoreCase(DomainConstants.HRS)
							|| spsFormat.equalsIgnoreCase(DomainConstants.HOUR)
							|| spsFormat
									.equalsIgnoreCase(DomainConstants.HOURS)) {
						hippaCodeValue = DomainConstants.HS;
						break;
					} else if (spsFormat.equalsIgnoreCase(DomainConstants.DAY)
							|| spsFormat.equalsIgnoreCase(DomainConstants.DAYS)
							|| spsFormat.equalsIgnoreCase(DomainConstants.LEN)
							|| spsFormat
									.equalsIgnoreCase(DomainConstants.DAY_DYS)
							|| spsFormat
									.equalsIgnoreCase(DomainConstants.DAY_DY)) {
						hippaCodeValue = DomainConstants.DY;
						break;
					} else if (spsFormat.equalsIgnoreCase(DomainConstants.MTH)
							|| spsFormat.equalsIgnoreCase(DomainConstants.MTHS)
							|| spsFormat
									.equalsIgnoreCase(DomainConstants.MONTH)
							|| spsFormat
									.equalsIgnoreCase(DomainConstants.MONTHS)) {
						hippaCodeValue = DomainConstants.MN;
						break;
					} else if (spsFormat.equalsIgnoreCase(DomainConstants.YR)
							|| spsFormat.equalsIgnoreCase(DomainConstants.YRS)) {
						hippaCodeValue = DomainConstants.YY;
						break;
					}
				}
				if (!hippaCodeValue.equalsIgnoreCase("")) {
					Iterator hippaSegmentDescriptionIterator = hippaSegmentDescription
							.iterator();
					while (hippaSegmentDescriptionIterator.hasNext()) {
						hippaCodeValueFromResult = (HippaCodeValue) hippaSegmentDescriptionIterator
								.next();
						hippaVal = hippaCodeValueFromResult.getValue();
						if (hippaVal.equalsIgnoreCase(hippaCodeValue)) {
							hippaCodeSelectedValue
									.add(hippaCodeValueFromResult);
							eb09.setHippaCodeSelectedValues(hippaCodeSelectedValue);
							mapping.setEb09(eb09);
							return mapping;

						}
					}
				}
			}

		}
		return mapping;
	}
	
	public Map<String,String> fetchHippaSegmentDescription(String hippaSegmentName){
		return hippaSegmentRepository.fetchHippaSegmentDescription(hippaSegmentName);
	}
}
	
	
	
		

