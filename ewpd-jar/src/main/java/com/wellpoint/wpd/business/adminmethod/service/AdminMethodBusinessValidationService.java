/*
 * AdminMethodBusinessValidationService.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.adminmethod.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.adminmethod.builder.AdminMethodBusinessObjectBuilder;
import com.wellpoint.wpd.business.adminmethod.locatecriteria.AdminMethodValidationThread;
import com.wellpoint.wpd.business.adminmethod.locatecriteria.AdminMethodValidationThreadForProduct;
import com.wellpoint.wpd.business.contract.builder.ContractTreeBuilder;
import com.wellpoint.wpd.business.contract.locatecriteria.DateSegmentLocateCriteria;
import com.wellpoint.wpd.business.contract.locateresult.ContractLocateResult;
import com.wellpoint.wpd.business.framework.bo.BusinessObjectManagerFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager;
import com.wellpoint.wpd.business.framework.service.WPDBusinessValidationService;
import com.wellpoint.wpd.business.product.builder.ProductTreeBuilder;
import com.wellpoint.wpd.business.productstructure.builder.ProductStructureBusinessObjectBuilder;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodBO;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodsPopupBO;
import com.wellpoint.wpd.common.adminmethod.request.AdminMethodSynchronousValidationRequest;
import com.wellpoint.wpd.common.adminmethod.request.GeneralBenefitAdminMethodValidationRequest;
import com.wellpoint.wpd.common.adminmethod.response.AdminMethodSynchronousValidationResponse;
import com.wellpoint.wpd.common.adminmethod.response.GeneralBenefitAdminMethodValidationResponse;
import com.wellpoint.wpd.common.benefitcomponent.tree.bo.TreeBenefitComponent;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.contract.tree.bo.ContractTreeProducts;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.product.tree.bo.ProductTreeBenefitComponents;
import com.wellpoint.wpd.common.product.tree.bo.ProductTreeStandardBenefits;
import com.wellpoint.wpd.common.productstructure.tree.bo.ProdStructureTreeBenefitCmpnts;
import com.wellpoint.wpd.common.productstructure.tree.bo.ProdStructureTreeStandardBenefits;
import com.wellpoint.wpd.web.util.WPDStringUtil;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AdminMethodBusinessValidationService  extends
WPDBusinessValidationService {
    List messageList = null;
    private List messageListForProduct ; 
    HashMap criteriaMap = new HashMap();
    List generalBenefitAnsList = new ArrayList();
    List generalBenefitSPSList = new ArrayList();
    private boolean validationForContract;
    
   
    /**
     * @param adminMethodRequest
     * @return WPDResponse
     * @throws SevereException
     * @throws WPDException
     */
    public WPDResponse execute(GeneralBenefitAdminMethodValidationRequest gnrlBenefitAdminMethodRequest)
            throws SevereException ,WPDException{
        
    	GeneralBenefitAdminMethodValidationResponse gnrlBenefitAdminMethodResponse = (GeneralBenefitAdminMethodValidationResponse)WPDResponseFactory.getResponse(WPDResponseFactory.ADMIN_METHOD_RESPONSE);
        String entityType = gnrlBenefitAdminMethodRequest.getEntityType();
        boolean validation = true;
        /**
         * The code for general benefit admin method validation for standard benefit and benefit component are obsolete and is be removed.
         *
         */
       /* if("STANDARDBENEFIT".equals(entityType)){
            criteriaMap.put("entityType","BENEFIT");
            validation = this.validateBenefitAdminMethod(adminMethodRequest.getEntityId(),adminMethodRequest.getBenefitName());
        }else if ("BENEFITCOMP".equals(entityType)){
            criteriaMap.put("entityId",new Integer(adminMethodRequest.getEntityId()));
            criteriaMap.put("entityType","BENEFITCOMP");
            validation = this.getBenefitIdFromBenefitComp(adminMethodRequest.getEntityId(),adminMethodRequest.getBenefitComponentName());
        } */
        
        // Check for General Benefits Mandatory check in the following cases
        if(BusinessConstants.ENTITY_TYPE_PRODUCT.equals(entityType)){
            criteriaMap.put("entityType",BusinessConstants.ENTITY_TYPE_PRODUCT);
            validation=this.getGeneralBenefitsBenefitComponentIdFromProduct(gnrlBenefitAdminMethodRequest.getEntityId());
        }else if("CONTRACT".equals(entityType)){
            criteriaMap.put("entityType","CONTRACT");
        	validation = this.getDateSegmentId(gnrlBenefitAdminMethodRequest);
    	}
      
        if(!validation){
        	gnrlBenefitAdminMethodResponse = addMessagesToResponse(gnrlBenefitAdminMethodResponse);
        }
        
        return gnrlBenefitAdminMethodResponse;
    }
    
    
	/**
     * This method to validate the admin methods corresponding the entity sys id.
     * @param entitySysId
     * @return
     * @throws SevereException
     * @throws WPDException
     */
    private boolean validateAdminMethod(int entitySysId)throws SevereException,WPDException
    {
    	boolean validation = true;  
    	try{
    		this.messageList=new ArrayList();
	        AdminMethodBusinessObjectBuilder 
					adminMethodBusinessObjectBuilder = 
						new AdminMethodBusinessObjectBuilder();
	        List message = adminMethodBusinessObjectBuilder.
					validateAdminMethod(entitySysId);	
	        
	        if(null != message && !message.isEmpty()){
	        	AdminMethodsPopupBO adminMethodsPopupBO
							= (AdminMethodsPopupBO) message.get(0);
	        	// Get the details of which benefit validation breaks
	        	ErrorMessage errorMessage=null;
	        	if(adminMethodsPopupBO.getFlagId()==1){
	        		errorMessage =
		        		new ErrorMessage("all.superprocess.Steps.notselected");
		        	errorMessage.setParameters(new String[] {
							adminMethodsPopupBO.getBenefitName(),
							adminMethodsPopupBO.getBenAdmnDateRange(),	
							adminMethodsPopupBO.getBenefitComponentName()});
		        	

	        	}else if(adminMethodsPopupBO.getFlagId()==2){
	        		    errorMessage =
		        		new ErrorMessage("admin.method.filter.failed");
		        	errorMessage.setParameters(new String[] {
							adminMethodsPopupBO.getBenefitName(),
							adminMethodsPopupBO.getBenAdmnDateRange(),	
							adminMethodsPopupBO.getBenefitComponentName(),	
							adminMethodsPopupBO.getSpsName()});
	        	}
	    

	        	this.messageList.add(errorMessage);
	        	validation = false;
	        }else{
	        	validation = true;
	        }
		    
	    }catch(Exception e){
	        Logger.logError(e);
	    }
	    
	    return validation;
    }    
    
    private GeneralBenefitAdminMethodValidationResponse addMessagesToResponse(GeneralBenefitAdminMethodValidationResponse gnrlBenefitAdminMethodResponse)throws SevereException
    {
    	gnrlBenefitAdminMethodResponse.setMessages(this.messageList);
        return gnrlBenefitAdminMethodResponse;
    }
    private boolean getDateSegmentId(GeneralBenefitAdminMethodValidationRequest gnrlBenefitAdminMethodRequest)
    throws WPDException{
        
        DateSegmentLocateCriteria locateCriteriaDomain = new DateSegmentLocateCriteria();
		LocateResults locateResultsDomain;
		locateCriteriaDomain.setContractSysId(gnrlBenefitAdminMethodRequest.getContractId());
		BusinessObjectManager bom = this.getBusinessObjectManager();
		locateResultsDomain = bom.locate(locateCriteriaDomain, gnrlBenefitAdminMethodRequest.getUser());
		int dateSegmtCount = locateResultsDomain.getLocateResults().size();
		boolean validation = true;
        AdminMethodValidationThreadForProduct adminMethodValidationThreadForProduct = new AdminMethodValidationThreadForProduct();
		if (dateSegmtCount >= 0) {
			for (int i = 0; i < dateSegmtCount; i++) {
				ContractLocateResult contractLocateResult = (ContractLocateResult) locateResultsDomain
						.getLocateResults().get(i);
				int dateSegmentId = contractLocateResult.getDateSegmentId();
				int contractSysId = contractLocateResult.getContractKey();
				Date startDate	  = contractLocateResult.getStartDate();
				Date endDate	  = contractLocateResult.getEndDate();
				validation = getProductFromContract(dateSegmentId,contractSysId,startDate,endDate);
				 
			}
		}
		
		 List message=new ArrayList();
      	 this.messageList=messageListForProduct;
         return validation;
  		
    }
    /**
	 * Method to get Business Object Manager.
	 * 
	 * @return BusinessObjectManager.
	 */
	private BusinessObjectManager getBusinessObjectManager() {
		BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory) ObjectFactory
				.getFactory(ObjectFactory.BOM);
		BusinessObjectManager bom = bomf.getBusinessObjectManager();
		return bom;
	}
	
	 /**
     * method for synchronous validation.
     * 
     * @param adminMethodSynchronousValidationRequest
     * @return WPDResponse
     * @throws ServiceException
     */
    public WPDResponse execute(AdminMethodSynchronousValidationRequest request)
    throws ServiceException {

    	int level = request.getLevel();
    	AdminMethodBO adminMethodBO = new AdminMethodBO();
    	adminMethodBO.setContractId(request.getContractId());
    	adminMethodBO.setProductId(request.getProductId());
    	if(request.getAdminSysId() != 0)
    		adminMethodBO.setAdministrationId(request.getAdminSysId());
    	else if(request.getBenefitAdministrationId() != 0)
    		adminMethodBO.setAdministrationId(request.getBenefitAdministrationId());
    	adminMethodBO.setBenefitCompSysId(request.getBenefitComponentId());
    	adminMethodBO.setBenSysId(request.getBenefitId());
    	if("CONTRACT".equals(request.getEntityType())){
    		adminMethodBO.setEntitySysId(request.getContractId());
    		adminMethodBO.setDateSgmntId(request.getEntityId());
    	}else{
    		adminMethodBO.setEntitySysId(request.getEntityId());
    	}
    	adminMethodBO.setEntityType(request.getEntityType());
    	
    	//if entity type is product
    	switch (level) {
    	
    	// Admin option Tier for Product
    	case AdminMethodSynchronousValidationRequest.DELETE_ADMIN_OPTIONS_TIER_IN_PRODUCT_VALIDATION:
    		adminMethodBO.setProcessType(BusinessConstants.ASYNCHRONOUS_PROCESS); 
	    	adminMethodBO.setChangeType(BusinessConstants.ADMIN_PROD_TIER_OPTION_TYPE);
    		break;
    		
    	// Admin option Tier for Contract
    	case AdminMethodSynchronousValidationRequest.DELETE_ADMIN_OPTIONS_TIER_IN_CONTRACT_VALIDATION:
    		adminMethodBO.setProcessType(BusinessConstants.ASYNCHRONOUS_PROCESS); 
	    	adminMethodBO.setChangeType(BusinessConstants.ADMIN_CONTRACT_TIER_OPTION_TYPE);
    		break;    		
    	
    	
    	// admin option benefit level change
		case AdminMethodSynchronousValidationRequest.BENEFIT_VALIDATION:
			adminMethodBO.setProcessType(BusinessConstants.ASYNCHRONOUS_PROCESS); 
	    	adminMethodBO.setChangeType(BusinessConstants.BENEFIT_VALIDATION_TYPE);
	    	adminMethodBO.setBenefitCompSysId(request.getBenefitComponentId());
	    	adminMethodBO.setBenSysId(request.getBenefitId());
	    	adminMethodBO.setQuestionProduct(BusinessConstants.QUESTION_PRODUCTSTRUCTURE_FLAG);
			break;
    	
    	// Contract Benefit Lines value change validation
		case AdminMethodSynchronousValidationRequest.BENEFITLEVELS_IN_CONTRACT:
		    //if(BusinessConstants.GEN_BENEFIT_CONSTANT.equals(request.getBenefitCompName()))
				adminMethodBO.setProcessType(BusinessConstants.ASYNCHRONOUS_PROCESS);
//			else
//				adminMethodBO.setProcessType(BusinessConstants.SYNCHRONOUS_PROCESS); 
	    	adminMethodBO.setChangeType(BusinessConstants.LEVEL_TYPE);
	    	adminMethodBO.setBenefitCompSysId(request.getBenefitComponentId());
	    	adminMethodBO.setBenSysId(request.getBenefitId());
	    	adminMethodBO.setQuestionProduct(BusinessConstants.QUESTION_PRODUCTSTRUCTURE_FLAG);
			break;
    	
    	// admin option benefit level change
		case AdminMethodSynchronousValidationRequest.ADMIN_OPTION_SAVE_IN_BENEFIT_ADMIN:
		    if(request.getBenefitCompName().equals(BusinessConstants.GEN_BENEFIT_CONSTANT))
				adminMethodBO.setProcessType(BusinessConstants.ASYNCHRONOUS_PROCESS);
			else
				adminMethodBO.setProcessType(BusinessConstants.SYNCHRONOUS_PROCESS); 
	    	adminMethodBO.setChangeType(BusinessConstants.ADMIN_OPTION_TYPE);
	    	adminMethodBO.setBenefitCompSysId(request.getBenefitComponentId());
	    	adminMethodBO.setBenSysId(request.getBenefitId());
	    	adminMethodBO.setQuestionProduct(BusinessConstants.QUESTION_PRODUCTSTRUCTURE_FLAG);
			break;
			// to chage
		case AdminMethodSynchronousValidationRequest.DELETE_ADMIN_OPTIONS_IN_PRODUCT:
		    adminMethodBO.setProcessType(BusinessConstants.ASYNCHRONOUS_PROCESS); 
		    adminMethodBO.setChangeType(BusinessConstants.ADMIN_OPTION_TYPE);
		    adminMethodBO.setQuestionProduct(BusinessConstants.QUESTION_PRODUCT_FLAG);
			break;
			// product level question change
		case AdminMethodSynchronousValidationRequest.PRDCT_ADMIN_OPTION_QSTNS_SAVE:
			adminMethodBO.setProcessType(BusinessConstants.ASYNCHRONOUS_PROCESS); 
			adminMethodBO.setChangeType(BusinessConstants.QUESTION_CHANGE_TYPE);
		    adminMethodBO.setQuestionProduct(BusinessConstants.QUESTION_PRODUCT_FLAG);
			break;
			//product level admin option add
		case AdminMethodSynchronousValidationRequest.SAVE_ADMIN_OPTIONS_IN_PRODUCT:
		    adminMethodBO.setProcessType(BusinessConstants.ASYNCHRONOUS_PROCESS); 
		    adminMethodBO.setChangeType(BusinessConstants.ADMIN_OPTION_TYPE);
		    adminMethodBO.setQuestionProduct(BusinessConstants.QUESTION_PRODUCT_FLAG);
			break;
			// GB SPS change to change
		case AdminMethodSynchronousValidationRequest.GENERAL_BENEFIT_IN_PRODUCT:
		    adminMethodBO.setProcessType(BusinessConstants.ASYNCHRONOUS_PROCESS); 
			adminMethodBO.setChangeType(BusinessConstants.GENERAL_BENEFIT_TYPE);
			break;
		// benefit level change 		
		case AdminMethodSynchronousValidationRequest.BENEFITLEVELS_IN_ENTITY:
			 adminMethodBO.setProcessType(BusinessConstants.SYNCHRONOUS_PROCESS); 
		    adminMethodBO.setChangeType(BusinessConstants.LEVEL_TYPE);
		    
		    adminMethodBO.setBenefitCompSysId(request.getBenefitComponentId());
		    adminMethodBO.setBenSysId(request.getBenefitId());
		    adminMethodBO.setQuestionProduct(BusinessConstants.QUESTION_PRODUCTSTRUCTURE_FLAG);
			break;
			//benefit component level for unhide
		case AdminMethodSynchronousValidationRequest.BC_LIST_CHANGE_IN_CNTRCT:
			adminMethodBO.setProcessType(BusinessConstants.ASYNCHRONOUS_PROCESS);
			adminMethodBO.setChangeType(BusinessConstants.BC_UNHIDE); // Constant value changed to 'BCUNHIDE' from '-1'
			break;
		/*case AdminMethodSynchronousValidationRequest.QSTN_OVERRIDE_IN_BENEFIT:
			if(request.getBenefitCompName().equals(BusinessConstants.GEN_BENEFIT_CONSTANT))
				adminMethodBO.setProcessType(BusinessConstants.ASYNCHRONOUS_PROCESS);
			else
				adminMethodBO.setProcessType(BusinessConstants.SYNCHRONOUS_PROCESS); 
	    	adminMethodBO.setChangeType(BusinessConstants.ADMIN_OPTION_TYPE);
	    	adminMethodBO.setBenefitCompSysId(request.getBenefitComponentId());
	    	adminMethodBO.setBenSysId(request.getBenefitId());
	    	adminMethodBO.setQuestionProduct(BusinessConstants.QUESTION_PRODUCTSTRUCTURE_FLAG);
			break;	*/
			
			// benefit admin question change
		case AdminMethodSynchronousValidationRequest.BNFT_ADMIN_QUESTION:
			//if(request.getBenefitCompName().equals(BusinessConstants.GEN_BENEFIT_CONSTANT))
			//	adminMethodBO.setProcessType(BusinessConstants.ASYNCHRONOUS_PROCESS);
		//	else
				adminMethodBO.setProcessType(BusinessConstants.ASYNCHRONOUS_PROCESS); 
			adminMethodBO.setChangeType(BusinessConstants.QUESTION_CHANGE_TYPE);
			adminMethodBO.setBenefitCompSysId(request.getBenefitComponentId());
			adminMethodBO.setBenSysId(request.getBenefitId());
			adminMethodBO.setQuestionProduct(BusinessConstants.QUESTION_PRODUCTSTRUCTURE_FLAG);
			
			break;
			// benefit componnet change
		case AdminMethodSynchronousValidationRequest.BC_LIST_INPRODUCT:
			adminMethodBO.setProcessType(BusinessConstants.ASYNCHRONOUS_PROCESS); 
			adminMethodBO.setChangeType(BusinessConstants.VALUE_NEGONE);
		    break;
		    // benefit change
		case AdminMethodSynchronousValidationRequest.BENEFITS_CHANGE_IN_ENTITY:
		    adminMethodBO.setProcessType(BusinessConstants.ASYNCHRONOUS_PROCESS); 
			adminMethodBO.setChangeType(BusinessConstants.BEN_UNHIDE);  // Constant value changed to 'BENUNHIDE' from '-1'
			adminMethodBO.setAffectedBenefits(request.getChangedIds());
			if(null != request.getChangedIds()){
				List bcomps = new ArrayList();
				for(int i=0;i<request.getChangedIds().size();i++){
					bcomps.add(request.getBenefitComponentId() + "");
				}
				adminMethodBO.setAffectedBenComps(bcomps);
			}
		    break;
		    
			
			
			
		default:
			break;
		}
    	
    	  	
    	AdminMethodBusinessObjectBuilder builder = new AdminMethodBusinessObjectBuilder();
    	try {
    		//CARS START
    		builder.validateChangedAdminMethods(adminMethodBO,request.getChangedIds(),
					request.getChangedTiers(),request.getChangedTierSysIds(),
						adminMethodBO.getProcessType());
			//CARS END
		} catch (SevereException e) {
			throw new ServiceException("Validation Error",null, e);
		}
    	AdminMethodSynchronousValidationResponse response = new AdminMethodSynchronousValidationResponse(); 
    	return response;
    }


    /**
	 * @param changedIds
	 * @return
	 */
	private String getCommaSeparatedValues(List changedIds) {
		if(null != changedIds && changedIds.size() > 0){
			StringBuffer buffer = new StringBuffer();
			Iterator iterator = changedIds.iterator();
			while(iterator.hasNext()){
				buffer.append(iterator.next());
				if(iterator.hasNext())
					buffer.append(",");
			}
			return buffer.toString();
		}
		return " ";
	}
	
	 /**
     * @param benefitId
     * @param benefitName
     * @param thread
     * @param adminMethodValidationThread
     */
    private void startAdminMethodThreadForProduct(int contractSysId,int dateSegmentId,Thread thread,AdminMethodValidationThreadForProduct adminMethodValidationThreadForProduct,Date startDate,Date endDate){
        
        adminMethodValidationThreadForProduct.setContractSysId(contractSysId);
        adminMethodValidationThreadForProduct.setDateSegmentId(dateSegmentId);
        adminMethodValidationThreadForProduct.setStartDate(startDate);
        adminMethodValidationThreadForProduct.setEndDate(endDate);
        thread.start();
        
    }
    
    public boolean getProductFromContract(int dateSegmentId,int contractId,Date startDate,Date endDate)throws SevereException,WPDException
    {
        criteriaMap.put("entityId",new Integer(dateSegmentId));
        criteriaMap.put("contractId",new Integer(contractId));
        criteriaMap.put("entityType","contract");
        criteriaMap.put("effectiveDateForContract",WPDStringUtil.getStringDate(startDate)+"-"+ WPDStringUtil.getStringDate(endDate));
      
        boolean validation = true;        
        ContractTreeBuilder contractTreeBuilder = new ContractTreeBuilder();
        ContractTreeProducts productDetails = new ContractTreeProducts();
        productDetails.setContractId(contractId);
        productDetails.setDateSegmentId(dateSegmentId);
        productDetails = (ContractTreeProducts) 
    	contractTreeBuilder.retrieveProducts(productDetails);
        List message=new ArrayList();
        if(null != productDetails){
            criteriaMap.put("productId",new Integer(productDetails.getProductId()));
            
            ProductTreeBenefitComponents benefitComponentDetails = new ProductTreeBenefitComponents();
            
            benefitComponentDetails.setProductId(productDetails.getProductId());
            benefitComponentDetails.setDateSegmentId(dateSegmentId);
            
            List benefitComponents = new ArrayList();
            benefitComponents = contractTreeBuilder.getBenefitComponents( benefitComponentDetails);
            
            for(int m=0;m<benefitComponents.size();m++)
            {
            	benefitComponentDetails = (ProductTreeBenefitComponents)benefitComponents.get(m);
        		criteriaMap.put("benefitComponent",new Integer(benefitComponentDetails.getBenefitComponentId()));
        		criteriaMap.put("benefitComponentName",benefitComponentDetails.getBenefitComponentDesc());
        		
        		if(benefitComponentDetails.getBenefitComponentDesc().equals("GENERAL BENEFITS")){
        			
        			
                    ProductTreeStandardBenefits standardBenefitsDetails = new ProductTreeStandardBenefits();
                    
                    standardBenefitsDetails.setBenefitComponentId(benefitComponentDetails.getBenefitComponentId());
                    standardBenefitsDetails.setDateSegmentId(dateSegmentId);
                    List standardBenefitList = new ArrayList();
                    standardBenefitList = contractTreeBuilder.getStandardBenefits(standardBenefitsDetails);
        			
	                Iterator iteratorTree = standardBenefitList.iterator();
	                int i=0,j=0,k=0;
//	                Thread thread[]=new Thread[standardBenefitList.size()];
	                AdminMethodValidationThread adminMethodValidationThread = new AdminMethodValidationThread();
	            	while (iteratorTree.hasNext()){
	            	    
//	            	    adminMethodValidationThread[k]=new AdminMethodValidationThread();
//	            	    thread[i]=new Thread(adminMethodValidationThread[k]);
	            	   standardBenefitsDetails = ((ProductTreeStandardBenefits)iteratorTree.next());
	            		int benefitId = standardBenefitsDetails.getStandardBenefitId();
	            		String benefitName = standardBenefitsDetails.getStandardBenefitDesc();
//	            	    startAdminMethodThread(benefitId,benefitName,thread[i],adminMethodValidationThread[k]);
	            	    validation = adminMethodValidationThread.validateBenefitAdminMethod(benefitId, benefitName, criteriaMap, message);
	            	    /*if(i==0){
	        				try {
	        					thread[i].join();
	        					generalBenefitAnsList=adminMethodValidationThread[i].getGeneralBenefitAnsList();
	        					generalBenefitSPSList=adminMethodValidationThread[i].getGeneralBenefitSPSList();
	        				} catch (InterruptedException e) {
	        					// TODO Auto-generated catch block
	        					e.printStackTrace();
	        				}
	        				
	            	    }*/
	            	    
//	            	    i++;
//	            	    k++;
	            	}
	               
	            	/*while (j<i){
	            	    try {
	                        thread[j].join();
	                    } catch (InterruptedException e) {
	                        // TODO Auto-generated catch block
	                        e.printStackTrace();
	                    }
	                    if(!thread[j].isAlive()){
	                        message=adminMethodValidationThread[j].getMessageList();
	                	    validation=adminMethodValidationThread[j].isResult();
	                    }
	                       j++;
	            	    if(!validation){
	            	        break;
	            	    }
	            	        
	            	}*/
	                 this.messageListForProduct=message;
//	                 this.validationForContract = validation;
	                 if(!validation){
	         	        break;
	         	    }
	                
        		}
        	
                
            }
            
            //validation = getBenefitComponentIdFromProduct(productDetails.getProductId());
        }
        return validation;
    }
    
    private boolean getGeneralBenefitsBenefitComponentIdFromProduct(int productId)throws SevereException,WPDException
    {
        boolean validation = true;
        List benefitComponentList = new ArrayList();
        ProductTreeBuilder productTreeBuilder=new ProductTreeBuilder();
        ProductTreeBenefitComponents benefitComponentDetails = new ProductTreeBenefitComponents();
        benefitComponentDetails.setProductId(productId);
        benefitComponentList = productTreeBuilder.getBenefitComponents( benefitComponentDetails);
        Iterator iterator = benefitComponentList.iterator();
        criteriaMap.put("entityId",new Integer(productId));
        //criteriaMap.put("benefitComponent",new Integer(benefitComponentId));
        while (iterator.hasNext()){
            benefitComponentDetails = (ProductTreeBenefitComponents) iterator
            .next();
    		int benefitComponentId = benefitComponentDetails.getBenefitComponentId();
    		String benefitComponentName = benefitComponentDetails.getBenefitComponentDesc();
    		criteriaMap.put("benefitComponent",new Integer(benefitComponentId));
    		criteriaMap.put("benefitComponentName",benefitComponentName);
//    		validation = getBenefitIdFromBenefitComp(benefitComponentId,benefitComponentName);
    		
    		if(benefitComponentName.equals("GENERAL BENEFITS")){
    		 List standardBenefitList=new ArrayList();
    	     
    	     // an instance of Standard Benefit BO is created
    	     ProductTreeStandardBenefits standardBenefitsDetails=new ProductTreeStandardBenefits();
    	        
    	     //sets the benefit component id to the BO
    	     standardBenefitsDetails.setBenefitComponentId(benefitComponentId);
    	       
    	     // change for new enhancement -- Start
    	     standardBenefitsDetails.setProductId(productId);
    	       
    	     standardBenefitList=productTreeBuilder.getStandardBenefits(standardBenefitsDetails);
      	     
             Iterator iteratorTree = standardBenefitList.iterator();
//             int i=0,j=0,k=0;
//             Thread thread[]=new Thread[standardBenefitList.size()];
             AdminMethodValidationThread adminMethodValidationThread=new AdminMethodValidationThread();
             List message=new ArrayList();
         	while (iteratorTree.hasNext()){
         	    
         	   standardBenefitsDetails = ((ProductTreeStandardBenefits)iteratorTree.next());
         		int benefitId = standardBenefitsDetails.getStandardBenefitId();
         		String benefitName = standardBenefitsDetails.getStandardBenefitDesc();
         		validation = adminMethodValidationThread.validateBenefitAdminMethod(benefitId, benefitName, criteriaMap, message);
         		message=adminMethodValidationThread.getMessageList();
         		if(!validation){
         	        break;
         	    }
//         	    startAdminMethodThread(benefitId,benefitName,thread[i],adminMethodValidationThread[k]);
         	    /*if(i==0){
     				try {
     					thread[i].join();
     					generalBenefitAnsList=adminMethodValidationThread[i].getGeneralBenefitAnsList();
    					generalBenefitSPSList=adminMethodValidationThread[i].getGeneralBenefitSPSList();
     				} catch (InterruptedException e) {
     					// TODO Auto-generated catch block
     					e.printStackTrace();
     				}
     				
         	    }*/
         	    
//         	    i++;
//         	    k++;
         	}
              
         	/*while (j<i){
         	    try {
                     thread[j].join();
                 } catch (InterruptedException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                 }
                 if(!thread[j].isAlive()){
                     message=adminMethodValidationThread.getMessageList();
             	    validation=adminMethodValidationThread.isResult();
                 }
                    j++;
         	    if(!validation){
         	        break;
         	    }
         	        
         	}*/
              this.messageList=message;
         	
              if(!validation){
     	        break;
     	    }
    		}
    		
    	}
        
        return validation;
    }
    /*private boolean getBenefitComponentIdFromProduct(int productId)throws SevereException,WPDException
    {
        boolean validation = true;
        List benefitComponentList = new ArrayList();
        ProductTreeBuilder productTreeBuilder=new ProductTreeBuilder();
        ProductTreeBenefitComponents benefitComponentDetails = new ProductTreeBenefitComponents();
        benefitComponentDetails.setProductId(productId);
        benefitComponentList = productTreeBuilder.getBenefitComponents( benefitComponentDetails);
        Iterator iterator = benefitComponentList.iterator();
        
        while (iterator.hasNext()){
            benefitComponentDetails = (ProductTreeBenefitComponents) iterator
            .next();
    		int benefitComponentId = benefitComponentDetails.getBenefitComponentId();
    		String benefitComponentName = benefitComponentDetails.getBenefitComponentDesc();
    		criteriaMap.put("benefitComponent",new Integer(benefitComponentId));
    		criteriaMap.put("benefitComponentName",benefitComponentName);
//    		validation = getBenefitIdFromBenefitComp(benefitComponentId,benefitComponentName);
    		
    		 List standardBenefitList=new ArrayList();
    	        
    	     
    	        
    	     // an instance of Standard Benefit BO is created
    	     ProductTreeStandardBenefits standardBenefitsDetails=new ProductTreeStandardBenefits();
    	        
    	     //sets the benefit component id to the BO
    	     standardBenefitsDetails.setBenefitComponentId(benefitComponentId);
    	       
    	     // change for new enhancement -- Start
    	     standardBenefitsDetails.setProductId(productId);
    	       
    	     standardBenefitList=productTreeBuilder.getStandardBenefits(standardBenefitsDetails);
      	     
             Iterator iteratorTree = standardBenefitList.iterator();
             int i=0,j=0,k=0;
             Thread thread[]=new Thread[standardBenefitList.size()];
             AdminMethodValidationThread adminMethodValidationThread[]=new AdminMethodValidationThread[standardBenefitList.size()];
         	while (iteratorTree.hasNext()){
         	    
         	    adminMethodValidationThread[k]=new AdminMethodValidationThread();
         	    thread[i]=new Thread(adminMethodValidationThread[k]);
         	   standardBenefitsDetails = ((ProductTreeStandardBenefits)iteratorTree.next());
         		int benefitId = standardBenefitsDetails.getStandardBenefitId();
         		String benefitName = standardBenefitsDetails.getStandardBenefitDesc();
         	    startAdminMethodThread(benefitId,benefitName,thread[i],adminMethodValidationThread[k]);
         	    if(i==0){
     				try {
     					thread[i].join();
     					generalBenefitAnsList=adminMethodValidationThread[i].getGeneralBenefitAnsList();
    					generalBenefitSPSList=adminMethodValidationThread[i].getGeneralBenefitSPSList();
     				} catch (InterruptedException e) {
     					// TODO Auto-generated catch block
     					e.printStackTrace();
     				}
     				
         	    }
         	    
         	    i++;
         	    k++;
         	}
              List message=new ArrayList();
         	while (j<i){
         	    try {
                     thread[j].join();
                 } catch (InterruptedException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                 }
                 if(!thread[j].isAlive()){
                     message=adminMethodValidationThread[j].getMessageList();
             	    validation=adminMethodValidationThread[j].isResult();
                 }
                    j++;
         	    if(!validation){
         	        break;
         	    }
         	        
         	}
              this.messageList=message;
         	
              if(!validation){
     	        break;
     	    }
     		
    		
    	}
        
        return validation;
    }*/
    private boolean getBenefitComponentIdFromProductStructure(int productStructureId)throws SevereException
    {
        boolean validation = true;
        List benefitComponentList = new ArrayList();
        ProdStructureTreeBenefitCmpnts prodStructureTreeBenefitCmpnts = new ProdStructureTreeBenefitCmpnts();
        ProductStructureBusinessObjectBuilder productStructureBusinessObjectBuilder = new ProductStructureBusinessObjectBuilder();        
        prodStructureTreeBenefitCmpnts.setProductStructureId(productStructureId);
        prodStructureTreeBenefitCmpnts.setBenefitCmpntDesc(null);
        benefitComponentList = productStructureBusinessObjectBuilder
                    .getBenefitComponents(prodStructureTreeBenefitCmpnts);
        Iterator iterator = benefitComponentList.iterator();
        
        while (iterator.hasNext()){
            prodStructureTreeBenefitCmpnts = (ProdStructureTreeBenefitCmpnts) iterator
            .next();
    		int benefitComponentId = prodStructureTreeBenefitCmpnts.getBenefitCmpntId();
    		String benefitComponentName = prodStructureTreeBenefitCmpnts.getBenefitCmpntDesc();
    		criteriaMap.put("benefitComponent",new Integer(benefitComponentId));
    		criteriaMap.put("benefitComponentName",benefitComponentName);
    		ProdStructureTreeStandardBenefits treeStandardBenefits = new ProdStructureTreeStandardBenefits();
            treeStandardBenefits.setBenefitCmpntId(benefitComponentId);
            treeStandardBenefits.setProductStructure(productStructureId);
            List psStdBnftList = new ArrayList();
            try {
            	psStdBnftList = productStructureBusinessObjectBuilder
                        .getStandardBenefits(treeStandardBenefits);
            } catch (WPDException e) {
                //TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            ProdStructureTreeStandardBenefits treeStandardBenefitsForPS = new ProdStructureTreeStandardBenefits();
            Iterator iteratorTree = psStdBnftList.iterator();
            int i=0,j=0,k=0;
            Thread thread[]=new Thread[psStdBnftList.size()];
            AdminMethodValidationThread adminMethodValidationThread[]=new AdminMethodValidationThread[psStdBnftList.size()];
        	while (iteratorTree.hasNext()){
        	    
        	    adminMethodValidationThread[k]=new AdminMethodValidationThread();
        	    thread[i]=new Thread(adminMethodValidationThread[k]);
        	    treeStandardBenefitsForPS = ((ProdStructureTreeStandardBenefits)iteratorTree.next());
        		int benefitId = treeStandardBenefitsForPS.getStandardBenefitId();
        		String benefitName = treeStandardBenefitsForPS.getStandardBenefitDesc();
        	    startAdminMethodThread(benefitId,benefitName,thread[i],adminMethodValidationThread[k]);
        	    if(i==0){
    				try {
    					thread[i].join();
    					generalBenefitAnsList=adminMethodValidationThread[i].getGeneralBenefitAnsList();
    					generalBenefitSPSList=adminMethodValidationThread[i].getGeneralBenefitSPSList();
    				} catch (InterruptedException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    				
        	    }
        	    
        	    i++;
        	    k++;
        	}
             List message=new ArrayList();
        	while (j<i){
        	    try {
                    thread[j].join();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if(!thread[j].isAlive()){
                    message=adminMethodValidationThread[j].getMessageList();
            	    validation=adminMethodValidationThread[j].isResult();
                }
                   j++;
                   if(!validation){
        	        break;
        	    }
        	        
        	}
        	this.messageList=message;
        	 if(!validation){
    	        break;
    	    }
             
        	
    	}
        return validation;
    }

//    
//    private boolean getBenefitAdminMethodValidation(int benefitId,String benefitName)throws SevereException{
//        AdminMethodValidationThread adminMethodValidationThread=new AdminMethodValidationThread();
//        return adminMethodValidationThread.validateBenefitAdminMethod(benefitId,benefitName,criteriaMap,messageList);
//        
//    }
    
   private boolean getBenefitIdFromBenefitComp(int benefitComponentId,String benefitComponentName)throws SevereException
    {   
        List benefitIds = new ArrayList();
        AdminMethodBusinessObjectBuilder adminMethodBusinessObjectBuilder = new AdminMethodBusinessObjectBuilder();
        TreeBenefitComponent treeBenefitComponent = new TreeBenefitComponent();
        try {
            benefitIds = adminMethodBusinessObjectBuilder.getBenefitIds(benefitComponentId);
        } catch (SevereException e1) {
            // TODO Auto-generated catch block
        	Logger.logError(e1);
        } catch (AdapterException e1) {
            // TODO Auto-generated catch block
        	Logger.logError(e1);
        }
        criteriaMap.put("benefitComponent",new Integer(benefitComponentId));
        criteriaMap.put("benefitComponentName",benefitComponentName);
        
        Iterator iterator = benefitIds.iterator();
        boolean validation = true;
        int i=0,j=0,k=0;
        Thread thread[]=new Thread[benefitIds.size()];
        AdminMethodValidationThread adminMethodValidationThread[]=new AdminMethodValidationThread[benefitIds.size()];
    	while (iterator.hasNext()){
    	    
    	    adminMethodValidationThread[k]=new AdminMethodValidationThread();
    	    thread[i]=new Thread(adminMethodValidationThread[k]);
    	    treeBenefitComponent = ((TreeBenefitComponent)iterator.next());
    		int benefitId = treeBenefitComponent.getBenefitId();
    		String benefitName = treeBenefitComponent.getBenefitName();
    	    startAdminMethodThread(benefitId,benefitName,thread[i],adminMethodValidationThread[k]);
    	    if(i==0){
				try {
					thread[i].join();
					generalBenefitAnsList=adminMethodValidationThread[i].getGeneralBenefitAnsList();
					generalBenefitSPSList=adminMethodValidationThread[i].getGeneralBenefitSPSList();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					Logger.logError(e);
				}
				
    	    }
    	    
    	    i++;
    	    k++;
    	}
         List message=new ArrayList();
    	while (j<i){
    	    try {
                thread[j].join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
            	Logger.logError(e);
            }
            if(!thread[j].isAlive()){
                message=adminMethodValidationThread[j].getMessageList();
        	    validation=adminMethodValidationThread[j].isResult();
            }
               j++;
    	    if(!validation){
    	        break;
    	    }
    	        
    	}
         this.messageList=message;
    	
        return validation;

    }
    
    
	/**
	 * @return Returns the generalBenefitSPSList.
	 */
	public List getGeneralBenefitSPSList() {
		return generalBenefitSPSList;
	}
	/**
	 * @param generalBenefitSPSList The generalBenefitSPSList to set.
	 */
	public void setGeneralBenefitSPSList(List generalBenefitSPSList) {
		this.generalBenefitSPSList = generalBenefitSPSList;
	}
    
    
    /**
     * @param benefitId
     * @param benefitName
     * @param thread
     * @param adminMethodValidationThread
     */
    private void startAdminMethodThread(int benefitId,String benefitName,Thread thread,AdminMethodValidationThread adminMethodValidationThread){
        
        adminMethodValidationThread.setBenefitId(benefitId);
        adminMethodValidationThread.setBenefitName(benefitName);
        adminMethodValidationThread.setCriteriaMap(criteriaMap);
        adminMethodValidationThread.setMessageList(messageList);
        adminMethodValidationThread.setGeneralBenefitAnsList(generalBenefitAnsList);
        adminMethodValidationThread.setGeneralBenefitSPSList(generalBenefitSPSList);
        thread.start();
        
    }
    
    private boolean validateBenefitAdminMethod(int benefitId,String benefitName)
    throws SevereException{
        AdminMethodValidationThread adminMethodValidationThread=new AdminMethodValidationThread();
        messageList=new ArrayList();
        boolean result=adminMethodValidationThread.validateBenefitAdminMethod(benefitId,benefitName,criteriaMap,messageList);
        this.messageList=adminMethodValidationThread.getMessageList();
        return result;
        
    }
    
    
   /* private boolean validateBenefitAdminMethod(int benefitId,String benefitName)
    throws SevereException{
        AdminMethodBusinessObjectBuilder adminMethodBusinessObjectBuilder = new AdminMethodBusinessObjectBuilder();
        List filterList = new ArrayList();
        
        boolean validation = true;
        if(criteriaMap.get("entityType").equals("BENEFIT")){
            List benefitAdmin=null;
            try {
                benefitAdmin = adminMethodBusinessObjectBuilder.getAdminstationId(benefitId);
            } catch (SevereException e) {
                // TODO Auto-generated catch block
                Logger.logError(e);
            } catch (AdapterException e) {
                // TODO Auto-generated catch block
                Logger.logError(e);
            }
            if(null != benefitAdmin &&  benefitAdmin.size() >0){
	            for (int i=0;i<benefitAdmin.size();i++){
	                messageList = new ArrayList();
	                BenefitAdministrationBO benefitAdministrationBO =(BenefitAdministrationBO) benefitAdmin.get(i);
	                int benefitAdminKey = benefitAdministrationBO.getBenefitAdministrationKey();
	                String standardDefinition = benefitAdministrationBO.getEffectiveDateStandDef() +"-"+benefitAdministrationBO.getExpiryDateStandDef();
	                int benefitDefinitionKey = benefitAdministrationBO.getBenefitDefinitionKey();
	                String[] argMsg = new String[1];
	                argMsg[0] =standardDefinition;
	                List adminMethod=null;
                    try {
                        adminMethod = adminMethodBusinessObjectBuilder.getAssociatedAdminMethod(benefitAdminKey);
                    } catch (SevereException e1) {
                        // TODO Auto-generated catch block
                        Logger.logError(e1);
                    } catch (AdapterException e1) {
                        // TODO Auto-generated catch block
                        Logger.logError(e1);
                    }
                    criteriaMap.put("benefitAdministration",new Integer(benefitAdminKey));
	                
		                if(null != adminMethod && adminMethod.size() > 0)
		                {
		                    AdminMethodBO adminMethodBO = new AdminMethodBO();
		                    adminMethodBO = (AdminMethodBO)adminMethod.get(0);
		                    int validationId = adminMethodBO.getValidationId();
		                    if(validationId==0)
		                    {
		                        ErrorMessage msg = new ErrorMessage(
    		                    "all.superprocess.Steps.notselected.benefit");
                    		    msg.setParameters(argMsg);
		                        this.messageList.add(msg);
		                        validation = false;
		                        break;
		                    }else{
		                        Iterator iterator = adminMethod.iterator();
		                        while (iterator.hasNext()){
		                            AdminMethodsPopupBO adminMethodsPopupBO = new AdminMethodsPopupBO();
		                            adminMethodBO = new AdminMethodBO();
		                            adminMethodBO = ((AdminMethodBO)iterator.next());
		                    		int spsId = adminMethodBO.getSpsId();
		                    		int adminMethodId = adminMethodBO.getAdminMethodSysId();
		                    		adminMethodsPopupBO.setEntityType("BENEFIT");
		                    		adminMethodsPopupBO.setSpsId(spsId);
		                    		adminMethodsPopupBO.setEntityId(benefitId);
		                    		adminMethodsPopupBO.setStdDefId(benefitDefinitionKey);
		                    		adminMethodsPopupBO.setAdminId(benefitAdminKey);
		                    		
		                    		try {
                                        filterList = adminMethodBusinessObjectBuilder.getListAfterFilter(adminMethodsPopupBO);
                                    } catch (SevereException e2) {
                                        // TODO Auto-generated catch block
                                        Logger.logError(e2);
                                    } catch (AdapterException e2) {
                                        // TODO Auto-generated catch block
                                        Logger.logError(e2);
                                    }
		                    		boolean filterValidation = false;
		                    		if ( null !=filterList && filterList.size()>0){
		                    		    Iterator iter = filterList.iterator();
		                    		    while (iter.hasNext()){
		                    		        AdminMethodsPopupBO adminMethodsFilterPopup = (AdminMethodsPopupBO)iter.next();
		                    		        if (adminMethodId == adminMethodsFilterPopup.getAdminMethodId()){
		                    		            filterValidation = true;
		                    		        }
		                    		    }
			                    		if(!filterValidation){
			                    		    validation = false;
			                    		    ErrorMessage msg = new ErrorMessage(
			    		                    "admin.method.filter.failed.benefit");
			                    		    msg.setParameters(argMsg);
					                        this.messageList.add(msg);
			            		            break;
				                        }
		                    		}
		                    	}           
		                    }
		                }else{
		                    ErrorMessage msg = new ErrorMessage(
		                    "admin.method.not.present.benefit");
		                    msg.setParameters(argMsg);
		                    this.messageList.add(msg);
		                    validation = false;
		                    break;
		                }
	            }
            }
    	}else {
    	    
    	    List benefitAdmin=null;
            try {
                benefitAdmin = adminMethodBusinessObjectBuilder.getAdminstationIdFromBC(benefitId,Integer.parseInt(criteriaMap.get("benefitComponent").toString()));
            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                Logger.logError(e);
            } catch (SevereException e) {
                // TODO Auto-generated catch block
                Logger.logError(e);
            } catch (AdapterException e) {
                // TODO Auto-generated catch block
                Logger.logError(e);
            }
            if(null != benefitAdmin &&  benefitAdmin.size() >0){
    	        
    	        for (int i=0;i<benefitAdmin.size();i++){
	                messageList = new ArrayList();
	                AdminMethodOverrideBO adminMethodOverride ;
	                BenefitAdministrationBO benefitAdministrationBO =(BenefitAdministrationBO) benefitAdmin.get(i);
	                int benefitAdminKey = benefitAdministrationBO.getBenefitAdministrationKey();
	                String standardDefinition = benefitAdministrationBO.getEffectiveDateStandDef() +"-"+benefitAdministrationBO.getExpiryDateStandDef();
	                int benefitDefinitionKey = benefitAdministrationBO.getBenefitDefinitionKey();
	                String[] argMsg = new String[2];
	                argMsg[0] = benefitName;
	                argMsg[1] = standardDefinition;
	                
	                String entityType = criteriaMap.get("entityType").toString();
	                int entityId  = Integer.parseInt(criteriaMap.get("entityId").toString());
	                int benefitComponent = Integer.parseInt(criteriaMap.get("benefitComponent").toString());
	                AdminMethodOverrideBO adminMethodOverrideBO = new AdminMethodOverrideBO();
	                adminMethodOverrideBO.setEntitySysId(entityId);
	                adminMethodOverrideBO.setEntityType(entityType);
	                adminMethodOverrideBO.setBenefitCompSysId(benefitComponent);
	                adminMethodOverrideBO.setBnftAdmnId(benefitAdminKey);
	                List countList=null;
                    try {
                        countList = adminMethodBusinessObjectBuilder.getSPSNamesForAdminMethodOverrideValidation(adminMethodOverrideBO);
                    } catch (SevereException e1) {
                        // TODO Auto-generated catch block
                        Logger.logError(e1);
                    } catch (AdapterException e1) {
                        // TODO Auto-generated catch block
                        Logger.logError(e1);
                    }
                    if(null != countList && countList.size()>0){
                        adminMethodOverrideBO = (AdminMethodOverrideBO)countList.get(0);
                        if(adminMethodOverrideBO.getValidationId() ==0){
                            ErrorMessage msg = new ErrorMessage(
		                    "all.superprocess.Steps.notselected");
                            msg.setParameters(argMsg);
                            this.messageList.add(msg);
                        validation = false;
                        break;
                            
                        }else{
                            Iterator iterator = countList.iterator();
	                        while (iterator.hasNext()){
	                            adminMethodOverride = new AdminMethodOverrideBO();
	                            adminMethodOverride = ((AdminMethodOverrideBO)iterator.next());
	                    		int spsId = adminMethodOverride.getSpsId();
	                    		int adminMethodId = adminMethodOverride.getAdminMethodSysId();
	                    		AdminMethodsPopupBO adminMethodsPopupBO = new AdminMethodsPopupBO();
	                    		 if(criteriaMap.get("entityType").equals("contract")){
	                    		     adminMethodsPopupBO.setContractId(Integer.parseInt(criteriaMap.get("entityId").toString()));
 	        	                     adminMethodsPopupBO.setEntityId(Integer.parseInt(criteriaMap.get("contractId").toString()));
 	        	                     adminMethodsPopupBO.setProdId(Integer.parseInt(criteriaMap.get("productId").toString()));
 	        	                     adminMethodsPopupBO.setBenefitCompId(Integer.parseInt(criteriaMap.get("benefitComponent").toString()));
	                    		 }else if (criteriaMap.get("entityType").equals("product")){
	                    		     adminMethodsPopupBO.setEntityId(Integer.parseInt(criteriaMap.get("entityId").toString()));
	                    		     adminMethodsPopupBO.setBenefitCompId(Integer.parseInt(criteriaMap.get("benefitComponent").toString()));
	                    		
	                    		 }else if(criteriaMap.get("entityType").equals("ProdStructure")){
	                    		     adminMethodsPopupBO.setEntityId(Integer.parseInt(criteriaMap.get("entityId").toString()));
	                    		     adminMethodsPopupBO.setBenefitCompId(Integer.parseInt(criteriaMap.get("benefitComponent").toString()));
	                    		
	                    		 }else if (criteriaMap.get("entityType").equals("BENEFITCOMP")){
	                    		     adminMethodsPopupBO.setEntityId(Integer.parseInt(criteriaMap.get("entityId").toString()));
	                    		 }
	                    		adminMethodsPopupBO.setStdDefId(benefitDefinitionKey);
	                    		adminMethodsPopupBO.setEntityType((String)criteriaMap.get("entityType"));
	                    		adminMethodsPopupBO.setSpsId(spsId);
	                    		adminMethodsPopupBO.setBenftId(benefitId);
	                    		
	                    		boolean filterValidation = false;
	                    		try {
                                    filterList = adminMethodBusinessObjectBuilder.getListAfterFilter(adminMethodsPopupBO);
                                } catch (SevereException e2) {
                                    // TODO Auto-generated catch block
                                    Logger.logError(e2);
                                } catch (AdapterException e2) {
                                    // TODO Auto-generated catch block
                                    Logger.logError(e2);
                                }
	                    		if ( null !=filterList && filterList.size()>0){
	                    		    Iterator iter = filterList.iterator();
	                    		    while (iter.hasNext()){
	                    		        AdminMethodsPopupBO adminMethodsFilterPopup = (AdminMethodsPopupBO)iter.next();
	                    		        if (adminMethodId == adminMethodsFilterPopup.getAdminMethodId()){
	                    		            filterValidation = true;
	                    		        }
	                    		    }
		                    		if(!filterValidation){
		                    		    validation = false;
		                    		    ErrorMessage msg = new ErrorMessage(
		    		                    "admin.method.filter.failed");
		                    		    msg.setParameters(argMsg);
				                        this.messageList.add(msg);
		            		            break;
			                        }
	                    		}
	                        }
                        }
	                }else{
	                    ErrorMessage msg = new ErrorMessage(
	                    "admin.method.not.present");
            		    msg.setParameters(argMsg);
            		    
	                    this.messageList.add(msg);
	                    validation = false;
	                    break;
	                }
	                
	                
    	        }
    	        
    	    }
    	    
    	}
    
        return validation;
    }*/
    
    /**
     * Returns the messageListForProduct
     * @return List messageListForProduct.
     */

    public List getMessageListForProduct() {
        return messageListForProduct;
    }
    /**
     * Sets the messageListForProduct
     * @param messageListForProduct.
     */

    public void setMessageListForProduct(List messageListForProduct) {
        this.messageListForProduct = messageListForProduct;
    }
    
    
    /**
     * Returns the validationForContract
     * @return boolean validationForContract.
     */

    public boolean isValidationForContract() {
        return validationForContract;
    }
    /**
     * Sets the validationForContract
     * @param validationForContract.
     */

    public void setValidationForContract(boolean validationForContract) {
        this.validationForContract = validationForContract;
    }
}
