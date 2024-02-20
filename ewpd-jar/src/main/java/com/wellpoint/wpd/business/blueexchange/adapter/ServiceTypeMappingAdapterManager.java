/*
 * ServiceTypeMappingAdapterManager.java
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
import com.wellpoint.wpd.common.blueexchange.bo.RuleMapping;
import com.wellpoint.wpd.common.blueexchange.bo.RuleServiceTypeAssociation;
import com.wellpoint.wpd.common.blueexchange.bo.ServiceTypeLocateCriteriaBO;
import com.wellpoint.wpd.common.blueexchange.bo.ServiceTypeMapping;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.web.util.WebConstants;



/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ServiceTypeMappingAdapterManager {

	/**
	 * 
	 * @param serviceTypeMapping
	 * @param audit
	 * @param adapterServicesAccess
	 * @return
	 * @throws SevereException
	 */ 
    public boolean deleteServiceTypeMapping(ServiceTypeMapping serviceTypeMapping, Audit audit)
            throws SevereException {
        AdapterUtil.performRemove(serviceTypeMapping, audit.getUser());
        return true;
    }
    
    public RuleMapping retrieveRuleMapping(RuleMapping mapping) throws SevereException {
    	return (RuleMapping)AdapterUtil.performRetrieve(mapping);
    }
    
    public List retrieveAssociatedServiceTypes(String ruleId) throws SevereException {
    	HashMap map = new HashMap();
    	map.put("ruleId", ruleId);
    	SearchCriteria criteria = AdapterUtil.getAdapterSearchCriteria(RuleServiceTypeAssociation.class.getName(),"retrieveAssociatedServiceTypes",map);
    	SearchResults searchResults = AdapterUtil.performSearch(criteria);
    	return searchResults.getSearchResults();
    }
	/**
	 * Adapter methode for locate 
	 * @return SearchResults
	 * @param ServiceTypeLocateCriteriaBO
	 * @throws SevereException
	 */
	public SearchResults searchServiceTypeMapping(ServiceTypeLocateCriteriaBO criteriaBO) throws SevereException{
		SearchResults searchResults = null;
		HashMap refValSet = new HashMap();		
		//FIX Start
		List headerRuleList = null;
		List subList = null;
		List resultList = null;
		
		if(null != criteriaBO.getHeaderRule() && !(criteriaBO.getHeaderRule().isEmpty())){
			//Getting the header rule list from the criteria and set that in headerRuleList
			headerRuleList = criteriaBO.getHeaderRule();
			//Checking for the size of the list to 500
			while(headerRuleList.size() > 500){
				//Getting the first 500 object and setting to the list 
				subList = headerRuleList.subList(0,500);		
				criteriaBO.setHeaderRule(subList);
				//Calls the method to retreive from DB
				searchResults = retreiveSearchList(criteriaBO, searchResults, resultList);
				resultList = searchResults.getSearchResults();
				//Clears the 0 to 500 records from the list 
				headerRuleList.subList(0,500).clear(); 
			}
			//if the list size in not more than 500
			criteriaBO.setHeaderRule(headerRuleList);
		}
		//Calls the method to retreive from DB
		searchResults = retreiveSearchList(criteriaBO, searchResults, resultList);
		
		//FIX End
	     return searchResults;
	}	
	/**
	 * Method to retreive the search result
	 * @param criteriaBO
	 * @param searchResults
	 * @param resultList
	 * @return searchResults
	 * @throws SevereException
	 */	 
	private SearchResults retreiveSearchList(ServiceTypeLocateCriteriaBO criteriaBO, SearchResults searchResults, List resultList) throws SevereException {
		
		HashMap refValSet = new HashMap();
		List tempList = null;
		SearchCriteria searchCriteria;
		//Method to set the value in the hash map
		setValuesToMap(refValSet, criteriaBO);
		searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                BusinessConstants.RULE_MAPPING,
                BusinessConstants.LOCATE_SERVICE_TYPE_MAPPING, refValSet, 50);
		//Perform search from the DB
		searchResults = AdapterUtil.performSearch(searchCriteria);
		//Checking null for the search list
		if(null != searchResults.getSearchResults() && !(searchResults.getSearchResults().isEmpty())){
			//Setting the resultant list to the resultList list
			if(null != resultList && !(resultList.isEmpty())){
				resultList.addAll(searchResults.getSearchResults());
			}else{
				resultList = searchResults.getSearchResults();
			}			
		}
		//Checking null for the resultList list
		if(null != resultList && !(resultList.isEmpty())){
			searchResults.setSearchResults(resultList);
		}
		return searchResults;
	} 
	/**
	 *  Methode for setting values to Map 
	 * @param HashMap
	 * @param ServiceTypeLocateCriteriaBO
	 */
	private void setValuesToMap(HashMap map,
			ServiceTypeLocateCriteriaBO criteriaBO){
		if(null != criteriaBO.getEb03Identifier() && !(criteriaBO.getEb03Identifier().isEmpty())){
			  map.put(WebConstants.EOB03_INDENTIFIER, criteriaBO.getEb03Identifier());
		}
		if(null != criteriaBO.getHeaderRule() && !(criteriaBO.getHeaderRule().isEmpty())){
			  map.put(WebConstants.HEADER_RULE, criteriaBO.getHeaderRule());
		}
		if(null != criteriaBO.getApplicableToBX()){
			map.put(WebConstants.APPLICABLE_BX,criteriaBO.getApplicableToBX());
		}
		
	}
	
	/**
	 * Method to retrive ServiceTypeMappings for Datafeed
	 * @return List
	 * @throws AdapterException
	 */
	public List retrieveServiceMappingForDataFeed(String status) throws AdapterException{
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
        SearchResults searchResults = null;
        List locateResultsList = new ArrayList();
        HashMap refValSet = new HashMap();
        refValSet.put("statusCd",status);
		searchCriteria = AdapterUtil.getAdapterSearchCriteria(RuleServiceTypeAssociation.class.getName(),
				BusinessConstants.RETRIEVE_SERVICE_MAPPING_VALUES, refValSet);
		
        try{
        	searchResults = AdapterUtil.performSearch(searchCriteria);
        }catch (Exception e) {
        	throw new AdapterException(null, e);			
		}

        locateResultsList = searchResults.getSearchResults();

		return locateResultsList;
	}
	/**
	 * Method to retrive ServiceTypeMappings for Datafeed
	 * @return List
	 * @throws AdapterException
	 */
	public List retrieveNAServiceMappingForDataFeed() throws AdapterException{
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
        SearchResults searchResults = null;
        List locateResultsList = new ArrayList();
        HashMap refValSet = new HashMap();
        refValSet.put("statusCd",BusinessConstants.NOT_APPLICABLE_STATUS);
		searchCriteria = AdapterUtil.getAdapterSearchCriteria(RuleServiceTypeAssociation.class.getName(),
				BusinessConstants.RETRIEVE_NA_SERVICE_MAPPING_VALUES, refValSet);
		
        try{
        	searchResults = AdapterUtil.performSearch(searchCriteria);
        }catch (Exception e) {
        	throw new AdapterException(null, e);			
		}

        locateResultsList = searchResults.getSearchResults();

		return locateResultsList;
	}
	/**
	 * Method to create a Service Type mapping 
	 * @param ServiceTypeMapping
	 * @param audit
	 * @throws SevereException
	 */
	public void createServiceTypeMapping(ServiceTypeMapping serviceTypeMapping, Audit audit) throws SevereException{
		AdapterUtil.performInsert(serviceTypeMapping, audit.getUser());
	}
	/**
	 * Method to update a Service Type mapping 
	 * @param ServiceTypeMapping
	 * @param audit
	 * @throws SevereException
	 */
	public void updateServiceTypeMapping(ServiceTypeMapping serviceTypeMapping, Audit audit) throws SevereException{
		AdapterUtil.performUpdate(serviceTypeMapping, audit.getUser());
		
	}
	
	
	
	
	/**
	 * This method is used to get values from business object and put it in map for criteria values
	 * 
	 * @param map
	 * @param serviceTypeMapping
	 */
	private void setValuesToMap(HashMap map,ServiceTypeMapping serviceTypeMapping){	
		if(null != serviceTypeMapping.getHeaderRuleId()){
			map.put(WebConstants.REQUEST_HEADER_RULE_ID, serviceTypeMapping.getHeaderRuleId());
		}
		if(null != serviceTypeMapping.getPlaceOfService()){
			map.put(WebConstants.REQUEST_PLACE_OF_SERVICE, serviceTypeMapping.getPlaceOfService());
		}
		if(null != serviceTypeMapping.getEb03Identifier() && serviceTypeMapping.getEb03Identifier()!= "" && serviceTypeMapping.getEb03Identifier().length() != 0){
			 map.put(WebConstants.REQUEST_SERVICE_TYPE_CODE, serviceTypeMapping.getEb03Identifier());
		} 
		  
		  		
	}
	/**
	 * Method to retrieve a Service Type mapping 
	 * @param ServiceTypeMapping
	 * @return ServiceTypeMapping
	 * @throws SevereException
	 */
	public ServiceTypeMapping retrieveServiceTypeMapping(ServiceTypeMapping serviceTypeMapping) throws SevereException{
		return(ServiceTypeMapping)AdapterUtil.performRetrieve(serviceTypeMapping);
	}
}
