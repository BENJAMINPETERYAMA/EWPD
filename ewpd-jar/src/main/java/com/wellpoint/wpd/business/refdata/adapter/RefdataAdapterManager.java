/*
 * Created on Jul 18, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.refdata.adapter;

import java.util.HashMap;
import java.util.List;

import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchCriteriaImpl;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.refdata.bo.AssignedRuleIdBO;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.framework.exception.SevereException;

/**
 * @author u14631
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RefdataAdapterManager {
	
	/* Function to create the universe values
	 * 
	 */
	public boolean createRuleId(AssignedRuleIdBO assignedRuleIdBO,
			AdapterServicesAccess adapterServiceAccess,Audit audit)
			throws SevereException,AdapterException {		
		AdapterUtil.performInsert(assignedRuleIdBO, audit.getUser(), adapterServiceAccess);
		return true;
	}
	
	
	
	/*
	 * to update the datas regarding term,qualifier,PVA and datatype and store
	 * them to DB
	 *  
	 */
	public boolean deleteRuleId(AssignedRuleIdBO assignedRuleIdBO,
			AdapterServicesAccess adapterServiceAccess,Audit audit)
			throws SevereException,AdapterException {
		AdapterUtil.performRemove(assignedRuleIdBO,audit.getUser(),adapterServiceAccess);
		return true;
	}

	/* Function to search for a particular standard benefit using the key
	 * 
	 */
	public List searchRuleId(AssignedRuleIdBO assignedRuleIdBO) throws SevereException {
		HashMap criteriaValues = new HashMap();

		criteriaValues.put("entityKey", new Integer(assignedRuleIdBO
				.getEntitySystemId()));
		criteriaValues.put("itemType", assignedRuleIdBO
				.getEntityType());
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
		searchCriteria.setReferenceValueSet(criteriaValues);
		searchCriteria.setBusinessObjectName(assignedRuleIdBO.getClass().getName());
		searchCriteria.setSearchQueryName("SearchForReferenceIds");
		searchCriteria.setMaxSearchResultSize(999999);
		searchCriteria.setSearchDomain("medical");
		
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);

		if (searchResults.getSearchResultCount() > 0) {
			return searchResults.getSearchResults();
		}
		return null;

	}
	
	//For Datafeed
	/* Function to search Rules for a particular standard benefit using the key
	 * 
	 */
	public List searchRuleIdForDatafeed(AssignedRuleIdBO assignedRuleIdBO)
			throws SevereException {
		HashMap criteriaValues = new HashMap();

		criteriaValues.put("productSysId", new Integer(assignedRuleIdBO
				.getProductId()));
		criteriaValues.put("dateSegmentSysId", new Integer(assignedRuleIdBO
				.getDateSegmentId()));
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
		searchCriteria.setReferenceValueSet(criteriaValues);
		searchCriteria.setBusinessObjectName(assignedRuleIdBO.getClass()
				.getName());
		searchCriteria.setSearchQueryName("SearchForReferenceIdsDatafeed");
		searchCriteria.setMaxSearchResultSize(999999);
		searchCriteria.setSearchDomain("medical");

		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);

		if (searchResults.getSearchResultCount() > 0) {
			return searchResults.getSearchResults();
		}
		return null;

	}
	
}
