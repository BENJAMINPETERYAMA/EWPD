/*
 * Created on May 10, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
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
import com.wellpoint.wpd.common.blueexchange.bo.ContractVariableMappingBO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractVariableMappingAdapterManager {

	/**
	 * method for retriving the contract variable mapping
	 * @param contractVariableMappingBO
	 * @return
	 * @throws AdapterException
	 */
	public List retrieveContractVariableMappingForDataFeed(ContractVariableMappingBO contractVariableMappingBO) throws AdapterException {
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
        SearchResults searchResults = null;
		SearchCriteria searchCriteriaTemp = new SearchCriteriaImpl();
        SearchResults searchResultsTemp = null;
        List locateResultsList = new ArrayList();
        List locateResultsListTemp = new ArrayList();
        HashMap refValSet = new HashMap();
        refValSet.put("status",contractVariableMappingBO.getStatus());
       	searchCriteriaTemp = AdapterUtil.getAdapterSearchCriteria(
           		ContractVariableMappingBO.class.getName(),
           		BusinessConstants.RETRIEVE_TEMP_VAR_MAPPING_DF, refValSet); 
      	 searchCriteria = AdapterUtil.getAdapterSearchCriteria(
           		ContractVariableMappingBO.class.getName(),
           		BusinessConstants.RETRIEVE_VAR_MAPPING_DF, refValSet); 
        try{
        	
           	searchResults = AdapterUtil.performSearch(searchCriteria);
            locateResultsList = searchResults.getSearchResults();
           	searchResultsTemp = AdapterUtil.performSearch(searchCriteriaTemp);
             locateResultsListTemp =  searchResultsTemp.getSearchResults();
            locateResultsList.addAll(locateResultsListTemp);
         }catch (Exception e) {
        	throw new AdapterException(null, e);			
		}       

 
		return locateResultsList;
	}

/**
 * method for retriving not applicable the contract variable mapping
 * @param contractVariableMappingBO
 * @return
 * @throws AdapterException
 */
	public List retrievenotApplicableContractVariableMapping(ContractVariableMappingBO contractVariableMappingBO) throws AdapterException{
		
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
        SearchResults searchResults = null;
        SearchCriteria searchCriteriaTemp = new SearchCriteriaImpl();
        SearchResults searchResultsTemp = null;
        List locateResultsList = new ArrayList();
        List locateResultsListTemp = new ArrayList();
        HashMap refValSet = new HashMap();
        refValSet.put("status",contractVariableMappingBO.getStatus());
       	 searchCriteria = AdapterUtil.getAdapterSearchCriteria(
           		ContractVariableMappingBO.class.getName(),
           		BusinessConstants.RETRIEVE_NA_VAR_MAPPING_DF, refValSet);
       /* searchCriteria = AdapterUtil.getAdapterSearchCriteria(
           		ContractVariableMappingBO.class.getName(),
           		BusinessConstants.RETRIEVE_VAR_MAPPING_DF, refValSet);*/

        try{
        	
        	searchResults = AdapterUtil.performSearch(searchCriteria);
            locateResultsList = searchResults.getSearchResults();
            //locateResultsList.addAll(locateResultsListTemp);
        }catch (Exception e) {
        	throw new AdapterException(null, e);			
		}       

        //locateResultsList = searchResults.getSearchResults();

		return locateResultsList;
	}
	/**
	 * method for updating the status of the contract variable mapping
	 * @param contractVariableMappingBO
	 * @param user
	 * @return
	 * @throws AdapterException
	 */
	public boolean updateStausForDataFeed(ContractVariableMappingBO contractVariableMappingBO, String user) throws AdapterException{
		try{
			if(("SCHEDULED_TO_TEST").equalsIgnoreCase(contractVariableMappingBO.getStatus())){
        	AdapterUtil.performUpdate(contractVariableMappingBO ,user );
			}
			
        }catch (Exception e) {
        	throw new AdapterException(null, e);			
		}    
		return true;
	}

	
}
