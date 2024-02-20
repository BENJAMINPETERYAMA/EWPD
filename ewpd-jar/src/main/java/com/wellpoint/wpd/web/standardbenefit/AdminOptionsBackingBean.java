/*
 * AdminOptionsBackingBean.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */

package com.wellpoint.wpd.web.standardbenefit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.myfaces.component.html.ext.HtmlSelectBooleanCheckbox;


import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.standardbenefit.bo.AdministrationOptionBO;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitAdministrationAdminOptionImpl;
import com.wellpoint.wpd.common.standardbenefit.request.AdministrationOptionRequest;
import com.wellpoint.wpd.common.standardbenefit.request.DeleteAdministrationOptionRequest;
import com.wellpoint.wpd.common.standardbenefit.request.LocateAdministrationOptionRequest;
import com.wellpoint.wpd.common.standardbenefit.request.LookupAdminOptionRequest;
import com.wellpoint.wpd.common.standardbenefit.request.RetrieveAdministrationOptionRequest;
import com.wellpoint.wpd.common.standardbenefit.response.AdministrationOptionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.DeleteAdministrationOptionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.LocateAdministrationOptionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.LookupAdminOptionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.RetrieveAdministrationOptionResponse;
import com.wellpoint.wpd.common.standardbenefit.vo.AdministrationOptionVO;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.framework.util.SequenceUtil;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * Backing bean for admin options.
 * 
 * This bean will bind with the jap pages.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AdminOptionsBackingBean extends WPDBackingBean {

	private String benefitAdministration;

	//To display it in BreadCrumb
	private String adminOptionName;

	private String administrationLevel;

	private String benefitLevel = null;

	private String adminOption;

	private boolean requiredAdminLevel;

	private boolean requiredBenefitLevel;

	private boolean requiredAdminOption;

	//private HtmlPanelGrid displayPanel;

	private HtmlPanelGrid headerPanel;

	private List validationMessages = null;

	private List adminOptionsList = null;

	private List benefitAdministrationAdminOptionsList = null;

	private String reference;

	private int masterKeyUsedForUpdate = -1;

	private boolean saveAndAddFlag = false;

	private String STANDARD_BENEFIT_SESSION_KEY = WebConstants.STANDARD_BENEFIT_SESSION_KEY;

	/* From Benefit Level Pop Up */
	String hiddenLevelId;

	String hiddenReferenceId;

	String hiddenDescription;

	// List for storing search result list
	private List associatedAdministrationList;

	private int adminLevelOptionAssnSystemId;

	private int benefitAdminSystemId;

	private int administrationLevelId;

	private int benefitLevelId;

	private String benefitLevelDesc;

	private int adminOptionId;

	private int adminOptionIdForUpdate;

	private String adminOptionDesc;

	private String benefitAdminName;

	private int benefitLevelIdForUpdate;

	private List adminOptionsListForPopup;

	//  CR - To include the sequence number

	private HtmlPanelGrid displayPanel = new HtmlPanelGrid();

	private HtmlPanelGrid adminOptionHeaderViewPanel = new HtmlPanelGrid();

	private HtmlPanelGrid adminOptionPanel = new HtmlPanelGrid();

	private Map hiddenValMapSeq = new HashMap();

	private Map hiddenValALAssnId = new HashMap();

	private Map hiddenValBnftAdmnSysId = new HashMap();

	private Map hiddenValAdminLevelId = new HashMap();

	private Map hiddenValAdminOption = new HashMap();

	private Map hiddenBenefitLevelId = new HashMap();

	private Map hiddenValBnftLvlSysIdFrmMstr = new HashMap();

	List adminOptionsListFrmScreen = new ArrayList();
	//added for mulitiple adminoption delete
	private Map hiddenValDeleteBnftAdmnSysId = new HashMap();

	//private boolean editAdminOption = false;

	private boolean saveAdminOption = false;

	private boolean editButtonIsClicked = false;

	private String dummyField;
	
	private int benefitAdminSystemIdHidden;

	/**
	 * @return dummyField
	 * 
	 * Returns the dummyField.
	 */
	public String getDummyField() {
		
		LookupAdminOptionRequest lookupAdminOptionRequest = null;
		LookupAdminOptionResponse lookupAdminOptionResponse = null;
		lookupAdminOptionRequest = getLookupAdminOptionRequest();

		lookupAdminOptionResponse = (LookupAdminOptionResponse) this
				.executeService(lookupAdminOptionRequest);
		if (null != lookupAdminOptionResponse) {
			adminOptionsList = lookupAdminOptionResponse.getLookupAdminOption();
			if (adminOptionsList.size() == 0) {
				adminOptionsList = null;
			}
			this.setAdminOptionsListForPopup(adminOptionsList);
		}
		return dummyField;
	}

	/**
	 * @param dummyField
	 * 
	 * Sets the dummyField.
	 */
	public void setDummyField(String dummyField) {
		this.dummyField = dummyField;
	}

	// End CR

	/**
	 * Constructor
	 */
	public AdminOptionsBackingBean() {
		validationMessages = new ArrayList();
	}

	/**
	 * Method to save the newly created admin options.
	 * 
	 * @return String Returns the navigation path.
	 */
	public String saveAndAdd() {

		if (!validateRequiredFields()) {
			addAllMessagesToRequest(validationMessages);
			return "";
		}
		this.saveAndAddFlag = true;
		this.editButtonIsClicked = false;
		AdministrationOptionRequest administrationOptionRequest = getAdministrationOptionRequest();
		// Setting the value of the sequence number for the update adminOptions
		// after the retrieve is called.
		if (0 != this.getStandardBenefitSessionObject().getSequenceNumber()) {
			administrationOptionRequest.getAdministrationOptionVO()
					.setSequenceNumber(
							this.getStandardBenefitSessionObject()
									.getSequenceNumber());
		}
		this.setSaveAdminOption(true);
		administrationOptionRequest.setActionForUpdateSequence(false);
		AdministrationOptionResponse administrationOptionResponse = (AdministrationOptionResponse) executeService(administrationOptionRequest);
		if (administrationOptionResponse != null
				&& !administrationOptionResponse.isErrorMessageInList()) {
			if(null != administrationOptionResponse
							.getAdministrationOptionBO()){
				this.setAdminLevelOptionAssnSystemId(administrationOptionResponse
						.getAdministrationOptionBO()
						.getAdminLevelOptionAssnSystemId());
				getSession().setAttribute(WebConstants.SESSION_ADMIN_OPTN_ASSN,
						new Integer(this.getAdminLevelOptionAssnSystemId()));
			}
			//getStandardBenefitSessionObject().setBenefitAdminLvlAssociationId(administrationOptionResponse.getAdministrationOptionBO().getAdminLevelOptionAssnSystemId());
			resetAdministrationOptionObject();
		} else if (administrationOptionResponse != null
				&& administrationOptionResponse.isErrorMessageInList()) {
			this.setRequiredAdminOption(true);
		}
		 getRequest().setAttribute("RETAIN_Value", "");
		return "adminOptionSave";
	}

	/**
	 * Method to save the modified list of associated admin options.
	 * 
	 * @return String Returns the navigation path.
	 */
	public String saveAdminOptions() {
		if (!validateRequiredFields()) {
			addAllMessagesToRequest(validationMessages);
			return "";
		}
		AdministrationOptionRequest administrationOptionRequest = getAdministrationOptionRequest();
		// Setting the value of the sequence number for the update adminOptions
		// after the retrieve is called.
		if (0 != this.getStandardBenefitSessionObject().getSequenceNumber()) {
			administrationOptionRequest.getAdministrationOptionVO()
					.setSequenceNumber(
							this.getStandardBenefitSessionObject()
									.getSequenceNumber());
		}
		this.setSaveAdminOption(true);
		administrationOptionRequest.setActionForUpdateSequence(false);
		AdministrationOptionResponse administrationOptionResponse = (AdministrationOptionResponse) executeService(administrationOptionRequest);
		if (administrationOptionResponse != null
				&& !administrationOptionResponse.isErrorMessageInList()) {
			this.setAdminLevelOptionAssnSystemId(administrationOptionResponse
					.getAdministrationOptionBO()
					.getAdminLevelOptionAssnSystemId());
			this.setMasterKeyUsedForUpdate(this
					.getAdminLevelOptionAssnSystemId());
			//added
			this.setAdminOptionIdForUpdate(administrationOptionResponse
					.getAdministrationOptionBO().getAdminOptionIdFromMaster());
			//getStandardBenefitSessionObject().setBenefitAdminLvlAssociationId(this.getAdminLevelOptionAssnSystemId());
			getSession().setAttribute(WebConstants.SESSION_ADMIN_OPTN_ASSN,
					new Integer(this.getAdminLevelOptionAssnSystemId()));
		} else if (administrationOptionResponse != null
				&& administrationOptionResponse.isErrorMessageInList()) {
			this.setRequiredAdminOption(true);
		}

		return "adminOptionSave";
	}

	/**
	 * Method to retrieve the admin options.
	 * 
	 * @return String Returns the navigation path.
	 */
	public String retrieveAdminOptions() {
		RetrieveAdministrationOptionRequest request = (RetrieveAdministrationOptionRequest) this
				.getServiceRequest(ServiceManager.RETRIEVE_ADMINISTRATIVE_OPTION_REQUEST);
		//      request.setAdminLevelOptionAssnSystemId(this
		//      .getAdminLevelOptionAssnSystemId());
		int bnftAdmnOptLvlAssnSysId = 0;
		AdministrationOptionBO adminOptionBO1;
		this.setEditButtonIsClicked(true);
		//** Change
		if (null != this.getSession().getAttribute("selectedAdminOption")) {
			bnftAdmnOptLvlAssnSysId = Integer.parseInt((String) this
					.getSession().getAttribute("selectedAdminOption"));
			request.setAdminLevelOptionAssnSystemId(bnftAdmnOptLvlAssnSysId);
		}
		//** End ** Change
		//TODO Remove hard coding when session obj is ready - Start
		request.setMainObjIdentifier(getStandardBenefitSessionObject()
				.getStandardBenefitName());
		request.setMainObjKey(getStandardBenefitSessionObject()
				.getStandardBenefitKey());
		request.setMainObjVersion(getStandardBenefitSessionObject()
				.getStandardBenefitVersionNumber());
		request.setBusinessDomains(getStandardBenefitSessionObject()
				.getBusinessDomains());
		request.setStandardBenefitParentKey(getStandardBenefitSessionObject()
				.getStandardBenefitParentKey());
		request.setStandardBenefitStatus(getStandardBenefitSessionObject()
				.getStandardBenefitStatus());
		request.setItemCode("1");
		//TODO Remove hard coding when session obj is ready - End
		RetrieveAdministrationOptionResponse response = (RetrieveAdministrationOptionResponse) executeService(request);
		this.setMasterKeyUsedForUpdate(response.getAdministrationOptionBO()
				.getAdminLevelOptionAssnSystemId());
		if (response != null) {

			List adminOptionsBOList = this.getAssociatedAdministrationList();
			// To get the seqNumber value for update after retrieve
			if (null != adminOptionsBOList && adminOptionsBOList.size() != 0) {
				bnftAdmnOptLvlAssnSysId = Integer.parseInt((String) this
						.getSession().getAttribute("selectedAdminOption"));
				for (int i = 0; i < adminOptionsBOList.size(); i++) {
					adminOptionBO1 = (AdministrationOptionBO) adminOptionsBOList
							.get(i);
					if (bnftAdmnOptLvlAssnSysId == adminOptionBO1
							.getAdminLevelOptionAssnSystemId()) {
						//this.setUpdateAfterRetrieve(true);
						//this.setSeqNumForUpdateAfterRetrieve(adminOptionBO1.getSequenceNumber());
						this.getStandardBenefitSessionObject()
								.setSequenceNumber(
										adminOptionBO1.getSequenceNumber());
						//this.setEditAdminOption(true);
					}
				}
			}
			reorder(adminOptionsBOList);
		}
		createScreenCompatibleValues(response);
		return "";
	}

	/**
	 * Method to delete the admin options.
	 * 
	 * @return String Returns the navigation path.
	 */
	public String deleteAdminOptions() {
		List messageList = new ArrayList(1);
		List adminList = new ArrayList();
		Map map=this.getHiddenValDeleteBnftAdmnSysId();
		Iterator it = map.entrySet().iterator();
		 while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        if((Boolean)(pairs.getValue())==Boolean.TRUE){
	        adminList.add(pairs.getKey()) ;
	        }
	    }	
		DeleteAdministrationOptionRequest request = (DeleteAdministrationOptionRequest) this
				.getServiceRequest(ServiceManager.DELETE_ADMINISTRATIVE_OPTION_REQUEST);
			int bnftAdmnOptLvlAssnSysId = 0;
		//      ** Change
		if (null != this.getSession().getAttribute("selectedAdminOption")) {
			bnftAdmnOptLvlAssnSysId = Integer.parseInt((String) this
					.getSession().getAttribute("selectedAdminOption"));
			request.setAdminLevelOptionAssnSystemId(bnftAdmnOptLvlAssnSysId);
		}
		// ** End
		//TODO Remove hard coding when session obj is ready - Start
		request.setMainObjIdentifier(getStandardBenefitSessionObject()
				.getStandardBenefitName());
		request.setMainObjKey(getStandardBenefitSessionObject()
				.getStandardBenefitKey());
		request.setMainObjVersion(getStandardBenefitSessionObject()
				.getStandardBenefitVersionNumber());
		request.setBusinessDomains(getStandardBenefitSessionObject()
				.getBusinessDomains());
		request.setStandardBenefitParentKey(getStandardBenefitSessionObject()
				.getStandardBenefitParentKey());
		request.setStandardBenefitStatus(getStandardBenefitSessionObject()
				.getStandardBenefitStatus());
		//TODO Remove hard coding when session obj is ready - End
		/*
		 * modified for multile delete
		 */
		if(null!=adminList && adminList.size()>0){
		request.setAdminDeleteList(adminList);
		DeleteAdministrationOptionResponse response = (DeleteAdministrationOptionResponse) executeService(request);
		
		if (null != response) {
			List adminOptionBOList = this.getAssociatedAdministrationList();
			List adminOptionVOList = copyBOtoVO(adminOptionBOList);
			this.saveAdminOption(this.reorder(adminOptionVOList));
			messageList.add(new InformationalMessage(
					"product.adminoption.delete"));
			addAllMessagesToRequest(messageList);
		}
		}
		
		resetAdministrationOptionObject();
		getRequest().setAttribute("RETAIN_Value", "");
		return "adminOptionDelete";
	}

	/**
	 * @param adminOptionBOList
	 * @return
	 */
	private List copyBOtoVO(List adminOptionBOList) {
		List adminOptionVOsList = new ArrayList();
		AdministrationOptionVO valueObject = null;
		if (null != adminOptionBOList && adminOptionBOList.size() != 0) {
			for (int i = 0; i < adminOptionBOList.size(); i++) {
				AdministrationOptionBO businessObject = (AdministrationOptionBO) adminOptionBOList
						.get(i);
				valueObject = new AdministrationOptionVO();
				valueObject.setAdminLevelOptionAssnSystemId(businessObject
						.getAdminLevelOptionAssnSystemId());
				valueObject.setAdminLevelSystemId(businessObject
						.getAdminLevelSystemId());
				valueObject.setAdminOptionSystemId(businessObject
						.getAdminOptionSystemId());
				valueObject.setBenefitAdminSystemId(businessObject
						.getBenefitAdminSystemId());
				valueObject.setBenefitLevelSystemId(businessObject
						.getBenefitLevelSysIdFromMaster());
				valueObject.setSequenceNumber(businessObject
						.getSequenceNumber());
				valueObject
						.setStandardBenefitId(getStandardBenefitSessionObject()
								.getStandardBenefitKey());
				valueObject
						.setStandardBenefitParentId(getStandardBenefitSessionObject()
								.getStandardBenefitParentKey());
				valueObject
						.setStandardBenefitName(getStandardBenefitSessionObject()
								.getStandardBenefitName());
				valueObject
						.setStandardBenefitVersion(getStandardBenefitSessionObject()
								.getStandardBenefitVersionNumber());
				valueObject
						.setSbBusinessDomains(getStandardBenefitSessionObject()
								.getBusinessDomains());
				adminOptionVOsList.add(valueObject);
			}
		}
		return adminOptionVOsList;
	}

	/**
	 * Method to create Admin Option Request
	 * 
	 * @return AdministrationOptionRequest administrationOptionRequest
	 */
	private AdministrationOptionRequest getAdministrationOptionRequest() {
		List listAdminOption;
		List listBenefitLevel;
		int adminOption;
		int benefitLevel;

		AdministrationOptionRequest administrationOptionRequest = (AdministrationOptionRequest) this
				.getServiceRequest(ServiceManager.SAVE_ADMIN_OPTION_REQUEST);
		AdministrationOptionVO administrationOptionVO = new AdministrationOptionVO();

		if (this.getBenefitLevel() != null
				&& !"".equals(this.getBenefitLevel())) {
			listBenefitLevel = WPDStringUtil.getListFromTildaString(this
					.getBenefitLevel(), 2, 1, 1);
			benefitLevel = Integer.parseInt(listBenefitLevel.get(0).toString());
			administrationOptionVO.setBenefitLevelSystemId(benefitLevel);
		}

		listAdminOption = WPDStringUtil.getListFromTildaString(this
				.getAdminOption(), 2, 1, 1);
					//administrationOptionRequest.setAdminList(listAdminOption);
		administrationOptionVO.setAdminOptionsList(listAdminOption);
		adminOption = Integer.parseInt(listAdminOption.get(0).toString());
		administrationOptionVO.setBenefitAdminSystemId(Integer
				.parseInt(getSession().getAttribute(
						WebConstants.SESSION_BNFT_ADMIN_ID).toString()));
		administrationOptionVO.setAdminOptionSystemId(adminOption);
		administrationOptionVO.setIsOpen("F");
		administrationOptionVO.setAdminLevelSystemId(Integer.parseInt(this
				.getAdministrationLevel()));
		administrationOptionVO
				.setBusinessDomains(getStandardBenefitSessionObject()
						.getBusinessDomains());
		administrationOptionVO
				.setStandardBenefitParentKey(getStandardBenefitSessionObject()
						.getStandardBenefitParentKey());
		administrationOptionVO
				.setStandardBenefitStatus(getStandardBenefitSessionObject()
						.getStandardBenefitStatus());
		/** Getting the values from session */
		administrationOptionVO
				.setBenefitMasterSystemId(getStandardBenefitSessionObject()
						.getStandardBenefitKey());
		administrationOptionVO
				.setMasterVersion(getStandardBenefitSessionObject()
						.getStandardBenefitVersionNumber());
		administrationOptionVO.setBenefitDefinitionkey(Integer
				.parseInt(getSession().getAttribute("SESSION_BNFT_DEFN_ID").toString()));
		administrationOptionVO
				.setBenefitIdentifier(getStandardBenefitSessionObject()
						.getStandardBenefitName());
		if (this.isSaveAndAddFlag()) {
			if (this.getMasterKeyUsedForUpdate() == -1) {
				administrationOptionVO.setMasterKeyUsedForUpdate(-1);
			} else {
				administrationOptionVO
						.setMasterKeyUsedForUpdate(this.masterKeyUsedForUpdate);
				administrationOptionVO
						.setAdminLevelOptionAssnSystemId(this.masterKeyUsedForUpdate);
				administrationOptionVO.setAdminOptionSystemIdForUpdate(this
						.getAdminOptionIdForUpdate());
			}
		} else {
			administrationOptionVO
					.setMasterKeyUsedForUpdate(this.masterKeyUsedForUpdate);
			administrationOptionVO
					.setAdminLevelOptionAssnSystemId(this.masterKeyUsedForUpdate);
			administrationOptionVO.setAdminOptionSystemIdForUpdate(this
					.getAdminOptionIdForUpdate());
		}
		administrationOptionRequest
				.setAdministrationOptionVO(administrationOptionVO);
		return administrationOptionRequest;
	}

	/**
	 * Validate all the required mandatary information from the field
	 * 
	 * @return true if all the required fields are available.
	 */

	private boolean validateRequiredFields() {

		boolean isAvailable = true;
		validationMessages = new ArrayList(1);

		if (null == this.administrationLevel
				|| "".equals(this.administrationLevel.trim())) {
			this.requiredAdminLevel = true;
			isAvailable = false;

		}
		if ("1".equals(this.administrationLevel.trim())
				&& (null == this.benefitLevel || "".equals(this.benefitLevel
						.trim()))) {
			this.requiredBenefitLevel = true;
			isAvailable = false;
		}
		if (null == this.adminOption || "".equals(this.adminOption.trim())) {
			this.requiredAdminOption = true;
			isAvailable = false;
		}
		if (!isAvailable) {
			validationMessages.add(new ErrorMessage(
					WebConstants.MANDATORY_FIELDS_REQUIRED));
		}
		return isAvailable;
	}

	/**
	 * Method to reset all the values.
	 * 
	 * @return
	 */
	private void resetAdministrationOptionObject() {
		this.setBenefitAdministration("");
		this.setAdministrationLevel("");
		this.setBenefitLevel("");
		this.setAdminOption("");
		this.setMasterKeyUsedForUpdate(-1);
	}

	/**
	 * Method to create Screen Combatible values.
	 * 
	 * @param response
	 * @return
	 */
	public void createScreenCompatibleValues(
			RetrieveAdministrationOptionResponse response) {
		int benefitLevelId = response.getAdministrationOptionBO()
				.getBenefitLevelSysIdFromMaster();
		String benefitLevelDesc = response.getAdministrationOptionBO()
				.getBenefitLevelDesc();
		int adminOptionId = response.getAdministrationOptionBO()
				.getAdminOptionIdFromMaster();
		String adminOptionDesc = response.getAdministrationOptionBO()
				.getAdminOptionDesc();
		this.setAdminLevelOptionAssnSystemId(response
				.getAdministrationOptionBO().getAdminLevelOptionAssnSystemId());
		this.setBenefitAdminSystemId(response.getAdministrationOptionBO()
				.getBenefitAdminSystemId());
		this.setAdministrationLevelId(response.getAdministrationOptionBO()
				.getAdminLevelSystemId());
		this.setBenefitLevel(benefitLevelId + "~" + benefitLevelDesc);
		this.setAdminOption(adminOptionId + "~" + adminOptionDesc);
		this.setMasterKeyUsedForUpdate(response.getAdministrationOptionBO()
				.getAdminLevelOptionAssnSystemId());
		this.setBenefitLevelIdForUpdate(benefitLevelId);
		this.setAdministrationLevel(new Integer(response
				.getAdministrationOptionBO().getAdminLevelSystemId())
				.toString());
		this.setAdminOptionIdForUpdate(adminOptionId);
	}

	/**
	 * Method for Session Object
	 * 
	 * @return standardBenefitSessionObject
	 */
	protected StandardBenefitSessionObject getStandardBenefitSessionObject() {
		StandardBenefitSessionObject standardBenefitSessionObject = (StandardBenefitSessionObject) getSession()
				.getAttribute(STANDARD_BENEFIT_SESSION_KEY);

		if (standardBenefitSessionObject == null) {
			standardBenefitSessionObject = new StandardBenefitSessionObject();
			getSession().setAttribute(STANDARD_BENEFIT_SESSION_KEY,
					standardBenefitSessionObject);
		}
		return standardBenefitSessionObject;
	}

	/**
	 * Returns the adminLevelOptionAssnSystemId
	 * 
	 * @return int adminLevelOptionAssnSystemId.
	 */
	public int getAdminLevelOptionAssnSystemId() {
		return adminLevelOptionAssnSystemId;
	}

	/**
	 * Sets the adminLevelOptionAssnSystemId
	 * 
	 * @param adminLevelOptionAssnSystemId.
	 */
	public void setAdminLevelOptionAssnSystemId(int adminLevelOptionAssnSystemId) {
		this.adminLevelOptionAssnSystemId = adminLevelOptionAssnSystemId;
	}

	/**
	 * Returns the associatedAdministrationList
	 * 
	 * @return List associatedAdministrationList.
	 */
	public List getAssociatedAdministrationList() {
		LocateAdministrationOptionRequest locateAdministrationOptionRequest = (LocateAdministrationOptionRequest) this
				.getServiceRequest(ServiceManager.LOCATE_ADMIN_OPTION_REQUEST);
		// TODO Remove hardcoding when actual objects are ready.
		this.setBenefitAdminName(getSession().getAttribute(
				WebConstants.SESSION_BNFT_ADMIN_DESC).toString());
		locateAdministrationOptionRequest.setBenefitAdminSysId(Integer
				.parseInt(getSession().getAttribute(
						WebConstants.SESSION_BNFT_ADMIN_ID).toString()));
		locateAdministrationOptionRequest.setBenefitDefinitionKey(Integer
				.parseInt(getSession().getAttribute("SESSION_BNFT_DEFN_ID").toString()));
		locateAdministrationOptionRequest.setMaxSearchResultSize(WebConstants.MAX_SEARCH_RESULT_SIZE);
		LocateAdministrationOptionResponse response = (LocateAdministrationOptionResponse) executeService(locateAdministrationOptionRequest);
		//change
		if (null != this.getStandardBenefitSessionObject()
				.getStandardBenefitMode()
				&& (WebConstants.BENEFIT_VIEW).equals(this
						.getStandardBenefitSessionObject()
						.getStandardBenefitMode())) {
			this.setBreadCrumbText(WebConstants.BENEFIT_BREADCRUMB
					+ " ("
					+ this.getStandardBenefitSessionObject()
							.getStandardBenefitName() + ") >> View");
		} else
			this.setBreadCrumbText(WebConstants.BENEFIT_BREADCRUMB
					+ " ("
					+ this.getStandardBenefitSessionObject()
							.getStandardBenefitName() + ") >> Edit");
		//end
		List list = null;
		if(null != response
				.getAssociatedBenefitAdministrationOptionList() && response.getAssociatedBenefitAdministrationOptionList().size() > 0){
			registerSequence(response
					.getAssociatedBenefitAdministrationOptionList());
			SequenceUtil sequenceUtil = new SequenceUtil();
			list = sequenceUtil.reOrderObjects(response
					.getAssociatedBenefitAdministrationOptionList());
		    
		}
		return list;
	}

	/**
	 * Sets the associatedAdministrationList
	 * 
	 * @param associatedAdministrationList.
	 */
	public void setAssociatedAdministrationList(
			List associatedAdministrationList) {
		this.associatedAdministrationList = associatedAdministrationList;
	}

	/**
	 * @return Returns the reference.
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * @param reference
	 *            The reference to set.
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * Returns the benefitAdministration.
	 * 
	 * @return benefitAdministration
	 */
	public String getBenefitAdministration() {
		return benefitAdministration;
	}

	/**
	 * Sets the benefitAdministration
	 * 
	 * @param benefitAdministration
	 */
	public void setBenefitAdministration(String benefitAdministration) {
		this.benefitAdministration = benefitAdministration;
	}

	/**
	 * Returns the benefitLevel.
	 * 
	 * @return benefitLevel.
	 */
	public String getBenefitLevel() {
		return benefitLevel;
	}

	/**
	 * Sets the benefitLevel
	 * 
	 * @param benefitLevel
	 */
	public void setBenefitLevel(String benefitLevel) {
		this.benefitLevel = benefitLevel;
	}

	/**
	 * Returns the requiredAdminLevel.
	 * 
	 * @return requiredAdminLevel
	 */
	public boolean getRequiredAdminLevel() {
		return requiredAdminLevel;
	}

	/**
	 * Sets the requiredAdminLevel
	 * 
	 * @param requiredAdminLevel
	 */
	public void setRequiredAdminLevel(boolean requiredAdminLevel) {
		this.requiredAdminLevel = requiredAdminLevel;
	}

	/**
	 * Returns the requiredAdminOption.
	 * 
	 * @return requiredAdminOption
	 */
	public boolean getRequiredAdminOption() {
		return requiredAdminOption;
	}

	/**
	 * Sets the requiredAdminOption
	 * 
	 * @param requiredAdminOption
	 */
	public void setRequiredAdminOption(boolean requiredAdminOption) {
		this.requiredAdminOption = requiredAdminOption;
	}

	/**
	 * Returns the requiredBenefitLevel.
	 * 
	 * @return requiredBenefitLevel
	 */
	public boolean getRequiredBenefitLevel() {
		return requiredBenefitLevel;
	}

	/**
	 * Sets the requiredBenefitLevel
	 * 
	 * @param requiredBenefitLevel
	 */
	public void setRequiredBenefitLevel(boolean requiredBenefitLevel) {
		this.requiredBenefitLevel = requiredBenefitLevel;
	}

	/**
	 * Returns the administrationLevel.
	 * 
	 * @return administrationLevel
	 */
	public String getAdministrationLevel() {
		return administrationLevel;
	}

	/**
	 * Sets the administrationLevel
	 * 
	 * @param administrationLevel
	 */
	public void setAdministrationLevel(String administrationLevel) {
		this.administrationLevel = administrationLevel;
	}

	/**
	 * Returns the adminOption.
	 * 
	 * @return adminOption
	 */
	public String getAdminOption() {
		return adminOption;
	}

	/**
	 * Sets the adminOption
	 * 
	 * @param adminOption
	 */
	public void setAdminOption(String adminOption) {
		this.adminOption = adminOption;
	}

	/**
	 * Returns the validationMessages.
	 * 
	 * @return validationMessages
	 */
	public List getValidationMessages() {
		return validationMessages;
	}

	/**
	 * Sets the validationMessages
	 * 
	 * @param validationMessages
	 */
	public void setValidationMessages(List validationMessages) {
		this.validationMessages = validationMessages;
	}

	/**
	 * Returns the adminOptionsList
	 * 
	 * @return adminOptionsList.
	 */
	public List getAdminOptionsList() {

		return adminOptionsList;
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
	 * @return Returns the hiddenLevelId.
	 */
	public String getHiddenLevelId() {
		return hiddenLevelId;
	}

	/**
	 * @param hiddenLevelId
	 *            The hiddenLevelId to set.
	 */
	public void setHiddenLevelId(String hiddenLevelId) {
		this.hiddenLevelId = hiddenLevelId;
	}

	/**
	 * @return Returns the hiddenReferenceId.
	 */
	public String getHiddenReferenceId() {
		return hiddenReferenceId;
	}

	/**
	 * @param hiddenReferenceId
	 *            The hiddenReferenceId to set.
	 */
	public void setHiddenReferenceId(String hiddenReferenceId) {
		this.hiddenReferenceId = hiddenReferenceId;
	}

	/**
	 * Sets the adminOptionsList
	 * 
	 * @param adminOptionsList.
	 */
	public void setAdminOptionsList(List adminOptionsList) {
		this.adminOptionsList = adminOptionsList;
	}

	/**
	 * Returns the benefitAdministrationAdminOptionsList
	 * 
	 * @return List benefitAdministrationAdminOptionsList.
	 */
	public List getBenefitAdministrationAdminOptionsList() {
		BenefitAdministrationAdminOptionImpl benefitAdministrationAdminOption = null;
		List adminOptionsList = new ArrayList();

		for (int i = 0; i < 5; i++) {
			benefitAdministrationAdminOption = new BenefitAdministrationAdminOptionImpl();
			benefitAdministrationAdminOption.setAdminName("Admin Name " + i);
			benefitAdministrationAdminOption.setAdminLevel("Benefit Level");
			benefitAdministrationAdminOption
					.setBenefitLevel("Copay Individual");
			benefitAdministrationAdminOption.setReference("XREF_CLM");

			adminOptionsList.add(benefitAdministrationAdminOption);

		}
		return adminOptionsList;
	}

	/**
	 * Sets the benefitAdministrationAdminOptionsList
	 * 
	 * @param benefitAdministrationAdminOptionsList.
	 */
	public void setBenefitAdministrationAdminOptionsList(
			List benefitAdministrationAdminOptionsList) {
		this.benefitAdministrationAdminOptionsList = benefitAdministrationAdminOptionsList;
	}

	/**
	 * Returns the benefitAdminSystemId
	 * 
	 * @return int benefitAdminSystemId.
	 */
	public int getBenefitAdminSystemId() {
		return benefitAdminSystemId;
	}

	/**
	 * Sets the benefitAdminSystemId
	 * 
	 * @param benefitAdminSystemId.
	 */
	public void setBenefitAdminSystemId(int benefitAdminSystemId) {
		this.benefitAdminSystemId = benefitAdminSystemId;
	}

	/**
	 * Returns the administrationLevelId
	 * 
	 * @return int administrationLevelId.
	 */
	public int getAdministrationLevelId() {
		return administrationLevelId;
	}

	/**
	 * Sets the administrationLevelId
	 * 
	 * @param administrationLevelId.
	 */
	public void setAdministrationLevelId(int administrationLevelId) {
		this.administrationLevelId = administrationLevelId;
	}

	/**
	 * Returns the benefitLevelId
	 * 
	 * @return int benefitLevelId.
	 */
	public int getBenefitLevelId() {
		return benefitLevelId;
	}

	/**
	 * Sets the benefitLevelId
	 * 
	 * @param benefitLevelId.
	 */
	public void setBenefitLevelId(int benefitLevelId) {
		this.benefitLevelId = benefitLevelId;
	}

	/**
	 * Returns the benefitLevelDesc
	 * 
	 * @return String benefitLevelDesc.
	 */
	public String getBenefitLevelDesc() {
		return benefitLevelDesc;
	}

	/**
	 * Sets the benefitLevelDesc
	 * 
	 * @param benefitLevelDesc.
	 */
	public void setBenefitLevelDesc(String benefitLevelDesc) {
		this.benefitLevelDesc = benefitLevelDesc;
	}

	/**
	 * Returns the adminOptionDesc
	 * 
	 * @return String adminOptionDesc.
	 */
	public String getAdminOptionDesc() {
		return adminOptionDesc;
	}

	/**
	 * Sets the adminOptionDesc
	 * 
	 * @param adminOptionDesc.
	 */
	public void setAdminOptionDesc(String adminOptionDesc) {
		this.adminOptionDesc = adminOptionDesc;
	}

	/**
	 * Returns the adminOptionId
	 * 
	 * @return int adminOptionId.
	 */
	public int getAdminOptionId() {
		return adminOptionId;
	}

	/**
	 * Sets the adminOptionId
	 * 
	 * @param adminOptionId.
	 */
	public void setAdminOptionId(int adminOptionId) {
		this.adminOptionId = adminOptionId;
	}

	/**
	 * @return Returns the masterKeyUsedForUpdate.
	 */
	public int getMasterKeyUsedForUpdate() {
		return masterKeyUsedForUpdate;
	}

	/**
	 * @param masterKeyUsedForUpdate
	 *            The masterKeyUsedForUpdate to set.
	 */
	public void setMasterKeyUsedForUpdate(int masterKeyUsedForUpdate) {
		this.masterKeyUsedForUpdate = masterKeyUsedForUpdate;
	}

	/**
	 * @return Returns the setSaveAndAddFlag.
	 */
	public boolean isSaveAndAddFlag() {
		return saveAndAddFlag;
	}

	/**
	 * @param saveAndAddFlag
	 *            The setSaveAndAddFlag to set.
	 */
	public void setSaveAndAddFlag(boolean saveAndAddFlag) {
		this.saveAndAddFlag = saveAndAddFlag;
	}

	/**
	 * Returns the benefitAdminName
	 * 
	 * @return String benefitAdminName.
	 */
	public String getBenefitAdminName() {
		return getSession().getAttribute(WebConstants.SESSION_BNFT_ADMIN_DESC)
				.toString();
	}

	/**
	 * Sets the benefitAdminName
	 * 
	 * @param benefitAdminName.
	 */
	public void setBenefitAdminName(String benefitAdminName) {
		this.benefitAdminName = benefitAdminName;
	}

	/**
	 * Returns the benefitLevelIdForUpdate
	 * 
	 * @return int benefitLevelIdForUpdate.
	 */
	public int getBenefitLevelIdForUpdate() {
		return benefitLevelIdForUpdate;
	}

	/**
	 * Sets the benefitLevelIdForUpdate
	 * 
	 * @param benefitLevelIdForUpdate.
	 */
	public void setBenefitLevelIdForUpdate(int benefitLevelIdForUpdate) {
		this.benefitLevelIdForUpdate = benefitLevelIdForUpdate;
	}

	/**
	 * @return Returns the adminOptionName.
	 */
	public String getAdminOptionName() {
		String adminName = (String) this.getStandardBenefitSessionObject()
				.getStandardBenefitName();
		return adminName;
	}

	/**
	 * @param adminOptionName
	 *            The adminOptionName to set.
	 */
	public void setAdminOptionName(String adminOptionName) {
		this.adminOptionName = adminOptionName;
	}

	/**
	 * @return Returns the adminOptionsListForPopup.
	 */
	public List getAdminOptionsListForPopup() {
		return adminOptionsListForPopup;
	}

	/**
	 * @param adminOptionsListForPopup
	 *            The adminOptionsListForPopup to set.
	 */
	public void setAdminOptionsListForPopup(List adminOptionsListForPopup) {
		this.adminOptionsListForPopup = adminOptionsListForPopup;
	}

	/**
	 * @return Returns the LookupAdminOptionRequest.
	 */
	public LookupAdminOptionRequest getLookupAdminOptionRequest() {
		AdministrationOptionVO administrationOptionVO = new AdministrationOptionVO();
		String benefitLevel = (String) (getRequest()
				.getParameter("benefitLevel"));
		if (benefitLevel != null && !"".equals(benefitLevel)) {
			List listBenefitLevel = WPDStringUtil.getListFromTildaString(
					benefitLevel, 2, 1, 1);
			int benefitLevelInt = Integer.parseInt(listBenefitLevel.get(0)
					.toString());
			administrationOptionVO.setBenefitLevelSystemId(benefitLevelInt);
		}
		LookupAdminOptionRequest lookupAdminOptionRequest = (LookupAdminOptionRequest) this
				.getServiceRequest(ServiceManager.LOOK_UP_ADMIN_OPTION_LIST);
		// Setting the values of benefitAdminsystemId to rule out the items
		// listed in teh associated admin table.
		administrationOptionVO.setBenefitAdminSystemId((Integer
				.parseInt(getSession().getAttribute(
						WebConstants.SESSION_BNFT_ADMIN_ID).toString())));
		lookupAdminOptionRequest
				.setAdministrationOptionVO(administrationOptionVO);
		lookupAdminOptionRequest.setMaxSearchResultSize(99999);
		return lookupAdminOptionRequest;
	}

	/**
	 * @return Returns the adminOptionIdForUpdate.
	 */
	public int getAdminOptionIdForUpdate() {
		return adminOptionIdForUpdate;
	}

	/**
	 * @param adminOptionIdForUpdate
	 *            The adminOptionIdForUpdate to set.
	 */
	public void setAdminOptionIdForUpdate(int adminOptionIdForUpdate) {
		this.adminOptionIdForUpdate = adminOptionIdForUpdate;
	}

	/**
	 * For displying admin option tab.
	 * 
	 * @return String
	 */
	public String loadForBenefit() {
		Logger.logInfo("Entering the method for loading admin option");
		return "success";
	}

	public String loadForBenefitView() {
		Logger.logInfo("Entering the method for loading admin option");
		this.setBreadCrumbText(WebConstants.BENEFIT_BREADCRUMB
				+ " ("
				+ this.getStandardBenefitSessionObject()
						.getStandardBenefitName() + ") >> View");

		return "adminOptionView";

	}

	/**
	 * @return Returns the adminOptionHeaderViewPanel.
	 */
	public HtmlPanelGrid getAdminOptionHeaderViewPanel() {
		HtmlPanelGrid viewPanel = new HtmlPanelGrid();
		viewPanel.setWidth("100%");
		viewPanel.setColumns(6);
		viewPanel.setBorder(0);
		viewPanel.setBgcolor("#cccccc");
		viewPanel.setCellpadding("3");
		viewPanel.setCellspacing("1");
		//HtmlOutputText updateOutputText = new HtmlOutputText();
		HtmlCommandButton updateButton = new HtmlCommandButton();
		updateButton.setId("Update");
		updateButton.setStyleClass("wpdbutton");
		updateButton.setValue("Update");
		updateButton.setOnmousedown("javascript:savePageAction(this.id);");
		
		MethodBinding updateMetBind = FacesContext.getCurrentInstance()
				.getApplication().createMethodBinding(
						"#{adminOptionsBackingBean.updateAdminOption}",
						new Class[] {});
		updateButton.setAction(updateMetBind);

		HtmlOutputText nameOutputText = new HtmlOutputText();
		HtmlOutputText editHeader = new HtmlOutputText();
		HtmlOutputText adminLevelOutputText = new HtmlOutputText();
		HtmlOutputText benefitLevelOutputText = new HtmlOutputText();
		HtmlOutputText testText = new HtmlOutputText();
//		added for mulitiple adminoption delete
//		HtmlCommandButton deleteButton = new HtmlCommandButton();
		HtmlOutputText deleteHeader = new HtmlOutputText();
		nameOutputText.setValue("Name");
		nameOutputText.setId("Name");
		nameOutputText.setStyleClass("dataTableHeader");

		adminLevelOutputText.setValue("Admin Level");
		adminLevelOutputText.setId("AdminLevel");
		adminLevelOutputText.setStyleClass("dataTableHeader");

		benefitLevelOutputText.setValue("Benefit Level");
		benefitLevelOutputText.setId("BenefitLevel");
		benefitLevelOutputText.setStyleClass("dataTableHeader");

		editHeader.setValue(" ");
		editHeader.setId("Edit");
		editHeader.setStyleClass("dataTableHeader");
//		added for mulitiple adminoption delete
		deleteHeader.setValue("Delete");
		deleteHeader.setStyleClass("dataTableHeader");
//		deleteButton.setId("DeleteAll" );
//		deleteButton.setStyle("border:0;");
//		deleteButton.setImage("../../images/delete.gif");
//		deleteButton.setTitle("Delete");
//		deleteButton.setOnclick("return deleteAdminOptionAttachedToBenefit()");
		MethodBinding deleteMetBind = FacesContext
		.getCurrentInstance()
		.getApplication()
		.createMethodBinding(
				"#{adminOptionsBackingBean.deleteAdminOptions}",
				new Class[] {});
//		deleteButton.setAction(deleteMetBind);
		viewPanel.getChildren().add(updateButton);
		viewPanel.getChildren().add(nameOutputText);
		viewPanel.getChildren().add(adminLevelOutputText);
		viewPanel.getChildren().add(benefitLevelOutputText);
		viewPanel.getChildren().add(editHeader);
//		added for mulitiple adminoption delete
		viewPanel.getChildren().add(deleteHeader);
		return viewPanel;
	}

	// Update AdminOption

	public String updateAdminOption() {
		List benefitAdminVOList = new ArrayList();
		List messageList = new ArrayList(1);
		
		Set valueKeys = hiddenValMapSeq.keySet();
		boolean isSequenceChanged = false;
		if (!valueKeys.isEmpty()) {
			boolean sequenceValid = true;
			for (Iterator iter = valueKeys.iterator(); iter.hasNext()
					&& sequenceValid;) {
				Long rowNum = (Long) iter.next();
				String seqNum = (String) hiddenValMapSeq.get(rowNum);
				int seqNumInt = 0;
				//String componentName = (String) adminDescMap.get(rowNum);
				// Validating whether Sequence is blank.
				if (seqNum == null || seqNum.trim().equals("")) {
					messageList.add(new ErrorMessage("product.seq.empty"));
					sequenceValid = false;
					break;
				}
				// Validating whether sequence is integer.
				if (!seqNum.matches("[\\d]+")) {
					messageList.add(new ErrorMessage("product.seq.invalid"));
					sequenceValid = false;
					break;
				}
				seqNumInt = Integer.valueOf(seqNum).intValue();
				if (seqNumInt <= 0) {
					messageList.add(new ErrorMessage("product.seq.invalid"));
					sequenceValid = false;
					break;
				}
				// If everything valid
				AdministrationOptionVO benefitAdminVO = new AdministrationOptionVO();
				// Set AdmnOptLvlAssnId
				benefitAdminVO.setAdminLevelOptionAssnSystemId(Integer.valueOf(
						(String) hiddenValALAssnId.get(rowNum)).intValue());
				// Set ADMIN_LVL_SYS_ID
				benefitAdminVO.setAdminLevelSystemId(Integer.valueOf(
						(String) hiddenValAdminLevelId.get(rowNum)).intValue());
				//Set ADMIN_OPT_SYS_ID
				benefitAdminVO.setAdminOptionSystemId(Integer.valueOf(
						(String) hiddenValAdminOption.get(rowNum)).intValue());
				//Set BNFT_ADMIN_SYS_ID
				benefitAdminVO
						.setBenefitAdminSystemId(Integer.valueOf(
								(String) hiddenValBnftAdmnSysId.get(rowNum))
								.intValue());
				//Set BLVL_BNFT_LVL_ID
				benefitAdminVO.setBenefitLevelSystemId(Integer.valueOf(
						(String) hiddenValBnftLvlSysIdFrmMstr.get(rowNum))
						.intValue());

				benefitAdminVO
						.setStandardBenefitId(getStandardBenefitSessionObject()
								.getStandardBenefitKey());
				benefitAdminVO
						.setStandardBenefitParentId(getStandardBenefitSessionObject()
								.getStandardBenefitParentKey());
				benefitAdminVO
						.setStandardBenefitName(getStandardBenefitSessionObject()
								.getStandardBenefitName());
				benefitAdminVO
						.setStandardBenefitVersion(getStandardBenefitSessionObject()
								.getStandardBenefitVersionNumber());
				benefitAdminVO
						.setSbBusinessDomains(getStandardBenefitSessionObject()
								.getBusinessDomains());

				benefitAdminVO.setSequenceNumber(seqNumInt);

				benefitAdminVOList.add(benefitAdminVO);
			}
			List orderedList = null;
			if (sequenceValid) {
				orderedList = this.reorder(benefitAdminVOList);
				AdministrationOptionResponse administrationOptionResponse = saveAdminOption(orderedList);
				if (null != administrationOptionResponse) {
					this.associatedAdministrationList = administrationOptionResponse
							.getAdminOptionList();
					 getRequest().setAttribute("RETAIN_Value", "");
					return "benefitAdminAssociation";
				}
			} else {
				 getRequest().setAttribute("RETAIN_Value", "");
				addAllMessagesToRequest(messageList);
				return "";
			}
		}
		 getRequest().setAttribute("RETAIN_Value", "");
		return "";
	}

	/**
	 * @param orderedList
	 * @return
	 */
	private AdministrationOptionResponse saveAdminOption(List orderedList) {
		AdministrationOptionRequest administrationOptionRequest = (AdministrationOptionRequest) this
				.getServiceRequest(ServiceManager.SAVE_ADMIN_OPTION_REQUEST);
		administrationOptionRequest.setActionForUpdateSequence(true);
		administrationOptionRequest.setAdminList(orderedList);
		administrationOptionRequest.setBenefitAdminSysId(Integer
				.parseInt(getSession().getAttribute(
						WebConstants.SESSION_BNFT_ADMIN_ID).toString()));
		administrationOptionRequest.setBenefitDefiniitonKey(Integer
				.parseInt(getSession().getAttribute("SESSION_BNFT_DEFN_ID").toString()));
		AdministrationOptionResponse administrationOptionResponse = (AdministrationOptionResponse) executeService(administrationOptionRequest);
		return administrationOptionResponse;
	}

	/**
	 * @param adminOptionHeaderViewPanel
	 *            The adminOptionHeaderViewPanel to set.
	 */
	public void setAdminOptionHeaderViewPanel(
			HtmlPanelGrid adminOptionHeaderViewPanel) {
		this.adminOptionHeaderViewPanel = adminOptionHeaderViewPanel;
	}

	/**
	 * @return Returns the adminOptionPanel.
	 */
	public HtmlPanelGrid getAdminOptionPanel() {
		HtmlPanelGrid panel = new HtmlPanelGrid();
		AdministrationOptionBO adminOption = null;
		//List seqNumList = new ArrayList();
		int seqNum = 0;

		if (null != this.getAssociatedAdministrationList()
				&& this.getAssociatedAdministrationList().size() != 0) {
			List adminOptionList = this.getAssociatedAdministrationList();
			if (null != adminOptionList) {
				panel.setWidth("100%");
				panel.setColumns(6);
				panel.setBorder(0);
				panel.setBgcolor("#cccccc");
				panel.setCellpadding("3");
				panel.setCellspacing("1");
				Collections.sort(adminOptionList);
				for (int i = 0; i < adminOptionList.size(); i++) {
					
					if (adminOptionList.size() > 0) {
						adminOption = (AdministrationOptionBO) adminOptionList
								.get(i);
						// !this.isEditAdminOption() &&
						if (!this.isEditButtonIsClicked()
								&& this.saveAdminOption) {
							if (adminOptionList.indexOf(adminOption) == 0) {
								seqNum = adminOption.getSequenceNumber();
							} else {
								if (seqNum != 0
										&& seqNum > adminOption
												.getSequenceNumber()) {
									this.getStandardBenefitSessionObject()
											.setSequenceNumber(seqNum);

								} else {
									seqNum = adminOption.getSequenceNumber();
									this.getStandardBenefitSessionObject()
											.setSequenceNumber(seqNum);
								}
							}
						}

					}

					HtmlInputText inputText = new HtmlInputText();
					inputText.setId("SeqNo" + i);
					inputText.setStyleClass("formInputField");
					inputText.setStyle("width:50px;");
					inputText.setValue("" + adminOption.getSequenceNumber());
					inputText.setOnkeypress("isNum();");
					inputText.setMaxlength(3);
					inputText.setSize(1);

					ValueBinding valBindingForSeq = FacesContext
							.getCurrentInstance().getApplication()
							.createValueBinding(
									"#{adminOptionsBackingBean.hiddenValMapSeq["
											+ i + "]}");
					inputText.setValueBinding("value", valBindingForSeq);

					HtmlInputHidden hiddenForAdminLevelAssnId = new HtmlInputHidden();
					hiddenForAdminLevelAssnId.setId("ALAssnId" + i);
					hiddenForAdminLevelAssnId.setValue(new Integer(adminOption
							.getAdminLevelOptionAssnSystemId()));

					ValueBinding valBindingForAssnId = FacesContext
							.getCurrentInstance().getApplication()
							.createValueBinding(
									"#{adminOptionsBackingBean.hiddenValALAssnId["
											+ i + "]}");
					hiddenForAdminLevelAssnId.setValueBinding("value",
							valBindingForAssnId);

					HtmlInputHidden hiddenForAdminLevelId = new HtmlInputHidden();
					hiddenForAdminLevelId.setId("AdminLevelId" + i);
					hiddenForAdminLevelId.setValue(new Integer(adminOption
							.getAdminLevelSystemId()));

					ValueBinding valBindingForAdminLevelId = FacesContext
							.getCurrentInstance().getApplication()
							.createValueBinding(
									"#{adminOptionsBackingBean.hiddenValAdminLevelId["
											+ i + "]}");
					hiddenForAdminLevelId.setValueBinding("value",
							valBindingForAdminLevelId);

					HtmlInputHidden hiddenForAdminOptionId = new HtmlInputHidden();
					hiddenForAdminOptionId.setId("AdminOptionId" + i);
					hiddenForAdminOptionId.setValue(new Integer(adminOption
							.getAdminOptionSystemId()));

					ValueBinding valBindingForAdminOptionId = FacesContext
							.getCurrentInstance().getApplication()
							.createValueBinding(
									"#{adminOptionsBackingBean.hiddenValAdminOption["
											+ i + "]}");
					hiddenForAdminOptionId.setValueBinding("value",
							valBindingForAdminOptionId);

					HtmlInputHidden hiddenForBenefitLevelId = new HtmlInputHidden();
					hiddenForBenefitLevelId.setId("BenefitLevelId" + i);
					hiddenForBenefitLevelId.setValue(new Integer(adminOption
							.getBenefitLevelSysIdFromMaster()));

					ValueBinding valBindingForBenefitLevelId = FacesContext
							.getCurrentInstance().getApplication()
							.createValueBinding(
									"#{adminOptionsBackingBean.hiddenBenefitLevelId["
											+ i + "]}");
					hiddenForBenefitLevelId.setValueBinding("value",
							valBindingForBenefitLevelId);

					HtmlInputHidden hiddenForBnftAdmnSysId = new HtmlInputHidden();
					hiddenForBnftAdmnSysId.setId("BnftAdmnSysId" + i);
					hiddenForBnftAdmnSysId.setValue(new Integer(adminOption
							.getBenefitAdminSystemId()));

					ValueBinding valBindingForBnftAdmnSysId = FacesContext
							.getCurrentInstance().getApplication()
							.createValueBinding(
									"#{adminOptionsBackingBean.hiddenValBnftAdmnSysId["
											+ i + "]}");
					hiddenForBnftAdmnSysId.setValueBinding("value",
							valBindingForBnftAdmnSysId);

					HtmlInputHidden hiddenForBnftLvlSysIdFrmMstr = new HtmlInputHidden();
					hiddenForBnftLvlSysIdFrmMstr.setId("BnftAdminSysId" + i);
					hiddenForBnftLvlSysIdFrmMstr.setValue(new Integer(
							adminOption.getBenefitLevelSysIdFromMaster()));

					ValueBinding valBindingForBnftLvlSysIdFrmMstr = FacesContext
							.getCurrentInstance().getApplication()
							.createValueBinding(
									"#{adminOptionsBackingBean.hiddenValBnftLvlSysIdFrmMstr["
											+ i + "]}");
					hiddenForBnftLvlSysIdFrmMstr.setValueBinding("value",
							valBindingForBnftLvlSysIdFrmMstr);

					HtmlOutputText outputText1 = new HtmlOutputText();
					outputText1.setId("Name" + i);
					outputText1.setValue(adminOption.getAdminOptionDesc());

					HtmlOutputText outputText2 = new HtmlOutputText();
					outputText2.setId("AdminLevel" + i);
					outputText2.setValue(adminOption.getAdminLevelDesc());

					HtmlOutputText outputText3 = new HtmlOutputText();
					outputText3.setId("BenefitLevel" + i);
					outputText3.setValue(adminOption.getBenefitLevelDesc());

					HtmlOutputText htmlOutputText = new HtmlOutputText();
					htmlOutputText.setValue("    ");
//					HtmlCommandButton deleteButton = new HtmlCommandButton();
//					added for mulitiple adminoption delete
					HtmlSelectBooleanCheckbox deleteCheckBox = new HtmlSelectBooleanCheckbox();
					deleteCheckBox.setId("checkbox"+i);
					deleteCheckBox.setOnclick("test(this)");
					ValueBinding valBindingForDeleteBnftAdmnSysId = FacesContext
					.getCurrentInstance().getApplication()
					.createValueBinding(
							"#{adminOptionsBackingBean.hiddenValDeleteBnftAdmnSysId["
									+ adminOption.getAdminLevelOptionAssnSystemId() + "]}");
//					added for mulitiple adminoption delete		
					deleteCheckBox.setValueBinding("value",valBindingForDeleteBnftAdmnSysId);
					
							
					deleteCheckBox.setId("Delete" + i);
//					deleteCheckBox.setStyle("border:0;");
					MethodBinding deleteMetBind = FacesContext
							.getCurrentInstance()
							.getApplication()
							.createMethodBinding(
									"#{adminOptionsBackingBean.deleteAdminOptions}",
									new Class[] {});
//					deleteButton
//							.setOnclick("return deleteAdminOptionAttachedToBenefit()");
//					deleteButton.setAction(deleteMetBind);
//					deleteButton
//							.addActionListener(new BenefitLevelToAdminOptionListener());

					HtmlCommandButton editButton = new HtmlCommandButton();
					editButton.setId("Edit" + i);
					editButton.setStyle("border:0;");
					editButton.setImage("../../images/edit.gif");
					editButton.setTitle("Edit");
					editButton.setValue(new Integer(adminOption
							.getAdminLevelOptionAssnSystemId()));

					MethodBinding editMetBind = FacesContext
							.getCurrentInstance()
							.getApplication()
							.createMethodBinding(
									"#{adminOptionsBackingBean.retrieveAdminOptions}",
									new Class[] {});
					editButton.setAction(editMetBind);
					editButton
							.addActionListener(new BenefitLevelToAdminOptionListener());

					HtmlOutputLabel lblDesc = new HtmlOutputLabel();
					lblDesc.setFor("Update" + i);
					//lblDesc.setId("lblDesc" + i);
					lblDesc.setId("lblDesc"+RandomStringUtils.randomAlphanumeric(15));

					HtmlOutputLabel lblName = new HtmlOutputLabel();
					lblName.setFor("Name" + i);
					//lblName.setId("lblName" + i);
					lblName.setId("lblName"+RandomStringUtils.randomAlphanumeric(15));
					
					HtmlOutputLabel lblAdminLevel = new HtmlOutputLabel();
					lblName.setFor("AdminLevel" + i);
					//lblName.setId("lblAdminLevel" + i);
					lblAdminLevel.setId("lblAdminLevel"+RandomStringUtils.randomAlphanumeric(15));
					
					HtmlOutputLabel lblBenefitLevel = new HtmlOutputLabel();
					lblName.setFor("BenefitLevel" + i);
					//lblName.setId("lblBenefitLevel" + i);
					lblBenefitLevel.setId("lblBenefitLevel"+RandomStringUtils.randomAlphanumeric(15));
					
					HtmlOutputLabel lblDelete = new HtmlOutputLabel();
					lblDelete.setFor("Delete" + i);
					//lblDelete.setId("lblDelete" + i);
					lblDelete.setId("lblDelete"+RandomStringUtils.randomAlphanumeric(15));

					lblDesc.getChildren().add(inputText);
					lblName.getChildren().add(outputText1);
					lblAdminLevel.getChildren().add(outputText2);
					lblBenefitLevel.getChildren().add(outputText3);
					lblName.getChildren().add(hiddenForAdminLevelAssnId);
					lblName.getChildren().add(hiddenForBnftAdmnSysId);
					lblName.getChildren().add(hiddenForBnftLvlSysIdFrmMstr);
					lblName.getChildren().add(hiddenForAdminLevelId);
					lblName.getChildren().add(hiddenForAdminOptionId);
					lblName.getChildren().add(hiddenForBenefitLevelId);
					lblDelete.getChildren().add(editButton);
					lblDelete.getChildren().add(htmlOutputText);
					//lblDelete.getChildren().add(deleteButton);

					panel.getChildren().add(lblDesc);
					panel.getChildren().add(lblName);
					panel.getChildren().add(lblAdminLevel);
					panel.getChildren().add(lblBenefitLevel);
					panel.getChildren().add(lblDelete);
//					added for mulitiple adminoption delete
					panel.getChildren().add(deleteCheckBox);
					
				}
			}
		}
		return panel;

	}

	private void registerSequence(List adminOptionsBOList) {
		SequenceUtil sequenceUtil = new SequenceUtil();
		sequenceUtil.registerObjects(adminOptionsBOList,
				"adminLevelOptionAssnSystemId", "sequenceNumber");
	}

	private List reorder(List adminVOList) {
		SequenceUtil sequenceUtil = new SequenceUtil();
		return sequenceUtil.reOrderObjects(adminVOList);
	}

	/**
	 * @param adminOptionPanel
	 *            The adminOptionPanel to set.
	 */
	public void setAdminOptionPanel(HtmlPanelGrid adminOptionPanel) {
		this.adminOptionPanel = adminOptionPanel;
	}

	/**
	 * @return Returns the adminOptionsListFrmScreen.
	 */
	public List getAdminOptionsListFrmScreen() {
		return adminOptionsListFrmScreen;
	}

	/**
	 * @param adminOptionsListFrmScreen
	 *            The adminOptionsListFrmScreen to set.
	 */
	public void setAdminOptionsListFrmScreen(List adminOptionsListFrmScreen) {
		this.adminOptionsListFrmScreen = adminOptionsListFrmScreen;
	}

	/**
	 * @return Returns the displayPanel.
	 */
	public HtmlPanelGrid getDisplayPanel() {
		HtmlPanelGrid displayPanel = new HtmlPanelGrid();
		List adminOptionList = new ArrayList();
		displayPanel.setCellpadding("0");
		displayPanel.setCellspacing("0");
		displayPanel.setWidth("100%");
		displayPanel.setColumns(5);
		displayPanel.setBorder(0);
		displayPanel.setStyle("height:20px;");
		displayPanel.setStyleClass("dataTableHeader");
		displayPanel.setBgcolor("#cccccc");
		HtmlOutputText outputText = new HtmlOutputText();
		outputText.setValue("Associated Admin Options");
		displayPanel.getChildren().add(outputText);
		return displayPanel;
	}

	/**
	 * @param displayPanel
	 *            The displayPanel to set.
	 */
	public void setDisplayPanel(HtmlPanelGrid displayPanel) {
		this.displayPanel = displayPanel;
	}

	/**
	 * @return Returns the headerPanel.
	 */
	public HtmlPanelGrid getHeaderPanel() {
		return headerPanel;
	}

	/**
	 * @param headerPanel
	 *            The headerPanel to set.
	 */
	public void setHeaderPanel(HtmlPanelGrid headerPanel) {
		this.headerPanel = headerPanel;
	}

	/**
	 * @return Returns the hiddenBenefitLevelId.
	 */
	public Map getHiddenBenefitLevelId() {
		return hiddenBenefitLevelId;
	}

	/**
	 * @param hiddenBenefitLevelId
	 *            The hiddenBenefitLevelId to set.
	 */
	public void setHiddenBenefitLevelId(Map hiddenBenefitLevelId) {
		this.hiddenBenefitLevelId = hiddenBenefitLevelId;
	}

	/**
	 * @return Returns the hiddenValAdminLevelId.
	 */
	public Map getHiddenValAdminLevelId() {
		return hiddenValAdminLevelId;
	}

	/**
	 * @param hiddenValAdminLevelId
	 *            The hiddenValAdminLevelId to set.
	 */
	public void setHiddenValAdminLevelId(Map hiddenValAdminLevelId) {
		this.hiddenValAdminLevelId = hiddenValAdminLevelId;
	}

	/**
	 * @return Returns the hiddenValAdminOption.
	 */
	public Map getHiddenValAdminOption() {
		return hiddenValAdminOption;
	}

	/**
	 * @param hiddenValAdminOption
	 *            The hiddenValAdminOption to set.
	 */
	public void setHiddenValAdminOption(Map hiddenValAdminOption) {
		this.hiddenValAdminOption = hiddenValAdminOption;
	}

	/**
	 * @return Returns the hiddenValALAssnId.
	 */
	public Map getHiddenValALAssnId() {
		return hiddenValALAssnId;
	}

	/**
	 * @param hiddenValALAssnId
	 *            The hiddenValALAssnId to set.
	 */
	public void setHiddenValALAssnId(Map hiddenValALAssnId) {
		this.hiddenValALAssnId = hiddenValALAssnId;
	}

	/**
	 * @return Returns the hiddenValBnftAdmnSysId.
	 */
	public Map getHiddenValBnftAdmnSysId() {
		return hiddenValBnftAdmnSysId;
	}

	/**
	 * @param hiddenValBnftAdmnSysId
	 *            The hiddenValBnftAdmnSysId to set.
	 */
	public void setHiddenValBnftAdmnSysId(Map hiddenValBnftAdmnSysId) {
		this.hiddenValBnftAdmnSysId = hiddenValBnftAdmnSysId;
	}

	/**
	 * @return Returns the hiddenValBnftLvlSysIdFrmMstr.
	 */
	public Map getHiddenValBnftLvlSysIdFrmMstr() {
		return hiddenValBnftLvlSysIdFrmMstr;
	}

	/**
	 * @param hiddenValBnftLvlSysIdFrmMstr
	 *            The hiddenValBnftLvlSysIdFrmMstr to set.
	 */
	public void setHiddenValBnftLvlSysIdFrmMstr(Map hiddenValBnftLvlSysIdFrmMstr) {
		this.hiddenValBnftLvlSysIdFrmMstr = hiddenValBnftLvlSysIdFrmMstr;
	}

	/**
	 * @return Returns the hiddenValMapSeq.
	 */
	public Map getHiddenValMapSeq() {
		return hiddenValMapSeq;
	}

	/**
	 * @param hiddenValMapSeq
	 *            The hiddenValMapSeq to set.
	 */
	public void setHiddenValMapSeq(Map hiddenValMapSeq) {
		this.hiddenValMapSeq = hiddenValMapSeq;
	}

	/**
	 * @return Returns the saveAdminOption.
	 */
	public boolean isSaveAdminOption() {
		return saveAdminOption;
	}

	/**
	 * @param saveAdminOption
	 *            The saveAdminOption to set.
	 */
	public void setSaveAdminOption(boolean saveAdminOption) {
		this.saveAdminOption = saveAdminOption;
	}

	/**
	 * @return Returns the editButtonIsClicked.
	 */
	public boolean isEditButtonIsClicked() {
		return editButtonIsClicked;
	}

	/**
	 * @param editButtonIsClicked
	 *            The editButtonIsClicked to set.
	 */
	public void setEditButtonIsClicked(boolean editButtonIsClicked) {
		this.editButtonIsClicked = editButtonIsClicked;
	}
	/**
	 * @return Returns the hiddenValDeleteBnftAdmnSysId.
	 */
	public Map getHiddenValDeleteBnftAdmnSysId() {
		return hiddenValDeleteBnftAdmnSysId;
	}
	/**
	 * @param hiddenValDeleteBnftAdmnSysId The hiddenValDeleteBnftAdmnSysId to set.
	 */
	public void setHiddenValDeleteBnftAdmnSysId(Map hiddenValDeleteBnftAdmnSysId) {
		this.hiddenValDeleteBnftAdmnSysId = hiddenValDeleteBnftAdmnSysId;
	}
	/**
	 * @return Returns the benefitAdminSystemIdHidden.
	 */
	public int getBenefitAdminSystemIdHidden() {
		benefitAdminSystemIdHidden = (Integer.parseInt(getSession().getAttribute(
						WebConstants.SESSION_BNFT_ADMIN_ID).toString()));
		return benefitAdminSystemIdHidden;
	}
	/**
	 * @param benefitAdminSystemIdHidden The benefitAdminSystemIdHidden to set.
	 */
	public void setBenefitAdminSystemIdHidden(int benefitAdminSystemIdHidden) {
		this.benefitAdminSystemIdHidden = benefitAdminSystemIdHidden;
	}
}
