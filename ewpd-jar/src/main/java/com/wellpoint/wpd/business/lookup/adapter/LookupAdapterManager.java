/*
 * LookupAdapterManager.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.lookup.adapter;

import com.wellpoint.adapter.access.AdapterAccessFactory;
import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchCriteriaImpl;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.lookup.locateCriteria.LookupAdminQuestionLocateCriteria;
import com.wellpoint.wpd.business.lookup.locateResult.LookupAdminQuestionLocateResult;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.BenefitLevelListLocateCriteria;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.lookup.bo.LookupAdminQuestionBO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class LookupAdapterManager {
	
	public LookupAdapterManager(){
		
	}
	  private AdapterServicesAccess getAdapterService(){
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory.getAdapterServicesAccess("ewpd");
        return adapterServicesAccess;
    }
	
	
	/**
	 * Method to search the question
	 * 
	 * @param lookupAdminQuestionLocateCriteria
	 * @param user
	 * @return LocateResults
	 * @throws SevereException
	 */
	public  LocateResults locateAdminQuestion(LookupAdminQuestionLocateCriteria lookupAdminQuestionLocateCriteria, User user) throws AdapterException,SevereException {
		SearchResults questionSearchResults = null;
		LocateResults locateResults = new LocateResults();
		List locateResultsList = new ArrayList();
		String questionDesc = lookupAdminQuestionLocateCriteria.getQuestion();
		int adminOptionId = lookupAdminQuestionLocateCriteria.getAdminOptionId();
		HashMap referenceValueSet = new HashMap();
		if(null != questionDesc){
		    questionDesc = "%"+questionDesc.replaceAll("_", "`_").trim() + "%";
		}
		else{
		    questionDesc = "%";
		}
        referenceValueSet.put("questionDescription", questionDesc);
        referenceValueSet.put("adminOptionId", new Integer(adminOptionId));
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(LookupAdminQuestionBO.class.getName(),"adminQuestionSearch",referenceValueSet);
        try{
        	questionSearchResults = AdapterUtil.performSearch(searchCriteria);
        }catch(Exception e){
        	throw new AdapterException("Exception occured while adapter call",e);
        }
        Iterator searchResultIterator = questionSearchResults.getSearchResults().iterator();
		while(searchResultIterator.hasNext()){
			LookupAdminQuestionBO lookupAdminQuestionBO = (LookupAdminQuestionBO)searchResultIterator.next();
			LookupAdminQuestionLocateResult lookupAdminQuestionLocateResult = new LookupAdminQuestionLocateResult();
			lookupAdminQuestionLocateResult.setQuestionDescription(lookupAdminQuestionBO.getQuestionDescription());
			lookupAdminQuestionLocateResult.setQuestionNumber(lookupAdminQuestionBO.getQuestionNo());
			
			if(null==lookupAdminQuestionBO.getSpsId()){
				lookupAdminQuestionLocateResult.setSpsId("null");
				lookupAdminQuestionLocateResult.setDescription("null");
			}
			else{
				lookupAdminQuestionLocateResult.setSpsId(lookupAdminQuestionBO.getSpsId());
				lookupAdminQuestionLocateResult.setDescription(lookupAdminQuestionBO.getDescription());
			}
			locateResultsList.add(lookupAdminQuestionLocateResult);
		}
		locateResults.setLocateResults(locateResultsList);
		return locateResults;
	}
	
	/**
	 * This method is to get the benefitLvel list for BenefitLevel Popup from the table
	 * @param locateCriteria
	 * @return
	 * @throws WPDException
	 */
	public LocateResults locateBenefitLevelList(BenefitLevelListLocateCriteria locateCriteria) throws SevereException {			
	    LocateResults locateResults = new LocateResults();	   
	    SearchResults searchResults = null;	    
	    SearchCriteria searchCriteria = new SearchCriteriaImpl();	   
	    searchCriteria.setBusinessObjectName("com.wellpoint.wpd.common.standardbenefit.bo.BenefitLevelListBO");
	    searchCriteria.setMaxSearchResultSize(Integer.MAX_VALUE);
	    searchCriteria.setSearchDomain("medical");
	    searchCriteria.setSearchQueryName("searchBenefitLevelList");	    
	    HashMap refValSet = new HashMap();   
	    refValSet.put("benefitMasterSystemId",new Integer(locateCriteria.getBenefitsystemId()));
	    searchCriteria.setReferenceValueSet(refValSet);
	    try {	        
			searchResults = getAdapterService().searchObject(searchCriteria);
		} catch (AdapterException adapterException) {
		    List errorParams = new ArrayList();
	        errorParams.add(searchCriteria.getBusinessObjectName());
	        errorParams.add(searchCriteria.getSearchQueryName());
	        throw new ServiceException("Adapter Exception occured", errorParams, adapterException);
		}		
		locateResults.setLocateResults(searchResults.getSearchResults());	  
	    return locateResults;
	}
	
}
