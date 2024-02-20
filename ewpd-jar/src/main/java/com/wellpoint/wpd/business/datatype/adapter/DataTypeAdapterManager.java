/*
 * Created on Mar 6, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.datatype.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.adapter.access.AdapterAccessFactory;
import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchCriteriaImpl;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.datatype.locatecriteria.DataTypeLocateResult;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.datatype.bo.DataTypeBO;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.security.domain.User;


/**
 * @author u12322
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DataTypeAdapterManager {

	/**
	 * @param user
	 * @return
	 * @throws ServiceException
	 */
	public LocateResults searchDataType(User user) throws AdapterException,SevereException {
		SearchResults dataTypeSearchResults = null;
		LocateResults locateResults = new LocateResults();
		List locateResultsList = new ArrayList();
		HashMap referenceValueSet = new HashMap();
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        searchCriteria.setReferenceValueSet(referenceValueSet);
        searchCriteria.setMaxSearchResultSize(9999999);
        searchCriteria
                .setBusinessObjectName("com.wellpoint.wpd.common.datatype.bo.DataTypeBO");
        searchCriteria.setSearchQueryName("dataTypeSearch");
        searchCriteria.setSearchDomain("medical");
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess();
        try {
        	dataTypeSearchResults = adapterServicesAccess
                    .searchObject(searchCriteria);
        	if(null != dataTypeSearchResults.getSearchResults()){
    	        Iterator searchResultIterator = dataTypeSearchResults.getSearchResults().iterator();
    			while(searchResultIterator.hasNext()){
    				DataTypeBO dataTypeBO = (DataTypeBO)searchResultIterator.next();
    				DataTypeLocateResult dataTypeLocateResult = new DataTypeLocateResult();
    				dataTypeLocateResult.setDataTypeId(dataTypeBO.getDataTypeId());
    				dataTypeLocateResult.setDataTypeName(dataTypeBO.getDataTypeName());
    				dataTypeLocateResult.setDataTypeDesc(dataTypeBO.getDataTypeDesc());
    				dataTypeLocateResult.setDataTypeLgnd(dataTypeBO.getDataTypeLgnd());
    				locateResultsList.add(dataTypeLocateResult);
    			}
            }
        } catch (Exception exception) {
            throw new AdapterException("Adapter Exception occured", exception);
        }
        
		locateResults.setLocateResults(locateResultsList);
		return locateResults;
	}

	

}
