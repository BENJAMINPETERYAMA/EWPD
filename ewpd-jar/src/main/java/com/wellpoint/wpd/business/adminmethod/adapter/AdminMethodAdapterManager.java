/**
 * AdminMethodAdapterManager.java 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 * Created on Sep 12, 2007
 */
package com.wellpoint.wpd.business.adminmethod.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.adapter.access.AdapterAccessFactory;
import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchCriteriaImpl;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.framework.bo.AuditFactory;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodAnswerOverrideBO;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodBO;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodContractValidationBO;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodOverrideBO;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodProductValidationBO;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodSPSValidationBO;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodTierOverrideBO;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodValidationBO;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodsPopupBO;
import com.wellpoint.wpd.common.adminmethodmaintain.bo.AdminMethodMaintainBO;
import com.wellpoint.wpd.common.adminmethodmaintain.bo.ConfigurationBO;
import com.wellpoint.wpd.common.adminmethodmaintain.bo.ReferenceGroupBO;
import com.wellpoint.wpd.common.adminmethodmaintain.bo.RequiredParamBO;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitAdministrationBO;
import com.wellpoint.wpd.web.search.util.SearchUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * Adapter manager for admin method
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AdminMethodAdapterManager {

	/**
	 * @param adminMethodMaintainBO
	 * @return
	 * @throws SevereException
	 */
	public boolean createAdminMethod(AdminMethodMaintainBO adminMethodMaintainBO)
			throws SevereException, AdapterException {

		boolean suceess = false;
		try {

			AdapterUtil.performInsert(adminMethodMaintainBO,
					adminMethodMaintainBO.getCreatedUser());

			suceess = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return suceess;
	}

	public boolean createConfiguration(ConfigurationBO configurationBO)
			throws SevereException, AdapterException {

		boolean suceess = false;
		try {

			AdapterUtil.performInsert(configurationBO, configurationBO
					.getCreatedUser());

			suceess = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return suceess;
	}

	/**
	 * @param requiredParamGroupBO
	 * @return
	 */
	public boolean createGroup(ReferenceGroupBO referenceGroupBO) {

		boolean suceess = false;
		try {

			AdapterUtil.performInsert(referenceGroupBO, referenceGroupBO
					.getCreatedUser());

			suceess = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return suceess;
	}

	/**
	 * @param requiredParamBO
	 * @return
	 */
	public boolean createRequiredParam(RequiredParamBO requiredParamBO) {
		boolean suceess = false;
		try {

			AdapterUtil.performInsert(requiredParamBO, requiredParamBO
					.getCreatedUser());

			suceess = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return suceess;
	}

	/**
	 * Method for deleting overriden admin methods.
	 * 
	 * @param overrideBO
	 * @param user
	 * @param adapterServiceAccess
	 * @throws SevereException,AdapterException
	 */
	public void deleteAdminMethodsValidation(
			AdminMethodContractValidationBO adminMethodContractValidationBO,
			String user, AdapterServicesAccess adapterServiceAccess)
			throws SevereException, AdapterException {
		Logger
				.logInfo("Entering the method for deleteOverriddenAdminMethods method");
		try {
			AdapterUtil.performRemove(adminMethodContractValidationBO, user,
					adapterServiceAccess);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		Logger
				.logInfo("Returning the method for deleteOverriddenAdminMethods method");
	}

	/**
	 * Method for deleting overriden admin methods.
	 * 
	 * @param overrideBO
	 * @param user
	 * @param adapterServiceAccess
	 * @throws SevereException,AdapterException
	 */
	public void deleteAdminMethodsValidation(
			AdminMethodProductValidationBO adminMethodProductValidationBO,
			String user, AdapterServicesAccess adapterServiceAccess)
			throws SevereException, AdapterException {
		Logger
				.logInfo("Entering the method for deleteOverriddenAdminMethods method");
		try {
			AdapterUtil.performRemove(adminMethodProductValidationBO, user,
					adapterServiceAccess);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		Logger
				.logInfo("Returning the method for deleteOverriddenAdminMethods method");
	}

	/**
	 * Method for deleting overriden admin methods.
	 * 
	 * @param overrideBO
	 * @param user
	 * @param adapterServiceAccess
	 * @throws SevereException,AdapterException
	 */
	public void deleteAdminMethodsValidation(
			AdminMethodValidationBO adminMethodValidationBO, String user,
			AdapterServicesAccess adapterServiceAccess) throws SevereException,
			AdapterException {
		Logger
				.logInfo("Entering the method for deleteOverriddenAdminMethods method");
		try {
			AdapterUtil.performRemove(adminMethodValidationBO, user,
					adapterServiceAccess);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		Logger
				.logInfo("Returning the method for deleteOverriddenAdminMethods method");
	}

	/**
	 * Method for updating admin methods.
	 * 
	 * @param adminMethodOverrideBO
	 * @param user
	 * @param adapterServiceAccess
	 * @throws SevereException,AdapterException
	 */
	public void updateAdminMethodsValidation(
			AdminMethodValidationBO adminMethodValidationBO, String user,
			AdapterServicesAccess adapterServiceAccess) throws SevereException,
			AdapterException {
		AuditFactory auditFactory = (AuditFactory) com.wellpoint.wpd.business.framework.bo.ObjectFactory
				.getFactory(com.wellpoint.wpd.business.framework.bo.ObjectFactory.AUDIT);
		adminMethodValidationBO.setLastUpdatedTimestamp(auditFactory.getAudit()
				.getTime());
		adminMethodValidationBO.setLastUpdatedUser(auditFactory.getAudit()
				.getUser());
		try {
			AdapterUtil.performUpdate(adminMethodValidationBO, user,
					adapterServiceAccess);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
	}

	/**
	 * Method for inserting admin methods at override.
	 * 
	 * @param overrideBO
	 * @param user
	 * @param adapterServiceAccess
	 * @throws SevereException,AdapterException
	 */
	public void insertOverriddenAdminMethodsValidation(
			AdminMethodValidationBO adminMethodValidationBO, String user,
			AdapterServicesAccess adapterServiceAccess) throws SevereException,
			AdapterException {
		Logger
				.logInfo("Entering the method for insertOverriddenAdminMethods method");
		try {
			AdapterUtil.performInsert(adminMethodValidationBO, user,
					adapterServiceAccess);
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		Logger
				.logInfo("Returning the method for insertOverriddenAdminMethods method");
	}

	/**
	 * Method returns sps names for admin method override.
	 * 
	 * @param adminMethodOverrideBO
	 * @return list
	 * @throws SevereException,AdapterException
	 */
	public List getSPSNamesListForAdminMethodValidation(
			AdminMethodOverrideBO methodOverrideBO) throws SevereException,
			AdapterException {
		HashMap criteriaMap = new HashMap();
		criteriaMap.put(BusinessConstants.ENTITYSYSID, new Integer(
				methodOverrideBO.getEntitySysId()));
		criteriaMap.put(BusinessConstants.ENTITYTYPE, methodOverrideBO
				.getEntityType());

		criteriaMap.put(BusinessConstants.BENEFITCOMID, new Integer(
				methodOverrideBO.getBenefitCompSysId()));

		criteriaMap.put("benefitSysId", new Integer(methodOverrideBO
				.getBenefitSysId()));

		SearchResults searchResults;
		SearchCriteria searchCriteria = null;
		if (methodOverrideBO.getEntityType().equalsIgnoreCase("product")) {
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					AdminMethodOverrideBO.class.getName(),
					"getSPSForValidationForProduct", criteriaMap);
		} else if (methodOverrideBO.getEntityType()
				.equalsIgnoreCase("contract")) {
			criteriaMap.put("contractId", new Integer(methodOverrideBO
					.getContractSysId()));
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					AdminMethodOverrideBO.class.getName(),
					"getSPSForValidationForContract", criteriaMap);
		} else if (methodOverrideBO.getEntityType().equalsIgnoreCase(
				"prodstructure")) {
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					AdminMethodOverrideBO.class.getName(),
					"getSPSForValidationForProductStructure", criteriaMap);
		}

		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}

		return searchResults.getSearchResults();
	}

	public List getAdminMethodsForValidation(
			AdminMethodValidationBO adminMethodValidationBO)
			throws SevereException, AdapterException {

		List SPSList = new ArrayList();
		HashMap criteriaMap = new HashMap();
		String query = "";
		SearchResults searchResults;

		if (adminMethodValidationBO.getEntityType().equalsIgnoreCase("product")) {
			query = "getBenefitAdministrationForProduct";
		} else if (adminMethodValidationBO.getEntityType().equalsIgnoreCase(
				"prodstructure")) {
			query = "getBenefitAdministrationForProductStructure";
		} else if (adminMethodValidationBO.getEntityType().equalsIgnoreCase(
				"contract")) {
			query = "getBenefitAdministrationForContract";
		}
		criteriaMap.put("entitySysId", new Integer(adminMethodValidationBO
				.getEntitySysId()));
		criteriaMap.put("benefitComSysId", new Integer(adminMethodValidationBO
				.getBenefitComSysId()));
		criteriaMap.put("benefitSysId", new Integer(adminMethodValidationBO
				.getBenefitSysId()));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodValidationBO.class.getName(), query, criteriaMap);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}

		if (adminMethodValidationBO.getEntityType()
				.equalsIgnoreCase("contract")
				&& searchResults.getSearchResults().size() > 0) {
			HashSet productFamily = new HashSet();
			Iterator i = searchResults.getSearchResults().iterator();

			while (i.hasNext()) {

				AdminMethodValidationBO adminMethodValidationBO1 = (AdminMethodValidationBO) i
						.next();
				productFamily.add(adminMethodValidationBO1.getProductFamName()
						.toUpperCase());
			}

			// Check if both HMO and PPO is there. If so its POS
			if (productFamily.size() > 0 && productFamily.contains("HMO") && productFamily.contains("PPO")) {
				// Setting only in the first BO.*
				Iterator j = searchResults.getSearchResults().iterator();

				while (j.hasNext()) {
					
					AdminMethodValidationBO adminMethodValidationBO2 = (AdminMethodValidationBO) j
							.next();
					adminMethodValidationBO2.setPosProductFamily(true);

				}
			}
		}

		return searchResults.getSearchResults();
	}

	/**
	 * Method returns admin methods for benefit.
	 * 
	 * @param
	 * adminMethodsPopupBO, qualList,termValue
	 * @return list
	 * @throws SevereException,AdapterException
	 */
	public List getAdminMethods(AdminMethodsPopupBO adminMethodsPopupBO,
			List qualList, String termValue) throws SevereException,
			AdapterException {

		HashMap criteriaMap = new HashMap();
		criteriaMap.put(BusinessConstants.SPSID, new Integer(
				adminMethodsPopupBO.getSpsId()));
		String query = null;
		if (BusinessConstants.BENEFITCOMP.equalsIgnoreCase(adminMethodsPopupBO
				.getEntityType())) {
			query = BusinessConstants.ADMINMETHODFORBC;
			criteriaMap.put(BusinessConstants.BENID, new Integer(
					adminMethodsPopupBO.getBenftId()));
			criteriaMap.put(BusinessConstants.BENCOMID, new Integer(
					adminMethodsPopupBO.getEntityId()));
			criteriaMap.put(BusinessConstants.QUALLIST, qualList);
			criteriaMap.put(BusinessConstants.TERMLIST, termValue);
		} else if (BusinessConstants.STANDARDBENEFIT
				.equalsIgnoreCase(adminMethodsPopupBO.getEntityType())) {
			query = BusinessConstants.ADMINMETHODFORBENEFIT;
			criteriaMap.put(BusinessConstants.BENDEFID, new Integer(
					adminMethodsPopupBO.getStdDefId()));
			criteriaMap.put(BusinessConstants.QUALLIST, qualList);
			criteriaMap.put(BusinessConstants.TERMLIST, termValue);
		} else if (BusinessConstants.PRODUCTSTRUCTURE
				.equalsIgnoreCase(adminMethodsPopupBO.getEntityType())) {
			query = BusinessConstants.ADMINMETHODFORPS;
			criteriaMap.put(BusinessConstants.BENID, new Integer(
					adminMethodsPopupBO.getBenftId()));
			criteriaMap.put(BusinessConstants.BENCOMID, new Integer(
					adminMethodsPopupBO.getBenefitCompId()));
			criteriaMap.put(BusinessConstants.PRODSTRID, new Integer(
					adminMethodsPopupBO.getEntityId()));
			criteriaMap.put(BusinessConstants.QUALLIST, qualList);
			criteriaMap.put(BusinessConstants.TERMLIST, termValue);
		} else if (BusinessConstants.PRODUCT
				.equalsIgnoreCase(adminMethodsPopupBO.getEntityType())) {
			query = BusinessConstants.ADMINEMTHODFORPRODUCT;
			criteriaMap.put(BusinessConstants.BENID, new Integer(
					adminMethodsPopupBO.getBenftId()));
			criteriaMap.put(BusinessConstants.BENCOMID, new Integer(
					adminMethodsPopupBO.getBenefitCompId()));
			criteriaMap.put(BusinessConstants.PRODId, new Integer(
					adminMethodsPopupBO.getEntityId()));
			criteriaMap.put(BusinessConstants.QUALLIST, qualList);
			criteriaMap.put(BusinessConstants.TERMLIST, termValue);
		} else if (BusinessConstants.CONTRACT
				.equalsIgnoreCase(adminMethodsPopupBO.getEntityType())) {
			query = BusinessConstants.ADMINEMTHODFORCONTRACT;
			criteriaMap.put(BusinessConstants.BENID, new Integer(
					adminMethodsPopupBO.getBenftId()));
			criteriaMap.put(BusinessConstants.BENCOMID, new Integer(
					adminMethodsPopupBO.getBenefitCompId()));
			criteriaMap.put(BusinessConstants.DATESEGID, new Integer(
					adminMethodsPopupBO.getContractDateSgmntId()));
			criteriaMap.put(BusinessConstants.QUALLIST, qualList);
			criteriaMap.put(BusinessConstants.TERMLIST, termValue);
			criteriaMap.put(BusinessConstants.PRODId, new Integer(
					adminMethodsPopupBO.getProdId()));
		}
		SearchResults searchResults;

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodsPopupBO.class.getName(), query, criteriaMap);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		return searchResults.getSearchResults();
	}
    //CARS START 
    /**
     * Method returns sps names for admin method override fro data feed.
     * 
     * @param adminMethodOverrideBO
     * @return list
     * @throws SevereException,AdapterException
     */
    public HashMap getSPSNamesListForAdminMethodOverrideForDatafeed(
            AdminMethodOverrideBO adminMethodOverrideBO)
            throws SevereException, AdapterException {
    	HashMap spsAdminMethodListMap = new HashMap();
        HashMap criteriaMap = new HashMap();
        List spsAdminMethodList = new ArrayList();
        List spsAdminMethodTierList = new ArrayList();
        criteriaMap.put(BusinessConstants.ENTITYSYSID, new Integer(
                adminMethodOverrideBO.getEntitySysId()));
        criteriaMap.put(BusinessConstants.ENTITYTYPE, adminMethodOverrideBO
                .getEntityType());
        if (adminMethodOverrideBO.getBnftAdmnId() != 0) {
            criteriaMap.put(BusinessConstants.BNFTADMINID, new Integer(
                    adminMethodOverrideBO.getBnftAdmnId()));
        } else {
            criteriaMap.put(BusinessConstants.BNFTADMINID, null);
        }
        criteriaMap.put(BusinessConstants.BENEFITCOMID, new Integer(
                adminMethodOverrideBO.getBenefitCompSysId()));
        SearchResults searchResults;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                AdminMethodOverrideBO.class.getName(),
                BusinessConstants.SEARCHSPSNAMEFOROVERRIDE, criteriaMap);
        try {
            searchResults = AdapterUtil.performSearch(searchCriteria);
            spsAdminMethodList = searchResults.getSearchResults();
        } catch (Exception ex) {
            throw new AdapterException("Exception occured while adapter call",
                    ex);
        }
        searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                AdminMethodOverrideBO.class.getName(),
                BusinessConstants.SEARCHSPSNAMEFOROVERRIDE_FOR_DATAFEED, criteriaMap);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        spsAdminMethodTierList = searchResults.getSearchResults();
        
        //spsAdminMethodListMap.put("nonTieredAdminMethodList", spsAdminMethodList);
        spsAdminMethodListMap.put("tieredAdminMethodList", spsAdminMethodTierList);
        return spsAdminMethodListMap;
    }    
    
    //CARS:AM2:START{
    public List getSPSNamesListForAdminMethodOverrideInTiers(AdminMethodTierOverrideBO adminMethodOverrideBO)throws SevereException, AdapterException 
	{
        HashMap criteriaMap = new HashMap();
        criteriaMap.put(BusinessConstants.ENTITYSYSID, new Integer(
                adminMethodOverrideBO.getEntitySysId()));
        criteriaMap.put(BusinessConstants.ENTITYTYPE, adminMethodOverrideBO
                .getEntityType());
        if (adminMethodOverrideBO.getBnftAdmnId() != 0) {
            criteriaMap.put(BusinessConstants.BNFTADMINID, new Integer(
                    adminMethodOverrideBO.getBnftAdmnId()));
        } else {
            criteriaMap.put(BusinessConstants.BNFTADMINID, null);
        }
        criteriaMap.put(BusinessConstants.BENEFITCOMID, new Integer(
                adminMethodOverrideBO.getBenefitCompSysId()));

        criteriaMap.put("tierSysId",new Integer(adminMethodOverrideBO.getTierSysId()));
        criteriaMap.put("termQuery",adminMethodOverrideBO.getTermQuery());
  
        SearchResults searchResults;

        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(AdminMethodOverrideBO.class.getName(),BusinessConstants.FETCH_SPS_AM_MAPPINGS_FOR_TIERED_OVERRIDEN, criteriaMap);
        try {
            searchResults = AdapterUtil.performSearch(searchCriteria);
        } catch (Exception ex) {
            throw new AdapterException("Exception occured while adapter call",
                    ex);
        }

        return searchResults.getSearchResults();
    }
    public List getSPSNamesListForAdminMethodOverrideForContract_todel(AdminMethodTierOverrideBO adminMethodOverrideBO)throws SevereException, AdapterException 
	{
        HashMap criteriaMap = new HashMap();
        criteriaMap.put(BusinessConstants.ENTITYSYSID, new Integer(
                adminMethodOverrideBO.getEntitySysId()));
        criteriaMap.put(BusinessConstants.ENTITYTYPE, adminMethodOverrideBO
                .getEntityType());
        if (adminMethodOverrideBO.getBnftAdmnId() != 0) {
            criteriaMap.put(BusinessConstants.BNFTADMINID, new Integer(
                    adminMethodOverrideBO.getBnftAdmnId()));
        } else {
            criteriaMap.put(BusinessConstants.BNFTADMINID, null);
        }
        criteriaMap.put(BusinessConstants.BENEFITCOMID, new Integer(
                adminMethodOverrideBO.getBenefitCompSysId()));

        criteriaMap.put("tierSysId",new Integer(adminMethodOverrideBO.getTierSysId()));
        criteriaMap.put("termQuery",adminMethodOverrideBO.getTermQuery());
        criteriaMap.put("productFamily",adminMethodOverrideBO.getProductFamily());
        SearchResults searchResults;

        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(AdminMethodOverrideBO.class.getName(),"searchSPSNamesForOverrideInTiersForContract", criteriaMap);
        try {
            searchResults = AdapterUtil.performSearch(searchCriteria);
        } catch (Exception ex) {
            throw new AdapterException("Exception occured while adapter call",
                    ex);
        }

        return searchResults.getSearchResults();
    }
    
    //CARS:AM2:END}

    /**
     * Method create admin method
     * 
     * @param adminMethodBO,user,adapterServiceAccess
     * @throws SevereException,AdapterException
     */
    public void createAdminMethod(AdminMethodBO adminMethodBO, String user,
            AdapterServicesAccess adapterServiceAccess) throws SevereException,
            AdapterException {
        Logger.logInfo("Entering the method for saving admin method");
        try {
            AdapterUtil
                    .performInsert(adminMethodBO, user, adapterServiceAccess);
        } catch (Exception ex) {
            throw new AdapterException("Exception occured while adapter call",
                    ex);
        }
    }
	/**
	 * Method returns possible answers.
	 * 
	 * @param spsId
	 * @return List
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public List getPossibleAnswers(int spsId) throws SevereException,
			AdapterException {
		HashMap criteriaMap = new HashMap();
		criteriaMap.put(BusinessConstants.SPSID, new Integer(spsId));
		String query;
		SearchResults searchResults;
		query = BusinessConstants.POSSIBLEANSID;

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodsPopupBO.class.getName(), query, criteriaMap);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		return searchResults.getSearchResults();

	}
    //CARS:AM2:START{
    public void insertOverriddenTieredAdminMethods(AdminMethodTierOverrideBO overrideBO,String user, AdapterServicesAccess adapterServiceAccess)throws SevereException, AdapterException 
	{
        Logger.logInfo("Entering the method for insertOverriddenTieredAdminMethods method");
        try 
		{
            AdapterUtil.performInsert(overrideBO, user, adapterServiceAccess);
        }
        catch (SevereException ex) 
		{
            throw new AdapterException("Exception occured while adapter call",ex);
        }
        Logger.logInfo("Returning the method for insertOverriddenAdminMethods method");
    }
    public void deleteTieredOverriddenAdminMethods(AdminMethodTierOverrideBO overrideBO,
            String user, AdapterServicesAccess adapterServiceAccess)
            throws SevereException, AdapterException {
        Logger
                .logInfo("Entering the method for deleteOverriddenAdminMethods method");
        try {
            AdapterUtil.performRemove(overrideBO, user, adapterServiceAccess);
        } catch (Exception ex) {
            throw new AdapterException("Exception occured while adapter call",
                    ex);
        }
        Logger
                .logInfo("Returning the method for deleteOverriddenAdminMethods method");
    }
    //CARS:AM2:END}
	/**
	 * Method for retrieving question answer list.
	 * 
	 * @param entityId,adminId,entityType,dataSegId,bcName,benCompId
	 * @return List
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public List getQuestionAnswerList(AdminMethodsPopupBO adminMethodsPopupBO)
			throws SevereException, AdapterException {

		HashMap criteriaMap = new HashMap();
		criteriaMap.put(BusinessConstants.ADMINID, new Integer(
				adminMethodsPopupBO.getAdminId()));
		criteriaMap.put(BusinessConstants.ENTITYID, new Integer(
				adminMethodsPopupBO.getEntityId()));
		String query;
		query = BusinessConstants.GETQUESTIONFORBC;
		if (BusinessConstants.BENEFITCOMP.equalsIgnoreCase(adminMethodsPopupBO
				.getEntityType())) {
			query = BusinessConstants.GETQUESTIONFORBC;
		} else if (BusinessConstants.STANDARDBENEFIT
				.equalsIgnoreCase(adminMethodsPopupBO.getEntityType())) {
			query = BusinessConstants.GETQUESTIONFORB;
		} else if (BusinessConstants.PRODUCTSTRUCTURE
				.equalsIgnoreCase(adminMethodsPopupBO.getEntityType())) {
			if (!BusinessConstants.GENERALBENEFITS
					.equalsIgnoreCase(adminMethodsPopupBO
							.getBenefitComponentName()))
				criteriaMap.put(BusinessConstants.BCNAME,
						BusinessConstants.GENERALBENEFITS);
			criteriaMap.put(BusinessConstants.BENCOMID, new Integer(
					adminMethodsPopupBO.getBenefitCompId()));
			criteriaMap.put(BusinessConstants.BENEID, new Integer(
					adminMethodsPopupBO.getBenftId()));
			query = BusinessConstants.GETQUESTIONFORPS;
		} else if (WebConstants.PRODUCT.equalsIgnoreCase(adminMethodsPopupBO
				.getEntityType())) {
			query = BusinessConstants.GETQUESTIONFORP;
			if (!BusinessConstants.GENERALBENEFITS
					.equalsIgnoreCase(adminMethodsPopupBO
							.getBenefitComponentName())) {
				criteriaMap.put(BusinessConstants.BCNAME,
						BusinessConstants.GENERALBENEFITS);
			}
			criteriaMap.put(BusinessConstants.BENCOMID, new Integer(
					adminMethodsPopupBO.getBenefitCompId()));
			criteriaMap.put(BusinessConstants.BENEID, new Integer(
					adminMethodsPopupBO.getBenftId()));
		} else if (WebConstants.CONTRACT_PRODUCT_ADMIN
				.equalsIgnoreCase(adminMethodsPopupBO.getEntityType())) {
			query = BusinessConstants.GETQUESTIONFORC;
			if (!BusinessConstants.GENERALBENEFITS
					.equalsIgnoreCase(adminMethodsPopupBO
							.getBenefitComponentName()))
				criteriaMap.put(BusinessConstants.BCNAME,
						BusinessConstants.GENERALBENEFITS);
			criteriaMap.put(BusinessConstants.DATESEGID, new Integer(
					adminMethodsPopupBO.getContractDateSgmntId()));
			criteriaMap.put(BusinessConstants.BENCOMID, new Integer(
					adminMethodsPopupBO.getBenefitCompId()));
			criteriaMap.put(BusinessConstants.BENEID, new Integer(
					adminMethodsPopupBO.getBenftId()));
		}
		SearchResults searchResults;

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodsPopupBO.class.getName(), query, criteriaMap);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		return searchResults.getSearchResults();

	}

	/**
	 * Method for retrieving admin method list.
	 * 
	 * @param spsId
	 * @return List
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public List getAdminMethodList(int spsId) throws SevereException,
			AdapterException {
		HashMap criteriaMap = new HashMap();
		criteriaMap.put(BusinessConstants.SPSID, new Integer(spsId));
		SearchResults searchResults;

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodsPopupBO.class.getName(),
				BusinessConstants.SEARCHADMINMETHODS, criteriaMap);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		return searchResults.getSearchResults();
	}

	/**
	 * Method returns sps name list.
	 * 
	 * @param adminMethodBO
	 * @param flag
	 * @return list
	 * @throws SevereException,AdapterException
	 */
	public List getSPSNamesList(AdminMethodBO adminMethodBO, boolean flag)
			throws SevereException, AdapterException {
		HashMap criteriaMap = new HashMap();
		criteriaMap.put(BusinessConstants.ADMINISTRATIONID, new Integer(
				adminMethodBO.getAdministrationId()));
		SearchResults searchResults;
		SearchCriteria searchCriteria;

		searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodBO.class.getName(), BusinessConstants.SEARCHSPSNAME,
				criteriaMap);

		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		return searchResults.getSearchResults();
	}

	/**
	 * Method for updating admin methods.
	 * 
	 * @param adminMethodOverrideBO
	 * @param user
	 * @param adapterServiceAccess
	 * @throws SevereException,AdapterException
	 */
	public void updateAdminMethods(AdminMethodOverrideBO adminMethodOverrideBO,
			String user, AdapterServicesAccess adapterServiceAccess)
			throws SevereException, AdapterException {
		AuditFactory auditFactory = (AuditFactory) com.wellpoint.wpd.business.framework.bo.ObjectFactory
				.getFactory(com.wellpoint.wpd.business.framework.bo.ObjectFactory.AUDIT);
		adminMethodOverrideBO.setLastUpdatedTimestamp(auditFactory.getAudit()
				.getTime());
//		adminMethodOverrideBO.setLastUpdatedUser(auditFactory.getAudit()
//				.getUser());
		adminMethodOverrideBO.setLastUpdatedUser(adminMethodOverrideBO.getLastUpdatedUser());
		
		
		try {
			AdapterUtil.performUpdate(adminMethodOverrideBO, user,
					adapterServiceAccess);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
	}
    /*CARS AM2 START */
    public void updateTieredAdminMethods(AdminMethodTierOverrideBO adminMethodOverrideBO,String user, AdapterServicesAccess adapterServiceAccess) throws SevereException, AdapterException 
	{
        AuditFactory auditFactory = (AuditFactory) com.wellpoint.wpd.business.framework.bo.ObjectFactory
                .getFactory(com.wellpoint.wpd.business.framework.bo.ObjectFactory.AUDIT);
        adminMethodOverrideBO.setLastUpdatedTimestamp(auditFactory.getAudit()
                .getTime());
        adminMethodOverrideBO.setLastUpdatedUser(auditFactory.getAudit()
                .getUser());
        try {
            AdapterUtil.performUpdate(adminMethodOverrideBO, user,
                    adapterServiceAccess);
        } catch (Exception ex) {
            throw new AdapterException("Exception occured while adapter call",
                    ex);
        }
    }
    //CARS:AM2:END}
	/**
	 * Method returns sps names for admin method override.
	 * 
	 * @param adminMethodOverrideBO
	 * @return list
	 * @throws SevereException,AdapterException
	 */
	public List getSPSNamesListForAdminMethodOverride(
			AdminMethodOverrideBO adminMethodOverrideBO)
			throws SevereException, AdapterException {

		String productFamilyName = null;
		String baseProductFamily = null;
		SearchResults searchResults = null;

		if (adminMethodOverrideBO.getEntityType().equalsIgnoreCase("contract")) {

			//adminMethodOverrideBO.setBnftAdmnId(0);
			productFamilyName = getProductFamilyName(adminMethodOverrideBO);
			adminMethodOverrideBO.setProductFamily(productFamilyName);

			if (null != productFamilyName && productFamilyName.equalsIgnoreCase("POS")) {

				if (!("General Benefits").equalsIgnoreCase(adminMethodOverrideBO.getBenefitComponentName())) {
					baseProductFamily = getBaseProductFamily(adminMethodOverrideBO);

					if (baseProductFamily == null
							|| ("not answered".equalsIgnoreCase(baseProductFamily))) {
						baseProductFamily = getBaseProductFamilyFromLines(adminMethodOverrideBO); // Answer

						if ((baseProductFamily.equalsIgnoreCase("H") || baseProductFamily
								.equalsIgnoreCase("HMO"))
								|| (baseProductFamily.equalsIgnoreCase("P") || baseProductFamily
										.equalsIgnoreCase("PPO"))) {
							//CALL THE QUERY HMO 2

							if (baseProductFamily.equalsIgnoreCase("H")
									|| baseProductFamily
											.equalsIgnoreCase("HMO")) {
								adminMethodOverrideBO.setProductFamily("HMO");
							} else {
								adminMethodOverrideBO.setProductFamily("PPO");
							}
							searchResults = getHMOPPO_BaseProduct_SpsList(adminMethodOverrideBO);

						} else {
							adminMethodOverrideBO.setProductFamily("POS");
							searchResults = getPOSSpsList(adminMethodOverrideBO); //3
						}
					} else if (baseProductFamily.equalsIgnoreCase("B")) {
						adminMethodOverrideBO.setProductFamily("POS");
						searchResults = getPOSSpsList(adminMethodOverrideBO); //3				

					} else if ((baseProductFamily.equalsIgnoreCase("H") || baseProductFamily
							.equalsIgnoreCase("HMO"))
							|| baseProductFamily.equalsIgnoreCase("P")
							|| baseProductFamily.equalsIgnoreCase("PPO")) {

						if (baseProductFamily.equalsIgnoreCase("H")) {
							adminMethodOverrideBO.setProductFamily("HMO");
						} else {
							adminMethodOverrideBO.setProductFamily("PPO");
						}

						searchResults = getHMOPPO_BaseProduct_SpsList(adminMethodOverrideBO);
					}
				} else {
					searchResults = getPOSSpsList(adminMethodOverrideBO);
				}

			} else if (productFamilyName.equalsIgnoreCase("HMO")) {
				//CALL THE QUERY HMO  1
				searchResults = getHMOPPO_SpsList(adminMethodOverrideBO, "HMO");
			} else {
				searchResults = getHMOPPO_SpsList(adminMethodOverrideBO, "PPO");
			}
		} else {

			HashMap criteriaMap = new HashMap();
			criteriaMap.put(BusinessConstants.ENTITYSYSID, new Integer(
					adminMethodOverrideBO.getEntitySysId()));
			criteriaMap.put(BusinessConstants.ENTITYTYPE, adminMethodOverrideBO
					.getEntityType());
			if (adminMethodOverrideBO.getBnftAdmnId() != 0) {
				criteriaMap.put(BusinessConstants.BNFTADMINID, new Integer(
						adminMethodOverrideBO.getBnftAdmnId()));
			} else {
				criteriaMap.put(BusinessConstants.BNFTADMINID, null);
			}
			criteriaMap.put(BusinessConstants.BENEFITCOMID, new Integer(
					adminMethodOverrideBO.getBenefitCompSysId()));

			SearchCriteria searchCriteria = AdapterUtil
					.getAdapterSearchCriteria(AdminMethodOverrideBO.class
							.getName(),
							BusinessConstants.SEARCHSPSNAMEFOROVERRIDE,
							criteriaMap);
			try {
				searchResults = AdapterUtil.performSearch(searchCriteria);
			} catch (Exception ex) {
				throw new AdapterException(
						"Exception occured while adapter call", ex);
			}
		}
		Iterator i = searchResults.getSearchResults().iterator();
		while (i.hasNext()) {
			AdminMethodOverrideBO adminMethodOverrideBO1 = (AdminMethodOverrideBO) i
					.next();

			boolean isPOS = false;
			if (baseProductFamily != null) {
				if (baseProductFamily.equals("POS")
						|| baseProductFamily.equals("B"))
					isPOS = true;
			} else {
				if (productFamilyName != null
						&& productFamilyName.equals("POS"))
					isPOS = true;
			}
			adminMethodOverrideBO1.setPosProductFamily(isPOS);
		}

		return searchResults.getSearchResults();
	}
    /*CARS|AM2|POS|START*/
	/*  Method for fetching SPS-Admin Method Mapping.
	 * */
	public List fetchSPS_AM_MappingListForOverridenTier(AdminMethodTierOverrideBO adminMethodOverrideBO)throws SevereException, AdapterException 
	{
		String productFamilyName = null;
		String baseProductFamily = null;
		SearchResults searchResults = null;
		
		HashMap criteriaMap = new HashMap();
        criteriaMap.put(BusinessConstants.ENTITYSYSID, new Integer(adminMethodOverrideBO.getEntitySysId()));
        criteriaMap.put(BusinessConstants.ENTITYTYPE, adminMethodOverrideBO.getEntityType());
        if (adminMethodOverrideBO.getBnftAdmnId() != 0) 
        {
            criteriaMap.put(BusinessConstants.BNFTADMINID, new Integer(adminMethodOverrideBO.getBnftAdmnId()));
        }
        else
        {
            criteriaMap.put(BusinessConstants.BNFTADMINID, null);
        }
        criteriaMap.put(BusinessConstants.BENEFITCOMID, new Integer(adminMethodOverrideBO.getBenefitCompSysId()));
        criteriaMap.put("tierSysId",new Integer(adminMethodOverrideBO.getTierSysId()));
        criteriaMap.put("termQuery",adminMethodOverrideBO.getTermQuery());	
        SearchCriteria searchCriteria =null;		
		//For contract only POS is implemented currently(dec 09)
        String prodFamily = "";
		if (BusinessConstants.ENTITY_TYPE_CONTRACT.equalsIgnoreCase(adminMethodOverrideBO.getEntityType())){			
			productFamilyName = getProductFamilyName(adminMethodOverrideBO);
			adminMethodOverrideBO.setProductFamily(productFamilyName);
			if (BusinessConstants.PRODUCT_FAMILY_POS.equalsIgnoreCase(productFamilyName)) {
				if (!BusinessConstants.GENERAL_BENEFITS.equalsIgnoreCase(adminMethodOverrideBO.getBenefitComponentName())) {
					baseProductFamily = getBaseProductFamily(adminMethodOverrideBO);					
					if (baseProductFamily == null || BusinessConstants.NOT_ANSWERED.equalsIgnoreCase(baseProductFamily)) {
						baseProductFamily = getProductFamilyFromTieredLinesForContract(adminMethodOverrideBO); 												
					} 
					if(("H".equalsIgnoreCase(baseProductFamily) || BusinessConstants.PRODUCT_FAMILY_HMO.equalsIgnoreCase(baseProductFamily))) {
						adminMethodOverrideBO.setProductFamily(BusinessConstants.PRODUCT_FAMILY_HMO);
					}
					else if(("P".equalsIgnoreCase(baseProductFamily) || BusinessConstants.PRODUCT_FAMILY_PPO.equalsIgnoreCase(baseProductFamily))) {
						adminMethodOverrideBO.setProductFamily(BusinessConstants.PRODUCT_FAMILY_PPO);
					}	
					else if(BusinessConstants.PRODUCT_FAMILY_POS.equalsIgnoreCase(baseProductFamily) || ("B".equalsIgnoreCase(baseProductFamily))){
						adminMethodOverrideBO.setProductFamily(BusinessConstants.PRODUCT_FAMILY_POS);
					}
					//bug fix PROD00205050:start
					else
					{
						adminMethodOverrideBO.setProductFamily(BusinessConstants.EMPTY_STRING);
					}
					//bug fix PROD00205050:end
				} 
				else {
					adminMethodOverrideBO.setProductFamily(BusinessConstants.PRODUCT_FAMILY_POS);	
				}
			}// EPO,FFS,FSA,IND,MSUP	
			String pf=adminMethodOverrideBO.getProductFamily();
			if(pf.equalsIgnoreCase("EPO") ||pf.equalsIgnoreCase("FFS") ||pf.equalsIgnoreCase("FSA") ||
					pf.equalsIgnoreCase("IND") ||pf.equalsIgnoreCase("MSUP") ||pf.equalsIgnoreCase("PPO")) {
				prodFamily="PPO";
			}
			else if(pf.equalsIgnoreCase("HMO"))	{
				prodFamily="HMO";
			}
			else if(pf.equalsIgnoreCase(BusinessConstants.PRODUCT_FAMILY_POS)) {
				prodFamily="HMO,PPO";
			}
			//bug fix PROD00205050:start
			else if(BusinessConstants.EMPTY_STRING.equalsIgnoreCase(pf))
			{
				prodFamily = productFamilyName.equalsIgnoreCase("POS")?"HMO,PPO":productFamilyName;
				//prodFamily=BusinessConstants.EMPTY_STRING;
			}
			//bug fix PROD00205050:end
			else {
				prodFamily="PPO";
			}						
		} 
		else {
			prodFamily = "PPO";											
		}
		if(!BusinessConstants.EMPTY_STRING.equalsIgnoreCase(prodFamily)){
			try {
				searchCriteria = AdapterUtil.getAdapterSearchCriteria(AdminMethodTierOverrideBO.class.getName(),
						BusinessConstants.FETCH_SPS_AM_MAPPINGS_FOR_TIERED_OVERRIDEN_WITH_PRODUCT_FAMILY,criteriaMap);
				criteriaMap.put(BusinessConstants.POS_PRODUCT_FAMILY, prodFamily);
				searchResults = AdapterUtil.performSearch(searchCriteria);
			} 
			catch (Exception ex){
				throw new AdapterException("Exception occured while adapter call", ex);
			}
		}
		else{
		    new ArrayList(0);	
		}
		/*if (BusinessConstants.ENTITY_TYPE_CONTRACT.equalsIgnoreCase(adminMethodOverrideBO.getEntityType())){	
			Iterator i = searchResults.getSearchResults().iterator();
			List mappingList = new ArrayList(0);
			while (i.hasNext()) {
				AdminMethodTierOverrideBO adminMethodOverrideBOFromDB = (AdminMethodTierOverrideBO) i.next();
				Iterator termIter = adminMethodOverrideBO.getTermPVAMap().keySet().iterator();
				while(termIter.hasNext())
				{
					String termName = (String)termIter.next(); 
					List pvaList = (ArrayList)adminMethodOverrideBO.getTermPVAMap().get(termName);
					Iterator pvaIter = pvaList.iterator();
					while(pvaIter.hasNext()){
						String pvaName = (String)pvaIter.next();
						if(adminMethodOverrideBOFromDB.getTermName().equalsIgnoreCase(termName)
								&& adminMethodOverrideBOFromDB.getProductFamily().equalsIgnoreCase(pvaName)){
							boolean isPOS = false;
							if (baseProductFamily != null) {
								if (BusinessConstants.PRODUCT_FAMILY_POS.equals(baseProductFamily) || "B".equals(baseProductFamily)){
									isPOS = true;	
								}				
						} 
						else {
							if (productFamilyName != null && BusinessConstants.PRODUCT_FAMILY_POS.equals(productFamilyName)) {
								isPOS = true;	
							}				
						}
						adminMethodOverrideBOFromDB.setPosProductFamily(isPOS);		
						mappingList.add(adminMethodOverrideBOFromDB);
			   	    }
			   	}
			}			
		}
		return mappingList;  //searchResults.getSearchResults();
		}
		else {
			return searchResults.getSearchResults();
		}*/
		return searchResults.getSearchResults();
	}
	
	/*CARS|AM2|POS|END*/
	
	/*Method added to fetch tiered AMs for PRODUCT as part of Stabilization 2011*/
	
	public List fetchTieredAMsForProduct(AdminMethodTierOverrideBO adminMethodOverrideBO)throws SevereException, AdapterException 
	{
		SearchResults searchResults = null;
		
		HashMap criteriaMap = new HashMap();
        criteriaMap.put(BusinessConstants.ENTITYSYSID, new Integer(adminMethodOverrideBO.getEntitySysId()));
        criteriaMap.put(BusinessConstants.ENTITYTYPE, adminMethodOverrideBO.getEntityType());
        
        if (adminMethodOverrideBO.getBnftAdmnId() != 0) 
        {
            criteriaMap.put(BusinessConstants.BNFTADMINID, new Integer(adminMethodOverrideBO.getBnftAdmnId()));
        }
        else
        {
            criteriaMap.put(BusinessConstants.BNFTADMINID, null);
        }
        criteriaMap.put(BusinessConstants.BENEFITCOMID, new Integer(adminMethodOverrideBO.getBenefitCompSysId()));
        SearchCriteria searchCriteria =null;		
        try {
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(AdminMethodTierOverrideBO.class.getName(),
					BusinessConstants.FETCH_TIERED_AM_PRODUCT,criteriaMap);
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} 
		catch (Exception ex){
			throw new AdapterException("Exception occured while adapter call", ex);
		}
		return searchResults.getSearchResults();
	}
	
	/*Method added to fetch tiered AMs for PRODUCT as part of Stabilization 2011 - END*/

	/**
	 * @param adminMethodOverrideBO
	 * @return
	 * @throws AdapterException
	 */
	private String getBaseProductFamilyFromLines(
			AdminMethodOverrideBO adminMethodOverrideBO)
			throws AdapterException {
		HashMap criteriaMap = new HashMap();
		criteriaMap.put(BusinessConstants.ENTITYSYSID, new Integer(
				adminMethodOverrideBO.getEntitySysId()));
		criteriaMap.put(BusinessConstants.BENEFITCOMID, new Integer(
				adminMethodOverrideBO.getBenefitCompSysId()));
		criteriaMap.put(BusinessConstants.BENEFITSYSID, new Integer(
				adminMethodOverrideBO.getBenefitSysId()));

		SearchResults searchResults;
		String baseProductFamily = null;

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodOverrideBO.class.getName(),
				BusinessConstants.GETBASEPRODUCTFAMILYFROMLINES, criteriaMap);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}

		List productFamilyList = searchResults.getSearchResults();
		String productFamilyLine = "POS";
		ArrayList pvaLines = new ArrayList();
		if (productFamilyList != null && productFamilyList.size() > 0) {
			for (Iterator iter = productFamilyList.iterator(); iter.hasNext();) {
				AdminMethodOverrideBO adminMethodOverrideBO1 = (AdminMethodOverrideBO) iter
						.next();
				pvaLines.add(adminMethodOverrideBO1.getEntityType());
			}
		}

		if (pvaLines.contains("ALL")) {
			productFamilyLine = "POS";
		} else if (pvaLines.contains("HMO")
				&& (pvaLines.contains("NPAR") || pvaLines.contains("PAR") || pvaLines
						.contains("OPT"))) {
			productFamilyLine = "POS";
		} else if (pvaLines.contains("HMO")) {
			productFamilyLine = "HMO";
		} else if ((pvaLines.contains("NPAR") || pvaLines.contains("PAR") || pvaLines
				.contains("OPT"))) {
			productFamilyLine = "PPO";
		} else {
			productFamilyLine = "POS";
		}

		return productFamilyLine;
	}

	/*CARS|AM2|POS|{*/
	private String getProductFamilyFromTieredLinesForContract(AdminMethodTierOverrideBO adminMethodOverrideBO)throws AdapterException 
	{
		HashMap criteriaMap = new HashMap();
		criteriaMap.put(BusinessConstants.ENTITYSYSID, new Integer(adminMethodOverrideBO.getEntitySysId()));
		criteriaMap.put(BusinessConstants.BENEFITCOMID, new Integer(adminMethodOverrideBO.getBenefitCompSysId()));
		criteriaMap.put(BusinessConstants.BENEFITSYSID, new Integer(adminMethodOverrideBO.getBenefitSysId()));
		criteriaMap.put("tierSysId",new Integer(adminMethodOverrideBO.getTierSysId()));
		SearchResults searchResults;
		String baseProductFamily = null;
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(AdminMethodTierOverrideBO.class.getName(),
				BusinessConstants.FETCH_PROD_FAMILY_FROM_TIERED_LINES_FOR_CONTRACT, criteriaMap);
		try 
		{
			searchResults = AdapterUtil.performSearch(searchCriteria);
		}
		catch (Exception ex) 
		{
			throw new AdapterException("Exception occured while adapter call",ex);
		}
		List productFamilyList = searchResults.getSearchResults();
		String productFamilyLine = "POS";
		ArrayList pvaLines = new ArrayList();
		if (productFamilyList != null && productFamilyList.size() > 0) 
		{
			for (Iterator iter = productFamilyList.iterator(); iter.hasNext();) 
			{				
				AdminMethodOverrideBO adminMethodOverrideBO1 = (AdminMethodOverrideBO) iter	.next();				
				pvaLines.add(adminMethodOverrideBO1.getEntityType());								
			}	
		}
		if (pvaLines.contains("ALL")) 				
		{
			productFamilyLine="POS";				
		}
		else if (pvaLines.contains("HMO") && ( pvaLines.contains("NPAR") || pvaLines.contains("PAR") || pvaLines.contains("OPT")) ) 
		{
			productFamilyLine="POS";
		}
		else if (pvaLines.contains("HMO"))
		{ 
			productFamilyLine="HMO";
		}
		else if ((pvaLines.contains("NPAR") || pvaLines.contains("PAR") || pvaLines.contains("OPT"))) 
		{ 
			productFamilyLine="PPO";
		}
		else
		{
			//bug fix PROD00205050
			//productFamilyLine="POS";
			productFamilyLine="";
		}		
		return productFamilyLine;
	}

	/*CARS|AM2|POS|}*/
	private String getBaseProductFamily(
			AdminMethodOverrideBO adminMethodOverrideBO)
			throws AdapterException {
		HashMap criteriaMap = new HashMap();

		criteriaMap.put(BusinessConstants.ENTITYSYSID, new Integer(
				adminMethodOverrideBO.getEntitySysId()));

		if (adminMethodOverrideBO.getBnftAdmnId() != 0) {
			criteriaMap.put(BusinessConstants.BNFTADMINID, new Integer(
					adminMethodOverrideBO.getBnftAdmnId()));
		} else {
			criteriaMap.put(BusinessConstants.BNFTADMINID, null);
		}

		criteriaMap.put(BusinessConstants.BENEFITCOMID, new Integer(
				adminMethodOverrideBO.getBenefitCompSysId()));

		SearchResults searchResults;
		String baseProductFamily = null;

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodOverrideBO.class.getName(),
				BusinessConstants.GETBASEPRODUCTFAMILY, criteriaMap);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}

		List productFamilyList = searchResults.getSearchResults();
		if (productFamilyList != null && productFamilyList.size() > 0) {
			for (Iterator iter = productFamilyList.iterator(); iter.hasNext();) {
				AdminMethodOverrideBO adminMethodOverrideBO1 = (AdminMethodOverrideBO) iter
						.next();
				baseProductFamily = adminMethodOverrideBO1.getEntityType();
			}
		}
		return baseProductFamily;
	}

	/**
	 * @param adminMethodOverrideBO
	 * @return
	 * @throws AdapterException
	 */
	private SearchResults getPOSSpsList(AdminMethodOverrideBO adminMethodOverrideBO)throws AdapterException 
	{
		HashMap criteriaMap = new HashMap();
		SearchResults searchResults;
		criteriaMap.put(BusinessConstants.ENTITYSYSID, new Integer(
				adminMethodOverrideBO.getEntitySysId()));
		criteriaMap.put(BusinessConstants.ENTITYTYPE, adminMethodOverrideBO
				.getEntityType());

		if (adminMethodOverrideBO.getBnftAdmnId() != 0) {
			criteriaMap.put(BusinessConstants.BNFTADMINID, new Integer(
					adminMethodOverrideBO.getBnftAdmnId()));
		} else {
			criteriaMap.put(BusinessConstants.BNFTADMINID, null);
		}
		criteriaMap.put(BusinessConstants.BENEFITCOMID, new Integer(
				adminMethodOverrideBO.getBenefitCompSysId()));

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodOverrideBO.class.getName(),
				BusinessConstants.SEARCHSPSNAMEFORPOSOVERRIDE, criteriaMap);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		return searchResults;
	}

	private SearchResults getHMOPPO_SpsList(
			AdminMethodOverrideBO adminMethodOverrideBO,
			String productFamilyName) throws AdapterException {
		HashMap criteriaMap = new HashMap();

		SearchResults searchResults;
		criteriaMap.put(BusinessConstants.ENTITYSYSID, new Integer(
				adminMethodOverrideBO.getEntitySysId()));

		criteriaMap.put(BusinessConstants.ENTITYTYPE, adminMethodOverrideBO
				.getEntityType());

		if (adminMethodOverrideBO.getBnftAdmnId() != 0) {
			criteriaMap.put(BusinessConstants.BNFTADMINID, new Integer(
					adminMethodOverrideBO.getBnftAdmnId()));
		} else {
			criteriaMap.put(BusinessConstants.BNFTADMINID, null);
		}
		criteriaMap.put(BusinessConstants.BENEFITCOMID, new Integer(
				adminMethodOverrideBO.getBenefitCompSysId()));

		criteriaMap.put(BusinessConstants.POS_PRODUCT_FAMILY, new String(
				productFamilyName));

		//   criteriaMap.put(BusinessConstants.BENEFIT_DATE_SEGEMENT_ID, new Integer(adminMethodOverrideBO.get()));

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodOverrideBO.class.getName(),
				BusinessConstants.SEARCHSPSNAMEFORHMOOVERRIDE, criteriaMap);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		return searchResults;
	}

	private SearchResults getHMOPPO_BaseProduct_SpsList(
			AdminMethodOverrideBO adminMethodOverrideBO)
			throws AdapterException {
		HashMap criteriaMap = new HashMap();
		SearchResults searchResults;

		criteriaMap.put(BusinessConstants.ENTITYSYSID, new Integer(
				adminMethodOverrideBO.getEntitySysId()));
		criteriaMap.put(BusinessConstants.ENTITYTYPE, adminMethodOverrideBO
				.getEntityType());

		if (adminMethodOverrideBO.getBnftAdmnId() != 0) {
			criteriaMap.put(BusinessConstants.BNFTADMINID, new Integer(
					adminMethodOverrideBO.getBnftAdmnId()));
		} else {
			criteriaMap.put(BusinessConstants.BNFTADMINID, null);
		}

		criteriaMap.put(BusinessConstants.BENEFITCOMID, new Integer(
				adminMethodOverrideBO.getBenefitCompSysId()));

		criteriaMap.put(BusinessConstants.POS_PRODUCT_FAMILY, new String(
				adminMethodOverrideBO.getProductFamily()));

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodOverrideBO.class.getName(),
				BusinessConstants.SEARCHSPSNAMEFORBASEPRODUCTFAMILY,
				criteriaMap);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		return searchResults;
	}
    
	/**
	 * Method to get Product Family Name
	 * @param entitySysId
	 * @return
	 * @throws AdapterException
	 */

	private String getProductFamilyName(
			AdminMethodOverrideBO adminMethodOverrideBO)
			throws AdapterException {

		HashMap criteriaMap = new HashMap();
		criteriaMap.put(BusinessConstants.ENTITYSYSID, new Integer(
				adminMethodOverrideBO.getEntitySysId()));
		SearchResults searchResults;
		String productFamilyName = null;

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodOverrideBO.class.getName(),
				BusinessConstants.GETPRODUCTFAMILYNAME, criteriaMap);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}

		List productFamilyList = searchResults.getSearchResults();
		if (productFamilyList != null && productFamilyList.size() > 0) {
			for (Iterator iter = productFamilyList.iterator(); iter.hasNext();) {
				AdminMethodOverrideBO adminMethodOverrideBO1 = (AdminMethodOverrideBO) iter
						.next();
				productFamilyName = adminMethodOverrideBO1.getEntityType();
			}
		}
		return productFamilyName;

	}

	/**
	 * Update AdminMethod for Benefit
	 * 
	 * @param adminMethodBO
	 *            AdminMethodBO object.
	 * @param adapterServiceAccess
	 * @param user
	 * @throws SevereException,AdapterException
	 */
	public void updateAdminMethod(AdminMethodBO adminMethodBO, String user,
			AdapterServicesAccess adapterServiceAccess) throws SevereException,
			AdapterException {
		Logger.logInfo("Entering the method for updating admin method");
		try {
			AdapterUtil
					.performUpdate(adminMethodBO, user, adapterServiceAccess);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		Logger.logInfo("Returning the method for updating admin method");
	}

	/**
	 * Method for inserting admin methods at override.
	 * 
	 * @param overrideBO
	 * @param user
	 * @param adapterServiceAccess
	 * @throws SevereException,AdapterException
	 */
	public void insertOverriddenAdminMethods(AdminMethodOverrideBO overrideBO,
			String user, AdapterServicesAccess adapterServiceAccess)
			throws SevereException, AdapterException {
		Logger
				.logInfo("Entering the method for insertOverriddenAdminMethods method");
		try {
			AdapterUtil.performInsert(overrideBO, user, adapterServiceAccess);
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		Logger
				.logInfo("Returning the method for insertOverriddenAdminMethods method");
	}

	/**
	 * Method for deleting overriden admin methods.
	 * 
	 * @param overrideBO
	 * @param user
	 * @param adapterServiceAccess
	 * @throws SevereException,AdapterException
	 */
	public void deleteOverriddenAdminMethods(AdminMethodOverrideBO overrideBO,
			String user, AdapterServicesAccess adapterServiceAccess)
			throws SevereException, AdapterException {
		Logger
				.logInfo("Entering the method for deleteOverriddenAdminMethods method");
		try {
			AdapterUtil.performRemove(overrideBO, user, adapterServiceAccess);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		Logger
				.logInfo("Returning the method for deleteOverriddenAdminMethods method");
	}

	/**
	 * Method for deleting admin methods.
	 * 
	 * @param adminMethodBO
	 * @param user
	 * @param adapterServiceAccess
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public void deleteAdminMethods(AdminMethodBO adminMethodBO, String user,
			AdapterServicesAccess adapterServiceAccess) throws SevereException,
			AdapterException {
		Logger.logInfo("Entering the method for deleteAdminMethods method");
		try {
			AdapterUtil
					.performRemove(adminMethodBO, user, adapterServiceAccess);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		Logger.logInfo("Returning the method for deleteAdminMethods method");
	}

	/**
	 * Method to get benefit administration for check in validation .
	 * 
	 * @param benefitSysId
	 * @return list
	 * @throws SevereException,AdapterException
	 */
	public List getBenefitAdministration(int benefitSysId)
			throws SevereException, AdapterException {
		Logger.logInfo("Entering the method for saving admin method");
		HashMap criteriaMap = new HashMap();
		criteriaMap.put(BusinessConstants.STANDARDBENEFITKEY, new Integer(
				benefitSysId));
		SearchResults searchResults;

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				BenefitAdministrationBO.class.getName(),
				BusinessConstants.GETBENEFITADMINISTRATION, criteriaMap);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		return searchResults.getSearchResults();
	}

	/**
	 * Method to get benefit administration method for benefit for check in
	 * validation .
	 * 
	 * @param adminId
	 * @return list
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public List getAssociatedAdminMethod(int adminId) throws SevereException,
			AdapterException {
		Logger.logInfo("Entering the method for saving admin method");
		HashMap criteriaMap = new HashMap();
		criteriaMap.put(BusinessConstants.ADMINISTRATIONID,
				new Integer(adminId));
		SearchResults searchResults;

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodBO.class.getName(),
				BusinessConstants.SEARCHBENEFITADMIN, criteriaMap);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		return searchResults.getSearchResults();
	}

	/**
	 * Method to get benefit Component for check in validation .
	 * 
	 * @param benefitComponentId
	 * @return list
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public List getBenefitIds(int benefitComponentId) throws SevereException,
			AdapterException {
		HashMap benefitComponentMap = new HashMap();
		benefitComponentMap.put(BusinessConstants.BENEFITCOMID, Integer
				.toString(benefitComponentId));
		SearchCriteria benefitComponentSearchCriteria = new SearchCriteriaImpl();
		benefitComponentSearchCriteria
				.setReferenceValueSet(benefitComponentMap);
		benefitComponentSearchCriteria
				.setBusinessObjectName(BusinessConstants.TREE_BENEFIT_COMPONENT);
		benefitComponentSearchCriteria
				.setSearchQueryName(BusinessConstants.RETRIEVEBYBC);
		benefitComponentSearchCriteria
				.setMaxSearchResultSize(Integer.MAX_VALUE);
		benefitComponentSearchCriteria
				.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
		List benefitList = null;
		AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
				.getAdapterServicesAccess();
		try {
			benefitList = adapterServicesAccess.searchObject(
					benefitComponentSearchCriteria).getSearchResults();
		} catch (AdapterException adapterException) {
			throw new ServiceException(BusinessConstants.ADAPTER_EXCEPTION,
					null, adapterException);
		} catch (Exception ex) {
			throw new ServiceException(BusinessConstants.ADAPTER_EXCEPTION,
					null, ex);

		}
		return benefitList;
	}

	/**
	 * Method to get count of admin process in benefit for validation
	 * 
	 * @param adminMethodOverrideBO
	 * @return list
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public List getSPSNamesListForAdminMethodOverrideVAlidation(
			AdminMethodOverrideBO adminMethodOverrideBO)
			throws SevereException, AdapterException {
		HashMap criteriaMap = new HashMap();
		criteriaMap.put(BusinessConstants.ENTITYSYSID, new Integer(
				adminMethodOverrideBO.getEntitySysId()));
		criteriaMap.put(BusinessConstants.ENTITYTYPE, adminMethodOverrideBO
				.getEntityType());
		if (adminMethodOverrideBO.getBnftAdmnId() != 0) {
			criteriaMap.put(BusinessConstants.BNFTADMINID, new Integer(
					adminMethodOverrideBO.getBnftAdmnId()));
		} else {
			criteriaMap.put(BusinessConstants.BNFTADMINID, null);
		}
		criteriaMap.put(BusinessConstants.BENEFITCOMID, new Integer(
				adminMethodOverrideBO.getBenefitCompSysId()));

		SearchResults searchResults;

		SearchCriteria searchCriteria;
		if (!"contract".equalsIgnoreCase(adminMethodOverrideBO.getEntityType()))
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					AdminMethodOverrideBO.class.getName(),
					BusinessConstants.SEARCHSPSNAMEFORVALIDATION, criteriaMap);

		else
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					AdminMethodOverrideBO.class.getName(),
					BusinessConstants.SEARCHSPSNAMEFORCNTRVALIDATION,
					criteriaMap);

		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		return searchResults.getSearchResults();
	}

	/**
	 * Method to get benefit administration for check in validation .
	 * 
	 * @param benefitSysId,benefitComponentId
	 * @return list
	 * @throws SevereException,AdapterException
	 */
	public List getBenefitAdministrationFromBC(int benefitSysId,
			int benefitComponentId) throws SevereException, AdapterException {
		Logger.logInfo("Entering the method for saving admin method");
		HashMap criteriaMap = new HashMap();
		criteriaMap.put(BusinessConstants.STANDARDBENEFITKEY, new Integer(
				benefitSysId));
		criteriaMap.put(BusinessConstants.BENEFITCOMID, new Integer(
				benefitComponentId));

		SearchResults searchResults;

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				BenefitAdministrationBO.class.getName(),
				BusinessConstants.GETBENEFITADMINISTRATIONFROMBC, criteriaMap);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		return searchResults.getSearchResults();
	}

	/**
	 * Method to get sps name
	 * 
	 * @param spsId
	 * @return String
	 * @throws SevereException,AdapterException
	 */
	public String getSpsName(int spsId) throws SevereException,
			AdapterException {
		HashMap criteriaMap = new HashMap();
		criteriaMap.put(BusinessConstants.SPSID, new Integer(spsId));
		SearchResults searchResults;

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodsPopupBO.class.getName(),
				BusinessConstants.GETSPSNAME, criteriaMap);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}

		return null != searchResults.getSearchResults()
				&& searchResults.getSearchResults().isEmpty() ? null
				: ((AdminMethodsPopupBO) searchResults.getSearchResults()
						.get(0)).getSpsName();
	}

	/**
	 * Method to get answer list from criteria
	 * 
	 * @param spsId,answerId
	 * @return list
	 * @throws SevereException,AdapterException
	 */
	public List getAnswerListFromCriteria(String answerId, int spsId)
			throws SevereException, AdapterException {
		HashMap criteriaMap = new HashMap();

		SearchResults searchResults;

		criteriaMap.put(BusinessConstants.ANSWERLIST, SearchUtil
				.createQueryStringForSelectedPsblAnswer(answerId));
		criteriaMap.put(BusinessConstants.SPSID, new Integer(spsId));

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodsPopupBO.class.getName(),
				BusinessConstants.GETANSWERLIST, criteriaMap);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}

		return searchResults.getSearchResults();

	}

	/**
	 * Method to get qualifiers
	 * 
	 * @param
	 * adminMethodPopupBO,terms,benDefnId
	 * @return list
	 * @throws SevereException,AdapterException
	 */
	public List getQualifiers(AdminMethodsPopupBO adminMethodsPopupBO,
			String terms, int benDefnId) throws SevereException,
			AdapterException {
		HashMap criteriaMap = new HashMap();
		String query = null;
		if (BusinessConstants.BENEFITCOMP.equalsIgnoreCase(adminMethodsPopupBO
				.getEntityType())) {
			query = BusinessConstants.GETQUALIFIERFORBC;
			criteriaMap.put(BusinessConstants.BENSYSID, new Integer(
					adminMethodsPopupBO.getBenftId()));
			criteriaMap.put(BusinessConstants.TERMLIST, terms);
			criteriaMap.put(BusinessConstants.BENCOMID, new Integer(
					adminMethodsPopupBO.getEntityId()));
		} else if (BusinessConstants.STANDARDBENEFIT
				.equalsIgnoreCase(adminMethodsPopupBO.getEntityType())) {
			query = BusinessConstants.GETQUALIFIERFORB;
			criteriaMap.put(BusinessConstants.TERMLIST, terms);
			criteriaMap.put(BusinessConstants.BENDEFID, new Integer(benDefnId));
		} else if (BusinessConstants.PRODUCTSTRUCTURE
				.equalsIgnoreCase(adminMethodsPopupBO.getEntityType())) {
			query = BusinessConstants.GETQUALIFIERFORPS;
			criteriaMap.put(BusinessConstants.BENSYSID, new Integer(
					adminMethodsPopupBO.getBenftId()));
			criteriaMap.put(BusinessConstants.TERMLIST, terms);
			criteriaMap.put(BusinessConstants.BENCOMID, new Integer(
					adminMethodsPopupBO.getBenefitCompId()));
			criteriaMap.put(BusinessConstants.ENTITY_ID, new Integer(
					adminMethodsPopupBO.getEntityId()));
		} else if (BusinessConstants.PRODUCT
				.equalsIgnoreCase(adminMethodsPopupBO.getEntityType())) {
			query = BusinessConstants.GETQUALIFIERFORP;
			criteriaMap.put(BusinessConstants.BENSYSID, new Integer(
					adminMethodsPopupBO.getBenftId()));
			criteriaMap.put(BusinessConstants.TERMLIST, terms);
			criteriaMap.put(BusinessConstants.BENCOMID, new Integer(
					adminMethodsPopupBO.getBenefitCompId()));
			criteriaMap.put(BusinessConstants.ENTITY_ID, new Integer(
					adminMethodsPopupBO.getEntityId()));
		} else if (BusinessConstants.CONTRACT
				.equalsIgnoreCase(adminMethodsPopupBO.getEntityType())) {
			query = BusinessConstants.GETQUALIFIERFORC;
			criteriaMap.put(BusinessConstants.BENSYSID, new Integer(
					adminMethodsPopupBO.getBenftId()));
			criteriaMap.put(BusinessConstants.TERMLIST, terms);
			criteriaMap.put(BusinessConstants.BENCOMID, new Integer(
					adminMethodsPopupBO.getBenefitCompId()));
			criteriaMap.put(BusinessConstants.DATESEGID, new Integer(
					adminMethodsPopupBO.getContractDateSgmntId()));
			criteriaMap.put(BusinessConstants.ENTITY_ID, new Integer(
					adminMethodsPopupBO.getProdId()));
		}
		SearchResults searchResults;

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodsPopupBO.class.getName(), query, criteriaMap);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		return searchResults.getSearchResults();
	}

	/**
	 * Method to get terms
	 * 
	 * @param spsId
	 * @return list
	 * @throws SevereException,AdapterException
	 */
	public List getTerms(int spsId) throws SevereException, AdapterException {
		HashMap criteriaMap = new HashMap();
		criteriaMap.put(BusinessConstants.SPSID, new Integer(spsId));
		String query;
		query = BusinessConstants.GETTERM;
		SearchResults searchResults;

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodsPopupBO.class.getName(), query, criteriaMap);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		return searchResults.getSearchResults();
	}

	/**
	 * Method to get terms from entity
	 * 
	 * @param spsId
	 * @return list
	 * @throws SevereException,AdapterException
	 */
	public List getTermsFromEntity(AdminMethodsPopupBO adminMethodsPopupBO)
			throws SevereException, AdapterException {
		HashMap criteriaMap = new HashMap();
		String query = null;
		if (BusinessConstants.BENEFITCOMP.equalsIgnoreCase(adminMethodsPopupBO
				.getEntityType())) {
			query = BusinessConstants.GETTERMFROMBENEFITCOMP;
			criteriaMap.put(BusinessConstants.BENSYSID, new Integer(
					adminMethodsPopupBO.getBenftId()));
			criteriaMap.put(BusinessConstants.BENCOMID, new Integer(
					adminMethodsPopupBO.getEntityId()));
		} else if (BusinessConstants.STANDARDBENEFIT
				.equalsIgnoreCase(adminMethodsPopupBO.getEntityType())) {
			query = BusinessConstants.GETTERMFROMBENEFIT;
			criteriaMap.put(BusinessConstants.BENDEFID, new Integer(
					adminMethodsPopupBO.getStdDefId()));
		} else if (BusinessConstants.PRODUCTSTRUCTURE
				.equalsIgnoreCase(adminMethodsPopupBO.getEntityType())) {
			query = BusinessConstants.GETTERMFROMPRODUCTSTRUCT;
			criteriaMap.put(BusinessConstants.BENSYSID, new Integer(
					adminMethodsPopupBO.getBenftId()));
			criteriaMap.put(BusinessConstants.BENCOMID, new Integer(
					adminMethodsPopupBO.getBenefitCompId()));
			criteriaMap.put(BusinessConstants.ENTITY_ID, new Integer(
					adminMethodsPopupBO.getEntityId()));
		} else if (BusinessConstants.PRODUCT
				.equalsIgnoreCase(adminMethodsPopupBO.getEntityType())) {
			query = BusinessConstants.GETTERMFROMPRODUCT;
			criteriaMap.put(BusinessConstants.BENSYSID, new Integer(
					adminMethodsPopupBO.getBenftId()));
			criteriaMap.put(BusinessConstants.BENCOMID, new Integer(
					adminMethodsPopupBO.getBenefitCompId()));
			criteriaMap.put(BusinessConstants.ENTITY_ID, new Integer(
					adminMethodsPopupBO.getEntityId()));
		} else if (BusinessConstants.CONTRACT
				.equalsIgnoreCase(adminMethodsPopupBO.getEntityType())) {
			query = BusinessConstants.GETTERMFROMCONTRACT;
			criteriaMap.put(BusinessConstants.BENSYSID, new Integer(
					adminMethodsPopupBO.getBenftId()));
			criteriaMap.put(BusinessConstants.BENCOMID, new Integer(
					adminMethodsPopupBO.getBenefitCompId()));
			criteriaMap.put(BusinessConstants.DATESEGID, new Integer(
					adminMethodsPopupBO.getContractDateSgmntId()));
			criteriaMap.put(BusinessConstants.ENTITY_ID, new Integer(
					adminMethodsPopupBO.getProdId()));
		}
		SearchResults searchResults;

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodsPopupBO.class.getName(), query, criteriaMap);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		return searchResults.getSearchResults();
	}

	/**
	 * Method to get answer override value
	 * 
	 * @param adminMethodOverrideBO
	 * @return list
	 * @throws SevereException,AdapterException
	 */
	public List getAnswerOverrideValue(
			AdminMethodAnswerOverrideBO adminMethodOverrideBO)
			throws SevereException, AdapterException {

		HashMap criteriaMap = new HashMap();
		criteriaMap.put(BusinessConstants.ENTITYSYSID, new Integer(
				adminMethodOverrideBO.getEntitySysId()));
		criteriaMap.put(BusinessConstants.ENTITYTYPE, adminMethodOverrideBO
				.getEntityType());
		criteriaMap.put(BusinessConstants.BENEFITCOMID, new Integer(
				adminMethodOverrideBO.getBenefitCompSysId()));

		SearchResults searchResults;

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodAnswerOverrideBO.class.getName(),
				BusinessConstants.GETANSWEROVERIDEVALUES, criteriaMap);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}

		return searchResults.getSearchResults();
	}

	/**
	 * Method to get answer override value for benefit
	 * 
	 * @param adminMethodOverrideBO
	 * @return list
	 * @throws SevereException,AdapterException
	 */
	public List getAnswerOverrideValueForBenefit(
			AdminMethodAnswerOverrideBO adminMethodOverrideBO)
			throws SevereException, AdapterException {

		HashMap criteriaMap = new HashMap();
		criteriaMap.put(BusinessConstants.ENTITYSYSID, new Integer(
				adminMethodOverrideBO.getEntitySysId()));
		criteriaMap.put(BusinessConstants.ENTITYTYPE, adminMethodOverrideBO
				.getEntityType());
		criteriaMap.put(BusinessConstants.BENEFITCOMID, new Integer(
				adminMethodOverrideBO.getBenefitCompSysId()));
		criteriaMap.put(BusinessConstants.ADMINID, new Integer(
				adminMethodOverrideBO.getAdminId()));
		criteriaMap.put(BusinessConstants.DEFID, new Integer(
				adminMethodOverrideBO.getDefId()));
		SearchResults searchResults;

		SearchCriteria searchCriteria = AdapterUtil
				.getAdapterSearchCriteria(AdminMethodAnswerOverrideBO.class
						.getName(),
						BusinessConstants.GETANSWEROVERIDEVALUESFORBENEFIT,
						criteriaMap);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}

		return searchResults.getSearchResults();
	}

	/**
	 * Method to get sps ids
	 * 
	 * @param answerIds
	 * @return list
	 * @throws SevereException,AdapterException
	 */
	public List getspsIds(List answerIds) throws SevereException,
			AdapterException {

		HashMap criteriaMap = new HashMap();
		criteriaMap.put(BusinessConstants.ANSWERIDS, answerIds);
		SearchResults searchResults;

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodAnswerOverrideBO.class.getName(),
				BusinessConstants.GETSPSIDS, criteriaMap);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}

		return searchResults.getSearchResults();
	}

	/**
	 * Method to get admin methods
	 * 
	 * @param spsIds,adminId,benefitComponentId,entityId
	 * @return list
	 * @throws SevereException,AdapterException
	 */
	public List getAdminMethods(List spsIds, int adminId,
			int benefitComponentId, int entityId) throws SevereException,
			AdapterException {

		HashMap criteriaMap = new HashMap();
		criteriaMap.put(BusinessConstants.BENEFITCOMID, new Integer(
				benefitComponentId));
		criteriaMap.put(BusinessConstants.ADMINID, new Integer(adminId));
		criteriaMap.put(BusinessConstants.SPSID, spsIds);
		criteriaMap.put(BusinessConstants.ENTITYSYSID, new Integer(entityId));

		SearchResults searchResults;

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodAnswerOverrideBO.class.getName(),
				BusinessConstants.GETADMINMETHODS, criteriaMap);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}

		return searchResults.getSearchResults();
	}

	public static final String TYPE_PRODUCT = "PRODUCT";

	public static final String TYPE_PRODUCT_STRUCTURE = "PRODUCT STRUCTURE";

	public static final String TYPE_CONTRACT = "CONTRACT";
    
    public boolean validateChangedAdminMethods(AdminMethodBO methodBO, 
    		List changedIds,List changedTiers, List changedTierSysIds, String processType) throws AdapterException{
    	List messageList = null;
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("chType",methodBO.getChangeType());
		criteriaMap.put("chTypeIds",getCommaSeparatedIds(changedIds));
		//CARS start
    	boolean flag = true;
        if(null != changedTiers){
              HashSet tierHashSet = new HashSet(changedTiers);
              changedTiers.clear();
              changedTiers.addAll(tierHashSet);
        }
        if(null != changedTierSysIds){
              HashSet tierIdHashSet = new HashSet(changedTierSysIds);
              changedTierSysIds.clear();
              changedTierSysIds.addAll(tierIdHashSet);
        }
		criteriaMap.put("changedTiers",getCommaSeparatedIds(changedTiers));//CARS
		criteriaMap.put("changedTierSysIds",getCommaSeparatedIds(changedTierSysIds));//CARS
		//CARS end
		criteriaMap.put("benefitComponentId",new Integer(methodBO.getBenefitCompSysId()));
		criteriaMap.put("benefitId",new Integer(methodBO.getBenSysId()));
		//criteriaMap.put("entityId",new Integer(methodBO.getEntitySysId()));
		criteriaMap.put("processtype", processType);
		if (methodBO.isQuestionProduct())
			criteriaMap.put("quesProdFlag", new Integer(1));
		else
			criteriaMap.put("quesProdFlag", new Integer(0));
		criteriaMap.put("affectedbcomp", getCommaSeparatedIds(methodBO
				.getAffectedBenComps()));
		criteriaMap.put("affectedb", getCommaSeparatedIds(methodBO
				.getAffectedBenefits()));
		criteriaMap.put("administrationId", new Integer(methodBO
				.getAdministrationId()));

		SearchResults searchResults;
		SearchCriteria searchCriteria = null;

		if (TYPE_PRODUCT.equalsIgnoreCase(methodBO.getEntityType())) {
			flag = false;
			/*criteriaMap.put("entityId", new Integer(methodBO.getEntitySysId()));
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					AdminMethodBO.class.getName(),
					"validateChangesInAdminMethodsForProduct", criteriaMap);*/
		} else if (TYPE_PRODUCT_STRUCTURE.equalsIgnoreCase(methodBO
				.getEntityType())) {
			criteriaMap.put("entityId", new Integer(methodBO.getEntitySysId()));
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					AdminMethodBO.class.getName(),
					"validateChangesInAdminMethodsForProductStructure",
					criteriaMap);//validateAdminMethods
		} else if (TYPE_CONTRACT.equalsIgnoreCase(methodBO.getEntityType())) {
			criteriaMap.put("entityId", new Integer(methodBO.getEntitySysId()));
			criteriaMap.put("prodSysId", new Integer(methodBO.getProductId()));
			criteriaMap.put("dateSgmntId", new Integer(methodBO
					.getDateSgmntId()));
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					AdminMethodBO.class.getName(),
					"validateChangesInAdminMethodsForContract", criteriaMap);//validateAdminMethods
		}
		try {
			if(flag)
				searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}

		/*if(null != searchResults.getSearchResults()  && !searchResults.getSearchResults().isEmpty() 
		 ){
		 messageList = searchResults.getSearchResults();
		 }else{
		 throw new AdapterException("results empty, Data issue....",null);
		 }*/

		return true;

	}

	List messageList = null;

	public boolean validateChangedAdminMethods(int contractSysId)
			throws AdapterException {
		HashMap criteriaMap = new HashMap();
		SearchResults searchResults;
		SearchCriteria searchCriteria = null;

		criteriaMap.put("entityId", new Integer(contractSysId));
		searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodBO.class.getName(),
				"validateAdminMethodsForContract", criteriaMap);//validateAdminMethods

		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		return true;
	}

	/**
	 * @param changedIds
	 * @return
	 */
	private String getCommaSeparatedIds(List changedIds) {
		if (null != changedIds && changedIds.size() > 0) {
			StringBuffer buffer = new StringBuffer();
			Iterator iterator = changedIds.iterator();
			while (iterator.hasNext()) {
				buffer.append(iterator.next());
				if (iterator.hasNext())
					buffer.append(",");
			}
			return buffer.toString();
		}
		return " ";
	}
	public void insertValuesIntoAffectedSps(int dateSegmentId, boolean validateTiers){
		
		List messageList = null;
		HashMap criteriaMap = new HashMap();
		criteriaMap.put(BusinessConstants.ENTITYID, new Integer(dateSegmentId));//entityId
		
		if(validateTiers)
			criteriaMap.put("chType", "VALIDATE_TIER");//chType
		else
			criteriaMap.put("chType", "DO_NOT_VALIDATE_TIER");//chType

		SearchResults searchResults;

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodBO.class.getName(), "insertIntoAffectedSPS",
				criteriaMap);//validateAdminMethods
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException ex) {
			ex.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 * @param entitySysId
	 * @return
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public List validateAdminMethod(int entitySysId) throws SevereException,
			AdapterException {

		List messageList = null;
		HashMap criteriaMap = new HashMap();
		criteriaMap.put(BusinessConstants.ENTITYID, new Integer(entitySysId));//entityId

		SearchResults searchResults;

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodsPopupBO.class.getName(), "validateAdminMethods",
				criteriaMap);//validateAdminMethods
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}

		if (!searchResults.getSearchResults().isEmpty()
				&& null != searchResults.getSearchResults()) {
			messageList = searchResults.getSearchResults();
		}

		return messageList;
	}

	/**
	 * @param details
	 * @return
	 * @throws SevereException
	 */
	public List getBenefitComponents(
			AdminMethodValidationBO adminMethodValidationBO)
			throws SevereException {

		List benefitComponents = null;
		HashMap referenceValueSet = new HashMap();
		String queryName = "";
		if (null != adminMethodValidationBO) {
			int entityId = adminMethodValidationBO.getEntitySysId();

			if (0 != entityId) {
				referenceValueSet.put("entitySysId", new Integer(entityId));
			}
		}
		if (adminMethodValidationBO.getEntityType().equals("contract"))
			queryName = "getBenefitComponentForContract";
		else if (adminMethodValidationBO.getEntityType().equals("product"))
			queryName = "getBenefitComponentForProduct";
		else if (adminMethodValidationBO.getEntityType()
				.equals("ProdStructure"))
			queryName = "getBenefitComponentForProductStructure";

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodValidationBO.class.getName(), queryName,
				referenceValueSet);
		benefitComponents = AdapterUtil.performSearch(searchCriteria)
				.getSearchResults();
		return benefitComponents;

	}

	/**
	 * @param details
	 * @return
	 * @throws SevereException
	 */
	public List getStandardBenefit(
			AdminMethodValidationBO adminMethodValidationBO)
			throws SevereException {

		List benefitComponents = null;
		HashMap referenceValueSet = new HashMap();
		String queryName = "";
		if (null != adminMethodValidationBO) {
			int productId = adminMethodValidationBO.getEntitySysId();
			int benefitcom = adminMethodValidationBO.getBenefitComSysId();
			if (0 != productId) {
				referenceValueSet.put("entitySysId", new Integer(productId));
				referenceValueSet.put("benefitComSysId",
						new Integer(benefitcom));
			}
			if (adminMethodValidationBO.getEntityType().equals("contract"))
				queryName = "getStandardBenefitForContract";
			else if (adminMethodValidationBO.getEntityType().equals("product"))
				queryName = "getStandardBenefitForProduct";
			else if (adminMethodValidationBO.getEntityType().equals(
					"ProdStructure"))
				queryName = "getStandardBenefitForProductStructure";

		}
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodValidationBO.class.getName(), queryName,
				referenceValueSet);
		benefitComponents = AdapterUtil.performSearch(searchCriteria)
				.getSearchResults();
		return benefitComponents;

	}

	/**
	 * @param details
	 * @return
	 * @throws SevereException
	 */
	public List getDateSegments(AdminMethodValidationBO adminMethodValidationBO)
			throws SevereException {

		List benefitComponents = null;
		HashMap referenceValueSet = new HashMap();
		String queryName = "";
		if (null != adminMethodValidationBO) {
			int productId = adminMethodValidationBO.getEntitySysId();
			if (0 != productId) {
				referenceValueSet.put("entitySysId", new Integer(productId));
			}
			if (adminMethodValidationBO.getEntityType().equals("contract"))
				queryName = "getDateSegmentsForContract";

		}
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodValidationBO.class.getName(), queryName,
				referenceValueSet);
		benefitComponents = AdapterUtil.performSearch(searchCriteria)
				.getSearchResults();
		return benefitComponents;

	}

	/**
	 * @return
	 * @throws SevereException, AdapterException
	 */
	public List getInvalidSPSListAndAsynchronousThreadFlag(int entitySysId,
			String entityType, int contractId) throws SevereException,
			AdapterException {
		// 
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("entitySysId", new Integer(entitySysId));//entityId
		criteriaMap
				.put(BusinessConstants.ENTITY_TYPE, entityType.toUpperCase());//entityType
		SearchCriteria searchCriteria = null;
		SearchResults searchResults;
		if (entityType.equalsIgnoreCase("product") || entityType.equalsIgnoreCase(BusinessConstants.TIERPRODUCT)) {
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					AdminMethodValidationBO.class.getName(),
					"getInvalidSPSListForProduct", criteriaMap);
		} else if (entityType.equalsIgnoreCase("prodStructure")) {
			criteriaMap.put(BusinessConstants.ENTITY_TYPE, "PRODSTRUCTURE");
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					AdminMethodValidationBO.class.getName(),
					"getInvalidSPSListForProductStructure", criteriaMap);
		} else if (entityType.equalsIgnoreCase("contract") || entityType.equalsIgnoreCase(BusinessConstants.TIERCONTRACT)) {
			criteriaMap.put("contractId", new Integer(contractId));
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					AdminMethodValidationBO.class.getName(),
					"getInvalidSPSListForContract", criteriaMap);
		}
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}

		if (!searchResults.getSearchResults().isEmpty()) {
			AdminMethodValidationBO validationBO = (AdminMethodValidationBO) searchResults
					.getSearchResults().get(0);
			if (validationBO.getSpsValidFlag().equals("I"))
				return searchResults.getSearchResults();
		}

		if (entityType.equalsIgnoreCase("product")) {
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					AdminMethodValidationBO.class.getName(),
					"getAsynchronousThreadFlagForProduct", criteriaMap);
		} else if (entityType.equalsIgnoreCase("prodStructure")) {
			criteriaMap.put(BusinessConstants.ENTITY_TYPE, "PRODSTRUCTURE");
			searchCriteria = AdapterUtil
					.getAdapterSearchCriteria(AdminMethodValidationBO.class
							.getName(),
							"getAsynchronousThreadFlagForProductStructure",
							criteriaMap);
		} else if (entityType.equalsIgnoreCase("contract")) {
			criteriaMap.put("contractId", new Integer(contractId));
			criteriaMap.put(BusinessConstants.ENTITY_TYPE, entityType
					.toUpperCase());//entityType
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					AdminMethodValidationBO.class.getName(),
					"getAsynchronousThreadFlagForContract", criteriaMap);
		}
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}

		return searchResults.getSearchResults();
	}

	/**
	 * @param adminMethodsPopupBO
	 * @return List
	 * @throws SevereException, AdapterException
	 */
	public List getAdminMethodsView(AdminMethodsPopupBO adminMethodsPopupBO)
			throws SevereException, AdapterException {
		HashMap criteriaMap = new HashMap();
		SearchCriteria searchCriteria = null;
		SearchResults searchResults;
		criteriaMap.put(BusinessConstants.ADMINMETHODID, new Integer(
				adminMethodsPopupBO.getAdminId()));
		criteriaMap.put(BusinessConstants.ADMINSYSID, new Integer(
				adminMethodsPopupBO.getAdminMethodId()));
		if (WebConstants.BENEFIT_TYPE.equalsIgnoreCase(adminMethodsPopupBO
				.getEntityType())) {
			//criteriaMap.put("benDefId", new Integer(adminMethodsPopupBO.getStdDefId()));
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					AdminMethodsPopupBO.class.getName(),
					"getAdminConfigForBenefit", criteriaMap);
		} else if (WebConstants.BENEFIT_COMP
				.equalsIgnoreCase(adminMethodsPopupBO.getEntityType())) {
			criteriaMap.put(BusinessConstants.BENECOMPID, new Integer(
					adminMethodsPopupBO.getBenefitCompId()));
			criteriaMap.put(BusinessConstants.ENTYID, new Integer(
					adminMethodsPopupBO.getBenefitCompId()));
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					AdminMethodsPopupBO.class.getName(),
					"getAdminConfigForBenefitComponent", criteriaMap);

		} else if (WebConstants.PROD_STRUCT
				.equalsIgnoreCase(adminMethodsPopupBO.getEntityType())) {
			criteriaMap.put(BusinessConstants.BENECOMPID, new Integer(
					adminMethodsPopupBO.getBenefitCompId()));
			criteriaMap.put(BusinessConstants.ENTYID, new Integer(
					adminMethodsPopupBO.getEntityId()));
			if (!adminMethodsPopupBO.getBenefitComponentName().equals(
					BusinessConstants.GENERALBENEFITS)) {
				criteriaMap.put(BusinessConstants.BCNAME,
						BusinessConstants.GENERALBENEFITS);
			}
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					AdminMethodsPopupBO.class.getName(),
					"getAdminConfigForProductStructure", criteriaMap);

		} else if (WebConstants.PROD_TYPE.equalsIgnoreCase(adminMethodsPopupBO
				.getEntityType())) {
			criteriaMap.put(BusinessConstants.BENECOMPID, new Integer(
					adminMethodsPopupBO.getBenefitCompId()));
			criteriaMap.put(BusinessConstants.ENTYID, new Integer(
					adminMethodsPopupBO.getEntityId()));
			if (!adminMethodsPopupBO.getBenefitComponentName().equals(
					BusinessConstants.GENERALBENEFITS)) {
				criteriaMap.put(BusinessConstants.BCNAME,
						BusinessConstants.GENERALBENEFITS);
			}
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					AdminMethodsPopupBO.class.getName(),
					"getAdminConfigForProduct", criteriaMap);

		} else if (WebConstants.CONTRACT.equalsIgnoreCase(adminMethodsPopupBO
				.getEntityType())) {
			criteriaMap.put(BusinessConstants.BENECOMPID, new Integer(
					adminMethodsPopupBO.getBenefitCompId()));
			criteriaMap.put(BusinessConstants.ENTYID, new Integer(
					adminMethodsPopupBO.getContractDateSgmntId()));
			if (!adminMethodsPopupBO.getBenefitComponentName().equals(
					BusinessConstants.GENERALBENEFITS)) {
				criteriaMap.put(BusinessConstants.BCNAME,
						BusinessConstants.GENERALBENEFITS);
			}
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					AdminMethodsPopupBO.class.getName(),
					"getAdminConfigForContract", criteriaMap);
		}
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		return searchResults.getSearchResults();

	}

	/**
	 * To get the Admin Method Validation status
	 * @param details
	 * @return
	 * @throws SevereException
	 */
	public List getAdminMethodSPSValidationStatus(int contractSysId)
			throws AdapterException {

		List invalidSPS = null;
		HashMap referenceValueSet = new HashMap();
		String queryName = "getAdminMethodSPSValidationStatus";
		referenceValueSet.put("entitySysId", new Integer(contractSysId));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodValidationBO.class.getName(), queryName,
				referenceValueSet);
		try {
			invalidSPS = AdapterUtil.performSearch(searchCriteria)
					.getSearchResults();
		} catch (SevereException e) {
			throw new AdapterException("Exception occured while adapter call",
					e);
		}
		return invalidSPS;

	}

	/**
	 * To get the benefit Component information for the Tree
	 * @param adminMethodValidationBO
	 * @return List of AdminMethodValidationBO
	 * @throws SevereException
	 */

	public List getBenefitComponentsCodedSPS(
			AdminMethodValidationBO adminMethodValidationBO)
			throws AdapterException {

		if (adminMethodValidationBO == null)
			return null;

		List benefitComponents = null;
		HashMap referenceValueSet = new HashMap();
		String queryName = "";
		if (null != adminMethodValidationBO) {
			int entityId = adminMethodValidationBO.getEntitySysId();

			if (0 != entityId) {
				referenceValueSet.put("entitySysId", new Integer(entityId));
			}
		}
		if (adminMethodValidationBO.getEntityType().equals("contract"))
			queryName = "getBenefitComponentForCodedSPSContract";

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodValidationBO.class.getName(), queryName,
				referenceValueSet);
		try {
			benefitComponents = AdapterUtil.performSearch(searchCriteria)
					.getSearchResults();
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		return benefitComponents;

	}

	/**
	 * To get the Standard Benefit information for the Tree
	 * @param details
	 * @return List of AdminMethodValidationBO
	 * @throws SevereException
	 */
	public List getStandardBenefitCodedSPS(
			AdminMethodValidationBO adminMethodValidationBO)
			throws AdapterException {

		if (adminMethodValidationBO == null)
			return null;

		List benefitComponents = null;
		HashMap referenceValueSet = new HashMap();
		String queryName = "";
		if (null != adminMethodValidationBO) {
			int productId = adminMethodValidationBO.getEntitySysId();
			int benefitcom = adminMethodValidationBO.getBenefitComSysId();
			if (0 != productId) {
				referenceValueSet.put("entitySysId", new Integer(productId));
				referenceValueSet.put("benefitComSysId",
						new Integer(benefitcom));
			}
			if (adminMethodValidationBO.getEntityType().equals("contract"))
				queryName = "getStandardBenefitForCodedSPSContract";

		}
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodValidationBO.class.getName(), queryName,
				referenceValueSet);
		try {
			benefitComponents = AdapterUtil.performSearch(searchCriteria)
					.getSearchResults();
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		return benefitComponents;

	}

	/**
	 * To get the Date Segment information for the Tree
	 * @param details
	 * @return List of AdminMethodValidationBO
	 * @throws SevereException
	 */
	public List getDateSegmentsForCodedSPS(
			AdminMethodValidationBO adminMethodValidationBO)
			throws AdapterException {

		if (adminMethodValidationBO == null)
			return null;

		List benefitComponents = null;
		HashMap referenceValueSet = new HashMap();
		String queryName = "";
		if (null != adminMethodValidationBO) {
			int productId = adminMethodValidationBO.getEntitySysId();
			if (0 != productId) {
				referenceValueSet.put("entitySysId", new Integer(productId));
			}
			if (adminMethodValidationBO.getEntityType().equals("contract"))
				queryName = "getDateSegmentsForCodedSPSContract";

		}
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodValidationBO.class.getName(), queryName,
				referenceValueSet);
		try {
			benefitComponents = AdapterUtil.performSearch(searchCriteria)
					.getSearchResults();
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		return benefitComponents;

	}

	/**
	 * Method is used to return the list of invalid SPS references.
	 * @param adminMethodSPSValidationBO
	 * @return
	 * @throws AdapterException
	 */
	public List getAdminMethodSPSParameters(
			AdminMethodSPSValidationBO adminMethodSPSValidationBO)
			throws AdapterException {

		HashMap criteriaMap = new HashMap();

		criteriaMap.put(BusinessConstants.ENTITYID, new Integer(
				adminMethodSPSValidationBO.getEntityId()));
		criteriaMap.put(BusinessConstants.PRODSYSID, new Integer(
				adminMethodSPSValidationBO.getProdId()));
		criteriaMap.put(BusinessConstants.BENCOMPSYSID, new Integer(
				adminMethodSPSValidationBO.getBenCompId()));
		criteriaMap.put(BusinessConstants.BENSYSID, new Integer(
				adminMethodSPSValidationBO.getBenId()));
		criteriaMap.put(BusinessConstants.BENADMNID, new Integer(
				adminMethodSPSValidationBO.getBenAdminId()));

		SearchResults searchResults;
		SearchCriteria searchCriteria = null;

		searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodSPSValidationBO.class.getName(),
				"getInvalidReferenceFromContract", criteriaMap);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		return searchResults.getSearchResults();
	}
	/*CARS AM2 START 28/3/2010 FOR TIER*/
	public List getTieredAdminMethodSPSParameters(AdminMethodSPSValidationBO adminMethodSPSValidationBO) throws AdapterException {

		HashMap criteriaMap = new HashMap();

		criteriaMap.put(BusinessConstants.ENTITYID, new Integer(
				adminMethodSPSValidationBO.getEntityId()));
		criteriaMap.put(BusinessConstants.PRODSYSID, new Integer(
				adminMethodSPSValidationBO.getProdId()));
		criteriaMap.put(BusinessConstants.BENCOMPSYSID, new Integer(
				adminMethodSPSValidationBO.getBenCompId()));
		criteriaMap.put(BusinessConstants.BENSYSID, new Integer(
				adminMethodSPSValidationBO.getBenId()));
		criteriaMap.put(BusinessConstants.BENADMNID, new Integer(
				adminMethodSPSValidationBO.getBenAdminId()));

		SearchResults searchResults;
		SearchCriteria searchCriteria = null;

		searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodSPSValidationBO.class.getName(),
				"getInvalidReferenceFromContractForTiers", criteriaMap);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		return searchResults.getSearchResults();
	}
	/*CARS AM2 END 28/3/2010 SPS POP UP FOR TIER*/
    //CARS AM1 START
    /**
     * Method to retrieve tiered admin methods for a tiered benefit for the selected Product or Contract.
     * 
     * @param
     * adminMethodsPopupBO, qualList,termValue
     * @return list
     * @throws SevereException,AdapterException
     */
    public List getTierAdminMethods(AdminMethodsPopupBO adminMethodsPopupBO,List qualList,String termValue) 
    	throws SevereException,AdapterException {
    	  Logger.logInfo("Entering Adapter method, getTierAdminMethods in AdminMethodAdapterManager");	
    	  HashMap criteriaMap = new HashMap();
          criteriaMap.put(BusinessConstants.SPSID, new Integer(adminMethodsPopupBO.getSpsId()));
          String query = null;
          if (BusinessConstants.PRODUCT.equalsIgnoreCase(adminMethodsPopupBO.getEntityType())) {
            query = BusinessConstants.ADMINEMTHODFORTIERPRODUCT;
            criteriaMap.put(BusinessConstants.BENID, new Integer(adminMethodsPopupBO.getBenftId()));
            criteriaMap.put(BusinessConstants.BENCOMID, new Integer(adminMethodsPopupBO.getBenefitCompId()));
            criteriaMap.put(BusinessConstants.PRODId, new Integer(adminMethodsPopupBO.getEntityId()));
            criteriaMap.put(BusinessConstants.QUALLIST, qualList);
            criteriaMap.put(BusinessConstants.TERMLIST, termValue);
            criteriaMap.put(BusinessConstants.BEN_TIER_SYS_ID, new Integer(adminMethodsPopupBO.getBenefitTierSysId()));
        } else if (BusinessConstants.CONTRACT.equalsIgnoreCase(adminMethodsPopupBO.getEntityType())) {
            query = BusinessConstants.ADMINEMTHODFORTIERCONTRACT;
            criteriaMap.put(BusinessConstants.BENID, new Integer(adminMethodsPopupBO.getBenftId()));
            criteriaMap.put(BusinessConstants.BENCOMID, new Integer(adminMethodsPopupBO.getBenefitCompId()));
            criteriaMap.put(BusinessConstants.DATESEGID, new Integer(adminMethodsPopupBO.getContractDateSgmntId()));
            criteriaMap.put(BusinessConstants.QUALLIST, qualList);
            criteriaMap.put(BusinessConstants.TERMLIST, termValue);
            criteriaMap.put(BusinessConstants.PRODId, new Integer(adminMethodsPopupBO.getProdId()));
            criteriaMap.put(BusinessConstants.TERMLIST, termValue);
            criteriaMap.put(BusinessConstants.BEN_TIER_SYS_ID, new Integer(adminMethodsPopupBO.getBenefitTierSysId()));
        }
        SearchResults searchResults;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                AdminMethodsPopupBO.class.getName(), query, criteriaMap);
        try {
            searchResults = AdapterUtil.performSearch(searchCriteria);
        } catch (Exception ex) {
            throw new AdapterException("Exception occured while adapter call",
                    ex);
        }
        Logger.logInfo("Return from Adapter method, getTierAdminMethods in AdminMethodAdapterManager");	
        return searchResults.getSearchResults();    	
    }
    /**
     * Method retrieve tier question answer list for a tiered benefit for the selected Product or Contract.
     * 
     * @param entityId,adminId,entityType,dataSegId,bcName,benCompId
     * @return List
     * @throws SevereException
     * @throws AdapterException
     */
    public List getTierQuestionAnswerList(AdminMethodsPopupBO adminMethodsPopupBO)
            throws SevereException, AdapterException {
    	Logger.logInfo("Entering Adapter method, getTierQuestionAnswerList in AdminMethodAdapterManager");	
        HashMap criteriaMap = new HashMap();
        criteriaMap.put(BusinessConstants.ADMINID, new Integer(adminMethodsPopupBO.getAdminId()));
        criteriaMap.put(BusinessConstants.ENTITYID, new Integer(adminMethodsPopupBO.getEntityId()));
        String query;
        query = BusinessConstants.GET_QUESTION_FOR_TIERP;
        if (WebConstants.PRODUCT.equalsIgnoreCase(adminMethodsPopupBO.getEntityType())) {           
            if (!BusinessConstants.GENERALBENEFITS.equalsIgnoreCase(adminMethodsPopupBO.getBenefitComponentName())) {
                criteriaMap.put(BusinessConstants.BCNAME,
                        BusinessConstants.GENERALBENEFITS);
            }
            criteriaMap.put(BusinessConstants.BENCOMID, new Integer(adminMethodsPopupBO.getBenefitCompId()));
            criteriaMap.put(BusinessConstants.BENEID, new Integer(adminMethodsPopupBO.getBenftId()));
            criteriaMap.put(BusinessConstants.BEN_TIER_SYS_ID, new Integer(adminMethodsPopupBO.getBenefitTierSysId()));
            criteriaMap.put(BusinessConstants.ENTY_TIER_SYS_ID, new Integer(adminMethodsPopupBO.getEntityTierSysId()));            
        } else if (WebConstants.CONTRACT_PRODUCT_ADMIN
                .equalsIgnoreCase(adminMethodsPopupBO.getEntityType())) {
            query = BusinessConstants.GET_QUESTION_FOR_TIERC;
            if (!BusinessConstants.GENERALBENEFITS.equalsIgnoreCase(adminMethodsPopupBO.getBenefitComponentName()))
                criteriaMap.put(BusinessConstants.BCNAME,BusinessConstants.GENERALBENEFITS);
            criteriaMap.put(BusinessConstants.DATESEGID, new Integer(adminMethodsPopupBO.getContractDateSgmntId()));
            criteriaMap.put(BusinessConstants.BENCOMID, new Integer(adminMethodsPopupBO.getBenefitCompId()));
            criteriaMap.put(BusinessConstants.BENEID, new Integer(adminMethodsPopupBO.getBenftId()));
            criteriaMap.put(BusinessConstants.BEN_TIER_SYS_ID, new Integer(adminMethodsPopupBO.getBenefitTierSysId()));
            criteriaMap.put(BusinessConstants.ENTY_TIER_SYS_ID, new Integer(adminMethodsPopupBO.getEntityTierSysId()));
        }
        SearchResults searchResults;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                AdminMethodsPopupBO.class.getName(), query, criteriaMap);
        try {
            searchResults = AdapterUtil.performSearch(searchCriteria);
        } catch (Exception ex) {
            throw new AdapterException("Exception occured while adapter call",
                    ex);
        }
        Logger.logInfo("Return from Adapter method, getTierQuestionAnswerList in AdminMethodAdapterManager");	
        return searchResults.getSearchResults();
    }
    /**
     * Method retrieve qualifiers in a benefit tier for the selected Product or Contract.
     * 
     * @param
     * adminMethodPopupBO,terms,benDefnId
     * @return list
     * @throws SevereException,AdapterException
     */
    public List getTierQualifiers(AdminMethodsPopupBO adminMethodsPopupBO, String terms, int benDefnId) throws SevereException, AdapterException {
    	Logger.logInfo("Entering Adapter method, getTierQualifiers in AdminMethodAdapterManager");	
        HashMap criteriaMap = new HashMap();
        String query = null;
        if (BusinessConstants.PRODUCT.equalsIgnoreCase(adminMethodsPopupBO.getEntityType())) {
            query = BusinessConstants.GETQUALIFIERFORTIERP;
            criteriaMap.put(BusinessConstants.BENSYSID, new Integer(adminMethodsPopupBO.getBenftId()));
            criteriaMap.put(BusinessConstants.TERMLIST, terms);
            criteriaMap.put(BusinessConstants.BENCOMID, new Integer(adminMethodsPopupBO.getBenefitCompId()));
            criteriaMap.put(BusinessConstants.ENTITY_ID, new Integer(adminMethodsPopupBO.getEntityId()));
            criteriaMap.put(BusinessConstants.BEN_TIER_SYS_ID, new Integer(adminMethodsPopupBO.getBenefitTierSysId()));            
        } else if (BusinessConstants.CONTRACT.equalsIgnoreCase(adminMethodsPopupBO.getEntityType())) {
            query = BusinessConstants.GETQUALIFIERFORTIERC;
            criteriaMap.put(BusinessConstants.BENSYSID, new Integer(adminMethodsPopupBO.getBenftId()));
            criteriaMap.put(BusinessConstants.TERMLIST, terms);
            criteriaMap.put(BusinessConstants.BENCOMID, new Integer(adminMethodsPopupBO.getBenefitCompId()));
            criteriaMap.put(BusinessConstants.DATESEGID, new Integer(adminMethodsPopupBO.getContractDateSgmntId()));
            criteriaMap.put(BusinessConstants.ENTITY_ID, new Integer(adminMethodsPopupBO.getProdId()));
            criteriaMap.put(BusinessConstants.BEN_TIER_SYS_ID, new Integer(adminMethodsPopupBO.getBenefitTierSysId()));    
        }
        SearchResults searchResults;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                AdminMethodsPopupBO.class.getName(), query, criteriaMap);
        try {
            searchResults = AdapterUtil.performSearch(searchCriteria);
        } catch (Exception ex) {
            throw new AdapterException("Exception occured while adapter call",
                    ex);
        }
        Logger.logInfo("Return from Adapter method, getTierQualifiers in AdminMethodAdapterManager");	
        return searchResults.getSearchResults();
    }
    /**
     * Method retrieve terms from benefit tier Entity for the selected Product or Contract.
     * 
     * @param spsId
     * @return list
     * @throws SevereException,AdapterException
     */
    public List getTermsFromTierEntity(AdminMethodsPopupBO adminMethodsPopupBO) throws SevereException, AdapterException {
    	 Logger.logInfo("Entering Adapter method, getTermsFromTierEntity in AdminMethodAdapterManager");	
    	 HashMap criteriaMap = new HashMap();
         String query = null;  	
         if (BusinessConstants.PRODUCT.equalsIgnoreCase(adminMethodsPopupBO.getEntityType())) {
            query = BusinessConstants.GETTERMFROMTIERPRODUCT;
            criteriaMap.put(BusinessConstants.BENSYSID, new Integer(adminMethodsPopupBO.getBenftId()));
            criteriaMap.put(BusinessConstants.BENCOMID, new Integer(adminMethodsPopupBO.getBenefitCompId()));
            criteriaMap.put(BusinessConstants.ENTITY_ID, new Integer(adminMethodsPopupBO.getEntityId()));
            criteriaMap.put(BusinessConstants.BEN_TIER_SYS_ID, new Integer(adminMethodsPopupBO.getBenefitTierSysId()));
        } else if (BusinessConstants.CONTRACT.equalsIgnoreCase(adminMethodsPopupBO.getEntityType())) {
            query = BusinessConstants.GETTERMFROMTIERCONTRACT;
            criteriaMap.put(BusinessConstants.BENSYSID, new Integer(adminMethodsPopupBO.getBenftId()));
            criteriaMap.put(BusinessConstants.BENCOMID, new Integer(adminMethodsPopupBO.getBenefitCompId()));
            criteriaMap.put(BusinessConstants.DATESEGID, new Integer(adminMethodsPopupBO.getContractDateSgmntId()));
            criteriaMap.put(BusinessConstants.ENTITY_ID, new Integer(adminMethodsPopupBO.getProdId()));
            criteriaMap.put(BusinessConstants.BEN_TIER_SYS_ID, new Integer(adminMethodsPopupBO.getBenefitTierSysId()));
        }
        SearchResults searchResults;

        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                AdminMethodsPopupBO.class.getName(), query, criteriaMap);
        try {
            searchResults = AdapterUtil.performSearch(searchCriteria);
        } catch (Exception ex) {
            throw new AdapterException("Exception occured while adapter call",
                    ex);
        }
        Logger.logInfo("Return from Adapter method, getTermsFromTierEntity in AdminMethodAdapterManager");	
        return searchResults.getSearchResults();
    }
    
    /**
     * This method returns the invalid SPS List of the Admin Method Tiers for the selected Product or Contract.
     * @param adminMethodValidationBO
     * @return list
     * @throws SevereException
     * @throws AdapterException
     */
    public List getTierAdminMethodsForValidation(AdminMethodValidationBO adminMethodValidationBO) throws SevereException,
	AdapterException {    	
    	Logger.logInfo("Entering Adapter method, getTierAdminMethodsForValidation in AdminMethodAdapterManager");
    	List SPSList = new ArrayList();
    	HashMap criteriaMap = new HashMap();
    	String query = "";
    	SearchResults searchResults;
    	
    	if(adminMethodValidationBO.getEntityType().equalsIgnoreCase(BusinessConstants.PRODUCT)){
    		query = BusinessConstants.BENEFITADMINISTRATION_FOR_TIERPRODUCT;;
    	}else if(adminMethodValidationBO.getEntityType().equalsIgnoreCase(BusinessConstants.CONTRACT)){
    		query = BusinessConstants.BENEFITADMINISTRATION_FOR_TIERCONTRACT;
    	}
    	criteriaMap.put(BusinessConstants.ENTITYSYSID,new Integer(adminMethodValidationBO.getEntitySysId()));
    	criteriaMap.put(BusinessConstants.BENEFIT_COM_SYSID,new Integer(adminMethodValidationBO.getBenefitComSysId()));
    	criteriaMap.put(BusinessConstants.BEN_SYS_ID,new Integer(adminMethodValidationBO.getBenefitSysId()));    	
    	SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(AdminMethodValidationBO.class.getName(), query, criteriaMap);
    	try {
    		searchResults = AdapterUtil.performSearch(searchCriteria);
    	} catch (Exception ex) {
    		throw new AdapterException("Exception occured while adapter call",
    				ex);
    	}
    	if (null != searchResults && searchResults.getSearchResults().size() >0 && null != adminMethodValidationBO.getEntityType() && 
    	        adminMethodValidationBO.getEntityType().equalsIgnoreCase(BusinessConstants.CONTRACT)) {
			HashSet productFamily = new HashSet();
			Iterator searchResultsIterator = searchResults.getSearchResults().iterator();
			while (searchResultsIterator.hasNext()) {
				AdminMethodValidationBO adminMethodValidationBO1 = (AdminMethodValidationBO) searchResultsIterator.next();
				productFamily.add(adminMethodValidationBO1.getProductFamName()
						.toUpperCase());
			}
			
			AdminMethodValidationBO adminMethodValidationBO2 = (AdminMethodValidationBO) searchResults.getSearchResults().get(0);
			
			
			// Check if both HMO and PPO is there. If so its POS
			if (productFamily.size() > 0 && productFamily.contains("HMO") && productFamily.contains("PPO")) {
				// Setting only in the first BO.*
				adminMethodValidationBO2.setPosProductFamily(true);
			}
		}
    	Logger.logInfo("Return from Adapter method, getTierAdminMethodsForValidation in AdminMethodAdapterManager");
    	return searchResults.getSearchResults();	
    }
    //CARS AM1 END
}