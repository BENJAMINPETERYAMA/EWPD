/*
 * <MappingRuleIdRepositoryImpl.java>
 *
 * � 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.mapping.repository;

import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.wellpoint.ets.bx.mapping.domain.entity.AuditTrail;
import com.wellpoint.ets.bx.mapping.domain.entity.EB03Association;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.SPSMappingRetrieveResult;
import com.wellpoint.ets.bx.mapping.domain.exception.DomainException;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.vo.HippaSegmentMappingVO;
import com.wellpoint.ets.bx.mapping.domain.vo.User;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
/**
 * @author UST-GLOBAL This is an implementation class for performing all the life
 *         cycle operations on Rule Id mapping
 */
public class MappingRuleIdRepositoryImpl implements MappingRepository {
	private static Logger logger = Logger.getLogger(MappingRuleIdRepositoryImpl.class);
	private DataSource dataSource;
	private String insertMainMappingQuery;
	private String insertAuditTrailHeaderRuleQuery;
	private String insertTempMappingQuery;		
	private String deleteTempMappingQuery;	
	private String deleteMainMappingQuery;
	private String updateInTempFlagQuery;
	private String updateStatusMainQuery;
	private String updateStatusTempQuery;
	private String insertUmRuleInMain;
	private String insertUmRuleInTemp;
	private String deleteUmRuleInTemp;
	private String deleteUmRuleInMain;
	private boolean UM_RULE_TBALE_OPERATION = false; 
	/**
	 * Creating a new Mapping
	 * 
	 * @param mapping
	 * @param userComments
	 * @return MappingResult
	 */
	public boolean create(Mapping mapping,String userComments) {
		
		boolean isAuditPersisted = false;
		
		PersistHeaderRuleMapping persistHeaderRuleMapping = new PersistHeaderRuleMapping
		(dataSource, getInsertMainMappingQuery());	
		
		Date createdDate = new Date();
		mapping.setCreatedTmStamp(createdDate);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		sdf.format(createdDate);
		mapping.setCreatedDate(sdf.format(createdDate));
		//System.out.println(mapping.getCreatedDate());
		logger.info(mapping.getCreatedDate());
		if ((null != mapping.getEb03()) && ((DomainConstants.EB03_NAME).equals(mapping.getEb03().getName()))) {
					
					if((null != mapping.getEb03().getEb03Association()) 
							&& (! mapping.getEb03().getEb03Association().isEmpty())){
						
						List<EB03Association> eb03AssnList = mapping.getEb03().getEb03Association();
						
						for(int i=0; i< eb03AssnList.size(); i++){
							
							EB03Association eb03AssnObj = (EB03Association) eb03AssnList.get(i);								
							persistHeaderRuleMapping.insert(mapping, eb03AssnObj);								
						}
					}
					// if mapping not created and status is not applicable
					else{
						
						EB03Association eb03HippaValue = new EB03Association();
						HippaCodeValue eb03hippaCode = new HippaCodeValue();
						eb03hippaCode.setValue(" ");
						eb03HippaValue.setEb03(eb03hippaCode);
						persistHeaderRuleMapping.insert(mapping, eb03HippaValue);
					}
			}
		else{
			
			EB03Association eb03HippaValue = new EB03Association();
			HippaCodeValue eb03hippaCode = new HippaCodeValue();
			eb03hippaCode.setValue(" ");
			eb03HippaValue.setEb03(eb03hippaCode);
			persistHeaderRuleMapping.insert(mapping, eb03HippaValue);
		}
		
		//presist UM Rules to the table.
		insertUmRuleValues(mapping);
			String auditStatus = BxUtil.getAuditStatus(mapping.getVariableMappingStatus());
			
			isAuditPersisted = logMapping(mapping, userComments, auditStatus);
			
		return isAuditPersisted;
		
	}
	
	public void insertUmRuleValues(Mapping mapping){
		List umRuleSelectedValuesList = mapping.getUmRuleValues();
		if(null != umRuleSelectedValuesList){
			PersistUmRuleValues persistUmRuleValues = new PersistUmRuleValues(dataSource,getInsertUmRuleInMain());
			for(Iterator itr = umRuleSelectedValuesList.iterator();itr.hasNext();){
				String obj = (String)itr.next();
				if(null != obj && !"".equals(obj.trim())){
					persistUmRuleValues.insert(mapping, obj.trim());
				}
			}
		}
	}
	/**
	 * Inner class for persisting header rule
	 *
	 */
	private final class PersistHeaderRuleMapping extends SqlUpdate {

		private PersistHeaderRuleMapping(DataSource dataSource, String query) {
	        super(dataSource, query);
	        declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.CHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.DATE));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));		
			declareParameter(new SqlParameter(Types.CHAR));
			declareParameter(new SqlParameter(Types.CHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.CHAR));
		    compile();
	    }	
		
		/**
		 * sets the values to be persisted
		 * @param mapping
		 * @param hippaValue
		 */
		protected void insert(Mapping mapping, EB03Association eb03AssnObj) {
			if(null != eb03AssnObj){
				Object[] objs = new Object[] { 
						mapping.getRule().getHeaderRuleId(),
						(null != eb03AssnObj.getEb03() ? eb03AssnObj.getEb03().getValue() : ""),	
						mapping.getSensitiveBenefitIndicator(),
						mapping.getUser().getLastUpdatedUserName(),
						mapping.getCreatedDate(),
						mapping.getUser().getCreatedUserName(),
						mapping.getVariableMappingStatus(),
						mapping.getInTempTable(),
						mapping.getProcedureExcludedInd(),
						(null != eb03AssnObj.getCommaSeparatedIII02String() ? eb03AssnObj.getCommaSeparatedIII02String() : ""),
						(mapping.getIndvdlEb03AssnIndicator())
						};
				
				super.update(objs);
			}
		}
		
    }
	
	/**
	 * Inner class to Presist Um Rules
	 *
	 */
	private static class PersistUmRuleValues extends SqlUpdate{
		
		private PersistUmRuleValues(DataSource dataSource, String query){
			super(dataSource, query);
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			compile();
		}
		
		private void insert(Mapping mapping, String hippaCodeValue){
			Object[] params = new Object[]{
						mapping.getRule().getHeaderRuleId(),
						hippaCodeValue
					};
			super.update(params);
		}
	}
	
	/**
	 * 
	 * Inner class to persist audit trail
	 *
	 */
	private final class PersistAuditTrailHeaderRuleQuery extends SqlUpdate {

		private PersistAuditTrailHeaderRuleQuery(DataSource dataSource, String query) {
	        super(dataSource, query);
	        declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));			
		    compile();
	    }	
		
		/**
		 * insert audit trail
		 * @param auditTrail
		 */
		protected void insert(AuditTrail auditTrail) {
			Object[] objs = new Object[] { 
					auditTrail.getRuleId(),
					auditTrail.getSystemComments(),
					auditTrail.getUserComments(),
					auditTrail.getCreatedUser(),					
					auditTrail.getMappingStatus()};
			super.update(objs);
		}
		
    }
	/**
	 * Inner class for persisting header rule
	 *
	 */
	private final class PersistHeaderRuleBeingModifiedMapping extends SqlUpdate {

		private PersistHeaderRuleBeingModifiedMapping(DataSource dataSource, String query) {
	        super(dataSource, query);
	        declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.CHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.DATE));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.CHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.CHAR));
		    compile();
	    }	
		
		/**
		 * sets the values to be persisted
		 * @param mapping
		 * @param hippaValue
		 */
		protected void insert(Mapping mapping, EB03Association eb03AssnObj) {
			if(null != eb03AssnObj){
				Object[] objs = new Object[] { 
						mapping.getRule().getHeaderRuleId(),
						(null != eb03AssnObj.getEb03() ? eb03AssnObj.getEb03().getValue() : ""),	
						mapping.getSensitiveBenefitIndicator(),
						mapping.getUser().getLastUpdatedUserName(),
						mapping.getCreatedDate(),
						mapping.getUser().getCreatedUserName(),
						mapping.getVariableMappingStatus(), 
						mapping.getProcedureExcludedInd(),
						(null != eb03AssnObj.getCommaSeparatedIII02String() ? eb03AssnObj.getCommaSeparatedIII02String() : ""),
						(mapping.getIndvdlEb03AssnIndicator())
						};
				
				super.update(objs);
			}
		}
		
    }
	
	/**
	 * Method to insert the audit trail
	 * @param auditTrail
	 * @return boolean
	 */
	private boolean insertAuditTrail(AuditTrail auditTrail){
		
		if(null != auditTrail){
			PersistAuditTrailHeaderRuleQuery persistAuditTrailQuery = new PersistAuditTrailHeaderRuleQuery(dataSource, 
					getInsertAuditTrailHeaderRuleQuery());
			persistAuditTrailQuery.insert(auditTrail);
			return true; 
		}
		else { return false; }
	}
	
	/**
	 * Method that creates the audit trail object to be persisted
	 * and calls the insertaudittrail method 
	 * @param mapping
	 * @param userComments
	 * @param auditStatus
	 * @return boolean
	 */
	private boolean logMapping(Mapping mapping, String userComments,
			String auditStatus) {
		
		if(mapping == null || null == mapping.getVariableMappingStatus()) {
			throw new DomainException("Invalid Mapping Object! Cannot log Audit details.");
		}
		AuditTrail auditTrail = createAuditTrailObject( mapping,userComments, auditStatus, null);
		return insertAuditTrail(auditTrail);
	}
	
	/**
	 * Creates the AuditTrail object - sets the usercomments and mappingstatus fields
	 * @param mapping
	 * @param userCmnts
	 * @param auditStatus
	 * @param systemComments TODO
	 * @return AuditTrail
	 */
	private AuditTrail createAuditTrailObject(Mapping mapping,
			String userCmnts, String auditStatus, String systemComments) {
		
		AuditTrail auditTrail = new AuditTrail();
		String auditUser = null;
		auditTrail.setRuleId(mapping.getRule().getHeaderRuleId());		
		auditTrail.setMappingStatus(auditStatus);
		auditTrail.setUserComments(userCmnts);
		auditTrail.setSystemComments(systemComments);
		auditUser = mapping.getUser().getLastUpdatedUserName();
		if(null == auditUser){
			throw new DomainException(
			"Cannot log Mapping! User not found");
		}	
		auditTrail.setCreatedUser(auditUser);
		return auditTrail;
	}
	/**
	 * discarding changes to an already Published mapping
	 * 
	 * @param mapping
	 * @param userComments
	 * @return MappingResult
	 */
	public boolean discardChanges(Mapping mapping, String userComments) {
		
		boolean isAuditPersisted = false;
		// delete from temp table
		if((null != mapping.getRule()) && (null != mapping.getRule().getHeaderRuleId())){
			
			updateOperation(mapping, DomainConstants.DELETE_TEMP_OPERATION, null);
		}
		
		//logging in audit trail
		String auditStatus = DomainConstants.AUDIT_STATUS_DISCARD_CHANGES;
		mapping.setVariableMappingStatus(DomainConstants.STATUS_PUBLISHED);
		isAuditPersisted = logMapping(mapping, userComments, auditStatus);		
		
		return isAuditPersisted;
	}	
	/**
	 * updating an existing mapping
	 * 
	 * @param mapping
	 * @param userComments
	 * @param operation
	 * @return MappingResult
	 */
	public boolean update(Mapping mapping,String userComments, String operation) {
		
		boolean isAuditPersisted = false;
		
		if(null != mapping){
			
			if(null == mapping.getCreatedDate() || (null != mapping.getCreatedDate()  && mapping.getCreatedDate().equals(DomainConstants.EMPTY)) ){
				Date createdDate = new Date();
				mapping.setCreatedTmStamp(createdDate);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
				sdf.format(createdDate);
				mapping.setCreatedDate(sdf.format(createdDate));
				//System.out.println(mapping.getCreatedDate());
				logger.info(mapping.getCreatedDate());
			}
			
			if((DomainConstants.UPDATE_TEMP_OPERATION).equals(operation)){
				deleteTempForUpdate(mapping);
			}	
			else if((DomainConstants.UPDATE_MAIN_OPERATION).equals(operation)){
				deleteMainForUpdate(mapping);
			}
			if ((null != mapping.getEb03()) && ((DomainConstants.EB03_NAME).equals(mapping.getEb03().getName()))) {
				
				if((null != mapping.getEb03().getEb03Association()) 
						&& (! mapping.getEb03().getEb03Association().isEmpty())){
					
					List eb03AssnObject = mapping.getEb03().getEb03Association();
					UM_RULE_TBALE_OPERATION = false;
					for(int i=0; i< eb03AssnObject.size(); i++){
					
							EB03Association eb03HippaValue = (EB03Association) eb03AssnObject.get(i);
							
							updateOperation(mapping, operation, eb03HippaValue);	
							UM_RULE_TBALE_OPERATION = true; 
					}
				}
				
			}
			String auditStatus = BxUtil.getAuditStatus(mapping.getVariableMappingStatus());
			
			isAuditPersisted = logMapping(mapping, userComments, auditStatus);
		}
				
		return isAuditPersisted;
	}	
	/**
	 * The methods (sendToTest, approve,notApplicable) in fa�ade will invoke this method for updating the status  of the mapping
	 * @param mapping
	 * @param userComments
	 * @param status
	 * @param operation
	 * @return MappingResult
	 */
	public boolean updateStatus(Mapping mapping,
			String userComments, String operation) {
		
		boolean isAuditPersisted = false;
		
		if(null != mapping){
			
			updateOperation(mapping, operation, null);			
			
			String auditStatus = BxUtil.getAuditStatus(mapping.getVariableMappingStatus());
			if ((mapping.getDatafeedStatus()!=null)&&(mapping.getDatafeedStatus().equals(DomainConstants.DATAFEED_STATUS))){
				mapping.setUser(new User());
				mapping.getUser().setLastUpdatedUserName(
						DomainConstants.DATAFEED_UPDTD_USER);
			}
			
			isAuditPersisted = logMapping(mapping, userComments, auditStatus);
		}

		return isAuditPersisted;
	}
	/**
	 * Based on the operation the corresponding query is called
	 * @param mapping
	 * @param operation
	 * @param eb03Value
	 */
	private void updateOperation(Mapping mapping,String operation, EB03Association eb03AssnObj){
		/*
		 * Update the IN_TEMP_TAB to 'Y' and insert a row in the TEMP table
		 */
		if((DomainConstants.INSERT_TEMP_OPERATION).equals(operation)){
			
			PersistHeaderRuleBeingModifiedMapping persistHeaderRuleBeingModifiedMapping = new
			PersistHeaderRuleBeingModifiedMapping(dataSource, getInsertTempMappingQuery());
			mapping.setVariableMappingStatus(DomainConstants.STATUS_BEING_MODIFIED);
			persistHeaderRuleBeingModifiedMapping.insert(mapping, eb03AssnObj);
			
			UpdateHeaderRuleMapping updateHeaderRuleMapping = new UpdateHeaderRuleMapping(dataSource, getUpdateInTempFlagQuery());
			mapping.setInTempTable(DomainConstants.IN_TEMP_TAB_FLAG_UPDATE);			
			updateHeaderRuleMapping.update(mapping);
			if(!UM_RULE_TBALE_OPERATION){
				insertUmRuleIntoTemp(mapping,getInsertUmRuleInTemp());
			}
		}
		/*
		 * Update the mapping in TEMP table - first delete and then insert
		 */
		if((DomainConstants.UPDATE_TEMP_OPERATION).equals(operation)){
			
			PersistHeaderRuleBeingModifiedMapping persistHeaderRuleBeingModifiedMapping = new
			PersistHeaderRuleBeingModifiedMapping(dataSource, getInsertTempMappingQuery());
			
			persistHeaderRuleBeingModifiedMapping.insert(mapping, eb03AssnObj);
			if(!UM_RULE_TBALE_OPERATION){
				insertUmRuleIntoTemp(mapping,getInsertUmRuleInTemp());
			}
		}
		/*
		 * Delete the mapping from TEMP and update the IN_TEMP_TAB to 'N" in the MAIN table 
		 */
		if((DomainConstants.DELETE_TEMP_OPERATION).equals(operation)){
			
			RollbackTempRuleMapping rollbackTempRuleMapping = new 
			RollbackTempRuleMapping(dataSource, getDeleteTempMappingQuery());			
			
			rollbackTempRuleMapping.delete(mapping);	
			
			//Delete UmRule
			rollbackTempRuleMapping = new RollbackTempRuleMapping(dataSource, getDeleteUmRuleInTemp());			
			rollbackTempRuleMapping.delete(mapping);
			
			UpdateHeaderRuleMapping updateHeaderRuleMapping = new UpdateHeaderRuleMapping(dataSource, getUpdateInTempFlagQuery());
			mapping.setInTempTable(DomainConstants.IN_TEMP_TAB_FLAG_PERSIST);			
			updateHeaderRuleMapping.update(mapping);
			if(null != mapping.getVariableMappingStatus()){
				UpdateStatusInMain updateStatusInMain = new UpdateStatusInMain(dataSource, getUpdateStatusMainQuery());
				updateStatusInMain.update(mapping);
			}	
		}
		/*
		 * Update the mapping in the main table - first delete and then insert
		 */
		if((DomainConstants.UPDATE_MAIN_OPERATION).equals(operation)){
			
			PersistHeaderRuleMapping persistHeaderRuleMapping = new PersistHeaderRuleMapping
			(dataSource, getInsertMainMappingQuery());
			
			persistHeaderRuleMapping.insert(mapping, eb03AssnObj);
			if(!UM_RULE_TBALE_OPERATION){
				insertUmRuleValues(mapping);
			}
		}
		 /*
		  * Update the status alone in the main table
		  */
		if((DomainConstants.UPDATE_STATUS_MAIN_OPERATION).equals(operation)){
			
			UpdateStatusInMain updateStatusInMain = new UpdateStatusInMain(dataSource, getUpdateStatusMainQuery());
			updateStatusInMain.update(mapping);
		}
		/*
		  * Update the status alone in the temp table
		  */
		if((DomainConstants.UPDATE_STATUS_TEMP_OPERATION).equals(operation)){
				
			UpdateStatusInTemp updateStatusInTemp = new UpdateStatusInTemp(dataSource, getUpdateStatusTempQuery());
			updateStatusInTemp.update(mapping);
		}
	}
	
	public void insertUmRuleIntoTemp(Mapping mapping, String queryString){
		List umRuleSelectedValuesList = mapping.getUmRuleValues();
		if(null != umRuleSelectedValuesList  && !umRuleSelectedValuesList.isEmpty()){
			PersistUmRuleBeingModified persistUmRuleBeingModified = new PersistUmRuleBeingModified(dataSource, queryString);
			for(Iterator itr = umRuleSelectedValuesList.iterator();itr.hasNext();){
				String obj = (String)itr.next();
				if(null != obj && !"".equals(obj.trim())){
					persistUmRuleBeingModified.insert(mapping, obj.trim());
				}
			}
			
		}
	}
	
	private static class PersistUmRuleBeingModified extends SqlUpdate{
		private PersistUmRuleBeingModified(DataSource dataSource, String qurery){
			super(dataSource, qurery);
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
		    compile();
	    }	
		
		/**
		 * sets the values to be persisted
		 * @param mapping
		 * @param hippaValue
		 */
		protected void insert(Mapping mapping, String hippaValue) {				
			Object[] objs = new Object[] { 
					mapping.getRule().getHeaderRuleId(),
					hippaValue
					};
			
			super.update(objs);
		}
	}
	
	
	
	public boolean publish(Mapping mappingToPersist, String userComments) {
		String operation = null;
		mappingToPersist
				.setVariableMappingStatus(DomainConstants.STATUS_PUBLISHED);
		if (mappingToPersist.getInTempTable().equals("N")) {

			operation = DomainConstants.UPDATE_STATUS_MAIN_OPERATION;
			
			updateOperation(mappingToPersist, operation, null);			
		
			

		} else {
				operation = DomainConstants.UPDATE_MAIN_OPERATION;
				deleteMainForUpdate(mappingToPersist);

				if ((null != mappingToPersist.getEb03())
						&& ((DomainConstants.EB03_NAME).equals(mappingToPersist
								.getEb03().getName()))) {

					if ((null != mappingToPersist.getEb03()
							.getEb03Association())
							&& (!mappingToPersist.getEb03()
									.getEb03Association().isEmpty())) {

						List<EB03Association> eb03AssnList = mappingToPersist.getEb03()
								.getEb03Association();
						mappingToPersist.setInTempTable(DomainConstants.IN_TEMP_TAB_FLAG_PERSIST);
						UM_RULE_TBALE_OPERATION = false;
						for (int i = 0; i < eb03AssnList.size(); i++) {

							EB03Association eb03AssnObj = (EB03Association) eb03AssnList
									.get(i);

							updateOperation(mappingToPersist, operation,
									eb03AssnObj);
							UM_RULE_TBALE_OPERATION = true;
						}
					}
				}
				deleteTempForUpdate(mappingToPersist);
		}
		String auditStatus = DomainConstants.AUDIT_STATUS_PUBLISHED;
		if ((mappingToPersist.getDatafeedStatus()!=null)&&(mappingToPersist.getDatafeedStatus().equals(DomainConstants.DATAFEED_STATUS))){
			mappingToPersist.setUser(new User());
			mappingToPersist.getUser().setLastUpdatedUserName(
					DomainConstants.DATAFEED_UPDTD_USER);
		}
		return logMappingInDetail(mappingToPersist, userComments, auditStatus, null);
	}
	
	/**
	 * Driver method to log the xml string of the mapping
	 * @param mapping
	 * @param userComments
	 * @param auditStatus
	 * @param systemComments
	 * @return
	 */
	private boolean logMappingInDetail(Mapping mapping, String userComments,String auditStatus,String systemComments ) {
		if(mapping == null || mapping.getVariableMappingStatus()== null) {
			throw new DomainException("Invalid Mapping Object! Cannot log Audit in detail.");
		}
		HippaSegmentMappingVO hippaSegmentMappingVO = createHippaSegmentMappingVO(mapping);
		XStream stream = new XStream();
		stream.allowTypes(new Class[] {HippaSegmentMappingVO.class});
		stream.alias("mapping", HippaSegmentMappingVO.class);
		String mapingXml = stream.toXML(hippaSegmentMappingVO);		
		AuditTrail auditTrail = createAuditTrailObject( mapping,userComments, auditStatus, mapingXml);
		return insertAuditTrail(auditTrail);
	}
	
	/**
	 * Created the value object from which the xml is created
	 * @param mapping
	 * @return
	 */
	private HippaSegmentMappingVO createHippaSegmentMappingVO(Mapping mapping) {
		HippaSegmentMappingVO hippaSegmentMappingVO = new HippaSegmentMappingVO();
		hippaSegmentMappingVO.setEb01(mapping.getEB01Value());
		hippaSegmentMappingVO.setEb02(mapping.getEB02Value());
		hippaSegmentMappingVO.setEb03(BxUtil.getAsCSV(mapping.getEb03Values()));
		hippaSegmentMappingVO.setEb06(mapping.getEB06Value());
		hippaSegmentMappingVO.setEb09(mapping.getEB09Value());
		hippaSegmentMappingVO.setIi02(mapping.getIi02Value());
		hippaSegmentMappingVO.setHsd01(mapping.getHsd01Value());
		hippaSegmentMappingVO.setHsd02(BxUtil.getAsCSV(mapping.getHsd02Value()));
		hippaSegmentMappingVO.setHsd03(mapping.getHsd03Value());
		hippaSegmentMappingVO.setHsd04(mapping.getHsd04Value());
		hippaSegmentMappingVO.setHsd05(mapping.getHsd05Value());
		hippaSegmentMappingVO.setHsd06(mapping.getHsd06Value());
		hippaSegmentMappingVO.setHsd07(mapping.getHsd07Value());
		hippaSegmentMappingVO.setHsd08(mapping.getHsd08Value());
		hippaSegmentMappingVO.setAccum(BxUtil.getAsCSV(mapping.getAccumValues()));
		hippaSegmentMappingVO.setUtilizationMgmntRule(BxUtil.getAsCSV(BxUtil.removeBlankfromStringList(mapping.getUmRuleValues())));
		hippaSegmentMappingVO.setMessage(mapping.getMessage());
		hippaSegmentMappingVO.setMessageTypeRequired(mapping.getMsg_type_required());
		hippaSegmentMappingVO.setSensitiveBenefitIndicator(mapping.getSensitiveBenefitIndicator());
		hippaSegmentMappingVO.setNoteTypeCode(mapping.getNoteTypeCodeValue());
		return hippaSegmentMappingVO;
	}
	
	
	
	
	
	/**
	 * Deletes the temp mapping for Update mapping process
	 * @param mapping
	 */
	private void deleteTempForUpdate(Mapping mapping){
		
		RollbackTempRuleMapping rollbackTempRuleMapping = new RollbackTempRuleMapping
		(dataSource, getDeleteTempMappingQuery());
		
		rollbackTempRuleMapping.delete(mapping);

		//Delete Um rules
		rollbackTempRuleMapping = new RollbackTempRuleMapping(dataSource, getDeleteUmRuleInTemp());
		rollbackTempRuleMapping.delete(mapping);
		
	}
	/**
	 * Deletes the main mapping for Update mapping process
	 * @param mapping
	 */
	private void deleteMainForUpdate(Mapping mapping){
		
		RollbackHeaderRuleMapping rollbackHeaderRuleMapping = new RollbackHeaderRuleMapping
		(dataSource, getDeleteMainMappingQuery());
		rollbackHeaderRuleMapping.delete(mapping);
		
		//Delete Um rules
		rollbackHeaderRuleMapping = new RollbackHeaderRuleMapping(dataSource, getDeleteUmRuleInMain());
		rollbackHeaderRuleMapping.delete(mapping);
	}
	/**
	 * Inner class for deleting the Temp mapping
	 * 
	 */
	private final class RollbackTempRuleMapping extends SqlUpdate {

		private RollbackTempRuleMapping(DataSource dataSource, String query) {
	        super(dataSource, query);	        
			declareParameter(new SqlParameter(Types.VARCHAR));			
		    compile();
	    }	
		
		/**
		 * sets the values to be deleted
		 * @param mapping
		 */
		protected void delete(Mapping mapping) {				
			Object[] objs = new Object[] {mapping.getRule().getHeaderRuleId()};			
			super.update(objs);
		}
		
    }
	/**
	 * Inner class for Deleting the Main mapping	 
	 *
	 */
	private final class RollbackHeaderRuleMapping extends SqlUpdate {

		private RollbackHeaderRuleMapping(DataSource dataSource, String query) {
	        super(dataSource, query);	        
			declareParameter(new SqlParameter(Types.VARCHAR));			
		    compile();
	    }	
		
		/**
		 * sets the values to be deleted
		 * @param mapping
		 */
		protected void delete(Mapping mapping) {				
			Object[] objs = new Object[] {mapping.getRule().getHeaderRuleId()};			
			super.update(objs);
		}
		
    }
	/**
	 * Inner class for updating the in temp tab flag 	 
	 *
	 */
	private final class UpdateHeaderRuleMapping extends SqlUpdate {

		private UpdateHeaderRuleMapping(DataSource dataSource, String query) {
	        super(dataSource, query);	       
			declareParameter(new SqlParameter(Types.CHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));			
		    compile();
	    }	
		
		/**
		 * sets the values to be updated
		 * @param mapping
		 */
		protected void update(Mapping mapping) {				
			Object[] objs = new Object[] { 
					mapping.getInTempTable(),
					mapping.getRule().getHeaderRuleId()
					};			
			super.update(objs);
		}
		
    }
	/**
	 * Inner class for updating the status of the mappingin the main table	
	 *
	 */
	private final class UpdateStatusInMain extends SqlUpdate {

		private UpdateStatusInMain(DataSource dataSource, String query) {
	        super(dataSource, query);	       
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));	
			declareParameter(new SqlParameter(Types.VARCHAR));	
			declareParameter(new SqlParameter(Types.VARCHAR));			
		    compile();
	    }	
		
		/**
		 * sets the values to be updated
		 * @param mapping
		 */
		protected void update(Mapping mapping) {				
			Object[] objs = new Object[] { 
					mapping.getVariableMappingStatus(),
					mapping.getUser().getLastUpdatedUserName(),
					mapping.getDataFeedInd(),
					mapping.getRule().getHeaderRuleId()
					};			
			super.update(objs);
		}
		
    }
	/**
	 * Inner class for updating the status of the mapping in the temp table
	 *
	 */
	private final class UpdateStatusInTemp extends SqlUpdate {

		private UpdateStatusInTemp(DataSource dataSource, String query) {
	        super(dataSource, query);	       
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));	
			declareParameter(new SqlParameter(Types.VARCHAR));	
			declareParameter(new SqlParameter(Types.VARCHAR));	
		    compile();
	    }	
		
		/**
		 * sets the values to be updated
		 * @param mapping
		 */
		protected void update(Mapping mapping) {				
			Object[] objs = new Object[] { 
					mapping.getVariableMappingStatus(),
					mapping.getUser().getLastUpdatedUserName(),
					mapping.getDataFeedInd(),
					mapping.getRule().getHeaderRuleId()
					};			
			super.update(objs);
		}
		
    }	
	
	/**
	 * An existing mapping of custom message is deleted and a datafeed entry is made
	 * @param Mapping
	 * @param userComments
	 * @return MappingResult
	 */
	 public boolean delete(Mapping mapping,String userComments)  {
		return false;
	}		

	/**
	 * @return dataSource
	 */
	public DataSource getDataSource() {
		return dataSource;
	}
	/**
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}	
	/**
	 * @return String 
	 */
	public String getUpdateStatusMainQuery() {
		return updateStatusMainQuery;
	}
	/**
	 * @param updateStatusMainQuery
	 */
	public void setUpdateStatusMainQuery(String updateStatusMainQuery) {
		this.updateStatusMainQuery = updateStatusMainQuery;
	}	
	/**
	 * @return String
	 */
	public String getUpdateStatusTempQuery() {
		return updateStatusTempQuery;
	}	
	/**
	 * @param updateStatusTempQuery
	 */
	public void setUpdateStatusTempQuery(String updateStatusTempQuery) {
		this.updateStatusTempQuery = updateStatusTempQuery;
	}	
	/**
	 * @return String
	 */
	public String getDeleteMainMappingQuery() {
		return deleteMainMappingQuery;
	}	
	/**
	 * @param deleteMainMappingQuery
	 */
	public void setDeleteMainMappingQuery(String deleteMainMappingQuery) {
		this.deleteMainMappingQuery = deleteMainMappingQuery;
	}	
	/**
	 * @return String
	 */
	public String getDeleteTempMappingQuery() {
		return deleteTempMappingQuery;
	}	
	/**
	 * @param deleteTempMappingQuery
	 */
	public void setDeleteTempMappingQuery(String deleteTempMappingQuery) {
		this.deleteTempMappingQuery = deleteTempMappingQuery;
	}	
	/**
	 * @return
	 */
	public String getInsertAuditTrailHeaderRuleQuery() {
		return insertAuditTrailHeaderRuleQuery;
	}
	/**
	 * @param insertAuditTrailHeaderRuleQuery
	 */
	public void setInsertAuditTrailHeaderRuleQuery(
			String insertAuditTrailHeaderRuleQuery) {
		this.insertAuditTrailHeaderRuleQuery = insertAuditTrailHeaderRuleQuery;
	}
	/**
	 * @return String
	 */
	public String getInsertMainMappingQuery() {
		return insertMainMappingQuery;
	}
	/**
	 * @param insertMainMappingQuery
	 */
	public void setInsertMainMappingQuery(String insertMainMappingQuery) {
		this.insertMainMappingQuery = insertMainMappingQuery;
	}
	/**
	 * @return String
	 */
	public String getInsertTempMappingQuery() {
		return insertTempMappingQuery;
	}
	/**
	 * @param insertTempMappingQuery
	 */
	public void setInsertTempMappingQuery(String insertTempMappingQuery) {
		this.insertTempMappingQuery = insertTempMappingQuery;
	}
	/**
	 * @return String
	 */
	public String getUpdateInTempFlagQuery() {
		return updateInTempFlagQuery;
	}
	/**
	 * @param updateInTempFlagQuery
	 */
	public void setUpdateInTempFlagQuery(String updateInTempFlagQuery) {
		this.updateInTempFlagQuery = updateInTempFlagQuery;
	}
	
	public String getInsertUmRuleInMain() {
		return insertUmRuleInMain;
	}
	
	public void setInsertUmRuleInMain(String insertUmRuleInMain) {
		this.insertUmRuleInMain = insertUmRuleInMain;
	}
	public String getInsertUmRuleInTemp() {
		return insertUmRuleInTemp;
	}

	public void setInsertUmRuleInTemp(String insertUmRuleInTemp) {
		this.insertUmRuleInTemp = insertUmRuleInTemp;
	}

	public String getDeleteUmRuleInTemp() {
		return deleteUmRuleInTemp;
	}

	public void setDeleteUmRuleInTemp(String deleteUmRuleInTemp) {
		this.deleteUmRuleInTemp = deleteUmRuleInTemp;
	}

	public String getDeleteUmRuleInMain() {
		return deleteUmRuleInMain;
	}

	public void setDeleteUmRuleInMain(String deleteUmRuleInMain) {
		this.deleteUmRuleInMain = deleteUmRuleInMain;
	}
	/**
	 * This method updates the feed_run_flag as �T� after the datafeed to IMSN region is executed 
	 * and to �P� after the datafeed to IMSP region is executed.
	 * @param Mapping
	 * @return boolean
	 */
	public boolean updateDatafeedStatus(Mapping mapping) {
		// TODO Auto-generated method stub
		return false;
	}
	public SPSMappingRetrieveResult getSPSMappingMain(String spsId){
		return null;
	}
	
}
