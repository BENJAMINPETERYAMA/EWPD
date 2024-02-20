/*
 * StandardBenefitTreeAdapterManager.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.standardbenefit.adapter;

import com.wellpoint.adapter.access.AdapterAccessFactory;
import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchCriteriaImpl;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.standardbenefit.tree.bo.TreeAdminOptions;
import com.wellpoint.wpd.common.standardbenefit.tree.bo.TreeBenefitAdministration;
import com.wellpoint.wpd.common.standardbenefit.tree.bo.TreeBenefitDefinition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class StandardBenefitTreeAdapterManager {
	/**
     *  Adapter Manager methode to return Benefit Definition List
     * @param treeDataObject
     * @return List
     * @throws ServiceException
     */
	public List getStandardDefinitionData(TreeBenefitDefinition treeDataObject) throws ServiceException{
		HashMap standardBenefitMap = new HashMap();
		setTreeStandardValuesToMap(standardBenefitMap, treeDataObject);
        SearchCriteria standardBenefitSearchCriteria = new SearchCriteriaImpl();
        standardBenefitSearchCriteria.setReferenceValueSet(standardBenefitMap);
        standardBenefitSearchCriteria
                .setBusinessObjectName("com.wellpoint.wpd.common.standardbenefit.tree.bo.TreeBenefitDefinition");
        standardBenefitSearchCriteria
                .setSearchQueryName("retrieveByBenefitId");
        standardBenefitSearchCriteria.setMaxSearchResultSize(300);
        standardBenefitSearchCriteria.setSearchDomain("medical");
        List benefitDefinitionList = null;
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess();
        try {
        	benefitDefinitionList = adapterServicesAccess.searchObject(
        			standardBenefitSearchCriteria).getSearchResults();
        } catch (AdapterException adapterException) {
            throw new ServiceException("Adapter Manager", null,
                    adapterException);
        } catch (Exception e1) {
            throw new ServiceException("Adapter Manager", null,
                    e1);
        }
        return benefitDefinitionList;
	}

	/**
     *  Adapter Manager methode to return Benefit Administration List
     * @param treeDataObject
     * @return List
     * @throws ServiceException
     */
	public List getBenefitAdminData (TreeBenefitAdministration treeDataObject) throws ServiceException{
		HashMap standardBenefitMap = new HashMap();
		setTreeAdminValuesToMap(standardBenefitMap, treeDataObject);
        SearchCriteria standardBenefitSearchCriteria = new SearchCriteriaImpl();
        standardBenefitSearchCriteria.setReferenceValueSet(standardBenefitMap);
        standardBenefitSearchCriteria
                .setBusinessObjectName("com.wellpoint.wpd.common.standardbenefit.tree.bo.TreeBenefitAdministration");
        standardBenefitSearchCriteria
                .setSearchQueryName("retrieveByBenefitDef");
        standardBenefitSearchCriteria.setMaxSearchResultSize(300);
        standardBenefitSearchCriteria.setSearchDomain("medical");
        List benefitAdminList = null;
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess();
        try {
        	benefitAdminList = adapterServicesAccess.searchObject(
        			standardBenefitSearchCriteria).getSearchResults();
        } catch (AdapterException adapterException) {
            throw new ServiceException("Adapter Manager", null,
                    adapterException);
        } catch (Exception e1) {
            throw new ServiceException("Adapter Manager", null,
                    e1);
        }
        return benefitAdminList;
	}
	
	/**
     *  Adapter Manager methode to return Admin Option List
     * @param treeDataObject
     * @return List
     * @throws ServiceException
     */
	public List getAdminOptionData (TreeAdminOptions treeDataObject) throws ServiceException{
		HashMap standardBenefitMap = new HashMap();
		setTreeAdminOptionsToMap(standardBenefitMap, treeDataObject);
        SearchCriteria standardBenefitSearchCriteria = new SearchCriteriaImpl();
        standardBenefitSearchCriteria.setReferenceValueSet(standardBenefitMap);
        standardBenefitSearchCriteria
                .setBusinessObjectName("com.wellpoint.wpd.common.standardbenefit.tree.bo.TreeAdminOptions");
        standardBenefitSearchCriteria
                .setSearchQueryName("retrieveByBenefitAdmn");
        standardBenefitSearchCriteria.setMaxSearchResultSize(Integer.MAX_VALUE);
        standardBenefitSearchCriteria.setSearchDomain("medical");
        List adminOptionList = null;
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess();
        try {
        	adminOptionList = adapterServicesAccess.searchObject(
        			standardBenefitSearchCriteria).getSearchResults();
        } catch (AdapterException adapterException) {
            throw new ServiceException("Adapter Manager", null,
                    adapterException);
        } catch (Exception e1) {
            throw new ServiceException("Adapter Manager", null,
                    e1);
        }
        return adminOptionList;
	}
	
	/**
     *  Methode to put the search criteria into Map
     * @param map,treeDataObject
     */
	public void setTreeStandardValuesToMap(Map map,
			TreeBenefitDefinition treeDataObject) {
        map.put("standardBenefitId", Integer.toString(treeDataObject.getStandardBenefitId()));
        map.put("benefitDefinitionType",treeDataObject.getBenefitDefinitionType());
    }
	
	/**
     *  Methode to put the search criteria into Map
     * @param map,treeDataObject
     */
	public void setTreeAdminValuesToMap(Map map,
			TreeBenefitAdministration treeDataObject) {
        map.put("benefitDefinitionId", Integer.toString(treeDataObject.getBenefitDefinitionId()));
       
    }
	
	/**
     *  Methode to put the search criteria into Map
     * @param map,treeDataObject
     */
	public void setTreeAdminOptionsToMap(Map map,
			TreeAdminOptions treeDataObject) {
        map.put("benefitAdministrationId", Integer.toString(treeDataObject.getBenefitAdministrationId()));
        map.put("AdminLevelType",Integer.toString(treeDataObject.getAdminLevelType()));
       
    }
	
}
