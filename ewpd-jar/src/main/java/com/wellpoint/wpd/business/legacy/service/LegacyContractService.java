/*
 * LegacyContractService.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.legacy.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.wpd.business.contract.builder.ContractBusinessObjectBuilder;
import com.wellpoint.wpd.business.framework.service.ValidationServiceController;
import com.wellpoint.wpd.business.framework.service.WPDBusinessService;
import com.wellpoint.wpd.business.legacy.builder.LegacyBuilder;
import com.wellpoint.wpd.business.migration.builder.MigrationBusinessObjectBuilder;
import com.wellpoint.wpd.business.migration.util.MigrationContractUtil;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.framework.util.StringUtil;
import com.wellpoint.wpd.common.legacycontract.bo.CP2000Contract;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyContract;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyContractMajorHeading;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyContractMinorHeading;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyContractNotes;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyMajorNotes;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyMinorNotes;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyVariable;
import com.wellpoint.wpd.common.legacycontract.bo.MinorHeading;
import com.wellpoint.wpd.common.legacycontract.request.RetrieveHeadingsRequest;
import com.wellpoint.wpd.common.legacycontract.request.RetrieveLegacyContarctNotesRequest;
import com.wellpoint.wpd.common.legacycontract.request.RetrieveLegacyContractRequest;
import com.wellpoint.wpd.common.legacycontract.request.RetrieveLegacyLookUpRequest;
import com.wellpoint.wpd.common.legacycontract.response.RetrieveHeadingsResponse;
import com.wellpoint.wpd.common.legacycontract.response.RetrieveLegacyContractNotesResponse;
import com.wellpoint.wpd.common.legacycontract.response.RetrieveLegacyContractResponse;
import com.wellpoint.wpd.common.legacycontract.response.RetrieveLegacyLookUpResponse;
import com.wellpoint.wpd.common.legacycontract.vo.LegacyContractVO;
import com.wellpoint.wpd.common.legacycontract.vo.LegacyVariableMappingVO;
import com.wellpoint.wpd.common.migration.bo.MigrationContract;
import com.wellpoint.wpd.common.migration.bo.MigrationDateSegment;
import com.wellpoint.wpd.common.util.DateUtil;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id:
 */
public class LegacyContractService extends WPDBusinessService {
	/**
	 * 
	 * @param request
	 * @return WPDResponse
	 * @throws ServiceException
	 */
	public WPDResponse execute(RetrieveLegacyLookUpRequest request)
			throws ServiceException {
		try {
			Logger.logInfo("Entering execute method, request = "
					+ request.getClass().getName());

			RetrieveLegacyLookUpResponse retrieveLegacyLookUpResponse = (RetrieveLegacyLookUpResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.RETRIEVE_LEGACY_LOOKUP_RESPONSE);
			int action = request.getAction();
			LegacyBuilder legacyBuilder = new LegacyBuilder();

			switch (action) {

			case RetrieveLegacyLookUpRequest.RETRIEVE_LEGACY_VARIABLE_MAPPING_DATA:
				LegacyVariableMappingVO variableMappingVO = request
						.getLegacyVariableMappingVO();
				retrieveLegacyLookUpResponse
						.setVariableMappingList(legacyBuilder
								.getStructureVariables(variableMappingVO));
				break;

			}

			Logger.logInfo("Returning  from execute method for request="
					+ request.getClass().getName());

			return retrieveLegacyLookUpResponse;
		} catch (WPDException ex) {
			throw new ServiceException("Exception occured while BOM call",
					null, ex);
		}
	}
	/**
	 * 
	 * @param request
	 * @return WPDResponse
	 * @throws ServiceException
	 */
	public WPDResponse execute(RetrieveHeadingsRequest request)
			throws ServiceException {
		List majorHeadingList = null;
		List minorHeadingList = null;
		List variableList = null;
		List  variableNotesList = null;
		RetrieveHeadingsResponse headingsResponse = (RetrieveHeadingsResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.LEGACY_HEADING_RESPONSE);
		LegacyBuilder legacyBuilder = new LegacyBuilder();
		try {
			if (request.getAction() == 1) {
				majorHeadingList = legacyBuilder.getMajorHeadings(
						request.getSystem(), request.getStructureId(), request
								.getMajorHeading());
				headingsResponse.setMajorHeadingsList(majorHeadingList);
			}
			if (request.getAction() == 2) {
				MinorHeading minorHeading = new MinorHeading();
				minorHeading.setMajorHeadingId(request.getMajorHeadingId());
				minorHeading.setMinorHeadingText(request.getMinorHeading());
				minorHeadingList = legacyBuilder.getMinorHeadings(
						request.getSystem(), minorHeading);
				headingsResponse.setMinorHeadingList(minorHeadingList);
			}
			if (request.getAction() == 3) {
				LegacyVariable variable = new LegacyVariable(request
						.getSystem());
				variable.setMajorHeadingId(request.getMajorHeadingId());
				variable.setStructureId(request.getStructureId());
				variable.setMinorHeadingId(request.getMinorHeadingId());
				variable.setContractId(request.getContractId());
				variable.setProviderArrangement(request.getPvar());
				variable.setStartDate(request.getStartDate());
				variable.setFormat(request.getFormat());
				variable.setVariableId(request.getVariableId());
				variableList = legacyBuilder.getVariables(request
						.getSystem(), request.getOption(), variable);
				if(variableList!=null && variableList.size()>0){
					List newVarList = new ArrayList(variableList.size());
					Iterator iter =variableList.iterator();
					while(iter.hasNext()){
						LegacyVariable legacyVariable = (LegacyVariable)iter.next();
						if(StringUtil.isEmpty(legacyVariable.getVarNoteFlag())){
							legacyVariable.setVarNoteFlag("N");
						}
						newVarList.add(legacyVariable);
					}
					headingsResponse.setVariableList(newVarList);
				}else{
					headingsResponse.setVariableList(variableList);
				}
				
				
			}
			//Retrieve the variable notes
			if (request.getAction() == RetrieveHeadingsRequest.RETRIEVE_VARIABLE_NOTES_POPUP) {
				LegacyVariable variable = new LegacyVariable(request
						.getSystem());
				variable.setContractId(request.getContractId());
				variable.setStartDate(request.getStartDate());
				variable.setVariableId(request.getVariableId());
				variableNotesList = legacyBuilder.getVariableNote(request
						.getSystem(), variable);
				headingsResponse.setVariableNotesList(variableNotesList);
				if(variableNotesList==null || variableNotesList.size()==0){
					headingsResponse.addMessage(new ErrorMessage("no.variable.notes"));
				}
			}
			
		} catch (SevereException e) {
			Logger.logInfo(e);
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			String logMessage = "Failed while processing RetrieveHeadingsRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}

		return headingsResponse;
	}
	/**
	 * 
	 * @param request
	 * @return WPDResponse
	 * @throws SevereException
	 */
	public WPDResponse execute(RetrieveLegacyContractRequest request)throws SevereException {
		Logger.logInfo("Inside Retrieve Date Segment Service");

//		boolean status = true;
//		boolean migrationStatus = true;
		
		List messageList = new ArrayList(5);
		
		RetrieveLegacyContractResponse response = (RetrieveLegacyContractResponse)new ValidationServiceController().execute(request);
		
		if( !response.isValid())
			return response;
		String system = request.getLegacyContractVO().getSourceSystem();
		
//		if(response.isNewMigration() || request.isUserConfirmToAddNewDS()) {
		if(response.isNewMigration() || response.isAfterMigrationDateSegmentExist()) {		
			LegacyBuilder legacyBuilder = new LegacyBuilder();
			List searchResult = legacyBuilder.retrieveAllDateSegments(system, request
					.getLegacyContractVO().getContractId(),
					LegacyBuilder.OPT_DS_BASIC_INFO,
					"%");
			String currentOption = request.getLegacyContractVO().getOption();
			Date minEffectiveOfLatestContract = DateUtil.getDefaultEndDate();
			if(response.isAfterMigrationDateSegmentExist() 
					|| !isDateSegmentDiscontinuous(searchResult,
													minEffectiveOfLatestContract, 
													currentOption,
													system,
													response)){
				
		 	response.setDateSegmentList(searchResult);
		 	
		 	
//		 	if(request.isUserConfirmToAddNewDS()){
		 	if(response.isAfterMigrationDateSegmentExist()){	
//		 		if(currentOption == BusinessConstants.OPT_RENEW_DS){
		 			MigrationBusinessObjectBuilder builder = new MigrationBusinessObjectBuilder();
		 			String contractID = request.getLegacyContractVO().getContractId();
					List listForRenewDate =  builder.getContractRenewDate(contractID);
					
					LegacyContract legacyContract = (LegacyContract) searchResult
																			.get(searchResult.size()-1);
					currentOption = BusinessConstants.OPT_MIGRATE_DS;
					if(null != listForRenewDate 
						&& null != searchResult
						&& !listForRenewDate.isEmpty()
						&& !searchResult.isEmpty()) {
						
						java.util.Date renewDate = ((MigrationDateSegment) listForRenewDate
																			.get(0))
																			.getEffectiveDate();
//						LegacyContract legacyContract = (LegacyContract) searchResult
//																			.get(searchResult.size()-1);
						//currentOption = BusinessConstants.OPT_RENEW_DS;
						legacyContract.setEndDate(renewDate);
					}
					
				//a contract cannot have discontinuous date segments
					ContractBusinessObjectBuilder builder2 = new ContractBusinessObjectBuilder();
					minEffectiveOfLatestContract = builder2.minEffectiveOfLatestContract(contractID);
					if(null != minEffectiveOfLatestContract){
						if(isDateSegmentDiscontinuous(searchResult, 
														minEffectiveOfLatestContract, 
														currentOption,
														system,
														response)){
							response.setSuccess(false);
							return response;									  	
						}
/*						
						if(legacyContract.getEndDate().compareTo(minEffectiveOfLatestContract)!=0){
							//if dates are not same
							response.setSuccess(false);
							response.addMessage(new ErrorMessage("migration.contract.datesegment.discontinuous"));
							return response;							
						}
*/						
					}
					
		 		response.setOption(BusinessConstants.OPT_MIGRATE_DS);
		 		messageList.add(new InformationalMessage("migation.secondtime.for.contract"));
		 		if(builder2.isBaseContractInMarkForDeleteStatus(contractID)){
			 		messageList.add(new InformationalMessage("migation.secondtime.basecontract.markfordelete"));		 			
		 		}
		 		response.setMessages(messageList);
		 	}else{				
		 		response.setOption(currentOption);
		 	}
		  }else{
			response.setSuccess(false);
			return response;									  	
		  }
		} else if(!response.isAfterMigrationDateSegmentExist()){
//			String STEP1= "0";
//			LegacyBuilder legacyBuilder = new LegacyBuilder();
			LegacyContract legacyContract = copyValueObjectToLegacyContract(request);
			MigrationContract migrationContract = getMigrationContract(legacyContract);
//		 	MigrationDateSegment migrationDateSeg = getMigrationDateSeg(migrationContract);
//		 	String lastAccesedPage = migrationDateSeg.getLastAccessedPage();
//		 	if (lastAccesedPage.equals(STEP1)) {
//		 		List searchResult = legacyBuilder.retrieveAllDateSegments(legacyContract.getSystem(),legacyContract.getContractId(),LegacyBuilder.OPT_DS_BASIC_INFO);
//				response.setDateSegmentList(searchResult);
//		
//		 	}else {
				MigrationContractUtil.setMigrationContractSessionToRetrieveResponse(migrationContract,response);
//			}	
		}
		response.setSuccess(true);
		return response;
	}
	
	private boolean isDateSegmentDiscontinuous(List cp2000DateSegmentList, 
												Date endDate, 
												String option, 
												String system,
												RetrieveLegacyContractResponse response){
		boolean returnFlage = false;
		if(null != cp2000DateSegmentList && !cp2000DateSegmentList.isEmpty()){
//			cp2000DateSegmentList.
			CP2000Contract contract;
//			Date endDate = DateUtil.getDefaultEndDate();
			int numberOfDSNeedToValidate = 0;
			
			SimpleDateFormat dateFormat =new SimpleDateFormat("MM/dd/yyyy");
  			StringBuffer buffer = new StringBuffer();

			for (java.util.ListIterator cp2000DateSegmentListItr = cp2000DateSegmentList
																		.listIterator(cp2000DateSegmentList
																								.size());			
				cp2000DateSegmentListItr.hasPrevious(); ) 
			{
				contract =  (CP2000Contract) cp2000DateSegmentListItr.previous();				
				numberOfDSNeedToValidate++;				
				if(endDate.compareTo(contract.getEndDate())== 0 
						&& !(contract.getContractStat()
									.equals(BusinessConstants.STATUS_TRANSFERRED_TO_PRODUCTION)
									||contract.getContractStat()
									.equals(BusinessConstants.STATUS_MIGRATED ))){
					
					if(endDate.compareTo(contract.getEndDate())!= 0 &&
							response.isAfterMigrationDateSegmentExist() && 
							numberOfDSNeedToValidate == 1){
						response.addMessage(new ErrorMessage("migration.contract.datesegment.discontinuous"));
						/*
						 * There are missing Date Segments in ET-AutoBagging system 
						 * and hence cannot migrate rest of the date segments.
						 */
					}else{
						
						if(option.equals(BusinessConstants.OPT_MIGRATE_ALL_DS)
								||((option.equals(BusinessConstants.OPT_MIGRATE_LATEST_DS) 
										|| option.equals(BusinessConstants.OPT_RENEW_DS))
										&& numberOfDSNeedToValidate <= 1 )
								||(option.equals(BusinessConstants.OPT_MIGRATE_DS))
								){
							/*
							 * The Date Segment(s) having effective date {0} not in 'Transferred to Production' 
							 * status in {1} system.
							 * 
							 * first time migration the status of all the date segments should be in Transferred to Production
							 * And for second time migration it should be (Transferred to Production or Migrated ). 
							 * Date segment with any other status should be rejected
							 * 
							 */ 
			  				buffer.append(dateFormat.format(contract.getStartDate()));
			  				buffer.append(", ");
							returnFlage = true;
						}else{
							/*
							 * only possible if   
							 * selected  option is latest and ds other than latest are invalid
							 * or
							 * selected option is latest two and other than latest two are invalid.
							 *
							 * so if requirement will change. and validate only require for ds that user want to migrate.
							 * to implement comment the else
							 * 
							 *
							 * as per new requirement reply from Pravinth Kolangarethveetil 
							 * migration only if all date segments are in transferred to production status. 
							 * This is to maintain benefit gurantee and to make sure IMSN is in sync.
							 * 
							 *  
							 */ 
			  				buffer.append(dateFormat.format(contract.getStartDate()));
			  				buffer.append(", ");
							returnFlage = true;
						}//end if
						
					}//end else
					
				}	
				endDate = contract.getStartDate();
				
			}//end for
			if(buffer.length()!=0){
	  			buffer.deleteCharAt(buffer.length()-1);
	  			buffer.deleteCharAt(buffer.length()-1);
				ErrorMessage errorMessage =new ErrorMessage("migration.contract.datesegment.discontinuous.status");
				errorMessage.setParameters(new String[] { buffer.toString(), system });
//				errorMessage.setParameters(new String[] { system });
				response.addMessage(errorMessage);
			}				
			
		}else{
			return true;// if list is null //message no ds available
		}

		return returnFlage;//if validation success false will go
	}
	/**
	 * 
	 * @param migrationContract
	 * @return migrationDateSegment
	 * @throws SevereException
	 */
	/*private MigrationDateSegment getMigrationDateSeg(
			MigrationContract migrationContract) throws SevereException {
		MigrationDateSegment migrationDateSegment = null;
		List migrationDateSegments = new ArrayList();
		MigrationBusinessObjectBuilder migrationBusinessObjectBuilder = new MigrationBusinessObjectBuilder();
		if(0 != migrationContract.getLastAccessMigDateSegmentSysID()){
			migrationDateSegments = migrationBusinessObjectBuilder.getMigrationDateSegment(migrationContract);			
		}else{
			migrationDateSegments = migrationBusinessObjectBuilder.getMigrationDateSeg(migrationContract);
		}
		if (null != migrationDateSegments) {
			if (!migrationDateSegments.isEmpty()) {
				//FIXME list size
				return (MigrationDateSegment) migrationDateSegments.get(migrationDateSegments.size()-1);
			}
		}
		return migrationDateSegment;
	}*/
	/**
	 * 
	 * @param legacyContract
	 * @return migrationContract
	 * @throws SevereException
	 */
	private MigrationContract getMigrationContract(LegacyContract legacyContract)
			throws SevereException {
		MigrationContract migrationContract = null;
//		List migrationContracts = new ArrayList();
		MigrationBusinessObjectBuilder migrationBusinessObjectBuilder = new MigrationBusinessObjectBuilder();
		List migrationContracts = migrationBusinessObjectBuilder
				.getMigrationContract(legacyContract);
		if (null != migrationContracts) {
			if (!migrationContracts.isEmpty()) {
				return (MigrationContract) migrationContracts.get(0);
			}
		}
		return migrationContract;
	}
	/**
	 * 
	 * @param retrieveLegacyContractRequest
	 * @return legacyContract
	 */
	private LegacyContract copyValueObjectToLegacyContract(RetrieveLegacyContractRequest retrieveLegacyContractRequest) {
		
		LegacyContract legacyContract = new LegacyContract();
		LegacyContractVO legacyContractVO = retrieveLegacyContractRequest.getLegacyContractVO();
		legacyContract.setContractId(legacyContractVO.getContractId());
		legacyContract.setSystem(legacyContractVO.getSourceSystem());
		legacyContract.setCreatedUser(retrieveLegacyContractRequest.getUser().getUserId());
		legacyContract.setLastUpdatedUser(retrieveLegacyContractRequest.getUser().getUserId());
		legacyContract.setLastUpdatedTimestamp(new Date());
		return legacyContract;
	}
	
    /**
     * Method to set the list of messages to response.
     * 
     * @param response
     *            WPDResponse
     * @param messages
     *            List.
     */
    /*private void addMessagesToResponse(WPDResponse response, List messages) {
        if (null == messages || messages.size() == 0)
            return;
        if (null == response.getMessages())
            response.setMessages(messages);
        else
            response.getMessages().addAll(messages);
    }*/
	
	public WPDResponse execute(RetrieveLegacyContarctNotesRequest contractNotesRequest)
	throws ServiceException {
		Logger.logInfo("Entering execute method, request = "
				+ contractNotesRequest.getClass().getName());
		RetrieveLegacyContractNotesResponse contractNotesResponse = (RetrieveLegacyContractNotesResponse) WPDResponseFactory
			.getResponse(WPDResponseFactory.LEGACY_CONTRACT_NOTES_RESPONSE);
		LegacyBuilder legacyBuilder = new LegacyBuilder();
		List contractNotesList = null;
		List majorHeadingList = null;
		List minorHeadingList = null;
		List majorNotesList = null;
		List minorNotesList = null;
		try {
			
			if( contractNotesRequest.getAction() == RetrieveLegacyContarctNotesRequest
					.LEGACY_RETRIEVE_CONTRACT_NOTES){
				LegacyContractNotes legacyContractNotes = new LegacyContractNotes();
				legacyContractNotes.setContractId(contractNotesRequest.getLegacyContractNotesVO()
						.getContractId());
				legacyContractNotes.setStartDate(contractNotesRequest.getLegacyContractNotesVO()
						.getStartDate());
				legacyContractNotes.setUser(contractNotesRequest.getUser().getUserId());
				contractNotesList = legacyBuilder.getContractNotes(legacyContractNotes);
				contractNotesResponse.setContractNotes(contractNotesList);
				contractNotesResponse.setSuccess(true);
				
			}
			if (contractNotesRequest.getAction() == RetrieveLegacyContarctNotesRequest
					.LEGACY_RETRIEVE_MAJOR_HEADING){
				LegacyContractMajorHeading legacyMajorHeading = new LegacyContractMajorHeading();
				legacyMajorHeading.setContractId(contractNotesRequest.getLegacyContractNotesVO()
						.getContractId());
				legacyMajorHeading.setStartDate(contractNotesRequest.getLegacyContractNotesVO()
						.getStartDate());
				majorHeadingList = legacyBuilder.getContractMajorHeadings(legacyMajorHeading);
				contractNotesResponse.setMajorHeading(majorHeadingList);
				contractNotesResponse.setSuccess(true);
		
			}
			if(contractNotesRequest.getAction() == RetrieveLegacyContarctNotesRequest
					.LEGACY_RETRIEVE_MINOR_HEADING){
				LegacyContractMinorHeading legacyMinorHeading = new LegacyContractMinorHeading();
				legacyMinorHeading.setContractId(contractNotesRequest.getLegacyContractNotesVO()
						.getContractId());
				legacyMinorHeading.setStartDate(contractNotesRequest.getLegacyContractNotesVO()
					.getStartDate());
				legacyMinorHeading.setMajorHeadingId(contractNotesRequest.getLegacyContractNotesVO()
						.getMajorHeadingId());
				minorHeadingList = legacyBuilder.getContractMinorHeadings(legacyMinorHeading);
				contractNotesResponse.setMinorHeading(minorHeadingList);
				contractNotesResponse.setSuccess(true);
			}
			if(contractNotesRequest.getAction() == RetrieveLegacyContarctNotesRequest
					.LEGACY_RETRIEVE_MAJOR_NOTES){
				LegacyMajorNotes legacyMajorNotes = new LegacyMajorNotes();
				legacyMajorNotes.setContractId(contractNotesRequest.getLegacyContractNotesVO()
						.getContractId());
				legacyMajorNotes.setStartDate(contractNotesRequest.getLegacyContractNotesVO()
						.getStartDate());
				legacyMajorNotes.setMajorHeadingId(contractNotesRequest.getLegacyContractNotesVO()
						.getMajorHeadingId());
				majorNotesList = legacyBuilder.getContractMajorNotes(legacyMajorNotes);
				contractNotesResponse.setMajorNotes(majorNotesList);
				contractNotesResponse.setSuccess(true);
			}
			if(contractNotesRequest.getAction() == RetrieveLegacyContarctNotesRequest
					.LEGACY_RETRIEVE_MINOR_NOTES){
				LegacyMinorNotes legacyMinorNotes = new LegacyMinorNotes();
				legacyMinorNotes.setContractId(contractNotesRequest.getLegacyContractNotesVO()
						.getContractId());
				legacyMinorNotes.setStartDate(contractNotesRequest.getLegacyContractNotesVO()
						.getStartDate());
				legacyMinorNotes.setMinorHeadingId(contractNotesRequest.getLegacyContractNotesVO()
						.getMinorHeadingId());
				minorNotesList = legacyBuilder.getContractMinorNotes(legacyMinorNotes);
				contractNotesResponse.setMinorNotes(minorNotesList);
				contractNotesResponse.setSuccess(true);
			}
			if (contractNotesRequest.getAction() == RetrieveLegacyContarctNotesRequest
					.LEGACY_RETRIEVE_ALL_MAJOR_HEADING){
				LegacyContractMajorHeading legacyMajorHeading = new LegacyContractMajorHeading();
				legacyMajorHeading.setContractId(contractNotesRequest.getLegacyContractNotesVO()
						.getContractId());
				legacyMajorHeading.setStartDate(contractNotesRequest.getLegacyContractNotesVO()
						.getStartDate());
				majorHeadingList = legacyBuilder.getAllContrctMajorHeading(legacyMajorHeading);
				contractNotesResponse.setMajorHeading(majorHeadingList);
				contractNotesResponse.setSuccess(true);
		
			}
			if (contractNotesRequest.getAction() == RetrieveLegacyContarctNotesRequest
					.LEGACY_OLD_MINOR_HEADING){
				LegacyContractMinorHeading minorHeading = new LegacyContractMinorHeading();
				minorHeading.setMinorHeadingId(contractNotesRequest.getLegacyContractNotesVO()
						.getMinorHeadingId());
				minorHeading.setContractId(contractNotesRequest.getLegacyContractNotesVO().getContractId());
				minorHeading.setStartDate(contractNotesRequest.getLegacyContractNotesVO().getStartDate());
				minorHeadingList = legacyBuilder.getOldMinorHeadings(minorHeading);
				List list = null;
				LegacyContractMinorHeading contractMinorHeading;
				if(null!=minorHeadingList){
					list = new ArrayList(minorHeadingList.size());
					Iterator iterator = minorHeadingList.iterator();
					while(iterator.hasNext()){
						 minorHeading = (LegacyContractMinorHeading)iterator.next();
						 contractMinorHeading = new LegacyContractMinorHeading();
						 contractMinorHeading.setMinorHeadingId(minorHeading.getMinorHeadingId());
						 contractMinorHeading.setMinorHeadingDesc(minorHeading.getMinorHeadingDesc());
						 list.add(contractMinorHeading);
					}
				}
				
				contractNotesResponse.setMinorHeading(list);
				contractNotesResponse.setSuccess(true);
		
			}
		}
		catch(SevereException e){
			
			Logger.logInfo(e);
		}
		Logger.logInfo("Returning execute method, request = "
				+ contractNotesRequest.getClass().getName());
	 return contractNotesResponse;
	}
}