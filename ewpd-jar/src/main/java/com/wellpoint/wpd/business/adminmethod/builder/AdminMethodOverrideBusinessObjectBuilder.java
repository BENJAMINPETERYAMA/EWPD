/**
 * AdminMethodOverrideBusinessObjectBuilder.java 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 * Created on Sep 18, 2007
 */
package com.wellpoint.wpd.business.adminmethod.builder;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.adminmethod.adapter.AdminMethodAdapterManager;
import com.wellpoint.wpd.business.benefitcomponent.adapter.BenefitComponentAdapterManager;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.contract.adapter.ContractAdapterManager;
import com.wellpoint.wpd.business.framework.bo.AuditFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.product.adapter.ProductAdapterManager;
import com.wellpoint.wpd.business.productstructure.adapter.ProductStructureAdapterManager;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodContractValidationBO;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodOverrideBO;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodProductValidationBO;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodTierOverrideBO;

import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodValidationBO;
import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentBO;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.contract.bo.Contract;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.product.bo.ProductBO;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureBO;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * Builder for override Admin Method.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AdminMethodOverrideBusinessObjectBuilder {

    /**
     * Method to persist overridden admin methods.
     * 
     * @param adminMethodsId
     * @param spsId
     * @param individualAdminOverrideBO
     * @param adminMethodBoRetrveList
     * @param user
     * @return
     * @throws SevereException
     * @throws AdapterException
     */
    public boolean persistAdminMethodsValidation(AdminMethodValidationBO adminMethodValidationBO,
            List adminMethodBoRetrveList, User user) throws SevereException,
            AdapterException {
    	 AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();
        boolean success = false;
        AdminMethodProductValidationBO adminMethodProductValidationBO;
        AdminMethodContractValidationBO adminMethodContractValidationBO;
        long transactionId = AdapterUtil.getTransactionId();
        AuditFactory auditFactory = (AuditFactory) ObjectFactory
                .getFactory(ObjectFactory.AUDIT);
        Audit audit = auditFactory.getAudit(user);
        AdapterServicesAccess adapterServiceAccess = AdapterUtil
                .getAdapterService();
        List boList = adminMethodValidationBO.getAdminmethodValidationList();
        AdminMethodValidationBO adminMethodBO;
        try {
            AdapterUtil.beginTransaction(adapterServiceAccess);
            AdapterUtil
                    .logBeginTranstn(transactionId, this,                            
                            "persistOverriddenAdminMethods(List adminMethodsId, List spsId, AdminMethodOverrideBO individualAdminOverrideBO,	List adminMethodBoRetrveList, User user)");
            
            
            if(null != adminMethodValidationBO){
            	
            	for(int j=0;j<boList.size();j++){
            		AdminMethodValidationBO BO =  (AdminMethodValidationBO)boList.get(j);
            		
            		 int administrationId = BO.getBenefitAdminSysId();
                     int sps = BO.getSpsId();
                     int adminMethodIdForCheck=0;
                     int entityId = BO.getEntitySysId();
                     int benefitCOmId = BO.getBenefitComSysId();
                     String entityType = BO.getEntityType();
                     
                     if (null != adminMethodBoRetrveList
                            && !adminMethodBoRetrveList.isEmpty()) {
                        // Loop through the two lists
                        for (int i = 0; i < adminMethodBoRetrveList.size(); i++) {
                            // Get the retrieved admnBO and the selectedAdminBO
                        	AdminMethodOverrideBO retrievedAdminMethodBO = (AdminMethodOverrideBO) adminMethodBoRetrveList
                                    .get(i);
                      
                        	
                        	if(administrationId == retrievedAdminMethodBO.getBnftAdmnId() && sps ==retrievedAdminMethodBO.getSpsId() ){
                        		adminMethodIdForCheck = retrievedAdminMethodBO.getAdminMethodSysId();
                        		
                        		retrievedAdminMethodBO.setEntitySysId(entityId);
								retrievedAdminMethodBO.setEntityType(entityType);
								retrievedAdminMethodBO.setBenefitCompSysId(benefitCOmId);
								retrievedAdminMethodBO.setCreatedUser(audit.getUser());
								retrievedAdminMethodBO.setCreatedTimestamp(audit.getTime());
								retrievedAdminMethodBO.setLastUpdatedTimestamp(audit.getTime());
								retrievedAdminMethodBO.setLastUpdatedUser(audit.getUser());
								retrievedAdminMethodBO.setAdminMethodSysId(BO.getAdminMethodId());
                        		if (adminMethodIdForCheck == 0
        	                            && BO.getAdminMethodId() != 0) {
        	                        // Perform insert
                        			
        	                        success = this.persist(retrievedAdminMethodBO, user
        	                                .getUserId(), adapterServiceAccess, true);
        	                    } else if (adminMethodIdForCheck != 0
        	                            && BO.getAdminMethodId() != 0) {
        	                        // Check whether the rBO adminId has value and sBO
        	                        // adminId has value
        	                        // Perform update
        	                        success = this.persist(retrievedAdminMethodBO, user
        	                                .getUserId(), adapterServiceAccess, false);
        	                    } 
//        	                    if(entityType.equals("product")){
//        	                    	adminMethodProductValidationBO = new AdminMethodProductValidationBO();
//        	                    	adminMethodProductValidationBO.setEntitySysId(BO.getEntitySysId());
//        	                    	adminMethodProductValidationBO.setBenefitComSysId(BO.getBenefitComSysId());
//									adminMethodProductValidationBO.setBenefitSysId(BO.getBenefitSysId());
//									adminMethodProductValidationBO.setBenefitAdminSysId(BO.getBenefitAdminSysId());
//									adminMethodProductValidationBO.setSpsId(BO.getSpsId());
//									adapterManager.deleteAdminMethodsValidation(adminMethodProductValidationBO,
//	                                		user.getUserId(), adapterServiceAccess);
//
//        	                    }else if(entityType.equals("contract")){
//        	                    	adminMethodContractValidationBO = new AdminMethodContractValidationBO();
//        	                    	adminMethodContractValidationBO.setEntitySysId(BO.getEntitySysId());
//        	                    	adminMethodContractValidationBO.setBenefitComSysId(BO.getBenefitComSysId());
//        	                    	adminMethodContractValidationBO.setBenefitSysId(BO.getBenefitSysId());
//        	                    	adminMethodContractValidationBO.setBenefitAdminSysId(BO.getBenefitAdminSysId());
//        	                    	adminMethodContractValidationBO.setSpsId(BO.getSpsId());
//        	                    	adapterManager.deleteAdminMethodsValidation(adminMethodContractValidationBO,
//                                    		user.getUserId(), adapterServiceAccess);
//        	                    	
//        	                    }else{
//                        		  adapterManager.deleteAdminMethodsValidation(BO,
//                                		user.getUserId(), adapterServiceAccess);
//        	                    }
                        	}
                        
                        }
                        
                  
                     }
            		
            	}
            	
            }
            
            
            

   

  
            AdapterUtil.endTransaction(adapterServiceAccess);
            AdapterUtil
                    .logTheEndTransaction(transactionId, this ,                           
                            "persistOverriddenAdminMethods(List adminMethodsId, List spsId, AdminMethodOverrideBO individualAdminOverrideBO,	List adminMethodBoRetrveList, User user)");
        } catch (Exception ex) {
            AdapterUtil.abortTransaction(adapterServiceAccess);
            AdapterUtil
                    .logAbortTxn(transactionId , this , 
                            "persistOverriddenAdminMethods(List adminMethodsId, List spsId, AdminMethodOverrideBO individualAdminOverrideBO,	List adminMethodBoRetrveList, User user)");
            throw new SevereException("Unknown exception found.", null, ex);
        }

        return success;
    }
    
    
    
    /**
     * Method to persist overridden admin methods.
     * 
     * @param adminMethodsId
     * @param spsId
     * @param individualAdminOverrideBO
     * @param adminMethodBoRetrveList
     * @param user
     * @return
     * @throws SevereException
     * @throws AdapterException
     */
    public boolean persistOverriddenAdminMethods(List adminMethodsId,
            List spsId, AdminMethodOverrideBO individualAdminOverrideBO,
            List adminMethodBoRetrveList, User user) throws SevereException,
            AdapterException {
        boolean success = false;
        long transactionId = AdapterUtil.getTransactionId();
        AuditFactory auditFactory = (AuditFactory) ObjectFactory
                .getFactory(ObjectFactory.AUDIT);
        Audit audit = auditFactory.getAudit(user);
        AdapterServicesAccess adapterServiceAccess = AdapterUtil
                .getAdapterService();
        AdminMethodAdapterManager adminMethodAdapterManager = new AdminMethodAdapterManager();
        try {
            AdapterUtil.beginTransaction(adapterServiceAccess);
            AdapterUtil
                    .logBeginTranstn(transactionId, this,                            
                            "persistOverriddenAdminMethods(List adminMethodsId, List spsId, AdminMethodOverrideBO individualAdminOverrideBO,	List adminMethodBoRetrveList, User user)");

            if (null != adminMethodBoRetrveList
                    && !adminMethodBoRetrveList.isEmpty()) {
            	String entityType = individualAdminOverrideBO.getEntityType();
                // Loop through the two lists
                for (int i = 0; i < adminMethodBoRetrveList.size(); i++) {
                    // Get the retrieved admnBO and the selectedAdminBO
                    AdminMethodOverrideBO retrievedAdminOverrideBO = (AdminMethodOverrideBO) adminMethodBoRetrveList
                            .get(i);
                    individualAdminOverrideBO.setAdminMethodSysId(new Integer(
                            adminMethodsId.get(i).toString()).intValue());
                    individualAdminOverrideBO.setSpsId(new Integer(spsId.get(i)
                            .toString()).intValue());

                    individualAdminOverrideBO.setCreatedTimestamp(audit
                            .getTime());
                    individualAdminOverrideBO.setCreatedUser(audit.getUser());
                    individualAdminOverrideBO.setLastUpdatedUser(audit
                            .getUser());
                    individualAdminOverrideBO.setLastUpdatedTimestamp(audit
                            .getTime());
                    individualAdminOverrideBO.setTierSysId(-1); //CARS:AM2
                    // If the spsid are the same
                    // Check whether the rBO adminId is empty and sBO adminId
                    // has value
                    if (retrievedAdminOverrideBO.getAdminMethodSysId() == 0
                            && individualAdminOverrideBO.getAdminMethodSysId() != 0) {
                        // Perform insert
                        success = this.persist(individualAdminOverrideBO, user
                                .getUserId(), adapterServiceAccess, true);
                        
                        // After saving, delete the from the invalid status table
                        deleteInValidSPSFromAffectedInValidSPSTable(individualAdminOverrideBO, 
                        		audit, adapterServiceAccess, adminMethodAdapterManager, entityType);
                    } else if (retrievedAdminOverrideBO.getAdminMethodSysId() != 0
                            && individualAdminOverrideBO.getAdminMethodSysId() != 0
							&& retrievedAdminOverrideBO.getAdminMethodSysId() != individualAdminOverrideBO.getAdminMethodSysId()) {
                        // Check whether the rBO adminId has value and sBO
                        // adminId has value
                        // Perform update
                        success = this.persist(individualAdminOverrideBO, user
                                .getUserId(), adapterServiceAccess, false);
                        
                        // After saving, delete the from the invalid status table
                        deleteInValidSPSFromAffectedInValidSPSTable(individualAdminOverrideBO, 
                        		audit, adapterServiceAccess, adminMethodAdapterManager, entityType);
                    } else if (retrievedAdminOverrideBO.getAdminMethodSysId() != 0
                            && individualAdminOverrideBO.getAdminMethodSysId() == 0) {
                        // Check whether the rBO adminId is value and sBO
                        // adminId has empty
                        // Perform delete
                        success = this.delete(individualAdminOverrideBO, user
                                .getUserId(), adapterServiceAccess);
                        
                        // After saving, delete the from the invalid status table
                        deleteInValidSPSFromAffectedInValidSPSTable(individualAdminOverrideBO, 
                        		audit, adapterServiceAccess, adminMethodAdapterManager, entityType);
                    }
                    
                    
                }
                
                
                if (BusinessConstants.BENEFITCOMP.equals(entityType)) {
                    BenefitComponentAdapterManager benefitComponentAdapterManager = new BenefitComponentAdapterManager();
                    boolean insertFlag = true;
                    BenefitComponentBO benefitComponentBO = new BenefitComponentBO();
                    benefitComponentBO
                            .setBenefitComponentId(individualAdminOverrideBO
                                    .getEntitySysId());
                    benefitComponentBO = (BenefitComponentBO) benefitComponentAdapterManager
                            .retrieveBenefitComponent(benefitComponentBO);
                    benefitComponentBO.setLastUpdatedUser(audit.getUser());
                    benefitComponentBO.setLastUpdatedTimestamp(audit.getTime());
                    benefitComponentAdapterManager.updateBenefitComponent(
                            benefitComponentBO, audit, insertFlag,
                            adapterServiceAccess);
                } else if ((BusinessConstants.PRODSTRUCTURE_ENTITY
                        .equals(entityType))
                        || (WebConstants.PROD_STRUCT.equals(entityType))) {
                	
                	
                    ProductStructureBO productStructureBO = new ProductStructureBO();
                    ProductStructureAdapterManager adapterManager = new ProductStructureAdapterManager();
                    productStructureBO
                            .setProductStructureId(individualAdminOverrideBO
                                    .getEntitySysId());
                    productStructureBO = adapterManager
                            .retrieveByProductId(productStructureBO);
                    productStructureBO.setLastUpdatedUser(audit.getUser());
                    productStructureBO.setLastUpdatedTimestamp(audit.getTime());
                    adapterManager.updateProductStructure(productStructureBO,
                            adapterServiceAccess);
                } else if ((BusinessConstants.PRODUCT.equals(entityType))
                        || (WebConstants.PROD_TYPE.equals(entityType))) {
                	
                    ProductBO productBO = new ProductBO();
                    ProductAdapterManager adapterManager = new ProductAdapterManager();
                    productBO.setProductKey(individualAdminOverrideBO
                            .getEntitySysId());
                    productBO = adapterManager.retrieve(productBO);
                    productBO.setLastUpdatedUser(audit.getUser());
                    productBO.setLastUpdatedTimestamp(audit.getTime());
                    adapterManager.updateProduct(productBO,
                            adapterServiceAccess);
                } else if ((BusinessConstants.CONTRACT.equals(entityType))
                        || WebConstants.CONTRACT.equals(entityType)) {
                    Contract contract = new Contract();
                    ContractAdapterManager adapterManager = new ContractAdapterManager();
                    contract.setContractSysId(individualAdminOverrideBO
                            .getContractSysId());
                    contract = adapterManager.retrieveContract(contract);
                    contract.setLastUpdatedUser(audit.getUser());
                    contract.setLastUpdatedTimestamp(audit.getTime());
                    adapterManager.updateContract(contract,
                            adapterServiceAccess);
                }

            }
            AdapterUtil.endTransaction(adapterServiceAccess);
            AdapterUtil
                    .logTheEndTransaction(transactionId, this ,                           
                            "persistOverriddenAdminMethods(List adminMethodsId, List spsId, AdminMethodOverrideBO individualAdminOverrideBO,	List adminMethodBoRetrveList, User user)");
        } catch (AdapterException ex) {
            AdapterUtil.abortTransaction(adapterServiceAccess);
            AdapterUtil
                    .logAbortTxn(transactionId , this , 
                            "persistOverriddenAdminMethods(List adminMethodsId, List spsId, AdminMethodOverrideBO individualAdminOverrideBO,	List adminMethodBoRetrveList, User user)");
            throw new SevereException(null, null, ex);
        } catch (Exception ex) {
            AdapterUtil.abortTransaction(adapterServiceAccess);
            AdapterUtil
                    .logAbortTxn(transactionId , this , 
                            "persistOverriddenAdminMethods(List adminMethodsId, List spsId, AdminMethodOverrideBO individualAdminOverrideBO,	List adminMethodBoRetrveList, User user)");
            throw new SevereException("Unknown exception found.", null, ex);
        }

        return success;
    }
    //CARS:AM2:START{
    /**
     * Method to persist overridden tier admin methods.
     * 
     * @param tieredAdminMethodFromView
     * @param user
     * @return success
     * @throws SevereException
     * @throws AdapterException
     */
    public boolean persistOverriddenTieredAdminMethods(AdminMethodTierOverrideBO tieredAdminMethodFromView,User user) throws SevereException,AdapterException 
	{
        boolean success = false;
        long transactionId = AdapterUtil.getTransactionId();
        AuditFactory auditFactory = (AuditFactory) ObjectFactory.getFactory(ObjectFactory.AUDIT);
        Audit audit = auditFactory.getAudit(user);
        AdapterServicesAccess adapterServiceAccess = AdapterUtil.getAdapterService();
        AdminMethodAdapterManager adminMethodAdapterManager = new AdminMethodAdapterManager();
        try 
		{
            AdapterUtil.beginTransaction(adapterServiceAccess);
            AdapterUtil.logBeginTranstn(transactionId, this,"persistOverriddenAdminMethods(AdminMethodTierOverrideBO tieredAdminMethodFromView,List tieredAdminMethodListFromDB, User user)");
            if (null != tieredAdminMethodFromView) 
            {
            	String entityType = tieredAdminMethodFromView.getEntityType();
                    tieredAdminMethodFromView.setCreatedTimestamp(audit.getTime());
                    tieredAdminMethodFromView.setCreatedUser(audit.getUser());
                    tieredAdminMethodFromView.setLastUpdatedUser(audit.getUser());
                    tieredAdminMethodFromView.setLastUpdatedTimestamp(audit.getTime());
                    // If the spsid are the same
                    // Check whether the rBO adminId is empty and sBO adminId
                    // has value
                    if (null != tieredAdminMethodFromView && null != tieredAdminMethodFromView.getStatus())
                    {
                    	if(tieredAdminMethodFromView.getStatus().trim().equalsIgnoreCase("I"))
						{
                    		success = this.persist(tieredAdminMethodFromView, user.getUserId(), adapterServiceAccess);  
                    		deleteInValidSPSFromAffectedInValidSPSTable(tieredAdminMethodFromView,audit, adapterServiceAccess, adminMethodAdapterManager, entityType);
						}
                    	else if(tieredAdminMethodFromView.getStatus().trim().equalsIgnoreCase("U"))
						{
                    		success = this.persist(tieredAdminMethodFromView, user.getUserId(), adapterServiceAccess);  
                    		deleteInValidSPSFromAffectedInValidSPSTable(tieredAdminMethodFromView,audit, adapterServiceAccess, adminMethodAdapterManager, entityType);
						}
                    	else if(tieredAdminMethodFromView.getStatus().trim().equalsIgnoreCase("D"))
						{
                    		success = this.delete(tieredAdminMethodFromView, user.getUserId(), adapterServiceAccess);                        
                            // After saving, delete the from the invalid status table
                            deleteInValidSPSFromAffectedInValidSPSTable(tieredAdminMethodFromView,audit, adapterServiceAccess, adminMethodAdapterManager, entityType);
						}
                    }                                                          
                }

            
            AdapterUtil.endTransaction(adapterServiceAccess);
            AdapterUtil
                    .logTheEndTransaction(transactionId, this ,                           
                            "persistOverriddenAdminMethods(List adminMethodsId, List spsId, AdminMethodOverrideBO individualAdminOverrideBO,	List adminMethodBoRetrveList, User user)");
        } catch (AdapterException ex) {
            AdapterUtil.abortTransaction(adapterServiceAccess);
            AdapterUtil
                    .logAbortTxn(transactionId , this , 
                            "persistOverriddenAdminMethods(List adminMethodsId, List spsId, AdminMethodOverrideBO individualAdminOverrideBO,	List adminMethodBoRetrveList, User user)");
            throw new SevereException(null, null, ex);
        } catch (Exception ex) {
            AdapterUtil.abortTransaction(adapterServiceAccess);
            AdapterUtil
                    .logAbortTxn(transactionId , this , 
                            "persistOverriddenAdminMethods(List adminMethodsId, List spsId, AdminMethodOverrideBO individualAdminOverrideBO,	List adminMethodBoRetrveList, User user)");
            throw new SevereException("Unknown exception found.", null, ex);
        }

        return success;
    }
    //CARS:AM2:END}

    /**
	 * @param individualAdminOverrideBO
	 * @param audit
	 * @param adapterServiceAccess
	 * @param adminMethodAdapterManager
	 * @param entityType
	 * @throws SevereException
	 * @throws AdapterException
	 */
	private void deleteInValidSPSFromAffectedInValidSPSTable(AdminMethodOverrideBO individualAdminOverrideBO, Audit audit, AdapterServicesAccess adapterServiceAccess, AdminMethodAdapterManager adminMethodAdapterManager, String entityType) throws SevereException, AdapterException {
		// After reselecting an SPS admin method, delete the invalid status from the 
		// invalid SPS table of the corresponding module
		if ((BusinessConstants.PRODSTRUCTURE_ENTITY
		        .equals(entityType))
		        || (WebConstants.PROD_STRUCT.equals(entityType))) {
		
			// Change the status to valid if it is invalid in the affected SPS table
			AdminMethodValidationBO validationBO = new AdminMethodValidationBO();
			validationBO.setEntitySysId(individualAdminOverrideBO.getEntitySysId());
			validationBO.setBenefitComSysId(individualAdminOverrideBO.getBenefitCompSysId());
			validationBO.setBenefitAdminSysId(individualAdminOverrideBO.getBnftAdmnId());
			validationBO.setSpsId(individualAdminOverrideBO.getSpsId());
			adminMethodAdapterManager.deleteAdminMethodsValidation(validationBO, audit.getUser(), adapterServiceAccess);
		}else if ((BusinessConstants.PRODUCT.equals(entityType))
		        || (WebConstants.PROD_TYPE.equals(entityType))) {
			
			// Change the status to valid if it is invalid in the affected SPS table
			AdminMethodProductValidationBO validationBO = new AdminMethodProductValidationBO();
			validationBO.setEntitySysId(individualAdminOverrideBO.getEntitySysId());
			validationBO.setBenefitComSysId(individualAdminOverrideBO.getBenefitCompSysId());
			validationBO.setBenefitAdminSysId(individualAdminOverrideBO.getBnftAdmnId());
			validationBO.setSpsId(individualAdminOverrideBO.getSpsId());
			//CARS:AM2:START
			validationBO.setTierSysId(individualAdminOverrideBO.getTierSysId());
			//CARS:AM2:END
			
			adminMethodAdapterManager.deleteAdminMethodsValidation(validationBO, audit.getUser(), adapterServiceAccess);
		}else if ((BusinessConstants.CONTRACT.equals(entityType))
		        || WebConstants.CONTRACT.equals(entityType)) {
			
			// Change the status to valid if it is invalid in the affected SPS table
			AdminMethodContractValidationBO validationBO = new AdminMethodContractValidationBO();
			validationBO.setEntitySysId(individualAdminOverrideBO.getEntitySysId());
			validationBO.setBenefitComSysId(individualAdminOverrideBO.getBenefitCompSysId());
			validationBO.setBenefitAdminSysId(individualAdminOverrideBO.getBnftAdmnId());						
			validationBO.setSpsId(individualAdminOverrideBO.getSpsId());
			//CARS:AM2:START
			validationBO.setTierSysId(individualAdminOverrideBO.getTierSysId());
			//CARS:AM2:END
			adminMethodAdapterManager.deleteAdminMethodsValidation(validationBO, audit.getUser(), adapterServiceAccess);
		}
	}



	/**
     * Method to persist overridden admin methods.
     * 
     * @param adminMethodOverrideBO,user,adapterServiceAccess
     * @return boolean
     * @throws SevereException
     * @throws AdapterException
     */
    public boolean persist(AdminMethodOverrideBO adminMethodOverrideBO,
            String user, AdapterServicesAccess adapterServiceAccess,
            boolean insertFlag) throws SevereException {
        AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();

        if (insertFlag) {
            try {
                adapterManager.insertOverriddenAdminMethods(
                        adminMethodOverrideBO, user, adapterServiceAccess);
            } catch (AdapterException ex) {
                List errorParams = new ArrayList();
                String obj = ex.getClass().getName();
                errorParams.add(obj);
                errorParams.add(obj.getClass().getName());
                throw new SevereException(
                        "Exception occured in persist method, in AdminMethodOverrideBusinessObjectBuilder",
                        errorParams, ex);
            } catch (Exception e) {
                throw new SevereException("Unhandled exception is caught",
                        null, e);
            }
        } else {
            try {
                adapterManager.updateAdminMethods(adminMethodOverrideBO, user,
                        adapterServiceAccess);
            } catch (AdapterException ex) {
                List errorParams = new ArrayList();
                String obj = ex.getClass().getName();
                errorParams.add(obj);
                errorParams.add(obj.getClass().getName());
                throw new SevereException(
                        "Exception occured in persist method, in AdminMethodOverrideBusinessObjectBuilder",
                        errorParams, ex);
            } catch (Exception e) {
                throw new SevereException("Unhandled exception is caught",
                        null, e);
            }
        }
        return true;
    }
//CARS:AM2:START{
    public boolean persist(AdminMethodTierOverrideBO adminMethodOverrideBO,String user, AdapterServicesAccess adapterServiceAccess) throws SevereException 
	{
        AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();

        if (adminMethodOverrideBO.getStatus().equalsIgnoreCase("I")) {
            try {
                adapterManager.insertOverriddenTieredAdminMethods(adminMethodOverrideBO, user, adapterServiceAccess);
            } catch (AdapterException ex) {
                List errorParams = new ArrayList();
                String obj = ex.getClass().getName();
                errorParams.add(obj);
                errorParams.add(obj.getClass().getName());
                throw new SevereException(
                        "Exception occured in persist method, in AdminMethodOverrideBusinessObjectBuilder",
                        errorParams, ex);
            } catch (Exception e) {
                throw new SevereException("Unhandled exception is caught",
                        null, e);
            }
        } else {
            try {
                adapterManager.updateTieredAdminMethods(adminMethodOverrideBO,user,adapterServiceAccess);
            } catch (AdapterException ex) {
                List errorParams = new ArrayList();
                String obj = ex.getClass().getName();
                errorParams.add(obj);
                errorParams.add(obj.getClass().getName());
                throw new SevereException(
                        "Exception occured in persist method, in AdminMethodOverrideBusinessObjectBuilder",
                        errorParams, ex);
            } catch (Exception e) {
                throw new SevereException("Unhandled exception is caught",
                        null, e);
            }
        }
        return true;
    }
    public boolean delete(AdminMethodTierOverrideBO adminMethodOverrideBO,
            String user, AdapterServicesAccess adapterServiceAccess)
            throws SevereException {
        AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();
        try {
            adapterManager.deleteTieredOverriddenAdminMethods(adminMethodOverrideBO,
                    user, adapterServiceAccess);
        } catch (AdapterException ex) {
            List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in delete method, in AdminMethodOverrideBusinessObjectBuilder",
                    errorParams, ex);
        } catch (Exception e) {
            throw new SevereException("Unhandled exception is caught", null, e);
        }
        return true;
    }

//CARS:AM2:END}    
    /**
     * Method to delete overridden admin methods.
     * 
     * @param adminMethodOverrideBO,user,adapterServiceAccess
     * @return boolean
     * @throws SevereException
     * @throws AdapterException
     */
    public boolean delete(AdminMethodOverrideBO adminMethodOverrideBO,
            String user, AdapterServicesAccess adapterServiceAccess)
            throws SevereException {
        AdminMethodAdapterManager adapterManager = new AdminMethodAdapterManager();
        try {
            adapterManager.deleteOverriddenAdminMethods(adminMethodOverrideBO,
                    user, adapterServiceAccess);
        } catch (AdapterException ex) {
            List errorParams = new ArrayList();
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in delete method, in AdminMethodOverrideBusinessObjectBuilder",
                    errorParams, ex);
        } catch (Exception e) {
            throw new SevereException("Unhandled exception is caught", null, e);
        }
        return true;
    }
}