/*
 * DomainAdapterManager.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.common.adapter;

import java.util.HashMap;
import java.util.List;

import com.wellpoint.adapter.access.AdapterAccessFactory;
import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.domain.bo.Domain;
import com.wellpoint.wpd.common.domain.bo.DomainInfo;
import com.wellpoint.wpd.common.domain.bo.DomainItem;
import com.wellpoint.wpd.common.domain.bo.EntityDomainInfo;
import com.wellpoint.wpd.common.domain.bo.QuestionnaireDomainInfo;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.migration.bo.MigrationDomainInfo;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class DomainAdapterManager {

    /**
     * @deprecated
     */
    public void create(DomainInfo domainInfo, AdapterServicesAccess adapterServicesAccess) throws SevereException{
        AdapterUtil.performInsert(domainInfo,"user",adapterServicesAccess);
    }
   
    /**
     * @deprecated
     */
    public DomainInfo retrieve(DomainInfo domainInfo, AdapterServicesAccess adapterServicesAccess, String userId)throws SevereException{
        return (DomainInfo)AdapterUtil.performRetrieve(domainInfo,adapterServicesAccess);
    }
    
    public void create(Domain domain, AdapterServicesAccess adapterServicesAccess, String userId) throws SevereException{
        AdapterUtil.performInsert(domain,userId,adapterServicesAccess);
    }
    
    public Domain retrieve(Domain domain, AdapterServicesAccess adapterServicesAccess)throws SevereException{
        return (Domain)AdapterUtil.performRetrieve(domain,adapterServicesAccess);
    }
    
    public void create(EntityDomainInfo entityDomainInfo, AdapterServicesAccess adapterServicesAccess, String userId) throws SevereException{
        AdapterUtil.performInsert(entityDomainInfo,userId,adapterServicesAccess);
    }

    public void remove(EntityDomainInfo entityDomainInfo, AdapterServicesAccess adapterServicesAccess, String userId) throws SevereException{
        AdapterUtil.performRemove(entityDomainInfo,userId,adapterServicesAccess);
    }

    public List searchLineOfBusiness(String entityType, int entitySystemId, int catlogId) throws SevereException{
       return searchDomain(entityType, entitySystemId, catlogId,"getLineOfBusiness");
    }
    
    public List searchBusinessEntity(String entityType, int entitySystemId, int catlogId) throws SevereException{
        return searchDomain(entityType, entitySystemId, catlogId,"getBusinessEntity");
    }

    public List searchBusinessGroup(String entityType, int entitySystemId, int catlogId) throws SevereException{
        return searchDomain(entityType, entitySystemId, catlogId,"getBusinessGroup");
    }
    
    public List searchMarketBusinessUnit(String entityType, int entitySystemId, int catlogId) throws SevereException{
        return searchDomain(entityType, entitySystemId, catlogId,"getMarketBusinessUnit");
    }
    
    public List getAssociatedDomains(String entityType, int entityParentId) throws SevereException{
        HashMap valueSet = new HashMap();
        valueSet.put("entityType",entityType);
        valueSet.put("parentId",new Integer(entityParentId));
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(Domain.class.getName(),"getDomains",valueSet);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        return searchResults.getSearchResults();
        
    }
  
   public List getDomainsForMigration(int entityParentId) throws SevereException{
        HashMap valueSet = new HashMap();
        valueSet.put("contractSysId",new Integer(entityParentId));
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(MigrationDomainInfo.class.getName(),"retrieveDomainInfo",valueSet);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        return searchResults.getSearchResults();
        
    }
    
    public List searchDomain(String entityType, int entitySystemId, int catlogId, String queryName) throws SevereException{
        HashMap valueSet = new HashMap();
        valueSet.put("catlogId",new Integer(catlogId));
        valueSet.put("entitySystemId", new Integer(entitySystemId));
        valueSet.put("entityType",entityType);
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(DomainItem.class.getName(),queryName,valueSet);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        return searchResults.getSearchResults();
    }
    
    public List searchForDuplicateDomain(String entityType, String entityName, List lineOfBusiness, List businessEntity, List businessGroup, List marketBusinessUnit, int entityId) throws SevereException{
        HashMap valueSet = new HashMap();
        valueSet.put("entityType",entityType);
        valueSet.put("entityName", entityName);
        valueSet.put("lineOfBusiness",lineOfBusiness);
        valueSet.put("businessEntity",businessEntity);
        valueSet.put("businessGroup",businessGroup);
        valueSet.put("marketBusinessUnit", marketBusinessUnit);
        valueSet.put("entityId",new Integer(entityId));
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(EntityDomainInfo.class.getName(),"findDuplicate",valueSet);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        return searchResults.getSearchResults();
    }
    
    // Changes For Questionnaire Domain Info 
    public void createQuestionnaireDomain(QuestionnaireDomainInfo questionnaireDomainInfo, AdapterServicesAccess adapterServicesAccess, String userId) throws SevereException{
        AdapterUtil.performInsert(questionnaireDomainInfo,userId,adapterServicesAccess);
    }
    public void removeQuestionnaireDomain(QuestionnaireDomainInfo questionnaireDomainInfo, AdapterServicesAccess adapterServicesAccess, String userId) throws SevereException{
        AdapterUtil.performRemove(questionnaireDomainInfo,userId,adapterServicesAccess);
    }
    public List searchQstnrLineOfBusiness(int entitySystemId, int catlogId) throws SevereException{
        return searchQstnrDomain(entitySystemId, catlogId,"getQuestionnaireLineOfBusiness");
     }
     
     public List searchQstnrBusinessEntity(int entitySystemId, int catlogId) throws SevereException{
         return searchQstnrDomain(entitySystemId, catlogId,"getQuestionnaireBusinessEntity");
     }

     public List searchQstnrBusinessGroup(int entitySystemId, int catlogId) throws SevereException{
         return searchQstnrDomain(entitySystemId, catlogId,"getQuestionnaireBusinessGroup");
     }
     
     public List searchQstnrMarketBusinessUnit(int entitySystemId, int catlogId) throws SevereException{
        return searchQstnrDomain(entitySystemId, catlogId,"getQuestionnaireMarketBusinessUnit");
     }
     
    public List searchQstnrDomain(int entitySystemId, int catlogId, String queryName) throws SevereException{
        HashMap valueSet = new HashMap();
        valueSet.put("catlogId",new Integer(catlogId));
        valueSet.put("qstnrHrchyId", new Integer(entitySystemId));
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(DomainItem.class.getName(),queryName,valueSet);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        return searchResults.getSearchResults();
    }
    public List getQuestionnairAssociatedDomains(int entityParentId) throws SevereException{
        HashMap valueSet = new HashMap();
        valueSet.put("qstnrHrchyId",new Integer(entityParentId));
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(QuestionnaireDomainInfo.class.getName(),"getQuestionnaireDomains",valueSet);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        return searchResults.getSearchResults();
        
    }
    public List searchForQuestionnaireDuplicateDomain(List lineOfBusiness, List businessEntity, List businessGroup, List marketBusinessUnit, int questionnaireHrchySysId) throws SevereException{
        HashMap valueSet = new HashMap();
        valueSet.put("lineOfBusiness",lineOfBusiness);
        valueSet.put("businessEntity",businessEntity);
        valueSet.put("businessGroup", businessGroup);
        valueSet.put("marketBusinessUnit", marketBusinessUnit);
        valueSet.put("qstnrHrchyId",new Integer(questionnaireHrchySysId));
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(QuestionnaireDomainInfo.class.getName(),"findQuestionnaireDuplicate",valueSet);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        return searchResults.getSearchResults();
    }
    public List getFunctionalDomain(int questionnaireHrchySysId) throws SevereException {
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("qstnrHrchyId",new Integer(questionnaireHrchySysId));
        SearchResults searchResult = null;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
        		DomainItem.class.getName(), "functionalDomainSearch", referenceValueSet);
        searchResult = AdapterUtil.performSearch(searchCriteria);
        if (null != searchResult) {
            return searchResult.getSearchResults();
        }
        return null;
    }
    
    public AdapterServicesAccess getAdapterService() {
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess(BusinessConstants.EWPD);
        return adapterServicesAccess;
    }

//  changes ends here
}
