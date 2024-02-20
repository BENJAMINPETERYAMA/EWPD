/*
 * SPSMappingBusinessService.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.blueexchange;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.business.blueexchange.builder.SPSMappingBusinessObjectBuilder;
import com.wellpoint.wpd.business.framework.service.WPDService;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.blueexchange.bo.SPSMappingBO;
import com.wellpoint.wpd.common.blueexchange.request.DeleteSpsMappingRequest;
import com.wellpoint.wpd.common.blueexchange.request.SPSMappingCreateRequest;
import com.wellpoint.wpd.common.blueexchange.request.SPSMappingUpdateRequest;
import com.wellpoint.wpd.common.blueexchange.request.SPSMappingRetrieveRequest;
import com.wellpoint.wpd.common.blueexchange.request.SearchSPSMappingRequest;
import com.wellpoint.wpd.common.blueexchange.response.DeleteSpsMappingResponse;
import com.wellpoint.wpd.common.blueexchange.response.SPSMappingCreateResponse;
import com.wellpoint.wpd.common.blueexchange.response.SPSMappingUpdateResponse;
import com.wellpoint.wpd.common.blueexchange.response.SPSMappingViewResponse;
import com.wellpoint.wpd.common.blueexchange.response.SearchSPSMaintainResponse;
import com.wellpoint.wpd.common.blueexchange.vo.SPSMappingVO;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SPSMappingBusinessService extends WPDService {
	
	
	/**
	 * Inherited abstract method of WPDService
	 * 
	 *  @param request
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(WPDRequest request) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * This method transforms the value Object to BO and create response object.
	 * Calls the builder to insert the Business Object 
	 * 
	 * @param request
	 * @return mappingCreateResponse
	 * @throws ServiceException
	 */
	public WPDResponse execute(SPSMappingCreateRequest request) throws ServiceException {
		SPSMappingBusinessObjectBuilder sPSMappingBusinessObjectBuilder = new SPSMappingBusinessObjectBuilder();
		//Transforming the value Object to BO
		SPSMappingBO mappingBO = getSPSMappingBO(request.getSpsMappingVO());
		//Creating the Response Object
		SPSMappingCreateResponse mappingCreateResponse = (SPSMappingCreateResponse)WPDResponseFactory.getResponse(WPDResponseFactory.SPS_MAPPING_CREATE_RESPONSE);
		try {
			 // Builder call to insert the Business Object   
			if(sPSMappingBusinessObjectBuilder.persist(mappingBO,request.getUser())){
				List message = new ArrayList();
				InformationalMessage informationalMessage = new InformationalMessage("spsMapping.create.success");
				message.add(informationalMessage);
				mappingCreateResponse.setMessages(message);
				mappingCreateResponse.setMappingBO(mappingBO);
			}
		} catch (SevereException e) {
			List logParameters = new ArrayList();
			logParameters.add(request);
			String logMessage = "Failed while processing SPSMappingCreateRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}
		
		// TODO Auto-generated method stub
		return mappingCreateResponse;
	}
	/**
	 * 
	 * @param request
	 * @return mappingUpdateResponse
	 * @throws ServiceException
	 */
	public WPDResponse execute(SPSMappingUpdateRequest request) throws ServiceException {
		SPSMappingBusinessObjectBuilder sPSMappingBusinessObjectBuilder = new SPSMappingBusinessObjectBuilder();
		SPSMappingVO mappingVO = null;
		//Transforming the value Object to BO
		SPSMappingBO mappingBO = getSPSMappingBO(request.getSpsMappingVO());
		//Creating the Response Object
		SPSMappingUpdateResponse mappingUpdateResponse = (SPSMappingUpdateResponse)WPDResponseFactory.getResponse(WPDResponseFactory.SPS_MAPPING_UPDATE_RESPONSE);
		try {
		 // Builder call to update the Business Object    
			if(sPSMappingBusinessObjectBuilder.update(mappingBO,request.getUser())){
				mappingBO = sPSMappingBusinessObjectBuilder
				.retrieve(mappingBO);
				if (null != mappingBO) {
					mappingVO = this.getSPSMappingVO(mappingBO);
					mappingUpdateResponse.setMappingVO(mappingVO);
				}
				
				List message = new ArrayList();
				InformationalMessage informationalMessage = new InformationalMessage("spsMapping.update.success");
				message.add(informationalMessage);
				mappingUpdateResponse.setMessages(message);
			}
		} catch (SevereException e) {
			List logParameters = new ArrayList();
			logParameters.add(request);
			String logMessage = "Failed while processing SPSMappingUpdateRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}
		
		// TODO Auto-generated method stub
		return mappingUpdateResponse;
	}
	
	/**
	 * This method gets the request parameters from the backing bean and set the
	 * parameters to businessObject and sent this businessObject to
	 * SPSMappingBusinessObjectBuilder. SPSMappingBusinessObjectBuilder returns
	 * businessObject and it is converted to valueObject and sent back to
	 * backinBean
	 * 
	 * @param request
	 * @return mappingViewResponse
	 * @throws ServiceException
	 */
	public WPDResponse execute(SPSMappingRetrieveRequest request)
			throws ServiceException {

		SPSMappingBusinessObjectBuilder sPSMappingBusinessObjectBuilder = new SPSMappingBusinessObjectBuilder();
		SPSMappingBO mappingBO = getViewSPSMappingBO(request);
		SPSMappingViewResponse mappingViewResponse = (SPSMappingViewResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.SPS_MAPPING_VIEW_RESPONSE);
		SPSMappingVO mappingVO = null;

		try {
			mappingBO = sPSMappingBusinessObjectBuilder
					.retrieve(mappingBO);
			if (null != mappingBO) {
				mappingVO = this.getSPSMappingVO(mappingBO);
			}
		} catch (SevereException e) {
			List logParameters = new ArrayList();
			logParameters.add(request);
			String logMessage = "Failed while processing SPSMappingRetrieveRequest";
			throw new ServiceException(logMessage, logParameters, e);

		}
		mappingViewResponse.setMappingVO(mappingVO);
		return mappingViewResponse;
	}	
	
	/**
	 * This method set all the mappingVO values to mappingBO
	 * 
	 * @param mappingVO
	 * @return mappingBO
	 */
	private SPSMappingBO getSPSMappingBO(SPSMappingVO mappingVO){
		//Method for transforming the Value Obect to the Business object
		SPSMappingBO mappingBO = new SPSMappingBO();
		mappingBO.setSpsParameter(mappingVO.getSpsParameter());
		mappingBO.setEb01Value(mappingVO.getEb01Value());
		mappingBO.setEb02Value(mappingVO.getEb02Value());
		mappingBO.setEb06Value(mappingVO.getEb06Value());
		mappingBO.setEb09Value(mappingVO.getEb09Value());
		mappingBO.setHsd1Value(mappingVO.getHsd1Value());
		mappingBO.setHsd2Value(mappingVO.getHsd2Value());
		mappingBO.setHsd3Value(mappingVO.getHsd3Value());
		mappingBO.setHsd4Value(mappingVO.getHsd4Value());
		mappingBO.setHsd5Value(mappingVO.getHsd5Value());
		mappingBO.setHsd6Value(mappingVO.getHsd6Value());
		mappingBO.setAccumulatorSPSID(mappingVO.getAccummulatorSPSID());
		return mappingBO;
	}
	
	 /**
	   * Service method for SPSMapping Locate.
	   * 
	   * @param SearchSPSMappingRequest            
	   * @return searchSPSMaintainResponse
	   * @throws ServiceException
	   */
	  public WPDResponse execute(SearchSPSMappingRequest request) throws ServiceException
	  {
	  	Logger.logInfo("Entering execute method, request = " + request.getClass().getName());
	  	SPSMappingBusinessObjectBuilder sPSMappingBusinessObjectBuilder=new SPSMappingBusinessObjectBuilder();
	  	SearchSPSMaintainResponse searchSPSMaintainResponse = (SearchSPSMaintainResponse)
					WPDResponseFactory.getResponse(WPDResponseFactory.SEARCH_SPS_MAINTAIN_RESPONSE);
	  	List searchResult;
	  	switch (request.getAction()) {
	  	
	  	case BusinessConstants.LOCATE_SPS_MAPPING:
		  	try
			{
		  		
		  		Logger.logInfo("Returning  from execute method for request=" + request.getClass().getName());
		  		SPSMappingBO mappingBO =new SPSMappingBO();
		  		setInputList(mappingBO,request);
		  		searchResult=sPSMappingBusinessObjectBuilder.locateSpsMaintain(mappingBO);  			  		
		  		if(null != searchResult){
					if (searchResult.size() > 50) {
						searchSPSMaintainResponse.addMessage(new InformationalMessage(
								BusinessConstants.MSG_SEARCH_RESULT_EXCEEDS));
						searchResult.remove(searchResult.size() - 1);
					} else if (searchResult.size() <= 0) {
						searchSPSMaintainResponse.addMessage(new InformationalMessage(
								BusinessConstants.SEARCH_RESULT_NOT_FOUND));
					}
				}
		  		searchSPSMaintainResponse.setSearchList(searchResult);
	        }
	        catch (SevereException ex){
	        	
	        	throw new ServiceException("Exception occured while Builder call", null, ex);
	        }
	        break;
	        
	        
	  	case BusinessConstants.RETRIEVE_SPS_MAPPING_DF:
	  		try{
	  			
	  			Logger.logInfo("Returning  from execute method for request=" + request.getClass().getName());
	  			searchResult = sPSMappingBusinessObjectBuilder.retrieveSPSMapping(request.getStatus());
	  			searchSPSMaintainResponse.setSearchList(searchResult);
	  			
	  		}catch(SevereException exp){
	  			
	  			throw new ServiceException("Exception occured while Builder call", null, exp);	  			
	  		}
	  		break;
	 	case BusinessConstants.RETRIEVE_NA_SPS_MAPPING_DF:
	  		try{
	  			
	  			Logger.logInfo("Returning  from execute method for request=" + request.getClass().getName());
	  			searchResult = sPSMappingBusinessObjectBuilder.retrieveNASPSMapping();
	  			searchSPSMaintainResponse.setSearchList(searchResult);
	  			
	  		}catch(SevereException exp){
	  			
	  			throw new ServiceException("Exception occured while Builder call", null, exp);	  			
	  		}
	  		break;		  		
	  	}
        return searchSPSMaintainResponse;
	  }
	
	  /**
	   * this method set all the values to mappingBO
	   * @param mappingBO, SearchSPSMappingRequest
	   * 
	   */
	  
	  private void setInputList(SPSMappingBO mappingBO,SearchSPSMappingRequest request){
	  	
	  	mappingBO.setSpsParameterList(request.getSPSMappingSearchVO()
	  			.getSpsParameterList());
	  	mappingBO.setEb01ValueList(request.getSPSMappingSearchVO()
	  			.getEb01ValueList());
		mappingBO.setEb02ValueList(request.getSPSMappingSearchVO()
				.getEb02ValueList());
		mappingBO.setEb06ValueList(request.getSPSMappingSearchVO()
				.getEb06ValueList());
		mappingBO.setEb09ValueList(request.getSPSMappingSearchVO()
				.getEb09ValueList());
		mappingBO.setHsd1ValueList(request.getSPSMappingSearchVO()
				.getHsd1ValueList());
		mappingBO.setHsd2ValueList(request.getSPSMappingSearchVO()
				.getHsd2ValueList());
		mappingBO.setHsd3ValueList(request.getSPSMappingSearchVO()
				.getHsd3ValueList());
		mappingBO.setHsd4ValueList(request.getSPSMappingSearchVO()
				.getHsd4ValueList());
		mappingBO.setHsd5ValueList(request.getSPSMappingSearchVO()
				.getHsd5ValueList());
		mappingBO.setHsd6ValueList(request.getSPSMappingSearchVO()
				.getHsd6ValueList());
		mappingBO.setAccummulatorSPSIDList(request.getSPSMappingSearchVO()
				.getAccumulatorSPSIDList());
	  }
	  
	  /**
		 * This method gets the request parameters and build the business object and
		 * return it to the method called
		 * 
		 * @param mappingViewRequest
		 * @return mappingBO
		 */
		private SPSMappingBO getViewSPSMappingBO(
				SPSMappingRetrieveRequest mappingViewRequest) {
			SPSMappingBO mappingBO = new SPSMappingBO();
			mappingBO.setSpsParameter(mappingViewRequest.getSpsParameter());
			return mappingBO;

		}
		
		/**
		 * This method gets all parameters from the businessObject and
		 * set it to the valueObject and return the valueObject
		 * to the method called
		 * 
		 * @param mappingBO
		 * @return mappingVO
		 */
		private SPSMappingVO getSPSMappingVO(SPSMappingBO mappingBO) {

			SPSMappingVO mappingVO = new SPSMappingVO();
			mappingVO.setSpsParameter(mappingBO.getSpsParameter());
			mappingVO.setSpsParameterDesc(mappingBO.getSpsParameterDesc());
			mappingVO.setEb01Value(mappingBO.getEb01Value());
			mappingVO.setEb01ValueDesc(mappingBO.getEb01ValueDesc());
			mappingVO.setEb02Value(mappingBO.getEb02Value());
			mappingVO.setEb02ValueDesc(mappingBO.getEb02ValueDesc());
			mappingVO.setEb06Value(mappingBO.getEb06Value());
			mappingVO.setEb06ValueDesc(mappingBO.getEb06ValueDesc());
			mappingVO.setEb09Value(mappingBO.getEb09Value());
			mappingVO.setEb09ValueDesc(mappingBO.getEb09ValueDesc());
			mappingVO.setHsd1Value(mappingBO.getHsd1Value());
			mappingVO.setHsd1ValueDesc(mappingBO.getHsd1ValueDesc());
			mappingVO.setHsd2Value(mappingBO.getHsd2Value());
			//mappingVO.setHsd2ValueDesc(mappingBO.getHsd2ValueDesc());
			mappingVO.setHsd3Value(mappingBO.getHsd3Value());
			mappingVO.setHsd3ValueDesc(mappingBO.getHsd3ValueDesc());
			mappingVO.setHsd4Value(mappingBO.getHsd4Value());
			//mappingVO.setHsd4ValueDesc(mappingBO.getHsd4ValueDesc());
			mappingVO.setHsd5Value(mappingBO.getHsd5Value());
			mappingVO.setHsd5ValueDesc(mappingBO.getHsd5ValueDesc());
			mappingVO.setHsd6Value(mappingBO.getHsd6Value());
			//mappingVO.setHsd6ValueDesc(mappingBO.getHsd6ValueDesc());
			mappingVO.setAccummulatorSPSID(mappingBO.getAccumulatorSPSID());
			mappingVO.setAccummulatorSPSDesc(mappingBO.getAccummulatorSPSDesc());
			mappingVO.setCreatedUser(mappingBO.getCreatedUser());
			mappingVO.setCreatedTimestamp(mappingBO.getCreatedTimestamp());
			mappingVO.setLastUpdatedUser(mappingBO.getLastUpdatedUser());
			mappingVO.setLastUpdatedTimestamp(mappingBO.getLastUpdatedTimestamp());
			return mappingVO;
		}

		/**
		 * this method will call delete method in builder
		 * delete sps mapping 
		 * return response contain delete message 
		 * 
		 * @param deleteSpsMappingRequest
		 * @return deleteSpsMappingResponse
		 * @throws ServiceException
		 */
		public WPDResponse execute(DeleteSpsMappingRequest deleteSpsMappingRequest) throws ServiceException
		  {
		  	Logger.logInfo("Entering execute method, request = " + deleteSpsMappingRequest.getClass().getName());
		  	SPSMappingBusinessObjectBuilder sPSMappingBusinessObjectBuilder=new SPSMappingBusinessObjectBuilder();
		  	DeleteSpsMappingResponse deleteSpsMappingResponse = (DeleteSpsMappingResponse)
						WPDResponseFactory.getResponse(WPDResponseFactory.DELETE_SPS_MAPPING_RESPONSE);
		  	SPSMappingBO sPSMappingBO =new SPSMappingBO();
		  	try {
		  	sPSMappingBO.setSpsParameter(deleteSpsMappingRequest.getSpsParameter());
		  	sPSMappingBusinessObjectBuilder.delete(sPSMappingBO,deleteSpsMappingRequest.getUser());
		  	InformationalMessage message = new InformationalMessage(
					BusinessConstants.SPS_DELETED_SUCCESSFULY);
		  	message.setParameters(new String[] {deleteSpsMappingRequest.getSpsParameter()});
		  	deleteSpsMappingResponse.addMessage(message);
		  	}catch (SevereException e) {
		  		throw new ServiceException("Exception occured while Builder call", null, e);
			}
		  	
		  	return deleteSpsMappingResponse;
		  }
}
