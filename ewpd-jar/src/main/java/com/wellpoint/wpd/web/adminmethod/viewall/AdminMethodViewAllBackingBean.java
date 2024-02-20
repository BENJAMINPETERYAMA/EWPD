/*
 * Created on Oct 5, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.adminmethod.viewall;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.wellpoint.wpd.business.adminmethod.viewall.service.AdminmethodViewAllBusinessService;
import com.wellpoint.wpd.common.adminmethod.request.AdminmethodViewAllRequest;
import com.wellpoint.wpd.common.adminmethod.response.AdminmethodViewAllResponse;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WebConstants;
/**
 * @author u18739
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodViewAllBackingBean extends WPDBackingBean {

	
	private String adminMethodNoCriteria;

	private String adminMethodDescCriteria;

	private String processingMethodCriteria;
	
	private String configuration;

	private String comments;

	private List reqParamGroups; // List representing the required parametere
								 // group.

	private List searchAdminMethodList;

	private String createdUser;
	
	private String lastUpdatedUser;

	private Date createdDate;

	private Date lastUpdatedDate;
	
	private boolean requiredType;
	
	//Field added to provide the adminMethodNoCriteria highlight on page
	private boolean adminMethodNoReq;
	
	private List processingMethodTypes;

	private AdminmethodViewAllBusinessService adminmethodViewAllBusinessService;

	// Method to search the admin methods based on processing method/admin metho no: and desc
	public String searchAdminMethods(){
		
		AdminmethodViewAllRequest adminmethodViewAllRequest = (AdminmethodViewAllRequest) this.getServiceRequest(ServiceManager.SEARCH_ADMINMETHOD_VIEWALL_REQUEST);		
		
		// TO assign only one sps id. Corresponding to PPO.
		String [] str=processingMethodCriteria.split("~");
		adminmethodViewAllRequest.setProcessingMethods(str[0]);
		if(this.adminMethodNoCriteria != null && this.adminMethodNoCriteria.trim().length() != 0 ){
			try{
				Integer.parseInt(this.adminMethodNoCriteria.trim()); 
			}catch(Exception ex){
				addMessageToRequest(new ErrorMessage(
						WebConstants.ADMIN_MTHD_NMBR_NOT_NUMERIC));
				this.setAdminMethodNoReq(true);
				return "";
			}
		}
		adminmethodViewAllRequest.setAdminMethodNo(adminMethodNoCriteria);
		adminmethodViewAllRequest.setAdminMethodDescription(adminMethodDescCriteria);
		
		AdminmethodViewAllResponse adminmethodViewAllResponse = (AdminmethodViewAllResponse) this.executeService(adminmethodViewAllRequest);

		if (null != adminmethodViewAllResponse.getResultList() && adminmethodViewAllResponse.getResultList().size() > 0) {
			// Sorting the results based on the admin method no.
			Collections.sort(adminmethodViewAllResponse.getResultList());
			searchAdminMethodList = adminmethodViewAllResponse.getResultList();
		}		
		
		return "";
	}	
	/**
	 * @return Returns the requiredType.
	 */
	public boolean isRequiredType() {
		return requiredType;
	}

	/**
	 * @param requiredType
	 *            The requiredType to set.
	 */
	public void setRequiredType(boolean requiredType) {
		this.requiredType = requiredType;
	}
	/**
	 * @return Returns the processingMethodCriteria.
	 */
	public String getProcessingMethodCriteria() {
		return processingMethodCriteria;
	}
	/**
	 * @param processingMethodCriteria The processingMethodCriteria to set.
	 */
	public void setProcessingMethodCriteria(String processingMethodCriteria) {
		this.processingMethodCriteria = processingMethodCriteria;
	}
	/**
	 * @return Returns the adminMethodDescCriteria.
	 */
	public String getAdminMethodDescCriteria() {
		return adminMethodDescCriteria;
	}
	/**
	 * @param adminMethodDescCriteria The adminMethodDescCriteria to set.
	 */
	public void setAdminMethodDescCriteria(String adminMethodDescCriteria) {
		this.adminMethodDescCriteria = adminMethodDescCriteria;
	}
	/**
	 * @return Returns the adminMethodNoCriteria.
	 */
	public String getAdminMethodNoCriteria() {
		return adminMethodNoCriteria;
	}
	/**
	 * @param adminMethodNoCriteria The adminMethodNoCriteria to set.
	 */
	public void setAdminMethodNoCriteria(String adminMethodNoCriteria) {
		this.adminMethodNoCriteria = adminMethodNoCriteria;
	}
	/**
	 * @return Returns the searchAdminMethodList.
	 */
	public List getSearchAdminMethodList() {
		return searchAdminMethodList;
	}
	/**
	 * @param searchAdminMethodList The searchAdminMethodList to set.
	 */
	public void setSearchAdminMethodList(
			List searchAdminMethodList) {
		this.searchAdminMethodList = searchAdminMethodList;
	}
	/**
	 * @return Returns the adminMethodNoReq.
	 */
	public boolean isAdminMethodNoReq() {
		return adminMethodNoReq;
	}
	/**
	 * @param adminMethodNoReq The adminMethodNoReq to set.
	 */
	public void setAdminMethodNoReq(boolean adminMethodNoReq) {
		this.adminMethodNoReq = adminMethodNoReq;
	}
}
