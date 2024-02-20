/*
 * BenefitLevelBusinessService.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.benefitlevel.service;

import com.wellpoint.wpd.business.benefitlevel.builder.BenefitLevelBusinessObjectBuilder;
import com.wellpoint.wpd.business.benefitlevel.locatecriteria.BenefitLevelLocateCriteria;
import com.wellpoint.wpd.business.benefitlevel.locatecriteria.BenefitLocateCriteria;
import com.wellpoint.wpd.business.benefitlevel.locateresult.BenefitLevelLocateResult;
import com.wellpoint.wpd.business.framework.service.WPDService;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.benefitlevel.bo.BenefitLevelBO;
import com.wellpoint.wpd.common.benefitlevel.bo.BenefitLineBO;
import com.wellpoint.wpd.common.benefitlevel.bo.BenefitWrapperBO;
import com.wellpoint.wpd.common.benefitlevel.request.BenefitLevelRequest;
import com.wellpoint.wpd.common.benefitlevel.request.DeleteBenefitLevelRequest;
import com.wellpoint.wpd.common.benefitlevel.request.SaveBenefitLevelRequest;
import com.wellpoint.wpd.common.benefitlevel.response.BenefitLevelResponse;
import com.wellpoint.wpd.common.benefitlevel.vo.BenefitLevelVO;
import com.wellpoint.wpd.common.benefitlevel.vo.BenefitLineVO;
import com.wellpoint.wpd.common.benefitlevel.vo.BenefitWrapperVO;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.web.framework.util.SequenceUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * Business Service class for Benefit Level
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitLevelBusinessService extends WPDService{

	/**
	 *  (non-Javadoc)
	 * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
	 */
	public WPDResponse execute(WPDRequest request) throws ServiceException {

		return null;
	}
	/**
	 * 
	 * @param benefitLevelRequest
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(BenefitLevelRequest benefitLevelRequest) throws ServiceException {
		BenefitLevelResponse benefitLevelResponse = new BenefitLevelResponse();
        try {
          
        	BenefitWrapperVO benefitWrapperVO = benefitLevelRequest.getBenefitWrapperVO();
        	BenefitWrapperBO benefitWrapperBO = new BenefitWrapperBO();
    		List benefitLevels = benefitWrapperVO.getBenefitLevelsList();
    		int levelListSize =  benefitLevels.size();
    		List benefitLevelBOs = null;
    		List benefitlineBOs = null;
    		if (null != benefitLevels && levelListSize > 0) {
    			benefitLevelBOs = new ArrayList();
    			for(int i = 0; i < levelListSize;i++){
    				BenefitLevelVO benefitLevelVO = (BenefitLevelVO) benefitLevels.get(i);
    				BenefitLevelBO benefitLevelBO = copyBusinessObjectFromValueObject(benefitLevelVO);
    				List benefitLines = benefitLevelVO.getBenefitLines();
    				int lineListSize = benefitLines.size();
    				if(null != benefitLines && lineListSize > 0){
    					benefitlineBOs = new ArrayList();
    					for(int j = 0; j < lineListSize; j++){
    						BenefitLineVO benefitLineVO = (BenefitLineVO)benefitLines.get(j);
    						BenefitLineBO benefitLineBO = copyBusinessObjectFromValueObject(benefitLineVO);
    						benefitLineBO.setBenefitLevelId(benefitLevelBO.getBenefitLevelId());
    						benefitLineBO.setBenefitDefenitionId(benefitWrapperVO.getBenefitDefinitionId());
    						benefitlineBOs.add(benefitLineBO);
    					}
    				}
    				benefitLevelBO.setBenefitLines(benefitlineBOs);
    				benefitLevelBOs.add(benefitLevelBO);
    			}
    		}
        	benefitWrapperBO.setBenefitLevelsList(benefitLevelBOs);
        	benefitWrapperBO.setBenefitDefinitionId(benefitWrapperVO.getBenefitDefinitionId());
        	benefitLevelResponse = searchBenefit(benefitWrapperBO,benefitLevelRequest); 
        	registerSequence(benefitWrapperBO.getBenefitLevelsList());
        } catch (Exception ex) {
        	Logger.logError(ex);
            List logParameters = new ArrayList();
            logParameters.add(benefitLevelRequest);
            String logMessage = "Failed while processing CreateBenefitRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        return benefitLevelResponse;
	}
	
	/**
	 * 
	 * @param benefitLevels
	 */
	private void registerSequence(List benefitLevels) {
		String benefitLevelId = BusinessConstants.BENEFIT_LEVEL_ID;
		String benefitLevelSeq = BusinessConstants.BENEFIT_LEVEL_ID;
		SequenceUtil sequenceUtil = new SequenceUtil();
		sequenceUtil.registerObjects(benefitLevels,benefitLevelId,benefitLevelSeq);
	}
	/**
	 * 
	 * @param benefitWrapperBO
	 * @param benefitLevelRequest
	 * @return
	 * @throws ServiceException
	 */
	public BenefitLevelResponse searchBenefit(BenefitWrapperBO benefitWrapperBO,BenefitLevelRequest benefitLevelRequest) throws ServiceException {
		BenefitLevelResponse benefitLevelResponse = new BenefitLevelResponse();
        try {
        	List benefitSearchResultsList = null;
        	
        	LocateResults searchResults = null;
        	BenefitWrapperBO benefitWrapperBO2 = new BenefitWrapperBO();
        	BenefitLocateCriteria benefitLocateCriteria = new BenefitLocateCriteria();
        	benefitLocateCriteria.setBenefitDefinitionId(benefitWrapperBO.getBenefitDefinitionId());
        	benefitWrapperBO2.setBenefitDefinitionId(benefitWrapperBO.getBenefitDefinitionId());
        	BenefitLevelBusinessObjectBuilder benefitLevelBusinessObjectBuilder = new BenefitLevelBusinessObjectBuilder();
        	searchResults = benefitLevelBusinessObjectBuilder.searchBenefitLevels(benefitLocateCriteria,benefitLevelRequest.getUser(),false);
        	int searchResultCount = searchResults.getTotalResultsCount();
        	benefitSearchResultsList = searchResults.getLocateResults();
 	        if (null != benefitSearchResultsList && !benefitSearchResultsList.isEmpty()) {
 	        	benefitWrapperBO2.setBenefitLevelsList(benefitSearchResultsList);

 	        } else if (benefitSearchResultsList.size() == 0 && searchResultCount == 0) {
 	        	List errorList = new ArrayList();
 	            errorList.add(new InformationalMessage(
 	                    BusinessConstants.MANDATE_QUESTION_SEARCHRESULT_NOT_FOUND));
 	           benefitLevelResponse.setMessages(errorList);
 	        }
 	        searchBenefitLinesForCorrespondingBenefitLevel(benefitWrapperBO2.getBenefitLevelsList(),benefitLevelRequest);

        } catch (Exception ex) {
        	Logger.logError(ex);
            List logParameters = new ArrayList();
            logParameters.add(benefitLevelRequest);
            String logMessage = "Failed while processing CreateBenefitRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        return benefitLevelResponse;
	}

	/**
	 * @param benefitLevelsList
	 * @throws SevereException
	 */
	private void searchBenefitLinesForCorrespondingBenefitLevel(List benefitLevelsList,BenefitLevelRequest benefitLevelRequest) throws SevereException {
		BenefitLevelResponse benefitLevelResponse = new BenefitLevelResponse();
		List benefitLinesSearchResultsList = null;
    	int levelListSize = benefitLevelsList.size();
    	LocateResults searchResults = null;
		if(null != benefitLevelsList && levelListSize > 0){
			for(int i = 0; i < levelListSize; i++){
				BenefitLevelLocateResult benefitLevelLocateResult = (BenefitLevelLocateResult)benefitLevelsList.get(i);
				BenefitLevelLocateCriteria benefitLevelLocateCriteria = new BenefitLevelLocateCriteria();
				benefitLevelLocateCriteria.setBenefitLevelId(benefitLevelLocateResult.getBenefitLevelId());
				BenefitLevelBusinessObjectBuilder benefitLevelBusinessObjectBuilder = new BenefitLevelBusinessObjectBuilder();
	        	try {//FIXME multiple time db hit, modify the search query so only a single query will do(Performance check)
					searchResults = benefitLevelBusinessObjectBuilder.searchBenefitLines(benefitLevelLocateCriteria,benefitLevelRequest.getUser(),false);
				} catch (ServiceException e) {
					
					
					Logger.logError(e);
				}
	        	int searchResultCount = searchResults.getTotalResultsCount();
	        	benefitLinesSearchResultsList = searchResults.getLocateResults();
	 	        if (benefitLinesSearchResultsList.size() > 0) {
	 	        	benefitLevelLocateResult.setBenefitLines(benefitLinesSearchResultsList);

	 	        } else if (benefitLinesSearchResultsList.size() == 0 && searchResultCount == 0) {
	 	        	List errorList = new ArrayList();
	 	            errorList.add(new InformationalMessage(BusinessConstants.MANDATE_QUESTION_SEARCHRESULT_NOT_FOUND));
	 	           benefitLevelResponse.setMessages(errorList);
	 	        }
			}
		}
	}

	/**
	 * @param benefitLineVO
	 * @return
	 * @throws ServiceException
	 */
	private BenefitLineBO copyBusinessObjectFromValueObject(BenefitLineVO benefitLineVO) throws ServiceException {
		BenefitLineBO benefitLineBO = new BenefitLineBO();
		benefitLineBO.setBenefitValue(benefitLineVO.getBenefitValue());
		benefitLineBO.setDataTypeId(benefitLineVO.getDataTypeId());
		benefitLineBO.setPVA(benefitLineVO.getPVA());
		return benefitLineBO;
	}

	/**
	 * @param benefitLevelVO
	 * @return
	 */
	private BenefitLevelBO copyBusinessObjectFromValueObject(BenefitLevelVO benefitLevelVO) {
		BenefitLevelBO benefitLevelBO = new BenefitLevelBO();
		benefitLevelBO.setBenefitDefinitionId(benefitLevelVO.getBenefitDefinitionId());
		benefitLevelBO.setBenefitLevelId(benefitLevelVO.getBenefitLevelId());
		benefitLevelBO.setBenefitLevelDesc(benefitLevelVO.getBenefitLevelDesc());
		benefitLevelBO.setBenefitTerm(benefitLevelVO.getBenefitTerm());
		benefitLevelBO.setBenefitQualifier(benefitLevelVO.getBenefitQualifier());
		benefitLevelBO.setReference(benefitLevelVO.getReference());
		return benefitLevelBO;
	}
	/**
	 * to save the benefit levels
	 * 
	 * @param saveBenefitLevelRequest
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(SaveBenefitLevelRequest saveBenefitLevelRequest) throws ServiceException {
		BenefitLevelResponse benefitLevelResponse = new BenefitLevelResponse();
        try {      
        	BenefitLevelRequest benefitLevelRequest = new BenefitLevelRequest();
        	BenefitWrapperVO benefitWrapperVO = saveBenefitLevelRequest.getBenefitWrapperVO();
        	BenefitWrapperBO benefitWrapperBO = new BenefitWrapperBO();
        	
    		List benefitLevels = benefitWrapperVO.getBenefitLevelsList();
    		int levelListSize = benefitLevels.size();
    		if(null != benefitLevels && levelListSize > 0){
    			List benefitLevelBOs = new ArrayList();
    			for(int  i = 0;i < levelListSize;i++){
    				BenefitLevelVO benefitLevelVO = (BenefitLevelVO)benefitLevels.get(i);
    				BenefitLevelBO benefitLevelBO = new BenefitLevelBO();
    				benefitLevelBO.setBenefitLevelId(benefitLevelVO.getBenefitLevelId());
    				benefitLevelBO.setBenefitLevelDesc(benefitLevelVO.getBenefitLevelDesc());
    				benefitLevelBO.setBenefitDefinitionId(benefitLevelVO.getBenefitDefinitionId());
    				benefitLevelBO.setReference(benefitLevelVO.getReference());
    				benefitLevelBO.setBenefitLevelSeq(benefitLevelVO.getBenefitLevelSeq());
    				List benefitLineBOs = new ArrayList();
    				List benefitLines = benefitLevelVO.getBenefitLines();
    				int linesListSize = benefitLines.size();
    				if(null != benefitLines && linesListSize >0){
    					for(int  j = 0; j < linesListSize; j++){
    						BenefitLineVO benefitLineVO = (BenefitLineVO)benefitLines.get(j);
    						BenefitLineBO benefitLineBO = new BenefitLineBO();
    						benefitLineBO.setBenefitLevelId(benefitLineVO.getBenefitLevelId());
    						benefitLineBO.setBenefitLineId(benefitLineVO.getBenefitLineId());
    						benefitLineBO.setDataTypeId(benefitLineVO.getDataTypeId());
    						benefitLineBO.setBenefitValue(benefitLineVO.getBenefitValue());
    						benefitLineBO.setPVA(benefitLineVO.getPVA());
    						benefitLineBOs.add(benefitLineBO);
    					}
    				}
    				benefitLevelBO.setBenefitLines(benefitLineBOs);
    				benefitLevelBOs.add(benefitLevelBO);
    			}
    			benefitWrapperBO.setBenefitLevelsList(benefitLevelBOs);
    		}
    		
    		benefitWrapperBO.setBenefitDefinitionId(benefitWrapperVO.getBenefitDefinitionId());
    		benefitLevelResponse = this.searchBenefit(benefitWrapperBO,benefitLevelRequest);
        	registerSequence(benefitWrapperBO.getBenefitLevelsList());
        } catch (Exception ex) {
        	Logger.logError(ex);
            List logParameters = new ArrayList();
            logParameters.add(saveBenefitLevelRequest);
            String logMessage = "Failed while processing CreateBenefitRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        return benefitLevelResponse;
	}
	
	/**
	 * to delete the benefit levels
	 * 
	 * @param deleteBenefitLevelRequest
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(DeleteBenefitLevelRequest deleteBenefitLevelRequest) throws ServiceException {
		BenefitLevelResponse benefitLevelResponse = new BenefitLevelResponse();
        try {      
        	BenefitLevelRequest benefitLevelRequest = new BenefitLevelRequest();
        	BenefitWrapperVO benefitWrapperVO = deleteBenefitLevelRequest.getBenefitWrapperVO();
        	BenefitWrapperBO benefitWrapperBO = new BenefitWrapperBO();
        	
    		List benefitLevels = benefitWrapperVO.getBenefitLevelsList();
    		int levelListSize = benefitLevels.size();
    		if(null != benefitLevels && levelListSize > 0){
    			List benefitLevelBOs = new ArrayList();
    			for(int  i = 0;i < levelListSize;i++){
    				BenefitLevelVO benefitLevelVO = (BenefitLevelVO)benefitLevels.get(i);
    				BenefitLevelBO benefitLevelBO = new BenefitLevelBO();
    				benefitLevelBO.setBenefitLevelId(benefitLevelVO.getBenefitLevelId());
    				benefitLevelBO.setBenefitLevelDesc(benefitLevelVO.getBenefitLevelDesc());
    				benefitLevelBO.setBenefitDefinitionId(benefitLevelVO.getBenefitDefinitionId());
    				List benefitLineBOs = new ArrayList();
    				List benefitLines = benefitLevelVO.getBenefitLines();
    				int linesListSize = benefitLines.size();
    				if(null != benefitLines && linesListSize >0){
    					for(int  j = 0; j < linesListSize; j++){
    						BenefitLineVO benefitLineVO = (BenefitLineVO)benefitLines.get(j);
    						BenefitLineBO benefitLineBO = new BenefitLineBO();
    						benefitLineBO.setBenefitLevelId(benefitLineVO.getBenefitLevelId());
    						benefitLineBO.setBenefitLineId(benefitLineVO.getBenefitLineId());
    						benefitLineBO.setDataTypeId(benefitLineVO.getDataTypeId());
    						benefitLineBO.setBenefitValue(benefitLineVO.getBenefitValue());
    						benefitLineBO.setPVA(benefitLineVO.getPVA());
    						benefitLineBOs.add(benefitLineBO);
    					}
    				}
    				benefitLevelBO.setBenefitLines(benefitLineBOs);
    				benefitLevelBOs.add(benefitLevelBO);
    			}
    			benefitWrapperBO.setBenefitLevelsList(benefitLevelBOs);
    		}
    		
    		benefitWrapperBO.setBenefitDefinitionId(benefitWrapperVO.getBenefitDefinitionId());
        	BenefitLevelBusinessObjectBuilder benefitLevelBusinessObjectBuilder = new BenefitLevelBusinessObjectBuilder();        	
        	benefitLevelResponse = this.searchBenefit(benefitWrapperBO,benefitLevelRequest);
        	registerSequence(benefitWrapperBO.getBenefitLevelsList());
        } catch (Exception ex) {
        	Logger.logError(ex);
            List logParameters = new ArrayList();
            logParameters.add(deleteBenefitLevelRequest);
            String logMessage = "Failed while processing CreateBenefitRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        return benefitLevelResponse;
	}


}
