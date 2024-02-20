/**
* CatalogAdapterManager.java 
*  © 2006 WellPoint, Inc. All Rights Reserved.
* 
* This software is the confidential and proprietary information of Wellpoint
* Inc. ("Confidential Information"). You shall not disclose or use Confidential
* Information without the express written agreement of Wellpoint Inc.
* Created on May 16, 2007
*/
package com.wellpoint.wpd.business.catalog.adapter;

import java.util.HashMap;
import java.util.List;

import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchCriteriaImpl;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.catalog.locatecriteria.CatalogLocateCriteria;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.LocateCriteria;
import com.wellpoint.wpd.common.catalog.bo.CatalogBO;
import com.wellpoint.wpd.common.catalog.bo.SrdaCatalogBO;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.security.domain.User;
/**
 * Base class for catalog adapter
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class CatalogAdapterManager {
    /**
     * Method to create catalog
     * @param catalogBO
     * @param audit
     * @return boolean
     * @throws SevereException
     */
	public boolean createCatalog(CatalogBO catalogBO, Audit audit)
			throws SevereException, AdapterException{
		Logger.logInfo("CatalogAdapterManager - Entering createCatalog(): Catalog Create");
		try {
			AdapterUtil.performInsert(catalogBO, audit.getUser());
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
		Logger.logInfo("CatalogAdapterManager - Returning createCatalog(): Catalog Create");
		return true;
	}
    
	/**
	 * Method to edit catalog
	 * @param catalogBO
	 * @param audit
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean editCatalog(CatalogBO catalogBO, Audit audit)
			throws SevereException, AdapterException {
		Logger.logInfo("CatalogAdapterManager - Entering editCatalog(): Catalog Update");
		try {
		AdapterUtil.performUpdate(catalogBO, audit.getUser());
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
		Logger.logInfo("CatalogAdapterManager - Returning editCatalog(): Catalog Edit");
		return true;
	}
    
	/**
	 * Method to delete catalog
	 * @param catalogBO
	 * @param user
	 * @return boolean
	 * @throws SevereException
	 */
	
	public boolean deleteCatalog(CatalogBO catalogBO,User user)
	throws SevereException, AdapterException {
		Logger.logInfo("CatalogAdapterManager - Entering deleteCatalog(): Catalog Retrieve");
		try{
		AdapterUtil.performRemove(catalogBO,user.getUserId());
	} catch (Exception ex) {
		throw new AdapterException("Exception occured while adapter call",ex);
	}
		Logger.logInfo("CatalogAdapterManager - Returning deleteCatalog(): Catalog  Retrieve");
		return true;
		}
    
	/**
	 * Method to retrieve catalog
	 * @param catalogBO
	 * @return BO
	 * @throws SevereException
	 */
	public CatalogBO retrieveCatalog(CatalogBO catalogBO) throws SevereException, AdapterException {
		Logger.logInfo("CatalogAdapterManager - Entering retrieveCatalog(): Catalog Retrieve");
		try{
		AdapterUtil.performRetrieve(catalogBO);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
		Logger.logInfo("CatalogAdapterManager - Returning retrieveCatalog(): Catalog  Retrieve");
		return catalogBO;
	}
	
	/**
	 * Method to retrieve srdaCatalog
	 * @param srdaatalogBO
	 * @return BO
	 * @throws SevereException
	 */
	public SrdaCatalogBO retrieveSrdaCatalog(SrdaCatalogBO srdaatalogBO) throws SevereException, AdapterException {
		Logger.logInfo("CatalogAdapterManager - Entering retrieveCatalog(): SrdaCatalog Retrieve");
		try{
			
			AdapterUtil.performRetrieve(srdaatalogBO);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
		Logger.logInfo("CatalogAdapterManager - Returning retrieveSrdaCatalog(): SrdaCatalog  Retrieve");
		return srdaatalogBO;
	}
   
	/**
	 * method to search for duplicate name
	 * @param catalogBO
	 * @return List
	 * @throws SevereException
	 */
	public List searchForDuplicateName(CatalogBO catalogBO)throws SevereException,AdapterException{
		
			HashMap valueSet = new HashMap();
			valueSet.put("catalogName", catalogBO.getCatalogName());
			SearchResults searchResults = null;
			
			SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					catalogBO.getClass().getName(), "findDuplicate",valueSet,99999);
			try{
				searchResults = AdapterUtil.performSearch(searchCriteria);
			} catch (Exception ex) {
				throw new AdapterException("Exception occured while adapter call",ex);
			}
			return searchResults.getSearchResults();
			
			
	}
	
	
	/**
	 * Method to find if catalog is associated with an item
	 * @param catalogBO
	 * @return
	 * @throws SevereException
	 */
	public List searchForItem(CatalogBO catalogBO) throws SevereException,AdapterException{
        HashMap valueSet = new HashMap();
        valueSet.put("catalogId",new Integer(catalogBO.getCatalogId()));
        SearchResults searchResults = null;
        
	    SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(catalogBO.getClass().getName(),"findItem", valueSet, 99999);
	    try{
	    searchResults = AdapterUtil.performSearch(searchCriteria);
        } catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
	    return searchResults.getSearchResults();
	}

	/**
	 * Method to get all the subcatalogs associated with a catalog
	 * @param catalogBO
	 * @return
	 * @throws SevereException
	 */
	public List searchForSubCatalog(CatalogBO catalogBO)throws SevereException, AdapterException{
	    HashMap valueSet = new HashMap();
        valueSet.put("parentcatalogId",new Integer(catalogBO.getCatalogId()));
        SearchResults searchResults = null;
       
	    SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(catalogBO.getClass().getName(),"findAssociatedSubCatalog", valueSet, 99999);
	    try{
	    searchResults = AdapterUtil.performSearch(searchCriteria);
        } catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
	    return searchResults.getSearchResults();
	}
	
	/**
	 * Locate the list of Catalogs with the corresponding searchCriteria
	 * 
	 * @param locateCriteria
	 * @return list
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public List locate(LocateCriteria locateCriteria) throws SevereException, AdapterException {
		Logger.logInfo("Entering the method for locating catalog");
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
		HashMap criteriaValues = new HashMap();
		SearchResults searchResults = null;
		CatalogLocateCriteria catalogLocateCriteria = (CatalogLocateCriteria) locateCriteria;
		criteriaValues = getCriteriaForCatalogLocateCriteriaBO(catalogLocateCriteria);
		if(null!=catalogLocateCriteria.getSrdaFlag() && "SRDA".equalsIgnoreCase(catalogLocateCriteria.getSrdaFlag())){
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(SrdaCatalogBO.class
					.getName(), "locateCatalog", criteriaValues);
		
		}else{
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(CatalogBO.class
					.getName(), "locateCatalog", criteriaValues);
		}
		searchCriteria.setMaxSearchResultSize(51);
		try{
		searchResults = AdapterUtil.performSearch(searchCriteria);
		 } catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
		Logger.logInfo("Returning the method for locating catalog");
		return searchResults.getSearchResults();
	}

	/**
	 * Get the Criteria for search
	 * 
	 * @param catalogLocateCriteria
	 * @return HashMap
	 */
	private HashMap getCriteriaForCatalogLocateCriteriaBO(
			CatalogLocateCriteria catalogLocateCriteria) {
		Logger.logInfo("Entering the method for getting criteria for catalog locate criteria bo");
		HashMap criteriaValues = new HashMap();
		String name = catalogLocateCriteria.getCatalogName();
		if (name != null && name.trim().length() > 0) {
			name = "%" + name.toUpperCase() + "%";
		} else
			name = "%";
		criteriaValues.put("catalogName", name);
		String desc = catalogLocateCriteria.getCatalogDescription();
		if (desc != null && desc.trim().length() > 0) {
			desc = "%" + desc.toUpperCase() + "%";
		} else
			desc = "%";
		criteriaValues.put("description", desc);
		Logger.logInfo("Returning the method for getting criteria for catalog locate criteria bo");
		return criteriaValues;
	}

}

