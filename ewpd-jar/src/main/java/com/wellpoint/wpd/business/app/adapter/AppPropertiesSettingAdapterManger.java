/*
 * Created on Nov 5, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.app.adapter;

import java.util.HashMap;
import java.util.List;

import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchCriteriaImpl;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.common.app.dto.AppPropertiesSetting;

/**
 *This class contains methods for providing information to  adapter opeartion.
 *The adapter will perform retrieve,delete,insert and upadet operations.
 */
public class AppPropertiesSettingAdapterManger {
	
	/**
	 * This Method is for retrieving application configaration value from DB.
	 * @ param key
	 * using the input 'key' there will perform a seacrh in the db table to get the key value.
	 * @ return status
	 * The status will return from this method.The value will be 0 or 1.
	 * 
	 */
	public String getApplicationConfiguration(String key)throws AdapterException{
		
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
		String value = null;
		SearchResults searchResults = null;
		//setting business object to identify the adapter configurartions
		searchCriteria.setBusinessObjectName(AppPropertiesSetting.class.getName());
		searchCriteria.setSearchDomain("medical");
		//setting query name.
		searchCriteria.setSearchQueryName("getAppSettingProps");
		HashMap refValSet = new HashMap();
		searchCriteria.setReferenceValueSet(refValSet);
		//setting key to the serach criteria set
		refValSet.put("key",key);
		//performing search.
		try{
			searchResults = AdapterUtil.performSearch(searchCriteria);
		}catch(Exception e){
			throw new AdapterException("Exception occured while adapter call in the method " +
					"AppPropertiesSettingAdapterManger() of AppPropertiesSettingAdapterManger class",e);
		}
		List resultList = searchResults.getSearchResults();
		if(resultList!=null && resultList.size()>0){
			AppPropertiesSetting setting = (AppPropertiesSetting)resultList.get(0);
			value =setting.getValue();
		}
		return value;
	}

}
