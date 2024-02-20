package com.wellpoint.ets.ebx.mapping.application;

import java.sql.SQLException;
import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.Page;
import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.domain.vo.SearchCriteria;
import com.wellpoint.ets.ebx.mapping.domain.service.AdvanceSearchService;

/**
 * @author UST-GLOBAL
 * This is an facade class for locating or message text for search, and get count of total records 
 * based on search
 */
public class AdvanceSearchFacadeImpl implements AdvanceSearchFacade {
	
	private AdvanceSearchService advanceSearchService;
	
	
	public AdvanceSearchService getAdvanceSearchService() {
		return advanceSearchService;
	}

	public void setAdvanceSearchService(AdvanceSearchService advanceSearchService) {
		this.advanceSearchService = advanceSearchService;
	}

	/**
	 * For pagination in locate result page
	 * @param mapping
	 * @return int
	 * @throws EBXException
	 */
	public int getSearchRecordCount(SearchCriteria searchCriteria) throws EBXException{
		return advanceSearchService.getRecordCount(searchCriteria);
	}
	
	/**
	 * For getting exact match/retrieving records based on the search criteria
	 * @param Mapping
	 * @param status
	 * @param loggedInUser
	 * @return List
	 * @throws EBXException 
	 */
	public List getSearchRecords(SearchCriteria searchCriteria, int noOfRecords,
			Page page) throws EBXException {
		return advanceSearchService.getRecords(searchCriteria, noOfRecords, page);
	}

	public int getAdvanceSearchCount(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return advanceSearchService.getAdvanceSearchRecordCount(searchCriteria);
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.ets.ebx.mapping.application.AdvanceSearchFacade#getAdvanceSearchRecords(com.wellpoint.ets.bx.mapping.domain.vo.SearchCriteria, int, com.wellpoint.ets.bx.mapping.domain.entity.Page)
	 */
	public List getAdvanceSearchRecords(SearchCriteria searchCriteria, int noOfRecords, Page page) {
		// TODO Auto-generated method stub
		return advanceSearchService.getAdvanceRecords(searchCriteria, noOfRecords, page);
	}
	
	/**
	 * Method for getting records based on the search criteria for excel generation.
	 * @param searchCriteria
	 * @return List
	 */
	public List getRecordsForReport(SearchCriteria searchCriteria) throws SQLException {
		return advanceSearchService.getRecordsForReport(searchCriteria);
	}
	/*****************************January Release**********************/
	/**
	 * The method populates the System and Start Date for a Contract to load the System and Start date 
	 * in the advance search page while a contract id is entered.
	 * @param contractId
	 * @return List
	 */
	public List populateSystemAndStartDateForContract(String contractId, String contractSystem, boolean startDateFlag,String startDateForContract){
		return advanceSearchService.populateSystemAndStartDateForContract(contractId,contractSystem,startDateFlag,startDateForContract);
	}
	/*****************************January Release**********************/



}