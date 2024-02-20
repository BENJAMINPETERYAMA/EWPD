/*
 * Created on Oct 4, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.adminmethodmaintain.adapter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.adminmethodmaintain.bo.AdminMethodMaintainBO;
import com.wellpoint.wpd.common.adminmethodmaintain.bo.ConfigurationBO;
import com.wellpoint.wpd.common.adminmethodmaintain.bo.ReferenceGroupBO;
import com.wellpoint.wpd.common.adminmethodmaintain.bo.RequiredParamBO;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.security.domain.User;

/**
 * @author U17066
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodMaintainAdapterManager {

	/**
	 * Method to insert Admin Method Information.
	 * @param adminMethodMaintainBO
	 * @return
	 * @throws AdapterException
	 */
	public boolean createAdminMethod(
			AdminMethodMaintainBO adminMethodMaintainBO ) throws  AdapterException{
		
		boolean suceess = false;
		try {

			AdapterUtil.performInsert(adminMethodMaintainBO, adminMethodMaintainBO
					.getCreatedUser());
			
			
			

			suceess = true;
		} catch(SevereException  ad){
			
			throw new AdapterException(
					"Exception occured in createAdminMethod method in AdminMethodMaintainAdapterManager",
					ad);
		}
		
		Logger.logInfo("Returning the method for createAdminMethod Method");

		return suceess;
	}

	/**
	 * Method to insert Configuration List
	 * @param configurationBO
	 * @return
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public boolean createConfiguration(
			ConfigurationBO configurationBO ) throws AdapterException{
		
		boolean suceess = false;
		try {
			
			AdapterUtil.performInsert(configurationBO, configurationBO
					.getCreatedUser());
			

			suceess = true;
		} catch(SevereException  ad){
			
			throw new AdapterException(
					"Exception occured in createConfiguration method in AdminMethodMaintainAdapterManager",
					ad);
		}
		
		Logger.logInfo("Returning the method for createConfiguration Method");

		return suceess;
	}

	
	
	/**
	 * @param Method to insert Required Parameter Group refrences
	 * @return
	 * @throws AdapterException
	 */
	public boolean createGroup(ReferenceGroupBO referenceGroupBO) throws AdapterException {
		
		boolean suceess = false;
		try {
			
			AdapterUtil.performInsert(referenceGroupBO, referenceGroupBO
					.getCreatedUser());
			

			suceess = true;
		} catch(SevereException  ad){
			
			throw new AdapterException(
					"Exception occured in createGroup method in AdminMethodMaintainAdapterManager",
					ad);
		}
		
		Logger.logInfo("Returning the method for createGroup Method");
		return suceess;
	}
	
	/**
	 * @param Method to insert Required Parameter group
	 * @return
	 * @throws AdapterException
	 */
	public boolean createRequiredParam(RequiredParamBO requiredParamBO) throws AdapterException {
		boolean suceess = false;
		try {
			
			AdapterUtil.performInsert(requiredParamBO, requiredParamBO
					.getCreatedUser());
			

			suceess = true;
		} catch(SevereException  ad){
			
			throw new AdapterException(
					"Exception occured in createRequiredParam method in AdminMethodMaintainAdapterManager",
					ad);
		}
		
		Logger.logInfo("Returning the method for createRequiredParam Method");
		return suceess;
	}	

	/**
	 * @param Method to retrive Admin Methods
	 * @return
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public SearchResults retrieveAdminMethod(AdminMethodMaintainBO adminMethodMaintainBO)
			throws AdapterException {
		
		Logger
				.logInfo("AdminMethodMaintainAdapterManager - Retrieving Admin Method");
		SearchResults searchResults=null;
		HashMap refValSet = new HashMap();
		if (adminMethodMaintainBO.getAdminMethodNo() == null
				|| "".equals(adminMethodMaintainBO.getAdminMethodNo())) {

			refValSet.put("adminMethodNo", null);
		} else {
			refValSet.put("adminMethodNo", adminMethodMaintainBO
					.getAdminMethodNo());
		}
		if (!adminMethodMaintainBO.isSearchExisitng()) {		
			refValSet.put("description", "%"
					+ adminMethodMaintainBO.getDescription().toUpperCase() + "%");
		} else {
			refValSet.put("description",
					 adminMethodMaintainBO.getDescription().toUpperCase() );
		}
		
	    
	
		if (adminMethodMaintainBO.getProcessMethodList() == null || adminMethodMaintainBO.getProcessMethodList().size()==0 
				|| "".equals(adminMethodMaintainBO.getProcessMethodList()))	
										
			refValSet.put("processMethodList", null);
		else {

			refValSet.put("processMethodList", adminMethodMaintainBO
					.getProcessMethodList());
		}

		int maxResultSize = 100;
		SearchCriteria searchCriteria = null;
		
		 searchCriteria = AdapterUtil
				.getAdapterSearchCriteria(BusinessConstants.ADMIN_METHOD_BO,
						BusinessConstants.LOCATE_ADMIN_METHOD, refValSet,
						maxResultSize);
		
		try{
		searchResults = AdapterUtil.performSearch(searchCriteria);
		}
		catch(SevereException  ad){
			
			throw new AdapterException(
					"Exception occured in retrieveAdminMethod method in AdminMethodMaintainAdapterManager",
					ad);
		}
		
		Logger.logInfo("Returning the method for locate Admin Method");
		return searchResults;
		
	}

	/**
	 * Method for deleting an admin method
	 * 
	 * @param referenceMappingBO
	 * @param user
	 * @throws SevereException
	 */
	public void deleteAdminMethod(AdminMethodMaintainBO adminMethodMaintainBO,
			User user) throws SevereException {

		AdapterUtil.performRemove(adminMethodMaintainBO, user.getUserId());
	}
	/**
	 * This method checks whether any mappings are creted with the current admin
	 * method
	 * 
	 * @return
	 */
	public int isAdminMethodMapped(int adminMethodNo,String adminMethodDesc,String spsId )throws AdapterException {

		Logger
				.logInfo("AdminMethodMaintainAdapterManager - checking whether Mappings exists with the current AdminMethod");
		SearchResults searchResults = null;
		HashMap refValSet = new HashMap();
		refValSet.put("description", adminMethodDesc);
		refValSet.put("adminMethodNo", new Integer(adminMethodNo));
		refValSet.put("processMethod", spsId);
		
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				BusinessConstants.ADMIN_METHOD_BO,
				BusinessConstants.IS_ADMIN_METHOD_MAPPED, refValSet);

		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);

			Logger.logInfo("Mappings exists wit the current admin method.");
			
			AdminMethodMaintainBO adminMethodMaintainBO=(AdminMethodMaintainBO)searchResults.getSearchResults().get(0);
			
			return adminMethodMaintainBO.getAdminMethodSysId();
				
		} catch (SevereException ad) {
			throw new AdapterException(
					"Exception occured in isAdminMethodMapped method in AdminMethodMaintainAdapterManager",
					ad);
		}
	}
	/**
	 * @param Method to view Admin Method
	 * @return
	 * @throws AdapterException
	 */
	public SearchResults viewAdminMethod(AdminMethodMaintainBO adminMethodMaintainBO) throws AdapterException {
		Logger
		.logInfo("AdminMethodMaintainAdapterManager - View Admin Method");
        SearchResults searchResults=null;
        HashMap refValSet = new HashMap();
        refValSet.put("adminMethodSysId",new Integer(adminMethodMaintainBO.getAdminMethodSysId()));
        SearchCriteria searchCriteria = AdapterUtil
		.getAdapterSearchCriteria(BusinessConstants.ADMIN_METHOD_BO,
		BusinessConstants.VIEW_ADMIN_METHOD, refValSet);
    	
		

		
        try{
    		searchResults = AdapterUtil.performSearch(searchCriteria);
    		}
        catch(SevereException  ad){
			
			throw new AdapterException(
					"Exception occured in viewAdminMethod method in AdminMethodMaintainAdapterManager",
					ad);
		}
		
		Logger.logInfo("Returning the method for viewAdminMethod Admin Method");
		return searchResults;
	}

	/**
	 * @param Method to view Configuration List
	 * @throws AdapterException
	 */
	public List viewConfigList(ConfigurationBO configurationBO) throws AdapterException {
		Logger
		.logInfo("AdminMethodMaintainAdapterManager - View Configuration List of Admin Method");
        SearchResults searchResults=null;
        List configList=null;
        HashMap refValSet = new HashMap();
        refValSet.put("adminMethodSystemId",new Integer(configurationBO.getAdminMethodSysId()));
        SearchCriteria searchCriteria = AdapterUtil
		.getAdapterSearchCriteria(BusinessConstants.ADMIN_METHOD_CONFIG_BO,
		BusinessConstants.VIEW_CONFIGLIST_ADMIN_METHOD, refValSet);
        try {
			
			searchResults= AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException ad) {

			throw new AdapterException(
					"Exception occured in viewConfigList method in AdminMethodMaintainAdapterManager",
					ad);
		}
       
		configList = searchResults.getSearchResults();
		
		
		Logger.logInfo("Returning the method for viewConfigList Admin Method");
		return configList;
	}

	/**
	 * @param Method to view Required Parameter List
	 * @return
	 * @throws AdapterException
	 */
	public List viewReqParamList(RequiredParamBO requiredParamBO)
			throws AdapterException {

		
		Logger
				.logInfo("AdminMethodMaintainAdapterManager - View Required Parameter List of Admin Method");
		SearchResults searchResults = null;
		List reqParamList = null;
		List refGrpList = null;
		HashMap refValSet = new HashMap();
		ReferenceGroupBO referenceGroupBO = new ReferenceGroupBO();
		
		//refValSet.put("adminMethodNo", requiredParamBO.getAdminMethodNo());
		//refValSet.put("adminMethodDesc", requiredParamBO.getAdminMethodDesc());		
		refValSet.put("adminMethodSysId", new Integer(requiredParamBO.getAdminMethodSysId()));
		
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				BusinessConstants.ADMIN_METHOD_REQPARAM_BO,
				BusinessConstants.VIEW_REQPARAMGLIST_ADMIN_METHOD, refValSet);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
			reqParamList = searchResults.getSearchResults();
			 
			if (reqParamList != null && reqParamList.size() > 0) {
				
				for (Iterator iter = reqParamList.iterator(); iter.hasNext();) {
					RequiredParamBO requiredParameter = (RequiredParamBO) iter
							.next();						
					referenceGroupBO.setGroupID(requiredParameter.getGroupID());
					refValSet.put("groupId", new Integer(requiredParameter
							.getGroupID()));
					searchCriteria = AdapterUtil.getAdapterSearchCriteria(
							BusinessConstants.ADMIN_METHOD_REFGRP_BO,
							BusinessConstants.VIEW_REFGRPLIST_ADMIN_METHOD,
							refValSet);
					searchResults = AdapterUtil.performSearch(searchCriteria);
					refGrpList = searchResults.getSearchResults();
					requiredParameter.setReferenceGroupBo(refGrpList);
				}			
			}
			Logger.logInfo("Returning the method for reqParamList Admin Method");
			
			return reqParamList;

		} catch (SevereException ad) {

			throw new AdapterException(
					"Exception occured in viewreqParamList method in AdminMethodMaintainAdapterManager",
					ad);
		}
		
	}


	/**
	 * @param Method to delete Required Parameter List
	 * @return
	 * @throws AdapterException
	 */
	public boolean deleteRequiredParamGroup(RequiredParamBO requiredParamBO,User user) throws AdapterException {
		Logger
		.logInfo("AdminMethodMaintainAdapterManager - delete Required Parameter List of Admin Method");
		
		boolean suceess = false;
		try {			
			AdapterUtil.performRemove(requiredParamBO,user.getUserId());		
			suceess = true;
		} catch (SevereException ad) {
			throw new AdapterException(
					"Exception occured in deleteRequiredParamGroup method in AdminMethodMaintainAdapterManager",
					ad);
		}
		return suceess;
	}


	/**
	 * @param Method to delete Configuration List
	 * @param user
	 * @return
	 * @throws AdapterException
	 */
	public boolean deleteConfiguration(ConfigurationBO configurationBO, User user) throws AdapterException {
		Logger
		.logInfo("AdminMethodMaintainAdapterManager - delete Configuration List of Admin Method");
		
		boolean suceess = false;
		try {
			
			AdapterUtil.performRemove(configurationBO,user.getUserId());
			suceess = true;
		} catch (SevereException ad) {

			throw new AdapterException(
					"Exception occured in deleteConfiguration method in AdminMethodMaintainAdapterManager",
					ad);
		}

		return suceess;
	}


	/**
	 * @param Method to update Admin Method
	 * @param user
	 * @return
	 * @throws AdapterException
	 */
	public boolean updateAdminMethod(AdminMethodMaintainBO adminMethodMaintainBO, User user) throws AdapterException {
		Logger
		.logInfo("AdminMethodMaintainAdapterManager - updateAdmin Method");
		
		boolean suceess = false;
		try {
			
			AdapterUtil.performUpdate(adminMethodMaintainBO,user.getUserId());
			suceess = true;
		} catch (SevereException ad) {

			throw new AdapterException(
					"Exception occured in updateAdminMethod method in AdminMethodMaintainAdapterManager",
					ad);
		}
		return suceess;
	}

	/**
	 * Method to Delete Reference Group
	 * @param user
	 * @return
	 * @throws AdapterException
	 */
	public boolean deleteReferenceGroup(ReferenceGroupBO referenceGroupBO, User user) throws AdapterException {
		Logger
		.logInfo("AdminMethodMaintainAdapterManager - delete References of Admin Method");
		
		boolean suceess = false;
		try {
			
			AdapterUtil.performRemove(referenceGroupBO,user.getUserId());
			suceess = true;
		} catch (SevereException ad) {

			throw new AdapterException(
					"Exception occured in deleteReferenceGroup method in AdminMethodMaintainAdapterManager",
					ad);
		}
		return suceess;
	}
}