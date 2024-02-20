/*
 * ServiceTypeMappingBusinessObjectBuilder.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.blueexchange.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.blueexchange.adapter.ServiceTypeMappingAdapterManager;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.common.adapter.SequenceAdapterManager;
import com.wellpoint.wpd.common.blueexchange.bo.RuleMapping;
import com.wellpoint.wpd.common.blueexchange.bo.RuleServiceTypeAssociation;
import com.wellpoint.wpd.common.blueexchange.bo.ServiceTypeLocateCriteriaBO;
import com.wellpoint.wpd.common.blueexchange.bo.ServiceTypeMapping;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.framework.exception.SevereException;



/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ServiceTypeMappingBusinessObjectBuilder {
	
	
	ServiceTypeMappingAdapterManager adapterManager = new ServiceTypeMappingAdapterManager();
	
	public boolean isRuleExist(String ruleId) throws SevereException {
		HashMap map = new HashMap();
		map.put("ruleId",ruleId);
		SearchCriteria criteria = AdapterUtil.getAdapterSearchCriteria(RuleMapping.class.getName(),"checkRule",map);
		SearchResults searchResults = AdapterUtil.performSearch(criteria);
		if(searchResults.getSearchResultCount() > 0)
			return true;
		else
			return false;
	}
	
	public boolean isRuleMapped(String ruleId) throws SevereException {
		HashMap map = new HashMap();
		map.put("ruleId",ruleId);
		SearchCriteria criteria = AdapterUtil.getAdapterSearchCriteria(RuleMapping.class.getName(),"checkWhetherMapped",map);
		SearchResults searchResults = AdapterUtil.performSearch(criteria);
		if(searchResults.getSearchResultCount() > 0)
			return false;
		else
			return true;
		
	}
	
	public RuleMapping retrieveRuleMapping(String ruleId) throws SevereException {
		RuleMapping mapping = new RuleMapping();
		mapping.setRuleId(ruleId);
		mapping = adapterManager.retrieveRuleMapping(mapping);
		if(mapping == null)
			return null;
		List serviceTypeCodes = adapterManager.retrieveAssociatedServiceTypes(ruleId);
		mapping.setServiceTypeCodes(serviceTypeCodes);
		return mapping;
	}
	
	public void createRuleMapping(RuleMapping ruleMapping, Audit audit) throws SevereException {
		ruleMapping.setCreatedUser(audit.getUser());
		ruleMapping.setLastUpdatedUser(audit.getUser());
		ruleMapping.setCreatedTimestamp(audit.getTime());
		ruleMapping.setLastUpdatedTimestamp(audit.getTime());
		AdapterUtil.performInsert(ruleMapping,audit.getUser());
	}
	
	public void createRuleServiceTypeAssociation(RuleServiceTypeAssociation serviceTypeAssociation, Audit audit) throws SevereException {
		serviceTypeAssociation.setCreatedUser(audit.getUser());
		serviceTypeAssociation.setLastUpdatedUser(audit.getUser());
		serviceTypeAssociation.setCreatedTimestamp(audit.getTime());
		serviceTypeAssociation.setLastUpdatedTimestamp(audit.getTime());	
		AdapterUtil.performInsert(serviceTypeAssociation,audit.getUser());
	}
	
	public void updateRuleMapping(RuleMapping ruleMapping, Audit audit) throws SevereException {
		ruleMapping.setLastUpdatedUser(audit.getUser());
		ruleMapping.setLastUpdatedTimestamp(audit.getTime());
		AdapterUtil.performUpdate(ruleMapping,audit.getUser());	
	}
	
	public void updateRuleMappingTimeStamp(String ruleId, Audit audit) throws SevereException {
		RuleMapping ruleMapping = new RuleMapping();
		ruleMapping.setRuleId(ruleId);
		ruleMapping = adapterManager.retrieveRuleMapping(ruleMapping);
		ruleMapping.setLastUpdatedUser(audit.getUser());
		ruleMapping.setLastUpdatedTimestamp(audit.getTime());
		AdapterUtil.performUpdate(ruleMapping,audit.getUser());			
	}
	
	public void deleteServiceTypeAssociation(RuleServiceTypeAssociation serviceTypeAssociation, Audit audit) throws SevereException {
		AdapterUtil.performRemove(serviceTypeAssociation,audit.getUser());
	}
	

	
	public void updateServiceTypeAssociation(RuleServiceTypeAssociation serviceTypeAssociation, Audit audit) throws SevereException {
		serviceTypeAssociation.setLastUpdatedUser(audit.getUser());
		serviceTypeAssociation.setLastUpdatedTimestamp(audit.getTime());	
		AdapterUtil.performUpdate(serviceTypeAssociation,audit.getUser());				
	}
	
	public List retrieveUnMappedRules() throws SevereException {
		HashMap map = new HashMap();
		SearchCriteria criteria = AdapterUtil.getAdapterSearchCriteria(RuleMapping.class.getName(),"retrieveUnMappedRules",map);
		SearchResults searchResults = AdapterUtil.performSearch(criteria);
		return searchResults.getSearchResults();
	}
	/**
	 * Creates service type mapping.
	 * @param serviceTypeMapping
	 * @param audit
	 * @throws SevereException
	 */
	public void create(ServiceTypeMapping serviceTypeMapping, Audit audit) throws SevereException {
		serviceTypeMapping.setCreatedUser(audit.getUser());
		serviceTypeMapping.setCreatedTimestamp(audit.getTime());
		serviceTypeMapping.setLastUpdatedUser(audit.getUser());
		serviceTypeMapping.setLastUpdatedTimestamp(audit.getTime());
		SequenceAdapterManager sequenceAdapter = new SequenceAdapterManager();
		int sysId = sequenceAdapter.getNextServTypeMappingSysId();
		serviceTypeMapping.setMappingSysId(sysId);
		adapterManager.createServiceTypeMapping(serviceTypeMapping, audit);
	}
	

	/**
	 * Updates Service Type mapping.
	 * @param serviceTypeMapping
	 * @param audit
	 * @throws SevereException
	 */
	public void update(ServiceTypeMapping serviceTypeMapping, Audit audit) throws SevereException {
		serviceTypeMapping.setLastUpdatedUser(audit.getUser());
		serviceTypeMapping.setLastUpdatedTimestamp(audit.getTime());
		adapterManager.updateServiceTypeMapping(serviceTypeMapping, audit);
	}
	
	/**
	 * Checking for duplicate mapping.
	 * @param serviceTypeMapping
	 * @return
	 * @throws SevereException
	 */
	public boolean isServiceTypeMappingDuplicate(ServiceTypeMapping serviceTypeMapping) throws SevereException {
		HashMap map = new HashMap();
		map.put("eb03Identifier",serviceTypeMapping.getEb03Identifier());
		map.put("serviceCode", serviceTypeMapping.getServiceCode());
		map.put("serviceClassLow", serviceTypeMapping.getServiceClassLow());
		map.put("serviceClassHigh", serviceTypeMapping.getServiceClassHigh());
		map.put("placeOfService", serviceTypeMapping.getPlaceOfService());
		map.put("limitClassHigh", serviceTypeMapping.getLimitClassHigh());
		map.put("limitClassLow", serviceTypeMapping.getLimitClassLow());
		map.put("diagnosisLow", serviceTypeMapping.getDiagnosisLow());
		map.put("diagnosisHigh", serviceTypeMapping.getDiagnosisHigh());
		map.put("procModifierCode", serviceTypeMapping.getProcModifierCode());
		map.put("hcpHigh", serviceTypeMapping.getHcpHigh());
		map.put("hcpLow", serviceTypeMapping.getHcpLow());
		map.put("providerSpeciality", serviceTypeMapping.getProviderSpeciality());
		map.put("mappingSysId", new Integer(serviceTypeMapping.getMappingSysId()));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(ServiceTypeMapping.class.getName(),"checkDuplicate",map);
		SearchResults searchResult = AdapterUtil.performSearch(searchCriteria);
		if(searchResult.getSearchResultCount() > 0 )
			return true;
		return false;
	}

	
	/**
	 * Methode to Locate the Service Type MAppings corresponding the criteria
	 * @return  SearchResults
	 * @throws SevereException
	 */
	public SearchResults searchServiceTypeMapping(ServiceTypeLocateCriteriaBO criteriaBO) throws SevereException{
	       SearchResults searchResults = null;
	       searchResults = adapterManager.searchServiceTypeMapping(criteriaBO);	       
	       return searchResults;   
	}	
	
	/**
	 * Method to retrive ServiceTypeMappings for Datafeed
	 * @return List
	 * @throws SevereException
	 */
	public List retriveServiceTypeMappingDF(String status) throws SevereException{
		try {
			List searchList = new ArrayList();
			searchList = adapterManager.retrieveServiceMappingForDataFeed(status);
			return searchList;
		} catch (AdapterException excp) {
			List errorParams = new ArrayList();
			String obj = excp.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in retrieving SPSMapping Values in SPSMappingBusinessObjectBuilder",
					errorParams, excp);
		}
	}
	/**
	 * Method to retrive ServiceTypeMappings for Datafeed
	 * @return List
	 * @throws SevereException
	 */
	public List retriveNAServiceTypeMappingDF() throws SevereException{
		try {
			List searchList = new ArrayList();
			searchList = adapterManager.retrieveNAServiceMappingForDataFeed();
			return searchList;
		} catch (AdapterException excp) {
			List errorParams = new ArrayList();
			String obj = excp.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in retrieving SPSMapping Values in SPSMappingBusinessObjectBuilder",
					errorParams, excp);
		}
	}
	/**
	 * @throws SevereException
	 */
	public ServiceTypeMapping retrieve(ServiceTypeMapping serviceTypeMapping) throws SevereException {
		return adapterManager.retrieveServiceTypeMapping(serviceTypeMapping);
	}
	
	/**
     * Method to Delete latest version of contract.
     * 
     * @param contract
     * @param audit
     * @return
     * @throws SevereException
     */
    public boolean delete(ServiceTypeMapping serviceTypeMapping, Audit audit)
            throws SevereException {
        try {
            adapterManager.deleteServiceTypeMapping(serviceTypeMapping, audit);
        }catch (SevereException ex) {
            List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException("Exception in delete Contract in ContractBusinessObjectBuilder", errorParams, ex);
        } catch (Exception ex) {
            throw new SevereException(null, null, ex);
        }
        return true;
    }
    
}
