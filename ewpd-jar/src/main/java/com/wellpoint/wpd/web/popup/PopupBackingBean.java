/*
 * PopupBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.popup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.popup.bo.PopupFilterBO;
import com.wellpoint.wpd.common.popup.request.PopupRequest;
import com.wellpoint.wpd.common.popup.response.PopupResponse;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class PopupBackingBean extends WPDBackingBean {

	private int entityid;

	private String queryName;

	private String retrieveRecords;

	private String headerName;

	private String searchString = null;

	private List searchList;
	
	private List filteredSearchList;

	HashMap hashMap = new HashMap();

	private int popAction;

	private String records;
	
	private String filteredRecords;

	private int index;

	private String benefitLvlId;
	
	private String term;
	
	private String qualifier;
	
	private String dataType;
	
	private String pva;
	
	private String spsType;
	
	private String filter;
	private String spsId;
	private String recordsForAdminMethodPopup;
	private List searchListForAdmin;
	private String keyValue;
	
	private List processingMethodList;
	private List processingList;
	private boolean recordsGreaterThanMaxSize;
	
		
	/**
	 * @return Returns the spsType.
	 */
	public String getSpsType() {
		return spsType;
	}
	/**
	 * @param spsType The spsType to set.
	 */
	public void setSpsType(String spsType) {
		this.spsType = spsType;
	}
	/**
	 * @return Returns the filteredSearchList.
	 */
	public List getFilteredSearchList() {
		String searchString = this.getRequest().getParameter("searchString");
		String queryName = this.getRequest().getParameter("queryName");
		
		if (("getAdminOptionListForBenefitLevel").equals(queryName)) {
			this.benefitLvlId = this.getRequest().getParameter("benefitLvlId");
		}
		
		String headerName = this.getRequest().getParameter("headerName");
		String entityId = this.getRequest().getParameter("entityId");
		
		if (null != searchString && index == 0) {
			this.searchString = searchString;
			this.queryName = queryName;
			if (("getAdminOptionListForBenefitLevel").equals(queryName)) {
				this.benefitLvlId = this.getRequest().getParameter(
						"benefitLvlId");
			}
			this.headerName = headerName;
			this.entityid = Integer.parseInt(entityId);
			this.getFilteredRecords();
			index++;
		}
		
		return searchList;
		
	}
	/**
	 * @param filteredSearchList The filteredSearchList to set.
	 */
	public void setFilteredSearchList(List filteredSearchList) {
		this.filteredSearchList = filteredSearchList;
	}
	/**
	 * @param filteredRecords The filteredRecords to set.
	 */
	public void setFilteredRecords(String filteredRecords) {
		this.filteredRecords = filteredRecords;
	}
	/**
	 *
	 * 
	 * this method call at the time of pop up intial loading.
	 * Methos crate the  Request PopupRequest and using this request call the business service
	 * retrieve List of record for popUp and set backing bean values 
	 */
	public String getRecords() {
		PopupRequest request = getPopupRequest();
		request.setPopAction(WebConstants.FILTER_ACTION);
		this.popAction = WebConstants.FILTER_ACTION;
		PopupResponse response = null;
		response = (PopupResponse) executeService(request);
		if (null != response) {
			setValuesToBackinBean(response);
		}
		return null;
	}
	
	/**
	 *
	 * 
	 * this method call at the time of pop up intial loading.
	 * Methos crate the  Request PopupRequest and using this request call the business service
	 * retrieve List of record for popUp and set backing bean values 
	 */
	public String getFilteredRecords() {
		PopupRequest request = getPopupRequest();
		request.setPopAction(WebConstants.FILTER_ACTION);
		this.popAction = WebConstants.FILTER_ACTION;
		PopupResponse response = null;
		response = (PopupResponse) executeService(request);
		if (null != response) {
			setFilteredValuesToBackinBean(response);
		}
		return null;
	}
	
	
	/**
	 *
	 * 
	 * this method call at the time of filter Search.
	 * Methos crate the  Request PopupRequest and using this request call the business service
	 * retrieve List of record for popUp and set backing bean values 
	 */
	public void filterResult() {
		PopupRequest request = getPopupRequest();
		request.setPopAction(WebConstants.SEARCH_ACTION);
		this.popAction = WebConstants.SEARCH_ACTION;
		PopupResponse response = null;
		response = (PopupResponse) executeService(request);
		if(null != response){
		if (("retrieveAdminMethodNumber").equals(this.getRequest().getParameter("queryName")) && null != response ){
		setValuesToBackinBeanForAdminMethodPopup(response);
		}

		else {
			setValuesToBackinBean(response);
		}
		}		
	}
	
	/*
	 * @ return PopupRequest
	 * this methode create and return a request object for calling business service action 
	 * In this methode we are settign values for poup loading and filtering the records 
	 * 
	 */
	private PopupRequest getPopupRequest() {
		PopupRequest request = (PopupRequest) this
				.getServiceRequest(ServiceManager.POPUP_REQUEST);
		if (null != getRequest().getParameter("entityId"))
			this.entityid = Integer.parseInt(getRequest().getParameter(
					"entityId"));
		this.queryName = getRequest().getParameter("queryName");
		if (("getAdminOptionListForBenefitLevel").equals(queryName)) {
			this.benefitLvlId = this.getRequest().getParameter("benefitLvlId");
			if (null != this.benefitLvlId && !("").equals(this.benefitLvlId)) {
				List listBenefitLevel = WPDStringUtil.getListFromTildaString(
						this.benefitLvlId, 2, 1, 1);
				int benefitLevelInt = Integer.parseInt(listBenefitLevel.get(0)
						.toString());
				request.setBenefitLvlid(benefitLevelInt);
				hashMap.put(WebConstants.BNFT_LVL_ID, new Integer(
						benefitLevelInt));

			}
		}
		if(("retrieveAdminMethodNumber").equals(queryName)){
			String []tempList=this.getRequest().getParameter("spsId").split(",");
			List spsId=new ArrayList();
			for (int i = 0; i < tempList.length; i++) {
				spsId.add(tempList[i]);
				
			}
			
		    request.setSpsIdList(spsId);
		    this.spsId=this.getRequest().getParameter("spsId");
			//request.setSpsId(this.getRequest().getParameter("spsId"));
		    if(spsId != null && !"".equals(this.getRequest().getParameter("spsId")))
		    hashMap.put("spsIdList", tempList[0]);
		    else
		    hashMap.put("spsIdList", null);	
		}
		this.headerName = getRequest().getParameter("headerName");

		if (0 < this.entityid) {
			hashMap.put(WebConstants.ENTITY_ID, new Integer(this.entityid));
		}
		if (null != getRequest().getParameter("searchString")
				&& !"".equals(getRequest().getParameter("searchString")))
			searchString = getRequest().getParameter("searchString");
		if (null != searchString && !("").equals(searchString)) {
			String newSearchString = WPDStringUtil.escapeString(searchString);
			hashMap.put(WebConstants.POPUP_SEARCH_STRING, "%"
					+ newSearchString.trim().toUpperCase() + "%");
		} else {
			hashMap.put(WebConstants.POPUP_SEARCH_STRING, "%" + "" + "%");
		}
		
		if("searchSPSForMapping".equals(queryName)){
			String filter = this.getRequest().getParameter("filter");
			if(filter!= null && !"".equalsIgnoreCase(filter.trim()))
				this.filter =filter;
			
			if(this.getFilter() == null || "filter".equalsIgnoreCase(this.getFilter())){
			String spsType = this.getRequest().getParameter("spsType");
			
			String term = this.getRequest().getParameter("term");
			String pva = this.getRequest().getParameter("pva");
			String qualifier = this.getRequest().getParameter("qualifier");
			String dataType = this.getRequest().getParameter("dataType");
			
			if(term != null && !"".equalsIgnoreCase(term)){
				hashMap.put("term", term);
				this.term =term;
			}
			if(pva != null && !"".equalsIgnoreCase(pva)){
				hashMap.put("pva", pva);
				this.pva =pva;
			}
			if(qualifier != null){
				if("".equalsIgnoreCase(qualifier.trim())){
					if("LINE".equalsIgnoreCase(spsType))
					hashMap.put("qualifier", "null");
				}else
				hashMap.put("qualifier", qualifier);
				this.qualifier =qualifier;
			}
			if(dataType != null && !"".equalsIgnoreCase(dataType) && !"0".equalsIgnoreCase(dataType)){
				hashMap.put("dataType", new Integer(dataType));
				this.dataType =dataType;
			}
			if(spsType != null && !"".equalsIgnoreCase(spsType)){
				hashMap.put("spsType", spsType);
				this.spsType =spsType;
			}
			}else{
				String spsType = this.getRequest().getParameter("spsType");
				if(spsType != null && !"".equalsIgnoreCase(spsType)){
					hashMap.put("spsType", spsType);
					this.spsType =spsType;
				}
			}

			
		}
		request.setEntityid(this.entityid);
		request.setQueryName(this.queryName);
		if (null != this.headerName && !"".equals(this.headerName)) {
			if (this.headerName.equalsIgnoreCase(WebConstants.POPUP_ADJUD)
					|| this.headerName
							.equalsIgnoreCase(WebConstants.POPUP_UMRULE)
					|| this.headerName
							.equalsIgnoreCase(WebConstants.POPUP_BNFTDENY)) {
				hashMap.put(WebConstants.POPUP_HEADER_NAME, this.headerName);
				this.setHeaderName(this.headerName);
			} else if (this.headerName
					.equals(WebConstants.CONTRACT_EXCLUSION_RULE)) {

				hashMap.put(WebConstants.POPUP_HEADER_NAME, "&%");
			} else if (this.headerName
					.equals(WebConstants.CONTRACT_DENIAL_RULE)) {

				hashMap.put(WebConstants.POPUP_HEADER_NAME, "*%");
			} else if (this.headerName.equals(WebConstants.CONTRACT_UM_RULE)) {

				hashMap.put(WebConstants.POPUP_HEADER_NAME, "#%");
			} else if (this.headerName.equals(WebConstants.CONTRACT_PNR_RULE)) {

				hashMap.put(WebConstants.POPUP_HEADER_NAME, "$%");
			}
		}
		request.setHashMap(this.hashMap);
		request.setHeaderName(this.headerName);
		return request;
	}

	/*
	 * @ param  PopupResponse
	 * 
	 * it sets all the backing bean values from response 
	 */

	private void setValuesToBackinBean(PopupResponse response) {
		this.setEntityid(response.getEntitySysId());
		this.setQueryName(response.getQueryName());
		this.setHeaderName(response.getHeaderName());
		List resultTempList = new ArrayList();
		
		if(response.getResultList().size()>50){
			List resultListNew = new ArrayList();
			addMessageToRequest(new InformationalMessage(WebConstants.SEARCH_RESULT_EXCEEDS));
			setRecordsGreaterThanMaxSize(true);
			for(int i=0;i<50;i++){
				resultListNew.add(i,response.getResultList().get(i));
		    }
				this.setSearchList(resultListNew);
			}		
		else {
			this.setSearchList(response.getResultList());
			setRecordsGreaterThanMaxSize(false);
		}		
//		this.setSearchList(response.getResultList());
	}
	
	/*
	 * @ param  PopupResponse
	 * 
	 * it sets all the backing bean values from response 
	 */

	private void setFilteredValuesToBackinBean(PopupResponse response) {
		this.setEntityid(response.getEntitySysId());
		this.setQueryName(response.getQueryName());
		this.setHeaderName(response.getHeaderName());
		List resultTempList = new ArrayList();
		
		if("processingType".equals(this.queryName)){			
			List values = response.getResultList();
			processingList = parseSPSValues(values);
			this.setSearchList(processingList);
			
		}
		else if(response.getResultList().size()>50){
			List resultList = new ArrayList();
			addMessageToRequest(new InformationalMessage(WebConstants.SEARCH_RESULT_EXCEEDS));
			setRecordsGreaterThanMaxSize(true);
			for(int i=0;i<50;i++){
				resultList.add(i,response.getResultList().get(i));
		    }
				this.setSearchList(resultList);
			}		
		else {
			this.setSearchList(response.getResultList());
			setRecordsGreaterThanMaxSize(false);
		}
		
		if(this.getSearchList().size()>50)
		addMessageToRequest(new InformationalMessage(WebConstants.SEARCH_RESULT_EXCEEDS));
		if("searchSPSForMapping".equals(this.queryName)){
			if(this.filter==null){
			if(this.getSearchList().size()>0)
				this.setFilter("filter");
			else {
				List messageList=new ArrayList();
				messageList.add(new ErrorMessage("benefit.level.sps.no.results"));
				addAllMessagesToRequest(messageList);
				this.setFilter("nonfilter");
			}
			}
		}
	}

	
	private void setValuesToBackinBeanForAdminMethodPopup(PopupResponse response) {
		List resultList=new ArrayList();
		this.setEntityid(response.getEntitySysId());
		this.setQueryName(response.getQueryName());
		this.setHeaderName(response.getHeaderName());
		
		if(response.getResultList().size()>50){
		addMessageToRequest(new InformationalMessage(WebConstants.SEARCH_RESULT_EXCEEDS));
		setRecordsGreaterThanMaxSize(true);
		for(int i=0;i<50;i++){
			resultList.add(i,response.getResultList().get(i));
	       }
		this.setSearchList(resultList);
		}
		else{
			this.setSearchList(response.getResultList());
			setRecordsGreaterThanMaxSize(false);
			
		}
	}	

	private List parseSPSValues(List values){		
		List newSPSValues = new ArrayList();
		processingList = new ArrayList(); 
		
		for (int i = 0; i < values.size(); i++) {			
			PopupFilterBO popupFilterBO1 = (PopupFilterBO) values.get(i);//0,2,4,6,
			i++;
			PopupFilterBO popupFilterBO2 = (PopupFilterBO) values.get(i);//1,3,5,			
			popupFilterBO1.setKeyValue(popupFilterBO1.getKeyValue()+":"+popupFilterBO2.getKeyValue());			
			newSPSValues.add(popupFilterBO1);			
		}	
		return newSPSValues; 	
	}
	/**
	 * @return Returns the queryName.
	 */
	public String getQueryName() {
		return queryName;
	}

	/**
	 * @param queryName The queryName to set.
	 */
	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	/**
	 * @param retrieveRecords The retrieveRecords to set.
	 */
	public void setRetrieveRecords(String retrieveRecords) {
		this.retrieveRecords = retrieveRecords;
	}

	/**
	 * @return Returns the headerName.
	 */
	public String getHeaderName() {
		return headerName;
	}

	/**
	 * @param headerName The headerName to set.
	 */
	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}

	/**
	 * @return Returns the entityid.
	 */
	public int getEntityid() {
		return entityid;
	}

	/**
	 * @param entityid The entityid to set.
	 */
	public void setEntityid(int entityid) {
		this.entityid = entityid;
	}

	/**
	 * @return Returns the searchString.
	 */
	public String getSearchString() {
		return searchString;
	}

	/**
	 * @param searchString The searchString to set.
	 */
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	/**
	 * @return Returns the searchList.
	 */
	public List getSearchList() {
		String searchString = this.getRequest().getParameter("searchString");
		String queryName = this.getRequest().getParameter("queryName");
		if (("getAdminOptionListForBenefitLevel").equals(queryName)) {
			this.benefitLvlId = this.getRequest().getParameter("benefitLvlId");
		}
		String headerName = this.getRequest().getParameter("headerName");
		String entityId = this.getRequest().getParameter("entityId");
		if (null != searchString && index == 0) {
			this.searchString = searchString;
			this.queryName = queryName;
			if (("getAdminOptionListForBenefitLevel").equals(queryName)) {
				this.benefitLvlId = this.getRequest().getParameter(
						"benefitLvlId");
			}
			this.headerName = headerName;
			this.entityid = Integer.parseInt(entityId);
			index++;
			this.filterResult();
			
		}
		return searchList;
	}

	
	
	/**
	 * @param searchList The searchList to set.
	 */
	public void setSearchList(List searchList) {
		this.searchList = searchList;
	}

	/**
	 * @param records The records to set.
	 */
	public void setRecords(String records) {
		this.records = records;
	}

	/**
	 * @return Returns the popAction.
	 */
	public int getPopAction() {
		return popAction;
	}

	/**
	 * @param popAction The popAction to set.
	 */
	public void setPopAction(int popAction) {
		this.popAction = popAction;
	}

	/**
	 * @return Returns the benefitLvlId.
	 */
	public String getBenefitLvlId() {
		return benefitLvlId;
	}

	/**
	 * @param benefitLvlId The benefitLvlId to set.
	 */
	public void setBenefitLvlId(String benefitLvlId) {
		this.benefitLvlId = benefitLvlId;
	}
	
	/**
	 * @return Returns the dataType.
	 */
	public String getDataType() {
		return dataType;
	}
	/**
	 * @param dataType The dataType to set.
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	/**
	 * @return Returns the pva.
	 */
	public String getPva() {
		return pva;
	}
	/**
	 * @param pva The pva to set.
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
	 * @param qualifier The qualifier to set.
	 */
	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}
	/**
	 * @return Returns the term.
	 */
	public String getTerm() {
		return term;
	}
	/**
	 * @param term The term to set.
	 */
	public void setTerm(String term) {
		this.term = term;
	}
	/**
	 * @return Returns the filter.
	 */
	public String getFilter() {
		return filter;
	}
	/**
	 * @param filter The filter to set.
	 */
	public void setFilter(String filter) {
		this.filter = filter;
	}
	/**
	 * @return Returns the spsId.
	 */
	public String getSpsId() {
		return spsId;
	}
	/**
	 * @param spsId The spsId to set.
	 */
	public void setSpsId(String spsId) {
		this.spsId = spsId;
	}
	
	/**
	 * @return Returns the recordsForAdminMethodPopup.
	 */
	public String getRecordsForAdminMethodPopup() {
		PopupRequest request = getPopupRequest();
		request.setPopAction(WebConstants.FILTER_ACTION);
		this.popAction = WebConstants.FILTER_ACTION;
		PopupResponse response = null;
		response = (PopupResponse) executeService(request);
		if (null != response) {
			setValuesToBackinBeanForAdminMethodPopup(response);
		}
		return null;
	}
	/**
	 * @param recordsForAdminMethodPopup The recordsForAdminMethodPopup to set.
	 */
	public void setRecordsForAdminMethodPopup(String recordsForAdminMethodPopup) {
		this.recordsForAdminMethodPopup = recordsForAdminMethodPopup;
	}
	/**
	 * @param searchListForAdmin The searchListForAdmin to set.
	 */
	public void setSearchListForAdmin(List searchListForAdmin) {
		this.searchListForAdmin = searchListForAdmin;
	}
	/**
	 * @return Returns the keyValue.
	 */
	public String getKeyValue() {
		return keyValue;
	}
	/**
	 * @param keyValue The keyValue to set.
	 */
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}
	
	/**
	 * @return Returns the processingMethodList.
	 */
	public List getProcessingMethodList() {
		return processingMethodList;
	}
	/**
	 * @param processingMethodList The processingMethodList to set.
	 */
	public void setProcessingMethodList(List processingMethodList) {
		this.processingMethodList = processingMethodList;
	}
	/**
	 * @return Returns the processingList.
	 */
	public List getProcessingList() {
		return processingList;
	}
	/**
	 * @param processingList The processingList to set.
	 */
	public void setProcessingList(List processingList) {
		this.processingList = processingList;
	}
	/**
	 * @return Returns the recordsGreaterThanMaxSize.
	 */
	public boolean isRecordsGreaterThanMaxSize() {
		return recordsGreaterThanMaxSize;
	}
	/**
	 * @param recordsGreaterThanMaxSize The recordsGreaterThanMaxSize to set.
	 */
	public void setRecordsGreaterThanMaxSize(boolean recordsGreaterThanMaxSize) {
		this.recordsGreaterThanMaxSize = recordsGreaterThanMaxSize;
	}
}