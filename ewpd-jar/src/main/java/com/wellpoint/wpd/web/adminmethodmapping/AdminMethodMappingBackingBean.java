/*
 * Created on Oct 4, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.adminmethodmapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import com.wellpoint.wpd.business.framework.bo.AuditFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.adminmethodmapping.Response.AdminMethodMappingCreateResponse;
import com.wellpoint.wpd.common.adminmethodmapping.Response.AdminMethodMappingDeleteResponse;
import com.wellpoint.wpd.common.adminmethodmapping.Response.AdminMethodMappingEditResponse;
import com.wellpoint.wpd.common.adminmethodmapping.Response.AdminMethodMappingSearchResponse;
import com.wellpoint.wpd.common.adminmethodmapping.Response.AdminMethodMappingViewResponse;
import com.wellpoint.wpd.common.adminmethodmapping.Response.QuestionAnswerLookupResponse;
import com.wellpoint.wpd.common.adminmethodmapping.bo.AdminMethodMappingBO;
import com.wellpoint.wpd.common.adminmethodmapping.bo.QuestionAnswerGroupBO;
import com.wellpoint.wpd.common.adminmethodmapping.request.AdminMethodMappingCreateRequest;
import com.wellpoint.wpd.common.adminmethodmapping.request.AdminMethodMappingDeleteRequest;
import com.wellpoint.wpd.common.adminmethodmapping.request.AdminMethodMappingEditRequest;
import com.wellpoint.wpd.common.adminmethodmapping.request.AdminMethodMappingSearchRequest;
import com.wellpoint.wpd.common.adminmethodmapping.request.AdminMethodMappingViewRequest;
import com.wellpoint.wpd.common.adminmethodmapping.request.QuestionAnswerLookupRequest;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.item.bo.ItemBO;
import com.wellpoint.wpd.common.popup.bo.PopupFilterBO;
import com.wellpoint.wpd.common.popup.request.PopupRequest;
import com.wellpoint.wpd.common.popup.response.PopupResponse;
import com.wellpoint.wpd.common.subcatalog.bo.SubCatalogBO;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author U17066
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodMappingBackingBean extends WPDBackingBean {

	private String processingMethod;

	private String adminMethodNo;

	private boolean processMethodReq;

	private String processMethodDesc;

	private String processMethod;

	private String description;

	private String term;

	private String qualifier;

	private String pva;

	private String datatype;

	private String comments;

	private List questions;

	private List popupDataList;

	private String searchQuestionAnswer;

	private String searchValueForPopUp;

	private boolean searchCriteriaEntered;

	private List quesAnswerDataLookUpList;

	private String sortOrder;

	private boolean recordsGreaterThanMaxSize;

	private String termCriteria;

	private String termDesc;

	private String qualifierCriteria;

	private String qualifierDesc;

	private String pvaCriteria;

	private String dataTypeCriteria;

	private String adminMethodNoCriteria;

	private String descriptionCriteria;

	private List searchAdminMethodList;

	private boolean adminMethodNoReq;

	private String createdUser;

	private Date createdDate;

	private String lastUpdatedUser;

	private Date lastUpdatedDate;

	private String loadAdminMethod;

	private List adminMethodSysId = new ArrayList();

	private List processMethodList = new ArrayList();

	private String adminMethodsysID;

	private boolean aggregateTerm;
	private boolean deleteFlag=false;
	private boolean commentsReq;
	

 	private String questionAnswerString = "";
 	
 	private List questionAnswerDisplayList = new ArrayList();;
 	
 	private List questionAnswerGroupBOList = new ArrayList();
 	
 	private String questionDeleteString;
 	
 	private String deleteQuestionAnswerString;
 	
 	private boolean deleteButtonFlag=false;
 	
 	private String questionAnswerModifiedStaus;
 	/** Set to true when admin method mapping is under edit */
 	private static final String SESSION_IS_ADMIN_MTHD_MAPPING_EDIT = "adminMethodEditepage";
	private static final String SESSION_IS_ADMIN_MTHD_MAPPING_EDIT_PRINT = "adminMethodEditePrintPage";
 	
 	private boolean viewableForUser;
	
	private boolean editableForUser;
	
	private boolean deletableForUser;
	
	private String loadAdminMethodMappingForEditPrint;
	private String printBreadCrumbText;
	private boolean descriptionReq;
	private boolean TermReq;
	private String fromPage = "";
	
	/**
	 * @return Returns the fromPage.
	 */
	public String getFromPage() {
		return fromPage;
	}
	/**
	 * @param fromPage The fromPage to set.
	 */
	public void setFromPage(String fromPage) {
		this.fromPage = fromPage;
	}
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
	 * @return Returns the searchAdminMethodList.
	 */
	public List getSearchAdminMethodList() {
		return searchAdminMethodList;
	}

	/**
	 * @param searchAdminMethodList
	 *            The searchAdminMethodList to set.
	 */
	public void setSearchAdminMethodList(List searchAdminMethodList) {
		this.searchAdminMethodList = searchAdminMethodList;
	}

	/**
	 * @return Returns the pvaCriteria.
	 */
	public String getPvaCriteria() {
		return pvaCriteria;
	}

	/**
	 * @param pvaCriteria
	 *            The pvaCriteria to set.
	 */
	public void setPvaCriteria(String pvaCriteria) {
		this.pvaCriteria = pvaCriteria;
	}

	/**
	 * @return Returns the qualifierCriteria.
	 */
	public String getQualifierCriteria() {
		return qualifierCriteria;
	}

	/**
	 * @param qualifierCriteria
	 *            The qualifierCriteria to set.
	 */
	public void setQualifierCriteria(String qualifierCriteria) {
		this.qualifierCriteria = qualifierCriteria;
	}

	/**
	 * @return Returns the qualifierDesc.
	 */
	public String getQualifierDesc() {
		return qualifierDesc;
	}

	/**
	 * @param qualifierDesc
	 *            The qualifierDesc to set.
	 */
	public void setQualifierDesc(String qualifierDesc) {
		this.qualifierDesc = qualifierDesc;
	}

	/**
	 * @return Returns the termCriteria.
	 */
	public String getTermCriteria() {
		return termCriteria;
	}

	/**
	 * @param termCriteria
	 *            The termCriteria to set.
	 */
	public void setTermCriteria(String termCriteria) {
		this.termCriteria = termCriteria;
	}

	/**
	 * @return Returns the termDesc.
	 */
	public String getTermDesc() {
		return termDesc;
	}

	/**
	 * @param termDesc
	 *            The termDesc to set.
	 */
	public void setTermDesc(String termDesc) {
		this.termDesc = termDesc;
	}

	/**
	 * Method to Create an admin method mapping
	 * 
	 * @return
	 */
	public String createAdminMethodMapping() {

		/*
		 * Check if the mapping is Valid Admin Method No -- Mandatory Processing
		 * Method
		 * 
		 *  
		 */
		Logger
				.logInfo(" AdminMethodMappingBackingBean - Create Admin Method Mapping");
		if (!isValidMapping()) {
			this.setCreatedDate((Date)getSession().getAttribute("createdDate"));
			this.setLastUpdatedDate((Date)getSession().getAttribute("lastUpdatedDate"));
			setBreadCrumbText("Administration >> Admin Method >> Mapping Create");
			return "";

		}

		AdminMethodMappingCreateRequest adminMethodMappingCreateRequest = (AdminMethodMappingCreateRequest) this
				.getServiceRequest(ServiceManager.CREATE_ADMINMETHOD_MAPPING_CREATE_REQUEST);
		
		this.setFromPage("createEdit");;
		//To check mapping already exists.
		boolean duplicateExists = checkAdminMethodMappingExists();
		
		populateRequest(adminMethodMappingCreateRequest);
		
		if (duplicateExists) {
			addMessageToRequest(new ErrorMessage(
					WebConstants.ADMIN_MTHD_CREATE_UNIQUE_VALID));
			setBreadCrumbText("Administration >> Admin Method >> Mapping Create");
			return "";

		} else {
			AdminMethodMappingCreateResponse adminMethodMappingCreateResponse = (AdminMethodMappingCreateResponse) this
					.executeService(adminMethodMappingCreateRequest);

			retriveAdminMethodForEdit();

			addMessageToRequest(new InformationalMessage(
					WebConstants.ADMIN_METHOD_MAPPING_CREATE_SUCCESS));
			Logger
					.logInfo(" AdminMethodMappingBackingBean - Return from Create Admin Method Mapping Method");
			return "editAdminMethodMapping";
		}
	}

	/**
	 * 
	 * Method Check Mapping Already Exists
	 */
	private boolean checkAdminMethodMappingExists() {

		boolean duplicateExists = false;
		
		this.adminMethodsysID = WPDStringUtil.getStringFromTildaString(this
				.getAdminMethodNo(), 3, 3, 2);

		AdminMethodMappingSearchRequest adminMethodMappingSearchRequest = (AdminMethodMappingSearchRequest) this
				.getServiceRequest(ServiceManager.SEARCH_ADMINMETHOD_MAPPING_REQUEST);

		adminMethodMappingSearchRequest.setSearchFlag(true);
		adminMethodMappingSearchRequest
				.setAdminMethodSysId(this.adminMethodsysID);

		adminMethodMappingSearchRequest.setEditFlag(true);

		AdminMethodMappingSearchResponse adminMethodMappingSearchResponse = (AdminMethodMappingSearchResponse) this
				.executeService(adminMethodMappingSearchRequest);

		if (null != adminMethodMappingSearchResponse.getSearchResultList()
				&& adminMethodMappingSearchResponse.getSearchResultList()
						.size() > 0) {
			duplicateExists = true;
		}

		return duplicateExists;
	}

	/**
	 * @param Method to Populate AdminMethodMappingCreateRequest
	 */
	private void populateRequest(
			AdminMethodMappingCreateRequest adminMethodMappingCreateRequest) {

		String[] adminMethodNoArray = getAdminMethodNo().split("~");
		

		String[] terms = getTerm().split("~");
		String[] qualifiers = this.getQualifier().split("~");

		String termValue = terms[0];
		String qualifierValue = qualifiers[0];

		if (terms.length > 2 && this.aggregateTerm == true)
			termValue = termValue + "," + terms[2];
		
		  Date currentDate = ((AuditFactory) ObjectFactory
				.getFactory(ObjectFactory.AUDIT)).getAudit().getTime();
		  
		String possibleAnswer="";  
		HttpSession httpSession= this.getSession();
			
		Map questionAnswerMap=(Map)httpSession.getAttribute("questionAnswerMap");
		  
		if(null==questionAnswerMap || questionAnswerMap.isEmpty())
			possibleAnswer="null";
			
		List quesntionIdList = new ArrayList();
		List answerIdList = new ArrayList();
		
		  Iterator it = questionAnswerMap.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();
		        QuestionAnswerGroupBO answerGroupBO =(QuestionAnswerGroupBO)pairs.getValue();
		        quesntionIdList.add(answerGroupBO.getQuestionId());
		       // questionNbrList.add(answerGroupBO.getQuestionDesc());   
		        
		       for (Iterator iter = answerGroupBO.getPossibleAnswerIdList().iterator(); iter.hasNext();) {
				String ansId = (String) iter.next();
				answerIdList.add(ansId); 
				
				if(null!=possibleAnswer && !"".equals(possibleAnswer))
					possibleAnswer=possibleAnswer+","+ansId;
				else
					possibleAnswer=ansId;
		       }
		    }
		List qualifierList = WPDStringUtil.getListFromTildaString(this
				.getQualifier(), 2, 1, 2);

		this.adminMethodsysID = WPDStringUtil.getStringFromTildaString(this
				.getAdminMethodNo(), 3, 3, 2);

		String[] processMethodArray = this.getProcessMethod().split(",");

		for (int i = 0; i < processMethodArray.length; i++) {
			processMethodList.add(processMethodArray[i]);

		}
		this.setAdminMethodSysId(adminMethodSysId);
		this.setProcessMethodList(processMethodList);

		List pvaList = WPDStringUtil.getListFromTildaString(this.getPva(), 2,
				2, 2);

		List datatypeList = WPDStringUtil.getListFromTildaString(this
				.getDatatype(), 2, 1, 2);

		adminMethodMappingCreateRequest.setDatatypeList(datatypeList);
		adminMethodMappingCreateRequest.setPvaList(pvaList);
		adminMethodMappingCreateRequest.setQualifierList(qualifierList);
		adminMethodMappingCreateRequest.setTerm(termValue);
		adminMethodMappingCreateRequest.setComments(this.getComments());
		adminMethodMappingCreateRequest.setAdminMethodNo(adminMethodNoArray[0]);
		
		adminMethodMappingCreateRequest
				.setAdminMethodsysID(this.adminMethodsysID);
		adminMethodMappingCreateRequest.setProcessMethodList(processMethodList);
		adminMethodMappingCreateRequest.setQuesntionIdList(answerIdList);
		adminMethodMappingCreateRequest.setQuestionNbrList(quesntionIdList);
		adminMethodMappingCreateRequest.setPossibleAnswer(possibleAnswer);
		adminMethodMappingCreateRequest.setCreatedDate(currentDate);
		adminMethodMappingCreateRequest.setLastUpdatedDate(currentDate);
	}

	/**
	 * Method to convert String array to list
	 * 
	 * @return
	 */
	private List convertToList(String[] pva) {
		List pvaList = new ArrayList();
		for (int i = 0; i < pva.length; i++)
			pvaList.add(pva[i]);

		return pvaList;
	}

	/**
	 * Method to check mandatory fields.
	 */
	private boolean isValidMapping() {
		// TODO Auto-generated method stub
		boolean valid = true;
		boolean cmntsLength = true;
		if (this.adminMethodNo == null
				|| this.adminMethodNo.trim().length() == 0) {
			valid = false;
			this.setAdminMethodNoReq(true);
		}

		if (this.processMethod == null || this.processMethod.length() == 0) {
			valid = false;
			this.setProcessMethodReq(true);
		}
		if(this.getComments()!=null && this.getComments().length()>250) {
			cmntsLength = false;
			this.setCommentsReq(true);
			
		}
		if (!valid) {
			addMessageToRequest(new ErrorMessage(
					WebConstants.ADMIN_MTHD_MANDATORY_FIELD_REQUIRED));

		}else if(!cmntsLength){
			addMessageToRequest(new ErrorMessage(
					WebConstants.ADMIN_MTHD_CMNTS_LENGTH_NOT_EXCEED));	
			valid = false;			
		}
		

		return valid;
	}

	/**
	 * This Updates an admin Method mapping after editing
	 * 
	 * @return
	 */
	public String updateAdminMethodMapping() {

		AdminMethodMappingEditRequest adminMethodMappingEditRequest = (AdminMethodMappingEditRequest) this
				.getServiceRequest(ServiceManager.EDIT_ADMINMETHOD_MAPPING_CREATE_REQUEST);

		if(this.getComments()!=null && this.getComments().length()>250) {
			this.setCommentsReq(true);
			String breadCrumb = "Administration >> Admin Method ("
				+ this.getAdminMethodNo().split("~")[0] + ")>> Mapping Edit";
			setBreadCrumbText(breadCrumb);
			addMessageToRequest(new ErrorMessage(
					WebConstants.ADMIN_MTHD_CMNTS_LENGTH_NOT_EXCEED));	
			this.setLastUpdatedDate((Date)getSession().getAttribute("lastUpdatedDate"));
			this.setCreatedDate((Date)getSession().getAttribute("createdDate"));
			return "";
		}

		String breadCrumb = "Administration >> Admin Method ("
				+ this.getAdminMethodNo().split("~")[0] + ")>> Mapping Edit";
		setBreadCrumbText(breadCrumb);
		populateRequestForEdit(adminMethodMappingEditRequest);

		AdminMethodMappingEditResponse adminMethodMappingEditResponse = (AdminMethodMappingEditResponse) this
				.executeService(adminMethodMappingEditRequest);

		retriveAdminMethodForEdit();

		addMessageToRequest(new InformationalMessage(
				WebConstants.ADMIN_METHOD_EDIT_SUCCESS));

		return "";
	}

	/**
	 * Delete An admin Method mapping
	 * 
	 * @return
	 */
	public String deleteAdminMethodMapping() {
		Logger
		.logInfo(" AdminMethodMappingBackingBean -  Delete Admin Method Mapping");

		AdminMethodMappingDeleteRequest adminMethodMappingDeleteRequest = (AdminMethodMappingDeleteRequest) this
				.getServiceRequest(ServiceManager.DELETE_ADMINMETHOD_MAPPING_DELETE_REQUEST);

		adminMethodMappingDeleteRequest.setAdminMethodsysID(this
				.getAdminMethodsysID());

		AdminMethodMappingDeleteResponse adminMethodMappingDeleteResponse = (AdminMethodMappingDeleteResponse) this
				.executeService(adminMethodMappingDeleteRequest);

		

		setBreadCrumbText("Administration >> Admin Method >> Mapping Locate");
		Logger
				.logInfo(" AdminMethodMappingBackingBean - Returning from Delete Admin Method Mapping");
        this.setDeleteFlag(true);
		String search = searchAdminMethodMapping();
		addMessageToRequest(new InformationalMessage(
				BusinessConstants.ADMIN_METHOD_MAP_DELETE_SUCCESS));
	
		
		return "";

	}

	/*public String editAdminMethodMapping() {

		AdminMethodMappingEditRequest adminMethodMappingEditRequest = (AdminMethodMappingEditRequest) this
				.getServiceRequest(ServiceManager.EDIT_ADMINMETHOD_MAPPING_CREATE_REQUEST);

		if (isValidMapping()) {
			String breadCrumb = "Administration >> Admin Method ("
					+ this.getAdminMethodNo().split("~")[0] + ")>> Mapping  Edit";
			setBreadCrumbText(breadCrumb);
			return "";
		}

		populateRequestForEdit(adminMethodMappingEditRequest);

		AdminMethodMappingEditResponse adminMethodMappingEditResponse = (AdminMethodMappingEditResponse) this
				.executeService(adminMethodMappingEditRequest);

		return "";
	}
*/
	/**
	 * Search For an admin Method mapping
	 * 
	 * @return
	 */
	public String searchAdminMethodMapping() {

		Logger
				.logInfo(" AdminMethodMappingBackingBean - Search Admin Method Mapping");
		AdminMethodMappingSearchRequest adminMethodMappingSearchRequest = (AdminMethodMappingSearchRequest) this
				.getServiceRequest(ServiceManager.SEARCH_ADMINMETHOD_MAPPING_REQUEST);
		boolean valid = true;
		/*String adminMethodSysId = WPDStringUtil.getStringFromTildaString(this
				.getAdminMethodNoCriteria(), 3, 3, 2);	
		String adminMethodDescription = WPDStringUtil.getStringFromTildaString(this
				.getAdminMethodNoCriteria(), 3, 2, 2);	
		String adminmethodSysIdArray[]=adminMethodSysId.split(",");*/
		//String adminMethodSysId=this.adminMethodsysID;
		List processingMethodList=WPDStringUtil.getListFromTildaString(this.getProcessMethod(),2,1,2);
		String[] term = (this.termCriteria).split("~");
		String termComma = getCommaSeparated(term);
		List termList=new ArrayList();
		if (!this.aggregateTerm)
			termList = WPDStringUtil.getListFromCommaString(termComma, 1, 1, 2);
		else
			termList = WPDStringUtil.getListFromTildaString(termComma, 1, 1, 2);
		/*List termList = WPDStringUtil
				.getListFromCommaString(termComma, 1, 1, 2);*/
		List qualifierList = WPDStringUtil.getListFromTildaString(this
				.getQualifierCriteria(), 2, 1, 2);
		List pvaList = WPDStringUtil.getListFromTildaString(this.getPvaCriteria(), 2,
				2, 2);
		if ((this.adminMethodNoCriteria == null || this.adminMethodNoCriteria.trim().length() == 0) && (this.descriptionCriteria.trim()==null || this.descriptionCriteria.trim().length()==0 )) {
			if (!(qualifierList.size() > 0 || pvaList.size() > 0
					|| termList.size() > 0 || processingMethodList.size() > 0)) {

				addMessageToRequest(new ErrorMessage(
						WebConstants.ATLEAST_ONE_SEARCH));

				valid = false;
			}
		}else if(this.adminMethodNoCriteria != null && this.adminMethodNoCriteria.trim().length() != 0 ){
			try{
				Integer.parseInt(this.adminMethodNoCriteria.trim()); 
			}catch(Exception ex){
				addMessageToRequest(new ErrorMessage(
						WebConstants.ADMIN_MTHD_NMBR_NOT_NUMERIC));
				valid = false;
				this.setAdminMethodNoReq(true);
			}
		}
		else if(this.descriptionCriteria.trim()!=null && this.descriptionCriteria.trim().length() > BusinessConstants.ADMN_METHOD_FIELDS) {
			addMessageToRequest(new ErrorMessage(
					WebConstants.ADMIN_MTHD_DESC_LENGTH_NOT_EXCEED));
			this.setDescriptionReq(true);
			valid = false;			
		}			
		else if (term.length > 4 && (this.aggregateTerm == true)) {
			addMessageToRequest(new ErrorMessage(
					WebConstants.AGGREAGET_TERM_VALID));
			this.setTermReq(true);
			valid = false;
		}
		
		if (!valid) {
			setBreadCrumbText("Administration >> Admin Method  >> Mapping Locate");
			return "";
		}
		//adminMethodMappingSearchRequest.setAdminMethodSysId(adminMethodSysId);
		adminMethodMappingSearchRequest.setAdminMethodNo(this.adminMethodNoCriteria.trim());
		adminMethodMappingSearchRequest.setDescriptionCriteria(this.descriptionCriteria.trim());
		adminMethodMappingSearchRequest.setProcessMethodList(processingMethodList);
		adminMethodMappingSearchRequest.setQualifierList(qualifierList);
		adminMethodMappingSearchRequest.setTermList(termList);
		adminMethodMappingSearchRequest.setPvaList(pvaList);
		adminMethodMappingSearchRequest.setDeleteFlag(this.isDeleteFlag());
		//this.setDescriptionCriteria(adminMethodDescription);
		try {
			setUserPermissions();
		} catch (SevereException e) {
			addMessageToRequest(new ErrorMessage(
					e.getMessage()));
			setBreadCrumbText("Administration >> Admin Method  >> Mapping Locate");
			return "";
		}
		AdminMethodMappingSearchResponse adminMethodMappingSearchResponse = (AdminMethodMappingSearchResponse) this
				.executeService(adminMethodMappingSearchRequest);
		setBreadCrumbText("Administration >>Admin Method  >> Mapping Locate");
		if(null != adminMethodMappingSearchResponse
				.getSearchResultList() && (adminMethodMappingSearchResponse.getSearchResultList().size()>0)){
			
			Collections.sort(adminMethodMappingSearchResponse.getSearchResultList());
			
			this.setSearchAdminMethodList(adminMethodMappingSearchResponse.getSearchResultList());
						
		}
		

		Logger
				.logInfo(" AdminMethodMappingBackingBean -Return from Search Admin Method Mapping Method");

		return "";
	}

	private void setUserPermissions() throws SevereException {
		User user = getUser();
		viewableForUser = user.isAuthorized(WebConstants.ADMIN_METHOD_MODULE,WebConstants.ADMIN_MAINTAIN_MAPPING_TASK_MAINTAIN);
		deletableForUser = user.isAuthorized(WebConstants.ADMIN_METHOD_MODULE,WebConstants.ADMIN_METHOD_MAPPING_DEL_TASK);
		editableForUser = user.isAuthorized(WebConstants.ADMIN_METHOD_MODULE,WebConstants.ADMIN_METHOD_MAPPING_EDT_TASK);
	}


	/**
	 * 
	 * Method to get Processing Method List.
	 * @return
	 */
	public List getprocessingMethodList() {
		Logger
				.logInfo(" AdminMethodMappingBackingBean - Get Processing Method List");
		PopupRequest request = (PopupRequest) this
				.getServiceRequest(ServiceManager.POPUP_REQUEST);
		request.setQueryName("processingType");
		request.setPopAction(WebConstants.FILTER_ACTION);
		PopupResponse response = null;
		response = (PopupResponse) executeService(request);
		List processingList = new ArrayList();
		String spsValues;
		List values = response.getResultList();
		List newSPSValues = new ArrayList();

		newSPSValues = parseSPSValues(values);

		for (int i = 0; i < newSPSValues.size(); i++) {
			PopupFilterBO popupFilterBO1 = (PopupFilterBO) newSPSValues.get(i);

			processingList.add(new SelectItem(popupFilterBO1.getKeyValue(),
					popupFilterBO1.getDescription()));
		}
		Logger.logDebug("processing Method:" + getProcessMethod());
		return processingList;
	}

	private List parseSPSValues(List values) {

		List newSPSValues = new ArrayList();

		for (int i = 0; i < values.size(); i++) {

			PopupFilterBO popupFilterBO1 = (PopupFilterBO) values.get(i);//0,2,4,6,
			i++;
			PopupFilterBO popupFilterBO2 = (PopupFilterBO) values.get(i);//1,3,5,
			popupFilterBO1.setKeyValue(popupFilterBO1.getKeyValue() + ","
					+ popupFilterBO2.getKeyValue());
			newSPSValues.add(popupFilterBO1);
		}
		return newSPSValues;
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
	 * @return Returns the datatype.
	 */
	public String getDatatype() {
		return datatype;
	}

	/**
	 * @param datatype
	 *            The datatype to set.
	 */
	public void setDatatype(String datatype) {
		this.datatype = datatype;
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
	 * @return Returns the processingMethod.
	 */
	public String getProcessingMethod() {
		return processingMethod;
	}

	/**
	 * @param processingMethod
	 *            The processingMethod to set.
	 */
	public void setProcessingMethod(String processingMethod) {
		this.processingMethod = processingMethod;
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
	 * @return Returns the pva.
	 */
	public String getPva() {
		return pva;
	}

	/**
	 * @param pva
	 *            The pva to set.
	 */
	public void setPva(String pva) {
		this.pva = pva;
	}

	/**
	 * @return Returns the qualifier.
	 */
	public String getQualifier() {
		return qualifier;
	}

	/**
	 * @param qualifier
	 *            The qualifier to set.
	 */
	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	/**
	 * @return Returns the questions.
	 */
	public List getQuestions() {
		return questions;
	}

	/**
	 * @param questions
	 *            The questions to set.
	 */
	public void setQuestions(List questions) {
		this.questions = questions;
	}

	/**
	 * @return Returns the term.
	 */
	public String getTerm() {
		return term;
	}

	/**
	 * @param term
	 *            The term to set.
	 */
	public void setTerm(String term) {
		this.term = term;
	}

	/**
	 * @return Returns the popupDataList.
	 */
	public List getPopupDataList() {
		return popupDataList;
	}

	/**
	 * @param popupDataList
	 *            The popupDataList to set.
	 */
	public void setPopupDataList(List popupDataList) {
		this.popupDataList = popupDataList;
	}

	/**
	 * Method For showing Questions and answers in the pop up.
	 * 
	 * 
	 * @return String
	 */

	public String getSearchQuestionAnswer() {
		Logger
				.logInfo(" AdminMethodMaintainBackingBean - Get Question Answer List");

		QuestionAnswerLookupRequest questionAnswerLookupRequest = (QuestionAnswerLookupRequest) this
				.getServiceRequest(ServiceManager.QUESTION_ANSWER_LOOKUP_REQUEST);
		
		if(null!=getRequest().getParameter("searchValueForPopUp")&& !("").equals(getRequest().getParameter("searchValueForPopUp"))){
			searchValueForPopUp = getRequest().getParameter("searchValueForPopUp");
		}
		
			if (null != searchValueForPopUp
				&& !(WebConstants.EMPTY_STRING).equals(searchValueForPopUp
						.trim())) {
			searchCriteriaEntered = true;
			searchValueForPopUp = WPDStringUtil.addDelimiterForAMDesc(searchValueForPopUp);
			searchValueForPopUp = "%" + searchValueForPopUp + "%";
			//setSelectedQuestionAnswerToMap(this.questionAnswerString);
			
		} else {
			searchCriteriaEntered = true;
			searchValueForPopUp = "%";
		}
		
		questionAnswerLookupRequest.setSearchString((searchValueForPopUp)
				.trim().toUpperCase());
		questionAnswerLookupRequest
				.setSearchCriteriaEntered(searchCriteriaEntered);

		QuestionAnswerLookupResponse questionAnswerLookupLookupResponse = (QuestionAnswerLookupResponse) this
				.executeService(questionAnswerLookupRequest);
		Logger.logDebug(questionAnswerLookupLookupResponse.getQuesAnswerList());

		List resultList = questionAnswerLookupLookupResponse
				.getQuesAnswerList();

		if(null != resultList && !resultList.isEmpty()){
		resultList = createQuesAnswerList(resultList);
		}
		

		if (null != resultList && !resultList.isEmpty()) {

			if (questionAnswerLookupRequest.isRecordsGrtThanMaxSize()) {
				setRecordsGreaterThanMaxSize(true);
			} else {
				setRecordsGreaterThanMaxSize(false);
			}
			Iterator iter = resultList.iterator();
			if (iter.hasNext()) {
				Object obj = iter.next();
				if (obj instanceof SubCatalogBO || obj instanceof ItemBO) {
					Collections.sort(resultList);
				}
			}
			this.setQuesAnswerDataLookUpList(resultList);
		} else
			this.setQuesAnswerDataLookUpList(null);
		if (searchCriteriaEntered)
			this.setSearchValueForPopUp("");
		Logger
				.logInfo(" AdminMethodMaintainBackingBean - Return Question Answer List Method");

		return "test";

	}

	/**
	 * Method to view Admin Method
	 */
	private String retriveAdminMethod() {
		Logger.logInfo(" AdminMethodMaintainBackingBean - View Admin Method");

		String adminMethodSysId = (getRequest()
				.getParameter("adminMethodSysId"));

		if (adminMethodSysId != null || "".equals(adminMethodSysId)) {

			AdminMethodMappingViewRequest adminMethodMappingViewRequest = (AdminMethodMappingViewRequest) this
					.getServiceRequest(ServiceManager.VIEW_ADMIN_METHOD_MAPPING_REQUEST);
			adminMethodMappingViewRequest.setAdminMethodSysId(adminMethodSysId);
			AdminMethodMappingViewResponse adminMethodMappingViewResponse = (AdminMethodMappingViewResponse) this
					.executeService(adminMethodMappingViewRequest);

			if (null != adminMethodMappingViewResponse.getSearchResultList()) {
				setValuesToBackingBeanForView(adminMethodMappingViewResponse
						.getSearchResultList());
				setBreadCrumbText("Administration >> Admin Method ("
						+ this.getAdminMethodNo() + ")>> Mapping View");

			}

		}
		Logger
				.logInfo(" AdminMethodMaintainBackingBean - return from View Admin Method");
		return "";
	}
	/**
	 * Method to get values for admin Method Edit Print
	 * @return
	 */
	
	private String retriveMethodMappingForEditPrint(){
		Logger.logInfo(" AdminMethodMaintainBackingBean - View Admin Method");

		String adminMethodSysId = (String) this.getSession().getAttribute(SESSION_IS_ADMIN_MTHD_MAPPING_EDIT_PRINT);

		if (adminMethodSysId != null || "".equals(adminMethodSysId)) {

			AdminMethodMappingViewRequest adminMethodMappingViewRequest = (AdminMethodMappingViewRequest) this
					.getServiceRequest(ServiceManager.VIEW_ADMIN_METHOD_MAPPING_REQUEST);
			adminMethodMappingViewRequest.setAdminMethodSysId(adminMethodSysId);
			AdminMethodMappingViewResponse adminMethodMappingViewResponse = (AdminMethodMappingViewResponse) this
					.executeService(adminMethodMappingViewRequest);

			if (null != adminMethodMappingViewResponse.getSearchResultList()) {
				setValuesToBackingBeanForView(adminMethodMappingViewResponse
						.getSearchResultList());
				setBreadCrumbText("Administration >> Admin Method ("
						+ this.getAdminMethodNo() + ")>> Mapping Print");

			}

		}
		Logger
				.logInfo(" AdminMethodMaintainBackingBean - return from View Admin Method");
		return "";
		
	}

	/**
	 * Method to get Values for Edit
	 */
	public String retriveAdminMethodForEdit() {
		Logger.logInfo(" AdminMethodMaintainBackingBean - View Admin Method");

		
		if (this.getAdminMethodsysID() != null
				|| "".equals(this.getAdminMethodsysID())) {

			AdminMethodMappingSearchRequest adminMethodMappingSearchRequest = (AdminMethodMappingSearchRequest) this
					.getServiceRequest(ServiceManager.SEARCH_ADMINMETHOD_MAPPING_REQUEST);
			String[] adminMethodsysIDArray = this.getAdminMethodsysID().split(
					",");
			List adminMethodSysId = new ArrayList();
			for (int i = 0; i < adminMethodsysIDArray.length; i++) {
				adminMethodSysId.add(adminMethodsysIDArray[i]);

			}
			
			adminMethodMappingSearchRequest
					.setAdminMethodSysId(adminMethodsysIDArray[0]);
			adminMethodMappingSearchRequest.setEditFlag(true);
			
			adminMethodMappingSearchRequest.setEditRetireve(true);

			AdminMethodMappingSearchResponse adminMethodMappingSearchResponse = (AdminMethodMappingSearchResponse) this
					.executeService(adminMethodMappingSearchRequest);

			
			HttpSession httpSession= this.getSession();
			
			httpSession.setAttribute("questionAnswerMap",adminMethodMappingSearchResponse.getSelectedQuestionAnswerMap());
			
			if (null != adminMethodMappingSearchResponse.getSearchResultList()) {
				setValuesToBackingBeanForEdit(adminMethodMappingSearchResponse
						.getSearchResultList());
				setBreadCrumbText("Administration >> Admin Method  ("
						+ WPDStringUtil.getStringFromTildaString(this
								.getAdminMethodNo(), 3, 1, 2) + ")>> Mapping Edit");
				
				httpSession.setAttribute(SESSION_IS_ADMIN_MTHD_MAPPING_EDIT,"true");
				
				if(this.getFromPage() !=null && ! this.getFromPage().equals("createEdit"))
					this.setFromPage("locateEdit");
				
				httpSession.setAttribute("adminMethodEditepageBC","Administration >> Admin Method  ("
						+ WPDStringUtil.getStringFromTildaString(this
								.getAdminMethodNo(), 3, 1, 2) + ")>> Mapping Edit");
			}

		}

		return "editAdminMethodMapping";
	}

	/**
	 * @param Method to set values to backing bean for edit
	 */
	private void setValuesToBackingBeanForEdit(List searchResultList) {
		for (Iterator iter = searchResultList.iterator(); iter.hasNext();) {
			AdminMethodMappingBO adminMethodMappingBO = (AdminMethodMappingBO) iter
					.next();
			
			if(adminMethodMappingBO.getTerm()!=null){
				String[] term= (adminMethodMappingBO.getTerm()).split("~");
		
			if(term.length==4){
				this.setAggregateTerm(true);
			}
			}
			this.setAdminMethodNo(adminMethodMappingBO.getAdminMethodNo());
			String adminsysid = WPDStringUtil.getStringFromTildaString(adminMethodMappingBO.getAdminMethodNo(), 3, 3, 2);
			this.getSession().setAttribute(SESSION_IS_ADMIN_MTHD_MAPPING_EDIT_PRINT,adminsysid);
			this.getSession().setAttribute("adminMethodNo",WPDStringUtil.getStringFromTildaString(adminMethodMappingBO.getAdminMethodNo(), 3, 1, 2));
			this.setDescription(adminMethodMappingBO.getDescription());
			this.setProcessMethodDesc(adminMethodMappingBO
					.getProcessMethodDesc());
			this.setProcessMethod(adminMethodMappingBO.getProcessMethod());
			this.setComments(covertNulltoEmpty(adminMethodMappingBO
					.getComments()));
			this.setTerm(covertNulltoEmpty(adminMethodMappingBO.getTerm()));
			this.setQualifier(covertNulltoEmpty(adminMethodMappingBO
					.getQualifier()));
			this.setPva(covertNulltoEmpty(adminMethodMappingBO.getPva()));
			this.setDatatype(covertNulltoEmpty(adminMethodMappingBO
					.getDatatype()));
			this.setQuestions(adminMethodMappingBO.getQuestionAnswerList());
			this.setCreatedUser(adminMethodMappingBO.getCreatedUser());
			this.setCreatedDate( adminMethodMappingBO.getCreatedDate());
			this.setLastUpdatedUser(adminMethodMappingBO.getLastUpdatedUser());
			this.setLastUpdatedDate(adminMethodMappingBO.getLastUpdatedDate());
			this.getSession().setAttribute("createdDate",
                    adminMethodMappingBO.getCreatedDate());
this.getSession().setAttribute("lastUpdatedDate",
                    adminMethodMappingBO.getLastUpdatedDate());

		}
	}

	String covertNulltoEmpty(String str) {

		if (str == null || str.equalsIgnoreCase("null~null"))
			return "";

		return str;

	}

	/**
	 * @param searchResultList
	 */
	private void setValuesToBackingBeanForView(List searchResultList) {
		for (Iterator iter = searchResultList.iterator(); iter.hasNext();) {
			AdminMethodMappingBO adminMethodMappingBO = (AdminMethodMappingBO) iter
					.next();
			this.setAdminMethodNo(adminMethodMappingBO.getAdminMethodNo());
			this.setDescription(adminMethodMappingBO.getDescription());
			this.setProcessMethodDesc(adminMethodMappingBO
					.getProcessMethodDesc());
			this.setComments(adminMethodMappingBO.getComments());
			this.setTerm(adminMethodMappingBO.getTerm());
			this.setQualifier(adminMethodMappingBO.getQualifier());
			this.setPva(adminMethodMappingBO.getPvaid());
			this.setDatatype(adminMethodMappingBO.getDatatype());
			this.setQuestions(adminMethodMappingBO.getQuestionAnswerList());
			this.setCreatedUser(adminMethodMappingBO.getCreatedUser());
			this.setCreatedDate((Date)getSession().getAttribute("createdDate"));
			this.setLastUpdatedUser(adminMethodMappingBO.getLastUpdatedUser());
			this.setLastUpdatedDate((Date)getSession().getAttribute("lastUpdatedDate"));
		}
	}

	/**
	 * @param searchQuestionAnswer
	 *            The searchQuestionAnswer to set.
	 */
	public void setSearchQuestionAnswer(String searchQuestionAnswer) {
		this.searchQuestionAnswer = searchQuestionAnswer;
	}

	/**
	 * @return Returns the searchValueForPopUp.
	 */
	public String getSearchValueForPopUp() {
		return searchValueForPopUp;
	}

	/**
	 * @param searchValueForPopUp
	 *            The searchValueForPopUp to set.
	 */
	public void setSearchValueForPopUp(String searchValueForPopUp) {
		this.searchValueForPopUp = searchValueForPopUp;
	}

	private List createQuesAnswerList(List searchResults) {
		
		QuestionAnswerGroupBO questionAnswerGroupBO = new QuestionAnswerGroupBO();
		QuestionAnswerGroupBO questionAnswerGroupBO1 = new QuestionAnswerGroupBO();
		QuestionAnswerGroupBO questionAnswerGroupnewBO = null;
		QuestionAnswerGroupBO answerGroupBO = null;
		List answerList = null;
		List quesAnswerList = new ArrayList();
		String questionId = "";
		String questionDesc = "";
		List answer = new ArrayList();
		List newAnswerList = null;
		// Adding one dummy question and at the QAList and removing at the end.
		
		if(searchResults.size()> 0){
			QuestionAnswerGroupBO dummyAnswerGroupBO = new QuestionAnswerGroupBO();
			dummyAnswerGroupBO.setQuestionId("dummyID");
			dummyAnswerGroupBO.setQuestionDesc("dummyDesc");
			searchResults.add(dummyAnswerGroupBO);
		}
		
		// Add check for question too.
		for (Iterator itr = searchResults.iterator(); itr.hasNext();) {
			questionAnswerGroupBO = (QuestionAnswerGroupBO) itr.next();

			if (!(questionId.equalsIgnoreCase(questionAnswerGroupBO
					.getQuestionId()))) {
				if (quesAnswerList.size() > 0) {

					newAnswerList = new ArrayList();
					for (int i = 0; i < answerList.size(); i += 3) {
						answerGroupBO = new QuestionAnswerGroupBO();
						questionAnswerGroupBO1 = (QuestionAnswerGroupBO) answerList
								.get(i);
						answerGroupBO
								.setPossibleAnswerId(questionAnswerGroupBO1
										.getPossibleAnswerId());
						answerGroupBO
								.setPossibleAnswerDesc(questionAnswerGroupBO1
										.getPossibleAnswerDesc());
						answerGroupBO.setAnswerChecked(checkAnswersInMap(questionAnswerGroupnewBO.getQuestionId(),questionAnswerGroupBO1.getPossibleAnswerId(),1));
						if (answerList.size() > i + 1) {
							questionAnswerGroupBO1 = (QuestionAnswerGroupBO) answerList
									.get(i + 1);
							answerGroupBO
									.setPossibleAnswerId2(questionAnswerGroupBO1
											.getPossibleAnswerId());
							answerGroupBO
									.setPossibleAnswerDesc2(questionAnswerGroupBO1
											.getPossibleAnswerDesc());
							answerGroupBO.setAnswerChecked1(checkAnswersInMap(questionAnswerGroupnewBO.getQuestionId(),questionAnswerGroupBO1.getPossibleAnswerId(),2));

						}
						if (answerList.size() > i + 2) {
							questionAnswerGroupBO1 = (QuestionAnswerGroupBO) answerList
									.get(i + 2);
							answerGroupBO
									.setPossibleAnswerId3(questionAnswerGroupBO1
											.getPossibleAnswerId());
							answerGroupBO
									.setPossibleAnswerDesc3(questionAnswerGroupBO1
											.getPossibleAnswerDesc());
						//if(null != questionAnswerGroupBO1.getPossibleAnswerId() && !"".equals(questionAnswerGroupBO1.getPossibleAnswerId()))	
							answerGroupBO.setAnswerChecked2(checkAnswersInMap(questionAnswerGroupnewBO.getQuestionId(),questionAnswerGroupBO1.getPossibleAnswerId(),3));

						}
						newAnswerList.add(answerGroupBO);

					}
					
					questionAnswerGroupnewBO = new QuestionAnswerGroupBO();
					questionAnswerGroupnewBO
							.setQuestionId(questionAnswerGroupBO
									.getQuestionId());
					questionAnswerGroupnewBO.setQuestionDesc("");
					questionAnswerGroupnewBO
							.setPossibleAnswerIdList(newAnswerList);
					quesAnswerList.add(questionAnswerGroupnewBO);

				}
				questionAnswerGroupnewBO = new QuestionAnswerGroupBO();
				questionId = questionAnswerGroupBO.getQuestionId();
				questionAnswerGroupnewBO.setQuestionId(questionAnswerGroupBO
						.getQuestionId());
				questionAnswerGroupnewBO.setQuestionChecked(checkQuestionsInMap(questionAnswerGroupBO
						.getQuestionId()));
				questionAnswerGroupnewBO.setQuestionDesc(questionAnswerGroupBO
						.getQuestionDesc());
				questionAnswerGroupnewBO.setPossibleAnswerIdList(null);
				quesAnswerList.add(questionAnswerGroupnewBO);
				answerList = new ArrayList();
			}

			answerGroupBO = new QuestionAnswerGroupBO();
			answerGroupBO.setPossibleAnswerId(questionAnswerGroupBO
					.getPossibleAnswerId());
			answerGroupBO.setPossibleAnswerDesc(questionAnswerGroupBO
					.getPossibleAnswerDesc());

			answerList.add(answerGroupBO);

		}
		questionAnswerGroupnewBO = new QuestionAnswerGroupBO();
		questionAnswerGroupnewBO.setQuestionId(questionAnswerGroupBO
				.getQuestionId());
		questionAnswerGroupnewBO.setQuestionDesc("");
		questionAnswerGroupnewBO.setPossibleAnswerIdList(newAnswerList);
		quesAnswerList.add(questionAnswerGroupnewBO);
		// Removing dummy row. 
		if(searchResults.size()>0){
			quesAnswerList.remove(quesAnswerList.size()-1);
			quesAnswerList.remove(quesAnswerList.size()-1);
		}
		return quesAnswerList;
	}

	/**
	 * Checks whether the answer id is present in the questionAnswerMap. If its present, the method returns true.
	 * @param questionId
	 * @param answerId
	 * @param answerLocation
	 * @return
	 */
	private boolean checkAnswersInMap(String questionId, String answerId,
			int answerLocation) {

		HttpSession httpSession = getSession();
		Map questionAnswerMap = (Map) httpSession
				.getAttribute("questionAnswerMap");

		if (null!=questionAnswerMap && !questionAnswerMap.isEmpty() && questionAnswerMap.containsKey(questionId)) {

			QuestionAnswerGroupBO questionAnswerBO = (QuestionAnswerGroupBO) questionAnswerMap
					.get(questionId);
			
			if(questionAnswerBO.getPossibleAnswerIdList().contains(answerId))
				return true;
			
//			if (answerLocation == 1) {
//				if (null!= questionAnswerBO.getPossibleAnswerId() && !"".equals(questionAnswerBO.getPossibleAnswerId()) && questionAnswerBO.getPossibleAnswerId().equalsIgnoreCase(
//						answerId))
//					return true;
//			} else if (answerLocation == 2) {
//				if (null!= questionAnswerBO.getPossibleAnswerId2() && !"".equals(questionAnswerBO.getPossibleAnswerId2()) && questionAnswerBO.getPossibleAnswerId2().equalsIgnoreCase(
//						answerId))
//					return true;
//			} else {
//				if (null!= questionAnswerBO.getPossibleAnswerId3() && !"".equals(questionAnswerBO.getPossibleAnswerId3()) && questionAnswerBO.getPossibleAnswerId3().equalsIgnoreCase(
//						answerId))
//					return true;
//			}
		}
		return false;

	}
	private boolean checkQuestionsInMap(String questionId) {
		
		HttpSession httpSession = getSession();
		Map questionAnswerMap = (Map) httpSession
				.getAttribute("questionAnswerMap");
		
		if (null!=questionAnswerMap && !questionAnswerMap.isEmpty() && questionAnswerMap.containsKey(questionId)) 
			return true;
			
		return false;

	}
	/**
	 * @param quesAnswerDataLookUpList2
	 * @return
	 */
	private List processQuesAnswerList(List quesAnswerDataLookUpList2) {
		QuestionAnswerGroupBO questionAnswerGroupBO = new QuestionAnswerGroupBO();

		for (Iterator itr = quesAnswerDataLookUpList2.iterator(); itr.hasNext();) {
			questionAnswerGroupBO = (QuestionAnswerGroupBO) itr.next();

		}

		return null;
	}

	/**
	 * Method to make the tilda separated to comma separated
	 * 
	 * @param term
	 * @return
	 */
	private String getCommaSeparated(String[] term) {

		if (null != term && term.length > 0) {

			StringBuffer termComma = new StringBuffer();
			for (int i = 0; i < term.length; i = i + 2) {
				termComma.append(term[i]).append(',');
			}
			termComma.deleteCharAt(termComma.length() - 1);
			return termComma.toString();

		}
		return "";
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
	 * @return Returns the recordsGreaterThanMaxSize.
	 */
	public boolean isRecordsGreaterThanMaxSize() {
		return recordsGreaterThanMaxSize;
	}

	/**
	 * @param recordsGreaterThanMaxSize
	 *            The recordsGreaterThanMaxSize to set.
	 */
	public void setRecordsGreaterThanMaxSize(boolean recordsGreaterThanMaxSize) {
		this.recordsGreaterThanMaxSize = recordsGreaterThanMaxSize;
	}

	/**
	 * @return Returns the sortOrder.
	 */
	public String getSortOrder() {
		return sortOrder;
	}

	/**
	 * @param sortOrder
	 *            The sortOrder to set.
	 */
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	/**
	 * @return Returns the quesAnswerDataLookUpList.
	 */
	public List getQuesAnswerDataLookUpList() {
		return quesAnswerDataLookUpList;
	}

	/**
	 * @param quesAnswerDataLookUpList
	 *            The quesAnswerDataLookUpList to set.
	 */
	public void setQuesAnswerDataLookUpList(List quesAnswerDataLookUpList) {
		this.quesAnswerDataLookUpList = quesAnswerDataLookUpList;
	}

	/**
	 * @return Returns the searchCriteriaEntered.
	 */
	public boolean isSearchCriteriaEntered() {
		return searchCriteriaEntered;
	}

	/**
	 * @param searchCriteriaEntered
	 *            The searchCriteriaEntered to set.
	 */
	public void setSearchCriteriaEntered(boolean searchCriteriaEntered) {
		this.searchCriteriaEntered = searchCriteriaEntered;
	}

	/**
	 * @param adminMethodMappingCreateRequest
	 */
	private void populateRequestForEdit(
			AdminMethodMappingEditRequest adminMethodMappingEditRequest) {

		String[] adminMethodNoArray = this.getAdminMethodNo().split("~");

	String[] terms = (this.getTerm()).split("~");
		//String termComma = getCommaSeparated(term);
		String termValue = terms[0];
		if (terms.length > 2 && this.aggregateTerm == true)
			termValue = termValue + "," + terms[2];
		
        String possibleAnswer="";  
		HttpSession httpSession= this.getSession();
		
	Map questionAnswerMap=(Map)httpSession.getAttribute("questionAnswerMap");
	  
	if(null==questionAnswerMap || questionAnswerMap.isEmpty())
		possibleAnswer="null";
		
	List answerIdList = new ArrayList();
	List quesntionIdList = new ArrayList();
	
	  Iterator it = questionAnswerMap.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        QuestionAnswerGroupBO answerGroupBO =(QuestionAnswerGroupBO)pairs.getValue();
	        quesntionIdList.add(answerGroupBO.getQuestionId());
	        //questionNbrList.add(answerGroupBO.getQuestionDesc());   
	        
	       for (Iterator iter = answerGroupBO.getPossibleAnswerIdList().iterator(); iter.hasNext();) {
			String ansId = (String) iter.next();
			answerIdList.add(ansId);
			
			if(null!=possibleAnswer && !"".equals(possibleAnswer)){
				possibleAnswer=possibleAnswer+","+ansId;
				
			}
			else
				possibleAnswer=ansId;
	       }
	    }
        
	   Date updatedDate = ((AuditFactory) ObjectFactory
					.getFactory(ObjectFactory.AUDIT)).getAudit().getTime();
		List qualifierList = WPDStringUtil.getListFromTildaString(this
				.getQualifier(), 2, 1, 2);

		String adminsysid = WPDStringUtil.getStringFromTildaString(this
				.getAdminMethodNo(), 3, 3, 2);

		this.setAdminMethodsysID(adminsysid);

		
		List pvaList = WPDStringUtil.getListFromTildaString(this.getPva(), 2,
				2, 2);
		

		List datatypeList = WPDStringUtil.getListFromTildaString(this
				.getDatatype(), 2, 1, 2);

		adminMethodMappingEditRequest.setDatatypeList(datatypeList);
		adminMethodMappingEditRequest.setPvaList(pvaList);
		adminMethodMappingEditRequest.setQualifierList(qualifierList);
		adminMethodMappingEditRequest.setTerm(termValue);
		adminMethodMappingEditRequest.setComments(this.getComments());
		adminMethodMappingEditRequest.setAdminMethodNo(adminMethodNoArray[0]);
		adminMethodMappingEditRequest.setAdminMethodsysID(adminsysid);
		adminMethodMappingEditRequest.setProcessMethodDesc(this
				.getProcessMethodDesc());
		adminMethodMappingEditRequest.setQuesntionIdList(answerIdList);
		adminMethodMappingEditRequest.setQuestionNbrList(quesntionIdList);
		adminMethodMappingEditRequest.setPossibleAnswer(possibleAnswer);
		adminMethodMappingEditRequest.setCreatedDate((Date)getSession().getAttribute("createdDate"));
		adminMethodMappingEditRequest.setLastUpdatedDate(updatedDate);
		
	}

	/**
	 * @return Returns the adminMethodNoCriteria.
	 */
	public String getAdminMethodNoCriteria() {
		return adminMethodNoCriteria;
	}

	/**
	 * @param adminMethodNoCriteria
	 *            The adminMethodNoCriteria to set.
	 */
	public void setAdminMethodNoCriteria(String adminMethodNoCriteria) {
		this.adminMethodNoCriteria = adminMethodNoCriteria;
	}

	/**
	 * @return Returns the descriptionCriteria.
	 */
	public String getDescriptionCriteria() {
		return descriptionCriteria;
	}

	/**
	 * @param descriptionCriteria
	 *            The descriptionCriteria to set.
	 */
	public void setDescriptionCriteria(String descriptionCriteria) {
		this.descriptionCriteria = descriptionCriteria;
	}

	/**
	 * @return Returns the adminMethodsysID.
	 */
	public String getAdminMethodsysID() {
		return adminMethodsysID;
	}

	/**
	 * @param adminMethodsysID
	 *            The adminMethodsysID to set.
	 */
	public void setAdminMethodsysID(String adminMethodsysID) {
		this.adminMethodsysID = adminMethodsysID;
	}

	/**
	 * @return Returns the adminMethodSysId.
	 */
	public List getAdminMethodSysId() {
		return adminMethodSysId;
	}

	/**
	 * @param adminMethodSysId
	 *            The adminMethodSysId to set.
	 */
	public void setAdminMethodSysId(List adminMethodSysId) {
		this.adminMethodSysId = adminMethodSysId;
	}

	/**
	 * @return Returns the processMethodList.
	 */
	public List getProcessMethodList() {
		return processMethodList;
	}

	/**
	 * @param processMethodList
	 *            The processMethodList to set.
	 */
	public void setProcessMethodList(List processMethodList) {
		this.processMethodList = processMethodList;
	}

	/**
	 * @return Returns the aggregateTerm.
	 */
	public boolean isAggregateTerm() {
		return aggregateTerm;
	}

	/**
	 * @param aggregateTerm
	 *            The aggregateTerm to set.
	 */
	public void setAggregateTerm(boolean aggregateTerm) {
		this.aggregateTerm = aggregateTerm;
	}

	/**
	 * @return Returns the dataTypeCriteria.
	 */
	public String getDataTypeCriteria() {
		return dataTypeCriteria;
	}

	/**
	 * @param dataTypeCriteria
	 *            The dataTypeCriteria to set.
	 */
	public void setDataTypeCriteria(String dataTypeCriteria) {
		this.dataTypeCriteria = dataTypeCriteria;
	}

	/**
	 * @return Returns the questionAnswerString.
	 */
	public String getQuestionAnswerString() {
		return questionAnswerString;
	}

	/**
	 * @param questionAnswerString
	 *            The questionAnswerString to set.
	 */
	public void setQuestionAnswerString(String questionAnswerString) {
		this.questionAnswerString = questionAnswerString;
	}
	/**
	 * @return Returns the questionAnswerDisplayList.
	 */
	public List getQuestionAnswerDisplayList() {

		List questionAnswerDisplayList=new ArrayList();
		
		HttpSession httpSession= this.getSession();
		
		Map questionAnswerMap=(Map)httpSession.getAttribute("questionAnswerMap");
		if(null==questionAnswerMap || questionAnswerMap.isEmpty())
			return  new ArrayList();
				
		    Iterator it = questionAnswerMap.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();
		        QuestionAnswerGroupBO answerGroupBO =(QuestionAnswerGroupBO)pairs.getValue();
		      
		        String displayString= answerGroupBO.getQuestionDesc()+ "?  ";
		        
		        for (Iterator iter = answerGroupBO.getPossibleAnswerDescList().iterator(); iter
						.hasNext();) {
					String  element = (String ) iter.next();
				
					displayString=displayString+" "+element+",";					
				}
		        if(displayString.charAt(displayString.length()-1)==',')
			          displayString=   displayString.substring(0,displayString.length()-1);
		        
				
//		        if(null!= answerGroupBO.getPossibleAnswerDesc() && !"".equals(answerGroupBO.getPossibleAnswerDesc()))
//		        	displayString=displayString+" "+answerGroupBO.getPossibleAnswerDesc()+",";
//		        
//		        if(null!= answerGroupBO.getPossibleAnswerDesc2() && !"".equals(answerGroupBO.getPossibleAnswerDesc2()))
//		        	displayString=displayString+answerGroupBO.getPossibleAnswerDesc2()+",";
//		        
//		        if(null!= answerGroupBO.getPossibleAnswerDesc3() && !"".equals(answerGroupBO.getPossibleAnswerDesc3()))
//		        	displayString=displayString+answerGroupBO.getPossibleAnswerDesc3();
		        
		        questionAnswerDisplayList.add(displayString);
		        
		    }
		    if(null!= questionAnswerDisplayList && questionAnswerDisplayList.size()>0)
		    	Collections.sort(questionAnswerDisplayList);
		
//	//	List questionAnswerGroupBOList = this.getQuestionAnswerGroupBOList();
//		
//		String finalString="";
//		String[] selectedQuestionGroup=this.getQuestionAnswerString().split("~");
//		for (int i = 0; i < selectedQuestionGroup.length; i++) {
//			String[] questionDetails=selectedQuestionGroup[i].split("@");
//			String questionDesc=((String [])questionDetails[0].split("#"))[0];
//			String questionId=((String [])questionDetails[0].split("#"))[1];
//			
//			QuestionAnswerGroupBO answerGroupBO= new QuestionAnswerGroupBO();
//			answerGroupBO.setQuestionId(questionId);
//			String answerDesc="";
//			List answerList= new ArrayList();
//			for (int j = 1; j < questionDetails.length; j++) {
//				String[] answer=questionDetails[j].split("`");
//				String answerId=questionDetails[j].split("`")[0];
//				answerList.add(answerId);
//				if("".equals(answerDesc))
//				answerDesc= ((String [])questionDetails[j].split("`"))[1];
//				else 
//					answerDesc= answerDesc+","+((String [])questionDetails[j].split("`"))[1];
//			}
//			answerGroupBO.setPossibleAnswerIdList(answerList);
//			finalString=questionDesc+"?  "+answerDesc;
//			questionAnswerDisplayList.add(finalString);
//			questionAnswerGroupBOList.add(answerGroupBO);
//		}
//		 Set questionAnswerGroupBOSet= new HashSet(questionAnswerGroupBOList);
//		this.setQuestionAnswerGroupBOList(new ArrayList(questionAnswerGroupBOSet));
//		 HttpSession httpSession = getSession();
//	    httpSession.setAttribute("QuestionAnswerGroupBOList",questionAnswerGroupBOList);	
//	    
//	    Set questionAnswerSet= new HashSet(questionAnswerDisplayList);
//	    
//	    this.setQuestionAnswerDisplayList(new ArrayList(questionAnswerSet));
		return questionAnswerDisplayList;
	}
	/**
	 * @param questionAnswerDisplayList The questionAnswerDisplayList to set.
	 */
	public void setQuestionAnswerDisplayList(List questionAnswerDisplayList) {
		this.questionAnswerDisplayList = questionAnswerDisplayList;
	}
	public String pageReload(){
				
		setSelectedQuestionAnswerToMap(this.questionAnswerString);
		HttpSession httpSession= getSession();
		if(isPageInEditMode()) {
			String breadCrump=(String)	httpSession.getAttribute("adminMethodEditepageBC");
			setBreadCrumbText(breadCrump);
		} else {
			setBreadCrumbText("Administration >> Admin Method >> Mapping Create");
		}
		return "";
	}
	/**
	 * Check if request is from edit page.
	 * @return true if from edit page.
	 */
	private boolean isPageInEditMode() {
		return getSession().getAttribute(SESSION_IS_ADMIN_MTHD_MAPPING_EDIT) != null 
		&& String.valueOf(getSession().getAttribute(SESSION_IS_ADMIN_MTHD_MAPPING_EDIT)).equals("true");
	}

	private void setSelectedQuestionAnswerToMap(String questionAnswerString){
		
		HttpSession httpSession= this.getSession();
		
		Map questionAnswerMap=(Map)httpSession.getAttribute("questionAnswerMap");
		
		if(null==questionAnswerMap)
			questionAnswerMap=new HashMap();
		
		if(null!= this.getDeleteQuestionAnswerString() && !"".equals(this.getDeleteQuestionAnswerString())){
		String[] deleteString=this.deleteQuestionAnswerString.split("~");
		for (int i = 0; i < deleteString.length; i++) {
			questionAnswerMap.remove(deleteString[i]);
			
			}
		this.setDeleteQuestionAnswerString("");
		}
		deleteButtonFlag=true;
		List questionAnswerGroupBOList= new ArrayList();
		String questionId="";
		
		if(null!= questionAnswerString && !"".equals(questionAnswerString)){
		String[] selectedQuestionGroup=questionAnswerString.split("~");
		for (int i = 0; i < selectedQuestionGroup.length; i++) {
			String[] questionDetails=selectedQuestionGroup[i].split("@");
			String questionDesc=((String [])questionDetails[0].split("#"))[0];
			questionId=((String [])questionDetails[0].split("#"))[1];
			
			QuestionAnswerGroupBO answerGroupBO= new QuestionAnswerGroupBO();
			answerGroupBO.setQuestionId(questionId);
			answerGroupBO.setQuestionDesc(questionDetails[0].split("#")[0]);
			List answerIdList= new ArrayList();
			List answerDescList= new ArrayList();
			for (int j = 1; j < questionDetails.length; j++) {
				String[] answer=questionDetails[j].split("`");
				String answerId=questionDetails[j].split("`")[0];
				//answerGroupBO.setPossibleAnswerIdList().add(answerId);
				answerIdList.add(answerId);
				answerDescList.add(questionDetails[j].split("`")[1]);
//				if(j==1){
//					answerGroupBO.setPossibleAnswerDesc(questionDetails[j].split("`")[1]);
//					answerGroupBO.setPossibleAnswerId(questionDetails[j].split("`")[0]);
//				}
//				else if(j==2){
//					answerGroupBO.setPossibleAnswerDesc2(questionDetails[j].split("`")[1]);
//					answerGroupBO.setPossibleAnswerId2(questionDetails[j].split("`")[0]);
//				}
//				else {
//					answerGroupBO.setPossibleAnswerDesc3(questionDetails[j].split("`")[1]);
//					answerGroupBO.setPossibleAnswerId3(questionDetails[j].split("`")[0]);
//				}
			}
			answerGroupBO.setPossibleAnswerIdList(answerIdList);
			answerGroupBO.setPossibleAnswerDescList(answerDescList);
			questionAnswerGroupBOList.add(answerGroupBO);
			questionAnswerMap.put(questionId,answerGroupBO);
		}
		}
		this.setCreatedDate((Date)getSession().getAttribute("createdDate"));
		this.setLastUpdatedDate((Date)getSession().getAttribute("lastUpdatedDate"));
		
		httpSession.setAttribute("questionAnswerMap",questionAnswerMap);
		setQuestionAnswerModifiedStaus("true");
		
	}
	/**
	 * @return Returns the questionAnswerGroupBOList.
	 */
	public List getQuestionAnswerGroupBOList() {
		return questionAnswerGroupBOList;
	}
	/**
	 * @param questionAnswerGroupBOList The questionAnswerGroupBOList to set.
	 */
	public void setQuestionAnswerGroupBOList(List questionAnswerGroupBOList) {
		this.questionAnswerGroupBOList = questionAnswerGroupBOList;
	}
		/**
	 * @return Returns the createdDate.
	 */
	public Date getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate The createdDate to set.
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
	 * @param lastUpdatedDate The lastUpdatedDate to set.
	 */
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
		/**
	 * @return Returns the deleteFlag.
	 */
	public boolean isDeleteFlag() {
		return deleteFlag;
	}
	/**
	 * @param deleteFlag The deleteFlag to set.
	 */
	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	/**
	 * @return Returns the questionDeleteString.
	 */
	public String getQuestionDeleteString() {
		return questionDeleteString;
	}
	/**
	 * @param questionDeleteString The questionDeleteString to set.
	 */
	public void setQuestionDeleteString(String questionDeleteString) {
		this.questionDeleteString = questionDeleteString;
	}
	public String deleteQuestions(){
		
		String[] deleteString=this.questionDeleteString.split("~");
		Map questionAnswerMap= new HashMap();
		HttpSession httpSession= this.getSession();
		questionAnswerMap=(Map)httpSession.getAttribute("questionAnswerMap");
		List questionIdList= new ArrayList();
		for (int i = 0; i < deleteString.length; i++) {
			
		    Iterator it = questionAnswerMap.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();
		        QuestionAnswerGroupBO answerGroupBO =(QuestionAnswerGroupBO)pairs.getValue();
		        
		        String questionAnsString= answerGroupBO.getQuestionDesc()+"? ";
		        int answerCount=0;
		        for (Iterator iter = answerGroupBO.getPossibleAnswerDescList().iterator(); iter.hasNext();) {
					String  answerDesc = (String ) iter.next();
					if(answerCount!=0)
						questionAnsString=questionAnsString+", "+answerDesc;
					else
						questionAnsString=questionAnsString+answerDesc;
					answerCount++;
				}
		        
		        if(questionAnsString.equalsIgnoreCase(deleteString[i]))
		        	questionIdList.add(answerGroupBO.getQuestionId());
		    }
		}
		for (Iterator iter = questionIdList.iterator(); iter.hasNext();) {
			String element = (String) iter.next();
			questionAnswerMap.remove(element);
			
		}
		if(null==questionAnswerMap)
			this.setDeleteButtonFlag(false);
		else
			setDeleteButtonFlag(true);
			httpSession.setAttribute("questionAnswerMap",questionAnswerMap);
			if(isPageInEditMode()){
				String breadCrump=(String)	httpSession.getAttribute("adminMethodEditepageBC");
				this.setBreadCrumbText(breadCrump);
			}
			else {
				setBreadCrumbText("Administration >> Admin Method >> Mapping Create");
			}
			setQuestionAnswerModifiedStaus("true");
			this.setCreatedDate((Date)getSession().getAttribute("createdDate"));
            this.setLastUpdatedDate((Date)getSession().getAttribute("lastUpdatedDate"));

		return "";
	}
	/**
	 * @return Returns the deleteQuestionAnswerString.
	 */
	public String getDeleteQuestionAnswerString() {
		return deleteQuestionAnswerString;
	}
	/**
	 * @param deleteQuestionAnswerString The deleteQuestionAnswerString to set.
	 */
	public void setDeleteQuestionAnswerString(String deleteQuestionAnswerString) {
		this.deleteQuestionAnswerString = deleteQuestionAnswerString;
	}
	/**
	 * @return Returns the deleteButtonFlag.
	 */
	public boolean isDeleteButtonFlag() {
		
		HttpSession httpSession= this.getSession();
		Map questionAnswerMap=(Map)httpSession.getAttribute("questionAnswerMap");
		if(null==questionAnswerMap || questionAnswerMap.isEmpty())
			return false;
		else 
			return true;
	}
	/**
	 * @param deleteButtonFlag The deleteButtonFlag to set.
	 */
	public void setDeleteButtonFlag(boolean deleteButtonFlag) {
		this.deleteButtonFlag = deleteButtonFlag;
	}
		/**
	 * @return Returns the commentsReq.
	 */
	public boolean isCommentsReq() {
		return commentsReq;
	}
	/**
	 * @param commentsReq The commentsReq to set.
	 */
	public void setCommentsReq(boolean commentsReq) {
		this.commentsReq = commentsReq;
	}
	/**
	 * @return Returns the questionAnswerModifiedStaus.
	 */
	public String getQuestionAnswerModifiedStaus() {
		return questionAnswerModifiedStaus;
	}
	/**
	 * @param questionAnswerModifiedStaus The questionAnswerModifiedStaus to set.
	 */
	public void setQuestionAnswerModifiedStaus(
			String questionAnswerModifiedStaus) {
		this.questionAnswerModifiedStaus = questionAnswerModifiedStaus;
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
	 * @return Returns the loadAdminMethodMappingForEditPrint.
	 */
	public String getLoadAdminMethodMappingForEditPrint() {
		return retriveMethodMappingForEditPrint();
	}
	/**
	 * @param loadAdminMethodMappingForEditPrint The loadAdminMethodMappingForEditPrint to set.
	 */
	public void setLoadAdminMethodMappingForEditPrint(
			String loadAdminMethodMappingForEditPrint) {
		this.loadAdminMethodMappingForEditPrint = loadAdminMethodMappingForEditPrint;
	}
	/**
	 * @return Returns the printBreadCrumbText.
	 */
	public String getPrintBreadCrumbText() {
		return printBreadCrumbText="Administration >> Admin Method ("+this.getSession().getAttribute("adminMethodNo")+") >> Mapping Print";
	}
	/**
	 * @param printBreadCrumbText The printBreadCrumbText to set.
	 */
	public void setPrintBreadCrumbText(String printBreadCrumbText) {
		this.printBreadCrumbText= printBreadCrumbText;
	}
	/**
	 * @return Returns the descriptionReq.
	 */
	public boolean isDescriptionReq() {
		return descriptionReq;
	}
	/**
	 * @param descriptionReq The descriptionReq to set.
	 */
	public void setDescriptionReq(boolean descriptionReq) {
		this.descriptionReq = descriptionReq;
	}
	/**
	 * @return Returns the termReq.
	 */
	public boolean isTermReq() {
		return TermReq;
	}
	/**
	 * @param termReq The termReq to set.
	 */
	public void setTermReq(boolean termReq) {
		TermReq = termReq;
	}
}