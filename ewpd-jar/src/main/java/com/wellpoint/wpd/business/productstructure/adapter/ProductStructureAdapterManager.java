/*
 * ProductStructureAdapterManager.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.productstructure.adapter;

import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchCriteriaImpl;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.productstructure.locatecriteria.ProductStructureLocateCriteria;
import com.wellpoint.wpd.business.productstructure.locatecriteria.ProductStructureMandatesLocateCriteria;
import com.wellpoint.wpd.business.productstructure.locatecriteria.ProductStructureShowHiddenLocateCriteria;
import com.wellpoint.wpd.business.productstructure.locatecriteria.ViewAllVersionsLocateCriteria;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.LocateCriteria;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.domain.bo.Domain;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.productstructure.bo.HideAdminOption;
import com.wellpoint.wpd.common.productstructure.bo.MandatesBO;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureAssociatedBenefit;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureAssociatedBenefitComponent;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureAssociatedComponent;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureBO;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureBenefitAdministration;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureBenefitAdministrationBO;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureBenefitDefinition;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureComponent;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureQuestionnaireBO;
import com.wellpoint.wpd.common.productstructure.tree.bo.ProdStructureTreeAdminOptions;
import com.wellpoint.wpd.common.productstructure.tree.bo.ProdStructureTreeBenefitCmpnts;
import com.wellpoint.wpd.common.productstructure.tree.bo.ProdStructureTreeBenefitDate;
import com.wellpoint.wpd.common.productstructure.tree.bo.ProdStructureTreeStandardBenefits;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductStructureAdapterManager {

	/**
	 * Create ProductStructure
	 * 
	 * @param productStructureBO
	 *            ProductStructureBO object.
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean createProductStructure(
			ProductStructureBO productStructureBO,
			AdapterServicesAccess adapterServicesAccess)
			throws AdapterException {
		Logger.logInfo("Entering the method for creating a product structure");
		try {
			AdapterUtil.performInsert(productStructureBO, productStructureBO
					.getCreatedUser(), adapterServicesAccess);
		} catch (Exception e) {
			throw new AdapterException("Exception occured while adapter call",
					e);
		}
		Logger.logInfo("Returning the method for creating a product structure");
		return true;
	}

	/**
	 * Add Benefit component to a product structure
	 * 
	 * @param ProductStructureAssociatedBenefitComponent
	 *            productStructureAssociatedBenefitComponent.
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean addBenefitComponent(
			ProductStructureAssociatedBenefitComponent productStructureAssociatedBenefitComponent,
			AdapterServicesAccess adapterServicesAccess) throws SevereException {

		Logger.logInfo("Entering the method for adding benefit component");

		AdapterUtil.performInsert(productStructureAssociatedBenefitComponent,
				productStructureAssociatedBenefitComponent.getCreatedUser(),
				adapterServicesAccess);

		Logger.logInfo("Returning the method for adding benefit component");
		return true;
	}

	public boolean addBenefitComponentProc(
			ProductStructureAssociatedBenefitComponent productStructureAssociatedBenefitComponent,
			int proStrId, int benComid, Audit audit,
			AdapterServicesAccess adapterServicesAccess)
			throws AdapterException {

		Logger
				.logInfo("Entering the procedure for attaching Benefit Component to the Product Structure");
		SearchResults searchResults = null;
		LocateResults locateResults = new LocateResults();
		HashMap refValSet = new HashMap();
		refValSet.put(BusinessConstants.PRODUCT_STRUCTURE_ID, new Integer(
				proStrId));
		refValSet
				.put(BusinessConstants.BEN_COMPONENT_ID, new Integer(benComid));
		refValSet.put(WebConstants.USER, audit.getUser());

		SearchCriteria searchCriteria = getAdapterSearchCriteria(
				BusinessConstants.PRODUCT_STRUCTURE_BEN_COMP_ASSC_BO,
				BusinessConstants.ATTACH_BC_TO_PS, refValSet);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria,
					adapterServicesAccess);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}

		Logger
				.logInfo("Returning the procedure for attaching Benefit Component to the Product Structure");
		return true;
	}

	/**
	 * Get the Duplicate ProductStructure Information
	 * 
	 * @param productStructure
	 * @return ProductStructureBO
	 * @throws SevereException
	 */
	public ProductStructureBO checkDuplicate(ProductStructureBO productStructure)
			throws SevereException {

		Logger.logInfo("Entering the method for checking duplicate");
		AdapterUtil.performRetrieve(productStructure);

		Logger.logInfo("Returning the method for checking duplicate");
		return productStructure;
	}

	/**
	 * Retrieves the ProductStructure data corresponding to given
	 * productStructure name
	 * 
	 * @param productStructure
	 *            ProductStructureBO of the data to be retrieved.
	 * @return ProductStructureBO.
	 * @throws SevereException
	 */
	public ProductStructureBO retrieve(ProductStructureBO productStructure)
			throws SevereException {

		Logger.logInfo("Entering the method for retrieving product structure");

		AdapterUtil.performRetrieve(productStructure);

		Logger.logInfo("Returning the method for retrieving product structure");
		return productStructure;
	}

	/**
	 * Retrieves the latest ProductStructure corresponding to the
	 * ProductStructureName of the ProductStructure.
	 * 
	 * @param productStructureBO
	 *            ProductStructureBO object.
	 * @return ProductStructureBO.
	 * @throws SevereException
	 */
	public ProductStructureBO retrieveProductLatestVersion(String name,
			List lob, List businessEntity, List businessGroup, List marketBusinessUnit)
			throws SevereException {
		Logger
				.logInfo("Entering the method for retrieving product structure latest version");
		HashMap criteriaMap = getCriteriaForRetriveLatestVersion(name, lob,
				businessEntity, businessGroup, marketBusinessUnit);
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductStructureBO.class.getName(),
				BusinessConstants.LATEST_VERSION_CONTRACT, criteriaMap);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		Logger
				.logInfo("Returning the method for retrieving product structure latest version");
		if (searchResults.getSearchResultCount() > 0) {
			return (ProductStructureBO) searchResults.getSearchResults().get(0);
		}
		return null;
	}

	/**
	 * Retrieves the latest ProductStructure corresponding to the
	 * ProductStructureName of the ProductStructure.
	 * 
	 * @param productStructureBO
	 *            ProductStructureBO object.
	 * @return ProductStructureBO.
	 * @throws SevereException
	 */
	public int retrieveProductLatestVersionNumber(String name, List lob,
			List businessEntity, List businessGroup, List marketBusinessUnit) throws SevereException {
		Logger
				.logInfo("Entering the method for retrieving product structure latest version number");
		HashMap criteriaMap = getCriteriaForRetriveLatestVersion(name, lob,
				businessEntity, businessGroup, marketBusinessUnit);
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductStructureBO.class.getName(),
				BusinessConstants.LATEST_VERSION_CONTRACT_NUMBER, criteriaMap);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		if (searchResults != null && searchResults.getSearchResults() != null) {
			List searchList = searchResults.getSearchResults();
			ProductStructureBO productStructureBO = (ProductStructureBO) searchList
					.get(0);
			Logger
					.logInfo("Returning the method for retrieving product structure latest version number");
			if (productStructureBO != null)
				return productStructureBO.getVersion();
		}
		return 0;
	}

	/**
	 * Method to get the criteria map for search
	 * 
	 * @param name
	 * @param lob
	 * @param businessEntity
	 * @param businessGroup
	 * @return HashMap
	 */
	private HashMap getCriteriaForRetriveLatestVersion(String name, List lob,
			List businessEntity, List businessGroup, List marketBusinessUnit) {
		Logger
				.logInfo("Entering the method for getting criteria to retrieve latest version");
		HashMap criteriaMap = new HashMap();
		criteriaMap.put(BusinessConstants.PRODUCT_STRUCTURE_NAME, name);
		criteriaMap.put(BusinessConstants.LINE_OF_BUSINESS, lob);
		criteriaMap.put(BusinessConstants.BUSINESS_ENTITY, businessEntity);
		criteriaMap.put(BusinessConstants.BUSINESS_GROUP, businessGroup);
		//CARS START
		criteriaMap.put(BusinessConstants.MARKET_BUSINESS_UNIT, marketBusinessUnit);
		//CARS END
		Logger
				.logInfo("Returning the method for getting criteria to retrieve latest version");
		return criteriaMap;
	}

	/**
	 * Retrieves the ProductStructureBO corresponding to the ProductStructureId
	 * of the ProductStructure.
	 * 
	 * @param productStructureBO
	 *            ProductStructureBO object.
	 * @return ProductStructureBO.
	 * @throws SevereException
	 */
	public ProductStructureBO retrieveByProductId(
			ProductStructureBO productStructureBO) throws SevereException {
		Logger
				.logInfo("Entering the method for retrieving product structure by product structure id");
		HashMap criteriaMap = getCriteriaForProductStructureBO(productStructureBO);
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				productStructureBO.getClass().getName(),
				BusinessConstants.RETRIEVE_BY_PRODUCT_ID, criteriaMap);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		if (null != searchResults && searchResults.getSearchResultCount() > 0) {
			ProductStructureBO myProductStructureBO = (ProductStructureBO) searchResults
					.getSearchResults().get(0);
			setValuesToBO(myProductStructureBO, productStructureBO);
		}
		Logger
				.logInfo("Returning the method for retrieving product structure by product structure id");
		return productStructureBO;
	}

	/**
	 * Sets the values to ProductStructureBO
	 * 
	 * @param source
	 *            ProductStructureBO of the source.
	 * @param target
	 *            ProductStructureBO of the target.
	 * @return void.
	 */
	private void setValuesToBO(ProductStructureBO source,
			ProductStructureBO target) {
		Logger.logInfo("Entering the method for setting values to BO");
		if (null != source && null != target) {
			if (source.getCreatedTimestamp() != null)
				target.setCreatedTimestamp(new java.util.Date(source
						.getCreatedTimestamp().getTime()));
			if (source.getLastUpdatedTimestamp() != null)
				target.setLastUpdatedTimestamp(new java.util.Date(source
						.getLastUpdatedTimestamp().getTime()));
			target.setProductStructureId(source.getProductStructureId());
			target.setProductStructureParentId(source
					.getProductStructureParentId());
			target.setProductStructureName(source.getProductStructureName());
			target.setDescription(source.getDescription());
			target.setCreatedUser(source.getCreatedUser());
			target.setLastUpdatedUser(source.getLastUpdatedUser());
			target.setStatus(source.getStatus());
			target.setVersion(source.getVersion());
			target.setEffectiveDate(source.getEffectiveDate());
			target.setExpiryDate(source.getExpiryDate());
			target.setStructureType(source.getStructureType());
			target.setMandateType(source.getMandateType());
			target.setStateId(source.getStateId());
			target.setStateDesc(source.getStateDesc());
			target.setMandateDesc(source.getMandateDesc());
			target.setProductFamilyDesc(source.getProductFamilyDesc());
			target.setProductFamilyId(source.getProductFamilyId());
		}

	}

	/**
	 * Deletes the associated BenefitComponent
	 * 
	 * @param productStructureAssociatedBenefitComponent
	 * @param access
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean deleteBenefitComponent(
			ProductStructureAssociatedBenefitComponent productStructureAssociatedBenefitComponent,
			String userId, AdapterServicesAccess access)
			throws AdapterException {

		Logger.logInfo("Entering the method for deleting benfit component");
		try {
			AdapterUtil.performRemove(
					productStructureAssociatedBenefitComponent, userId, access);
		} catch (Exception ex) {
			throw new AdapterException(null, ex);
		}
		Logger.logInfo("Returning the method for deleting benfit component");
		return false;
	}

	/**
	 * Deletes the associated BenefitComponent
	 * 
	 * @param productStructureAssociatedBenefitComponent
	 * @param access
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean deleteProductStructureComponent(
			ProductStructureComponent productStructuretComponent,
			String userId, AdapterServicesAccess access)
			throws AdapterException {
		Logger.logInfo("Entering the method for deleting benfit component");
		try {
			AdapterUtil.performRemove(productStructuretComponent, userId,
					access);
		} catch (Exception ex) {
			throw new AdapterException(null, ex);
		}
		Logger.logInfo("Returning the method for deleting benfit component");
		return false;
	}

	/**
	 * Deletes the associated BenefitComponent which does not match the doamin
	 * change
	 * 
	 * @param productStructureAssociatedComponent
	 * @param access
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean deleteInvalidProductStructureComponent(
			ProductStructureAssociatedComponent productStructureAssociatedComponent,
			String userId, AdapterServicesAccess access)
			throws AdapterException {
		Logger.logInfo("Entering the method for deleting benfit component");
		try {
			AdapterUtil.performRemove(productStructureAssociatedComponent,
					userId, access);
		} catch (Exception ex) {
			throw new AdapterException(null, ex);
		}
		Logger.logInfo("Returning the method for deleting benfit component");
		return false;
	}

	/**
	 * Delete the ProductStructure from the db
	 * 
	 * @param productStructureBO
	 * @param userId
	 * @param access
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean deleteProductStructure(
			ProductStructureBO productStructureBO, String userId,
			AdapterServicesAccess access) throws AdapterException {

		Logger.logInfo("Entering the method for deleting product structure");
		try {
			AdapterUtil.performRemove(productStructureBO, userId, access);
		} catch (Exception ex) {
			throw new AdapterException(null, ex);
		}
		Logger.logInfo("Returning the method for deleting product structure");
		return true;
	}

	/**
	 * update ProductStructure
	 * 
	 * @param productStructureBO
	 *            ProductStructureBO object.
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean updateProductStructure(
			ProductStructureBO productStructureBO,
			AdapterServicesAccess adapterServicesAccess)
			throws AdapterException {

		Logger.logInfo("Entering the method for updating product structure");
		try {
			AdapterUtil.performUpdate(productStructureBO, productStructureBO
					.getLastUpdatedUser(), adapterServicesAccess);
		} catch (Exception ex) {
			throw new AdapterException(null, ex);
		}

		Logger.logInfo("Returning the method for updating product structure");
		return true;
	}
	public boolean updateProductStructure(
			ProductStructureBO productStructureBO)
			throws AdapterException {

		Logger.logInfo("Entering the method for updating Time Stamp product structure");
		try {
			AdapterUtil.performUpdate(productStructureBO, productStructureBO
					.getLastUpdatedUser());
		} catch (Exception ex) {
			throw new AdapterException(null, ex);
		}

		Logger.logInfo("Returning the method for updating Time Stamp product structure");
		return true;
	}
	/**
	 * Get the list of ProductStructures with the corresponding searchCriteria
	 * 
	 * @param searchCriteria
	 *            SearchCriteria object
	 * @return List of ProductStructures.
	 * @throws SevereException
	 */
	private SearchResults performSearch(SearchCriteria searchCriteria)
			throws SevereException {
		Logger.logInfo("Entering the method for performing search");
		SearchResults searchResults = null;

		searchResults = AdapterUtil.performSearch(searchCriteria);

		Logger.logInfo("Returning the method for updating product structure");
		return searchResults;
	}

	/**
	 * Get the Adapter Search Criteria
	 * 
	 * @param businessObjectName
	 *            Name of the Business Object
	 * @param queryName
	 *            Name of the query to be executed
	 * @param criteriaValues
	 *            HashMap to map the values
	 * @return SearchCriteria.
	 */
	private SearchCriteria getAdapterSearchCriteria(String businessObjectName,
			String queryName, HashMap criteriaValues) {
		Logger
				.logInfo("Entering the method for getting adapter search criteria");
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
		searchCriteria.setReferenceValueSet(criteriaValues);
		searchCriteria.setBusinessObjectName(businessObjectName);
		searchCriteria.setSearchQueryName(queryName);
		searchCriteria.setMaxSearchResultSize(999999);
		searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
		Logger
				.logInfo("Returning the method for getting adapter search criteria");
		return searchCriteria;
	}

	/**
	 * Get the Criteria for search
	 * 
	 * @param productStructureBO
	 *            ProductStructureBO Object
	 * @return HashMap.
	 */
	private HashMap getCriteriaForProductStructureBO(
			ProductStructureBO productStructureBO) {
		Logger
				.logInfo("Entering the method for getting criteria for product structure BO");
		HashMap criteriaValues = new HashMap();
		criteriaValues.put(BusinessConstants.PRODUCT_STRUCTURE_NAME,
				productStructureBO.getProductStructureName());
		criteriaValues.put(BusinessConstants.PRODUCT_STRUCTURE_ID, new Integer(
				productStructureBO.getProductStructureId()));
		Logger
				.logInfo("Returning the method for getting criteria for product structure BO");
		return criteriaValues;
	}

	/**
	 * Method for getting benefit components
	 * 
	 * @param prodStructureTreeBenefitCmpnts
	 * @return benfitcomponentlist
	 * @throws SevereException
	 */
	public List getBenefitComponents(
			ProdStructureTreeBenefitCmpnts prodStructureTreeBenefitCmpnts)
			throws SevereException {
		Logger.logInfo("Entering the method for getting benefit components");
		HashMap criteriaValues = new HashMap();
		criteriaValues.put(BusinessConstants.PRODUCT_STRUCTURE_ID, new Integer(
				prodStructureTreeBenefitCmpnts.getProductStructureId()));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				prodStructureTreeBenefitCmpnts.getClass().getName(),
				BusinessConstants.RETRIEVE_BENEFIT_COMPONENTS, criteriaValues);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		Logger.logInfo("Returning the method for getting benefit components");
		if (null != searchResults)
			return searchResults.getSearchResults();
		else
			return null;

	}

	/**
	 * Method for getting standard benefits
	 * 
	 * @param treeStandardBenefits
	 * @return standardBenefitList
	 * @throws SevereException
	 */
	public List getStandardBenefits(
			ProdStructureTreeStandardBenefits treeStandardBenefits)
			throws SevereException {
		Logger.logInfo("Entering the method for getting standard benefits");
		HashMap criteriaValues = new HashMap();
		criteriaValues.put(BusinessConstants.BEN_COMP_ID, new Integer(
				treeStandardBenefits.getBenefitCmpntId()));
		criteriaValues.put(BusinessConstants.PRODUCT_STRUCTURE_ID, new Integer(
				treeStandardBenefits.getProductStructure()));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				treeStandardBenefits.getClass().getName(),
				BusinessConstants.RETRIEVE_STD_BENEFITS, criteriaValues);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		Logger.logInfo("Returning the method for getting standard benefits");
		if (null != searchResults)
			return searchResults.getSearchResults();
		else
			return null;
	}

	/**
	 * Method for getting benefit dates
	 * 
	 * @param treeBenefitDate
	 * @return benefitDateList
	 * @throws SevereException
	 */
	public List getBenefitDate(ProdStructureTreeBenefitDate treeBenefitDate)
			throws SevereException {
		Logger.logInfo("Entering the method for getting benefit date");
		HashMap criteriaValues = new HashMap();
		criteriaValues.put(BusinessConstants.STANDARD_BENEFIT_ID, new Integer(
				treeBenefitDate.getStandardBenefitId()));
		criteriaValues.put(BusinessConstants.BNFT_COMP_ID, new Integer(
				treeBenefitDate.getBnftCmpntId()));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				treeBenefitDate.getClass().getName(),
				BusinessConstants.GET_BENEFIT_DATE, criteriaValues);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		Logger.logInfo("Returning the method for getting benefit date");
		if (null != searchResults)
			return searchResults.getSearchResults();
		else
			return null;
	}

	/**
	 * Method for getting admin options
	 * 
	 * @param treeAdminOptions
	 * @return adminOptionsList
	 * @throws SevereException
	 */
	public List getAdminOptions(ProdStructureTreeAdminOptions treeAdminOptions)
			throws SevereException {
		Logger.logInfo("Entering the method for getting admin options");
		HashMap criteriaValues = new HashMap();
		criteriaValues.put(BusinessConstants.ADMIN_ID, new Integer(
				treeAdminOptions.getAdminId()));
		criteriaValues.put(BusinessConstants.LEVEL_ID, new Integer(
				treeAdminOptions.getLvlId()));
		criteriaValues.put(BusinessConstants.ENTITYSYSID, new Integer(
				treeAdminOptions.getEntitySysId()));
		criteriaValues.put(BusinessConstants.BEN_COMPONENT_ID, new Integer(
				treeAdminOptions.getBenefitComponentId()));

		String queryString = WebConstants.EMPTY_STRING;

		if (treeAdminOptions.getLvlId() == 1) {
			queryString = BusinessConstants.RETRIEVE_BYADMIN_FOR_BEN_LEVEL;
		} else {
			queryString = BusinessConstants.RETRIEVE_BY_ADMIN_FOR_BENEFIT;
		}
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				treeAdminOptions.getClass().getName(), queryString,
				criteriaValues);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		Logger.logInfo("Returning the method for getting admin options");
		if (null != searchResults)
			return searchResults.getSearchResults();
		else
			return null;
	}

	/**
	 * Locate the list of ProductStructures with the corresponding
	 * searchCriteria
	 * 
	 * @param locateCriteria
	 *            LocateCriteria object.
	 * @return LocateResults
	 * @throws SevereException
	 * @throws Service
	 *             Exception
	 */
	public List locate(LocateCriteria locateCriteria) throws SevereException {
		Logger.logInfo("Entering the method for locating product structure");
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
		HashMap criteriaValues = new HashMap();
		SearchResults searchResults = null;

		ProductStructureLocateCriteria productStructureLocateCriteriaBO = 
		    (ProductStructureLocateCriteria) locateCriteria;
		criteriaValues = getCriteriaForproductStructureLocateCriteriaBO
		(productStructureLocateCriteriaBO);
		searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductStructureBO.class.getName(),
				BusinessConstants.LOCATE_PROD_STRUCTURE, criteriaValues);
		searchCriteria.setMaxSearchResultSize(51);
		searchResults = AdapterUtil.performSearch(searchCriteria);
		Logger.logInfo("Returning the method for locating product structure");
		if (null != searchResults)
			return searchResults.getSearchResults();
		else
			return null;
	}

	/**
	 * To get all the versoins of a ProductStructure from the db
	 * 
	 * @param viewAllVersionsLocateCriteria
	 * @return List
	 * @throws SevereException
	 */
	public List locate(
			ViewAllVersionsLocateCriteria viewAllVersionsLocateCriteria)
			throws SevereException {
		Logger.logInfo("Entering the method for locating view all versions");
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
		List searchResultList = new ArrayList(0);
		HashMap criteriaValues = new HashMap();
		SearchResults searchResults = null;
		LocateResults locateResults = new LocateResults();
		criteriaValues = getAllVersions(viewAllVersionsLocateCriteria);
		searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductStructureBO.class.getName(),
				BusinessConstants.VIEW_ALL_VERSIONS, criteriaValues);
		searchCriteria.setMaxSearchResultSize(Integer.MAX_VALUE);
		searchResults = AdapterUtil.performSearch(searchCriteria);
		Logger.logInfo("Returning the method for locating view all versions");
		if(0 != searchResults.getSearchResults().size()){
				searchResultList = searchResults.getSearchResults();
				return searchResultList;
		  }else{
				return searchResultList;
		}
		
	}

	/**
	 * To get all the valid mandates that can be associated to a
	 * ProductStructure
	 * 
	 * @param productStructureMandatesLocateCriteria
	 * @return List
	 * @throws SevereException
	 */
	public List locate(
			ProductStructureMandatesLocateCriteria productStructureMandatesLocateCriteria)
			throws SevereException {
		Logger
				.logInfo("Entering the method for locating product structure mandates");
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
		HashMap criteriaValues = new HashMap();
		SearchResults searchResults = null;
		LocateResults locateResults = new LocateResults();
		criteriaValues = getBenefitMandates(productStructureMandatesLocateCriteria);
		searchCriteria = AdapterUtil.getAdapterSearchCriteria(MandatesBO.class
				.getName(), BusinessConstants.VIEW_MANDATES, criteriaValues);
		searchCriteria.setMaxSearchResultSize(51);
		searchResults = AdapterUtil.performSearch(searchCriteria);

		Logger
				.logInfo("Returning the method for locating product structure mandates");
		if (null != searchResults)
			return searchResults.getSearchResults();
		else
			return null;
		//locateResults.setLocateResults(searchResults.getSearchResults());
	}

	/**
	 * Get the Criteria for search
	 * 
	 * @param productStructureLocateCriteriaBO
	 *            ProductStructureLocateCriteria Object
	 * @return HashMap.
	 */
	private HashMap getCriteriaForproductStructureLocateCriteriaBO(
			ProductStructureLocateCriteria productStructureLocateCriteriaBO) {
		Logger
				.logInfo("Entering the method for getting criteria for product structure locate criteria bo");
		HashMap criteriaValues = new HashMap();
		String productStructureNm = productStructureLocateCriteriaBO
				.getProductStructureName();
		if (productStructureNm != null
				&& productStructureNm.trim().length() > 0) {
			productStructureNm = "%" + productStructureNm.toUpperCase() + "%";
		} else
			productStructureNm = "%";
		criteriaValues.put("productStructureName", productStructureNm);
		criteriaValues.put("lineOfBusiness", productStructureLocateCriteriaBO
				.getLineOfBusiness());
		criteriaValues.put("businessEntity", productStructureLocateCriteriaBO
				.getBusinessEntity());
		criteriaValues.put("businessGroup", productStructureLocateCriteriaBO
				.getBusinessGroup());
		criteriaValues.put("marketBusinessUnit", productStructureLocateCriteriaBO
				.getMarketBusinessUnit());
		criteriaValues.put("effectiveDate", productStructureLocateCriteriaBO
				.getEffectiveDate());
		criteriaValues.put("expiryDate", productStructureLocateCriteriaBO
				.getExpiryDate());
		Logger
				.logInfo("Returning the method for getting criteria for product structure locate criteria bo");
		return criteriaValues;
	}

	/**
	 * To set the criteria values into the hashMap from the locateCriteria for
	 * getting the mandates for ProductStructure
	 * 
	 * @param locateCriteria
	 * @return HashMap
	 * @throws ServiceException
	 */
	public HashMap getBenefitMandates(
			ProductStructureMandatesLocateCriteria locateCriteria)
			throws ServiceException {
		Logger.logInfo("Entering the method for getting benefit mandates");
		HashMap criteriaValues = new HashMap();
		int benefitId = locateCriteria.getBenefitId();
		criteriaValues.put("benefitId", new Integer(benefitId));
		Logger.logInfo("Returning the method for getting benefit mandates");
		return criteriaValues;
	}

	/**
	 * To set the criteria values into the hashMap from the locateCriteria for
	 * getting the valid benefitComponents for ProductStructure
	 * 
	 * @param productId
	 * @return List
	 * @throws SevereException
	 */
	public List getValidComponentsForProduct(int productId)
			throws SevereException {
		Logger
				.logInfo("Entering the method for getting valid components for product");
		HashMap hashMap = new HashMap();
		hashMap.put("productId", new Integer(productId));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductStructureAssociatedBenefitComponent.class.getName(),
				"getValidComponentsForProduct", hashMap);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		Logger
				.logInfo("Returning the method for getting valid components for product");
		if (null != searchResults)
			return searchResults.getSearchResults();
		else
			return null;
	}

	/**
	 * To set the criteria values into the hashMap from the locateCriteria for
	 * getting the valid admin for ProductStructure
	 * 
	 * @param productId
	 * @return List
	 * @throws SevereException
	 */
	public List getValidAdminForProduct(int productId) throws SevereException {
		Logger
				.logInfo("Entering the method for getting valid admin for product");
		HashMap hashMap = new HashMap();
		hashMap.put("productId", new Integer(productId));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductStructureAssociatedBenefitComponent.class.getName(),
				"getValidAdminForProduct", hashMap);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		Logger
				.logInfo("Returning the method for getting valid admin for product");
		if (null != searchResults)
			return searchResults.getSearchResults();
		else
			return null;
	}

	/**
	 * To set the criteria values into the hashMap from the locateCriteria for
	 * viewAllVersions functionality of ProductStructure
	 * 
	 * @param viewAllVersionsLocateCriteria
	 * @return HashMap
	 * @throws ServiceException
	 */
	public HashMap getAllVersions(
			ViewAllVersionsLocateCriteria viewAllVersionsLocateCriteria)
			throws ServiceException {
		Logger.logInfo("Entering the method for getting all versions");
		HashMap criteriaValues = new HashMap();
		int productStructureId = viewAllVersionsLocateCriteria
				.getProductStructureId();
		criteriaValues.put("productStructureId",
				new Integer(productStructureId));
		Logger.logInfo("Returning the method for getting all versions");
		return criteriaValues;
	}

	/**
	 * To get the benefitComponents associated with a productStructure
	 * 
	 * @param productStructureId
	 * @return List
	 * @throws SevereException
	 */
	public List retrieveBenefitComponents(int productStructureId)
			throws SevereException {

		Logger.logInfo("Entering the method for retrieving benefit components");
		HashMap hashMap = new HashMap();
		hashMap.put("productStructureId", new Integer(productStructureId));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductStructureAssociatedBenefitComponent.class.getName(),
				"retrieveBenefitComponents", hashMap);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		Logger
				.logInfo("Returning the method for retrieving benefit components");
		if (null != searchResults){
			return searchResults.getSearchResults();
		}else{
			return null;
		}
	}

	/**
	 * To get the sequence numbers of the benefit components associated with the
	 * ProductStructure
	 * 
	 * @param associatedBenefitComponent
	 * @return int
	 * @throws SevereException
	 */
	public int getSequenceNumber(
			ProductStructureAssociatedBenefitComponent associatedBenefitComponent)
			throws SevereException {
		Logger.logInfo("Entering the method for getting sequence number");
		AdapterUtil.performRetrieve(associatedBenefitComponent);

		Logger.logInfo("Returning the method for getting sequence number");
		return associatedBenefitComponent.getSequenceNum();
	}

	/**
	 * To update the benefitcomponent values associated with a ProductStructure
	 * 
	 * @param productStructureAssociatedBenefitComponent
	 * @param access
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean updateBenefitComponent(
			ProductStructureAssociatedBenefitComponent productStructureAssociatedBenefitComponent,
			AdapterServicesAccess access) throws SevereException {

		Logger.logInfo("Entering the method for updating benfit component");

		AdapterUtil
				.performUpdate(productStructureAssociatedBenefitComponent,
						productStructureAssociatedBenefitComponent
								.getLastUpdatedUser(), access);

		Logger.logInfo("Returning the method for updating benfit component");
		return true;

	}

	/**
	 * To get the benefitDefinition values from the db
	 * 
	 * @param benefitComponentId
	 * @param access
	 * @return
	 * @throws SevereException
	 */
	public List getBenefitLineOverrideValue(int benefitComponentId)
			throws SevereException {
		Logger
				.logInfo("Entering the method for getting benefit line override value");
		HashMap referenceValueSet = new HashMap();
		referenceValueSet.put("productStructureId", new Integer(
				benefitComponentId));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductStructureBenefitDefinition.class.getName(),
				"retrieveBenefitDefenition", referenceValueSet);
		List definitionList = AdapterUtil.performSearch(searchCriteria)
				.getSearchResults();
		Logger
				.logInfo("Returning the method for getting benefit line override value");
		return definitionList;
	}

	/**
	 * To save the benefitDefinition values assocaited to a ProductStructure
	 * 
	 * @param benefitDefinition
	 * @param access
	 * @throws SevereException
	 */
	public void insertBenefitLineOverrideValue(
			ProductStructureBenefitDefinition benefitDefinition,
			AdapterServicesAccess access) throws SevereException {

		Logger
				.logInfo("Entering the method for inserting benefit line override value");
		AdapterUtil.performInsert(benefitDefinition, benefitDefinition
				.getCreatedUser(), access);
		Logger
				.logInfo("Returning the method for inserting benefit line override value");

	}

	/**
	 * To get the benefitDefinition values from the db
	 * 
	 * @param productStructureId
	 * @return List
	 * @throws SevereException
	 */
	public List retrieveBenefitLineOverrideValue(int productStructureId)
			throws SevereException {
		Logger
				.logInfo("Entering the method for retrieving benefit line override value");
		HashMap referenceValueSet = new HashMap();
		referenceValueSet.put("productStructureId", new Integer(
				productStructureId));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductStructureBenefitDefinition.class.getName(),
				"retrieveBenefitLineValue", referenceValueSet);
		List benefitLineList = AdapterUtil.performSearch(searchCriteria)
				.getSearchResults();
		Logger
				.logInfo("Returning the method for retrieving benefit line override value");
		return benefitLineList;

	}

	/**
	 * To get the latest version of benefitDefinition values from the db
	 * 
	 * @param benefitCmpntId
	 * @return List
	 * @throws SevereException
	 */
	public List retrieveBenefitLineValueLatestVersion(int benefitCmpntId)
			throws SevereException {
		Logger
				.logInfo("Entering the method for retrieving benefit line value latest version");
		HashMap referenceValueSet = new HashMap();
		referenceValueSet
				.put(BusinessConstants.BENCOMID, new Integer(benefitCmpntId));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductStructureBenefitDefinition.class.getName(),
				"retrieveBenefitLineValueLatestVersion", referenceValueSet);
		List benefitLineList = AdapterUtil.performSearch(searchCriteria)
				.getSearchResults();
		Logger
				.logInfo("Returning the method for retrieving benefit line value latest version");
		return benefitLineList;
	}

	/**
	 * To get the benefitDefinition values from the db
	 * 
	 * @param benefitCmpntId
	 * @param productStructureId
	 * @return List
	 * @throws SevereException
	 */
	public List retrieveBenefitLineOverrideValueForStructure(
			int benefitCmpntId, int productStructureId) throws SevereException {
		Logger
				.logInfo("Entering the method for retrieving benefit line override value for structure");
		HashMap referenceValueSet = new HashMap();
		referenceValueSet
				.put("benefitComponentId", new Integer(benefitCmpntId));
		referenceValueSet.put("productStructureId", new Integer(
				productStructureId));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductStructureBenefitDefinition.class.getName(),
				"retrieveBenefitLineOverrideValueForStructure",
				referenceValueSet);
		List benefitLineList = AdapterUtil.performSearch(searchCriteria)
				.getSearchResults();
		Logger
				.logInfo("Returning the method for retrieving benefit line override value for structure");
		return benefitLineList;
	}

	/**
	 * To get the benefitAdministration from the db
	 * 
	 * @param benefitComponentId
	 * @param access
	 * @return
	 * @throws SevereException
	 */
	public List getBenefitAdministration(int benefitComponentId)
			throws SevereException {
		Logger
				.logInfo("Entering the method for getting benefit administration");
		HashMap referenceValueSet = new HashMap();
		referenceValueSet.put("productStructureId", new Integer(
				benefitComponentId));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductStructureBenefitAdministration.class.getName(),
				"retrieveBenefitAdmn", referenceValueSet);
		List definitionList = AdapterUtil.performSearch(searchCriteria)
				.getSearchResults();
		Logger
				.logInfo("Returning the method for getting benefit administration");
		return definitionList;
	}

	/**
	 * To save the administration values associated with a productStructure
	 * 
	 * @param administration
	 * @param access
	 * @throws SevereException
	 */
	public void insertAdministrationValue(
			ProductStructureBenefitAdministration administration,
			AdapterServicesAccess access) throws SevereException {

		Logger
				.logInfo("Entering the method for inserting administration values");
		AdapterUtil.performInsert(administration, administration
				.getCreatedUser(), access);
		Logger
				.logInfo("Returning the method for inserting administration values");

	}

	/**
	 * To get the benefitAdministration values associated with a
	 * ProductStructure
	 * 
	 * @param productStructureId
	 * @return List
	 * @throws SevereException
	 */
	public List retrieveBenefitAdministration(int productStructureId)
			throws SevereException {
		Logger
				.logInfo("Entering the method for retrieving administration values");
		HashMap referenceValueSet = new HashMap();
		referenceValueSet.put("productStructureId", new Integer(
				productStructureId));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductStructureBenefitAdministration.class.getName(),
				"getBenefitAdmn", referenceValueSet);
		List admnList = AdapterUtil.performSearch(searchCriteria)
				.getSearchResults();
		Logger
				.logInfo("Returning the method for retrieving administration values");
		return admnList;
	}

	/**
	 * To retrieve the latest administration values from the db
	 * 
	 * @param benefitComponentId
	 * @return List
	 * @throws SevereException
	 */
	public List retrieveBenefitAdministrationLatest(int benefitComponentId)
			throws SevereException {
		Logger
				.logInfo("Entering the method for retrieving administration latest");
		HashMap referenceValueSet = new HashMap();
		referenceValueSet.put("benefitComponentId", new Integer(
				benefitComponentId));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductStructureBenefitAdministration.class.getName(),
				"getBenefitAdmnForCmpnt", referenceValueSet);
		List admnList = AdapterUtil.performSearch(searchCriteria)
				.getSearchResults();
		Logger
				.logInfo("Returning the method for retrieving administration latest");
		return admnList;
	}

	/**
	 * To get all the benefitAdministration values associated to a
	 * ProductStructure from the db
	 * 
	 * @param benefitComponentId
	 * @param productStructureId
	 * @return List
	 * @throws SevereException
	 */
	public List retrieveBenefitAdministrationOverrideValue(
			int benefitComponentId, int productStructureId)
			throws SevereException {
		Logger
				.logInfo("Entering the method for retrieving benefit administration override value");
		HashMap referenceValueSet = new HashMap();
		referenceValueSet.put("benefitComponentId", new Integer(
				benefitComponentId));
		referenceValueSet.put("productStructureId", new Integer(
				productStructureId));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductStructureBenefitAdministration.class.getName(),
				"getBenefitAdmnForProduct", referenceValueSet);
		List admnList = AdapterUtil.performSearch(searchCriteria)
				.getSearchResults();
		Logger
				.logInfo("Returning the method for retrieving benefit administration override value");
		return admnList;
	}

	/**
	 * To get the valid list of ProductStructures from the db
	 * 
	 * @param lineOfBusiness
	 * @param businessEntity
	 * @param businessGroup
	 * @param effectiveDate
	 * @param expiryDate
	 * @return List
	 * @throws SevereException
	 */
	public List getValidProductStructures(List lineOfBusiness,
			List businessEntity, List businessGroup, Date effectiveDate,
			Date expiryDate, String structureType, String mandateType,
			String stateId, List marketBusinessUnit) throws SevereException {
		Logger
				.logInfo("Entering the method for getting valid product structure");
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("lineOfBusiness", lineOfBusiness);
		criteriaMap.put("businessEntity", businessEntity);
		criteriaMap.put("businessGroup", businessGroup);
		criteriaMap.put("marketBusinessUnit", marketBusinessUnit);
		criteriaMap.put("expiryDate", WPDStringUtil
				.convertDateToString(expiryDate));
		criteriaMap.put("structureType", structureType);
		criteriaMap.put("mandateType", mandateType);
		criteriaMap.put("stateId", stateId);
		// This is done for making adapter dynamic query working
		if (effectiveDate == null)
			criteriaMap.put("effectiveDate", "12/31/9999");
		else
			criteriaMap.put("effectiveDate", WPDStringUtil
					.convertDateToString(effectiveDate));

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductStructureBO.class.getName(),
				"getAllValidProductStructureForProduct", criteriaMap);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		Logger
				.logInfo("Returning the method for getting valid product structure");
		if (null != searchResults)
			return searchResults.getSearchResults();
		else
			return null;
	}

	/**
	 * To get the valid set of benefitComponents from db
	 * 
	 * @param productStructureId
	 * @param effectiveDate
	 * @param expiryDate
	 * @throws SevereException
	 */
	public List inValidDateRange(int productStructureId, Date effectiveDate,
			Date expiryDate) throws SevereException {
		Logger.logInfo("Entering the method for getting valid date range");
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("expiryDate", WPDStringUtil
				.convertDateToString(expiryDate));
		criteriaMap.put("effectiveDate", WPDStringUtil
				.convertDateToString(effectiveDate));
		criteriaMap.put("productStructureId", new Integer(productStructureId));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductStructureAssociatedBenefitComponent.class.getName(),
				"getValidDateRange", criteriaMap);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		Logger.logInfo("Returning the method for getting valid date range");
		if (null != searchResults)
			return searchResults.getSearchResults();
		else
			return null;
	}

	/**
	 * 
	 * 
	 * @param productStructureId
	 * @return List
	 * @throws SevereException
	 */
	public List getDuplicateHeirarchy(int productStructureId)
			throws SevereException {
		Logger.logInfo("Entering the method for getting duplicate heirarchy");
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("productStructureId", new Integer(productStructureId));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductStructureAssociatedBenefitComponent.class.getName(),
				"getduplicateHeirarchy", criteriaMap);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		Logger.logInfo("Returning the method for getting duplicate heirarchy");
		if (null != searchResults)
			return searchResults.getSearchResults();
		else
			return null;

	}

	/**
	 * To get the latest published version of a productstructure
	 * 
	 * @param productStructureId
	 * @return ProductStructureBO
	 * @throws SevereException
	 */
	public ProductStructureBO retrieveLatestPublishedVersion(
			int productStructureId) throws SevereException {
		Logger
				.logInfo("Entering the method for retrieving latest publishe version");
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("productStructureId", new Integer(productStructureId));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductStructureBO.class.getName(),
				"retrieveLatestPublishedVersion", criteriaMap);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		Logger
				.logInfo("Returning the method for retrieving latest publishe version");
		if (null != searchResults && searchResults.getSearchResultCount() > 0) {
			return (ProductStructureBO) searchResults.getSearchResults().get(0);
		}
		return null;
	}

	/**
	 * To get the latest published version of a productstructure
	 * 
	 * @param productStructureId
	 * @return ProductStructureBO
	 * @throws SevereException
	 */
	public ProductStructureBO validDatedLatestPublishedVersion(int productSysId)
			throws SevereException {
		Logger
				.logInfo("Entering the method for retrieving latest publishe version");
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("productSysId", new Integer(productSysId));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductStructureBO.class.getName(),
				"validDateLatestPublishedVersion", criteriaMap);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		Logger
				.logInfo("Returning the method for retrieving latest publishe version");
		if (null != searchResults && searchResults.getSearchResultCount() > 0) {
			return (ProductStructureBO) searchResults.getSearchResults().get(0);
		}
		return null;
	}

	/**
	 * To get the mandatory components associated to a ProductStructure from the
	 * db
	 * 
	 * @param productStructureId
	 * @return List
	 * @throws SevereException
	 */
	public List getMandatoryComponents(int productStructureId)
			throws SevereException {
		Logger.logInfo("Entering the method for getting mandatory components");
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("productStructureId", new Integer(productStructureId));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductStructureAssociatedBenefitComponent.class.getName(),
				"getmandatoryComponent", criteriaMap);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		Logger.logInfo("Returning the method for getting mandatory components");
		if (null != searchResults)
			return searchResults.getSearchResults();
		else
			return null;
	}

	/**
	 * To get the mandatory components associated to a ProductStructure from the
	 * db
	 * 
	 * @param productStructureId
	 * @return List
	 * @throws SevereException
	 */
	public List getMandatoryGenProvision(int productStructureId)
			throws SevereException {
		Logger.logInfo("Entering the method for getting mandatory components(General Provision)");
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("productStructureId", new Integer(productStructureId));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductStructureAssociatedBenefitComponent.class.getName(),
				"getmandatoryGenProvision", criteriaMap);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		Logger.logInfo("Returning the method for getting mandatory components(General Provision)");
		if (null != searchResults)
			return searchResults.getSearchResults();
		else
			return null;
	}

	/**
	 * @param productStructureName
	 * @param lob
	 * @param be
	 * @param bg
	 * @param productStructureParentId
	 * @return
	 * @throws SevereException
	 */
	public List getDuplicateNameList(String entityName, List domainList,
        	int entityParentId)
			throws SevereException {
		
		List lineOfBusiness = new ArrayList(domainList==null?0:domainList.size());
        List businessEntity = new ArrayList(domainList==null?0:domainList.size());
        List businessGroup = new ArrayList(domainList==null?0:domainList.size());
        //CARS START
        List marketBusinessUnit = new ArrayList(domainList==null?0:domainList.size());
        //CARS END
        Domain domain = null;
        boolean flag = true;
        if(null != domainList && !domainList.isEmpty()) {
	        for (Iterator iter = domainList.iterator(); iter.hasNext();) {
	            domain = (Domain) iter.next();
	            lineOfBusiness.add(domain.getLineOfBusiness());
	            businessEntity.add(domain.getBusinessEntity());
	            businessGroup.add(domain.getBusinessGroup());
	            marketBusinessUnit.add(domain.getMarketBusinessUnit());
	        }
        }
        
        HashMap criteriaMap = new HashMap();
        
        //      If Line of business is 'ALL' for new Product Structure, then Product 
        //Structure with same name will be considered as 
        // duplicate for any value of Line of Business if Business entity and Buseinss Group are matching.
        // So Line of business needs to be excluded from the query if it is 'ALL'.
        // This is also valid for BE and BG.
       
        if(!lineOfBusiness.contains(BusinessConstants.UNIVERSAL_DOMAIN)) {
        	 criteriaMap.put(BusinessConstants.LINE_OF_BUSINESS, lineOfBusiness);
        }
        
        if(!businessEntity.contains(BusinessConstants.UNIVERSAL_DOMAIN)) {
        	criteriaMap.put(BusinessConstants.BUSINESS_ENTITY, businessEntity);        	
        }
        
        if(!businessGroup.contains(BusinessConstants.UNIVERSAL_DOMAIN)) {
        	criteriaMap.put(BusinessConstants.BUSINESS_GROUP, businessGroup);    	
        }
        //CARS START
        if(!marketBusinessUnit.contains(BusinessConstants.UNIVERSAL_DOMAIN)) {
        	criteriaMap.put(BusinessConstants.MARKET_BUSINESS_UNIT, marketBusinessUnit);    	
        }//CARS END
		criteriaMap.put(BusinessConstants.PRODUCT_STRUCTURE_NAME,entityName);
		criteriaMap.put("productStructureParentId", new Integer(
				entityParentId));
		
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductStructureBO.class.getName(), "findDuplicate",
				criteriaMap);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		if (searchResults != null)
			return searchResults.getSearchResults();
		else
			return null;
	}
	
	
	/**
	 * This method is for checking out the product structure
	 * 
	 * @param srcProductStructureBO
	 * @param productStructureBO
	 * @param audit
	 * @return
	 * @throws SevereException
	 */
	public boolean checkoutProductStructure(
			ProductStructureBO srcProductStructureBO,
			ProductStructureBO productStructureBO, Audit audit,
			AdapterServicesAccess adapterServicesAccess)
			throws SevereException, AdapterException {
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("oldProductStructureId", new Integer(
				srcProductStructureBO.getProductStructureId()));
		criteriaMap.put("newProductStructureId", new Integer(productStructureBO
				.getProductStructureId()));
		criteriaMap.put("checkOutUser", audit.getUser());
		criteriaMap.put("lastUpdatedTimeStamp", audit.getTime());
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductStructureBO.class.getName(), "checkOutProductStructure",
				criteriaMap);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria,
				adapterServicesAccess);
		if (searchResults != null)
			return true;
		else
			return false;
	}

	/**
	 * This method is for copying the product structure
	 * 
	 * @param srcProductStructureBO
	 * @param productStructureBO
	 * @param audit
	 * @return
	 * @throws SevereException
	 */
	public boolean copyProductStructure(
			ProductStructureBO srcProductStructureBO,
			ProductStructureBO productStructureBO, Audit audit,
			AdapterServicesAccess adapterServicesAccess)
			throws SevereException, AdapterException {
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("oldProductStructureId", new Integer(
				srcProductStructureBO.getProductStructureId()));
		criteriaMap.put("newProductStructureId", new Integer(productStructureBO
				.getProductStructureId()));
		criteriaMap.put("checkOutUser", audit.getUser());
		criteriaMap.put("lastUpdatedTimeStamp", audit.getTime());
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductStructureBO.class.getName(), "copyProductStructure",
				criteriaMap);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria,
				adapterServicesAccess);
		if (searchResults != null)
			return true;
		else
			return false;
	}

	public boolean getVisibleBenefits(
			ProductStructureAssociatedBenefitComponent associatedComponent)
			throws SevereException {
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("productStructureId", new Integer(associatedComponent
				.getProductStructureId()));
		criteriaMap.put("benefitComponentId", new Integer(associatedComponent
				.getBenefitComponentId()));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductStructureAssociatedBenefitComponent.class.getName(),
				"getVisibleBenefits", criteriaMap);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		if (searchResults != null && searchResults.getSearchResultCount() > 0)
			return true;
		else
			return false;
	}

	/**
	 * updates the product associated benefits with hidden as true or false
	 * 
	 * @param productTreeStandardBenefits
	 * @throws SevereException
	 */
	public void updateAllBenefitDetails(
			ProductStructureAssociatedBenefit productStructureAssociatedBenefit,
			AdapterServicesAccess access, String userId) throws SevereException,
			AdapterException {
		int benefitComponentId = 0;
		int productStructureId = 0;
		List benefitDetailsUpdatedList = null;

		if (null != productStructureAssociatedBenefit) {
			benefitComponentId = productStructureAssociatedBenefit
					.getBenefitComponentId();
			//  benefitComponentId = 1146;
			productStructureId = productStructureAssociatedBenefit
					.getProductStructureId();
			//   productStructureId = 364;
		}
		// For updating all the benefit details
		benefitDetailsUpdatedList = productStructureAssociatedBenefit
				.getBenefitDetailsList();
		for (int i = 0; i < benefitDetailsUpdatedList.size(); i++) {
			ProductStructureAssociatedBenefit productStructureAssociatedBenefitForUpdates = null;
			productStructureAssociatedBenefitForUpdates = (ProductStructureAssociatedBenefit) benefitDetailsUpdatedList
					.get(i);
			productStructureAssociatedBenefitForUpdates
					.setBenefitComponentId(benefitComponentId);
			productStructureAssociatedBenefitForUpdates
					.setProductStructureId(productStructureId);
			productStructureAssociatedBenefitForUpdates.setEntityType("ProdStructure");

			/**Removing the Update query which updates without PVA Validation**/
		//Hiding all the correspondong files if the benefit flag is hidden.
			/**if (productStructureAssociatedBenefitForUpdates
					.isBenefitVisibilityStatus()) {
				productStructureAssociatedBenefitForUpdates
						.setBenefitHideFlag(WebConstants.CONST_T);
				productStructureAssociatedBenefitForUpdates
						.setBenefitLevelFlag(WebConstants.CONST_T);
				productStructureAssociatedBenefitForUpdates
						.setBenefitLineFlag(WebConstants.CONST_T);
				productStructureAssociatedBenefitForUpdates
						.setAdminOptionFlag(WebConstants.CONST_T);
				productStructureAssociatedBenefitForUpdates
						.setQuestionsFlag(WebConstants.CONST_T);
			} 
			// Unhidding all the benefit flag if the benefit flag is unhidden.
		//	else {
		
				productStructureAssociatedBenefitForUpdates
						.setBenefitHideFlag(WebConstants.CONST_F);
				productStructureAssociatedBenefitForUpdates
						.setBenefitLevelFlag(WebConstants.CONST_F);
				productStructureAssociatedBenefitForUpdates
						.setBenefitLineFlag(WebConstants.CONST_F);
				productStructureAssociatedBenefitForUpdates
						.setAdminOptionFlag(WebConstants.CONST_F);
				productStructureAssociatedBenefitForUpdates
						.setQuestionsFlag(WebConstants.CONST_F);
			
		}
			AdapterUtil
					.performUpdate(productStructureAssociatedBenefitForUpdates,
							userId, access); **/
		
		
				/** Updation of the flags taking place in the procedure PS_HIDE_UNHIDE_BENEFITS eWPD Stabilization Release**/
			
				HashMap hideUnhideMap = new HashMap();
			    hideUnhideMap.put("productStructureId", new Integer(productStructureAssociatedBenefit.getProductStructureId()));
			    hideUnhideMap.put("benefitComponentId", new Integer(productStructureAssociatedBenefit.getBenefitComponentId()));
			    hideUnhideMap.put("benefitId", new Integer(productStructureAssociatedBenefitForUpdates.getStandardBenefitId()));
			    hideUnhideMap.put("userId", userId);
			    hideUnhideMap.put("isBenefitVisibilityStatus",""+productStructureAssociatedBenefitForUpdates.isBenefitVisibilityStatus());
				SearchCriteria criteria = AdapterUtil.getAdapterSearchCriteria(ProductStructureAssociatedBenefit.class.getName(), "PS_hideunhidebenefits", hideUnhideMap);
				AdapterUtil.performSearch(criteria, access); 

			// Remove customization while hiding.
			
			if (productStructureAssociatedBenefitForUpdates.isBenefitVisibilityStatus()) {
				HashMap map = new HashMap();
				map.put("productStructureId", new Integer(productStructureAssociatedBenefit.getProductStructureId()));
				map.put("benefitComponentId", new Integer(productStructureAssociatedBenefit.getBenefitComponentId()));
				map.put("benefitId", new Integer(productStructureAssociatedBenefitForUpdates.getStandardBenefitId()));
				map.put("userId", userId);
				SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(ProductStructureAssociatedBenefit.class.getName(), "PS_removeBenefitCustomization", map);
				AdapterUtil.performSearch(searchCriteria, access);
			}
	}
	}

	/**
	 * retrieves the product Benefit details.
	 * 
	 * @param benefitDetails
	 * @throws SevereException
	 */
	public LocateResults retrieveProductStructureAssociatedBenefit(
			ProductStructureShowHiddenLocateCriteria productStructureLocateCriteria)
			throws SevereException {

		LocateResults locateResults = new LocateResults();
		HashMap referenceValueSet = new HashMap();
		if (null != productStructureLocateCriteria) {
			int benefitComponentId = productStructureLocateCriteria
					.getBenefitComponentId();
			int productStructId = productStructureLocateCriteria
					.getProductStructureId();
			//benefitComponentId = 1146;
			// productStructId = 364;
			if (0 != benefitComponentId) {
				// Setting the search criteria for the query.
				referenceValueSet.put("benefitComponentId", new Integer(
						benefitComponentId));				
			}
			referenceValueSet.put("productStructureId", new Integer(
					productStructId));
		}
		SearchCriteria searchCriteria = new SearchCriteriaImpl();;
		//Checking to retrieve the associated component hierarchy
		if(productStructureLocateCriteria.isHierarchyFlag()){
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					ProductStructureAssociatedBenefit.class.getName(),
					"searchComponentHierarchy", referenceValueSet);
		}
		// Checking if the Status Hidden is Checked inorder to retrieve entire
		// records.
		else if (productStructureLocateCriteria.isHiddenFlag()) {

			searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					ProductStructureAssociatedBenefit.class.getName(),
					"searchAllStandardBenefits", referenceValueSet);

		} else {
			// Retrieving the records with benefit visibility as true for the
			// time of Load
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					ProductStructureAssociatedBenefit.class.getName(),
					"searchVisibleStandardBenefits", referenceValueSet);
		}
		if (searchCriteria != null) {			
			locateResults.setLocateResults(AdapterUtil.performSearch(
					searchCriteria).getSearchResults());

		}
		return locateResults;

	}
	
	/**
	 * To get the list of BenefitAdministration objects from the db for
	 * ProductStructure
	 * 
	 * @param productStructureBO
	 * @param benefitAdministrationSubObject
	 * @return List
	 * @throws SevereException
	 */
	public List getQuestionnaireValues(
			int adminLevelOptionSysId, int beneftComponentId, int entityId,
			int benefitAdminId, int adminOptionId, int parentId)
			throws SevereException {

		// Create a hashMap object
		HashMap map = new HashMap();
		map.put(BusinessConstants.PRODUCT_STRUCTURE_ID, new Integer(entityId)); 
		map.put(BusinessConstants.ADMN_LVL_OPTION_SYS_ID, new Integer(adminLevelOptionSysId));
		map.put(BusinessConstants.BEN_COMPONENT_ID, new Integer(beneftComponentId));
		
		map.put("benefitAdminId",new Integer(benefitAdminId));
		map.put("adminOptionId",new Integer(adminOptionId));
		map.put("parentId",new Integer(parentId));
		
		// Get the searchCriteria object
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductStructureQuestionnaireBO.class.getName(),
				"getQuestionnaire", map);
		
		// Get the locate Result after the search operation in the db
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		
		// Return the resulting list to the builder
		return searchResults.getSearchResults();
		
	}
	
	/**
	 * This method retrieves all the child questionnaire values
	 * 
	 * @param ProductStructureQuestionnaireBO
	 * @param selectedAnswerid,questionnaireId, productStructureId
	 * 
	 */
	public List getChildQuestionnaireValues(int selectedAnswerid, int questionnaireId, int productStructureId,
	        int adminLvlOptAssnId,int benefitCompId,int benefitDefnId)
		throws SevereException {
	
			HashMap map = new HashMap();
			// Set the searchCriteria values in a map
			map.put(BusinessConstants.SELECTED_ANSWER_ID, String.valueOf(selectedAnswerid));
			map.put(BusinessConstants.QUESTIONNAIRE_ID, String.valueOf(questionnaireId));
			map.put(BusinessConstants.PRODUCT_STRUCTURE_ID, new Integer(productStructureId));
			
			map.put(BusinessConstants.ADMN_LVL_OPTION_SYS_ID,  new Integer(adminLvlOptAssnId));
			map.put(BusinessConstants.BEN_COMPONENT_ID, new Integer(benefitCompId));
			map.put(BusinessConstants.BENEFIT_DEFNID, String.valueOf(benefitDefnId));
			
			
			
			// Get the searchCriteria object
			SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					ProductStructureQuestionnaireBO.class.getName(),
					"getChildQuestionnaire", map);
			// Get the locate Result after the search operation in the db
			SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
			// Return the resulting list to the builder
			return searchResults.getSearchResults();
	
	}

	/**
	 * This method delete all the existing questionare and persist a new questionnare
	 * 
	 * @param ProductStructureQuestionnaireBO
	 * @param Audit,AdapterServicesAccess
	 * 
	 */
	public void saveQuestionnaire(ProductStructureBenefitAdministrationBO administrationBO,Audit audit,
			AdapterServicesAccess access) throws SevereException{
		
		List questionnaireList = administrationBO.getQuestionnaireList();
		List newQuestions 	   = administrationBO.getQuestionnaireListToAdd();
		List modifiedQuestions = administrationBO.getQuestionnaireListToUpdate();
		List removedQuestions  = administrationBO.getQuestionnaireListToRemove();
		
		int productStructureId = administrationBO.getProductStructureId();
		ProductStructureQuestionnaireBO productStructureQuestionnaireBO = new ProductStructureQuestionnaireBO();
		productStructureQuestionnaireBO.setProductStructureId(administrationBO.getProductStructureId());
		productStructureQuestionnaireBO.setAdminLevelOptionSysId(administrationBO.getAdminLevelOptionSysId());
		Iterator it1 = newQuestions.iterator();
		while(it1.hasNext()){
			ProductStructureQuestionnaireBO productStructureQuestionnaireBOToAdd = 
				(ProductStructureQuestionnaireBO)it1.next();
			productStructureQuestionnaireBOToAdd.setProductStructureId(administrationBO.getProductStructureId());
			productStructureQuestionnaireBOToAdd.setBenefitComponentId(administrationBO.getBenefitComponentId());
			productStructureQuestionnaireBOToAdd.setAdminLevelOptionSysId(administrationBO.getAdminLevelOptionSysId());
			productStructureQuestionnaireBOToAdd.setCreatedUser(audit.getUser());
			productStructureQuestionnaireBOToAdd.setCreatedTimestamp(audit.getTime());
			productStructureQuestionnaireBOToAdd.setLastUpdatedUser(audit.getUser());
			productStructureQuestionnaireBOToAdd.setLastUpdatedTimestamp(audit.getTime());
			AdapterUtil.performInsert(productStructureQuestionnaireBOToAdd, audit.getUser(),access);
		}
		Iterator it2 = modifiedQuestions.iterator();
		while(it2.hasNext()){
			ProductStructureQuestionnaireBO productStructureQuestionnaireBOToUpdate = 
				(ProductStructureQuestionnaireBO)it2.next();
			productStructureQuestionnaireBOToUpdate.setProductStructureId(administrationBO.getProductStructureId());
			productStructureQuestionnaireBOToUpdate.setBenefitComponentId(administrationBO.getBenefitComponentId());
			productStructureQuestionnaireBOToUpdate.setAdminLevelOptionSysId(administrationBO.getAdminLevelOptionSysId());
			productStructureQuestionnaireBOToUpdate.setCreatedUser(audit.getUser());
			productStructureQuestionnaireBOToUpdate.setCreatedTimestamp(audit.getTime());
			productStructureQuestionnaireBOToUpdate.setLastUpdatedUser(audit.getUser());
			productStructureQuestionnaireBOToUpdate.setLastUpdatedTimestamp(audit.getTime());
			AdapterUtil.performUpdate(productStructureQuestionnaireBOToUpdate, audit.getUser(),access);
		}
		Iterator it3 = removedQuestions.iterator();
		while(it3.hasNext()){
			ProductStructureQuestionnaireBO productStructureQuestionnaireBOToRemove = 
				(ProductStructureQuestionnaireBO)it3.next();
			productStructureQuestionnaireBOToRemove.setProductStructureId(administrationBO.getProductStructureId());
			productStructureQuestionnaireBOToRemove.setBenefitComponentId(administrationBO.getBenefitComponentId());
			productStructureQuestionnaireBOToRemove.setAdminLevelOptionSysId(administrationBO.getAdminLevelOptionSysId());
			productStructureQuestionnaireBOToRemove.setCreatedUser(audit.getUser());
			productStructureQuestionnaireBOToRemove.setCreatedTimestamp(audit.getTime());
			productStructureQuestionnaireBOToRemove.setLastUpdatedUser(audit.getUser());
			productStructureQuestionnaireBOToRemove.setLastUpdatedTimestamp(audit.getTime());
			AdapterUtil.performRemove(productStructureQuestionnaireBOToRemove, audit.getUser(),access);
		}
		
		
	}
	
	 /**
	 * To refresh the questionnaire 
	 * 
	 * @param ProductStructureQuestionnaireBO
	 * @throws SevereException
	 */
    public void refreshQuestionnaire(int prodStrSysId, int benefitComponentId, String userId) throws SevereException {
    	HashMap values = new HashMap();
    	values.put(BusinessConstants.PRODUCT_STRUCTURE_ID,String.valueOf(prodStrSysId));
		values.put("lastUpdatedUser",userId);
		values.put(BusinessConstants.BEN_COMPONENT_ID,String.valueOf(benefitComponentId));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(ProductStructureQuestionnaireBO.class.getName(),"refreshQuestionaireProc",values);
		AdapterUtil.performSearch(searchCriteria);
    }
    
	 /**
	 * To refresh the questionnaire 
	 * 
	 * @param ProductStructureQuestionnaireBO
	 * @throws SevereException
	 */
    public void refreshQuestionnaire(int prodStrSysId, int benefitComponentId, String userId, AdapterServicesAccess access) throws SevereException {
    	HashMap values = new HashMap();
    	values.put(BusinessConstants.PRODUCT_STRUCTURE_ID,String.valueOf(prodStrSysId));
		values.put("lastUpdatedUser",userId);
		values.put(BusinessConstants.BEN_COMPONENT_ID,String.valueOf(benefitComponentId));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(ProductStructureQuestionnaireBO.class.getName(),"refreshQuestionaireProc",values);
		AdapterUtil.performSearch(searchCriteria, access);
    }
    /**
	 * To update the overridded values of the administration option questions
	 * 
	 * @param productStructureBO
	 * @param benefitAdministration
	 * @throws SevereException
	 */
	public void updateAdminOptionValues(HideAdminOption adminOption,
			String userId, AdapterServicesAccess access)
			throws AdapterException {
		try {
			// Call the update method
			AdapterUtil.performUpdate(adminOption, userId, access);
		} catch (Exception e) {
			throw new AdapterException("Exception occured while adapter call",
					e);
		}
	}
	
	 
	 /**
	 * @param benefitAdminId
	 * @return
	 * @throws SevereException
	 */
	public List getBenefitDefinitionId(int benefitAdminId)
		throws SevereException {
		    int benefitDefnId  = 0;		    
		    Logger.logInfo("Entering the method for getting BenefitDefinitionId ");
		    HashMap criteriaMap = new HashMap();
		    criteriaMap.put("bnftAdmnId", new Integer(benefitAdminId));
		    SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
		            ProdStructureTreeBenefitDate.class.getName(),
			"getBenefitDefnId", criteriaMap);		   
		    SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		    Logger.logInfo("Returning the method for getting BenefitDefinitionId");		   
		    if (null != searchResults)
				return searchResults.getSearchResults();
			else
				return null;	
	}
	
	/*
	 * @ param productStructureId
	 *
	 * @return proudctFamily
	 * @throws SevereException
	 */
	 public String getProductFamily(int productStructureId)throws SevereException{
	  	String proudctFamily = null;
	  	Logger.logInfo("Entering the method for getting proudctFamily ");
	    HashMap criteriaMap = new HashMap();
	    criteriaMap.put("productStructureId", new Integer(productStructureId));
	    SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
	    		ProductStructureBO.class.getName(),
		"getProductFamily", criteriaMap);		   
	    SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
	    if (null != searchResults && searchResults.getSearchResults().size()>0){
	    	  proudctFamily = ((ProductStructureBO)searchResults.getSearchResults().get(0)).getProductFamilyId();
	    }
	  	return proudctFamily;
	  }
	
	
}