/*
 * Created on Sep 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.adminmethod.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.ets.ewpd.am.context.ApplicationContext;
import com.wellpoint.ets.ewpd.am.domain.validation.AdminMethodValidationManager;
import com.wellpoint.wpd.business.adminmethod.adapter.AdminMethodAdapterManager;
import com.wellpoint.wpd.business.adminmethod.builder.AdminMethodBusinessObjectBuilder;
import com.wellpoint.wpd.business.adminmethod.builder.AdminMethodOverrideBusinessObjectBuilder;
import com.wellpoint.wpd.business.contract.locatecriteria.ContractBenefitDefinitionLocateCriteria;
import com.wellpoint.wpd.business.framework.bo.BusinessObjectManagerFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager;
import com.wellpoint.wpd.business.framework.service.ValidationServiceController;
import com.wellpoint.wpd.business.framework.service.WPDBusinessService;
import com.wellpoint.wpd.business.product.locatecriteria.ProductBenefitDefinitionLocateCriteria;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodBO;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodOverrideBO;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodSPSValidationBO;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodTierOverrideBO;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodValidationBO;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodsPopupBO;
import com.wellpoint.wpd.common.adminmethod.request.AdminMethodFetchInvalidDatesegmantRequest;
import com.wellpoint.wpd.common.adminmethod.request.AdminMethodOverrideRequest;
import com.wellpoint.wpd.common.adminmethod.request.AdminMethodSPSRetrieveRequest;
import com.wellpoint.wpd.common.adminmethod.request.AdminMethodSPSValidationRequest;
import com.wellpoint.wpd.common.adminmethod.request.AdminMethodSynchronousValidationRequest;
import com.wellpoint.wpd.common.adminmethod.request.AdminMethodValidationRequest;
import com.wellpoint.wpd.common.adminmethod.request.AdminMethodValidationStatusRequest;
import com.wellpoint.wpd.common.adminmethod.request.GeneralBenefitAdminMethodValidationRequest;
import com.wellpoint.wpd.common.adminmethod.request.OverrideAdminMethodRequest;
import com.wellpoint.wpd.common.adminmethod.request.RetrieveAdminMethodsRequest;
import com.wellpoint.wpd.common.adminmethod.request.SaveAdminMethodRequest;
import com.wellpoint.wpd.common.adminmethod.request.SaveAdminMethodValidationRequest;
import com.wellpoint.wpd.common.adminmethod.response.AdminMethodOverrideResponse;
import com.wellpoint.wpd.common.adminmethod.response.AdminMethodSPSRetrieveResponse;
import com.wellpoint.wpd.common.adminmethod.response.AdminMethodSPSValidationResponse;
import com.wellpoint.wpd.common.adminmethod.response.AdminMethodSynchronousValidationResponse;
import com.wellpoint.wpd.common.adminmethod.response.AdminMethodValidationResponse;
import com.wellpoint.wpd.common.adminmethod.response.AdminMethodValidationStatusResponse;
import com.wellpoint.wpd.common.adminmethod.response.GeneralBenefitAdminMethodValidationResponse;
import com.wellpoint.wpd.common.adminmethod.response.OverrideAdminMethodResponse;
import com.wellpoint.wpd.common.adminmethod.response.RetrieveAdminMethodsResponse;
import com.wellpoint.wpd.common.adminmethod.response.SaveAdminMethodResponse;
import com.wellpoint.wpd.common.adminmethod.response.SaveAdminMethodValidationResponse;
import com.wellpoint.wpd.common.adminmethod.vo.AdminMethodVO;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.contract.bo.DateSegment;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTier;
import com.wellpoint.wpd.common.override.benefit.bo.TierDefinitionBO;
import com.wellpoint.wpd.common.util.BenefitTierUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * Admin method service.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class  AdminMethodService extends WPDBusinessService{

	
	/**
	 * Method to check whether there is any asynchronous process running. 
	 * If running, it will check whether any invalid SPS found.
	 * If no invalid SPS found and process still running, it will return the 
	 * benefit component name and id.
	 * If invalid SPS found, then it will return that status.
	 * 
	 * @param request
	 * @return WPDResponse
	 * @throws ServiceException
	 */
	public WPDResponse execute(AdminMethodValidationStatusRequest request)throws ServiceException{	


	    if("prodStructure".equalsIgnoreCase(request.getEntityType()) || "product".equals(request.getEntityType()) 

	    		|| BusinessConstants.ENTITY_TYPE_CONTRACT.equals(request.getEntityType())){		
	    	AdminMethodValidationStatusResponse response = (AdminMethodValidationStatusResponse) 
	    		WPDResponseFactory.getResponse(WPDResponseFactory.ADMIN_METHODS_VALIDATION_STATUS_RESPONSE);
	    	
	    	boolean processRunningFlag = false;
	    	boolean spsInvalidFlag = false;
	    	
	    	try{
		        // Call the builder
		    	AdminMethodBusinessObjectBuilder builder = new AdminMethodBusinessObjectBuilder();
		        List invalidSPSList = builder.getInvalidSPSList(request.getEntityId(),request.getEntityType(), request.getContractId());
		        
		        // Check if invalid SPS found		
		        if(null != invalidSPSList && !invalidSPSList.isEmpty()){
		        	
	                // Check the flag value for Process Running
		        		AdminMethodValidationBO validationBO = (AdminMethodValidationBO)invalidSPSList.get(0);
		        			
		        		
	        			// Check whether any invalid SPS is present in for the entity
	        			if("I".equals(validationBO.getSpsValidFlag())){
	        				
	        				// Set the invalid SPS flag as true
	        				spsInvalidFlag = true; 
	        			}
	        			
	        			// Check whether any asynchronous process is running
	        			if("T".equals(validationBO.getPrcssRunningFlag())){
	        				
	        				// Set the process running flag as true
	        				processRunningFlag = true;
		        			// Set the benefit component id and name to the response
		        			response.setBenefitComponentId(validationBO.getBenefitComSysId());
		        			response.setBeneftiComponentName(validationBO.getBenefitComName());
	        			} 
		        			
		        		}
		        		
	                // If invalid SPS present, 
	                if(spsInvalidFlag){
	                	response.setStatus(AdminMethodValidationStatusResponse.VALIDATION_ERRORS);
	                }else{
	//                	 See process if running
	                    if(processRunningFlag){
	                    	//if yes user should wait
	                    	response.setStatus(AdminMethodValidationStatusResponse.VALIDATION_WAITING);
	                    }else{
	                    	//if not validation is success.
	                    	response.setStatus(AdminMethodValidationStatusResponse.VALIDATION_SUCCESS);
	                    }
	                }

	                return response;

		        
	    	}catch(SevereException e){
	    		Logger.logError(e);
		        throw new ServiceException("Error in getting validation status",null,e);	
		    }
	    }

		return null;

	}
	
	
	
	
	  /**
	   * Return the response object with the invalid admin method list and the tier data. 
	   * 
	   * @param adminMethodValidationRequest
	   * @return adminMethodValidationResponse
	   * @throws ServiceException
	   */
	  public WPDResponse execute(AdminMethodValidationRequest adminMethodValidationRequest) throws ServiceException{
	  	Logger.logInfo("Entering execute method, AdminMethodValidationRequest in AdminMethodService");
	  	AdminMethodValidationResponse adminMethodValidationResponse = new AdminMethodValidationResponse();
	  	AdminMethodValidationBO adminMethodValidationBO = new AdminMethodValidationBO();
	  	AdminMethodBusinessObjectBuilder builder = new AdminMethodBusinessObjectBuilder();
	  	List validationSPSList = new ArrayList(0);;//CARS:AM1
	  	List validationTierSPSList = new ArrayList(0);//CARS:AM1
	  	
	  	adminMethodValidationBO.setEntitySysId(adminMethodValidationRequest.getEntitySysId());
	  	adminMethodValidationBO.setBenefitComSysId(adminMethodValidationRequest.getBenefitComSysId());
	  	adminMethodValidationBO.setBenefitSysId(adminMethodValidationRequest.getBenefitSysId());
	  	adminMethodValidationBO.setEntityType(adminMethodValidationRequest.getEntityType());
	  	try{		
	  		//CARS:AM1:START 
	  		validationTierSPSList = builder.getTierAdminMethodsForValidation(adminMethodValidationBO);					
	  		validationSPSList = builder.getAdminMethodsForValidation(adminMethodValidationBO);	  		
	  		if(null != validationSPSList && validationSPSList.size()>0 ){
	  			adminMethodValidationResponse.setResultList(validationSPSList);
	  		}
	  		if(null != validationTierSPSList && validationTierSPSList.size()>0 ){
	  			adminMethodValidationResponse.setTieredAdminMethodList(validationTierSPSList);
	  			LocateResults locateResults =null;
		  		BusinessObjectManager bom = getBusinessObjectManager();
				if(adminMethodValidationRequest.getEntityType().equalsIgnoreCase(WebConstants.ENTITY_TYPE_CONTRACT)){
		             ContractBenefitDefinitionLocateCriteria contractBenefitDefinitionLocateCriteria = new ContractBenefitDefinitionLocateCriteria();
		             contractBenefitDefinitionLocateCriteria.setBenefitId(adminMethodValidationBO.getBenefitSysId());
		             contractBenefitDefinitionLocateCriteria.setBenefitComponentId(adminMethodValidationBO.getBenefitComSysId());
		             contractBenefitDefinitionLocateCriteria.setDateSegmentId(adminMethodValidationBO.getEntitySysId());
					 contractBenefitDefinitionLocateCriteria.setAction(1);
					 locateResults = bom.locate(contractBenefitDefinitionLocateCriteria, adminMethodValidationRequest.getUser());
					 if(null != locateResults.getLocateResults() && !locateResults.getLocateResults().isEmpty()){
					 		contractBenefitDefinitionLocateCriteria.setAction(3);
				  			LocateResults criteriaResults = bom.locate(contractBenefitDefinitionLocateCriteria, adminMethodValidationRequest.getUser());
				  			adminMethodValidationResponse.setCriteriaList(criteriaResults.getLocateResults());	  			
					 }	
	  		    }
				else if(adminMethodValidationRequest.getEntityType().equalsIgnoreCase(WebConstants.PRODUCT)){
			  		ProductBenefitDefinitionLocateCriteria productBenefitDefinitionLocateCriteria = new ProductBenefitDefinitionLocateCriteria();
			  		productBenefitDefinitionLocateCriteria.setBenefitId(adminMethodValidationBO.getBenefitSysId());
			  		productBenefitDefinitionLocateCriteria.setProductId(adminMethodValidationBO.getEntitySysId());
			  		productBenefitDefinitionLocateCriteria.setBenefitComponentId(adminMethodValidationBO.getBenefitComSysId());
			  		locateResults = bom.locate(productBenefitDefinitionLocateCriteria, adminMethodValidationRequest.getUser());
			  		if(null != locateResults.getLocateResults() && !locateResults.getLocateResults().isEmpty()){
			  			productBenefitDefinitionLocateCriteria.setType(WebConstants.CRITERIA);
			  			LocateResults criteriaResults = bom.locate(productBenefitDefinitionLocateCriteria, adminMethodValidationRequest.getUser());
			  			adminMethodValidationResponse.setCriteriaList(criteriaResults.getLocateResults());	  			
					}	
				}					
				adminMethodValidationResponse.setBenefitDefinitionsList(locateResults.getLocateResults());	  								                                       
	  		}
	  	}
	  	catch (AdapterException e) {
	  		Logger.logInfo(e);
	  		List logParameters = new ArrayList();
	  		logParameters.add(adminMethodValidationRequest);
	  		String logMessage = "Failed while processing adminMethodValidationRequest";
	  		throw new ServiceException(logMessage, logParameters, e);
	  	}catch (SevereException e) {
	  		Logger.logInfo(e);
	  		List logParameters = new ArrayList();
	  		logParameters.add(adminMethodValidationRequest);
	  		String logMessage = "Failed while processing adminMethodValidationRequest";
	  		throw new ServiceException(logMessage, logParameters, e);
	  	}
	  	catch (WPDException e) {
	  		Logger.logInfo(e);
	  		List logParameters = new ArrayList();
	  		logParameters.add(adminMethodValidationRequest);
	  		String logMessage = "Failed while processing adminMethodValidationRequest";
	  		throw new ServiceException(logMessage, logParameters, e);
	  	}		
	  	Logger.logInfo("Returning execute method, AdminMethodValidationRequest in AdminMethodService");
	  	//CARS:AM1:END

	  	return adminMethodValidationResponse;
	}
        
 
                
	/**
     * Method to retrieve Admin Methods for Popup.
     * 
     * @param adminMethodsRequest
     * @return adminMethodsResponse
     * @throws WPDException
     * @throws AdapterException
     */
    public WPDResponse execute(RetrieveAdminMethodsRequest adminMethodsRequest) throws ServiceException{
        RetrieveAdminMethodsResponse adminMethodsResponse =(RetrieveAdminMethodsResponse)WPDResponseFactory.getResponse(WPDResponseFactory.ADMIN_METHOD_POPUP_RESPONSE);
        AdminMethodsPopupBO adminMethodsPopupBO = new AdminMethodsPopupBO();
        adminMethodsPopupBO.setAdminMethodId(adminMethodsRequest.getAdminMethodsPopupVO().getAdminMethodId());
        adminMethodsPopupBO.setSpsId(adminMethodsRequest.getSpsId());
        adminMethodsPopupBO.setEntityType(adminMethodsRequest.getEntityType());
        adminMethodsPopupBO.setStdDefId(adminMethodsRequest.getStdDefid());
        adminMethodsPopupBO.setAdminId(adminMethodsRequest.getAdminId());
        adminMethodsPopupBO.setBenftId(adminMethodsRequest.getStdBenftId());
        adminMethodsPopupBO.setBenefitCompId(adminMethodsRequest.getBenefitCompId());
        adminMethodsPopupBO.setEntityId(adminMethodsRequest.getEntityId());
        adminMethodsPopupBO.setContractDateSgmntId(adminMethodsRequest.getContractDateSgmntId());
        adminMethodsPopupBO.setProdId(adminMethodsRequest.getProdId());
        adminMethodsPopupBO.setBenefitComponentName(adminMethodsRequest.getBcName());
        //CARS AM1 START
        adminMethodsPopupBO.setBenefitTierSysId(adminMethodsRequest.getBenefitTierSysId());
        //CARS AM1 END
        AdminMethodBusinessObjectBuilder builder = new AdminMethodBusinessObjectBuilder();
        List adminMethods = new ArrayList();
        try {
        	if(adminMethodsRequest.getAction() == WebConstants.ADMIN_METHOD_VIEW_POPUP){
                adminMethods =  builder.getAdminMethodsView(adminMethodsPopupBO);
                adminMethodsResponse.setAdminMethods(adminMethods);
        	}else{
                adminMethods =  builder.getAdminMethodsForPopup(adminMethodsPopupBO);
                //if(null != adminMethods && adminMethods.size() != 0 && !(adminMethods.size() > 50)){
                    adminMethodsResponse.setAdminMethods(adminMethods);
               // } else {
                	
                	 	/*if(adminMethodsRequest.getEntityType().equals(BusinessConstants.STANDARDBENEFIT) ||
                	 			adminMethodsRequest.getEntityType().equals(BusinessConstants.BENEFITCOMP)||
                	 		(adminMethodsRequest.getEntityType().equals(BusinessConstants.PRODUCTSTRUCTURE) && 
                	 				adminMethodsRequest.getBcName().equals(BusinessConstants.GENERAL_BENEFITS) )||
                	 			(adminMethodsRequest.getEntityType().equals(BusinessConstants.PRODUCT) && 
                    	 				adminMethodsRequest.getBcName().equals(BusinessConstants.GENERAL_BENEFITS)) ||
                    	 			(adminMethodsRequest.getEntityType().equals(BusinessConstants.CONTRACT) && 
                        	 				adminMethodsRequest.getBcName().equals(BusinessConstants.GENERAL_BENEFITS))){*/
                	 		/*if((adminMethods.size() > 50))
                	 			adminMethodsResponse.addMessage(new InformationalMessage(WebConstants.ADMIN_METHOD_EXCEEDS_FIFTY));
                	 		else if(null == adminMethods || adminMethods.size() == 0)
                	 			adminMethodsResponse.addMessage(new InformationalMessage(WebConstants.NO_ADMIN_METHOD_FOUND));
     */
                    
                	 	//}
                	 	/*else
                	 			adminMethodsResponse.addMessage(new InformationalMessage(WebConstants.GB_ADMIN_METHOD_APPLICABLE));*/
              // }
        	}
        } catch (AdapterException e) {
			Logger.logError(e);
			List logParameters = new ArrayList();
			logParameters.add(adminMethodsRequest);
			String logMessage = "Failed while processing RetrieveAdminMethodsRequest";
			throw new ServiceException(logMessage, logParameters, e);
        }catch (SevereException e) {
			Logger.logError(e);
			List logParameters = new ArrayList();
			logParameters.add(adminMethodsRequest);
			String logMessage = "Failed while processing RetrieveAdminMethodsRequest";
			throw new ServiceException(logMessage, logParameters, e);
        }
        return adminMethodsResponse;
    }
   
    /**
     * Method to retrieve Admin Methods.
     * @param adminMethodRequest
     * @return WPDResponse
     * @throws ServiceException
     */
    public WPDResponse execute(GeneralBenefitAdminMethodValidationRequest gnrlBenefitAdminMethodRequest) throws ServiceException{
    	GeneralBenefitAdminMethodValidationResponse gnrlBenefitAdminMethodResponse = (GeneralBenefitAdminMethodValidationResponse)WPDResponseFactory.getResponse(WPDResponseFactory.ADMIN_METHOD_RESPONSE);
        AdminMethodBO adminMethodBO = new AdminMethodBO();
        adminMethodBO.setAdministrationId(gnrlBenefitAdminMethodRequest.getAdminMethodLocateCriteria().getAdministrationId());
        AdminMethodBusinessObjectBuilder builder = new AdminMethodBusinessObjectBuilder();
        try {
           List adminMethods =  builder.getSPSNames(adminMethodBO);
           gnrlBenefitAdminMethodResponse.setSpsList(adminMethods);
        } catch (AdapterException e) {
			Logger.logError(e);
			List logParameters = new ArrayList();
			logParameters.add(gnrlBenefitAdminMethodRequest);
			String logMessage = "Failed while processing AdminMethodRequest";
			throw new ServiceException(logMessage, logParameters, e);
        }catch (SevereException e) {
			Logger.logError(e);
			List logParameters = new ArrayList();
			logParameters.add(gnrlBenefitAdminMethodRequest);
			String logMessage = "Failed while processing AdminMethodRequest";
			throw new ServiceException(logMessage, logParameters, e);
        }
        return gnrlBenefitAdminMethodResponse;
    }
    /**
     * Method to save Admin Methods.
     * 
     * @param saveAdminMethodRequest
     * @return saveAdminMethodResponse
     * @throws WPDException
     * @throws AdapterException
     */
    public WPDResponse execute(SaveAdminMethodRequest saveAdminMethodRequest)throws ServiceException {
    	AdminMethodVO adminMethodVO = saveAdminMethodRequest.getAdminMethodVO();
    	SaveAdminMethodResponse saveAdminMethodResponse = (SaveAdminMethodResponse)WPDResponseFactory.getResponse(WPDResponseFactory.SAVE_ADMIN_METHOD_RESPONSE);
		//Creating BusinessObjectManager object
    	AdminMethodBusinessObjectBuilder builder = new AdminMethodBusinessObjectBuilder();
  		List adminMethodBoList = new ArrayList();
		
		List origAdminMethodList=saveAdminMethodRequest.getOrigAdminMethodsList();
		
		boolean success = false;
		int adminId = 0;
		int spsId = 0;
		
    	 try {
    	 	if(null != adminMethodVO)
	    	 	{
    	 	    	List adminIdList = adminMethodVO.getAdminIdList();
	    	 		List spsIdList = adminMethodVO.getSpsIdList();
	    	 		for(int i=0; i<adminIdList.size();i++){ //FIX ME Null check
	    	 		    AdminMethodBO adminMethodBO = new AdminMethodBO();
	    	 		    if(null != adminIdList.get(i) && !adminIdList.get(i).equals("") && !adminIdList.get(i).equals(" ")){
		    	 		    adminId = new Integer(adminIdList.get(i).toString()).intValue();
		    	 			spsId = new Integer(spsIdList.get(i).toString()).intValue();
		    	 			adminMethodBO.setAdminMethodSysId(adminId);
		    	 			adminMethodBO.setSpsId(spsId);
		    	 			adminMethodBO.setAdministrationId(saveAdminMethodRequest.getAdministrationId());
		    	 			adminMethodBO.setBenSysId(saveAdminMethodRequest.getStdBenId());
		    	 			adminMethodBoList.add(adminMethodBO);
	    	 		    }
	    	 		}
	    	 		success = builder.persist(origAdminMethodList,adminMethodBoList,saveAdminMethodRequest.getUser(),true);
	    	 }
    	 	AdminMethodBO retrievemethodBO = new AdminMethodBO();
    	 	retrievemethodBO.setAdministrationId(saveAdminMethodRequest.getAdministrationId());
    	 	adminMethodBoList = builder.getSPSNames(retrievemethodBO);
    	 	saveAdminMethodResponse.setResultList(adminMethodBoList);
            saveAdminMethodResponse.addMessage(new InformationalMessage(WebConstants.ADMIN_METHOD_SAVE_SUCCESS));
        }catch (AdapterException e) {
			Logger.logError(e);
			List logParameters = new ArrayList();
			logParameters.add(saveAdminMethodRequest);
			String logMessage = "Failed while processing SaveAdminMethodRequest";
			throw new ServiceException(logMessage, logParameters, e);
        } catch (SevereException e) {
			Logger.logError(e);
			List logParameters = new ArrayList();
			logParameters.add(saveAdminMethodRequest);
			String logMessage = "Failed while processing SaveAdminMethodRequest";
			throw new ServiceException(logMessage, logParameters, e);
        }    	
    	return saveAdminMethodResponse;   	   	
    }
    /**
     * Method to override Admin Methods.
     * 
     * @param overrideAdminMethodRequest
     * @return overrideAdminMethodResponse
     * @throws WPDException
     * @throws AdapterException
     */
    public WPDResponse execute(
    		SaveAdminMethodValidationRequest saveAdminMethodValidationRequest)
			throws ServiceException{
		
    	
		boolean success = false;
		AdminMethodValidationBO adminMethodValidationBO;
		AdminMethodOverrideBO methodOverrideBO;
		SaveAdminMethodValidationResponse saveAdminMethodValidationResponse = new SaveAdminMethodValidationResponse();
		int sqncNumber = 0;
		
		AdminMethodOverrideBusinessObjectBuilder builder = new AdminMethodOverrideBusinessObjectBuilder();
		AdminMethodBusinessObjectBuilder adminMethodBusinessObjectBuilder = new AdminMethodBusinessObjectBuilder();
		
		try {

			
			adminMethodValidationBO = new AdminMethodValidationBO();
			methodOverrideBO = new AdminMethodOverrideBO();
			
			adminMethodValidationBO.setEntitySysId(saveAdminMethodValidationRequest.getEntitySysId());
			adminMethodValidationBO.setEntityType(saveAdminMethodValidationRequest.getEntityType());
			
			adminMethodValidationBO.setBenefitComSysId(saveAdminMethodValidationRequest.getBenefitComSysId());
			adminMethodValidationBO.setBenefitSysId(saveAdminMethodValidationRequest.getBenefitSysId());
			
			methodOverrideBO.setEntitySysId(saveAdminMethodValidationRequest.getEntitySysId());
			methodOverrideBO.setEntityType(saveAdminMethodValidationRequest.getEntityType());
			methodOverrideBO.setBenefitCompSysId(saveAdminMethodValidationRequest.getBenefitComSysId());
			methodOverrideBO.setBenefitSysId(saveAdminMethodValidationRequest.getBenefitSysId());
			int generalBenefitsAdministrationId = 0;
			List adminMethodBoRetrveList = adminMethodBusinessObjectBuilder
			.getSPSNamesForAdminMethodValidation(methodOverrideBO);
			
			
			
			List validationList = saveAdminMethodValidationRequest.getAdminMethodValidationList();
			List boList = new ArrayList();
			List OverrideBOList = new ArrayList();
			List spsListForGeneralBenefits = new ArrayList();
			List spsList =new ArrayList();
			if(null != validationList ){
				for(int i=0;i<validationList.size();i++){
					SaveAdminMethodValidationRequest request =(SaveAdminMethodValidationRequest )validationList.get(i);
					
					
					AdminMethodValidationBO adminMethodBO = new AdminMethodValidationBO();
					AdminMethodOverrideBO OverrideBO = new AdminMethodOverrideBO();
					adminMethodBO.setEntitySysId(request.getEntitySysId());
					adminMethodBO.setBenefitComSysId(request.getBenefitComSysId());
					adminMethodBO.setEntityType(request.getEntityType());
					adminMethodBO.setBenefitSysId(request.getBenefitSysId());
					adminMethodBO.setBenefitAdminSysId(request.getBenefitAdminSysId());
					adminMethodBO.setSpsId(request.getSpsId());
					adminMethodBO.setAdminMethodId(request.getAdminMethodId());
					
					boList.add(adminMethodBO);
					
					OverrideBO.setEntitySysId(request.getEntitySysId());
					OverrideBO.setBenefitCompSysId(request.getBenefitComSysId());
					OverrideBO.setEntityType(request.getEntityType());
					OverrideBO.setBnftAdmnId(request.getBenefitAdminSysId());
					OverrideBO.setSpsId(request.getSpsId());
					OverrideBO.setAdminMethodSysId(request.getAdminMethodId());
					OverrideBOList.add(OverrideBO);
					
					// If it is General Benefits benefit component, set the benefit administration id 
					// and the affected SPS list to variables for running the asynchronous validation.
					if(saveAdminMethodValidationRequest.getBenefitComponentName().equals("GENERAL BENEFITS")){
						generalBenefitsAdministrationId = request.getBenefitAdminSysId();
						spsListForGeneralBenefits.add(new Integer(request.getSpsId()));
					}
					spsList.add(new Integer(request.getSpsId()));
				}
				
			}
			
			adminMethodValidationBO.setAdminmethodValidationList(boList);
			methodOverrideBO.setValidationBOList(OverrideBOList);
			success = builder.persistAdminMethodsValidation(
					adminMethodValidationBO,
					adminMethodBoRetrveList, saveAdminMethodValidationRequest.getUser());
			
			boolean tearExists= false;
			
			  //CARS:AM2:START{
            //for insert or update tiered admin methods
			AdminMethodTierOverrideBO adminMethodTierOverrideBO =null;
			List tieredAdminMethodList = saveAdminMethodValidationRequest.getTieredAdminMethodList();
			if (null != tieredAdminMethodList  && tieredAdminMethodList.size() > 0) 
			{
				adminMethodTierOverrideBO = new AdminMethodTierOverrideBO();
				adminMethodTierOverrideBO.setEntitySysId(saveAdminMethodValidationRequest.getEntitySysId());			
				adminMethodTierOverrideBO.setEntityType(saveAdminMethodValidationRequest.getEntityType());					
				adminMethodTierOverrideBO.setBenefitCompSysId(saveAdminMethodValidationRequest.getBenefitComSysId());   //watch		

				for(int i = 0; i<tieredAdminMethodList.size() ; i++ )
				{
					AdminMethodTierOverrideBO tieredAdminMethodFromView = (AdminMethodTierOverrideBO) tieredAdminMethodList.get(i);
					adminMethodTierOverrideBO.setTermQuery(tieredAdminMethodFromView.getTermQuery());
					adminMethodTierOverrideBO.setTierSysId(tieredAdminMethodFromView.getTierSysId());
					adminMethodTierOverrideBO.setSpsId(tieredAdminMethodFromView.getSpsId());
					adminMethodTierOverrideBO.setAdminMethodSysId(tieredAdminMethodFromView.getAdminMethodNumber());
					adminMethodTierOverrideBO.setStatus(tieredAdminMethodFromView.getStatus());
					adminMethodTierOverrideBO.setBnftAdmnId(tieredAdminMethodFromView.getBnftAdmnId());		 //watch	
					//List tieredAdminMethodListFromDB = adminMethodBusinessObjectBuilder.getSPSNamesForAdminMethodOverrideInTiers(adminMethodTierOverrideBO);
					success = builder.persistOverriddenTieredAdminMethods(adminMethodTierOverrideBO,saveAdminMethodValidationRequest.getUser());
					//todo:
				}
				/*CARS AM2 START 22/3/2010*/								
				
				if(success){
					if(tieredAdminMethodList != null && tieredAdminMethodList.size()>0)
					{
						 if("contract".equalsIgnoreCase(saveAdminMethodValidationRequest.getEntityType()))
						 		tearExists=true;
					}
				}

					
				}
				
			triggerAdminMethodValidations(saveAdminMethodValidationRequest.getEntitySysId(),saveAdminMethodValidationRequest.getBenefitComponentName(),tearExists, saveAdminMethodValidationRequest.getUser().getUserId());
				
				/*CARS AM2 END 22/3/2010*/
//			}10
			methodOverrideBO = new AdminMethodOverrideBO();
			methodOverrideBO.setEntitySysId(saveAdminMethodValidationRequest.getEntitySysId());
			methodOverrideBO.setEntityType(saveAdminMethodValidationRequest.getEntityType());
			methodOverrideBO.setBnftAdmnId(saveAdminMethodValidationRequest.getBenefitAdminSysId()); //watch		
			methodOverrideBO.setBenefitCompSysId(saveAdminMethodValidationRequest.getBenefitComSysId());//watch		

	
			boolean bcNotFound = true;
			boolean benNotFound = true;
			List standardBenefitList;
			
			//if(saveAdminMethodValidationRequest.getEntityType().equalsIgnoreCase("contract")){
				
			//}
			
			// Check whether current benefit has any invalid Admin Methods
			List validationSPSList = adminMethodBusinessObjectBuilder.getAdminMethodsForValidation(adminMethodValidationBO);
			
			List validationTierSPSList = adminMethodBusinessObjectBuilder.getTierAdminMethodsForValidation(adminMethodValidationBO);
			// If no invalid Admin Methods for current benefit
			if(null == validationSPSList || validationSPSList.isEmpty()){
			    if(null != validationTierSPSList && validationTierSPSList.size()>0){
					saveAdminMethodValidationResponse.setNextBcExists(true);
				}else{				
					// Check whether the current benefit component has any benefits with invalid Admin Methods 
					benNotFound = getBenefitIdForPageDisplayInTree(saveAdminMethodValidationRequest.getBenefitComSysId(), 
							adminMethodValidationBO, saveAdminMethodValidationResponse,
							saveAdminMethodValidationRequest.getBenefitSqncNumber(), benNotFound);
					saveAdminMethodValidationResponse.setNextBcExists(true);
					// If no other invalid Admin Method benefits, get next benefit components
					if(benNotFound){
						// Get the benefit components with invalid admin methods
						List benefitComponents = adminMethodBusinessObjectBuilder.getBenefitComponents(adminMethodValidationBO);
						if(null != benefitComponents && !benefitComponents.isEmpty()){
							for(int i=0; i< benefitComponents.size(); i++){
						
								AdminMethodValidationBO bcValidationBO = (AdminMethodValidationBO)benefitComponents.get(i);
			
								if(bcValidationBO.getAdminMethodId() > saveAdminMethodValidationRequest.getBenefitComSqncNumber()){
								
									saveAdminMethodValidationResponse.setBenefitComponentId(bcValidationBO.getBenefitComSysId());
									
									getBenefitIdForPageDisplayInTree(bcValidationBO.getBenefitComSysId(), 
											adminMethodValidationBO, saveAdminMethodValidationResponse, 0, benNotFound);
									saveAdminMethodValidationResponse.setNextBcExists(true);
									break;
								}else{
									saveAdminMethodValidationResponse.setNextBcExists(false);
								}
							}
						}
					}
				}	
			}else if(validationSPSList!=null && validationSPSList.size()>0){
				saveAdminMethodValidationResponse.setNextBcExists(true);
			}

			if (success) {
				saveAdminMethodValidationResponse
						.addMessage(new InformationalMessage(WebConstants.ADMIN_METHOD_SAVE_SUCCESS)); 
			}
			
		} catch (AdapterException e) {
			Logger.logError(e);
			List logParameters = new ArrayList();
			logParameters.add(saveAdminMethodValidationRequest);
			String logMessage = "Failed while processing overrideAdminMethodRequest";
			throw new ServiceException(logMessage, logParameters, e);
        } catch (SevereException e) {
			Logger.logError(e);
			List logParameters = new ArrayList();
			logParameters.add(saveAdminMethodValidationRequest);
			String logMessage = "Failed while processing overrideAdminMethodRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}
		return saveAdminMethodValidationResponse;
	}
	
    
    



	/**
	 * @param adminMethodValidationBO
	 * @param saveAdminMethodValidationResponse
	 * @param adminMethodBusinessObjectBuilder
	 * @param benNotFound
	 * @return
	 * @throws SevereException
	 * @throws AdapterException
	 */
	private boolean getBenefitIdForPageDisplayInTree(int bcId, AdminMethodValidationBO adminMethodValidationBO, 
			SaveAdminMethodValidationResponse saveAdminMethodValidationResponse, int benefitSqnc, boolean benNotFound) throws SevereException, AdapterException {
		List standardBenefitList;
		adminMethodValidationBO.setBenefitComSysId(bcId);
		AdminMethodBusinessObjectBuilder adminMethodBusinessObjectBuilder = new AdminMethodBusinessObjectBuilder();
		standardBenefitList = adminMethodBusinessObjectBuilder.getStandardBenefits(adminMethodValidationBO);
		if(null != standardBenefitList && !standardBenefitList.isEmpty()){
			
			for(int s=0; s< standardBenefitList.size() ; s++){
				AdminMethodValidationBO benefitValidationBO = (AdminMethodValidationBO) standardBenefitList.get(s);
				if(benNotFound){
					if(benefitSqnc < benefitValidationBO.getAdminMethodId()){
						saveAdminMethodValidationResponse.setBenefitId(benefitValidationBO.getBenefitSysId());
						benNotFound = false;
						break;
					}
				}else{
					saveAdminMethodValidationResponse.setBenefitId(benefitValidationBO.getBenefitSysId());
					break;
				}
				
			}
			
		}
		return benNotFound;
	}
	/**
     *  Save Admin Methods
     */    
    public WPDResponse execute(OverrideAdminMethodRequest overrideAdminMethodRequest)throws ServiceException{
		List adminMethodsId = overrideAdminMethodRequest.getAdminMethodsId();
		List spsId = overrideAdminMethodRequest.getSpsId();
         /** Retrieving array of objects containing spsid and tierid and admin method no **/         
        List tieredAdminMethods = overrideAdminMethodRequest.getAdminMethodListForDB();//CARS AM2
       	boolean success = false;
		AdminMethodOverrideBO methodOverrideBO;
		List adminMethodBoRetrveList = null;
		OverrideAdminMethodResponse overrideAdminMethodResponse = (OverrideAdminMethodResponse) WPDResponseFactory.getResponse(WPDResponseFactory.OVERRIDE_ADMIN_METHOD_RESPONSE);
		
		AdminMethodOverrideBusinessObjectBuilder builder = new AdminMethodOverrideBusinessObjectBuilder();
		AdminMethodBusinessObjectBuilder adminMethodBusinessObjectBuilder = new AdminMethodBusinessObjectBuilder();
		
		AdminMethodOverrideBO individualAdminOverrideBO = new AdminMethodOverrideBO();//FIX ME Move to place where needed
		try {
			methodOverrideBO = new AdminMethodOverrideBO();
			if (null != adminMethodsId) {				
				
				methodOverrideBO.setEntitySysId(overrideAdminMethodRequest.getEntitySysId());			
				methodOverrideBO.setEntityType(overrideAdminMethodRequest.getEntityType());
				methodOverrideBO.setBnftAdmnId(overrideAdminMethodRequest.getBenefitAdminId());
				methodOverrideBO.setBenefitCompSysId(overrideAdminMethodRequest.getBenefitCompSysId());
				methodOverrideBO.setBenefitSysId(overrideAdminMethodRequest.getBenefitSysId());			
				methodOverrideBO.setBenefitComponentName(overrideAdminMethodRequest.getBenefitCompName());
				
				//adminMethodBoRetrveList = adminMethodBusinessObjectBuilder.getSPSNamesForAdminMethodOverride(methodOverrideBO);
				//Added as part of performance stabilization 2011.
				adminMethodBoRetrveList = overrideAdminMethodRequest.getAdminMethodsList();
						
				individualAdminOverrideBO.setEntitySysId(overrideAdminMethodRequest.getEntitySysId());
				individualAdminOverrideBO.setEntityType(overrideAdminMethodRequest.getEntityType());
				individualAdminOverrideBO.setBnftAdmnId(overrideAdminMethodRequest.getBenefitAdminId());
				individualAdminOverrideBO.setBenefitCompSysId(overrideAdminMethodRequest.getBenefitCompSysId());
				individualAdminOverrideBO.setBenefitSysId(overrideAdminMethodRequest.getBenefitSysId());
				individualAdminOverrideBO.setBenefitComponentName(overrideAdminMethodRequest.getBenefitCompName());
				individualAdminOverrideBO.setContractSysId(overrideAdminMethodRequest.getContractSysId());
				
				/** Saving General Admin Methods **/
				success = builder.persistOverriddenAdminMethods(adminMethodsId, spsId,individualAdminOverrideBO,adminMethodBoRetrveList, overrideAdminMethodRequest.getUser());
			}
			
            /** Duplicate Call commented as part of performance stabilization 2011 **/
            /*
				List adminMethodBoList = adminMethodBusinessObjectBuilder.getSPSNamesForAdminMethodOverride(methodOverrideBO);
			*/
			List adminMethodBoList = adminMethodBoRetrveList;
			overrideAdminMethodResponse.setResultList(adminMethodBoList);  
			if (success) {
				if(overrideAdminMethodRequest.isSpsChanged() && 
						BusinessConstants.GENERAL_BENEFITS.equals(overrideAdminMethodRequest.getBenefitCompName())){
						if("contract".equals(overrideAdminMethodRequest.getEntityType())){
							AdminMethodSynchronousValidationRequest validationRqst = new AdminMethodSynchronousValidationRequest();
							validationRqst.setBenefitComponentId(overrideAdminMethodRequest.getBenefitCompSysId());
							validationRqst.setEntityId(overrideAdminMethodRequest.getEntitySysId());
							validationRqst.setEntityType(AdminMethodSynchronousValidationRequest.TYPE_CONTRACT);
							validationRqst.setContractId(overrideAdminMethodRequest.getContractSysId());
							validationRqst.setBenefitId(overrideAdminMethodRequest.getStdBenId());
							validationRqst.setChangedIds(overrideAdminMethodRequest.getChangedIds());
							validationRqst.setLevel(AdminMethodSynchronousValidationRequest.GENERAL_BENEFIT_IN_PRODUCT);
							validationRqst.setBenefitCompName(overrideAdminMethodRequest.getBenefitCompName());
							validationRqst.setBenefitAdministrationId(overrideAdminMethodRequest.getBenefitAdminId());
							validationRqst.setBenefitId(overrideAdminMethodRequest.getStdBenId());
							validationRqst.setProductId(overrideAdminMethodRequest.getProductId());
							validationRqst.setContractId(overrideAdminMethodRequest.getContractSysId());
							AdminMethodSynchronousValidationResponse validationResponse = 
								(AdminMethodSynchronousValidationResponse)new ValidationServiceController().execute(validationRqst);
					      }
						}else if("contract".equalsIgnoreCase(overrideAdminMethodRequest.getEntityType())){
						/** For validating all the SPS 
						 * when we change the Admin Mehtod 
						 * at specific benefit level **/					
						AdminMethodSynchronousValidationRequest validationRqst = new AdminMethodSynchronousValidationRequest();
						validationRqst.setBenefitComponentId(overrideAdminMethodRequest
								.getBenefitCompSysId());
						validationRqst.setEntityId(overrideAdminMethodRequest
								.getEntitySysId());
						validationRqst.setEntityType(AdminMethodSynchronousValidationRequest.TYPE_CONTRACT);
						validationRqst.setContractId(overrideAdminMethodRequest.getContractSysId());
						validationRqst.setBenefitId(overrideAdminMethodRequest.getStdBenId());
						validationRqst.setChangedIds(overrideAdminMethodRequest.getChangedIds());
						validationRqst.setLevel(AdminMethodSynchronousValidationRequest.BENEFIT_VALIDATION);
						validationRqst.setBenefitCompName(overrideAdminMethodRequest.getBenefitCompName());
						validationRqst.setBenefitId(overrideAdminMethodRequest.getStdBenId());
						validationRqst.setProductId(overrideAdminMethodRequest.getProductId());
						validationRqst.setContractId(overrideAdminMethodRequest.getContractSysId());
						AdminMethodSynchronousValidationResponse validationResponse = 
								(AdminMethodSynchronousValidationResponse)new ValidationServiceController().execute(validationRqst);
						}
					overrideAdminMethodResponse.addMessage(new InformationalMessage(WebConstants.ADMIN_METHOD_SAVE_SUCCESS)); 
				}
			
			/** CARS :AM2: START 
            To insert or update tiered admin methods **/
			
			success = false;
			AdminMethodTierOverrideBO adminMethodTierOverrideBO =null;
			if (null != tieredAdminMethods) 
			{
				adminMethodTierOverrideBO = new AdminMethodTierOverrideBO();
				adminMethodTierOverrideBO.setEntitySysId(overrideAdminMethodRequest.getEntitySysId());			
				adminMethodTierOverrideBO.setEntityType(overrideAdminMethodRequest.getEntityType());
				adminMethodTierOverrideBO.setBnftAdmnId(overrideAdminMethodRequest.getBenefitAdminId());				
				adminMethodTierOverrideBO.setBenefitCompSysId(overrideAdminMethodRequest.getBenefitCompSysId());

				for(int i = 0; i<tieredAdminMethods.size() ; i++ )
				{
					AdminMethodTierOverrideBO tieredAdminMethodFromView = (AdminMethodTierOverrideBO) tieredAdminMethods.get(i);
					adminMethodTierOverrideBO.setTermQuery(tieredAdminMethodFromView.getTermQuery());
					adminMethodTierOverrideBO.setTierSysId(tieredAdminMethodFromView.getTierSysId());
					adminMethodTierOverrideBO.setSpsId(tieredAdminMethodFromView.getSpsId());
					adminMethodTierOverrideBO.setAdminMethodSysId(tieredAdminMethodFromView.getAdminMethodNumber());
					adminMethodTierOverrideBO.setStatus(tieredAdminMethodFromView.getStatus());
					
					success = builder.persistOverriddenTieredAdminMethods(adminMethodTierOverrideBO,overrideAdminMethodRequest.getUser());					
				}				
				if(success){
					/** Validation for Tiers **/
					tieredAdminMethods = overrideAdminMethodRequest.getAdminMethodListForDB();
					if(tieredAdminMethods != null && tieredAdminMethods.size()>0)
					{
						List tierSps = new ArrayList(0);
						List tierSysIds = new ArrayList(0); 
						AdminMethodTierOverrideBO tierOverrideBO = null;
						for(int i=0 ; i< tieredAdminMethods.size() ; i++)
						{
							tierOverrideBO = (AdminMethodTierOverrideBO)tieredAdminMethods.get(i);
							if(!tierSps.contains(""+tierOverrideBO.getSpsId()))
								tierSps.add(""+tierOverrideBO.getSpsId());
							if(!tierSysIds.contains(""+tierOverrideBO.getTierSysId()))
								tierSysIds.add(""+tierOverrideBO.getTierSysId());
						}
					   
						if("contract".equalsIgnoreCase(overrideAdminMethodRequest.getEntityType())){
						/** For validating all the SPS 
						 * when we change the Admin Mehtod 
						 * at specific benefit level **/					
							AdminMethodSynchronousValidationRequest validationRqst = new AdminMethodSynchronousValidationRequest();
							validationRqst.setBenefitComponentId(overrideAdminMethodRequest.getBenefitCompSysId());
							validationRqst.setEntityId(overrideAdminMethodRequest.getEntitySysId());
							validationRqst.setEntityType(AdminMethodSynchronousValidationRequest.TYPE_CONTRACT);
							validationRqst.setContractId(overrideAdminMethodRequest.getContractSysId());
							validationRqst.setBenefitId(overrideAdminMethodRequest.getStdBenId());
							validationRqst.setChangedIds(new ArrayList(0));
							validationRqst.setChangedTiers(tierSps);
							validationRqst.setChangedTierSysIds(tierSysIds);
							validationRqst.setLevel(AdminMethodSynchronousValidationRequest.BENEFIT_VALIDATION);
							validationRqst.setBenefitCompName(overrideAdminMethodRequest.getBenefitCompName());
							validationRqst.setBenefitId(overrideAdminMethodRequest.getStdBenId());
							validationRqst.setProductId(overrideAdminMethodRequest.getProductId());
							validationRqst.setContractId(overrideAdminMethodRequest.getContractSysId());
							AdminMethodSynchronousValidationResponse validationResponse = 
								(AdminMethodSynchronousValidationResponse)new ValidationServiceController().execute(validationRqst);
						}
					}																												
				    overrideAdminMethodResponse.addMessage(new InformationalMessage(WebConstants.ADMIN_METHOD_TIER_SAVE_SUCCESS)); 
				}
			}	
			/** CARS :AM2: END **/
		} catch (AdapterException e) {
			Logger.logError(e);
			List logParameters = new ArrayList();
			logParameters.add(overrideAdminMethodRequest);
			String logMessage = "Failed while processing overrideAdminMethodRequest";
			throw new ServiceException(logMessage, logParameters, e);
        } catch (SevereException e) {
			Logger.logError(e);
			List logParameters = new ArrayList();
			logParameters.add(overrideAdminMethodRequest);
			String logMessage = "Failed while processing overrideAdminMethodRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}
		return overrideAdminMethodResponse;
	}
	
	/**
	 * Method to get Business Object Manager
	 * 
	 * @return bom BusinessObjectManager
	 */
	private BusinessObjectManager getBusinessObjectManager() {
		BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory) ObjectFactory
		.getFactory(ObjectFactory.BOM);
		BusinessObjectManager bom = bomf.getBusinessObjectManager();
		return bom;
	}
	
	/**
     * Method to override Admin Methods.
     * 
     * This method retrieves admin methods information corresponding to general and tiered lines in the current benefit. It gets data like admin 
     * methods corresponding to general lines and tier information like tier definition info, criteria info, and sps and admin
     * methods corresponding to the terms resides inside each tier criteria.
     * Retrieval of data for product and contract modules is done here.   
     */	
    public WPDResponse execute(AdminMethodOverrideRequest adminMethodOverrideRequest) throws ServiceException{
    	boolean flag  = true;
    	AdminMethodOverrideResponse adminMethodOverrideResponse = (AdminMethodOverrideResponse)WPDResponseFactory.getResponse(WPDResponseFactory.ADMIN_METHOD_OVERRIDE_RESPONSE);
    	AdminMethodOverrideBO adminMethodOverrideBO = new AdminMethodOverrideBO();
    	adminMethodOverrideBO.setEntitySysId(adminMethodOverrideRequest.getAdminMethodLocateCriteria().getEntityId());//FIX ME Can use local for locate criteria
       	adminMethodOverrideBO.setBenefitCompSysId(adminMethodOverrideRequest.getAdminMethodLocateCriteria().getBenefitComponentId());
    	adminMethodOverrideBO.setEntityType(adminMethodOverrideRequest.getAdminMethodLocateCriteria().getEntityType()); 
    	adminMethodOverrideBO.setBenefitSysId(adminMethodOverrideRequest.getAdminMethodLocateCriteria().getBenefitDefenitionId());
    	adminMethodOverrideBO.setBnftAdmnId(adminMethodOverrideRequest.getAdminMethodLocateCriteria().getAdministrationId());
    	adminMethodOverrideBO.setBenefitComponentName(adminMethodOverrideRequest.getAdminMethodLocateCriteria().getBenefitComponentName());
        	
    	AdminMethodBusinessObjectBuilder builder = new AdminMethodBusinessObjectBuilder();
    	try
		{
    		/** Retrieving General Admin Methods **/
    		
    		List adminMethods; 			
			adminMethods = builder.getSPSNamesForAdminMethodOverride(adminMethodOverrideBO);
			adminMethodOverrideResponse.setSpsList(adminMethods);
			
			if(adminMethodOverrideRequest.getAdminMethodLocateCriteria().isDatafeedFlag()){
				HashMap spsAdminMethodListMap;
				spsAdminMethodListMap = builder.getSPSNamesForAdminMethodOverrideForDatafeed(adminMethodOverrideBO);
				spsAdminMethodListMap.put("nonTieredAdminMethodList", adminMethods);
				adminMethodOverrideResponse.setSpsAdminMethodListMap(spsAdminMethodListMap);
				flag  = false;
			}
			
	        /** CARS:AM2:START 
			 tiered admin methods
			 retrieving information for contract module. **/
			
			if(flag){
				if(BusinessConstants.ENTITY_TYPE_CONTRACT.equalsIgnoreCase(adminMethodOverrideBO.getEntityType()))
				{
		              if(adminMethods!=null && adminMethods.size()!=0)
		              {  	              	
		              	ContractBenefitDefinitionLocateCriteria contractBenefitDefinitionLocateCriteria = new ContractBenefitDefinitionLocateCriteria();
		              	contractBenefitDefinitionLocateCriteria.setBenefitId(adminMethodOverrideBO.getBenefitSysId());
		              	contractBenefitDefinitionLocateCriteria.setBenefitComponentId(adminMethodOverrideBO.getBenefitCompSysId());
						contractBenefitDefinitionLocateCriteria.setDateSegmentId(adminMethodOverrideBO.getEntitySysId()); 

						BusinessObjectManager bom = getBusinessObjectManager();
		              	
		              	/*** Commented for performance improvement stabilization 2011 ***/
		            	/*
		            	contractBenefitDefinitionLocateCriteria.setAction(1);//request.getAction());
		            	LocateResults locateResults = bom.locate(contractBenefitDefinitionLocateCriteria, adminMethodOverrideRequest.getUser());
		            	adminMethodOverrideResponse.setBenefitDefinitionsList(locateResults.getLocateResults()); 
		            	*/
		            	
		            	AdminMethodTierOverrideBO adminMethodTierOverrideBO =new AdminMethodTierOverrideBO();
		            	adminMethodTierOverrideBO.setEntitySysId(adminMethodOverrideBO.getEntitySysId());
		            	adminMethodTierOverrideBO.setEntityType(adminMethodOverrideBO.getEntityType());
		            	adminMethodTierOverrideBO.setBnftAdmnId(adminMethodOverrideBO.getBnftAdmnId());
		            	adminMethodTierOverrideBO.setBenefitCompSysId(adminMethodOverrideBO.getBenefitCompSysId());
		            	adminMethodTierOverrideBO.setBenefitSysId(adminMethodOverrideBO.getBenefitSysId());
		            	
		            	/*** Commented for performance improvement stabilization 2011 ***/
		            	/*if(null != locateResults.getLocateResults() && !locateResults.getLocateResults().isEmpty())
		            	{*/
		            	contractBenefitDefinitionLocateCriteria.setAction(3);
		            	LocateResults criteriaResults = bom.locate(contractBenefitDefinitionLocateCriteria, adminMethodOverrideRequest.getUser());
		            	adminMethodOverrideResponse.setCriteriaList(criteriaResults.getLocateResults());
		            	if(null != criteriaResults.getLocateResults() && !criteriaResults.getLocateResults().isEmpty())
		            	{            			
		            		contractBenefitDefinitionLocateCriteria.setAction(5);
		            		contractBenefitDefinitionLocateCriteria.setTierSysIdList(getTierList(criteriaResults.getLocateResults()));
		                    LocateResults lvlLineResults = bom.locate(contractBenefitDefinitionLocateCriteria, adminMethodOverrideRequest.getUser());
		                    List lvlLineList = BenefitTierUtil.getLvlLineListForTerms(lvlLineResults.getLocateResults());
	                        List adminMethodsInTier =null;
	                        List tierProductFamily= new ArrayList(0);
	                           
		                    for(int i = 0 ; i < lvlLineList.size(); i++)
		                     {
		                       BenefitTier benefitTier = (BenefitTier)lvlLineList.get(i);
		                       adminMethodTierOverrideBO.setTierSysId(benefitTier.getBenefitTierSysId());
		                       adminMethodTierOverrideBO.setTermQuery(benefitTier.getTermsQuery());	
		                       adminMethodTierOverrideBO.setTermPVAMap(benefitTier.getTermsPVAMap());
		                       adminMethodsInTier = builder.getSPSNamesForAdminMethodOverrideInTiers(adminMethodTierOverrideBO);                                
		                          
		                       for(int j=0 ; j<adminMethodsInTier.size() ; j++){		                           	  		                           	
		                           AdminMethodTierOverrideBO tierOverrideBO = (AdminMethodTierOverrideBO)adminMethodsInTier.get(j);
		                           if( !tierProductFamily.contains(tierOverrideBO.getProductFamily())) {
		                           	tierProductFamily.add(tierOverrideBO.getProductFamily());
		                           	}		                           	
		                       }
		                           
		                       benefitTier.setAdminMethods(adminMethodsInTier);
		                     }
		                    if(tierProductFamily.contains("HMO") && tierProductFamily.contains("PPO")) {
		                        adminMethodOverrideResponse.setTierPOS(true);	
		                    }
		                    adminMethodOverrideResponse.setTierProductFamily(tierProductFamily);
		                    adminMethodOverrideResponse.setTieredAdminMethodList(lvlLineList);                            
		            		}
		            	//}
		            }
				}
				
			/** Retrieving information for product module.**/
			
			else if(adminMethodOverrideBO.getEntityType().equalsIgnoreCase("PRODUCT"))
			{
              if(adminMethods!=null && adminMethods.size()!=0)
              {              
            	ProductBenefitDefinitionLocateCriteria productBenefitDefinitionLocateCriteria = new ProductBenefitDefinitionLocateCriteria();
            	productBenefitDefinitionLocateCriteria.setBenefitId(adminMethodOverrideBO.getBenefitSysId());
            	productBenefitDefinitionLocateCriteria.setProductId(adminMethodOverrideBO.getEntitySysId());
            	productBenefitDefinitionLocateCriteria.setBenefitComponentId(adminMethodOverrideBO.getBenefitCompSysId());
            	productBenefitDefinitionLocateCriteria.setBenefitLevelHideFlag("F");//adminMethodOverrideRequest.getBenefitLevelHideFlag());
            	productBenefitDefinitionLocateCriteria.setBenefitLineHideFlag("F"); //adminMethodOverrideRequest.getBenefitLineHideFlag());
            	
            	BusinessObjectManager bom = getBusinessObjectManager();
            	
            	/** Commented as part of eWPD Stabilization fix 2011 **/
            	/*
            	LocateResults locateResults = bom.locate(productBenefitDefinitionLocateCriteria, adminMethodOverrideRequest.getUser());
            	adminMethodOverrideResponse.setBenefitDefinitionsList(locateResults.getLocateResults()); 
            	*/
            	
            	AdminMethodTierOverrideBO adminMethodTierOverrideBO =new AdminMethodTierOverrideBO();
            	adminMethodTierOverrideBO.setEntitySysId(adminMethodOverrideBO.getEntitySysId());
            	adminMethodTierOverrideBO.setEntityType(adminMethodOverrideBO.getEntityType());
            	adminMethodTierOverrideBO.setBnftAdmnId(adminMethodOverrideBO.getBnftAdmnId());
            	adminMethodTierOverrideBO.setBenefitCompSysId(adminMethodOverrideBO.getBenefitCompSysId());

            	/*if(null != locateResults.getLocateResults() && !locateResults.getLocateResults().isEmpty())
            	{*/
            	productBenefitDefinitionLocateCriteria.setType("Criteria");
            	LocateResults criteriaResults = bom.locate(productBenefitDefinitionLocateCriteria, adminMethodOverrideRequest.getUser());
            	adminMethodOverrideResponse.setCriteriaList(criteriaResults.getLocateResults());
            	if(null != criteriaResults.getLocateResults() && !criteriaResults.getLocateResults().isEmpty())
            	{
            		
            		productBenefitDefinitionLocateCriteria.setType("Level");
                   productBenefitDefinitionLocateCriteria.setTierSysIdList(getTierList(criteriaResults.getLocateResults()));
                   LocateResults lvlLineResults = bom.locate(productBenefitDefinitionLocateCriteria, adminMethodOverrideRequest.getUser());
                   List lvlLineList = BenefitTierUtil.getLvlLineListForTerms(lvlLineResults.getLocateResults());
                   List adminMethodsInTier =null;
                   
                  for(int i = 0 ; i < lvlLineList.size(); i++)
                    {
                     BenefitTier benefitTier = (BenefitTier)lvlLineList.get(i);
                     adminMethodTierOverrideBO.setTierSysId(benefitTier.getBenefitTierSysId());
                     adminMethodTierOverrideBO.setTermQuery(benefitTier.getTermsQuery());
                     adminMethodsInTier = builder.getSPSNamesForAdminMethodOverrideInTiers(adminMethodTierOverrideBO);                                
                     benefitTier.setAdminMethods(adminMethodsInTier);
                    }
                   /*Commented above part and new retrieval call added to fetch tiered AM's as part of Stabilization 2011*/
                   //List adminMethodsInTier = builder.getTieredAMsForProduct(adminMethodTierOverrideBO);
                   //adminMethodOverrideResponse.setTieredAdminMethodList(adminMethodsInTier); 
                   /*Commented above part and new retrieval call added to fetch tiered AM's as part of Stabilization 2011 - END*/
                  
                  adminMethodOverrideResponse.setTieredAdminMethodList(lvlLineList); 
            	}
            	//}
              }   
			}	
		}
		 /** CARS:AM2:END **/		
    	} catch (AdapterException e1) {
			Logger.logError(e1);
			List logParameters = new ArrayList();
			logParameters.add(adminMethodOverrideRequest);
			String logMessage = "Failed while processing AdminMethodOverrideRequest";
			throw new ServiceException(logMessage, logParameters, e1);
		}catch (SevereException e) {
			Logger.logError(e);
			List logParameters = new ArrayList();
			logParameters.add(adminMethodOverrideRequest);
			String logMessage = "Failed while processing AdminMethodOverrideRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}catch (WPDException ex)
	    {
		      throw new ServiceException("Exception occured while BOM call", null, ex);
		}
    	return adminMethodOverrideResponse;    	
    }
    
    /**
     * Service to get the grouped List of Invalid references
     * @param adminMethodSPSRetrieveRequest
     * @return
     * @throws ServiceException
     */ 
    public WPDResponse execute(AdminMethodSPSRetrieveRequest adminMethodSPSRetrieveRequest)throws ServiceException{	
    	
    	AdminMethodSPSRetrieveResponse adminMethodSPSRetrieveResponse = (AdminMethodSPSRetrieveResponse)WPDResponseFactory
		.getResponse(WPDResponseFactory.ADMIN_METHOD_SPS_VALID_RESPONSE);
      	
    	AdminMethodBusinessObjectBuilder builder = new AdminMethodBusinessObjectBuilder();
    	AdminMethodSPSValidationBO adminMethodSPSValidationBO= new AdminMethodSPSValidationBO();
    	try {
    		
    		   /*CARS AM2 START 23/3/2010 TIERING IN SPS POPUP*/
    		ContractBenefitDefinitionLocateCriteria contractBenefitDefinitionLocateCriteria = new ContractBenefitDefinitionLocateCriteria();
			contractBenefitDefinitionLocateCriteria.setDateSegmentId(adminMethodSPSRetrieveRequest.getEntityId());
			contractBenefitDefinitionLocateCriteria.setBenefitComponentId(adminMethodSPSRetrieveRequest.getBenCompSysId());
			contractBenefitDefinitionLocateCriteria.setBenefitId(adminMethodSPSRetrieveRequest.getBenSysyId());
			contractBenefitDefinitionLocateCriteria.setAction(3);
			
			BusinessObjectManager bom = getBusinessObjectManager();
			LocateResults criteriaResults = bom.locate(contractBenefitDefinitionLocateCriteria, adminMethodSPSRetrieveRequest.getUser());
			List tierCriteriaList = criteriaResults.getLocateResults();
			adminMethodSPSRetrieveResponse.setTierCriteriaList(tierCriteriaList);			
    		   /*CARS AM2 END 23/3/2010  TIERING IN SPS POPUP*/
       		adminMethodSPSValidationBO.setEntityId(adminMethodSPSRetrieveRequest.getEntityId());
    		adminMethodSPSValidationBO.setProdId(adminMethodSPSRetrieveRequest.getProdSysId());
    		adminMethodSPSValidationBO.setBenCompId(adminMethodSPSRetrieveRequest.getBenCompSysId());
    		adminMethodSPSValidationBO.setBenId(adminMethodSPSRetrieveRequest.getBenSysyId());
    		adminMethodSPSValidationBO.setBenAdminId(adminMethodSPSRetrieveRequest.getBenAdminId());
    		
    		
			List amParamList=builder.getAdminMethodSPSParameters(adminMethodSPSValidationBO);
    		Map amParamMap=builder.getTieredAdminMethodSPSParameters(adminMethodSPSValidationBO,tierCriteriaList);
			adminMethodSPSRetrieveResponse.setAdminMethodSPSParameterList(amParamList);
    		adminMethodSPSRetrieveResponse.setAdminMethodSPSParameterMap(amParamMap);
						
		}
    	catch (SevereException e) {
			Logger.logError(e);
			List logParameters = new ArrayList();
			logParameters.add(adminMethodSPSRetrieveResponse);
			String logMessage = "Failed while processing AdminMethodSPSValidationRequest";
			throw new ServiceException(logMessage, logParameters, e);
		} catch (WPDException ex) {
			throw new ServiceException("Exception occured while BOM call",
					null, ex);
		}
    	
    	return adminMethodSPSRetrieveResponse;
    
    }
    
    /**
     * Service to get the Admin Method Validation status
     * 
     * @param adminMethodOverrideRequest
     * @return adminMethodOverrideRequest
     * @throws WPDException
     */
	
    public WPDResponse execute(AdminMethodSPSValidationRequest adminMethodSPSValidationRequest) throws ServiceException{
    	AdminMethodSPSValidationResponse adminMethodSPSValidationResponse = (AdminMethodSPSValidationResponse)WPDResponseFactory
			.getResponse(WPDResponseFactory.ADMIN_MTHD_SPS_VALD_RESPONSE);
    	AdminMethodBusinessObjectBuilder builder = new AdminMethodBusinessObjectBuilder();
    	try{
    		int invalidSPSCnt=0;
    		invalidSPSCnt = builder.getAdminMethodSPSValidationStatus(adminMethodSPSValidationRequest.getContractSysId());
    		if(invalidSPSCnt>0)
    		adminMethodSPSValidationResponse.setStatus(adminMethodSPSValidationResponse.VALIDATION_FAIL);
    		else
    		adminMethodSPSValidationResponse.setStatus(adminMethodSPSValidationResponse.VALIDATION_SUCCESS);	
    	}catch (SevereException e) {
			Logger.logError(e);
			List logParameters = new ArrayList();
			logParameters.add(adminMethodSPSValidationRequest);
			String logMessage = "Failed while processing AdminMethodSPSValidationRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}

    	return adminMethodSPSValidationResponse;    	

    }//CARS START
    private List getTierList(List criteriaListFrmDB){    	
    	List tierSysIdList = new ArrayList();    	
    	TierDefinitionBO oldTierDef = (TierDefinitionBO)criteriaListFrmDB.get(0);
    	Integer oldCntrctTierId= new Integer(oldTierDef.getTierSysId());    	
    	tierSysIdList.add(oldCntrctTierId);    	
    	for(int i=1;i<criteriaListFrmDB.size();i++){    		
        	TierDefinitionBO newTierDef = (TierDefinitionBO)criteriaListFrmDB.get(i);
        	Integer newCntrctTierId = new Integer(newTierDef.getTierSysId());        	
        	if(oldCntrctTierId.intValue() != newCntrctTierId.intValue()){        		
        		tierSysIdList.add(newCntrctTierId);        	}
        	oldCntrctTierId = newCntrctTierId;
    	}    	
    	return tierSysIdList;
    }
    //END CARS
    /*   WLPRD00444546  changes starts */ 
    public WPDResponse execute(AdminMethodFetchInvalidDatesegmantRequest datesegmantRequest) throws ServiceException{
    	AdminMethodValidationResponse adminMethodValidationResponse = new AdminMethodValidationResponse();
    	List dateSegmentList=null;
    	AdminMethodBusinessObjectBuilder adminMethodBusinessObjectBuilder = new AdminMethodBusinessObjectBuilder();
        AdminMethodValidationBO adminMethodValidationBO= new AdminMethodValidationBO();
 	   
        adminMethodValidationBO.setEntitySysId(Integer.parseInt(datesegmantRequest.getContractId()));
        adminMethodValidationBO.setEntityType(datesegmantRequest.getEntityType());
        
        
        try {
            //fteches the standard benefit details
        	dateSegmentList=adminMethodBusinessObjectBuilder.getDateSegments(adminMethodValidationBO);
        	adminMethodValidationResponse.setResultList(dateSegmentList);
     	
            
        }  catch(SevereException e)
        {
			Logger.logError(e);
        }
        catch(AdapterException e)
        {
			Logger.logError(e);
        }
    	return adminMethodValidationResponse;    	

    }
    /*   WLPRD00444546  changes ends */ 
//  For Tier Adminmethod Map to List Added by Krishnakumar as part of eWPD Stabilization 
	/*private Map generateListsFromMap(Map amLineIds){
		Map listMap = new LinkedHashMap();
		
		if(amLineIds!=null && amLineIds.size()>0){
				
		Set b  = amLineIds.keySet();
		Iterator itSL = b.iterator();
					
		String tempFullStr="";
		String tempStr="";
		while(itSL.hasNext()){
			List tempList=new ArrayList();
			Object keyVal =  itSL.next();
			if(amLineIds.get(keyVal)!=null && !amLineIds.get(keyVal).equals(null)){
			tempList=(ArrayList)amLineIds.get(keyVal);
			if(tempList.size()>0){
			Iterator tempListItr = tempList.iterator();
			while(tempListItr.hasNext()){
				if (tempStr == "") {
					tempStr = tempListItr.next().toString();
				} else {
					tempStr = tempStr + "," + tempListItr.next();
				}
			}
			if (tempFullStr == "") {
				tempFullStr = keyVal + ":" + tempStr;
			} else {
				tempFullStr = tempFullStr + "~" + keyVal + ":"
						+ tempStr;
			}
			tempStr="";
		   }
		  }
		 }
		listMap.put("0",tempFullStr);
		}
		return listMap;
	}*/
	/**
	 * @param entityId
	 * @param b
	 */

	private void triggerAdminMethodValidations(int  dateSegmentId, String benefitComponentName,boolean  validateTiers, String userId) {
		
		if(benefitComponentName.equalsIgnoreCase("GENERAL BENEFITS"))
			insertValuesIntoAffectedSps(dateSegmentId,true);
		
		ApplicationContext context = ApplicationContext.createApplicationContext();
		
		//System.out.println("----------- Start of Validation"+ new Date());
		Logger.logInfo("----------- End of Validation"+ new Date());
		AdminMethodValidationManager validationManager  = (AdminMethodValidationManager)context.getContext().getBean("adminMethodValidationManager");
		validationManager.validate(new long[]{dateSegmentId}, userId);
		//System.out.println("----------- End of Validation"+ new Date());
		Logger.logInfo("----------- End of Validation"+ new Date());
	}
	private void insertValuesIntoAffectedSps(int dateSegmentId,boolean validateTiers){
		
		AdminMethodAdapterManager adminMethodAdapterManager= new AdminMethodAdapterManager();
		adminMethodAdapterManager.insertValuesIntoAffectedSps(dateSegmentId, validateTiers);
	}
	
    
    
}
