/*
 * Created on Mar 7, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.standardbenefit.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.wpd.business.benefitdefinition.adapter.NonAdjBenefitMandateAdapterManager;
import com.wellpoint.wpd.business.benefitlevel.builder.BenefitLevelBusinessObjectBuilder;
import com.wellpoint.wpd.business.benefitlevel.locatecriteria.BenefitLevelLocateCriteria;
import com.wellpoint.wpd.business.benefitlevel.locatecriteria.BenefitLineLocateCriteria;
import com.wellpoint.wpd.business.benefitlevel.locatecriteria.BenefitLocateCriteria;
import com.wellpoint.wpd.business.framework.bo.BusinessObjectManagerFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager;
import com.wellpoint.wpd.business.framework.service.WPDBusinessValidationService;
import com.wellpoint.wpd.business.standardbenefit.adapter.StandardBenefitAdapterManager;
import com.wellpoint.wpd.business.standardbenefit.builder.StandardBenefitBusinessObjectBuilder;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.AdministrativeOptionLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.BenefitAdministrationLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.BenefitDefinitionLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.BenefitMandateLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.MandateListLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.SelectedQuestionListLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.StandardBenefitDeleteLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.StandardBenefitLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.StateLocateCriteria;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.business.util.BusinessUtil;
import com.wellpoint.wpd.common.benefitlevel.bo.BenefitLevelBO;
import com.wellpoint.wpd.common.benefitlevel.bo.BenefitLineBO;
import com.wellpoint.wpd.common.benefitlevel.request.DeleteBenefitLevelRequest;
import com.wellpoint.wpd.common.benefitlevel.request.DeleteBenefitLineRequest;
import com.wellpoint.wpd.common.benefitlevel.response.DeleteBenefitLevelResponse;
import com.wellpoint.wpd.common.benefitlevel.response.DeleteBenefitLineResponse;
import com.wellpoint.wpd.common.benefitlevel.vo.BenefitLineVO;
import com.wellpoint.wpd.common.benefitlevel.vo.BenefitWrapperVO;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.standardbenefit.bo.AdministrationOptionBO;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitAdministrationBO;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitDefinitionBO;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitMandateBO;
import com.wellpoint.wpd.common.standardbenefit.bo.SelectedQuestionListBO;
import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitBO;
import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitDatatypeBO;
import com.wellpoint.wpd.common.standardbenefit.bo.TermQualifierPVABO;
import com.wellpoint.wpd.common.standardbenefit.request.AdministrationOptionRequest;
import com.wellpoint.wpd.common.standardbenefit.request.BenefitDefinitionRequest;
import com.wellpoint.wpd.common.standardbenefit.request.CreateBenefitAdministrationRequest;
import com.wellpoint.wpd.common.standardbenefit.request.NonAdjBenefitMandateRequest;
import com.wellpoint.wpd.common.standardbenefit.request.ScheduleForTestSBRequest;
import com.wellpoint.wpd.common.standardbenefit.request.StandardBenefitCheckInRequest;
import com.wellpoint.wpd.common.standardbenefit.request.StandardBenefitCopyRequest;
import com.wellpoint.wpd.common.standardbenefit.request.StandardBenefitCreateRequest;
import com.wellpoint.wpd.common.standardbenefit.request.StandardBenefitDeleteRequest;
import com.wellpoint.wpd.common.standardbenefit.request.StandardBenefitDeleteVersionsRequest;
import com.wellpoint.wpd.common.standardbenefit.request.StandardBenefitUpdateRequest;
import com.wellpoint.wpd.common.standardbenefit.request.StandardBenefitVersionsLifeCycleRequest;
import com.wellpoint.wpd.common.standardbenefit.response.AdministrationOptionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.BenefitDefinitionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.CreateBenefitAdministrationResponse;
import com.wellpoint.wpd.common.standardbenefit.response.NonAdjBenefitMandateResponse;
import com.wellpoint.wpd.common.standardbenefit.response.ScheduleForTestSBResponse;
import com.wellpoint.wpd.common.standardbenefit.response.StandardBenefitCheckInResponse;
import com.wellpoint.wpd.common.standardbenefit.response.StandardBenefitCopyResponse;
import com.wellpoint.wpd.common.standardbenefit.response.StandardBenefitDeleteResponse;
import com.wellpoint.wpd.common.standardbenefit.response.StandardBenefitResponse;
import com.wellpoint.wpd.common.standardbenefit.response.StandardBenefitVersionsLifeCycleResponse;
import com.wellpoint.wpd.common.standardbenefit.vo.StandardBenefitVO;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author u13274
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class StandardBenefitBusinessValidationService extends
        WPDBusinessValidationService {

    List messageList = null;


    /**
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param request
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(WPDRequest request) throws ServiceException {
        throw new ServiceException("Unknown Request Type", null, null);
    }


    /**
     * 
     * This method gets the values from BenefitDefinitionRequest and sets them
     * to BenefitDefinitionBO so as to do the business validation for the
     * benefit defn details of a particular benefit
     * 
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param BenefitDefinitionRequest
     * @return
     * @throws SevereException
     */
    public WPDResponse execute(BenefitDefinitionRequest request)
            throws SevereException {
        StandardBenefitBusinessObjectBuilder builder = new StandardBenefitBusinessObjectBuilder();
        BenefitDefinitionBO benefitDefinitionBO = new BenefitDefinitionBO();
        boolean isSameDate = false;
        boolean checkBenefitDefinitionDateInBenefitAdministrationFlag = false;
        BenefitDefinitionResponse benefitDefinitionResponse = (BenefitDefinitionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.BENEFIT_DEFINITION_RESPONSE);
        List validDateList = null;
        BenefitDefinitionLocateCriteria benefitDefinitionLocateCriteria = new BenefitDefinitionLocateCriteria();
        benefitDefinitionLocateCriteria.setBenefitMasterSystemId(request
                .getBenefitDefinitionVO().getBenefitMasterSystemId());
        benefitDefinitionLocateCriteria.setEffectiveDate(request
                .getBenefitDefinitionVO().getEffectiveDate());
        benefitDefinitionLocateCriteria.setExpiryDate(request
                .getBenefitDefinitionVO().getExpiryDate());
        benefitDefinitionLocateCriteria.setBenefitDefinitionMasterKey(request
                .getBenefitDefinitionVO().getMasterKeyUsedForUpdate());
        // gets the date range for validation
        validDateList = builder
                .validateDateRange(benefitDefinitionLocateCriteria);
        if(null != validDateList){
	        Iterator validDateListIterator = validDateList.iterator();
	
	        while (validDateListIterator.hasNext()) {
	            benefitDefinitionBO = (BenefitDefinitionBO) validDateListIterator
	                    .next();
	            if (request.getBenefitDefinitionVO().getMasterKeyUsedForUpdate() != -1) {
	                if (benefitDefinitionBO.getEffectiveDate().equals(
	                        request.getBenefitDefinitionVO().getEffectiveDate())) {
	                    if (benefitDefinitionBO.getExpiryDate().equals(
	                            request.getBenefitDefinitionVO().getExpiryDate())) {
	                        isSameDate = true;
	                    }
	                }
	            }
	        }
        }
        if (!isSameDate && validDateList != null && validDateList.size() > 0) {
            checkBenefitDefinitionDateInBenefitAdministrationFlag = true;
            benefitDefinitionResponse.addMessage(new ErrorMessage(
                    "date.range.not.valid"));
        }
        //TODO call
        // builder.checkBenefitDefinitionDateInBenefitAdministration(benefitDefinitionBO);
        // return List
        // TODO if list.size > 0, error message -> cannot edit.
        /*if (!checkBenefitDefinitionDateInBenefitAdministrationFlag
                && request.getBenefitDefinitionVO().getMasterKeyUsedForUpdate() != -1) {
            validDateList = builder
                    .checkIfBenefitAdminAvailable(benefitDefinitionLocateCriteria);
            if (validDateList != null && validDateList.size() > 0) {
                validDateList = builder
                        .checkBenefitDefinitionDateInBenefitAdministration(benefitDefinitionLocateCriteria);
                if (validDateList != null && validDateList.size() == 0) {
                    benefitDefinitionResponse.addMessage(new ErrorMessage(
                            "benefit.definition.not.valid"));
                }
            }
        }*/
        return benefitDefinitionResponse;
    }


    /**
     * 
     * This method gets the values from StandardBenefitCreateRequest and sets
     * them to StandardBenefitBO so as to do the business validation for the
     * benefit details of a particular benefit
     * 
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param StandardBenefitCreateRequest
     * @return
     * @throws ServiceException
     */

    public WPDResponse execute(StandardBenefitCreateRequest request)
            throws ServiceException {
        Logger
                .logInfo("StandardBenefitBusinessValidationService - Entering execute(): Standard Benefit Create");
        StandardBenefitResponse standardBenefitResponse = null;
        StandardBenefitBO standardBenefitBO = getStandardBenefitObject(request);
        standardBenefitResponse = (StandardBenefitResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.STANDARD_BENEFIT_RESPONSE);
        List messageList = new ArrayList(1);
        try {
            if (isStandardBenefitDuplicate(standardBenefitBO)) {
                messageList.add(new ErrorMessage("standard.benefit.duplicate"));
                standardBenefitResponse.setSuccess(false);
            } else {
                standardBenefitResponse.setSuccess(true);
            }
            if(standardBenefitResponse.isSuccess()) {
                if(!isUniqueBenefitName(standardBenefitBO)){
	                messageList.add(new ErrorMessage(
	                	"benefitcomponent.with.same.benefit.name"));
	                standardBenefitResponse.setSuccess(false);
	                standardBenefitResponse.setMessages(messageList);
	            } else {
	            	standardBenefitResponse.setSuccess(true);
	            }
            }
            
        } catch (WPDException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing StandardBenefitCreateRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }

        standardBenefitResponse.setStandardBenefitBO(standardBenefitBO);
        standardBenefitResponse.setMessages(messageList);
        Logger
                .logInfo("StandardBenefitBusinessValidationService - Returning execute(): Standard Benefit Create");
        return standardBenefitResponse;
    }
    /**
     * Method for checking if Benefit has name same as any benefit component.
     * 
     * @param StandardBenefitBO
     *            Object
     * @return boolean
     * @throws WPDException
     */
    private boolean isUniqueBenefitName(
    		StandardBenefitBO standardBenefitBO) throws SevereException {
    	    	StandardBenefitBusinessObjectBuilder benefitBusinessObjectBuilder = new StandardBenefitBusinessObjectBuilder();
    	List benefitComponentNameList = benefitBusinessObjectBuilder
                .checkUniqueBenefitName(standardBenefitBO);
        if ((null != benefitComponentNameList )&&( benefitComponentNameList.size() > 0))
            return false;
        else
            return true;
    }

    public WPDResponse execute(StandardBenefitDeleteRequest request)
            throws ServiceException {
        Logger
                .logInfo("StandardBenefitBusinessValidationService - Entering execute(): Standard Benefit Delete");
        
        StandardBenefitBusinessObjectBuilder builder = new StandardBenefitBusinessObjectBuilder();
        StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
        StandardBenefitDeleteResponse standardBenefitDeleteResponse = (StandardBenefitDeleteResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.STANDARD_BENEFIT_DELETE_RESPONSE);
        try {
            StandardBenefitDeleteLocateCriteria criteria = new StandardBenefitDeleteLocateCriteria();
            criteria.setStandardBenefitKey(request.getStandardBenefitVO()
                    .getStandardBenefitKey());
            List associationList = builder.isStandardBenefitPresent(criteria);

            if(null != associationList){
	            if (!associationList.isEmpty()) {
	                //standardBenefitDeleteResponse.addMessage(new
	                // ErrorMessage("standard.benefit.not.deleted"));
	                standardBenefitDeleteResponse.setSuccess(false);
	            } else {
	                //standardBenefitDeleteResponse.addMessage(new
	                // InformationalMessage("standard.benefit.deleted"));
	                standardBenefitDeleteResponse.setSuccess(true);
	            }
            }
        } catch (WPDException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing StandardBenefitCreateRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        Logger
                .logInfo("StandardBenefitBusinessValidationService - Returning execute(): Standard Benefit Delete");
        return standardBenefitDeleteResponse;
    }


    public WPDResponse execute(StandardBenefitDeleteVersionsRequest request)
            throws ServiceException {
        Logger
                .logInfo("StandardBenefitBusinessValidationService - Entering execute(): Standard Benefit Delete Versions");
        
        StandardBenefitBusinessObjectBuilder builder = new StandardBenefitBusinessObjectBuilder();
        StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
        StandardBenefitDeleteResponse standardBenefitDeleteResponse = (StandardBenefitDeleteResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.STANDARD_BENEFIT_DELETE_RESPONSE);
        StandardBenefitDeleteLocateCriteria criteria = new StandardBenefitDeleteLocateCriteria();
        try {
            criteria.setStandardBenefitKey(request.getStandardBenefitKey());
            List associationList = builder.isStandardBenefitPresent(criteria);

            if(null!= associationList){

	            if (!associationList.isEmpty()) {
	                //standardBenefitDeleteResponse.addMessage(new
	                // ErrorMessage("standard.benefit.not.deleted"));
	                standardBenefitDeleteResponse.setSuccess(false);
	            } else {
	                //standardBenefitDeleteResponse.addMessage(new
	                // InformationalMessage("standard.benefit.deleted"));
	                standardBenefitDeleteResponse.setSuccess(true);
	            }
            }
        } catch (WPDException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing StandardBenefitCreateRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        Logger
                .logInfo("StandardBenefitBusinessValidationService - Returning execute(): Standard Benefit Delete Versions");
        return standardBenefitDeleteResponse;
    }


    public WPDResponse execute(StandardBenefitUpdateRequest request)
            throws ServiceException {
        Logger
                .logInfo("StandardBenefitBusinessValidationService - Entering execute(): Standard Benefit Update");
        StandardBenefitResponse standardBenefitResponse = null;
        StandardBenefitBO standardBenefitBO = getStandardBenefitObject(request);
        standardBenefitResponse = (StandardBenefitResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.STANDARD_BENEFIT_RESPONSE);
        messageList = new ArrayList(1);

        try {
            if (isStandardBenefitDuplicate(standardBenefitBO)) {
                    messageList.add(new ErrorMessage("standard.benefit.duplicate"));
                    standardBenefitResponse.setSuccess(false);
            }else {
                int key = standardBenefitBO.getStandardBenefitKey();
                StandardBenefitLocateCriteria standardBenefitLocateCriteria = new StandardBenefitLocateCriteria();
                standardBenefitLocateCriteria.setStandardBenefitKey(key);
                checkTermUpdate(standardBenefitLocateCriteria,
                        standardBenefitBO);
                checkQualifierUpdate(standardBenefitLocateCriteria,
                        standardBenefitBO);
                checkPVAUpdate(standardBenefitLocateCriteria, standardBenefitBO);
                checkDataTypeUpdate(standardBenefitLocateCriteria,
                        standardBenefitBO);
                if (this.messageList.size() == 0) {
                    standardBenefitResponse.setSuccess(true);
                } else {
                    standardBenefitResponse.setMessages(messageList);
                    standardBenefitResponse.setSuccess(false);
                }
                
            }
            if(standardBenefitResponse.isSuccess()) {
                if(!isUniqueBenefitName(standardBenefitBO)){
	                messageList.add(new ErrorMessage(
	                	"benefitcomponent.with.same.benefit.name"));
	                standardBenefitResponse.setSuccess(false);
	                standardBenefitResponse.setMessages(messageList);
	            } else {
	            	standardBenefitResponse.setSuccess(true);
	            }
            }
        } catch (WPDException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing StandardBenefitCreateRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }

        standardBenefitResponse.setStandardBenefitBO(standardBenefitBO);
        standardBenefitResponse.setMessages(messageList);
        Logger
                .logInfo("StandardBenefitBusinessValidationService - Returning execute(): Standard Benefit Update");
        return standardBenefitResponse;

    }


    public WPDResponse execute(ScheduleForTestSBRequest scheduleForTestSBRequest)
            throws ServiceException {
        Logger
                .logInfo("StandardBenefitBusinessValidationService - Entering execute(): Scheduled for Test Standard Benefit");
        ScheduleForTestSBResponse response = null;
        StandardBenefitBO standardBenefitBO = new StandardBenefitBO();

        //Create Response object
        response = (ScheduleForTestSBResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.SCHEDULE_FOR_TEST_STD_BEN_RESPONSE);

        if (isStandardBenefitValidated(scheduleForTestSBRequest)) {
            response.setSuccess(true);
        }
        response.setStandardBenefitBO(standardBenefitBO);
        response.setMessages(this.messageList);
        Logger
                .logInfo("StandardBenefitBusinessValidationService - Returning execute(): Scheduled for Test Standard Benefit");
        return response;
    }


    public WPDResponse execute(StandardBenefitVersionsLifeCycleRequest request)
            throws ServiceException {
        Logger
                .logInfo("StandardBenefitBusinessValidationService - Entering execute(): Standard Benefit Versions Life Cycle");
        StandardBenefitVersionsLifeCycleResponse response = null;
        response = (StandardBenefitVersionsLifeCycleResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.STANDARD_BENEFIT_VERSIONS_LIFECYCLE_RESPONSE);

        if (isStandardBenefitValidated(request)) {
            response.setSuccess(true);
        }
        response.setMessages(this.messageList);
        Logger
                .logInfo("StandardBenefitBusinessValidationService - Returning execute(): Standard Benefit Versions Life Cycle");
        return response;
    }


    private boolean isStandardBenefitValidated(
            StandardBenefitVersionsLifeCycleRequest request)
            throws ServiceException {
        messageList = new ArrayList(1);
        BenefitDefinitionLocateCriteria benefitDefinitionLocateCriteria = new BenefitDefinitionLocateCriteria();
        benefitDefinitionLocateCriteria.setBenefitMasterSystemId(request
                .getStandardBenefitKey());
        StandardBenefitBusinessObjectBuilder builder = new StandardBenefitBusinessObjectBuilder();
        try {
            LocateResults locateResults = builder.locate(
                    benefitDefinitionLocateCriteria, request.getUser());
            List resultList = locateResults.getLocateResults();
            int size = resultList.size();
            int count = 0;
            BenefitLevelBusinessObjectBuilder benefitLevelBusinessObjectBuilder = new BenefitLevelBusinessObjectBuilder();
            ;
            BenefitLocateCriteria benefitLocateCriteria = null;
            BenefitLevelBO benefitLevelBO = null;
            BenefitLineBO benefitLineBO = null;
            int countDatatype = 0;

            if (size > 0) {
                boolean flag = true;
                for (int i = 0; i < size; i++) {
                    BenefitDefinitionBO benefitDefinitionBOImpl = (BenefitDefinitionBO) resultList
                            .get(i);
                    benefitLocateCriteria = new BenefitLocateCriteria();
                    benefitLocateCriteria
                            .setBenefitDefinitionId(benefitDefinitionBOImpl
                                    .getBenefitDefinitionMasterKey());
                    LocateResults locateResult = benefitLevelBusinessObjectBuilder
                            .searchBenefitLevels(benefitLocateCriteria, request
                                    .getUser(), true);
                    if (locateResult.getLocateResults().size() > 0) {
                        count++;
                        List list = locateResult.getLocateResults();
                        for (int j = 0; j < list.size(); j++) {
                            benefitLevelBO = (BenefitLevelBO) list.get(j);
                            List benefitLine = benefitLevelBO.getBenefitLines();
                            for (int k = 0; k < benefitLine.size(); k++) {
                                benefitLineBO = (BenefitLineBO) benefitLine
                                        .get(k);
                                if (benefitLineBO.getDataTypeName() == null) {
                                    countDatatype++;
                                }
                            }
                            if (countDatatype > 0) {
                                break;
                            }
                        }
                    }
                }
                if (size != count || countDatatype > 0) {
                    this.messageList
                            .add(new ErrorMessage(
                                    "benefit.definition.should.have.atleast.one.benefit.level"));
                    return false;
                }
            } else {

                this.messageList.add(new ErrorMessage(
                        "benefit.should.have.atleast.one.benefit.definition"));
                return false;
            }
            return true;
        } catch (WPDException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing StandardBenefitVersionsLifeCycleRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
    }


    private boolean isStandardBenefitValidated(ScheduleForTestSBRequest request)
            throws ServiceException {
        messageList = new ArrayList(1);
        BenefitDefinitionLocateCriteria benefitDefinitionLocateCriteria = new BenefitDefinitionLocateCriteria();
        benefitDefinitionLocateCriteria.setBenefitMasterSystemId(request
                .getStandardBenefitKey());
        StandardBenefitBusinessObjectBuilder builder = new StandardBenefitBusinessObjectBuilder();
        try {
            LocateResults locateResults = builder.locate(
                    benefitDefinitionLocateCriteria, request.getUser());
            List resultList = locateResults.getLocateResults();
            int size = resultList.size();
            int count = 0;
            BenefitLevelBusinessObjectBuilder benefitLevelBusinessObjectBuilder = new BenefitLevelBusinessObjectBuilder();
            ;
            BenefitLocateCriteria benefitLocateCriteria = null;
            BenefitLevelBO benefitLevelBO = null;
            BenefitLineBO benefitLineBO = null;
            int countDatatype = 0;

            if (size > 0) {
                boolean flag = true;
                for (int i = 0; i < size; i++) {
                    BenefitDefinitionBO benefitDefinitionBOImpl = (BenefitDefinitionBO) resultList
                            .get(i);
                    benefitLocateCriteria = new BenefitLocateCriteria();
                    benefitLocateCriteria
                            .setBenefitDefinitionId(benefitDefinitionBOImpl
                                    .getBenefitDefinitionMasterKey());
                    LocateResults locateResult = benefitLevelBusinessObjectBuilder
                            .searchBenefitLevels(benefitLocateCriteria, request
                                    .getUser(), true);
                    if (locateResult.getLocateResults().size() > 0) {
                        count++;
                        List list = locateResult.getLocateResults();
                        for (int j = 0; j < list.size(); j++) {
                            benefitLevelBO = (BenefitLevelBO) list.get(j);
                            List benefitLine = benefitLevelBO.getBenefitLines();
                            for (int k = 0; k < benefitLine.size(); k++) {
                                benefitLineBO = (BenefitLineBO) benefitLine
                                        .get(k);
                                if (benefitLineBO.getDataTypeName() == null) {
                                    countDatatype++;
                                }
                            }
                            if (countDatatype > 0) {
                                break;
                            }
                        }
                    }
                }
                if (size != count || countDatatype > 0) {
                    this.messageList
                            .add(new ErrorMessage(
                                    "benefit.definition.should.have.atleast.one.benefit.level"));
                    return false;
                }
            } else {

                this.messageList.add(new ErrorMessage(
                        "benefit.should.have.atleast.one.benefit.definition"));
                return false;
            }
            return true;
        } catch (WPDException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing ScheduleForTestSBRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
    }


    public void checkTermUpdate(
            StandardBenefitLocateCriteria standardBenefitLocateCriteria,
            StandardBenefitBO standardBenefitBO) throws SevereException {
        List term = standardBenefitBO.getTermList();
        int count = 0;
        StandardBenefitBusinessObjectBuilder builder = new StandardBenefitBusinessObjectBuilder();
        // **Change**
        /*
         * SearchResults searchResults =
         * builder.checkTermUpdate(standardBenefitLocateCriteria);
         * if(searchResults != null){ List locateresult =
         * (List)searchResults.getSearchResults();
         */
        List locateresult = builder
                .checkTermUpdate(standardBenefitLocateCriteria);
        //	**End**
        
        if (locateresult != null) {
        	Iterator iter = locateresult.iterator();
            while (iter.hasNext()) {
                TermQualifierPVABO termQualifierPVABO = (TermQualifierPVABO) iter
                        .next();
                for (int j = 0; j < term.size(); j++) {
                    if (termQualifierPVABO.getTermCode().equals(term.get(j))) {
                        count++;
                    }

                }
                if (count != 1) {
                    ErrorMessage errorMessage = new ErrorMessage(
                            "cannot.be.removed");
                    errorMessage
                            .setParameters(new String[] { termQualifierPVABO
                                    .getCodeDescText() });
                    this.messageList.add(errorMessage);
                }
                count = 0;
            }
        }
        // **Change**
        //}
        // **End**
    }


    public void checkQualifierUpdate(
            StandardBenefitLocateCriteria standardBenefitLocateCriteria,
            StandardBenefitBO standardBenefitBO) throws SevereException {

        List qualifier = standardBenefitBO.getQualifierList();
        int count = 0;
        StandardBenefitBusinessObjectBuilder builder = new StandardBenefitBusinessObjectBuilder();
        //Change: Qualifier Aggregate
        List locateresult = builder
                  .checkQualifierUpdate(standardBenefitLocateCriteria);
        //end
            Iterator iter = locateresult.iterator();
            if (locateresult != null) {
                while (iter.hasNext()) {
                    TermQualifierPVABO termQualifierPVABO = (TermQualifierPVABO) iter
                            .next();
                  
                    for (int j = 0; j < qualifier.size(); j++) {
	                        if (termQualifierPVABO.getQualifierCode().equals(
	                                qualifier.get(j))) {
	                        	count++;
	                        	}
                    	
                    }
                    if (count != 1) {
                        ErrorMessage errorMessage = new ErrorMessage(
                                "cannot.be.removed");
                        errorMessage
                                .setParameters(new String[] { termQualifierPVABO
                                        .getCodeDescText() });
                        this.messageList.add(errorMessage);
                    }
                    count = 0;
                }
            }
     }


    public void checkPVAUpdate(
            StandardBenefitLocateCriteria standardBenefitLocateCriteria,
            StandardBenefitBO standardBenefitBO) throws SevereException {

        List pva = standardBenefitBO.getPVAList();
        int count = 0;
        StandardBenefitBusinessObjectBuilder builder = new StandardBenefitBusinessObjectBuilder();
        SearchResults searchResults = builder
                .checkPVAUpdate(standardBenefitLocateCriteria);
        if (searchResults != null) {
            List locateresult = (List) searchResults.getSearchResults();
            Iterator iter = locateresult.iterator();
            if (locateresult != null) {
                while (iter.hasNext()) {
                    TermQualifierPVABO termQualifierPVABO = (TermQualifierPVABO) iter
                            .next();
                    for (int j = 0; j < pva.size(); j++) {
                        if (termQualifierPVABO.getPvaCode().equals(pva.get(j))) {
                            count++;
                        }

                    }
                    if (count != 1) {
                        ErrorMessage errorMessage = new ErrorMessage(
                                "cannot.be.removed");
                        errorMessage
                                .setParameters(new String[] { termQualifierPVABO
                                        .getCodeDescText() });
                        this.messageList.add(errorMessage);
                    }
                    count = 0;
                }
            }
        }
    }


    public void checkDataTypeUpdate(
            StandardBenefitLocateCriteria standardBenefitLocateCriteria,
            StandardBenefitBO standardBenefitBO) throws SevereException {

        List dataType = standardBenefitBO.getDataTypeList();
        int count = 0;
        StandardBenefitBusinessObjectBuilder builder = new StandardBenefitBusinessObjectBuilder();
        SearchResults searchResults = builder
                .checkDataTypeUpdate(standardBenefitLocateCriteria);
        if (searchResults != null) {
            List locateresult = (List) searchResults.getSearchResults();
            Iterator iter = locateresult.iterator();
            if (locateresult != null) {
                while (iter.hasNext()) {
                    StandardBenefitDatatypeBO standardBenefitDatatypeBO = (StandardBenefitDatatypeBO) iter
                            .next();
                    for (int j = 0; j < dataType.size(); j++) {
                        if (standardBenefitDatatypeBO.getSelectedItemCode()
                                .equals(dataType.get(j))) {
                            count++;
                        }

                    }
                    if (count != 1) {
                        ErrorMessage errorMessage = new ErrorMessage(
                                "cannot.be.removed");
                        errorMessage
                                .setParameters(new String[] { standardBenefitDatatypeBO
                                        .getDataTypeName() });
                        this.messageList.add(errorMessage);
                    }
                    count = 0;
                }
            }
        }
    }


    // enhancement -- to check if mandate info is available
    public boolean checkForMandateInfo(
            StandardBenefitCheckInRequest benefitCheckInRequest)
            throws ServiceException {
        NonAdjBenefitMandateAdapterManager adapterManager = new NonAdjBenefitMandateAdapterManager();
        BenefitMandateBO benefitMandateBO = new BenefitMandateBO();
       try{
        benefitMandateBO.setBenefitSystemId(benefitCheckInRequest
                .getStandardBenefitVO().getStandardBenefitKey());
        benefitMandateBO = (BenefitMandateBO) adapterManager
                .retrieve(benefitMandateBO);
        if (benefitMandateBO.getBenefitMandateId() == 0
                || benefitMandateBO.getBenefitMandateId() == -1) {
            return false;
        } else {
            StateLocateCriteria criteria = new StateLocateCriteria();
            criteria
                    .setBenefitMandateId(benefitMandateBO.getBenefitMandateId());
            LocateResults locateResults = adapterManager
                    .locateStateObject(criteria);
            List list = locateResults.getLocateResults();
            Iterator iterator = list.iterator();
            if (iterator.hasNext())
                benefitCheckInRequest.setAction(1);
            else
                benefitCheckInRequest.setAction(2);
       
            return true;
        }
            }catch (Exception ex) {
    			List errorParams = new ArrayList(2);
    			String obj = ex.getClass().getName();
    			errorParams.add(obj);
    			errorParams.add(obj.getClass().getName());
    			throw new ServiceException(
    					"Exception occured in checkForMandateInfo StandardBenefitCheckInRequest method in StandardBusinessValidationService",
    					null, ex);
    		}

    }
    /*
     * Validate rule 
     * 
     * @BenefitComponentBO
     * 
     * return boolean
     */
    private boolean isValidrule(StandardBenefitBO standardBenefitBO) throws SevereException {
    	StandardBenefitAdapterManager standardBenefitAdapterManager 
									= new StandardBenefitAdapterManager();
    	int entityId=standardBenefitBO.getStandardBenefitKey();
    	String entityType=BusinessConstants.BENEFIT_TYPE;
    	String ruleId=(String)standardBenefitBO.getRuleTypeList().get(0);
    	List ruleList = standardBenefitAdapterManager.
							validateBenefitRule(entityId,entityType,ruleId);
    	if(ruleList.size() > 0 )
    		return true;
    	return false;
		
    }

    public WPDResponse execute(StandardBenefitCopyRequest request)
            throws ServiceException {
        Logger
                .logInfo("StandardBenefitBusinessValidationService - Entering execute(): Standard Benefit Copy");
        StandardBenefitCopyResponse standardBenefitCopyResponse = null;
        StandardBenefitBO standardBenefitBO = getStandardBenefitCopyObject(request);
        standardBenefitCopyResponse = (StandardBenefitCopyResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.STANDARD_BENEFIT_COPY_RESPONSE);
        messageList = new ArrayList(1);
        try {
            if (isStandardBenefitDuplicate(standardBenefitBO)) {
                messageList.add(new ErrorMessage("standard.benefit.duplicate"));
                standardBenefitCopyResponse.setSuccess(false);
                standardBenefitCopyResponse.setStandardBenefitBO(standardBenefitBO);
                standardBenefitCopyResponse.setMessages(messageList);
            } else {
                int key = standardBenefitBO.getStandardBenefitKey();
	            StandardBenefitLocateCriteria standardBenefitLocateCriteria = new StandardBenefitLocateCriteria();
	            standardBenefitLocateCriteria.setStandardBenefitKey(key);
	            checkTermUpdate(standardBenefitLocateCriteria,
	                    standardBenefitBO);
	            checkQualifierUpdate(standardBenefitLocateCriteria,
	                    standardBenefitBO);
	            checkPVAUpdate(standardBenefitLocateCriteria, standardBenefitBO);
	            checkDataTypeUpdate(standardBenefitLocateCriteria,
	                    standardBenefitBO);
	            if (this.messageList.size() == 0) {
	                standardBenefitCopyResponse.setSuccess(true);
	            } else {
	                standardBenefitCopyResponse.setMessages(this.messageList);
	                standardBenefitCopyResponse.setSuccess(false);
	            }
	                //standardBenefitCopyResponse.setSuccess(true);
            }
            if(standardBenefitCopyResponse.isSuccess()) {
                if(!isUniqueBenefitName(standardBenefitBO)){
	                messageList.add(new ErrorMessage(
	                	"benefitcomponent.with.same.benefit.name"));
	                standardBenefitCopyResponse.setSuccess(false);
	                standardBenefitCopyResponse.setMessages(messageList);
	            } else {
	            	standardBenefitCopyResponse.setSuccess(true);
	            }
            }
        } catch (WPDException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing StandardBenefitCopyRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
//        standardBenefitCopyResponse.setStandardBenefitBO(standardBenefitBO);
//        standardBenefitCopyResponse.setMessages(messageList);
        Logger
                .logInfo("StandardBenefitBusinessValidationService - Returning execute(): Standard Benefit Copy");
        return standardBenefitCopyResponse;
    }


    /**
     * @param request
     * @return
     */
    private StandardBenefitBO getStandardBenefitCopyObject(
            StandardBenefitCopyRequest request) {
        StandardBenefitVO standardBenefitVO = (StandardBenefitVO) request
                .getStandardBenefitVO();
        StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
        standardBenefitBO.setStandardBenefitKey(standardBenefitVO
                .getStandardBenefitKey());
        standardBenefitBO.setBenefitIdentifier(standardBenefitVO
                .getBenefitIdentifier());
        standardBenefitBO.setDescription(standardBenefitVO.getDescription());
        //standardBenefitBO.setParentSystemId(standardBenefitVO.getStandardBenefitKey());
        standardBenefitBO.setParentSystemId(0);

        List lineOfBusiness = standardBenefitVO.getLobList();
        List businessEntity = standardBenefitVO.getBusinessEntityList();
        List businessGroup = standardBenefitVO.getBusinessGroupList();
        List marketBusinessUnit = standardBenefitVO.getMarketBusinessUnitList();
        standardBenefitBO.setBusinessDomains(BusinessUtil.convertToDomains(
                lineOfBusiness, businessEntity, businessGroup, marketBusinessUnit));
        
        //change **12/31
        standardBenefitBO.setLobList(lineOfBusiness);
        standardBenefitBO.setBeList(businessEntity);
        standardBenefitBO.setBgList(businessGroup);
        /*START CARS*/
        standardBenefitBO.setMarketBusinessUnitList(marketBusinessUnit);
        /*END CARS*/
        //**end

        standardBenefitBO.setTermList(standardBenefitVO.getTermList());
        standardBenefitBO
                .setQualifierList(standardBenefitVO.getQualifierList());
        standardBenefitBO.setDataTypeList(standardBenefitVO.getDataTypeList());
        standardBenefitBO.setPVAList(standardBenefitVO.getPVAList());
        standardBenefitBO.setVersion(standardBenefitVO.getVersion());
        return standardBenefitBO;
    }


    /**
     * 
     * @param request
     * @return
     * @throws SevereException
     */
    public WPDResponse execute(NonAdjBenefitMandateRequest request)
            throws SevereException {
        StandardBenefitBusinessObjectBuilder builder = new StandardBenefitBusinessObjectBuilder();
        BenefitMandateBO benefitMandateBO = new BenefitMandateBO();
        boolean isSameDate = false;
        boolean mandateChangedFlag = false;
        NonAdjBenefitMandateResponse nonAdjBenefitMandateResponse = (NonAdjBenefitMandateResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.NON_ADJ_BENEFIT_MANDATE_RESPONSE);
        LocateResults locateResults = new LocateResults();
        MandateListLocateCriteria criteria = new MandateListLocateCriteria();
        criteria.setEffDate(WPDStringUtil.convertDateToString(request
                .getNonAdjBenefitMandateVO().getEffectiveDate()));
        criteria.setExpDate(WPDStringUtil.convertDateToString(request
                .getNonAdjBenefitMandateVO().getExpiryDate()));
        criteria.setMandateId(new Integer(request.getNonAdjBenefitMandateVO()
                .getMandate()).intValue());

        List validDateList = null;
        BenefitMandateLocateCriteria benefitMandateLocateCriteria = new BenefitMandateLocateCriteria();
        benefitMandateLocateCriteria.setBenefitMasterSystemId(request
                .getNonAdjBenefitMandateVO().getBenefitSystemId());
        benefitMandateLocateCriteria.setEffectiveDate(request
                .getNonAdjBenefitMandateVO().getEffectiveDate());
        benefitMandateLocateCriteria.setExpiryDate(request
                .getNonAdjBenefitMandateVO().getExpiryDate());
        benefitMandateLocateCriteria.setBenefitMandateId(request
                .getNonAdjBenefitMandateVO().getBenefitMandateId());
        validDateList = builder
                .validateDateRangeForNonAdjMandate(benefitMandateLocateCriteria);
        if(null!= validDateList){
	        Iterator validDateListIterator = validDateList.iterator();
	
	        while (validDateListIterator.hasNext()) {
	            benefitMandateBO = (BenefitMandateBO) validDateListIterator.next();
	            if (request.getNonAdjBenefitMandateVO().getMasterKeyUsedForUpdate() != -1) {
	                if (benefitMandateBO.getEffectiveDate().equals(
	                        request.getNonAdjBenefitMandateVO().getEffectiveDate())) {
	                    if (benefitMandateBO.getExpiryDate()
	                            .equals(
	                                    request.getNonAdjBenefitMandateVO()
	                                            .getExpiryDate())) {
	                        isSameDate = true;
	                    }
	                }
	            }
	        }
        }
        if (!isSameDate && validDateList != null && validDateList.size() > 0) {
            mandateChangedFlag = true;
            nonAdjBenefitMandateResponse.addMessage(new ErrorMessage(
                    "date.range.not.valid"));
        }
        if (!mandateChangedFlag) {
            locateResults = builder.locate(criteria, request.getUser());
            if (locateResults.getLocateResults().size() == 0) {
                nonAdjBenefitMandateResponse.addMessage(new ErrorMessage(
                        "mandate.not.valid"));
            }
        }
        return nonAdjBenefitMandateResponse;
    }


    /**
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param request
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(AdministrationOptionRequest request)
            throws ServiceException {
        StandardBenefitBusinessObjectBuilder builder = new StandardBenefitBusinessObjectBuilder();
        AdministrationOptionBO optionBO = new AdministrationOptionBO();
        AdministrationOptionResponse administrationOptionResponse = (AdministrationOptionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.CREATE_BENEFIT_ADMINOPTION_RESPONSE);
        List adminOptionList = null;
        AdministrativeOptionLocateCriteria criteria = new AdministrativeOptionLocateCriteria();
        criteria.setAdminOptionId(request.getAdministrationOptionVO()
                .getAdminOptionSystemId());
        criteria.setBenefitAdministrationSystemId(request
                .getAdministrationOptionVO().getBenefitAdminSystemId());
        criteria.setAdminLevelAssociationSysemId(request
                .getAdministrationOptionVO().getAdminLevelOptionAssnSystemId());
        adminOptionList = builder.isAdminOptionPresent(criteria);
        if (null != adminOptionList && adminOptionList.size() > 0) {
            administrationOptionResponse.addMessage(new ErrorMessage(
                    "admin.option.exists"));
        }
        return administrationOptionResponse;
    }


    /**
     * checkin validations
     * 
     * @param request
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(StandardBenefitCheckInRequest request)
            throws ServiceException {
        Logger
                .logInfo("StandardBenefitBusinessValidationService - Entering execute(): Standard Benefit CheckIn");
        StandardBenefitVO standardBenefitVO = (StandardBenefitVO) request
                .getStandardBenefitVO();
        StandardBenefitBO standardBenefitBO = getStandardBenefitObject(standardBenefitVO);
        BusinessObjectManager bom = getBOM();
        StandardBenefitCheckInResponse standardBenefitCheckInResponse = (StandardBenefitCheckInResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.STANDARD_BENEFIT_CHECKIN_RESPONSE);

        /*
         * newly added validations for update
         */

        messageList = new ArrayList(1);
        //boolean valid = true;
        try {
        	//if(!this.isValidBenefitValue(standardBenefitBO.getStandardBenefitKey(),messageList)){
                //standardBenefitCheckInResponse.setValidationSuccess(false); 
                //valid = false;
        	//}else 
            /*
			 * modified on 16thNov calls the duplicate check function for checkin
			 */
        	//if(standardBenefitBO.getVersion() == 0){
	           	 if (isStandardBenefitDuplicate(standardBenefitBO)) {
	                messageList.add(new ErrorMessage("standard.benefit.duplicate"));
	                standardBenefitCheckInResponse.setValidationSuccess(false);
	        	 }
// CheckInValidation 'CR' Start	           	 
	           	 //else {
// CheckInValidation 'CR' End	           	 
	                int key = standardBenefitBO.getStandardBenefitKey();
	                StandardBenefitLocateCriteria standardBenefitLocateCriteria = new StandardBenefitLocateCriteria();
	                standardBenefitLocateCriteria.setStandardBenefitKey(key);
	                checkTermUpdate(standardBenefitLocateCriteria,
	                        standardBenefitBO);
	                checkQualifierUpdate(standardBenefitLocateCriteria,
	                        standardBenefitBO);
	                checkPVAUpdate(standardBenefitLocateCriteria, standardBenefitBO);
	                checkDataTypeUpdate(standardBenefitLocateCriteria,
	                        standardBenefitBO);
	                if (this.messageList.size() == 0) {
	                    standardBenefitCheckInResponse.setValidationSuccess(true);
	                } else {
	                    standardBenefitCheckInResponse.setValidationSuccess(false);
	                }
// CheckInValidation 'CR' Start	                
	            //}
        	//if(standardBenefitCheckInResponse.isValidationSuccess()) {
// CheckInValidation 'CR' End         		
	                if(!isUniqueBenefitName(standardBenefitBO)){
		                messageList.add(new ErrorMessage(
		                	"benefitcomponent.with.same.benefit.name"));
		                standardBenefitCheckInResponse.setValidationSuccess(false);
		                standardBenefitCheckInResponse.setMessages(messageList);
		            } else {
// CheckInValidation 'CR' Start	
		            	if(standardBenefitCheckInResponse.isValidationSuccess())
//	CheckInValidation 'CR' End 		            	
		            		standardBenefitCheckInResponse.setValidationSuccess(true);
		            }
// CheckInValidation 'CR' Start		                
	            //}
// CheckInValidation 'CR' End       
        	/*}
        	else{
        		standardBenefitCheckInResponse.setValidationSuccess(true);
        	}*/

           	if(isValidrule(standardBenefitBO))
            {
            	this.messageList.add(new ErrorMessage(BusinessConstants.MSG_PRDCT_RULE_VALIDATE));
            	standardBenefitCheckInResponse.setValidationSuccess(false);
            }
            standardBenefitCheckInResponse
                    .setStandardBenefitBO(standardBenefitBO);
            /*
             * validation ends
             */
           // if(valid){
// CheckInValidation 'CR' Start	            
            //if(standardBenefitCheckInResponse.isValidationSuccess()){            	
	            //if (isStandardBenefitValidated(request) && !isValidrule(standardBenefitBO)) {
            if (isStandardBenefitValidated(request) 
					&& standardBenefitCheckInResponse.isValidationSuccess()) {
// CheckInValidation 'CR' End	            	
	                standardBenefitCheckInResponse.setCheckinSuccess(true);
	            }
// CheckInValidation 'CR' Start		            
            //}
// CheckInValidation 'CR' End        
           // }
            standardBenefitCheckInResponse.setMessages(this.messageList);
        } catch (SevereException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing StandardBenefitCheckInRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        Logger
                .logInfo("StandardBenefitBusinessValidationService - Returning execute(): Standard Benefit CheckIn");
        return standardBenefitCheckInResponse;
    }
    //  new check for duplicate function for checkin
    // modified on 16th Nov
    /**
     * @param standardBenefitBO
     * @return
     * @throws SevereException
     */
    private boolean isStandardBenefitDuplicateForCheckin(
            StandardBenefitBO standardBenefitBO) throws SevereException {
        StandardBenefitBusinessObjectBuilder builder = new StandardBenefitBusinessObjectBuilder();
        return builder.isDuplicate(standardBenefitBO
                .getBenefitIdentifier(),
                standardBenefitBO.getBusinessDomains(), standardBenefitBO
                        .getParentSystemId());
        
    }
    // modification ends
    
    


    private StandardBenefitBO getStandardBenefitObject(
            StandardBenefitVO stdBenefitVO) {
        StandardBenefitBO stdBenefitBO = new StandardBenefitBO();
        stdBenefitBO
                .setStandardBenefitKey(stdBenefitVO.getStandardBenefitKey());
        stdBenefitBO.setBenefitIdentifier(stdBenefitVO.getBenefitIdentifier());
        stdBenefitBO.setParentSystemId(stdBenefitVO
                .getStandardBenefitParentKey());

        List lobList = stdBenefitVO.getLobList();
        List businessEntityList = stdBenefitVO.getBusinessEntityList();
        List businessGroupList = stdBenefitVO.getBusinessGroupList();
        List marketBusinessUnitList = stdBenefitVO.getMarketBusinessUnitList();
        stdBenefitBO.setLobList(lobList);
        stdBenefitBO.setBeList(businessEntityList);
        stdBenefitBO.setBgList(businessGroupList);
        stdBenefitBO.setMarketBusinessUnitList(marketBusinessUnitList);

        stdBenefitBO.setBusinessDomains(BusinessUtil.convertToDomains(lobList,
                businessEntityList, businessGroupList, marketBusinessUnitList));

        stdBenefitBO.setDataTypeList(stdBenefitVO.getDataTypeList());
        stdBenefitBO.setDescription(stdBenefitVO.getDescription());
        stdBenefitBO.setTermList(stdBenefitVO.getTermList());
        stdBenefitBO.setQualifierList(stdBenefitVO.getQualifierList());
        stdBenefitBO.setPVAList(stdBenefitVO.getPVAList());
        stdBenefitBO.setRuleTypeList(stdBenefitVO.getRuleTypeList());
        //stdBenefitBO.setState(stdBenefitVO.getState());
        stdBenefitBO.setStatus(stdBenefitVO.getStatus());
        stdBenefitBO.setVersion(stdBenefitVO.getVersion());
        stdBenefitBO.setBenefitCategory(stdBenefitVO.getBenefitCategory());
        return stdBenefitBO;
    }

    private boolean isValidBenefitValue(int benefitDefinitionId, List messagesList) throws SevereException{
        boolean validationFlag = true;
        StandardBenefitBusinessObjectBuilder builder = new StandardBenefitBusinessObjectBuilder();
    	List benefitValueList = builder.getBenefitValue(benefitDefinitionId);
    	BenefitLevelBO benefitLevelBO;  
    	if(null != benefitValueList && ! benefitValueList.isEmpty()){
    		for(Iterator benefitValueListItr = benefitValueList.iterator();
    		benefitValueListItr.hasNext();){
    			benefitLevelBO = (BenefitLevelBO)benefitValueListItr.next();
    			if(null == benefitLevelBO.getBenefitLevelDesc()
    				||	benefitLevelBO.getBenefitLevelDesc().trim().equals(WebConstants.EMPTY_STRING)){
    				validationFlag = false;
    				//add mess
    				messageList.add(new ErrorMessage("standard.benefit.null.value"));
    				break;
//    				ErrorMessage errorMessage = new ErrorMessage("standard.benefit.null.value");
  //  				messagesList.add(errorMessage);
    			}    			
    		}
    	}
        return validationFlag;    	
    }

    /**
     * @param request
     * @return
     */
    private boolean isStandardBenefitValidated(
            StandardBenefitCheckInRequest request) throws ServiceException {
        BenefitDefinitionLocateCriteria benefitDefinitionLocateCriteria = new BenefitDefinitionLocateCriteria();
        benefitDefinitionLocateCriteria.setBenefitMasterSystemId(request
                .getStandardBenefitVO().getStandardBenefitKey());
        StandardBenefitBusinessObjectBuilder builder = new StandardBenefitBusinessObjectBuilder();
        StandardBenefitBO standardBenefitBO =new StandardBenefitBO();
        standardBenefitBO.setStandardBenefitKey(request
                .getStandardBenefitVO().getStandardBenefitKey());
        standardBenefitBO.setRuleTypeList(request.getStandardBenefitVO().getRuleTypeList());
      //  String levelDescForFirstDuplicateRef = null;
    //    String PVAForFirstDuplicateRef = null;
    //    String levelDescForSecondDuplicateRef = null;
    //    String PVAForSecondDuplicateRef = null;
        String question = null;
        String questionDesc = null;
        String adminOptionName = null;
        String adminOptionDesc = null;
// CheckInValidation 'CR' Start
        boolean returnFlag = true;
// CheckInValidation 'CR' End	        
        try {
            LocateResults locateResults = builder.locate(
                    benefitDefinitionLocateCriteria, request.getUser());
            List resultList = locateResults.getLocateResults();
            int size = resultList.size();
            int count = 0;
            BenefitLevelBusinessObjectBuilder benefitLevelBusinessObjectBuilder = new BenefitLevelBusinessObjectBuilder();
            ;
            
            BenefitLocateCriteria benefitLocateCriteria = null;
            
            BenefitLevelBO benefitLevelBO = null;
            AdministrativeOptionLocateCriteria administrativeOptionLocateCriteria = null;
            SelectedQuestionListLocateCriteria selectedQuestionListLocateCriteria = null;
            BenefitLineBO benefitLineBO = null;
            int countDatatype = 0;
            // **Change**
            int countReference = 0;
            int countValue = 0;
            List referenceCode = null;
         //   boolean uniqueReference = true;
            boolean uniqueReferenceForAdminOption = true;
            boolean isReferencePresent = true;
            List benefitDesc = null;
            boolean uniqueDesc = true;
            List benefitLines = null;
            // **End**
            //check for mandate info
            // **Change made for only the check to happen if mandate
            // definition**
            //boolean mandateFlag = checkForMandateInfo(request);
            boolean mandateFlag = true;
            if ((request.getStandardBenefitVO().getBenefitType().trim()
                    .toUpperCase()).equals(WebConstants.MNDT_TYPE)) {
                mandateFlag = checkForMandateInfo(request);
            }
            if (!mandateFlag) {
                ErrorMessage errorMessage = new ErrorMessage(
                        "Mandate.info.should.be.present");                
                this.messageList.add(errorMessage);
// CheckInValidation 'CR' Start
                //return false;                
                returnFlag = false;
// CheckInValidation 'CR' End               
            } else {
                if (request.getAction() == 2) {
                    ErrorMessage errorMessage = new ErrorMessage(
                            "State.info.should.be.present");
                    this.messageList.add(errorMessage);
// CheckInValidation 'CR' Start
                    //return false;                
                    returnFlag = false;
// CheckInValidation 'CR' End
                }
            }
            // **End**
            if (size > 0) {
                boolean flag = true;
                for (int i = 0; i < size; i++) {
                    // **Change**
//                    referenceCode = new ArrayList();
//                    benefitDesc = new ArrayList();
//                    benefitLines = new ArrayList();
                    // **End**
                    BenefitDefinitionBO benefitDefinitionBOImpl = (BenefitDefinitionBO) resultList
                            .get(i);
                    benefitLocateCriteria = new BenefitLocateCriteria();
                    benefitLocateCriteria
                            .setBenefitDefinitionId(benefitDefinitionBOImpl
                                    .getBenefitDefinitionMasterKey());
                    benefitLocateCriteria.setSelectedBenefitTerm("%");
                    LocateResults locateResult = benefitLevelBusinessObjectBuilder
                            .searchBenefitLevels(benefitLocateCriteria, request
                                    .getUser(), true);
                    if (locateResult.getLocateResults().size() > 0) {
                        count++;
                        List list = locateResult.getLocateResults();
                        for (int j = 0; j < list.size(); j++) {
                            benefitLevelBO = (BenefitLevelBO) list.get(j);
                            // **Change**
                            // validation for unique description
//                            if (null != benefitLevelBO.getBenefitLevelDesc()) {
//                                if (!(benefitDesc.contains(benefitLevelBO
//                                        .getBenefitLevelDesc().trim()
//                                        .toUpperCase()))) {
//                                    benefitDesc.add(benefitLevelBO
//                                            .getBenefitLevelDesc().trim()
//                                            .toUpperCase());
//                                } else {
//                                    uniqueDesc = false;
//                                    break;
//                                }
//                            }
                            // **End**
                            List benefitLine = benefitLevelBO.getBenefitLines();
                            for (int k = 0; k < benefitLine.size(); k++) {
                                benefitLineBO = (BenefitLineBO) benefitLine
                                        .get(k);
                                if (benefitLineBO.getDataTypeName() == null) {
                                    countDatatype++;
                                }
                                // **Change**
                                if (benefitLineBO.getReference() == null) {
                                    countReference++;
                                }
                                if ((request.getStandardBenefitVO()
                                        .getBenefitType().trim().toUpperCase())
                                        .equals(WebConstants.MNDT_TYPE)) {
                                    if (benefitLineBO.getBenefitValue() == null
                                            || "".equals(benefitLineBO
                                                    .getBenefitValue())) {
                                        countValue++;
                                    }
                                }
                                // validation for unique reference for a benefit
                              //  if (null != benefitLineBO.getReference()) {
                                    // check whether that reference already
                                    // added
//                                    if (!(referenceCode.contains(benefitLineBO
//                                            .getReference().trim()
//                                            .toUpperCase()))) {
//                                        referenceCode.add(benefitLineBO
//                                                .getReference().trim()
//                                                .toUpperCase());
//                                        benefitLineBO.setBenefitLevelDesc(
//                                                benefitLevelBO.getBenefitLevelDesc());
//                                        benefitLines.add(benefitLineBO);
//                                    } //else {
//                                        levelDescForFirstDuplicateRef = benefitLevelBO.getBenefitLevelDesc();
//                                        PVAForFirstDuplicateRef = benefitLineBO.getPvaCode();
                                        // get the first line details - for 
                                        // unique reference validations
//                                        if(null != benefitLines && !benefitLines.isEmpty()){
//                                            BenefitLineBO benefitLineBOForRefVal = null; 
//                                            for(int p = 0; p < benefitLines.size(); p++){
//                                                benefitLineBOForRefVal 
//                                        			= (BenefitLineBO) benefitLines.get(p); 
//                                                if(benefitLineBO
//			                                            .getReference().trim()
//			                                            .toUpperCase().equals(
//			                                              benefitLineBOForRefVal.
//			                                              getReference().trim().toUpperCase())){
//                                                    levelDescForSecondDuplicateRef = 
//                                                        benefitLineBOForRefVal.getBenefitLevelDesc();
//                                                    PVAForSecondDuplicateRef = 
//                                                        benefitLineBOForRefVal.getPvaCode();
//                                                    break;
//                                                }
//                                            }
//                                        }
                                     //   uniqueReference = false;
                                  //  }
                         //       }
                                if (countDatatype > 0 || countReference > 0
                                        || countValue > 0){ // || !uniqueReference) {
                                    break;
                                }
                                // **End**
                            }
                            // **Change**
                            //if(countDatatype > 0){
                            if (countDatatype > 0 || countReference > 0
                                    || countValue > 0 ){ // || !uniqueReference) {
                                // **End**
                                break;
                            }
                          
                        }
                    }
                    BenefitAdministrationLocateCriteria administrationLocateCriteria = new BenefitAdministrationLocateCriteria();
                    administrationLocateCriteria.setBenefitDefinitionKey(benefitDefinitionBOImpl.getBenefitDefinitionMasterKey());
                    LocateResults results = builder.locate(administrationLocateCriteria,request
                                    .getUser());
                    List list = results.getLocateResults();
                    if(list.size() > 0){
	                    for (int j = 0; j < list.size(); j++) {
	                        BenefitAdministrationBO administrationBO = (BenefitAdministrationBO)list.get(j);
	                        administrativeOptionLocateCriteria = new AdministrativeOptionLocateCriteria();
	                        administrativeOptionLocateCriteria.setBenefitAdministrationSystemId(administrationBO.getBenefitAdministrationKey());
	                        //for bug fixing 42318
	                        administrativeOptionLocateCriteria.setBenefitDefinitionKey(administrationBO.getBenefitDefinitionKey());
	                        LocateResults results2 = builder.locate(administrativeOptionLocateCriteria,request.getUser());
	                        List adminOptionList = results2.getLocateResults();
	                        HashMap hashMap = new HashMap();
	                        HashMap adminOptionHashMap = new HashMap();
	                        if(adminOptionList.size() > 0){
//	                            List referenceCodeForAdminOption = new ArrayList();
	                            for (int k = 0; k < adminOptionList.size(); k++) {
	                                
	                                AdministrationOptionBO administrationOptionBO = (AdministrationOptionBO)adminOptionList.get(k);
		                            selectedQuestionListLocateCriteria = new SelectedQuestionListLocateCriteria();
		                            selectedQuestionListLocateCriteria.setAdminOptionSysId(administrationOptionBO.getAdminLevelOptionAssnSystemId());
		                            LocateResults selectedQuestionLocateResults = builder.locate(selectedQuestionListLocateCriteria,request.getUser());
		                            List selectedQuestionList = selectedQuestionLocateResults.getLocateResults();
		                            //List referenceCodeForAdminOption = new ArrayList();
		                            if(selectedQuestionList.size() > 0){
		                                for(int l = 0; l<selectedQuestionList.size(); l++){
			                                SelectedQuestionListBO selectedQuestionListBO = (SelectedQuestionListBO)selectedQuestionList.get(l);
			                                
			//                              validation for unique reference for an admin option
			                                if (null != selectedQuestionListBO.getReferenceId() && null != selectedQuestionListBO.getReferenceDesc()) {
			                                    // check whether that reference already
			                                    // added
//			                                    if (!(referenceCodeForAdminOption.contains(selectedQuestionListBO
//			                                            .getReferenceId().trim()
//			                                            .toUpperCase()))) {
			                                        
//			                                        referenceCodeForAdminOption.add(selectedQuestionListBO
//			                                                .getReferenceId().trim()
//			                                                .toUpperCase());
			                                    if (!(hashMap.containsKey(selectedQuestionListBO
			                                            .getReferenceId().trim()
			                                            .toUpperCase()))) {
			                                        hashMap.put(selectedQuestionListBO
			                                                .getReferenceId().trim()
			                                                .toUpperCase(),selectedQuestionListBO.getQuestionDesc());
			                                        adminOptionHashMap.put(selectedQuestionListBO
			                                                .getReferenceId().trim()
			                                                .toUpperCase(),administrationOptionBO.getAdminOptionDesc());
			                                        
			                                    } else {
			                                        
			                                        questionDesc = (String)hashMap.get(selectedQuestionListBO.getReferenceId());
			                                        question = selectedQuestionListBO.getQuestionDesc();
			                                        adminOptionName = administrationOptionBO.getAdminOptionDesc();
			                                        adminOptionDesc = (String)adminOptionHashMap.get(selectedQuestionListBO.getReferenceId());
			                                        
			                                        uniqueReferenceForAdminOption = false;
			                                    }
			                                }else{
			                                    isReferencePresent = false;
			                                }
//			                                if(!uniqueReferenceForAdminOption || !isReferencePresent){
			                                if(!isReferencePresent){
			                                    break;
			                                }
			                                
			                            } 
		                            }
//		                            if(!uniqueReferenceForAdminOption || !isReferencePresent){
		                            if(!isReferencePresent){
	                                    break;
	                                } 
		                        }
	                        }
//	                        if(!uniqueReferenceForAdminOption || !isReferencePresent){
	                        if(!isReferencePresent){
                                break;
                            }
	                    }
                    }
                    /*if(!uniqueReferenceForAdminOption || !isReferencePresent){
                        break;
                    }*/
                }
                // **Change**
                //if(size != count || countDatatype > 0){
                if (size != count) {
                    // **End**
                    this.messageList
                            .add(new ErrorMessage(
                                    "benefit.definition.should.have.atleast.one.benefit.level"));
// CheckInValidation 'CR' Start
                    //return false;                
                    returnFlag = false;
// CheckInValidation 'CR' End
                }
                // **Change**
                if (countDatatype > 0) {
                    this.messageList.add(new ErrorMessage(
                            "benefit.line.dataType.mandatory"));
// CheckInValidation 'CR' Start
                    //return false;                
                    returnFlag = false;
// CheckInValidation 'CR' End
                }
                if (countReference > 0) {
                    this.messageList.add(new ErrorMessage(
                            "benefit.line.reference.mandatory"));
// CheckInValidation 'CR' Start
                    //return false;                
                    returnFlag = false;
// CheckInValidation 'CR' End
                }
                if (countValue > 0) {
                    this.messageList.add(new ErrorMessage(
                            "benefit.line.value.mandatory"));
// CheckInValidation 'CR' Start
                    //return false;                
                    returnFlag = false;
// CheckInValidation 'CR' End
                }
//                if (!uniqueReference) {
//                    ErrorMessage errorMessage = new ErrorMessage("benefit.line.reference.unique");
//                    errorMessage.setParameters(new String[] {
//                            levelDescForFirstDuplicateRef,
//                            PVAForFirstDuplicateRef,
//                            levelDescForSecondDuplicateRef,
//                            PVAForSecondDuplicateRef});
//                    this.messageList.add(errorMessage);
//                    return false;
//                }
//                if (!uniqueReferenceForAdminOption) {
//                    if(adminOptionDesc.equals(adminOptionName)){
//                        ErrorMessage errorMessage = new ErrorMessage("admin.option.reference.unique");
//                        errorMessage.setParameters(new String[] {
//                                question,questionDesc,adminOptionName});
//                        this.messageList.add(errorMessage);
//                        return false; 
//                    }else{
//                        ErrorMessage errorMessage = new ErrorMessage("admin.option.reference.for.two.admin.options.unique");
//                        errorMessage.setParameters(new String[] {
//                                question,questionDesc,adminOptionName,adminOptionDesc});
//                        this.messageList.add(errorMessage);
//                        return false;
//                    }
//                    
//                }
                if (!isReferencePresent) {                    
                    this.messageList.add(new ErrorMessage(
                            "adminoption.reference.not.exist.info"));
// CheckInValidation 'CR' Start
                    //return false;                
                    returnFlag = false;
// CheckInValidation 'CR' End
                }
//                if (!uniqueDesc) {
//                    this.messageList.add(new ErrorMessage(
//                            "benefit.level.description.unique"));
//                    return false;
//                }
                if(isValidrule(standardBenefitBO))
                {
// CheckInValidation 'CR' Start
                    //return false;                
                    returnFlag = false;
// CheckInValidation 'CR' End
                }
                // **End**
                if (size == count && returnFlag) {
                    this.messageList.add(new InformationalMessage(
                            "standard.benefit.validation.success"));
                }
            } else if ((request.getStandardBenefitVO().getBenefitType().trim()
                    .toUpperCase()).equals(WebConstants.MNDT_TYPE)) {
                this.messageList
                        .add(new ErrorMessage(
                                "mandate.benefit.should.have.atleast.one.mandate.definition"));
// CheckInValidation 'CR' Start
                //return false;                
                returnFlag = false;
// CheckInValidation 'CR' End
            } else {
                this.messageList.add(new ErrorMessage(
                        "benefit.should.have.atleast.one.benefit.definition"));
// CheckInValidation 'CR' Start
                //return false;                
                returnFlag = false;
// CheckInValidation 'CR' End
            }
// CheckInValidation 'CR' Start
            //return true;                
            return returnFlag;
//CheckInValidation 'CR' End
        } catch (SevereException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing StandardBenefitCheckInRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
    }


    /**
     * @param standardBenefitBO
     * @return
     * @throws SevereException
     */
    private boolean isStandardBenefitDuplicate(
            StandardBenefitBO standardBenefitBO) throws SevereException {
        StandardBenefitBusinessObjectBuilder builder = new StandardBenefitBusinessObjectBuilder();
        return builder.isEntityDuplicate(standardBenefitBO
                .getBenefitIdentifier(),
                standardBenefitBO.getBusinessDomains(), standardBenefitBO
                        .getParentSystemId());
        /*
         * return
         * DomainUtil.isEntityDuplicate("stdbenefit",standardBenefitBO.getBenefitIdentifier(),
         * standardBenefitBO.getBusinessDomains(),standardBenefitBO.getParentSystemId());
         */
    }


    /**
     * @param StandardBenefitCreateRequest
     * @return
     *  
     */
    public StandardBenefitBO getStandardBenefitObject(
            StandardBenefitCreateRequest request) {
        StandardBenefitVO standardBenefitVO = (StandardBenefitVO) request
                .getStandardBenefitVO();
        StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
        standardBenefitBO.setStandardBenefitKey(standardBenefitVO
                .getStandardBenefitKey());
        standardBenefitBO.setBenefitIdentifier(standardBenefitVO
                .getBenefitIdentifier());
        standardBenefitBO.setDescription(standardBenefitVO.getDescription());

        List lobList = standardBenefitVO.getLobList();
        List businessEntityList = standardBenefitVO.getBusinessEntityList();
        List businessGroupList = standardBenefitVO.getBusinessGroupList();
        List marketBusinessUnitList = standardBenefitVO.getMarketBusinessUnitList();
        //change **12/28
        standardBenefitBO.setLobList(lobList);
        standardBenefitBO.setBeList(businessEntityList);
        standardBenefitBO.setBgList(businessGroupList);
        standardBenefitBO.setMarketBusinessUnitList(marketBusinessUnitList);
        //**end
        standardBenefitBO.setBusinessDomains(BusinessUtil.convertToDomains(
                lobList, businessEntityList, businessGroupList, marketBusinessUnitList));

        standardBenefitBO.setTermList(standardBenefitVO.getTermList());
        standardBenefitBO
                .setQualifierList(standardBenefitVO.getQualifierList());
        standardBenefitBO.setDataTypeList(standardBenefitVO.getDataTypeList());
        standardBenefitBO.setPVAList(standardBenefitVO.getPVAList());

        //new code for enhancement
        standardBenefitBO.setMandateType(standardBenefitBO.getMandateType());
        standardBenefitBO.setBenefitType(standardBenefitBO.getBenefitType());
        standardBenefitBO.setStateCode(standardBenefitBO.getStateCode());
        standardBenefitBO.setStateDesc(standardBenefitBO.getStateDesc());
        standardBenefitBO.setRuleTypeList(standardBenefitBO.getRuleTypeList());

        return standardBenefitBO;

    }


    /**
     * @param StandardBenefitDeleteRequest
     * @return
     *  
     */
    public StandardBenefitBO getStandardBenefitObject(
            StandardBenefitDeleteRequest request) {
        StandardBenefitVO standardBenefitVO = (StandardBenefitVO) request
                .getStandardBenefitVO();
        StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
        standardBenefitBO.setStandardBenefitKey(standardBenefitVO
                .getStandardBenefitKey());
        return standardBenefitBO;
    }


    /**
     * @param StandardBenefitUpdateRequest
     * @return
     *  
     */
    public StandardBenefitBO getStandardBenefitObject(
            StandardBenefitUpdateRequest request) {
        StandardBenefitVO standardBenefitVO = (StandardBenefitVO) request
                .getStandardBenefitVO();
        StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
        standardBenefitBO.setStandardBenefitKey(standardBenefitVO
                .getStandardBenefitKey());
        standardBenefitBO.setBenefitIdentifier(standardBenefitVO
                .getBenefitIdentifier());
        standardBenefitBO.setParentSystemId(standardBenefitVO
                .getStandardBenefitParentKey());
        standardBenefitBO.setDescription(standardBenefitVO.getDescription());

        List lobList = standardBenefitVO.getLobList();
        List businessEntityList = standardBenefitVO.getBusinessEntityList();
        List businessGroupList = standardBenefitVO.getBusinessGroupList();
        List marketBusinessUnitList = standardBenefitVO.getMarketBusinessUnitList();
        //**12/29
        standardBenefitBO.setLobList(lobList);
        standardBenefitBO.setBeList(businessEntityList);
        standardBenefitBO.setBgList(businessGroupList);
        standardBenefitBO.setMarketBusinessUnitList(marketBusinessUnitList);
        //**end
        
        standardBenefitBO.setBusinessDomains(BusinessUtil.convertToDomains(
                lobList, businessEntityList, businessGroupList, marketBusinessUnitList));

        standardBenefitBO.setTermList(standardBenefitVO.getTermList());
        standardBenefitBO
                .setQualifierList(standardBenefitVO.getQualifierList());
        standardBenefitBO.setDataTypeList(standardBenefitVO.getDataTypeList());
        standardBenefitBO.setPVAList(standardBenefitVO.getPVAList());
        return standardBenefitBO;
    }


    public BusinessObjectManager getBOM() {
        BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory) ObjectFactory
                .getFactory(ObjectFactory.BOM);
        BusinessObjectManager bom = bomf.getBusinessObjectManager();
        return bom;
    }


    /**
     * @param businessObject
     * @return
     */
    private DomainDetail getBusinessDomainValues(
            StandardBenefitBO standardBenefitBO) {
        DomainDetail businessDomain = new DomainDetail();
        businessDomain.setEntityType("stdbenefit");
        businessDomain.setEntityName(standardBenefitBO.getBenefitIdentifier());
        businessDomain.setEntityParentId(standardBenefitBO.getParentSystemId());
        businessDomain.setDomainList(standardBenefitBO.getBusinessDomains());
        businessDomain.setLastUpdatedUser(standardBenefitBO
                .getLastUpdatedUser());
        businessDomain.setLastUpdatedTimeStamp(standardBenefitBO
                .getLastUpdatedTimestamp());
        return businessDomain;
    }


    /*
     * Used for validating whether the effective date and expiry date of a
     * benefit administration comes within its corresponding benefit definition
     */

    public WPDResponse execute(CreateBenefitAdministrationRequest request)
            throws ServiceException {
        Logger
                .logInfo("StandardBenefitBusinessValidationService - Entering execute(): Benefit Administration Create");
        CreateBenefitAdministrationResponse createBenefitAdministrationResponse = null;
        createBenefitAdministrationResponse = (CreateBenefitAdministrationResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.CREATE_BENEFIT_ADMINISTRATION_RESPONSE);
        Date effectiveDate = request.getBenefitAdministrationVO()
                .getEffectiveDate();
        Date expiryDate = request.getBenefitAdministrationVO().getExpiryDate();
        StandardBenefitBusinessObjectBuilder builder = new StandardBenefitBusinessObjectBuilder();
        
        boolean isSameDate = false;
        try {
            BenefitDefinitionBO benefitDefinitionBO = new BenefitDefinitionBO();
            benefitDefinitionBO.setBenefitDefinitionMasterKey(request
                    .getBenefitAdministrationVO().getBenefitDefinitionKey());

            benefitDefinitionBO = (BenefitDefinitionBO) builder
                    .retrieve(benefitDefinitionBO);

            if (!(effectiveDate.compareTo(benefitDefinitionBO
                    .getEffectiveDate()) < 0 || expiryDate
                    .compareTo(benefitDefinitionBO.getExpiryDate()) > 0)) {
                List validDateList = null;
                BenefitAdministrationLocateCriteria benefitAdministrationLocateCriteria = new BenefitAdministrationLocateCriteria();
                benefitAdministrationLocateCriteria
                        .setBenefitAdministrationKey(request
                                .getBenefitAdministrationVO()
                                .getBenefitAdministrationKey());
                benefitAdministrationLocateCriteria.setEffectiveDate(request
                        .getBenefitAdministrationVO().getEffectiveDate());
                benefitAdministrationLocateCriteria.setExpiryDate(request
                        .getBenefitAdministrationVO().getExpiryDate());
                benefitAdministrationLocateCriteria
                        .setBenefitDefinitionKey(request
                                .getBenefitAdministrationVO()
                                .getBenefitDefinitionKey());

                validDateList = null; 
                //builder.validateDateRange(benefitAdministrationLocateCriteria);

                if (validDateList != null && validDateList.size() > 0) {
                    createBenefitAdministrationResponse
                            .addMessage(new ErrorMessage("date.range.not.valid"));
                    createBenefitAdministrationResponse.setSuccess(false);
                } else {
                    createBenefitAdministrationResponse.setSuccess(true);

                }
            } else {
                createBenefitAdministrationResponse
                        .addMessage(new ErrorMessage(
                                "benefit.administation.date.range"));
                createBenefitAdministrationResponse.setSuccess(false);

            }

        } catch (WPDException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing CreateBenefitAdministrationRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        Logger
                .logInfo("StandardBenefitBusinessValidationService - Returning execute(): Benefit Administration Create");
        return createBenefitAdministrationResponse;
    }


    /**
     * checkin validations
     * 
     * @param DeleteBenefitLevelRequest
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(
            DeleteBenefitLevelRequest deleteBenefitLevelRequest)
            throws ServiceException {

        BenefitWrapperVO benefitWrapperVO = (BenefitWrapperVO) deleteBenefitLevelRequest
                .getBenefitWrapperVO();
        BusinessObjectManager bom = getBOM();
        DeleteBenefitLevelResponse deleteBenefitLevelResponse = (DeleteBenefitLevelResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.DELETE_BENEFIT_LEVEL_RESPONSE);
        List messages = null;
        BenefitLineLocateCriteria benefitLineLocateCriteria = null;
        BenefitLevelLocateCriteria benefitLevelLocateCriteria = null;
        try {
            StandardBenefitBusinessObjectBuilder builder = new StandardBenefitBusinessObjectBuilder();
            List benefitLevels = benefitWrapperVO.getBenefitLevelsList();
            Collections.sort(benefitLevels);
            messages = new ArrayList(1);
            benefitLevelLocateCriteria = new BenefitLevelLocateCriteria();
            benefitLevelLocateCriteria
                    .setBenefitLevelId(deleteBenefitLevelRequest
                            .getBenefitLevelVO().getBenefitLevelId());
            if (!builder.locate(benefitLevelLocateCriteria)) {
                LocateResults locateResults = builder.locate(
                        benefitLevelLocateCriteria, deleteBenefitLevelRequest
                                .getUser());
                if (null != locateResults
                        && null != locateResults.getLocateResults()) {
                    List benefitLines = locateResults.getLocateResults();
                    Collections.sort(benefitLines);
                    if (null != benefitLines) {
                        for (int j = 0; j < benefitLines.size(); j++) {
                            BenefitLineBO benefitLineBO = (BenefitLineBO) benefitLines
                                    .get(j);
                            benefitLineLocateCriteria = new BenefitLineLocateCriteria();
                            benefitLineLocateCriteria
                                    .setBenefitLineId(benefitLineBO
                                            .getBenefitLineId());
                            if (builder.locate(benefitLineLocateCriteria)) {
                                messages.add(new ErrorMessage(
                                        "benefit.line.in.use"));
                                deleteBenefitLevelResponse
                                        .setMessages(messages);
                            }
                        }
                    }
                }
            } else {
                messages.add(new ErrorMessage("benefit.level.in.use"));
                deleteBenefitLevelResponse.setMessages(messages);
            }
        } catch (Exception e) {
            List logParameters = new ArrayList(1);
            logParameters.add(deleteBenefitLevelRequest);
            String logMessage = "Failed while processing DeleteBenefitLevelRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        return deleteBenefitLevelResponse;
    }


    /**
     * checkin validations
     * 
     * @param request
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(DeleteBenefitLineRequest deleteBenefitLineRequest)
            throws ServiceException {

        BenefitWrapperVO benefitWrapperVO = (BenefitWrapperVO) deleteBenefitLineRequest
                .getBenefitWrapperVO();
        BusinessObjectManager bom = getBOM();
        DeleteBenefitLineResponse deleteBenefitLineResponse = (DeleteBenefitLineResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.DELETE_BENEFIT_LINE_RESPONSE);
        List messages = null;
        BenefitLineLocateCriteria benefitLineLocateCriteria = null;
        BenefitLevelLocateCriteria benefitLevelLocateCriteria = null;
        try {
            StandardBenefitBusinessObjectBuilder builder = new StandardBenefitBusinessObjectBuilder();
            List benefitLevels = benefitWrapperVO.getBenefitLevelsList();
            Collections.sort(benefitLevels);
            messages = new ArrayList(1);
            BenefitLineVO benefitLineVO = deleteBenefitLineRequest
                    .getBenefiLineVO();
            benefitLineLocateCriteria = new BenefitLineLocateCriteria();
            benefitLineLocateCriteria.setBenefitLineId(benefitLineVO
                    .getBenefitLineId());
            if (builder.locate(benefitLineLocateCriteria)) {
                messages.add(new ErrorMessage("benefit.line.in.use"));
                deleteBenefitLineResponse.setMessages(messages);
            }
        } catch (Exception e) {
            List logParameters = new ArrayList(1);
            logParameters.add(deleteBenefitLineRequest);
            String logMessage = "Failed while processing DeleteBenefitLineRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        return deleteBenefitLineResponse;
    }

}