package com.wellpoint.wpd.business.accumulator.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import com.wellpoint.adapter.access.AdapterAccessFactory;
import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.BusinessTransactionContext;
import com.wellpoint.adapter.access.BusinessTransactionContextImpl;
import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.common.adapter.AdapterSearchCriteria;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.accumulator.bo.Accumulator;
import com.wellpoint.wpd.common.accumulator.bo.SearchResultSet;
import com.wellpoint.wpd.common.accumulator.bo.SearchResultSetImpl;
import com.wellpoint.wpd.common.accumulator.bo.StandardAccumulator;
import com.wellpoint.wpd.common.domain.bo.Domain;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.common.adapter.DomainAdapterManager;
import com.wellpoint.wpd.business.common.adapter.SequenceAdapterManager;

import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTier;

public class AccumulatorAdapterManager {

	public List filterAccumValues(String queryName, Accumulator accumulator)
			throws ServiceException {
		final int SEARCH_RESULT_SIZE = 50;

		SearchCriteria adapterSearchCriteria = new AdapterSearchCriteria();
		HashMap referenceValueSet = new HashMap();

		referenceValueSet.put(BusinessConstants.ACCUM_DESC, accumulator
				.getName());
		referenceValueSet
				.put(BusinessConstants.ACCUM_PVA, accumulator.getPva());
		referenceValueSet.put(BusinessConstants.ACCUM_CST_TYP, accumulator
				.getCstTyp());
		referenceValueSet.put(BusinessConstants.ACCUM_SVC_CDE, accumulator
				.getSvcCde());
		referenceValueSet.put("question", accumulator
				.getQuestion());
		referenceValueSet.put("benefit", accumulator
				.getBenefit());
		referenceValueSet.put(BusinessConstants.SYSTEM, "eWPD");

		adapterSearchCriteria.setReferenceValueSet(referenceValueSet);
		adapterSearchCriteria
				.setBusinessObjectName("com.wellpoint.wpd.common.accumulator.bo.AccumulatorImpl");
		adapterSearchCriteria.setSearchQueryName(queryName);
		adapterSearchCriteria.setMaxSearchResultSize(SEARCH_RESULT_SIZE);

		adapterSearchCriteria.setSearchDomain(BusinessConstants.MEDICAL_DOMAIN);
		List searchResultList = null;
		AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
				.getAdapterServicesAccess();
		try {
			searchResultList = adapterServicesAccess.searchObject(
					adapterSearchCriteria).getSearchResults();

		} catch (AdapterException adapterException) {
			throw new ServiceException(BusinessConstants.ADATPER_EXCEPTION_MSG,
					null, adapterException);
		}
		return searchResultList;

	}

	protected SearchCriteria getSearchCriteriaObject(String queryName,
			HashMap referenceValueSet, String businessObject) {
		SearchCriteria adapterSearchCriteria = new AdapterSearchCriteria();
		adapterSearchCriteria.setReferenceValueSet(referenceValueSet);
		adapterSearchCriteria.setBusinessObjectName(businessObject);
		adapterSearchCriteria.setSearchQueryName(queryName);
		adapterSearchCriteria.setMaxSearchResultSize(50);
		adapterSearchCriteria.setSearchDomain("medical");
		return adapterSearchCriteria;
	}

	protected SearchResults getSearchResults(SearchCriteria searchCriteria)
			throws AdapterException {
		AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
				.getAdapterServicesAccess();
		SearchResults searchResult = adapterServicesAccess
				.searchObject(searchCriteria);
		return searchResult;
	}

	
	public SearchResults getBusinessDomain(String lob, String businessEntity,
			String group, String mbu) throws ServiceException {

		final String SERACH_QUERY_NAME = "getbusinessdomain";
		HashMap referenceValueSet = new HashMap();
		SearchResults searchResults = null;

		referenceValueSet.put("lineOfBusiness", lob);
		referenceValueSet.put("be", businessEntity);
		referenceValueSet.put("group", group);
		referenceValueSet.put("mbu", mbu);

		com.wellpoint.adapter.access.SearchCriteria adapterSearchCriteria = new AdapterSearchCriteria();
		adapterSearchCriteria.setReferenceValueSet(referenceValueSet);
		adapterSearchCriteria
				.setBusinessObjectName("com.wellpoint.wpd.common.accumulator.bo.StandardAccumulator");
		adapterSearchCriteria.setSearchQueryName(SERACH_QUERY_NAME);
		adapterSearchCriteria.setMaxSearchResultSize(50);
		adapterSearchCriteria.setSearchDomain(BusinessConstants.MEDICAL_DOMAIN);
		AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
				.getAdapterServicesAccess();
		try {
			searchResults = adapterServicesAccess
					.searchObject(adapterSearchCriteria);

		} catch (AdapterException adapterException) {
			List errorParams = new ArrayList();
			errorParams.add(adapterSearchCriteria);
			errorParams
					.add("com.wellpoint.wpd.common.accumulator.bo.AccumulatorImpl");
			throw new ServiceException(BusinessConstants.ADATPER_EXCEPTION_MSG,
					errorParams, adapterException);
		}
		return searchResults;

	}

	
	public SearchResults searchStandardAccumulator(int benefit,int question,
			String lineOfBusiness, List be, String group, String mbu,
			String byOrCy, List accumName) throws ServiceException {

		final String SERACH_QUERY_NAME = "searchStdAccumlist";
		HashMap referenceValueSet = new HashMap();
		String nullValue=null;
		SearchResults searchResults = null;
		// referenceValueSet.put("contvar",varId);
		if(question!= 0)
		referenceValueSet.put("question",Integer.toString( question));
		else
			referenceValueSet.put("question",nullValue);
		if(benefit != 0)
		referenceValueSet.put("benefit",Integer.toString( benefit));
		else
			referenceValueSet.put("benefit",nullValue);
		referenceValueSet.put("lineOfBusiness", lineOfBusiness);
		referenceValueSet.put("beList", be);
		referenceValueSet.put("group", group);
		referenceValueSet.put("mbu", mbu);
         if (null != byOrCy && !byOrCy.trim().equalsIgnoreCase(""))
		referenceValueSet.put("byOrCy", byOrCy);
		referenceValueSet.put("accumNameList", accumName);
		com.wellpoint.adapter.access.SearchCriteria adapterSearchCriteria = new AdapterSearchCriteria();
		adapterSearchCriteria.setReferenceValueSet(referenceValueSet);
		adapterSearchCriteria
				.setBusinessObjectName("com.wellpoint.wpd.common.accumulator.bo.StandardAccumulator");
		adapterSearchCriteria.setSearchQueryName(SERACH_QUERY_NAME);
		adapterSearchCriteria.setMaxSearchResultSize(50);
		adapterSearchCriteria.setSearchDomain(BusinessConstants.MEDICAL_DOMAIN);
		AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
				.getAdapterServicesAccess();
		try {
			searchResults = adapterServicesAccess
					.searchObject(adapterSearchCriteria);

		} catch (AdapterException adapterException) {
			List errorParams = new ArrayList();
			errorParams.add(adapterSearchCriteria);
			errorParams
					.add("com.wellpoint.wpd.common.accumulator.bo.StandardAccumulator");
			throw new ServiceException(BusinessConstants.ADATPER_EXCEPTION_MSG,
					errorParams, adapterException);
		}
		return searchResults;

	}
	
	public SearchResults searchMappedStandardAccumulator(List benefit,List question,
			List lob, List businessEntity, List group, List mbu,
			String byOrCy, List accumName) throws ServiceException {

		final String SERACH_QUERY_NAME = "searchMappedStdAccumlist";
		HashMap referenceValueSet = new HashMap();
		String nullValue=null;
		SearchResults searchResults = null;
		referenceValueSet.put("quesList",question);
		referenceValueSet.put("benList",benefit);
		referenceValueSet.put("lobList", lob);
		referenceValueSet.put("beList",businessEntity);
		referenceValueSet.put("groupList", group);
		referenceValueSet.put("mbuList", mbu);
		 if (null != byOrCy && !byOrCy.trim().equalsIgnoreCase(""))
        referenceValueSet.put("byOrCy", byOrCy);
		referenceValueSet.put("accumNameList", accumName);
		com.wellpoint.adapter.access.SearchCriteria adapterSearchCriteria = new AdapterSearchCriteria();
		adapterSearchCriteria.setReferenceValueSet(referenceValueSet);
		adapterSearchCriteria
				.setBusinessObjectName("com.wellpoint.wpd.common.accumulator.bo.StandardAccumulator");
		adapterSearchCriteria.setSearchQueryName(SERACH_QUERY_NAME);
		adapterSearchCriteria.setMaxSearchResultSize(50);
		adapterSearchCriteria.setSearchDomain(BusinessConstants.MEDICAL_DOMAIN);
		AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
				.getAdapterServicesAccess();
		try {
			searchResults = adapterServicesAccess
					.searchObject(adapterSearchCriteria);

		} catch (AdapterException adapterException) {
			List errorParams = new ArrayList();
			errorParams.add(adapterSearchCriteria);
			errorParams
					.add("com.wellpoint.wpd.common.accumulator.bo.StandardAccumulator");
			throw new ServiceException(BusinessConstants.ADATPER_EXCEPTION_MSG,
					errorParams, adapterException);
		}
		return searchResults;

	}

	public SearchResults retrievetandardAccumulatorMapping(int benefit,int question,
			String lob, List businessEntity, String group, String mbu,
			String type, List accumName) throws ServiceException {

		final String SERACH_QUERY_NAME = "searchStdAccumlist";
		HashMap referenceValueSet = new HashMap();
		SearchResults searchResults = null;
		// referenceValueSet.put("contvar",varId);
		referenceValueSet.put("question",Integer.toString( question));
		referenceValueSet.put("benefit",Integer.toString( benefit));
		referenceValueSet.put("lineOfBusiness", lob);
		referenceValueSet.put("businessentityList", businessEntity);
		referenceValueSet.put("group", group);
		referenceValueSet.put("mbu", mbu);
		referenceValueSet.put("byOrCy", type);
		referenceValueSet.put("accumNameList", accumName);
		com.wellpoint.adapter.access.SearchCriteria adapterSearchCriteria = new AdapterSearchCriteria();
		adapterSearchCriteria.setReferenceValueSet(referenceValueSet);
		adapterSearchCriteria
				.setBusinessObjectName("com.wellpoint.wpd.common.accumulator.bo.StandardAccumulator");
		adapterSearchCriteria.setSearchQueryName(SERACH_QUERY_NAME);
		adapterSearchCriteria.setMaxSearchResultSize(50);
		adapterSearchCriteria.setSearchDomain(BusinessConstants.MEDICAL_DOMAIN);
		AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
				.getAdapterServicesAccess();
		try {
			searchResults = adapterServicesAccess
					.searchObject(adapterSearchCriteria);

		} catch (AdapterException adapterException) {
			List errorParams = new ArrayList();
			errorParams.add(adapterSearchCriteria);
			errorParams
					.add("com.wellpoint.wpd.common.accumulator.bo.StandardAccumulator");
			throw new ServiceException(BusinessConstants.ADATPER_EXCEPTION_MSG,
					errorParams, adapterException);
		}
		return searchResults;

	}

	private AdapterServicesAccess getAdapterService() {
		AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
				.getAdapterServicesAccess(BusinessConstants.EWPD);
		return adapterServicesAccess;
	}

	public void createStdAccumulator(StandardAccumulator stdAccumulator)
	throws AdapterException, SevereException {

		String bEntity = stdAccumulator.getBe();
		StringTokenizer stringTokenizerBe = new StringTokenizer(bEntity, "~");
		List bEList = new ArrayList();
		while (stringTokenizerBe.hasMoreTokens()) {
			String beVar = stringTokenizerBe.nextToken();
			if (beVar.indexOf("@@") <= -1) {
				bEList.add(beVar);
			}
		}
		
		for(int i=0;i<bEList.size();i++){
			StandardAccumulator tempSA = new StandardAccumulator();
			
			DomainAdapterManager adapter = new DomainAdapterManager();
			SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
			Domain domain=new Domain();
			domain.setBusinessEntity(stdAccumulator.getBe().toString());
			domain.setLineOfBusiness(stdAccumulator.getLineOfBusiness());
			domain.setBusinessGroup(stdAccumulator.getGroup());
			domain.setMarketBusinessUnit(stdAccumulator.getMbu());

			AdapterServicesAccess adapterServicesAccess = this.getAdapterService();
			try {
				domain = adapter.retrieve(domain,adapterServicesAccess);
				if(null == domain){
					domain = new Domain();
					domain.setBusinessEntity(stdAccumulator.getBe().toString());
					domain.setLineOfBusiness(stdAccumulator.getLineOfBusiness());
					domain.setBusinessGroup(stdAccumulator.getGroup());
					domain.setMarketBusinessUnit(stdAccumulator.getMbu());
					domain.setDomainSysId(sequenceAdapterManager.getNextDomainIdSequence(adapterServicesAccess));
					adapter.create(domain,adapterServicesAccess,BusinessConstants.TESTUSER);
				}
			}catch (SevereException e) {
				e.printStackTrace();
				throw new AdapterException("Domain creation failed", e);
			}
			
			tempSA.setBusinessdomain(domain.getDomainSysId());
			
			tempSA.setSystem("ewpd");
			tempSA.setQuestion(stdAccumulator.getQuestion());
			if(stdAccumulator.getByOrCy().trim().equalsIgnoreCase(""))
			{
				tempSA.setByOrCy(null);
			}
			else
			tempSA.setByOrCy(stdAccumulator.getByOrCy());
			tempSA.setBenefit(stdAccumulator.getBenefit());
			tempSA.setId("1");
			boolean flag = deleteStandardAccumulator(tempSA);
			
		}
		if (stdAccumulator.getStandardAccumMappingInsertLst() != null) {

			for (int j = 0; j < stdAccumulator.getStandardAccumMappingInsertLst() .size(); j++) {
				StandardAccumulator stdAccumInsertObj = (StandardAccumulator) stdAccumulator
				.getStandardAccumMappingInsertLst() .get(j);

				DomainAdapterManager adapter = new DomainAdapterManager();
				SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
				Domain domain=new Domain();
				domain.setBusinessEntity(stdAccumInsertObj.getBe().toString());
				domain.setLineOfBusiness(stdAccumInsertObj.getLineOfBusiness());
				domain.setBusinessGroup(stdAccumInsertObj.getGroup());
				domain.setMarketBusinessUnit(stdAccumInsertObj.getMbu());

				AdapterServicesAccess adapterServicesAccess = this.getAdapterService();
				try {
					domain = adapter.retrieve(domain,adapterServicesAccess);
					if(null == domain){
						domain = new Domain();
						domain.setBusinessEntity(stdAccumInsertObj.getBe().toString());
						domain.setLineOfBusiness(stdAccumInsertObj.getLineOfBusiness());
						domain.setBusinessGroup(stdAccumInsertObj.getGroup());
						domain.setMarketBusinessUnit(stdAccumInsertObj.getMbu());
						domain.setDomainSysId(sequenceAdapterManager.getNextDomainIdSequence(adapterServicesAccess));
						adapter.create(domain,adapterServicesAccess,BusinessConstants.TESTUSER);
					}
				}catch (SevereException e) {
					e.printStackTrace();
					throw new AdapterException("Domain creation failed", e);
				}

				stdAccumInsertObj.setBusinessdomain(domain.getDomainSysId());
                if(stdAccumInsertObj.getByOrCy().trim().equalsIgnoreCase("")){
                	stdAccumInsertObj.setByOrCy(null);
                }
				stdAccumInsertObj.setStandardAccumulatorStr(stdAccumInsertObj.getStandardAccumulatorStr());
				stdAccumInsertObj.setSystem(stdAccumulator.getSystem());
				stdAccumInsertObj.setId("1");
				BusinessTransactionContext btc = new BusinessTransactionContextImpl();
				btc
				.setBusinessTransactionType(BusinessTransactionContext.BUSINESS_TRANSACTION_CONTEXT_CREATE);
				btc.setBusinessTransactionUser(BusinessConstants.TESTUSER);

				this
				.getAdapterService()
				.persistObject(
						stdAccumInsertObj,
						"com.wellpoint.wpd.common.accumulator.bo.StandardAccumulator",
						btc);
				/*
				 * this.getAdapterServicesAccess().persistObject(stdAccumInsertObj
				 * ,
				 * "com.wellpoint.wpd.common.accumulator.bo.StandardAccumulator"
				 * ,
				 * this.getBusinessTransactionContext(BusinessTransactionContext
				 * .BUSINESS_TRANSACTION_CONTEXT_CREATE));
				 */
			}
		}

	}
	
	
	 public boolean deleteStandardAccumulator( StandardAccumulator stdAccumulator)
	    throws AdapterException,SevereException {
		SearchResults searchResults = null;
		SearchCriteria searchCriteria = null;
	    HashMap refValSet = new HashMap();
	    refValSet.put("businessdomain",new Integer(stdAccumulator.getBusinessdomain()));
	    refValSet.put("question",new Integer(stdAccumulator.getQuestion()));        
	    refValSet.put("system",stdAccumulator.getSystem());    
	    refValSet.put("benefit",new Integer(stdAccumulator.getBenefit()));    
	    refValSet.put("byOrCy",stdAccumulator.getByOrCy());    
	    try{
	    	searchCriteria = AdapterUtil.getAdapterSearchCriteria(StandardAccumulator.class.getName(), "deleteStdAccumulator", refValSet);
	    	searchResults = AdapterUtil.performSearch(searchCriteria);
	    }catch(Exception ex){
	    	throw new AdapterException ("Exception occured while adapter call",ex);
	    }
	    return true;   
	}
}
