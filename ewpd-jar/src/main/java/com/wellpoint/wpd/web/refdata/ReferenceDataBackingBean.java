/*
 * ReferenceDataBackingBean.java
 * 
 * Created on Feb 21, 2007
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */

package com.wellpoint.wpd.web.refdata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import javax.faces.model.SelectItem;

import com.wellpoint.wpd.business.datatype.locatecriteria.DataTypeLocateResult;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.AdminLevelLocateCriteria;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.datatype.request.DataTypeRequest;
import com.wellpoint.wpd.common.datatype.response.DataTypeResponse;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.refdata.domain.ReferenceData;
import com.wellpoint.wpd.common.framework.util.StringUtil;
import com.wellpoint.wpd.common.item.bo.ItemBO;
import com.wellpoint.wpd.common.lookup.request.LookupAdminQuestionRequest;
import com.wellpoint.wpd.common.lookup.response.LookupAdminQuestionResponse;
import com.wellpoint.wpd.common.product.bo.ProductBO;
import com.wellpoint.wpd.common.refdata.request.RefDataRequest;
import com.wellpoint.wpd.common.refdata.request.ReferenceDataLookupRequest;
import com.wellpoint.wpd.common.refdata.response.RefDataResponse;
import com.wellpoint.wpd.common.refdata.response.ReferenceDataLookupResponse;
import com.wellpoint.wpd.common.standardbenefit.request.AdminLevelRequest;
import com.wellpoint.wpd.common.standardbenefit.request.LocateBenefitLevelListRequest;
import com.wellpoint.wpd.common.standardbenefit.request.LookupAdminOptionRequest;
import com.wellpoint.wpd.common.standardbenefit.response.AdminLevelResponse;
import com.wellpoint.wpd.common.standardbenefit.response.LocateBenefitLevelListResponse;
import com.wellpoint.wpd.common.standardbenefit.response.LookupAdminOptionResponse;
import com.wellpoint.wpd.common.subcatalog.bo.SubCatalogBO;
import com.wellpoint.wpd.common.subcatalog.vo.SubCatalogVO;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * 
 * Backing bean for the popups.
 * 
 * 
 * 
 * This bean will bind with the jsp pages.
 * 
 * 
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * 
 * @version $Id: $
 *  
 */

public class ReferenceDataBackingBean extends WPDBackingBean {

	private String questionCriteria;

	private List lobList;

	private List busEntityList;

	private List lobListSearch;

	private List busEntityListSearch;

	private List busGroupListSearch;

	private List dataTypeList;

	private boolean dataTypeRetrieved = false;

	private List termList;

	private List qualifierList;

	private List referenceList;

	private List questionList;

	private List busGroupList;

	private List referenceResultList;

	private List termResultList;

	private List termQualifiersList;

	private List providerArrangementsList;

	private RefDataRequest refdataRequest;

	private List dataTypeListForCombo;

	private List productFamilyList;

	private List questionSearchResultList;

	private List adminOptionsList;

	private List administrationList;

	List benefitLevelListPopUp;

	private List mandateTypeListForCombo;

	private List groupSizeListForCombo;

	private List groupSizeList;

	private List fundingArrangementListForCombo;

	private List jurisdictionResultList;

	private List noteTypeListForCombo;

	private List fundingArrangementList;

	private List corporatePlanList;

	private List productCodes;

	private List headQuartersStates;

	private List standardPlanCode;

	private List coverageList;

	private List networkList;

	private List pricingList;

	private List productList;

	private List systemDomainResultList;

	private List contractTypeList;

	private List baseContractCode;

	//  for contract gen Info

	private List contractTypeListForCombo = new ArrayList();
	
	private List contractTypeListForAccumCombo = new ArrayList();

	private List complianceStatusListForCombo = new ArrayList();

	private List noteTypeList;

	private List stateResultList;

	private List ruleResultList;

	private List benefitTypeList;

	private List entityTypeListForCombo;

	private List entityTypeListForSearch;

	private String mandate = "MANDATE";

	private String standard = "STANDARD";

	private List effectivenessList;

	private List mandateCategoryList;

	private String refDataLookupRecords;

	private String benefitLevelRecords;

	private String searchValueForPopUp;

	private boolean searchCriteriaEntered;

	private boolean searchCheck;

	private List adminOptionList;

	private List entityTypeListForBenefitCombo;

	private String sortOrder;

	private boolean renderFirstList = false;

	private List benefitCategoryListForCombo;

	private int entityId;

	private int adminId;

	private String entityType;

	private List spsMappingSearchResults;

	//For Blue Exchange Popup
	private String blueExchangeCode;

	//For the Header in the Blue Exchange popup
	private String blueExchangeHeader;

	//for the message Indicator in blue exchanfe
	private List messageIndicatorList;

	private String searchString;

	private int lookUpActionHidden;

	private String catalogNameHidden;

	private String searchTermQualifier;

	private String catalogNameForTermQualifier;

	private String lob;

	private String businessEntity;

	private String businessGroup;

	private String lookUpAction;

	private List marketSegmentList;

	private int index;

	private String parentCatalog;

	private String indicativeLoad;

	private String indicativeLoadHeader;

	private boolean recordsGreaterThanMaxSize;

	private List claimTypeListForCombo;

	private List medAssignIndicatorListForCombo;

	private List hospitalFacilityCodeListForCombo;

	private List genderListForCombo;

	private List groupStateListForCombo;

	private List memberRelationshipCodeListForCombo;

	private List placeOfServiceListForCombo;

	private List typeOfBillListForCombo;

	private List contractStatuses;
	/**
	 * @param spsMappingSearchResults
	 *            The spsMappingSearchResults to set.
	 */
	public void setSpsMappingSearchResults(List spsMappingSearchResults) {
		this.spsMappingSearchResults = spsMappingSearchResults;
	}

	/**
	 * @return Returns the benefitLevelRecords.
	 */
	public String getBenefitLevelRecords() {

		LocateBenefitLevelListRequest locateBenefitLevelListRequest = (LocateBenefitLevelListRequest)

		this.getServiceRequest(ServiceManager.LOCATE_BENEFITLEVEL_LIST);

		int benefitsystemId = Integer.parseInt((String) this.getSession()
				.getAttribute("SESSION_BNFT_DEFN_ID"));

		locateBenefitLevelListRequest.setBenefitsystemId(benefitsystemId);

		LocateBenefitLevelListResponse locateBenefitLevelListResponse =

		(LocateBenefitLevelListResponse) executeService(locateBenefitLevelListRequest);

		if (null != locateBenefitLevelListResponse) {
			benefitLevelListPopUp = locateBenefitLevelListResponse
					.getBenefitlevelList();
		} else
			benefitLevelListPopUp = null;

		this.setBenefitLevelListPopUp(benefitLevelListPopUp);

		return benefitLevelRecords;
	}

	/**
	 * @return Returns the blueExchangeCode.
	 */
	public String getBlueExchangeCode() {
		blueExchangeHeader = (String) getRequest().getParameter(
				WebConstants.POPUP_HEADER);
		this.getRefDataLookupRecords();
		return blueExchangeCode;
	}

	/**
	 * @param blueExchangeCode
	 *            The blueExchangeCode to set.
	 */
	public void setBlueExchangeCode(String blueExchangeCode) {
		this.blueExchangeCode = blueExchangeCode;
	}

	/**
	 * @param benefitLevelRecords
	 *            The benefitLevelRecords to set.
	 */
	public void setBenefitLevelRecords(String benefitLevelRecords) {
		this.benefitLevelRecords = benefitLevelRecords;
	}

	public void loadRefDataPopUp() {
		List resultList = new ArrayList();
		String lookUpAction = (new Integer(this.lookUpActionHidden)).toString();
		if (null != lookUpAction
				&& !lookUpAction.equals(WebConstants.EMPTY_STRING)) {
			int action = Integer.parseInt(lookUpAction);
			if (action != 4 && action != 6) {
				ReferenceDataLookupRequest referenceDataLookupRequest = getFilterReferenceDataLookupRequest();
				ReferenceDataLookupResponse referenceDataLookupResponse = (ReferenceDataLookupResponse) this
						.executeService(referenceDataLookupRequest);
				if (null != referenceDataLookupResponse) {
					resultList = referenceDataLookupResponse
							.getSubCatalogList();
				}
				this.lookUpActionHidden = referenceDataLookupRequest
						.getAction();
				this.catalogNameHidden = referenceDataLookupRequest
						.getSubCatalogVO().getParentCatalog();
				this.setSortOrder(referenceDataLookupResponse.getSortOrder());
				if (this.getSortOrder().equals(BusinessConstants.PRIMARY_CODE))
					this.setRenderFirstList(true);

				if (null != resultList && !resultList.isEmpty()) {
					Iterator iter = resultList.iterator();
					if (iter.hasNext()) {
						Object obj = iter.next();
						if (obj instanceof SubCatalogBO
								|| obj instanceof ItemBO) {
							Collections.sort(resultList);
						}
					}
					this.setQualifierDataLookUpList(resultList);
				} else
					this.setQualifierDataLookUpList(null);
			}
		}

	}

	/**
	 * @return Returns the refDataLookupRecords.
	 */
	public String getRefDataLookupRecords() {
		String lookUpAction = getRequest().getParameter(
				WebConstants.LOOK_UP_ACTION);
		if (null != lookUpAction && !("").equals(lookUpAction)) {
			this.lookUpActionHidden = Integer.parseInt(lookUpAction);
		}
		if (null != lookUpAction
				&& !lookUpAction.equals(WebConstants.EMPTY_STRING)) {
			int action = Integer.parseInt(lookUpAction);
			if (action != 4 && action != 6) {
				ReferenceDataLookupRequest referenceDataLookupRequest = getReferenceDataLookupRequest();
				ReferenceDataLookupResponse referenceDataLookupResponse = (ReferenceDataLookupResponse) this
						.executeService(referenceDataLookupRequest);

				refDataLookUpList = referenceDataLookupResponse
						.getSubCatalogList();
				this.setSortOrder(referenceDataLookupResponse.getSortOrder());

				if ((action == 5 && refDataLookUpList == null)
						|| ((refDataLookUpList == null) || refDataLookUpList
								.size() == 0)) {
					ErrorMessage em = new ErrorMessage(
							WebConstants.SEARCH_RESULTS_ZERO_FOR_TERMPVA);
					this.addMessageToRequest(em);
					return "";
				}
				if (this.getSortOrder().equals(BusinessConstants.PRIMARY_CODE))
					this.setRenderFirstList(true);

				if (null != refDataLookUpList && !refDataLookUpList.isEmpty()) {
					Iterator iter = refDataLookUpList.iterator();
					if (iter.hasNext()) {
						Object obj = iter.next();
						if (obj instanceof SubCatalogBO
								|| obj instanceof ItemBO) {
							Collections.sort(refDataLookUpList);
						}
					}
					this.setRefDataLookUpList(refDataLookUpList);
				} else
					this.setRefDataLookUpList(null);
			}
		}

		return refDataLookupRecords;
	}

	public String accumulatorSPSIdSearch() {

		//Check if search criteria field is not entered
		if (searchValueForPopUp != null
				&& !"".equals(searchValueForPopUp.trim())) {
			searchCriteriaEntered = false;
		} else {
			searchCriteriaEntered = true;
			searchValueForPopUp = "%";
		}
		//        if (!searchCriteriaEntered) {
		//            ErrorMessage em = new
		// ErrorMessage(WebConstants.REQUIRED_SEARCH_FIELDS);
		//            this.addMessageToRequest(em);
		//            return "SearchCriteria";
		//        }

		SubCatalogVO subCatalogVO = new SubCatalogVO();

		ReferenceDataLookupRequest referenceDataLookupRequest = (ReferenceDataLookupRequest) this
				.getServiceRequest(ServiceManager.REF_DATA_LOOK_UP_REQUEST);
		//		   if(searchValueForPopUp.indexOf("%") >= 0 ){
		//		   	searchValueForPopUp = searchValueForPopUp.replaceAll("%", "`%");
		//    	}else if(searchValueForPopUp.indexOf("_") >= 0){
		//    		searchValueForPopUp = searchValueForPopUp.replaceAll("_", "`_");
		//    	}
		subCatalogVO.setSearchCriteriaEntered(searchCriteriaEntered);
		subCatalogVO.setSearchText(searchValueForPopUp.trim());
		referenceDataLookupRequest.setAction(11);
		/*
		 * if(this.getEntityId()>0)
		 * subCatalogVO.setEntityId(this.getEntityId());
		 * if(null!=this.getEntityType()&&!"".equals(this.getEntityType()))
		 * subCatalogVO.setEntityType(this.getEntityType());
		 */
		referenceDataLookupRequest.setSubCatalogVO(subCatalogVO);
		ReferenceDataLookupResponse referenceDataLookupResponse = (ReferenceDataLookupResponse) this
				.executeService(referenceDataLookupRequest);

		this.refDataLookUpList = referenceDataLookupResponse
				.getSubCatalogList();
		if (null != refDataLookUpList)
			Collections.sort(refDataLookUpList);
		if (searchCriteriaEntered)
			this.setSearchValueForPopUp("");
		if (this.refDataLookUpList == null
				|| this.refDataLookUpList.size() == 0) {
			ErrorMessage em = new ErrorMessage(WebConstants.SEARCH_RESULTS_ZERO);
			this.addMessageToRequest(em);
			return "SearchCriteria";

		}

		return "";

	}

	public String search() {

		//Check if search criteria field is not entered
		if (searchValueForPopUp != null
				&& !"".equals(searchValueForPopUp.trim())) {
			searchCriteriaEntered = false;
		} else {
			searchCriteriaEntered = true;
			searchValueForPopUp = "%";
		}
		//        if (!searchCriteriaEntered) {
		//            ErrorMessage em = new
		// ErrorMessage(WebConstants.REQUIRED_SEARCH_FIELDS);
		//            this.addMessageToRequest(em);
		//            return "SearchCriteria";
		//        }

		SubCatalogVO subCatalogVO = new SubCatalogVO();

		ReferenceDataLookupRequest referenceDataLookupRequest = (ReferenceDataLookupRequest) this
				.getServiceRequest(ServiceManager.REF_DATA_LOOK_UP_REQUEST);
		//		   if(searchValueForPopUp.indexOf("%") >= 0 ){
		//		   	searchValueForPopUp = searchValueForPopUp.replaceAll("%", "`%");
		//    	}else if(searchValueForPopUp.indexOf("_") >= 0){
		//    		searchValueForPopUp = searchValueForPopUp.replaceAll("_", "`_");
		//    	}
		subCatalogVO.setSearchCriteriaEntered(searchCriteriaEntered);
		subCatalogVO.setSearchText(searchValueForPopUp.trim());
		referenceDataLookupRequest.setAction(4);
		if (this.getEntityId() > 0)
			subCatalogVO.setEntityId(this.getEntityId());
		if (null != this.getEntityType() && !"".equals(this.getEntityType()))
			subCatalogVO.setEntityType(this.getEntityType());
		referenceDataLookupRequest.setSubCatalogVO(subCatalogVO);
		ReferenceDataLookupResponse referenceDataLookupResponse = (ReferenceDataLookupResponse) this
				.executeService(referenceDataLookupRequest);

		this.refDataLookUpList = referenceDataLookupResponse
				.getSubCatalogList();
		if (searchCriteriaEntered)
			this.setSearchValueForPopUp("");
		if (this.refDataLookUpList == null
				|| this.refDataLookUpList.size() == 0) {
			ErrorMessage em = new ErrorMessage(WebConstants.SEARCH_RESULTS_ZERO);
			this.addMessageToRequest(em);
			return "SearchCriteria";

		}

		return "";

	}

	/**
	 * This method gets the search string from the page and sends the request to
	 * service class and gets the response as a list for the search string.
	 * 
	 * @return String
	 */
	public String getSearchTermQualifier() {
		SubCatalogVO subCatalogVO = new SubCatalogVO();
		if (null != getRequest().getParameter(
				WebConstants.FILTER_PARENT_CATALOG)) {
			this.catalogNameForTermQualifier = getRequest().getParameter(
					WebConstants.FILTER_PARENT_CATALOG);
		}
		this.lookUpAction = getRequest().getParameter(
				WebConstants.FILTER_LOOKUP_ACTION);
		this.lob = getRequest().getParameter(WebConstants.FILTER_LINE_OF_BUSINESS);
		if(null!=this.lob){
		Pattern pLob = Pattern.compile("^[0-9~a-zA-Z/_\\s]+$");
		boolean pLobPatternFound = pLob.matcher(this.lob ).find();
		if (pLobPatternFound == true) {
			this.lob=this.lob;
		} else {
			this.lob=null;
		}
		}
		else{
			this.lob=null;
		}
		this.businessEntity = getRequest().getParameter(WebConstants.FILTER_BUSINESS_ENTITY);
		if(null!=this.businessEntity){
		Pattern pBusinessEntity = Pattern.compile("^[0-9~a-zA-Z/_\\s]+$");
		boolean pBuiEntityPatternFound = pBusinessEntity.matcher(this.businessEntity).find();
		if (pBuiEntityPatternFound == true) {
			this.businessEntity=this.businessEntity;
		} else {
			this.businessEntity=null;
		}
		}
		else{
			this.businessEntity=null;
		}
		this.businessGroup = getRequest().getParameter(
				WebConstants.FILTER_BUSINESS_GROUP);
		this.entityType = getRequest().getParameter(
				WebConstants.FILTER_ENTITY_TYPE);
		if (null != (getRequest().getParameter(WebConstants.FILTER_ENTITY_ID))) {
			this.entityId = Integer.parseInt(getRequest().getParameter(
					WebConstants.FILTER_ENTITY_ID));
		}
		if (null != this.catalogNameForTermQualifier) {
			subCatalogVO.setParentCatalog(this.catalogNameForTermQualifier);
		}
		ReferenceDataLookupRequest referenceDataLookupRequest = (ReferenceDataLookupRequest) this
				.getServiceRequest(ServiceManager.REF_DATA_LOOK_UP_REQUEST);
		if (null != lookUpAction
				&& !(WebConstants.EMPTY_STRING).equals(lookUpAction)) {
			int action = Integer.parseInt(lookUpAction);
			//		Check if search criteria field is not entered
			if (action != 1) {
				if (null != searchValueForPopUp
						&& !(WebConstants.EMPTY_STRING)
								.equals(searchValueForPopUp.trim())) {
					searchCriteriaEntered = false;
					//					searchValueForPopUp = WPDStringUtil
					//							.escapeString(searchValueForPopUp);
				} else {
					searchCriteriaEntered = true;
					searchValueForPopUp = "%";
				}
			}
			if (action == 1 || action == 31) {
				if (null != searchValueForPopUp
						&& !(WebConstants.EMPTY_STRING)
								.equals(searchValueForPopUp.trim())) {
					searchCriteriaEntered = false;
					searchValueForPopUp = WPDStringUtil
							.escapeString(searchValueForPopUp);
				} else {
					searchCriteriaEntered = true;
					searchValueForPopUp = "%";
				}
				//subCatalogVO.setParentCatalog(parentCatalog);
				referenceDataLookupRequest
						.setSearchString((searchValueForPopUp).trim()
								.toUpperCase());
			} else {
				if (action == 13
						&& this.getEntityType().equalsIgnoreCase("contract")) {
					subCatalogVO.setEntityId(this.entityId);
					subCatalogVO.setEntityType(this.entityType);
				}
				String lob = this.getLob();
				lob = lob.replaceAll("/a", "&");
				String businessEntity = this.getBusinessEntity();
				businessEntity = businessEntity.replaceAll("/a", "&");
				String businessGroup = this.getBusinessGroup();
				businessGroup = businessGroup.replaceAll("/a", "&");
				List lobList = WPDStringUtil.getListFromTildaString(lob, 2, 2,
						2);
				if (null != lobList) {
					this.getSession().setAttribute("lobList", lobList);
				}
				List businessEntityList = WPDStringUtil.getListFromTildaString(
						businessEntity, 2, 2, 2);
				if (null != businessEntityList) {
					this.getSession()
							.setAttribute("beList", businessEntityList);
				}
				List businessGroupList = WPDStringUtil.getListFromTildaString(
						businessGroup, 2, 2, 2);
				if (null != businessGroupList) {
					this.getSession().setAttribute("bgList", businessGroupList);
				}
				subCatalogVO.setBeList(businessEntityList);
				subCatalogVO.setBgList(businessGroupList);
				subCatalogVO.setLobList(lobList);
				//subCatalogVO.setParentCatalog(parentCatalog);
			}
			referenceDataLookupRequest.setAction(action);
			subCatalogVO.setSearchCriteriaEntered(searchCriteriaEntered);
			subCatalogVO.setSearchText(searchValueForPopUp.trim());
			referenceDataLookupRequest.setSubCatalogVO(subCatalogVO);
			ReferenceDataLookupResponse referenceDataLookupResponse = (ReferenceDataLookupResponse) this
					.executeService(referenceDataLookupRequest);
			refDataLookUpList = referenceDataLookupResponse.getSubCatalogList();
			this.setSortOrder(referenceDataLookupResponse.getSortOrder());
			if (this.getSortOrder().equals(BusinessConstants.PRIMARY_CODE))
				this.setRenderFirstList(true);
			if (null != refDataLookUpList && !refDataLookUpList.isEmpty()) {

				if (referenceDataLookupResponse.isRecordsGrtThanMaxSize()) {
					setRecordsGreaterThanMaxSize(true);
				} else {
					setRecordsGreaterThanMaxSize(false);
				}
				Iterator iter = refDataLookUpList.iterator();
				if (iter.hasNext()) {
					Object obj = iter.next();
					if (obj instanceof SubCatalogBO || obj instanceof ItemBO) {
						Collections.sort(refDataLookUpList);
					}
				}
				this.setRefDataLookUpList(refDataLookUpList);
			} else
				this.setRefDataLookUpList(null);
			if (searchCriteriaEntered)
				this.setSearchValueForPopUp("");
		}
		return null;
	}

	/**
	 * @param refDataLookupRecords
	 *            The refDataLookupRecords to set.
	 */
	public void setRefDataLookupRecords(String refDataLookupRecords) {
		this.refDataLookupRecords = refDataLookupRecords;
	}

	/**
	 * 
	 * @return Returns the mandate.
	 *  
	 */

	public String getMandate() {

		return mandate;

	}

	/**
	 * 
	 * @param mandate
	 *            The mandate to set.
	 *  
	 */

	public void setMandate(String mandate) {

		this.mandate = mandate;

	}

	/**
	 * 
	 * @return Returns the standard.
	 *  
	 */

	public String getStandard() {

		return standard;

	}

	/**
	 * @param standard
	 *            The standard to set.
	 */

	public void setStandard(String standard) {
		this.standard = standard;
	}

	/**
	 * @return Returns the systemDomainResultList.
	 */
	public List getSystemDomainResultList() {
		int action = 1;
		String parentCatalog = "System Domain";

		ReferenceDataLookupResponse referenceDataLookupResponse = getComboRefDataResponse(
				action, parentCatalog);
		systemDomainResultList = referenceDataLookupResponse
				.getSubCatalogList();

		return systemDomainResultList;
	}

	/**
	 * 
	 * @param systemDomainResultList
	 *            The systemDomainResultList to set.
	 *  
	 */

	public void setSystemDomainResultList(List systemDomainResultList) {

		this.systemDomainResultList = systemDomainResultList;

	}

	/**
	 * 
	 * Constructor
	 *  
	 */

	public ReferenceDataBackingBean() {

		super();

	}

	/**
	 * 
	 * Returns the standardPlanCode
	 * 
	 * @return List standardPlanCode.
	 *  
	 */

	public List getStandardPlanCode() {

		this.refdataRequest = getRefDataRequest();

		refdataRequest.setPopupId(1701);

		RefDataResponse refDataResponse = (RefDataResponse) this

		.executeService(refdataRequest);

		standardPlanCode = refDataResponse.getList();

		return standardPlanCode;

	}

	/**
	 * 
	 * Sets the standardPlanCode
	 * 
	 * @param standardPlanCode.
	 *  
	 */

	public void setStandardPlanCode(List standardPlanCode) {

		this.standardPlanCode = standardPlanCode;

	}

	/**
	 * 
	 * @return Returns the benefitLevelListPopUp.
	 *  
	 */

	public List getBenefitLevelListPopUp() {

		return benefitLevelListPopUp;

	}

	/**
	 * 
	 * @param benefitLevelListPopUp
	 *            The benefitLevelListPopUp to set.
	 *  
	 */

	public void setBenefitLevelListPopUp(List benefitLevelListPopUp) {

		this.benefitLevelListPopUp = benefitLevelListPopUp;

	}

	/**
	 * 
	 * @return Returns the dataTypeList.
	 *  
	 */

	public List getLobList() {

		this.refdataRequest = getRefDataRequest();

		refdataRequest.setPopupId(1925);

		RefDataResponse refDataResponse = (RefDataResponse) this

		.executeService(refdataRequest);

		lobList = refDataResponse.getList();

		return lobList;

	}

	/**
	 * 
	 * @return Returns the RefDataRequest.
	 *  
	 */

	private RefDataRequest getRefDataRequest() {

		RefDataRequest refdataRequest = (RefDataRequest) this

		.getServiceRequest(ServiceManager.REF_DATA_REQUEST);

		return refdataRequest;

	}

	/**
	 * 
	 * @param lobList
	 *            The lobList to set.
	 *  
	 */

	public void setLobList(List lobList) {

		this.lobList = lobList;

	}

	/**
	 * 
	 * @return Returns the dataTypeList.
	 *  
	 */

	public List getDataTypeList() {
		if (!dataTypeRetrieved) {
			//			List dataTypeList = null;

			DataTypeRequest dataTypeRequest = null;

			DataTypeResponse dataTypeResponse = null;

			//Create the Request Object

			dataTypeRequest = getDataTypeRequest();

			//Call the service method

			dataTypeResponse = (DataTypeResponse) this

			.executeService(dataTypeRequest);

			if (null != dataTypeResponse) {

				dataTypeList = dataTypeResponse.getDataTypesList();
				dataTypeRetrieved = true;

			}
		}
		return dataTypeList;

	}

	/**
	 * 
	 * @return DataTypeRequest
	 *  
	 */

	private DataTypeRequest getDataTypeRequest() {

		DataTypeRequest dataTypeRequest = (DataTypeRequest) this

		.getServiceRequest(ServiceManager.SEARCH_DATATYPE_REQUEST);

		return dataTypeRequest;

	}

	/**
	 * 
	 * @param dataTypeList
	 *            The dataTypeList to set.
	 *  
	 */

	public void setDataTypeList(List dataTypeList) {

		this.dataTypeList = dataTypeList;

	}

	/**
	 * 
	 * Returns the qualifierList
	 * 
	 * 
	 * 
	 * @return List qualifierList.
	 *  
	 */

	public List getQualifierList() {

		//Use termQualifiersList instead of qualifierList.

		List qualifierList = new ArrayList();

		/*
		 * 
		 * qualifierList.add("Individual"); qualifierList.add("Waiver");
		 * 
		 * qualifierList.add("Last Quarter"); qualifierList.add("Family");
		 * 
		 * qualifierList.add("Number Of Days"); qualifierList.add("Dollar
		 * 
		 * Amounts");
		 *  
		 */

		return qualifierList;

	}

	/**
	 * 
	 * Sets the qualifierList
	 * 
	 * 
	 * 
	 * @param qualifierList.
	 *  
	 */

	public void setQualifierList(List qualifierList) {

		this.qualifierList = qualifierList;

	}

	/**
	 * 
	 * Returns the questionList
	 * 
	 * 
	 * 
	 * @return List questionList.
	 *  
	 */

	public List getQuestionList() {

		List questionList = new ArrayList();

		questionList.add("Is Copay waived?");

		questionList.add("Does Copay Add to OOP?");

		return questionList;

	}

	/**
	 * 
	 * Sets the questionList
	 * 
	 * 
	 * 
	 * @param questionList.
	 *  
	 */

	public void setQuestionList(List questionList) {

		this.questionList = questionList;

	}

	/**
	 * 
	 * Returns the refdataRequest
	 * 
	 * 
	 * 
	 * @return RefDataRequest refdataRequest.
	 *  
	 */

	public RefDataRequest getRefdataRequest() {

		return refdataRequest;

	}

	/**
	 * 
	 * Sets the refdataRequest
	 * 
	 * 
	 * 
	 * @param refdataRequest.
	 *  
	 */

	public void setRefdataRequest(RefDataRequest refdataRequest) {

		this.refdataRequest = refdataRequest;

	}

	/**
	 * 
	 * Returns the referenceList
	 * 
	 * 
	 * 
	 * @return List referenceList.
	 *  
	 */

	public List getReferenceList() {

		List referenceList = new ArrayList();

		/*
		 * 
		 * referenceList.add("XREF_CLM"); referenceList.add("XREF_CLP");
		 *  
		 */

		return referenceList;

	}

	/**
	 * 
	 * Sets the referenceList
	 * 
	 * 
	 * 
	 * @param referenceList.
	 *  
	 */

	public void setReferenceList(List referenceList) {

		this.referenceList = referenceList;

	}

	/**
	 * 
	 * Returns the termList
	 * 
	 * 
	 * 
	 * @return List termList.
	 *  
	 */

	public List getTermList() {

		//Use the termResultList instead of termList.

		List termList = new ArrayList();

		/*
		 * 
		 * termList.add("Copay"); termList.add("Deductable");
		 * 
		 * termList.add("Payment Percent"); termList.add("Stop Loss");
		 *  
		 */

		return termList;

	}

	/**
	 * 
	 * Sets the termList
	 * 
	 * 
	 * 
	 * @param termList.
	 *  
	 */

	public void setTermList(List termList) {

		this.termList = termList;

	}

	/**
	 * 
	 * @return Returns the busEntityList.
	 *  
	 */

	public List getBusEntityList() {

		this.refdataRequest = getRefDataRequest();

		refdataRequest.setPopupId(1932);

		RefDataResponse refDataResponse = (RefDataResponse) this

		.executeService(refdataRequest);

		busEntityList = refDataResponse.getList();

		return busEntityList;

	}

	/**
	 * 
	 * @param busEntityList
	 *            The busEntityList to set.
	 *  
	 */

	public void setBusEntityList(List busEntityList) {

		this.busEntityList = busEntityList;

	}

	/**
	 * 
	 * @return Returns the busGroupList.
	 *  
	 */

	public List getBusGroupList() {

		this.refdataRequest = getRefDataRequest();

		refdataRequest.setPopupId(1933);

		RefDataResponse refDataResponse = (RefDataResponse) this

		.executeService(refdataRequest);

		busGroupList = refDataResponse.getList();

		return busGroupList;

	}

	/**
	 * 
	 * @param busGroupList
	 *            The busGroupList to set.
	 *  
	 */

	public void setBusGroupList(List busGroupList) {

		this.busGroupList = busGroupList;

	}

	/**
	 * 
	 * Returns the referenceResultList
	 * 
	 * 
	 * 
	 * @return List referenceResultList.
	 *  
	 */

	public List getReferenceResultList() {

		this.refdataRequest = getRefDataRequest();

		refdataRequest.setPopupId(1938);

		RefDataResponse refDataResponse = (RefDataResponse) this

		.executeService(refdataRequest);

		referenceResultList = refDataResponse.getList();

		return referenceResultList;

	}

	private List refDataLookUpList;

	private List refDataCatalogList;

	private List qualifierDataLookUpList;

	/**
	 * 
	 * @return refDataCatalogList
	 * 
	 * Returns the refDataCatalogList.
	 *  
	 */

	public List getRefDataCatalogList() {

		return refDataCatalogList;

	}

	/**
	 * 
	 * @param refDataCatalogList
	 * 
	 * Sets the refDataCatalogList.
	 *  
	 */

	public void setRefDataCatalogList(List refDataCatalogList) {

		this.refDataCatalogList = refDataCatalogList;

	}

	/**
	 * 
	 * Method to get the reference data for lookup
	 * 
	 * @return refDataLookUpList
	 * 
	 * Returns the refDataLookUpList.
	 *  
	 */
	public List getRefDataLookUpList() {
		String searchString = this.getRequest().getParameter(
				WebConstants.FILTER_SEARCH_STRING);
		String catalogName = this.getRequest().getParameter(
				WebConstants.FILTER_PARENT_CATALOG);
		String lookUpAction = this.getRequest().getParameter(
				WebConstants.FILTER_LOOKUP_ACTION);
		String lob = this.getRequest().getParameter(
				WebConstants.FILTER_LINE_OF_BUSINESS);
		String be = this.getRequest().getParameter(
				WebConstants.FILTER_BUSINESS_ENTITY);
		String bg = this.getRequest().getParameter(
				WebConstants.FILTER_BUSINESS_GROUP);
		String entityType = this.getRequest().getParameter(
				WebConstants.FILTER_ENTITY_TYPE);
		if (null != searchString && index == 0) {
			this.lookUpAction = lookUpAction;
			this.catalogNameForTermQualifier = catalogName;
			this.lob = lob;
			this.businessEntity = be;
			this.businessGroup = bg;
			this.searchValueForPopUp = (searchString.trim()).toUpperCase();
			this.entityType = entityType;
			getSearchTermQualifier();
			index++;
		}
		return refDataLookUpList;
	}

	/**
	 * 
	 * @param refDataLookUpList
	 * 
	 * Sets the refDataLookUpList.
	 *  
	 */

	public void setRefDataLookUpList(List refDataLookUpList) {

		this.refDataLookUpList = refDataLookUpList;

	}

	private ReferenceDataLookupRequest getFilterReferenceDataLookupRequest()

	{
		String parentCatalog = this.catalogNameHidden;

		int action = this.lookUpActionHidden;
		SubCatalogVO subCatalogVO = new SubCatalogVO();
		ReferenceDataLookupRequest referenceDataLookupRequest = (ReferenceDataLookupRequest) this
				.getServiceRequest(ServiceManager.REF_DATA_LOOK_UP_REQUEST);
		if (null != this.searchString) {
			String newSearchString = WPDStringUtil
					.escapeString(this.searchString);
			referenceDataLookupRequest.setSearchString((newSearchString.trim()
					.toUpperCase()));
		}
		if (action == 1 || action == 6 || action == 10)

		{

			subCatalogVO.setParentCatalog(parentCatalog);
			if (action == 10) {
				String headerRuleId = getRequest().getParameter("headerRuleId");
				referenceDataLookupRequest.setHeaderRuleId(headerRuleId);
				referenceDataLookupRequest.setAction(10);
			} else {
				referenceDataLookupRequest.setAction(1);
			}

		}

		else if (action == 2)

		{

			String lob = getRequest().getParameter("lob");

			lob = lob.replaceAll("/a", "&");

			String businessEntity = getRequest().getParameter("be");

			businessEntity = businessEntity.replaceAll("/a", "&");

			String businessGroup = getRequest().getParameter("bg");

			businessGroup = businessGroup.replaceAll("/a", "&");

			List lobList = WPDStringUtil.getListFromTildaString(lob, 2, 2, 2);

			List businessEntityList = WPDStringUtil.getListFromTildaString(
					businessEntity, 2, 2, 2);

			List businessGroupList = WPDStringUtil.getListFromTildaString(
					businessGroup, 2, 2, 2);

			subCatalogVO.setBeList(businessEntityList);

			subCatalogVO.setBgList(businessGroupList);

			subCatalogVO.setLobList(lobList);

			subCatalogVO.setParentCatalog(parentCatalog);

			referenceDataLookupRequest.setAction(2);

		}

		else if (action == 3) {

			int entityId = Integer.parseInt(getRequest().getParameter(
					"entityId"));

			String entityType = getRequest().getParameter("entityType");

			subCatalogVO.setEntityId(entityId);

			subCatalogVO.setEntityType(entityType);

			referenceDataLookupRequest.setAction(3);

		}

		else if (action == 5) {

			int entityId = Integer.parseInt(getRequest().getParameter(
					"entityId"));

			String entityType = getRequest().getParameter("entityType");

			if (null != entityType)
				this.setEntityType(entityType);

			String pva = getRequest().getParameter("pva");

			String term = getRequest().getParameter("term");

			String catalogNameForPva = getRequest().getParameter("catalogName");

			subCatalogVO.setEntityId(entityId);

			this.setEntityId(entityId);

			subCatalogVO.setEntityType(entityType);

			subCatalogVO.setBenefitLevelTerm(term);

			subCatalogVO.setBenefitLevelPVA(pva);

			subCatalogVO.setCatalogNameForPva(catalogNameForPva);

			referenceDataLookupRequest.setAction(5);

		} else if (action == 7) {
			subCatalogVO.setParentCatalog(parentCatalog);

			referenceDataLookupRequest.setAction(7);
		} else if (action == 27) {
			subCatalogVO.setParentCatalog(parentCatalog);

			referenceDataLookupRequest.setAction(27);
		}

		else if (action == 8) {
			subCatalogVO.setParentCatalog(parentCatalog);

			referenceDataLookupRequest.setAction(8);
		} else if (action == 9) {
			subCatalogVO.setParentCatalog(parentCatalog);

			referenceDataLookupRequest.setAction(9);
		}

		subCatalogVO.setParentCatalog(parentCatalog);

		referenceDataLookupRequest.setSubCatalogVO(subCatalogVO);
		return referenceDataLookupRequest;
	}

	private ReferenceDataLookupRequest getReferenceDataLookupRequest()

	{

		String parentCatalog = getRequest().getParameter("parentCatalog");

		int action = Integer
				.parseInt(getRequest().getParameter("lookUpAction"));

		SubCatalogVO subCatalogVO = new SubCatalogVO();

		ReferenceDataLookupRequest referenceDataLookupRequest = (ReferenceDataLookupRequest)

		this.getServiceRequest(ServiceManager.REF_DATA_LOOK_UP_REQUEST);

		if (action == 1 || action == 6 || action == 10 || action == 99)

		{

			subCatalogVO.setParentCatalog(parentCatalog);
			if (action == 10) {
				String headerRuleId = getRequest().getParameter("headerRuleId");
				referenceDataLookupRequest.setHeaderRuleId(headerRuleId);
				referenceDataLookupRequest.setAction(10);
			} else if (action == 99) {
				referenceDataLookupRequest.setAction(99);
			} else {
				referenceDataLookupRequest.setAction(1);
			}

		}

		else if (action == 2)

		{

			String lob = getRequest().getParameter("lob");

			lob = lob.replaceAll("/a", "&");

			String businessEntity = getRequest().getParameter("be");

			businessEntity = businessEntity.replaceAll("/a", "&");

			String businessGroup = getRequest().getParameter("bg");

			businessGroup = businessGroup.replaceAll("/a", "&");

			List lobList = WPDStringUtil.getListFromTildaString(lob, 2, 2, 2);

			List businessEntityList = WPDStringUtil.getListFromTildaString(
					businessEntity, 2, 2, 2);

			List businessGroupList = WPDStringUtil.getListFromTildaString(
					businessGroup, 2, 2, 2);

			subCatalogVO.setBeList(businessEntityList);

			subCatalogVO.setBgList(businessGroupList);

			subCatalogVO.setLobList(lobList);

			subCatalogVO.setParentCatalog(parentCatalog);

			referenceDataLookupRequest.setAction(2);

		}

		else if (action == 3) {

			int entityId = Integer.parseInt(getRequest().getParameter(
					"entityId"));

			String entityType = getRequest().getParameter("entityType");

			subCatalogVO.setEntityId(entityId);

			subCatalogVO.setEntityType(entityType);

			referenceDataLookupRequest.setAction(3);

		}

		else if (action == 5) {

			int entityId = Integer.parseInt(getRequest().getParameter(
					"entityId"));

			String entityType = getRequest().getParameter("entityType");

			if (null != entityType)
				this.setEntityType(entityType);

			String pva = getRequest().getParameter("pva");

			String term = getRequest().getParameter("term");

			String catalogNameForPva = getRequest().getParameter("catalogName");

			subCatalogVO.setEntityId(entityId);

			this.setEntityId(entityId);

			subCatalogVO.setEntityType(entityType);

			subCatalogVO.setBenefitLevelTerm(term);

			subCatalogVO.setBenefitLevelPVA(pva);

			subCatalogVO.setCatalogNameForPva(catalogNameForPva);

			referenceDataLookupRequest.setAction(5);

		} else if (action == 7) {
			subCatalogVO.setParentCatalog(parentCatalog);

			referenceDataLookupRequest.setAction(7);
		} else if (action == 27) {
			subCatalogVO.setParentCatalog(parentCatalog);

			referenceDataLookupRequest.setAction(27);
		}

		else if (action == 8) {
			subCatalogVO.setParentCatalog(parentCatalog);

			referenceDataLookupRequest.setAction(8);
		} else if (action == 9) {
			subCatalogVO.setParentCatalog(parentCatalog);
			referenceDataLookupRequest.setAction(9);
		} else if (action == 17) {
			subCatalogVO.setParentCatalog(parentCatalog);
			String searchString = getRequest().getParameter("searchCriteria");
			subCatalogVO.setSearchText(searchString);
			referenceDataLookupRequest.setAction(17);
		} else if (action == 31)

		{

			subCatalogVO.setParentCatalog(parentCatalog);
			referenceDataLookupRequest.setAction(31);

		}

		subCatalogVO.setParentCatalog(parentCatalog);

		referenceDataLookupRequest.setSubCatalogVO(subCatalogVO);

		return referenceDataLookupRequest;

	}

	private List refDataComboList;

	/**
	 * 
	 * @return refDataComboList
	 * 
	 * Returns the refDataComboList.
	 *  
	 */

	public List getRefDataComboList() {

		int action = 1;

		String parentCatalog = "";

		ReferenceDataLookupResponse referenceDataLookupResponse = getComboRefDataResponse(
				action, parentCatalog);

		if (null != referenceDataLookupResponse.getSubCatalogList() &&

		!referenceDataLookupResponse.getSubCatalogList().isEmpty()) {

			refDataComboList = referenceDataLookupResponse.getSubCatalogList();

		} else {

			refDataComboList = null;

		}

		return refDataComboList;

	}

	/**
	 * 
	 * Method to get the list from the
	 * 
	 * @param parentCatalog
	 * 
	 * @param action
	 * 
	 * @return ReferenceDataLookupResponse
	 *  
	 */

	private ReferenceDataLookupResponse getComboRefDataResponse(int action,
			String parentCatalog) {

		// Get the request object

		ReferenceDataLookupRequest referenceDataLookupRequest = (ReferenceDataLookupRequest)

		this.getServiceRequest(ServiceManager.REF_DATA_LOOK_UP_REQUEST);

		// Create an instance of SubCatalogVO

		SubCatalogVO subCatalogVO = new SubCatalogVO();

		// Set the value of action in the request

		referenceDataLookupRequest.setAction(action);

		// Set the parent Catalog name

		subCatalogVO.setParentCatalog(parentCatalog);

		// Set the VO in the request

		referenceDataLookupRequest.setSubCatalogVO(subCatalogVO);

		// Get the response

		ReferenceDataLookupResponse referenceDataLookupResponse = (ReferenceDataLookupResponse)

		this.executeService(referenceDataLookupRequest);

		// Return the Response

		return referenceDataLookupResponse;

	}

	/**
	 * 
	 * @param refDataComboList
	 * 
	 * Sets the refDataComboList.
	 *  
	 */

	public void setRefDataComboList(List refDataComboList) {

		this.refDataComboList = refDataComboList;

	}

	/**
	 * 
	 * Sets the referenceResultList
	 * 
	 * 
	 * 
	 * @param referenceResultList.
	 *  
	 */

	public void setReferenceResultList(List referenceResultList) {

		this.referenceResultList = referenceResultList;

	}

	/**
	 * 
	 * @return Returns the termResultList.
	 *  
	 */

	public List getTermResultList() {

		this.refdataRequest = getRefDataRequest();

		refdataRequest.setPopupId(1934);

		RefDataResponse refDataResponse = (RefDataResponse) this

		.executeService(refdataRequest);

		termResultList = refDataResponse.getList();

		return termResultList;

	}

	/**
	 * 
	 * @param termResultList
	 *            The termResultList to set.
	 *  
	 */

	public void setTermResultList(List termResultList) {

		this.termResultList = termResultList;

	}

	/**
	 * 
	 * @return Returns the stateResultList.
	 *  
	 */

	public List getStateResultList() {

		this.refdataRequest = getRefDataRequest();

		refdataRequest.setPopupId(1626);

		RefDataResponse refDataResponse = (RefDataResponse) this

		.executeService(refdataRequest);

		stateResultList = refDataResponse.getList();

		return stateResultList;

	}

	/**
	 * 
	 * @param stateResultList
	 *            The termResultList to set.
	 *  
	 */

	public void setStateResultList(List stateResultList) {

		this.stateResultList = stateResultList;

	}

	/**
	 * 
	 * @return Returns the ruleResultList.
	 *  
	 */

	public List getRuleResultList() {

		this.refdataRequest = getRefDataRequest();

		refdataRequest.setPopupId(1938);

		RefDataResponse refDataResponse = (RefDataResponse) this

		.executeService(refdataRequest);

		ruleResultList = refDataResponse.getList();

		return ruleResultList;

	}

	/**
	 * 
	 * @param ruleResultList
	 *            The termResultList to set.
	 *  
	 */

	public void setRuleResultList(List ruleResultList) {

		this.ruleResultList = ruleResultList;

	}

	/**
	 * 
	 * @return Returns the termQualifiersList.
	 *  
	 */

	public List getTermQualifiersList() {

		this.refdataRequest = getRefDataRequest();

		refdataRequest.setPopupId(1935);

		RefDataResponse refDataResponse = (RefDataResponse) this

		.executeService(refdataRequest);

		termQualifiersList = refDataResponse.getList();

		return termQualifiersList;

	}

	/**
	 * 
	 * @param termQualifiersList
	 *            The termQualifiersList to set.
	 *  
	 */

	public void setTermQualifiersList(List termQualifiersList) {

		this.termQualifiersList = termQualifiersList;

	}

	/**
	 * 
	 * @return Returns the providerArrangementsList.
	 *  
	 */

	public List getProviderArrangementsList() {

		this.refdataRequest = getRefDataRequest();

		refdataRequest.setPopupId(1936);

		RefDataResponse refDataResponse = (RefDataResponse) this

		.executeService(refdataRequest);

		providerArrangementsList = refDataResponse.getList();

		return providerArrangementsList;

	}

	/**
	 * 
	 * @param providerArrangementsList
	 *            The providerArrangementsList to set.
	 *  
	 */

	public void setProviderArrangementsList(List providerArrangementsList) {

		this.providerArrangementsList = providerArrangementsList;

	}

	/**
	 * 
	 * @return Returns the dataTypeList.
	 *  
	 */

	public List getDataTypeListForCombo() {
		List dataTypeFetchList = null;
		dataTypeFetchList = this.getDataTypeList();
		dataTypeListForCombo = new ArrayList();
		dataTypeListForCombo.add(new SelectItem(new Integer(-1), "--Select--"));
		if (null != dataTypeFetchList) {
			Iterator itr = dataTypeFetchList.iterator();
			while (itr.hasNext()) {
				DataTypeLocateResult dataTypeLocateResult = (DataTypeLocateResult) itr
						.next();
				dataTypeListForCombo.add(new SelectItem(new Integer(
						dataTypeLocateResult.getDataTypeId()),
				//                        dataTypeLocateResult.getDataTypeName()));
						dataTypeLocateResult.getDataTypeLgnd()));
			}
		}
		return dataTypeListForCombo;
	}

	/**
	 * 
	 * @param dataTypeList
	 *            The dataTypeList to set.
	 *  
	 */

	public void setDataTypeListForCombo(List dataTypeListForCombo) {

		this.dataTypeListForCombo = dataTypeListForCombo;

	}

	/**
	 * 
	 * @return Returns the productFamilyList.
	 *  
	 */

	public List getProductFamilyList() {

		this.refdataRequest = getRefDataRequest();

		refdataRequest.setPopupId(1937);

		RefDataResponse refDataResponse = (RefDataResponse) this

		.executeService(refdataRequest);

		productFamilyList = refDataResponse.getList();

		return productFamilyList;

	}

	/**
	 * 
	 * @param productFamilyList
	 *            The productFamilyList to set.
	 *  
	 */

	public void setProductFamilyList(List productFamilyList) {

		this.productFamilyList = productFamilyList;

	}

	/**
	 * 
	 * Returns the String
	 * 
	 * @return List questionSearchResultList.
	 *  
	 */
	public String searchQuestions() {

		List questionSearchList = null;

		LookupAdminQuestionRequest lookupAdminQuestionRequest = null;

		LookupAdminQuestionResponse lookupAdminQuestionResponse = null;

		// Create the Request Object

		lookupAdminQuestionRequest = getLookupAdminQuestionRequest();

		// Call the service method

		lookupAdminQuestionResponse = (LookupAdminQuestionResponse) this
				.executeService(lookupAdminQuestionRequest);

		if (null != lookupAdminQuestionResponse) {

			questionSearchList = lookupAdminQuestionResponse
					.getAdminOptionQuestionList();

			this.setQuestionSearchResultList(questionSearchList);

		}

		return WebConstants.EMPTY_STRING;
	}

	/**
	 * 
	 * Returns the questionSearchResultList
	 * 
	 * @return List questionSearchResultList.
	 *  
	 */

	public List getQuestionSearchResultList() {

		return questionSearchResultList;

	}

	/**
	 * 
	 * Sets the questionSearchResultList
	 * 
	 * @param questionSearchResultList.
	 *  
	 */

	public void setQuestionSearchResultList(List questionSearchResultList) {

		this.questionSearchResultList = questionSearchResultList;

	}

	/**
	 * 
	 * Returns the lookupAdminQuestionRequest
	 * 
	 * @return LookupAdminQuestionRequest lookupAdminQuestionRequest.
	 *  
	 */

	public LookupAdminQuestionRequest getLookupAdminQuestionRequest() {

		LookupAdminQuestionRequest lookupAdminQuestionRequest = (LookupAdminQuestionRequest) this
				.getServiceRequest(ServiceManager.ADMIN_QUESTION_REQUEST);

		String adminQuestionAutoCompleteValue = this.searchValueForPopUp;
		lookupAdminQuestionRequest.getLookupAdminQuestionVO()
				.setCriteriaQuestion(adminQuestionAutoCompleteValue);
		lookupAdminQuestionRequest.getLookupAdminQuestionVO().setAdminOptionId(
				this.adminId);
		return lookupAdminQuestionRequest;

	}

	/**
	 * 
	 * Returns the questionCriteria
	 * 
	 * @return String questionCriteria.
	 *  
	 */

	public String getQuestionCriteria() {

		return questionCriteria;

	}

	/**
	 * 
	 * Sets the questionCriteria
	 * 
	 * @param questionCriteria.
	 *  
	 */

	public void setQuestionCriteria(String questionCriteria) {

		this.questionCriteria = questionCriteria;

	}

	/**
	 * 
	 * @return Returns the adminOptionsList.
	 *  
	 */

	public List getAdminOptionsList() {

		List AdminOptionsList = null;

		LookupAdminOptionRequest lookupAdminOptionRequest = null;

		LookupAdminOptionResponse lookupAdminOptionResponse = null;

		lookupAdminOptionRequest = getLookupAdminOptionRequest();

		lookupAdminOptionResponse = (LookupAdminOptionResponse) this
				.executeService(lookupAdminOptionRequest);

		if (null != lookupAdminOptionResponse) {

			adminOptionsList = lookupAdminOptionResponse.getLookupAdminOption();

			this.setAdminOptionsList(adminOptionsList);

		}

		return adminOptionsList;

	}

	/**
	 * 
	 * @param adminOptionsList
	 *            The adminOptionsList to set.
	 *  
	 */

	public void setAdminOptionsList(List adminOptionsList) {

		this.adminOptionsList = adminOptionsList;

	}

	public LookupAdminOptionRequest getLookupAdminOptionRequest() {

		LookupAdminOptionRequest lookupAdminOptionRequest = (LookupAdminOptionRequest) this
				.getServiceRequest(ServiceManager.LOOK_UP_ADMIN_OPTION_LIST);

		lookupAdminOptionRequest
				.setMaxSearchResultSize(WebConstants.MAX_SEARCH_RESULT_SIZE);

		return lookupAdminOptionRequest;

	}

	/**
	 * 
	 * @return Returns the administrationList.
	 *  
	 */

	public List getAdministrationList() {

		List administrationListForCombo = new ArrayList();

		AdminLevelRequest request = (AdminLevelRequest) this
				.getServiceRequest(ServiceManager.ADMIN_LVL_REQUEST);

		AdminLevelResponse response = (AdminLevelResponse) executeService(request);

		// TODO Uncomment the below lines when table is ready

		administrationList = response.getAdminLevelList();

		Iterator administrationListIterator = administrationList.iterator();

		while (administrationListIterator.hasNext()) {

			AdminLevelLocateCriteria impl = (AdminLevelLocateCriteria) administrationListIterator
					.next();

			administrationListForCombo.add(new SelectItem(new Integer(impl
					.getAdminLevelId()).toString(), impl.getAdminLevelDesc()));

		}

		return administrationListForCombo;

	}

	/**
	 * 
	 * @param administrationList
	 *            The administrationList to set.
	 *  
	 */

	public void setAdministrationList(List administrationList) {

		this.administrationList = administrationList;

	}

	/**
	 * 
	 * @return Returns the fundingArrangementListForCombo.
	 *  
	 */

	public List getFundingArrangementListForCombo() {

		List fundingArrangementList = new ArrayList();

		fundingArrangementListForCombo = new ArrayList();

		int action = 1;

		String parentCatalog = "Funding Arrangement";

		ReferenceDataLookupResponse referenceDataLookupResponse = getComboRefDataResponse(
				action, parentCatalog);

		fundingArrangementList = referenceDataLookupResponse
				.getSubCatalogList();

		Iterator fundingArrangementIterator = fundingArrangementList.iterator();

		fundingArrangementListForCombo.add(new SelectItem(""));

		while (fundingArrangementIterator.hasNext()) {

			SubCatalogBO fundingResult = (SubCatalogBO) fundingArrangementIterator
					.next();

			fundingArrangementListForCombo.add(new SelectItem(fundingResult
					.getPrimaryCode(), fundingResult.getDescription()));

		}

		return fundingArrangementListForCombo;

	}

	public List getEntityTypeListForCombo() {

		List entityList = new ArrayList();

		entityTypeListForCombo = new ArrayList();

		int action = 1;

		String parentCatalog = "Entity Sub Types";

		ReferenceDataLookupResponse referenceDataLookupResponse = getComboRefDataResponse(
				action, parentCatalog);

		entityList = referenceDataLookupResponse.getSubCatalogList();

		Iterator entityTypeIterator = entityList.iterator();
		List benefitType = new ArrayList();

		while (entityTypeIterator.hasNext()) {

			SubCatalogBO entityTypeResult = (SubCatalogBO) entityTypeIterator
					.next();

			if (entityTypeResult.getPrimaryCode().equals("STANDARD")) {
				benefitType.add(new SelectItem(entityTypeResult
						.getPrimaryCode(), entityTypeResult.getDescription()));
				if (entityTypeListForCombo.size() > 0) {
					Iterator iterator = entityTypeListForCombo.iterator();
					while (iterator.hasNext()) {
						SelectItem entityType = (SelectItem) iterator.next();
						benefitType.add(entityType);
					}
				}
				return benefitType;

			}

			entityTypeListForCombo.add(new SelectItem(entityTypeResult
					.getPrimaryCode(), entityTypeResult.getDescription()));

		}

		return entityTypeListForCombo;
	}

	public List getEntityTypeListForBenefitCombo() {

		List entityList = new ArrayList();

		entityList = this.getEntityTypeListForCombo();

		if (entityList.size() > 1)
			entityList.add(new SelectItem("ALL", "ALL"));

		return entityList;

	}

	public List getEntityTypeListForSearch() {

		List entityList = new ArrayList();

		entityTypeListForCombo = new ArrayList();

		int action = 1;

		String parentCatalog = "Entity Sub Types";

		ReferenceDataLookupResponse referenceDataLookupResponse = getComboRefDataResponse(
				action, parentCatalog);

		entityList = referenceDataLookupResponse.getSubCatalogList();

		Iterator entityTypeIterator = entityList.iterator();
		List benefitType = new ArrayList();

		while (entityTypeIterator.hasNext()) {

			SubCatalogBO entityTypeResult = (SubCatalogBO) entityTypeIterator
					.next();

			entityTypeListForCombo.add(entityTypeResult.getPrimaryCode());

		}

		return entityTypeListForCombo;
	}

	/**
	 * 
	 * @param fundingArrangementListForCombo
	 *            The fundingArrangementListForCombo to set.
	 *  
	 */

	public void setFundingArrangementListForCombo(

	List fundingArrangementListForCombo) {

		this.fundingArrangementListForCombo = fundingArrangementListForCombo;

	}

	/**
	 * 
	 * @return Returns the groupSizeList.
	 *  
	 */

	public List getgroupSizeList() {

		groupSizeList = new ArrayList();

		this.refdataRequest = getRefDataRequest();

		refdataRequest.setPopupId(1940);

		RefDataResponse refDataResponse = (RefDataResponse) this

		.executeService(refdataRequest);

		groupSizeList = refDataResponse.getList();

		return groupSizeList;

	}

	/**
	 * 
	 * @param groupSizeList
	 *            The groupSizeList to set.
	 *  
	 */

	public void setgroupSizeList(List groupSizeList) {

		this.groupSizeList = groupSizeList;

	}

	/**
	 * 
	 * @return Returns the groupSizeListForCombo.
	 *  
	 */

	public List getGroupSizeListForCombo() {

		groupSizeListForCombo = new ArrayList();

		List groupSizeList = new ArrayList();

		int action = 1;

		String parentCatalog = "Group Size";

		ReferenceDataLookupResponse referenceDataLookupResponse = getComboRefDataResponse(
				action, parentCatalog);

		if (null != referenceDataLookupResponse.getSubCatalogList() &&

		!referenceDataLookupResponse.getSubCatalogList().isEmpty()) {

			refDataComboList = referenceDataLookupResponse.getSubCatalogList();

		} else {

			refDataComboList = null;

		}

		groupSizeList = referenceDataLookupResponse.getSubCatalogList();

		Iterator groupSizeListIterator = groupSizeList.iterator();

		groupSizeListForCombo.add(new SelectItem(""));

		while (groupSizeListIterator.hasNext()) {

			SubCatalogBO groupSizeResult = (SubCatalogBO) groupSizeListIterator
					.next();

			groupSizeListForCombo.add(new SelectItem(groupSizeResult
					.getPrimaryCode(), groupSizeResult.getDescription()));

		}

		return groupSizeListForCombo;

	}

	/**
	 * 
	 * @param groupSizeListForCombo
	 *            The groupSizeListForCombo to set.
	 *  
	 */

	public void setGroupSizeListForCombo(List groupSizeListForCombo) {

		this.groupSizeListForCombo = groupSizeListForCombo;

	}

	/**
	 * 
	 * @return Returns the mandateTypeListForCombo.
	 *  
	 */

	public List getMandateTypeListForCombo() {

		List mandateTypeList = new ArrayList();

		mandateTypeListForCombo = new ArrayList();

		int action = 1;

		String parentCatalog = "Mandate Type";

		ReferenceDataLookupResponse referenceDataLookupResponse = getComboRefDataResponse(
				action, parentCatalog);

		mandateTypeList = referenceDataLookupResponse.getSubCatalogList();

		Iterator mandateTypeListIterator = mandateTypeList.iterator();

		mandateTypeListForCombo.add(new SelectItem(""));

		while (mandateTypeListIterator.hasNext()) {

			SubCatalogBO mandateTypeResult = (SubCatalogBO) mandateTypeListIterator
					.next();

			mandateTypeListForCombo.add(new SelectItem(mandateTypeResult
					.getPrimaryCode(), mandateTypeResult.getDescription()));

		}

		return mandateTypeListForCombo;

	}

	/**
	 * @return Returns the benefitCategoryListForCombo.
	 */
	public List getBenefitCategoryListForCombo() {

		List benefitCategoryList = new ArrayList();

		benefitCategoryListForCombo = new ArrayList();

		int action = 1;

		String parentCatalog = "BENEFIT CATEGORY";

		ReferenceDataLookupResponse referenceDataLookupResponse = getComboRefDataResponse(
				action, parentCatalog);

		benefitCategoryList = referenceDataLookupResponse.getSubCatalogList();

		Iterator benefitCategoryListIterator = benefitCategoryList.iterator();

		benefitCategoryListForCombo.add(new SelectItem(""));

		while (benefitCategoryListIterator.hasNext()) {

			SubCatalogBO mandateTypeResult = (SubCatalogBO) benefitCategoryListIterator
					.next();

			benefitCategoryListForCombo.add(new SelectItem(mandateTypeResult
					.getPrimaryCode(), mandateTypeResult.getDescription()));

		}

		return benefitCategoryListForCombo;
	}

	/**
	 * 
	 * @param mandateTypeListForCombo
	 *            The mandateTypeListForCombo to set.
	 *  
	 */

	public void setMandateTypeListForCombo(List mandateTypeListForCombo) {

		this.mandateTypeListForCombo = mandateTypeListForCombo;

	}

	/**
	 * 
	 * @return Returns the jurisdictionResultList.
	 *  
	 */

	public List getJurisdictionResultList() {

		this.refdataRequest = getRefDataRequest();

		refdataRequest.setPopupId(1942);

		RefDataResponse refDataResponse = (RefDataResponse) this

		.executeService(refdataRequest);

		jurisdictionResultList = refDataResponse.getList();

		return jurisdictionResultList;

	}

	/**
	 * 
	 * @param jurisdictionResultList
	 *            The jurisdictionResultList to set.
	 *  
	 */

	public void setJurisdictionResultList(List jurisdictionResultList) {

		this.jurisdictionResultList = jurisdictionResultList;

	}

	/**
	 * 
	 * @return Returns the noteTypeListForCombo.
	 *  
	 */

	public List getNoteTypeListForCombo() {

		noteTypeListForCombo = new ArrayList();

		List noteTypeList = new ArrayList();

		int action = 1;

		String parentCatalog = "Note Type";

		ReferenceDataLookupResponse referenceDataLookupResponse = getComboRefDataResponse(
				action, parentCatalog);

		noteTypeList = referenceDataLookupResponse.getSubCatalogList();

		Iterator noteTypeListIterator = noteTypeList.iterator();

		noteTypeListForCombo.add(new SelectItem(""));

		while (noteTypeListIterator.hasNext()) {

			SubCatalogBO noteTypeResult = (SubCatalogBO) noteTypeListIterator
					.next();

			noteTypeListForCombo.add(new SelectItem(noteTypeResult
					.getPrimaryCode(), noteTypeResult.getDescription()));

		}

		this.getSession().setAttribute("noteTypeList", noteTypeListForCombo);

		return noteTypeListForCombo;

	}

	/**
	 * 
	 * @param noteTypeListForCombo
	 *            The noteTypeListForCombo to set.
	 *  
	 */

	public void setNoteTypeListForCombo(List noteTypeListForCombo) {

		this.noteTypeListForCombo = noteTypeListForCombo;

	}

	/**
	 * 
	 * Returns the corporatePlanList
	 * 
	 * @return List corporatePlanList.
	 *  
	 */

	public List getCorporatePlanList() {

		this.refdataRequest = getRefDataRequest();

		refdataRequest.setPopupId(1947);

		RefDataResponse refDataResponse = (RefDataResponse) this

		.executeService(refdataRequest);

		corporatePlanList = refDataResponse.getList();

		return corporatePlanList;

	}

	/**
	 * 
	 * Sets the corporatePlanList
	 * 
	 * @param corporatePlanList.
	 *  
	 */

	public void setCorporatePlanList(List corporatePlanList) {

		this.corporatePlanList = corporatePlanList;

	}

	/**
	 * 
	 * Returns the fundingArrangementList
	 * 
	 * @return List fundingArrangementList.
	 *  
	 */

	public List getFundingArrangementList() {

		fundingArrangementList = new ArrayList();

		this.refdataRequest = getRefDataRequest();

		refdataRequest.setPopupId(1941);

		RefDataResponse refDataResponse = (RefDataResponse) this

		.executeService(refdataRequest);

		fundingArrangementList = refDataResponse.getList();

		return fundingArrangementList;

	}

	/**
	 * 
	 * Sets the fundingArrangementList
	 * 
	 * @param fundingArrangementList.
	 *  
	 */

	public void setFundingArrangementList(List fundingArrangementList) {

		this.fundingArrangementList = fundingArrangementList;

	}

	/**
	 * 
	 * Returns the headQuartersStates
	 * 
	 * @return List headQuartersStates.
	 *  
	 */

	public List getHeadQuartersStates() {

		this.refdataRequest = getRefDataRequest();

		refdataRequest.setPopupId(1626);

		RefDataResponse refDataResponse = (RefDataResponse) this

		.executeService(refdataRequest);

		headQuartersStates = refDataResponse.getList();

		return headQuartersStates;

	}

	/**
	 * 
	 * Sets the headQuartersStates
	 * 
	 * @param headQuartersStates.
	 *  
	 */

	public void setHeadQuartersStates(List headQuartersStates) {

		this.headQuartersStates = headQuartersStates;

	}

	/**
	 * 
	 * Returns the productCodes
	 * 
	 * @return List productCodes.
	 *  
	 */

	public List getProductCodes() {

		this.refdataRequest = getRefDataRequest();

		refdataRequest.setPopupId(1786);

		RefDataResponse refDataResponse = (RefDataResponse) this

		.executeService(refdataRequest);

		productCodes = refDataResponse.getList();

		return productCodes;

	}

	/**
	 * 
	 * Sets the productCodes
	 * 
	 * @param productCodes.
	 *  
	 */

	public void setProductCodes(List productCodes) {

		this.productCodes = productCodes;

	}

	public List getPricingList() {

		pricingList = new ArrayList();

		try

		{

			this.refdataRequest = getRefDataRequest();

			refdataRequest.setPopupId(1696);

			RefDataResponse refDataResponse = (RefDataResponse) this

			.executeService(refdataRequest);

			pricingList = refDataResponse.getList();

		}

		catch (Exception serviceException) {

			Logger.logInfo(serviceException);

		}

		return pricingList;

	}

	/**
	 * 
	 * @param pricingList
	 *            The pricingList to set.
	 *  
	 */

	public void setPricingList(List pricingList) {

		this.pricingList = pricingList;

	}

	/**
	 * 
	 * @return Returns the networkList.
	 *  
	 */

	public List getNetworkList() {

		networkList = new ArrayList();

		try

		{

			this.refdataRequest = getRefDataRequest();

			refdataRequest.setPopupId(1749);

			RefDataResponse refDataResponse = (RefDataResponse) this

			.executeService(refdataRequest);

			networkList = refDataResponse.getList();

		} catch (Exception serviceException) {

			Logger.logInfo(serviceException);

		}

		return networkList;

	}

	/**
	 * 
	 * @param networkList
	 *            The networkList to set.
	 *  
	 */

	public void setNetworkList(List networkList) {

		this.networkList = networkList;

	}

	/**
	 * 
	 * @return Returns the coverageList.
	 *  
	 */

	public List getCoverageList() {

		coverageList = new ArrayList();

		try

		{

			this.refdataRequest = getRefDataRequest();

			refdataRequest.setPopupId(1695);

			RefDataResponse refDataResponse = (RefDataResponse) this

			.executeService(refdataRequest);

			coverageList = refDataResponse.getList();

		}

		catch (Exception serviceException)

		{

			Logger.logInfo(serviceException);

		}

		return coverageList;

	}

	/**
	 * 
	 * @param coverageList
	 *            The coverageList to set.
	 *  
	 */

	public void setCoverageList(List coverageList) {

		this.coverageList = coverageList;

	}

	//for contract gen Info

	/**
	 * 
	 * Returns the contractTypeList
	 * 
	 * @return List contractTypeList
	 *  
	 */

	public List getContractTypeList() {

		contractTypeList = new ArrayList();

		try {

			this.refdataRequest = getRefDataRequest();

			refdataRequest.setPopupId(1946);

			RefDataResponse refDataResponse = (RefDataResponse) this

			.executeService(refdataRequest);

			contractTypeList = refDataResponse.getList();

		}

		catch (Exception serviceException)

		{

			Logger.logInfo(serviceException);

		}

		return contractTypeList;

	}

	/**
	 * 
	 * Sets the contractTypeList
	 * 
	 * @param contractTypeList.
	 *  
	 */

	public void setContractTypeList(List contractTypeList) {

		this.contractTypeList = contractTypeList;

	}

	/**
	 * 
	 * Returns the contractTypeListForCombo
	 * 
	 * @return List contractTypeListForCombo.
	 *  
	 */
	public List getContractTypeListForAccumCombo() {

		contractTypeListForAccumCombo = new ArrayList();

		List contractTypeList = new ArrayList();

		int action = 1;

		String parentCatalog = "Contract Type";

		ReferenceDataLookupResponse referenceDataLookupResponse = getComboRefDataResponse(
				action, parentCatalog);

		contractTypeList = referenceDataLookupResponse.getSubCatalogList();

		Iterator contractTypeListIterator = contractTypeList.iterator();

		contractTypeListForAccumCombo.add(new SelectItem("",""));

	//	while (contractTypeListIterator.hasNext()) {

			//SubCatalogBO contractTypeResult = (SubCatalogBO) contractTypeListIterator
				//	.next();

		//	contractTypeListForCombo.add(new SelectItem(contractTypeResult
			//		.getPrimaryCode(), contractTypeResult.getDescription()));
		contractTypeListForAccumCombo.add(new SelectItem("BY","BY"));
		contractTypeListForAccumCombo.add(new SelectItem("CY","CY"));

		//}

		return contractTypeListForAccumCombo;

	}
	public List getContractTypeListForCombo() {

		contractTypeListForCombo = new ArrayList();

		List contractTypeList = new ArrayList();

		int action = 1;

		String parentCatalog = "Contract Type";

		ReferenceDataLookupResponse referenceDataLookupResponse = getComboRefDataResponse(
				action, parentCatalog);

		contractTypeList = referenceDataLookupResponse.getSubCatalogList();

		Iterator contractTypeListIterator = contractTypeList.iterator();

		contractTypeListForCombo.add(new SelectItem(""));

		while (contractTypeListIterator.hasNext()) {

			SubCatalogBO contractTypeResult = (SubCatalogBO) contractTypeListIterator
					.next();

			contractTypeListForCombo.add(new SelectItem(contractTypeResult
					.getPrimaryCode(), contractTypeResult.getDescription()));

			//contractTypeListForCombo.add(new SelectItem("CUST","Custom"));

		}

		return contractTypeListForCombo;

	}

	/**
	 * 
	 * Returns the complianceStatusListForCombo
	 * 
	 * @return List complianceStatusListForCombo.
	 *  
	 */

	public List getComplianceStatusListForCombo() {

		complianceStatusListForCombo = new ArrayList();

		List complianceStatusList = new ArrayList();

		int action = 1;

		String parentCatalog = "COMPLIANCE STATUS";

		ReferenceDataLookupResponse referenceDataLookupResponse = getComboRefDataResponse(
				action, parentCatalog);

		complianceStatusList = referenceDataLookupResponse.getSubCatalogList();

		Iterator complianceStatusListIterator = complianceStatusList.iterator();

		while (complianceStatusListIterator.hasNext()) {

			SubCatalogBO complianceStatusResult = (SubCatalogBO) complianceStatusListIterator
					.next();

			complianceStatusListForCombo.add(new SelectItem(
					complianceStatusResult.getPrimaryCode(),
					complianceStatusResult.getDescription()));

			//contractTypeListForCombo.add(new SelectItem("CUST","Custom"));

		}

		return complianceStatusListForCombo;

	}

	/**
	 * 
	 * @return Returns the baseContractCode.
	 *  
	 */

	public List getBaseContractCode() {

		baseContractCode = new ArrayList();

		baseContractCode.add("C001");

		baseContractCode.add("C002");

		return baseContractCode;

	}

	/**
	 * 
	 * Sets the contractTypeListForCombo
	 * 
	 * @param contractTypeListForCombo.
	 *  
	 */

	public void setContractTypeListForCombo(List contractTypeListForCombo) {

		this.contractTypeListForCombo = contractTypeListForCombo;

	}

	/**
	 * 
	 * Sets the complianceStatusListForCombo
	 * 
	 * @param complianceStatusListForCombo.
	 *  
	 */

	public void setComplianceStatusListForCombo(
			List complianceStatusListForCombo) {

		this.complianceStatusListForCombo = complianceStatusListForCombo;

	}

	/**
	 * 
	 * Returns the productList
	 * 
	 * @return List productList.
	 *  
	 */

	public List getProductList() {

		productList = new ArrayList();

		ProductBO productBo1 = new ProductBO();

		productBo1.setProductName("GIGGS2");

		productBo1.setProductKey(1078);

		productList.add(productBo1);

		ProductBO productBo2 = new ProductBO();

		productBo2.setProductName("DEEPA11");

		productBo2.setProductKey(1109);

		productList.add(productBo2);

		ProductBO productBo3 = new ProductBO();

		productBo3.setProductName("TEST_DEL_TEST");

		productBo3.setProductKey(1099);

		productList.add(productBo3);

		ProductBO productBo4 = new ProductBO();

		productBo4.setProductName("UTUBE");

		productBo4.setProductKey(1163);

		productList.add(productBo4);

		return productList;

	}

	/**
	 * 
	 * Sets the productList
	 * 
	 * @param productList.
	 *  
	 */

	public void setProductList(List productList) {

		this.productList = productList;

	}

	/**
	 * 
	 * Returns the noteTypeList
	 * 
	 * @return List noteTypeList.
	 *  
	 */

	public List getNoteTypeList() {

		noteTypeList = new ArrayList();

		this.refdataRequest = getRefDataRequest();

		refdataRequest.setPopupId(1945);

		RefDataResponse refDataResponse = (RefDataResponse) this

		.executeService(refdataRequest);

		noteTypeList = refDataResponse.getList();

		return noteTypeList;

	}

	/**
	 * 
	 * Sets the noteTypeList
	 * 
	 * @param noteTypeList.
	 *  
	 */

	public void setNoteTypeList(List noteTypeList) {

		this.noteTypeList = noteTypeList;

	}

	/**
	 * 
	 * @return Returns the benefitTypeList.
	 *  
	 */

	public List getBenefitTypeList() {

		List benefitTypeList = new ArrayList();

		benefitTypeList.add(0, this.mandate);

		benefitTypeList.add(1, this.standard);

		return benefitTypeList;

	}

	/**
	 * 
	 * @param benefitTypeList
	 *            The benefitTypeList to set.
	 *  
	 */

	public void setBenefitTypeList(List benefitTypeList) {

		this.benefitTypeList = benefitTypeList;

	}

	/**
	 * 
	 * Returns the effectivenessList
	 * 
	 * @return List effectivenessList.
	 *  
	 */

	public List getEffectivenessList() {

		effectivenessList = new ArrayList();

		List effectiveness = new ArrayList();

		this.refdataRequest = getRefDataRequest();

		refdataRequest.setPopupId(335);

		RefDataResponse refDataResponse = (RefDataResponse) this

		.executeService(refdataRequest);

		effectiveness = refDataResponse.getList();

		Iterator effectivenessIterator = effectiveness.iterator();

		effectivenessList.add(new SelectItem(""));

		while (effectivenessIterator.hasNext()) {

			ReferenceData effectivenessResult = (ReferenceData) effectivenessIterator
					.next();

			effectivenessList.add(new SelectItem(effectivenessResult.getCode(),
					effectivenessResult.getDescription()));

		}

		return effectivenessList;

	}

	/**
	 * 
	 * Sets the effectivenessList
	 * 
	 * @param effectivenessList.
	 *  
	 */

	public void setEffectivenessList(List effectivenessList) {

		this.effectivenessList = effectivenessList;

	}

	/**
	 * 
	 * Returns the mandateCategoryList
	 * 
	 * @return List mandateCategoryList.
	 *  
	 */

	public List getMandateCategoryList() {

		mandateCategoryList = new ArrayList();

		String mandateCode = null;

		String mandateDesc = null;

		List mandateCategory = new ArrayList();

		this.refdataRequest = getRefDataRequest();

		refdataRequest.setPopupId(334);

		RefDataResponse refDataResponse = (RefDataResponse) this

		.executeService(refdataRequest);

		mandateCategory = refDataResponse.getList();

		Iterator mandateCategoryIterator = mandateCategory.iterator();

		while (mandateCategoryIterator.hasNext()) {

			ReferenceData mandateCategoryResult = (ReferenceData) mandateCategoryIterator
					.next();

			// enhancements -- start. to keep "Mandate" as defaulted field in
			// the mandate info page.

			//            if(mandateCategoryResult.getDescription().equals("Mandate")){

			//                      mandateCode = mandateCategoryResult.getCode();

			//                      mandateDesc = mandateCategoryResult.getDescription();

			//                      

			//            }else

			mandateCategoryList.add(new SelectItem(mandateCategoryResult
					.getCode(), mandateCategoryResult.getDescription()));

		}

		//        if(null != mandateDesc){

		//            mandateCategoryList.add(new SelectItem(mandateCode,mandateDesc));

		//        }

		//enhancements -- end

		return mandateCategoryList;

	}

	/**
	 * 
	 * Sets the mandateCategoryList
	 * 
	 * @param mandateCategoryList.
	 *  
	 */

	public void setMandateCategoryList(List mandateCategoryList) {

		this.mandateCategoryList = mandateCategoryList;

	}

	/**
	 * @return Returns the adminOptionList.
	 */
	public List getAdminOptionList() {
		return adminOptionList;
	}

	/**
	 * @param adminOptionList
	 *            The adminOptionList to set.
	 */
	public void setAdminOptionList(List adminOptionList) {
		this.adminOptionList = adminOptionList;
	}

	/**
	 * @return Returns the searchCheck.
	 */
	public boolean isSearchCheck() {
		return searchCheck;
	}

	/**
	 * @param searchCheck
	 *            The searchCheck to set.
	 */
	public void setSearchCheck(boolean searchCheck) {
		this.searchCheck = searchCheck;
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
	 * @return Returns the renderFirstList.
	 */
	public boolean isRenderFirstList() {
		return renderFirstList;
	}

	/**
	 * @param renderFirstList
	 *            The renderFirstList to set.
	 */
	public void setRenderFirstList(boolean renderFirstList) {
		this.renderFirstList = renderFirstList;
	}

	/**
	 * @param benefitCategoryListForCombo
	 *            The benefitCategoryListForCombo to set.
	 */
	public void setBenefitCategoryListForCombo(List benefitCategoryListForCombo) {
		this.benefitCategoryListForCombo = benefitCategoryListForCombo;
	}

	/**
	 * @return Returns the entityId.
	 */
	public int getEntityId() {
		return entityId;
	}

	/**
	 * @param entityId
	 *            The entityId to set.
	 */
	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	/**
	 * @return Returns the entityType.
	 */
	public String getEntityType() {
		return entityType;
	}

	/**
	 * @param entityType
	 *            The entityType to set.
	 */
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	/**
	 * @return Returns the blueExchangeHeader.
	 */
	public String getBlueExchangeHeader() {
		return blueExchangeHeader;
	}

	/**
	 * @param blueExchangeHeader
	 *            The blueExchangeHeader to set.
	 */
	public void setBlueExchangeHeader(String blueExchangeHeader) {
		this.blueExchangeHeader = blueExchangeHeader;
	}

	/**
	 * @return Returns the messageIndicatorList.
	 */
	public List getMessageIndicatorList() {
		messageIndicatorList = new ArrayList();
		messageIndicatorList.add(new SelectItem(""));
		messageIndicatorList.add(new SelectItem("NO"));
		messageIndicatorList.add(new SelectItem("YES"));
		return messageIndicatorList;
	}

	/**
	 * @param messageIndicatorList
	 *            The messageIndicatorList to set.
	 */
	public void setMessageIndicatorList(List messageIndicatorList) {
		this.messageIndicatorList = messageIndicatorList;
	}

	/**
	 * @return Returns the adminId.
	 */
	public int getAdminId() {
		if (null != getRequest().getParameter("adminOptionId")) {
			adminId = new Integer(getRequest().getParameter("adminOptionId"))
					.intValue();
		}

		return adminId;
	}

	/**
	 * @param adminId
	 *            The adminId to set.
	 */
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	/**
	 * @return Returns the searchString.
	 */
	public String getSearchString() {
		return searchString;
	}

	/**
	 * @param searchString
	 *            The searchString to set.
	 */
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	/**
	 * @return Returns the catalogNameHidden.
	 */
	public String getCatalogNameHidden() {
		return catalogNameHidden;
	}

	/**
	 * @param catalogNameHidden
	 *            The catalogNameHidden to set.
	 */
	public void setCatalogNameHidden(String catalogNameHidden) {
		this.catalogNameHidden = catalogNameHidden;
	}

	/**
	 * @return Returns the lookUpActionHidden.
	 */
	public int getLookUpActionHidden() {
		return lookUpActionHidden;
	}

	/**
	 * @param lookUpActionHidden
	 *            The lookUpActionHidden to set.
	 */
	public void setLookUpActionHidden(int lookUpActionHidden) {
		this.lookUpActionHidden = lookUpActionHidden;
	}

	/**
	 * @return Returns the catalogNameForTermQualifier.
	 */
	public String getCatalogNameForTermQualifier() {
		return catalogNameForTermQualifier;
	}

	/**
	 * @param catalogNameForTermQualifier
	 *            The catalogNameForTermQualifier to set.
	 */
	public void setCatalogNameForTermQualifier(
			String catalogNameForTermQualifier) {
		this.catalogNameForTermQualifier = catalogNameForTermQualifier;
	}

	/**
	 * @return Returns the businessEntity.
	 */
	public String getBusinessEntity() {
		return businessEntity;
	}

	/**
	 * @param businessEntity
	 *            The businessEntity to set.
	 */
	public void setBusinessEntity(String businessEntity) {
		this.businessEntity = businessEntity;
	}

	/**
	 * @return Returns the businessGroup.
	 */
	public String getBusinessGroup() {
		return businessGroup;
	}

	/**
	 * @param businessGroup
	 *            The businessGroup to set.
	 */
	public void setBusinessGroup(String businessGroup) {
		this.businessGroup = businessGroup;
	}

	/**
	 * @return Returns the lob.
	 */
	public String getLob() {
		return lob;
	}

	/**
	 * @param lob
	 *            The lob to set.
	 */
	public void setLob(String lob) {
		this.lob = lob;
	}

	/**
	 * @return Returns the lookUpAction.
	 */
	public String getLookUpAction() {
		return lookUpAction;
	}

	/**
	 * @param lookUpAction
	 *            The lookUpAction to set.
	 */
	public void setLookUpAction(String lookUpAction) {
		this.lookUpAction = lookUpAction;
	}

	/**
	 * @return Returns the marketSegmentList.
	 */
	public List getMarketSegmentList() {
		List marketSegmentListTemp = new ArrayList();

		marketSegmentList = new ArrayList();

		int action = 1;

		String parentCatalog = "Market Segment";

		ReferenceDataLookupResponse referenceDataLookupResponse = getComboRefDataResponse(
				action, parentCatalog);

		marketSegmentListTemp = referenceDataLookupResponse.getSubCatalogList();

		Iterator marketSegmentListTempIterator = marketSegmentListTemp
				.iterator();

		marketSegmentList.add(new SelectItem(""));

		while (marketSegmentListTempIterator.hasNext()) {

			SubCatalogBO marketSegmentResult = (SubCatalogBO) marketSegmentListTempIterator
					.next();

			marketSegmentList.add(new SelectItem(marketSegmentResult
					.getPrimaryCode(), marketSegmentResult.getDescription()));

		}

		return marketSegmentList;
	}

	/**
	 * @param marketSegmentList
	 *            The marketSegmentList to set.
	 */
	public void setMarketSegmentList(List marketSegmentList) {
		this.marketSegmentList = marketSegmentList;
	}

	/**
	 * @return Returns the qualifierDataLookUpList.
	 */
	public List getQualifierDataLookUpList() {

		String searchString = this.getRequest().getParameter("searchString");
		String catalogName = this.getRequest().getParameter("catalogName");
		String lookUpAction = this.getRequest().getParameter("lookUpAction");

		if (null != searchString && index == 0) {
			this.lookUpAction = lookUpAction;
			if (null != lookUpAction && !"".equals(lookUpAction)) {
				this.lookUpActionHidden = (Integer.parseInt(lookUpAction));
			}
			this.catalogNameHidden = catalogName;

			this.searchValueForPopUp = (searchString.trim()).toUpperCase();
			this.searchString = searchString;
			loadRefDataPopUp();
			index++;
		}

		return qualifierDataLookUpList;
	}

	/**
	 * @param qualifierDataLookUpList
	 *            The qualifierDataLookUpList to set.
	 */
	public void setQualifierDataLookUpList(List qualifierDataLookUpList) {
		this.qualifierDataLookUpList = qualifierDataLookUpList;
	}

	/**
	 * @param searchTermQualifier
	 *            The searchTermQualifier to set.
	 */
	public void setSearchTermQualifier(String searchTermQualifier) {
		this.searchTermQualifier = searchTermQualifier;
	}

	/**
	 * @return Returns the parentCatalog.
	 */
	public String getParentCatalog() {
		return parentCatalog;
	}

	/**
	 * @param parentCatalog
	 *            The parentCatalog to set.
	 */
	public void setParentCatalog(String parentCatalog) {
		this.parentCatalog = parentCatalog;
	}

	/**
	 * @return Returns the indicativeLoad.
	 */
	public String getIndicativeLoad() {

		indicativeLoadHeader = (String) getRequest().getParameter(
				WebConstants.POPUP_HEADER);
		this.getRefDataLookupRecords();
		return indicativeLoad;
	}

	/**
	 * @param indicativeLoad
	 *            The indicativeLoad to set.
	 */
	public void setIndicativeLoad(String indicativeLoad) {
		this.indicativeLoad = indicativeLoad;
	}

	/**
	 * @return Returns the claimTypeListForCombo.
	 */
	public List getClaimTypeListForCombo() {

		// Need to remove this session attributes.
		List sessionList = (List) (this.getSession()
				.getAttribute("claimTypeList"));
		if (sessionList != null) {
			return sessionList;
		}

		claimTypeListForCombo = new ArrayList();

		List claimTypeList = new ArrayList();

		int action = BusinessConstants.DROP_DOWN_ACTION;

		String parentCatalog = "Claim Type";

		ReferenceDataLookupResponse referenceDataLookupResponse = getComboRefDataResponse(
				action, parentCatalog);

		claimTypeList = referenceDataLookupResponse.getSubCatalogList();

		Iterator claimTypeListIterator = claimTypeList.iterator();

		claimTypeListForCombo.add(new SelectItem(""));

		while (claimTypeListIterator.hasNext()) {

			SubCatalogBO claimTypeResult = (SubCatalogBO) claimTypeListIterator
					.next();

			claimTypeListForCombo.add(new SelectItem(claimTypeResult
					.getPrimaryCode(), claimTypeResult.getDescription()));

		}

		this.getSession().setAttribute("claimTypeList", claimTypeListForCombo);

		return claimTypeListForCombo;
	}

	/**
	 * @param claimTypeListForCombo
	 *            The claimTypeListForCombo to set.
	 */
	public void setClaimTypeListForCombo(List claimTypeListForCombo) {
		this.claimTypeListForCombo = claimTypeListForCombo;
	}

	/**
	 * @return Returns the genderListForCombo.
	 */
	public List getGenderListForCombo() {
		// Need to remove this session attributes.
		List sessionList = (List) (this.getSession().getAttribute("genderList"));
		if (sessionList != null) {
			return sessionList;
		}

		genderListForCombo = new ArrayList();

		List genderList = new ArrayList();

		int action = BusinessConstants.DROP_DOWN_ACTION;

		String parentCatalog = "Gender Code";

		ReferenceDataLookupResponse referenceDataLookupResponse = getComboRefDataResponse(
				action, parentCatalog);

		genderList = referenceDataLookupResponse.getSubCatalogList();

		Iterator genderListIterator = genderList.iterator();

		genderListForCombo.add(new SelectItem(""));

		while (genderListIterator.hasNext()) {

			SubCatalogBO genderResult = (SubCatalogBO) genderListIterator
					.next();

			genderListForCombo.add(new SelectItem(
					genderResult.getPrimaryCode(), genderResult
							.getDescription()));

		}

		this.getSession().setAttribute("genderList", genderListForCombo);

		return genderListForCombo;
	}

	/**
	 * @param genderListForCombo
	 *            The genderListForCombo to set.
	 */
	public void setGenderListForCombo(List genderListForCombo) {
		this.genderListForCombo = genderListForCombo;
	}

	/**
	 * @return Returns the groupStateListForCombo.
	 */
	public List getGroupStateListForCombo() {

		// Need to remove this session attributes.
		List sessionList = (List) (this.getSession()
				.getAttribute("groupStateList"));
		if (sessionList != null) {
			return sessionList;
		}

		groupStateListForCombo = new ArrayList();

		List groupStateList = new ArrayList();

		int action = BusinessConstants.DROP_DOWN_ACTION;

		String parentCatalog = "Group State";

		ReferenceDataLookupResponse referenceDataLookupResponse = getComboRefDataResponse(
				action, parentCatalog);

		groupStateList = referenceDataLookupResponse.getSubCatalogList();

		Iterator groupStateListIterator = groupStateList.iterator();

		groupStateListForCombo.add(new SelectItem(""));

		while (groupStateListIterator.hasNext()) {

			SubCatalogBO groupStateResult = (SubCatalogBO) groupStateListIterator
					.next();

			groupStateListForCombo.add(new SelectItem(groupStateResult
					.getPrimaryCode(), groupStateResult.getDescription()));

		}

		this.getSession()
				.setAttribute("groupStateList", groupStateListForCombo);

		return groupStateListForCombo;
	}

	/**
	 * @param groupStateListForCombo
	 *            The groupStateListForCombo to set.
	 */
	public void setGroupStateListForCombo(List groupStateListForCombo) {
		this.groupStateListForCombo = groupStateListForCombo;
	}

	/**
	 * @return Returns the hospitalFacilityCodeListForCombo.
	 */
	public List getHospitalFacilityCodeListForCombo() {

		// Need to remove this session attributes.
		List sessionList = (List) (this.getSession()
				.getAttribute("hospitalFacilityCodeList"));
		if (sessionList != null) {
			return sessionList;
		}

		hospitalFacilityCodeListForCombo = new ArrayList();

		List hospitalFacilityCodeList = new ArrayList();

		int action = BusinessConstants.DROP_DOWN_ACTION;

		String parentCatalog = "Hospital Facility Code";

		ReferenceDataLookupResponse referenceDataLookupResponse = getComboRefDataResponse(
				action, parentCatalog);

		hospitalFacilityCodeList = referenceDataLookupResponse
				.getSubCatalogList();

		Iterator hospitalFacilityCodeListIterator = hospitalFacilityCodeList
				.iterator();

		hospitalFacilityCodeListForCombo.add(new SelectItem(""));

		while (hospitalFacilityCodeListIterator.hasNext()) {

			SubCatalogBO hospitalFacilityCodeResult = (SubCatalogBO) hospitalFacilityCodeListIterator
					.next();

			hospitalFacilityCodeListForCombo.add(new SelectItem(
					hospitalFacilityCodeResult.getPrimaryCode(),
					hospitalFacilityCodeResult.getDescription()));

		}

		this.getSession().setAttribute("hospitalFacilityCodeList",
				hospitalFacilityCodeListForCombo);

		return hospitalFacilityCodeListForCombo;
	}

	/**
	 * @param hospitalFacilityCodeListForCombo
	 *            The hospitalFacilityCodeListForCombo to set.
	 */
	public void setHospitalFacilityCodeListForCombo(
			List hospitalFacilityCodeListForCombo) {
		this.hospitalFacilityCodeListForCombo = hospitalFacilityCodeListForCombo;
	}

	/**
	 * @return Returns the medAssignIndicatorListForCombo.
	 */
	public List getMedAssignIndicatorListForCombo() {

		// Need to remove this session attributes.
		List sessionList = (List) (this.getSession()
				.getAttribute("medAssignIndicatorList"));
		if (sessionList != null) {
			return sessionList;
		}
		medAssignIndicatorListForCombo = new ArrayList();

		List medAssignIndicatorList = new ArrayList();

		int action = BusinessConstants.DROP_DOWN_ACTION;

		String parentCatalog = "Medicate Indicator";

		ReferenceDataLookupResponse referenceDataLookupResponse = getComboRefDataResponse(
				action, parentCatalog);

		medAssignIndicatorList = referenceDataLookupResponse
				.getSubCatalogList();

		Iterator medAssignIndicatorListIterator = medAssignIndicatorList
				.iterator();

		medAssignIndicatorListForCombo.add(new SelectItem(""));

		while (medAssignIndicatorListIterator.hasNext()) {

			SubCatalogBO medAssignIndicatorResult = (SubCatalogBO) medAssignIndicatorListIterator
					.next();

			medAssignIndicatorListForCombo.add(new SelectItem(
					medAssignIndicatorResult.getPrimaryCode(),
					medAssignIndicatorResult.getDescription()));

		}

		this.getSession().setAttribute("medAssignIndicatorList",
				medAssignIndicatorListForCombo);

		return medAssignIndicatorListForCombo;
	}

	/**
	 * @param medAssignIndicatorListForCombo
	 *            The medAssignIndicatorListForCombo to set.
	 */
	public void setMedAssignIndicatorListForCombo(
			List medAssignIndicatorListForCombo) {
		this.medAssignIndicatorListForCombo = medAssignIndicatorListForCombo;
	}

	/**
	 * @return Returns the memberRelationshipCodeListForCombo.
	 */
	public List getMemberRelationshipCodeListForCombo() {

		// Need to remove this session attributes.
		List sessionList = (List) (this.getSession()
				.getAttribute("memberRelationshipCodeList"));
		if (sessionList != null) {
			return sessionList;
		}

		memberRelationshipCodeListForCombo = new ArrayList();

		List memberRelationshipCodeList = new ArrayList();

		int action = BusinessConstants.DROP_DOWN_ACTION;

		String parentCatalog = "Member Releationship Code";

		ReferenceDataLookupResponse referenceDataLookupResponse = getComboRefDataResponse(
				action, parentCatalog);

		memberRelationshipCodeList = referenceDataLookupResponse
				.getSubCatalogList();

		Iterator memberRelationshipCodeListIterator = memberRelationshipCodeList
				.iterator();

		memberRelationshipCodeListForCombo.add(new SelectItem(""));

		while (memberRelationshipCodeListIterator.hasNext()) {

			SubCatalogBO memberRelationshipCodeResult = (SubCatalogBO) memberRelationshipCodeListIterator
					.next();

			memberRelationshipCodeListForCombo.add(new SelectItem(
					memberRelationshipCodeResult.getPrimaryCode(),
					memberRelationshipCodeResult.getDescription()));

		}

		this.getSession().setAttribute("memberRelationshipCodeList",
				memberRelationshipCodeListForCombo);

		return memberRelationshipCodeListForCombo;
	}

	/**
	 * @param memberRelationshipCodeListForCombo
	 *            The memberRelationshipCodeListForCombo to set.
	 */
	public void setMemberRelationshipCodeListForCombo(
			List memberRelationshipCodeListForCombo) {
		this.memberRelationshipCodeListForCombo = memberRelationshipCodeListForCombo;
	}

	/**
	 * @return Returns the placeOfServiceListForCombo.
	 */
	public List getPlaceOfServiceListForCombo() {

		// Need to remove this session attributes.
		List sessionList = (List) (this.getSession()
				.getAttribute("placeOfServiceList"));
		if (sessionList != null) {
			return sessionList;
		}
		placeOfServiceListForCombo = new ArrayList();

		List placeOfServiceList = new ArrayList();

		int action = BusinessConstants.DROP_DOWN_ACTION;

		String parentCatalog = "Place Of Service";

		ReferenceDataLookupResponse referenceDataLookupResponse = getComboRefDataResponse(
				action, parentCatalog);

		placeOfServiceList = referenceDataLookupResponse.getSubCatalogList();

		Iterator placeOfServiceListIterator = placeOfServiceList.iterator();

		placeOfServiceListForCombo.add(new SelectItem(""));

		while (placeOfServiceListIterator.hasNext()) {

			SubCatalogBO placeOfServiceResult = (SubCatalogBO) placeOfServiceListIterator
					.next();

			placeOfServiceListForCombo.add(new SelectItem(placeOfServiceResult
					.getPrimaryCode(), placeOfServiceResult.getDescription()));

		}

		this.getSession().setAttribute("placeOfServiceList",
				placeOfServiceListForCombo);

		return placeOfServiceListForCombo;
	}

	/**
	 * @param placeOfServiceListForCombo
	 *            The placeOfServiceListForCombo to set.
	 */
	public void setPlaceOfServiceListForCombo(List placeOfServiceListForCombo) {
		this.placeOfServiceListForCombo = placeOfServiceListForCombo;
	}

	/**
	 * @return Returns the typeOfBillListForCombo.
	 */
	public List getTypeOfBillListForCombo() {

		// Need to remove this session attributes.
		List sessionList = (List) (this.getSession()
				.getAttribute("typeOfBillList"));
		if (sessionList != null) {
			return sessionList;
		}

		typeOfBillListForCombo = new ArrayList();

		List typeOfBillList = new ArrayList();

		int action = BusinessConstants.DROP_DOWN_ACTION;

		String parentCatalog = "Type Of Bill";

		ReferenceDataLookupResponse referenceDataLookupResponse = getComboRefDataResponse(
				action, parentCatalog);

		typeOfBillList = referenceDataLookupResponse.getSubCatalogList();

		Iterator typeOfBillListIterator = typeOfBillList.iterator();

		while (typeOfBillListIterator.hasNext()) {

			SubCatalogBO typeOfBillResult = (SubCatalogBO) typeOfBillListIterator
					.next();

			typeOfBillListForCombo.add(typeOfBillResult);

		}

		this.getSession()
				.setAttribute("typeOfBillList", typeOfBillListForCombo);

		return typeOfBillListForCombo;
	}

	/**
	 * @param typeOfBillListForCombo
	 *            The typeOfBillListForCombo to set.
	 */
	public void setTypeOfBillListForCombo(List typeOfBillListForCombo) {
		this.typeOfBillListForCombo = typeOfBillListForCombo;
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

	public List getContractStatuses() {
		contractStatuses = new ArrayList();
		contractStatuses.add(new SelectItem(WebConstants.CNTRT_STATUS_EMPTY,WebConstants.CNTRT_STATUS_EMPTY));
		contractStatuses.add(new SelectItem(WebConstants.CNTRT_STATUS_ACTIVE,WebConstants.CNTRT_STATUS_ACTIVE));
		contractStatuses.add(new SelectItem(WebConstants.CNTRT_STATUS_INACTIVE,WebConstants.CNTRT_STATUS_INACTIVE));
		contractStatuses.add(new SelectItem(WebConstants.CNTRT_STATUS_TERMED,WebConstants.CNTRT_STATUS_TERMED));
		return contractStatuses;
	}

	public void setContractStatuses(List contractStatuses) {
		this.contractStatuses = contractStatuses;
	}

	public void setContractTypeListForAccumCombo(
			List contractTypeListForAccumCombo) {
		this.contractTypeListForAccumCombo = contractTypeListForAccumCombo;
	}
}