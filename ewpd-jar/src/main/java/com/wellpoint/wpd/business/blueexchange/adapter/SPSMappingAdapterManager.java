/*
 * SPSMappingAdapterManager.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.blueexchange.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchCriteriaImpl;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.blueexchange.bo.SPSMappingBO;
import com.wellpoint.wpd.common.framework.logging.Logger;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SPSMappingAdapterManager {

//	private AdapterUtil adapterUtil;

	/**
	 * This method is used to Insert the Business Object
	 * @param mappingBO 
	 * @param userId
	 * @return boolean
	 * @throws AdapterException
	 */
	public boolean createSPSMapping(SPSMappingBO mappingBO, String userId)
			throws AdapterException {
		try {
			//Adapter Method to Insert the Business Object
			AdapterUtil.performInsert(mappingBO, userId);

		} catch (Exception e) {
			//Exception thrown from the Adapter
			throw new AdapterException(null, e);
		}
		return true;
	}

	/**
	 * This method is to Update the Business Object
	 * @param mappingBO
	 * @param userId
	 * @return boolean
	 * @throws AdapterException
	 */
	public boolean editSPSMapping(SPSMappingBO mappingBO, String userId)
			throws AdapterException {
		try {
			//Adapter Method to Update the Business Object
			AdapterUtil.performUpdate(mappingBO, userId);
		} catch (Exception e) {
			//Exception thrown from the Adapter
			throw new AdapterException(null, e);
		}
		return true;
	}
	
	/**
	 * This method takes spsmappingBO as input and map these values and perform search
	 * @param mappingBO
	 * @return List
	 * @throws AdapterException
	 */
	public List locate(SPSMappingBO mappingBO)throws AdapterException {
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
        SearchResults searchResults = null;
        List locateResultsList = new ArrayList();
         HashMap refValSet = new HashMap();
        refValSet.put("spsParameterList", mappingBO.getSpsParameterList());
        refValSet.put("eb01ValueList", mappingBO.getEb01ValueList() );
        refValSet.put("eb02ValueList",mappingBO.getEb02ValueList());
        refValSet.put("eb06ValueList", mappingBO.getEb06ValueList());
        refValSet.put("eb09ValueList",mappingBO.getEb09ValueList());
        refValSet.put("hsd1ValueList", mappingBO.getHsd1ValueList());
        refValSet.put("hsd2ValueList", mappingBO.getHsd2ValueList());
        refValSet.put("hsd3ValueList", mappingBO.getHsd3ValueList());
        refValSet.put("hsd4ValueList", mappingBO.getHsd4ValueList());
        refValSet.put("hsd5ValueList", mappingBO.getHsd5ValueList());
        refValSet.put("hsd6ValueList", mappingBO.getHsd6ValueList()); 
        refValSet.put("accummulatorSPSIDList", mappingBO.getAccummulatorSPSIDList()); 
		
        searchCriteria = AdapterUtil.getAdapterSearchCriteria(
        		SPSMappingBO.class.getName(),
        		"searchSPSMapping", refValSet,BusinessConstants.RESULT_SET_SIZE); 
        try{
        	searchResults = AdapterUtil.performSearch(searchCriteria);
        }catch (Exception e) {
        	throw new AdapterException(null, e);
		}
        
        locateResultsList = searchResults.getSearchResults();
		return locateResultsList;
	}

	/**
	 * This method gets spsMappingBo businessObject as input parameters from
	 * SPSMappingBusinessObjectBuilder. From the businessObject it takes the
	 * Search Criteria and perform Search. Search Results are sent back to the
	 * SPSMappingBusinessObjectBuilder.
	 * 
	 * @param mappingBO
	 * @return searchResults
	 * @throws AdapterException
	 */
	public SearchResults retrieveSPSMapping(SPSMappingBO mappingBO)
			throws AdapterException {
		Logger
				.logInfo("Entering the method for retrieving sps parameters using sps parameter id");
		HashMap criteriaMap = getCriteriaForSpsMappingBO(mappingBO);
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				mappingBO.getClass().getName(),
				BusinessConstants.VIEW_SPSMAPPING, criteriaMap);
		SearchResults searchResults = null;
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception e) {
			throw new AdapterException(null, e);
		}
		Logger
				.logInfo("Returning the method for retrieving sps parameters using sps parameter id");
		return searchResults;
	}
	
	public SearchResults retrieveSPSMappingForUpdate(SPSMappingBO mappingBO)
	throws AdapterException {
	Logger
			.logInfo("Entering the method for retrieving sps parameters using sps parameter id");
	HashMap criteriaMap = getCriteriaForSpsMappingBO(mappingBO);
	SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
			mappingBO.getClass().getName(),
			"loadForUpdate", criteriaMap);
	SearchResults searchResults = null;
	try {
		searchResults = AdapterUtil.performSearch(searchCriteria);
	} catch (Exception e) {
		throw new AdapterException(null, e);
	}
	Logger
			.logInfo("Returning the method for retrieving sps parameters using sps parameter id");
	return searchResults;
	}
	
	/**
	 * This method is to Delete the Business Object
	 * @param mappingBO, userId
	 * @return boolean
	 * @throws AdapterException	 
	 */
	public boolean deleteSPSMapping(SPSMappingBO mappingBO, String userId)
			throws AdapterException {
		try {
			//Adapter Method to Delete the Business Object
			AdapterUtil.performRemove(mappingBO, userId);
		} catch (Exception e) {
			//Exception thrown from the Adapter
			throw new AdapterException(null, e);
		}
		return true;
	}
	/**
	 * This method gets the Search Criteria Value from the businessObject and
	 * return it to the method called.
	 * 
	 * @param mappingBO
	 * @return hashmap
	 */
	private HashMap getCriteriaForSpsMappingBO(SPSMappingBO mappingBO) {

		Logger
				.logInfo("Entering the method for getting criteria for sps mapping BO");
		HashMap criteriaValues = new HashMap();
		criteriaValues.put(BusinessConstants.SPS_PARAMETER, mappingBO
				.getSpsParameter());
		Logger
				.logInfo("Returning the method for getting criteria for sps mapping BO");
		return criteriaValues;

	}
	/**
	 * This method is used to retrieve spsmapping values for data feed
	 * @return List
	 * @throws AdapterException
	 */
	public List retrieveSPSMappingForDataFeed(String status) throws AdapterException{
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
        SearchResults searchResults = null;
        List locateResultsList = new ArrayList();
        SPSMappingBO sPSMappingBO = new SPSMappingBO();
        sPSMappingBO.setStatusCd(status);
        HashMap refValSet = new HashMap();
        refValSet.put("statusCd",sPSMappingBO.getStatusCd());
        //refValSet.put("statusCd2",sPSMappingBO.getStatusCd());
        searchCriteria = AdapterUtil.getAdapterSearchCriteria(
        		SPSMappingBO.class.getName(),
        		BusinessConstants.RETRIEVE_SPS_MAPPING_VALUES, refValSet); 
        try{
        	searchResults = AdapterUtil.performSearch(searchCriteria);
        }catch (Exception e) {
        	throw new AdapterException(null, e);	
        	
		}       
        locateResultsList = searchResults.getSearchResults();

		return locateResultsList;
	}
	/**
	 * This method is used to retrieve spsmapping values for data feed
	 * @return List
	 * @throws AdapterException
	 */
	public List retrieveNASPSMappingForDataFeed() throws AdapterException{
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
        SearchResults searchResults = null;
        List locateResultsList = new ArrayList();
    
        HashMap refValSet = new HashMap();
        refValSet.put("statusCd",BusinessConstants.NOT_APPLICABLE_STATUS);
      
        	
        searchCriteria = AdapterUtil.getAdapterSearchCriteria(
        		SPSMappingBO.class.getName(),
        		BusinessConstants.RETRIEVE_NOT_APPLICABLE_SPS_MAPPING_VALUES, refValSet); 
        try{
        	searchResults = AdapterUtil.performSearch(searchCriteria);
        }catch (Exception e) {
        	throw new AdapterException(null, e);	
        	
		}       

        locateResultsList = searchResults.getSearchResults();

		return locateResultsList;
	}
}