/*
 * SubCatalogAdapterManager.java
 *  © 2006 WellPoint, Inc. All Rights
 * Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.business.subcatalog.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchCriteriaImpl;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.subcatalog.locatecriteria.ItemLocateCriteria;
import com.wellpoint.wpd.business.subcatalog.locatecriteria.ReferenceDataLocateCriteria;
import com.wellpoint.wpd.business.subcatalog.locatecriteria.SubCatalogLocateCriteria;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.LocateCriteria;
import com.wellpoint.wpd.common.catalog.bo.CatalogBO;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.indicativemapping.bo.IndicativeMappingBO;
import com.wellpoint.wpd.common.item.bo.ItemBO;

import com.wellpoint.wpd.common.subcatalog.bo.SubCatalogBO;
import com.wellpoint.wpd.web.search.util.SearchUtil;

/**
 * Base class for subcatalog adapter
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SubCatalogAdapterManager {
	/**
	 * Method to create subcatalog
	 * @param catalogBO
	 * @param audit
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean createSubCatalog(CatalogBO catalogBO, Audit audit)
			throws SevereException, AdapterException {
		Logger
				.logInfo("SubCalogAdapterManager - Entering createSubCatalog(): Sub-Catalog Create");
		try {
			AdapterUtil.performInsert(catalogBO, audit.getUser());
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		Logger
				.logInfo("SubCatalogAdapterManager - Returning createSubCatalog(): Sub-Catalog Create");
		return true;
	}

	/**
	 * Method to create subcatalog
	 * @param catalogBO
	 * @param audit
	 * @param subcatalogAdapterServiceAccess
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean updateSubCatalog(CatalogBO catalogBO, Audit audit)
			throws SevereException, AdapterException {
		Logger
				.logInfo("SubCatalogAdapterManager - Entering createCatalog(): SubCatalog Create");
		try {
			AdapterUtil.performUpdate(catalogBO, audit.getUser());
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		Logger
				.logInfo("SubCatalogAdapterManager - Returning createCatalog(): SubCatalog Create");
		return true;
	}

	public boolean updateSubCatalogForMultipleTxns(CatalogBO catalogBO,
			Audit audit, AdapterServicesAccess adapterServicesAccess)
			throws AdapterException {

		Logger
				.logInfo("SubCatalogAdapterManager - Entering updateSubCatalogForMultipleTxns(): SubCatalog updateSubCatalogForMultipleTxns");
		try {
			AdapterUtil.performUpdate(catalogBO, audit.getUser(),
					adapterServicesAccess);
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		Logger
				.logInfo("SubCatalogAdapterManager - Returning updateSubCatalogForMultipleTxns(): SubCatalog updateSubCatalogForMultipleTxns");
		return true;
	}
	
	
	
	
	 /**
     * Method to search for duplicate subcatalog
     * @param catalogBO
     * @return List
     * @throws SevereException
     */
	public List searchForDuplicateSubCatalog(CatalogBO catalogBO, List lob, List be, List bg, List mbu)
			throws SevereException, AdapterException {

		HashMap valueSet = new HashMap();
		valueSet.put("catalogName", catalogBO.getCatalogName());
		valueSet.put("parentcatalogId", new Integer(catalogBO
				.getCatalogParentid()));
		valueSet.put("lob", lob);
		valueSet.put("be", be);
		valueSet.put("bg", bg);
		valueSet.put("mbu", mbu);
		SearchResults searchResults;

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				catalogBO.getClass().getName(), "findSubCatalogDuplicate",
				valueSet, 99999);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		return searchResults.getSearchResults();
	}

	/**
	 * Method to search for duplicate subcatalog and parentCatalog
	 * @param catalogBO
	 * @return List
	 * @throws SevereException
	 */
	public List searchForDuplicateSubCatalogAndParentCatalog(CatalogBO catalogBO)
			throws SevereException, AdapterException {
		HashMap valueSet = new HashMap();
		valueSet.put("catalogName", catalogBO.getCatalogName());
		valueSet.put("parentcatalogId", new Integer(catalogBO
				.getCatalogParentid()));
		SearchResults searchResults;

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				catalogBO.getClass().getName(),
				"findSubCatalogAndCatalogDuplicate", valueSet, 99999);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		return searchResults.getSearchResults();
	}

	/**
	 * Method to delete sub-catalog
	 * @param catalogBO
	 * @param user
	 * @param subCatalogAdapterServiceAccess
	 * @return boolean
	 * @throws SevereException
	 */

	public boolean deleteSubCatalog(CatalogBO catalogBO, User user)
			throws SevereException, AdapterException {
		Logger
				.logInfo("Sub-CatalogAdapterManager - Entering deleteSub-Catalog(): Sub-Catalog Retrieve");
		try {
			AdapterUtil.performRemove(catalogBO, user.getUserId());
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		Logger
				.logInfo("Sub-CatalogAdapterManager - Returning deleteSub-Catalog(): Sub-Catalog  Retrieve");
		return true;
	}

	public boolean deleteItemAssociation(SubCatalogBO subCatalogBO, User user)
			throws SevereException, AdapterException {
		Logger
				.logInfo("Sub-CatalogAdapterManager - Entering deleteSub-Catalog(): Sub-Catalog Item association Delete");
		try {
			AdapterUtil.performRemove(subCatalogBO, user.getUserId());
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		Logger
				.logInfo("Sub-CatalogAdapterManager - Returning deleteSub-Catalog(): Sub-Catalog  Item association Delete");
		return true;
	}

	public boolean deleteItemAssociationForMultipleTnxs(
			SubCatalogBO subCatalogBO, User user,
			AdapterServicesAccess adapterServicesAccess)
			throws AdapterException {
		Logger
				.logInfo("Sub-CatalogAdapterManager - Entering deleteItemAssociationForMultipleTnxs(): Sub-Catalog Item association Delete For Multiple Transactions");
		try {
			AdapterUtil.performRemove(subCatalogBO, user.getUserId(),
					adapterServicesAccess);
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		Logger
				.logInfo("Sub-CatalogAdapterManager - Returning deleteItemAssociationForMultipleTnxs(): Sub-Catalog  Item association Delete For Multiple Transactions");

		return true;
	}

	/**
	 * Method to find if sub-catalog is associated with an item
	 * @param catalogBO
	 * @return
	 * @throws SevereException
	 */
	public List searchForItem(CatalogBO catalogBO) throws SevereException,
			AdapterException {
		HashMap valueSet = new HashMap();
		valueSet.put("catalogId", new Integer(catalogBO.getCatalogId()));
		SearchResults searchResults;

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				catalogBO.getClass().getName(), "findItem", valueSet, 99999);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		return searchResults.getSearchResults();
	}

	/**
	 * Method to locate sub catalogs based on the search criteria
	 * @return List
	 * @throws SevereException
	 */
	public List locate(SubCatalogLocateCriteria subCatalogLocateCriteria)
			throws SevereException, AdapterException {

		// Create an instance of the search criteria
		SearchCriteria searchCriteria = new SearchCriteriaImpl();

		// Create a reference of searchResults
		SearchResults searchResults = null;

		HashMap criteriaValues = new HashMap();

		// Set the search criteria values in the hash map
		criteriaValues = getSearchCriteriaMap(criteriaValues,
				subCatalogLocateCriteria);

		// Set the criteria to the searchCriteria instance

		searchCriteria = AdapterUtil.getAdapterSearchCriteria(CatalogBO.class
				.getName(), "locateSubCatalog", criteriaValues);
		try {
			// Call the performSearch() of the adapterutil
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}

		List searchResultList = searchResults.getSearchResults();

		// Return the list
		return searchResultList;
	}

	/**
	 * Method to locate reference data based on the parent catalog name and 
	 * business domains
	 * action 1:to retrieve based upon the parent catalog name
	 * action 2:to retrieve based upon the business domains
	 * action 3:retrieve based upon the entityId and entityType
	 * action 4:for reference popup - retrieve based upon entered criteria
	 * action 5:for reference popup - retrieve based upon the combo of term and pva
	 * @return List
	 * @throws SevereException
	 */
	public List locate(ReferenceDataLocateCriteria referenceDataLocateCriteria)
			throws SevereException, AdapterException {

		// Create an instance of the search criteria
		SearchCriteria searchCriteria = new SearchCriteriaImpl();

		// Create a reference of searchResults
		SearchResults searchResults = null;

		HashMap criteriaValues = new HashMap();

		// Set the search criteria values in the hash map
		criteriaValues = getSearchCriteriaMap(criteriaValues,
				referenceDataLocateCriteria);

		try {
			if (referenceDataLocateCriteria.getAction() == 1
					|| referenceDataLocateCriteria.getAction() == 99
					|| referenceDataLocateCriteria.getAction() == 31) {
				searchCriteria = AdapterUtil.getAdapterSearchCriteria(
						ItemBO.class.getName(),
						"getAssociatedItemByCatalogNameForRefData",
						criteriaValues);

			} else if (referenceDataLocateCriteria.getAction() == 10) {
				searchCriteria = AdapterUtil.getAdapterSearchCriteria(
						ItemBO.class.getName(),
						"getNonAssociatedItemByCatalogNameForRefData",
						criteriaValues);

			} else if (referenceDataLocateCriteria.getAction() == 5) {
				searchCriteria = AdapterUtil.getAdapterSearchCriteria(
						SubCatalogBO.class.getName(),
						"getAssociatedItemByTermPvaForReference",
						criteriaValues);

			} else if (referenceDataLocateCriteria.getAction() == 7
					|| referenceDataLocateCriteria.getAction() == 27) {
				searchCriteria = AdapterUtil.getAdapterSearchCriteria(
						ItemBO.class.getName(), "getBlueExchangeData",
						criteriaValues);

			} else if (referenceDataLocateCriteria.getAction() == 8) {
				searchCriteria = AdapterUtil.getAdapterSearchCriteria(
						ItemBO.class.getName(), "getBlueExchangeLocateData",
						criteriaValues);

			} else if (referenceDataLocateCriteria.getAction() == 9) {
				searchCriteria = AdapterUtil.getAdapterSearchCriteria(
						ItemBO.class.getName(), "getSPSParameter",
						criteriaValues);

			} else if (referenceDataLocateCriteria.getAction() == 17) {
				searchCriteria = AdapterUtil.getAdapterSearchCriteria(
						IndicativeMappingBO.class.getName(), "getBenefits",
						criteriaValues);
			} else {
				// Set the criteria to the searchCriteria instance
				searchCriteria = AdapterUtil.getAdapterSearchCriteria(
						SubCatalogBO.class.getName(), "locateReferenceData",
						criteriaValues);
			}
			if (referenceDataLocateCriteria.getAction() == 4
					&& null != referenceDataLocateCriteria.getLobList()
					&& !referenceDataLocateCriteria.getLobList().isEmpty()) {
				criteriaValues.put("searchValue", "%" + "%" + "%");
				searchCriteria = AdapterUtil.getAdapterSearchCriteria(
						SubCatalogBO.class.getName(),
						"getAssociatedItemByCatalogNameForReference",
						criteriaValues);
			}
			if (referenceDataLocateCriteria.getAction() == 11) {
				criteriaValues
						.put("parentCatalogName", "ACCUMULATOR REFERENCE");
				criteriaValues.put("ctlgNameForSortByCD",
						"ACCUMULATOR REFERENCE");
				criteriaValues.put("searchValue", "%"
						+ referenceDataLocateCriteria.getSearchValueForPopUp()
						+ "%");
				searchCriteria = AdapterUtil.getAdapterSearchCriteria(
						ItemBO.class.getName(), "getAccumulatorSPSIdData",
						criteriaValues);
			}

			// Call the performSearch() of the adapterutil
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}

		List searchResultList = searchResults.getSearchResults();

		if (referenceDataLocateCriteria.getAction() == 4) {
			if (null != searchResultList && !searchResultList.isEmpty()
					&& null != referenceDataLocateCriteria.getLobList()
					&& !referenceDataLocateCriteria.getLobList().isEmpty()) {
				criteriaValues.put("searchValue", "%"
						+ referenceDataLocateCriteria.getSearchValueForPopUp()
						+ "%");

				searchCriteria = AdapterUtil.getAdapterSearchCriteria(
						SubCatalogBO.class.getName(),
						"getAssociatedItemByCatalogNameForReference",
						criteriaValues);
			} else {
				criteriaValues.put("searchValue", "%"
						+ referenceDataLocateCriteria.getSearchValueForPopUp()
						+ "%");
				searchCriteria = AdapterUtil.getAdapterSearchCriteria(
						ItemBO.class.getName(),
						"getAssociatedItemByCatalogNameForReference",
						criteriaValues);
			}
			try {
				// Call the performSearch() of the adapterutil
				searchResults = AdapterUtil.performSearch(searchCriteria);
			} catch (SevereException ex) {
				throw new AdapterException(
						"Exception occured while adapter call", ex);
			}
			searchResultList = searchResults.getSearchResults();
		}
		if (referenceDataLocateCriteria.getAction() != 1
				&& referenceDataLocateCriteria.getAction() != 10
				&& referenceDataLocateCriteria.getAction() != 4
				&& referenceDataLocateCriteria.getAction() != 7
				&& referenceDataLocateCriteria.getAction() != 11
				&& referenceDataLocateCriteria.getAction() != 8
				&& referenceDataLocateCriteria.getAction() != 12
				&& (null == searchResultList || searchResultList.isEmpty())
				&& referenceDataLocateCriteria.getAction() != 5
				&& (null == searchResultList || searchResultList.isEmpty())) {

			searchCriteria = AdapterUtil.getAdapterSearchCriteria(ItemBO.class
					.getName(), "getAssociatedItemByCatalogNameForRefData",
					criteriaValues);
			try {
				// Call the performSearch() of the adapterutil
				searchResults = AdapterUtil.performSearch(searchCriteria);
			} catch (SevereException ex) {
				throw new AdapterException(
						"Exception occured while adapter call", ex);
			}
			searchResultList = searchResults.getSearchResults();
		}
		if (referenceDataLocateCriteria.getAction() == 12
				|| referenceDataLocateCriteria.getAction() == 13) {
			if (null != searchResultList && !searchResultList.isEmpty()
					&& null != referenceDataLocateCriteria.getLobList()
					&& !referenceDataLocateCriteria.getLobList().isEmpty()) {
				criteriaValues.put("parentCatalogName",
						referenceDataLocateCriteria.getParentCatalog());
				criteriaValues.put("ctlgNameForSortByCD",
						referenceDataLocateCriteria.getParentCatalog());
				criteriaValues.put("searchValue", "%"
						+ referenceDataLocateCriteria.getSearchValueForPopUp()
						+ "%");
				if (referenceDataLocateCriteria.getParentCatalog().equals(
						BusinessConstants.COVERAGE)
						|| referenceDataLocateCriteria.getParentCatalog()
								.equals(BusinessConstants.NETWORK)
						|| referenceDataLocateCriteria.getParentCatalog()
								.equals(BusinessConstants.PRODUCT_CODES)
						|| referenceDataLocateCriteria
								.getParentCatalog()
								.equals(
										BusinessConstants.PROVIDER_SPECIALITY_CODE)
						|| referenceDataLocateCriteria.getParentCatalog()
								.equals(BusinessConstants.STANDARD_PLAN_CODES)
						|| referenceDataLocateCriteria.getParentCatalog()
								.equals(BusinessConstants.CORPORATE_PLAN_CODES)) {
					searchCriteria = AdapterUtil.getAdapterSearchCriteria(
							ItemBO.class.getName(),
							"getTermQualifierSearchFilterByCode",
							criteriaValues);
				} else {
					searchCriteria = AdapterUtil.getAdapterSearchCriteria(
							ItemBO.class.getName(),
							"getTermQualifierSearchFilter", criteriaValues);
				}
			} else {

				if (referenceDataLocateCriteria.getParentCatalog().equals(
						BusinessConstants.COVERAGE)
						|| referenceDataLocateCriteria
								.getParentCatalog()
								.equals(
										BusinessConstants.PROVIDER_SPECIALITY_CODE)
						|| referenceDataLocateCriteria.getParentCatalog()
								.equals(BusinessConstants.NETWORK)
						|| referenceDataLocateCriteria.getParentCatalog()
								.equals(BusinessConstants.PRODUCT_CODES)
						|| referenceDataLocateCriteria.getParentCatalog()
								.equals(BusinessConstants.STANDARD_PLAN_CODES)
						|| referenceDataLocateCriteria.getParentCatalog()
								.equals(BusinessConstants.CORPORATE_PLAN_CODES)) {
					criteriaValues.put("searchValue", "%"
							+ referenceDataLocateCriteria
									.getSearchValueForPopUp() + "%");
					searchCriteria = AdapterUtil.getAdapterSearchCriteria(
							ItemBO.class.getName(),
							"getTermQualifierSearchFilterByCode",
							criteriaValues);
				} else {
					criteriaValues.put("searchValue", "%"
							+ referenceDataLocateCriteria
									.getSearchValueForPopUp() + "%");
					searchCriteria = AdapterUtil.getAdapterSearchCriteria(
							ItemBO.class.getName(),
							"getTermQualifierSearchFilter", criteriaValues);
				}
			}
			try {
				// Call the performSearch() of the adapterutil
				searchResults = AdapterUtil.performSearch(searchCriteria);
			} catch (SevereException ex) {
				throw new AdapterException(
						"Exception occured while adapter call", ex);
			}
			searchResultList = searchResults.getSearchResults();
		}

		if (referenceDataLocateCriteria.getAction() == 99) {
			Iterator iter = searchResultList.iterator();
			while (iter.hasNext()) {
				Object obj = iter.next();
				if (obj instanceof SubCatalogBO) {
					SubCatalogBO subCatalogBO = (SubCatalogBO) obj;
					if (subCatalogBO.getPrimaryCode().equals("ALL")) {
						searchResultList.remove(obj);
						break;
					}

				} else if (obj instanceof ItemBO) {
					ItemBO itemBO = (ItemBO) obj;
					if (itemBO.getPrimaryCode().equals("ALL")) {
						searchResultList.remove(obj);
						break;
					}
				}

			}
		}

		// Return the list
		return searchResultList;
	}

	/**
	 * 
	 * @param subCatalogLocateCriteria
	 * @param user
	 * @return
	 * @throws SevereException
	 */
	public List locateItemAssociation(
			SubCatalogLocateCriteria subCatalogLocateCriteria)
			throws SevereException, AdapterException {
		SearchResults searchResults = null;

		// Create an instance of the search criteria
		SearchCriteria searchCriteria = new SearchCriteriaImpl();

		HashMap criteriValues = new HashMap();

		Integer id = new Integer(subCatalogLocateCriteria.getSubCatalogId());

		criteriValues.put("catalogId", id);

		searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				SubCatalogBO.class.getName(), "locateAssociation",
				criteriValues);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}

		return searchResults.getSearchResults();
	}

	/**
	 * @param criteriaValues
	 * @param subCatalogLocateCriteria
	 * @return
	 */
	private HashMap getSearchCriteriaMap(HashMap criteriaValues,
			SubCatalogLocateCriteria subCatalogLocateCriteria) {
		// Set the values to the hashmap from the locatCriteria
		if (null != subCatalogLocateCriteria.getSubCatalogName()
				&& !subCatalogLocateCriteria.getSubCatalogName().equals("")) {
			criteriaValues.put("catalogName", "%"
					+ subCatalogLocateCriteria.getSubCatalogName() + "%");
		} else {
			criteriaValues.put("catalogName", null);
		}

		if (null != subCatalogLocateCriteria.getCatalogList()
				&& !subCatalogLocateCriteria.getCatalogList().isEmpty()) {
			criteriaValues.put("parentCatalogList", subCatalogLocateCriteria
					.getCatalogList());
		} else {
			criteriaValues.put("parentCatalogList", null);
		}

		return criteriaValues;
	}

	/**
	 * @param criteriaValues
	 * @param referenceDataLocateCriteria
	 * @return HashMap
	 */
	private HashMap getSearchCriteriaMap(HashMap criteriaValues,
			ReferenceDataLocateCriteria referenceDataLocateCriteria) {

		// Set the values to the hashmap from the locatCriteria
		if (null != referenceDataLocateCriteria.getParentCatalog()
				&& !referenceDataLocateCriteria.getParentCatalog().equals("")) {
			//Sets the search criteria value based upon required sorting order
			if (referenceDataLocateCriteria.getSortOrder().equals(
					BusinessConstants.PRIMARY_CODE))
				criteriaValues.put("ctlgNameForSortByPC",
						referenceDataLocateCriteria.getParentCatalog());//for order by primary code
			else
				criteriaValues.put("ctlgNameForSortByCD",
						referenceDataLocateCriteria.getParentCatalog());//for order by code description

			criteriaValues.put("parentCatalogName", referenceDataLocateCriteria
					.getParentCatalog());
		} else {
			criteriaValues.put("parentCatalogName", null);
		}

		if (null != referenceDataLocateCriteria.getLobList()
				&& !referenceDataLocateCriteria.getLobList().isEmpty()) {
			criteriaValues.put("lob", referenceDataLocateCriteria.getLobList());
		} else {
			criteriaValues.put("lob", null);
		}
		if (null != referenceDataLocateCriteria.getBeList()
				&& !referenceDataLocateCriteria.getBeList().isEmpty()) {
			criteriaValues.put("be", referenceDataLocateCriteria.getBeList());
		} else {
			criteriaValues.put("be", null);
		}
		if (null != referenceDataLocateCriteria.getBgList()
				&& !referenceDataLocateCriteria.getBgList().isEmpty()) {
			criteriaValues.put("bg", referenceDataLocateCriteria.getBgList());
		} else {
			criteriaValues.put("bg", null);
		}
		if (referenceDataLocateCriteria.getAction() != 12
				&& referenceDataLocateCriteria.getAction() != 13) {
			if (null != referenceDataLocateCriteria.getSearchValueForPopUp()
					&& !referenceDataLocateCriteria.getSearchValueForPopUp()
							.equals("")) {
				criteriaValues.put("searchValue", "%"
						+ referenceDataLocateCriteria.getSearchValueForPopUp()
						+ "%");
				criteriaValues.put("parentCatalogName", "reference");
				criteriaValues.put("ctlgNameForSortByCD", "reference");
			} else {
				criteriaValues.put("searchValue", null);
			}
		} else {
			if (null != referenceDataLocateCriteria.getSearchValueForPopUp()
					&& !referenceDataLocateCriteria.getSearchValueForPopUp()
							.equals("")) {
				criteriaValues.put("searchValue", "%"
						+ referenceDataLocateCriteria.getSearchValueForPopUp()
						+ "%");
				criteriaValues.put("parentCatalogName",
						referenceDataLocateCriteria.getParentCatalog());
			} else {
				criteriaValues.put("searchValue", null);
			}
		}

		if (null != referenceDataLocateCriteria.getTermValue()
				&& !referenceDataLocateCriteria.getTermValue().equals("")
				&& (null != referenceDataLocateCriteria.getPvaValue() && !referenceDataLocateCriteria
						.getPvaValue().equals(""))) {

			List benefitTermList = new ArrayList();

			StringTokenizer tokenizer = new StringTokenizer(
					referenceDataLocateCriteria.getTermValue().toString()
							.trim(), ",");

			while (tokenizer.hasMoreTokens()) {
				benefitTermList.add(tokenizer.nextToken().toUpperCase());

			}
			criteriaValues.put("termValue", SearchUtil
					.createQueryStringForTerm(benefitTermList, false));
			criteriaValues.put("pvaValue", "%"
					+ referenceDataLocateCriteria.getPvaValue().toUpperCase()
					+ "%");
			criteriaValues.put("parentCatalogName", "reference");
			criteriaValues.put("ctlgNameForSortByCD", "reference");
		} else {
			criteriaValues.put("termValue", null);
			criteriaValues.put("pvaValue", null);
		}

		if (null != referenceDataLocateCriteria.getHeaderRuleId()
				&& !referenceDataLocateCriteria.getHeaderRuleId().equals("")) {
			criteriaValues.put("headerRuleId", referenceDataLocateCriteria
					.getHeaderRuleId());
		} else {
			criteriaValues.put("headerRuleId", null);
		}
		if (null != referenceDataLocateCriteria.getSearchString()) {
			criteriaValues.put("searchString", "%"
					+ referenceDataLocateCriteria.getSearchString() + "%");
		}
		return criteriaValues;
	}

	/**
	 * Method to retireve the Subcatalog BO from the db
	 * @param catalogBO
	 * @return CatalogBO
	 * @throws SevereException
	 */
	public CatalogBO retrieve(CatalogBO catalogBO) throws SevereException,
			AdapterException {

		// Create the SearchCriteria 
		SearchCriteria searchCriteria = new SearchCriteriaImpl();

		// Create a hashmap
		HashMap criteria = new HashMap();

		// Set the searchCriteria value in the hashmap
		criteria.put("catalogId", new Integer(catalogBO.getCatalogId()));

		// Call the performRetrieve() of the AdapterUtil
		SearchResults searchResults;

		searchCriteria = AdapterUtil.getAdapterSearchCriteria(CatalogBO.class
				.getName(), "retrieveSubCatalog", criteria);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}

		if (searchResults.getSearchResultCount() > 0) {
			catalogBO = (CatalogBO) searchResults.getSearchResults().get(0);
		}

		// Return the retrieved BO
		return catalogBO;
	}

	/**
	 * Method to locate reference data for validation 
	 * @return List
	 * @throws SevereException
	 */
	public List locateRefData(
			ReferenceDataLocateCriteria referenceDataLocateCriteria)
			throws SevereException, AdapterException {

		// Create an instance of the search criteria
		SearchCriteria searchCriteria = new SearchCriteriaImpl();

		// Create a reference of searchResults
		SearchResults searchResults = null;

		HashMap criteriaValues = new HashMap();

		// Set the search criteria values in the hash map
		criteriaValues = getSearchCriteriaMapForRefDataValidation(
				criteriaValues, referenceDataLocateCriteria);

		searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				SubCatalogBO.class.getName(), "locateReferenceData",
				criteriaValues);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}

		return searchResults.getSearchResults();
	}

	/**
	 * Method for selecting the item non-associated with subCatalog
	 * @param LocateCriteria
	 * @return List of items
	 * @throws SevereException
	 */
	public List locate(LocateCriteria LocateCriteria) throws SevereException,
			AdapterException {
		Logger.logInfo("Entering the method for locating item");
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
		HashMap criteriaValues = new HashMap();
		SearchResults searchResults = null;

		ItemLocateCriteria itemLocateCriteria = (ItemLocateCriteria) LocateCriteria;
		criteriaValues = getCriteriaForItemLocateCriteria(itemLocateCriteria);

		searchCriteria = AdapterUtil.getAdapterSearchCriteria(ItemBO.class
				.getName(), "getItemsForSubCatalog", criteriaValues);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}

		Logger.logInfo("Returning the method for locating item");
		return searchResults.getSearchResults();
	}

	private HashMap getCriteriaForItemLocateCriteria(
			ItemLocateCriteria itemLocateCriteria) {
		HashMap criteriaValues = new HashMap();

		criteriaValues
				.put("catalogId", itemLocateCriteria.getParentCatalogId());
		criteriaValues.put("subCatalogId", new Integer(itemLocateCriteria
				.getSubCatalogId()));

		return criteriaValues;
	}

	public boolean createSubCatalogAssociation(SubCatalogBO subCatalogBO,
			Audit audit, AdapterServicesAccess adapterServicesAccess)
			throws SevereException, AdapterException {
		Logger
				.logInfo("SubCatalogAdapterManager - Entering createSubCatalogAssociation(): SubCatalogAssociation Create");
		try {
			AdapterUtil.performInsert(subCatalogBO, audit.getUser(),
					adapterServicesAccess);
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		Logger
				.logInfo("SubCatalogAdapterManager - Returning createSubCatalogAssociation(): SubCatalogAssociation Create");
		return true;
	}

	/**
	 * Method for retrieving subcatalog based upon the domain values
	 * and parent catalog
	 * @param criteriaValues
	 * @param referenceDataLocateCriteria
	 * @return HashMap
	 */
	private HashMap getSearchCriteriaMapForRefDataValidation(
			HashMap criteriaValues,
			ReferenceDataLocateCriteria referenceDataLocateCriteria) {
		// Set the values to the hashmap from the locatCriteria
		if (null != referenceDataLocateCriteria.getParentCatalog()
				&& !referenceDataLocateCriteria.getParentCatalog().equals("")) {

			// criteriaValues.put("parentCatalogName", referenceDataLocateCriteria.getParentCatalog());
			//Sets the search criteria value based upon required sorting order
			criteriaValues.put("ctlgNameForSortByCD",
					referenceDataLocateCriteria.getParentCatalog());//for order by code description

		} else {
			criteriaValues.put("parentCatalogName", null);
		}

		if (null != referenceDataLocateCriteria.getLobList()
				&& !referenceDataLocateCriteria.getLobList().isEmpty()) {
			criteriaValues.put("lob", referenceDataLocateCriteria.getLobList());
		} else {
			criteriaValues.put("lob", null);
		}
		if (null != referenceDataLocateCriteria.getBeList()
				&& !referenceDataLocateCriteria.getBeList().isEmpty()) {
			criteriaValues.put("be", referenceDataLocateCriteria.getBeList());
		} else {
			criteriaValues.put("be", null);
		}
		if (null != referenceDataLocateCriteria.getBgList()
				&& !referenceDataLocateCriteria.getBgList().isEmpty()) {
			criteriaValues.put("bg", referenceDataLocateCriteria.getBgList());
		} else {
			criteriaValues.put("bg", null);
		}

		return criteriaValues;
	}

}