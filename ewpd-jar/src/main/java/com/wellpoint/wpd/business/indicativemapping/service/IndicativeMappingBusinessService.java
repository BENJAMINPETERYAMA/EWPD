/*
 * IndicativeMappingBusinessService.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.indicativemapping.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wellpoint.wpd.business.framework.service.WPDBusinessService;
import com.wellpoint.wpd.business.indicativemapping.builder.IndicativeMappingBusinessObjectBuilder;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.indicativemapping.bo.IndicativeDetailBO;
import com.wellpoint.wpd.common.indicativemapping.bo.IndicativeMappingBO;
import com.wellpoint.wpd.common.indicativemapping.request.ConfirmImportIndicativeMappingRequest;
import com.wellpoint.wpd.common.indicativemapping.request.CreateIndicativeMappingRequest;
import com.wellpoint.wpd.common.indicativemapping.request.DeleteIndicativeMappingRequest;
import com.wellpoint.wpd.common.indicativemapping.request.EditIndicativeMappingRequest;
import com.wellpoint.wpd.common.indicativemapping.request.IndicativeDetailRequest;
import com.wellpoint.wpd.common.indicativemapping.request.RetrieveIndicativeMappingRequest;
import com.wellpoint.wpd.common.indicativemapping.request.SearchIndicativeMappingRequest;
import com.wellpoint.wpd.common.indicativemapping.response.ConfirmImportIndicativeMappingResponse;
import com.wellpoint.wpd.common.indicativemapping.response.CreateIndicativeMappingResponse;
import com.wellpoint.wpd.common.indicativemapping.response.DeleteIndicativeMappingResponse;
import com.wellpoint.wpd.common.indicativemapping.response.EditIndicativeMappingResponse;
import com.wellpoint.wpd.common.indicativemapping.response.IndicativeDetailResponse;
import com.wellpoint.wpd.common.indicativemapping.response.RetrieveIndicativeMappingResponse;
import com.wellpoint.wpd.common.indicativemapping.response.SearchIndicativeMappingResponse;
import com.wellpoint.wpd.common.indicativemapping.vo.IndicativeDetailVO;
import com.wellpoint.wpd.common.referencemapping.bo.ReferenceMappingBO;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class IndicativeMappingBusinessService extends WPDBusinessService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.wpd.business.framework.service.WPDBusinessService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
	 */
	public WPDResponse execute(CreateIndicativeMappingRequest request)
			throws ServiceException {

		CreateIndicativeMappingResponse createIndicativeMappingResponse = null;
		try {
			createIndicativeMappingResponse = (CreateIndicativeMappingResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.CREATE_IND_MAINTAIN_RESPONSE);
			IndicativeMappingBO indicativeMappingBO = new IndicativeMappingBO();
			indicativeMappingBO.setBenefit(request.getIndicativeMappingVO()
					.getBenefit());
			indicativeMappingBO.setSpsParameterCode(request
					.getIndicativeMappingVO().getSpsParameter());
			indicativeMappingBO.setIndicativeSegmentCode(request
					.getIndicativeMappingVO().getIndicativeSegment());
			indicativeMappingBO.setIndSegDesc(request.getIndicativeMappingVO()
					.getDescription());
			indicativeMappingBO.setIndSegDesc(request.getIndicativeMappingVO()
					.getDescription());
			indicativeMappingBO.setCreatedUser(request.getUser().getUserId());
			indicativeMappingBO.setLastUpdatedUser(request.getUser()
					.getUserId());
			IndicativeMappingBusinessObjectBuilder builder = new IndicativeMappingBusinessObjectBuilder();
			boolean success = builder
					.createIndicativeMapping(indicativeMappingBO);
			createIndicativeMappingResponse.setSuccess(success);
			if (success)
				createIndicativeMappingResponse
						.addMessage(new InformationalMessage(
								WebConstants.INDICATIVE_MAPPING_CREATE_SUCCESS));

		} catch (SevereException e) {
			e.printStackTrace();
		}

		return createIndicativeMappingResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.wpd.business.framework.service.WPDBusinessService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
	 */
	public WPDResponse execute(EditIndicativeMappingRequest request)
			throws ServiceException {

		EditIndicativeMappingResponse editIndicativeMappingResponse = null;
		try {
			editIndicativeMappingResponse = (EditIndicativeMappingResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.EDIT_IND_MAINTAIN_RESPONSE);
			IndicativeMappingBO indicativeMappingBO = new IndicativeMappingBO();
			indicativeMappingBO.setBenefit(request.getIndicativeMappingVO()
					.getBenefit());
			indicativeMappingBO.setSpsParameterCode(request
					.getIndicativeMappingVO().getSpsParameter());
			indicativeMappingBO.setIndicativeSegmentCode(request
					.getIndicativeMappingVO().getIndicativeSegment());
			indicativeMappingBO.setIndSegDesc(request.getIndicativeMappingVO()
					.getDescription());

			indicativeMappingBO.setIndicativeSegment(request
					.getIndicativeMappingVO().getPrevInd());
			indicativeMappingBO.setSpsParameter(request
					.getIndicativeMappingVO().getPrevSps());
			indicativeMappingBO.setPreBen(request.getIndicativeMappingVO()
					.getPrevBen());
			indicativeMappingBO.setLastUpdatedUser(request.getUser()
					.getUserId());
			IndicativeMappingBusinessObjectBuilder builder = new IndicativeMappingBusinessObjectBuilder();
			boolean success = builder
					.editIndicativeMapping(indicativeMappingBO);
			editIndicativeMappingResponse.setSuccess(success);
			if (success)
				editIndicativeMappingResponse
						.addMessage(new InformationalMessage(
								WebConstants.INDICATIVE_MAPPING_SAVE_SUCCESS));

		} catch (SevereException e) {
			e.printStackTrace();
		}

		return editIndicativeMappingResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.wpd.business.framework.service.WPDBusinessService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
	 */
	public WPDResponse execute(RetrieveIndicativeMappingRequest request)
			throws ServiceException {
		RetrieveIndicativeMappingResponse indicativeMappingResponse = (RetrieveIndicativeMappingResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.RETRIEVE_IND_MAINTAIN_RESPONSE);
		IndicativeMappingBusinessObjectBuilder indicativeMappingBusinessObjectBuilder = new IndicativeMappingBusinessObjectBuilder();
		IndicativeMappingBO indicativeMappingBO = new IndicativeMappingBO();
		indicativeMappingBO.setIndicativeSegmentCode(request
				.getIndicativeMappingVO().getIndicativeSegment());
		indicativeMappingBO.setSpsParameterCode(request
				.getIndicativeMappingVO().getSpsParameter());
		indicativeMappingBO.setBenefit(request.getIndicativeMappingVO()
				.getBenefit());
		try {
			indicativeMappingBO = indicativeMappingBusinessObjectBuilder
					.retrieveIndicativeMapping(indicativeMappingBO);
			List messages = new ArrayList();
			setValuesToResponse(indicativeMappingResponse, indicativeMappingBO);
		} catch (SevereException e) {
			e.printStackTrace();
		}
		return indicativeMappingResponse;
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(DeleteIndicativeMappingRequest request)
			throws ServiceException {
		DeleteIndicativeMappingResponse deleteIndicativeMappingResponse = (DeleteIndicativeMappingResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.DELETE_IND_MAINTAIN_RESPONSE);
		IndicativeMappingBusinessObjectBuilder indicativeMappingBusinessObjectBuilder = new IndicativeMappingBusinessObjectBuilder();
		IndicativeMappingBO indicativeMappingBO = new IndicativeMappingBO();
		indicativeMappingBO.setIndicativeSegmentCode(request
				.getIndicativeMappingVO().getIndicativeSegment());
		indicativeMappingBO.setSpsParameterCode(request
				.getIndicativeMappingVO().getSpsParameter());
		indicativeMappingBO.setBenefit(request.getIndicativeMappingVO()
				.getBenefit());

		try {
			indicativeMappingBusinessObjectBuilder.deleteIndicativeMapping(
					indicativeMappingBO, request.getUser());
			List messages = new ArrayList();
			InformationalMessage informationalMessage = new InformationalMessage(
					BusinessConstants.IND_MAP_DELETE_SUCCESS);
			messages.add(informationalMessage);
			deleteIndicativeMappingResponse.setMessages(messages);
		} catch (SevereException e) {
			e.printStackTrace();
		}
		return deleteIndicativeMappingResponse;
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(SearchIndicativeMappingRequest request)
			throws ServiceException {
		SearchIndicativeMappingResponse searchIndicativeMappingResponse = (SearchIndicativeMappingResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.SEARCH_IND_MAINTAIN_RESPONSE);

		IndicativeMappingBusinessObjectBuilder indicativeMappingBusinessObjectBuilder = new IndicativeMappingBusinessObjectBuilder();
		IndicativeMappingBO indicativeMappingBO = new IndicativeMappingBO();
		List searchResults = new ArrayList();
		String region  = "";

		switch (request.getAction()) {
		case BusinessConstants.LOCATE_INDICATIVE_MAPPING:

			indicativeMappingBO.setIndicativeList(request.getIndicativeList());
			indicativeMappingBO.setBenefitList(request.getBenefitList());
			indicativeMappingBO.setSpsList(request.getSpsList());

			try {
				searchResults = indicativeMappingBusinessObjectBuilder
						.searchIndicativeMapping(indicativeMappingBO);

				if (null != searchResults) {
					if (searchResults.size() > 50) {
						searchIndicativeMappingResponse
								.addMessage(new InformationalMessage(
										BusinessConstants.MSG_SEARCH_RESULT_EXCEEDS));
						searchResults.remove(searchResults.size() - 1);
					} else if (searchResults.size() <= 0) {
						searchIndicativeMappingResponse
								.addMessage(new InformationalMessage(
										BusinessConstants.SEARCH_RESULT_NOT_FOUND));
					}
				}
				searchIndicativeMappingResponse.setSearchList(searchResults);
			} catch (SevereException e) {
				e.printStackTrace();
			}
			break;
		case BusinessConstants.LOCATE_INDICATIVE_MAPPING_DF:
			region = request.getRegion();
			try {
				searchResults = indicativeMappingBusinessObjectBuilder
						.retrieveIndicativeMappingsForDatafeed(region);
				searchIndicativeMappingResponse.setSearchList(searchResults);
			} catch (SevereException exception) {
				exception.printStackTrace();
			}
			break;
			//Added as part of Indicative Mapping APRIl 2015 Release
				//This case is added to fetch the indicative file format from DB: will query INDCTV_DTL table
			case BusinessConstants.DATAFEED_INDICATIVE_CONFIGURATION:
				region = request.getRegion();
				try {
					searchResults = indicativeMappingBusinessObjectBuilder
							.retrieveIndicativeMappingsConfigurationForDatafeed(region);
					searchIndicativeMappingResponse.setSearchList(searchResults);
				} catch (SevereException exception) {
					exception.printStackTrace();
				}
				break;
			}
			
		return searchIndicativeMappingResponse;
	}

	private void setValuesToResponse(
			RetrieveIndicativeMappingResponse retrieveIndicativeMappingResponse,
			IndicativeMappingBO indicativeMappingBO) {
		retrieveIndicativeMappingResponse
				.setIndicativeSegment(indicativeMappingBO
						.getIndicativeSegmentCode());
		retrieveIndicativeMappingResponse
				.setIndicativeSegmentDesc(indicativeMappingBO
						.getIndicativeSegment());
		retrieveIndicativeMappingResponse.setSegmentNumber(indicativeMappingBO
				.getIndicativeSegmentNumber());
		retrieveIndicativeMappingResponse.setSpsParameter(indicativeMappingBO
				.getSpsParameterCode());
		retrieveIndicativeMappingResponse
				.setSpsParameterDesc(indicativeMappingBO.getSpsParameter());
		retrieveIndicativeMappingResponse.setBenefit(indicativeMappingBO
				.getBenefit());
		retrieveIndicativeMappingResponse.setIndMapDesc(indicativeMappingBO
				.getIndSegDesc());
		retrieveIndicativeMappingResponse
				.setLastUpdatedDate(indicativeMappingBO
						.getLastUpdatedTimestamp());
		retrieveIndicativeMappingResponse
				.setLastUpdatedUser(indicativeMappingBO.getLastUpdatedUser());
		retrieveIndicativeMappingResponse.setCreatedDate(indicativeMappingBO
				.getCreatedTimestamp());
		retrieveIndicativeMappingResponse.setCreatedUser(indicativeMappingBO
				.getCreatedUser());
	}

		
	/**
	 * This method accepts two lists and compares to determines lists to be inserted, deleted and updated
	 * @param firstInputList_ToCompare
	 * @param secondList_ToBeCompared
	 * @return  Map<String, List<IndicativeDetailVO>>
	 */
	private Map<String, List<IndicativeDetailVO>> compareData(List<IndicativeDetailVO> firstInputList_ToCompare,
			List<IndicativeDetailVO> secondList_ToBeCompared) {
		Map<String, List<IndicativeDetailVO>> resultMap = new HashMap<String, List<IndicativeDetailVO>>();		
		List<IndicativeDetailVO> newIndicativeCodes = new ArrayList<IndicativeDetailVO>(firstInputList_ToCompare);
		newIndicativeCodes.removeAll(secondList_ToBeCompared);
		List<IndicativeDetailVO> deletedIndicativeCodes = new ArrayList<IndicativeDetailVO>(secondList_ToBeCompared);
		deletedIndicativeCodes.removeAll(firstInputList_ToCompare);
		List<IndicativeDetailVO> tempModifiedIndicativeCodes = new ArrayList<IndicativeDetailVO>(firstInputList_ToCompare);		
		List<IndicativeDetailVO> modifiedIndicativeCodes = new ArrayList<IndicativeDetailVO>();
		tempModifiedIndicativeCodes.retainAll(secondList_ToBeCompared);
		//Iterating input Lists for various actions
		for (IndicativeDetailVO indicativeDetailVO : tempModifiedIndicativeCodes) {
			for (IndicativeDetailVO indicativeFromDB : secondList_ToBeCompared) {
				// Below condition identifies if any update is there
				if (indicativeFromDB.getIndicativeCode().equalsIgnoreCase(
						indicativeDetailVO.getIndicativeCode())
						&& compareFieldsForIndicativeObjects(
								indicativeDetailVO, indicativeFromDB)) {
					modifiedIndicativeCodes.add(indicativeDetailVO);
				}

			}
		}
		resultMap.put(BusinessConstants.ADDED_INDICATIVES, newIndicativeCodes);
		resultMap.put(BusinessConstants.UPDATED_INDICATIVES, modifiedIndicativeCodes);
		resultMap.put(BusinessConstants.DELETED_INDICATIVES, deletedIndicativeCodes);
		return resultMap;
	}
	
	private boolean compareFieldsForIndicativeObjects(IndicativeDetailVO inputIndicativeObject, IndicativeDetailVO indicativeFromDB){
		boolean toBeUpdated = false;		
		if((! (indicativeFromDB.getIndValue() != null ? indicativeFromDB.getIndValue(): "")
					.equalsIgnoreCase(inputIndicativeObject.getIndValue() != null ? inputIndicativeObject.getIndValue() : "" ))
				|| (!(indicativeFromDB.getDfltIndicativeIndicator() != null ? indicativeFromDB.getDfltIndicativeIndicator(): "")
					.equalsIgnoreCase(inputIndicativeObject.getDfltIndicativeIndicator() != null ? inputIndicativeObject.getDfltIndicativeIndicator(): ""))
				|| (!(indicativeFromDB.getLogicIndicator() != null ? indicativeFromDB.getLogicIndicator(): "")
					.equalsIgnoreCase(inputIndicativeObject.getLogicIndicator() != null ? inputIndicativeObject.getLogicIndicator(): ""))
				|| (!(indicativeFromDB.getFieldLength()==inputIndicativeObject.getFieldLength()))
				|| (!(indicativeFromDB.getIndicativeSeq()==inputIndicativeObject.getIndicativeSeq()))
				|| (!(indicativeFromDB.getIndComments()!= null ? indicativeFromDB.getIndComments(): "")
					.equalsIgnoreCase(inputIndicativeObject.getIndComments()!= null ? inputIndicativeObject.getIndComments(): ""))){
			toBeUpdated = true;
		}
		return toBeUpdated;
	}
	/**
	 * This method return indicative details from DB based on region and segment
	 * @param indicativeSegment
	 * @param region
	 * @return
	 * @throws SevereException
	 */
	
	private List<IndicativeDetailBO> getIndicativeMappingFrmDB(String indicativeSegment, String region) throws SevereException {
		IndicativeDetailBO indicativeLayout = new IndicativeDetailBO();
		indicativeLayout.setIndicativeSegment(indicativeSegment);
		indicativeLayout.setIndicativeRegion(region);
		List<IndicativeDetailBO> result = null;
		result = new IndicativeMappingBusinessObjectBuilder()
				.exportIndicativeDetail(indicativeLayout);
		return result;
	}
	
	/**
	 * Method which convert a list of IndicativeDetailVO to IndicativeDetailBO
	 * @param indicativeDetailBOList
	 * @return List<IndicativeDetailVO>
	 */
	private List<IndicativeDetailVO> convertIndicativeDetailBOToVO(List<IndicativeDetailBO> indicativeDetailBOList){
		List<IndicativeDetailVO> resultList = new ArrayList<IndicativeDetailVO>();
		IndicativeDetailVO indicativeDetailVO;
		for(IndicativeDetailBO indicativeDetailBO: indicativeDetailBOList){
			indicativeDetailVO = new IndicativeDetailVO();
			indicativeDetailVO.setIndicativeSeq(indicativeDetailBO.getIndicativeSeq());
			indicativeDetailVO.setIndicativeSegment(indicativeDetailBO.getIndicativeSegment());
			indicativeDetailVO.setIndicativeCode(indicativeDetailBO.getIndicativeCode());
			indicativeDetailVO.setIndValue(indicativeDetailBO.getIndValue());
			indicativeDetailVO.setDfltIndicativeIndicator(indicativeDetailBO.getDfltIndicativeIndicator());
			indicativeDetailVO.setLastChangeTimestamp(indicativeDetailBO.getLastChangeTimestamp());
			indicativeDetailVO.setLastChangeUser(indicativeDetailBO.getLastChangeUser());
			indicativeDetailVO.setCreatedUser(indicativeDetailBO.getCreatedUser());
			indicativeDetailVO.setCreatedTimestamp(indicativeDetailBO.getCreatedTimestamp());
			indicativeDetailVO.setLogicIndicator(indicativeDetailBO.getLogicIndicator());
			indicativeDetailVO.setIndicativeRegion(indicativeDetailBO.getIndicativeRegion());
			indicativeDetailVO.setFieldLength(indicativeDetailBO.getFieldLength());
			indicativeDetailVO.setIndComments(indicativeDetailBO.getIndComments());
			indicativeDetailVO.setIndicativeCodeDescText(indicativeDetailBO.getIndicativeCodeDescText());
			resultList.add(indicativeDetailVO);
		}
		return resultList;
	}

	
	/**
	 * Method which convert a list of IndicativeDetailVO to IndicativeDetailBO
	 * @param indicativeDetailVOList
	 * @return List<IndicativeDetailBO>
	 */
	private List<IndicativeDetailBO> convertIndicativeDetailVOToBO(List<IndicativeDetailVO> indicativeDetailVOList){
		List<IndicativeDetailBO> resultList = new ArrayList<IndicativeDetailBO>();
		IndicativeDetailBO indicativeDetailBO;
		for(IndicativeDetailVO indicativeDetailVO: indicativeDetailVOList){
			indicativeDetailBO = new IndicativeDetailBO();
			indicativeDetailBO.setIndicativeSeq(indicativeDetailVO.getIndicativeSeq());
			indicativeDetailBO.setIndicativeSegment(indicativeDetailVO.getIndicativeSegment());
			indicativeDetailBO.setIndicativeCode(indicativeDetailVO.getIndicativeCode());
			indicativeDetailBO.setIndValue(indicativeDetailVO.getIndValue());
			indicativeDetailBO.setDfltIndicativeIndicator(indicativeDetailVO.getDfltIndicativeIndicator());
			indicativeDetailBO.setLastChangeTimestamp(indicativeDetailVO.getLastChangeTimestamp());
			indicativeDetailBO.setLastChangeUser(indicativeDetailVO.getLastChangeUser());
			indicativeDetailBO.setCreatedUser(indicativeDetailVO.getCreatedUser());
			indicativeDetailBO.setCreatedTimestamp(indicativeDetailVO.getCreatedTimestamp());
			indicativeDetailBO.setLogicIndicator(indicativeDetailVO.getLogicIndicator());
			indicativeDetailBO.setIndicativeRegion(indicativeDetailVO.getIndicativeRegion());
			indicativeDetailBO.setFieldLength(indicativeDetailVO.getFieldLength());
			indicativeDetailBO.setIndComments(indicativeDetailVO.getIndComments());
			indicativeDetailBO.setIndicativeCodeDescText(indicativeDetailVO.getIndicativeCodeDescText());
			resultList.add(indicativeDetailBO);
		}
		return resultList;
	}
	
	/**
	 * This method is being used to check if a indicative already exists in item table
	 * @param indicativeDetailVOList
	 * @return private List<IndicativeDetailVO>
	 * @throws SevereException
	 */
	private Map<String, List<IndicativeDetailVO>> validateIndicativeItemExists(List<IndicativeDetailVO> indicativeDetailVOList) throws SevereException{
		Map<String, List<IndicativeDetailVO>> ifExistsValidationResults = new HashMap<String, List<IndicativeDetailVO>>();
		IndicativeMappingBusinessObjectBuilder indicativeDetailBuilder = new IndicativeMappingBusinessObjectBuilder();
		List<ReferenceMappingBO> indicativeCodesFromItemList = 
			indicativeDetailBuilder.getIndicativeCodeDescriptionFromItem(BusinessConstants.CATALOG_ID_FOR_INDICATIVE);
		List<IndicativeDetailVO> indicativeCodesListNotPresent = new ArrayList<IndicativeDetailVO>();		
		for(IndicativeDetailVO indicativeDetailVO : indicativeDetailVOList){
			boolean itemExist = false;
			for (ReferenceMappingBO referenceMappingBO: indicativeCodesFromItemList) {
				if(referenceMappingBO.getReferenceId().equals(indicativeDetailVO.getIndicativeCode())){
					itemExist = true;
					indicativeDetailVO.setIndicativeCodeDescText(referenceMappingBO.getDescription());
					continue;
				}
			}
			if(!itemExist){
				indicativeCodesListNotPresent.add(indicativeDetailVO);
			}
		}
		ifExistsValidationResults.put("existingIndicatives", indicativeDetailVOList);
		ifExistsValidationResults.put("nonExistingIndicatives", indicativeCodesListNotPresent);
		return ifExistsValidationResults;
	}
	
	/**
	 * This method gets triggered when user performs import/copytoprod functionality
	 * @param indicativeDetailRequest
	 * @return WPDResponse
	 * @throws SevereException
	 */
	public WPDResponse execute(IndicativeDetailRequest indicativeDetailRequest)throws SevereException {		
		IndicativeDetailResponse indicativeDetailResponse = (IndicativeDetailResponse) WPDResponseFactory
		.getResponse(WPDResponseFactory.INDICATIVE_DETAIL_RESPONSE);
		List<ErrorMessage> validatedErrorMessageList = new ArrayList<ErrorMessage>();
		String action = indicativeDetailRequest.getAction();
		String segment = indicativeDetailRequest.getIndicativeSegment();
		String region = indicativeDetailRequest.getRegion();
		List<IndicativeDetailVO> firstInputList_ToCompare = null;
		List<IndicativeDetailVO> secondList_ToBeCompared = null;
		if(action.equalsIgnoreCase(BusinessConstants.INDICATIVE_MAPPING_OPERATION_PERFORMED_IMPORT)){
			Map<String, List<IndicativeDetailVO>> isExistsValidationProcessedResult =
			validateIndicativeItemExists(indicativeDetailRequest.getInidcativeDetailListToCompare());
			List<IndicativeDetailVO> nonExistingIndicativeCodeList = (List<IndicativeDetailVO>)isExistsValidationProcessedResult.get("nonExistingIndicatives");
			if(nonExistingIndicativeCodeList != null
					 && nonExistingIndicativeCodeList.size()>0){
				ErrorMessage errorMessage = new ErrorMessage(BusinessConstants.SEG_CODES_DOES_NOT_EXIST_ERROR);
				StringBuffer nonExistingSegmentCodes = new StringBuffer();
				for (IndicativeDetailVO indicativeDetailVO : nonExistingIndicativeCodeList) {
					if(nonExistingSegmentCodes.length()>0){
						nonExistingSegmentCodes.append(", ");
					}
					nonExistingSegmentCodes.append(indicativeDetailVO.getIndicativeCode());
				}						
				errorMessage.setParameters(new String[] { nonExistingSegmentCodes.toString() });
				validatedErrorMessageList.add(errorMessage);
			}else{
				firstInputList_ToCompare = (List<IndicativeDetailVO>)isExistsValidationProcessedResult.get("existingIndicatives");
				secondList_ToBeCompared = convertIndicativeDetailBOToVO(getIndicativeMappingFrmDB(segment,region));
			}
		}else if(action.equalsIgnoreCase(BusinessConstants.INDICATIVE_MAPPING_OPERATION_PERFORMED_COPY_TO_PROD)){
			firstInputList_ToCompare = convertIndicativeDetailBOToVO(getIndicativeMappingFrmDB(segment,BusinessConstants.REGION_TEST));
			secondList_ToBeCompared = convertIndicativeDetailBOToVO(getIndicativeMappingFrmDB(segment,BusinessConstants.REGION_PROD.toUpperCase()));			
		}		
		if(validatedErrorMessageList.size()>0){
			indicativeDetailResponse.setErrorMessageList(validatedErrorMessageList);
		}else{
			Map<String, List<IndicativeDetailVO>> processedDetailsForConfirmation = 
				compareData(firstInputList_ToCompare, secondList_ToBeCompared);
			indicativeDetailResponse.setProcessedDetailsForConfirmation(processedDetailsForConfirmation);
		}
		return indicativeDetailResponse;
	}
	
	/**
	 * This method gets triggered when user clicks confirm button of either import/copyToProd functionality of
	 * indicative mapping
	 * @param request
	 * @return WPDResponse
	 * @throws SevereException 
	 */
	public WPDResponse execute(ConfirmImportIndicativeMappingRequest confirmImportIndicativeMappingRequest)throws SevereException {
		ConfirmImportIndicativeMappingResponse confirmImportIndicativeMappingResponse = (ConfirmImportIndicativeMappingResponse) WPDResponseFactory
		.getResponse(WPDResponseFactory.CONFIRM_IMPORT_RESPONSE);		
		Map<String, List<IndicativeDetailVO>> excelIndicativeSegments = 
			confirmImportIndicativeMappingRequest.getProcessedExcelIndicativeSeg();
		IndicativeMappingBusinessObjectBuilder indicativeMappingBusinessObjectBuilder = new IndicativeMappingBusinessObjectBuilder();	
		List<IndicativeDetailBO> deletedIndicativeCodes = 
			convertIndicativeDetailVOToBO((List<IndicativeDetailVO>)excelIndicativeSegments.get(BusinessConstants.DELETED_INDICATIVES));
		List<IndicativeDetailBO> modifiedIndicativeCodes = 
			convertIndicativeDetailVOToBO((List<IndicativeDetailVO>)excelIndicativeSegments.get(BusinessConstants.UPDATED_INDICATIVES));
		List<IndicativeDetailBO> newIndicativeCodes = 
			convertIndicativeDetailVOToBO((List<IndicativeDetailVO>)excelIndicativeSegments.get(BusinessConstants.ADDED_INDICATIVES));
		boolean isSuccess = indicativeMappingBusinessObjectBuilder
		.processUploadIndicativeMapping(deletedIndicativeCodes,
				modifiedIndicativeCodes, newIndicativeCodes,
				confirmImportIndicativeMappingRequest.getUser(),
				confirmImportIndicativeMappingRequest.getIndicativeSegmentNumber(),
				confirmImportIndicativeMappingRequest.getRegion());
		if (isSuccess) {
			if(confirmImportIndicativeMappingRequest.getRegion().equalsIgnoreCase(BusinessConstants.REGION_PROD)){
				confirmImportIndicativeMappingResponse.addMessage(new InformationalMessage(BusinessConstants.COPY_TO_PROD_SUCCESS));						
			}else{
				confirmImportIndicativeMappingResponse.addMessage(new InformationalMessage(BusinessConstants.IMPORT_SUCCESS));
			}
		} else {
			confirmImportIndicativeMappingResponse.addMessage(new ErrorMessage(BusinessConstants.UPLOAD_ERROR));
		}
		return confirmImportIndicativeMappingResponse;
	}	
	
}