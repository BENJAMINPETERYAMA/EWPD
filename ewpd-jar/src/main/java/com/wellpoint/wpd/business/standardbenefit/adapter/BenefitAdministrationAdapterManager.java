/*
 * Created on Mar 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.standardbenefit.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.wellpoint.adapter.access.AdapterAccessFactory;
import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.BusinessTransactionContext;
import com.wellpoint.adapter.access.BusinessTransactionContextImpl;
import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchCriteriaImpl;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.BenefitAdministrationLocateCriteria;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.LocateCriteria;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitAdministrationBO;


/**
 * @author U13274
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitAdministrationAdapterManager {
    private BusinessTransactionContext getTransactionContext(String operation, String userId){
        BusinessTransactionContext btc = new BusinessTransactionContextImpl();
        btc.setBusinessTransactionType(operation);
        btc.setBusinessTransactionUser("testUser");
        return btc;
    }
    
    private AdapterServicesAccess getAdapterService(){
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory.getAdapterServicesAccess("ewpd");
        return adapterServicesAccess;
    }
    
    /**
	 * @see com.wellpoint.wpd.business.framework.bo.manager.BenefitDefinitionAdapterManager#persist(java.lang.Object, com.wellpoint.wpd.common.bo.BusinessObject, com.wellpoint.wpd.common.bo.Audit, boolean)
	 * @param subObject
	 * @param mainObject
	 * @param audit
	 * @param insertFlag
	 * @return
	 * @throws WPDException
	 */
	public boolean createBenefitAdministrationObject(BenefitAdministrationBO subObject, Audit audit, boolean insertFlag) throws SevereException {
		//BusinessTransactionContext btc = new BusinessTransactionContextImpl();
		//btc.setBusinessTransactionType(WebConstants.CREATE_STRUCTURE);
		//btc.setBusinessTransactionUser("USER");
		//AdapterServicesAccess adapterServicesAccess = this.getAdapterService();
		AdapterUtil.performInsert(subObject, BusinessConstants.TESTUSER);
		return true;
	}
	
	/**
	 * @see com.wellpoint.wpd.business.framework.bo.manager.BenefitDefinitionAdapterManager#persist(java.lang.Object, com.wellpoint.wpd.common.bo.BusinessObject, com.wellpoint.wpd.common.bo.Audit, boolean)
	 * @param subObject
	 * @param mainObject
	 * @param audit
	 * @param insertFlag
	 * @return
	 * @throws WPDException
	 */
	public boolean updateBenefitAdministrationObject(BenefitAdministrationBO subObject, Audit audit, boolean insertFlag) throws SevereException {
	//	BusinessTransactionContext btc = new BusinessTransactionContextImpl();
	//	btc.setBusinessTransactionType("edit");
	//	btc.setBusinessTransactionUser("USER");
	//	AdapterServicesAccess adapterServicesAccess = this.getAdapterService();
		AdapterUtil.performUpdate(subObject, BusinessConstants.TESTUSER);
		return true;
	}
	
	/**
	 * @see com.wellpoint.wpd.business.framework.bo.manager.BenefitDefinitionAdapterManager#persist(java.lang.Object, com.wellpoint.wpd.common.bo.BusinessObject, com.wellpoint.wpd.common.bo.Audit, boolean)
	 * @param subObject
	 * @param mainObject
	 * @param audit
	 * @param insertFlag
	 * @return
	 * @throws AdapterException
	 * @throws WPDException
	 */
	public boolean deleteBenefitAdministrationObject
				(BenefitAdministrationBO subObject, Audit audit) 
				throws SevereException, AdapterException {
	//	BusinessTransactionContext btc = new BusinessTransactionContextImpl();
	//	btc.setBusinessTransactionType("DELETE");
	//	btc.setBusinessTransactionUser("USER");
		/*AdapterServicesAccess adapterServicesAccess = 
			AdapterAccessFactory.getAdapterServicesAccess("ewpd");*/
	//	AdapterUtil.performRemove(subObject, BusinessConstants.TESTUSER);
		
		SearchResults searchResults = null;
		HashMap refValSet = new HashMap();
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
		refValSet.put("benefitAdministrationKeysForDelete",
				subObject.getBenefitAdministrationKeysForDelete());
		refValSet.put("benefitDefinitionKey", new Integer(subObject.getBenefitDefinitionKey()));
		searchCriteria
              .setBusinessObjectName("com.wellpoint.wpd.common.standardbenefit.bo.BenefitAdministrationBO");
		searchCriteria.setMaxSearchResultSize(100);
		searchCriteria.setSearchQueryName("benefitAdministrationDelete");
		searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
		searchCriteria.setReferenceValueSet(refValSet);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        return true;
	}
	
	
	/**
	 * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder#locate(com.wellpoint.wpd.common.bo.LocateCriteria)
	 * @param locateCriteria
	 * @return
	 * @throws WPDException
	 */
	public LocateResults locate(LocateCriteria locateCriteria) throws SevereException {
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
		SearchResults searchResults = null;
		LocateResults locateResults = new LocateResults();
		List locateResultsList = new ArrayList();
		BenefitAdministrationLocateCriteria benefitAdministrationLocateCriteria = (BenefitAdministrationLocateCriteria)locateCriteria;
		searchCriteria.setBusinessObjectName("com.wellpoint.wpd.common.standardbenefit.bo.BenefitAdministrationBO");
		searchCriteria.setMaxSearchResultSize(Integer.MAX_VALUE);
		searchCriteria.setSearchQueryName("getAssociatedBenefitAdministration");
		searchCriteria.setSearchDomain("medical");
		HashMap refValSet = new HashMap();
		refValSet.put("benefitDefinitionKey", new Integer(benefitAdministrationLocateCriteria.getBenefitDefinitionKey()));
		searchCriteria.setReferenceValueSet(refValSet);
		searchResults = AdapterUtil.performSearch(searchCriteria);
		locateResults.setLocateResults(searchResults.getSearchResults());
		return locateResults;
	}

	
	/**
	 * to retrieve the data in the selected row for editing in benefitAdministration.jsp
	 * 
	 * 
	 * @param BenefitAdministrationBO
	 * @return
	 * @throws WPDException
	 */
	public Object retrieveForEdit(BenefitAdministrationBO businessObject)throws SevereException{
		businessObject = (BenefitAdministrationBO)AdapterUtil.performRetrieve(businessObject);
		return businessObject;
	}
}
