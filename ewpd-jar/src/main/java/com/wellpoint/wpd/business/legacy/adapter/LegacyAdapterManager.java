/*
 * LegacyAdapterManager.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.legacy.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.util.StringUtil;
import com.wellpoint.wpd.common.legacycontract.bo.CP2000Contract;
import com.wellpoint.wpd.common.legacycontract.bo.CP2000ContractLock;
import com.wellpoint.wpd.common.legacycontract.bo.CP2000ContractUpdate;
import com.wellpoint.wpd.common.legacycontract.bo.CP2000LimitClassExclusion;
import com.wellpoint.wpd.common.legacycontract.bo.CP2000MajorHeading;
import com.wellpoint.wpd.common.legacycontract.bo.CP2000MinorHeading;
import com.wellpoint.wpd.common.legacycontract.bo.CP2000Pricing;
import com.wellpoint.wpd.common.legacycontract.bo.CP2000ServiceClassExclusion;
import com.wellpoint.wpd.common.legacycontract.bo.CP2000ServiceCodeExclusion;
import com.wellpoint.wpd.common.legacycontract.bo.CP2000Variable;
import com.wellpoint.wpd.common.legacycontract.bo.ContractLock;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyContract;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyContractMajorHeading;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyContractMinorHeading;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyContractNotes;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyMajorNotes;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyMinorNotes;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyVariable;
import com.wellpoint.wpd.common.legacycontract.bo.SpecialityCodeExclusion;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id:
 */
public class LegacyAdapterManager {
	/**
	 * 
	 * @param variable
	 * @return
	 * @throws SevereException
	 */
	public List getVariables(LegacyVariable variable) throws SevereException{
		HashMap map = new HashMap();
		map.put("contractId", variable.getContractId());
		map.put("startDate", getStringDate(variable.getStartDate()));
		map.put("minorHeadingId", variable.getMinorHeadingId());
		if(variable.getVariableId() == null)
			variable.setVariableId("");
		map.put("variableId", variable.getVariableId()+"%");
		if(!StringUtil.isEmpty(variable.getFormat())){
			map.put("format", variable.getFormat().toUpperCase());
		}else{
			map.put("format", "%");
		}
		map.put("providerArrangement", "%");
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(CP2000Variable.class.getName(), "getContracMinorHeadingVariables", map);
		SearchResults searchResults =AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}
	
	public void scheduleContractToTest(LegacyContract contract, AdapterServicesAccess txn) throws SevereException{
		CP2000ContractUpdate cp2000Contract = new CP2000ContractUpdate(contract);
		AdapterUtil.performUpdate(cp2000Contract, cp2000Contract.getLastUpdatedUser(), txn);
	}
	
	public void changeContractStatus(LegacyContract contract, String contractStatusForSC, AdapterServicesAccess txn) throws SevereException{
		CP2000ContractUpdate cp2000Contract = new CP2000ContractUpdate(contract);
		cp2000Contract.setContractStatusForSC(contractStatusForSC);
		if(contract.getContractStat().equals(BusinessConstants.STATUS_MIGRATION_IN_PROGRESS)){
			cp2000Contract.setRowStatus(2);
		}else{
			cp2000Contract.setRowStatus(1);			
		}

		AdapterUtil.performUpdate(cp2000Contract, cp2000Contract.getLastUpdatedUser(), txn);
	}
	
	public List getPartialDateSegments(String contractId) throws SevereException{
		HashMap map = new HashMap();
		map.put("contractId", contractId);
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(CP2000Contract.class.getName(), "getPartialDatesegments", map);
		SearchResults searchResults =AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}
	
	/**
	 * 
	 * @param majorHeadingId
	 * @param minorHeadingText
	 * @return
	 * @throws SevereException
	 */	
	public List getMinorHeadings(String majorHeadingId, String minorHeadingText) throws SevereException{
		HashMap map = new HashMap();
		map.put("majorHeadingId", majorHeadingId);
		map.put("minorHeadingText", minorHeadingText);
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(CP2000MinorHeading.class.getName(), "getMajorAsssociatedMinHeadings", map);
		SearchResults searchResults =AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}
	/**
	 * 
	 * @param structureId
	 * @param majorHeadingText
	 * @return
	 * @throws SevereException
	 */	
	public List getMajorHeadings(String structureId, String majorHeadingText) throws SevereException{
		HashMap map = new HashMap();
		map.put("structureId", structureId);
		map.put("majorHeadingText", majorHeadingText);
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(CP2000MajorHeading.class.getName(), "getStructureMajHeadings", map);
		SearchResults searchResults =AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}
	/**
	 * 
	 * @param contractId
	 * @return
	 * @throws SevereException
	 */
	public boolean isContractLocked(String contractId) throws SevereException{
		CP2000ContractLock contractLock = new CP2000ContractLock();
		contractLock.setContractId(contractId);
		contractLock = (CP2000ContractLock)AdapterUtil.performRetrieve(contractLock);
		if(null == contractLock)
			return false;
		else
			return true;
	}
	/**
	 * 
	 * @param contractId
	 * @param adapterServicesAccess
	 * @return
	 * @throws SevereException
	 */
	public boolean isContractLocked(String contractId, AdapterServicesAccess adapterServicesAccess) throws SevereException{
		CP2000ContractLock contractLock = new CP2000ContractLock();
		contractLock.setContractId(contractId);
		contractLock = (CP2000ContractLock)AdapterUtil.performRetrieve(contractLock, adapterServicesAccess);
		if(null == contractLock)
			return false;
		else
			return true;
	}	
	/**
	 * 
	 * @param contractId
	 * @param user
	 * @return
	 * @throws SevereException
	 */
	public boolean isContractLockedBySameUser(String contractId, String user) throws SevereException{
		HashMap map = new HashMap();
		map.put("contractId", contractId);
		map.put("lockedUser", user);
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(CP2000ContractLock.class.getName(), "contractLockedBySameUser", map);
		List searchResultList = AdapterUtil.performSearch(searchCriteria).getSearchResults();
		if(null != searchResultList && !searchResultList.isEmpty()){
			return true;			
		}else{
			return false;			
		}
	}
	/**
	 * 
	 * @param contractId
	 * @return
	 * @throws SevereException
	 */
	public ContractLock retrieveLock(String contractId) throws SevereException {
		CP2000ContractLock contractLock = new CP2000ContractLock();
		contractLock.setContractId(contractId);
		contractLock = (CP2000ContractLock)AdapterUtil.performRetrieve(contractLock);
		return contractLock;
	}
	/**
	 * 
	 * @param contractId
	 * @param userId
	 * @throws SevereException
	 */
	public void removeLock(String contractId, String userId) throws SevereException{
		CP2000ContractLock lock = new CP2000ContractLock();
		lock.setContractId(contractId);
		AdapterUtil.performRemove(lock, userId);
	}
	/**
	 * 
	 * @param contractId
	 * @param userId
	 * @param access
	 * @throws SevereException
	 */
	public void removeLock(String contractId, String userId, AdapterServicesAccess access) throws SevereException{
		CP2000ContractLock lock = new CP2000ContractLock();
		lock.setContractId(contractId);
		AdapterUtil.performRemove(lock, userId,access);
	}
	/**
	 * 
	 * @param contractId
	 * @param lockUserId
	 * @param userId
	 * @throws SevereException
	 */
	public void persistLock(String contractId, String lockUserId, String userId) throws SevereException{
		ContractLock contractLock = new CP2000ContractLock();
		contractLock.setContractId(contractId);
		contractLock.setCreatedUser(userId);
		contractLock.setLastUpdatedUser(userId);
		contractLock.setCreatedTimestamp(new Date());
		contractLock.setLastUpdatedTimestamp(new Date());
		contractLock.setLockedUser(lockUserId);
		contractLock.setLockedTime(new Date());
		AdapterUtil.performInsert(contractLock, userId);
	}
	/**
	 * 
	 * @param contractId
	 * @param lockUserId
	 * @param userId
	 * @param txn
	 * @throws SevereException
	 */
	public void persistLock(String contractId, String lockUserId, String userId, AdapterServicesAccess txn) throws SevereException{
		ContractLock contractLock = new CP2000ContractLock();
		contractLock.setContractId(contractId);
		contractLock.setCreatedUser(userId);
		contractLock.setLastUpdatedUser(userId);
		contractLock.setCreatedTimestamp(new Date());
		contractLock.setLastUpdatedTimestamp(new Date());
		contractLock.setLockedUser(lockUserId);
		contractLock.setLockedTime(new Date());
		AdapterUtil.performInsert(contractLock, userId, txn);
	}
	
	/**
	 * 
	 * @param contractId
	 * @return
	 * @throws SevereException
	 */
	public boolean isContractIdValid(String contractId, String contractStatus) throws SevereException{
		SearchResults seResults = searchContract(contractId,null, contractStatus, "checkContract");
		if(seResults.getSearchResultCount()>0)
			return true;
		return false;
	}
	/**
	 * 
	 * @param contractId
	 * @param contractStatus
	 * @param adapterServicesAccess
	 * @return
	 * @throws SevereException
	 */
	public boolean isContractIdValid(String contractId, String contractStatus, AdapterServicesAccess adapterServicesAccess) throws SevereException{
		SearchResults seResults = searchContract(contractId,null, contractStatus, "checkContract", adapterServicesAccess);
		if(seResults.getSearchResultCount()>0)
			return true;
		return false;
	}
	
	/**
	 * 
	 * @param contractId
	 * @param startDate
	 * @return
	 * @throws SevereException
	 */
	public String getBenefitYearAccumIndicator (String contractId, Date startDate) throws SevereException{
		SearchResults seResults = searchContract(contractId, startDate,null,"benefitYearAccumIndicator");
	    List result = seResults.getSearchResults();
	    if(null != result && !result.isEmpty()){
	        return ((CP2000Contract)result.get(0)).getBenefitYearAccumInd();
	    }
	    return WebConstants.EMPTY_STRING;
	}
	/**
	 * 
	 * @param contractId
	 * @return
	 * @throws SevereException
	 */
	public List getContracts(String contractId, String contractStatus) throws SevereException {
		return searchContract(contractId, null, contractStatus, "getAllDateSegments").getSearchResults();
	}

	/**
	 * 
	 * @param contractId
	 * @return
	 * @throws SevereException
	 */
	public CP2000Contract getLatestDS(String contractId, String contractStatus) throws SevereException {
		CP2000Contract contract = null;
		List list  = searchContract(contractId,null, contractStatus, "getLatestDS").getSearchResults();
		if(null != list && list.size() > 0) {
			contract = (CP2000Contract)list.get(0);
		}
		return contract;
	}
	/**
	 * 
	 * @param contractId
	 * @return
	 * @throws SevereException
	 */
	public List getAllDS(String contractId, String contractStatus) throws SevereException {
		List list  = getContracts(contractId,contractStatus);
		return list;
	}
	
	/**
	 * 
	 * @param contractId
	 * @return
	 * @throws SevereException
	 */
	public List getLeftOverDS(String contractId) throws SevereException {
		HashMap map = new HashMap();
		map.put("contractId", contractId);
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(CP2000Contract.class.getName(), "getLeftOverDateSegments", map);
		SearchResults searchResults =AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}
	/**
	 * 
	 * @param contractId
	 * @return
	 * @throws SevereException
	 */
	public CP2000Contract getDS(String contractId, Date startDate ,String contractStatus) throws SevereException {
		CP2000Contract contract = null;
		List list  = searchContract(contractId, startDate, contractStatus, "getDS").getSearchResults();
		if(null != list && list.size() > 0) {
			contract = (CP2000Contract)list.get(0);
		}
		return contract;
	}
	/**
	 * 
	 * @param contractId
	 * @return
	 * @throws SevereException
	 */
	public CP2000Contract getFirstDS(String contractId) throws SevereException {
		CP2000Contract contract = null;
		List list  = searchContract(contractId,null,"%","getFirstDS").getSearchResults();
		if(list.size() > 0) {
			contract = (CP2000Contract)list.get(0);
		}
		return contract;
	}
	/**
	 * 
	 * @param contractId
	 * @return
	 * @throws SevereException
	 *///FIXME not in use
	public CP2000Contract getSecondLastDS(String contractId) throws SevereException {
		CP2000Contract contract = null;
		List list  = searchContract(contractId,null,null,"getSecondLastDS").getSearchResults();
		if(list.size() > 0) {
			contract = (CP2000Contract)list.get(0);
		}
		return contract;
	}
	/**
	 * 
	 * @param contract
	 * @return
	 * @throws SevereException
	 */
	public List getContractPricing(LegacyContract contract) throws SevereException {
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(CP2000Pricing.class.getName(), "getAllpricing", getMapForContract(contract));
		return AdapterUtil.performSearch(searchCriteria).getSearchResults();
	}
	/**
	 * 
	 * @param contract
	 * @return
	 * @throws SevereException
	 */
	public List getServiceClassExclusions(LegacyContract contract) throws SevereException {
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(CP2000ServiceClassExclusion.class.getName(), "getServiceClass", getMapForContract(contract));
		return AdapterUtil.performSearch(searchCriteria).getSearchResults();
	}
	/**
	 * 
	 * @param contract
	 * @return
	 * @throws SevereException
	 */
	public List getServiceCodeExclusions(LegacyContract contract) throws SevereException {
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(CP2000ServiceCodeExclusion.class.getName(), "getServiceCodes", getMapForContract(contract));
		return AdapterUtil.performSearch(searchCriteria).getSearchResults();
	}
	/**
	 * 
	 * @param contract
	 * @return
	 * @throws SevereException
	 */
	public List getLimitClassExclusions(LegacyContract contract) throws SevereException {
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(CP2000LimitClassExclusion.class.getName(), "getLimitClasses", getMapForContract(contract));
		return AdapterUtil.performSearch(searchCriteria).getSearchResults();
	}
	/**
	 * 
	 * @param contract
	 * @return
	 * @throws SevereException
	 */
	public List getSpecialityCodeExclns(LegacyContract contract) throws SevereException {
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(SpecialityCodeExclusion.class.getName(), "getSpecialityCodeExlns", getMapForContract(contract));
		return AdapterUtil.performSearch(searchCriteria).getSearchResults();
	}
	/**
	 * 
	 * @param contract
	 * @return
	 * @throws SevereException
	 */
	public List getCodedVariables(LegacyContract contract) throws SevereException {
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(CP2000Variable.class.getName(), "getCodedVariablesForDS", getMapForContract(contract));
		return AdapterUtil.performSearch(searchCriteria).getSearchResults();
	}
	/**
	 * 
	 * @param contract
	 * @return
	 */
	private HashMap getMapForContract(LegacyContract contract){
		HashMap map = new HashMap();
		map.put("contractId", contract.getContractId());
		map.put("startDate", getStringDate(contract.getStartDate()));
		return map;
	}
	/**
	 * 
	 * @param contractId
	 * @return
	 * @throws SevereException
	 */	
	public boolean isContractScheduleToTest(String contractId) throws SevereException{
//		SearchResults seResults = searchContract(contractId,null,"getScheduleToTestContracts");
		SearchResults seResults = searchContract(contractId,null, "Schedule to Test", "checkContract");
		if(seResults.getSearchResultCount()>0)
			return true;
		return false;
	}
	/**
	 * 
	 * @param contractId
	 * @return
	 * @throws SevereException
	 */
	public boolean isContractTransferredToProduction(String contractId) throws SevereException{
//		SearchResults seResults = searchContract(contractId,null,"getTransferredToProductionContracts");
		SearchResults seResults = searchContract(contractId, null, "Transferred to Production", "checkContract");
		if(seResults.getSearchResultCount()>0)
			return true;
		return false;
	}
	
	/**
	 * 
	 * @param contractId
	 * @return
	 * @throws SevereException
	 */
	public boolean isContractScheduledToProduction(String contractId) throws SevereException{
//		SearchResults seResults = searchContract(contractId,null,"getScheduleToProductionContracts");
		SearchResults seResults = searchContract(contractId, null, "Schedule to Production", "checkContract");
		if(seResults.getSearchResultCount()>0)
			return true;
		return false;
	}
	/**
	 * 
	 * @param contractId
	 * @return
	 * @throws SevereException
	 */
	public boolean isBenefitStructureAssociated(String contractId) throws SevereException{
		SearchResults seResults = searchContract(contractId,null,null,"getcontractsNotHavingStructure");
		if(seResults.getSearchResultCount() >0)
			return false;
		return true;
	}
	/**
	 * 
	 * @param contractId
	 * @param startDate
	 * @param queryName
	 * @return
	 * @throws SevereException
	 */
	private SearchResults searchContract(String contractId, Date startDate, String contractStatus, String queryName) throws SevereException{
		HashMap map = new HashMap();
		map.put("contractId", contractId);
		map.put("startDate", getStringDate(startDate));
		map.put("contractStat",contractStatus);
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(CP2000Contract.class.getName(), queryName, map);
		SearchResults searchResults =AdapterUtil.performSearch(searchCriteria);
		return searchResults;
	}
	/**
	 * 
	 * @param contractId
	 * @param startDate
	 * @param contractStatus
	 * @param queryName
	 * @param adapterServicesAccess
	 * @return
	 * @throws SevereException
	 */
	private SearchResults searchContract(String contractId, Date startDate, String contractStatus, String queryName, AdapterServicesAccess adapterServicesAccess) throws SevereException{
		HashMap map = new HashMap();
		map.put("contractId", contractId);
		map.put("startDate", getStringDate(startDate));
		map.put("contractStat",contractStatus);
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(CP2000Contract.class.getName(), queryName, map);
		SearchResults searchResults =AdapterUtil.performSearch(searchCriteria, adapterServicesAccess);
		return searchResults;
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 */
    private static String getStringDate(Date date) {
        String dateString = null;
        if (null != date) {
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        dateString = df.format(date);
        }
        return dateString;
    }

    
	public List getVariableNotes(LegacyVariable variable) throws SevereException{
		HashMap map = new HashMap();
		map.put("contractId", variable.getContractId());
		map.put("startDate", getStringDate(variable.getStartDate()));
		
		if(variable.getVariableId() == null)
			variable.setVariableId("");
		map.put("variableId", variable.getVariableId());
		
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(CP2000Variable.class.getName(), "getContracVariableNotes", map);
		SearchResults searchResults =AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}

    
    public List getContractNotes(LegacyContractNotes contractNotes) throws SevereException{
    	HashMap map = new HashMap();
		map.put("contractId", contractNotes.getContractId());
		map.put("startDate", contractNotes.getStartDate());
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(LegacyContractNotes.class.getName(), "getContractNotes", map);
		SearchResults searchResults =AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}
    public List getContrctMajorHeading(LegacyContractMajorHeading contractMajorHeading) throws SevereException{
    	HashMap map = new HashMap();
		map.put("contractId", contractMajorHeading.getContractId());
		map.put("startDate", contractMajorHeading.getStartDate());
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(LegacyContractMajorHeading.class.getName(), "getContractMajorHeadings", map);
		SearchResults searchResults =AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}
    public List getAllContrctMajorHeading(LegacyContractMajorHeading contractMajorHeading) throws SevereException{
    	HashMap map = new HashMap();
		map.put("contractId", contractMajorHeading.getContractId());
		map.put("startDate", contractMajorHeading.getStartDate());
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(LegacyContractMajorHeading.class.getName(), "getAllContractMajorHeadings", map);
		SearchResults searchResults =AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}
    public List getContractMinorHeadings(LegacyContractMinorHeading contractMinorHeading) throws SevereException{
    	HashMap map = new HashMap();
		map.put("contractId", contractMinorHeading.getContractId());
		map.put("startDate", contractMinorHeading.getStartDate());
		map.put("majorHeadingId", contractMinorHeading.getMajorHeadingId());
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(LegacyContractMinorHeading.class.getName(), "getContractMinorHeadings", map);
		SearchResults searchResults =AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}
    
    public List getContractMajorNotes(LegacyMajorNotes legacyMajorNotes) throws SevereException{
       	HashMap map = new HashMap();
		map.put("contractId", legacyMajorNotes.getContractId());
		map.put("startDate", legacyMajorNotes.getStartDate());
		map.put("majorHeadingId", legacyMajorNotes.getMajorHeadingId());
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(LegacyMajorNotes.class.getName(), "getContractMajorNotes", map);
		SearchResults searchResults =AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
    	
    }
    public List getContractMinorNotes(LegacyMinorNotes legacyMinorNotes) throws SevereException{
       	HashMap map = new HashMap();
		map.put("contractId", legacyMinorNotes.getContractId());
		map.put("startDate", legacyMinorNotes.getStartDate());
		map.put("minorHeadingId", legacyMinorNotes.getMinorHeadingId());
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(LegacyMinorNotes.class.getName(), "getContractMinorNotes", map);
		SearchResults searchResults =AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
    	
    }

	/**
	 * @param legacyMinorHeading
	 * @return
	 */
	public List getOldMinorHeadings(LegacyContractMinorHeading minorHeading) throws SevereException{
       	HashMap map = new HashMap();
		map.put("minorHeadingId", minorHeading.getMinorHeadingId());
		map.put("contractId", minorHeading.getContractId());
		map.put("startDate", minorHeading.getStartDate());
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(LegacyContractMinorHeading.class.getName(), "getOldMinorHeadingId", map);
		SearchResults searchResults =AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}
    
    

}
