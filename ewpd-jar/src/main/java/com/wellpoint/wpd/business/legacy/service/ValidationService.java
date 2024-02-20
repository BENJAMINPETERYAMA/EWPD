/*
 * ValidationService.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.legacy.service;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.business.framework.service.WPDBusinessService;
import com.wellpoint.wpd.business.legacy.builder.LegacyBuilder;
import com.wellpoint.wpd.business.migration.builder.MigrationBusinessObjectBuilder;
import com.wellpoint.wpd.business.migration.util.MigrationContractUtil;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyContract;
import com.wellpoint.wpd.common.migration.bo.MigrationContract;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id:
 */
public class ValidationService extends WPDBusinessService{
	
	/**
	 * 
	 * @param legacyContract
	 * @return
	 * @throws SevereException
	 */
	public List validateNewLegacyContract(LegacyContract legacyContract) throws SevereException{
		
		String system = legacyContract.getSystem();
		String contractId = legacyContract.getContractId();
		
		LegacyBuilder legacyBuilder = new LegacyBuilder();
		List messageList = new ArrayList();		
		
		if (!legacyBuilder.isContractExists(system, contractId, "Transferred to Production")) { // Check If the given contract does not exist in Legacy, show error message
			messageList.add(new ErrorMessage(BusinessConstants.SEARCH_CONTRACT_DOES_NOT_EXISTS));
		}
		else if (checkContract(legacyContract.getContractId())){//If contract exist in ewpd, show error message.
			messageList.add(new ErrorMessage(BusinessConstants.MIGRATION_ALREADY_EXISTS_IN_EWPD));
		}else if(legacyBuilder.isContractLocked(system,contractId)){ // Check if contract is locked in Legacy, show error message.
			messageList.add(new ErrorMessage(BusinessConstants.SEARCH_CONTRACT_LOCKED));
		}else if(!legacyBuilder.isBenefitStructureAssociated(system,contractId)){// Check If contract is not associated with benefit structure,  show error message
			messageList.add(new ErrorMessage(BusinessConstants.BENEFIT_STRUCTURE_NOT_ASSOCIATED));
		}else if(legacyBuilder.isContractScheduledToTest(system,contractId)){// Check If contract is scheduled to test show error message.
			messageList.add(new ErrorMessage(BusinessConstants.CONTRACT_SCHEDULED_TO_TEST));
		}else if(legacyBuilder.isContractScheduledToProduction(system,contractId)){// Check If contract is scheduled to Production show error message.
			messageList.add(new ErrorMessage(BusinessConstants.CONTRACT_SCHEDULED_TO_PRODUCTION));
		}			

		return messageList;
	}
	
	/**
	 * 
	 * @param legacyContract
	 * @param migrationContract
	 * @param currentOption
	 * @return
	 * @throws SevereException
	 */
	public List validateMigratedLegacyContract(LegacyContract legacyContract,MigrationContract migrationContract,
						String currentOption) throws SevereException{		

		List messageList = new ArrayList();
		String loggedUser = legacyContract.getCreatedUser();
		String migrationUser = migrationContract.getCreatedUser();
		
		if((MigrationContractUtil.DONE_FLAG_YES).equals(migrationContract.getDoneFlag())){
			messageList.add(new ErrorMessage(BusinessConstants.MIGRATION_COMPLETED_FOR_CONTRACT));

		}else if (!loggedUser.equals(migrationUser)) {
			messageList.add(new ErrorMessage(BusinessConstants.MIGRATION_STARTED_FOR_CONTRACT));
		
		}else if(!currentOption.equals(migrationContract.getOption())){
			messageList.add(new ErrorMessage(BusinessConstants.MIGRATION_ALREADY_STARTED_ANOTHER_OPTION));
			
		}
		return messageList;		
	}
	
	/**
	 * 
	 * @param contractId
	 * @return
	 * @throws SevereException
	 */
	private boolean checkContract(String contractId) throws SevereException {
		boolean isContractExistsInEwpdb = true;
		MigrationBusinessObjectBuilder migrationBusinessObjectBuilder = new MigrationBusinessObjectBuilder();
		if (migrationBusinessObjectBuilder.isContractExistingInEwpdb(contractId)) {
			return isContractExistsInEwpdb;
		}
		return false;
	}
}
