/*
 * BenefitComponentBusinessService.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.benefitcomponent.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.jdbc.UncategorizedSQLException;

import com.wellpoint.wpd.business.benefitcomponent.builder.BenefitComponentBusinessObjectBuilder;
import com.wellpoint.wpd.business.benefitcomponent.locatecriteria.BenefitComponentNotesLocateCriteria;
import com.wellpoint.wpd.business.benefitcomponent.locatecriteria.BenefitHierarchyLocateCriteria;
import com.wellpoint.wpd.business.benefitcomponent.locatecriteria.BenefitLocateCriteria;
import com.wellpoint.wpd.business.benefitcomponent.locatecriteria.ComponentsBenefitAdministrationLocateCriteria;
import com.wellpoint.wpd.business.benefitcomponent.locatecriteria.ComponentsBenefitDefinitionLocateCriteria;
import com.wellpoint.wpd.business.benefitcomponent.locatecriteria.NonAdjBenefitMandateLocateCriteria;
import com.wellpoint.wpd.business.common.util.DomainUtil;
import com.wellpoint.wpd.business.framework.bo.BusinessObjectManagerFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager;
import com.wellpoint.wpd.business.framework.service.ValidationServiceController;
import com.wellpoint.wpd.business.framework.service.WPDService;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.business.util.BusinessUtil;

import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitBO;
import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponent;
import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentBO;
import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitHierarchyAssociationBO;
import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitHierarchyBO;
import com.wellpoint.wpd.common.benefitcomponent.bo.ComponentsBenefitAdministrationBO;
import com.wellpoint.wpd.common.benefitcomponent.locatecriteria.BenefitComponentLocateCriteria;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentApproveRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentAttachNotesRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentCheckOutRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentCopyRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentDeleteRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentPublishRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentRejectRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentRetrieveRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentRetrieveRequestForEdit;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentSaveRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentScheduleForTestRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentSearchRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentTestFailRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentTestPassRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentUnlockRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentViewAllRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentViewRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitHierarchyCreateRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitHierarchyDeleteRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitHierarchySearchRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitHierarchyUpdateRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitRetrieveRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.DeleteBenefitComponentNotesRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.LocateBenefitComponentNotesRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.LocateComponentsBenefitAdministrationRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.LocateComponentsBenefitDefinitionRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.LocateNonAdjBenefitMandateRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.RetrieveBenefitComponentQuestionnairRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.UpdateBenefitLinesRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.UpdateComponentsBenefitAdministrationRequest;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentApproveResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentAttachNotesResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentCheckOutResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentCopyResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentDeleteResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentPublishResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentRejectResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentRetrieveResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentRetrieveResponseForEdit;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentSaveResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentScheduleForTestResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentSearchResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentTestFailResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentTestPassResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentUnlockResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentViewAllResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentViewResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitHierarchyDeleteResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitHierarchyResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitHierarchySearchResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitHierarchyUpdateResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitRetrieveResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.DeleteBenefitComponentNotesResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.LocateBenefitComponentNotesResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.LocateComponentsBenefitAdministrationResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.LocateComponentsBenefitDefinitionResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.LocateNonAdjBenefitMandateResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.UpdateBenefitLinesResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.UpdateComponentsBenefitAdministrationResponse;
import com.wellpoint.wpd.common.benefitcomponent.vo.BenefitComponentLocateCriteriaVO;
import com.wellpoint.wpd.common.benefitcomponent.vo.BenefitComponentVO;
import com.wellpoint.wpd.common.benefitcomponent.vo.BenefitHierarchyAssociationVO;
import com.wellpoint.wpd.common.benefitcomponent.vo.BenefitHierarchyVO;
import com.wellpoint.wpd.common.benefitcomponent.vo.BenefitVO;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.notes.bo.AttachedNotesBO;
import com.wellpoint.wpd.common.notes.bo.NotesAttachmentDomainOverrideBO;
import com.wellpoint.wpd.common.notes.bo.NotesAttachmentOverrideBO;
import com.wellpoint.wpd.common.notes.bo.NotesToQuestionAttachmentBO;
import com.wellpoint.wpd.common.notes.request.BnftCompNotesToQuestionAttachmentRequest;
import com.wellpoint.wpd.common.notes.response.BnftCompNotesToQuestionAttachmentResponse;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitLevel;
import com.wellpoint.wpd.common.refdata.request.RefDataDomainValidationRequest;
import com.wellpoint.wpd.common.refdata.response.RefDataDomainValidationResponse;
import com.wellpoint.wpd.common.standardbenefit.bo.AdminOptionHideBO;
import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitBO;
import com.wellpoint.wpd.common.standardbenefit.request.HideAdminOptionRequest;
import com.wellpoint.wpd.common.standardbenefit.response.HideAdminOptionResponse;
import com.wellpoint.wpd.common.standardbenefit.vo.AdministrationOptionVO;
import com.wellpoint.wpd.common.subcatalog.vo.SubCatalogVO;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * Business Service class for Benefit Component
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitComponentBusinessService extends WPDService {
    private List messageList = null;

    /**
     * Default constructor
     */
    public BenefitComponentBusinessService() {
    }


    /**
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param request
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(WPDRequest request) throws ServiceException {
        throw new ServiceException(BusinessConstants.UNKNOWN_REQUEST_EXCEPTION,
                null, null);
    }


    /**
     * Execute method for benefitComponentSaveRequest
     * 
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param benefitComponentSaveRequest
     * @return benefitComponentSaveResponse
     * @throws WPDException
     */
    public WPDResponse execute(
            BenefitComponentSaveRequest benefitComponentSaveRequest)
            throws ServiceException {
        try {
        	User user = benefitComponentSaveRequest.getUser();
            Logger.logInfo("Inside Benefit Component Save Service");
            BenefitComponentSaveResponse benefitComponentSaveResponse = null;
            
            
            BenefitComponentVO benefitComponentVO = (BenefitComponentVO) benefitComponentSaveRequest
                    .getBenefitComponentVO();
            BenefitComponentBO benefitComponentBO = getBenefitComponentObject(benefitComponentVO);
            modifyDomainValues(benefitComponentVO);
            BusinessObjectManager bom = getBOM();
            benefitComponentSaveResponse = (BenefitComponentSaveResponse) WPDResponseFactory
                    .getResponse(WPDResponseFactory.SAVE_BENEFIT_COMPONENT_RESPONSE);
            if (benefitComponentSaveRequest.getAction() == BenefitComponentSaveRequest.CREATE_BENEFIT_COMPONENT) {
                benefitComponentSaveResponse = (BenefitComponentSaveResponse) new ValidationServiceController()
                        .execute(benefitComponentSaveRequest);
            
                if (benefitComponentSaveResponse.isSuccessFlag()) {
                    //Calling the persist method to create a benefit
	            	try{
	                    /*
	                     * Checking Too many domain combinations
	                     */
	                    bom.persist(benefitComponentBO, user, true);
	            	}catch(UncategorizedSQLException e){
	            		benefitComponentSaveResponse = null;
	            		return benefitComponentSaveResponse;
	            	}
                    benefitComponentSaveResponse
                            .addMessage(new InformationalMessage(
                                    BusinessConstants.BENEFIT_COMPONENT_SAVE_SUCCESS));
                    Logger.logInfo("Benefit Component Saved Successfully");
                }
            } else {
                BenefitComponentVO oldBenefitComponentVO = (BenefitComponentVO) benefitComponentSaveRequest
                        .getOldBenefitComponentVO();
                BenefitComponentBO oldBenefitComponentBO = getOldBenefitComponentBO(benefitComponentSaveRequest);
                //If OldBenefitComponentBO and BenefitCoomponentBO are
                // different changeIdentity method is called
                if (isKeyValueChanged(oldBenefitComponentBO, benefitComponentBO)) {
                    benefitComponentSaveResponse = (BenefitComponentSaveResponse) new ValidationServiceController()
                            .execute(benefitComponentSaveRequest);
             
                    if (!benefitComponentSaveResponse.isSuccessFlagForSave()){
                    	benefitComponentSaveResponse.setBenefitComponentBO(oldBenefitComponentBO);
                        return benefitComponentSaveResponse;
                    }
//Enhancement - To incorporate delete for domain Change   
                   
                    bom.changeIdentity(oldBenefitComponentBO,
                            benefitComponentBO, user);                   
//                  if (benefitComponentSaveRequest.isDomainChange()){
/*  only happen if domain value change                  
                  	if(!this.isValidBenefitPerDomain(benefitComponentBO)){
                    	if(benefitComponentSaveRequest.isDoneFlag()){
                        	benefitComponentSaveResponse
	                        	.addMessage(new ErrorMessage("benefit.hierarchy.invalid.benefit")); 
                        	benefitComponentSaveRequest.setDoneFlag(false);                    		
                    	}else{                    		
                        	benefitComponentSaveResponse
	                        	.addMessage(new InformationalMessage("benefit.hierarchy.invalid.benefit.change")); 
                    	}
                   	}
                   	}
*/                    
                } else {
                    //Calling the persist method to update benefit component if
                    // the domain Value is not changed                   
                    bom.persist(benefitComponentBO, user, false);            
                }
                
                // To delete the notes if the componentTypes do not match
                if (benefitComponentSaveRequest.isDeleteBenefit()) {
                    BenefitComponent subObj = new BenefitComponent();
                    subObj
                            .setBenefitComponentId(benefitComponentSaveRequest
                                    .getBenefitComponentVO()
                                    .getBenefitComponentId());
                    bom.delete(subObj, benefitComponentBO,
                            user);                   

                    NotesAttachmentDomainOverrideBO notesAttachmentDomainOverrideBO = new NotesAttachmentDomainOverrideBO();
                    // Set the required values to the bo
                    notesAttachmentDomainOverrideBO
                            .setPrimaryEntityId(benefitComponentBO
                                    .getBenefitComponentId());
                    notesAttachmentDomainOverrideBO
                            .setPrimaryEntityType(BusinessConstants.ATTACH_COMPONENT);	
                    notesAttachmentDomainOverrideBO
                            .setBenefitComponentId(benefitComponentBO
                                    .getBenefitComponentId());
                    // calling the delete mathod of the bom
                    boolean benefitComponentNotesDeleted = bom.delete(
                            notesAttachmentDomainOverrideBO,
                            benefitComponentBO, user);                   
                } 
                benefitComponentSaveRequest.setDeleteBenefit(false);
                // To delete the notes if the componentTypes do not match END
                
                
              	if(!this.isValidBenefitPerDomain(benefitComponentBO)){
                	if(benefitComponentSaveRequest.isDoneFlag()){
                	                		ErrorMessage errorMessage = new ErrorMessage(BusinessConstants.BENEFIT_HIERARCHY_INVALID_BENEFIT_CHANGE);
	            			errorMessage.setParameters(new String[]{("'")});
	                    	benefitComponentSaveResponse
	                        	.addMessage(errorMessage); 
                          	benefitComponentSaveRequest.setDoneFlag(false);                    		
                	}//else{                    		
//                    	benefitComponentSaveResponse
  //                      	.addMessage(new ErrorMessage(BusinessConstants.BENEFIT_HIERARCHY_INVALID_BENEFIT));
    //            	}
               	}
            

                benefitComponentSaveResponse
                        .addMessage(new InformationalMessage(BusinessConstants.BENEFIT_COMPONENT_UPDATED_SUCCESS));
                benefitComponentSaveResponse.setSuccessFlag(true);
                benefitComponentSaveResponse.setSuccessFlagForSave(true);
            }            
          
            
            benefitComponentSaveResponse
                    .setBenefitComponentBO(benefitComponentBO);
            
            if (benefitComponentSaveRequest.isDateChanged()) {
                BenefitComponentBusinessObjectBuilder benefitComponentBusinessObjectBuilder = new BenefitComponentBusinessObjectBuilder();
                BenefitHierarchyLocateCriteria benefitHierarchyLocateCriteria = new BenefitHierarchyLocateCriteria();
                benefitHierarchyLocateCriteria
                        .setBenefitComponentId(benefitComponentBO
                                .getBenefitComponentId());
                LocateResults locateResults = benefitComponentBusinessObjectBuilder
                        .locate(benefitHierarchyLocateCriteria,
                                benefitComponentSaveRequest.getUser());
                List searchList = locateResults.getLocateResults();
                if (null != locateResults
                        && null != searchList
                        && searchList.size() > 0) {
                	
                	BenefitHierarchyBO benefitHierarchyBO = new BenefitHierarchyBO();
                    benefitHierarchyBO.setBenefitHierarchiesList(searchList);
                     
                    for (int i = 0; i < searchList.size(); i++) {
                                        	
                        BenefitHierarchyAssociationBO benefitHierarchyAssociationBO = (BenefitHierarchyAssociationBO) searchList.get(i);
                        benefitHierarchyBO
                                .setBenefitHierarchyAssociationId(benefitHierarchyAssociationBO
                                        .getBenefitHierarchyAssociationId());
                        benefitHierarchyBO
                                .setBenefitHierarchyId(benefitHierarchyAssociationBO
                                        .getBenefitHierarchyId());
                        benefitHierarchyBO
                                .setBenefitComponentId(benefitHierarchyAssociationBO
                                        .getBenefitComponentId());
  
                        benefitHierarchyBO.setBenefitId(benefitHierarchyAssociationBO
                        				.getBenefitId());

                        bom.delete(benefitHierarchyBO, benefitComponentBO,
                                benefitComponentSaveRequest.getUser());
                    }
                    //return benefitComponentSaveResponse;
                }
            }           
            
          //  if(benefitComponentSaveResponse.isSuccessFlag()){
 
            if (benefitComponentSaveRequest.isDoneFlag()) {
            	if(!this.isValidBenefitPerDomain(benefitComponentBO)){
                	benefitComponentSaveResponse
                		.addMessage(new ErrorMessage(BusinessConstants.BENEFIT_HIERARCHY_INVALID_BENEFIT));
                	
            	}else{
	                benefitComponentSaveResponse = doDoneOperations(benefitComponentSaveRequest);
	          
	                if (benefitComponentSaveResponse.isSuccessFlag()) {
	                    if (!benefitComponentSaveRequest.isCheckInFlag() && !benefitComponentSaveRequest.isDateChanged()){
	                        benefitComponentSaveResponse
	                                .addMessage(new InformationalMessage(
	                                		BusinessConstants.BENFIT_COMPONENT_VALIDATION_SUCCESS));
		                    benefitComponentSaveResponse
	                        	.addMessage(new InformationalMessage(BusinessConstants.BENEFIT_COMPONENT_UPDATED_SUCCESS));
		                    Logger.logInfo("Bnefit Component Updated Successfully");
		                    benefitComponentSaveResponse
		                    	.setBenefitComponentBO(benefitComponentBO);
	                    }		                   
	                    else if(benefitComponentSaveResponse.isSuccessFlag() && benefitComponentSaveRequest.isCheckInFlag() && benefitComponentSaveRequest.isDoneFlag()){
	                    	//benefitComponentSaveResponse
								//.addMessage(new InformationalMessage(BusinessConstants.BENEFIT_COMPONENT_UPDATED_SUCCESS));
	                    	benefitComponentSaveResponse.setBenefitComponentBO(null);
	                    }else{
	                    	benefitComponentSaveResponse
                        		.addMessage(new InformationalMessage(BusinessConstants.BENEFIT_COMPONENT_UPDATED_SUCCESS));
	                    }

	                } else {
	                    benefitComponentSaveResponse
	                            .setBenefitComponentBO(benefitComponentBO);
	                    benefitComponentSaveResponse
                        	.addMessage(new InformationalMessage(BusinessConstants.BENEFIT_COMPONENT_UPDATED_SUCCESS));
	                    //benefitComponentSaveResponse.setSuccessFlag(true);
	                }
            	}
            }
           // }
            
            benefitComponentSaveResponse.setDomainDetail(DomainUtil
                    .retrieveDomainDetailInfo(BusinessConstants.BENEFIT_COMPONENT, benefitComponentBO
                            .getBenefitComponentParentId()));
            return benefitComponentSaveResponse;
        } catch (Exception ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(benefitComponentSaveRequest);
            String logMessage = "Failed while processing BenefitComponentCreateRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        
        
    }
    
    private boolean isValidBenefitPerDomain(BenefitComponentBO targetBenefitComponentBO)throws SevereException{
        BenefitComponentBusinessObjectBuilder benefitComponentBusinessObjectBuilder = new BenefitComponentBusinessObjectBuilder();
        BenefitHierarchyLocateCriteria benefitHierarchyLocCriteria = new BenefitHierarchyLocateCriteria();
        benefitHierarchyLocCriteria.setBenefitComponentId(targetBenefitComponentBO
                        .getBenefitComponentId());
        benefitHierarchyLocCriteria.setLobList(BusinessUtil.getLobList(targetBenefitComponentBO
                        .getBusinessDomainList()));
        benefitHierarchyLocCriteria.setBusinessEntityList(BusinessUtil
                        .getbusinessEntityList(targetBenefitComponentBO
                                .getBusinessDomainList()));
        benefitHierarchyLocCriteria.setBusinessGroupList(BusinessUtil
                        .getBusinessGroupList(targetBenefitComponentBO
                                .getBusinessDomainList()));
        benefitHierarchyLocCriteria.setMarketBusinessUnitList(BusinessUtil
                .getMarketBusinessUnitList(targetBenefitComponentBO
                        .getBusinessDomainList()));
        LocateResults locateResults = benefitComponentBusinessObjectBuilder
                .locateBenefitHieararchiesToBeRemoved(benefitHierarchyLocCriteria);
        if(null != locateResults 
        		&& null != locateResults.getLocateResults() 
				&& !locateResults.getLocateResults().isEmpty()){
        	return false;
        }
        return true;
    }


    /**
     * Benefit component save method
     * 
     * @param benefitComponentSaveRequest
     * @return
     * @throws ServiceException
     */
    private BenefitComponentSaveResponse doDoneOperations(
            BenefitComponentSaveRequest benefitComponentSaveRequest)
            throws ServiceException {
        Logger.logInfo("Inside Benefit Component Check in Service");
        BenefitComponentSaveResponse benefitComponentSaveResponse = null;
        List messageList;
        benefitComponentSaveResponse = (BenefitComponentSaveResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.SAVE_BENEFIT_COMPONENT_RESPONSE);
        benefitComponentSaveResponse = (BenefitComponentSaveResponse) (new ValidationServiceController()
                .execute(benefitComponentSaveRequest));
         
        if (benefitComponentSaveResponse.isSuccessFlag()){
	        //added for admin method  validation

	               
	        try {
	        	BusinessObjectManager bom = getBOM();
	            if (benefitComponentSaveResponse.isSuccessFlag()
	                    && benefitComponentSaveRequest.isCheckInFlag()) {
	                // reference data validations
	                RefDataDomainValidationRequest refDataDomainValidationRequest = refDataBenefitValidation(benefitComponentSaveRequest);
	                RefDataDomainValidationResponse refDataDomainValidationResponse ;
	                refDataDomainValidationResponse=(RefDataDomainValidationResponse) new ValidationServiceController().execute(refDataDomainValidationRequest);
	            	    	
	            	if(!refDataDomainValidationResponse.isSuccess()){
	            	    ErrorMessage errorMessage = new ErrorMessage(WebConstants.REFERENCE_DATA_VALIDATION_FAILURE);
	            	    errorMessage.setParameters (new String[] {" "+refDataDomainValidationResponse.getErrorMessage()} );
	            	    benefitComponentSaveResponse.addMessage(errorMessage);
	            	    benefitComponentSaveRequest.setCheckInFlag(false);
	            	}
	            	if(refDataDomainValidationResponse.isSuccess()){
	            		messageList = new ArrayList(1);
	            		
	                    BenefitComponentBO benefitComponentBO = benefitComponentSaveResponse.getBenefitComponentBO();
	            		String [] param = new String[] {benefitComponentSaveRequest.getBenefitComponentVO().getBenefitComponentName()};
	            		
	                    //InformationalMessage message;
		                if (bom.checkIn(benefitComponentBO, benefitComponentSaveRequest
		                        .getUser())) {
		                	InformationalMessage message = new InformationalMessage(WebConstants.BENEFIT_COMPONENT_CHECKIN_SUCCESS);
		                	benefitComponentSaveResponse.setSuccessFlag(true);
		                    Logger.logInfo("Benefit Component Checked In Successfully");
		                    message.setParameters(param);
		                	messageList.add(message);
		                } else {
	
		                	ErrorMessage message = new ErrorMessage(WebConstants.BENEFIT_COMPONENT_CHECKIN_FAILURE);
		                	benefitComponentSaveResponse.setSuccessFlag(false);
		                	message.setParameters(param);
		                	messageList.add(message);
		                	Logger.logInfo("Benefit Component Check In Failed");
		                }
	                	benefitComponentSaveResponse.setMessages(messageList);
		            }
	            }
	        } catch (WPDException ex) {
	            benefitComponentSaveResponse.setSuccessFlag(false);
	            List errorParams = new ArrayList(2);
	            String obj = ex.getClass().getName();
	            errorParams.add(obj);
	            errorParams.add(obj.getClass().getName());
	            throw new ServiceException(
	                    " Exception occured in the doDoneOperations method  in BenefitComponentService",
	                    errorParams, ex); 
	        }
        }    
        return benefitComponentSaveResponse;
    }
    
    /** 
     * Benefit Component Reference data validation
     * 
     * @param BenefitHierarchyCreateRequest
     * @return
     * @throws ServiceException
     */
    private RefDataDomainValidationRequest refDataBenefitValidation(BenefitHierarchyCreateRequest request) throws ServiceException
    {
    	String structureType=request.getBenefitComponentBO().getComponentType();
        RefDataDomainValidationRequest refDataDomainValidationRequest=new RefDataDomainValidationRequest();
        BenefitComponentBO benefitComponentBO = new BenefitComponentBO();
        BenefitComponentBusinessObjectBuilder builder = new BenefitComponentBusinessObjectBuilder();
        List stateList = new ArrayList(1);
        
        HashMap selectedItemMap = new HashMap();
        List parentCatalogList = new ArrayList(1);
    	SubCatalogVO subCatalogVO = new SubCatalogVO();
    	subCatalogVO.setEntityId(request.getBenefitComponentBO().getBenefitComponentParentId());
    	subCatalogVO.setEntityType(BusinessConstants.BENEFIT_COMPONENT);

	    if(!(BusinessConstants.STANDARD).equalsIgnoreCase(structureType))
	        parentCatalogList.add(BusinessConstants.STATE_CODE);
	    benefitComponentBO.setBenefitComponentId(request.getBenefitComponentBO().getBenefitComponentParentId());
	    try{
	        benefitComponentBO = (BenefitComponentBO) builder.retrieveBenefitComponentById(benefitComponentBO);
	    }catch(SevereException e){
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing CheckInRequest";
            throw new ServiceException(logMessage, logParameters, e);
	    }
	    stateList.add(benefitComponentBO.getStateId());
	    refDataDomainValidationRequest.setParentCatalogList(parentCatalogList);
    	refDataDomainValidationRequest.setSubCatalogVO(subCatalogVO);
    	

    	if(!(BusinessConstants.STANDARD).equalsIgnoreCase(structureType)){
    	    selectedItemMap.put(BusinessConstants.STATE_CODE,stateList);
    	}
    	refDataDomainValidationRequest.setSelectedItemMap(selectedItemMap);
    	return refDataDomainValidationRequest;
    }
    
    /**
     * Benefit Component Reference Data Validation
     * 
     * @param BenefitComponentSaveRequest
     * @return
     * @throws ServiceException
     */
    private RefDataDomainValidationRequest refDataBenefitValidation(BenefitComponentSaveRequest request) throws ServiceException
    {
    	String structureType=request.getBenefitComponentVO().getComponentType();
        RefDataDomainValidationRequest refDataDomainValidationRequest=new RefDataDomainValidationRequest();
        BenefitComponentBO benefitComponentBO = new BenefitComponentBO();
        BenefitComponentBusinessObjectBuilder builder = new BenefitComponentBusinessObjectBuilder();
        List stateList = new ArrayList(1);
        List locateResults = new ArrayList(1);
        HashMap selectedItemMap = new HashMap();
        List parentCatalogList = new ArrayList(1);
    	SubCatalogVO subCatalogVO = new SubCatalogVO();
    	subCatalogVO.setEntityId(request.getBenefitComponentVO().getBenefitComponentParentId());
    	subCatalogVO.setEntityType(BusinessConstants.BENEFIT_COMPONENT);

	    if(!(BusinessConstants.STANDARD).equalsIgnoreCase(structureType))
	        parentCatalogList.add(BusinessConstants.STATE_CODE);
	    benefitComponentBO.setBenefitComponentId(request.getBenefitComponentVO().getBenefitComponentId());
	    try{
	        benefitComponentBO = (BenefitComponentBO) builder.retrieveBenefitComponentById(benefitComponentBO);
	    }catch(SevereException e){
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing CheckInRequest";
            throw new ServiceException(logMessage, logParameters, e);
	    }
	    stateList.add(benefitComponentBO.getStateId());
	    refDataDomainValidationRequest.setParentCatalogList(parentCatalogList);
    	refDataDomainValidationRequest.setSubCatalogVO(subCatalogVO);
    	

    	if(!(BusinessConstants.STANDARD).equalsIgnoreCase(structureType)){
    	    selectedItemMap.put(BusinessConstants.STATE_CODE,stateList);
    	}
    	refDataDomainValidationRequest.setSelectedItemMap(selectedItemMap);
    	return refDataDomainValidationRequest;
    }

    /**
     * Creates BenefitComponentBO from a BenefitComponentSaveRequest via a
     * BenefitComponentVO.
     * 
     * @param BenefitComponentSaveRequest
     * @return BenefitComponentBO
     */
    private BenefitComponentBO getBenefitComponentObject(
            BenefitComponentVO benefitComponentVO) {
        BenefitComponentBO benefitComponentBO = new BenefitComponentBO();

        //modifyDomainValues(benefitComponentVO);
        benefitComponentBO.setBenefitComponentId(benefitComponentVO
                .getBenefitComponentId());
        benefitComponentBO.setBenefitComponentParentId(benefitComponentVO
                .getBenefitComponentParentId());
        benefitComponentBO.setName(benefitComponentVO.getBenefitComponentName()
                .toUpperCase());
        benefitComponentBO.setBusinessEntity(benefitComponentVO
                .getBusinessEntityCodeList());
        benefitComponentBO.setBusinessGroup(benefitComponentVO
                .getBusinessGroupCodeList());
        benefitComponentBO.setMarketBusinessUnit(benefitComponentVO
        		.getMarketBusinessUnitCodeList());
        benefitComponentBO.setLineOfBusiness(benefitComponentVO
                .getLineOfBusinessCodeList());
        benefitComponentBO.setEffectiveDate(benefitComponentVO
                .getEffectivedate());
        benefitComponentBO.setExpiryDate(benefitComponentVO.getExpirydate());
        benefitComponentBO.setDescription(benefitComponentVO.getDescription()
                .toUpperCase().trim());
        benefitComponentBO.setStatus(benefitComponentVO.getStatus());
        benefitComponentBO.setVersion(benefitComponentVO.getVersion());

        benefitComponentBO.setComponentType(benefitComponentVO
                .getComponentType());
        benefitComponentBO.setMandateType(benefitComponentVO.getMandateType());
        benefitComponentBO.setStateDesc(benefitComponentVO.getStateDesc());
        benefitComponentBO.setStateId(benefitComponentVO.getStateId());
        benefitComponentBO.setRuleList(benefitComponentVO.getRuleIdList());
         
        if (null == benefitComponentVO.getBusinessDomainList()
                || benefitComponentVO.getBusinessDomainList().size() == 0) {
            List lineOfBusiness = benefitComponentVO
                    .getLineOfBusinessCodeList();
            List businessEntity = benefitComponentVO
                    .getBusinessEntityCodeList();
            List businessGroup = benefitComponentVO.getBusinessGroupCodeList();
            List marketBusinessUnit = benefitComponentVO.getMarketBusinessUnitCodeList();
            benefitComponentBO.setBusinessDomainList(BusinessUtil
                    .convertToDomains(lineOfBusiness, businessEntity,
                            businessGroup, marketBusinessUnit));
        } else
            benefitComponentBO.setBusinessDomainList(benefitComponentVO
                    .getBusinessDomainList());
        return benefitComponentBO;

    }


    /**
     * Creates BenefitComponentBO from a BenefitComponentSaveRequest via a
     * BenefitComponentVO.
     * 
     * @param BenefitComponentSaveRequest
     * @return BenefitComponentBO
     */
    private BenefitComponentBO getOldBenefitComponentBO(
            BenefitComponentSaveRequest benefitComponentSaveRequest) {
        BenefitComponentVO oldBenefitComponentVO = (BenefitComponentVO) benefitComponentSaveRequest
                .getOldBenefitComponentVO();
        BenefitComponentBO oldBenefitComponentBO = new BenefitComponentBO();

        //modifyDomainValues(benefitComponentVO);
        oldBenefitComponentBO.setBenefitComponentId(oldBenefitComponentVO
                .getBenefitComponentId());
        oldBenefitComponentBO.setBenefitComponentParentId(oldBenefitComponentVO
                .getBenefitComponentParentId());
        oldBenefitComponentBO.setName(oldBenefitComponentVO
                .getBenefitComponentName().toUpperCase());
        oldBenefitComponentBO.setVersion(oldBenefitComponentVO.getVersion());
        oldBenefitComponentBO.setBusinessDomainList(oldBenefitComponentVO
                .getBusinessDomainList());
        oldBenefitComponentBO.setEffectiveDate(oldBenefitComponentVO
                .getEffectivedate());
        oldBenefitComponentBO.setExpiryDate(oldBenefitComponentVO
                .getExpirydate());
        oldBenefitComponentBO.setComponentType(oldBenefitComponentVO
                .getComponentType());
        oldBenefitComponentBO.setMandateType(oldBenefitComponentVO
                .getMandateType());
        oldBenefitComponentBO.setStateId(oldBenefitComponentVO.getStateId());
        oldBenefitComponentBO
                .setRuleList(oldBenefitComponentVO.getRuleIdList());
        return oldBenefitComponentBO;

    }

    


    /**
     * Method to check is the Benefit Component Name or Domain changed.
     * 
     * @param oldBenefitComponentBO
     * @param benefitComponentBO
     * @return boolean
     */
    private boolean isKeyValueChanged(BenefitComponentBO oldBenefitComponentBO,
            BenefitComponentBO benefitComponentBO) {
        if (!benefitComponentBO.getName().equals(
                oldBenefitComponentBO.getName()))
            return true;
        if (!BusinessUtil.isBuisnessDomainsEqual(benefitComponentBO
                .getBusinessDomainList(), oldBenefitComponentBO
                .getBusinessDomainList()))
            return true;
        return false;
    }


    /**
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param BenefitComponentSearchRequest
     * @return
     * @throws WPDException
     */
    public WPDResponse execute(BenefitComponentSearchRequest request)
            throws ServiceException {
        Logger.logInfo("Inside Benefit Component Search Service");
        try {
            BenefitComponentSearchResponse benefitComponentSearchResponse = new BenefitComponentSearchResponse();
            BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory) ObjectFactory
                    .getFactory(ObjectFactory.BOM);
            BusinessObjectManager bom = bomf.getBusinessObjectManager();
            BenefitComponentLocateCriteria benefitComponentLocateCriteria = new BenefitComponentLocateCriteria();
            BenefitComponentLocateCriteriaVO benefitComponentLocateCriteriaVO = request
                    .getBenefitComponentLocateCriteriaVO();
            benefitComponentLocateCriteria
                    .setBusinessEntity(benefitComponentLocateCriteriaVO
                            .getBusinessEntity());
            benefitComponentLocateCriteria
                    .setBusinessGroup(benefitComponentLocateCriteriaVO
                            .getBusinessGroup());
            benefitComponentLocateCriteria
					.setMarketBusinessUnit(benefitComponentLocateCriteriaVO
							.getMarketBusinessUnit());
            benefitComponentLocateCriteria
                    .setLob(benefitComponentLocateCriteriaVO.getLob());
            benefitComponentLocateCriteria
                    .setEffeciveDate(benefitComponentLocateCriteriaVO
                            .getEffeciveDate());
            benefitComponentLocateCriteria
                    .setExpiryDate(benefitComponentLocateCriteriaVO
                            .getExpiryDate());
            benefitComponentLocateCriteria
                    .setName(BusinessUtil.escpeSpecialCharacters(benefitComponentLocateCriteriaVO.getName()));
            benefitComponentLocateCriteria
                    .setMaximumResultSize(benefitComponentLocateCriteriaVO
                            .getMaxSearchResultSize());
            benefitComponentLocateCriteria.setAction(BusinessConstants.BENEFIT_COMPONENT_LOCATE1);
            BenefitComponentBusinessObjectBuilder benefitComponentBusinessObjectBuilder = new BenefitComponentBusinessObjectBuilder();
            LocateResults locateResults = bom.locate(
                    benefitComponentLocateCriteria, request.getUser());
            /*
             * Checks if the result that is obtained after performing a search
             * is null or not
             */
            if (locateResults.getLocateResults().size() >= 50) {
                benefitComponentSearchResponse
                        .addMessage(new InformationalMessage(
                                BusinessConstants.MSG_SEARCH_RESULT_EXCEEDS));
            }
            benefitComponentSearchResponse
                    .setBenefitComponentList(locateResults.getLocateResults());
            return benefitComponentSearchResponse;

        } catch (WPDException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing BenefitComponentSearchRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }

    }


    /**
     * Creates benefitComponentBO from a BenefitComponentRetrieveRequest via a
     * BenefitComponentVO.
     * 
     * @param request
     * @return standardBenefitBO
     */
    private BenefitComponentBO getBenefitComponentObject(
            BenefitComponentRetrieveRequest request) {
        BenefitComponentBO benefitComponentBO = new BenefitComponentBO();
        benefitComponentBO.setBenefitComponentId(request
                .getBenefitComponentVO().getBenefitComponentId());
        benefitComponentBO.setName(request.getBenefitComponentVO()
                .getBenefitComponentName());
        benefitComponentBO.setBusinessDomainList(request
                .getBenefitComponentVO().getBusinessDomainList());
        benefitComponentBO.setAction(request.getBenefitComponentVO()
                .getAction());
        benefitComponentBO.setStatus(BusinessConstants.STATUS_BUILDING);
        benefitComponentBO.setVersion(request.getMainObjVersion());
        return benefitComponentBO;
    }


    /**
     * Method to search NonAdjBenefitMandate.
     * 
     * @param request
     * @return locateNonAdjBenefitMandateResponse
     * @throws WPDException
     *             setting the BenefitSystemId from
     *             LocateNonAdjBenefitMandateRequest to
     *             NonAdjBenefitMandateLocateCriteria Also set the locate
     *             results to locateNonAdjBenefitMandateResponse
     */
    public WPDResponse execute(LocateNonAdjBenefitMandateRequest request)
            throws ServiceException {
        try {
            Logger.logInfo("Inside Non Adjudicable Benefit Search Service");
            LocateNonAdjBenefitMandateResponse locateNonAdjBenefitMandateResponse = null;
            BusinessObjectManager bom = getBOM();
            NonAdjBenefitMandateLocateCriteria nonAdjBenefitMandateLocateCriteria = new NonAdjBenefitMandateLocateCriteria();
            locateNonAdjBenefitMandateResponse = (LocateNonAdjBenefitMandateResponse) WPDResponseFactory
                    .getResponse(WPDResponseFactory.LOCATE_NON_ADJ_BENEFIT_MANDATE_RESPONSE);
            nonAdjBenefitMandateLocateCriteria.setBenefitSystemId(request
                    .getBenefitSystemId());
            LocateResults locateResults = bom.locate(
                    nonAdjBenefitMandateLocateCriteria, request.getUser());
            if(null != locateResults){
            	locateNonAdjBenefitMandateResponse
                    .setBenefitMandateList(locateResults.getLocateResults());
            }
            return locateNonAdjBenefitMandateResponse;
        } catch (WPDException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing LocateNonAdjBenefitMandateRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
    }


    /**
     * Mehthod to check the validation of Benefit Component
     * 
     * @param BenefitComponentBO
     * @param User
     * @throws WPDException
     * @return boolean
     */
    private boolean isBenefitComponentValidated(
            BenefitComponentBO benefitComponentBO, User user)
            throws ServiceException {
        try {
            messageList = new ArrayList(1);
            BenefitHierarchyLocateCriteria criteria = new BenefitHierarchyLocateCriteria();
            criteria.setBenefitComponentId(benefitComponentBO
                    .getBenefitComponentId());
            BenefitComponentBusinessObjectBuilder benefitComponentBusinessObjectBuilder = new BenefitComponentBusinessObjectBuilder();
            LocateResults locateResult = benefitComponentBusinessObjectBuilder
                    .locate(criteria, user);
           
            List resultList = locateResult.getLocateResults();
            
            

            if (null != resultList && !resultList.isEmpty()) {
            	int size = resultList.size();
                int count = 0;
                for (int i = 0; i < size; i++) {
                    BenefitHierarchyAssociationBO benefitHierarchyAssociationBO = (BenefitHierarchyAssociationBO) resultList
                            .get(i);
                    criteria.setBenefitComponentId(benefitComponentBO
                            .getBenefitComponentId());
                    criteria
                            .setBenefitHierarchyId(benefitHierarchyAssociationBO
                                    .getBenefitHierarchyId());
                    List benefitList = benefitComponentBusinessObjectBuilder
                            .searchForBenefits(criteria); //FIXME performance check require //FIXME multiple time db hit, modify the search query so only a single query will do
                    if (benefitList.size() > 0) {
                        count++;
                    }
                }
                if (size != count) {
                    messageList
                            .add(new ErrorMessage(
                                    BusinessConstants.BENEFIT_HRCHY_SHOULD_HAVE_ATLEAST_ONE_BENEFIT));
                    return false;
                } else {
                    return true;
                }

            } 
            else {
                messageList
                        .add(new ErrorMessage(
                        		BusinessConstants.BENEFIT_HRCHY_SHOULD_HAVE_ATLEAST_ONE_BENEFIT_HRCHY));
                return false;
            }
        } catch (WPDException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(benefitComponentBO);
            String logMessage = "Failed while processing benefitComponentBO";
            throw new ServiceException(logMessage, logParameters, ex);

        }
    }


    /**
     * Execute method for BenefitComponentRetrieveRequest
     * 
     * @param request
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(BenefitComponentRetrieveRequest request)
            throws ServiceException {
        Logger.logInfo("Inside Benefit Component Retreive Service");
        try {
            BenefitComponentRetrieveResponse benefitComponentRetrieveResponse = null;
            HashMap params = new HashMap();
            BusinessObjectManager bom = getBOM();
            StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
            standardBenefitBO.setStandardBenefitKey(request
                    .getBenefitComponentVO().getStandardBenefitKey());
            BenefitComponentBO benefitComponentBO = getBenefitComponentObject(request);
            benefitComponentBO.setStandardBenefitBO(standardBenefitBO);
            params.put("subObjectName", "StandardBenefitBO");
            params.put("keyForRetrieve", new Integer(request
                    .getBenefitComponentVO().getStandardBenefitKey()));
            benefitComponentBO = (BenefitComponentBO) bom.retrieve(
                    benefitComponentBO, request.getUser(), params);

            benefitComponentRetrieveResponse = (BenefitComponentRetrieveResponse) WPDResponseFactory
                    .getResponse(WPDResponseFactory.RETRIEVE_BENEFIT_COMPONENT_VIEW_RESPONSE);
            benefitComponentRetrieveResponse.setDomainDetail(DomainUtil
                    .retrieveDomainDetailInfo("stdbenefit", benefitComponentBO
                            .getStandardBenefitBO().getParentSystemId()));
            benefitComponentRetrieveResponse
                    .setStandardBenefitBO(benefitComponentBO
                            .getStandardBenefitBO());
            benefitComponentRetrieveResponse
                    .setBenefitMandateBO(benefitComponentBO
                            .getBenefitMandateBO());
            return benefitComponentRetrieveResponse;
        } catch (WPDException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing BenefitComponentRetrieveRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
    }


    /**
     * Creates BusinessObjectManager object
     * 
     * @return BusinessObjectManager
     */
    public BusinessObjectManager getBOM() {
        BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory) ObjectFactory
                .getFactory(ObjectFactory.BOM);
        BusinessObjectManager bom = bomf.getBusinessObjectManager();
        return bom;
    }


    /**
     * This method is used to retrieve the benefitComponentDetails according to
     * the searched benefitComponentId
     * 
     * @param request
     * @return
     * @throws WPDException
     */
    public WPDResponse execute(BenefitComponentRetrieveRequestForEdit request)
            throws ServiceException {
        try {
            Logger.logInfo("Inside Benefit Component Retreive Service");
            boolean lockAquired = true;
            List messages = new ArrayList(1); 
            BenefitComponentRetrieveResponseForEdit responseForEdit = null;
            BusinessObjectManager bom = getBOM();
            BenefitComponentBO benefitComponentBO = new BenefitComponentBO();
            benefitComponentBO.setBenefitComponentId(request.getRetrieveKey());
            benefitComponentBO.setName(request.getRetrieveName());
            benefitComponentBO.setVersion(request.getVersionNumber());
            benefitComponentBO.setBusinessDomainList(request.getDomainList());
            
            responseForEdit = (BenefitComponentRetrieveResponseForEdit) WPDResponseFactory
            					.getResponse(WPDResponseFactory.BENEFIT_COMPONENT_RETRIEVE_FOR_EDIT);
            
            if(request.isEditMode()){
				lockAquired = bom.lock(benefitComponentBO,request.getUser());
			}
            responseForEdit.setLockAquired(lockAquired);
            if(lockAquired){
            	benefitComponentBO = (BenefitComponentBO) bom.retrieve(
                    benefitComponentBO, request.getUser());
            	responseForEdit.setBenefitComponentBO(benefitComponentBO);
            }
            else{
            	InformationalMessage message = new InformationalMessage(BusinessConstants.BC_LOCKED_USER);
            	message.setParameters(new String[] {request.getRetrieveName()});
            	messages.add(message);
				responseForEdit.setMessages(messages);
			}
            return responseForEdit;
        } catch (WPDException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing BenefitComponentRetrieveRequestForEdit";
            throw new ServiceException(logMessage, logParameters, ex);
        }
    }


    /**
     * Execute method for BenefitRetrieveRequest
     * 
     * @param benefitRetrieveRequest
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(BenefitRetrieveRequest benefitRetrieveRequest)
            throws ServiceException {
        try {
            Logger.logInfo("Inside Benefit Component Retreive Service");
            BenefitRetrieveResponse benefitRetrieveResponse = null;
            
            BusinessObjectManager bom = getBOM();
            BenefitLocateCriteria benefitLocateCriteria = new BenefitLocateCriteria();
            benefitLocateCriteria.setBenefitComponentId(benefitRetrieveRequest
                    .getBenefitComponentId());
            benefitLocateCriteria
                    .setLobList(BusinessUtil.getLobList(benefitRetrieveRequest
                            .getBusinessDomainList()));
            benefitLocateCriteria.setBusinessEntityList(BusinessUtil
                    .getbusinessEntityList(benefitRetrieveRequest
                            .getBusinessDomainList()));
            benefitLocateCriteria.setBusinessGroupList(BusinessUtil
                    .getBusinessGroupList(benefitRetrieveRequest
                            .getBusinessDomainList()));
            benefitLocateCriteria.setMarketBusinessUnit(BusinessUtil
                    .getMarketBusinessUnitList(benefitRetrieveRequest
                            .getBusinessDomainList()));
            LocateResults locateResults = bom.locate(benefitLocateCriteria,
                    benefitRetrieveRequest.getUser());
            if (null != locateResults.getLocateResults()) {
                copyBusinessObjectToValueObject(locateResults
                        .getLocateResults());
                benefitRetrieveResponse = (BenefitRetrieveResponse) WPDResponseFactory
                        .getResponse(WPDResponseFactory.RETRIEVE_BENEFIT__RESPONSE);
                benefitRetrieveResponse.setBenefits(locateResults
                        .getLocateResults());
            }
            return benefitRetrieveResponse;
        } catch (WPDException ex) {
        	Logger.logError(ex);
            List logParameters = new ArrayList(1);
            logParameters.add(benefitRetrieveRequest);
            String logMessage = "Failed while processing BenefitRetrieveRequest";
            throw new ServiceException(logMessage, logParameters, ex);

        }
    }


    /**
     * Method to set Benefit Component VO from Benefit Component BO
     * 
     * @param locateResults
     */
    private void copyBusinessObjectToValueObject(List locateResults) {
        List benefitVOsList = null;
        if (null != locateResults) {
            benefitVOsList = new ArrayList(1);
            for (int i = 0; i < locateResults.size(); i++) {
                BenefitBO benefitBO = (BenefitBO) locateResults.get(i);
                BenefitVO benefitVO = new BenefitVO();
                benefitVO.setBenefitName(benefitBO.getBenefitName());
                benefitVO.setBenefitId(benefitBO.getBenefitId());
                benefitVOsList.add(benefitVO);
            }
        }
    }


    /**
     * Execute method for benefitHierarchyCreateRequest
     * 
     * @param benefitHierarchyCreateRequest
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(
            BenefitHierarchyCreateRequest benefitHierarchyCreateRequest)
            throws ServiceException {
        Logger.logInfo("Inside Benefit Hierarchy Save Service");
        BenefitHierarchyResponse benefitHierarchyResponse = new BenefitHierarchyResponse();
        BenefitHierarchyVO benefitHierarchyVO = null;
        
//        List errorList = null;
        List messagesList = new ArrayList(1);
        try {
            HashMap params = new HashMap();
            BusinessObjectManager bom = getBOM();
            BenefitComponentBO benefitComponentBO = new BenefitComponentBO();
            benefitComponentBO
                    .setBenefitComponentId(benefitHierarchyCreateRequest
                            .getBenefitHierarchyVO().getBenefitComponentId());
            benefitComponentBO
                    .setBenefitComponentParentId(benefitHierarchyCreateRequest
                            .getBenefitHierarchyVO()
                            .getBenefitComponentParentId());
            benefitComponentBO
                    .setBusinessDomainList(benefitHierarchyCreateRequest
                            .getBenefitHierarchyVO().getBusinessDomainList());
            benefitComponentBO.setName(benefitHierarchyCreateRequest
                    .getBenefitHierarchyVO().getName());
            benefitComponentBO.setVersion(benefitHierarchyCreateRequest
                    .getBenefitHierarchyVO().getMasterVersion());
            benefitComponentBO
                    .setBusinessDomainList(benefitHierarchyCreateRequest
                            .getBenefitHierarchyVO().getBusinessDomainList());         
            
            if (null != benefitHierarchyCreateRequest.getBenefitHierarchyVO()
                    && null != benefitHierarchyCreateRequest
                            .getBenefitHierarchyVO()
                            .getBenefitHierarchiesList()) {
            	
            	BenefitHierarchyVO benefitHierarchyVO2 = null;
                BenefitHierarchyBO benefitHierarchyBO = copyVOToBO(benefitHierarchyCreateRequest
                        .getBenefitHierarchyVO());
                
                benefitHierarchyCreateRequest.setBenefitComponentBO(benefitComponentBO);
                benefitHierarchyResponse = (BenefitHierarchyResponse) new ValidationServiceController().execute(benefitHierarchyCreateRequest);
                
                //checking the maximum number of benefits flag
                if(benefitHierarchyResponse.isMaxFlag()){
	                if (bom.persist(benefitHierarchyBO, benefitComponentBO,
	                        benefitHierarchyCreateRequest.getUser(), true))
	                    benefitHierarchyVO = searchBenefitHierarchies(
	                            benefitHierarchyBO, 
								benefitHierarchyCreateRequest.getBenefitHierarchyVO().getBusinessDomainList(),
								benefitHierarchyCreateRequest.getUser());
	                if (null != benefitHierarchyVO ) {
	
	                    benefitHierarchyResponse
	                            .setBenefitHierarchyVO(benefitHierarchyVO); 
	                    messagesList.add(new InformationalMessage(
	                            WebConstants.BENEFIT_HIERARCHY_CREATE));
	                } else {
	
	                	messagesList.add(new ErrorMessage(
	                            WebConstants.BENEFIT_HIERARCHY_SEARCH_FAILURE));
	                }
                }
            }
            if (benefitHierarchyCreateRequest.isDoneFlag()) {
            	BenefitHierarchyVO benefitHierarchyVO2 = null;
            	
        		benefitHierarchyResponse = doDoneOperationsForHierarchy(benefitHierarchyCreateRequest, messagesList);
                if (benefitHierarchyResponse.isSuccessFlag()) {
                    if (!benefitHierarchyCreateRequest.isCheckInFlag()
                            && !benefitHierarchyCreateRequest.isNotesFlag()) {
                    	messagesList = new ArrayList(1);
                    	messagesList.add(new InformationalMessage(BusinessConstants.BENFIT_COMPONENT_VALIDATION_SUCCESS));
                    	messagesList.add(new InformationalMessage(WebConstants.BENEFIT_HIERARCHY_UPDATE));

//                            benefitHierarchyResponse.addMessage(new InformationalMessage(WebConstants.BENEFIT_HIERARCHY_UPDATE));
                    } else if (!benefitHierarchyCreateRequest.isCheckInFlag()
                            && benefitHierarchyCreateRequest.isNotesFlag()) {                        
                    	messagesList.add(new InformationalMessage(BusinessConstants.BENFIT_COMPONENT_VALIDATION_SUCCESS));
                    	messagesList.add(new InformationalMessage(WebConstants.BENEFIT_NOTES_UPDATE));
                    }
                }else{
                	if (!benefitHierarchyCreateRequest.isNotesFlag()) {
                    	//messagesList = new ArrayList();
                    	//messagesList.add(new InformationalMessage(BusinessConstants.BENFIT_COMPONENT_VALIDATION_SUCCESS));
                    	messagesList.add(new InformationalMessage(WebConstants.BENEFIT_HIERARCHY_UPDATE));

//                            benefitHierarchyResponse.addMessage(new InformationalMessage(WebConstants.BENEFIT_HIERARCHY_UPDATE));
                    } else if (benefitHierarchyCreateRequest.isNotesFlag()) {                        
                    	//messagesList.add(new InformationalMessage(BusinessConstants.BENFIT_COMPONENT_VALIDATION_SUCCESS));
                    	messagesList.add(new InformationalMessage(WebConstants.BENEFIT_NOTES_UPDATE));
                    }
                }
//            	messagesList.addAll(benefitHierarchyResponse.getMessages());
                BenefitHierarchyBO benefitHierarchyBO2 = copyVOToBO(benefitHierarchyCreateRequest
                        .getBenefitHierarchyVO());
                if (!benefitHierarchyCreateRequest.isNotesFlag()) {
                    benefitHierarchyVO2 = searchBenefitHierarchies(
                            benefitHierarchyBO2,
                            benefitHierarchyCreateRequest.getBenefitHierarchyVO().getBusinessDomainList(),
							benefitHierarchyCreateRequest.getUser());
                }
                if (null != benefitHierarchyVO2) {
                    benefitHierarchyResponse
                            .setBenefitHierarchyVO(benefitHierarchyVO2);
                }
            }
            Logger.logInfo("Inside Benefit Hierarchy Save Successful");
        } catch (Exception ex) {
            List logParameters = new ArrayList(1);
            String logMessage = "Failed while processing searchBenefit";
            throw new ServiceException(logMessage, logParameters, ex);
        }     
        if(null != benefitHierarchyResponse.getMessages() && benefitHierarchyResponse.getMessages().size() > 0)
        	messagesList.addAll(benefitHierarchyResponse.getMessages());      	
        benefitHierarchyResponse.setMessages(messagesList);
        return benefitHierarchyResponse;
    }


    /**
     * Method to search benefit hierarchies
     * 
     * @param benefitHierarchyBO
     * @param user
     * @return
     * @throws ServiceException
     */
    private BenefitHierarchyVO searchBenefitHierarchies(
            BenefitHierarchyBO benefitHierarchyBO, List businessDomainList, User user)
            throws ServiceException {
        Logger.logInfo("Inside Benefit Hierarchy Search Service");
        BenefitHierarchyBO benefitHierarchyBO2 = null;
        BenefitHierarchyVO benefitHierarchyVO = null;
        try {
            BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory) ObjectFactory
                    .getFactory(ObjectFactory.BOM);
            BusinessObjectManager bom = bomf.getBusinessObjectManager();
            List benefitSearchResultsList = null;
            LocateResults locateResults = null;
            BenefitHierarchyLocateCriteria benefitHierarchyLocateCriteria = new BenefitHierarchyLocateCriteria();
            
            benefitHierarchyLocateCriteria.setBenefitComponentId(benefitHierarchyBO.getBenefitComponentId());            
            benefitHierarchyLocateCriteria.setLobList(BusinessUtil.getLobList(businessDomainList));
            benefitHierarchyLocateCriteria.setBusinessEntityList(BusinessUtil.getbusinessEntityList(businessDomainList));
            benefitHierarchyLocateCriteria.setBusinessGroupList(BusinessUtil.getBusinessGroupList(businessDomainList));
            benefitHierarchyLocateCriteria.setMarketBusinessUnitList(BusinessUtil.getMarketBusinessUnitList(businessDomainList));
            benefitHierarchyLocateCriteria.setValidationFlag(false);
            locateResults = bom.locate(benefitHierarchyLocateCriteria, user);
            int searchResultCount = locateResults.getTotalResultsCount();
            benefitSearchResultsList = locateResults.getLocateResults();
            
            
            if (null != benefitSearchResultsList && benefitSearchResultsList.size() > 0) {            

                benefitHierarchyBO2 = new BenefitHierarchyBO();
                benefitHierarchyBO2.setBenefitHierarchyId(benefitHierarchyBO.getBenefitHierarchyId());
                benefitHierarchyBO2.setBenefitHierarchiesList(benefitSearchResultsList);
                benefitHierarchyVO = copyBusinessObjectToValueObject(benefitHierarchyBO2);
            }
        } catch (Exception ex) {
            List logParameters = new ArrayList(1);
            String logMessage = "Failed while processing searchBenefit";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        return benefitHierarchyVO;
    }


    /**
     * Method to copy Benefit Component BO to VO
     * 
     * @param benefitHierarchyBO
     * @return benefitHierarchyVO
     */
    private BenefitHierarchyVO copyBusinessObjectToValueObject(
            BenefitHierarchyBO benefitHierarchyBO) {
        BenefitHierarchyVO benefitHierarchyVO = null;
        List benefitHierarchyVOsList = null;
        if (null != benefitHierarchyBO) {
            benefitHierarchyVO = new BenefitHierarchyVO();
            List benefitHierarchyBOsList = benefitHierarchyBO
                    .getBenefitHierarchiesList();
            benefitHierarchyVOsList=new ArrayList(benefitHierarchyBOsList==null?0:benefitHierarchyBOsList.size());
            if (null != benefitHierarchyBOsList
                    && !benefitHierarchyBOsList.isEmpty()) {
                for (int i = 0; i < benefitHierarchyBOsList.size(); i++) {
                    BenefitHierarchyAssociationBO benefitHierarchyAssociationBO = (BenefitHierarchyAssociationBO) benefitHierarchyBOsList
                            .get(i);
                    BenefitHierarchyAssociationVO benefitHierarchyAssociationVO = new BenefitHierarchyAssociationVO();
                    benefitHierarchyAssociationVO
                            .setBenefitComponentId(benefitHierarchyAssociationBO
                                    .getBenefitComponentId());
                    benefitHierarchyAssociationVO
                            .setBenefitHierarchyAssociationId(benefitHierarchyAssociationBO
                                    .getBenefitHierarchyAssociationId());
                    benefitHierarchyAssociationVO
                            .setBenefitHierarchyId(benefitHierarchyAssociationBO
                                    .getBenefitHierarchyId());
                    benefitHierarchyAssociationVO
                            .setBenefitId(benefitHierarchyAssociationBO
                                    .getBenefitId());
                    benefitHierarchyAssociationVO
                            .setBenefitName(benefitHierarchyAssociationBO
                                    .getBenefitName());
                    benefitHierarchyAssociationVO
                            .setSequenceNumber(benefitHierarchyAssociationBO
                                    .getSequenceNumber());
                    benefitHierarchyAssociationVO.setValidBenefit(benefitHierarchyAssociationBO.getBenefitValid());
                    benefitHierarchyVO
                            .setBenefitHierarchyId(benefitHierarchyAssociationBO
                                    .getBenefitHierarchyId());
                    benefitHierarchyVOsList.add(benefitHierarchyAssociationVO);
                }
            }
        }
        benefitHierarchyVO.setBenefitComponentId(benefitHierarchyBO
                .getBenefitComponentId());
        benefitHierarchyVO.setBenefitHierarchiesList(benefitHierarchyVOsList);
        return benefitHierarchyVO;
    }


    /**
     * Method to copy BenefitHierarchyVO to BenefitHierarchyBO
     * 
     * @param benefitsList
     * @return BenefitHierarchyBO
     */
    private BenefitHierarchyBO copyVOToBO(BenefitHierarchyVO benefitHierarchyVO) {
        BenefitHierarchyBO benefitHierarchyBO = new BenefitHierarchyBO();
        List benefitBOsList = null;
        if (null != benefitHierarchyVO.getBenefitHierarchiesList()) {
            benefitBOsList = new ArrayList(benefitHierarchyVO.getBenefitHierarchiesList().size());
            for (int i = 0; i < benefitHierarchyVO.getBenefitHierarchiesList()
                    .size(); i++) {
                BenefitHierarchyAssociationVO benefitHierarchyAssociationVO = (BenefitHierarchyAssociationVO) benefitHierarchyVO
                        .getBenefitHierarchiesList().get(i);
                BenefitHierarchyAssociationBO benefitHierarchyAssociationBO = new BenefitHierarchyAssociationBO();
                benefitHierarchyAssociationBO
                        .setBenefitName(benefitHierarchyAssociationVO
                                .getBenefitName());
                benefitHierarchyAssociationBO
                        .setBenefitId(benefitHierarchyAssociationVO
                                .getBenefitId());
                benefitHierarchyAssociationBO
                        .setBenefitComponentId(benefitHierarchyVO
                                .getBenefitComponentId());
                benefitHierarchyAssociationBO
                        .setBenefitHierarchyAssociationId(benefitHierarchyAssociationVO
                                .getBenefitHierarchyAssociationId());
                benefitHierarchyAssociationBO
                        .setBenefitHierarchyId(benefitHierarchyAssociationVO
                                .getBenefitHierarchyId());
                benefitHierarchyAssociationBO
                        .setSequenceNumber(benefitHierarchyAssociationVO
                                .getSequenceNumber());
                benefitBOsList.add(benefitHierarchyAssociationBO);
            }
        }
        benefitHierarchyBO.setBenefitComponentId(benefitHierarchyVO
                .getBenefitComponentId());
        benefitHierarchyBO.setBenefitHierarchyId(benefitHierarchyVO
                .getBenefitHierarchyId());
        benefitHierarchyBO.setBenefitHierarchiesList(benefitBOsList);
        return benefitHierarchyBO;
    }


    /**
     * Method to view Benefit Component
     * 
     * @param request
     * @return benefitComponentViewResponse
     * @throws WPDException
     */
    public WPDResponse execute(BenefitComponentViewRequest request)
            throws ServiceException {
        try {
            Logger.logInfo("Inside Benefit Component View Service");
            BenefitComponentViewResponse benefitComponentViewResponse = null;
            BusinessObjectManager bom = getBOM();
            BenefitComponentBO benefitComponentBO = getBenefitComponentViewObject(request);
            benefitComponentBO = (BenefitComponentBO) bom.retrieve(
                    benefitComponentBO, request.getUser());
            benefitComponentViewResponse = (BenefitComponentViewResponse) WPDResponseFactory
                    .getResponse(WPDResponseFactory.RETRIEVE_BENEFIT_VIEW_RESPONSE);
            if (null != benefitComponentViewResponse) {
                benefitComponentViewResponse
                        .setBenefitComponentBO(benefitComponentBO);
            }
            return benefitComponentViewResponse;
        } catch (WPDException ex) {
        	Logger.logError(ex);
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing BenefitComponentViewRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
    }


    /**
     * Method to get the Benefit Component view object
     * 
     * @param request
     * @return benefitComponentBO
     */
    public BenefitComponentBO getBenefitComponentViewObject(
            BenefitComponentViewRequest request) {
        BenefitComponentBO benefitComponentBO = new BenefitComponentBO();
        benefitComponentBO.setBenefitComponentId(request
                .getBenefitComponentVO().getBenefitComponentId());
        benefitComponentBO.setName(request.getBenefitComponentVO()
                .getBenefitComponentName());
        benefitComponentBO.setVersion(request.getBenefitComponentVO()
                .getVersion());
        benefitComponentBO.setBusinessDomainList(request
                .getBenefitComponentVO().getBusinessDomainList());
        return benefitComponentBO;

    }


    /**
     * This method is used to get the benefit definitions list from the table
     * 
     * @param request
     * @return
     * @throws WPDException
     */

    //**Benefit Level/Line hide
    
    public WPDResponse execute(LocateComponentsBenefitDefinitionRequest request)
    throws ServiceException {
    	try {
    		
		    Logger.logInfo("Inside Benefit Definition Search Service");
		    LocateComponentsBenefitDefinitionResponse response = null;
		    BusinessObjectManager bom = getBOM();
		    ComponentsBenefitDefinitionLocateCriteria locateCriteria = new ComponentsBenefitDefinitionLocateCriteria();
		    locateCriteria.setBenefitId(request.getBenefitId());
		    locateCriteria.setBenefitComponentId(request
		            .getBenefitComponentId());
		    //** Benefit Level/line hide
		    if(request.isShowHidden()){
		    	locateCriteria.setShowHidden(request.isShowHidden());
		    }
		    //** End
		    response = (LocateComponentsBenefitDefinitionResponse) WPDResponseFactory
		            .getResponse(WPDResponseFactory.LOCATE_COMPONENTS_BENEFIT_DEFINITION_RESPONSE);
		    LocateResults locateResults = bom.locate(locateCriteria, request
		            .getUser());
		    response
		            .setBenefitDefinitionsList(locateResults.getLocateResults());//null check
		    
		    return response;
		} catch (WPDException ex) {
		    List logParameters = new ArrayList(1);
		    logParameters.add(request);
		    String logMessage = "Failed while processing LocateComponentsBenefitDefinitionRequest";
		    throw new ServiceException(logMessage, logParameters, ex);
		}
}
    


    /**
     * Method to update Benefit Line
     * 
     * @param request
     * @return UpdateBenefitLinesResponse
     * @throws WPDException
     */
    public WPDResponse execute(UpdateBenefitLinesRequest request)
            throws ServiceException {
        Logger.logInfo("Inside Update Benefit Lines Service");
        UpdateBenefitLinesResponse response = null;
        BusinessObjectManager bom = getBOM();
        BenefitComponentBO benefitComponentBO = new BenefitComponentBO();
        benefitComponentBO.setBenefitComponentId(request.getMainObjectKey());
        benefitComponentBO.setName(request.getMainObjectIdentifier());
        benefitComponentBO.setVersion(request.getVersionNumber());
        benefitComponentBO.setBusinessDomainList(request.getDomainList());
        List updatedBenefitLinesList = request.getUpdatedBenefitLines();
        BenefitLevel benefitLevelBO = new BenefitLevel();
        benefitLevelBO.setBenefitLines(updatedBenefitLinesList);

        try {
            bom.persist(benefitLevelBO, benefitComponentBO, request.getUser(),
                    false);
        } catch (SevereException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "SevereException while processing UpdateBenefitLinesRequest";
            throw new ServiceException(logMessage, logParameters, e);
        } catch (WPDException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing UpdateBenefitLinesRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }

        response = (UpdateBenefitLinesResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.UPDATE_BENEFIT_LINES_RESPONSE);
        response.addMessage(new InformationalMessage(
                BusinessConstants.BENEFIT_LINES_VALUES_OVERRIDE_SUCCESS));
        
        return response;
    }


    /**
     * Execute method for benefitHierarchyUpdateRequest
     * 
     * @param benefitHierarchyUpdateRequest
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(
            BenefitHierarchyUpdateRequest benefitHierarchyUpdateRequest)
            throws ServiceException {
        Logger.logInfo("Inside Update Benefit Hierarchy Service");
        BenefitHierarchyUpdateResponse benefitHierarchyUpdateResponse = null;
        BenefitHierarchyVO benefitHierarchyVO2 = null;
        List updatedBenefitBOsList = null;
        
        try {
            BenefitComponentBO benefitComponentBO = new BenefitComponentBO();
            benefitComponentBO
                    .setBenefitComponentId(benefitHierarchyUpdateRequest
                            .getBenefitHierarchyVO().getBenefitComponentId());
            benefitComponentBO
                    .setBenefitComponentParentId(benefitHierarchyUpdateRequest
                            .getBenefitHierarchyVO()
                            .getBenefitComponentParentId());
            benefitComponentBO.setName(benefitHierarchyUpdateRequest
                    .getBenefitHierarchyVO().getName());
            benefitComponentBO.setVersion(benefitHierarchyUpdateRequest
                    .getBenefitHierarchyVO().getMasterVersion());
            benefitComponentBO
                    .setBusinessDomainList(benefitHierarchyUpdateRequest
                            .getBenefitHierarchyVO().getBusinessDomainList());
            HashMap params = new HashMap();
            BusinessObjectManager bom = getBOM();
            BenefitHierarchyBO benefitHierarchyBO = copyVOToBO(benefitHierarchyUpdateRequest
                    .getBenefitHierarchyVO());
            if (bom.persist(benefitHierarchyBO, benefitComponentBO,
                    benefitHierarchyUpdateRequest.getUser(), false))
                benefitHierarchyVO2 = searchBenefitHierarchies(
                        benefitHierarchyBO,
						benefitHierarchyUpdateRequest.getBenefitHierarchyVO().getBusinessDomainList(),
						benefitHierarchyUpdateRequest.getUser());
            if (null != benefitHierarchyVO2) {
                benefitHierarchyUpdateResponse = new BenefitHierarchyUpdateResponse();
                benefitHierarchyUpdateResponse
                        .setBenefitHierarchyVO(benefitHierarchyVO2);
                List messagesList = null;
                messagesList = new ArrayList(1);
                messagesList.add(new InformationalMessage(
                        WebConstants.BENEFIT_HIERARCHY_UPDATE));
                benefitHierarchyUpdateResponse.setMessages(messagesList);
            } else {
            	List errorList = null;
                errorList = new ArrayList(1);
                benefitHierarchyUpdateResponse = new BenefitHierarchyUpdateResponse();
                errorList.add(new ErrorMessage(
                        WebConstants.BENEFIT_HIERARCHY_SEARCH_FAILURE));
                benefitHierarchyUpdateResponse.setMessages(errorList);
            }
        } catch (Exception ex) {
            List logParameters = new ArrayList(1);
            String logMessage = "Failed while processing searchBenefit";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        return benefitHierarchyUpdateResponse;
    }


    /**
     * Benefit hierarchy create
     * 
     * @param benefitHierarchyCreateRequest
     * @return BenefitHierarchyResponse
     * @throws ServiceException
     */
    private BenefitHierarchyResponse doDoneOperationsForHierarchy(
            BenefitHierarchyCreateRequest benefitHierarchyCreateRequest, List messagesList)
            throws ServiceException {
        BenefitHierarchyResponse benefitHierarchyResponse = null;
        
        benefitHierarchyResponse = (BenefitHierarchyResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.BENEFIT_HIERARCHY_RESPONSE);
        BenefitComponentBO benefitComponentBO = new BenefitComponentBO();
        
        BenefitHierarchyVO benefitHierarchyVO = benefitHierarchyCreateRequest.getBenefitHierarchyVO();
        benefitComponentBO.setBenefitComponentId(benefitHierarchyVO.getBenefitComponentId());
        benefitComponentBO
                .setBenefitComponentParentId(benefitHierarchyVO.getBenefitComponentParentId());
        benefitComponentBO.setName(benefitHierarchyVO.getName());
        benefitComponentBO.setVersion(benefitHierarchyVO.getMasterVersion());
        benefitComponentBO.setBusinessDomainList(benefitHierarchyVO.getBusinessDomainList());
        benefitComponentBO.setStatus(benefitHierarchyVO.getStatus());
        benefitHierarchyCreateRequest.setBenefitComponentBO(benefitComponentBO);
        benefitHierarchyResponse = (BenefitHierarchyResponse) (new ValidationServiceController()
                .execute(benefitHierarchyCreateRequest));
        
        if (benefitHierarchyResponse.isSuccessFlag()){
        	
	        
	        
	        try {
	            if (benefitHierarchyResponse.isSuccessFlag()
	                    && benefitHierarchyCreateRequest.isCheckInFlag()) {
	                // reference data validations
	                RefDataDomainValidationRequest refDataDomainValidationRequest = refDataBenefitValidation(benefitHierarchyCreateRequest);
	                RefDataDomainValidationResponse refDataDomainValidationResponse = new RefDataDomainValidationResponse();
	                refDataDomainValidationResponse=(RefDataDomainValidationResponse) new ValidationServiceController().execute(refDataDomainValidationRequest);
	            	    	
	            	if(!refDataDomainValidationResponse.isSuccess()){
	            	    ErrorMessage errorMessage = new ErrorMessage(WebConstants.REFERENCE_DATA_VALIDATION_FAILURE);
	            	    errorMessage.setParameters (new String[] {" "+refDataDomainValidationResponse.getErrorMessage()} );
	            	    messagesList.add(errorMessage);
	            	    benefitHierarchyCreateRequest.setCheckInFlag(false);
	            	}
	            	if(refDataDomainValidationResponse.isSuccess()){
	            		BusinessObjectManager bom = getBOM();
		                if (bom.checkIn(benefitComponentBO,
		                        benefitHierarchyCreateRequest.getUser())) {
		                   	InformationalMessage message = new InformationalMessage(WebConstants.BENEFIT_COMPONENT_CHECKIN_SUCCESS);
		                	message.setParameters(new String[] {benefitHierarchyCreateRequest.getBenefitComponentBO().getName()});
		                	//List messageList = new ArrayList();
		                	messagesList.add(message);
		                    benefitHierarchyResponse.setSuccessFlag(true);
		                } else {
		                	messagesList.add(new ErrorMessage(
		                    		WebConstants.BENEFIT_COMPONENT_CHECKIN_FAILURE));
		                    benefitHierarchyResponse.setSuccessFlag(false);
		                }
	            	}
	            }
	        } catch (WPDException e) {
	            benefitHierarchyResponse.setSuccessFlag(false);
	
	            List logParameters = new ArrayList(1);
	            logParameters.add(benefitHierarchyCreateRequest);
	            String logMessage = "Failed while processing doDoneOperationsForHierarchy in benefitComponentService";
	            throw new ServiceException(logMessage, logParameters, e);
	        }
        }
        return benefitHierarchyResponse;
    }


    /**
     * Execute method for benefitHierarchyDeleteRequest
     * 
     * @param benefitHierarchyDeleteRequest
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(
            BenefitHierarchyDeleteRequest benefitHierarchyDeleteRequest)
            throws ServiceException {
        Logger.logInfo("Inside Delete Benefit Hierarchy Service");
        BenefitHierarchyDeleteResponse benefitHierarchyDeleteResponse = null;
        BenefitHierarchyVO benefitHierarchyVO2 = null;
        List errorList = null;
        List messagesList = null;
        boolean isDeleted = false;
        try {
            HashMap params = new HashMap();
            BusinessObjectManager bom = getBOM();
            BenefitComponentBO benefitComponentBO = new BenefitComponentBO();
            benefitComponentBO
                    .setBenefitComponentId(benefitHierarchyDeleteRequest
                            .getBenefitHierarchyVO().getBenefitComponentId());
            benefitComponentBO
                    .setBenefitComponentParentId(benefitHierarchyDeleteRequest
                            .getBenefitHierarchyVO()
                            .getBenefitComponentParentId());
      
            benefitComponentBO.setName(benefitHierarchyDeleteRequest
                    .getBenefitHierarchyVO().getName());
            benefitComponentBO.setVersion(benefitHierarchyDeleteRequest
                    .getBenefitHierarchyVO().getMasterVersion());
            benefitComponentBO
                    .setBusinessDomainList(benefitHierarchyDeleteRequest
                            .getBenefitHierarchyVO().getBusinessDomainList());
            BenefitHierarchyBO benefitHierarchyBO = copyVOToBOForDelete(benefitHierarchyDeleteRequest
                    .getBenefitHierarchyVO());
            isDeleted = bom.delete(benefitHierarchyBO, benefitComponentBO,
                    benefitHierarchyDeleteRequest.getUser());
            if (isDeleted) {
                benefitHierarchyVO2 = searchBenefitHierarchies(
                        benefitHierarchyBO, 
                        benefitHierarchyDeleteRequest.getBenefitHierarchyVO().getBusinessDomainList(),
						benefitHierarchyDeleteRequest.getUser());
            }
            
            benefitHierarchyDeleteResponse = new BenefitHierarchyDeleteResponse();
            if (null != benefitHierarchyVO2) {

                benefitHierarchyDeleteResponse
                        .setBenefitHierarchyVO(benefitHierarchyVO2);
                messagesList = new ArrayList(1);
                messagesList.add(new InformationalMessage(
                        WebConstants.BENEFIT_HIERARCHY_DELETE));
                benefitHierarchyDeleteResponse.setMessages(messagesList);
            } else {
                
                messagesList = new ArrayList(1);
                messagesList.add(new InformationalMessage(
                        WebConstants.BENEFIT_HIERARCHY_DELETE));
                benefitHierarchyDeleteResponse.setMessages(messagesList);
            }
        } catch (Exception ex) {
            List logParameters = new ArrayList(1);
            String logMessage = "Failed while processing searchBenefit";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        return benefitHierarchyDeleteResponse;
    }


    /**
     * Method to copy Benefit Hierarchy VO to BO for Benefit Hierarchy delete.
     * 
     * @param benefitHierarchyAssociationVO
     * @return benefitHierarchyBO
     */
    private BenefitHierarchyBO copyVOToBOForDelete(
            BenefitHierarchyVO benefitHierarchyVO) {
        BenefitHierarchyBO benefitHierarchyBO = new BenefitHierarchyBO();
        benefitHierarchyBO.setBenefitHierarchyId(benefitHierarchyVO
                .getBenefitHierarchyId());
        benefitHierarchyBO.setBenefitComponentId(benefitHierarchyVO
                .getBenefitComponentId());
        benefitHierarchyBO.setBenefitHierarchyAssociationId(benefitHierarchyVO
                .getBenefitHierarchyAssociationId());
        // changed Nov 26th
        benefitHierarchyBO.setBenefitId(benefitHierarchyVO.getBenefitId());
        // change ends
        return benefitHierarchyBO;
    }


    /**
     * Execute method for benefitHierarchySearchRequest
     * 
     * @param benefitHierarchySearchRequest
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(
            BenefitHierarchySearchRequest benefitHierarchySearchRequest)
            throws ServiceException {
        Logger.logInfo("Inside Benefit Hierarchy Search Service");
        BenefitHierarchySearchResponse benefitHierarchySearchResponse = null;
        BenefitHierarchyVO benefitHierarchyVO2 = null;
        List errorList = null;
        try {
            BenefitHierarchyBO benefitHierarchyBO = copyVOToBOForDelete(benefitHierarchySearchRequest
                    .getBenefitHierarchyVO());
            if(benefitHierarchySearchRequest.getAction().equals("benefitHierarchy")){
                benefitHierarchyVO2 = searchBenefitHierarchies(benefitHierarchyBO,
                		benefitHierarchySearchRequest.getBenefitHierarchyVO().getBusinessDomainList(),
                        benefitHierarchySearchRequest.getUser());
                benefitHierarchySearchResponse = new BenefitHierarchySearchResponse();
                if (null != benefitHierarchyVO2) {
                    
                    benefitHierarchySearchResponse
                            .setBenefitHierarchyVO(benefitHierarchyVO2);
                } else {
                	
                    errorList = new ArrayList(1);
                    benefitHierarchySearchResponse = new BenefitHierarchySearchResponse();
                    errorList.add(new ErrorMessage(
                            WebConstants.BENEFIT_HIERARCHY_SEARCH_FAILURE));
                    benefitHierarchySearchResponse.setMessages(errorList);
                }
            }else{
            	benefitHierarchyVO2 = searchBenefitsForPrintAndView(benefitHierarchyBO, benefitHierarchySearchRequest.getUser());
                benefitHierarchySearchResponse = new BenefitHierarchySearchResponse();
            	benefitHierarchySearchResponse
                		.setBenefitHierarchyVO(benefitHierarchyVO2);
            }
        } catch (Exception ex) {
            List logParameters = new ArrayList(1);
            String logMessage = "Failed while processing searchBenefit";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        return benefitHierarchySearchResponse;
    }


    /**
	 * @param benefitHierarchyBO
	 * @param user
	 * @return BenefitHierarchyVO
	 */
	private BenefitHierarchyVO searchBenefitsForPrintAndView(BenefitHierarchyBO benefitHierarchyBO, User user) throws ServiceException { 
		
        Logger.logInfo("Inside Benefit Hierarchy Search Service");
        BenefitHierarchyBO benefitHierarchyBO2 = null;
        BenefitHierarchyVO benefitHierarchyVO = null;
        try {
            BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory) ObjectFactory
                    .getFactory(ObjectFactory.BOM);
            BusinessObjectManager bom = bomf.getBusinessObjectManager();
            List benefitSearchResultsList = null;
            LocateResults locateResults = null;
            com.wellpoint.wpd.business.benefitcomponent.locatecriteria.BenefitComponentLocateCriteria benefitComponentLocateCriteria = new com.wellpoint.wpd.business.benefitcomponent.locatecriteria.BenefitComponentLocateCriteria();
            benefitComponentLocateCriteria.setBenefitComponentId(benefitHierarchyBO.getBenefitComponentId());

            locateResults = bom.locate(benefitComponentLocateCriteria, user);
            int searchResultCount = locateResults.getTotalResultsCount();
            benefitSearchResultsList = locateResults.getLocateResults();
            
            if (null != benefitSearchResultsList && benefitSearchResultsList.size() > 0) {            

                benefitHierarchyBO2 = new BenefitHierarchyBO();
                benefitHierarchyBO2.setBenefitHierarchyId(benefitHierarchyBO.getBenefitHierarchyId());
                benefitHierarchyBO2.setBenefitHierarchiesList(benefitSearchResultsList);
                benefitHierarchyVO = copyBusinessObjectToValueObject(benefitHierarchyBO2);
            }
        } catch (Exception ex) {
            List logParameters = new ArrayList(1);
            String logMessage = "Failed while processing searchBenefit";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        return benefitHierarchyVO;

	}


	/**
     * Method to modify the domain values
     * 
     * @param benefitComponentVO
     */
    private void modifyDomainValues(BenefitComponentVO benefitComponentVO) {
        Iterator iter = null;
        List lineOfBusiness = benefitComponentVO.getLineOfBusinessCodeList();
        List businessEntity = benefitComponentVO.getBusinessEntityCodeList();
        List businessGroup = benefitComponentVO.getBusinessGroupCodeList();
        List marketBusinessUnit = benefitComponentVO.getMarketBusinessUnitCodeList();
        String item = null;
        for (iter = lineOfBusiness.iterator(); iter.hasNext();) {
            item = (String) iter.next();
            if (BusinessConstants.UNIVERSAL_DOMAIN.equals(item)) {
                lineOfBusiness.clear();
                lineOfBusiness.add(BusinessConstants.UNIVERSAL_DOMAIN);
                break;
            }
        }
        for (iter = businessEntity.iterator(); iter.hasNext();) {
            item = (String) iter.next();
            if (BusinessConstants.UNIVERSAL_DOMAIN.equals(item)) {
                businessEntity.clear();
                businessEntity.add(BusinessConstants.UNIVERSAL_DOMAIN);
                break;
            }
        }
        for (iter = businessGroup.iterator(); iter.hasNext();) {
            item = (String) iter.next();
            if (BusinessConstants.UNIVERSAL_DOMAIN.equals(item)) {
                businessGroup.clear();
                businessGroup.add(BusinessConstants.UNIVERSAL_DOMAIN);
                break;
            }
        }
        for (iter = marketBusinessUnit.iterator(); iter.hasNext();) {
            item = (String) iter.next();
            if (BusinessConstants.UNIVERSAL_DOMAIN.equals(item)) {
            	marketBusinessUnit.clear();
            	marketBusinessUnit.add(BusinessConstants.UNIVERSAL_DOMAIN);
                break;
            }
        }
    }


    /**
     * Method to delete Benefit Component
     * 
     * @param BenefitComponentDeleteRequest
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(BenefitComponentDeleteRequest request)
            throws ServiceException {
        try {
        	List messageList = new ArrayList(1);        		
            Logger.logInfo("Inside Delete Benefit Component Service");
            BenefitComponentDeleteResponse benefitComponentDeleteResponse = null;
            BenefitComponentBO benefitComponentBO = new BenefitComponentBO();

            benefitComponentBO.setBenefitComponentId(request
                    .getBenefitComponentKey());
            benefitComponentBO.setName(request.getBenefitComponentName());
            benefitComponentBO.setVersion(request.getBenefitComponentVersion());
            benefitComponentBO.setBusinessDomainList(request.getDomainList());

            BusinessObjectManager bom = getBOM();

            benefitComponentDeleteResponse = (BenefitComponentDeleteResponse) WPDResponseFactory
                    .getResponse(WPDResponseFactory.DELETE_BENEFIT_COMPONENT_RESPONSE);
            // Call the delete method of the BOM
            boolean result = bom.delete(benefitComponentBO, request.getUser());
            if (result) {
            	InformationalMessage message = new InformationalMessage(WebConstants.BENEFIT_COMPONENT_DELETE_SUCCESS);
            	 message.setParameters(new String[] {request.getBenefitComponentName()});
            	 messageList.add(message);
            	 benefitComponentDeleteResponse.setMessages(messageList);

                Logger.logInfo("Benefit Component Deleted Succesfully");

            } else {
            	ErrorMessage message = new ErrorMessage(WebConstants.BENEFIT_COMPONENT_DELETE_FAILURE);
            	message.setParameters(new String[] {request.getBenefitComponentName()});
            	 messageList.add(message);
            	 benefitComponentDeleteResponse.setMessages(messageList);
            	 

            }
            return benefitComponentDeleteResponse;
        } catch (WPDException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing BenefitComponentDeleteRequest";
            throw new ServiceException(logMessage, logParameters, ex);

        }
    }


    /**
     * This method is used to get the benefit definitions list from the table
     * 
     * @param LocateComponentsBenefitAdministrationRequest
     * @return
     * @throws WPDException
     */
    public WPDResponse execute(
            LocateComponentsBenefitAdministrationRequest request)
            throws ServiceException {
        try {
            Logger.logInfo("Inside  Benefit Administration Search Service");
            LocateComponentsBenefitAdministrationResponse response = null;
            BusinessObjectManager bom = getBOM();
            ComponentsBenefitAdministrationLocateCriteria locateCriteria = new ComponentsBenefitAdministrationLocateCriteria();
            locateCriteria.setAdminOptSysId(request.getAdminSysId());
            locateCriteria.setBenefitAdminSysId(request.getBenefitAdminSysId());
            locateCriteria.setEntityId(request.getBenefitComponentId());
            locateCriteria.setEntiityType(BusinessConstants.BENEFIT_COMPONENT);
            response = (LocateComponentsBenefitAdministrationResponse) WPDResponseFactory
                    .getResponse(WPDResponseFactory.LOCATE_COMPONENTS_BENEFIT_ADMIN_RESPONSE);
            LocateResults locateResults = bom.locate(locateCriteria, request
                    .getUser());
            response.setBenefitAdministrationList(locateResults
                    .getLocateResults());
            return response;
        } catch (WPDException ex) {
            
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing LocateComponentsBenefitAdministrationRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
    }


    /**
     * Method to Save BenefitComponentQuestionnare
     * 
     * @param UpdateComponentsBenefitAdministrationRequest
     * 		  request contains list of Questionnaire,benefitcomponentId,AdminleveloptionSysid 
     * @return UpdateComponentsBenefitAdministrationResponse
     * 		  response contain suucees full message
     * @throws WPDException
     */
    public WPDResponse execute(
            UpdateComponentsBenefitAdministrationRequest request)
            throws ServiceException {
    	List messageList = new ArrayList(2);
        Logger.logInfo("Inside Update Admistration Benefit Component Service");
        UpdateComponentsBenefitAdministrationResponse response = null;
        BusinessObjectManager bom = getBOM();
        BenefitComponentBO benefitComponentBO = new BenefitComponentBO();
        benefitComponentBO.setName(request.getMainObjectIdentifier());
        benefitComponentBO.setBusinessDomainList(request.getDomainList());
        benefitComponentBO.setVersion(request.getVersionNumber());
        List questionnareList = request.getQuestionnareList();
       
        ComponentsBenefitAdministrationBO administrationBO = new ComponentsBenefitAdministrationBO();
        administrationBO
                .setQuestionnareList(request.getQuestionnareList());
        administrationBO.setNewQuestions(request.getQuestionnaireListToAdd());
        administrationBO.setModifiedQuestions(request.getQuestionnaireListToUpdate());
        administrationBO.setRemovedQuestions(request.getQuestionnaireListToRemove());
		
        administrationBO.setBenefitComponentid(request.getEntityId());
        administrationBO.setAdminLevelOptionSysId(request.getAdminlevelOptionSysId());
       
        try {
            bom.persist(administrationBO, benefitComponentBO,
                    request.getUser(), false);
            messageList.add(new InformationalMessage(BusinessConstants.MSG_QUESTIONNARE_SAVE_BC_SUCCESS));
            
        } catch (SevereException ex) {
        	Logger.logError(ex);
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing UpdateComponentsBenefitAdministrationRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        } catch (WPDException ex) {
        	Logger.logError(ex);
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing UpdateComponentsBenefitAdministrationRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
    
        response = (UpdateComponentsBenefitAdministrationResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.UPDATE_COMPONENTS_BENEFIT_ADMIN_RESPONSE);
        response.setMessages(messageList);
        return response;
    }
   

    /**
     * Check Out the Benefit Component details
     * 
     * @param benefitComponentCheckOutRequest
     * @return benefitComponentCheckOutResponse
     * @throws ServiceException
     */
    public WPDResponse execute(
            BenefitComponentCheckOutRequest benefitComponentCheckOutRequest)
            throws ServiceException {
        Logger.logInfo("Inside Benefit Component Check out Service");
        BenefitComponentCheckOutResponse benefitComponentCheckOutResponse = (BenefitComponentCheckOutResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.CHECKOUT_BENEFIT_COMPONENT_RESPONSE);
        List messageList = new ArrayList(1);	
        try {

            BenefitComponentBO benefitComponentBO = copyBusinessObjectFromValueObject(benefitComponentCheckOutRequest
                    .getBenefitComponentVO());

            BusinessObjectManager businessObjectManager = this.getBOM();

            benefitComponentBO = (BenefitComponentBO) businessObjectManager
                    .checkOut(benefitComponentBO,
                            benefitComponentCheckOutRequest.getUser());
            
            InformationalMessage message = new InformationalMessage(WebConstants.BENEFIT_COMPONENT_CHECKEDOUT_SUCCESS);
            message.setParameters(new String[] {benefitComponentCheckOutRequest.getBenefitComponentVO().getBenefitComponentName()});
            messageList.add(message);
            benefitComponentCheckOutResponse.setMessages(messageList);
            
//            benefitComponentCheckOutResponse
//                    .addMessage(new InformationalMessage(
//                            "benefit.component.checked.out.success.info"));
            Logger.logInfo(" Benefit Component Checked out Successfully");

            BenefitComponentVO benefitComponentVO = copyValueObjectFromBusinessObject(benefitComponentBO);

            benefitComponentCheckOutResponse
                    .setBenefitComponentVO(benefitComponentVO);
        } catch (WPDException ex) {
             //benefitComponentCheckOutResponse.setErrorFlag(true);
//            benefitComponentCheckOutResponse.addMessage(new ErrorMessage(
//                    "benefit.component.checked.out.failure.info"));
            List logParameters = new ArrayList(1);
            logParameters.add(benefitComponentCheckOutRequest);
            String logMessage = "Failed while processing BenefitComponentCheckOutRequest  ";
            throw new ServiceException(logMessage, logParameters, ex);
            
        } catch (Exception ex) {
        	Logger.logError(ex);
            List logParameters = new ArrayList(1);
            logParameters.add(benefitComponentCheckOutRequest);
            String logMessage = "Failed while processing BenefitComponentCheckOutResponse";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        return benefitComponentCheckOutResponse;
    }


    /**
     * Publish the Benefit Component details
     * 
     * @param benefitComponentPublishRequest
     * @return benefitComponentPublishResponse
     * @throws ServiceException
     */
    public WPDResponse execute(
            BenefitComponentPublishRequest benefitComponentPublishRequest)
            throws ServiceException {
        Logger.logInfo("Inside  Publish Benefit Component Service");
        List messageList = new ArrayList(1);
        
        BenefitComponentPublishResponse benefitComponentPublishResponse = (BenefitComponentPublishResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.PUBLISH_BENEFIT_COMPONENT_RESPONSE);

        try {

            BenefitComponentBO benefitComponentBO = copyBusinessObjectFromValueObject(benefitComponentPublishRequest
                    .getBenefitComponentVO());

            BusinessObjectManager businessObjectManager = this.getBOM();

            businessObjectManager.publish(benefitComponentBO,
                    benefitComponentPublishRequest.getUser());


            InformationalMessage message = new InformationalMessage(WebConstants.BENEFIT_COMPONENT_PUBLISH_SUCCESS);
            message.setParameters(new String[] {benefitComponentPublishRequest.getBenefitComponentVO().getBenefitComponentName()});
            messageList.add(message);
            benefitComponentPublishResponse.setMessages(messageList);
            
            Logger.logInfo("Benefit Component  Publish Successfully");

            BenefitComponentVO benefitComponentVO = copyValueObjectFromBusinessObject(benefitComponentBO);

            benefitComponentPublishResponse
                    .setBenefitComponentVO(benefitComponentVO);
        } catch (WPDException ex) {
        	
            List logParameters = new ArrayList(1);
            logParameters.add(benefitComponentPublishRequest);
            String logMessage = "Failed while processing benefitComponentPublishRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        } catch (Exception ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(benefitComponentPublishRequest);
            String logMessage = "Failed while processing BenefitComponentPublishResponse";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        return benefitComponentPublishResponse;
    }


    /**
     * Schedule For Test the Benefit Component details
     * 
     * @param benefitComponentScheduleForTestRequest
     * @return benefitComponentScheduleForTestResponse
     * @throws ServiceException
     */
    public WPDResponse execute(
            BenefitComponentScheduleForTestRequest benefitComponentScheduleForTestRequest)
            throws ServiceException {
        Logger.logInfo("Inside Benefit Component  Schedule For Test Service");
        BenefitComponentScheduleForTestResponse benefitComponentScheduleForTestResponse = null;
        try {
            benefitComponentScheduleForTestResponse = (BenefitComponentScheduleForTestResponse) WPDResponseFactory
                    .getResponse(WPDResponseFactory.SCHEDULE_TEST_BENEFIT_COMPONENT_RESPONSE);
            BenefitComponentBO benefitComponentBO = copyBusinessObjectFromValueObject(benefitComponentScheduleForTestRequest
                    .getBenefitComponentVO());
            benefitComponentScheduleForTestRequest
                    .setBenefitComponentBO(benefitComponentBO);
            benefitComponentScheduleForTestResponse = (BenefitComponentScheduleForTestResponse) (new ValidationServiceController()
                    .execute(benefitComponentScheduleForTestRequest));
           
            if (!benefitComponentScheduleForTestResponse.isErrorFlag()) {
            	BusinessObjectManager businessObjectManager = this.getBOM();
                businessObjectManager.scheduleForTest(benefitComponentBO,
                        benefitComponentScheduleForTestRequest.getUser());

                benefitComponentScheduleForTestResponse
                        .addMessage(new InformationalMessage(
                        		BusinessConstants.BENEFIT_COMP_SCHEDULE_FOR_TEST_SUCCESS));
                Logger.logInfo(" Benefit Component Scheduled For Test");

                BenefitComponentVO benefitComponentVO = copyValueObjectFromBusinessObject(benefitComponentBO);

                benefitComponentScheduleForTestResponse
                        .setBenefitComponentVO(benefitComponentVO);
            }
        } catch (WPDException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(benefitComponentScheduleForTestRequest);
            String logMessage = "Failed while processing BenefitComponentScheduleForTestRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        } catch (Exception ex) {
           
            List logParameters = new ArrayList(1);
            logParameters.add(benefitComponentScheduleForTestRequest);
            String logMessage = "Failed while processing BenefitComponentScheduleForTestResponse";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        return benefitComponentScheduleForTestResponse;
    }


    /**
     * Test Pass the Benefit Component details
     * 
     * @param benefitComponentTestPassRequest
     * @return benefitComponentTestPassResponse
     * @throws ServiceException
     */
    public WPDResponse execute(
            BenefitComponentTestPassRequest benefitComponentTestPassRequest)
            throws ServiceException {
        Logger.logInfo("Inside Benefit Component  Test Pass Service");
        BenefitComponentTestPassResponse benefitComponentTestPassResponse = (BenefitComponentTestPassResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.TEST_PASS_BENEFIT_COMPONENT_RESPONSE);

        try {

            BenefitComponentBO benefitComponentBO = copyBusinessObjectFromValueObject(benefitComponentTestPassRequest
                    .getBenefitComponentVO());

            BusinessObjectManager businessObjectManager = this.getBOM();

            businessObjectManager.testPass(benefitComponentBO,
                    benefitComponentTestPassRequest.getUser());

            benefitComponentTestPassResponse
                    .addMessage(new InformationalMessage(
                    		BusinessConstants.BENEFIT_COMP_TESTPASS_SUCCESS));
            Logger.logInfo(" Benefit Component  Test Pass Done");

            BenefitComponentVO benefitComponentVO = copyValueObjectFromBusinessObject(benefitComponentBO);

            benefitComponentTestPassResponse
                    .setBenefitComponentVO(benefitComponentVO);
        } catch (WPDException ex) {

            List logParameters = new ArrayList(1);
            logParameters.add(benefitComponentTestPassRequest);
            String logMessage = "Failed while processing BenefitComponentTestPassRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        } catch (Exception ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(benefitComponentTestPassRequest);
            String logMessage = "Failed while processing BenefitComponentTestPassResponse";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        return benefitComponentTestPassResponse;
    }


    /**
     * Test Fail the Benefit Component details
     * 
     * @param benefitComponentTestFailRequest
     * @return benefitComponentTestFailResponse
     * @throws ServiceException
     */
    public WPDResponse execute(
            BenefitComponentTestFailRequest benefitComponentTestFailRequest)
            throws ServiceException {
        Logger.logInfo("Inside Benefit Component  Test Fail Service");
        BenefitComponentTestFailResponse benefitComponentTestFailResponse = (BenefitComponentTestFailResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.TEST_FAIL_BENEFIT_COMPONENT_RESPONSE);

        try {
            BenefitComponentBO benefitComponentBO = copyBusinessObjectFromValueObject(benefitComponentTestFailRequest
                    .getBenefitComponentVO());
            BusinessObjectManager businessObjectManager = this.getBOM();

            businessObjectManager.testFail(benefitComponentBO,
                    benefitComponentTestFailRequest.getUser());

            benefitComponentTestFailResponse
                    .addMessage(new ErrorMessage(
                    		BusinessConstants.BENEFIT_COMP_TESTFAIL));
            Logger.logInfo("Benefit Component  Test Fail Done");
            BenefitComponentVO benefitComponentVO = copyValueObjectFromBusinessObject(benefitComponentBO);

            benefitComponentTestFailResponse
                    .setBenefitComponentVO(benefitComponentVO);
        } catch (WPDException ex) {

            List logParameters = new ArrayList(1);
            logParameters.add(benefitComponentTestFailRequest);
            String logMessage = "Failed while processing benefitComponentTestFailRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        } catch (Exception ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(benefitComponentTestFailRequest);
            String logMessage = "Failed while processing BenefitComponentTestFailResponse";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        return benefitComponentTestFailResponse;
    }


    /**
     * Copying the contents of a BenefitComponent Business Object to
     * BenefitComponent Value Object.
     * 
     * @param benefitComponentBO
     * @return benefitComponentVO
     */
    private BenefitComponentVO copyValueObjectFromBusinessObject(
            BenefitComponentBO benefitComponentBO) {
        BenefitComponentVO benefitComponentVO = new BenefitComponentVO();
        benefitComponentVO.setBenefitComponentId(benefitComponentBO
                .getBenefitComponentId());
        benefitComponentVO
                .setBenefitComponentName(benefitComponentBO.getName());
        benefitComponentVO.setBusinessDomainList(benefitComponentBO
                .getBusinessDomainList());
        benefitComponentVO.setVersion(benefitComponentBO.getVersion());
        benefitComponentVO.setStatus(benefitComponentBO.getStatus());
        benefitComponentVO.setComponentType(benefitComponentBO
                .getComponentType());
        benefitComponentVO.setMandateType(benefitComponentBO.getMandateType());
        benefitComponentVO.setStateId(benefitComponentBO.getStateId());
        benefitComponentVO.setStateDesc(benefitComponentBO.getStateDesc());
        benefitComponentVO.setRuleIdList(benefitComponentBO.getRuleList());
        benefitComponentVO
                .setRuleNameList(benefitComponentBO.getRuleNameList());
        return benefitComponentVO;
    }


    /**
     * Copying the contents of a BenefitComponent Value Object to
     * BenefitComponent Business Object.
     * 
     * @param adminOptionVO
     * @return adminOptionBO
     */
    private BenefitComponentBO copyBusinessObjectFromValueObject(
            BenefitComponentVO benefitComponentVO) {

        BenefitComponentBO benefitComponentBO = new BenefitComponentBO();

        benefitComponentBO.setBenefitComponentId(benefitComponentVO
                .getBenefitComponentId());
        benefitComponentBO
                .setName(benefitComponentVO.getBenefitComponentName());
        benefitComponentBO.setBusinessDomainList(benefitComponentVO
                .getBusinessDomainList());
        benefitComponentBO.setVersion(benefitComponentVO.getVersion());
        benefitComponentBO.setStatus(benefitComponentVO.getStatus());
        return benefitComponentBO;
    }


    /**
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param benefitComponentSaveRequest
     * @return benefitComponentSaveResponse
     * @throws WPDException
     */
    public WPDResponse execute(
            BenefitComponentCopyRequest benefitComponentCopyRequest)
            throws ServiceException {
        Logger.logInfo("Inside Benefit Component Copy Service");
        BenefitComponentCopyResponse benefitComponentCopyResponse = null;
        
       
        try {
            BenefitComponentVO benefitComponentVO = (BenefitComponentVO) benefitComponentCopyRequest
                    .getBenefitComponentVO();
            BenefitComponentVO oldBenefitComponentVO = (BenefitComponentVO) benefitComponentCopyRequest
                    .getOldBenefitComponentVO();
            BenefitComponentBO sourceBenefitComponentBO = getOldBenefitComponentObject(oldBenefitComponentVO);
            BenefitComponentBO targetBenefitComponentBO = getBenefitComponentObject(benefitComponentVO);
            modifyDomainValues(benefitComponentVO);
            

            benefitComponentCopyResponse = (BenefitComponentCopyResponse) WPDResponseFactory
                    .getResponse(WPDResponseFactory.COPY_BENEFIT_COMPONENT_RESPONSE);
            benefitComponentCopyResponse = (BenefitComponentCopyResponse) new ValidationServiceController()
                    .execute(benefitComponentCopyRequest);
//          FIXME use adding( && ) in place of orring( || ) or use && !benefitComponentCopyResponse.getMessages().isEmpty()
            if (null != benefitComponentCopyResponse
                    && !(null != benefitComponentCopyResponse.getMessages() && !benefitComponentCopyResponse
                            .getMessages().isEmpty())) {
                HashMap map = new HashMap();
                BusinessObjectManager bom = getBOM();
                bom.persist(targetBenefitComponentBO,
                        benefitComponentCopyRequest.getUser(), true);
                bom.copy(sourceBenefitComponentBO, targetBenefitComponentBO,
                        benefitComponentCopyRequest.getUser(), map);
                benefitComponentCopyResponse.setDomailDetail(DomainUtil
                        .retrieveDomainDetailInfo(BusinessConstants.BENEFIT_COMPONENT,
                                targetBenefitComponentBO
                                        .getBenefitComponentParentId()));
                benefitComponentCopyResponse
                        .setBenefitComponentBO(targetBenefitComponentBO);
                
// Enhancement
//              Enhancement - To incorporate delete for domain Change    
               
// End - Enhancement                
                if (benefitComponentCopyRequest.isDeleteBenefit()) {
                    BenefitComponent subObj = new BenefitComponent();
                    subObj.setBenefitComponentId(targetBenefitComponentBO
                            .getBenefitComponentId());
                    bom.delete(subObj, targetBenefitComponentBO,
                            benefitComponentCopyRequest.getUser());

                    //Code to delete the notes corresponding to the selected
                    // standarbBenefit
                    NotesAttachmentDomainOverrideBO notesAttachmentDomainOverrideBO = new NotesAttachmentDomainOverrideBO();
                    notesAttachmentDomainOverrideBO
                            .setPrimaryEntityId(targetBenefitComponentBO
                                    .getBenefitComponentId());
                    notesAttachmentDomainOverrideBO
                            .setPrimaryEntityType(BusinessConstants.ATTACH_COMPONENT);
                    notesAttachmentDomainOverrideBO
                            .setBenefitComponentId(targetBenefitComponentBO
                                    .getBenefitComponentId());
                    boolean benefitComponentNotesDeleted = bom.delete(
                            notesAttachmentDomainOverrideBO,
                            targetBenefitComponentBO,
                            benefitComponentCopyRequest.getUser());
                }

                benefitComponentCopyRequest.setDeleteBenefit(false);

                InformationalMessage message = new InformationalMessage(BusinessConstants.BENEFIT_COMPONENT_COPY_SUCCESS);
                message.setParameters(new String[] {benefitComponentCopyRequest.getOldBenefitComponentVO().getBenefitComponentName()});
                List messageList = new ArrayList(1);
                messageList.add(message);
                
                if(benefitComponentCopyRequest.isDomainChange()){
                    BenefitComponentBusinessObjectBuilder benefitComponentBusinessObjectBuilder = new BenefitComponentBusinessObjectBuilder();
                    BenefitHierarchyLocateCriteria benefitHierarchyLocCriteria = new BenefitHierarchyLocateCriteria();
                    benefitHierarchyLocCriteria.setBenefitComponentId(targetBenefitComponentBO.getBenefitComponentId());
                    benefitHierarchyLocCriteria.setLobList(BusinessUtil
                    											.getLobList(targetBenefitComponentBO
                    													.getBusinessDomainList()));
                    benefitHierarchyLocCriteria.setBusinessEntityList(BusinessUtil
                                    .getbusinessEntityList(targetBenefitComponentBO
                                            .getBusinessDomainList()));
                    benefitHierarchyLocCriteria.setBusinessGroupList(BusinessUtil
                                    .getBusinessGroupList(targetBenefitComponentBO
                                            .getBusinessDomainList())); 
                    benefitHierarchyLocCriteria.setMarketBusinessUnitList(BusinessUtil
                            .getMarketBusinessUnitList(targetBenefitComponentBO
                                    .getBusinessDomainList())); 
                    LocateResults locateResults1 = benefitComponentBusinessObjectBuilder
                            .locateBenefitHieararchiesToBeRemoved(benefitHierarchyLocCriteria);
                    if(null != locateResults1 
                    		&& null != locateResults1.getLocateResults() 
    						&& !locateResults1.getLocateResults().isEmpty()){                    	
                        messageList.add(new InformationalMessage(BusinessConstants.BENEFIT_HIERARCHY_INVALID_BENEFIT_CHANGE));   
                    }
                }
                
                benefitComponentCopyResponse.setMessages(messageList);
                
            } else
                benefitComponentCopyResponse.setBenefitComponentBO(null);
        } catch (Exception ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(benefitComponentCopyRequest);
            String logMessage = "Failed while processing BenefitComponentCopyRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        return benefitComponentCopyResponse;
    }


    /**
     * Gets old benefit componet VO
     * 
     * @param oldBenefitComponentVO
     * @return
     */
    private BenefitComponentBO getOldBenefitComponentObject(
            BenefitComponentVO oldBenefitComponentVO) {

        BenefitComponentBO sourceBenefitComponentBO = new BenefitComponentBO();

        sourceBenefitComponentBO.setBenefitComponentId(oldBenefitComponentVO
                .getBenefitComponentId());
        sourceBenefitComponentBO
                .setBenefitComponentParentId(oldBenefitComponentVO
                        .getBenefitComponentParentId());
        sourceBenefitComponentBO.setName(oldBenefitComponentVO
                .getBenefitComponentName().toUpperCase());
        sourceBenefitComponentBO.setVersion(oldBenefitComponentVO.getVersion());
        sourceBenefitComponentBO.setBusinessDomainList(oldBenefitComponentVO
                .getBusinessDomainList());
        sourceBenefitComponentBO.setEffectiveDate(oldBenefitComponentVO
                .getEffectivedate());
        sourceBenefitComponentBO.setExpiryDate(oldBenefitComponentVO
                .getExpirydate());

        return sourceBenefitComponentBO;

    }


    /**
     * Method to view all versions of the benefit component
     * 
     * @param request
     * @return WPDResponse
     * @throws WPDException
     */
    public WPDResponse execute(
            BenefitComponentViewAllRequest benefitComponentViewAllRequest)
            throws ServiceException {
        try {
            Logger
                    .logInfo("Inside Benefit Component View All Versions Service");
            BenefitComponentViewAllResponse benefitComponentViewAllResponse = new BenefitComponentViewAllResponse();
            List searchResultsList = null;
            List errorList = new ArrayList(1);
            LocateResults searchResults = null;
            BusinessObjectManager bom = getBOM();
            BenefitComponentLocateCriteria locateCriteria = new BenefitComponentLocateCriteria();
            locateCriteria.setComponentId(benefitComponentViewAllRequest
                    .getComponentId());
            locateCriteria
                    .setAction(benefitComponentViewAllRequest.getAction());
            locateCriteria.setViewAllAction(true);
            searchResults = bom.locate(locateCriteria,
                    benefitComponentViewAllRequest.getUser());
            searchResultsList = searchResults.getLocateResults();
            benefitComponentViewAllResponse
                    .setBenefitComponentViewAllList(searchResultsList);
            return benefitComponentViewAllResponse;
        } catch (WPDException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(benefitComponentViewAllRequest);
            String logMessage = "Failed while processing BenefitComponentViewAllRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
    }


    /**
     * Method to attach notes for benefitComponent
     * 
     * @param benefitComponentAttachNotesRequest
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(
            BenefitComponentAttachNotesRequest benefitComponentAttachNotesRequest)
            throws ServiceException {
        try {
            List notesIdList = new ArrayList(1);
            Logger.logInfo("Inside Benefit Component Attach Notes Service");

            BenefitComponentAttachNotesResponse benefitComponentAttachNotesResponse = null;
            BenefitComponentVO benefitComponentVO = (BenefitComponentVO) benefitComponentAttachNotesRequest
                    .getBenefitComponentVO();
            BenefitComponentBO benefitComponentBO = new BenefitComponentBO();
            benefitComponentBO.setStatus(benefitComponentVO.getStatus());
            benefitComponentBO.setVersion(benefitComponentVO.getVersion());
            benefitComponentBO.setName(benefitComponentVO
                    .getBenefitComponentName());
            benefitComponentBO.setBusinessDomainList(benefitComponentVO
                    .getBusinessDomainList());

            BusinessObjectManager bom = getBOM();
            benefitComponentAttachNotesResponse = (BenefitComponentAttachNotesResponse) WPDResponseFactory
                    .getResponse(WPDResponseFactory.ATTACH_BENEFIT_COMPONENT_NOTES_RESPONSE);
            if (benefitComponentAttachNotesRequest.getAction() == 1) {
                notesIdList = getBenefitComponentNotesObject(benefitComponentVO
                        .getSelectednotesIdList(), benefitComponentVO
                        .getBenefitComponentId(), benefitComponentVO
                        .getVersionList());
               
                bom.persist(notesIdList, benefitComponentBO,
                        benefitComponentAttachNotesRequest.getUser(), true);
               
               
            }
            // To attach Notes for standardBenefitOverride
            if (benefitComponentAttachNotesRequest.getAction() == 2) {

                int bcId = benefitComponentVO.getBenefitComponentId();
                int sbId = benefitComponentVO.getStandardBenefitKey();
                notesIdList = getStandardBenefitOverrideNotesObject(
                        benefitComponentVO.getSelectednotesIdList(), bcId,
                        sbId, benefitComponentVO.getVersionList());
               
                bom.persist(notesIdList, benefitComponentBO,
                        benefitComponentAttachNotesRequest.getUser(), false);
                //benefitComponentAttachNotesResponse
                        //.addMessage(new InformationalMessage(
                              //  BusinessConstants.MSG_BENEFIT_COMPONENT_NOTES_ATTACH_SUCCESS));
               
            }
            benefitComponentAttachNotesResponse
            .addMessage(new InformationalMessage(
                    BusinessConstants.MSG_BENEFIT_COMPONENT_NOTES_ATTACH_SUCCESS));

            Logger.logInfo("Benefit Component Notes Saved Successfully");
            return benefitComponentAttachNotesResponse;
        } catch (Exception ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(benefitComponentAttachNotesRequest);
            String logMessage = "Failed while processing BenefitComponentAttachNotesRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
    }


    /**
     * method that retuns the list of subobjects
     * 
     * @param idList
     * @param entityId
     * @param versionList
     * @return
     */
    private List getBenefitComponentNotesObject(List idList, int entityId,
            List versionList) {

        List noteIdList = null;
        if(idList.size() == versionList.size()){
        	noteIdList = new ArrayList(idList.size());
	        for (int i = 0; i < idList.size(); i++) {
	            AttachedNotesBO attachedNotesBO = new AttachedNotesBO();
	            attachedNotesBO.setNoteId(String.valueOf(idList.get(i)));
	            attachedNotesBO.setVersion(Integer.parseInt((String) versionList
	                    .get(i)));
	            attachedNotesBO.setEntityId(entityId);
	            attachedNotesBO.setEntityType(WebConstants.ATTACH_BENEFIT_COMP);
	            noteIdList.add(attachedNotesBO);
	        }
        }
        return noteIdList;
    }


    /**
     * method that retuns the list of subobject
     * 
     * @param idList
     * @param entityId
     * @param sbId
     * @param versionList
     * @return
     */
    private List getStandardBenefitOverrideNotesObject(List idList,
            int entityId, int sbId, List versionList) {

        List noteIdList = null;
        if(idList.size() == versionList.size()){
        	noteIdList = new ArrayList(idList.size());
	        for (int i = 0; i < idList.size(); i++) {
	            NotesAttachmentOverrideBO overrideNotesBO = new NotesAttachmentOverrideBO();
	            overrideNotesBO.setNoteId((String.valueOf(idList.get(i))));
	            overrideNotesBO.setVersion(Integer.parseInt((String) versionList
	                    .get(i)));
	            overrideNotesBO.setPrimaryEntityId(entityId);
	            overrideNotesBO
	                    .setPrimaryEntityType(WebConstants.ATTACH_BENEFIT_COMP);
	            overrideNotesBO.setSecondaryEntityType(WebConstants.ATTACH_BENEFIT);
	            overrideNotesBO.setSecondaryEntityId(sbId);
	            overrideNotesBO.setBenefitComponentId(entityId);
	            noteIdList.add(overrideNotesBO);
	        }
        }
        return noteIdList;       
    }


    /**
     * Delete service for detaching Notes attached to a BenefitComponent - Multiple delete is implemented.
     * 
     * @param request
     * @return WPDResponse
     * @throws WPDException
     */
    public WPDResponse execute(DeleteBenefitComponentNotesRequest request)
            throws WPDException {
    	Logger.logInfo("Inside Benefit Component Detach Notes Service");
        DeleteBenefitComponentNotesResponse deleteBenefitComponentNotesResponse = null;
        List notesIdList = null;
        boolean isDeleteSuccess = false;
        BenefitComponentBO benefitComponentBO = new BenefitComponentBO();
        benefitComponentBO.setName(request.getBenefitComponentName());
        benefitComponentBO.setBusinessDomainList(request.getBusinessDomains());
        benefitComponentBO.setVersion(request.getVersion());
        benefitComponentBO.setStatus(request.getStatus());
        BusinessObjectManager bom = getBOM();
        deleteBenefitComponentNotesResponse = (DeleteBenefitComponentNotesResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.DELETE_BENEFIT_COMPONENT_NOTES_RESPONSE);
        
        if (BusinessConstants.BENEFIT_COMPONENT_LOCATE1 == request.getAction()) {
        	notesIdList = request.getBenefitComponentNotesIdsList();
        	if(null != notesIdList && !notesIdList.isEmpty()){
        		for(int i = 0; i < notesIdList.size(); i++){
		            AttachedNotesBO attachedNotesBO = new AttachedNotesBO();
		            if(null != notesIdList.get(i)){
		            	attachedNotesBO.setNoteId((notesIdList.get(i)).toString());
		            }
		            attachedNotesBO.setEntityId(request.getEntityId());
		            attachedNotesBO.setEntityType(WebConstants.ATTACH_BENEFIT_COMP);
		            if(bom.delete(attachedNotesBO, benefitComponentBO, request.getUser())){
		            	isDeleteSuccess = true;
		            }
		            else{
		            	isDeleteSuccess = false;
		            }
        		}
        	}
        	if(isDeleteSuccess){
	            deleteBenefitComponentNotesResponse
	                    .addMessage(new InformationalMessage(
	                    		BusinessConstants.MSG_BENEFIT_COMPONENT_NOTES_MULTI_DELETE_SUCCESS));
        	}
        	else{
            	deleteBenefitComponentNotesResponse
                .addMessage(new ErrorMessage(
                        BusinessConstants.MSG_BENEFIT_COMPONENT_NOTES_DELETE_FAILURE));
        	 }
        } else if (request.getAction() == BusinessConstants.BENEFIT_COMPONENT_LOCATE2) {
//            NotesAttachmentOverrideBO subObject = new NotesAttachmentOverrideBO();
            //multiple delete of benefit level notes
            notesIdList = request.getBenefitComponentNotesIdsList();
            List notesList = request.getNotesList();
            NotesAttachmentOverrideBO subObject1 = new NotesAttachmentOverrideBO();
			
            if(null != notesIdList && !notesIdList.isEmpty()){
        		
      			for(int i=0; i<notesIdList.size();i++){
      				String noteId = (String) notesIdList.get(i);
      				if(null != notesList && !notesList.isEmpty()){
      					for(int j=0; j<notesList.size();j++){
      						subObject1 = (NotesAttachmentOverrideBO)notesList.get(j);
      						
      						if(noteId.equals(subObject1.getNoteId())){
      							NotesAttachmentOverrideBO subObject = new NotesAttachmentOverrideBO();
    	  						subObject.setNoteId((notesIdList.get(i)).toString());
    	  			            subObject.setPrimaryEntityId(request.getEntityId());
    	  			            subObject.setPrimaryEntityType(WebConstants.ATTACH_BENEFIT_COMP);
    	  			            subObject.setSecondaryEntityId(request.getSecEntityId());
    	  			            subObject.setSecondaryEntityType(WebConstants.ATTACH_BENEFIT);
    	  			            subObject.setBenefitComponentId(request.getBenefitComponentId());
    	  			            subObject.setVersion(subObject1.getVersion());
    	  				      bom.persist(subObject, benefitComponentBO, request.getUser(),
		                            false);
    	  					}
      					}
      				}
      			}
        	}
            
            
            deleteBenefitComponentNotesResponse
                    .addMessage(new InformationalMessage(
                            BusinessConstants.MSG_STANDARD_BENEFIT_NOTES_OVERRIDE_DELETE_SUCCESS));
        }
        Logger.logInfo("Exiting Benefit Component Detach Notes Service");
        return deleteBenefitComponentNotesResponse;
    }


    /**
     * Locate for Notes attached to BenefitComponent
     * 
     * @param request
     * @return
     * @throws SevereException
     */
    public WPDResponse execute(LocateBenefitComponentNotesRequest request)
            throws SevereException {
        LocateBenefitComponentNotesResponse locateBenefitComponentNotesResponse = null;
        BusinessObjectManager bom = getBOM();
        locateBenefitComponentNotesResponse = (LocateBenefitComponentNotesResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.LOCATE_BENEFIT_COMPONENT_NOTES_RESPONSE);
        BenefitComponentNotesLocateCriteria notesLocateCriteria = new BenefitComponentNotesLocateCriteria();
        if (request.getAction() == BusinessConstants.BENEFIT_COMPONENT_LOCATE1) {
            notesLocateCriteria.setEntityId(request.getEntityId());
            notesLocateCriteria.setEntityType(request.getEntityType());
            notesLocateCriteria
                    .setMaximumResultSize(request.getMaxResultSize());
            notesLocateCriteria.setAction(1);
        } else if (request.getAction() == BusinessConstants.BENEFIT_COMPONENT_LOCATE2) {
            notesLocateCriteria.setEntityId(request.getEntityId());
            notesLocateCriteria.setEntityType(request.getEntityType());
            notesLocateCriteria
                    .setMaximumResultSize(request.getMaxResultSize());
            notesLocateCriteria.setSecEntityId(request.getSecEntityId());
            notesLocateCriteria.setSecEntityType(request.getSecEntityType());
            notesLocateCriteria.setBenefitComponentId(request
                    .getBenefitComponentId());
            notesLocateCriteria.setAction(BusinessConstants.BENEFIT_COMPONENT_LOCATE2);
        }
        LocateResults locateResults;
        try {
            locateResults = bom.locate(notesLocateCriteria, request.getUser());
        } catch (WPDException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing LocateBenefitComponentNotesRequest";
            throw new SevereException(logMessage, logParameters, e);
           
        }
        if (null != locateResults.getLocateResults()
                && !locateResults.getLocateResults().isEmpty()) {
            locateBenefitComponentNotesResponse
                    .setBenefitComponentNotesList(locateResults
                            .getLocateResults());
        }
        return locateBenefitComponentNotesResponse;
    }


    /**
     * Test Pass the Benefit Component details
     * 
     * @param benefitComponentApproveRequest
     * @return WPDResponse
     * @throws ServiceException
     */
    public WPDResponse execute(
            BenefitComponentApproveRequest benefitComponentApproveRequest)
            throws ServiceException {
        Logger.logInfo("Inside Benefit Component  Test Pass Service");
        BenefitComponentApproveResponse benefitComponentApproveResponse = (BenefitComponentApproveResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.APPROVE_BENEFIT_COMPONENT_RESPONSE);
        List messageList = new ArrayList(1);
        try {

            BenefitComponentBO benefitComponentBO = copyBusinessObjectFromValueObject(benefitComponentApproveRequest
                    .getBenefitComponentVO());
            BusinessObjectManager businessObjectManager = this.getBOM();
            
            User user =  benefitComponentApproveRequest.getUser();
            businessObjectManager.approve(benefitComponentBO,
                    user);
            businessObjectManager.publish(benefitComponentBO,
                    user);


            InformationalMessage message = new InformationalMessage(BusinessConstants.BENEFIT_COMP_APPROVE_SUCCESS);
            message.setParameters(new String[] {benefitComponentApproveRequest.getBenefitComponentVO().getBenefitComponentName()});
            messageList.add(message);
            benefitComponentApproveResponse.setMessages(messageList);
            Logger.logInfo(" Benefit Component  Approved");

            BenefitComponentVO benefitComponentVO = copyValueObjectFromBusinessObject(benefitComponentBO);

            benefitComponentApproveResponse
                    .setBenefitComponentVO(benefitComponentVO);
        } catch (WPDException ex) {
            benefitComponentApproveResponse.setErrorFlag(true);
            throw new ServiceException("Exception occured while BOM call",
                    null, ex);
        } catch (Exception ex) {
        	Logger.logError(ex);
            List logParameters = new ArrayList(1);
            logParameters.add(benefitComponentApproveRequest);
            String logMessage = "Failed while processing BenefitComponentTestPassResponse";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        return benefitComponentApproveResponse;
    }


    /**
     * Reject the Benefit Component details
     * 
     * @param benefitComponentRejectRequest
     * @return benefitComponentRejectResponse
     * @throws ServiceException
     */
    public WPDResponse execute(
            BenefitComponentRejectRequest benefitComponentRejectRequest)
            throws ServiceException {
        Logger.logInfo("Inside Benefit Component  Test Pass Service");
        BenefitComponentRejectResponse benefitComponentRejectResponse = (BenefitComponentRejectResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.REJECT_BENEFIT_COMPONENT_RESPONSE);
        List messageList = new ArrayList(1);
        try {

            BenefitComponentBO benefitComponentBO = copyBusinessObjectFromValueObject(benefitComponentRejectRequest
                    .getBenefitComponentVO());

            BusinessObjectManager businessObjectManager = this.getBOM();

            businessObjectManager.reject(benefitComponentBO,
                    benefitComponentRejectRequest.getUser());
            
            InformationalMessage message = new InformationalMessage(BusinessConstants.BENEFIT_COMP_REJECT_SUCCESS);
            message.setParameters(new String[] {benefitComponentRejectRequest.getBenefitComponentVO().getBenefitComponentName()});
            messageList.add(message);
            benefitComponentRejectResponse.setMessages(messageList);	

            Logger.logInfo(" Benefit Component  Approved");

            BenefitComponentVO benefitComponentVO = copyValueObjectFromBusinessObject(benefitComponentBO);

            benefitComponentRejectResponse
                    .setBenefitComponentVO(benefitComponentVO);
        } catch (WPDException ex) {
            benefitComponentRejectResponse.setErrorFlag(true);
            throw new ServiceException("Exception occured while BOM call",
                    null, ex);
        } catch (Exception ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(benefitComponentRejectRequest);
            String logMessage = "Failed while processing BenefitComponentTestPassResponse";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        return benefitComponentRejectResponse;
    }
    
    /**
     * Method to persist the hide/unhide of admin option in benefit component
     * @param request
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(HideAdminOptionRequest request) throws ServiceException {
	    HideAdminOptionResponse hideAdminOptionResponse = (HideAdminOptionResponse) WPDResponseFactory
	        .getResponse(WPDResponseFactory.HIDE_ADMIN_OPTION_RESPONSE);
	    BusinessObjectManager bom = getBOM();
	    
	    BenefitComponentBO benefitComponentBO=new BenefitComponentBO();
	    List adminOptionList=new ArrayList(request.getAdminOptionList().size());
	    AdministrationOptionVO administrationOptionVO = null;
	    
	    for(int i=0; i< request.getAdminOptionList().size();i++){
		    administrationOptionVO = (AdministrationOptionVO)request.getAdminOptionList().get(i);
		    AdminOptionHideBO adminOptionHideBusinessObject = new AdminOptionHideBO();
		    adminOptionHideBusinessObject.setAdminLevelOptionAssnSystemId(administrationOptionVO.getAdminLevelOptionAssnSystemId());
		    adminOptionHideBusinessObject.setHideFlag(administrationOptionVO.getHideFlag());
		    adminOptionHideBusinessObject.setQuestionHideflag(administrationOptionVO.getQuestionHideFlag());
		    adminOptionHideBusinessObject.setEntityType(administrationOptionVO.getEntityType());
		    adminOptionHideBusinessObject.setModified(administrationOptionVO.isModified());
		    adminOptionHideBusinessObject.setEntityId(administrationOptionVO.getEntitySysId());
		    adminOptionList.add(adminOptionHideBusinessObject);
		  
		    
	    }
	    benefitComponentBO.setName(administrationOptionVO.getStandardBenefitName());
	    benefitComponentBO.setBusinessDomainList(administrationOptionVO.getBusinessDomains());
	   
	    benefitComponentBO.setVersion(administrationOptionVO.getMasterVersion());
	  
	    AdminOptionHideBO adminOptionHideBO = new AdminOptionHideBO();
	    adminOptionHideBO.setAdminList(adminOptionList);
		    try {
	            bom.persist(adminOptionHideBO,benefitComponentBO,request.getUser(),false);
	        } catch (SevereException ex) {
	        	Logger.logError(ex);
	            List logParameters = new ArrayList(1);
	            logParameters.add(request);
	            String logMessage = "Failed while processing HideAdminOptionRequest";
	            throw new ServiceException(logMessage, logParameters, ex);
	        } catch (WPDException ex) {
	        	Logger.logError(ex);
	            List logParameters = new ArrayList(1);
	            logParameters.add(request);
	            String logMessage = "Failed while processing HideAdminOptionRequest";
	            throw new ServiceException(logMessage, logParameters, ex);
	        }
		   
	    
	    hideAdminOptionResponse.setSuccess(true);	
	    hideAdminOptionResponse.addMessage(new InformationalMessage(
	    		BusinessConstants.BENEFIT_COMP_ADMN_UPDATE_SUCCESS)); 
    return hideAdminOptionResponse;
    }
    
    /**
     * Method to unlock a Benefit Component
     * @param request
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(BenefitComponentUnlockRequest benefitComponentUnlockRequest) throws ServiceException,WPDException {
	    
    	BenefitComponentUnlockResponse benefitComponentUnlockResponse = (BenefitComponentUnlockResponse) WPDResponseFactory
        .getResponse(WPDResponseFactory.UNLOCK_BENEFIT_COMPONENT_RESPONSE);
    	BusinessObjectManager bom = getBOM();
    	
    	BenefitComponentBO benefitComponentBO = new BenefitComponentBO();
    	benefitComponentBO = benefitComponentUnlockRequest.getBenefitComponentBO();
    	
        bom.unlock(benefitComponentBO, benefitComponentUnlockRequest.getUser());
        InformationalMessage message = new InformationalMessage(
				BusinessConstants.BENEFIT_COMPONENT_UNLOCKED);
        message.setParameters(new String[]{benefitComponentBO.getName()});
        benefitComponentUnlockResponse.addMessage(message);
        benefitComponentUnlockResponse.setSuccess(true);
        Logger.logInfo("Returning  from execute method for request=" + benefitComponentUnlockRequest.getClass().getName());
      return benefitComponentUnlockResponse;
    }
    
    /**
     * This method is used to get the Questionnaire list from the table
     * 
     * @param RetrieveBenefitComponentQuestionnairRequest
     * 		  request for loading initial questionnare,changedquestionnaire and view result 
     * @return LocateComponentsBenefitAdministrationResponse
     * 		  response will hold the questionnaire List
     * @throws WPDException
     */
    public WPDResponse execute(
    		RetrieveBenefitComponentQuestionnairRequest request)
            throws ServiceException {
        try {
            Logger.logInfo("Inside  Benefit Administration Search Service");
            LocateComponentsBenefitAdministrationResponse response = null;
            BusinessObjectManager bom = getBOM();
            ComponentsBenefitAdministrationLocateCriteria locateCriteria = new ComponentsBenefitAdministrationLocateCriteria();
            response = (LocateComponentsBenefitAdministrationResponse) WPDResponseFactory
            .getResponse(WPDResponseFactory.LOCATE_COMPONENTS_BENEFIT_ADMIN_RESPONSE);
            int action = request.getAction();
            switch(action){
             case RetrieveBenefitComponentQuestionnairRequest.LOAD_QUESTIONNARE_LIST:
            locateCriteria.setAction(ComponentsBenefitAdministrationLocateCriteria.LOAD_QUESTIONNARE_LIST);
            locateCriteria.setAdminOptSysId(request.getAdminSysId());
            locateCriteria.setBenefitAdminSysId(request.getBenefitAdminSysId());
            locateCriteria.setEntityId(request.getBenefitComponentId());
            locateCriteria.setEntiityType(BusinessConstants.BENEFIT_COMPONENT);
            locateCriteria.setAllPossibleAnswerMap(request
					.getAllPossibleAnswerMap());
            locateCriteria.setAdminLvlOptSystemId(request.getAdminLvlOptSystemId());
            locateCriteria.setSelectedAnswerDesc(request.getSelectedAnswerDesc());
            locateCriteria.setBeneftCompParentId(request.getBeneftCompParentId());
        	 break;
             case RetrieveBenefitComponentQuestionnairRequest.LOAD_SELECTED_CHILD:
        	 locateCriteria.setAction(ComponentsBenefitAdministrationLocateCriteria.LOAD_SELECTED_CHILD);
        	 locateCriteria.setAdminOptSysId(request.getAdminSysId());
             locateCriteria.setBenefitAdminSysId(request.getBenefitAdminSysId());
             locateCriteria.setBenefitComponentQuesitionnaireBO(request.getBenefitComponentQuesitionnaireBO());
             locateCriteria.setAnswerId(request.getSelectedAnswerId());
             locateCriteria.setEntityId(request.getBenefitComponentId());
             locateCriteria.setQuestionnareList(request.getQuestionnareList());
             locateCriteria.setQuestionareListIndex(request.getQuestionareListIndex());
             locateCriteria.setAllPossibleAnswerMap(request.getAllPossibleAnswerMap());
             locateCriteria.setSelectedAnswerDesc(request.getSelectedAnswerDesc());
             locateCriteria.setAdminLvlOptSystemId(request.getAdminLvlOptSystemId());
             locateCriteria.setBeneftCompParentId(request.getBeneftCompParentId());
        	break;
             case RetrieveBenefitComponentQuestionnairRequest.LOAD_QUESTIONNARE_VIEW:
           	 locateCriteria.setAction(ComponentsBenefitAdministrationLocateCriteria.LOAD_QUESTIONNARE_VIEW);
             locateCriteria.setAdminOptSysId(request.getAdminSysId());
             locateCriteria.setBenefitAdminSysId(request.getBenefitAdminSysId());
             locateCriteria.setEntityId(request.getBenefitComponentId());
             locateCriteria.setAllPossibleAnswerMap(request.getAllPossibleAnswerMap());
             locateCriteria.setSelectedAnswerDesc(request.getSelectedAnswerDesc());
             locateCriteria.setAdminLvlOptSystemId(request.getAdminLvlOptSystemId());
             locateCriteria.setBeneftCompParentId(request.getBeneftCompParentId());
           	break;
            }
            LocateResults locateResults = bom.locate(locateCriteria, request
                    .getUser());
            response.setQuestionnareList(locateResults
                    .getLocateResults());
            
            return response;
        } catch (WPDException ex) {
            
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing LocateComponentsBenefitAdministrationRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
    }
    /**
     * @param bnftCompNotesToQuestionAttachmentRequest
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(
			BnftCompNotesToQuestionAttachmentRequest bnftCompNotesToQuestionAttachmentRequest)
			throws ServiceException {

		BnftCompNotesToQuestionAttachmentResponse bnftCompNotesToQuestionAttachmentResponse = (BnftCompNotesToQuestionAttachmentResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.BNFT_COMP_NOTES_TO_QUESTION_ATTACHMENT_RESPONSE);

		NotesToQuestionAttachmentBO bo = setValuesToNotesToQuestionAttachmentBO(bnftCompNotesToQuestionAttachmentRequest);
		BusinessObjectManager bom = getBOM();

		BenefitComponentBO benefitComponentBO = new BenefitComponentBO();
		benefitComponentBO.setName(bnftCompNotesToQuestionAttachmentRequest
				.getNotesToQuestionAttachmentVO().getBcName());
		benefitComponentBO
				.setBusinessDomainList(bnftCompNotesToQuestionAttachmentRequest
						.getNotesToQuestionAttachmentVO()
						.getBusinessDomainList());
		benefitComponentBO.setVersion(bnftCompNotesToQuestionAttachmentRequest
				.getNotesToQuestionAttachmentVO().getBcVersion());
		List messageList = new ArrayList();

		try {
			if (bnftCompNotesToQuestionAttachmentRequest
					.getNotesToQuestionAttachmentVO().isInsertRequest()) {
				bom.persist(bo, benefitComponentBO,
						bnftCompNotesToQuestionAttachmentRequest.getUser(),
						true);
				InformationalMessage informationalMessage = new InformationalMessage(
						BusinessConstants.SAVE_QUESTION_NOTE__SUCCESS);
				messageList.add(informationalMessage);
			}
		} catch (WPDException exception) {

			List errorParams = new ArrayList();
			String obj = exception.getClass().getName();
			errorParams.add(obj);

			throw new ServiceException(
					"Exception occured in persist method , for persisting the BusinessObject NotesToQuestionAttachmentBO, in BCBusinessService",
					errorParams, exception);
		}
		try {
			if (bnftCompNotesToQuestionAttachmentRequest
					.getNotesToQuestionAttachmentVO().isUpdateRequest()) {
				bom.persist(bo, benefitComponentBO,
						bnftCompNotesToQuestionAttachmentRequest.getUser(),
						false);
				InformationalMessage informationalMessage = new InformationalMessage(
						BusinessConstants.UPDATE_QUESTION_NOTE__SUCCESS);
				messageList.add(informationalMessage);
			}
		} catch (WPDException exception) {

			List errorParams = new ArrayList();
			String obj = exception.getClass().getName();
			errorParams.add(obj);

			throw new ServiceException(
					"Exception occured in persist method, for updating the BusinessObject NotesToQuestionAttachmentBO, in BCBusinessService",
					errorParams, exception);
		}
		try {
			if (bnftCompNotesToQuestionAttachmentRequest
					.getNotesToQuestionAttachmentVO().isDeleteRequest()) {
				bom.delete(bo, benefitComponentBO,
						bnftCompNotesToQuestionAttachmentRequest.getUser());
				InformationalMessage informationalMessage = new InformationalMessage(
						BusinessConstants.DELETE_QUESTION_NOTE__SUCCESS);
				messageList.add(informationalMessage);
			}
		} catch (WPDException exception) {

			List errorParams = new ArrayList();
			String obj = exception.getClass().getName();
			errorParams.add(obj);

			throw new ServiceException(
					"Exception occured in delete method , for delete the BusinessObject NotesToQuestionAttachmentBO, in BCBusinessService",
					errorParams, exception);

		}
		addMessagesToResponse(bnftCompNotesToQuestionAttachmentResponse,
				messageList);
		return bnftCompNotesToQuestionAttachmentResponse;
	}

	private void addMessagesToResponse(WPDResponse response, List messages) {
		if (null == messages || messages.size() == 0)
			return;
		if (null == response.getMessages())
			response.setMessages(messages);
		else
			response.getMessages().addAll(messages);
	}

	/**
	 * @param request
	 * @return
	 */
	private NotesToQuestionAttachmentBO setValuesToNotesToQuestionAttachmentBO(
			BnftCompNotesToQuestionAttachmentRequest request) {

		NotesToQuestionAttachmentBO notesToQuestionAttachmentBO = new NotesToQuestionAttachmentBO();

//		notesToQuestionAttachmentBO.setRequestType(request
//				.getNotesToQuestionAttachmentVO().getRequestType());
		notesToQuestionAttachmentBO.setBenefitCompId(request
				.getNotesToQuestionAttachmentVO().getBenefitCompId());
		notesToQuestionAttachmentBO.setBnftDefId(Integer.toString(request
				.getNotesToQuestionAttachmentVO().getBnftDefId()));
		notesToQuestionAttachmentBO.setCreatedUser(request.getUser()
				.getUserId());
		notesToQuestionAttachmentBO.setNoteId(request
				.getNotesToQuestionAttachmentVO().getNoteId());
		notesToQuestionAttachmentBO.setNoteOverrideStatus(request
				.getNotesToQuestionAttachmentVO().getNoteOverrideStatus());
		notesToQuestionAttachmentBO.setNoteVersionNumber(request
				.getNotesToQuestionAttachmentVO().getNoteVersionNumber());
		notesToQuestionAttachmentBO.setPrimaryEntityType(request
				.getNotesToQuestionAttachmentVO().getPrimaryEntityType());
		notesToQuestionAttachmentBO.setPrimaryId(request
				.getNotesToQuestionAttachmentVO().getPrimaryId());
		notesToQuestionAttachmentBO.setQuestionId(request
				.getNotesToQuestionAttachmentVO().getQuestionId());
		notesToQuestionAttachmentBO.setSecondaryEntityType(request
				.getNotesToQuestionAttachmentVO().getSecondaryEntityType());
		notesToQuestionAttachmentBO.setSecondaryId(request
				.getNotesToQuestionAttachmentVO().getSecondaryId());

		return notesToQuestionAttachmentBO;
	}
}