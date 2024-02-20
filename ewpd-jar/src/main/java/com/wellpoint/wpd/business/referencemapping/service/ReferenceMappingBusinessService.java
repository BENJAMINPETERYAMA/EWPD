/*
 * Created on Jul 20, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.referencemapping.service;

import java.util.ArrayList;

import java.util.Iterator;

import java.util.List;

import com.wellpoint.wpd.business.framework.service.WPDBusinessService;
import com.wellpoint.wpd.business.referencemapping.builder.ReferenceMappingBusinessObjectBuilder;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;

import com.wellpoint.wpd.common.referencemapping.bo.ReferenceMappingBO;
import com.wellpoint.wpd.common.referencemapping.request.CreateReferenceMappingRequest;
import com.wellpoint.wpd.common.referencemapping.request.DeleteReferenceMappingRequest;
import com.wellpoint.wpd.common.referencemapping.request.EditReferenceMappingRequest;
import com.wellpoint.wpd.common.referencemapping.request.RetrieveReferenceMappingRequest;
import com.wellpoint.wpd.common.referencemapping.request.SearchReferenceMappingRequest;
import com.wellpoint.wpd.common.referencemapping.response.CreateReferenceMappingResponse;
import com.wellpoint.wpd.common.referencemapping.response.DeleteReferenceMappingResponse;
import com.wellpoint.wpd.common.referencemapping.response.EditReferenceMappingResponse;
import com.wellpoint.wpd.common.referencemapping.response.RetrieveReferenceMappingResponse;
import com.wellpoint.wpd.common.referencemapping.response.SearchReferenceMappingResponse;

import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author U14659
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

public class ReferenceMappingBusinessService extends WPDBusinessService {

	/*
	 * Create reuest
	 */
	public WPDResponse execute(CreateReferenceMappingRequest request)
			throws ServiceException {

		ReferenceMappingBusinessObjectBuilder referenceMappingBusinessObjectBuilder;
		CreateReferenceMappingResponse createReferenceMappingResponse = null;
		try {

			referenceMappingBusinessObjectBuilder = new ReferenceMappingBusinessObjectBuilder();
			createReferenceMappingResponse = (CreateReferenceMappingResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.CREATE_REF_MAPPING_RESPONSE);
			ReferenceMappingBO referenceMappingBO = new ReferenceMappingBO();

			populateBO(request, referenceMappingBO);

			boolean success = referenceMappingBusinessObjectBuilder
					.createReferenceMapping(referenceMappingBO);
			createReferenceMappingResponse.setSuccess(true);

			if (success)
				createReferenceMappingResponse
						.addMessage(new InformationalMessage(
								WebConstants.REFERENCE_MAPPING_CREATE_SUCCESS));

		} catch (SevereException e) {

			e.printStackTrace();
		}
		return createReferenceMappingResponse;

	}

	/*
	 * Edit page request
	 */
	public WPDResponse execute(EditReferenceMappingRequest request)
			throws ServiceException {

		ReferenceMappingBusinessObjectBuilder referenceMappingBusinessObjectBuilder = new ReferenceMappingBusinessObjectBuilder();
		EditReferenceMappingResponse editReferenceMappingResponse = null;
		ReferenceMappingBO referenceMappingBO = new ReferenceMappingBO();
		try {
			populateBO(request, referenceMappingBO);

			boolean success = referenceMappingBusinessObjectBuilder
					.editReferenceMapping(referenceMappingBO);

			editReferenceMappingResponse = (EditReferenceMappingResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.EDIT_REF_MAPPING_RESPONSE);

			editReferenceMappingResponse.setSuccess(success);

			if (success) {
				editReferenceMappingResponse
						.addMessage(new InformationalMessage(
								WebConstants.REFERENCE_MAPPING_EDIT_SUCCESS));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return editReferenceMappingResponse;
	}
	
	/*
	 * Search reference request
	 */
	public WPDResponse execute(SearchReferenceMappingRequest request)
			throws ServiceException {
		ReferenceMappingBusinessObjectBuilder referenceMappingBusinessObjectBuilder = new ReferenceMappingBusinessObjectBuilder();
		SearchReferenceMappingResponse searchReferenceMappingResponse;
		ReferenceMappingBO referenceMappingBO = new ReferenceMappingBO();
		List searchResults = new ArrayList();

		referenceMappingBO.setReferenceList(request.getReferenceList());
		referenceMappingBO.setTypeList(request.getTypeList());
		referenceMappingBO.setTermList(request.getTermList());
		referenceMappingBO.setQualifierList(request.getQualifierList());
		referenceMappingBO.setPvaList(request.getPvaList());
		referenceMappingBO.setDataTypeList(request.getDataTypeList());

		searchReferenceMappingResponse = (SearchReferenceMappingResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.SEARCH_REF_MAPPING_RESPONSE);

		try {
			searchResults = (List) referenceMappingBusinessObjectBuilder
					.searchReferenceMapping(referenceMappingBO);

			if ((null == searchResults) || searchResults.size() <= 0) {
				searchReferenceMappingResponse
						.addMessage(new InformationalMessage(
								BusinessConstants.SEARCH_RESULT_NOT_FOUND));
			} else if (searchResults.size() > 50) {
				searchReferenceMappingResponse
						.addMessage(new InformationalMessage(
								BusinessConstants.MSG_SEARCH_RESULT_EXCEEDS));
				searchResults.remove(searchResults.size() - 1);
			}

			if (null != searchResults && searchResults.size() >= 0) {

				for (Iterator itr = searchResults.iterator(); itr.hasNext();) {

					ReferenceMappingBO referenceMappingObj = (ReferenceMappingBO) itr
							.next();
					searchReferenceMappingResponse
							.setChangeDate(referenceMappingObj.getChangeTime());
					searchReferenceMappingResponse
							.setCreatedDate(referenceMappingObj
									.getCreatedTime());
					searchReferenceMappingResponse
							.setCreatedUser(referenceMappingObj
									.getCreatedUser());
					searchReferenceMappingResponse
							.setLastUpdatedUser(referenceMappingObj
									.getLastUpdatedUser());

				}

			}

			searchReferenceMappingResponse.setSearchList(searchResults);

		} catch (SevereException e) {

			e.printStackTrace();
		}

		return searchReferenceMappingResponse;
	}
	

	public WPDResponse execute(RetrieveReferenceMappingRequest request)
			throws ServiceException {

		RetrieveReferenceMappingResponse retrieveReferenceMappingResponse = (RetrieveReferenceMappingResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.RETRIEVE_REF_MAPPING_RESPONSE);

		ReferenceMappingBusinessObjectBuilder referenceMappingBusinessObjectBuilder = new ReferenceMappingBusinessObjectBuilder();

		ReferenceMappingBO referenceMappingBO = new ReferenceMappingBO();

		referenceMappingBO.setReferenceId(request.getReferencemappingVO()
				.getReferenceCriteriaDelete());
		referenceMappingBO.setType(request.getReferencemappingVO()
				.getTypeCriteriaDelete());
		referenceMappingBO.setTerm(request.getReferencemappingVO()
				.getTermCriteriaDelete());
		referenceMappingBO.setQualifier(request.getReferencemappingVO()
				.getQualifierCriteriaDelete());
		referenceMappingBO.setPva(request.getReferencemappingVO()
				.getPvaCriteriaDelete());
		referenceMappingBO.setDatatype(Integer.parseInt(request
				.getReferencemappingVO().getDataTypeCriteriaDelete()));

		try {
			referenceMappingBO = referenceMappingBusinessObjectBuilder
					.retrieveReferenceMapping(referenceMappingBO);
			List messages = new ArrayList();
			setValuesToResponse(retrieveReferenceMappingResponse,
					referenceMappingBO);
		} catch (Exception e) {

		}

		return (WPDResponse) new RetrieveReferenceMappingResponse();
	}

	/**
	 * @param retrieveReferenceMappingResponse
	 * @param referenceMappingBO
	 */
	private void setValuesToResponse(
			RetrieveReferenceMappingResponse retrieveReferenceMappingResponse,
			ReferenceMappingBO referenceMappingBO) {

		retrieveReferenceMappingResponse
				.setReferenceCriteria(referenceMappingBO.getReferenceId());
		retrieveReferenceMappingResponse
				.setQualifierCriteria(referenceMappingBO.getQualifier());
		retrieveReferenceMappingResponse.setTermCriteria(referenceMappingBO
				.getTerm());
		retrieveReferenceMappingResponse.setTypeCriteria(referenceMappingBO
				.getType());
		retrieveReferenceMappingResponse.setDescription(referenceMappingBO
				.getDescription());
		retrieveReferenceMappingResponse.setPvaCriteria(referenceMappingBO
				.getPva());
		retrieveReferenceMappingResponse.setDataTypeCriteria(new Integer(
				referenceMappingBO.getDatatype()).toString());

	}

	/*
	 * delete the reference mapping
	 */
	public WPDResponse execute(DeleteReferenceMappingRequest request)
			throws ServiceException {

		DeleteReferenceMappingResponse deleteReferenceMappingResponse = (DeleteReferenceMappingResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.DELETE_REF_MAPPING_RESPONSE);

		ReferenceMappingBusinessObjectBuilder referenceMappingBusinessObjectBuilder = new ReferenceMappingBusinessObjectBuilder();

		ReferenceMappingBO referenceMappingBO = new ReferenceMappingBO();

		referenceMappingBO.setReferenceId(request.getReferencemappingVO()
				.getReferenceCriteriaDelete());
		referenceMappingBO.setType(request.getReferencemappingVO()
				.getTypeCriteriaDelete());
		referenceMappingBO.setTerm(request.getReferencemappingVO()
				.getTermCriteriaDelete());
		referenceMappingBO.setQualifier(request.getReferencemappingVO()
				.getQualifierCriteriaDelete());
		referenceMappingBO.setPva(request.getReferencemappingVO()
				.getPvaCriteriaDelete());

		referenceMappingBO.setDatatype(Integer.parseInt(request
				.getReferencemappingVO().getDataTypeCriteriaDelete()));

		try {
			referenceMappingBusinessObjectBuilder.deleteReferenceMapping(
					referenceMappingBO, request.getUser());

			List messages = new ArrayList();
			InformationalMessage informationalMessage = new InformationalMessage(
					BusinessConstants.REF_MAP_DELETE_SUCCESS);
			messages.add(informationalMessage);
			deleteReferenceMappingResponse.setMessages(messages);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return deleteReferenceMappingResponse;
	}

	/**
	 * @param request
	 * @param referenceMappingBO
	 */
	private void populateBO(CreateReferenceMappingRequest request,
			ReferenceMappingBO referenceMappingBO) {
		referenceMappingBO.setReferenceId(request.getReferencemappingVO()
				.getReferenceId());
		referenceMappingBO.setType(request.getReferencemappingVO().getType());
		referenceMappingBO.setTerm(request.getReferencemappingVO().getTerm());
		referenceMappingBO.setQualifier(request.getReferencemappingVO()
				.getQualifier());
		referenceMappingBO.setPva(request.getReferencemappingVO().getPva());
		referenceMappingBO.setDatatype(request.getReferencemappingVO()
				.getDatatype());

		referenceMappingBO.setCreatedUser(request.getUser().getUserId());
		referenceMappingBO.setLastUpdatedUser(request.getUser().getUserId());
		referenceMappingBO.setKeyValue("test");
		//		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy HH:mm:ss");
		//		 Date date = new Date();

		//	 request.getReferencemappingVO().setCreatedUser(request.getUser().getUserId());
		//		 request.getReferencemappingVO().setCreatedDate((dateFormat.format(date).toString()));
		//		 request.getReferencemappingVO().setChangeDate((dateFormat.format(date).toString()));
		//	 request.getReferencemappingVO().setLastUpdatedUser(request.getUser().getUserId());

	}

	private void populateBO(EditReferenceMappingRequest request,
			ReferenceMappingBO referenceMappingBO) {
		referenceMappingBO.setReferenceId(request.getReferencemappingVO()
				.getReferenceId());
		referenceMappingBO.setType(request.getReferencemappingVO().getType());
		referenceMappingBO.setTerm(request.getReferencemappingVO().getTerm());
		referenceMappingBO.setQualifier(request.getReferencemappingVO()
				.getQualifier());
		referenceMappingBO.setPva(request.getReferencemappingVO().getPva());
		referenceMappingBO.setDatatype(request.getReferencemappingVO()
				.getDatatype());
		referenceMappingBO.setCreatedUser(request.getUser().getUserId());
		referenceMappingBO.setLastUpdatedUser(request.getUser().getUserId());

		referenceMappingBO.setPrevType(request.getReferencemappingVO()
				.getPrevType());
		referenceMappingBO.setPrevTerm(request.getReferencemappingVO()
				.getPrevTerm());
		referenceMappingBO.setPrevQualifier(request.getReferencemappingVO()
				.getPrevQualifier());
		referenceMappingBO.setPrevpva(request.getReferencemappingVO()
				.getPrevPva());
		referenceMappingBO.setPrevDataType(request.getReferencemappingVO()
				.getPrevDatatype());

	}

}