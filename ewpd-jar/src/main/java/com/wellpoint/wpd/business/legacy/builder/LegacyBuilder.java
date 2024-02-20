/*
 * LegacyBuilder.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.legacy.builder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.framework.bo.AuditFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.legacy.adapter.LegacyAdapterManager;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.legacycontract.bo.CP2000Contract;
import com.wellpoint.wpd.common.legacycontract.bo.CP2000MigratedDateSegment;
import com.wellpoint.wpd.common.legacycontract.bo.CP2000Variable;
import com.wellpoint.wpd.common.legacycontract.bo.ContractLock;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyContract;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyContractMajorHeading;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyContractMinorHeading;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyContractNotes;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyMajorNotes;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyMinorNotes;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyObject;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyVariable;
import com.wellpoint.wpd.common.legacycontract.bo.MinorHeading;
import com.wellpoint.wpd.common.legacycontract.vo.LegacyVariableMappingVO;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id:
 */
public class LegacyBuilder {
	/**
	 * Options for retrieving date segments. 
	 * OPT_DS_BASIC_INFO - For this option retrieved date segment will have only the
	 * 					   basic information which is part of LegacyContract object.
	 * OPT_DS_DETAIL_INFO - For this option retrieved date segment will have the basic
	 * 						information and the associated informations incorporated in
	 * 						Contract object. ie Pricing Information, Coded Variables and values,
	 * 						Service class Exclusions, Limit Class Exclusions and 
	 * 						Service code Exclusions.
	 */
	public static final int OPT_DS_BASIC_INFO = 1;
	public static final int OPT_DS_DETAIL_INFO = 2;

	/**
	 * Different options that could be provided while searching for variables.
	 * OPT_DS_CODED_VARIABLES -  Returns Only the coded variables for the given contract.
	 * OPT_MAJR_HDNG_VARIABLES - Returns Variables which is associated with the given Major heading.
	 * OPT_MINR_HDNG_VARIABLES - Returns Variables which is associated with the given Minor heading.
	 * OPT_MAJR_MINR_VARIABLES - Returns Variables which is associated with all minor headings of the
	 * 							 given Major heading.
	 * OPT_STRUCTURE_VARIABLES - Returns variables associated with the given Structure.
	 */
	public static final int OPT_DS_CODED_VARIABLES = 11;
	public static final int OPT_MAJR_HDNG_VARIABLES = 12;
	public static final int OPT_MINR_HDNG_VARIABLES = 13;
	public static final int OPT_MAJR_MINR_VARIABLES = 14;
	public static final int OPT_STRUCTURE_VARIABLES = 15;
 
	
	/**
	 * Different migration options
	 */
	public static final int OPT_MIG_LATEST_DS = 21;
	public static final int OPT_MIGRATION = 22;
	public static final int OPT_MIG_ALL_DS = 23;
	public static final int OPT_MIG_RENEW_DS = 24;
	
	public static final String STATUS_SCHEDULE_TO_TEST = BusinessConstants.STATUS_SCHEDULE_TO_TEST;//DB Status
	public static final String STATUS_TRANSFERRED_TO_PRODUCTION = BusinessConstants.STATUS_TRANSFERRED_TO_PRODUCTION;//DB Status
	public static final String STATUS_MIGRATION_IN_PROGRESS = BusinessConstants.STATUS_MIGRATION_IN_PROGRESS;//DB Status
	private static final int DS_ROW_STATUS_DELETED = 2;
//	private static final int DS_ROW_STATUS_DELETED_LATEST = 2;
	private static final int DS_ROW_STATUS_DELETED_RENEW_LATEST = 3;
	private static final String STATUS_DELETED = BusinessConstants.STATUS_MARKED_FOR_DELETION;//DB status
	private static final String MIGRATED = BusinessConstants.STATUS_MIGRATED;//DB Status
	private static final String RENEWED_IN_ETAB=BusinessConstants.STATUS_RENEWED_IN_ETAB;//DB status
	/**
	 * Adapter manager for CP2000 Database connection.
	 */
	private static LegacyAdapterManager adapterManager=getCP2000AdapterManager();
	private Audit audit;
	
	/**
	 * Method to to check whether the given contract is locked in the 
	 * source system.
	 * @param system		Specifies source system. CP or CP2000
	 * @param contractId	Legacy contract Id.
	 * @return				true if contract is locked, false otherwise.
	 * @throws SevereException
	 */
	public boolean isContractLocked(String system, String contractId) throws SevereException{
		validateSystem(system);
		if(LegacyObject.SYSTEM_CP2000.equals(system)) {
			return getCP2000AdapterManager().isContractLocked(contractId);
		}else if(LegacyObject.SYSTEM_CP.equals(system)) {
			
		}
		return false;
	}
	/**
	 * Method to to check whether the given contract is locked in the 
	 * source system.
	 * @param system
	 * @param contractId
	 * @param adapterServicesAccess
	 * @return
	 * @throws SevereException
	 */
	public boolean isContractLocked(String system, String contractId, AdapterServicesAccess adapterServicesAccess) throws SevereException{
		validateSystem(system);
		if(LegacyObject.SYSTEM_CP2000.equals(system)) {
			return getCP2000AdapterManager().isContractLocked(contractId, adapterServicesAccess);
		}else if(LegacyObject.SYSTEM_CP.equals(system)) {
			
		}
		return false;
	}
	
	/**
	 * Method to to check whether the given contract is locked By same user in the 
	 * source system.
	 * @param system		Specifies source system. CP or CP2000
	 * @param contractId	Legacy contract Id.
	 * @param user			Source system user.
	 * @return				true if contract is locked, false otherwise.
	 * @throws SevereException
	 */
	public boolean isContractLockedBySameUser(String system, String contractId, String user) throws SevereException{
		validateSystem(system);
		if(LegacyObject.SYSTEM_CP2000.equals(system)) {
			return getCP2000AdapterManager().isContractLockedBySameUser(contractId, user);
		}else if(LegacyObject.SYSTEM_CP.equals(system)) {
			
		}
		return false;
	}
		
	/**
	 * Method to lock the Given contract in the Source system.
	 * @param system		Specifies source system. CP or CP2000	
	 * @param contractId	Legacy contract Id which is to be locked.
	 * @param userId		Currently operating user.
	 * @throws SevereException
	 */
	public void lockContract(String system, String contractId, String userId) throws SevereException {
		validateSystem(system);
		if(! isContractExists(system, contractId, STATUS_TRANSFERRED_TO_PRODUCTION))
			throw new IllegalArgumentException("Given contract id =" + contractId + " does not exist in source system");
		if(isContractLocked(system, contractId))
			throw new IllegalStateException("Contract already locked");
		if(LegacyObject.SYSTEM_CP2000.equals(system)) {
			getCP2000AdapterManager().persistLock(contractId, getMigrationUserId(userId), userId);
		}else if(LegacyObject.SYSTEM_CP.equals(system)) {

		}
	}
	/**
	 * 
	 * @param contract
	 * @throws SevereException
	 */
	public void lockAndChangeContractStatus(List legacyContractList, String option, AdapterServicesAccess serviceAccessCP2000) throws SevereException {
		LegacyContract contract =(LegacyContract) legacyContractList.get(legacyContractList.size() - 1);
		String system = contract.getSystem();
		String contractId = contract.getContractId();
		String userId = contract.getCreatedUser();	
	    String contractStatusBefore = STATUS_TRANSFERRED_TO_PRODUCTION;
	    String contractStatusAfter = STATUS_MIGRATION_IN_PROGRESS;			    	

		validateSystem(system);
		if(! isContractExists(system, contractId, contractStatusBefore, serviceAccessCP2000))
			throw new IllegalArgumentException("Given contract id =" + contractId + " does not exist in source system");
		if(isContractLocked(system, contractId, serviceAccessCP2000))
			throw new IllegalStateException("Contract already locked");
		if(LegacyObject.SYSTEM_CP2000.equals(system)) {
			String methodName = "lockAndChangeContractStatus(LegacyContract contract)";
/*			
			AdapterServicesAccess serviceAccessCP2000 = AdapterUtil.getAdapterServiceForCP2000();
			long transactionId = AdapterUtil.getTransactionId();
			try{
			    AdapterUtil.beginTransaction(serviceAccessCP2000);
			    AdapterUtil.logBeginTranstn(transactionId , this ,methodName);
*/
				LegacyBuilder.getCP2000AdapterManager().persistLock(contractId, getMigrationUserId(userId), userId, serviceAccessCP2000);
				this.changeContractStatus(legacyContractList, option,contractStatusAfter, contractStatusBefore,serviceAccessCP2000);
/*				
				AdapterUtil.endTransaction(serviceAccessCP2000);
				AdapterUtil.logTheEndTransaction(transactionId , this ,methodName);
				
			}catch (SevereException ex) {
			    AdapterUtil.abortTransaction(serviceAccessCP2000);
			    AdapterUtil.logAbortTxn(transactionId , this ,methodName);
		        List errorParams = new ArrayList();
		        String obj = ex.getClass().getName();
		        errorParams.add(obj);
		        errorParams.add(obj.getClass().getName());
		        throw new SevereException(
		                "Exception occured in persist method , for persisting the BusinessObject ContractLock, CP2000ContractUpdate, in LegacyBuilder.java",
		                errorParams, ex);
		    }catch(AdapterException ex){
		        AdapterUtil.abortTransaction(serviceAccessCP2000);
		        AdapterUtil.logAbortTxn(transactionId , this ,methodName);
		        List errorParams = new ArrayList();
		        String obj = ex.getClass().getName();
		        errorParams.add(obj);
		        errorParams.add(obj.getClass().getName());
		        throw new SevereException(
		                "Exception occured in persist method , for persisting the BusinessObject ContractLock, CP2000ContractUpdate, in LegacyBuilder.java",
		                errorParams, ex);
		    } catch (Exception e){
		        AdapterUtil.abortTransaction(serviceAccessCP2000);
		        AdapterUtil.logAbortTxn(transactionId , this ,methodName);
		        throw new SevereException("Unhandled exception is caught",null,e);
		    }			
*/				
		}else if(LegacyObject.SYSTEM_CP.equals(system)) {

		}
	}
	
	public void changeContractStatus( List legacyContractList , String soption, String contractStatus, String contractStatusForSC, AdapterServicesAccess transaction) throws SevereException{
		LegacyContract contract =(LegacyContract) legacyContractList.get(legacyContractList.size() - 1);
		validateSystem(contract.getSystem());
		LegacyAdapterManager adapterManager = getCP2000AdapterManager();
		int option ;
		if(soption.equals(BusinessConstants.OPT_RENEW_DS)){
			// Renew Date Segment Option
			option = LegacyBuilder.OPT_MIG_RENEW_DS;
		} else if(soption.equals(BusinessConstants.OPT_MIGRATE_DS)){
			option = LegacyBuilder.OPT_MIGRATION;
		} else {
			throw new IllegalArgumentException("Invalid option found");
		}
		
		if( option != OPT_MIGRATION
				&& option != OPT_MIG_RENEW_DS)
			throw new IllegalArgumentException("invalid option");

		String contractId = contract.getContractId();
//		String userId = contract.getLastUpdatedUser();
//		if(null==userId) userId=contract.getCreatedUser();
		String userId = contract.getCreatedUser();
		Date date = getTime();
		CP2000Contract tempDS;
		
		switch (option) {
		case OPT_MIG_RENEW_DS:
			// Deleting latest ds
			tempDS = adapterManager.getLatestDS(contractId, contractStatusForSC);
			tempDS.setContractStat(contractStatus);//Status  
			if(contractStatus.equals(STATUS_MIGRATION_IN_PROGRESS)){
				tempDS.setRowStatus(2);
			}else{
				tempDS.setRowStatus(1);
			}
			tempDS.setLastUpdatedUser(userId);
			tempDS.setLastUpdatedTimestamp(date);
			AdapterUtil.performUpdate(tempDS, userId, transaction);

			break;
		
		case OPT_MIGRATION:
			Iterator dtItr = legacyContractList.iterator();
			while(dtItr.hasNext()){
				LegacyContract contractDs = (LegacyContract)dtItr.next();
				String contractIdDs  = contractDs.getContractId();
				Date daStartDate = contractDs.getStartDate();
				tempDS = adapterManager.getDS(contractIdDs,daStartDate,contractStatusForSC);
				tempDS.setContractStat(contractStatus);//Status  
				tempDS.setLastUpdatedUser(userId);
				if(contractStatus.equals(STATUS_MIGRATION_IN_PROGRESS)){
					tempDS.setRowStatus(2);
				}else{
					tempDS.setRowStatus(1);
				}
				tempDS.setLastUpdatedTimestamp(date);
				AdapterUtil.performUpdate(tempDS, userId, transaction);
			}

			break;
		default:
			throw new IllegalArgumentException("Given option is not valid");
		}		
	}
	
	/**
	 * Modified user Id inorder to block the same user working on the
	 * contract using Legacy application.
	 * 
	 * @param userId
	 * @return String
	 */
	private String getMigrationUserId(String userId) {
		if(userId == null)
			return null;
		userId = userId + "@";
		return userId;
	}
	/**
	 * Method to unlock a contract from legacy system.
	 * @param system		Specifies source system. CP or CP2000	
	 * @param contractId	Legacy contract Id which is to be unLocked.
	 * @param userId		Currently operating user.
	 * @throws SevereException
	 */
	public void unLockContract(String system, String contractId, String userId) throws SevereException {
		validateSystem(system);
		if(! isContractLocked(system, contractId))
			throw new IllegalStateException("The requested contract for unlocking is not locked");
		ContractLock lock = getCP2000AdapterManager().retrieveLock(contractId );
		if(userId == null || !getMigrationUserId(userId).equalsIgnoreCase(lock.getLockedUser())){
			Logger.logError("The contract -" + contractId + " is locked by the user - " + lock.getLockedUser() + 
							" and cannot be unlocked by the user - "+ userId);
			throw new IllegalStateException("Only locked user can unlock the contract");
		}
		getCP2000AdapterManager().removeLock(contractId, userId);
	}
	/*
	 * @cancel migration
	 */
	public void unLockAndChangeContractStatus(LegacyContract contract, String option) throws SevereException {
		String system = contract.getSystem();
		String contractId = contract.getContractId();
		String userId = contract.getCreatedUser();	
			
		String methodName = "unLockAndChangeContractStatus(LegacyContract contract)";
		AdapterServicesAccess serviceAccessCP2000 = AdapterUtil.getAdapterServiceForCP2000();
		long transactionId = AdapterUtil.getTransactionId();
		try{
		    AdapterUtil.beginTransaction(serviceAccessCP2000);
		    AdapterUtil.logBeginTranstn(transactionId , this ,methodName);
		    
		    String contractStatusBefore = STATUS_MIGRATION_IN_PROGRESS;
		    String contractStatusAfter = STATUS_TRANSFERRED_TO_PRODUCTION;			    	

			this.unLockContract(system, contractId, userId,  serviceAccessCP2000);
			List legacyContractList = adapterManager.getAllDS(contractId, contractStatusBefore);
			this.changeContractStatus(legacyContractList, option, contractStatusAfter, contractStatusBefore, serviceAccessCP2000);
			
			AdapterUtil.endTransaction(serviceAccessCP2000);
			AdapterUtil.logTheEndTransaction(transactionId , this ,methodName);
		}catch (SevereException ex) {
		    AdapterUtil.abortTransaction(serviceAccessCP2000);
		    AdapterUtil.logAbortTxn(transactionId , this ,methodName);
	        List errorParams = new ArrayList(3);
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                "Exception occured in persist method , for persisting the BusinessObject ContractLock, CP2000ContractUpdate, in LegacyBuilder.java",
	                errorParams, ex);
/*		    }catch(AdapterException ex){
	        AdapterUtil.abortTransaction(serviceAccessCP2000);
	        AdapterUtil.logAbortTxn(transactionId , this ,methodName);
	        List errorParams = new ArrayList();
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                "Exception occured in persist method , for persisting the BusinessObject ContractLock, CP2000ContractUpdate, in LegacyBuilder.java",
	                errorParams, ex);
*/		    } catch (Exception e){
	        AdapterUtil.abortTransaction(serviceAccessCP2000);
	        AdapterUtil.logAbortTxn(transactionId , this ,methodName);
	        throw new SevereException("Unhandled exception is caught",null,e);
	    }			
		
	}

	/**
	 * 
	 * @param system
	 * @param contractId
	 * @return return true if contract with provied contractID in given system
	 * having staus as ScheduledToTest 
	 * @throws SevereException
	 */
	public boolean isContractScheduledToTest(String system, String contractId) throws SevereException{
		validateSystem(system);
		return getCP2000AdapterManager().isContractScheduleToTest(contractId);
	}
	
	public boolean isContractTransferredToProduction(String system, String contractId) throws SevereException{
		validateSystem(system);
		return getCP2000AdapterManager().isContractTransferredToProduction(contractId);
	}
	
	/**
	 * 
	 * @param system
	 * @param contractId
	 * @return return true if contract with provied contractID in given system
	 * having staus as ScheduledToproduction 
	 * @throws SevereException
	 */
	public boolean isContractScheduledToProduction(String system, String contractId) throws SevereException{
		validateSystem(system);
		return getCP2000AdapterManager().isContractScheduledToProduction(contractId);
	}
	/**
	 * 
	 * @param system
	 * @param contractId
	 * @return return true if contract with provied contractID contain benefit structure
	 * @throws SevereException
	 */
	public boolean isBenefitStructureAssociated(String system, String contractId) throws SevereException{
		validateSystem(system);
		return getCP2000AdapterManager().isBenefitStructureAssociated(contractId);
	}	
	/**
	 * unlock the locked contract by the same user who created it.
	 * @param system
	 * @param contractId
	 * @param userId
	 * @param txn
	 * @throws SevereException
	 */
	public void unLockContract(String system, String contractId, String userId, AdapterServicesAccess txn) throws SevereException {
		validateSystem(system);
		if(! isContractLocked(system, contractId))
			throw new IllegalStateException("The requested contract for unlocking is not locked");
		ContractLock lock = getCP2000AdapterManager().retrieveLock(contractId);
		if(userId == null || !getMigrationUserId(userId).equalsIgnoreCase(lock.getLockedUser())){
			Logger.logError("The contract -" + contractId + " is locked by the user - " + lock.getLockedUser() + 
							" and cannot be unlocked by the user - "+ userId);
			throw new IllegalStateException("Only locked user can unlock the contract");
		}
		getCP2000AdapterManager().removeLock(contractId, userId,txn);
	}

	/**
	 * Method checks whether given contract Id exist in source system.
	 * return true if exist and false otherwise.
	 * @param system		Specifies source system. CP or CP2000
	 * @param contractId	Legacy contract Id.
	 * @return				True if exist, false otherwise.
	 * @throws SevereException
	 */
	public boolean isContractExists(String system, String contractId, String contractStatus) throws SevereException{
		validateSystem(system);
		return getCP2000AdapterManager().isContractIdValid(contractId, contractStatus);
	}
	/**
	 * Method checks whether given contract Id exist in source system.
	 * return true if exist and false otherwise.
	 * 
	 * @param system
	 * @param contractId
	 * @param contractStatus
	 * @param adapterServicesAccess
	 * @return
	 * @throws SevereException
	 */
	public boolean isContractExists(String system, String contractId, String contractStatus, AdapterServicesAccess adapterServicesAccess) throws SevereException{
		validateSystem(system);
		return getCP2000AdapterManager().isContractIdValid(contractId, contractStatus, adapterServicesAccess);
	}
	
	/**
	 * Method will retrieve all datesegments having the given contract id. 
	 * The kind of returned will depend on the option provided.
	 * if option - OPT_DS_BASIC_INFO  -> Each Contract object will have basic information only.
	 * 	 		 - OPT_DS_DETAIL_INFO -> Each Contract object will have full associated informations
	 * 									 ie Pricing, CodedVariables, ServiceCodeExclusions, 
	 * 										LimitClassExclusions, ServiceCodeExclusions.
	 * @param system		Source System - valid values are 
	 * 										LegacyObject.SYSTEM_CP2000 &
	 * 										LegacyObject.SYSTEM_CP
	 * @param contractId	ContractId of Legacy Contract.
	 * @param option		Valid values are OPT_DS_BASIC_INFO & OPT_DS_DETAIL_INFO.
	 * @return
	 * @throws SevereException
	 */
	public List retrieveAllDateSegments(String system, String contractId, int option, String contractStatus) throws SevereException{
		validateSystem(system);
//		List contractsList = new ArrayList();
		if(option != OPT_DS_DETAIL_INFO && option != OPT_DS_BASIC_INFO)
			throw new IllegalArgumentException("invalid option");
		if(LegacyObject.SYSTEM_CP2000.equals(system)) {
			List contractsList = getCP2000AdapterManager().getContracts(contractId, contractStatus);
			if(OPT_DS_DETAIL_INFO == option) {
				LegacyContract contract;
				for (Iterator iterator = contractsList.iterator(); iterator
						.hasNext();) {
					contract = (LegacyContract) iterator.next();
					updateContractDetails(contract);
				}
			}
			return contractsList;
		}
		return null;
	}
	
	/**
	 * Method will retrieve a single Contract having the given contract id and start date. 
	 * The kind of returned will depend on the option provided.
	 * if option - OPT_DS_BASIC_INFO  -> Each Contract object will have basic information only.
	 * 	 		 - OPT_DS_DETAIL_INFO -> Each Contract object will have full associated informations
	 * 									 ie Pricing, CodedVariables, ServiceCodeExclusions, 
	 * 										LimitClassExclusions, ServiceCodeExclusions. 
	 * @param contract		Contract Object. Source system, contract Id and Start date should be 
	 * 						specified in this object.
	 * @param option		Valid values are OPT_DS_BASIC_INFO & OPT_DS_DETAIL_INFO.
	 * @return				Retrieved LegacyContract object.
	 * @throws SevereException
	 */
	public LegacyContract retrieveDateSegment(LegacyContract contract, int option, String contractStatus) throws SevereException {
		validateSystem(contract.getSystem());
		if(option != OPT_DS_DETAIL_INFO && option != OPT_DS_BASIC_INFO)
			throw new IllegalArgumentException("invalid option");

		if(LegacyObject.SYSTEM_CP2000.equals(contract.getSystem())) {
			LegacyContract CP2000contract = new CP2000Contract(contract);
			CP2000contract.setContractStat(contractStatus);
			CP2000contract = (CP2000Contract)AdapterUtil.performRetrieve(CP2000contract);
			if(CP2000contract != null && option == OPT_DS_DETAIL_INFO) {
				updateContractDetails(CP2000contract);
			}
			return CP2000contract;
		}else if(LegacyObject.SYSTEM_CP.equals(contract.getSystem())) {
			
		}
		return null;
	}
	/**
	 * 
	 * @param contractId
	 * @param startDate
	 * @return
	 * @throws SevereException
	 */
	public String getBenefitYearAccumIndicator (String contractId, Date startDate) throws SevereException{
	    return getCP2000AdapterManager().getBenefitYearAccumIndicator(contractId, startDate);
	}
	/**
	 * 
	 * @param system
	 * @param contractId
	 * @return latest datesegment from legacy with provied contractID in given system
	 * @throws SevereException
	 */
	public LegacyContract getLatestDateSegment(String system, String contractId) throws SevereException{
		validateSystem(system);
		return getCP2000AdapterManager().getLatestDS(contractId, "%");
	}
	/**
	 * 
	 * @param system
	 * @param contractId
	 * @return first datesegment from legacy with provied contractID in given system
	 * @throws SevereException
	 */
	public LegacyContract getFirstDateSegment(String system, String contractId) throws SevereException{
		validateSystem(system);
		return getCP2000AdapterManager().getFirstDS(contractId);
	}
	/**
	 * to retrive adapter access services 
	 * @param system
	 * @return instance of adapterServicesAccess
	 */
	public AdapterServicesAccess getAdapterServiceAccess(String system) {
		validateSystem(system);
		AdapterServicesAccess adapterServicesAccess = null;
		if(LegacyObject.SYSTEM_CP2000.equals(system)) {
			adapterServicesAccess = AdapterUtil.getAdapterServiceForCP2000();
		} else if(LegacyObject.SYSTEM_CP2000.equals(system)){
			
		}
		return adapterServicesAccess;
	}
	/**
	 * changing status as logical delete for legacy datesegment(s) who successfully migrated to ewpd   
	 * changing legacy contract status to schedule to test
	 * unlocak the legacy contract 
	 * @param contract
	 * @param ewpdEffectiveDate
	 * @param transaction
	 * @throws SevereException
	 */
	public void finalizeMigrationForRenewal(LegacyContract contract, Date ewpdEffectiveDate, AdapterServicesAccess transaction) throws SevereException {
		String contractId = contract.getContractId();
		String userId = contract.getLastUpdatedUser();
		Date date = getTime();
		CP2000Contract tempDS;
		
	    String contractStatusBefore = STATUS_MIGRATION_IN_PROGRESS;
//	    String contractStatusAfter = RENEWED_IN_ETAB;		
//	    String contractStatusAfter = STATUS_TRANSFERRED_TO_PRODUCTION;		
	    String contractStatusAfter = BusinessConstants.STATUS_BEING_MODIFIED;
	    
		tempDS = adapterManager.getLatestDS(contractId, contractStatusBefore);
		tempDS.setContractStat(contractStatusAfter);//Status  after Migration 
		tempDS.setRowStatus(DS_ROW_STATUS_DELETED_RENEW_LATEST);//Row Status =3 for Migrate latest Date Segment
		tempDS.setLastUpdatedUser(userId);
		tempDS.setLastUpdatedTimestamp(date);
		tempDS.setEndDate(ewpdEffectiveDate);
		AdapterUtil.performUpdate(tempDS,userId,transaction);
		// Scheduling contract
//		scheduleContractToTest(contract, transaction);
		unLockContract(contract.getSystem(), contractId, userId, transaction);
		addContractToDataFeedSyncTable(contract,userId,transaction);
	}
	
	/**
	 * changing status as logical delete for legacy datesegment(s) who successfully migrated to ewpd
	 * changing legacy contract status to schedule to test
	 * unlocak the legacy contract
	 * @param contract
	 * @param option
	 *  Available options are
	 *  	OPT_MIG_LATEST_DS
	 *  	OPT_MIG_ALL_DS
	 *  	OPT_MIG_LATEST_TWO_DS
	 * @param transaction
	 * @throws SevereException
	 */
	public void finalizeMigrationForDateSegments(LegacyContract contract, int option, AdapterServicesAccess transaction) throws SevereException {
		validateSystem(contract.getSystem());
		LegacyAdapterManager adapterManager = getCP2000AdapterManager();
		if( option != OPT_MIGRATION)
			throw new IllegalArgumentException("invalid option");

		String contractId = contract.getContractId();
		String userId = contract.getLastUpdatedUser();
		Date date = getTime();
		CP2000Contract tempDS;
		
	    String contractStatusBefore = STATUS_MIGRATION_IN_PROGRESS;
	    String contractStatusAfter = MIGRATED;
	    
			// Deleting latest ds
			List listDs = adapterManager.getAllDS(contractId, contractStatusBefore);
			Iterator itrDs = listDs.iterator();
			while(itrDs.hasNext()){
				tempDS =(CP2000Contract)itrDs.next();
				tempDS.setContractStat(contractStatusAfter);//Status  after Migration 
				tempDS.setRowStatus(DS_ROW_STATUS_DELETED);//Row Status =2
				tempDS.setLastUpdatedUser(userId);
				tempDS.setLastUpdatedTimestamp(date);
				AdapterUtil.performUpdate(tempDS, userId, transaction);
				
			}
			
			// Scheduling contract
//			scheduleContractToTest(contract, transaction);
			// Unlocking contract
			unLockContract(contract.getSystem(), contractId, userId, transaction);
			List dateSegmentsLeft = adapterManager.getLeftOverDS(contractId);
			if(null == dateSegmentsLeft || dateSegmentsLeft.size()==0){
				removeEntryFromDataFeedSyncTable(contractId,userId,transaction);
			}
			
	}
	/**
	 * 
	 * @param contract
	 * @param txn
	 * @throws SevereException
	 */
	public void scheduleContractToTest(LegacyContract contract, AdapterServicesAccess txn) throws SevereException{
		validateSystem(contract.getSystem());
		if(LegacyObject.SYSTEM_CP2000.equals(contract.getSystem())) {
			contract.setContractStat(STATUS_SCHEDULE_TO_TEST);
			contract.setLastUpdatedTimestamp(getTime());
			adapterManager.scheduleContractToTest(contract, txn);
		}
	}
	
	
	
	/**
	 * Method to retrieve All major headings associated with a Benefit Structure.
	 * @param system		Specifies source system. SYSTEM_CP or SYSTEM_CP2000
	 * @param structureId	Legacy benefit strucute Id.
	 * @return				Associated major heading list
	 * @throws SevereException
	 */
	public List getMajorHeadings(String system,String structureId, String majorHeadingText) throws SevereException{
		validateSystem(system);
		return getCP2000AdapterManager().getMajorHeadings(structureId, majorHeadingText);
	}

	/**
	 * Method to retrieve the Minor headings associated with a Major heading.
	 * @param system			Specifies source system. SYSTEM_CP or SYSTEM_CP2000.
	 * @param majorHeading		Id of legacy major heading.
	 * @return
	 * @throws SevereException
	 */
	public List getMinorHeadings(String system, String majorHeading) throws SevereException{
		validateSystem(system);
		return null;
	}

	/**
	 * Method to retrieve the variables associated with the criteria specified in the
	 * variable object.
	 * @param system	Specifies source system. SYSTEM_CP or SYSTEM_CP2000.
	 * @param option	More details are specified at top of the builder class.
	 * @param variable	This object should have the criteria for the search.
	 * 					The following attributes should be specified in order to perform search.
	 * 					contractId, startDate, minorHeadingId,
	 * 					format(The data type of benefit Line), providerArrangement.
	 * @return			List of variables.
	 * @throws SevereException
	 */
	public List getVariables(String system, int option, LegacyVariable variable) throws SevereException{
		validateSystem(system);
		switch(option) {
		case OPT_MINR_HDNG_VARIABLES: 
			return getCP2000AdapterManager().getVariables(variable);
		default :
			throw new IllegalArgumentException("The given option is not valid");
		}
		
	}	
	/**
	 * 
	 * @param variableMappingVO
	 * @return
	 * @throws WPDException
	 */
	public List getStructureVariables(LegacyVariableMappingVO variableMappingVO)throws WPDException{
		return null;
	}
	/**
	 * 
	 * @return
	 */
    private static LegacyAdapterManager getCP2000AdapterManager(){
    	if(LegacyBuilder.adapterManager == null)
    		LegacyBuilder.adapterManager = new LegacyAdapterManager();
    	return LegacyBuilder.adapterManager;
    }
    /**
     * 
     * @param contract
     * @throws SevereException
     */
    private void updateContractDetails(LegacyContract contract) throws SevereException {
		contract.setCodedVariables(getCP2000AdapterManager().getCodedVariables(contract));
		contract.setLimitClassExclusions(getCP2000AdapterManager().getLimitClassExclusions(contract));
		contract.setServiceClassExclusions(getCP2000AdapterManager().getServiceClassExclusions(contract));
		contract.setServiceCodeExclusions(getCP2000AdapterManager().getServiceCodeExclusions(contract));
		contract.setPricing(getCP2000AdapterManager().getContractPricing(contract));
		contract.setSpecialityCodeExclusions(getCP2000AdapterManager().getSpecialityCodeExclns(contract));
	}
    /**
     * validate system 
     * @param system
     */
	private void validateSystem(String system){
		if( !LegacyObject.SYSTEM_CP.equals(system) && !LegacyObject.SYSTEM_CP2000.equals(system)) {
			Logger.logError("The value give for source system = "+ system + " is not a valid source system\n"+
							"The Valid systems are LegacyObject.SYSTEM_CP, LegacyObject.SYSTEM_CP2000 ");
			throw new IllegalArgumentException("Provided system is not valid");
		}		
	}
	/**
	 * to retrive all minor heading in provided system
	 * @param system
	 * @param minorHeading
	 * @return list with CP2000MinorHeading type element(s)
	 * @throws SevereException
	 */
	public List getMinorHeadings(String system, MinorHeading minorHeading ) throws SevereException {
		return getCP2000AdapterManager().getMinorHeadings(minorHeading.getMajorHeadingId(), minorHeading.getMinorHeadingText());
	}
	
	/**
	 * Method to get the current time using audit factory.
	 * @return	current time.
	 */
	private Date getTime() {
		if(audit == null) {
			AuditFactory auditFactory = (AuditFactory)ObjectFactory.getFactory(ObjectFactory.AUDIT);
			audit = auditFactory.getAudit();
		}
		return audit.getTime();
	}
	
    public void addContractToDataFeedSyncTable(LegacyContract contract, String user, AdapterServicesAccess txn) throws SevereException {
    	final String TEST_IND = "T";
    	final String PROD_IND = "P";
    	
    	CP2000MigratedDateSegment migratedDateSegment = new CP2000MigratedDateSegment();
    	migratedDateSegment.setContractId(contract.getContractId());
    	migratedDateSegment.setStartDate(contract.getStartDate());
    	migratedDateSegment.setCreatedUser(user);
    	migratedDateSegment.setLastUpdatedUser(user);
    	migratedDateSegment.setCreatedTimestamp(getTime());
    	migratedDateSegment.setLastUpdatedTimestamp(getTime());
    	migratedDateSegment.setProductionIndicator(TEST_IND);
    	AdapterUtil.performInsert(migratedDateSegment, user, txn);
    	migratedDateSegment.setProductionIndicator(PROD_IND);
    	AdapterUtil.performInsert(migratedDateSegment, user, txn);
    }
    
    public void removeEntryFromDataFeedSyncTable(String contractId, String user, AdapterServicesAccess txn) throws SevereException {
    	CP2000MigratedDateSegment migratedDateSegment = new CP2000MigratedDateSegment();
    	migratedDateSegment.setContractId(contractId);

    	// Setting some values to pass adapter key validation.
    	migratedDateSegment.setStartDate(new Date());
    	migratedDateSegment.setProductionIndicator(" ");
    	
    	AdapterUtil.performRemove(migratedDateSegment,user, txn);
    }

    public String getMajorHeadingNote(String contractId, Date startDate, String majorHeadingId) throws SevereException {
    	LegacyMajorNotes legacyMajorNote = new LegacyMajorNotes();
    	legacyMajorNote.setContractId(contractId);
    	legacyMajorNote.setStartDate(getStringDate(startDate));
    	legacyMajorNote.setMajorHeadingId(majorHeadingId);
    	legacyMajorNote = (LegacyMajorNotes)AdapterUtil.performRetrieve(legacyMajorNote);
    	if(legacyMajorNote == null)
    		return null;
    	return legacyMajorNote.getMajorNotes();
    }
    
    public String getMinorHeadingNote(String contractId, Date startDate, String minorHeadingId) throws SevereException {
    	LegacyMinorNotes legacyMinorNotes = new LegacyMinorNotes();
    	legacyMinorNotes.setContractId(contractId);
    	legacyMinorNotes.setStartDate(getStringDate(startDate));
    	legacyMinorNotes.setMinorHeadingId(minorHeadingId );
    	legacyMinorNotes = (LegacyMinorNotes)AdapterUtil.performRetrieve(legacyMinorNotes);
    	if(legacyMinorNotes == null)
    		return null;
    	return legacyMinorNotes.getMinorNotes();
    }
    
    public String getVariableNote(String contractId, Date startDate, String variableId) throws SevereException {
    	HashMap map = new HashMap();
    	map.put("contractId", contractId);
		map.put("startDate", getStringDate(startDate));
		map.put("variableId",variableId);
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(CP2000Variable.class.getName(),"getContracVariableNotes",map);
    	SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
    	if(searchResults.getSearchResultCount()  == 0) {
    		return null;
    	}
    	LegacyVariable variable = (LegacyVariable)searchResults.getSearchResults().get(0);
    	return variable.getNotes();
    }
    
	/**
	 * 
	 * @param date
	 * @return
	 */
    private static String getStringDate(Date date) {
        String dateString = null;
        if (null != date) {
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        dateString = df.format(date);
        }
        return dateString;
    }
    
    public List getVariableNote(String system, LegacyVariable variable) throws SevereException{
		validateSystem(system);
		
			return getCP2000AdapterManager().getVariableNotes(variable);
		
		}

    public List getContractNotes(LegacyContractNotes legacyContractNotes ) throws SevereException{
		return getCP2000AdapterManager().getContractNotes(legacyContractNotes);
	}
    
    public List getContractMajorHeadings(LegacyContractMajorHeading legacyMajorHeading)throws SevereException{
    	
    	return getCP2000AdapterManager().getContrctMajorHeading(legacyMajorHeading);


    }
    
    public List getAllContrctMajorHeading(LegacyContractMajorHeading legacyMajorHeading)throws SevereException{
    	
    	return getCP2000AdapterManager().getAllContrctMajorHeading(legacyMajorHeading);


    }
    
    public List getContractMinorHeadings(LegacyContractMinorHeading legacyMinorHeading)throws SevereException{
    	
    	return getCP2000AdapterManager().getContractMinorHeadings(legacyMinorHeading);

    }
    public List getContractMajorNotes(LegacyMajorNotes legacyMajorNotes)throws SevereException{
    	
    	return getCP2000AdapterManager().getContractMajorNotes(legacyMajorNotes);

    } 
    public List getContractMinorNotes(LegacyMinorNotes legacyMinorNotes)throws SevereException{
    	
    	return getCP2000AdapterManager().getContractMinorNotes(legacyMinorNotes);
    
    }
	/**
	 * @param legacyMinorHeading
	 * @return
	 */
	public List getOldMinorHeadings(LegacyContractMinorHeading minorHeading) throws SevereException{
		// TODO Auto-generated method stub
    	return getCP2000AdapterManager().getOldMinorHeadings(minorHeading);
	} 
	
	public boolean ifPartialDateSegmentExist(String contractId)throws SevereException{
		List partialDtList = getCP2000AdapterManager().getPartialDateSegments(contractId);
		if(null !=partialDtList && !partialDtList.isEmpty())
			return true;
		return false;
	}
    


}
