/*
 * Created on Oct 4, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.adminmethodmaintain.builder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.adminmethodmaintain.adapter.AdminMethodMaintainAdapterManager;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.common.adapter.SequenceAdapterManager;
import com.wellpoint.wpd.common.adminmethodmaintain.bo.AdminMethodMaintainBO;
import com.wellpoint.wpd.common.adminmethodmaintain.bo.ConfigurationBO;
import com.wellpoint.wpd.common.adminmethodmaintain.bo.ReferenceGroupBO;
import com.wellpoint.wpd.common.adminmethodmaintain.bo.RequiredParamBO;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.web.util.WPDStringUtil;

/**
 * @author U17066
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodMaintainBusinessObjectBuilder {

	AdminMethodMaintainAdapterManager adminMethodMaintainAdapterManager = new AdminMethodMaintainAdapterManager();

	SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();

	/**
	 * @param Method
	 *            to create Admin method
	 * @return
	 * @throws SevereException
	 */
	public boolean createAdminMethod(AdminMethodMaintainBO adminMethodMaintainBO)
			throws SevereException {
		Logger
				.logInfo("AdminMethodMaintainBusinessObjectBuilder - Create Admin Methods");
		AdapterServicesAccess serviceAccessEWPDB = AdapterUtil
				.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		ConfigurationBO configurationBO = new ConfigurationBO();
		ReferenceGroupBO referenceGroupBO = new ReferenceGroupBO();
		RequiredParamBO requiredParamBO = new RequiredParamBO();

		configurationBO.setCreatedUser(adminMethodMaintainBO.getCreatedUser());
		configurationBO.setLastUpdatedUser(adminMethodMaintainBO
				.getLastUpdatedUser());

		referenceGroupBO.setCreatedUser(adminMethodMaintainBO.getCreatedUser());
		referenceGroupBO.setLastUpdatedUser(adminMethodMaintainBO
				.getLastUpdatedUser());
	

		requiredParamBO.setCreatedUser(adminMethodMaintainBO.getCreatedUser());
		requiredParamBO.setLastUpdatedUser(adminMethodMaintainBO
				.getLastUpdatedUser());

		try {
			AdapterUtil.beginTransaction(serviceAccessEWPDB);
			AdapterUtil.logBeginTranstn(transactionId, this,
					"adminMethodMaintainBO ");
			
			SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();			
			
			String spsProcssingNoReference = (String) adminMethodMaintainBO.getProcessMethod();			
			String[] spsProcessingNoArray = spsProcssingNoReference.split("~");
			
			List adminMethodsysIdList = new ArrayList();
			if(spsProcessingNoArray !=null && spsProcessingNoArray.length!=0) {
						
				for(int i=0; i<spsProcessingNoArray.length;i++) {			
				
				int adminMthodSysID = sequenceAdapterManager
						.getNextAdminMethodSysIDSequence();
								
				adminMethodsysIdList.add(Integer.toString(adminMthodSysID));
				
				adminMethodMaintainBO.setAdminMethodSysId(adminMthodSysID);
				adminMethodMaintainBO.setAdminMethodSysIdList(adminMethodsysIdList);
				configurationBO.setAdminMethodSysId(adminMthodSysID);	
				referenceGroupBO.setAdminMethodSysId(adminMthodSysID);
				referenceGroupBO.setAdminMethodNo(adminMethodMaintainBO.getAdminMethodNo());
				requiredParamBO.setAdminMethodSysId(adminMthodSysID);
				requiredParamBO.setAdminMethodNo(adminMethodMaintainBO.getAdminMethodNo());
				requiredParamBO.setAdminMethodDesc(adminMethodMaintainBO.getAdminMethodDesc());
				
				adminMethodMaintainBO.setProcessMethod(spsProcessingNoArray[i].toString());									
			
				boolean adminMethodStatus = adminMethodMaintainAdapterManager
					.createAdminMethod(adminMethodMaintainBO);
			
				if (!adminMethodMaintainBO.getReferenceIDList().isEmpty()) {
					for (int j = 0; j < adminMethodMaintainBO.getReferenceIDList()
							.size(); j++) {
	
						configurationBO.setAdminMethodSysId(adminMethodMaintainBO
								.getAdminMethodSysId());
						configurationBO.setConfigID(adminMethodMaintainBO
								.getReferenceIDList().get(j).toString());
						boolean configurationStatus = adminMethodMaintainAdapterManager
								.createConfiguration(configurationBO);	
					}
				}
				
				if (!adminMethodMaintainBO.getRequiredParamsList().isEmpty()) {
					for (int k = 0; k < adminMethodMaintainBO
							.getRequiredParamsList().size(); k++) {
	
						int groupID = sequenceAdapterManager
								.getNextAdminMethodGroupIDSequence();
						referenceGroupBO.setGroupID(groupID);
						referenceGroupBO.setDescription("dummy");
						/*
						 * Persist for the group table. group id and admin method
						 * sys id persisted.
						 */
						boolean groupStatus = adminMethodMaintainAdapterManager
								.createGroup(referenceGroupBO);
	
						String groupReference = (String) adminMethodMaintainBO
								.getRequiredParamsList().get(k);
	
						String[] groupReferenceArray = groupReference.split(",");
	
						for (int j = 0; j < groupReferenceArray.length; j++) {
							requiredParamBO.setReferenceID(groupReferenceArray[j]);
							requiredParamBO.setGroupID(groupID);
							boolean requiredParamStatus = adminMethodMaintainAdapterManager
									.createRequiredParam(requiredParamBO);
	
						}
					  }
				   }
				}
			}

			AdapterUtil.endTransaction(serviceAccessEWPDB);
			AdapterUtil.logTheEndTransaction(transactionId, this,
					"adminMethodMaintainBO");
			
		} catch (AdapterException ex) {
			
			AdapterUtil.abortTransaction(serviceAccessEWPDB);
			AdapterUtil.logAbortTxn(transactionId, this,
					"adminMethodMaintainBO");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in AdminMethodMaintainBusinessObjectBuilder for Create Admin Method",
					errorParams, ex);
		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(serviceAccessEWPDB);
			AdapterUtil.logAbortTxn(transactionId, this,
					"adminMethodMaintainBO");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in AdminMethodMaintainBusinessObjectBuilder for Create Admin Method",
					errorParams, ex);
		} catch (Exception e) {
			AdapterUtil.abortTransaction(serviceAccessEWPDB);
			AdapterUtil.logAbortTxn(transactionId, this,
					"adminMethodMaintainBO");
			throw new SevereException("Unhandled exception is caught", null, e);
		}
		Logger
				.logInfo("AdminMethodMaintainBusinessObjectBuilder - Returning execute(): Create Admin Method");
		return true;
	}
	
	

	/**
	 * @param Method
	 *            for Editing Admin Method
	 * @return
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public boolean editAdminMethod(AdminMethodMaintainBO adminMethodMaintainBO,
			User user) throws SevereException {
		Logger
				.logInfo("AdminMethodMaintainBusinessObjectBuilder - Edit Admin Methods");

		ConfigurationBO configurationBO = new ConfigurationBO();
		ReferenceGroupBO referenceGroupBO = new ReferenceGroupBO();
		RequiredParamBO requiredParamBO = new RequiredParamBO();

		configurationBO.setCreatedUser(user.getUserId());
		configurationBO.setLastUpdatedUser(user.getUserId());

		referenceGroupBO.setCreatedUser(user.getUserId());
		referenceGroupBO.setLastUpdatedUser(user.getUserId());

		referenceGroupBO.setAdminMethodSysId(adminMethodMaintainBO.getAdminMethodSysId());
		referenceGroupBO.setAdminMethodNo(adminMethodMaintainBO.getAdminMethodNo());

		requiredParamBO.setCreatedUser(user.getUserId());
		adminMethodMaintainBO.setLastUpdatedUser(user.getUserId());		
		
		if (adminMethodMaintainBO.getReferenceIDList() != null) {
			for (int i = 0; i < adminMethodMaintainBO.getReferenceIDList()
					.size(); i++) {

				configurationBO.setAdminMethodSysId(adminMethodMaintainBO
						.getAdminMethodSysId());
				
				configurationBO.setConfigID(adminMethodMaintainBO
						.getReferenceIDList().get(i).toString());

				try {
					boolean configurationStatus = adminMethodMaintainAdapterManager
							.createConfiguration(configurationBO);
					
				} catch (AdapterException ad) {
					List errorParams = new ArrayList(2);
					String obj = ad.getClass().getName();
					errorParams.add(obj);
					errorParams.add(obj.getClass().getName());
					throw new SevereException(
							"Exception occured in AdminMethodMaintainBusinessObjectBuilder for editAdminMethod when inserting Configuration list",
							errorParams, ad);
				} catch (Exception ex) {
					List errorParams = new ArrayList(2);
					String obj = ex.getClass().getName();
					errorParams.add(obj);
					errorParams.add(obj.getClass().getName());
					throw new SevereException(
							"Exception occured in AdminMethodMaintainBusinessObjectBuilder for editAdminMethod when inserting Configuration list",
							null, ex);
				}

			}
		}

		if (adminMethodMaintainBO.getReqParamGroups() != null
				&& adminMethodMaintainBO.getReqParamGroups().size() != 0) {
			for (int i = 0; i < adminMethodMaintainBO.getReqParamGroups()
					.size(); i++) {

				int groupID = sequenceAdapterManager
						.getNextAdminMethodGroupIDSequence();
				referenceGroupBO.setGroupID(groupID);
				referenceGroupBO.setDescription("dummy");

				try {
					/*
					 * Persist for the group table. group id and admin method
					 * sys id persisted.
					 */

					boolean groupStatus = adminMethodMaintainAdapterManager
							.createGroup(referenceGroupBO);
				} catch (AdapterException ad) {
					List errorParams = new ArrayList(2);
					String obj = ad.getClass().getName();
					errorParams.add(obj);
					errorParams.add(obj.getClass().getName());
					throw new SevereException(
							"Exception occured in AdminMethodMaintainBusinessObjectBuilder editAdminMethod when inserting Required Parameter Group",
							errorParams, ad);
				}

				String groupReference = (String) adminMethodMaintainBO
						.getReqParamGroups().get(i);

				String[] groupReferenceArray = groupReference.split(",");

				for (int j = 0; j < groupReferenceArray.length; j++) {
					requiredParamBO.setReferenceID(groupReferenceArray[j]);
					requiredParamBO.setGroupID(groupID);
					try {
						boolean requiredParamStatus = adminMethodMaintainAdapterManager
								.createRequiredParam(requiredParamBO);
					} catch (AdapterException ad) {
						List errorParams = new ArrayList(2);
						String obj = ad.getClass().getName();
						errorParams.add(obj);
						errorParams.add(obj.getClass().getName());
						throw new SevereException(
								"Exception occured in AdminMethodMaintainBusinessObjectBuilder editAdminMethod method when inserting Required Parameter Group",
								errorParams, ad);
					}

				}

			}

		}

		boolean groupStatus;
		try {
			groupStatus = adminMethodMaintainAdapterManager.updateAdminMethod(
					adminMethodMaintainBO, user);
			return groupStatus;
		} catch (AdapterException ad) {
			List errorParams = new ArrayList(2);
			String obj = ad.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate editAdminMethod method when updating Admin Method in AdminMethodMaintainBusinessObjectBuilder",
					errorParams, ad);
		} catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate editAdminMethod method when Admin Method in AdminMethodMaintainBusinessObjectBuilder",
					null, ex);
		}

	}

	/**
	 * @param Method
	 *            for Searching Admin Methods
	 * @return
	 * @throws SevereException
	 */
	public SearchResults searchAdminMethod(
			AdminMethodMaintainBO adminMethodMaintainBO) throws SevereException {
		Logger
				.logInfo("AdminMethodMaintainBusinessObjectBuilder - Retrieving Admin Method");
		try {
			 adminMethodMaintainBO.setDescription((WPDStringUtil.addDelimiterForAMDesc( adminMethodMaintainBO.getDescription())));
			 
			return adminMethodMaintainAdapterManager
					.retrieveAdminMethod(adminMethodMaintainBO);
		} catch (AdapterException ad) {
			List errorParams = new ArrayList(2);
			String obj = ad.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate searchAdminMethod method in AdminMethodMaintainBusinessObjectBuilder",
					errorParams, ad);
		} catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate searchAdminMethod method in AdminMethodMaintainBusinessObjectBuilder",
					null, ex);
		}
	}

	/**
	 * Method for deleting an admin method
	 * 
	 * @param AdminMethodMaintainBO
	 * @throws SevereException
	 */
	public String deleteAdminMethod(
			AdminMethodMaintainBO adminMethodMaintainBO, User user)
			throws SevereException {
		Logger
				.logInfo("AdminMethodMaintainBusinessObjectBuilder - Delete Admin Method");
		try {
			adminMethodMaintainBO.setDescription(adminMethodMaintainBO.getAdminMethodDesc());
			// This method checks whether any mappings are creted with the
			// current admin method
			String spsId = adminMethodMaintainBO.getProcessMethod().replaceAll("~",",");
			
			int deleteValue= adminMethodMaintainAdapterManager.isAdminMethodMapped(Integer.parseInt(adminMethodMaintainBO.getAdminMethodNo()), 
					adminMethodMaintainBO.getAdminMethodDesc(),spsId);
				
		    if (deleteValue==AdminMethodMaintainBO.adminMethodMapped)
				return "Mapped";
			else if (deleteValue==AdminMethodMaintainBO.adminMethodCoded)
				return "Coded";
			else if (deleteValue==AdminMethodMaintainBO.adminMethodCodedAndMapped)
				return "CodedAndMapped";
			// Method to delete an admin method.
			else 				
				if(adminMethodMaintainBO.getProcessMethod()!=null && adminMethodMaintainBO.getProcessMethod().length()>0) {
					String[] processMethodIdArray = adminMethodMaintainBO.getProcessMethod().split("~");
					for (int j = 0; j < processMethodIdArray.length; j++) {
						adminMethodMaintainBO.setProcessMethod(processMethodIdArray[j]);				
						adminMethodMaintainAdapterManager.deleteAdminMethod(adminMethodMaintainBO, user);
					}
				}
			return "Sucess";

		} catch (AdapterException ad) {

			List errorParams = new ArrayList(2);
			String obj = ad.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in deleteAdminMethod method in AdminMethodMaintainBusinessObjectBuilder while checking for isAdminMethodMapped",
					errorParams, ad);
		}

	}

	/**
	 * Method for View Admin Method
	 *  
	 */
	public AdminMethodMaintainBO viewAdminMethod(
			AdminMethodMaintainBO adminMethodMaintainBO) throws SevereException {
		Logger
				.logInfo("AdminMethodMaintainBusinessObjectBuilder - View Admin Method");
		try {

			ConfigurationBO configurationBO = new ConfigurationBO();
			List searchResultList;
						
			configurationBO.setAdminMethodSysId(adminMethodMaintainBO
					.getAdminMethodSysId());
			adminMethodMaintainBO
					.setConfigurationList(adminMethodMaintainAdapterManager
							.viewConfigList(configurationBO));
			
			RequiredParamBO requiredParamBO = new RequiredParamBO();
			requiredParamBO.setAdminMethodNo(adminMethodMaintainBO.getAdminMethodNo());
			requiredParamBO.setAdminMethodDesc(adminMethodMaintainBO.getAdminMethodDesc());
			requiredParamBO.setAdminMethodSysId(adminMethodMaintainBO.getAdminMethodSysId());
			
			adminMethodMaintainBO
					.setReqParamGroups(adminMethodMaintainAdapterManager
							.viewReqParamList(requiredParamBO));
			
			SearchResults searchResults = adminMethodMaintainAdapterManager
					.viewAdminMethod(adminMethodMaintainBO);
			
			searchResultList = searchResults.getSearchResults();
			if (searchResultList != null) {
				for (Iterator iter = searchResultList.iterator(); iter
						.hasNext();) {
					AdminMethodMaintainBO adminMethodMaintainBOList = (AdminMethodMaintainBO) iter
							.next();
					adminMethodMaintainBO
							.setAdminMethodNo(adminMethodMaintainBOList
									.getAdminMethodNo());
					adminMethodMaintainBO
							.setDescription(adminMethodMaintainBOList
									.getDescription());
					adminMethodMaintainBO.setComments(adminMethodMaintainBOList
							.getComments());
					adminMethodMaintainBO
							.setProcessMethodDesc(adminMethodMaintainBOList
									.getProcessMethodDesc());
					adminMethodMaintainBO
							.setCreatedUser(adminMethodMaintainBOList
									.getCreatedUser());
					adminMethodMaintainBO
							.setCreatedDate(adminMethodMaintainBOList
									.getCreatedDate());
					adminMethodMaintainBO
							.setLastUpdatedUser(adminMethodMaintainBOList
									.getLastUpdatedUser());
					adminMethodMaintainBO
							.setLastUpdatedDate(adminMethodMaintainBOList
									.getLastUpdatedDate());

				}
			}
			return adminMethodMaintainBO;
		} catch (AdapterException ad) {
			List errorParams = new ArrayList(2);
			String obj = ad.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in viewAdminMethod method in AdminMethodMaintainBusinessObjectBuilder",
					errorParams, ad);
		} catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured  viewAdminMethod method in AdminMethodMaintainBusinessObjectBuilder",
					null, ex);
		}

	}

	/**
	 * @param Method to delete Required Parameter Group
	 * @return
	 * @throws SevereException
	 */
	public boolean deleteRequiredParamGroup(RequiredParamBO requiredParamBO,
			User user) throws SevereException {
		Logger
				.logInfo("AdminMethodMaintainBusinessObjectBuilder - Delete Required Parameter Group Method");
		try {

			boolean adminMethodStatus = adminMethodMaintainAdapterManager
					.deleteRequiredParamGroup(requiredParamBO, user);

			return adminMethodStatus;
		} catch (AdapterException ad) {
			List errorParams = new ArrayList(2);
			String obj = ad.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured deleteRequiredParamGroup method in AdminMethodMaintainBusinessObjectBuilder",
					errorParams, ad);
		}

		catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured deleteRequiredParamGroup method in AdminMethodMaintainBusinessObjectBuilder",
					null, ex);
		}
	}

	/**
	 * @param Method to delete Confiuration List
	 * @param user
	 * @return
	 * @throws SevereException
	 */
	public boolean deleteConfiguration(ConfigurationBO configurationBO,
			User user) throws SevereException {
		Logger
				.logInfo("AdminMethodMaintainBusinessObjectBuilder - Delete deleteConfiguration Method");
		try {

			boolean adminMethodStatus = adminMethodMaintainAdapterManager
					.deleteConfiguration(configurationBO, user);
			return adminMethodStatus;
		} catch (AdapterException ad) {
			List errorParams = new ArrayList(2);
			String obj = ad.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured deleteConfiguration method in AdminMethodMaintainBusinessObjectBuilder",
					errorParams, ad);
		}

		catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured deleteConfiguration method in AdminMethodMaintainBusinessObjectBuilder",
					null, ex);
		}
	}

	/**
	 * @param Method to delete Reference Group
	 *
	 * @return
	 * @throws SevereException
	 */
	public boolean deleteReferenceGroup(ReferenceGroupBO referenceGroupBO,
			User user) throws SevereException {
		Logger
				.logInfo("AdminMethodMaintainBusinessObjectBuilder - deleteReferenceGroup Method");
		try {

			boolean status = adminMethodMaintainAdapterManager
					.deleteReferenceGroup(referenceGroupBO, user);
			return status;
		} catch (AdapterException ad) {
			List errorParams = new ArrayList(2);
			String obj = ad.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured  deleteReferenceGroup method in AdminMethodMaintainBusinessObjectBuilder",
					errorParams, ad);
		}

		catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured  deleteReferenceGroup method in AdminMethodMaintainBusinessObjectBuilder",
					null, ex);
		}
	}

}