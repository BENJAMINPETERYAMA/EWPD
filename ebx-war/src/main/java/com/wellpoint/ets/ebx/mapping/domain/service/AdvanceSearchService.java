package com.wellpoint.ets.ebx.mapping.domain.service;

import java.sql.SQLException;
import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.Page;
import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.domain.vo.SearchCriteria;

/**
 * @author UST-GLOBAL
 * This is an service interface for locating or message text for search, and get count of total records 
 * based on search
 */
public interface AdvanceSearchService {
	
	/**
	 * For pagination in locate result page
	 * @param mapping
	 * @return int
	 */
	int getRecordCount(SearchCriteria searchCriteria) throws EBXException;
	int getAdvanceSearchRecordCount(SearchCriteria searchCriteria);
	
	/**
	 * For getting exact match/retrieving records based on the search criteria
	 * @param Mapping
	 * @param status
	 * @param loggedInUser
	 * @return List
	 */
	List getRecords(SearchCriteria searchCriteria, int noOfRecords, Page page) throws EBXException;
	List getAdvanceRecords(SearchCriteria searchCriteria, int noOfRecords, Page page);
	
	/**
	 * Method for getting records based on the search criteria for excel generation.
	 * @param searchCriteria
	 * @return List
	 */
	List getRecordsForReport(SearchCriteria searchCriteria) throws SQLException;
	
	/*****************************January Release**********************/
	/**
	 * The method populates the System and Start Date for a Contract to load the System and Start date 
	 * in the advance search page while a contract id is entered.
	 * @param contractId
	 * @return List
	 */
	List populateSystemAndStartDateForContract(String contractId, String contractSystem,boolean startDateFlag,String startDateForContract);
	/*****************************January Release**********************/
}
