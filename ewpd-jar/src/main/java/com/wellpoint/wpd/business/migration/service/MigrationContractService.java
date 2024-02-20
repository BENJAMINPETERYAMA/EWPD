/*
 * MigrationContractService.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.migration.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.adminmethod.builder.AdminMethodBusinessObjectBuilder;
import com.wellpoint.wpd.business.common.adapter.SequenceAdapterManager;
import com.wellpoint.wpd.business.common.util.DomainUtil;
import com.wellpoint.wpd.business.contract.builder.ContractBusinessObjectBuilder;
import com.wellpoint.wpd.business.contract.locateresult.RuleLocateResult;
import com.wellpoint.wpd.business.framework.bo.AuditFactory;
import com.wellpoint.wpd.business.framework.bo.BusinessObjectManagerFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager;
import com.wellpoint.wpd.business.framework.service.ValidationServiceController;
import com.wellpoint.wpd.business.framework.service.WPDBusinessService;
import com.wellpoint.wpd.business.legacy.builder.LegacyBuilder;
import com.wellpoint.wpd.business.migration.adapter.MigrationAdapterManager;
import com.wellpoint.wpd.business.migration.builder.MigrationBusinessObjectBuilder;
import com.wellpoint.wpd.business.migration.util.ExclusionMigrationUtil;
import com.wellpoint.wpd.business.migration.util.MigrationContractUtil;
import com.wellpoint.wpd.business.product.adapter.ProductAdapterManager;
import com.wellpoint.wpd.business.product.builder.ProductBusinessObjectBuilder;
import com.wellpoint.wpd.business.product.locatecriteria.ProductRuleRefDataLocateCriteria;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.AuditImpl;
import com.wellpoint.wpd.common.bo.DataFeedStatus;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.contract.bo.Contract;
import com.wellpoint.wpd.common.domain.bo.Domain;
import com.wellpoint.wpd.common.domain.bo.DomainItem;
import com.wellpoint.wpd.common.domain.bo.MigrationDomain;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.framework.util.StringUtil;
import com.wellpoint.wpd.common.legacycontract.bo.CP2000Contract;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyContract;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyObject;
import com.wellpoint.wpd.common.migration.bo.BenefitComponentNote;
import com.wellpoint.wpd.common.migration.bo.BenefitLineNote;
import com.wellpoint.wpd.common.migration.bo.CancelMigration;
import com.wellpoint.wpd.common.migration.bo.CancelSecondMigration;
import com.wellpoint.wpd.common.migration.bo.ConfirmMigrationContractForSave;
import com.wellpoint.wpd.common.migration.bo.DetachProduct;
import com.wellpoint.wpd.common.migration.bo.MigrateNotesBO;
import com.wellpoint.wpd.common.migration.bo.MigrationContract;
import com.wellpoint.wpd.common.migration.bo.MigrationContractRulesAssnBO;
import com.wellpoint.wpd.common.migration.bo.MigrationContractRulesBO;
import com.wellpoint.wpd.common.migration.bo.MigrationDateSegment;
import com.wellpoint.wpd.common.migration.bo.MigrationDomainInfo;
import com.wellpoint.wpd.common.migration.bo.MigrationMappedVariables;
import com.wellpoint.wpd.common.migration.bo.MigrationPossibleAnswer;
import com.wellpoint.wpd.common.migration.bo.MigrationPricing;
import com.wellpoint.wpd.common.migration.bo.MigrationProduct;
import com.wellpoint.wpd.common.migration.bo.MigrationProductAdministration;
import com.wellpoint.wpd.common.migration.bo.MigrationVariableInsert;
import com.wellpoint.wpd.common.migration.bo.NavigationInfo;
import com.wellpoint.wpd.common.migration.bo.ProductRule;
import com.wellpoint.wpd.common.migration.bo.RemoveProduct;
import com.wellpoint.wpd.common.migration.bo.UpdateMigrationContractStatus;
import com.wellpoint.wpd.common.migration.request.CancelMigrationRequest;
import com.wellpoint.wpd.common.migration.request.ConfirmMigrationContractRequest;
import com.wellpoint.wpd.common.migration.request.MigrationContractRequest;
import com.wellpoint.wpd.common.migration.request.MigrationContractRulesRequest;
import com.wellpoint.wpd.common.migration.request.MigrationGenerateReportRequest;
import com.wellpoint.wpd.common.migration.request.MigrationPricingInfoRequest;
import com.wellpoint.wpd.common.migration.request.MigrationProductInfoRequest;
import com.wellpoint.wpd.common.migration.request.RetrieveBenefitDetailsRequest;
import com.wellpoint.wpd.common.migration.request.RetrieveMigGeneralInfoRequest;
import com.wellpoint.wpd.common.migration.request.RetrieveMigVariableMappingRequest;
import com.wellpoint.wpd.common.migration.request.SaveLastAccessPageInfoRequest;
import com.wellpoint.wpd.common.migration.request.SaveLegacyContractRequest;
import com.wellpoint.wpd.common.migration.request.SaveMigGeneralInfoRequest;
import com.wellpoint.wpd.common.migration.request.SaveMigVariableMappingRequest;
import com.wellpoint.wpd.common.migration.request.SaveMigrationNotesRequest;
import com.wellpoint.wpd.common.migration.response.CancelMigrationResponse;
import com.wellpoint.wpd.common.migration.response.ConfirmMigrationContractResponse;
import com.wellpoint.wpd.common.migration.response.MigrationContractRulesResponse;
import com.wellpoint.wpd.common.migration.response.MigrationGenerateReportResponse;
import com.wellpoint.wpd.common.migration.response.MigrationPricingInfoResponse;
import com.wellpoint.wpd.common.migration.response.MigrationProductInfoResponse;
import com.wellpoint.wpd.common.migration.response.RetrieveBenefitDetailsResponse;
import com.wellpoint.wpd.common.migration.response.RetrieveMigGeneralInfoResponse;
import com.wellpoint.wpd.common.migration.response.RetrieveMigVariableMappingResponse;
import com.wellpoint.wpd.common.migration.response.SaveLastAccessPageInfoResponse;
import com.wellpoint.wpd.common.migration.response.SaveLegacyContractResponse;
import com.wellpoint.wpd.common.migration.response.SaveMigGeneralInfoResponse;
import com.wellpoint.wpd.common.migration.response.SaveMigVariableMappingResponse;
import com.wellpoint.wpd.common.migration.response.SaveMigrationNotesResponse;
import com.wellpoint.wpd.common.migration.vo.MigProductMappingVO;
import com.wellpoint.wpd.common.migration.vo.MigrationContractSession;
import com.wellpoint.wpd.common.product.bo.ProductBO;
import com.wellpoint.wpd.common.product.bo.ProductRuleAssociation;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id:
 */
public class MigrationContractService extends WPDBusinessService {
	/**
	 * 
	 * @param request
	 * @return
	 * @throws ServiceException
	 * @throws AdapterException
	 */
	public WPDResponse execute(SaveMigGeneralInfoRequest request)
			throws ServiceException, AdapterException {
		Audit audit = getAudit(request.getUser());
		Logger.logInfo("Entering execute method, request = "
				+ request.getClass().getName());
		try {
			SaveMigGeneralInfoResponse saveResponse = (SaveMigGeneralInfoResponse) new ValidationServiceController()
																						.execute(request);
			// Returns if validation fails.
			if (!saveResponse.isValid()) {
				return saveResponse;
			}
			
			MigrationBusinessObjectBuilder migrationBusinessObjectBuilder = new MigrationBusinessObjectBuilder();
			
			MigrationDateSegment migDS = new MigrationDateSegment();
			int action = request.getAction();
	
			switch (action) {
			case SaveMigGeneralInfoRequest.RETRIEVE_PRODUCT_PARENT_SYS_ID:   
				//This case for Retrieve Product Parent System Id
				
				MigrationDateSegment migrationDateSegment = new MigrationDateSegment();
			    migrationDateSegment.setContractSysId(request.getContractSysId());
			    saveResponse.setMigDateSegment(migrationBusinessObjectBuilder.retrieveProductParentSysId( migrationDateSegment));
			    break;

			case SaveMigGeneralInfoRequest.SAVE_MIG_GENERAL_INFO:		
										
				 MigrationContract migrationContract;
					MigrationDomainInfo migDomainInfo = new MigrationDomainInfo();
					MigrationDomain migDI = new MigrationDomain();
					MigrationContractUtil.setValuesToMigDS(migDS, request);
					MigrationContractUtil.setValuesToMigDI(migDI, request);
		
					// Checking whether new domains are mathching with the product (if product already associated)
					
					if((null==request.getBaseContractId()) ||("".equalsIgnoreCase(request.getBaseContractId()))){
						if( !isAssociatedProductValid(request)){
							
							/* Added for detaching Product from step 5
							 * and the related information on the ongoing steps	
							 */
							
							DetachProduct detachProduct = new DetachProduct();
							detachProduct.setMigContractSysId(request.getContractSysId());
							detachProduct.setMigDateSegmentSysId(request.getDateSegmentSysId());
							
							//boolean detachStatus = migrationBusinessObjectBuilder.cancelMigration(cancelMigration, audit));
							boolean detachStatus = migrationBusinessObjectBuilder.detachProduct(detachProduct, audit);
							if(detachStatus){
								migrationBusinessObjectBuilder.persistMigGenInfo(migDS, audit);
								migrationBusinessObjectBuilder.persistDomainInfo(migDI, audit);
								saveResponse.addMessage(new InformationalMessage(BusinessConstants.PRODUCT_DETACHED_SUCCESSFULLY));
								request.getMigrationContractSession().getNavigationInfo().setUpdateLastAccessedPageOnly(true);
								request.getMigrationContractSession().getNavigationInfo().setStepCompleted(4);
								migrationContract = updateStepCompleted(request,
			                    		"4", audit, migrationBusinessObjectBuilder);
								MigrationContractUtil.setMigrationContractSessionToResponse(
			    						migrationContract, request, saveResponse);
								saveResponse.setSuccess(detachStatus);
								return saveResponse;
							}
							else
								return null;
						}
					}
					
					//Retrieve the migratenotes flag for the date segment.
					
					MigrationDateSegment dateSegment = new MigrationDateSegment();
					dateSegment.setSystemId(request.getDateSegmentSysId());
					List dateSegmentList = migrationBusinessObjectBuilder.searchContractForMigration(dateSegment,audit);
					Iterator iterator = dateSegmentList.iterator();
					while(iterator.hasNext()){
						MigrationDateSegment dateSegmentObjForFlag = (MigrationDateSegment)iterator.next();
						if(null!=dateSegmentObjForFlag){
							migDS.setContractNoteMigrateFlag(dateSegmentObjForFlag.getContractNoteMigrateFlag());
						}
					}
					
					migrationBusinessObjectBuilder.persistMigGenInfo(migDS, audit);
					migrationBusinessObjectBuilder.persistDomainInfo(migDI, audit);
					request.getMigrationContractSession()
							.getMigrationContract()
							.setContractType(request.getContractType());
		//			request.getMigrationContractSession().getMigrationContract()
		//			.setDoneFlag(MigrationContractUtil.DONE_FLAG_NO);
					request.getMigrationContractSession()
							.getMigrationContract()
							.setDoneFlag(request.getMigrationContractSession()
												.getMigrationContract()
												.getDoneFlag());
					request.getMigrationContractSession()
							.getMigrationContract()
							.setHeadQuartersState(request.getHeadQuartersState());
					request.getMigrationContractSession()
							.getMigrationContract()
							.setBaseDateSegmentId(String.valueOf(request.getBaseDateSegmentSysId()));
					
					// persists the legacy pricing info to migration pricing table.
					migrationBusinessObjectBuilder.persistPricingInfo(request.getDateSegmentSysId(),audit,WPDStringUtil.getStringDate(request.getMigrationContractSession().getStartDateLegacy()));
					 
					// added to update step number completed
	               
	                
	                    migrationContract = updateStepCompleted(request,
	                    		"3", audit, migrationBusinessObjectBuilder);
	                    MigrationContractUtil.setMigrationContractSessionToResponse(
	    						migrationContract, request, saveResponse);
                 
                    
				saveResponse.setSuccess(true);
				break;
				
				case SaveMigGeneralInfoRequest.RETRIEVE_PRODUCT_ATTACHED:
				    if(!StringUtil.isEmpty(request.getBaseContractId())){
				        migDS.setBaseContractSysId(Integer.parseInt(request.getBaseContractId()));
				    }
					migDS.setEffectiveDate(request.getStartDate());
					migDS.setExpiryDate(request.getEndDate());
					saveResponse.setProductIdList(migrationBusinessObjectBuilder.getProductAttachedToBaseContract(migDS,audit));
					saveResponse.setSuccess(true);
				break;
				case SaveMigGeneralInfoRequest.RETRIEVE_MAPPED_PRODUCT_ID:
				    
				    migDS.setMappingSysId(request.getMigrationContractSession().getMigrationContract().getMigratedProdStructureMappingSysID());
					if(!StringUtil.isEmpty(migDS.getMappingSysId())){
					    saveResponse.setProductIdList(migrationBusinessObjectBuilder.getMappedProductID(migDS,audit));  
					}
					saveResponse.setSuccess(true);
				    
				break;
				case SaveMigGeneralInfoRequest.RETRIEVE_BY_CY_CONFLICT_MESSAGE:
					migrationContract = request.getMigrationContractSession().getMigrationContract();
					String contractId = migrationContract.getContractId();
					String migSysIdString = migrationContract.getMigrationSystemId();
					int baseDateSegSysId = request.getBaseDateSegmentSysId();
					List dateSegments = migrationBusinessObjectBuilder.getDateSegmentDetails(migSysIdString);
					String ewpdPlanRenewalType = migrationBusinessObjectBuilder.getPlanRenewalType(BusinessConstants.ENTITY_CONTRACT,baseDateSegSysId);
					boolean conflict = isConflictInPlanRenewalType(ewpdPlanRenewalType,contractId,dateSegments);
					if(conflict) {
						saveResponse.setBYCYConflict(true);
					}
					break;
			}
			Logger.logInfo("Returning execute method, request = "
					+ request.getClass().getName());
			return saveResponse;
		} catch (WPDException ex) {
			throw new ServiceException("Exception occured while BOM call",
					null, ex);
		}
	}
	/**
	 * 
	 * @param request
	 * @return
	 * @throws SevereException
	 */
	private boolean isAssociatedProductValid(SaveMigGeneralInfoRequest request) throws SevereException{
		MigrationBusinessObjectBuilder migBuilder = new MigrationBusinessObjectBuilder();
		ProductBusinessObjectBuilder prdBuilder = new ProductBusinessObjectBuilder();
		MigrationContract migrationContract = new MigrationContract();
		migrationContract.setMigrationSystemId(""+request.getContractSysId());
		migrationContract = migBuilder.retrieve(migrationContract);
		String productSysIdString = migrationContract.getEwpdProductSystemId();
		// If product associated
		if(productSysIdString != null && !productSysIdString.trim().equals("")) {
			int prodSysIdInt = Integer.parseInt(productSysIdString);
			int prodParentId = 0;
			if(prodSysIdInt != 0) {
				ProductBO productBO = new ProductBO();
				productBO.setProductKey(prodSysIdInt);
				try {
					productBO = (ProductBO)prdBuilder.retrieve(productBO);
				} catch (WPDException ex) {
					throw new SevereException(ex.getMessage(),null,ex);
				}
				prodParentId = productBO.getParentProductKey();
				// ------------------ Domain validation -------------------
				List migContDomainItems = null;
		        List productDomainItems = null;

		        Iterator iter1 = null;
		        Iterator iter2 = null;
		        String domainItem1 = null;
		        String domainItem2 = null;
		        boolean found = false;
		        boolean valid = true;

		        migContDomainItems = request.getLineOfBusinessList();
		        productDomainItems = DomainUtil.getLineOfBusiness(BusinessConstants.ENTITY_TYPE_PRODUCT, prodParentId);		        

		        lobCheck: for (iter1 = migContDomainItems.iterator(); iter1.hasNext();) {
		            domainItem1 = (String) iter1.next();
		            found = false;
		            for (iter2 = productDomainItems.iterator(); iter2.hasNext();) {
		                domainItem2 = ((DomainItem) iter2.next()).getItemId();
		                if (BusinessConstants.UNIVERSAL_DOMAIN.equals(domainItem2))
		                    break lobCheck;
		                if (domainItem1.equals(domainItem2)) {
		                    found = true;
		                    break;
		                }
		            }
		            if (!found) {
		                valid = false;
		                break;
		            }
		        }

		        if (valid) {
		            migContDomainItems = request.getBusinessEntityList();
		            productDomainItems = DomainUtil.getBusinessEntity(BusinessConstants.ENTITY_TYPE_PRODUCT, prodParentId);
		            beCheck: for (iter1 = migContDomainItems.iterator(); iter1
		                    .hasNext();) {
		                domainItem1 = (String) iter1.next();
		                found = false;
		                for (iter2 = productDomainItems.iterator(); iter2.hasNext();) {
		                    domainItem2 = ((DomainItem) iter2.next()).getItemId();
		                    if (BusinessConstants.UNIVERSAL_DOMAIN.equals(domainItem2))
		                        break beCheck;
		                    if (domainItem1.equals(domainItem2)) {
		                        found = true;
		                        break;
		                    }
		                }
		                if (!found) {
		                    valid = false;
		                    break;
		                }
		            }
		        }

		        if (valid) {
		            migContDomainItems = request.getBusinessGroupList();
		            productDomainItems = DomainUtil.getBusinessGroup(BusinessConstants.ENTITY_TYPE_PRODUCT, prodParentId);
		            bgCheck: for (iter1 = migContDomainItems.iterator(); iter1
		                    .hasNext();) {
		                domainItem1 = (String) iter1.next();
		                found = false;
		                for (iter2 = productDomainItems.iterator(); iter2.hasNext();) {
		                    domainItem2 = ((DomainItem) iter2.next()).getItemId();
		                    if (BusinessConstants.UNIVERSAL_DOMAIN.equals(domainItem2))
		                        break bgCheck;
		                    if (domainItem1.equals(domainItem2)) {
		                        found = true;
		                        break;
		                    }
		                }
		                if (!found) {
		                    valid = false;
		                    break;
		                }
		            }
		        }
		        return valid;
		        //  ------------------ Domain validation -------------------
			}
		}
		return true;
	}
	/**
	 * 
	 * @param request
	 * @return
	 * @throws ServiceException
	 * @throws AdapterException
	 */
	public WPDResponse execute(RetrieveMigGeneralInfoRequest request)
			throws ServiceException, AdapterException {
		Logger.logInfo("Entering execute method, request = "
				+ request.getClass().getName());
		try {
			Audit audit = getAudit(request.getUser());
			MigrationDateSegment migDateSegment = new MigrationDateSegment();
			MigrationDomainInfo migDomainInfo = new MigrationDomainInfo();
			migDateSegment.setSystemId(request.getMigDateSegmentSysId());
			migDateSegment.setContractSysId(request.getMigContractSysId());
			RetrieveMigGeneralInfoResponse retrieveMigGeneralInfoResponse = (RetrieveMigGeneralInfoResponse)WPDResponseFactory
						.getResponse(WPDResponseFactory.RETRIEVE_MIG_GENINFO_RESPONSE);
			
			MigrationBusinessObjectBuilder migrationBusinessObjectBuilder = new MigrationBusinessObjectBuilder();
			MigrationContract migrationContract = updateStepCompleted(request,
					"3", audit, migrationBusinessObjectBuilder);
			retrieveMigGeneralInfoResponse
					.setMigDateSegment(migrationBusinessObjectBuilder
							.retrieveMigGenInfo(migDateSegment));
			retrieveMigGeneralInfoResponse
					.setMigrationDomainInfoList(migrationBusinessObjectBuilder
							.retrieveMigDomainInfo(request
									.getMigContractSysId()));
			Logger.logInfo("Returning execute method, request = "
					+ request.getClass().getName());
			return retrieveMigGeneralInfoResponse;
		} catch (WPDException ex) {
			throw new ServiceException("Exception occured while builder call",
					null, ex);
		}
	}
	/**
	 * 
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(CancelMigrationRequest request)
			throws ServiceException {
		Logger.logInfo("Entering execute method, request = "
				+ request.getClass().getName());
		try {			
		    int migContractSysID = request.getMigContractSysId();
			Audit audit = getAudit(request.getUser());
			CancelMigration cancelMigration = new CancelMigration();
			CancelSecondMigration cancelSecMig = new CancelSecondMigration();
			cancelMigration.setMigContractSysId(request.getMigContractSysId());
			cancelMigration.setMigDateSegmentSysId(request
					.getMigDateSegmentSysId());
			cancelSecMig.setMigContractSysId(request.getMigContractSysId());
			cancelSecMig.setMigDateSegmentSysId(request
					.getMigDateSegmentSysId());
			//cancelSecMig.setDoneFlag(request.getDoneFlag());
			
			CancelMigrationResponse cancelMigResponse = (CancelMigrationResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.CANCEL_MIGRATION_RESPONSE);

			MigrationBusinessObjectBuilder migrationBusinessObjectBuilder = new MigrationBusinessObjectBuilder();
			//for second time migration 
			if(request.getDoneFlag().equals(MigrationContractUtil.DONE_FLAG_SECOND)){
				this.updateMigrationStatus(migContractSysID,	
						MigrationContractUtil.DONE_FLAG_YES,
						BusinessConstants.OPT_MIGRATE_LATEST_DS,
						audit);
			    cancelMigResponse.setSuccess(migrationBusinessObjectBuilder
						.cancelMigration(cancelSecMig, audit));
			}
			else{			    

			    cancelMigResponse.setSuccess(migrationBusinessObjectBuilder
					.cancelMigration(cancelMigration, audit));
			}

		    String migrationContractID = request.getMigContractId();
		    String system = request.getSystem();
		    LegacyContract contract = new LegacyContract();
		    contract.setContractId(migrationContractID);
		    contract.setSystem(system);
		    contract.setCreatedUser(audit.getUser());
		    contract.setCreatedTimestamp(audit.getTime());
		    String option = request.getOption();
			LegacyBuilder legacyBuilder = new LegacyBuilder();
				legacyBuilder.unLockAndChangeContractStatus(contract, option);
				
			cancelMigResponse.addMessage(new InformationalMessage(BusinessConstants.CANCEL_MIGRATION_SUCCESS));
			Logger.logInfo("Returning execute method, request = "
					+ request.getClass().getName());
			return cancelMigResponse;
		} catch (WPDException ex) {
			throw new ServiceException("Exception occured while builder call",
					null, ex);
		}

	}
	/**
	 * 
	 * @param request
	 * @return
	 * @throws ServiceException
	 * @throws AdapterException
	 */
	public WPDResponse execute(RetrieveMigVariableMappingRequest request)
			throws WPDException, AdapterException {
			Logger.logInfo("Entering execute method, request = "
				+ request.getClass().getName());		
			User user = request.getUser();
			Audit audit = getAudit(request.getUser());
			RetrieveMigVariableMappingResponse retrieveMigVariableMappingResponse = (RetrieveMigVariableMappingResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.RETRIEVE_MIG_VARIABLE_MAPPING_RESPONSE);
			int action = request.getAction();
			MigrationBusinessObjectBuilder migrationBusinessObjectBuilder = new MigrationBusinessObjectBuilder();
			MigrationContract migrationContracts = null;
			switch (action) {

			case RetrieveMigVariableMappingRequest.RETRIEVE_MIG_VARIABLE_MAPPING_DATA:
				
				migrationContracts = updateStepCompleted(request,
						"7", audit, migrationBusinessObjectBuilder);
				MigrationContractUtil.setMigrationContractSessionToResponse(
						migrationContracts, request, retrieveMigVariableMappingResponse);
				MigProductMappingVO productMappingVO = request
						.getMigProductMappingVO();
				Map map = MigrationContractUtil.getDetailsInMap(
						productMappingVO, request);
				List valueObjectList = MigrationContractUtil
						.getValueObjectBenefitList(migrationBusinessObjectBuilder
								.getBenefitList((HashMap) map, request
										.getUser()));
				retrieveMigVariableMappingResponse
						.setBenefitLineList(valueObjectList);
				
				break;

			
			case RetrieveMigVariableMappingRequest.RETRIEVE_CONFLICTING_DATA:
			
				MigrationContractSession  migrationContractSession = request.getMigrationContractSession();
				MigrationContract migrationContract = migrationContractSession.getMigrationContract();
				Map conflictMap = new HashMap();
					
				conflictMap.put("migDatesegmentSysId", new Integer(migrationContractSession.getDateSegmentId()));
			    
			    List conflictRecordList =MigrationContractUtil
						.getValueObjectBenefitList( migrationBusinessObjectBuilder.getConflictRecordList((HashMap) conflictMap, request
						.getUser()));
			    
			    
				
				retrieveMigVariableMappingResponse
								.setConflictRecordList(conflictRecordList);
			
			break;
			}		
			Logger.logInfo("Returning execute method, request = "
					+ request.getClass().getName());
			return retrieveMigVariableMappingResponse;
		 
	}
	/**
	 * 
	 * @param request
	 * @return
	 * @throws ServiceException
	 * @throws AdapterException
	 */
	public WPDResponse execute(SaveMigVariableMappingRequest request)
			throws WPDException, AdapterException {
			Logger.logInfo("Entering execute method, request = "
				+ request.getClass().getName());
			SaveMigVariableMappingResponse saveMigVariableMappingResponse = (SaveMigVariableMappingResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.SAVE_MIG_VARIABLE_MAPPING_RESPONSE);
			int action = request.getAction();
			MigrationBusinessObjectBuilder migrationBusinessObjectBuilder = new MigrationBusinessObjectBuilder();
			MigrationContract migrationContract = request.getMigrationContractSession().getMigrationContract();
			User user = request.getUser();
			Audit audit = getAudit(request.getUser());
			switch (action) {

			case SaveMigVariableMappingRequest.SAVE_MIG_VARIABLE_MAPPING_DATA:
				List variableMappingList = request.getVariableMappingList();
				List businessObjectList = null;
				List migrateNotesList = null;
				if (null != variableMappingList
						&& !variableMappingList.isEmpty()) {
					businessObjectList = new ArrayList(variableMappingList.size());
					migrateNotesList = new ArrayList(variableMappingList.size());
					for (int i = 0; i < variableMappingList.size(); i++) {

						MigProductMappingVO migProductMappingVO = (MigProductMappingVO) variableMappingList
								.get(i);
						MigrationVariableInsert migrationVariable = new MigrationVariableInsert();

						migrationVariable.setBftCompSysId(migProductMappingVO
								.getBenefitComponentId());
						migrationVariable.setVariableId(migProductMappingVO
								.getVariableId());
						migrationVariable.setVarDetails(migProductMappingVO
								.getVarDetails());
						migrationVariable.setBftLineId(migProductMappingVO
								.getBftLineId());
						migrationVariable
								.setMigDatesegmentID(migProductMappingVO.getDateSegmentId());

						migrationVariable.setMappingSysId(migProductMappingVO
								.getMappingSysId());
						
						if(WebConstants.FLAG_Y.equalsIgnoreCase(migProductMappingVO.getDeleteFlag())){

							migrationBusinessObjectBuilder
									.deleteVariableMappingList(migrationVariable,
											request.getUser()); 
						
						}
						

						if (!StringUtil.isEmpty(migrationVariable
								.getVariableId())) {
						  
							businessObjectList.add(migrationVariable);
						}
						
						if(WebConstants.FLAG_Y.equalsIgnoreCase(migProductMappingVO.getNotesFlag())){
							
							BenefitLineNote benefitLineNote = new BenefitLineNote();
							benefitLineNote.setMigDateSegSysId(migProductMappingVO.getDateSegmentId());
							benefitLineNote.setBenefitCompSysId(migProductMappingVO
									.getBenefitComponentId());
							benefitLineNote.setBenefitLineSysId(migProductMappingVO.getBftLineId());
							benefitLineNote.setMigrateNoteFlag(migProductMappingVO.getNotesFlag());							
							migrateNotesList.add(benefitLineNote);
						
						}else{
							BenefitLineNote benefitLineNote = new BenefitLineNote();
							benefitLineNote.setMigDateSegSysId(migProductMappingVO.getDateSegmentId());
							benefitLineNote.setBenefitCompSysId(migProductMappingVO
									.getBenefitComponentId());
							benefitLineNote.setBenefitLineSysId(migProductMappingVO.getBftLineId());
							migrationBusinessObjectBuilder.deleteMigrateNotesInfo(benefitLineNote,request.getUser());
						}
					}

				}

				migrationBusinessObjectBuilder.saveVariableMappingList(
						businessObjectList,migrateNotesList, request.getUser());
				
			
				migrationContract = updateStepCompleted(
						request, "7", audit,
						migrationBusinessObjectBuilder);
				MigrationContractUtil.setMigrationContractSessionToResponse(
						migrationContract, request,
						saveMigVariableMappingResponse);
				saveMigVariableMappingResponse.setSuccess(true);
				saveMigVariableMappingResponse.addMessage(new InformationalMessage(BusinessConstants.VARIABLES_SAVED_SUCCESS));
				break;
				
				case SaveMigVariableMappingRequest.DELETE_MIG_VARIABLE_MAPPING_DATA:
				    List deleteMappingRecordList = request.getVariableMappingList();

					if (null != deleteMappingRecordList
						&& !deleteMappingRecordList.isEmpty()) {			
	
							MigProductMappingVO migProductMappingVO = (MigProductMappingVO) deleteMappingRecordList
									.get(0);							
							ConfirmMigrationContractForSave confirmMigrationContractForSave = new ConfirmMigrationContractForSave();
							confirmMigrationContractForSave.setBftLineId(migProductMappingVO.getBftLineId());
							confirmMigrationContractForSave.setBftCompSysId(migProductMappingVO.getBenefitComponentId());
							confirmMigrationContractForSave.setMappingSysId(migProductMappingVO.getMappingSysId());
							migrationBusinessObjectBuilder.deleteVariableMappingMasterList(confirmMigrationContractForSave,request.getUser());							
						
					}
					saveMigVariableMappingResponse.setSuccess(true);
					saveMigVariableMappingResponse.addMessage(new InformationalMessage(BusinessConstants.VARIABLES_DELETED_SUCCESS));
				break;
				case SaveMigVariableMappingRequest.UPDATE_STEP_COMPLETED:
				    migrationContract = updateStepCompleted(
							request, "7", audit,
							migrationBusinessObjectBuilder);
					MigrationContractUtil.setMigrationContractSessionToResponse(
							migrationContract, request,
							saveMigVariableMappingResponse);
					saveMigVariableMappingResponse.setSuccess(true);
			   break;
			}
			Logger.logInfo("Returning execute method, request = "
					+ request.getClass().getName());
			return saveMigVariableMappingResponse;
		
	}

	/**
	 * Function to Locate, Persist, Remove a Migration Pricing Information.
	 * 
	 * @param MigrationPricingInfoRequest
	 * @param audit
	 * @param
	 * @return WPDResponse
	 * @throws ServiceException
	 */
	public WPDResponse execute(
			MigrationPricingInfoRequest migrationPricingInfoRequest)
			throws ServiceException {
		Logger.logInfo("Entering execute method, request = "
				+ migrationPricingInfoRequest.getClass().getName());
		MigrationPricingInfoResponse migrationPricingInfoResponse = null;
		MigrationBusinessObjectBuilder migrationBusinessObjectBuilder = new MigrationBusinessObjectBuilder();
		MigrationContract migrationContract = null;
		migrationPricingInfoResponse = (MigrationPricingInfoResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.MIGRATION_PRICING_INFO_RESPOSE);
		int action = migrationPricingInfoRequest.getAction();
		User user = migrationPricingInfoRequest.getUser();
		Audit audit = getAudit(migrationPricingInfoRequest.getUser());

		switch (action) {

		case MigrationPricingInfoRequest.MIGRATION_RETRIEVE_PRICING_INFO:
			try {
				HashMap params = new HashMap();
				
				migrationContract = updateStepCompleted(migrationPricingInfoRequest, "4", audit,
						migrationBusinessObjectBuilder);
				MigrationContractUtil.setMigrationContractSessionToResponse(
						migrationContract, migrationPricingInfoRequest,

								migrationPricingInfoResponse);
				params.put("contractDateSegmentSysId", new Integer(
						migrationPricingInfoRequest.getMigratedDSSysID()));
				migrationPricingInfoResponse
						.setPricingInformationList(migrationBusinessObjectBuilder
								.getPricingInfoList(params));
				migrationPricingInfoResponse.setSuccess(true);
			} catch (Exception ex) {
				List logParameters = new ArrayList(2);
				logParameters.add(migrationPricingInfoRequest);
				String logMessage = "LOCATE Failed: MigrationPricingInformation";
				throw new ServiceException(logMessage, logParameters, ex);
			}
			break;
		case MigrationPricingInfoRequest.MIGRATION_SAVE_ADD_PRICING_INFO:
			try {
				MigrationPricing migrationPricing = new MigrationPricing();
				MigrationContractUtil.populateMigrationPricing(
						migrationPricingInfoRequest, migrationPricing);
				// for checking duplicate entry
				MigrationContractUtil.setMigrationContractSessionToResponse(
						migrationContract, migrationPricingInfoRequest,
						migrationPricingInfoResponse);
				if (migrationBusinessObjectBuilder
						.isPricingInfoDuplicate(migrationPricing)) {
					migrationPricingInfoResponse.setSuccess(false);
					migrationPricingInfoResponse
							.addMessage(new ErrorMessage(
									BusinessConstants.MIGRATION_PRICINGINFO_SAVE_DUPLICATE));
					return migrationPricingInfoResponse;
				}
				migrationPricingInfoResponse
						.setSuccess(migrationBusinessObjectBuilder
								.persistMigrationPricingInfo(migrationPricing,
										audit));
				migrationPricingInfoResponse
						.addMessage(new InformationalMessage(BusinessConstants.PRICING_INFO_SAVE_SUCCESS));


			} catch (Exception ex) {
				List logParameters = new ArrayList(2);
				logParameters.add(migrationPricingInfoRequest);
				String logMessage = "PERSIST Failed: MigrationPricingInformation";
				throw new ServiceException(logMessage, logParameters, ex);
			}
			break;
		case MigrationPricingInfoRequest.MIGRATION_DELETE_PRICING_INFO:
			try {
				MigrationPricing migrationPricing = new MigrationPricing();
				MigrationContractUtil.populateMigrationPricing(
						migrationPricingInfoRequest, migrationPricing);
				migrationPricingInfoResponse
						.setSuccess(migrationBusinessObjectBuilder
								.deleteMigrationPricingInfo(migrationPricing,
										audit));
				migrationPricingInfoResponse
						.addMessage(new InformationalMessage(
								BusinessConstants.PRICING_INFO_DELETE_SUCCESS));
				MigrationContractUtil.setMigrationContractSessionToResponse(
						migrationContract, migrationPricingInfoRequest,
						migrationPricingInfoResponse);

			} catch (Exception ex) {
				List logParameters = new ArrayList(2);
				logParameters.add(migrationPricingInfoRequest);
				String logMessage = "DELETE Failed: MigrationPricingInformation";
				throw new ServiceException(logMessage, logParameters, ex);
			}
			break;
		case MigrationPricingInfoRequest.MIGRATION_DELETE_ALL_PRICING_INFO:
			try {
				MigrationPricing migrationPricing = new MigrationPricing();
				migrationPricing
						.setContractDateSegmentSysId(migrationPricingInfoRequest
								.getMigratedDSSysID());
				migrationPricingInfoResponse
						.setSuccess(migrationBusinessObjectBuilder
								.rollbackAllMigrationPricingInfo(
										migrationPricing, audit));
				MigrationContractUtil.setMigrationContractSessionToResponse(
						migrationContract, migrationPricingInfoRequest,
						migrationPricingInfoResponse);

				migrationPricingInfoResponse
						.addMessage(new InformationalMessage(
								BusinessConstants.PRICING_INFO_DELETE_ALL_SUCCESS));
			} catch (Exception ex) {
				List logParameters = new ArrayList(2);
				logParameters.add(migrationPricingInfoRequest);
				String logMessage = "ROLLBACK ALL Failed: MigrationPricingInformation";
				throw new ServiceException(logMessage, logParameters, ex);
			}
			break;
		case MigrationPricingInfoRequest.UPDATE_STEP_COMPLETED:
			try {
				migrationContract = updateStepCompleted(
						migrationPricingInfoRequest, "4", audit,
						migrationBusinessObjectBuilder);
				// migrationPricingInfoResponse.setMigrationContract(migrationContract);
				MigrationContractUtil.setMigrationContractSessionToResponse(
						migrationContract, migrationPricingInfoRequest,
						migrationPricingInfoResponse);

				migrationPricingInfoResponse.setSuccess(true);
				migrationPricingInfoResponse
						.addMessage(new InformationalMessage(
								BusinessConstants.STEP_NUMBER_UPDATE_SUCCESS));
				MigrationContractUtil.setMigrationContractSessionToResponse(
						migrationContract, migrationPricingInfoRequest,
						migrationPricingInfoResponse);
			} catch (Exception ex) {
				List logParameters = new ArrayList(2);
				logParameters.add(migrationPricingInfoRequest);
				String logMessage = "Update Step Number Failed: MigrationPricingInformation";
				throw new ServiceException(logMessage, logParameters, ex);
			}
			break;
		}
		Logger.logInfo("Entering execute method, request = "
				+ migrationPricingInfoRequest.getClass().getName());
		return migrationPricingInfoResponse;

	}

	/**
	 * Function to Locate a Migration Product Information.
	 * 
	 * @param contractSysId,dateSegmentId,legacyStructure,
	 * @param migrationBusinessObjectBuilder
	 * @param migrationProductInfoResponse
	 * @return WPDResponse
	 * @throws ServiceException
	 */
	private void retreiveProductInfo(int contractSysId, String dateSegmentId,String legacyStructure,
				MigrationBusinessObjectBuilder migrationBusinessObjectBuilder,
				MigrationProductInfoResponse migrationProductInfoResponse)
				throws WPDException,AdapterException {
//		List migrationProductList = new ArrayList();
		MigrationProduct migrationProduct;
		HashMap params = new HashMap();
		params.put("mgrtdDateSegmentId", new Integer(Integer.parseInt(dateSegmentId)));
		List migrationProductList = migrationBusinessObjectBuilder
				.getProductInfo(params);
		if (migrationProductList != null) {
			migrationProduct = (MigrationProduct) migrationProductList.get(0);
			migrationProductInfoResponse.setLegacyStructure(legacyStructure);
			migrationProductInfoResponse
					.setMigrationDomainInfoList(migrationBusinessObjectBuilder
							.retrieveMigDomainInfo(contractSysId));

			MigrationDateSegment migDateSegment = new MigrationDateSegment();
			MigrationDomainInfo migDomainInfo = new MigrationDomainInfo();
			migDateSegment.setSystemId(Integer.parseInt(dateSegmentId));
			migDateSegment.setContractSysId(contractSysId);
			migrationProductInfoResponse
					.setMigDateSegment(migrationBusinessObjectBuilder
							.retrieveMigGenInfo(migDateSegment));

			if (null != migrationProduct.getProductName()
					|| 0 != migrationProduct.getEwpdProductSysId()) {
				migrationProductInfoResponse.setPersistStatus(true);
				migrationProductInfoResponse.setProductName(migrationProduct
						.getProductName());
				migrationProductInfoResponse.setProductFamily(migrationProduct
						.getProductFamily());
				migrationProductInfoResponse.setEwpdProdSysId(migrationProduct
						.getEwpdProductSysId());
			}else if(0 != migDateSegment.getEwpdProductSystemId()){
				migrationProductInfoResponse.setPersistStatus(true);
				migrationProductInfoResponse.setProductName(migDateSegment
						.getProductName());
				migrationProductInfoResponse.setProductFamily(migDateSegment
						.getProductFamily());
				migrationProductInfoResponse.setEwpdProdSysId(migDateSegment
						.getEwpdProductSystemId());
			}
		}

		migrationProductInfoResponse.setSuccess(true);
	}

	/**
	 * Function to Locate, Persist, Remove a Migration Product Information.
	 * 
	 * @param MigrationProductInfoRequest
	 * @param audit
	 * @param
	 * @return WPDResponse
	 * @throws SevereException 
	 * @throws NumberFormatException 
	 */
	public WPDResponse execute(
			MigrationProductInfoRequest migrationProductInfoRequest)
			throws NumberFormatException, SevereException {
		Logger.logInfo("Entering execute method, request = "
				+ migrationProductInfoRequest.getClass().getName());
		
		MigrationProductInfoResponse migrationProductInfoResponse = (MigrationProductInfoResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.MIGRATION_PRODUCT_INFO_RESPOSE);
		MigrationBusinessObjectBuilder migrationBusinessObjectBuilder = new MigrationBusinessObjectBuilder();
		int action = migrationProductInfoRequest.getAction();
		Audit audit = getAudit(migrationProductInfoRequest.getUser());
		MigrationContractSession migrationContractSession = migrationProductInfoRequest
				.getMigrationContractSession();		
		MigrationContract migrationContract = migrationContractSession
				.getMigrationContract();		
		boolean isNewMapping = false;
		switch (action) {

		case MigrationProductInfoRequest.MIGRATION_RETRIEVE_PRODUCT_INFO:
			try {
				migrationContract = updateStepCompleted(migrationProductInfoRequest, "5", audit,
						migrationBusinessObjectBuilder);
				MigrationContractUtil.setMigrationContractSessionToResponse(
						migrationContract, migrationProductInfoRequest,
						migrationProductInfoResponse);
				retreiveProductInfo(Integer.parseInt(migrationContractSession
						.getMigrationContract().getMigrationSystemId()),
						migrationContractSession.getDateSegmentId(),
						migrationContractSession.getMigrationContract()
								.getStructreProductMappingId(),
						migrationBusinessObjectBuilder,
						migrationProductInfoResponse);
				MigrationContractUtil.setMigrationContractSessionToResponse(
						migrationContract, migrationProductInfoRequest,
						migrationProductInfoResponse);
			} catch (Exception ex) {
				List logParameters = new ArrayList(2);
				logParameters.add(migrationProductInfoRequest);
				String logMessage = "LOCATE Failed: MigrationProductInformation";
				throw new ServiceException(logMessage, logParameters, ex);
			}
			break;
		case MigrationProductInfoRequest.MIGRATION_SAVE_PRODUCT_INFO:
			boolean productChanged = true;
			MigrationDateSegment retrievedDS = new MigrationDateSegment();
			retrievedDS.setSystemId(Integer.parseInt(migrationContractSession.getDateSegmentId()));
			retrievedDS = migrationBusinessObjectBuilder.retrieveMigGenInfo(retrievedDS);
			if(retrievedDS.getEwpdProductSystemId() != 0){
				int productSysIdInt = retrievedDS.getEwpdProductSystemId();
				int newProductId = migrationContractSession.getProductId();
				if(productSysIdInt != 0 && productSysIdInt == newProductId)
					productChanged = false;
			}
			try {

				MigrationProduct migrationProduct = new MigrationProduct();
				migrationProduct.setLegacySourceSystem(migrationContract
						.getSystem());
				migrationProduct.setLockStatusFlag("0");
				migrationProduct.setLegacyStructure(migrationContract
						.getStructreProductMappingId());
				
//				migrationProduct.setEwpdProductSysId(Integer
//						.parseInt(migrationContract.getEwpdProductSystemId()));
				
				migrationProduct.setMigratedContractSysID(Integer
						.parseInt(migrationProductInfoRequest.getMigratedDateSegmentId()));
				
				migrationProduct.setEwpdProductSysId(migrationProductInfoRequest.getEwpdProductSysId());
				
				if (migrationContract.getMigratedProdStructureMappingSysID() == null) {
					isNewMapping = true;
				}
/*				List dateSegments = migrationBusinessObjectBuilder
						.getDateSegmentDetails(migrationContract
								.getMigrationSystemId());*/
				List productMappingList = migrationBusinessObjectBuilder
						.retrieveProductInfo(migrationProduct
								.getEwpdProductSysId(), migrationProduct
								.getLegacySourceSystem(), migrationProduct
								.getLegacyStructure());
				if (null != productMappingList && !productMappingList.isEmpty()) {
					if (null != productMappingList.get(0)) {
						migrationProduct
								.setMigratedProdStructureMappingSysID((((MigrationProduct) (productMappingList
										.get(0)))
										.getMigratedProdStructureMappingSysID()));
					}
				} else {
					SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
					int migratedProdStructureMappingSysID = sequenceAdapterManager
							.getMigratedProdStructureMappingSysID();
					migrationProduct
							.setMigratedProdStructureMappingSysID(migratedProdStructureMappingSysID);
					//persist MigrationProduct
					migrationBusinessObjectBuilder.persistMigrationProductInfo(
							migrationProduct, audit);
				}
				//persist MigrationContract
				migrationContract.setMigratedProdStructureMappingSysID(String
						.valueOf(migrationProduct
								.getMigratedProdStructureMappingSysID()));
				int productKey = migrationProductInfoRequest.getEwpdProductSysId();
				migrationContract.setEwpdProductSystemId(String.valueOf(productKey));
				migrationContract.setMgrtdDatesegmentId(Integer.parseInt(migrationProductInfoRequest.getMigratedDateSegmentId()));
//				migrationContract
//				.setDoneFlag(MigrationContractUtil.DONE_FLAG_NO);
//				migrationContract.setDoneFlag(MigrationContractUtil.DONE_FLAG_SECOND);						
				migrationContract.setDoneFlag(migrationProductInfoRequest
													.getMigrationContractSession()
													.getMigrationContract()
													.getDoneFlag());
				ProductBO productBO = new ProductBO();
				productBO.setProductKey(productKey);
				ProductBusinessObjectBuilder builder = new ProductBusinessObjectBuilder();
				List productFamilyList = builder.retrieveProductForReferenceValidation(productBO);
				if(null != productFamilyList && !productFamilyList.isEmpty()){
					productBO = (ProductBO)productFamilyList.get(0);
					migrationContract.setProductFamily(productBO.getProductFamilyId());
				}
				migrationBusinessObjectBuilder.persistMigrationContractInfo(migrationContract, audit, false, null);			
				if (!isNewMapping && productChanged) {
						MigrationContractRulesAssnBO migrationContractRulesAssnBO = new MigrationContractRulesAssnBO();
						migrationContractRulesAssnBO
								.setContractDateSegmentSysId(migrationContract.getMgrtdDatesegmentId());
						migrationBusinessObjectBuilder.removeDateSegmentRules(
								migrationContractRulesAssnBO, audit);
						migrationProduct.setMgrtdDateSegmentId(migrationContract.getMgrtdDatesegmentId());
					migrationBusinessObjectBuilder.removeContractMapping(
							migrationProduct, audit);
				}
				//copy rules from legacy to migration table
				if(productChanged) {
							migrationBusinessObjectBuilder.copyRulesFromLegacyToEwpdb(migrationContract.getMgrtdDatesegmentId(), migrationProduct
									.getEwpdProductSysId(), audit);
					// To retain the mappings, if any
					migrationBusinessObjectBuilder.mappingProductForNewVersion(migrationProduct, audit);

				}				
				//	migrationProductInfoResponse.setMigrationContract(migrationContract);
				retreiveProductInfo(Integer.parseInt(migrationContract
						.getMigrationSystemId()), migrationProductInfoRequest
						.getMigrationContractSession()
						.getDateSegmentId()
//						.getMigratedDateSegmentId()
						, migrationContract
						.getStructreProductMappingId(),
						migrationBusinessObjectBuilder,
						migrationProductInfoResponse);

				/*
				 * int migContractDateSegSysID = 0; migContractDateSegSysID =
				 * Integer.parseInt(migrationProductInfoRequest.getMigratedDateSegmentId());
				 * this.copyRulesFromLegacyToEwpdb(migContractDateSegSysID,
				 * migrationProductInfoRequest.getEwpdProductSysId(), audit);
				 */
				// to update step number
				migrationContract = updateStepCompleted(
						migrationProductInfoRequest, "5", audit,
						migrationBusinessObjectBuilder);
				MigrationContractUtil.setMigrationContractSessionToResponse(
						migrationContract, migrationProductInfoRequest,
						migrationProductInfoResponse);

				migrationProductInfoResponse
						.addMessage(new InformationalMessage(
								BusinessConstants.PRODUCT_INFO_SAVE_SUCCESS));								
				migrationProductInfoResponse.setSuccess(true);
			} catch (Exception ex) {
				List logParameters = new ArrayList(2);
				logParameters.add(migrationProductInfoRequest);
				String logMessage = "PERSIST Failed: MigrationProductInformation";
				throw new ServiceException(logMessage, logParameters, ex);
			}
			break;
		case MigrationProductInfoRequest.MIGRATION_UPDATE_STEP_NUMBER_COMPLETED:
			try {
				migrationContract = updateStepCompleted(
						migrationProductInfoRequest, "5", audit,
						migrationBusinessObjectBuilder);
				//migrationProductInfoResponse.setMigrationContract(migrationContract);
				MigrationContractUtil.setMigrationContractSessionToResponse(
						migrationContract, migrationProductInfoRequest,
						migrationProductInfoResponse);
				migrationProductInfoResponse.setSuccess(true);
				migrationProductInfoResponse
						.addMessage(new InformationalMessage(
								BusinessConstants.STEP_NUMBER_UPDATE_SUCCESS));
			} catch (Exception ex) {
				List logParameters = new ArrayList(2);
				logParameters.add(migrationProductInfoRequest);
				String logMessage = "LOCATE Failed: MigrationProductInformation";
				throw new ServiceException(logMessage, logParameters, ex);
			}
			break;
		case MigrationProductInfoRequest.PRODUCT_POPUP:
			List domains = migrationBusinessObjectBuilder.retrieveMigDomainInfo(Integer.parseInt(migrationContract.getMigrationSystemId()));
			if(null!=domains){
				List lob = new ArrayList(domains.size());
				List be = new ArrayList(domains.size());
				List bg = new ArrayList(domains.size());
				/*START CARS*/
				List mbu = new ArrayList(domains.size());
				/*END CARS*/
				for (Iterator iterator = domains.iterator(); iterator.hasNext();) {
					MigrationDomainInfo domain = (MigrationDomainInfo) iterator.next();
					lob.add(domain.getLobId());
					be.add(domain.getBusinessEntityId());
					bg.add(domain.getBusinessGroupId());
					/*START CARS*/
					mbu.add(domain.getMarketBusinessUnitId());
					/*END CARS*/
				}
				LegacyBuilder legacyBuilder = new LegacyBuilder();
				int migDateSegmentSysID =  Integer.parseInt(migrationProductInfoRequest.getMigrationContractSession()
																						.getDateSegmentId());
				int productParentSysId = migrationProductInfoRequest.getProductParentSysId();
	
				// start and end date of legacy contract			
				Date effDate = legacyBuilder.getFirstDateSegment(LegacyObject.SYSTEM_CP2000, migrationContract.getContractId()).getStartDate();
				Date expDate = legacyBuilder.getLatestDateSegment(LegacyObject.SYSTEM_CP2000, migrationContract.getContractId()).getEndDate();
				WPDStringUtil.removeDuplicate(lob);
				WPDStringUtil.removeDuplicate(be);
				WPDStringUtil.removeDuplicate(bg);
				/*START CARS*/
				WPDStringUtil.removeDuplicate(mbu);
				/*END CARS*/
				ProductAdapterManager prodAdapterManager = new ProductAdapterManager();
				/*START CARS*/
				List productList = prodAdapterManager.getProductsForContract(lob, be, bg, mbu, effDate, expDate, BusinessConstants.STANDARD_TYPE, null, null,productParentSysId);
				/*END CARS*/
				migrationProductInfoResponse.setValidProductList(productList);
			}
			break;
			case MigrationProductInfoRequest.REMOVE_PRODUCT_INFO:
				RemoveProduct removeProduct = new RemoveProduct();
				removeProduct.setMigDateSegmentSysId(Integer.parseInt(migrationProductInfoRequest.getMigratedDateSegmentId()));
				migrationBusinessObjectBuilder.updateAndRemoveProduct(removeProduct, getAudit(migrationProductInfoRequest.getUser()));
			break;
			
			case MigrationProductInfoRequest.RETRIEVE_BY_CY_CONFLICT_MESSAGE:
			MigrationProductInfoRequest request = migrationProductInfoRequest;
			int productSysId = request.getEwpdProductSysId();
			migrationContract = request.getMigrationContractSession().getMigrationContract();
			List dateSegments = migrationBusinessObjectBuilder.getDateSegmentDetails(migrationContract.getMigrationSystemId());
			String ewpdPlanRenewalType = migrationBusinessObjectBuilder.getPlanRenewalTypeForProduct(BusinessConstants.PRODUCT_TYPE,productSysId);
			boolean conflict = isConflictInPlanRenewalType(ewpdPlanRenewalType,migrationContract.getContractId(),dateSegments);
			if(conflict) {
				migrationProductInfoResponse.setBYCYConflict(true);
			}
		}
		Logger.logInfo("Returning execute method, request = "
				+ migrationProductInfoRequest.getClass().getName());
		return migrationProductInfoResponse;
	}

	/**
	 * Function to Locate, Unmapped variables for Report Generation.
	 * 
	 * @param MigrationGenerateReportRequest
	 * @param audit
	 * @param
	 * @return WPDResponse
	 * @throws ServiceException
	 */
	public WPDResponse execute(
			MigrationGenerateReportRequest migrationGenerateReportRequest)
			throws ServiceException {
		Logger.logInfo("Entering execute method, request = "
				+ migrationGenerateReportRequest.getClass().getName());
		MigrationGenerateReportResponse migrationGenerateReportResponse = null;
		MigrationBusinessObjectBuilder migrationBusinessObjectBuilder = new MigrationBusinessObjectBuilder();
		MigrationContract migrationContract = null;
		migrationGenerateReportResponse = (MigrationGenerateReportResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.MIGRATION_GENERATE_REPORT_RESPONSE);
		int action = migrationGenerateReportRequest.getAction();
		User user = migrationGenerateReportRequest.getUser();
		Audit audit = getAudit(migrationGenerateReportRequest.getUser());

		switch (action) {
		case MigrationGenerateReportRequest.MIGRATION_RETRIEVE_UNMAPPED_VARIALBES:
			try {
				HashMap params = new HashMap();
				List dateSegmentIdList = null;
				MigrationDateSegment migrationDateSegment = new MigrationDateSegment();
				migrationContract = updateStepCompleted(migrationGenerateReportRequest, "9", audit,
						migrationBusinessObjectBuilder);
				MigrationContractUtil.setMigrationContractSessionToResponse(
						migrationContract, migrationGenerateReportRequest,
						migrationGenerateReportResponse);
				params.put("legacyContractId", migrationGenerateReportRequest
						.getContractId());
				params.put("legacyDatesegmentId", migrationGenerateReportRequest.getDateSegmentId());
				// Locate Mapped Variables
				List mappedVariables = migrationBusinessObjectBuilder
						.locateMappedVariables(params);
				List mappedVariablesForReport = new ArrayList(mappedVariables.size());
				mappedVariablesForReport.add("0"); //Adding zero so that not in
				// will always work
				MigrationMappedVariables migrationMappedVariables = new MigrationMappedVariables();
				//Wrap mapped variables in list
				if (mappedVariables != null && mappedVariables.size() > 0) {
					Iterator iterator = mappedVariables.iterator();
					while (iterator.hasNext()) {
						migrationMappedVariables = (MigrationMappedVariables) iterator
								.next();
						mappedVariablesForReport.add(migrationMappedVariables
								.getVariableId());
					}
				}
				params.put("mappedVariableList", mappedVariablesForReport); 
				//need this outside	 while else framework will put like	 %	 Retrieve Effective date
				try{
						dateSegmentIdList = migrationBusinessObjectBuilder
											.getContractEffectiveDate(Integer
														.parseInt(migrationGenerateReportRequest
																	.getDateSegmentId()));
				}catch (NumberFormatException nfe){
					
				}
				if (dateSegmentIdList != null && dateSegmentIdList.size() > 0) {
					migrationDateSegment = (MigrationDateSegment) dateSegmentIdList
							.get(0);
					params.put("startDate", WPDStringUtil
							.convertDateToString(migrationDateSegment
									.getLegacyStartDate()));
				}
				// Locate Unmapped Variables
				migrationGenerateReportResponse
						.setUnmappedVariableList(migrationBusinessObjectBuilder
								.locateUnmappedVariables(params));
				// --------- Get Lines which has conflicting mappings with master  entry -------
				int migSysId=0;
				try{
					migSysId = Integer.parseInt(migrationGenerateReportRequest.getMigrationContractSession().getMigrationContract().getMigrationSystemId());
				}catch (NumberFormatException nfe){
					
				}
				List conflictingMappings = migrationBusinessObjectBuilder
						.getAllConflictingMappings(Integer
								.parseInt(migrationGenerateReportRequest
										.getDateSegmentId()));
				migrationGenerateReportResponse.setConflictingLines(conflictingMappings);
				// ---------- Get Lines which has conflicting mappings with master  entry end ----------
				
				migrationGenerateReportResponse.setSuccess(true);
			} catch (Exception ex) {
				List logParameters = new ArrayList(2);
				logParameters.add(migrationGenerateReportRequest);
				String logMessage = "LOCATE Failed: Migration Generate Report";
				throw new ServiceException(logMessage, logParameters, ex);
			}
			break;
		case MigrationGenerateReportRequest.UPDATE_STEP_COMPLETED:
			try {

				migrationContract = updateStepCompleted(
						migrationGenerateReportRequest, "9", audit,
						migrationBusinessObjectBuilder);
				MigrationContractUtil.setMigrationContractSessionToResponse(
						migrationContract, migrationGenerateReportRequest,
						migrationGenerateReportResponse);
				migrationGenerateReportResponse.setSuccess(true);
			} catch (Exception ex) {
				List logParameters = new ArrayList(2);
				logParameters.add(migrationGenerateReportRequest);
				String logMessage = "Update Step Number Completed Failed: Migration Generate Report";
				throw new ServiceException(logMessage, logParameters, ex);
			}
			break;
		}
		Logger.logInfo("Returning execute method, request = "
				+ migrationGenerateReportRequest.getClass().getName());
		return migrationGenerateReportResponse;
	}

	/**
	 * This method finds the legacy exclusions to eWPD rules mappings and
	 * returns a list of ProductRule Objects which is created by processing the
	 * exclusions and coded variables in the contract.
	 * 
	 * @param contract
	 *            Legacy Contract object. This should have ServiceCode
	 *            Exclusions, ServiceClass Exclusions, LimitClass Exclusions,
	 *            SpecialityCode Exclusions and CodedVariable to find out
	 *            corresponding ewpd Rules.
	 * @param productSysId
	 *            Associated product. Rules will be taken based on the product.
	 * @return List of ProductRule Objects. These objects will have a value
	 *         which is migrated from the Legacy.
	 */
	private List migrateExclusionsToRules(LegacyContract contract,
			int productSysId) throws SevereException {
		MigrationBusinessObjectBuilder builder = new MigrationBusinessObjectBuilder();
		List generatedRules = builder.getProductAssociatedRules(productSysId);
		List ruleDetails = builder.getProductExclusionRuleDetails(productSysId);
		ExclusionMigrationUtil migrationUtil = new ExclusionMigrationUtil(
				contract, ruleDetails, generatedRules);
		List mappingRules = migrationUtil.generateRuleMapping();
		return mappingRules;
	}

	/**
	 * Method to get Business Object Manager.
	 * Migration Contract step 6
	 * @return BusinessObjectManager.
	 */
	private BusinessObjectManager getBusinessObjectManager() {
		BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory) ObjectFactory
				.getFactory(ObjectFactory.BOM);
		BusinessObjectManager bom = bomf.getBusinessObjectManager();
		return bom;
	}

	/**
	 * Service method for Denial and Exclusion.
	 * Migration Contract step 6
	 * @param request
	 * @return
	 * @throws ServiceException
	 * @throws AdapterException
	 */
	public WPDResponse execute(MigrationContractRulesRequest request)
			throws ServiceException, AdapterException {
		Logger.logInfo("Entering execute method, request = "
				+ request.getClass().getName());
		try {
			MigrationContractRulesResponse migrationContractRulesResponse = (MigrationContractRulesResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.CONTRACT_RULES_RESPONSE);
			List messageList = new ArrayList(5);
			MigrationBusinessObjectBuilder bom = new MigrationBusinessObjectBuilder();

			MigrationContract migrationContract = new MigrationContract();
			MigrationContract migrationContracts = new MigrationContract();

			User user = request.getUser();
			Audit audit = getAudit(request.getUser());

			MigrationContractRulesBO migrationContractRulesBO = null;
			int action = request.getAction();
			ProductRuleAssociation productRuleAssociation;

			int dateSegmentKey = request.getDateSegmentKey();

			MigrationDateSegment migrationDateSegment = new MigrationDateSegment();
			migrationDateSegment.setSystemId(dateSegmentKey);
			List migDatesegList = new ArrayList(2);
			migDatesegList.add(migrationDateSegment);
			migrationContract.setMigrationDateSegments(migDatesegList);

			List rulesList =null;
			List refList = null;
			ProductRuleRefDataLocateCriteria locateCriteria = new ProductRuleRefDataLocateCriteria();
			LocateResults locateResults;
			MigrationBusinessObjectBuilder migrationBusinessObjectBuilder = new MigrationBusinessObjectBuilder();

			switch (action) {
			case MigrationContractRulesRequest.ADD_CONTRACT_RULES:
				productRuleAssociation = new ProductRuleAssociation();
				if(null!=request.getRulesList()){
					rulesList = new ArrayList(request.getRulesList().size());
					int ProductRuleSysID;
	
					java.util.Iterator iterComponent = request.getRulesList()
							.iterator();
					while (iterComponent.hasNext()) {
						ProductRuleSysID = Integer.parseInt(iterComponent.next()
								.toString());
	
						migrationContractRulesBO = new MigrationContractRulesBO();
						migrationContractRulesBO
								.setProductRuleSysID(ProductRuleSysID);
						migrationContractRulesBO
								.setContractDateSegmentSysId(dateSegmentKey);
						migrationContractRulesBO.setFlag("N");
	
						rulesList.add(migrationContractRulesBO);
					}
				}

				productRuleAssociation.setRulesList(rulesList);

				bom.persist(productRuleAssociation, audit, false);
				messageList.add(new InformationalMessage(
						BusinessConstants.MSG_PRODUCT_RULE_SAVE_SUCCESS));
				migrationContractRulesResponse.setMessages(messageList);
				migrationContractRulesResponse.setSuccess(true);
				migrationContractRulesResponse.setMigrationContractSession(request.getMigrationContractSession());
				break;
				
			case MigrationContractRulesRequest.DELETE_CONTRACT_RULES:
				productRuleAssociation = new ProductRuleAssociation();
			if(null!=request.getRulesList()){
				rulesList = new ArrayList(request.getRulesList().size());

				if (request.getRulesList().size() == 1) {
					migrationContractRulesBO = new MigrationContractRulesBO();
					migrationContractRulesBO
							.setProductRuleSysID(Integer.parseInt(request
									.getRulesList().get(0).toString()));
					migrationContractRulesBO
							.setContractDateSegmentSysId(dateSegmentKey);
					migrationContractRulesBO.setFlag("Y");

					rulesList.add(migrationContractRulesBO);
				}
			}

				productRuleAssociation.setRulesList(rulesList);

				bom.persist(productRuleAssociation, audit, false);
	            InformationalMessage message = new InformationalMessage(BusinessConstants.MSG_PRODUCT_RULE_DELETE_SUCCESS2);
//	            message.setParameters(new String[] {request.getEwpdGenRuleID()});
	            messageList.add(message);
				
				migrationContractRulesResponse.setMessages(messageList);
				migrationContractRulesResponse.setSuccess(true);
				migrationContractRulesResponse.setMigrationContractSession(request.getMigrationContractSession());
				break;
				
			case MigrationContractRulesRequest.UPDATE_CONTRACT_RULES:
				MigrationContractRulesAssnBO migrationContractRulesAssnBO = null;
				productRuleAssociation = new ProductRuleAssociation();
				if(null!=request.getRulesList()){
					rulesList = new ArrayList(request.getRulesList().size());
					Map tempMap = (HashMap) request.getRulesList().get(0);
					java.util.Iterator ruleIDIter = tempMap.keySet().iterator();
					int proRuleSysID;
					String ruleComment;
					while (ruleIDIter.hasNext()) {
						Object key = ruleIDIter.next();
						proRuleSysID = Integer.parseInt(key.toString());
						ruleComment = (String) tempMap.get(key);
	
						migrationContractRulesAssnBO = new MigrationContractRulesAssnBO();
						migrationContractRulesAssnBO
								.setProductRuleSysID(proRuleSysID);
						migrationContractRulesAssnBO.setRuleComment(ruleComment);
						migrationContractRulesAssnBO
								.setContractDateSegmentSysId(dateSegmentKey);
	
						rulesList.add(migrationContractRulesAssnBO);
					}//end while
				}
				productRuleAssociation.setRulesList(rulesList);

				bom.persist(productRuleAssociation, audit, true);
				messageList.add(new InformationalMessage(
						BusinessConstants.MSG_PRODUCT_RULE_UPDATE_SUCCESS));
				migrationContractRulesResponse.setMessages(messageList);
				migrationContractRulesResponse.setSuccess(true);
				migrationContractRulesResponse.setMigrationContractSession(request.getMigrationContractSession());
				
				break;
				
			case MigrationContractRulesRequest.RULE_TYPE:
				locateCriteria.setQueryName("ruleType");
				locateResults = bom.locate(locateCriteria, request.getUser());
				refList = locateResults.getLocateResults();
				if (null == refList || 0 == refList.size()) {
					messageList.add(new InformationalMessage(
							"search.association.message1"));
				}
				break;
				
			case MigrationContractRulesRequest.RULE_ID:
				locateCriteria.setQueryName("migrationRuleID");
				locateCriteria.setRuleType(request.getRuleType());
				locateCriteria.setKey(request.getDateSegmentKey());
				locateResults = bom.locate(locateCriteria, request.getUser());
				refList = locateResults.getLocateResults();
				if (null == refList || 0 == refList.size()) {
					messageList.add(new InformationalMessage(
							"search.association.message1"));
				}
				break;
				
			case MigrationContractRulesRequest.RETRIEVE_CONTRACT_RULES:
				Map params = new HashMap();

				//migrationContracts = updateStepCompleted(request, "6", audit,bom);
				params.put("action", "retrieveDenialAndExclusion");
				params.put("ruleType", request.getRuleType());
				migrationContract = (MigrationContract) bom.retrieve(
						migrationContract, params);
				refList = migrationContract.getDenialAndExclusionList();
				//MigrationContractUtil.setMigrationContractSessionToResponse(migrationContract, request,
				//		migrationContractRulesResponse);
				migrationContractRulesResponse.setMigrationContractSession(request.getMigrationContractSession());
				break;
				
			case MigrationContractRulesRequest.RULES_UPDATE_STEP_COMPLETED:

				migrationBusinessObjectBuilder.persistVariableInfo(new Integer(request.getMigrationContractSession().getDateSegmentId()).intValue(),
								WPDStringUtil.getStringDate(request.getMigrationContractSession().getStartDateLegacy()));
				migrationContract = updateStepCompleted(request, "6", audit,
						bom);
				MigrationContractUtil.setMigrationContractSessionToResponse(
						migrationContract, request,
						migrationContractRulesResponse);

				migrationContractRulesResponse.setSuccess(true);
				break;
				
			default:
				return null;
			}//end switch

			migrationContractRulesResponse.setRuleList(refList);
			migrationContractRulesResponse.setMessages(messageList);
			Logger.logInfo("Returning execute method, request = "
					+ request.getClass().getName());
			return migrationContractRulesResponse;

		} catch (WPDException ex) {
			throw new ServiceException("Exception occured while BOM call",
					null, ex);
		}
	}

	private void copyRulesFromLegacyToEwpdb(int contractDateSegmentSysId, int productSysId, Audit audit) throws SevereException {
		if (contractDateSegmentSysId == 0) {
			return;
		}
		MigrationBusinessObjectBuilder migrationBuilder = new MigrationBusinessObjectBuilder();
//		List migrationDateSegmentList = new ArrayList();
		List migrationDateSegmentList = migrationBuilder.getContractEffectiveDate(contractDateSegmentSysId);

		if (null != migrationDateSegmentList
				|| 0 != migrationDateSegmentList.size()) {
			MigrationDateSegment migrationDateSegment = (MigrationDateSegment) migrationDateSegmentList
					.get(0);
			LegacyBuilder legacyBuilder = new LegacyBuilder();
			LegacyContract contract = new LegacyContract();
			contract.setContractId(migrationDateSegment.getContractId());
			contract.setStartDate(migrationDateSegment.getLegacyStartDate());
			contract.setSystemCP2000();

			contract = legacyBuilder.retrieveDateSegment(contract,
														LegacyBuilder.OPT_DS_DETAIL_INFO,
														BusinessConstants.STATUS_MIGRATION_IN_PROGRESS);
			if (null != contract) {
				
				List mappedRules = migrateExclusionsToRules(contract, productSysId);
				List unmappedRules = migrationBuilder.getUnmappedRules(productSysId,mappedRules);
				List dateSegmentRules = null;
				if(null!=mappedRules && null!=unmappedRules){
					dateSegmentRules =new ArrayList(mappedRules.size()+unmappedRules.size());
				}else if(null!=mappedRules){
					dateSegmentRules =new ArrayList(mappedRules.size());
				}else if(null!=unmappedRules){
					dateSegmentRules =new ArrayList(unmappedRules.size());
				}								
				
				Iterator iterator;
				ProductRule element;
				MigrationContractRulesBO migrationContractRulesBO = null;
				// Mapped Rules will be shown as associated and others will be shown as available but not 
				// associated to date segment.
				for (iterator = mappedRules.iterator(); iterator.hasNext();) {
					element = (ProductRule) iterator.next();
					migrationContractRulesBO = new MigrationContractRulesBO();
					migrationContractRulesBO
							.setContractDateSegmentSysId(contractDateSegmentSysId);
					migrationContractRulesBO.setProductRuleSysID(element
							.getProductRuleSysId());
					migrationContractRulesBO.setRuleComment(element
							.getValue());
					migrationContractRulesBO.setFlag("N");
					dateSegmentRules.add(migrationContractRulesBO);
				}
				for (iterator = unmappedRules.iterator(); iterator.hasNext();) {
					element = (ProductRule) iterator.next();
					migrationContractRulesBO = new MigrationContractRulesBO();
					migrationContractRulesBO
							.setContractDateSegmentSysId(contractDateSegmentSysId);
					migrationContractRulesBO.setProductRuleSysID(element
							.getProductRuleSysId());
					migrationContractRulesBO.setRuleComment(element
							.getValue());
					migrationContractRulesBO.setFlag("Y");
					dateSegmentRules.add(migrationContractRulesBO);
				}
				ProductRuleAssociation productRuleAssociation = new ProductRuleAssociation();
				productRuleAssociation.setRulesList(dateSegmentRules);
				try {
					migrationBuilder.persist(
							productRuleAssociation, audit);
				} catch (WPDException ex) {
					throw new ServiceException(
							"Exception occured while BusinessObjectBuilder call",
							null, ex);
				}
			}
		}
	}
	
	private void updateMigrationStatus(int migrationContractSysID, String doneFlag, String option, Audit audit) throws SevereException{
		MigrationBusinessObjectBuilder builder = new MigrationBusinessObjectBuilder();
		UpdateMigrationContractStatus  migrationContractStatus = new UpdateMigrationContractStatus();
		migrationContractStatus.setMigrationSystemId(migrationContractSysID);
		migrationContractStatus.setDoneFlag(doneFlag);
		migrationContractStatus.setOption(option);
		builder.updateMigrationStatus(migrationContractStatus, audit);
	}
	/**
	 * 
	 * @param saveLegacyContractRequest
	 * @return
	 * @throws ServiceException
	 * @throws SevereException
	 */
	public WPDResponse execute(
			SaveLegacyContractRequest saveLegacyContractRequest)
			throws ServiceException, SevereException {
		Logger.logInfo("Entering execute method, request = "
				+ saveLegacyContractRequest.getClass().getName());
		//Getting the response object from the Response Factory
		Audit audit = getAudit(saveLegacyContractRequest.getUser());
		
		String userID = saveLegacyContractRequest.getUser().getUserId();
		int cp2000ContractListSize;
//		List messageList = new ArrayList();
		LegacyContract legacyContract;
		MigrationContract migrationContract;
		MigrationDateSegment migrationDatesegment = new MigrationDateSegment();
		LegacyBuilder legacyBuilder = new LegacyBuilder();
		MigrationBusinessObjectBuilder migrationBusinessObjectBuilder = new MigrationBusinessObjectBuilder();

		SaveLegacyContractResponse saveLegacyContractResponse = (SaveLegacyContractResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.SAVE_LEGACY_CONTRACT_RESPONSE);

		List legacyContractList = saveLegacyContractRequest.getAllDateSegments();
		MigrationContractSession migrationContractSession = null;//ew MigrationContractSession();
		
		if (legacyContractList != null && legacyContractList.size() > 0) {

		 if (saveLegacyContractRequest.getOption().equals(BusinessConstants.OPT_MIGRATE_DS )) {

				migrationContract = new MigrationContract();
				String selectedDateSegment = saveLegacyContractRequest
						.getSelectedDateSegmentForMigration();

				if (!StringUtil.isEmpty(selectedDateSegment)) {

					if (!"false".equals(selectedDateSegment)) {

						String[] arrayTilda = selectedDateSegment.split("~");
						selectedDateSegment = arrayTilda[0];
						int datesegmentID = Integer
								.parseInt(selectedDateSegment);

						migrationContract = saveLegacyContractRequest
								.getMigrationContractSession()
								.getMigrationContract();

						MigrationContractUtil
								.setMigrationContractSessionToResponseDateSegment(
										migrationContract, datesegmentID, audit,
										saveLegacyContractResponse);
					} else {
						migrationContract = saveLegacyContractRequest
								.getMigrationContractSession()
								.getMigrationContract();
						//FIXME CHECK this else 
						MigrationContractUtil
								.setCreateMigrationContractSessionToResponse(
										migrationContract, audit,
										saveLegacyContractResponse);
					}//end inner if
				}else{
					// step 2 to step 3 first time
					//change start here
					migrationContractSession = migrationBusinessObjectBuilder.persistMigrationInfo(
							legacyContractList,
							BusinessConstants.OPT_MIGRATE_DS,
							saveLegacyContractRequest.isAfterMigrationAddNewDateSegment(),
							null,
							null,
							audit);
					saveLegacyContractResponse.setMigrationContractSession(migrationContractSession);
					//change end
			
				}// end option 4
			}// end if OPT_MIGRATE_LATEST_TWO_DS

		else if (saveLegacyContractRequest.getOption().equals(BusinessConstants.OPT_RENEW_DS)) {
			cp2000ContractListSize = 0;

			migrationContract = new MigrationContract();
			String selectedDateSegmentStartDate = saveLegacyContractRequest
					.getSelectedDateSegmentForMigration();

			if (null != selectedDateSegmentStartDate && !selectedDateSegmentStartDate.equals("false")) {
				
				String[] arrayTilda = selectedDateSegmentStartDate.split("~");
				selectedDateSegmentStartDate = arrayTilda[0];
				//change start here
				migrationContractSession = migrationBusinessObjectBuilder.persistMigrationInfo(
						legacyContractList,
						BusinessConstants.OPT_RENEW_DS,
						false,
						selectedDateSegmentStartDate,
						saveLegacyContractRequest.getSelectedNewDate(),
						audit);
				saveLegacyContractResponse.setMigrationContractSession(migrationContractSession);
				//change end
				
/*				
					Iterator legacyListIterator = legacyContractList.iterator();

					while (legacyListIterator.hasNext()) {
						legacyContract = (LegacyContract) legacyListIterator.next();
						SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

						String legacyContractStartDate = formatter.format(legacyContract.getStartDate());
						
						if (legacyContractStartDate.equals(selectedDateSegmentStartDate)) {

							migrationContract = copyLegacyContractToMigrationContract(saveLegacyContractRequest, legacyContract);
							persistMigrationInfo(migrationContract,saveLegacyContractRequest);
							String selectedNewDate = saveLegacyContractRequest.getSelectedNewDate();
							migrationDatesegment = copyRenewContractToMigrationDateSegment(
									legacyContract, 
									migrationContract,
									WPDStringUtil.getDateFromString(selectedNewDate));

							persistMigDateSegmentInfo(migrationContract
									.getContractId(), migrationDatesegment,
									saveLegacyContractRequest);
							
							String option = BusinessConstants.OPT_RENEW_DS;
							legacyContract.setCreatedUser(userID);
							legacyBuilder.lockAndChangeContractStatus(legacyContract, option);							
							MigrationContractUtil.setCreateMigrationContractSessionToResponse(
											migrationContract, audit,
											saveLegacyContractResponse);
						}//end if
						}//emd while
*/
				}else{
					//if no StartDate(DateSegment) selected
					saveLegacyContractResponse.addMessage(new ErrorMessage("migration.please.select.datesegment"));
					MigrationContractUtil.setMigrationContractSessionToResponse(
															saveLegacyContractRequest,
															saveLegacyContractResponse);
				}
			}//end OPT_RENEW_DS
		}//end if legacyContractList is null or empty
		Logger.logInfo("Returning execute method, request = "
				+ saveLegacyContractRequest.getClass().getName());
		
		return saveLegacyContractResponse;
	}
	/**
	 * 
	 * @param legacyContract
	 * @param migrationContract
	 * @param newDate
	 * @return
	 * @throws SevereException
	 */
	private MigrationDateSegment copyRenewContractToMigrationDateSegment(
			LegacyContract legacyContract, MigrationContract migrationContract,
			Date newDate) throws SevereException {

		MigrationDateSegment migrationDateSegment = new MigrationDateSegment();
		MigrationBusinessObjectBuilder migrationBusinessObjectBuilder = new MigrationBusinessObjectBuilder();
		Date endDate = WPDStringUtil.getDateFromString("12/31/9999");
		migrationDateSegment.setBenefitPlan(legacyContract.getBenefitPlan());

		migrationDateSegment.setContractSysId(Integer
				.parseInt(migrationContract.getMigrationSystemId()));

		if (migrationBusinessObjectBuilder.validateItemField(1947,
				legacyContract.getCorporatePlanCode())) {
			migrationDateSegment.setCorporatePlanCode(legacyContract
					.getCorporatePlanCode());
		}

		migrationDateSegment.setCreatedTimeStamp(legacyContract
				.getCreatedTimestamp());
		migrationDateSegment.setCreatedUser(legacyContract.getCreatedUser());
		migrationDateSegment.setGroupSizeDesc(legacyContract.getGroupName());

		migrationDateSegment.setLastUpdatedTimeStamp(legacyContract
				.getLastUpdatedTimestamp());
		migrationDateSegment.setLastUpdatedUser(legacyContract
				.getLastUpdatedUser());
		if (migrationBusinessObjectBuilder.validateItemField(1786,
				legacyContract.getProductCode())) {
			migrationDateSegment
					.setProductCode(legacyContract.getProductCode());
		}

		migrationDateSegment
				.setProductFamily(legacyContract.getProductFamily());
		if (migrationBusinessObjectBuilder.validateItemField(1701,
				legacyContract.getStandardPlanCode())) {
			migrationDateSegment.setStandardPlanCode(legacyContract
					.getStandardPlanCode());
		}

		SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
		int sysId = sequenceAdapterManager.getMigratedDateSegSysIdSequence();
		migrationDateSegment.setSystemId(sysId);

		migrationDateSegment.setEffectiveDate(newDate);


		migrationDateSegment.setExpiryDate(endDate);
		migrationDateSegment.setLastAccessedPage("step2");
		migrationDateSegment.setStepCompleted("2");
		migrationDateSegment.setLegacyStartDate(legacyContract.getStartDate());
		
		CP2000Contract contract = (CP2000Contract)legacyContract;
		
		if(null == contract.getItsHomeAdjInd()){
		    migrationDateSegment.setItsHomeAdjInd("N");
		}
		else{
		    migrationDateSegment.setItsHomeAdjInd(contract.getItsHomeAdjInd());
		}
		
		if(null == contract.getMedicareAdjudInd()){
		    migrationDateSegment.setMedicareAdjudInd("N");
		}
		else{
		    migrationDateSegment.setMedicareAdjudInd(contract.getMedicareAdjudInd());
		}
		
		if(null == contract.getCobAdjudInd()){
		    migrationDateSegment.setCobAdjudInd("N");
		}
		else{
		    migrationDateSegment.setCobAdjudInd(contract.getCobAdjudInd());
		}
		
		return migrationDateSegment;
	}
	/**
	 * 
	 * @param migrationContract
	 * @param saveLegacyContractRequest
	 * @throws SevereException
	 */
	/*
	private void persistMigrationInfo(MigrationContract migrationContract,
			SaveLegacyContractRequest saveLegacyContractRequest)
			throws SevereException {
		MigrationBusinessObjectBuilder migrationBusinessObjectBuilder = new MigrationBusinessObjectBuilder();
		Audit audit = getAudit(saveLegacyContractRequest.getUser());

		migrationBusinessObjectBuilder.persistMigrationContractInfo(
				migrationContract, audit, true);

	}
	*/
	/**
	 * 
	 * @param contractNo
	 * @param migrationDateSegment
	 * @param saveLegacyContractRequest
	 * @throws SevereException
	 */
	/*
	private void persistMigDateSegmentInfo(String contractNo,
			MigrationDateSegment migrationDateSegment,
			SaveLegacyContractRequest saveLegacyContractRequest)
			throws SevereException {
		MigrationBusinessObjectBuilder migrationBusinessObjectBuilder = new MigrationBusinessObjectBuilder();
		Audit audit = getAudit(saveLegacyContractRequest.getUser());

		migrationBusinessObjectBuilder.persistMigrationDateSegmentInfo(		contractNo, migrationDateSegment, audit);
	}
	*/
	/**
	 * 
	 * @param contractID
	 * @return
	 * @throws SevereException
	 */
	private MigrationContract getMigrationContract(String contractID)
			throws SevereException {
		MigrationContract migrationContract;
		LegacyContract legacyContract = new LegacyContract();
		legacyContract.setContractId(contractID);
		MigrationBusinessObjectBuilder migrationBusinessObjectBuilder = new MigrationBusinessObjectBuilder();
		List legacyContractList;
		legacyContractList = migrationBusinessObjectBuilder
				.getMigrationContract(legacyContract);
		if (null != legacyContractList) {
			migrationContract = (legacyContractList.size() > 0) ? (MigrationContract) legacyContractList
					.get(0)
					: new MigrationContract();
			return migrationContract;
		}
		return new MigrationContract();
	}

	/**
	 * Delete all detesegment and contract and all related data from migration
	 * tables
	 * 
	 * @param request
	 * @throws SevereException
	 */
	private void removeAllDateSegment(MigrationContractRequest request,
			String contractID) throws SevereException {
		Audit audit = getAudit(request.getUser());
		MigrationContract migrationContract;
		migrationContract = this.getMigrationContract(contractID);

		String migrationSystemId = migrationContract.getMigrationSystemId();
		if (null != migrationSystemId) {
			MigrationBusinessObjectBuilder migrationBusinessObjectBuilder = new MigrationBusinessObjectBuilder();
			CancelMigration cancelMigration = new CancelMigration();
			cancelMigration.setMigContractSysId(Integer
					.parseInt(migrationSystemId));
			migrationBusinessObjectBuilder.cancelMigration(cancelMigration,
					audit);
		}
	}

	/**
	 * @param migrationContract
	 * @return
	 */
	private MigrationDateSegment copyMigrationContractToMigrationDateSegment(
			LegacyContract legacyContract, MigrationContract migrationContract)
			throws SevereException {
	    MigrationDateSegment migrationDateSegment = new MigrationDateSegment();
		MigrationBusinessObjectBuilder migrationBusinessObjectBuilder = new MigrationBusinessObjectBuilder();

		migrationDateSegment.setBenefitPlan(legacyContract.getBenefitPlan());

		migrationDateSegment.setContractSysId(Integer
				.parseInt(migrationContract.getMigrationSystemId()));

		if (migrationBusinessObjectBuilder.validateItemField(1947,
				legacyContract.getCorporatePlanCode())) {
			migrationDateSegment.setCorporatePlanCode(legacyContract
					.getCorporatePlanCode());
		}

		migrationDateSegment.setCreatedTimeStamp(legacyContract
				.getCreatedTimestamp());
		migrationDateSegment.setCreatedUser(legacyContract.getCreatedUser());
		migrationDateSegment.setGroupSizeDesc(legacyContract.getGroupName());
		if (migrationBusinessObjectBuilder.validateItemField(1626,
				legacyContract.getHeadQuarterState())) {
			migrationDateSegment.setHeadQuartersState(legacyContract
					.getHeadQuarterState());
		}
		migrationDateSegment.setLastUpdatedTimeStamp(legacyContract
				.getLastUpdatedTimestamp());
		migrationDateSegment.setLastUpdatedUser(legacyContract
				.getLastUpdatedUser());
		if (migrationBusinessObjectBuilder.validateItemField(1786,
				legacyContract.getProductCode())) {
			migrationDateSegment
					.setProductCode(legacyContract.getProductCode());
		}

		migrationDateSegment
				.setProductFamily(legacyContract.getProductFamily());
		if (migrationBusinessObjectBuilder.validateItemField(1701,
				legacyContract.getStandardPlanCode())) {
			migrationDateSegment.setStandardPlanCode(legacyContract
					.getStandardPlanCode());
		}
		SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
		int sysId = sequenceAdapterManager.getMigratedDateSegSysIdSequence();
		migrationDateSegment.setSystemId(sysId);
		migrationDateSegment.setLastAccessedPage("step2");
		migrationDateSegment.setStepCompleted("2");
		migrationDateSegment.setEffectiveDate(legacyContract.getStartDate());
		migrationDateSegment.setLegacyStartDate(legacyContract.getStartDate());
		migrationDateSegment.setExpiryDate(legacyContract.getEndDate());
		
		CP2000Contract contract = (CP2000Contract)legacyContract;
		
		if(null == contract.getItsHomeAdjInd()){
		    migrationDateSegment.setItsHomeAdjInd("N");
		}
		else{
		    migrationDateSegment.setItsHomeAdjInd(contract.getItsHomeAdjInd());
		}
		
		if(null == contract.getMedicareAdjudInd()){
		    migrationDateSegment.setMedicareAdjudInd("N");
		}
		else{
		    migrationDateSegment.setMedicareAdjudInd(contract.getMedicareAdjudInd());
		}
		
		if(null == contract.getCobAdjudInd()){
		    migrationDateSegment.setCobAdjudInd("N");
		}
		else{
		    migrationDateSegment.setCobAdjudInd(contract.getCobAdjudInd());
		}

		return migrationDateSegment;
	}

	/**
	 * @param cp2000Contract
	 * @return
	 */
	/*
	private MigrationContract copyLegacyContractToMigrationContract(
			SaveLegacyContractRequest saveLegacyContractRequest,
			LegacyContract legacyContract) throws SevereException {
		MigrationContract migrationContract = new MigrationContract();

		migrationContract.setContractId(legacyContract.getContractId());
		migrationContract.setCreatedTimeStamp(legacyContract
				.getCreatedTimestamp());
		migrationContract.setCreatedUser(legacyContract.getCreatedUser());
		migrationContract.setDoneFlag("N"); //TODO add it to constant for not
		// done
		migrationContract.setEwpdProductSystemId(null);
		migrationContract.setLastUpdatedTimeStamp(legacyContract
				.getLastUpdatedTimestamp());
		migrationContract.setLastUpdatedUser(legacyContract
				.getLastUpdatedUser());

		SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
		int sysId = sequenceAdapterManager.getMigratedContractSysIdSequence();
		migrationContract.setMigrationSystemId(new Integer(sysId).toString());
		migrationContract.setOption(saveLegacyContractRequest.getOption());
		migrationContract.setProductFamily(null);
		
		migrationContract.setStructreProductMappingId(legacyContract
				.getBenefitStructure());
		migrationContract.setSystem(legacyContract.getSystem());
		
		//added
		//migrationContract.setHeadQuartersState(legacyContract.getHeadQuarterState());
		migrationContract.setProductFamily(legacyContract.getProductFamily());
		String contractInd = legacyContract.getModelIndicator();
		String migContractType =null;
		boolean isType = false;
		if(contractInd != null)
		{
			if(contractInd.equals("C")){
		
			    migContractType = WebConstants.CUSTOM;
			    isType = true;
			}
			else if (contractInd.equals("M")){
			    migContractType = WebConstants.MODEL;
			    isType = true;
			}
			else if (contractInd.equals("S")){
			    migContractType = WebConstants.STANDAR;
			    isType = true;
			}
			if(isType){
			    migrationContract.setContractType(migContractType);
			}
		}
		
		migrationContract.setHeadQuartersState(legacyContract.getHeadQuarterState());
		
		return migrationContract;
	}
	*/
	/**
	 * 
	 * @param contractID
	 * @return
	 * @throws SevereException
	 */
	public boolean isContractExistingInEwpdb(String contractID) throws SevereException{
		ContractBusinessObjectBuilder contractBusinessObjectBuilder = new ContractBusinessObjectBuilder();
		//FIXME check for MARKED_FOR_DELETION
		return contractBusinessObjectBuilder.searchContractId(contractID);		
	}
	/**
	 * 
	 * @param contractID
	 * @param isSecondTimeMigration true for second time migration 
	 * @param messageList
	 * @return
	 * @throws SevereException
	 */
	public boolean isContractExistingInEwpdb(String contractID, boolean isSecondTimeMigration, List messageList) throws SevereException{
		ContractBusinessObjectBuilder contractBusinessObjectBuilder = new ContractBusinessObjectBuilder();
		if(isSecondTimeMigration){
			String status = contractBusinessObjectBuilder.contractStatusFromLatestVersion(contractID);
			ErrorMessage errorMessage = null;
			if(status == null){
				// boom stop migration 
				errorMessage =new ErrorMessage(BusinessConstants.MSG_MIGRATION_CONTRACT_DELETED);
			}else{
				if(status.equals("CHECKED_OUT")){
					//messsages for checkout "Sorry, the already migrated contract cannot be check-out."					
					errorMessage =new ErrorMessage(BusinessConstants.MSG_MIGRATION_VALIDATION_STATUS);
					errorMessage.setParameters(new String[] { "Checked_Out"});					
				}else if(status.equals("SCHEDULED_FOR_PRODUCTION")){
					// messages for scheduled for test
					errorMessage =new ErrorMessage(BusinessConstants.MSG_MIGRATION_VALIDATION_STATUS);
					errorMessage.setParameters(new String[] { "Scheduled_For_Production"});					
				}else if(status.equals("BUILDING")){
					//message for buiding  not possible  
					errorMessage =new ErrorMessage(BusinessConstants.MSG_MIGRATION_VALIDATION_STATUS);
					errorMessage.setParameters(new String[] { "Building"});					
				}else if(status.equals("SCHEDULED_FOR_TEST")&& this.isDataFeedRunning() ){
					errorMessage =new ErrorMessage(BusinessConstants.DATAFEED_RUNNING);
				}else if(status.equals("MARKED_FOR_DELETION")){
					// boom stop migration 
					errorMessage =new ErrorMessage(BusinessConstants.MSG_MIGRATION_CONTRACT_DELETED);
//					errorMessage =new ErrorMessage(BusinessConstants.MSG_MIGRATION_VALIDATION_STATUS);
//					errorMessage.setParameters(new String[] { "MARKED_FOR_DELETION".toLowerCase()});					
				}else{
					return false;
				}
			}
			messageList.add(errorMessage);
		}else{
			if(contractBusinessObjectBuilder.searchContractId(contractID)){
				messageList.add(new ErrorMessage(BusinessConstants.MIGRATION_CONTRACT_EXISTING));
			}else{
				return false;
			}
		}
		return true;
	}
	//a contract cannot have discontinuous date segments
	public boolean isMigrationContractAndEwpdbContractInSequence(String contractID, List messageList) throws SevereException{
		MigrationBusinessObjectBuilder builder = new MigrationBusinessObjectBuilder();
		ContractBusinessObjectBuilder builder2 = new ContractBusinessObjectBuilder();
		Date migrationContractMaxEffectiveDate = builder.maxEffectiveOfMigratingContract(contractID);
		Date minEffectiveOfLatestContract = builder2.minEffectiveOfLatestContract(contractID);
		if(null != minEffectiveOfLatestContract && null != migrationContractMaxEffectiveDate){
			if(migrationContractMaxEffectiveDate.compareTo(minEffectiveOfLatestContract)!=0){
				//if dates are not same
				messageList.add(new ErrorMessage("migration.contract.datesegment.discontinuous"));
				return false;							
			}
		}
		return true;
	}
	/**
	 * 
	 * @param contractID
	 * @param messageList
	 * @return
	 * @throws SevereException
	 */
	public boolean isAllDateSegmentThroughStep8(String contractID, List messageList)throws SevereException{
  		MigrationAdapterManager migrationAdapterManager = new MigrationAdapterManager();
  		List checkList = migrationAdapterManager.getAllDateSegmentNotThroughStep8(contractID);
  		NavigationInfo info;
  		if(null == checkList || checkList.isEmpty()){
  			return true;
  		}else{
			SimpleDateFormat dateFormat =new SimpleDateFormat("MM/dd/yyyy");
  			StringBuffer buffer = new StringBuffer();
  			for(Iterator checkListItr = checkList.iterator(); checkListItr.hasNext();){
  				info = (NavigationInfo)checkListItr.next();
  				buffer.append(dateFormat.format(info.getDateSegmentEffectiveDate()));
  				buffer.append(", ");
  			}
  			buffer.deleteCharAt(buffer.length()-1);
  			buffer.deleteCharAt(buffer.length()-1);
			ErrorMessage errorMessage =new ErrorMessage(BusinessConstants.MIGRATION_CONTRACT_DATESEGMENT_STEP8);
			errorMessage.setParameters(new String[] { buffer.toString() });
			messageList.add(errorMessage);
  			return false;
  		}
	}
	/**
	 * 
	 * @param contractID
	 * @param messageList
	 * @return
	 * @throws SevereException
	 */
	public boolean isAllDateSegmentContainPricing(String contractID, List messageList)throws SevereException{
  		MigrationAdapterManager migrationAdapterManager = new MigrationAdapterManager();
  		List checkList = migrationAdapterManager.getAllDateSegmentNotContainPricing(contractID);
  		NavigationInfo info;
  		if(null == checkList || checkList.isEmpty()){
  			return true;
  		}else{
			SimpleDateFormat dateFormat =new SimpleDateFormat("MM/dd/yyyy");
  			StringBuffer buffer = new StringBuffer();
  			for(Iterator checkListItr = checkList.iterator(); checkListItr.hasNext();){
  				info = (NavigationInfo)checkListItr.next();
  				buffer.append(dateFormat.format(info.getDateSegmentEffectiveDate()));
  				buffer.append(", ");
  			}
  			buffer.deleteCharAt(buffer.length()-1);
  			buffer.deleteCharAt(buffer.length()-1);
			ErrorMessage errorMessage =new ErrorMessage(BusinessConstants.CHECKIN_VALID_FAIL_PRICING);
			errorMessage.setParameters(new String[] { buffer.toString() });
			messageList.add(errorMessage);
  			return false;
  		}
	}
	
	public boolean isMigrationProductRulesUMAndPNRCoded(String contractID, List messageList) throws SevereException{
  		MigrationAdapterManager migrationAdapterManager = new MigrationAdapterManager();
  		List invalidRuleList = migrationAdapterManager.migrationProductRulesUMAndPNRCoded(contractID);
  		if(null != invalidRuleList && !invalidRuleList.isEmpty()){
			StringBuffer buffer = new StringBuffer();
			RuleLocateResult  locateResult;
			SimpleDateFormat dateFormat =new SimpleDateFormat("MM/dd/yyyy");
			for( java.util.Iterator invalidRuleListItr = invalidRuleList.iterator();
					invalidRuleListItr.hasNext();){
				locateResult = (RuleLocateResult)invalidRuleListItr.next();
  				buffer.append(dateFormat.format(locateResult.getCreatedTime()));
  				buffer.append(":"+locateResult.getGeneratedRuleId());
  				buffer.append(", ");
			}
  			buffer.deleteCharAt(buffer.length()-1);
  			buffer.deleteCharAt(buffer.length()-1);
			ErrorMessage errorMessage =new ErrorMessage(BusinessConstants.MIGRATION_CONTRACT_RULE_INVALID);
			errorMessage.setParameters(new String[] {buffer.toString() });
			messageList.add(errorMessage);  			
  			return false;
  		}
		return true;		
	}
	/**
	 * 
	 * @param migratedProdStructureMappingSysID
	 * @return
	 * @throws SevereException
	 */
	public boolean isMigratedProductStructureNotLocked(int migratedProdStructureMappingSysID, List messageList)throws SevereException{
  		MigrationAdapterManager migrationAdapterManager = new MigrationAdapterManager();
  		if(migrationAdapterManager.isMigratedProductStructureLocked(migratedProdStructureMappingSysID)){
			messageList.add(new ErrorMessage(BusinessConstants.PRODUCT_STRUCTURE_LOCKED));
  			return false;
  		}
			return true;		
	}

	private boolean isDataFeedRunning() throws SevereException {
		ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
		DataFeedStatus dataFeedStatus = new DataFeedStatus();
		dataFeedStatus = builder.getDataFeed(dataFeedStatus);
		if ((dataFeedStatus != null) && (1 == dataFeedStatus.getFeedRunFlag())) {
			return true;
		}
		return false;
	}
	
	public boolean checkCorporatePlanCode(ConfirmMigrationContractRequest request, List messageList)throws SevereException{
	    MigrationAdapterManager migrationAdapterManager = new MigrationAdapterManager();
	    String productFamily = request.getMigrationContractSession().getMigrationContract().getProductFamily();
	    int migSysId = Integer.parseInt(request.getMigrationContractSession().getMigrationContract().getMigrationSystemId().toString());
	    	    
	    if (productFamily.equals("HMO")|| productFamily.equals("POS")){
	        List corpPlanList = migrationAdapterManager.isCorporatePlanCodeNull(migSysId);
	        if(null != corpPlanList && !corpPlanList.isEmpty()){
	            StringBuffer buffer = new StringBuffer();
				SimpleDateFormat dateFormat =new SimpleDateFormat("MM/dd/yyyy");
	            for( java.util.Iterator corpPlanListItr = corpPlanList.iterator(); corpPlanListItr.hasNext();){
	                MigrationDateSegment migDS= (MigrationDateSegment)corpPlanListItr.next();
	                buffer.append(dateFormat.format(migDS.getEffectiveDate()));
	                buffer.append(", ");
	            }
	            
	            ErrorMessage errorMessage =new ErrorMessage(BusinessConstants.MIGR_CRPRT_PLANCODE_NULL);
				errorMessage.setParameters(new String[] { buffer.toString() });
				messageList.add(errorMessage);
	           	           
	            return false;
	        }
	        
	    }
	    return true;
	}
	
	public void confirmLineMappingsToMappingMaster(ConfirmMigrationContractRequest request, Audit audit) 
	throws SevereException {
		
		int mappingSysId = Integer.parseInt(request.getMigrationContractSession().
				getMigrationContract().getMigratedProdStructureMappingSysID());		
		int migrationdId = Integer.parseInt(request.getMigrationContractSession().
				getMigrationContract().getMigrationSystemId());	
		String authorized = null;
		if( (request.getUser().isAuthorized(WebConstants.MIG_WIZARD, WebConstants.OVER_RIDE_VAR))){
			authorized = "Yes";
		}else{
			authorized = "No";
		}
		MigrationBusinessObjectBuilder migrationBusinessObjectBuilder = new MigrationBusinessObjectBuilder();
		migrationBusinessObjectBuilder.confirmLineMappings(migrationdId, mappingSysId, request.getUser(),authorized);
	}
	
//	public boolean confirmLineMappingsToMappingMaster(ConfirmMigrationContractRequest request, Audit audit) throws SevereException {
//		MigrationContract migrationContract = request.getMigrationContractSession().getMigrationContract();
//		int mappingSysId = Integer.parseInt(request.getMigrationContractSession().getMigrationContract().getMigratedProdStructureMappingSysID());
//		int migrationdId = Integer.parseInt(migrationContract.getMigrationSystemId());
//		String flag;
//		MigrationBusinessObjectBuilder migrationBusinessObjectBuilder = new MigrationBusinessObjectBuilder();
//		boolean lockSuccess = false;
//		
//		List confirmMigDataList = migrationBusinessObjectBuilder.retrieveConfirmDataForInsert(migrationdId, mappingSysId, request.getUser());
//		List confirmMigDataListForUpdate = migrationBusinessObjectBuilder.retrieveConfirmDataForUpdate(migrationdId, mappingSysId, request.getUser());
//
//		if( (confirmMigDataList != null && confirmMigDataList.size() > 0) || (confirmMigDataListForUpdate != null && confirmMigDataListForUpdate.size() > 0) ) {
//			
//			// Trying to lock the Structure Product Mapping.
//			lockSuccess = tryForLockOnMapping(migrationBusinessObjectBuilder, mappingSysId, audit);
//			
//			if (lockSuccess == false) {
//				// Not able to acquire lock. 
//				return false;
//			} else {
//				try {
//					confirmMigDataList = migrationBusinessObjectBuilder.retrieveConfirmDataForInsert(migrationdId, mappingSysId, request.getUser());
//					confirmMigDataListForUpdate = migrationBusinessObjectBuilder.retrieveConfirmDataForUpdate(migrationdId, mappingSysId, request.getUser());
//					
//					if (null != confirmMigDataList && confirmMigDataList.size() > 0) {
//						List confirmMigSaveDataList = MigrationContractUtil.prepareDataForConfirmMigrationSave(confirmMigDataList);
//
//						if (null != confirmMigSaveDataList) {
//							flag = "insert";
//							migrationBusinessObjectBuilder.saveConfirmMigrationData(confirmMigSaveDataList, request.getUser(), flag);
//						}
//					}
//
//					if (null != confirmMigDataListForUpdate && confirmMigDataListForUpdate.size() > 0) {
//						List confirmMigUpdateDataList = MigrationContractUtil.prepareDataForConfirmMigrationSave(confirmMigDataListForUpdate);
//						//Only Admin can override conflicts
//						if (request.getUser().isAuthorized(WebConstants.MIG_WIZARD, WebConstants.OVER_RIDE_VAR)) {
//
//							if (null != confirmMigUpdateDataList) {
//								flag = "update";
//								migrationBusinessObjectBuilder.saveConfirmMigrationData(confirmMigUpdateDataList, request.getUser(), flag);
//							}
//						}
//					}	
//				} finally {
//					// Unlocking the mapping.
//					migrationBusinessObjectBuilder.releaseLockOnMapping(mappingSysId, audit);
//				}				
//			}
//		}
//		return true;
//	}

	// This method will try to lock the Structure - Product mapping. This will try in an intervel of 100 milliseconds
	// 30 times.
	private boolean tryForLockOnMapping(MigrationBusinessObjectBuilder builder, int mappingSysId, Audit audit) throws SevereException {
		final int SLEEP_TIME = 200;
		final int MAX_RETRY_COUNT = 15;
		boolean lockSuccess = false;
		
		for(int i=0; i<MAX_RETRY_COUNT; i++) {
			lockSuccess = builder.acquireLockOnMapping(mappingSysId, audit);
			if (lockSuccess == true) {
				break;
			}
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException e) {
				break;
			}
		}
		return lockSuccess;
	}
	
	/**
	 * @param request
	 * @return
	 * @throws WPDException
	 * @throws AdapterException
	 * @throws SevereException
	 */
	public WPDResponse execute(ConfirmMigrationContractRequest request) throws WPDException, SevereException, AdapterException {
		Logger.logInfo("Entering execute method, request = " + request.getClass().getName());
		String migrationContractID = request.getMigrationContractSession().getMigrationContract().getContractId();
		int migratedProdStructureMappingSysID = Integer.parseInt(request.getMigrationContractSession().getMigrationContract().getMigratedProdStructureMappingSysID());
		ConfirmMigrationContractResponse response = (ConfirmMigrationContractResponse) WPDResponseFactory.getResponse(WPDResponseFactory.CONFIRM_MIG_CONTRACT_RESPONSE);
		Audit audit = getAudit(request.getUser());
		List messageList = new ArrayList();
		int ewpdContractSysID;
		int action = request.getAction();
		MigrationBusinessObjectBuilder migrationBusinessObjectBuilder = new MigrationBusinessObjectBuilder();
		MigrationContract migrationContract = request.getMigrationContractSession().getMigrationContract();
		boolean benefitYearIndConflicts = false;

		switch (action) {
			case ConfirmMigrationContractRequest.RETRIEVE_MIG_CONTRACT_REQUEST:
	
				migrationContract = updateStepCompleted(request, "10", audit, migrationBusinessObjectBuilder);
				MigrationContractUtil.setMigrationContractSessionToResponse(migrationContract, request, response);
				response.setBenefitYearIndConflictMessage(getBenefitYearConflictMessage(request));
				break;
	
			case ConfirmMigrationContractRequest.CONFIRM_MIG_CONTRACT_REQUEST:
				boolean isSecondTimeMigration = request.getMigrationContractSession().getMigrationContract().getDoneFlag().equals("S");
				Contract contract = new Contract();
				if (this.isMigrationContractAndEwpdbContractInSequence(migrationContractID, messageList)) {
					if (!this.isContractExistingInEwpdb(migrationContractID, isSecondTimeMigration, messageList)) {
						if (this.isAllDateSegmentThroughStep8(migrationContractID, messageList)) {
							if (WebConstants.SHELL.equals(migrationContract.getContractType()) || this.isAllDateSegmentContainPricing(migrationContractID, messageList)) {
								if (this.isMigrationProductRulesUMAndPNRCoded(migrationContractID, messageList)) {
									if (checkCorporatePlanCode(request, messageList)) {
		
											confirmLineMappingsToMappingMaster(request, audit);
											int migrationdId = 0;
											migrationContract = updateStepCompleted(request, "10", audit, migrationBusinessObjectBuilder);
											MigrationContractUtil.setMigrationContractSessionToResponse(migrationContract, request, response);
											migrationdId = Integer.parseInt(migrationContract.getMigrationSystemId());
											List domainsList = null;
											Domain domain;
											MigrationDomainInfo migrationDomainInfo;
											BusinessObjectManager bom = getBusinessObjectManager();
											try {	
												// check for second time migration				
												if (isSecondTimeMigration) {
													ContractBusinessObjectBuilder contractBusinessObjectBuilder = new ContractBusinessObjectBuilder();
													contract.setContractId(migrationContract.getContractId());
													contract = contractBusinessObjectBuilder.retrieveLatestVersion(contract);
													if (null != contract) {
														contract = (Contract) bom.checkOut(contract, request.getUser());
													} else {
														//FIXME all are mark for delete ?? boom
													}
												} else {
													List businessDomains = migrationBusinessObjectBuilder.retrieveMigDomainInfo(migrationdId);
													if (null != businessDomains) {
														domainsList = new ArrayList(businessDomains.size());
														Iterator businessDomainsItr = businessDomains.iterator();
														while (businessDomainsItr.hasNext()) {
															domain = new Domain();
															migrationDomainInfo = (MigrationDomainInfo) businessDomainsItr.next();
															domain.setLineOfBusiness(migrationDomainInfo.getLobId());
															domain.setBusinessEntity(migrationDomainInfo.getBusinessEntityId());
															domain.setBusinessGroup(migrationDomainInfo.getBusinessGroupId());
															domainsList.add(domain);
														}
													}
													contract.setContractId(migrationContract.getContractId());
													contract.setBusinessDomains(domainsList);
													contract.setContractType(migrationContract.getContractType());
													contract.setBaseDateSegmentSysId(Integer.parseInt(migrationContract.getBaseDateSegmentId()));
													if (null != migrationContract.getHeadQuartersState()) {
														contract.setStateCode(migrationContract.getHeadQuartersState());
													}
													bom.persist(contract, request.getUser(), true);
												}
												String migContractSysID = request.getMigrationContractSession().getMigrationContract().getMigrationSystemId();
				
												int migContractSysId = 0;
												ewpdContractSysID = contract.getContractSysId();
												if (!StringUtil.isEmpty(migContractSysID)) {
													migContractSysId = Integer.parseInt(migContractSysID);
													List dateSegments = migrationBusinessObjectBuilder.getDateSegmentDetails(request.getMigrationContractSession().getMigrationContract()
															.getMigrationSystemId());
													migrationBusinessObjectBuilder.confirmedMigrationDataToEWPD(migContractSysID, ewpdContractSysID, request.getUser());
													benefitYearIndConflicts = isConflictInPlanRenewalType(Integer.parseInt(migContractSysID), dateSegments);
												}
												// If its second time migration and benefitYearConflict contract should not be scheduled. It
												// needs to be kept in Checked out status.
/*												if (!(isSecondTimeMigration && benefitYearIndConflicts)) {*/
													bom.checkIn(contract, request.getUser());
													/*ContractBusinessObjectBuilder cob = new ContractBusinessObjectBuilder();
													contract.setContractDataFeedIndicator("N");
													cob.persist(contract, audit, false);
													bom.scheduleForTest(contract, request.getUser());
												}*/
												String system = request.getMigrationContractSession().getMigrationContract().getSystem();
												// In second time migration if there is BY/CY conflict, contract wil be kept in checked out status and 
												// it will not be scheduled. So this checkout need not be done.
												/*if (benefitYearIndConflicts && !isSecondTimeMigration) {*/
													ContractBusinessObjectBuilder contractBusinessObjectBuilder = new ContractBusinessObjectBuilder();
													contract = contractBusinessObjectBuilder.retrieveLatestVersion(contract);
													contract = (Contract) bom.checkOut(contract, request.getUser());
													AdminMethodBusinessObjectBuilder adminMethodBusinessObjectBuilder=new AdminMethodBusinessObjectBuilder();
													adminMethodBusinessObjectBuilder.validateChangedAdminMethodsForContract(contract.getContractSysId());
/*												}*/
											} catch (SevereException ex) {
												try{
													bom.delete(contract, request.getUser());
												}catch (SevereException e) {
													throw e;
												}
												//make unLock to product structure
												throw ex;
											} catch (Exception ex1) {
												try{
													bom.delete(contract, request.getUser());
												}catch (SevereException e) {
													throw new SevereException(e.getMessage(), null, e);
												}
												//make unLock to product structure
												throw new SevereException(ex1.getMessage(), null, ex1);
											}
											if (request.getMigrationContractSession().getNavigationInfo().isNavigationFlag()) {
												migrationContract = updateStepCompleted(request, "10", audit, migrationBusinessObjectBuilder);
											}
											MigrationContractUtil.setMigrationContractSessionToResponse(migrationContract, request, response);
											response.setSuccess(true);
											InformationalMessage messageInfo = null;
/*											if (benefitYearIndConflicts)
												messageInfo = new InformationalMessage("migration.ended.BYCYConflict.contract");
											else*/
												messageInfo = new InformationalMessage(BusinessConstants.MIGRATION_ENDED_FOR_CONTRACT);
											messageInfo.setParameters(new String[] { migrationContractID });
											messageList.add(messageInfo);
											break;
										}
									}
								}
							}
						}
					}
				}
		response.setMessages(messageList);
		Logger.logInfo("Returning execute method, request = " + request.getClass().getName());
		return response;		
	}
	/**
	 * @param MigrateNotesRequest
	 * @return WPDResponse
	 * @throws WPDException
	 * @throws AdapterException
	 * @throws SevereException
	 */
	public WPDResponse execute(RetrieveBenefitDetailsRequest request)
			throws WPDException, SevereException, AdapterException {
		Logger.logInfo("Entering execute method, request = "
				+ request.getClass().getName());
		RetrieveBenefitDetailsResponse benefitDetailsResponse = (RetrieveBenefitDetailsResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.RETRIEVE_BENEFIT_DETAILS_RESPONSE); 
		Audit audit = new AuditImpl();
        audit.setUser(request.getUser().getUserId());
        audit.setTime(new Date());
		MigrationBusinessObjectBuilder migrationBusinessObjectBuilder = new MigrationBusinessObjectBuilder();
		
		request.getMigrationContractSession().getNavigationInfo()
								.setUpdateLastAccessedPageOnly(true);
		
		MigrationContract migrationContract = updateStepCompleted(request,
				"8", audit, migrationBusinessObjectBuilder);
		
//		List list = new ArrayList();
		List list = null;
		if(request.getAction().equals("Benefit")){
			MigrateNotesBO migrateNotesBO = new MigrateNotesBO();
			migrateNotesBO.setBenefitComponentId(request.getBenefitComponentId());
			migrateNotesBO.setMigratedDateSegmentId(request.getMigratedDateSegmentId());
			migrateNotesBO.setContractSystemId(request.getContractSysId());
			if(null!=request.getMigrationContractSession()){
				if(null!=request.getMigrationContractSession().getMigrationContract()){
					migrateNotesBO.setBaseDateSegId(request.getMigrationContractSession().getMigrationContract().getBaseDateSegmentId());
				}
			}
			list = migrationBusinessObjectBuilder.searchBenefitsForMigration(migrateNotesBO,audit);
	        benefitDetailsResponse.setList(list);
		}else if(request.getAction().equals("BenefitComponent")){
			BenefitComponentNote benefitComponentNote = new BenefitComponentNote();
			benefitComponentNote.setBenefitCompSysId(request.getBenefitComponentId());
			benefitComponentNote.setMigDateSegSysId(new Integer(request.getMigratedDateSegmentId()).intValue());
			benefitComponentNote = migrationBusinessObjectBuilder.searchBenefitComponentForMigration(benefitComponentNote,audit);
			benefitDetailsResponse.setBenefitComponentNote(benefitComponentNote);
		}else if(request.getAction().equals("Contract")){
			MigrationDateSegment dateSegment = new MigrationDateSegment();
			dateSegment.setSystemId(new Integer(request.getMigratedDateSegmentId()).intValue());
			list = migrationBusinessObjectBuilder.searchContractForMigration(dateSegment,audit);
	        benefitDetailsResponse.setList(list);
		}
		Logger.logInfo("Returning execute method, request = "
				+ request.getClass().getName());
		return benefitDetailsResponse;
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws SevereException
	 */
	public WPDResponse execute(SaveLastAccessPageInfoRequest request)throws SevereException{
		Logger.logInfo("Entering execute method, request = "
				+ request.getClass().getName());
		SaveLastAccessPageInfoResponse saveLastAccessPageInfoResponse = (SaveLastAccessPageInfoResponse) WPDResponseFactory
						.getResponse(WPDResponseFactory.LAST_ACCESS_PAGE_MIG_CONTRACT_RESPONSE);
		
	        MigrationBusinessObjectBuilder migrationBusinessObjectBuilder = new MigrationBusinessObjectBuilder();
	        NavigationInfo navigationInfo = request.getMigrationContractSession()
	                								.getNavigationInfo();
	        Audit audit = new AuditImpl();
	        audit.setUser(request.getUser().getUserId());
	        audit.setTime(new Date());
	        
	        navigationInfo.setLastAccessedPage(WebConstants.MIG_CONTRACT_STEP2);
	        migrationBusinessObjectBuilder.persistPageInformation(navigationInfo, audit, false);	       
	        saveLastAccessPageInfoResponse.getMigrationContractSession().setNavigationInfo(navigationInfo);		
	        Logger.logInfo("Returning execute method, request = "
					+ request.getClass().getName());
		return saveLastAccessPageInfoResponse;
	}
	
	/**
	 * @param confirmMigSaveDataList
	 * @param request
	 * @throws SevereException
	 */
	private void deleteFromMigContractMappingMaster(
			List confirmMigSaveDataList, ConfirmMigrationContractRequest request)
			throws SevereException {
		if (null != confirmMigSaveDataList) {
			Iterator migSaveDataListIterator = confirmMigSaveDataList
					.iterator();
			MigrationBusinessObjectBuilder migrationBusinessObjectBuilder = new MigrationBusinessObjectBuilder();
			while (migSaveDataListIterator.hasNext()) {
				ConfirmMigrationContractForSave confirmMigrationContractForSave = (ConfirmMigrationContractForSave) migSaveDataListIterator
						.next();
				migrationBusinessObjectBuilder.deleteVariableMappingMasterList(
						confirmMigrationContractForSave, request.getUser());
			}//end while
		}//end if
	}//end deleteFromMigContractMappingMaster

	/**
	 * @param confirmMigSaveDataList
	 * @param request
	 * @throws SevereException
	 */
	private void deleteFromTempContractMapping(int migContractSysId,
			List confirmMigSaveDataList, User user) throws SevereException {

		MigrationBusinessObjectBuilder migrationBusinessObjectBuilder = new MigrationBusinessObjectBuilder();
		ConfirmMigrationContractForSave confirmMigrationContractForSave = new ConfirmMigrationContractForSave();
		
			confirmMigrationContractForSave.setMigContractSysId(migContractSysId);
			migrationBusinessObjectBuilder.deleteFromTempContractMapping(
					confirmMigrationContractForSave, user);
	}

	/**
	 * To update stepCompleted field after each step
	 * 
	 * @param MigrationContract,
	 *            String stepCompleted, Audit audit,
	 *            MigrationBusinessObjectBuilder migrationBusinessObjectBuilder
	 * @return MigrationContract Object with updated values.
	 * @throws SevereException
	 * @throws AdapterException
	 */
	private MigrationContract updateStepCompleted(
			MigrationContractRequest migrationContractRequest,
			String stepCompleted, Audit audit,
			MigrationBusinessObjectBuilder migrationBusinessObjectBuilder)
			throws SevereException, AdapterException {
		
		String lastAccessedPage = null;
		MigrationContract migrationContract = migrationContractRequest
				.getMigrationContractSession().getMigrationContract();
		NavigationInfo navigationInfo =
				migrationContractRequest.getMigrationContractSession().getNavigationInfo();
		lastAccessedPage = "step".concat(stepCompleted);
		if(null != navigationInfo){
		if(navigationInfo.isUpdateLastAccessedPageOnly()){
			navigationInfo.setLastAccessedPage(lastAccessedPage);			
			navigationInfo.setUpdateLastAccessedPageOnly(false);
		}
		else{
			try{			
				if(navigationInfo.getStepCompleted() < Integer.parseInt(stepCompleted)){
					navigationInfo.setStepCompleted(Integer.parseInt(stepCompleted));
				}		
			}catch (NumberFormatException nfe){
				
			}
			navigationInfo.setNavigationFlag(false);
		}
		try{
		navigationInfo.setDateSegmentSysId(Integer.parseInt(migrationContractRequest
															.getMigrationContractSession()
															.getDateSegmentId()));
		}catch (NumberFormatException nfe){
			
		}		
		navigationInfo.setLastUpdatedTimeStamp(audit.getTime());
		navigationInfo.setLastUpdatedUser(audit.getUser());
			if(0 != navigationInfo.getDateSegmentSysId()){
				migrationBusinessObjectBuilder.persistPageInformation(navigationInfo,audit, false);
				migrationContractRequest.getMigrationContractSession().setNavigationInfo(navigationInfo);
			}
		}
		return migrationContract;
	}

	/**
	 * This will do 1. Validation - check whether valid for migration to ewpd 2.
	 * Update the mapping 3. Create contract in ewpd. 4. All the migrated data
	 * will be populated in new contract. 5. Schedule contract to Test.
	 * 
	 * @param migContSysId
	 * @param messages
	 * @return
	 * @throws WPDException
	 */
	private Contract finalizeMigration(int migContSysId, User user,
			List messages) throws WPDException {
//		List migrationDomains = new ArrayList();
		List ewpdDomains = null;
		Domain domain;
		MigrationDomainInfo migrationDomainInfo;
		BusinessObjectManager bom = getBusinessObjectManager();
		MigrationBusinessObjectBuilder migrationBusinessObjectBuilder = new MigrationBusinessObjectBuilder();

		MigrationContract migrationContract = null;

		// Retrieving Domains
		List migrationDomains = migrationBusinessObjectBuilder
				.retrieveMigDomainInfo(migContSysId);
		if(null!=migrationDomains){
			ewpdDomains = new ArrayList(migrationDomains.size());
			Iterator migrationDomainsItr = migrationDomains.iterator();
			while (migrationDomainsItr.hasNext()) {
				domain = new Domain();
				migrationDomainInfo = (MigrationDomainInfo) migrationDomainsItr
						.next();
				domain.setLineOfBusiness(migrationDomainInfo.getLobId());
				domain.setBusinessEntity(migrationDomainInfo.getBusinessEntityId());
				domain.setBusinessGroup(migrationDomainInfo.getBusinessGroupId());
				ewpdDomains.add(domain);
			}
		}

		Contract contract = new Contract();
		contract.setContractId(migrationContract.getContractId());
		contract.setBusinessDomains(ewpdDomains);
		contract.setContractType(migrationContract.getContractType());

		bom.persist(contract, user, true);
		return null;
	}
	
	/**
	 * Get the Audit object using audit factory.
	 * @param user	User object
	 * @return	Audit object
	 */
	private Audit getAudit(User user) {
		AuditFactory auditFactory = (AuditFactory)ObjectFactory.getFactory(ObjectFactory.AUDIT);
		Audit audit = auditFactory.getAudit(user);
		return audit;
	}
	

	private boolean mapQuestionAnswer(ConfirmMigrationContractRequest request, int ewpdContractSysID, List dateSegments) throws SevereException{
			MigrationBusinessObjectBuilder migrationBuilder = new MigrationBusinessObjectBuilder();
			MigrationProductAdministration entityProductAdministration =  new MigrationProductAdministration();
			HashMap params = new HashMap();
//			List possibleAnswerList =  new ArrayList();
			List accumIndicatorList = null;
			MigrationPossibleAnswer possibleAnswer = null;
			Audit audit = getAudit(request.getUser());
			String contractId = request.getMigrationContractSession().getMigrationContract().getContractId();
		
			if (null != dateSegments || 0 != dateSegments.size()) {
			    Iterator dateSegmentsItr = dateSegments.iterator();
			    MigrationDateSegment migrationDateSegment;
			    int ewpdDatesegmentID;
		    while(dateSegmentsItr.hasNext()){

				 migrationDateSegment = (MigrationDateSegment) dateSegmentsItr.next();
				 //get ewpd datesegment id
				ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
				ewpdDatesegmentID = builder.getDateSegmentSysId(ewpdContractSysID, WPDStringUtil.convertDateToString( migrationDateSegment.getEffectiveDate()));
				LegacyBuilder legacyBuilder = new LegacyBuilder();
				String benefitYearAccumIndicator = legacyBuilder.getBenefitYearAccumIndicator(contractId , migrationDateSegment.getLegacyStartDate());

				if(!StringUtil.isEmpty(benefitYearAccumIndicator)){
				    accumIndicatorList = new ArrayList();
					if(benefitYearAccumIndicator.equalsIgnoreCase(MigrationContractUtil.ACCUM_IND_YES)){
						accumIndicatorList.add(new String(MigrationContractUtil.ACCUM_IND_BY));
					}else if(benefitYearAccumIndicator.equalsIgnoreCase(MigrationContractUtil.ACCUM_IND_FLAG_NO)){
						accumIndicatorList.add(new String(MigrationContractUtil.ACCUM_IND_CY));
					}
					params.put("referenceID",request.getReferenceID().toUpperCase());
					params.put("possibleAnswerDesc",accumIndicatorList);
					params.put("entitySysID", String.valueOf(request.getMigrationContractSession().getMigrationContract().getEwpdProductSystemId()));
					List possibleAnswerList = migrationBuilder.retrievePossibleAnswer(params);						
					if(possibleAnswerList!=null && possibleAnswerList.size()>0){
						possibleAnswer = (MigrationPossibleAnswer)possibleAnswerList.get(0);
						entityProductAdministration.setAnswerId(possibleAnswer.getPossibleAnswerId());
						entityProductAdministration.setEntityId(ewpdDatesegmentID);						
						entityProductAdministration.setAdminOptSysId(possibleAnswer.getAdminOptionSysID());
						entityProductAdministration.setQuestionNumber(possibleAnswer.getAdminQuestionNumber());
						entityProductAdministration.setEntityType("CONTRACT");
						migrationBuilder.persistProductAdministrationValues(entityProductAdministration, audit);
					}		
				}
			}
			}
		  return true;
	   }
	
	/**
	 * This will return the message that needs to be shown in confirm screen if there is 
	 * any conflict in BY/CY indicator between Legacy and Ewpd.
	 * @param request
	 * @return
	 * @throws SevereException
	 */
	private InformationalMessage getBenefitYearConflictMessage(ConfirmMigrationContractRequest request) throws SevereException {
		int migSysId = Integer.parseInt(request.getMigrationContractSession().getMigrationContract().getMigrationSystemId());
		MigrationBusinessObjectBuilder migrationBuilder = new MigrationBusinessObjectBuilder();
		List dateSegments = migrationBuilder.getDateSegmentDetails(String.valueOf(migSysId));
		MigrationContract migrationContract = new MigrationContract();
		migrationContract.setMigrationSystemId(String.valueOf(migSysId));
		migrationBuilder.retrieve(migrationContract);
		boolean conflict = isConflictInPlanRenewalType(migSysId, dateSegments);
		InformationalMessage message = null;
		if(conflict) {
			if(isModelContractAssociated(migrationContract)) {
				message = new InformationalMessage("migration.bnftYrIndConflict.model");
			} else {
				message = new InformationalMessage("migration.bnftYrIndConflict.product");
			}
		}
		return message;
		
	}
	
	/**
	 * Method returns true if there is a conflict between BY/CY answer in Legacy and ewpd.
	 * ewpd answer will be considered from associated base contract if the migration is 
	 * based on a model contract otherwise from product.
	 * @param migContSysId
	 * @param dateSegments
	 * @return
	 * @throws SevereException
	 */
	private boolean isConflictInPlanRenewalType(int migContSysId, List dateSegments) throws SevereException {
		MigrationBusinessObjectBuilder migrationBuilder = new MigrationBusinessObjectBuilder();
		MigrationContract migrationContract = new MigrationContract();

		String ewpdPlanRenewalType = getEwpdPlanRenewalType(migContSysId);
		migrationContract.setMigrationSystemId(String.valueOf(migContSysId));
		migrationBuilder.retrieve(migrationContract);
		return isConflictInPlanRenewalType(ewpdPlanRenewalType, migrationContract.getContractId(), dateSegments);
	}
	
	private boolean isConflictInPlanRenewalType(String ewpdPlanRenewalType, String contractId, List dateSegments) throws SevereException {
		LegacyBuilder legacyBuilder = new LegacyBuilder();
		String legacyPlanRenewalType = null;
		boolean conflictExists = false;
		if(ewpdPlanRenewalType == null ) {
			// BY/CY answer does not exist for associated Product/baseContract
			return false;
		}
		
		if(ewpdPlanRenewalType.equals(MigrationContractUtil.ACCUM_IND_BY)) 
			ewpdPlanRenewalType = MigrationContractUtil.ACCUM_IND_YES;
		else
			ewpdPlanRenewalType = MigrationContractUtil.ACCUM_IND_FLAG_NO;


		for (Iterator iter = dateSegments.iterator(); iter.hasNext();) {
			MigrationDateSegment migDateSegment = (MigrationDateSegment) iter.next();
			legacyPlanRenewalType = legacyBuilder.getBenefitYearAccumIndicator(contractId , migDateSegment.getLegacyStartDate());
			if(! legacyPlanRenewalType.equals(ewpdPlanRenewalType)) {
				conflictExists = true;
				break;
			}
		}
		return conflictExists;
	}
	
	/**
	 * Method returns BY or CY which is the answer associated with the question which has reference 0004.
	 * If the migration is based on Model contract this will return answer from associated Model contract
	 * otherwise from associated product.
	 * @param migContSysId
	 * @return
	 * @throws SevereException
	 */
	public String getEwpdPlanRenewalType(int migContSysId) throws SevereException {
		MigrationBusinessObjectBuilder migrationBuilder = new MigrationBusinessObjectBuilder();
		String ewpdPlanRenewalType = null;
		int entitySysId = 0;
		String entityType = null;
		MigrationContract migrationContract = new MigrationContract();
		
		migrationContract.setMigrationSystemId(String.valueOf(migContSysId));
		migrationBuilder.retrieve(migrationContract);

		String baseDateSegSysId = migrationContract.getBaseDateSegmentId() ;
		if(isModelContractAssociated(migrationContract)) {
			// Get the BY/CY answer from associated base contract admin option
			entityType = "CONTRACT";
			entitySysId = Integer.parseInt(baseDateSegSysId);
		} else {
			// Get the BY/CY answer from associated product admin option
			entityType = "PRODUCT";
			entitySysId = Integer.parseInt(migrationContract.getEwpdProductSystemId());
		}
		ewpdPlanRenewalType = migrationBuilder.getPlanRenewalType(entityType, entitySysId);
		return ewpdPlanRenewalType;
	}
	
	/**
	 * Method returns true if the contract has an associated base contract, and false otherwise.
	 * @param migContract
	 * @return
	 */
	public boolean isModelContractAssociated(MigrationContract migContract) {
		String baseDateSegSysId = migContract.getBaseDateSegmentId();
		if(baseDateSegSysId != null && !baseDateSegSysId.trim().equals("") && (Integer.parseInt(baseDateSegSysId) != 0))
			return true;
		return false;
	}
	
	/**
	 * Method saves the notes of the benefit,benefit component and contract
	 * @param SaveMigrationNotesRequest
	 * @return WPDResponse
	 */ 
	public WPDResponse execute(SaveMigrationNotesRequest request)
				throws WPDException, AdapterException {
		Logger.logInfo("Entering execute method, request = "
				+ request.getClass().getName());
		SaveMigrationNotesResponse saveMigrationNotesResponse = (SaveMigrationNotesResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.SAVE_MIGRATION_NOTES_RESPONSE);
			int action = request.getAction();
			MigrationBusinessObjectBuilder migrationBusinessObjectBuilder = new MigrationBusinessObjectBuilder();
			MigrationContract migrationContract = request.getMigrationContractSession().getMigrationContract();
			User user = request.getUser();
			Audit audit = getAudit(request.getUser());
			if(action == SaveMigrationNotesRequest.SAVE_MIGRATION_NOTES){
				
				List notesMigrationList = request.getNoteMigrationList();
				// Get BenefitComponentNotesObject
				BenefitComponentNote  benefitComponentNote = MigrationContractUtil.getBenefitComponentNote(notesMigrationList);
				//Get BenefitNotesObject
				List benefitNoteList  = MigrationContractUtil.getBenefitNoteList(notesMigrationList);
//				Get date segement details.
				if(null!=request.getMigrationContractSession().getDateSegmentId()){
					migrationContract.setLastAccessMigDateSegmentSysID(Integer.parseInt(request.getMigrationContractSession().getDateSegmentId()));
				}
				List dateSegmentList = migrationBusinessObjectBuilder.getMigrationDateSegment(migrationContract);
				
				MigrationDateSegment migrationDateSegment = MigrationContractUtil.getContractNote(dateSegmentList,notesMigrationList);
				
				migrationDateSegment.setContractType(migrationContract.getContractType());

                if(null!=migrationContract.getBaseDateSegmentId()){

                    migrationDateSegment.setBaseDateSegmentSysId(Integer.parseInt(migrationContract.getBaseDateSegmentId()));

                }

                if(null!=migrationContract.getMigrationSystemId()){

                   migrationDateSegment.setContractSysId(Integer.parseInt(migrationContract.getMigrationSystemId()));

                }

				migrationBusinessObjectBuilder.saveNotesMigrationInfo(
						benefitNoteList,benefitComponentNote,migrationDateSegment, request.getUser());
				
				if(request.getMigrationContractSession().getNavigationInfo().isNavigationFlag()){
					migrationContract = updateStepCompleted(
										request, "8", audit,
									migrationBusinessObjectBuilder);
				}
			
				saveMigrationNotesResponse.addMessage(new InformationalMessage("migration.notes.save.success"));
				
			}
			Logger.logInfo("Returning execute method, request = "
					+ request.getClass().getName());
			return saveMigrationNotesResponse;
	}
	
}