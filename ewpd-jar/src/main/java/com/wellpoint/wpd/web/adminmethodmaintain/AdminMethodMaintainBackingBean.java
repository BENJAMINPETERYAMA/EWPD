/*
 * Created on Oct 4, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.adminmethodmaintain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.component.html.HtmlDataTable;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import com.wellpoint.wpd.business.framework.bo.AuditFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
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
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.popup.bo.PopupFilterBO;
import com.wellpoint.wpd.common.popup.request.PopupRequest;
import com.wellpoint.wpd.common.popup.response.PopupResponse;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;
import com.wellpoint.wpd.common.framework.messages.Message;

/**
 * @author U17066
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

public class AdminMethodMaintainBackingBean extends WPDBackingBean {

	// Contains the required parameter.separated by dollar.It contains all the
	// req parameter.
	private String reqParams;

	// contains all req paramter

	private String reqParamsAll;

	private HtmlDataTable createDataTable;
	
	private HtmlDataTable editDataTable;

	private int selectedRowIndex = -1;

	private int selectedRowNumber;

	private boolean adminMethodNoReq;

	private boolean descriptionReq;

	private boolean processMethodReq;

	private String requiredParameter;

	private String hiddenDescription;

	private String hiddenConfiguration;

	private String adminMethodNo;

	private String description;

	private String hiddenComments;

	private String hiddenReqParamsAll;

	private String adminMethoddescription;

	private String processMethodDesc;

	private String locateAdminMethodNo;

	private String adminMethodSysIdAll;

	private String selectedProcessMethod;

	private boolean commentsReq;
	
	private boolean viewableForUser;
	
	private boolean editableForUser;
	
	private boolean deletableForUser;
	private String printBreadCrumbText;

	private String loadAdminMethodForEditPrint;
	
	/**
	 * @return Returns the createDataTable.
	 */
	public HtmlDataTable getCreateDataTable() {
		return createDataTable;
	}
	/**
	 * @param createDataTable The createDataTable to set.
	 */
	public void setCreateDataTable(HtmlDataTable createDataTable) {
		this.createDataTable = createDataTable;
	}
	/**
	 * @return Returns the editDataTable.
	 */
	public HtmlDataTable getEditDataTable() {
		return editDataTable;
	}
	/**
	 * @param editDataTable The editDataTable to set.
	 */
	public void setEditDataTable(HtmlDataTable editDataTable) {
		this.editDataTable = editDataTable;
	}
	/**
	 * @return Returns the hiddenDescription.
	 */
	public String getHiddenDescription() {
		return hiddenDescription;
	}

	/**
	 * @param hiddenDescription
	 *            The hiddenDescription to set.
	 */
	public void setHiddenDescription(String hiddenDescription) {
		this.hiddenDescription = hiddenDescription;
	}

	/**
	 * @return Returns the requiredParameter.
	 */
	public String getRequiredParameter() {
		return requiredParameter;
	}

	/**
	 * @param requiredParameter
	 *            The requiredParameter to set.
	 */
	public void setRequiredParameter(String requiredParameter) {
		this.requiredParameter = requiredParameter;
	}

	/**
	 * @return Returns the adminMethodNoReq.
	 */
	public boolean isAdminMethodNoReq() {
		return adminMethodNoReq;
	}

	/**
	 * @param adminMethodNoReq
	 *            The adminMethodNoReq to set.
	 */
	public void setAdminMethodNoReq(boolean adminMethodNoReq) {
		this.adminMethodNoReq = adminMethodNoReq;
	}

	/**
	 * @return Returns the descriptionReq.
	 */
	public boolean isDescriptionReq() {
		return descriptionReq;
	}

	/**
	 * @param descriptionReq
	 *            The descriptionReq to set.
	 */
	public void setDescriptionReq(boolean descriptionReq) {
		this.descriptionReq = descriptionReq;
	}

	/** WAS 6.0 Migration changes - duplicate method exist
	 * @return Returns the processingMethodList.
	 
	public List getProcessingMethodList() {
		return processingMethodList;
	} */

	/**
	 * @param processingMethodList
	 *            The processingMethodList to set.
	 */
	public void setProcessingMethodList(List processingMethodList) {
		this.processingMethodList = processingMethodList;
	}

	/**
	 * @return Returns the processMethodReq.
	 */
	public boolean isProcessMethodReq() {
		return processMethodReq;
	}

	/**
	 * @param processMethodReq
	 *            The processMethodReq to set.
	 */
	public void setProcessMethodReq(boolean processMethodReq) {
		this.processMethodReq = processMethodReq;
	}

	/**
	 * @return Returns the reqParams.
	 */
	public String getReqParams() {
		return reqParams;
	}

	/**
	 * @param reqParams
	 *            The reqParams to set.
	 */
	public void setReqParams(String reqParams) {
		this.reqParams = reqParams;
	}

	/**
	 * @return Returns the reqParamsAll.
	 */
	public String getReqParamsAll() {
		return reqParamsAll;
	}

	/**
	 * @param reqParamsAll
	 *            The reqParamsAll to set.
	 */
	public void setReqParamsAll(String reqParamsAll) {
		this.reqParamsAll = reqParamsAll;
	}

	/**
	 * @return Returns the processMethodDesc.
	 */
	public String getProcessMethodDesc() {
		return processMethodDesc;
	}

	/**
	 * @param processMethodDesc
	 *            The processMethodDesc to set.
	 */
	public void setProcessMethodDesc(String processMethodDesc) {
		this.processMethodDesc = processMethodDesc;
	}

	private String processMethod;

	private String configuration;

	private List configurationList;

	private List reqParamList;

	/**
	 * @return Returns the configurationList.
	 */
	public List getConfigurationList() {
		return configurationList;
	}

	/**
	 * @param configurationList
	 *            The configurationList to set.
	 */
	public void setConfigurationList(List configurationList) {
		this.configurationList = configurationList;
	}

	private String comments;

	private String requiredParm;

	private List reqParamGroups = new ArrayList();

	private List reqParameterList = new ArrayList();

	private List reqParamEditList = new ArrayList();
	
	// group.

	private List searchResults;

	private String createdUser;

	private String lastUpdatedUser;

	private Date createdDate;

	private Date lastUpdatedDate;	

	private List processingMethodList;

	private int adminMethodSysId;

	private String loadAdminMethod;

	public static final String REQ_PARAM_LIST = "REQ_PARAM_LIST";

	public static final String REQ_PARAM_ID_LIST = "REQ_PARAM_ID_LIST";
	
	private String reqParamModifiedStaus;

	/**
	 * @return Returns the loadAdminMethod.
	 */
	public String getLoadAdminMethod() {	
		return retriveAdminMethod();
	}

	/**
	 * @param loadAdminMethod
	 *            The loadAdminMethod to set.
	 */
	public void setLoadAdminMethod(String loadAdminMethod) {
		this.loadAdminMethod = loadAdminMethod;
	}

	/**
	 * @return Returns the adminMethodSysId.
	 */
	public int getAdminMethodSysId() {
		return adminMethodSysId;
	}

	/**
	 * @param adminMethodSysId
	 *            The adminMethodSysId to set.
	 */
	public void setAdminMethodSysId(int adminMethodSysId) {
		this.adminMethodSysId = adminMethodSysId;
	}

	/**
	 * Method to get Processing Method List.
	 */
	public List getprocessingMethodList() {
		Logger
				.logInfo(" AdminMethodMaintainBackingBean - Get Processing Method List");
		PopupRequest request = (PopupRequest) this
				.getServiceRequest(ServiceManager.POPUP_REQUEST);
		request.setQueryName("processingType");
		request.setPopAction(WebConstants.FILTER_ACTION);
		PopupResponse response = null;
		response = (PopupResponse) executeService(request);
		List processingList = new ArrayList();
		String spsValues;
		//processingList.add(new SelectItem("", ""));
		List values = response.getResultList();
		List newSPSValues = new ArrayList();
		getSession().setAttribute("ADMIN_METHOD_EDIT_SCREEN", "false");

		newSPSValues = parseSPSValues(values);

		for (int i = 0; i < newSPSValues.size(); i++) {
			PopupFilterBO popupFilterBO1 = (PopupFilterBO) newSPSValues.get(i);

			processingList.add(new SelectItem(popupFilterBO1.getKeyValue(),
					popupFilterBO1.getDescription()));
		}
		return processingList;
	}

	private List parseSPSValues(List values) {

		List newSPSValues = new ArrayList();

		for (int i = 0; i < values.size(); i++) {

			PopupFilterBO popupFilterBO1 = (PopupFilterBO) values.get(i);//0,2,4,6,
			i++;
			PopupFilterBO popupFilterBO2 = (PopupFilterBO) values.get(i);//1,3,5,
			popupFilterBO1.setKeyValue(popupFilterBO1.getKeyValue() + "~"
					+ popupFilterBO2.getKeyValue());
			newSPSValues.add(popupFilterBO1);
		}

		return newSPSValues;
	}

	/**
	 * @param processMethodListForCombo
	 *            The processMethodListForCombo to set.
	 */
	public void setprocessingMethodList(List processingMethodList) {
		this.processingMethodList = processingMethodList;
	}

	/**
	 * Method for Create an admin method
	 * 
	 * @return
	 */
	public String createAdminMethod() {

		if (!isvalidAdminMethod()) {
			
			reqParamGroups = getRequiredParamListFromSession();			
			setBreadCrumbText("Administration >> Admin method >> Create");
			return "";
			
		}        
		
		if (checkAdminMethodExists()) {
			addMessageToRequest(new ErrorMessage(
					WebConstants.ADMIN_MTHD_CREATE_UNIQUE_VALID));
			reqParamGroups = getRequiredParamListFromSession();
			setBreadCrumbText("Administration >> Admin method >> Create");
			return "";

		} 
	
			AdminMethodCreateRequest adminMethodCreateRequest = (AdminMethodCreateRequest) this
					.getServiceRequest(ServiceManager.CREATE_ADMINMETHOD_CREATE_REQUEST);

			populateRequest(adminMethodCreateRequest);

			AdminMethodCreateResponse adminMethodCreateResponse = (AdminMethodCreateResponse) this
					.executeService(adminMethodCreateRequest);

			if (adminMethodCreateResponse.isStatus()) {
				setAdminMethodSysId(adminMethodCreateResponse
						.getAdminMethodSysId());
				setHiddenDescription(adminMethodCreateResponse
						.getAdminMethodDesc());

				if (adminMethodCreateResponse.getAdminMethodSysIdAll() != null
						&& adminMethodCreateResponse.getAdminMethodSysIdAll()
								.size() > 0) {

					String adminSysidAll = "";
					List adminSysId = adminMethodCreateResponse
							.getAdminMethodSysIdAll();

					for (int i = 0; i < adminSysId.size(); i++) {
						adminSysidAll = adminMethodCreateResponse
								.getAdminMethodSysIdAll().get(i).toString()
								+ "~"
								+ adminMethodCreateResponse
										.getAdminMethodSysIdAll().get(++i)
										.toString();
					}
					setAdminMethodSysIdAll(adminSysidAll);
				}

				setBreadCrumbText("Administration >> Admin Method ("
						+ this.adminMethodNo + ")>> Edit");

				getSession().setAttribute("ADMIN_METHOD_EDIT_SCREEN", "true");

				editAdminMethod();	
				addMessageToRequest(new InformationalMessage(
						WebConstants.ADMIN_METHOD_CREATE_SUCCESS));
							
				/*WAS 6.0 migration changes - fix for avoiding duplicate message*/
				HttpServletRequest request = getRequest();
				List messagesList = (List)request.getAttribute("messages");
				 if(messagesList != null && !messagesList.isEmpty()) {
			        	for (Iterator iterator = messagesList.iterator(); iterator.hasNext();) {
							Message message = (Message) iterator.next();						
				    		if(message.getId().equals(BusinessConstants.MSG_PRDCT_NO_SEARCH_RESULT))
								iterator.remove();						
						}
			        }
				return "editAdminMethod";
			}
		return "";
	}

	/**
	 * Method to check Admin method already exists.
	 * 
	 * @return
	 */
	private boolean checkAdminMethodExists() {

		boolean duplicateExists = false;
		AdminMethodSearchRequest adminMethodSearchRequest = (AdminMethodSearchRequest) this
				.getServiceRequest(ServiceManager.SEARCH_ADMIN_METHOD_REQUEST);
		adminMethodSearchRequest.setAdminMethodNo(this.getAdminMethodNo());
		adminMethodSearchRequest.setDescription(this.getDescription().trim()
				.toUpperCase());

		adminMethodSearchRequest.setProcessMethod(this.getProcessMethod());
		// Flag added for seatching unique description
		adminMethodSearchRequest.setSearchExisitng(true);
		AdminMethodSearchResponse adminMethodSearchResponse = (AdminMethodSearchResponse) this
				.executeService(adminMethodSearchRequest);

		if (null != adminMethodSearchResponse.getSearchList()
				&& adminMethodSearchResponse.getSearchList().size() > 0) {
			duplicateExists = true;

		}
		return duplicateExists;
	}

	/**
	 * @returns if admin method is valid or not for create
	 *  
	 */

	public boolean isvalidAdminMethod() {

		boolean valid = true;
		boolean descLength = true;
		boolean cmntsLength = true;
		int adminMethodNumberInteger = 0;
		
		if (this.adminMethodNo == null
				|| this.adminMethodNo.trim().length() == 0) {
			valid = false;
			this.setAdminMethodNoReq(true);
		}
		else {
			try{
				adminMethodNumberInteger = Integer.parseInt(this.adminMethodNo.trim());
			}catch (Exception ex){
				adminMethodNumberInteger = -1;
			}
		}
		if ((this.getDescription() == null || (this.getDescription().trim().length()) == 0)) {
			valid = false;
			this.setDescriptionReq(true);
			
		} else if (valid && this.getDescription() != null
				&& this.getDescription().trim().length() > BusinessConstants.ADMN_METHOD_FIELDS) {
			descLength = false;
			this.setDescriptionReq(true);
		}		
		/*
		 * if ((this.getProcessMethod() ==null ||
		 * (this.getProcessMethod().length()) == 0)) { valid = false;
		 * this.setProcessMethodReq(true); }
		 */		
		if (valid && (this.getComments() != null && this.getComments().trim().length() >  BusinessConstants.ADMN_METHOD_FIELDS)) {
			cmntsLength = false;
			this.setCommentsReq(true);
		}
		if (!valid) {
			addMessageToRequest(new ErrorMessage(
					WebConstants.ADMIN_MTHD_MANDATORY_FIELD_REQUIRED));
			valid = false;
		}else if (!descLength && !cmntsLength){
			addMessageToRequest(new ErrorMessage(
					WebConstants.ADMIN_MTHD_DESC_AND_COMNTS_LENGTH_NOT_EXCEED));
			valid = false;
		}else if (!descLength) {
			addMessageToRequest(new ErrorMessage(
					WebConstants.ADMIN_MTHD_DESC_LENGTH_NOT_EXCEED));
			valid = false;
		}else if (!cmntsLength) {
			addMessageToRequest(new ErrorMessage(
					WebConstants.ADMIN_MTHD_CMNTS_LENGTH_NOT_EXCEED));
			valid = false;
		}
		if (adminMethodNumberInteger == -1){
			addMessageToRequest(new ErrorMessage(
			WebConstants.ADMIN_MTHD_NMBR_NOT_NUMERIC));
			valid = false;
			this.setAdminMethodNoReq(true);
		}//else
		//	valid = isValidPattern(this.getDescription());

		return valid;
	}

	public boolean isvalidAdminMethodEdit() {

		boolean valid = true;

		if (this.getDescription().equals(this.getHiddenDescription())
				&& (this.getHiddenComments().equals(this.getComments()))
				&& (this.getHiddenConfiguration().equals(this
						.getConfiguration()))) {
			valid = false;
			addMessageToRequest(new InformationalMessage(
					WebConstants.ADMIN_MTHD_EDIT_INVALID));
		}
		return valid;
	}

	/**
	 * @returns if admin method is valid or not for edit
	 *  
	 */

	public boolean isvalidAdminMethodForEdit() {

		boolean valid = true;
		boolean descLength = true;
		boolean cmntsLength = true;
		boolean adminNo = true;
		boolean adminDesc = true;
		if (this.adminMethodNo == null
				|| this.adminMethodNo.trim().length() == 0) {
			adminNo = false;
			this.setAdminMethodNoReq(true);
		}
		if (this.getComments() != null
				&& this.getComments().trim().length() >  BusinessConstants.ADMN_METHOD_FIELDS) {			
			cmntsLength = false;
			this.setCommentsReq(true);
			
		}	
		if (this.getDescription().trim().length() == 0 || (this.getDescription() == null)) {
			adminDesc = false;
			this.setDescriptionReq(true);			

		} else if (this.getDescription() != null
				&& this.getDescription().trim().length() >  BusinessConstants.ADMN_METHOD_FIELDS) {
			descLength = false;
			this.setDescriptionReq(true);
		}
		
		if(!(adminNo)){
			
			addMessageToRequest(new ErrorMessage(
					WebConstants.ADMIN_MTHD_NUMBER_INVALID));
			valid = false;
		}
		if(!(descLength) && !(cmntsLength)) {
			addMessageToRequest(new ErrorMessage(
					WebConstants.ADMIN_MTHD_DESC_AND_COMNTS_LENGTH_NOT_EXCEED));
			valid = false;
		}
		else if(!(descLength)){
			
			addMessageToRequest(new ErrorMessage(
					WebConstants.ADMIN_MTHD_DESC_LENGTH_NOT_EXCEED));
			valid = false;
		}else if(!(cmntsLength)){
			
			addMessageToRequest(new ErrorMessage(
					WebConstants.ADMIN_MTHD_CMNTS_LENGTH_NOT_EXCEED));
			valid = false;
		}
		if(!(adminDesc)){
			
			addMessageToRequest(new ErrorMessage(
					WebConstants.ADMIN_MTHD_MANDATORY_FIELD_REQUIRED));	
			valid = false;
		}
		 //if((isValidPattern(this.getDescription()))) {
		//	valid = false;
		//}
		 
		return valid;
	}

	/**
	 * 
	 * Method to check valid pattern for description
	 * 
	 * @return
	 */
	boolean isValidPattern(String text) {
		Pattern p = Pattern.compile("[^A-Za-z\\_\\/\\-\\ ]");
		Matcher m = p.matcher(text);
		boolean flag = m.find();

		if (flag)
			addMessageToRequest(new ErrorMessage(
					WebConstants.ADMIN_MTHD_DESC_INVALID));
		else
			flag = true;
		return (flag);
	}

	/**
	 * Method to add required Parameter Group
	 * 
	 * @return
	 */
	public String addRequiredParameter() {

		String requiredParamString = WPDStringUtil.getStringFromTildaString(reqParams, 2, 2, 2).replace('~', ',');
		String requiredParamIdString = WPDStringUtil.getStringFromTildaString(reqParams, 2, 1, 2).replace('~', ',');
		
		reqParamGroups=	getRequiredParamListFromSession();
		List reqParamIdList= getRequiredParamIdListFromSession();
		this.setCreatedDate((Date)getSession().getAttribute("createdDate"));
        this.setLastUpdatedDate((Date)getSession().getAttribute("lastUpdatedDate"));
        
		if(isAddedReqParamDuplicate(requiredParamString)) {
			addMessageToRequest(new ErrorMessage(
					WebConstants.DUPLICATE_ADMINMETHOD_GROUP_ADDED));
			
			if (isEditPageReload()) {
				setBreadCrumbText("Administration >> Admin Method ("
						+ this.adminMethodNo + ")>> Edit");
				return "editAdminMethod";

			} else {
				setBreadCrumbText("Administration >> Admin Method >> Create");
				return "createAdminMethod";
			}
		}
		
		reqParamIdList.add(requiredParamIdString);
		addRequiredParamIdListToSession(reqParamIdList);
		
		reqParamGroups.add(requiredParamString);
		addRequiredParamListToSession(reqParamGroups);
		setReqParamModifiedStaus("true");		
		
		if (isEditPageReload()) {
			setBreadCrumbText("Administration >> Admin Method ("
					+ this.adminMethodNo + ")>> Edit");
			return "editAdminMethod";

		} else {
			setBreadCrumbText("Administration >> Admin Method >> Create");
			return "createAdminMethod";
		}
	}
	/**
	 * @param reqParamIdList
	 */
	private void addRequiredParamIdListToSession(List reqParamIdList) {

		getSession().setAttribute(REQ_PARAM_ID_LIST,reqParamIdList);
		
	}

	/**
	 * @return
	 */
	private List getRequiredParamIdListFromSession() {
		List requiredParamIdList = new ArrayList();
		if (null != getSession().getAttribute(REQ_PARAM_ID_LIST))
			requiredParamIdList = (List) getSession().getAttribute(REQ_PARAM_ID_LIST);

		return requiredParamIdList;
	}

	/**
	 * @return
	 */
	private boolean isAddedReqParamDuplicate(String requiredParamString) {
		
		List reqParamList=	getRequiredParamListFromSession();
		return reqParamList.contains(requiredParamString);
	}

	private boolean isEditPageReload(){
		
		return (getSession().getAttribute("ADMIN_METHOD_EDIT_SCREEN") != null)
				&& (getSession().getAttribute("ADMIN_METHOD_EDIT_SCREEN")
						.equals("true"));
		
	}
	
/**
 * Method to retreive the required param list
 * @return
 */
	private List getRequiredParamListFromSession() {

		List requiredParamList = new ArrayList();
		if (null != getSession().getAttribute(REQ_PARAM_LIST))
			requiredParamList = (List) getSession().getAttribute(REQ_PARAM_LIST);

		return requiredParamList;
	}
	private void addRequiredParamListToSession(List requiredParamList ) {

		getSession().setAttribute(REQ_PARAM_LIST,requiredParamList);
	}
	/**
	 * Method to delete required Parameter Group.
	 * 
	 * @return
	 */
	public String deleteGroup() {

		reqParamGroups=getRequiredParamListFromSession();
		reqParamGroups.remove(selectedRowNumber);
		this.setCreatedDate((Date)getSession().getAttribute("createdDate"));
        this.setLastUpdatedDate((Date)getSession().getAttribute("lastUpdatedDate"));
        
		List requiredParamIdList= getRequiredParamIdListFromSession();
		requiredParamIdList.remove(selectedRowNumber);
		
		addMessageToRequest(new InformationalMessage(WebConstants.DELETE_ADMIN_MTHD));
		
		addRequiredParamListToSession(reqParamGroups);
		addRequiredParamIdListToSession(requiredParamIdList);
		setReqParamModifiedStaus("true");
		if (isEditPageReload()) {
			setBreadCrumbText("Administration >> Admin Method ("
					+ this.adminMethodNo + ")>> Edit");
			return "editAdminMethod";
		} else {
			setBreadCrumbText("Administration >> Admin Method >> Create");
			return "createAdminMethod";
		}
		
	}

	/**
	 * Method to populate request for create.
	 */
	private void populateRequest(
			AdminMethodCreateRequest adminMethodCreateRequest) {
		Date currentDate = ((AuditFactory) ObjectFactory
				.getFactory(ObjectFactory.AUDIT)).getAudit().getTime();
		adminMethodCreateRequest.setAdminMethodNo(this.getAdminMethodNo());
		adminMethodCreateRequest.setDescription(this.getDescription()
				.toUpperCase().trim());
		adminMethodCreateRequest.setProcessMethod(this.getProcessMethod());
		adminMethodCreateRequest.setConfiguration(this.getConfiguration());
		adminMethodCreateRequest.setRequiredParamList(getRequiredParamIdListFromSession());
		adminMethodCreateRequest.setComments(this.getComments());

		List referenceList = WPDStringUtil.getListFromTildaString(this
				.getConfiguration(), 2, 1, 2);

		adminMethodCreateRequest.setConfigurationList(referenceList);
		adminMethodCreateRequest.setComments(this.getComments());
		adminMethodCreateRequest.setCreatedDate(currentDate);
		adminMethodCreateRequest.setLastUpdatedDate(currentDate);

		String regex = ":";
	}

	/**
	 * Method to populate request for edit.
	 */
	private void populateEditRequest(
			AdminMethodEditRequest adminMethodEditRequest) {
		Date currentDate = ((AuditFactory) ObjectFactory
				.getFactory(ObjectFactory.AUDIT)).getAudit().getTime();
		adminMethodEditRequest.setReqParamGroups(getRequiredParamIdListFromSession());
		adminMethodEditRequest.setOldDescription(this.hiddenDescription.toUpperCase().trim());
		adminMethodEditRequest.setAdminMethodSysId(this.getAdminMethodSysId());
		adminMethodEditRequest.setAdminMethodSysIdAll(this
				.getAdminMethodSysIdAll());
		adminMethodEditRequest.setDescription(this.getDescription().toUpperCase().trim());
		adminMethodEditRequest.setAdminMethodNo(this.getAdminMethodNo());
		adminMethodEditRequest.setConfiguration(this.getConfiguration());
		adminMethodEditRequest.setSpsIdList(this.getProcessMethod());
		List referenceList = WPDStringUtil.getListFromTildaString(this
				.getConfiguration(), 2, 1, 2);

		adminMethodEditRequest.setConfigurationList(referenceList);
		adminMethodEditRequest.setComments(this.getComments());
		adminMethodEditRequest.setLastUpdatedDate(currentDate);
	}

	public int getSelectedRowIndex() {

		return selectedRowIndex;

	}

	public void setSelectedRowIndex(int selectedRowIndex) {

		this.selectedRowIndex = selectedRowIndex;

	}

	/**
	 * @return Returns the selectedRowNumber.
	 */
	public int getSelectedRowNumber() {
		return selectedRowNumber;
	}

	/**
	 * @param selectedRowNumber
	 *            The selectedRowNumber to set.
	 */
	public void setSelectedRowNumber(int selectedRowNumber) {
		this.selectedRowNumber = selectedRowNumber;
	}

	/**
	 * View an admin Method
	 * 
	 * @return
	 */
	public String viewAdminMethod() {

		return "";
	}

	/**
	 * Method to get admin Method information for Edit
	 * 
	 * @return
	 */
	public String editAdminMethod() {
		Logger
				.logInfo(" AdminMethodMaintainBackingBean - getting Admin Method information  for Edit");
		adminMethodSysId = this.getAdminMethodSysId();
		if (adminMethodSysId != 0) {

			AdminMethodViewRequest adminMethodViewRequest = (AdminMethodViewRequest) this
					.getServiceRequest(ServiceManager.VIEW_ADMIN_METHOD_REQUEST);

			adminMethodViewRequest.setAdminMethodSysId(adminMethodSysId);
			adminMethodViewRequest.setAdminMethodNo(adminMethodNo);
			adminMethodViewRequest.setAdminMethodDesc(this.description);

			AdminMethodViewResponse adminMethodViewResponse = (AdminMethodViewResponse) this
					.executeService(adminMethodViewRequest);

			if (null != adminMethodViewResponse.getAdminMethodMaintainBO()) {
				AdminMethodMaintainBO adminMethodMaintainBO = (AdminMethodMaintainBO) adminMethodViewResponse
						.getAdminMethodMaintainBO();

				setValuesToBackingBeanForEdit(adminMethodMaintainBO);

				setBreadCrumbText("Administration >> Admin Method ("
						+ this.getAdminMethodNo() + ")>> Edit");
			}
			getSession().setAttribute("ADMIN_METHOD_EDIT_SCREEN", "true");
		}
		return "editAdminMethod";
	}

	/**
	 * Method to Edit an admin Method
	 * 
	 * @return
	 */
	public String saveAdminMethod() {

		Logger.logInfo(" AdminMethodMaintainBackingBean - save Admin Method");
		List temperList = new ArrayList();
		
		this.setCreatedDate((Date)getSession().getAttribute("createdDate"));
        this.setLastUpdatedDate((Date)getSession().getAttribute("lastUpdatedDate"));
        
		if (!isvalidAdminMethodForEdit()) {
			setBreadCrumbText("Administration >> Admin Method("
					+ this.getAdminMethodNo() + ")>> Edit");
				reqParamGroups = getRequiredParamListFromSession();
				
			return "";
		}
		if (!(this.getHiddenDescription().trim().equals(this.getDescription().toUpperCase().trim()))) {

			if (checkAdminMethodExists()) {
				addMessageToRequest(new ErrorMessage(
						WebConstants.ADMIN_MTHD_CREATE_UNIQUE_VALID));
				setBreadCrumbText("Administration >> Admin Method("
						+ this.getAdminMethodNo() + ") >> Edit");
				reqParamGroups = getRequiredParamListFromSession();
				return "";
			}
		}
		
		AdminMethodEditRequest adminMethodEditRequest = (AdminMethodEditRequest) this
				.getServiceRequest(ServiceManager.EDIT_ADMIN_METHOD_REQUEST);
		populateEditRequest(adminMethodEditRequest);
		AdminMethodEditResponse adminMethodEditResponse = (AdminMethodEditResponse) this
				.executeService(adminMethodEditRequest);
		this.setDescription(adminMethodEditRequest.getDescription().toUpperCase());
		reqParamGroups = getRequiredParamListFromSession();
		retriveAdminMethodForEdit();
		setBreadCrumbText("Administration >> Admin Method("
				+ this.getAdminMethodNo() + ") >> Edit");
		addMessageToRequest(new InformationalMessage(
				WebConstants.ADMIN_METHOD_EDIT_SUCCESS));
		/*WAS 6.0 migration changes - fix for avoiding duplicate message*/
		HttpServletRequest request = getRequest();
		List messagesList = (List)request.getAttribute("messages");
		 if(messagesList != null && !messagesList.isEmpty()) {
	        	for (Iterator iterator = messagesList.iterator(); iterator.hasNext();) {
					Message message = (Message) iterator.next();						
		    		if(message.getId().equals(BusinessConstants.MSG_PRDCT_NO_SEARCH_RESULT))
						iterator.remove();						
				}
	        }
		Logger
				.logInfo(" AdminMethodMaintainBackingBean - Returning from Edit Admin Method");

		return "editAdminMethod";
	}
	
	private String retriveAdminMethodForEdit() {
		Logger.logInfo(" AdminMethodMaintainBackingBean - Retrieve Admin Method for edit");
		adminMethodSysId = this.getAdminMethodSysId();
		
		if (adminMethodSysId != 0) {
			AdminMethodViewRequest adminMethodViewRequest = (AdminMethodViewRequest) this
					.getServiceRequest(ServiceManager.VIEW_ADMIN_METHOD_REQUEST);
			adminMethodViewRequest.setAdminMethodSysId(adminMethodSysId);		
			adminMethodViewRequest.setAdminMethodNo(adminMethodNo);
			adminMethodViewRequest.setAdminMethodDesc(this.description);	
			AdminMethodViewResponse adminMethodViewResponse = (AdminMethodViewResponse) this
					.executeService(adminMethodViewRequest);

			if (null != adminMethodViewResponse.getAdminMethodMaintainBO()) {
				AdminMethodMaintainBO adminMethodMaintainBO = (AdminMethodMaintainBO) adminMethodViewResponse
						.getAdminMethodMaintainBO();
				setValuesToBackingBeanForEdit(adminMethodMaintainBO);
				setBreadCrumbText("Administration >> Admin Method ("
						+ this.getAdminMethodNo() + ")>> Edit");
			}
		}
		return "";
	}
	

	/**
	 * 
	 * Method to retrive Admin Method for Edit Print Page.
	 */

	private String retriveAdminMethodForEditPrint() {
		Logger.logInfo(" AdminMethodMaintainBackingBean - Edit Print Admin Method");
		adminMethodSysId = ((Integer) this.getSession().getAttribute(
				"adminMethodSysId")).intValue();

		if (adminMethodSysId != 0) {
			AdminMethodViewRequest adminMethodViewRequest = (AdminMethodViewRequest) this
					.getServiceRequest(ServiceManager.VIEW_ADMIN_METHOD_REQUEST);
			adminMethodViewRequest.setAdminMethodSysId(adminMethodSysId);
			//adminMethodViewRequest.setAdminMethodNo(adminMethodNo);
			//adminMethodViewRequest.setAdminMethodDesc(this.description);

			AdminMethodViewResponse adminMethodViewResponse = (AdminMethodViewResponse) this
					.executeService(adminMethodViewRequest);

			if (null != adminMethodViewResponse.getAdminMethodMaintainBO()) {
				AdminMethodMaintainBO adminMethodMaintainBO = (AdminMethodMaintainBO) adminMethodViewResponse
						.getAdminMethodMaintainBO();
				setValuesToBackingBeanForView(adminMethodMaintainBO);
				
			}
		}
		return "";
	}
	
	/**
	 * Method to delete An admin Method
	 * 
	 * @return
	 */
	public String deleteAdminMethod() {

		Logger.logInfo(" AdminMethodMaintainBackingBean - Delete Admin Method");
		AdminMethodDeleteRequest adminMethodDeleteRequest = (AdminMethodDeleteRequest) this
				.getServiceRequest(ServiceManager.DELETE_ADMIN_METHOD_REQUEST);

		// Setting the admin method no and desc to the request
		adminMethodDeleteRequest.setAdminMethodNo(adminMethodNo);
		adminMethodDeleteRequest.setAdminMethodDesc(this.description);
		adminMethodDeleteRequest.setProcessingMethodIds(this.processMethod);
		
		AdminMethodDeleteResponse adminMethodDeleteResponse = (AdminMethodDeleteResponse) this
				.executeService(adminMethodDeleteRequest);

		// Setting the user messages to the bean
		setBreadCrumbText("Administration >> Admin Method >> Maintain");
		Logger
				.logInfo(" AdminMethodMaintainBackingBean - Returning from Delete Admin Method");

		String search =locateAdminMethods();
		
		addAllMessagesToRequest(adminMethodDeleteResponse.getMessages());
		
		return "";
	}

	/**
	 * Search For an admin Method
	 * 
	 * @return
	 */
	public String searchAdminMethod() {

		return "";
	}

	/**
	 * print an admin method
	 * 
	 * @return
	 */
	public String printAdminMethod() {

		return "";
	}

	/**
	 * @return Returns the adminMethodNo.
	 */
	public String getAdminMethodNo() {
		return adminMethodNo;
	}

	/**
	 * @param adminMethodNo
	 *            The adminMethodNo to set.
	 */
	public void setAdminMethodNo(String adminMethodNo) {
		this.adminMethodNo = adminMethodNo;
	}

	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return Returns the configuration.
	 */
	public String getConfiguration() {
		return configuration;
	}

	/**
	 * @param configuration
	 *            The configuration to set.
	 */
	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}

	/**
	 * @return Returns the createdUser.
	 */
	public String getCreatedUser() {
		return createdUser;
	}

	/**
	 * @param createdUser
	 *            The createdUser to set.
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return Returns the lastUpdatedUser.
	 */
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}

	/**
	 * @param lastUpdatedUser
	 *            The lastUpdatedUser to set.
	 */
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}

	/**
	 * @return Returns the processMethod.
	 */
	public String getProcessMethod() {
		return processMethod;
	}

	/**
	 * @param processMethod
	 *            The processMethod to set.
	 */
	public void setProcessMethod(String processMethod) {
		this.processMethod = processMethod;
	}

	/**
	 * @return Returns the reqParamGroups.
	 */
	public List getReqParamGroups() {
		return reqParamGroups;
	}

	/**
	 * @param reqParamGroups
	 *            The reqParamGroups to set.
	 */
	public void setReqParamGroups(List reqParamGroups) {
		this.reqParamGroups = reqParamGroups;
	}

	/**
	 * @return Returns the searchResults.
	 */
	public List getSearchResults() {
		return searchResults;
	}

	/**
	 * @param searchResults
	 *            The searchResults to set.
	 */
	public void setSearchResults(List searchResults) {
		this.searchResults = searchResults;
	}

	public boolean isvalidAdminMethodForLocate() {

		boolean valid = true;
		if ((this.locateAdminMethodNo == null || this.locateAdminMethodNo
				.trim().length() == 0)
				&& (this.adminMethoddescription == null || this.adminMethoddescription
						.trim().length() == 0)
				&& (this.selectedProcessMethod == null || this.selectedProcessMethod
						.trim().length() == 0)) {
			valid = false;

		}
		return valid;
	}

	/**
	 * Method to search admin Method
	 *  
	 */
	public String locateAdminMethods() {
		Logger.logInfo(" AdminMethodMaintainBackingBean - Search Admin Method");

		List spsIdList = new ArrayList();
		int adminMethodNoIntValue = 0;
		try{
			adminMethodNoIntValue = Integer.parseInt(this.locateAdminMethodNo.trim());
		} catch (Exception ex){
			adminMethodNoIntValue = -1;
		}
				
		if (!isvalidAdminMethodForLocate()) {

			addMessageToRequest(new ErrorMessage(
					WebConstants.ADMIN_MTHD_LOCATE_INVALID));
			setBreadCrumbText("Administration >> Admin Method >> Locate");

		} else if(isvalidAdminMethodForLocate() && (this.locateAdminMethodNo != null && this.locateAdminMethodNo
				.trim().length() != 0) && adminMethodNoIntValue == -1){
			addMessageToRequest(new ErrorMessage(
					WebConstants.ADMIN_MTHD_NMBR_NOT_NUMERIC));
			this.setAdminMethodNoReq(true);
			setBreadCrumbText("Administration >> Admin Method >> Locate");
		}
		else if (this.getAdminMethoddescription().trim() != null
				&& this.getAdminMethoddescription().trim().length() > BusinessConstants.ADMN_METHOD_FIELDS) {
			addMessageToRequest(new ErrorMessage(
					WebConstants.ADMIN_MTHD_DESC_LENGTH_NOT_EXCEED));
			this.setDescriptionReq(true);
			setBreadCrumbText("Administration >> Admin Method >> Locate");

		} else {
			try {
				setUserPermissions();
			} catch (SevereException e) {
				addMessageToRequest(new ErrorMessage(
						e.getMessage()));
				setBreadCrumbText("Administration >> Admin Method >> Locate");
				return "";
			}
			
			AdminMethodSearchRequest adminMethodSearchRequest = (AdminMethodSearchRequest) this
					.getServiceRequest(ServiceManager.SEARCH_ADMIN_METHOD_REQUEST);

			adminMethodSearchRequest.setAdminMethodNo(this
					.getLocateAdminMethodNo());
			adminMethodSearchRequest.setDescription(this
					.getAdminMethoddescription().trim());
			adminMethodSearchRequest.setSelectedProcessMethod(this
					.getSelectedProcessMethod());

			AdminMethodSearchResponse adminMethodSearchResponse = (AdminMethodSearchResponse) this
					.executeService(adminMethodSearchRequest);
			if (null != adminMethodSearchResponse.getSearchList()
					&& adminMethodSearchResponse.getSearchList().size() > 0) {
				spsIdList = adminMethodSearchResponse.getSearchList();
				searchResults = parseSearchList(spsIdList);
			}
			setBreadCrumbText("Administration >> Admin Method >> Locate");
		}

		Logger
				.logInfo(" AdminMethodMaintainBackingBean - Returning Search Admin Method");
		return "";
	}

	/**
	 * @throws SevereException
	 * 
	 */
	private void setUserPermissions() throws SevereException {
		User user = getUser();
		viewableForUser  = user.isAuthorized(WebConstants.ADMIN_METHOD_MODULE,WebConstants.ADMIN_METHOD_MAINTAIN_TASK);
		deletableForUser = user.isAuthorized(WebConstants.ADMIN_METHOD_MODULE,WebConstants.ADMIN_METHOD_MAINTAIN_DEL_TASK);
		editableForUser  = user.isAuthorized(WebConstants.ADMIN_METHOD_MODULE,WebConstants.ADMIN_METHOD_MAINTAIN_EDT_TASK);
	}

	private List parseSearchList(List searchResults) {
		List newSearchList = new ArrayList();

		for (int i = 0; i < searchResults.size(); i++) {

			AdminMethodMaintainBO AdminMethodMaintainBO1 = (AdminMethodMaintainBO) searchResults
					.get(i);//0,2,4,6,

			if (searchResults.size() > 1 && searchResults.size() != ++i) {
				//i++;
				AdminMethodMaintainBO AdminMethodMaintainBO2 = (AdminMethodMaintainBO) searchResults
						.get(i);//1,3,5,
				AdminMethodMaintainBO1
						.setAdminMethodSysIdAll(AdminMethodMaintainBO1
								.getAdminMethodSysId()
								+ "~"
								+ AdminMethodMaintainBO2.getAdminMethodSysId());
				AdminMethodMaintainBO1.setProcessMethod(AdminMethodMaintainBO1
						.getProcessMethod()
						+ "~" + AdminMethodMaintainBO2.getProcessMethod());
			} else {
				AdminMethodMaintainBO1
						.setAdminMethodSysIdAll(AdminMethodMaintainBO1
								.getAdminMethodSysId()
								+ "~");
				AdminMethodMaintainBO1.setProcessMethod(AdminMethodMaintainBO1
						.getProcessMethod()
						+ "~");
			}
			AdminMethodMaintainBO1.setAdminMethodNo(AdminMethodMaintainBO1
					.getAdminMethodNo());
			AdminMethodMaintainBO1.setDescription(AdminMethodMaintainBO1
					.getDescription());
			AdminMethodMaintainBO1.setProcessMethodDesc(AdminMethodMaintainBO1
					.getProcessMethodDesc());
			AdminMethodMaintainBO1.setRequiredParm(AdminMethodMaintainBO1
					.getRequiredParm());
			AdminMethodMaintainBO1.setConfiguration(AdminMethodMaintainBO1
					.getConfiguration());

			newSearchList.add(AdminMethodMaintainBO1);
		}
		return newSearchList;
	}

	/**
	 * Method to retrive Admin Method for view
	 */
	private String retriveAdminMethod() {
		Logger.logInfo(" AdminMethodMaintainBackingBean - View Admin Method");
		adminMethodSysId = Integer.parseInt(getRequest().getParameter(
				"adminMethodSysId"));
		description = getRequest().getParameter("description");
		adminMethodNo = getRequest().getParameter("adminMethodNo");

		if (adminMethodSysId != 0) {
			AdminMethodViewRequest adminMethodViewRequest = (AdminMethodViewRequest) this
					.getServiceRequest(ServiceManager.VIEW_ADMIN_METHOD_REQUEST);
			adminMethodViewRequest.setAdminMethodSysId(adminMethodSysId);
			adminMethodViewRequest.setAdminMethodNo(adminMethodNo);
			adminMethodViewRequest.setAdminMethodDesc(this.description);

			AdminMethodViewResponse adminMethodViewResponse = (AdminMethodViewResponse) this
					.executeService(adminMethodViewRequest);

			if (null != adminMethodViewResponse.getAdminMethodMaintainBO()) {
				AdminMethodMaintainBO adminMethodMaintainBO = (AdminMethodMaintainBO) adminMethodViewResponse
						.getAdminMethodMaintainBO();
				setValuesToBackingBeanForView(adminMethodMaintainBO);
				setBreadCrumbText("Administration >> Admin Method ("
						+ this.getAdminMethodNo() + ")>> View");
			}
		}
		return "";
	}

	/**
	 * @param Method
	 *            is used to set values to Backing Bean for view.
	 */
	private void setValuesToBackingBeanForView(
			AdminMethodMaintainBO adminMethodMaintainBO) {

		List refGrpList = new ArrayList();
		this.setAdminMethodNo(adminMethodMaintainBO.getAdminMethodNo());
		this.setDescription(adminMethodMaintainBO.getDescription().toUpperCase());
		this.setProcessMethodDesc(adminMethodMaintainBO.getProcessMethodDesc());
		this.setComments(adminMethodMaintainBO.getComments());
		this.setConfigurationList(adminMethodMaintainBO.getConfigurationList());

		this.setReqParams(getTildaStringFromReqParamList(adminMethodMaintainBO
				.getReqParamGroups()));

		this.setCreatedUser(adminMethodMaintainBO.getCreatedUser());
		this.setCreatedDate(adminMethodMaintainBO.getCreatedDate());
		this.setLastUpdatedUser(adminMethodMaintainBO.getLastUpdatedUser());
		this.setLastUpdatedDate(adminMethodMaintainBO.getLastUpdatedDate());

		if (adminMethodMaintainBO.getReqParamGroups() != null) {
			for (int i = 0; i < adminMethodMaintainBO.getReqParamGroups()
					.size(); i++) {
				RequiredParamBO requiredParamBO = (RequiredParamBO) adminMethodMaintainBO
						.getReqParamGroups().get(i);
				String references = "";
				if (requiredParamBO.getReferenceGroupBo() != null) {
					for (int j = 0; j < requiredParamBO.getReferenceGroupBo()
							.size(); j++) {

						ReferenceGroupBO referenceGroupBO = (ReferenceGroupBO) requiredParamBO
								.getReferenceGroupBo().get(j);
						if (references.length() == 0)
							references = referenceGroupBO.getDescription();
						else
							references = references + ", "
									+ referenceGroupBO.getDescription();

					}
					this.setReqParamGroups(adminMethodMaintainBO
							.getReqParamGroups());

					requiredParamBO.setReferenceDesc(references);
				}
			}

		}

	}

	/**
	 * @param Method
	 *            is used to set values to Backing Bean for edit.
	 */
	private void setValuesToBackingBeanForEdit(
			AdminMethodMaintainBO adminMethodMaintainBO) {

		List refGrpList = new ArrayList();
		this.setAdminMethodNo(adminMethodMaintainBO.getAdminMethodNo());
		this.setDescription(adminMethodMaintainBO.getDescription().toUpperCase());
		this.setHiddenDescription(adminMethodMaintainBO.getDescription().toUpperCase());
		this.setProcessMethodDesc(adminMethodMaintainBO.getProcessMethodDesc());
		this.setComments(adminMethodMaintainBO.getComments());
		this.setHiddenComments(adminMethodMaintainBO.getComments());
		this.setAdminMethodSysId(adminMethodMaintainBO.getAdminMethodSysId());
		this.getSession().setAttribute("adminMethodSysId",
				new Integer(adminMethodMaintainBO.getAdminMethodSysId()));
		this.getSession().setAttribute("adminMethodNo",
				adminMethodMaintainBO.getAdminMethodNo());

		this
				.setConfiguration(getTildaStringFromConfigurationList(adminMethodMaintainBO
						.getConfigurationList()));
		this
				.setHiddenConfiguration(getTildaStringFromConfigurationList(adminMethodMaintainBO
						.getConfigurationList()));
		
		reqParamGroups = getRequiredParamList(adminMethodMaintainBO);
		addRequiredParamListToSession(reqParamGroups);
		
		List requiredParamIdList = getRequiredParamIdList(adminMethodMaintainBO);
		addRequiredParamIdListToSession(requiredParamIdList);
		
		//this.setHiddenReqParamGroups(reqParamGroups);
		//this.setHiddenReqParamsAll(this.getReqParamsAll());
		this.setCreatedUser(adminMethodMaintainBO.getCreatedUser());
		this.setCreatedDate(adminMethodMaintainBO.getCreatedDate());
		this.setLastUpdatedUser(adminMethodMaintainBO.getLastUpdatedUser());
		this.setLastUpdatedDate(adminMethodMaintainBO.getLastUpdatedDate());
		this.getSession().setAttribute("createdDate",adminMethodMaintainBO.getCreatedDate());
		this.getSession().setAttribute("lastUpdatedDate",adminMethodMaintainBO.getLastUpdatedDate());
	}
	
	private List getRequiredParamList(AdminMethodMaintainBO adminMethodMaintainBO){
		
		List reqParameterList= new ArrayList();
		for (Iterator iter = adminMethodMaintainBO.getReqParamGroups().iterator(); iter.hasNext();) {
			RequiredParamBO requiredParamBO = (RequiredParamBO) iter.next();
			String descList="";
			for (Iterator iterator = requiredParamBO.getReferenceGroupBo().iterator(); iterator.hasNext();) {
			ReferenceGroupBO referenceGroupBO  = (ReferenceGroupBO) iterator.next();
				if("".equals(descList))
					descList=referenceGroupBO.getDescription();
				else
					descList=descList+","+referenceGroupBO.getDescription();
			}
			reqParameterList.add(descList);
		}
		return reqParameterList;
	}
	private List getRequiredParamIdList(AdminMethodMaintainBO adminMethodMaintainBO){
		
		List reqParameterIdList= new ArrayList();
		for (Iterator iter = adminMethodMaintainBO.getReqParamGroups().iterator(); iter.hasNext();) {
			RequiredParamBO requiredParamBO = (RequiredParamBO) iter.next();
			String idList="";
			for (Iterator iterator = requiredParamBO.getReferenceGroupBo().iterator(); iterator.hasNext();) {
			ReferenceGroupBO referenceGroupBO  = (ReferenceGroupBO) iterator.next();
				if("".equals(idList))
					idList=referenceGroupBO.getReferenceId();
				else
					idList=idList+","+referenceGroupBO.getReferenceId();
			}
			reqParameterIdList.add(idList);
		}
		return reqParameterIdList;
	}
	/**
	 * @param Method
	 *            to get tilda String for Configuration List
	 * @return
	 */

	public static String getTildaStringFromConfigurationList(
			List configurationList) {

		if (configurationList == null)
			return "";

		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < configurationList.size(); i++) {
			ConfigurationBO configurationBO = (ConfigurationBO) configurationList
					.get(i);
			if (i != 0) {
				buffer.append("~");
			}
			buffer.append(configurationBO.getConfigID());
			buffer.append("~" + configurationBO.getConfigDesc());
		}
		return buffer.toString();
	}

	/**
	 * @param Method
	 *            to get tilda String for Required Parameter List
	 * @return
	 */

	public static String getTildaStringFromReqParamList(List reqParamList) {

		if (reqParamList == null || reqParamList.size() == 0)
			return "";

		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < reqParamList.size(); i++) {
			RequiredParamBO requiredParamBO = (RequiredParamBO) reqParamList
					.get(i);
			if (i != 0) {
				buffer.append("$");
			}

			if (requiredParamBO.getReferenceGroupBo() != null) {
				for (int j = 0; j < requiredParamBO.getReferenceGroupBo()
						.size(); j++) {
					ReferenceGroupBO referenceGroupBO = (ReferenceGroupBO) requiredParamBO
							.getReferenceGroupBo().get(j);
					if (j != 0) {
						buffer.append("~");
					}
					buffer.append(referenceGroupBO.getReferenceId());
					buffer.append("~" + referenceGroupBO.getDescription());

				}
			}
		}
		return buffer.toString();
	}

	/**
	 * @return Returns the reqParamFlag.
	 */
	public String getRequiredParm() {
		return requiredParm;
	}

	/**
	 * @param reqParamFlag
	 *            The reqParamFlag to set.
	 */
	public void setRequiredParm(String requiredParm) {
		this.requiredParm = requiredParm;
	}

	/**
	 * @return Returns the reqParamList.
	 */
	public List getReqParamList() {
		return reqParamList;
	}

	/**
	 * @param reqParamList
	 *            The reqParamList to set.
	 */
	public void setReqParamList(List reqParamList) {
		this.reqParamList = reqParamList;
	}

	/**
	 * @return Returns the adminMethoddescription.
	 */
	public String getAdminMethoddescription() {
		return adminMethoddescription;
	}

	/**
	 * @param adminMethoddescription
	 *            The adminMethoddescription to set.
	 */
	public void setAdminMethoddescription(String adminMethoddescription) {
		this.adminMethoddescription = adminMethoddescription;
	}

	/**
	 * @return Returns the createdDate.
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            The createdDate to set.
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return Returns the lastUpdatedDate.
	 */
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	/**
	 * @param lastUpdatedDate
	 *            The lastUpdatedDate to set.
	 */
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	/**
	 * @return Returns the reqParameterList.
	 */
	public List getReqParameterList() {
		return reqParameterList;
	}

	/**
	 * @param reqParameterList
	 *            The reqParameterList to set.
	 */
	public void setReqParameterList(List reqParameterList) {
		this.reqParameterList = reqParameterList;
	}

	/**
	 * @return Returns the locateAdminMethodNo.
	 */
	public String getLocateAdminMethodNo() {
		return locateAdminMethodNo;
	}

	/**
	 * @param locateAdminMethodNo
	 *            The locateAdminMethodNo to set.
	 */
	public void setLocateAdminMethodNo(String locateAdminMethodNo) {
		this.locateAdminMethodNo = locateAdminMethodNo;
	}

	/**
	 * @return Returns the adminMethodSysIdAll.
	 */
	public String getAdminMethodSysIdAll() {
		return adminMethodSysIdAll;
	}

	/**
	 * @param adminMethodSysIdAll
	 *            The adminMethodSysIdAll to set.
	 */
	public void setAdminMethodSysIdAll(String adminMethodSysIdAll) {
		this.adminMethodSysIdAll = adminMethodSysIdAll;
	}

	/**
	 * @return Returns the selectedProcessMethod.
	 */
	public String getSelectedProcessMethod() {
		return selectedProcessMethod;
	}

	/**
	 * @param selectedProcessMethod
	 *            The selectedProcessMethod to set.
	 */
	public void setSelectedProcessMethod(String selectedProcessMethod) {
		this.selectedProcessMethod = selectedProcessMethod;
	}

	/**
	 * @return Returns the commentsReq.
	 */
	public boolean getCommentsReq() {
		return commentsReq;
	}

	/**
	 * @param commentsReq
	 *            The commentsReq to set.
	 */
	public void setCommentsReq(boolean commentsReq) {
		this.commentsReq = commentsReq;
	}

	/**
	 * @return Returns the hiddenComments.
	 */
	public String getHiddenComments() {
		return hiddenComments;
	}

	/**
	 * @param hiddenComments
	 *            The hiddenComments to set.
	 */
	public void setHiddenComments(String hiddenComments) {
		this.hiddenComments = hiddenComments;
	}

	/**
	 * @return Returns the hiddenConfiguration.
	 */
	public String getHiddenConfiguration() {
		return hiddenConfiguration;
	}

	/**
	 * @param hiddenConfiguration
	 *            The hiddenConfiguration to set.
	 */
	public void setHiddenConfiguration(String hiddenConfiguration) {
		this.hiddenConfiguration = hiddenConfiguration;
	}

	/**
	 * @return Returns the hiddenReqParamsAll.
	 */
	public String getHiddenReqParamsAll() {
		return hiddenReqParamsAll;
	}

	/**
	 * @param hiddenReqParamsAll
	 *            The hiddenReqParamsAll to set.
	 */
	public void setHiddenReqParamsAll(String hiddenReqParamsAll) {
		this.hiddenReqParamsAll = hiddenReqParamsAll;
	}
	/**
	 * @return Returns the deletableForUser.
	 */
	public boolean isDeletableForUser() {
		return deletableForUser;
	}
	/**
	 * @param deletableForUser The deletableForUser to set.
	 */
	public void setDeletableForUser(boolean deletableForUser) {
		this.deletableForUser = deletableForUser;
	}
	/**
	 * @return Returns the editableForUser.
	 */
	public boolean isEditableForUser() {
		return editableForUser;
	}
	/**
	 * @param editableForUser The editableForUser to set.
	 */
	public void setEditableForUser(boolean editableForUser) {
		this.editableForUser = editableForUser;
	}
	/**
	 * @return Returns the viewableForUser.
	 */
	public boolean isViewableForUser() {
		return viewableForUser;
	}
	/**
	 * @param viewableForUser The viewableForUser to set.
	 */
	public void setViewableForUser(boolean viewableForUser) {
		this.viewableForUser = viewableForUser;
	}
	
	
	/**
	 * @return Returns the loadAdminMethodForEditPrint.
	 */
	public String getLoadAdminMethodForEditPrint() {
		return retriveAdminMethodForEditPrint();
	}
	/**
	 * @param loadAdminMethodForEditPrint The loadAdminMethodForEditPrint to set.
	 */
	public void setLoadAdminMethodForEditPrint(
			String loadAdminMethodForEditPrint) {
		this.loadAdminMethodForEditPrint = loadAdminMethodForEditPrint;
	}
	/**
	 * @return Returns the printBreadCrumbText.
	 */
	public String getPrintBreadCrumbText() {
		return printBreadCrumbText="Administration >> Admin Method ("+this.getSession().getAttribute("adminMethodNo")+") >> Print";
	}
	/**
	 * @param printBreadCrumbText The printBreadCrumbText to set.
	 */
	public void setPrintBreadCrumbText(String printBreadCrumbText) {
		this.printBreadCrumbText = printBreadCrumbText;
	}
	/**
	 * @return Returns the reqParamModifiedStaus.
	 */
	public String getReqParamModifiedStaus() {
		return reqParamModifiedStaus;
	}
	/**
	 * @param reqParamModifiedStaus The reqParamModifiedStaus to set.
	 */
	public void setReqParamModifiedStaus(String reqParamModifiedStaus) {
		this.reqParamModifiedStaus = reqParamModifiedStaus;
	}

}