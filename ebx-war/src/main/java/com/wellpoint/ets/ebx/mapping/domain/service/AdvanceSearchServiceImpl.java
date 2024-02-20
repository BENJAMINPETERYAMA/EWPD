package com.wellpoint.ets.ebx.mapping.domain.service;

import java.sql.SQLException;
import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.Page;
import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.domain.vo.SearchCriteria;
import com.wellpoint.ets.ebx.mapping.repository.AdvanceSearchRepository;

/**
 * @author UST-GLOBAL
 * This is an service class for locating or message text for search, and get count of total records 
 * based on search
 */
public class AdvanceSearchServiceImpl implements AdvanceSearchService {
	
	private AdvanceSearchRepository advanceSearchRepository;
	
	private AdvanceSearchRepository advanceSearchRepositoryWPD;

	public AdvanceSearchRepository getAdvanceSearchRepository() {
		return advanceSearchRepository;
	}

	public void setAdvanceSearchRepository(
			AdvanceSearchRepository advanceSearchRepository) {
		this.advanceSearchRepository = advanceSearchRepository;
	}
	public void setAdvanceSearchRepositoryWPD(AdvanceSearchRepository advanceSearchRepositoryWPD) {
		this.advanceSearchRepositoryWPD = advanceSearchRepositoryWPD;
	}
	
	
	/**
	 * For pagination in locate result page
	 * @param mapping
	 * @return int
	 * @throws EBXException 
	 */
	public int getRecordCount(SearchCriteria searchCriteria) throws EBXException {
		SearchCriteria searchCrit = (SearchCriteria)SearchCriteria.clone(searchCriteria);
		return advanceSearchRepository.getRecordCount(searchCrit);
	}
	
	/**
	 * For getting exact match/retrieving records based on the search criteria
	 * @param Mapping
	 * @param status
	 * @param loggedInUser
	 * @return List
	 * @throws EBXException 
	 */
	public List getRecords(SearchCriteria searchCriteria, int noOfRecords,
			Page page) throws EBXException {
		SearchCriteria searchCrit = (SearchCriteria)SearchCriteria.clone(searchCriteria);
		return advanceSearchRepository.getRecords(searchCrit, noOfRecords, page);
	}
	
	/**
	 * For getting the advance search record count from WPD or EWPD
	 */
	public int getAdvanceSearchRecordCount(SearchCriteria searchCriteria) {
		if(searchCriteria.isCheckWPD() == true){
				return advanceSearchRepositoryWPD.getAdvanceRecordCount(searchCriteria);
		}else{ 
			return advanceSearchRepository.getAdvanceRecordCount(searchCriteria);
		}
	}

	/**
	 * For getting the advance search records from WPD or EWPD
	 */
	public List getAdvanceRecords(SearchCriteria searchCriteria, int noOfRecords, Page page) {
		if(searchCriteria.isCheckWPD() == true){
				return advanceSearchRepositoryWPD.getAdvanceRecords(searchCriteria, noOfRecords, page);
		}else{
			return advanceSearchRepository.getAdvanceRecords(searchCriteria, noOfRecords, page);
		}
	}

	/**
	 * Method for getting records based on the search criteria for excel generation.
	 * @param searchCriteria
	 * @return List
	 */
	
	public List getRecordsForReport(SearchCriteria searchCriteria) throws SQLException{
		if(searchCriteria.isCheckWPD() == true){
				return advanceSearchRepositoryWPD.getRecordsForReport(searchCriteria);
		}else{
				return advanceSearchRepository.getRecordsForReport(searchCriteria);
		}
	}
	
	/*****************************January Release**********************/
	/**
	 * The method populates the System and Start Date for a Contract to load the System and Start date 
	 * in the advance search page while a contract id is entered.
	 * @param contractId
	 * @return List
	 */
	public List populateSystemAndStartDateForContract(String contractId, String contractSystem,boolean startDateFlag,String startDateForContract){
		return advanceSearchRepositoryWPD.populateSystemAndStartDateForContract(contractId,contractSystem,startDateFlag,startDateForContract);
	}
	/*****************************January Release**********************/
	
}