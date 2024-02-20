/*
 * SPSMappingBusinessObjectBuilder.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.blueexchange.builder;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.blueexchange.adapter.SPSMappingAdapterManager;
import com.wellpoint.wpd.business.framework.bo.AuditFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.common.blueexchange.bo.SPSMappingBO;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.security.domain.User;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SPSMappingBusinessObjectBuilder {


	/**
	 * This method gets the audit object and calls the adapter to insert the Business Object
	 * @param mappingBO
	 * @param user
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean persist(SPSMappingBO mappingBO,User user)throws SevereException {
		SPSMappingAdapterManager sPSMappingAdapterManager = new SPSMappingAdapterManager();
		try {
		    //Audit Object is created to get the created time of teh Business Object
			Audit audit = getAudit(user);
			mappingBO.setCreatedUser(audit.getUser());
			mappingBO.setCreatedTimestamp(audit.getTime());
			mappingBO.setLastUpdatedUser(audit.getUser());
			mappingBO.setLastUpdatedTimestamp(audit.getTime());
			//Adapter call to insert the Business Object
			return sPSMappingAdapterManager.createSPSMapping(mappingBO,user.getUserId());
		} catch (AdapterException e) {
			List errorParams = new ArrayList();
			String obj = e.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method in SPSMappingBusinessObjectBuilder",
					errorParams, e);
		}
	}

	/**
	 * This method gets the audit object and calls the adapter to update the Business Object
	 * @param mappingBO
	 * @param user
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean update(SPSMappingBO mappingBO,User user)throws SevereException {
		SPSMappingAdapterManager sPSMappingAdapterManager = new SPSMappingAdapterManager();
		try {
		    //Audit Object is created to get the created time of the Business Object
			Audit audit = getAudit(user);
			mappingBO.setCreatedUser(audit.getUser());
			mappingBO.setCreatedTimestamp(audit.getTime());
			mappingBO.setLastUpdatedUser(audit.getUser());
			mappingBO.setLastUpdatedTimestamp(audit.getTime());
			//Adapter call to update the Business Object
			return sPSMappingAdapterManager.editSPSMapping(mappingBO,user.getUserId());
		} catch (AdapterException e) {
			List errorParams = new ArrayList();
			String obj = e.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in update method in SPSMappingBusinessObjectBuilder",
					errorParams, e);
		}
	}
	
	
	/**
	 * This method gets the audit object and calls the adapter to delete the Business Object 
	 * @param mappingBO
	 * @param user
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean delete(SPSMappingBO mappingBO,User user) throws SevereException {
		SPSMappingAdapterManager sPSMappingAdapterManager = new SPSMappingAdapterManager();
		try {
		    //Audit Object is created to get the created time of the Business Object
			Audit audit = getAudit(user);
			mappingBO.setCreatedUser(audit.getUser());
			mappingBO.setCreatedTimestamp(audit.getTime());
			mappingBO.setLastUpdatedUser(audit.getUser());
			mappingBO.setLastUpdatedTimestamp(audit.getTime());
			//Adapter call to delete the Business Object
			return sPSMappingAdapterManager.deleteSPSMapping(mappingBO,user.getUserId());
		} catch (AdapterException e) {
			List errorParams = new ArrayList();
			String obj = e.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in retrieve BenefitComponentBO method in benefitComponentBuilder",
					errorParams, e);
		}
	}
	/**
	 * This method gets spsMappingBo businessObject as input parameters from
	 * BusinessService and pass the businessObeject to the
	 * SPSMappingAdapterManager. SPSMappingAdapterManager returns search results
	 * and this result are converted to spsMappingBo businessObject and return
	 * it to the BusinessService
	 * 
	 * @param mappingBO
	 * @return mappingBO
	 *  @throws SevereException
	 */
	public SPSMappingBO retrieve(SPSMappingBO mappingBO) throws SevereException {
		SPSMappingAdapterManager spsMappingAdapterManager = new SPSMappingAdapterManager();
		SearchResults searchResults = null;

		try {
			searchResults = spsMappingAdapterManager
					.retrieveSPSMapping(mappingBO);
			if (null != searchResults
					&& searchResults.getSearchResultCount() > 0) {
				mappingBO = (SPSMappingBO) searchResults.getSearchResults()
						.get(0);
			}
		} catch (AdapterException e) {
			List errorParams = new ArrayList();
			String obj = e.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in retrieve SPSMappingBO method in SPSMappingBusinessObjectBuilder",
					errorParams, e);
		}
		return mappingBO;
	}
	/**
	 * This method is used to build the audit object with user
	 * @param user
	 * @return audit
	 */
	private Audit getAudit(User user){
		AuditFactory auditFactory = (AuditFactory) ObjectFactory
        .getFactory(ObjectFactory.AUDIT);
		Audit audit = auditFactory.getAudit(user);
		return audit;
	}
		
	/**
	 * this method used to search sps mapping values and it returns list
	 * @param mappingBO 
	 * @return list
	 *  @throws SevereException
	 */
	public List locateSpsMaintain(SPSMappingBO mappingBO)throws SevereException{
		SPSMappingAdapterManager sPSMappingAdapterManager =new SPSMappingAdapterManager();
		try {
		List searchList = new ArrayList();
		searchList = sPSMappingAdapterManager.locate(mappingBO);
		return searchList;
		} catch (AdapterException e) {
			List errorParams = new ArrayList();
			String obj = e.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locateSpsMaintain method in SPSMappingBusinessObjectBuilder",
					errorParams, e);
		}
	}
	/**
	 * this method is used to retrieve the spsmapping values 
	 * @param
	 * @return list
	 *  @throws SevereException
	 */
	public List retrieveSPSMapping(String status)throws SevereException{
		SPSMappingAdapterManager sPSMappingAdapterManager =new SPSMappingAdapterManager();
		try {
			List searchList = new ArrayList();
			searchList = sPSMappingAdapterManager.retrieveSPSMappingForDataFeed(status);
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
	 * this method is used to retrieve the spsmapping values 
	 * @param
	 * @return list
	 *  @throws SevereException
	 */
		SPSMappingAdapterManager sPSMappingAdapterManager =new SPSMappingAdapterManager();
	public List retrieveNASPSMapping()throws SevereException{
		try {
			List searchList = new ArrayList();
			searchList = sPSMappingAdapterManager.retrieveNASPSMappingForDataFeed();
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
}
