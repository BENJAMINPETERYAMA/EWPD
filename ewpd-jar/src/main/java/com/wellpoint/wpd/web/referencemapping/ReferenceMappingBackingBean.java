/*
 * Created on Jul 17, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.referencemapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import javax.faces.model.SelectItem;

import org.owasp.esapi.ESAPI;

import com.wellpoint.wpd.business.framework.bo.AuditFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.Message;
import com.wellpoint.wpd.common.popup.bo.PopupFilterBO;
import com.wellpoint.wpd.common.popup.request.PopupRequest;
import com.wellpoint.wpd.common.popup.response.PopupResponse;
import com.wellpoint.wpd.common.referencemapping.request.CreateReferenceMappingRequest;
import com.wellpoint.wpd.common.referencemapping.request.DeleteReferenceMappingRequest;
import com.wellpoint.wpd.common.referencemapping.request.EditReferenceMappingRequest;
import com.wellpoint.wpd.common.referencemapping.request.SearchReferenceMappingRequest;
import com.wellpoint.wpd.common.referencemapping.response.CreateReferenceMappingResponse;
import com.wellpoint.wpd.common.referencemapping.response.DeleteReferenceMappingResponse;
import com.wellpoint.wpd.common.referencemapping.response.EditReferenceMappingResponse;
import com.wellpoint.wpd.common.referencemapping.response.SearchReferenceMappingResponse;
import com.wellpoint.wpd.common.referencemapping.vo.ReferencemappingVO;
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

public class ReferenceMappingBackingBean extends WPDBackingBean {

	private String referenceCriteria;

	private String cobolValue;

	private String description;

	private boolean referenceReq;

	private String typeCriteria;

	private String referenceCriteriaEdit;

	private String termCriteria;

	private String termDesc;

	private String qualifierCriteria;

	private String qualifierDesc;

	private String pvaCriteria;

	private String dataTypeCriteria;

	private boolean requiredDataType;

	private boolean requiredProviderArrangement;

	private boolean requiredQualifier;

	private boolean requiredTerm;

	private boolean requiredType;

	private List searchResults;

	private String createdUser;

	private String lastUpdatedUser;

	private String createdDate;

	private String changeDate;

	private String dataTypeEdit;

	private String dataTypeEditDesc;

	private boolean aggregateTerm;

	private String prevReferenceCriteria;

	private String prevTypeCriteria;

	private String prevTermCriteria;

	private String prevQualifierCriteria;

	private String prevpvaCriteria;

	private String prevDataTypeCriteria;

	private String referenceCriteriaDelete;

	private String typeCriteriaDelete;

	private String termCriteriaDelete;

	private String qualifierCriteriaDelete;

	private String pvaCriteriaDelete;

	private String dataTypeCriteriaDelete;

	private String loadReferenceMapping;

	private boolean aggregateQualifier;

	private String timeZone;

	private List mappingTypes;

	/**
	 * @return Returns the referenceCriteriaEdit.
	 */
	public String getReferenceCriteriaEdit() {
		return referenceCriteriaEdit;
	}

	/**
	 * @param referenceCriteriaEdit
	 *            The referenceCriteriaEdit to set.
	 */
	public void setReferenceCriteriaEdit(String referenceCriteriaEdit) {
		this.referenceCriteriaEdit = referenceCriteriaEdit;
	}

	/**
	 * @return Returns the referenceCriteriaDelete.
	 */
	public String getReferenceCriteriaDelete() {
		return referenceCriteriaDelete;
	}

	/**
	 * @return Returns the cobolValue.
	 */
	public String getCobolValue() {
		return cobolValue;
	}

	/**
	 * @param cobolValue
	 *            The cobolValue to set.
	 */
	public void setCobolValue(String cobolValue) {
		this.cobolValue = cobolValue;
	}

	/**
	 * @param referenceCriteriaDelete
	 *            The referenceCriteriaDelete to set.
	 */
	public void setReferenceCriteriaDelete(String referenceCriteriaDelete) {
		this.referenceCriteriaDelete = referenceCriteriaDelete;
	}

	/**
	 * @return Returns the aggregateQualifier.
	 */
	public boolean isAggregateQualifier() {
		return aggregateQualifier;
	}

	/**
	 * @param aggregateQualifier
	 *            The aggregateQualifier to set.
	 */
	public void setAggregateQualifier(boolean aggregateQualifier) {
		this.aggregateQualifier = aggregateQualifier;
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
	 * @return Returns the dataTypeEdit.
	 */
	public String getDataTypeEdit() {
		return dataTypeEdit;
	}

	/**
	 * @param dataTypeEdit
	 *            The dataTypeEdit to set.
	 */
	public void setDataTypeEdit(String dataTypeEdit) {
		this.dataTypeEdit = dataTypeEdit;
	}

	/**
	 * @return Returns the changeDate.
	 */
	public String getChangeDate() {
		return changeDate;
	}

	/**
	 * @param changeDate
	 *            The changeDate to set.
	 */
	public void setChangeDate(String changeDate) {
		this.changeDate = changeDate;
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

	public String getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            The createdDate to set.
	 */
	public void setCreatedDate(String createdDate) {
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

	private Date lastUpdatedDate;

	/**
	 * @return Returns the requiredTerm.
	 */

	/**
	 * @return Returns the requiredQualifier.
	 */
	public boolean isRequiredQualifier() {
		return requiredQualifier;
	}

	/**
	 * @param requiredQualifier
	 *            The requiredQualifier to set.
	 */
	public void setRequiredQualifier(boolean requiredQualifier) {
		this.requiredQualifier = requiredQualifier;
	}

	/**
	 * @return Returns the requiredProviderArrangement.
	 */
	public boolean isRequiredProviderArrangement() {
		return requiredProviderArrangement;
	}

	/**
	 * @param requiredProviderArrangement
	 *            The requiredProviderArrangement to set.
	 */
	public void setRequiredProviderArrangement(
			boolean requiredProviderArrangement) {
		this.requiredProviderArrangement = requiredProviderArrangement;
	}

	/**
	 * @return Returns the requiredDataType.
	 */
	public boolean isRequiredDataType() {
		return requiredDataType;
	}

	/**
	 * @param requiredDataType
	 *            The requiredDataType to set.
	 */
	public void setRequiredDataType(boolean requiredDataType) {
		this.requiredDataType = requiredDataType;
	}

	/**
	 * @return Returns the checkForCopy.
	 */

	/**
	 * @return Returns the referenceReq.
	 */
	public boolean isReferenceReq() {
		return referenceReq;
	}

	/**
	 * @param referenceReq
	 *            The referenceReq to set.
	 */
	public void setReferenceReq(boolean referenceReq) {
		this.referenceReq = referenceReq;
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
	 * @return Returns the referenceCriteria.
	 */
	public String getReferenceCriteria() {
		return referenceCriteria;
	}

	/**
	 * @param referenceCriteria
	 *            The referenceCriteria to set.
	 */
	public void setReferenceCriteria(String referenceCriteria) {
		this.referenceCriteria = referenceCriteria;
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
	 * @return Returns the typeCriteria.
	 */
	public String getTypeCriteria() {
		return typeCriteria;
	}

	/**
	 * @param typeCriteria
	 *            The typeCriteria to set.
	 */
	public void setTypeCriteria(String typeCriteria) {
		this.typeCriteria = typeCriteria;
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

	/*
	 * Create Reference Mapping
	 *  
	 */
	public String createReferenceMapping() {

		CreateReferenceMappingRequest createReferenceMappingRequest = (CreateReferenceMappingRequest) this
				.getServiceRequest(ServiceManager.CREATE_REF_MAPPING_REQUEST);

		ReferencemappingVO referenceMappingVO = new ReferencemappingVO();
		if (!validateCreateRequest()) {
			setBreadCrumbText("Administration >> Reference Mapping >> Create");
			return "";
		}
		populateVO(referenceMappingVO);

		int size = checkMappingExists(referenceMappingVO.getReferenceId(),
				referenceMappingVO.getType(), referenceMappingVO.getTerm(),
				referenceMappingVO.getQualifier(), referenceMappingVO.getPva(),
				(referenceMappingVO.getDatatype()));

		if (size > 0) {
			addMessageToRequest(new ErrorMessage(
					WebConstants.REF_MAPPING_CREATE_UNIQUE_VALID));
			setBreadCrumbText("Administration >> Reference Mapping >> Create");
			return "";
		}

		createReferenceMappingRequest.setReferencemappingVO(referenceMappingVO);
		CreateReferenceMappingResponse createReferenceMappingResponse = (CreateReferenceMappingResponse) this
				.executeService(createReferenceMappingRequest);
		List messages = createReferenceMappingResponse.getMessages();

		getReferenceMapping(referenceMappingVO.getReferenceId(),
				referenceMappingVO.getType(), referenceMappingVO.getTerm(),
				referenceMappingVO.getQualifier(), referenceMappingVO.getPva(),
				(referenceMappingVO.getDatatype()));

		this.setPrevTypeCriteria(this.typeCriteria);
		this.setPrevTermCriteria(this.termCriteria);
		this.setPrevQualifierCriteria(this.qualifierCriteria);
		this.setPrevpvaCriteria(this.pvaCriteria);
		this.setPrevDataTypeCriteria(this.dataTypeCriteria);

		addAllMessagesToRequest(messages);
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

		String breadCrumb = "Administration >> Reference Mapping ("
				+ referenceMappingVO.getReferenceId() + ")>> Edit";
		setBreadCrumbText(breadCrumb);
		return "editspsIDMapping";

	}

	/*
	 * delete the reference mapping
	 */

	public String deleteIndicativeMapping() {

		DeleteReferenceMappingRequest deleteReferenceMappingRequest = (DeleteReferenceMappingRequest) this
				.getServiceRequest(ServiceManager.DELETE_REF_MAPPING_REQUEST);
		ReferencemappingVO referenceMappingVO = new ReferencemappingVO();

		//	referenceMappingVO.setReferenceId(this.referenceCriteria);

		if (null != this.termCriteriaDelete
				&& !"".equalsIgnoreCase(this.termCriteriaDelete))
			referenceMappingVO.setTermCriteriaDelete(this.termCriteriaDelete);
		else
			this.setTermCriteriaDelete("null");

		if (null != this.termCriteriaDelete
				&& !"".equalsIgnoreCase(this.pvaCriteriaDelete))
			referenceMappingVO.setPvaCriteriaDelete(this.pvaCriteriaDelete);
		else
			this.setPvaCriteriaDelete("null");

		if (null != this.qualifierCriteriaDelete
				&& !"".equalsIgnoreCase(this.qualifierCriteriaDelete))
			referenceMappingVO
					.setQualifierCriteriaDelete(this.qualifierCriteriaDelete);
		else
			this.setQualifierCriteriaDelete("null");

		referenceMappingVO
				.setReferenceCriteriaDelete(this.referenceCriteriaDelete);
		referenceMappingVO.setTypeCriteriaDelete(this.typeCriteriaDelete);
		referenceMappingVO.setTermCriteriaDelete(this.termCriteriaDelete);

		referenceMappingVO
				.setDataTypeCriteriaDelete(this.dataTypeCriteriaDelete);

		deleteReferenceMappingRequest.setReferencemappingVO(referenceMappingVO);
		DeleteReferenceMappingResponse deleteReferenceMappingResponse = (DeleteReferenceMappingResponse) this
				.executeService(deleteReferenceMappingRequest);

		String search = searchReferenceMapping();

		addMessageToRequest((Message) deleteReferenceMappingResponse
				.getMessages().get(0));
		return search;
	}

	/*
	 * The flow from maintain to view page The values in View is populated as
	 * Query Strings
	 */
	public String retrieveReferenceMapping() {

		DeleteReferenceMappingResponse deleteReferenceMappingResponse;

		String refid = (String) ESAPI.encoder().encodeForHTML(getRequest().getParameter("refid"));
		if(null!=refid && refid.matches("[0-9a-zA-Z_]+")){
			refid = refid;
		}
		String cobolVal = (String) getRequest().getParameter("cobolVal");
		String refDesc = (String) getRequest().getParameter("refDesc");

		String type = (String) getRequest().getParameter("type");
		String term = (String) getRequest().getParameter("term");
		String pva = (String) getRequest().getParameter("pva");
		String qualifier = (String) getRequest().getParameter("qualifier");
		String dataType = (String) getRequest().getParameter("dataType");
		String chngdDate = (String) getRequest().getParameter("chngdTime");
		String createdDate = (String) getRequest().getParameter("createdTime");
		String chngduser = (String) getRequest().getParameter("chngduser");
		String user = (String) getRequest().getParameter("user");
		String timeZone = (String) getRequest().getParameter("timeZone");

		this.setReferenceCriteria(refid);
		this.setCobolValue(cobolVal);
		this.setDescription(refDesc);

		this.setTermCriteria(term);
		this.setTypeCriteria(type);
		this.setPvaCriteria(pva);
		this.setQualifierCriteria(qualifier);
		this.setDataTypeCriteria(dataType);
		this.setCreatedUser(user);
		this.setLastUpdatedUser(chngduser);
		this.setChangeDate(chngdDate);
		this.setCreatedDate(createdDate);
		this.setTimeZone(timeZone);

		ReferencemappingVO referenceMappingVO = new ReferencemappingVO();

		referenceMappingVO
				.setReferenceCriteriaDelete(this.referenceCriteriaDelete);
		referenceMappingVO.setTypeCriteriaDelete(this.typeCriteriaDelete);
		referenceMappingVO.setTermCriteriaDelete(this.termCriteriaDelete);
		referenceMappingVO
				.setQualifierCriteriaDelete(this.qualifierCriteriaDelete);
		referenceMappingVO.setPvaCriteriaDelete(this.pvaCriteriaDelete);
		referenceMappingVO
				.setDataTypeCriteriaDelete(this.dataTypeCriteriaDelete);
		this.setTermCriteria(term);
		String breadCrumb = "Administration >> Reference Mapping ("
				+ refid + ")>> View";
		setBreadCrumbText(breadCrumb);	
		return "";

	}

	/**
	 * Checks if a SPS mapping exists of the same combination passed
	 * 
	 * @param referenceId
	 * @param type
	 * @param term
	 * @param qualifier
	 * @param pva
	 * @param dataType
	 * @return
	 */
	private int checkMappingExists(String referenceId, String type,
			String term, String qualifier, String pva, int dataType) {
		SearchReferenceMappingRequest searchReferenceMappingRequest = (SearchReferenceMappingRequest) this
				.getServiceRequest(ServiceManager.SEARCH_REF_MAPPING_REQUEST);

		List referenceList = new ArrayList();
		List typeList = new ArrayList();
		List dataTypeList = new ArrayList();

		referenceList.add(referenceId);
		typeList.add(type);

		//if (!(pva.equalsIgnoreCase("null") && (null != pva))) {
			List pvaList = new ArrayList();
			pvaList.add(pva);
			searchReferenceMappingRequest.setPvaList(pvaList);

		//}

		//if (!term.equalsIgnoreCase("null")) {
			List termList = new ArrayList();
			termList.add(term);
			searchReferenceMappingRequest.setTermList(termList);
		//}
		//if (!qualifier.equalsIgnoreCase("null")) {
			List qualifierList = new ArrayList();
			qualifierList.add(qualifier);
			searchReferenceMappingRequest.setQualifierList(qualifierList);
		//}

		dataTypeList.add(Integer.toString(dataType));
		searchReferenceMappingRequest.setDataTypeList(dataTypeList);
		searchReferenceMappingRequest.setReferenceList(referenceList);
		searchReferenceMappingRequest.setTypeList(typeList);
		searchReferenceMappingRequest.setDataTypeList(dataTypeList);
		int mapppingExists = 0;

		searchReferenceMappingRequest
				.setAction(BusinessConstants.LOCATE_REF_MAPPING);

		SearchReferenceMappingResponse searchReferenceMappingResponse = (SearchReferenceMappingResponse) this
				.executeService(searchReferenceMappingRequest);

		List searchResults = searchReferenceMappingResponse.getSearchList();

		if (searchResults != null && searchResults.size() > 0) {    
			mapppingExists = searchResults.size();
		}

		return mapppingExists;
	}

	/*
	 * This retrieves the reference mapping values This is called after every
	 * create / update method
	 */
	private List getReferenceMapping(String referenceId, String type,
			String term, String qualifier, String pva, int dataType) {
		SearchReferenceMappingRequest searchReferenceMappingRequest = (SearchReferenceMappingRequest) this
				.getServiceRequest(ServiceManager.SEARCH_REF_MAPPING_REQUEST);

		List referenceList = new ArrayList();
		List typeList = new ArrayList();
		List dataTypeList = new ArrayList();

		referenceList.add(referenceId);
		typeList.add(type);

		if (!(pva.equalsIgnoreCase("null") && (null != pva))) {
			List pvaList = new ArrayList();
			pvaList.add(pva);
			searchReferenceMappingRequest.setPvaList(pvaList);

		}

		if (!term.equalsIgnoreCase("null")) {
			List termList = new ArrayList();
			termList.add(term);
			searchReferenceMappingRequest.setTermList(termList);
		}
		if (!qualifier.equalsIgnoreCase("null")) {
			List qualifierList = new ArrayList();
			qualifierList.add(qualifier);
			searchReferenceMappingRequest.setQualifierList(qualifierList);
		}

		dataTypeList.add(Integer.toString(dataType));
		searchReferenceMappingRequest.setDataTypeList(dataTypeList);
		searchReferenceMappingRequest.setReferenceList(referenceList);
		searchReferenceMappingRequest.setTypeList(typeList);
		searchReferenceMappingRequest.setDataTypeList(dataTypeList);
		int mapppingExists = 0;

		searchReferenceMappingRequest
				.setAction(BusinessConstants.LOCATE_REF_MAPPING);

		SearchReferenceMappingResponse searchReferenceMappingResponse = (SearchReferenceMappingResponse) this
				.executeService(searchReferenceMappingRequest);

		this.setCreatedDate(searchReferenceMappingResponse.getCreatedDate());
		this.setCreatedUser(searchReferenceMappingResponse.getCreatedUser());
		this.setLastUpdatedUser(searchReferenceMappingResponse
				.getLastUpdatedUser());
		this.setChangeDate(searchReferenceMappingResponse.getChangeDate());

		List searchResults = searchReferenceMappingResponse.getSearchList();
		searchReferenceMappingResponse.setMessages(messages);
		return searchResults;
	}

	/*
	 * Maintian flow finds out a sps mapping of the combination
	 */

	public String searchReferenceMapping() {

		SearchReferenceMappingRequest searchReferenceMappingRequest = (SearchReferenceMappingRequest) this
				.getServiceRequest(ServiceManager.SEARCH_REF_MAPPING_REQUEST);

		ReferencemappingVO referenceMappingVO = new ReferencemappingVO();

		String[] term = (this.termCriteria).split("~");
		String[] qualifier = (this.qualifierCriteria).split("~");
		boolean valid = true;

		String termComma = getCommaSeparated(term);
		String qualifierComma = getCommaSeparated(qualifier);

		List referenceList = WPDStringUtil.getListFromTildaString(
				this.referenceCriteria, 2, 1, 2);
		List typeList = WPDStringUtil.getListFromTildaString(this.typeCriteria,
				1, 1, 2);
		//	typeList.add(new String(this.typeCriteria));
		List termList = null;
		if (!this.aggregateTerm)
			termList = WPDStringUtil.getListFromCommaString(termComma, 1, 1, 2);
		else
			termList = WPDStringUtil.getListFromTildaString(termComma, 1, 1, 2);

		List qualifierList = null;

		if (!this.aggregateQualifier)
			qualifierList = WPDStringUtil.getListFromCommaString(
					qualifierComma, 1, 1, 2);
		else
			qualifierList = WPDStringUtil.getListFromTildaString(
					qualifierComma, 1, 1, 2);

		List pvaList = WPDStringUtil.getListFromTildaString(this.pvaCriteria,
				1, 1, 2);
		//pvaList.add(new String(this.pvaCriteria));
		List dataTypeList = WPDStringUtil.getListFromTildaString(
				this.dataTypeCriteria, 2, 1, 2);

		if (!(referenceList.size() > 0 || typeList.size() > 0
				|| termList.size() > 0 || qualifierList.size() > 0
				|| pvaList.size() > 0 || dataTypeList.size() > 0)) {

			addMessageToRequest(new ErrorMessage(
					WebConstants.ATLEAST_ONE_SEARCH));

			valid = false;
		}

		if (term.length > 4 && (this.aggregateTerm == true)) {
			addMessageToRequest(new ErrorMessage(
					WebConstants.AGGREAGET_TERM_VALID));
			valid = false;
		}

		if (qualifier.length > 4 && (this.aggregateQualifier == true)) {
			addMessageToRequest(new ErrorMessage(
					WebConstants.AGGREAGET_QUALIFIER_VALID));
			valid = false;
		}

		if (!valid) {
			setBreadCrumbText("Administration >> Reference Mapping >> Locate");
			return "";
		}

		searchReferenceMappingRequest.setReferenceList(referenceList);
		searchReferenceMappingRequest.setTypeList(typeList);
		searchReferenceMappingRequest.setTermList(termList);
		searchReferenceMappingRequest.setQualifierList(qualifierList);
		searchReferenceMappingRequest.setPvaList(pvaList);
		searchReferenceMappingRequest.setDataTypeList(dataTypeList);
		TimeZone timeZone = ((AuditFactory) ObjectFactory
				.getFactory(ObjectFactory.AUDIT)).getAudit().getTimeZone();
		this.timeZone = timeZone
				.getDisplayName(false, java.util.TimeZone.SHORT);

		SearchReferenceMappingResponse searchReferenceMappingResponse = (SearchReferenceMappingResponse) this
				.executeService(searchReferenceMappingRequest);

		if (null != searchReferenceMappingResponse.getSearchList()
				&& searchReferenceMappingResponse.getSearchList().size() > 0) {
			searchResults = searchReferenceMappingResponse.getSearchList();
		}

		setBreadCrumbText("Administration >> Reference Mapping >> Locate");
		return "";

	}

	/**
	 * @param referenceMappingVO
	 *            populate the VO
	 */
	private void populateVO(ReferencemappingVO referenceMappingVO) {
		String[] spsCodes = (this.getReferenceCriteria()).split("~");
		String[] term = (this.getTermCriteria()).split("~");
		String[] qulaifier = (this.getQualifierCriteria()).split("~");
		String[] pva = (this.getPvaCriteria()).split("~");
		String[] dataType = (this.getDataTypeCriteria()).split("~");

		String termComma = getCommaSeparated(term);
		String qualifierComma = getCommaSeparated(qulaifier);
		if (term.length <= 2)
			this.aggregateTerm = false;
		if (qulaifier.length <= 2)
			this.aggregateQualifier = false;

		referenceMappingVO.setReferenceId(spsCodes[0]);
		referenceMappingVO.setType(this.getTypeCriteria());

		if (null != qualifierComma && !"".equalsIgnoreCase(qualifierComma))
			referenceMappingVO.setQualifier(qualifierComma);
		else
			referenceMappingVO.setQualifier("null");

		if (null != termComma && !"".equalsIgnoreCase(termComma))
			referenceMappingVO.setTerm(termComma);
		else
			referenceMappingVO.setTerm("null");

		if (null != pva[0] && !"".equalsIgnoreCase(pva[0]))
			referenceMappingVO.setPva(pva[0]);
		else
			referenceMappingVO.setPva("null");

		if (null != this.getDataTypeCriteria()
				&& ((this.getDataTypeCriteria().length() >= 1))) {
			referenceMappingVO.setDatatype(Integer.parseInt(dataType[0]));
		}

	}

	/**
	 * Make the tilda separated to comma separated
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

	/*
	 * Edit the reference mapping
	 */

	public String editReferenceMapping() {

		EditReferenceMappingRequest editReferenceMappingRequest = (EditReferenceMappingRequest) this
				.getServiceRequest(ServiceManager.EDIT_REF_MAPPING_REQUEST);

		ReferencemappingVO referenceMappingVO = new ReferencemappingVO();

		if (!validateCreateRequest()) {
			String breadCrumb = "Administration >> Reference Mapping ("
					+ this.getReferenceCriteria().split("~")[0] + ")>> Edit";
			setBreadCrumbText(breadCrumb);
			return "";
		}

		populateVOEdit(referenceMappingVO);

		int size = checkMappingExists(referenceMappingVO.getReferenceId(),
				referenceMappingVO.getType(), referenceMappingVO.getTerm(),
				referenceMappingVO.getQualifier(), referenceMappingVO.getPva(),
				(referenceMappingVO.getDatatype()));

		if (size > 0) {

			addMessageToRequest(new ErrorMessage(
					WebConstants.REF_MAPPING_CREATE_UNIQUE_VALID));
			String breadCrumb = "Administration >> Reference Mapping ("
					+ referenceMappingVO.getReferenceId() + ")>> Edit";
			setBreadCrumbText(breadCrumb);
			return "";
		}

		editReferenceMappingRequest.setReferencemappingVO(referenceMappingVO);
		EditReferenceMappingResponse editReferenceMappingResponse = (EditReferenceMappingResponse) this
				.executeService(editReferenceMappingRequest);
		List messages = editReferenceMappingResponse.getMessages();

		getReferenceMapping(referenceMappingVO.getReferenceId(),
				referenceMappingVO.getType(), referenceMappingVO.getTerm(),
				referenceMappingVO.getQualifier(), referenceMappingVO.getPva(),
				(referenceMappingVO.getDatatype()));

		if (this.pvaCriteria.equalsIgnoreCase("null")) {

			this.setPvaCriteria("");
		}

		this.setPrevTypeCriteria(this.typeCriteria);
		this.setPrevTermCriteria(this.termCriteria);
		this.setPrevQualifierCriteria(this.qualifierCriteria);
		this.setPrevpvaCriteria(this.pvaCriteria);
		this.setPrevDataTypeCriteria(this.dataTypeCriteria);

		addAllMessagesToRequest(messages);
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
		String breadCrumb = "Administration >> Reference Mapping ("
				+ referenceMappingVO.getReferenceId() + ")>> Edit";
		setBreadCrumbText(breadCrumb);
		return "editspsIDMapping";
	}

	/*
	 * maintain to edit flow The edit is populated from Maintan
	 */
	public String locateReferenceMapping() {

		if (!((termCriteriaDelete == null || termCriteriaDelete
				.equalsIgnoreCase("null")))) {
			String term = getTerm();
			this.setTermCriteria(term);
			this.setPrevTermCriteria(term);
		}

		if (!((qualifierCriteriaDelete == null || qualifierCriteriaDelete
				.equalsIgnoreCase("null")))) {
			String qual = getQual();
			this.setQualifierCriteria(qual);
			this.setPrevQualifierCriteria(qual);
		}

		this.setDataTypeCriteria(this.dataTypeEdit + "~"
				+ this.dataTypeEditDesc);
		if (!this.pvaCriteriaDelete.equalsIgnoreCase("null"))
			this.setPvaCriteria(this.pvaCriteriaDelete);

		this.setReferenceCriteria(this.referenceCriteriaEdit + "~"
				+ this.cobolValue + "~" + this.description);

		this.setPrevTypeCriteria(this.typeCriteriaDelete);
		this.setTypeCriteria(this.typeCriteriaDelete);
		this.setPrevpvaCriteria(this.pvaCriteriaDelete);
		this.setPrevDataTypeCriteria(this.dataTypeEdit + "~"
				+ this.dataTypeEditDesc);

		this.setCreatedDate(this.createdDate);
		this.setCreatedUser(this.createdUser);
		this.setChangeDate(this.changeDate);
		this.setLastUpdatedUser(this.lastUpdatedUser);

		String breadCrumb = "Administration >> Reference Mapping ("
				+ this.referenceCriteriaEdit + ")>> Edit";
		setBreadCrumbText(breadCrumb);
		return "editspsIDMapping";

	}

	/**
	 * @return
	 */
	private String getQual() {
		StringBuffer qual = new StringBuffer();

		String[] id = qualifierCriteriaDelete.split(",");
		String[] desc = qualifierDesc.split(",");
		int i = 0;

		for (i = 0; i < id.length && i < desc.length; i++) {
			qual.append(id[i]).append("~").append(desc[i]).append("~");
		}
		if (i > 1) {
			this.setAggregateQualifier(true);
		} else {
			this.setAggregateQualifier(false);
		}
		qual.deleteCharAt(qual.length() - 1);

		return qual.toString();
	}

	private String getTerm() {
		StringBuffer term = new StringBuffer();
		int i = 0;
		if (termCriteriaDelete != null) {
			String[] id = termCriteriaDelete.split(",");
			String[] desc = termDesc.split(",");
			for (i = 0; i < id.length && i < desc.length; i++) {
				term.append(id[i]).append("~").append(desc[i]).append("~");
			}
		}
		if (i > 1) {
			this.setAggregateTerm(true);
		} else {
			this.setAggregateTerm(false);
		}

		term.deleteCharAt(term.length() - 1);

		return term.toString();
	}

	private void populateVOEdit(ReferencemappingVO referenceMappingVO) {
		String[] spsCodes = (this.getReferenceCriteria()).split("~");
		String[] term = (this.getTermCriteria()).split("~");
		String[] qulaifier = (this.getQualifierCriteria()).split("~");
		String[] pva = (this.getPvaCriteria()).split("~");
		String[] dataType = (this.getDataTypeCriteria()).split("~");

		String[] prevTerm = (this.getPrevTermCriteria()).split("~");
		String[] prevQulaifier = (this.getPrevQualifierCriteria()).split("~");
		String[] prevPva = (this.getPrevpvaCriteria()).split("~");
		String[] prevDataType = (this.getPrevDataTypeCriteria()).split("~");

		String termComma = getCommaSeparated(term);
		String qualifierComma = getCommaSeparated(qulaifier);
		String prevTermComma = getCommaSeparated(prevTerm);
		String prevQualifierComma = getCommaSeparated(prevQulaifier);

		if (qulaifier.length <= 2)
			aggregateQualifier = false;
		if (term.length <= 2)
			aggregateTerm = false;

		referenceMappingVO.setReferenceId(spsCodes[0]);
		referenceMappingVO.setType(this.getTypeCriteria());

		if (null != qualifierComma && !"".equalsIgnoreCase(qualifierComma))
			referenceMappingVO.setQualifier(qualifierComma);
		else
			referenceMappingVO.setQualifier("null");

		if (null != termComma && !"".equalsIgnoreCase(termComma))
			referenceMappingVO.setTerm(termComma);
		else
			referenceMappingVO.setTerm("null");

		if (null != pva[0] && !"".equalsIgnoreCase(pva[0]))
			referenceMappingVO.setPva(pva[0]);
		else
			referenceMappingVO.setPva("null");

		if (null != this.getDataTypeCriteria()
				&& ((this.getDataTypeCriteria().length() >= 1))) {
			referenceMappingVO.setDatatype(Integer.parseInt(dataType[0]));
		}

		referenceMappingVO.setPrevType(this.getPrevTypeCriteria());
		if (null != prevTermComma && !"".equalsIgnoreCase(prevTermComma))
			referenceMappingVO.setPrevTerm(prevTermComma);
		else
			referenceMappingVO.setPrevTerm("null");

		if (null != prevQualifierComma
				&& !"".equalsIgnoreCase(prevQualifierComma))
			referenceMappingVO.setPrevQualifier(prevQualifierComma);
		else
			referenceMappingVO.setPrevQualifier("null");

		if (null != prevPva[0] && !"".equalsIgnoreCase(prevPva[0]))
			referenceMappingVO.setPrevPva(prevPva[0]);
		else
			referenceMappingVO.setPrevPva("null");

		if (null != this.getPrevDataTypeCriteria()
				&& ((this.getPrevDataTypeCriteria().length() >= 1))) {
			referenceMappingVO.setPrevDatatype(Integer
					.parseInt(prevDataType[0]));
		}

	}

	/*
	 * Validate the create request
	 */
	private boolean validateCreateRequest() {

		String[] spsCodes = (this.getReferenceCriteria()).split("~");
		String[] term = (this.getTermCriteria()).split("~");
		String[] qulaifier = (this.getQualifierCriteria()).split("~");
		String[] pva = (this.getPvaCriteria()).split("~");
		String[] dataType = (this.getDataTypeCriteria()).split("~");
		String termComma = getCommaSeparated(term);
		String qualifierComma = getCommaSeparated(qulaifier);
		boolean aggregateTerm = true;
		boolean aggregateQual = true;
		boolean valid = true;

		if (null == this.getReferenceCriteria()
				|| "".equals(this.getReferenceCriteria())) {
			this.setReferenceReq(true);
			valid = false;
		}

		if (null == this.getTypeCriteria() || "".equals(this.getTypeCriteria())
				|| "".equalsIgnoreCase(this.getTypeCriteria())) {
			this.setRequiredType(true);
			valid = false;
		}
		if (!valid) {
			addMessageToRequest(new ErrorMessage(
					WebConstants.REFERENCE_MAPPING_CREATE_VALID));
			return valid;
		}

		if (valid) {
			if ("LINE".equalsIgnoreCase(this.typeCriteria)) {

				if (null == this.getTermCriteria()
						|| "".equals(this.getTermCriteria())) {
					this.setRequiredTerm(true);
					valid = false;
				}

				String[] terms = (this.getTermCriteria()).split("~");
				if (terms.length > 4) {
					addMessageToRequest(new ErrorMessage(
							BusinessConstants.MSG_BENEFIT_LEVEL_MAX_TERM_CHECK));
					aggregateTerm = false;
				}

				if (aggregateTerm && terms.length > 2
						&& (this.aggregateTerm == false)) {
					addMessageToRequest(new ErrorMessage(
							WebConstants.AGGREAGET_TERM_VALID));
					aggregateTerm = false;

				}

				String[] qualifier = (this.getQualifierCriteria()).split("~");
				if (qualifier.length > 4) {
					addMessageToRequest(new ErrorMessage(
							BusinessConstants.MSG_BENEFIT_LEVEL_MAX_QUALIFIER_CHECK));
					aggregateQual = false;
				}

				if (aggregateQual && aggregateQual && qualifier.length > 2
						&& (this.aggregateQualifier == false)) {
					addMessageToRequest(new ErrorMessage(
							WebConstants.AGGREAGET_QUALIFIER_VALID));
					aggregateQual = false;
				}

				if (null == this.getPvaCriteria()
						|| "".equals(this.getPvaCriteria())) {
					this.setRequiredProviderArrangement(true);
					valid = false;
				}

				if (null == this.getDataTypeCriteria()
						|| "".equals(this.getDataTypeCriteria())) {
					this.setRequiredDataType(true);
					valid = false;
				} else if (dataType != null && dataType.length >= 1
						&& "0".equalsIgnoreCase(dataType[0])) {
					this.setRequiredDataType(true);
					valid = false;
				}

			}

			if ("QUESTION".equalsIgnoreCase(this.typeCriteria)) {
				String[] terms = (this.getTermCriteria()).split("~");

				if (terms.length > 4) {
					addMessageToRequest(new ErrorMessage(
							BusinessConstants.MSG_BENEFIT_LEVEL_MAX_TERM_CHECK));
					aggregateTerm = false;
				}

				if (aggregateTerm && terms.length > 2
						&& (this.aggregateTerm == false)) {
					addMessageToRequest(new ErrorMessage(
							WebConstants.AGGREAGET_TERM_VALID));
					aggregateTerm = false;

				}

				String[] qualifier = (this.getQualifierCriteria()).split("~");

				if (qualifier.length > 4) {
					addMessageToRequest(new ErrorMessage(
							BusinessConstants.MSG_BENEFIT_LEVEL_MAX_QUALIFIER_CHECK));
					aggregateQual = false;
				}

				if (qualifier.length > 2 && (this.aggregateQualifier == false)) {
					addMessageToRequest(new ErrorMessage(
							WebConstants.AGGREAGET_QUALIFIER_VALID));
					aggregateQual = false;
				}
			}

			if (qulaifier.length <= 2)
				this.aggregateQualifier = false;
			if (term.length <= 2)
				this.aggregateTerm = false;

		}
		if (!valid) {
			addMessageToRequest(new ErrorMessage(
					WebConstants.REFERENCE_MAPPING_LINE_VALID));
			return valid;
		}

		return aggregateTerm && aggregateQual;
	}

	/**
	 * @param b
	 */

	/**
	 * @return Returns the termRequired.
	 */

	/**
	 * @return Returns the requiredTerm.
	 */
	public boolean isRequiredTerm() {
		return requiredTerm;
	}

	/**
	 * @param requiredTerm
	 *            The requiredTerm to set.
	 */
	public void setRequiredTerm(boolean requiredTerm) {
		this.requiredTerm = requiredTerm;
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
	 * @return Returns the prevReferenceCriteria.
	 */
	public String getPrevReferenceCriteria() {
		return prevReferenceCriteria;
	}

	/**
	 * @param prevReferenceCriteria
	 *            The prevReferenceCriteria to set.
	 */
	public void setPrevReferenceCriteria(String prevReferenceCriteria) {
		this.prevReferenceCriteria = prevReferenceCriteria;
	}

	/**
	 * @return Returns the mappingTypes.
	 */

	/*
	 * for populating the type select box
	 */
	public List getMappingTypes() {
		PopupRequest request = (PopupRequest) this
				.getServiceRequest(ServiceManager.POPUP_REQUEST);
		request.setQueryName("searchType");
		request.setPopAction(WebConstants.FILTER_ACTION);
		PopupResponse response = null;
		response = (PopupResponse) executeService(request);
		List types = new ArrayList();
		types.add(new SelectItem("", ""));
		List values = response.getResultList();
		for (int i = 0; i < values.size(); i++) {
			PopupFilterBO popupFilterBO = (PopupFilterBO) values.get(i);
			types.add(new SelectItem(popupFilterBO.getKeyValue(), popupFilterBO
					.getDescription()));
		}

		return types;
	}

	/**
	 * @param mappingTypes
	 *            The mappingTypes to set.
	 */
	public void setMappingTypes(List mappingTypes) {
		this.mappingTypes = mappingTypes;
	}

	/**
	 * @return Returns the prevDataTypeCriteria.
	 */
	public String getPrevDataTypeCriteria() {
		return prevDataTypeCriteria;
	}

	/**
	 * @param prevDataTypeCriteria
	 *            The prevDataTypeCriteria to set.
	 */
	public void setPrevDataTypeCriteria(String prevDataTypeCriteria) {
		this.prevDataTypeCriteria = prevDataTypeCriteria;
	}

	/**
	 * @return Returns the prevpvaCriteria.
	 */
	public String getPrevpvaCriteria() {
		return prevpvaCriteria;
	}

	/**
	 * @param prevpvaCriteria
	 *            The prevpvaCriteria to set.
	 */
	public void setPrevpvaCriteria(String prevpvaCriteria) {
		this.prevpvaCriteria = prevpvaCriteria;
	}

	/**
	 * @return Returns the prevQualifierCriteriaCriteria.
	 */
	public String getPrevQualifierCriteria() {
		return prevQualifierCriteria;
	}

	/**
	 * @param prevQualifierCriteriaCriteria
	 *            The prevQualifierCriteriaCriteria to set.
	 */
	public void setPrevQualifierCriteria(String prevQualifierCriteriaCriteria) {
		this.prevQualifierCriteria = prevQualifierCriteriaCriteria;
	}

	/**
	 * @return Returns the prevTermCriteria.
	 */
	public String getPrevTermCriteria() {
		return prevTermCriteria;
	}

	/**
	 * @param prevTermCriteria
	 *            The prevTermCriteria to set.
	 */
	public void setPrevTermCriteria(String prevTermCriteria) {
		this.prevTermCriteria = prevTermCriteria;
	}

	/**
	 * @return Returns the prevTypeCriteria.
	 */
	public String getPrevTypeCriteria() {
		return prevTypeCriteria;
	}

	/**
	 * @param prevTypeCriteria
	 *            The prevTypeCriteria to set.
	 */
	public void setPrevTypeCriteria(String prevTypeCriteria) {
		this.prevTypeCriteria = prevTypeCriteria;
	}

	/**
	 * @return Returns the dataTypeCriteriaDelete.
	 */
	public String getDataTypeCriteriaDelete() {
		return dataTypeCriteriaDelete;
	}

	/**
	 * @param dataTypeCriteriaDelete
	 *            The dataTypeCriteriaDelete to set.
	 */
	public void setDataTypeCriteriaDelete(String dataTypeCriteriaDelete) {
		this.dataTypeCriteriaDelete = dataTypeCriteriaDelete;
	}

	/**
	 * @return Returns the pvaCriteriaDelete.
	 */
	public String getPvaCriteriaDelete() {
		return pvaCriteriaDelete;
	}

	/**
	 * @param pvaCriteriaDelete
	 *            The pvaCriteriaDelete to set.
	 */
	public void setPvaCriteriaDelete(String pvaCriteriaDelete) {
		this.pvaCriteriaDelete = pvaCriteriaDelete;
	}

	/**
	 * @return Returns the qualifierCriteriaDelete.
	 */
	public String getQualifierCriteriaDelete() {
		return qualifierCriteriaDelete;
	}

	/**
	 * @param qualifierCriteriaDelete
	 *            The qualifierCriteriaDelete to set.
	 */
	public void setQualifierCriteriaDelete(String qualifierCriteriaDelete) {
		this.qualifierCriteriaDelete = qualifierCriteriaDelete;
	}

	/**
	 * @return Returns the termCriteriaDelete.
	 */
	public String getTermCriteriaDelete() {
		return termCriteriaDelete;
	}

	/**
	 * @param termCriteriaDelete
	 *            The termCriteriaDelete to set.
	 */
	public void setTermCriteriaDelete(String termCriteriaDelete) {
		this.termCriteriaDelete = termCriteriaDelete;
	}

	/**
	 * @return Returns the typeCriteriaDelete.
	 */
	public String getTypeCriteriaDelete() {
		return typeCriteriaDelete;
	}

	/**
	 * @param typeCriteriaDelete
	 *            The typeCriteriaDelete to set.
	 */
	public void setTypeCriteriaDelete(String typeCriteriaDelete) {
		this.typeCriteriaDelete = typeCriteriaDelete;
	}

	/**
	 * @return Returns the loadReferenceMapping.
	 */
	public String getLoadReferenceMapping() {
		return retrieveReferenceMapping();
	}

	/**
	 * @param loadReferenceMapping
	 *            The loadReferenceMapping to set.
	 */
	public void setLoadReferenceMapping(String loadReferenceMapping) {
		this.loadReferenceMapping = loadReferenceMapping;
	}

	/**
	 * @return Returns the dataTypeEditDesc.
	 */
	public String getDataTypeEditDesc() {
		return dataTypeEditDesc;
	}

	/**
	 * @param dataTypeEditDesc
	 *            The dataTypeEditDesc to set.
	 */
	public void setDataTypeEditDesc(String dataTypeEditDesc) {
		this.dataTypeEditDesc = dataTypeEditDesc;
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
	 * @return Returns the timeZone.
	 */
	public String getTimeZone() {
		return timeZone;
	}

	/**
	 * @param timeZone
	 *            The timeZone to set.
	 */
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
}