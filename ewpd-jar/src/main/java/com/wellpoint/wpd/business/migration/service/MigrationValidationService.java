/*
 * MigrationValidationService.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.migration.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wellpoint.wpd.business.contract.adapter.ContractAdapterManager;
import com.wellpoint.wpd.business.contract.builder.ContractBusinessObjectBuilder;
import com.wellpoint.wpd.business.framework.service.WPDBusinessValidationService;
import com.wellpoint.wpd.business.legacy.adapter.LegacyAdapterManager;
import com.wellpoint.wpd.business.legacy.builder.LegacyBuilder;
import com.wellpoint.wpd.business.migration.adapter.MigrationAdapterManager;
import com.wellpoint.wpd.business.migration.builder.MigrationBusinessObjectBuilder;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.contract.bo.Contract;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyObject;
import com.wellpoint.wpd.common.legacycontract.request.RetrieveLegacyContractRequest;
import com.wellpoint.wpd.common.legacycontract.response.RetrieveLegacyContractResponse;
import com.wellpoint.wpd.common.migration.bo.MigrationContract;
import com.wellpoint.wpd.common.migration.request.SaveMigGeneralInfoRequest;
import com.wellpoint.wpd.common.migration.response.SaveMigGeneralInfoResponse;
import com.wellpoint.wpd.common.util.DateUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id:
 */
public class MigrationValidationService extends WPDBusinessValidationService {

	/**
	 * 
	 * @param request
	 * @return
	 * @throws SevereException
	 */
	public WPDResponse execute(RetrieveLegacyContractRequest request) throws SevereException{
		RetrieveLegacyContractResponse response = (RetrieveLegacyContractResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.RETRIEVE_LEGACY_CONTRACT_RESPONSE);
		boolean valid = true;
		//true only after user confirmation for second tome migration.
//        boolean userConfirmToAddNewDS = request.isUserConfirmToAddNewDS(); 
		List messages = new ArrayList();
		boolean newMigration = false;
		boolean afterMigrationDateSegmentExist = false;
		
//        if(!userConfirmToAddNewDS){
        	newMigration = true;

		LegacyBuilder legacyBuilder = new LegacyBuilder();
		String system = request.getLegacyContractVO().getSourceSystem();
		String contractId = request.getLegacyContractVO().getContractId();
		String loggedUser = request.getUser().getUserId();
		String option = request.getLegacyContractVO().getOption();
		
		MigrationContract migrationContract =  getMigrationContract(contractId);
		
		if(system.equals(LegacyObject.SYSTEM_CP2000)) {
			if(! request.getUser().isAuthorized(WebConstants.MIGRATION_WIZARD_MODULE, WebConstants.MIGRATE_CP2000_TASK)) {
				valid = false;
				messages.add(new ErrorMessage("insufficient.privilege.cp2000migration"));
			}
		} 
		if(system.equals(LegacyObject.SYSTEM_CP)) {
			if(! request.getUser().isAuthorized(WebConstants.MIGRATION_WIZARD_MODULE, WebConstants.MIGRATE_CP_TASK)) {
				valid = false;
				messages.add(new ErrorMessage("insufficient.privilege.cpmigration"));
			}
		} 
		
		if(valid && migrationContract != null) {
			newMigration = false;
			//chesk for isMigrationCompleted
			boolean isSecondTimeMigration = migrationContract.getDoneFlag().equals("Y");
			if(isSecondTimeMigration){
				valid = false;
				if(!this.isMigratedContractDeletedInEwpdb(contractId,  messages )){
					//check for any ds left in legacy
					// check if user used "Migrate All Date Segments" option in last time migration process 
					
						//check for any ds left in legacy
						if(legacyBuilder.isContractExists(system, contractId, "%")) {
							
							List dateSegmentsLeft = new LegacyAdapterManager().getLeftOverDS(contractId);
							if(null == dateSegmentsLeft || dateSegmentsLeft.size()==0){
								messages.add(new ErrorMessage(BusinessConstants.MIGRATION_COMPLETED_FOR_CONTRACT));
							}else{
							//second time migration 
								valid = true;
								valid = this.isLegacyContractValid(system, contractId, valid, messages);
								afterMigrationDateSegmentExist = valid;
							}
							//same kind of validation that is require at the time of confirm migration.
//							if(valid && !isContractIdExistInEwpdb(contractId, messages)){
//								afterMigrationDateSegmentExist = true;	
//							}else{
//								valid = false;
//							}
							
						}else{
							//Migration completed and no more ds available for further migration 
							messages.add(new ErrorMessage(BusinessConstants.MIGRATION_COMPLETED_FOR_CONTRACT));					
						}
					
				}
				if(BusinessConstants.OPT_RENEW_DS.equals(option)){
					valid = false;
					if(messages.size() == 0)  //To check if the contract is already migrated.
						messages.add(new ErrorMessage(BusinessConstants.MIGRATION_PARTIALLY_DONE));
				}
//			}
//			if(isMigrationCompleted(migrationContract)) {
//				valid = false;
//				messages.add(new ErrorMessage(BusinessConstants.MIGRATION_COMPLETED_FOR_CONTRACT));
			} else {				
			if(valid && !loggedUser.equalsIgnoreCase(migrationContract.getCreatedUser())) {
				valid = false;
				messages.add(new ErrorMessage(BusinessConstants.MIGRATION_STARTED_FOR_CONTRACT));
			}
			String validOption = migrationContract.getOption();
			
			if(valid && !option.equals(validOption)) {
				valid = false;
				ErrorMessage errorMessage = new ErrorMessage(BusinessConstants.MIGRATION_ALREADY_STARTED_ANOTHER_OPTION);
				String message;
				switch (validOption.charAt(0)) {
					case '2':
						message = WebConstants.RENEW_EXISTING_DATE_SEGMENT;
						break;
					case '3':
						message = WebConstants.MIGRATE;
						break;
					default:
						message = "another";
				}
				errorMessage.setParameters(new String []{message});
				messages.add(errorMessage);
			}
			}
		} 
		
		if(valid && newMigration) {
			if(!legacyBuilder.isContractExists(system, contractId, "%")) {
				valid = false;
				messages.add(new ErrorMessage(BusinessConstants.SEARCH_CONTRACT_DOES_NOT_EXISTS));
			}
			if(valid && isContractIdExistInEwpdb(contractId)) {
				valid = false;
				messages.add(new ErrorMessage(BusinessConstants.MIGRATION_ALREADY_EXISTS_IN_EWPD));
			}
//			if(valid && option.equals(BusinessConstants.OPT_MIGRATE_LATEST_TWO_DS)) {
//				//FIXME check for discontinuity
//				if(getDateSegmentsCount(system,contractId, "%" ) < 2) {
//					valid = false;
//					messages.add(new ErrorMessage(BusinessConstants.NOT_ENOUGH_DATE_SEGMENTS));
//				}
//			}
			valid = this.isLegacyContractValid(system, contractId, valid, messages);
		}
//        }else{
//        	afterMigrationDateSegmentExist = true;
//        }
		response.setValid(valid);
		response.setNewMigration(newMigration);
		response.setMessages(messages);
		response.setAfterMigrationDateSegmentExist(afterMigrationDateSegmentExist);
		return response;
	}
	
	
	public WPDResponse execute(SaveMigGeneralInfoRequest request) throws SevereException{
		SaveMigGeneralInfoResponse response = (SaveMigGeneralInfoResponse) WPDResponseFactory
														.getResponse(WPDResponseFactory.SAVE_MIG_GENINFO_RESPONSE);
        boolean valid = true;
        List messageList = new ArrayList();
        ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
		
		if (BusinessConstants.MSG_CUSTOM_CONTRACT_TYPE.equals(request.getContractType())
				&& null != request.getBaseContractId()
				&& !request.getBaseContractId().equals(WebConstants.EMPTY_STRING)){
			 List baseContracts = null;
			 	valid = false;
		        try {
		        	ContractAdapterManager adapterManager = new ContractAdapterManager();
					LegacyBuilder legacyBuilder = new LegacyBuilder();
					// start and end date of legacy contract			
					Date effDate = legacyBuilder.getFirstDateSegment(LegacyObject.SYSTEM_CP2000, request.getContractId()).getStartDate();
//					Date expDate = legacyBuilder.getLatestDateSegment(LegacyObject.SYSTEM_CP2000, request.getContractId()).getEndDate();
					Date expDate = DateUtil.getDefaultEndDate();
					/*START CARS*/
		        	baseContracts = adapterManager.getBaseContracts(
										        			request.getLineOfBusinessList(),
										        			request.getBusinessEntityList(),
															request.getBusinessGroupList(),
															effDate,
															expDate,request.getMarketBusinessUnitList());
		        	/*END CARS*/
		        } catch(SevereException ex) {
		            throw new ServiceException("Adapter exception",null,ex);
		        }
		        if(null!=baseContracts && 0 != baseContracts.size()){
		        	for(int i=0;i<baseContracts.size();i++){
		        		Contract itr =(Contract) baseContracts.get(i);
//		        		if(itr.getContractSysId() == request.getBaseContractSysId())
		        		if(itr.getContractId().equals(request.getBaseContractId())){
		        			valid= true;
		        			break;
		        		}
		        	}		        	
		        }
//		        else{
//		        	valid = false;
//		        }
		        if(!(valid)){
		        	messageList.add(new ErrorMessage(BusinessConstants.DOMAIN_MISMATCH));
		        }
			
		}
        response.setValid(valid);
        response.setMessages(messageList);
		
		return response;
	}
	
	
	
	/**
	 * contract validation @ system (CP or CP200)
	 * @param system
	 * @param contractId
	 * @param valid
	 * @param messages
	 * @return
	 * @throws SevereException
	 */
	private boolean isLegacyContractValid (String system, String contractId, boolean valid, List messages)
	throws SevereException{
		LegacyBuilder legacyBuilder = new LegacyBuilder();
		if(valid && legacyBuilder.isContractLocked(system, contractId)) {
			valid = false;
			messages.add(new ErrorMessage(BusinessConstants.SEARCH_CONTRACT_LOCKED));
		}
		
		if(valid && !legacyBuilder.isBenefitStructureAssociated(system,contractId)) {
			valid = false;
			messages.add(new ErrorMessage(BusinessConstants.BENEFIT_STRUCTURE_NOT_ASSOCIATED));
		}
		
		if(valid && !legacyBuilder.isContractTransferredToProduction(system,contractId)) {
			valid = false;
			messages.add(new ErrorMessage(BusinessConstants.CONTRACT_TRANSFERRED_TO_PRODUCTION));
		}
		
/*		
		if(valid && legacyBuilder.isContractScheduledToTest(system,contractId)) {
			valid = false;
			messages.add(new ErrorMessage(BusinessConstants.CONTRACT_SCHEDULED_TO_TEST));
		}
		
		if(valid && legacyBuilder.isContractScheduledToProduction(system,contractId)) {
			valid = false;
			messages.add(new ErrorMessage(BusinessConstants.CONTRACT_SCHEDULED_TO_PRODUCTION));
		}
*/
		return valid;
	}
	/**
	 * contract validation @ ET-AutoBag system
	 * @param contractID
	 * @param messageList
	 * @return
	 * @throws SevereException
	 */
	public boolean isMigratedContractDeletedInEwpdb(String contractID, List messageList) throws SevereException{
		ContractBusinessObjectBuilder contractBusinessObjectBuilder = new ContractBusinessObjectBuilder();
			String status = contractBusinessObjectBuilder.contractStatusFromLatestVersion(contractID);
			ErrorMessage errorMessage = null;
			if(status == null){
					// boom stop migration 
				errorMessage =new ErrorMessage(BusinessConstants.MSG_MIGRATION_CONTRACT_DELETED);
				messageList.add(errorMessage);
			}else{
				if(status.equals("MARKED_FOR_DELETION")){
					// boom stop migration 
					errorMessage =new ErrorMessage(BusinessConstants.MSG_MIGRATION_CONTRACT_DELETED);
				}else if(status.equals("CHECKED_OUT")){
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
				}else{
					return false;
				}
				messageList.add(errorMessage);
			}
			return true;
	}
	/**
	 * contract validation @ ET-AutoBag system
	 * @param contractID
	 * @param messageList
	 * @return
	 * @throws SevereException
	 */
	public boolean isContractIdExistInEwpdb(String contractID,  List messageList) throws SevereException{
		ContractBusinessObjectBuilder contractBusinessObjectBuilder = new ContractBusinessObjectBuilder();
			String status = contractBusinessObjectBuilder.contractStatusFromLatestVersion(contractID);
			if(status != null){
				ErrorMessage errorMessage = null;
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
				}else if(status.equals("MARKED_FOR_DELETION")){
					// boom stop migration 
					errorMessage =new ErrorMessage(BusinessConstants.MSG_MIGRATION_VALIDATION_STATUS);
					errorMessage.setParameters(new String[] { "Marked_For_Deletion"});					
				}else{
					return false;
				}
				messageList.add(errorMessage);
			}else{
				 //only possible when record is deleted using db script 
			}		
		return true;
	}
		
	/**
	 * 
	 * @param contractId
	 * @return
	 * @throws SevereException
	 */
	private MigrationContract getMigrationContract(String contractId) throws SevereException{
		MigrationBusinessObjectBuilder builder = new MigrationBusinessObjectBuilder();
		MigrationAdapterManager adapterManager = new MigrationAdapterManager();
		List list = adapterManager.getMigrationContracts(contractId);
		MigrationContract migrationContract = null;
		if(list.size() > 0 ){
			migrationContract = (MigrationContract)list.get(0);
		}
		return migrationContract;
	}
	/**
	 * 
	 * @param system
	 * @param contractId
	 * @return
	 * @throws SevereException
	 */
	private int getDateSegmentsCount(String system, String contractId, String contractStatus) throws SevereException {
		LegacyBuilder builder = new LegacyBuilder();
		List ds = builder.retrieveAllDateSegments(system,contractId,LegacyBuilder.OPT_DS_BASIC_INFO, contractStatus);
		return ds.size();
	}
	/**
	 * 
	 * @param migrationContract
	 * @return
	 */
	private boolean isMigrationCompleted(MigrationContract migrationContract){
		if(migrationContract.getDoneFlag().equals("Y"))
			return true;
		else 
			return false;
	}
	/**
	 * 
	 * @param contractId
	 * @return
	 * @throws SevereException
	 */
	private boolean isContractIdExistInEwpdb(String contractId) throws SevereException {
		boolean isContractExistsInEwpdb = true;
		MigrationBusinessObjectBuilder migrationBusinessObjectBuilder = new MigrationBusinessObjectBuilder();
		if (migrationBusinessObjectBuilder.isContractExistingInEwpdb(contractId)) {
			return isContractExistsInEwpdb;
		}
		return false;
	}	
}
