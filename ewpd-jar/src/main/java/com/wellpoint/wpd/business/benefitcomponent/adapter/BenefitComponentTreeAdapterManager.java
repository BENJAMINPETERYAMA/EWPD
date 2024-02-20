/*
 * BenefitComponentTreeAdapterManager.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.benefitcomponent.adapter;

import com.wellpoint.adapter.access.AdapterAccessFactory;
import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchCriteriaImpl;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.benefitcomponent.tree.bo.TreeAdminLevels;
import com.wellpoint.wpd.common.benefitcomponent.tree.bo.TreeBenefitAdministration;
import com.wellpoint.wpd.common.benefitcomponent.tree.bo.TreeBenefitComponent;
import com.wellpoint.wpd.common.framework.exception.ServiceException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Adapter Manager for benefit componet tree
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitComponentTreeAdapterManager {
    public List getBenefitData(TreeBenefitComponent treeDataObject)
            throws ServiceException {
        HashMap benefitComponentMap = new HashMap();
        setTreeComponentValuesToMap(benefitComponentMap, treeDataObject);
        SearchCriteria benefitComponentSearchCriteria = new SearchCriteriaImpl();
        benefitComponentSearchCriteria
                .setReferenceValueSet(benefitComponentMap);
        benefitComponentSearchCriteria
                .setBusinessObjectName(BusinessConstants.TREE_BENEFIT_COMPONENT);
        benefitComponentSearchCriteria
                .setSearchQueryName("retrieveByBenefitComponent");
        benefitComponentSearchCriteria.setMaxSearchResultSize(300);
        benefitComponentSearchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
        List benefitList = null;
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess();
        try {
            benefitList = adapterServicesAccess.searchObject(
                    benefitComponentSearchCriteria).getSearchResults();
        } catch (AdapterException adapterException) {
            throw new ServiceException(BusinessConstants.ADAPTER_EXCEPTION, null,
                    adapterException);
        } catch (Exception ex) {
            throw new ServiceException(BusinessConstants.ADAPTER_EXCEPTION, null, ex);
        }
        return benefitList;

    }


    /**
     * Fetches admin data
     * 
     * @param treeDataObject
     * @return benefitList
     * @throws ServiceException
     */
    public List getAdminData(TreeBenefitAdministration treeDataObject)
            throws ServiceException {
        HashMap standardBenefitMap = new HashMap();
        setTreeBenefitValuesToMap(standardBenefitMap, treeDataObject);
        SearchCriteria benefitComponentSearchCriteria = new SearchCriteriaImpl();
        benefitComponentSearchCriteria.setReferenceValueSet(standardBenefitMap);
        benefitComponentSearchCriteria
                .setBusinessObjectName(BusinessConstants.TREE_BENEFIT_ADMIN);
        benefitComponentSearchCriteria
                .setSearchQueryName("retrieveByStandardBenefit");
        benefitComponentSearchCriteria.setMaxSearchResultSize(300);
        benefitComponentSearchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
        List benefitList = null;
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess();
        try {
            benefitList = adapterServicesAccess.searchObject(
                    benefitComponentSearchCriteria).getSearchResults();
        } catch (AdapterException adapterException) {
            throw new ServiceException(BusinessConstants.ADAPTER_EXCEPTION, null,
                    adapterException);
        } catch (Exception e1) {
            throw new ServiceException(BusinessConstants.ADAPTER_EXCEPTION, null, e1);
        }
        return benefitList;

    }


    /**
     * Fetches admin option data
     * 
     * @param treeDataObject
     * @return benefitList
     * @throws ServiceException
     */
    public List getAdminOptionData(TreeAdminLevels treeDataObject)
            throws ServiceException {
        HashMap administrationMap = new HashMap();
        setTreeAdministrationToMap(administrationMap, treeDataObject);
        SearchCriteria benefitComponentSearchCriteria = new SearchCriteriaImpl();
        benefitComponentSearchCriteria.setReferenceValueSet(administrationMap);
        benefitComponentSearchCriteria
                .setBusinessObjectName(BusinessConstants.TREE_ADMIN_LEVELS);
        if(treeDataObject.getAdminLevelType()==2)
        benefitComponentSearchCriteria
                .setSearchQueryName("retrieveByAdministrationIdForBenefit");
        else
        	benefitComponentSearchCriteria
            .setSearchQueryName("retrieveByAdministrationIdForBenefitLevel");
        benefitComponentSearchCriteria.setMaxSearchResultSize(300);
        benefitComponentSearchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
        List benefitList = null;
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess();
        try {
            benefitList = adapterServicesAccess.searchObject(
                    benefitComponentSearchCriteria).getSearchResults();
        } catch (AdapterException adapterException) {
            throw new ServiceException(BusinessConstants.ADAPTER_EXCEPTION, null,
                    adapterException);
        } catch (Exception ex) {
            throw new ServiceException(BusinessConstants.ADAPTER_EXCEPTION, null, ex);
        }
        return benefitList;

    }


    /**
     * Method to put the search criteria into Map
     * 
     * @param map
     * @param treeDataObject
     */
    public void setTreeComponentValuesToMap(Map map,
            TreeBenefitComponent treeDataObject) {
        map.put("benefitComponentId", Integer.toString(treeDataObject
                .getBenefitComponentId()));

    }


    /**
     * Method to put the search criteria into Map
     * 
     * @param map
     * @param treeDataObject
     */
    public void setTreeBenefitValuesToMap(Map map,
            TreeBenefitAdministration treeDataObject) {
        map.put("standardBenefitId", Integer.toString(treeDataObject
                .getStandardBenefitId()));
        map.put("benefitComponentId", Integer.toString(treeDataObject
                .getBenefitComponentId()));

    }


    /**
     * Method to put the search criteria into Map
     * 
     * @param map
     * @param treeDataObject
     */
    public void setTreeAdministrationToMap(Map map,
            TreeAdminLevels treeDataObject) {
        map.put("administrationId", Integer.toString(treeDataObject
                .getBenefitAdministrationId()));
        map.put("entitySysId", Integer.toString(treeDataObject
                .getEntitySysId()));
        map.put("benefitComponentId", Integer.toString(treeDataObject
                .getBenefitComponentId()));
    }

}