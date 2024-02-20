/*
 * IndicativeMappingBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.indicativemapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.Message;
import com.wellpoint.wpd.common.indicativemapping.request.CreateIndicativeMappingRequest;
import com.wellpoint.wpd.common.indicativemapping.request.DeleteIndicativeMappingRequest;
import com.wellpoint.wpd.common.indicativemapping.request.EditIndicativeMappingRequest;
import com.wellpoint.wpd.common.indicativemapping.request.RetrieveIndicativeMappingRequest;
import com.wellpoint.wpd.common.indicativemapping.request.SearchIndicativeMappingRequest;
import com.wellpoint.wpd.common.indicativemapping.response.CreateIndicativeMappingResponse;
import com.wellpoint.wpd.common.indicativemapping.response.DeleteIndicativeMappingResponse;
import com.wellpoint.wpd.common.indicativemapping.response.EditIndicativeMappingResponse;
import com.wellpoint.wpd.common.indicativemapping.response.RetrieveIndicativeMappingResponse;
import com.wellpoint.wpd.common.indicativemapping.response.SearchIndicativeMappingResponse;
import com.wellpoint.wpd.common.indicativemapping.vo.IndicativeMappingVO;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class IndicativeMappingBackingBean extends WPDBackingBean {

	private String indicativeSegment;

	private String indicativeSegmentDesc;

	private String segmentNumber;

	private String spsParameter;

	private String spsParameterDesc;

	private String benefit;

	private String indMapDesc;

	private String createdUser;

	private String lastUpdatedUser;

	private Date createdDate;

	private Date lastUpdatedDate;

	private List searchResults;

	private String indicativeCriteria;

	private String spsParamCriteria;

	private String benefitNameCriteria;

	private String locatebreadCrumbText = "Administration >> Indicative Mapping >> Locate";

	private List validationMessages = new ArrayList();

	private String loadIndicativeMapping;

	private String description;

	private boolean indicativereq;

	private boolean spsreq;

	private boolean benefitreq;

	private String prevInd;

	private String prevSps;

	private String prevBen;

	/**
	 * @return Returns the prevBen.
	 */
	public String getPrevBen() {
		return prevBen;
	}

	/**
	 * @param prevBen
	 *            The prevBen to set.
	 */
	public void setPrevBen(String prevBen) {
		this.prevBen = prevBen;
	}

	/**
	 * @return Returns the prevInd.
	 */
	public String getPrevInd() {
		return prevInd;
	}

	/**
	 * @param prevInd
	 *            The prevInd to set.
	 */
	public void setPrevInd(String prevInd) {
		this.prevInd = prevInd;
	}

	/**
	 * @return Returns the prevSps.
	 */
	public String getPrevSps() {
		return prevSps;
	}

	/**
	 * @param prevSps
	 *            The prevSps to set.
	 */
	public void setPrevSps(String prevSps) {
		this.prevSps = prevSps;
	}

	/**
	 * @return Returns the benefitreq.
	 */
	public boolean isBenefitreq() {
		return benefitreq;
	}

	/**
	 * @param benefitreq
	 *            The benefitreq to set.
	 */
	public void setBenefitreq(boolean benefitreq) {
		this.benefitreq = benefitreq;
	}

	/**
	 * @return Returns the indicativereq.
	 */
	public boolean isIndicativereq() {
		return indicativereq;
	}

	/**
	 * @param indicativereq
	 *            The indicativereq to set.
	 */
	public void setIndicativereq(boolean indicativereq) {
		this.indicativereq = indicativereq;
	}

	/**
	 * @return Returns the spsreq.
	 */
	public boolean isSpsreq() {
		return spsreq;
	}

	/**
	 * @param spsreq
	 *            The spsreq to set.
	 */
	public void setSpsreq(boolean spsreq) {
		this.spsreq = spsreq;
	}

	/**
	 * @return Returns the segmentNumber.
	 */
	public String getSegmentNumber() {
		return segmentNumber;
	}

	/**
	 * @param segmentNumber
	 *            The segmentNumber to set.
	 */
	public void setSegmentNumber(String segmentNumber) {
		this.segmentNumber = segmentNumber;
	}

	/**
	 * @return Returns the loadIndicativeMapping.
	 */
	public String getLoadIndicativeMapping() {
		return retrieveIndicativeMapping();
	}

	/**
	 * @param loadIndicativeMapping
	 *            The loadIndicativeMapping to set.
	 */
	public void setLoadIndicativeMapping(String loadIndicativeMapping) {
		this.loadIndicativeMapping = loadIndicativeMapping;
	}

	private void setRetrieveBreadcrumb(String action) {

		if ("view".equalsIgnoreCase(action)) {
			String viewBreadCrumbText = "Administration >> Indicative Mapping ("
					+ this.getIndicativeSegmentDesc() + ") >> View";
			getRequest().setAttribute("breadCrumbText", viewBreadCrumbText);
		} else if ("edit".equalsIgnoreCase(action)) {
			String editBreadCrumbText = "Administration >> Indicative Mapping ("
					+ this.getIndicativeSegmentDesc() + ") >> Edit";
			getRequest().setAttribute("breadCrumbText", editBreadCrumbText);
		}
	}

	/**
	 * @return Returns the indMapDesc.
	 */
	public String getIndMapDesc() {
		return indMapDesc;
	}

	/**
	 * @param indMapDesc
	 *            The indMapDesc to set.
	 */
	public void setIndMapDesc(String indMapDesc) {
		this.indMapDesc = indMapDesc;
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
	 * @return Returns the indicativeSegmentDesc.
	 */
	public String getIndicativeSegmentDesc() {
		return indicativeSegmentDesc;
	}

	/**
	 * @param indicativeSegmentDesc
	 *            The indicativeSegmentDesc to set.
	 */
	public void setIndicativeSegmentDesc(String indicativeSegmentDesc) {
		this.indicativeSegmentDesc = indicativeSegmentDesc;
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
	 * @return Returns the locatebreadCrumbText.
	 */
	public String getLocatebreadCrumbText() {
		return locatebreadCrumbText;
	}

	/**
	 * @param locatebreadCrumbText
	 *            The locatebreadCrumbText to set.
	 */
	public void setLocatebreadCrumbText(String locatebreadCrumbText) {
		this.locatebreadCrumbText = locatebreadCrumbText;
	}

	/**
	 * @return Returns the spsParameterDesc.
	 */
	public String getSpsParameterDesc() {
		return spsParameterDesc;
	}

	/**
	 * @param spsParameterDesc
	 *            The spsParameterDesc to set.
	 */
	public void setSpsParameterDesc(String spsParameterDesc) {
		this.spsParameterDesc = spsParameterDesc;
	}

	/**
	 * @return Returns the validationMessages.
	 */
	public List getValidationMessages() {
		return validationMessages;
	}

	/**
	 * @param validationMessages
	 *            The validationMessages to set.
	 */
	public void setValidationMessages(List validationMessages) {
		this.validationMessages = validationMessages;
	}

	private void setLocateBreadCrumb() {
		getRequest().setAttribute("breadCrumbText", locatebreadCrumbText);
	}

	/**
	 * @return Returns the benefitNameCriteria.
	 */
	public String getBenefitNameCriteria() {
		return benefitNameCriteria;
	}

	/**
	 * @param benefitNameCriteria
	 *            The benefitNameCriteria to set.
	 */
	public void setBenefitNameCriteria(String benefitNameCriteria) {
		this.benefitNameCriteria = benefitNameCriteria;
	}

	/**
	 * @return Returns the indicativeCriteria.
	 */
	public String getIndicativeCriteria() {
		return indicativeCriteria;
	}

	/**
	 * @param indicativeCriteria
	 *            The indicativeCriteria to set.
	 */
	public void setIndicativeCriteria(String indicativeCriteria) {
		this.indicativeCriteria = indicativeCriteria;
	}

	/**
	 * @return Returns the spsParamCriteria.
	 */
	public String getSpsParamCriteria() {
		return spsParamCriteria;
	}

	/**
	 * @param spsParamCriteria
	 *            The spsParamCriteria to set.
	 */
	public void setSpsParamCriteria(String spsParamCriteria) {
		this.spsParamCriteria = spsParamCriteria;
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

	/**
	 * 
	 * @return
	 */
	public String editIndicativeMapping() {

		String[] indicatives = (this.getIndicativeCriteria()).split("~");
		String[] spsCodes = (this.getSpsParamCriteria()).split("~");
		String[] bnNames = (this.getBenefitNameCriteria()).split("~");

		String[] previndicatives = (this.getPrevInd()).split("~");
		String[] prevspsCodes = (this.getPrevSps()).split("~");
		String[] prevbnNames = (this.getPrevBen()).split("~");
		if (!validateCreateRequest()) {
			getRequest().setAttribute("indSeg", previndicatives[0]);
			getRequest().setAttribute("spsParam", prevspsCodes[0]);
			getRequest().setAttribute("benNm", prevbnNames[1]);
			getRequest().setAttribute("action", "edit");
			if (null == this.getSpsParamCriteria()
					|| "".equals(this.getSpsParamCriteria())) {
		    getRequest().setAttribute("spsVFlg", "SET");
			}
			if (null == this.getBenefitNameCriteria()
					|| "".equals(this.getBenefitNameCriteria())) {
		    getRequest().setAttribute("benVFlg", "SET");
			}
			List messages = new ArrayList();
			messages.add(new ErrorMessage(
					WebConstants.INDICATIVE_MAPPING_CREATE_VALID));
			getRequest().setAttribute("messageId", messages);
			return "";
		}

		IndicativeMappingVO indicativeMappingVO = new IndicativeMappingVO();
		indicativeMappingVO.setIndicativeSegment(indicatives[0]);
		indicativeMappingVO.setSpsParameter(spsCodes[0]);
		indicativeMappingVO.setBenefit(bnNames[1]);
		if(this.indMapDesc != null)
		if(this.indMapDesc.length()<=250)	
		indicativeMappingVO.setDescription(this.indMapDesc.toUpperCase());
		else if(this.indMapDesc.length()>250){
			getRequest().setAttribute("indSeg", previndicatives[0]);
			getRequest().setAttribute("spsParam", prevspsCodes[0]);
			getRequest().setAttribute("benNm", prevbnNames[1]);
		
			
			getRequest().setAttribute("action", "edit");
			getRequest().setAttribute("maxDesc", this.indMapDesc);
			List messages = new ArrayList();
			ErrorMessage message = new ErrorMessage(
					WebConstants.INDICATIVE_MAPPING_DESC_LIMIT);
			message.setParameters(new String[] { "250"});
			messages.add(message);
		
			
			
			getRequest().setAttribute("indSegCrit", this.getIndicativeCriteria());
			getRequest().setAttribute("spsParamCrit",this.getSpsParamCriteria());
			getRequest().setAttribute("benNmCrit", this.getBenefitNameCriteria());
			getRequest().setAttribute("invalidDesc", "true");
			getRequest().setAttribute("createdUser", this.createdUser);
			getRequest().setAttribute("createdDate", this.createdDate);
			getRequest().setAttribute("lastUpdatedUser", this.lastUpdatedUser);
			getRequest().setAttribute("lastUpdatedDate", this.lastUpdatedDate);
			
			
			
			
			getRequest().setAttribute("messageId", messages);
			return "";	
		}	
		if (!(previndicatives[0].equalsIgnoreCase(indicatives[0])
				&& prevspsCodes[0].equalsIgnoreCase(spsCodes[0]) && bnNames[1]
				.equalsIgnoreCase(prevbnNames[1])))
			if (checkMappingExists(indicatives[0], bnNames[1], spsCodes[0]) > 0) {
				getRequest().setAttribute("indSeg", indicatives[0]);
				getRequest().setAttribute("spsParam", spsCodes[0]);
				getRequest().setAttribute("benNm", bnNames[1]);
				getRequest().setAttribute("indMapDesc", this.indMapDesc);
				
				getRequest().setAttribute("createdUser", this.createdUser);
				getRequest().setAttribute("createdDate", this.createdDate);
				getRequest().setAttribute("lastUpdatedUser", this.lastUpdatedUser);
				getRequest().setAttribute("lastUpdatedDate", this.lastUpdatedDate);
				getRequest().setAttribute("checkMappingExists", "true");
				getRequest().setAttribute("action", "edit");
			
				getRequest().setAttribute("previndSeg", this.getPrevInd());
				getRequest().setAttribute("prevspsParam", this.getPrevSps());
				getRequest().setAttribute("prevbenNm", this.getPrevBen());
				
				List messages = new ArrayList();
				messages.add(new ErrorMessage(
						WebConstants.INDICATIVE_MAPPING_CREATE_UNIQUE_VALID));
				getRequest().setAttribute("messageId", messages);
				return "";
			}

		List messageList = new ArrayList();
		EditIndicativeMappingRequest request = (EditIndicativeMappingRequest) this
				.getServiceRequest(ServiceManager.EDIT_IND_MAPPING_REQUEST);

		indicativeMappingVO.setPrevInd(previndicatives[0]);
		indicativeMappingVO.setPrevSps(prevspsCodes[0]);
		indicativeMappingVO.setPrevBen(prevbnNames[1]);
		request.setIndicativeMappingVO(indicativeMappingVO);
		EditIndicativeMappingResponse editIndicativeMappingResponse = (EditIndicativeMappingResponse) this
				.executeService(request);
		 List msgRemList = (ArrayList)getRequest().getAttribute("messages");
	        if(msgRemList != null && msgRemList.size()!=0){
	        	Iterator it = msgRemList.iterator();
	        	while(it.hasNext()){
	        		Message msg = (Message)it.next();
	        		if(msg.getId().equals("search.result.not.found")){
	        			it.remove();
	        		}
	        	}
	        }
		getRequest().setAttribute("indSeg", indicatives[0]);
		getRequest().setAttribute("spsParam", spsCodes[0]);
		getRequest().setAttribute("benNm", bnNames[1]);
		getRequest().setAttribute("action", "edit");
		getRequest().setAttribute("messageId",
				editIndicativeMappingResponse.getMessages());

		return "editIndicativeMapping";
	}

	/**
	 * 
	 * @return
	 */
	public String retrieveIndicativeMapping() {

		String indiactiveSegmentCode = (String) getRequest().getParameter(
				"indSeg");
		String spsParameterCode = (String) getRequest()
				.getParameter("spsParam");
		String benefitName = (String) getRequest().getParameter("benNm");
		String action = (String) getRequest().getParameter("action");
		String spsVFlg = (String) getRequest().getParameter("spsVFlg");
		String benVFlg = (String) getRequest().getParameter("benVFlg");
		

		if (indiactiveSegmentCode == null && spsParameterCode == null
				&& benefitName == null && action == null) {
			indiactiveSegmentCode = (String) getRequest()
					.getAttribute("indSeg");
			spsParameterCode = (String) getRequest().getAttribute("spsParam");
			benefitName = (String) getRequest().getAttribute("benNm");
			action = (String) getRequest().getAttribute("action");
			spsVFlg = (String) getRequest().getAttribute("spsVFlg");
			benVFlg = (String) getRequest().getAttribute("benVFlg");
		}

		IndicativeMappingVO indicativeMappingVO = new IndicativeMappingVO();
		indicativeMappingVO.setIndicativeSegment(indiactiveSegmentCode);
		indicativeMappingVO.setSpsParameter(spsParameterCode);
		indicativeMappingVO.setBenefit(benefitName);
		RetrieveIndicativeMappingRequest retrieveIndicativeMappingRequest = (RetrieveIndicativeMappingRequest) this
				.getServiceRequest(ServiceManager.RETRIEVE_IND_MAPPING_REQUEST);
		retrieveIndicativeMappingRequest
				.setIndicativeMappingVO(indicativeMappingVO);
		RetrieveIndicativeMappingResponse retrieveIndicativeMappingResponse = (RetrieveIndicativeMappingResponse) this
				.executeService(retrieveIndicativeMappingRequest);
		setAttributevalues(action, retrieveIndicativeMappingResponse,spsVFlg,benVFlg);	
		if (getRequest().getAttribute("checkMappingExists")!=null && ((String) getRequest().getAttribute("checkMappingExists")).equalsIgnoreCase("true")) {
			this.setIndMapDesc((getRequest().getAttribute("indMapDesc")).toString());
			this.setCreatedUser((getRequest().getAttribute("createdUser")).toString());
			this.setCreatedDate((Date) getRequest().getAttribute("createdDate"));
			this.setLastUpdatedDate((Date) getRequest().getAttribute("lastUpdatedDate"));
			this.setLastUpdatedUser((getRequest().getAttribute("lastUpdatedUser")).toString());
			
			this.setPrevBen((getRequest().getAttribute("prevbenNm")).toString());
			this.setPrevInd((getRequest().getAttribute("previndSeg")).toString());
			this.setPrevSps((getRequest().getAttribute("prevspsParam")).toString());
			
	
		
		}	
		
		
		if (getRequest().getAttribute("invalidDesc")!=null && ((String) getRequest().getAttribute("invalidDesc")).equalsIgnoreCase("true")) {
			this.setIndMapDesc((getRequest().getAttribute("maxDesc")).toString());
//			this.setCreatedUser((getRequest().getAttribute("createdUser")).toString());
//			this.setCreatedDate((Date) getRequest().getAttribute("createdDate"));
//			this.setLastUpdatedDate((Date) getRequest().getAttribute("lastUpdatedDate"));
//			this.setLastUpdatedUser((getRequest().getAttribute("lastUpdatedUser")).toString());
//			
			this.setPrevBen(this.getBenefitNameCriteria());
			this.setPrevInd(this.getIndicativeCriteria());
			this.setPrevSps(this.getSpsParamCriteria());
			
			this.setIndicativeCriteria((getRequest().getAttribute("indSegCrit")).toString());
			this.setSpsParamCriteria((getRequest().getAttribute("spsParamCrit")).toString());
			this.setBenefitNameCriteria((getRequest().getAttribute("benNmCrit")).toString());
			this.setCreatedUser((getRequest().getAttribute("createdUser")).toString());
			this.setCreatedDate((Date) getRequest().getAttribute("createdDate"));
			this.setLastUpdatedDate((Date) getRequest().getAttribute("lastUpdatedDate"));
			this.setLastUpdatedUser((getRequest().getAttribute("lastUpdatedUser")).toString());
		}	
		
		setRetrieveBreadcrumb(action);
		return "";
	}

	/**
	 * 
	 * @return
	 */
	public String deleteIndicativeMapping() {

		DeleteIndicativeMappingRequest deleteIndicativeMappingRequest = (DeleteIndicativeMappingRequest) this
				.getServiceRequest(ServiceManager.DELETE_IND_MAPPING_REQUEST);
		IndicativeMappingVO indicativeMappingVO = new IndicativeMappingVO();
		indicativeMappingVO.setIndicativeSegment(this.getIndicativeSegment());
		indicativeMappingVO.setSpsParameter(this.getSpsParameter());
		indicativeMappingVO.setBenefit(this.getBenefit());
		deleteIndicativeMappingRequest
				.setIndicativeMappingVO(indicativeMappingVO);
		DeleteIndicativeMappingResponse deleteIndicativeMappingResponse = (DeleteIndicativeMappingResponse) this
				.executeService(deleteIndicativeMappingRequest);

		String search = searchIndicativeMapping();
		addMessageToRequest((Message) deleteIndicativeMappingResponse
				.getMessages().get(0));
		return search;
	}

	/**
	 * 
	 * @return
	 */
	public String searchIndicativeMapping() {
		if (!validateSearchRequest()) {
			addAllMessagesToRequest(this.validationMessages);
			setBreadCrumbText("Administration >> Indicative Mapping >> Locate");
			return WebConstants.EMPTY_STRING;
		}
        String errorParam="";
        boolean valid =true;
		SearchIndicativeMappingRequest searchIndicativeMappingRequest = (SearchIndicativeMappingRequest) this
				.getServiceRequest(ServiceManager.SEARCH_IND_MAPPING_REQUEST);

		List indicativeList = WPDStringUtil.getListFromTildaString(
				this.indicativeCriteria, 2, 1, 2);
		if (indicativeList != null && indicativeList.size() > 0){
			if(indicativeList.size()>1000){
				if(errorParam != null && !"".equalsIgnoreCase(errorParam))
				errorParam += ",";	
				errorParam += "Indicative";
				valid=false;
			}else{
				searchIndicativeMappingRequest.setIndicativeList(indicativeList);
			}
		}
		
		List spsList = WPDStringUtil.getListFromTildaString(
				this.spsParamCriteria, 2, 1, 2);
		if (spsList != null && spsList.size() > 0){
			if(spsList.size()>1000){
				if(errorParam != null && !"".equalsIgnoreCase(errorParam))
					errorParam += ",";	
				errorParam += "SPS Parameter";
				valid=false;
			}else{
				searchIndicativeMappingRequest.setSpsList(spsList);
			}
		}
		List benefitList = WPDStringUtil.getListFromTildaString(
				this.benefitNameCriteria, 2, 2, 2);
		if (benefitList != null && benefitList.size() > 0){
			if(benefitList.size()>1000){
				if(errorParam != null && !"".equalsIgnoreCase(errorParam))
					errorParam += ",";	
				errorParam += "Benefit Name";
				valid=false;
			}else{
				searchIndicativeMappingRequest.setBenefitList(benefitList);
			}
		}
		if(!valid){
			ErrorMessage message = new ErrorMessage(
					WebConstants.SEARCH_GREATER_LIMIT_SIZE);
			message.setParameters(new String[] { errorParam});
			this.validationMessages.add(message);
			addAllMessagesToRequest(this.validationMessages);
			setBreadCrumbText("Administration >> Indicative Mapping >> Locate");
			return "";
		}

		searchIndicativeMappingRequest
				.setAction(BusinessConstants.LOCATE_INDICATIVE_MAPPING);

		SearchIndicativeMappingResponse searchIndicativeMappingResponse = (SearchIndicativeMappingResponse) this
				.executeService(searchIndicativeMappingRequest);

		if (null != searchIndicativeMappingResponse.getSearchList()
				&& searchIndicativeMappingResponse.getSearchList().size() > 0) {
			searchResults = searchIndicativeMappingResponse.getSearchList();
		}
		setLocateBreadCrumb();
		return "";
	}

	public String createIndicativeMapping() {
		if (!validateCreateRequest()){
			setBreadCrumbText("Administration >> Indicative Mapping >> Create");
			return "";
		}

		CreateIndicativeMappingRequest createIndicativeMappingRequest = (CreateIndicativeMappingRequest) this
				.getServiceRequest(ServiceManager.CREATE_IND_MAPPING_REQUEST);

		String[] indicatives = (this.getIndicativeCriteria()).split("~");
		String[] spsCodes = (this.getSpsParamCriteria()).split("~");
		String[] bnNames = (this.getBenefitNameCriteria()).split("~");

		IndicativeMappingVO indicativeMappingVO = new IndicativeMappingVO();
		indicativeMappingVO.setIndicativeSegment(indicatives[0]);
		indicativeMappingVO.setSpsParameter(spsCodes[0]);
		indicativeMappingVO.setBenefit(bnNames[1]);
		if(this.indMapDesc != null)
		if(this.indMapDesc.length()<=250)	
		indicativeMappingVO.setDescription(this.indMapDesc.toUpperCase());
		else if(this.indMapDesc.length()>250){	
			ErrorMessage message = new ErrorMessage(
					WebConstants.INDICATIVE_MAPPING_DESC_LIMIT);
			message.setParameters(new String[] { "250"});
			addMessageToRequest(message);
			setBreadCrumbText("Administration >> Indicative Mapping >> Create");
			return "";	
		}
		if (checkMappingExists(indicatives[0], bnNames[1], spsCodes[0]) > 0) {
			
			addMessageToRequest(new ErrorMessage(
					WebConstants.INDICATIVE_MAPPING_CREATE_UNIQUE_VALID));
			setBreadCrumbText("Administration >> Indicative Mapping >> Create");
			return "";
		}

		createIndicativeMappingRequest
				.setIndicativeMappingVO(indicativeMappingVO);

		CreateIndicativeMappingResponse createIndicativeMappingResponse = (CreateIndicativeMappingResponse) this
				.executeService(createIndicativeMappingRequest);
        List msgRemList = (ArrayList)getRequest().getAttribute("messages");
        if(msgRemList != null && msgRemList.size()!=0){
        	Iterator it = msgRemList.iterator();
        	while(it.hasNext()){
        		Message msg = (Message)it.next();
        		if(msg.getId().equals("search.result.not.found")){
        			it.remove();
        		}
        	}
        }
		getRequest().setAttribute("indSeg", indicatives[0]);
		getRequest().setAttribute("spsParam", spsCodes[0]);
		getRequest().setAttribute("benNm", bnNames[1]);
		getRequest().setAttribute("action", "edit");
		getRequest().setAttribute("messageId",
				createIndicativeMappingResponse.getMessages());

		return "editIndicativeMapping";
	}

	private int checkMappingExists(String indicativeCode, String benefitName,
			String spsParamCode) {
		int mappingExists = 0;

		SearchIndicativeMappingRequest searchIndicativeMappingRequest = (SearchIndicativeMappingRequest) this
				.getServiceRequest(ServiceManager.SEARCH_IND_MAPPING_REQUEST);

		List indicativeList = new ArrayList();
		indicativeList.add(indicativeCode);
		searchIndicativeMappingRequest.setIndicativeList(indicativeList);

		List spsList = new ArrayList();
		spsList.add(spsParamCode);
		searchIndicativeMappingRequest.setSpsList(spsList);

		List benefitList = new ArrayList();
		benefitList.add(benefitName);
		searchIndicativeMappingRequest.setBenefitList(benefitList);

		searchIndicativeMappingRequest
				.setAction(BusinessConstants.LOCATE_INDICATIVE_MAPPING);

		SearchIndicativeMappingResponse searchIndicativeMappingResponse = (SearchIndicativeMappingResponse) this
				.executeService(searchIndicativeMappingRequest);

		if (null != searchIndicativeMappingResponse) {

			List searchResult = searchIndicativeMappingResponse.getSearchList();

			if (null != searchResult && searchResult.size() > 0) {

				mappingExists = searchResult.size();

			}
		}
		return mappingExists;

	}

	//	private boolean locateValidation(){
	//		
	//	}

	/**
	 * @return Returns the benefit.
	 */
	public String getBenefit() {
		return benefit;
	}

	/**
	 * @param benefit
	 *            The benefit to set.
	 */
	public void setBenefit(String benefit) {
		this.benefit = benefit;
	}

	/**
	 * @return Returns the indicativeSegment.
	 */
	public String getIndicativeSegment() {
		return indicativeSegment;
	}

	/**
	 * @param indicativeSegment
	 *            The indicativeSegment to set.
	 */
	public void setIndicativeSegment(String indicativeSegment) {
		this.indicativeSegment = indicativeSegment;
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

	private void setAttributevalues(String action,
			RetrieveIndicativeMappingResponse indicativeMappingResponse,String spsVFlg,String benVFlg) {
		if ("edit".equalsIgnoreCase(action)) {
			int arbitry = 1000;
			this.setIndicativeCriteria(indicativeMappingResponse
					.getIndicativeSegment()
					+ "~"
					+ indicativeMappingResponse.getIndicativeSegmentDesc()
					+ "~" + indicativeMappingResponse.getSegmentNumber());
			if(spsVFlg==null)
			this.setSpsParamCriteria(indicativeMappingResponse
					.getSpsParameter()
					+ "~" + indicativeMappingResponse.getSpsParameterDesc());
			if(benVFlg==null)
			this.setBenefitNameCriteria("" + arbitry + "~"
					+ indicativeMappingResponse.getBenefit());
			List messages = (List) getRequest().getAttribute("messageId");
			if (messages != null && messages.size() > 0)
				addAllMessagesToRequest(messages);
			
			this.setIndMapDesc(indicativeMappingResponse.getIndMapDesc());
			String maxDesc= (String)getRequest().getAttribute("maxDesc");
			if (maxDesc != null && maxDesc.length() > 250)
			this.setIndMapDesc(maxDesc);
			this.setLastUpdatedDate(indicativeMappingResponse
					.getLastUpdatedDate());
			this.setLastUpdatedUser(indicativeMappingResponse
					.getLastUpdatedUser());
			this.setCreatedDate(indicativeMappingResponse.getCreatedDate());
			this.setCreatedUser(indicativeMappingResponse.getCreatedUser());
			this.setIndicativeSegmentDesc(indicativeMappingResponse
					.getIndicativeSegmentDesc());
			this.setPrevInd(indicativeMappingResponse.getIndicativeSegment()
					+ "~"
					+ indicativeMappingResponse.getIndicativeSegmentDesc()
					+ "~" + indicativeMappingResponse.getSegmentNumber());
			this.setPrevSps(indicativeMappingResponse.getSpsParameter() + "~"
					+ indicativeMappingResponse.getSpsParameterDesc());
			this.setPrevBen("" + arbitry + "~"
					+ indicativeMappingResponse.getBenefit());

		} else {
			this.setIndicativeSegment(indicativeMappingResponse
					.getIndicativeSegment());
			this.setIndicativeSegmentDesc(indicativeMappingResponse
					.getIndicativeSegmentDesc());
			this.setSegmentNumber(indicativeMappingResponse.getSegmentNumber());
			this.setSpsParameter(indicativeMappingResponse.getSpsParameter());
			this.setSpsParameterDesc(indicativeMappingResponse
					.getSpsParameterDesc());
			this.setBenefit(indicativeMappingResponse.getBenefit());
			this.setIndMapDesc(indicativeMappingResponse.getIndMapDesc());
			this.setLastUpdatedDate(indicativeMappingResponse
					.getLastUpdatedDate());
			this.setLastUpdatedUser(indicativeMappingResponse
					.getLastUpdatedUser());
			this.setCreatedDate(indicativeMappingResponse.getCreatedDate());
			this.setCreatedUser(indicativeMappingResponse.getCreatedUser());
		}
	}

	/*
	 * This methode for validating search inputs return a boolean value Alsu set
	 * a validation message if search inputs are invalid
	 *  
	 */
	private boolean validateSearchRequest() {
		boolean valid = true;
		if ((null == this.getIndicativeCriteria() || "".equals(this
				.getIndicativeCriteria()))
				&& (null == this.getSpsParamCriteria() || "".equals(this
						.getSpsParamCriteria()))
				&& (null == this.getBenefitNameCriteria() || "".equals(this
						.getBenefitNameCriteria()))) {
			valid = false;
		}
		if (!valid) {
			validationMessages.add(new ErrorMessage(
					WebConstants.ATLEAST_ONE_SEARCH));
		}
		return valid;
	}

	private boolean validateCreateRequest() {
		boolean valid = true;
		if (null == this.getIndicativeCriteria()
				|| "".equals(this.getIndicativeCriteria())) {
			this.setIndicativereq(true);
			valid = false;
		}
		if (null == this.getSpsParamCriteria()
				|| "".equals(this.getSpsParamCriteria())) {
			this.setSpsreq(true);
			valid = false;
		}
		if (null == this.getBenefitNameCriteria()
				|| "".equals(this.getBenefitNameCriteria())) {
			this.setBenefitreq(true);
			valid = false;
		}

		if (!valid) {
			addMessageToRequest(new ErrorMessage(
					WebConstants.INDICATIVE_MAPPING_CREATE_VALID));
		}
		return valid;
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

	public String locateEditIndMap() {

		getRequest().setAttribute("indSeg", this.getIndicativeSegment());
		getRequest().setAttribute("spsParam", this.getSpsParameter());
		getRequest().setAttribute("benNm", this.getBenefit());
		getRequest().setAttribute("dummyValue", "dummyValue");
		getRequest().setAttribute("action", "edit");

		return "editIndicativeMapping";
	}
}