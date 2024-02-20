/*
 * ItemAdapterManager.java 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.business.item.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchCriteriaImpl;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.item.locatecriteria.ItemLocateCriteria;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.LocateCriteria;
import com.wellpoint.wpd.common.catalog.bo.CatalogBO;

import com.wellpoint.wpd.common.catalog.bo.SrdaCatalogBO;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.item.bo.ItemBO;
import com.wellpoint.wpd.common.item.bo.ItemFrequencyBO;
import com.wellpoint.wpd.common.item.bo.ItemSoftDeleteBO;
import com.wellpoint.wpd.common.item.bo.ItemSrdaBO;
import com.wellpoint.wpd.common.item.bo.ItemSrdaHCSBO;

/**
 * Base class for item adapter manager
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ItemAdapterManager {
    /**
     * Inserts the item
     * @param itemBO
     * @param audit
     * @return boolean
     * @throws SevereException
     */
    public boolean createItem(ItemBO itemBO, Audit audit)
            throws SevereException, AdapterException {
        Logger
                .logInfo("ItemAdapterManager - Entering createItem(): Item Create");
        try {
			AdapterUtil.performInsert(itemBO, audit.getUser());
        } catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        Logger
                .logInfo("ItemAdapterManager - Returning createItem(): Item Create");
        return true;
    }
    /**
     * Inserts the item
     * @param itemBO
     * @param audit
     * @return boolean
     * @throws SevereException
     */
    public boolean createItem(ItemSrdaBO itemsrdaBO, Audit audit)
            throws SevereException, AdapterException {
        Logger
                .logInfo("ItemAdapterManager - Entering createItem(): Item Create");
        try {
			AdapterUtil.performInsert(itemsrdaBO, audit.getUser());
        } catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        Logger
                .logInfo("ItemAdapterManager - Returning createItem(): Item Create");
        return true;
    }
    
    /**
     * Inserts the item
     * @param itemBO
     * @param audit
     * @return boolean
     * @throws SevereException
     */
    public boolean createItem(ItemSrdaHCSBO itemSrdaHCSBO, Audit audit)
            throws SevereException, AdapterException {
        Logger
                .logInfo("ItemAdapterManager - Entering createItem(): Item Create");
        try {
			AdapterUtil.performInsert(itemSrdaHCSBO, audit.getUser());
        } catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        Logger
                .logInfo("ItemAdapterManager - Returning createItem(): Item Create");
        return true;
    }

    /**
     * Updates the item 
     * @param itemBO
     * @param audit
     * @return boolean
     * @throws SevereException
     */

    public boolean updateItem(ItemBO itemBO, Audit audit)
            throws SevereException, AdapterException {
        Logger
                .logInfo("ItemAdapterManager - Entering createItem(): Item Update");
        try {
			AdapterUtil.performUpdate(itemBO, audit.getUser());
        } catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        Logger
                .logInfo("ItemAdapterManager - Returning createItem(): Item Update");
        return true;
    }
    
    /**
     * Updates the item 
     * @param itemBO
     * @param audit
     * @return boolean
     * @throws SevereException
     */

    public boolean updateItem(ItemSrdaHCSBO itemSrdaHCSBO, Audit audit)
            throws SevereException, AdapterException {
        Logger
                .logInfo("ItemAdapterManager - Entering createItem(): Item Update");
        try {
			AdapterUtil.performUpdate(itemSrdaHCSBO, audit.getUser());
        } catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        Logger
                .logInfo("ItemAdapterManager - Returning createItem(): Item Update");
        return true;
    }
    /**
     * Updates the item 
     * @param itemBO
     * @param audit
     * @return boolean
     * @throws SevereException
     */

    public boolean updateItem(ItemSrdaBO itemBO, Audit audit)
            throws SevereException, AdapterException {
        Logger
                .logInfo("ItemAdapterManager - Entering createItem(): Item Update");
        try {
			AdapterUtil.performUpdate(itemBO, audit.getUser());
        } catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        Logger
                .logInfo("ItemAdapterManager - Returning createItem(): Item Update");
        return true;
    }
   /**
    * Insert and update in IT table for frequency entry
    * @param itemFrequencyBO
    * @param audit
    * @return boolean
    * @throws AdapterException
    */
    public boolean createITtableEntry(ItemFrequencyBO itemFrequencyBO,Audit audit,boolean flag) throws AdapterException{
    	Logger
        .logInfo("ItemAdapterManager - Entering createIT table entry(): Item Create");
		try {
			if(flag){
				AdapterUtil.performInsert(itemFrequencyBO, audit.getUser());
			}else{
				AdapterUtil.performUpdate(itemFrequencyBO, audit.getUser());
			}
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
		Logger
		        .logInfo("ItemAdapterManager - Returning createItem(): Item Create");
		return true;
    }
    /**
     * Retrieves the Frequency Information for edit
     * @param itemFrequencyBO
     * @return
     * @throws AdapterException
     */
    public ItemFrequencyBO retrieveFrequencyInfo(ItemFrequencyBO itemFrequencyBO) throws AdapterException{
        try {
			AdapterUtil.performRetrieve(itemFrequencyBO);
        } catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        return itemFrequencyBO;
    }
    /**
     * Retrieves the Catalog 
     * @return List of Catalog
     * @throws SevereException
     */
    public List retrieveCatalog(String srdaFlag) throws SevereException, AdapterException {
        HashMap map = new HashMap();
       
        try {
        	SearchResults searchResults = null;
        	SearchResults searchHCSDataResults = null;
        	if(null != srdaFlag && "SRDA".equalsIgnoreCase(srdaFlag)){ 
        		searchResults = AdapterUtil.performSearch(
			        AdapterUtil.getAdapterSearchCriteria(SrdaCatalogBO.class.getName(),
			                "findCatalog", map));
        		SearchResults duplicateSearchResults = AdapterUtil.performSearch(
    			        AdapterUtil.getAdapterSearchCriteria(SrdaCatalogBO.class.getName(),
    			                "findBusEntCatalog", map));
        		if(duplicateSearchResults != null){
        			searchResults.append(duplicateSearchResults);
        		}
        		searchHCSDataResults = AdapterUtil.performSearch(
                        AdapterUtil.getAdapterSearchCriteria(SrdaCatalogBO.class.getName(),
                                "findHCSDataCatalog", map));
                searchResults.append(searchHCSDataResults);

        	}
        	else{
        		searchResults =	AdapterUtil.performSearch(
			        AdapterUtil.getAdapterSearchCriteria(CatalogBO.class.getName(),
			                "findCatalog", map));
        	}	
					return searchResults.getSearchResults();
					
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
    }

    /**
     * Retrieves the item for edit
     * @param itemBO
     * @return ItemBO
     * @throws SevereException
     */
    public Object retrieve(Object object) throws SevereException, AdapterException {
        try {
			AdapterUtil.performRetrieve(object);
        } catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        return object;
    }

 
    /**
     * For checking whether there is any duplicate item for
     * @param itemBO
     * @return ItemBO
     * @throws SevereException
     */
    public List searchForDuplicateHCSItem(ItemSrdaHCSBO itemSrdaHCSBo) throws SevereException, AdapterException {
    	   HashMap valueSet = new HashMap();
    	   SearchResults searchResults;
    	   SearchCriteria searchCriteria;
    		   valueSet.put("catalogName", new Integer(itemSrdaHCSBo.getCatalogName()));
               valueSet.put("primaryCode", itemSrdaHCSBo.getPrimaryCode());
               searchCriteria = AdapterUtil.getAdapterSearchCriteria(
            		   itemSrdaHCSBo.getClass().getName(), "retriveHcsItem", valueSet, 99999);
 
   		try {
   			searchResults = AdapterUtil.performSearch(searchCriteria);
   		} catch (Exception ex) {
   			throw new AdapterException("Exception occured while adapter call",ex);
   		}
   		return searchResults.getSearchResults();
    }
    /**
     * For checking whether there is any duplicate item for
     * the corresponding entry
     * @param itemBO
     * @return List
     * @throws SevereException
     */
    public List searchForDuplicateItem(ItemBO itemBO) throws SevereException, AdapterException {
        HashMap valueSet = new HashMap();
        valueSet.put("catalogId", new Integer(itemBO.getCatalogId()));
        valueSet.put("primaryCode", itemBO.getPrimaryCode());
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                itemBO.getClass().getName(), "findItem", valueSet, 99999);
        SearchResults searchResults;
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
		return searchResults.getSearchResults();
    }

    
    public List searchForDuplicateItem(ItemSrdaBO itemSrdaBO) throws SevereException, AdapterException {
        HashMap valueSet = new HashMap();
        valueSet.put("catalogId", new Integer(itemSrdaBO.getCatalogId()));
        valueSet.put("primaryCode", itemSrdaBO.getPrimaryCode());
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
        		itemSrdaBO.getClass().getName(), "findItem", valueSet, 99999);
        SearchResults searchResults;
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
		return searchResults.getSearchResults();
    }
    
  
    /**
     * Locate the list of Item with the corresponding searchCriteria
     * 
     * @param locateCriteria
     *            LocateCriteria object.
     * @return LocateResults
     * @throws SevereException
     * @throws ServiceException
     */
    public List locate(LocateCriteria locateCriteria) throws SevereException, AdapterException {
        Logger.logInfo("Entering the method for locating item");
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        HashMap criteriaValues = new HashMap();
        SearchResults searchResults = null;

        ItemLocateCriteria itemLocateCriteria = (ItemLocateCriteria) locateCriteria;
        criteriaValues = getCriteriaForItemLocateCriteriaBO(itemLocateCriteria);
        
        if(null !=itemLocateCriteria.getSrdaFlag() && "SRDA".equalsIgnoreCase(itemLocateCriteria.getSrdaFlag())){
        	if(null != itemLocateCriteria.getDescription()){
        		searchCriteria = AdapterUtil.getAdapterSearchCriteria(ItemSrdaBO.class
                        .getName(), "locateHCSItem", criteriaValues);
        	}
        	else{
        	searchCriteria = AdapterUtil.getAdapterSearchCriteria(ItemSrdaBO.class
                    .getName(), "locateItem", criteriaValues);
        	}
        }else{
        	searchCriteria = AdapterUtil.getAdapterSearchCriteria(ItemBO.class
                    .getName(), "locateItem", criteriaValues);
        }
        try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
        } catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        Logger.logInfo("Returning the method for locating item");
        return searchResults.getSearchResults();
    }
    
  //sscr 17571
    public List locateReferenceItem(int CatalogId )throws SevereException, AdapterException{
    	Logger.logInfo("Entering locateReferenceItem method for locating item");
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
    	 HashMap criteriaRefValues = new HashMap();
    	 SearchResults searchRefResults = null;
    	 List arrCalog=new ArrayList();
    	 arrCalog.add(50210);
    	 
    	 criteriaRefValues.put("catalogIdList",arrCalog);
    	 	 
    	 searchCriteria=AdapterUtil.getAdapterSearchCriteria(ItemBO.class
                 .getName(), "locateItem",criteriaRefValues);
    	  try {
    		  searchRefResults = AdapterUtil.performSearch(searchCriteria);
          } catch (Exception ex) {
  			throw new AdapterException("Exception occured while adapter call",ex);
  		}
          Logger.logInfo("Returning the method for locating item");
          return searchRefResults.getSearchResults();

    }
    //sscr 17571-end
    /**
     * Method to update the item delete status of Item for Soft Delete
     * @param itemStatusBO
     * @param user
     * @return boolean
     * @throws SevereException
     */
    public boolean updateStatus(ItemSoftDeleteBO itemStatusBO,User user)throws SevereException, AdapterException{
        Logger.logInfo("ItemAdapterManager - Entering updateStatus(): Item Update");
        
        try {
			AdapterUtil.performUpdate(itemStatusBO,"user");
        } catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",ex);
		}
        
        Logger.logInfo("ItemAdapterManager - Returning updateStatus(): Item Update");
        return true;
    }
 
    /**
     * Get the Criteria for search
     * 
     * @param itemLocateCriteria
     * @return HashMap.
     */
    private HashMap getCriteriaForItemLocateCriteriaBO(
            ItemLocateCriteria itemLocateCriteria) {
        Logger
                .logInfo("Entering the method for getting criteria for item locate criteria bo");
        HashMap criteriaValues = new HashMap();
     
        List ctlgIdList = itemLocateCriteria.getCatalogIdList();      
        if(ctlgIdList!=null){
            criteriaValues.put("catalogIdList",ctlgIdList);
        } else {
            criteriaValues.put("catalogIdList",null);
        }
        if (null != itemLocateCriteria.getPrimaryCode()
                && !itemLocateCriteria.getPrimaryCode().equals("")) {
            criteriaValues.put("primaryCode", "%"
                    + itemLocateCriteria.getPrimaryCode() + "%");
        } else {
            criteriaValues.put("primaryCode", null);
        }
        if (null != itemLocateCriteria.getSecondaryCode()
                && !itemLocateCriteria.getSecondaryCode().equals("")) {
            criteriaValues.put("secondaryCode", "%"
                    + itemLocateCriteria.getSecondaryCode() + "%");
        } else {
            criteriaValues.put("secondaryCode", null);
        }
        
        if (null != itemLocateCriteria.getDescription()
                && !itemLocateCriteria.getDescription().equals("")) {
            criteriaValues.put("description", "%"
                    + itemLocateCriteria.getDescription() + "%");
        } else {
            criteriaValues.put("description", null);
        }
        Logger
                .logInfo("Returning the method for getting criteria for item locate criteria bo");
        return criteriaValues;
    }

    /**
     * This method checks whether catalog have a duplicate description for an item
     * @param itemBO
     * @return List searchResults
     * @throws SevereException
     */
    public List duplicateDescriptionItem(ItemBO itemBO) throws SevereException{
    	HashMap valueSet = new HashMap();
        valueSet.put("catalogId", new Integer(itemBO.getCatalogId()));
        valueSet.put("description", itemBO.getDescription());
        valueSet.put("primaryCode", itemBO.getPrimaryCode());
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                itemBO.getClass().getName(), "duplicateDescriptionItem", valueSet, 99999);
        SearchResults searchResults;
 		searchResults = AdapterUtil.performSearch(searchCriteria);
 		return searchResults.getSearchResults();
    }
    
    
    public List duplicateDescriptionItem(ItemSrdaBO itemSrdaBO) throws SevereException{
    	HashMap valueSet = new HashMap();
        valueSet.put("catalogId", new Integer(itemSrdaBO.getCatalogId()));
        valueSet.put("description", itemSrdaBO.getDescription());
        valueSet.put("primaryCode", itemSrdaBO.getPrimaryCode());
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
        		itemSrdaBO.getClass().getName(), "duplicateDescriptionItem", valueSet, 99999);
        SearchResults searchResults;
 		searchResults = AdapterUtil.performSearch(searchCriteria);
 		return searchResults.getSearchResults();
    }

}