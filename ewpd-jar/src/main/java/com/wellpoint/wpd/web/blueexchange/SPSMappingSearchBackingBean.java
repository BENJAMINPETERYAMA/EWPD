/*
 * SPSMappingSearchBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.blueexchange;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.blueexchange.bo.SPSMappingBO;
import com.wellpoint.wpd.common.blueexchange.request.DeleteSpsMappingRequest;
import com.wellpoint.wpd.common.blueexchange.request.SearchSPSMappingRequest;
import com.wellpoint.wpd.common.blueexchange.response.DeleteSpsMappingResponse;
import com.wellpoint.wpd.common.blueexchange.response.SearchSPSMaintainResponse;
import com.wellpoint.wpd.common.blueexchange.request.SPSMappingRetrieveRequest;
import com.wellpoint.wpd.common.blueexchange.response.SPSMappingViewResponse;
import com.wellpoint.wpd.common.blueexchange.vo.SPSMappingSearchVO;
import com.wellpoint.wpd.common.blueexchange.vo.SPSMappingVO;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */

public class SPSMappingSearchBackingBean extends WPDBackingBean {
	private String spsSearchString;

	private List searchResults;

	private String spsParameterForList;

	private String eb01ValueForList;

	private String eb02ValueForList;

	private String eb06ValueForList;

	private String eb09ValueForList;

	private String hsd1ValueForList;

	private String hsd2ValueForList;

	private String hsd3ValueForList;

	private String hsd4ValueForList;

	private String hsd5ValueForList;

	private String hsd6ValueForList;
	
	private String accumulatorSPSIDForList;

	private String spsMappingView;

	private String spsParameter;

	private String eb01Value;

	private String eb02Value;

	private String eb06Value;

	private String eb09Value;

	private String hsd1Value;

	private String hsd2Value;

	private String hsd3Value;

	private String hsd4Value;

	private String hsd5Value;

	private String hsd6Value;
	
	private String accumulatorSPSID;

	private String spsParameterDesc;

	private String eb01ValueDesc;

	private String eb02ValueDesc;

	private String eb06ValueDesc;

	private String eb09ValueDesc;

	private String hsd1ValueDesc;

	//private String hsd2ValueDesc;
	private String hsd3ValueDesc;

	//private String hsd4ValueDesc;
	private String hsd5ValueDesc;

	//private String hsd6ValueDesc;
	
	private String accumulatorSPSDesc;
	
	private String createdUser;

	private String lastChangedUser;

	private Date createdDate;

	private Date lastChangedDate;

	public String selectedSpsId;

	List spsParameterList;

	List eb01ValueList;

	List eb02ValueList;

	List eb06ValueList;

	List eb09ValueList;

	List hsd1ValueList;

	List hsd2ValueList = new ArrayList();

	List hsd3ValueList;

	List hsd4ValueList = new ArrayList();

	List hsd5ValueList;

	List hsd6ValueList = new ArrayList();
	
	List accumulatorSPSIDList;

	private List validationMessages = new ArrayList();

	/*
	 * methode for search Results
	 *  @ return List of BO
	 * 
	 *  
	 */

	public String spsMappingSearch() {

		SearchSPSMappingRequest searchSPSMappingRequest = (SearchSPSMappingRequest) this
				.getServiceRequest(ServiceManager.SEARCH_SPS_MAPPING_REQUEST);
		if (!validate()) {
			addAllMessagesToRequest(this.validationMessages);
			return WebConstants.EMPTY_STRING;
		}
		searchSPSMappingRequest = setValuesToRequest(searchSPSMappingRequest);
		if (!validateList()) {
			addAllMessagesToRequest(this.validationMessages);
			return WebConstants.EMPTY_STRING;
		}
		searchSPSMappingRequest.setAction(BusinessConstants.LOCATE_SPS_MAPPING);
		SearchSPSMaintainResponse searchSPSMaintainResponse = (SearchSPSMaintainResponse) executeService(searchSPSMappingRequest);
		if (null != searchSPSMaintainResponse.getSearchList()
				&& searchSPSMaintainResponse.getSearchList().size() > 0) {
			searchResults = searchSPSMaintainResponse.getSearchList();
		}
		return WebConstants.EMPTY_STRING;
	}

	/*
	 * This methode for validating search inputs return a boolean value Alsu set
	 * a validation message if search inputs are invalid
	 *  
	 */
	private boolean validate() {
		boolean valid = true;
		if ((null == this.getSpsParameterForList() || "".equals(this
				.getSpsParameterForList()))
				&& (null == this.getEb01ValueForList() || "".equals(this
						.getEb01ValueForList()))
				&& (null == this.getEb02ValueForList() || "".equals(this
						.getEb02ValueForList()))
				&& (null == this.getEb06ValueForList() || "".equals(this
						.getEb06ValueForList()))
				&& (null == this.getEb09ValueForList() || "".equals(this
						.getEb09ValueForList()))
				&& (null == this.getHsd1ValueForList() || "".equals(this
						.getHsd1ValueForList()))
				&& (null == this.getHsd2ValueForList() || "".equals(this
						.getHsd2ValueForList()))
				&& (null == this.getHsd3ValueForList() || "".equals(this
						.getHsd3ValueForList()))
				&& (null == this.getHsd4ValueForList() || "".equals(this
						.getHsd4ValueForList()))
				&& (null == this.getHsd5ValueForList() || "".equals(this
						.getHsd5ValueForList()))
				&& (null == this.getHsd6ValueForList() || "".equals(this
						.getHsd6ValueForList()))
		        &&(null == this.getAccumulatorSPSIDForList() || "".equals(this.getAccumulatorSPSIDForList()))){
			valid = false;
		}
		if (!valid) {
			validationMessages.add(new ErrorMessage(
					WebConstants.ATLEAST_ONE_SEARCH));
		}
		return valid;
	}

	private boolean validateList() {
		boolean valid = true;
		StringBuffer listOfmessage = new StringBuffer("");
		if (null != this.spsParameterList
				&& this.spsParameterList.size() > 1000) {
			listOfmessage.append("SPS Parameter ID");
			valid = false;
		}
		if (null != this.eb01ValueList && this.eb01ValueList.size() > 1000) {
			if (null != listOfmessage && !(listOfmessage.toString()).equals(""))
				listOfmessage.append(",");
			listOfmessage.append("EB01");
			valid = false;
		}
		if (null != this.eb02ValueList && this.eb02ValueList.size() > 1000) {
			if (null != listOfmessage && !(listOfmessage.toString()).equals(""))
				listOfmessage.append(",");
			listOfmessage.append("EB02");
			valid = false;
		}
		if (null != this.eb06ValueList && this.eb06ValueList.size() > 1000) {
			if (null != listOfmessage && !(listOfmessage.toString()).equals(""))
				listOfmessage.append(",");
			listOfmessage.append("EB06");
			valid = false;
		}
		if (null != this.eb09ValueList && this.eb09ValueList.size() > 1000) {
			if (null != listOfmessage && !(listOfmessage.toString()).equals(""))
				listOfmessage.append(",");
			listOfmessage.append("EB09");
			valid = false;
		}
		if (null != this.hsd1ValueList && this.hsd1ValueList.size() > 1000) {
			if (null != listOfmessage && !(listOfmessage.toString()).equals(""))
				listOfmessage.append(",");
			listOfmessage.append("HSD1");
			valid = false;
		}
		if (null != this.hsd2ValueList && this.hsd2ValueList.size() > 1000) {
			if (null != listOfmessage && !(listOfmessage.toString()).equals(""))
				listOfmessage.append(",");
			listOfmessage.append("HSD2");
			valid = false;
		}
		if (null != this.hsd3ValueList && this.hsd3ValueList.size() > 1000) {
			if (null != listOfmessage && !(listOfmessage.toString()).equals(""))
				listOfmessage.append(",");
			listOfmessage.append("HSD3");
			valid = false;
		}
		if (null != this.hsd4ValueList && this.hsd4ValueList.size() > 1000) {
			if (null != listOfmessage && !(listOfmessage.toString()).equals(""))
				listOfmessage.append(",");
			listOfmessage.append("HSD4");
			valid = false;
		}
		if (null != this.hsd5ValueList && this.hsd5ValueList.size() > 1000) {
			if (null != listOfmessage && !(listOfmessage.toString()).equals(""))
				listOfmessage.append(",");
			listOfmessage.append("HSD5");
			valid = false;
		}
		if (null != this.hsd6ValueList && this.hsd6ValueList.size() > 1000) {
			if (null != listOfmessage && !(listOfmessage.toString()).equals(""))
				listOfmessage.append(",");
			listOfmessage.append("HSD6");
			valid = false;
		}if(null!=this.accumulatorSPSIDList && this.accumulatorSPSIDList.size()>1000){
			if(null!=listOfmessage && !(listOfmessage.toString()).equals(""))
				listOfmessage.append(",");
				listOfmessage.append("ACCUMULATOR REFERENCE");
			valid = false;
		}
		if (!valid) {
			ErrorMessage message = new ErrorMessage(
					WebConstants.SEARCH_GREATER_LIMIT_SIZE);
			message.setParameters(new String[] { listOfmessage.toString() });
			validationMessages.add(message);
		}
		return valid;

	}

	/*
	 * methode for setting all input values o request
	 * 
	 * it separate tilda separated input string to a input lst
	 *  
	 */
	private SearchSPSMappingRequest setValuesToRequest(
			SearchSPSMappingRequest searchSPSMappingRequest) {

		SPSMappingSearchVO sPSMappingSearchVO = new SPSMappingSearchVO();
		spsParameterList = WPDStringUtil.getListFromTildaString(
				this.spsParameterForList, 2, 2, 2);
		eb01ValueList = WPDStringUtil.getListFromTildaString(
				this.eb01ValueForList, 2, 2, 2);
		eb02ValueList = WPDStringUtil.getListFromTildaString(
				this.eb02ValueForList, 2, 2, 2);
		eb06ValueList = WPDStringUtil.getListFromTildaString(
				this.eb06ValueForList, 2, 2, 2);
		eb09ValueList = WPDStringUtil.getListFromTildaString(
				this.eb09ValueForList, 2, 2, 2);
		hsd1ValueList = WPDStringUtil.getListFromTildaString(
				this.hsd1ValueForList, 2, 2, 2);
		if (null != this.hsd2ValueForList && !"".equals(this.hsd2ValueForList))
			hsd2ValueList.add(this.hsd2ValueForList);
		hsd3ValueList = WPDStringUtil.getListFromTildaString(
				this.hsd3ValueForList, 2, 2, 2);
		if (null != this.hsd4ValueForList && !"".equals(this.hsd4ValueForList))
			hsd4ValueList.add(this.hsd4ValueForList);
		hsd5ValueList = WPDStringUtil.getListFromTildaString(
				this.hsd5ValueForList, 2, 2, 2);
		if (null != this.hsd6ValueForList && !"".equals(this.hsd6ValueForList))
			hsd6ValueList.add(this.hsd6ValueForList);
		accumulatorSPSIDList = WPDStringUtil.getListFromTildaString(this.accumulatorSPSIDForList, 2, 2, 2);

		sPSMappingSearchVO.setSpsParameterList(spsParameterList);
		sPSMappingSearchVO.setEb01ValueList(eb01ValueList);
		sPSMappingSearchVO.setEb02ValueList(eb02ValueList);
		sPSMappingSearchVO.setEb06ValueList(eb06ValueList);
		sPSMappingSearchVO.setEb09ValueList(eb09ValueList);
		sPSMappingSearchVO.setHsd1ValueList(hsd1ValueList);
		sPSMappingSearchVO.setHsd2ValueList(hsd2ValueList);
		sPSMappingSearchVO.setHsd3ValueList(hsd3ValueList);
		sPSMappingSearchVO.setHsd4ValueList(hsd4ValueList);
		sPSMappingSearchVO.setHsd5ValueList(hsd5ValueList);
		sPSMappingSearchVO.setHsd6ValueList(hsd6ValueList);
		sPSMappingSearchVO.setAccumulatorSPSIDList(accumulatorSPSIDList);

		searchSPSMappingRequest.setSPSMappingSearchVO(sPSMappingSearchVO);

		return searchSPSMappingRequest;
	}

	/**
	 * @return Returns the eb01ValueForList.
	 */
	public String getEb01ValueForList() {
		return eb01ValueForList;
	}

	/**
	 * @param eb01ValueForList
	 *            The eb01ValueForList to set.
	 */
	public void setEb01ValueForList(String eb01ValueForList) {
		this.eb01ValueForList = eb01ValueForList;
	}

	/**
	 * @return Returns the eb02ValueForList.
	 */
	public String getEb02ValueForList() {
		return eb02ValueForList;
	}

	/**
	 * @param eb02ValueForList
	 *            The eb02ValueForList to set.
	 */
	public void setEb02ValueForList(String eb02ValueForList) {
		this.eb02ValueForList = eb02ValueForList;
	}

	/**
	 * @return Returns the eb06ValueForList.
	 */
	public String getEb06ValueForList() {
		return eb06ValueForList;
	}

	/**
	 * @param eb06ValueForList
	 *            The eb06ValueForList to set.
	 */
	public void setEb06ValueForList(String eb06ValueForList) {
		this.eb06ValueForList = eb06ValueForList;
	}

	/**
	 * @return Returns the eb09ValueForList.
	 */
	public String getEb09ValueForList() {
		return eb09ValueForList;
	}

	/**
	 * @param eb09ValueForList
	 *            The eb09ValueForList to set.
	 */
	public void setEb09ValueForList(String eb09ValueForList) {
		this.eb09ValueForList = eb09ValueForList;
	}

	/**
	 * @return Returns the hsd1ValueForList.
	 */
	public String getHsd1ValueForList() {
		return hsd1ValueForList;
	}

	/**
	 * @param hsd1ValueForList
	 *            The hsd1ValueForList to set.
	 */
	public void setHsd1ValueForList(String hsd1ValueForList) {
		this.hsd1ValueForList = hsd1ValueForList;
	}

	/**
	 * @return Returns the hsd2ValueForList.
	 */
	public String getHsd2ValueForList() {
		return hsd2ValueForList;
	}

	/**
	 * @param hsd2ValueForList
	 *            The hsd2ValueForList to set.
	 */
	public void setHsd2ValueForList(String hsd2ValueForList) {
		this.hsd2ValueForList = hsd2ValueForList;
	}

	/**
	 * @return Returns the hsd3ValueForList.
	 */
	public String getHsd3ValueForList() {
		return hsd3ValueForList;
	}

	/**
	 * @param hsd3ValueForList
	 *            The hsd3ValueForList to set.
	 */
	public void setHsd3ValueForList(String hsd3ValueForList) {
		this.hsd3ValueForList = hsd3ValueForList;
	}

	/**
	 * @return Returns the hsd4ValueForList.
	 */
	public String getHsd4ValueForList() {
		return hsd4ValueForList;
	}

	/**
	 * @param hsd4ValueForList
	 *            The hsd4ValueForList to set.
	 */
	public void setHsd4ValueForList(String hsd4ValueForList) {
		this.hsd4ValueForList = hsd4ValueForList;
	}

	/**
	 * @return Returns the hsd5ValueForList.
	 */
	public String getHsd5ValueForList() {
		return hsd5ValueForList;
	}

	/**
	 * @param hsd5ValueForList
	 *            The hsd5ValueForList to set.
	 */
	public void setHsd5ValueForList(String hsd5ValueForList) {
		this.hsd5ValueForList = hsd5ValueForList;
	}

	/**
	 * @return Returns the hsd6ValueForList.
	 */
	public String getHsd6ValueForList() {
		return hsd6ValueForList;
	}

	/**
	 * @param hsd6ValueForList
	 *            The hsd6ValueForList to set.
	 */
	public void setHsd6ValueForList(String hsd6ValueForList) {
		this.hsd6ValueForList = hsd6ValueForList;
	}

	/**
	 * @return Returns the searchResults.
	 */
	public List getSearchResults() {
		if (searchResults != null)
			for (int i = 0; i < searchResults.size(); i++) {
				SPSMappingBO mappingBO = (SPSMappingBO) searchResults.get(i);
				if (mappingBO.getSpsParameterDesc() != null
						&& mappingBO.getSpsParameterDesc().length() > 15)
					mappingBO.setSpsParameterDesc(mappingBO
							.getSpsParameterDesc().substring(0, 16)
							+ "...");
			}
		return searchResults;
	}

	/**
	 * @param searchResults
	 *            The searchResults to set.
	 */
	public void setSearchResults(List searchResults) {
		this.searchResults = searchResults;
	}

	/**
	 * @return Returns the spsParameterForList.
	 */
	public String getSpsParameterForList() {
		return spsParameterForList;
	}

	/**
	 * @param spsParameterForList
	 *            The spsParameterForList to set.
	 */
	public void setSpsParameterForList(String spsParameterForList) {
		this.spsParameterForList = spsParameterForList;
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
	 * @return Returns the eb01Value.
	 */
	public String getEb01Value() {
		return eb01Value;
	}

	/**
	 * @param eb01Value
	 *            The eb01Value to set.
	 */
	public void setEb01Value(String eb01Value) {
		this.eb01Value = eb01Value;
	}

	/**
	 * @return Returns the eb01ValueDesc.
	 */
	public String getEb01ValueDesc() {
		return eb01ValueDesc;
	}

	/**
	 * @param eb01ValueDesc
	 *            The eb01ValueDesc to set.
	 */
	public void setEb01ValueDesc(String eb01ValueDesc) {
		this.eb01ValueDesc = eb01ValueDesc;
	}

	/**
	 * @return Returns the eb02Value.
	 */
	public String getEb02Value() {
		return eb02Value;
	}

	/**
	 * @param eb02Value
	 *            The eb02Value to set.
	 */
	public void setEb02Value(String eb02Value) {
		this.eb02Value = eb02Value;
	}

	/**
	 * @return Returns the eb02ValueDesc.
	 */
	public String getEb02ValueDesc() {
		return eb02ValueDesc;
	}

	/**
	 * @param eb02ValueDesc
	 *            The eb02ValueDesc to set.
	 */
	public void setEb02ValueDesc(String eb02ValueDesc) {
		this.eb02ValueDesc = eb02ValueDesc;
	}

	/**
	 * @return Returns the eb06Value.
	 */
	public String getEb06Value() {
		return eb06Value;
	}

	/**
	 * @param eb06Value
	 *            The eb06Value to set.
	 */
	public void setEb06Value(String eb06Value) {
		this.eb06Value = eb06Value;
	}

	/**
	 * @return Returns the eb06ValueDesc.
	 */
	public String getEb06ValueDesc() {
		return eb06ValueDesc;
	}

	/**
	 * @param eb06ValueDesc
	 *            The eb06ValueDesc to set.
	 */
	public void setEb06ValueDesc(String eb06ValueDesc) {
		this.eb06ValueDesc = eb06ValueDesc;
	}

	/**
	 * @return Returns the eb09Value.
	 */
	public String getEb09Value() {
		return eb09Value;
	}

	/**
	 * @param eb09Value
	 *            The eb09Value to set.
	 */
	public void setEb09Value(String eb09Value) {
		this.eb09Value = eb09Value;
	}

	/**
	 * @return Returns the eb09ValueDesc.
	 */
	public String getEb09ValueDesc() {
		return eb09ValueDesc;
	}

	/**
	 * @param eb09ValueDesc
	 *            The eb09ValueDesc to set.
	 */
	public void setEb09ValueDesc(String eb09ValueDesc) {
		this.eb09ValueDesc = eb09ValueDesc;
	}

	/**
	 * @return Returns the hsd1Value.
	 */
	public String getHsd1Value() {
		return hsd1Value;
	}

	/**
	 * @param hsd1Value
	 *            The hsd1Value to set.
	 */
	public void setHsd1Value(String hsd1Value) {
		this.hsd1Value = hsd1Value;
	}

	/**
	 * @return Returns the hsd1ValueDesc.
	 */
	public String getHsd1ValueDesc() {
		return hsd1ValueDesc;
	}

	/**
	 * @param hsd1ValueDesc
	 *            The hsd1ValueDesc to set.
	 */
	public void setHsd1ValueDesc(String hsd1ValueDesc) {
		this.hsd1ValueDesc = hsd1ValueDesc;
	}

	/**
	 * @return Returns the hsd2Value.
	 */
	public String getHsd2Value() {
		return hsd2Value;
	}

	/**
	 * @param hsd2Value
	 *            The hsd2Value to set.
	 */
	public void setHsd2Value(String hsd2Value) {
		this.hsd2Value = hsd2Value;
	}

	/**
	 * @return Returns the hsd3Value.
	 */
	public String getHsd3Value() {
		return hsd3Value;
	}

	/**
	 * @param hsd3Value
	 *            The hsd3Value to set.
	 */
	public void setHsd3Value(String hsd3Value) {
		this.hsd3Value = hsd3Value;
	}

	/**
	 * @return Returns the hsd3ValueDesc.
	 */
	public String getHsd3ValueDesc() {
		return hsd3ValueDesc;
	}

	/**
	 * @param hsd3ValueDesc
	 *            The hsd3ValueDesc to set.
	 */
	public void setHsd3ValueDesc(String hsd3ValueDesc) {
		this.hsd3ValueDesc = hsd3ValueDesc;
	}

	/**
	 * @return Returns the hsd4Value.
	 */
	public String getHsd4Value() {
		return hsd4Value;
	}

	/**
	 * @param hsd4Value
	 *            The hsd4Value to set.
	 */
	public void setHsd4Value(String hsd4Value) {
		this.hsd4Value = hsd4Value;
	}

	/**
	 * @return Returns the hsd5Value.
	 */
	public String getHsd5Value() {
		return hsd5Value;
	}

	/**
	 * @param hsd5Value
	 *            The hsd5Value to set.
	 */
	public void setHsd5Value(String hsd5Value) {
		this.hsd5Value = hsd5Value;
	}

	/**
	 * @return Returns the hsd5ValueDesc.
	 */
	public String getHsd5ValueDesc() {
		return hsd5ValueDesc;
	}

	/**
	 * @param hsd5ValueDesc
	 *            The hsd5ValueDesc to set.
	 */
	public void setHsd5ValueDesc(String hsd5ValueDesc) {
		this.hsd5ValueDesc = hsd5ValueDesc;
	}

	/**
	 * @return Returns the hsd6Value.
	 */
	public String getHsd6Value() {
		return hsd6Value;
	}

	/**
	 * @param hsd6Value
	 *            The hsd6Value to set.
	 */
	public void setHsd6Value(String hsd6Value) {
		this.hsd6Value = hsd6Value;
	}

	/**
	 * @return Returns the lastChangedDate.
	 */
	public Date getLastChangedDate() {
		return lastChangedDate;
	}

	/**
	 * @param lastChangedDate
	 *            The lastChangedDate to set.
	 */
	public void setLastChangedDate(Date lastChangedDate) {
		this.lastChangedDate = lastChangedDate;
	}

	/**
	 * @return Returns the lastChangedUser.
	 */
	public String getLastChangedUser() {
		return lastChangedUser;
	}

	/**
	 * @param lastChangedUser
	 *            The lastChangedUser to set.
	 */
	public void setLastChangedUser(String lastChangedUser) {
		this.lastChangedUser = lastChangedUser;
	}

	/**
	 * @return Returns the spsSearchString.
	 */
	public String getSpsSearchString() {
		return spsSearchString;
	}

	/**
	 * @param spsSearchString
	 *            The spsSearchString to set.
	 */
	public void setSpsSearchString(String spsSearchString) {
		this.spsSearchString = spsSearchString;
	}

	/**
	 * This method is called when user clicks view option. It gets the
	 * spsParameter Id from the requestParameter and set to request. this
	 * request is sent to SPSMappingBusinessService and get back the response as
	 * value Object from businessService. spsMappingAttributes of backing bean
	 * are set from the value object.
	 * 
	 * @return Returns the spsMappingView.
	 */
	public String getSpsMappingView() {
		SPSMappingRetrieveRequest mappingViewRequest = (SPSMappingRetrieveRequest) this
				.getServiceRequest(ServiceManager.SPS_MAPPING_RETRIEVE_REQUEST);
		if (null != getRequest().getParameter("spsParamterId")) {
			mappingViewRequest.setSpsParameter(getRequest().getParameter(
					"spsParamterId"));
		} else {
			SPSMappingSessionObject mappingSessionObject = (SPSMappingSessionObject) getSession()
					.getAttribute("spsMappingSession");
			if (mappingSessionObject != null)
				mappingViewRequest.setSpsParameter(mappingSessionObject
						.getSpsParameterId());
		}
		SPSMappingViewResponse mappingViewResponse = (SPSMappingViewResponse) this
				.executeService(mappingViewRequest);
		if (null != mappingViewResponse) {
			setValuesToBackingBean(mappingViewResponse.getMappingVO());
		}
		this
				.setBreadCrumbText("Administration>> Blue Exchange >> SPS Mapping ("
						+ this.spsParameter + ") >> View");
		SPSMappingSessionObject mappingSessionObject = new SPSMappingSessionObject();
		mappingSessionObject.setSpsParameterId(this.spsParameter);
		getSession().setAttribute("spsMappingSession", mappingSessionObject);
		return spsMappingView;
	}

	/**
	 * @param spsMappingView
	 *            The spsMappingView to set.
	 */
	public void setSpsMappingView(String spsMappingView) {
		this.spsMappingView = spsMappingView;
	}

	/**
	 * This method is to get the spsMappingAttributes from the response object
	 * and set it to the backing bean spsMappingattributes
	 * 
	 * @param mappingVO
	 *  
	 */
	private void setValuesToBackingBean(SPSMappingVO mappingVO) {
		this.spsParameter = mappingVO.getSpsParameter();
		this.spsParameterDesc = mappingVO.getSpsParameterDesc();
		this.eb01Value = mappingVO.getEb01Value();
		this.eb01ValueDesc = mappingVO.getEb01ValueDesc();
		this.eb02Value = mappingVO.getEb02Value();
		this.eb02ValueDesc = mappingVO.getEb02ValueDesc();
		this.eb06Value = mappingVO.getEb06Value();
		this.eb06ValueDesc = mappingVO.getEb06ValueDesc();
		this.eb09Value = mappingVO.getEb09Value();
		this.eb09ValueDesc = mappingVO.getEb09ValueDesc();
		this.hsd1Value = mappingVO.getHsd1Value();
		this.hsd1ValueDesc = mappingVO.getHsd1ValueDesc();
		this.hsd2Value = mappingVO.getHsd2Value();
		this.hsd3Value = mappingVO.getHsd3Value();
		this.hsd3ValueDesc = mappingVO.getHsd3ValueDesc();
		this.hsd4Value = mappingVO.getHsd4Value();
		this.hsd5Value = mappingVO.getHsd5Value();
		this.hsd5ValueDesc = mappingVO.getHsd5ValueDesc();
		this.hsd6Value = mappingVO.getHsd6Value();
		this.accumulatorSPSID = mappingVO.getAccummulatorSPSID();
		this.accumulatorSPSDesc = mappingVO
				.getAccummulatorSPSDesc();
		this.createdUser = mappingVO.getCreatedUser();
		this.createdDate = mappingVO.getCreatedTimestamp();
		this.lastChangedUser = mappingVO.getLastUpdatedUser();
		this.lastChangedDate = mappingVO.getLastUpdatedTimestamp();

	}

	/*
	 * 
	 * delete one sps mapping. return success full message once delete a sps
	 * mapping @DeleteSpsMappingRequest @DeleteSpsMappingResponse
	 */
	public String deleteSps() {
		DeleteSpsMappingRequest deleteSpsMappingRequest = (DeleteSpsMappingRequest) this
				.getServiceRequest(ServiceManager.DELETE_SPS__MAPPING_REQUEST);
		deleteSpsMappingRequest.setSpsParameter(this.selectedSpsId);
		DeleteSpsMappingResponse deleteSpsMappingResponse = (DeleteSpsMappingResponse) this
				.executeService(deleteSpsMappingRequest);
		List list = deleteSpsMappingResponse.getMessages();
		this.spsMappingSearch();
		super.addAllMessagesToRequest(list);
		if (null == searchResults || searchResults.size() == 0) {
			setInputtoEmpty();
		}
		return WebConstants.EMPTY_STRING;
	}

	/*
	 * method for setting the jsp input parameters to "";
	 *  
	 */
	public void setInputtoEmpty() {
		this.spsParameterForList = "";
		this.eb01ValueForList = "";
		this.eb02ValueForList = "";
		this.eb06ValueForList = "";
		this.eb09ValueForList = "";
		this.hsd1ValueForList = "";
		this.hsd2ValueForList = "";
		this.hsd3ValueForList = "";
		this.hsd4ValueForList = "";
		this.hsd5ValueForList = "";
		this.hsd6ValueForList = "";
	}

	/**
	 * @return Returns the spsMappingView.
	 */
	public String getSpsMappingPrintBreadCrumb() {
		return "Administration>> Blue Exchange >> SPS Mapping ("
				+ this.spsParameter + ") >> Print";
	}

	/**
	 * @return Returns the spsParameter.
	 */
	public String getSpsParameter() {
		return spsParameter;
	}

	/**
	 * @param spsParameter
	 *            The spsParameter to set.
	 */
	public void setSpsParameter(String spsParameter) {
		this.spsParameter = spsParameter;
	}

	/**
	 * @return Returns the spsParameterDesc.
	 */
	public String getSpsParameterDesc() {
		return spsParameterDesc;
	}

	/**
	 * @param spsParameterDesc The spsParameterDesc to set.
	 */
	public void setSpsParameterDesc(String spsParameterDesc) {
		this.spsParameterDesc = spsParameterDesc;
	}

	/**
	 * @return Returns the selectedSpsId.
	 */
	public String getSelectedSpsId() {
		return selectedSpsId;
	}

	/**
	 * @param selectedSpsId The selectedSpsId to set.
	 */
	public void setSelectedSpsId(String selectedSpsId) {
		this.selectedSpsId = selectedSpsId;
	}

	/**
	 * @return Returns the accumulatorSPSDesc.
	 */
	public String getAccumulatorSPSDesc() {
		return accumulatorSPSDesc;
	}
	/**
	 * @param accumulatorSPSDesc The accumulatorSPSDesc to set.
	 */
	public void setAccumulatorSPSDesc(String accumulatorSPSDesc) {
		this.accumulatorSPSDesc = accumulatorSPSDesc;
	}
	/**
	 * @return Returns the accumulatorSPSID.
	 */
	public String getAccumulatorSPSID() {
		return accumulatorSPSID;
	}
	/**
	 * @param accumulatorSPSID The accumulatorSPSID to set.
	 */
	public void setAccumulatorSPSID(String accumulatorSPSID) {
		this.accumulatorSPSID = accumulatorSPSID;
	}
	/**
	 * @return Returns the accumulatorSPSIDForList.
	 */
	public String getAccumulatorSPSIDForList() {
		return accumulatorSPSIDForList;
	}
	/**
	 * @param accumulatorSPSIDForList The accumulatorSPSIDForList to set.
	 */
	public void setAccumulatorSPSIDForList(String accumulatorSPSIDForList) {
		this.accumulatorSPSIDForList = accumulatorSPSIDForList;
	}
	/**
	 * @return Returns the accumulatorSPSIDList.
	 */
	public List getAccumulatorSPSIDList() {
		return accumulatorSPSIDList;
	}
	/**
	 * @param accumulatorSPSIDList The accumulatorSPSIDList to set.
	 */
	public void setAccumulatorSPSIDList(List accumulatorSPSIDList) {
		this.accumulatorSPSIDList = accumulatorSPSIDList;
	}
}