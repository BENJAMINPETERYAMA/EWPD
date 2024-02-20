package com.wellpoint.ets.ebx.mapping.application;

import java.sql.SQLException;
import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.Page;
import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.domain.vo.SearchCriteria;

/**
 * @author UST-GLOBAL
 * This is an facade interface for locating or message text for search, and get count of total records 
 * based on search
 */
public interface AdvanceSearchFacade {
	
	/**
	 * For pagination in locate result page
	 * @param mapping
	 * @return int
	 */
	int getSearchRecordCount(SearchCriteria searchCriteria) throws EBXException;
	
	int getAdvanceSearchCount(SearchCriteria searchCriteria);
	
	/**
	 * For getting exact match/retrieving records based on the search criteria
	 * @param Mapping
	 * @param status
	 * @param loggedInUser
	 * @return List
	 */
	List getSearchRecords(SearchCriteria searchCriteria, int noOfRecords, Page page)  throws EBXException;;
	List getAdvanceSearchRecords(SearchCriteria searchCriteria, int noOfRecords, Page page);
	
	/**
	 * Method for getting records based on the search criteria for excel generation.
	 * @param searchCriteria
	 * @return List
	 * @throws SQLException
	 */
	List getRecordsForReport(SearchCriteria searchCriteria) throws SQLException;
	
	/*****************************January Release**********************/
	/**
	 * The method populates the System and Start Date for a Contract to load the System and Start date 
	 * in the advance search page while a contract id is entered.
	 * @param contractId
	 * @return List
	 */
	List populateSystemAndStartDateForContract(String contractId, String contractSystem, boolean StartDateFlag,String startDateForContract);
	/*****************************January Release**********************/

}