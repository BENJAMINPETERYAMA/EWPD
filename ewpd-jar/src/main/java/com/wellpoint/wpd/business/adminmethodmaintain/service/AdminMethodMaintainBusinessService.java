/*
 * Created on Oct 4, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.adminmethodmaintain.service;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.wpd.business.adminmethodmaintain.builder.AdminMethodMaintainBusinessObjectBuilder;
import com.wellpoint.wpd.business.framework.service.WPDBusinessService;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.adminmethodmaintain.bo.AdminMethodMaintainBO;
import com.wellpoint.wpd.common.adminmethodmaintain.bo.ConfigurationBO;
import com.wellpoint.wpd.common.adminmethodmaintain.bo.ReferenceGroupBO;
import com.wellpoint.wpd.common.adminmethodmaintain.bo.RequiredParamBO;
import com.wellpoint.wpd.common.adminmethodmaintain.request.AdminMethodCreateRequest;
import com.wellpoint.wpd.common.adminmethodmaintain.request.AdminMethodDeleteRequest;
import com.wellpoint.wpd.common.adminmethodmaintain.request.AdminMethodEditRequest;
import com.wellpoint.wpd.common.adminmethodmaintain.request.AdminMethodSearchRequest;
import com.wellpoint.wpd.common.adminmethodmaintain.request.AdminMethodViewRequest;
import com.wellpoint.wpd.common.adminmethodmaintain.response.AdminMethodCreateResponse;
import com.wellpoint.wpd.common.adminmethodmaintain.response.AdminMethodDeleteResponse;
import com.wellpoint.wpd.common.adminmethodmaintain.response.AdminMethodEditResponse;
import com.wellpoint.wpd.common.adminmethodmaintain.response.AdminMethodSearchResponse;
import com.wellpoint.wpd.common.adminmethodmaintain.response.AdminMethodViewResponse;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author U17066
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 * 
 *  
 */

public class AdminMethodMaintainBusinessService extends WPDBusinessService {

	AdminMethodMaintainBusinessObjectBuilder adminMethodMaintainBusinessObjectBuilder = new AdminMethodMaintainBusinessObjectBuilder();

	AdminMethodCreateResponse adminMethodCreateResponse = null;
	
	/**
	 * Method to create Admin Method
	 */
	public WPDResponse execute(AdminMethodCreateRequest request)
			throws ServiceException {
		Logger
				.logInfo("AdminMethodMaintainBusinessService - Create Admin Methods");
		adminMethodMaintainBusinessObjectBuilder = new AdminMethodMaintainBusinessObjectBuilder();

		try {
			adminMethodCreateResponse = (AdminMethodCreateResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.CREATE_ADMINMETHOD_RESPONSE);

			AdminMethodMaintainBO adminMethodMaintainBO = new AdminMethodMaintainBO();
			ConfigurationBO configurationBO = new ConfigurationBO();
			
			populateAdminMethodBO(request, adminMethodMaintainBO);
			if (!request.getConfigurationList().isEmpty()) {
				populateReferenceBO(request, configurationBO);
			}
			
			boolean status = adminMethodMaintainBusinessObjectBuilder
					.createAdminMethod(adminMethodMaintainBO);

			if (status) {
				adminMethodCreateResponse.addMessage(new InformationalMessage(
						WebConstants.ADMIN_METHOD_CREATE_SUCCESS));
				adminMethodCreateResponse.setAdminMethodSysId(adminMethodMaintainBO.getAdminMethodSysId());
				adminMethodCreateResponse.setAdminMethodDesc(adminMethodMaintainBO.getDescription());
				adminMethodCreateResponse.setAdminMethodSysIdAll(adminMethodMaintainBO.getAdminMethodSysIdList());
				
			}

			adminMethodCreateResponse.setStatus(status);
		} catch (SevereException ad) {
			List errorParams = new ArrayList(2);
			String obj = ad.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new ServiceException(
					"Exception occured in execute(AdminMethodCreateRequest request) method in AdminMethodMaintainBusinessService",
					errorParams, ad);
		} catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new ServiceException(
					"Exception occured in execute(AdminMethodCreateRequest request) method in AdminMethodMaintainBusinessService",
					null, ex);

		}
		Logger
				.logInfo("AdminMethodMaintainBusinessService - Returning execute(): Create Admin Method");
		return adminMethodCreateResponse;
	}

	/**
	 * @param Method
	 *            for Populate ReferenceBo
	 */
	private void populateReferenceBO(AdminMethodCreateRequest request,
			ConfigurationBO configurationBO) {
		configurationBO.setReferenceIDList(request.getConfigurationList());
	}

	/**
	 * @param Method
	 *            for Populate AdminMethodBO
	 */
	private void populateAdminMethodBO(AdminMethodCreateRequest request,
			AdminMethodMaintainBO adminMethodMaintainBO) {
		adminMethodMaintainBO.setAdminMethodNo(request.getAdminMethodNo());
		adminMethodMaintainBO.setDescription(request.getDescription());
		adminMethodMaintainBO.setConfiguration(request.getConfiguration());
		
		if (request.getProcessMethod() != null) {			
			adminMethodMaintainBO.setProcessMethod(request.getProcessMethod());	
		}
		
		adminMethodMaintainBO.setComments(request.getComments());
		adminMethodMaintainBO
				.setReferenceIDList(request.getConfigurationList());

		adminMethodMaintainBO.setCreatedUser(request.getUser().getUserId());
		adminMethodMaintainBO.setLastUpdatedUser(request.getUser().getUserId());
		adminMethodMaintainBO.setCreatedDate(request.getCreatedDate());
		adminMethodMaintainBO.setLastUpdatedDate(request.getLastUpdatedDate());
		adminMethodMaintainBO.setRequiredParamsList(request
				.getRequiredParamList());
	}

	/**
	 * @param Method
	 *            for Edit admin methods
	 * @return
	 * @throws SevereException
	 * @throws
	 */
	public WPDResponse execute(AdminMethodEditRequest request)
			throws ServiceException {

		Logger
				.logInfo("AdminMethodMaintainBusinessService - Edit Admin Methods");
		AdminMethodMaintainBusinessObjectBuilder adminMethodMaintainBusinessObjectBuilder = new AdminMethodMaintainBusinessObjectBuilder();
		AdminMethodEditResponse adminMethodEditResponse = null;

		AdminMethodMaintainBO adminMethodMaintainBO = new AdminMethodMaintainBO();
		RequiredParamBO requiredParamBO = new RequiredParamBO();
		ReferenceGroupBO referenceGroupBO = new ReferenceGroupBO();
		ConfigurationBO configurationBO = new ConfigurationBO();
		List searchResultsList = new ArrayList();
		
		adminMethodMaintainBO
				.setAdminMethodSysId(request.getAdminMethodSysId());
		adminMethodMaintainBO.setComments(request.getComments());
		adminMethodMaintainBO.setDescription(request.getOldDescription());
		adminMethodMaintainBO.setAdminMethodNo(request.getAdminMethodNo());
		adminMethodMaintainBO.setProcessMethod(request.getSpsIdList());
		adminMethodMaintainBO.setLastUpdatedDate(request.getLastUpdatedDate());
				
		configurationBO.setAdminMethodNo(request.getAdminMethodNo());
		configurationBO.setAdminMethodDescription(request.getOldDescription());
		configurationBO.setAdminMethodSysId(request.getAdminMethodSysId());
		
		referenceGroupBO.setAdminMethodSysId(request.getAdminMethodSysId());
		referenceGroupBO.setAdminMethodNo(request.getAdminMethodNo());
		referenceGroupBO.setDescription(request.getOldDescription());
		
		requiredParamBO.setAdminMethodSysId(request.getAdminMethodSysId());
		requiredParamBO.setAdminMethodNo(request.getAdminMethodNo());
		requiredParamBO.setAdminMethodDesc(request.getOldDescription());
		
		try {
		
			//set dummy value because in XML key value is config Id
			configurationBO.setConfigID("dummy");
			boolean status = adminMethodMaintainBusinessObjectBuilder
					.deleteConfiguration(configurationBO, request.getUser());
			if (status) {
				adminMethodMaintainBO.setReferenceIDList(request
						.getConfigurationList());
			}			

			//referenceGroupBO.setDescription("dummy");
			boolean refDeletestatus = adminMethodMaintainBusinessObjectBuilder
					.deleteReferenceGroup(referenceGroupBO, request
							.getUser());
			if (refDeletestatus) {
				//set dummy value because in XML key value is group Id
				requiredParamBO.setGroupID(0);
				boolean groupStatus = adminMethodMaintainBusinessObjectBuilder
						.deleteRequiredParamGroup(requiredParamBO, request
								.getUser());
				if (groupStatus) {
					adminMethodMaintainBO.setReqParamGroups(request
							.getReqParamGroups());
				}
			}

			

			adminMethodEditResponse = (AdminMethodEditResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.EDIT_ADMIN_METHOD_RESPONSE);
			
			if(adminMethodMaintainBO.getProcessMethod()!=null && adminMethodMaintainBO.getProcessMethod().length()> 0) {
				
				String spsProcssingNoReference = (String) adminMethodMaintainBO.getProcessMethod();			
				String[] spsProcessingNoArray = spsProcssingNoReference.split("~");
				List spsIdList = new ArrayList();
				
				for(int i=0; i<spsProcessingNoArray.length;i++) {
					spsIdList.add(spsProcessingNoArray[i].toString()); 
				}
				adminMethodMaintainBO.setProcessMethodList(spsIdList);
			}
			
			
			boolean flag = false;
			adminMethodMaintainBO.setDescription(request.getDescription());			
			if (request.getAdminMethodSysIdAll() != null && request.getAdminMethodSysIdAll().length()>0) {	
				String[] adminMethodSysIdAllArray = request.getAdminMethodSysIdAll().split("~");
				
				for (int i=0;i<adminMethodSysIdAllArray.length;i++){
					
					adminMethodMaintainBO.setAdminMethodSysId(Integer.parseInt(adminMethodSysIdAllArray[i]));	
					adminMethodMaintainBO.setProcessMethod(adminMethodMaintainBO.getProcessMethodList().get(i).toString());
					
					flag = adminMethodMaintainBusinessObjectBuilder
						.editAdminMethod(adminMethodMaintainBO, request.getUser());				   		   	
				}				
			}
		
			if (flag) {
				adminMethodEditResponse.addMessage(new InformationalMessage(
						WebConstants.ADMIN_METHOD_EDIT_SUCCESS));
			}
			
			adminMethodEditResponse.setStatus(flag);
			Logger
					.logInfo("AdminMethodMaintainBusinessService - Returning execute(): Edit Admin Method");
			return adminMethodEditResponse;
		} catch (SevereException ad) {
			List errorParams = new ArrayList(2);
			String obj = ad.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new ServiceException(
					"Exception occured in execute(AdminMethodEditRequest request) method in AdminMethodMaintainBusinessService",
					errorParams, ad);
		} catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new ServiceException(
					"Exception occured in execute(AdminMethodEditRequest request) method in AdminMethodMaintainBusinessService",
					null, ex);

		}

	}

	/**
	 * @param Method
	 *            for View admin methods
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(AdminMethodViewRequest request)
			throws ServiceException {

		Logger
				.logInfo("AdminMethodMaintainBusinessService - View Admin Method");
		AdminMethodMaintainBusinessObjectBuilder adminMethodMaintainBusinessObjectBuilder = new AdminMethodMaintainBusinessObjectBuilder();
		AdminMethodViewResponse adminMethodViewResponse = null;
		AdminMethodMaintainBO adminMethodMaintainBO = new AdminMethodMaintainBO();
		List searchResultsList = new ArrayList();
		adminMethodMaintainBO
				.setAdminMethodNo(request.getAdminMethodNo());
		adminMethodMaintainBO.setAdminMethodDesc(request.getAdminMethodDesc());
		adminMethodMaintainBO.setAdminMethodSysId(request.getAdminMethodSysId());
		adminMethodMaintainBO.setAdminMethodSysIdAll(request.getAdminMethodDSysIdAll());
		
		adminMethodViewResponse = (AdminMethodViewResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.VIEW_ADMIN_METHOD_RESPONSE);

		try {

			adminMethodMaintainBO = adminMethodMaintainBusinessObjectBuilder
					.viewAdminMethod(adminMethodMaintainBO);

		}

		catch (SevereException ad) {
			List errorParams = new ArrayList(2);
			String obj = ad.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new ServiceException(
					"Exception occured in execute(AdminMethodViewRequest request) method in AdminMethodMaintainBusinessService",
					errorParams, ad);
		} catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new ServiceException(
					"Exception occured in execute(AdminMethodViewRequest request) method in AdminMethodMaintainBusinessService",
					null, ex);

		}

		adminMethodViewResponse.setAdminMethodMaintainBO(adminMethodMaintainBO);
		Logger
				.logInfo("AdminMethodMaintainBusinessService - Returning execute(): View Admin Method");
		return adminMethodViewResponse;
	}

	/**
	 * Method for deleting admin methods
	 * 
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(AdminMethodDeleteRequest request)
			throws ServiceException {

		Logger
				.logInfo("AdminMethodMaintainBusinessService - Deleting Admin Methods");

		AdminMethodMaintainBusinessObjectBuilder adminMethodMaintainBusinessObjectBuilder = new AdminMethodMaintainBusinessObjectBuilder();
		List processMethodList = new ArrayList();
		String[] processMethodArray;
		List messageList = new ArrayList();		
		AdminMethodMaintainBO adminMethodMaintainBO = new AdminMethodMaintainBO();
		// Setting the adminm method no and desc to the BO
		adminMethodMaintainBO
				.setAdminMethodNo(request.getAdminMethodNo());
		adminMethodMaintainBO
		        .setAdminMethodDesc(request.getAdminMethodDesc());
		adminMethodMaintainBO.setProcessMethod(request.getProcessingMethodIds());
				
		AdminMethodDeleteResponse adminMethodDeleteResponse = null;
		adminMethodDeleteResponse = (AdminMethodDeleteResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.DELETE_ADMIN_METHOD_RESPONSE);

		try {
			String message = adminMethodMaintainBusinessObjectBuilder
					.deleteAdminMethod(adminMethodMaintainBO, request.getUser());

			// Checks condition whether any mappings are created with the
			// current admin method. if mapping is created, then display an
			// error message to the user

			if ("Mapped".equalsIgnoreCase(message)) {
				adminMethodDeleteResponse.addMessage(new ErrorMessage(
						BusinessConstants.MSG_ADMN_METHOD_MAPPED));
			//			 //Checks condition whether the current admin method is coded in
			// any entities. if yes, then display an error message to the user
			}else if ("Coded".equalsIgnoreCase(message)) {
				adminMethodDeleteResponse.addMessage(new ErrorMessage(
						BusinessConstants.MSG_ADMN_METHOD_CODED));
			//Checks condition whether the current admin method was deleted
			// sucessfully. if yes, then display an informational message to the
			// user
			}else if("CodedAndMapped".equalsIgnoreCase(message)){
				
				adminMethodDeleteResponse.addMessage(new ErrorMessage(
						BusinessConstants.MSG_ADMN_METHOD_MAPPED));
				
				adminMethodDeleteResponse.addMessage(new ErrorMessage(
						BusinessConstants.MSG_ADMN_METHOD_CODED));
				
				
			}else if ("Sucess".equalsIgnoreCase(message)) {
				
				
				InformationalMessage informationalMessage = new InformationalMessage(
						BusinessConstants.MSG_ADMN_METHOD_DELETED);
				informationalMessage.setParameters(new String[]{adminMethodMaintainBO.getAdminMethodNo(),adminMethodMaintainBO.getAdminMethodDesc()});
//				informationalMessage.setParameters(new String[]{adminMethodMaintainBO.getAdminMethodDesc()});
				messageList.add(informationalMessage);

				addMessagesToResponse(adminMethodDeleteResponse, messageList);
				//adminMethodDeleteResponse.setSuccess(true);
				adminMethodDeleteResponse.setMessages(messageList);
				
			}
			
		} catch (SevereException se) {

			List errorParams = new ArrayList(2);
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new ServiceException(
					"Exception occured in execute(AdminMethodDeleteRequest request) method in AdminMethodMaintainBusinessService",
					errorParams, se);
		}
		Logger
				.logInfo("AdminMethodMaintainBusinessService - Returning execute(): Delete Admin Method");
		return adminMethodDeleteResponse;
	}

	private void addMessagesToResponse(WPDResponse response, List messages) {
		if (null == messages || messages.size() == 0)
			return;
		if (null == response.getMessages())
			response.setMessages(messages);
		else
			response.getMessages().addAll(messages);
	}
	
	/**
	 * @param Method
	 *            to serach Admin Method
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(AdminMethodSearchRequest request)
			throws ServiceException {

		Logger
				.logInfo("AdminMethodMaintainBusinessService - Searching Admin Methods");
		AdminMethodMaintainBusinessObjectBuilder adminMethodMaintainBusinessObjectBuilder = new AdminMethodMaintainBusinessObjectBuilder();
		AdminMethodSearchResponse adminMethodSearchResponse = null;
		AdminMethodMaintainBO adminMethodMaintainBO = new AdminMethodMaintainBO();
		List searchResultsList = new ArrayList();
		adminMethodMaintainBO.setAdminMethodNo(request.getAdminMethodNo());
		adminMethodMaintainBO.setDescription(request.getDescription());	
				
		List processMethodList = new ArrayList();
		String[] processMethodArray;
		
		if (request.getProcessMethod() != null && request.getProcessMethod().length()>0) {			
			
			processMethodArray=request.getProcessMethod().split("~");
			for (int i=0;i<processMethodArray.length;i++){
			   	processMethodList.add(processMethodArray[i]);
			}
			adminMethodMaintainBO.setProcessMethodList(processMethodList);
			
		} else if (request.getSelectedProcessMethod() !=null && request.getSelectedProcessMethod().length()>0){		
			processMethodList = spsIdList(request);			
			adminMethodMaintainBO.setProcessMethodList(processMethodList);
		}
		
		adminMethodMaintainBO.setSearchExisitng(request.isSearchExisitng());
		adminMethodSearchResponse = (AdminMethodSearchResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.SEARCH_ADMIN_METHOD_RESPONSE);
		
		try {

			SearchResults searchResults = adminMethodMaintainBusinessObjectBuilder
					.searchAdminMethod(adminMethodMaintainBO);
			searchResultsList = searchResults.getSearchResults();

			if ((null == searchResults.getSearchResults())
					|| searchResults.getSearchResultCount() <= 0) {
				adminMethodSearchResponse.addMessage(new InformationalMessage(
						BusinessConstants.SEARCH_RESULT_NOT_FOUND));
			} else if (searchResults.getSearchResultCount() > 100) {
				adminMethodSearchResponse.addMessage(new InformationalMessage(
						BusinessConstants.MSG_SEARCH_RESULT_EXCEEDS));
			}

		} catch (SevereException ad) {
			List errorParams = new ArrayList(2);
			String obj = ad.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new ServiceException(
					"Exception occured in execute(AdminMethodSearchRequest request) method in AdminMethodMaintainBusinessService",
					errorParams, ad);
		} catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new ServiceException(
					"Exception occured in execute(AdminMethodSearchRequest request) method in AdminMethodMaintainBusinessService",
					null, ex);
		}

		adminMethodSearchResponse.setSearchList(searchResultsList);
		Logger.logInfo("AdminMethodMaintainBusinessService - Returning execute(): Search Admin Method");
		return adminMethodSearchResponse;
	}
	
	private List spsIdList(AdminMethodSearchRequest request){
		
		List processMethodList = new ArrayList();
		String[] processMethodArray;
		String processMethod = WPDStringUtil.getStringFromTildaString(request.getSelectedProcessMethod(),2,1,2);
		int result =  processMethod.indexOf("~");
					if(result > 0) {
			String[] pr1 = processMethod.split("~");
			
			String spsId="";
			for (int i=0; i<pr1.length ; i++){					
				spsId = pr1[i]+":"+spsId;
			}
			
			processMethodArray = spsId.split(":");
			
		}else {				
			processMethodArray = processMethod.split(":");				
		}
	
		for (int i=0;i<processMethodArray.length;i++){
		   	processMethodList.add(processMethodArray[i]);
		}
		return processMethodList;
	}	
}
