/*
 * <MappingSpsIdRepositoryImpl.java>
 *
 * � 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.mapping.repository;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.wellpoint.ets.bx.mapping.domain.entity.AuditTrail;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.SPSMappingRetrieveResult;
import com.wellpoint.ets.bx.mapping.domain.exception.DomainException;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.vo.HippaSegmentMappingVO;
import com.wellpoint.ets.bx.mapping.domain.vo.User;
import com.wellpoint.ets.bx.mapping.util.BxUtil;

/**
 * @author UST-GLOBAL This is an implementation class for performing all the
 *         life cycle operations on Sps Id mapping
 */
public class MappingSpsIdRepositoryImpl implements MappingRepository {
	private DataSource dataSource;  

	private String createSPSIDMappingQuery;

	private String insertSPSAuditTrailQuery;

	private String updateMainSPSIdMappingQuery;

	private String updateTmpSPSIdMappingQuery;

	private String insertTmpSPSIdMappingQuery;

	private String deleteTmpSPSIdMappingQuery;

	private String updateMainSPSIdStatusQuery;

	private String updateTmpSPSIdStatusQuery;

	private String updateInTempQuery;

	private String updateIsModifiedSPSIDMappingQuery;

	private String updateMainInTempAndStatusQuery;
	/**
	 * Creating a new Mapping
	 * 
	 * @param mapping
	 * @param userComments
	 * @return MappingResult
	 */
	public boolean create(Mapping mapping, String userComments) {
		CreateSPSMappingQuery createSPSMapping = new CreateSPSMappingQuery(
				dataSource);
		createSPSMapping.insert(mapping);
		String auditStatus = BxUtil.getAuditStatus(mapping
				.getVariableMappingStatus());
		String systemComments = null;
		/** ****************************************************** */
		// Changes to be done.. in SPS mapping
		/** *************************************************** */
		// TODO: Remove the duplicate call for log mapping....
		// TODO: Insert the finalzed status in "system comments" filed and the
		// other status should be inserted in "MAPG_STATUS" .
		// TODO: While viewing the history need to handle both status.....
		/** ******************************************************* */
		/** ****************************************************** */
		// Logging the status of FINALIZED or NOT_FINALIZED
		if (mapping.isFinalized()) {
			systemComments = DomainConstants.FINALIZED;
		} else {
			systemComments = DomainConstants.NOT_FINALIZED;
		}

		return logMapping(mapping, userComments, auditStatus, systemComments);

	}

	/**
	 * updating an existing mapping
	 * 
	 * @param mapping
	 * @param userComments
	 * @return MappingResult
	 */
	public boolean update(Mapping mapping, String userComments, String operation) {
		updateOperation(mapping, operation);
		String auditStatus = BxUtil.getAuditStatus(mapping
				.getVariableMappingStatus());
		String systemComments = null;

		if (mapping.isFinalizedFlagModified() && mapping.isFinalized()) {
			systemComments = DomainConstants.FINALIZED;
		} else if (mapping.isFinalizedFlagModified()
				&& !(mapping.isFinalized())) {
			systemComments = DomainConstants.NOT_FINALIZED;
		}
		return logMapping(mapping, userComments, auditStatus, systemComments);
	}

	private void updateOperation(Mapping mapping, String operation) {
		/*
		 * Update the IN_TEMP_TAB to 'Y' and insert a row in the TEMP table and
		 * the status in mapping to BEING_MODIFIED
		 */
		if (DomainConstants.INSERT_TEMP_OPERATION.equals(operation)) {
			mapping
					.setVariableMappingStatus(DomainConstants.STATUS_BEING_MODIFIED);
			InsertTmpSPSIdMappingQuery insertTmpSPSIdMapping = new InsertTmpSPSIdMappingQuery(
					dataSource, getInsertTmpSPSIdMappingQuery());
			insertTmpSPSIdMapping.insert(mapping);
			UpdateMainInTempQuery updateMainInTempQuery = new UpdateMainInTempQuery(
					dataSource, getUpdateInTempQuery());
			updateMainInTempQuery.updateInTemp(mapping, "Y");
		}
		/*
		 * Update the mapping in TEMP table
		 */
		if (DomainConstants.UPDATE_TEMP_OPERATION.equals(operation)) {
			UpdateTmpSPSIdMappingQuery updateTmpSPSIdMapping = new UpdateTmpSPSIdMappingQuery(
					dataSource, getUpdateTmpSPSIdMappingQuery());
			updateTmpSPSIdMapping.update(mapping);
		}
		/*
		 * Delete the mapping from TEMP and update the IN_TEMP_TAB to 'N" in the
		 * MAIN table
		 */
		if (DomainConstants.DELETE_TEMP_OPERATION.equals(operation)) {
			DeleteTmpSPSIdMappingQuery deleteTmpSPSIdMapping = new DeleteTmpSPSIdMappingQuery(
					dataSource, getDeleteTmpSPSIdMappingQuery());
			deleteTmpSPSIdMapping.delete(mapping);
			UpdateMainInTempAndStatusQuery updateMainInTempAndStatusQuery = new UpdateMainInTempAndStatusQuery(
					dataSource, getUpdateMainInTempAndStatusQuery());   
			updateMainInTempAndStatusQuery.update(mapping, "N");
		}
		/*
		 * Update the mapping in the main table
		 */
		if (DomainConstants.UPDATE_MAIN_OPERATION.equals(operation)) {
			UpdateMainSPSIdMappingQuery updateSPSIdMapping = new UpdateMainSPSIdMappingQuery(
					dataSource, getUpdateMainSPSIdMappingQuery());
			updateSPSIdMapping.update(mapping);
		}

		/*
		 * Update the status in the TEMP table
		 */
		if (DomainConstants.UPDATE_STATUS_MAIN_OPERATION.equals(operation)) {

			UpdateMainSPSIdStatusQuery updateMainSPSIdStatus = new UpdateMainSPSIdStatusQuery(
					dataSource, getUpdateMainSPSIdStatusQuery());
			updateMainSPSIdStatus.updateStatus(mapping);

		}
		/*
		 * Update the status in the TEMP table
		 */
		if (DomainConstants.UPDATE_STATUS_TEMP_OPERATION.equals(operation)) {
			UpdateTmpSPSIdStatusQuery updateTmpSPSIdStatus = new UpdateTmpSPSIdStatusQuery(
					dataSource, getUpdateTmpSPSIdStatusQuery());
			updateTmpSPSIdStatus.updateStatus(mapping);
		}

	}

	/**
	 * The methods (sendToTest, approve,notApplicable) in fa�ade will invoke
	 * this method for updating the status of the mapping For sentToTest and
	 * approve updateonly the status in the Main table
	 * 
	 * @param mapping
	 * @param userComments
	 * @param status
	 * @return MappingResult
	 */
	public boolean updateStatus(Mapping mapping, String userComments,
			String operation) {

		updateOperation(mapping, operation);
		String auditStatus = BxUtil.getAuditStatus(mapping
				.getVariableMappingStatus());
		String systemComments = null;

		if (mapping.isFinalizedFlagModified() && mapping.isFinalized()) {
			systemComments = DomainConstants.FINALIZED;
		} else if (mapping.isFinalizedFlagModified()
				&& !(mapping.isFinalized())) {
			systemComments = DomainConstants.NOT_FINALIZED;
		}
		if ((mapping.getDatafeedStatus()!=null)&&(mapping.getDatafeedStatus().equals(DomainConstants.DATAFEED_STATUS))){
			mapping.setUser(new User());
			mapping.getUser().setLastUpdatedUserName(
					DomainConstants.DATAFEED_UPDTD_USER);
		}
		return logMapping(mapping, userComments, auditStatus, systemComments);
	}

	/**
	 * 
	 */
	public boolean publish(Mapping mappingToPersist, String userComments) {

		String operation = null;
		if (mappingToPersist.getInTempTable().equals("N")) {

			operation = DomainConstants.UPDATE_STATUS_MAIN_OPERATION;
			updateOperation(mappingToPersist, operation);
		} else {
			mappingToPersist
					.setVariableMappingStatus(DomainConstants.STATUS_PUBLISHED);
			operation = DomainConstants.UPDATE_MAIN_OPERATION;
			updateOperation(mappingToPersist, operation);
			operation = DomainConstants.DELETE_TEMP_OPERATION;
			updateOperation(mappingToPersist, operation);

		}
		String auditStatus = DomainConstants.AUDIT_STATUS_PUBLISHED;
		if ((mappingToPersist.getDatafeedStatus()!=null)&&(mappingToPersist.getDatafeedStatus().equals(DomainConstants.DATAFEED_STATUS))){
			mappingToPersist.setUser(new User());
			mappingToPersist.getUser().setLastUpdatedUserName(
					DomainConstants.DATAFEED_UPDTD_USER);
		}
		return logMappingInDetail(mappingToPersist, userComments, auditStatus);
	}

	/**
	 * 
	 * The Inner Spring JDBC class for SPSMappingQuery
	 * 
	 */

	protected final class CreateSPSMappingQuery extends SqlUpdate {

		
		private CreateSPSMappingQuery(DataSource dataSource) {
			super(dataSource, getCreateSPSIDMappingQuery());
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.CHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.CHAR));
			compile();
		}

		// EB01_VALUE, EB02_VALUE,
		protected void insert(Mapping mapping) {
			String ebo1Value = null,ebo2Value = null,ebo6Value = null,ebo9Value = null,hsd1Value = null,hsd2Value = null,hsd3Value = null,hsd4Value = null,hsd5Value = null,hsd6Value = null,hsd7Value = null,hsd8Value = null,accum = null;
			if(null!= mapping.getEb01() &&  null!= mapping.getEb01()
					.getHippaCodeSelectedValues() && null != mapping.getEb01()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getEb01()
					.getHippaCodeSelectedValues().isEmpty())){
			ebo1Value = ((HippaCodeValue) mapping.getEb01()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			if(null!= mapping.getEb02() &&  null!= mapping.getEb02()
					.getHippaCodeSelectedValues() && null != mapping.getEb02()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getEb02()
					.getHippaCodeSelectedValues().isEmpty())){
			ebo2Value = ((HippaCodeValue) mapping.getEb02()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			if(null!= mapping.getEb06() &&  null!= mapping.getEb06()
					.getHippaCodeSelectedValues() && null != mapping.getEb06()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getEb06()
					.getHippaCodeSelectedValues().isEmpty())){
			ebo6Value = ((HippaCodeValue) mapping.getEb06()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			if(null!= mapping.getEb09() &&  null!= mapping.getEb09()
					.getHippaCodeSelectedValues() && null != mapping.getEb09()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getEb09()
					.getHippaCodeSelectedValues().isEmpty())){
			ebo9Value = ((HippaCodeValue) mapping.getEb09()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			if(null!= mapping.getHsd01() &&  null!= mapping.getHsd01()
					.getHippaCodeSelectedValues() && null != mapping.getHsd01()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getHsd01()
					.getHippaCodeSelectedValues().isEmpty())){
			hsd1Value = ((HippaCodeValue) mapping.getHsd01()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			if(null!= mapping.getHsd02() &&  null!= mapping.getHsd02()
					.getHippaCodeSelectedValues() && null != mapping.getHsd02()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getHsd02()
					.getHippaCodeSelectedValues().isEmpty())){
			hsd2Value = ((HippaCodeValue) mapping.getHsd02()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			if(null!= mapping.getHsd03() &&  null!= mapping.getHsd03()
					.getHippaCodeSelectedValues() && null != mapping.getHsd03()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getHsd03()
					.getHippaCodeSelectedValues().isEmpty())){
			hsd3Value = ((HippaCodeValue) mapping.getHsd03()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			if(null!= mapping.getHsd04() &&  null!= mapping.getHsd04()
					.getHippaCodeSelectedValues() && null != mapping.getHsd04()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getHsd04()
					.getHippaCodeSelectedValues().isEmpty())){
			hsd4Value = ((HippaCodeValue) mapping.getHsd04()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			if(null!= mapping.getHsd05() &&  null!= mapping.getHsd05()
					.getHippaCodeSelectedValues() && null != mapping.getHsd05()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getHsd05()
					.getHippaCodeSelectedValues().isEmpty())){
			hsd5Value = ((HippaCodeValue) mapping.getHsd05()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			if(null!= mapping.getHsd06() &&  null!= mapping.getHsd06()
					.getHippaCodeSelectedValues() && null != mapping.getHsd06()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getHsd06()
					.getHippaCodeSelectedValues().isEmpty())){
			hsd6Value = ((HippaCodeValue) mapping.getHsd06()
					.getHippaCodeSelectedValues().get(0)).getValue();
			
			}
			
			if(null!= mapping.getHsd07() &&  null!= mapping.getHsd07()
					.getHippaCodeSelectedValues() && null != mapping.getHsd07()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getHsd07()
					.getHippaCodeSelectedValues().isEmpty())){
			hsd7Value = ((HippaCodeValue) mapping.getHsd07()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			if(null!= mapping.getHsd08() &&  null!= mapping.getHsd08()
					.getHippaCodeSelectedValues() && null != mapping.getHsd08()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getHsd08()
					.getHippaCodeSelectedValues().isEmpty())){
			hsd8Value = ((HippaCodeValue) mapping.getHsd08()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			
			if(null!= mapping.getAccum() &&  null!= mapping.getAccum()
					.getHippaCodeSelectedValues() && null != mapping.getAccum()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getAccum()
					.getHippaCodeSelectedValues().isEmpty())){
			accum = ((HippaCodeValue) mapping.getAccum()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			
			String mappingCompleteInd = mapping.isFinalized() ? "Y" : "N";
			Object[] objs = new Object[] { mapping.getSpsId().getSpsId(),
					ebo1Value, ebo2Value,
					ebo6Value, ebo9Value,
					hsd1Value, hsd2Value,
					hsd3Value, hsd4Value,
					hsd5Value, hsd6Value,
					hsd7Value, hsd8Value,
					mapping.getVariableMappingStatus(), accum,
					mappingCompleteInd,
					mapping.getUser().getLastUpdatedUserName(),
				   mapping.getUser().getCreatedUserName(),
					
						mapping.getInTempTable()
			};

			super.update(objs);
		}

	}

	/**
	 * The Inner Spring JDBC class for UpdateMainSPSIdMappingQuery
	 */

	private final class UpdateMainSPSIdMappingQuery extends SqlUpdate {
		
		
		private UpdateMainSPSIdMappingQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.CHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			compile();
		}

		/**
		 * sets the values to be persisted
		 * 
		 * @param mapping
		 * @param hippaValue
		 */
		protected void update(Mapping mapping) {
			String ebo1Value = null,ebo2Value = null,ebo6Value = null,ebo9Value = null,hsd1Value = null,hsd2Value = null,hsd3Value = null,hsd4Value = null,hsd5Value = null,hsd6Value = null,hsd7Value = null,hsd8Value = null,accum = null;

			if(null!= mapping.getEb01() &&  null!= mapping.getEb01()
					.getHippaCodeSelectedValues() && null != mapping.getEb01()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getEb01()
					.getHippaCodeSelectedValues().isEmpty())){
			ebo1Value = ((HippaCodeValue) mapping.getEb01()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			if(null!= mapping.getEb02() &&  null!= mapping.getEb02()
					.getHippaCodeSelectedValues() && null != mapping.getEb02()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getEb02()
					.getHippaCodeSelectedValues().isEmpty())){
			ebo2Value = ((HippaCodeValue) mapping.getEb02()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			if(null!= mapping.getEb06() &&  null!= mapping.getEb06()
					.getHippaCodeSelectedValues() && null != mapping.getEb06()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getEb06()
					.getHippaCodeSelectedValues().isEmpty())){
			ebo6Value = ((HippaCodeValue) mapping.getEb06()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			if(null!= mapping.getEb09() &&  null!= mapping.getEb09()
					.getHippaCodeSelectedValues() && null != mapping.getEb09()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getEb09()
					.getHippaCodeSelectedValues().isEmpty())){
			ebo9Value = ((HippaCodeValue) mapping.getEb09()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			if(null!= mapping.getHsd01() &&  null!= mapping.getHsd01()
					.getHippaCodeSelectedValues() && null != mapping.getHsd01()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getHsd01()
					.getHippaCodeSelectedValues().isEmpty())){
			hsd1Value = ((HippaCodeValue) mapping.getHsd01()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			if(null!= mapping.getHsd02() &&  null!= mapping.getHsd02()
					.getHippaCodeSelectedValues() && null != mapping.getHsd02()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getHsd02()
					.getHippaCodeSelectedValues().isEmpty())){
			hsd2Value = ((HippaCodeValue) mapping.getHsd02()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			if(null!= mapping.getHsd03() &&  null!= mapping.getHsd03()
					.getHippaCodeSelectedValues() && null != mapping.getHsd03()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getHsd03()
					.getHippaCodeSelectedValues().isEmpty())){
			hsd3Value = ((HippaCodeValue) mapping.getHsd03()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			if(null!= mapping.getHsd04() &&  null!= mapping.getHsd04()
					.getHippaCodeSelectedValues() && null != mapping.getHsd04()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getHsd04()
					.getHippaCodeSelectedValues().isEmpty())){
			hsd4Value = ((HippaCodeValue) mapping.getHsd04()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			if(null!= mapping.getHsd05() &&  null!= mapping.getHsd05()
					.getHippaCodeSelectedValues() && null != mapping.getHsd05()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getHsd05()
					.getHippaCodeSelectedValues().isEmpty())){
			hsd5Value = ((HippaCodeValue) mapping.getHsd05()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			if(null!= mapping.getHsd06() &&  null!= mapping.getHsd06()
					.getHippaCodeSelectedValues() && null != mapping.getHsd06()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getHsd06()
					.getHippaCodeSelectedValues().isEmpty())){
			hsd6Value = ((HippaCodeValue) mapping.getHsd06()
					.getHippaCodeSelectedValues().get(0)).getValue();
			
			}
			
			if(null!= mapping.getHsd07() &&  null!= mapping.getHsd07()
					.getHippaCodeSelectedValues() && null != mapping.getHsd07()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getHsd07()
					.getHippaCodeSelectedValues().isEmpty())){
			hsd7Value = ((HippaCodeValue) mapping.getHsd07()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			if(null!= mapping.getHsd08() &&  null!= mapping.getHsd08()
					.getHippaCodeSelectedValues() && null != mapping.getHsd08()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getHsd08()
					.getHippaCodeSelectedValues().isEmpty())){
			hsd8Value = ((HippaCodeValue) mapping.getHsd08()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			
			if(null!= mapping.getAccum() &&  null!= mapping.getAccum()
					.getHippaCodeSelectedValues() && null != mapping.getAccum()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getAccum()
					.getHippaCodeSelectedValues().isEmpty())){
			accum = ((HippaCodeValue) mapping.getAccum()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			String mappingCompleteInd = mapping.isFinalized() ? "Y" : "N";
			String user = null;
			if(null !=mapping.getUser()){
					user = mapping.getUser().getLastUpdatedUserName();
			}
			Object[] objs = new Object[] { 
					ebo1Value, ebo2Value,
					ebo6Value, ebo9Value,
					hsd1Value, hsd2Value,
					hsd3Value, hsd4Value,
					hsd5Value, hsd6Value,
					hsd7Value, hsd8Value,
					mapping.getVariableMappingStatus(), accum,
					mappingCompleteInd,
				    user,	mapping.getSpsId().getSpsId()
					 };

			super.update(objs);
		
	}
	}

	/**
	 * 
	 * The Inner Spring JDBC class for UpdateTmpSPSIdStatusQuery
	 * 
	 */
	private final class UpdateTmpSPSIdStatusQuery extends SqlUpdate {

		private UpdateTmpSPSIdStatusQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			compile();
		}

		/**
		 * sets the values to be persisted
		 * 
		 * @param mapping
		 * @param hippaValue
		 */
		protected void updateStatus(Mapping mapping) {

			Object[] objs = new Object[] { mapping.getVariableMappingStatus(),
					mapping.getUser().getLastUpdatedUserName(),
					mapping.getDataFeedInd(),
					mapping.getSpsId().getSpsId() };

			super.update(objs);
		}

	}

	/**
	 * 
	 * The Inner Spring JDBC class for InsertTmpSPSIdMappingQuery
	 * 
	 */

	private final class InsertTmpSPSIdMappingQuery extends SqlUpdate {
		
		private InsertTmpSPSIdMappingQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.CHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			compile();
		}

		/**
		 * sets the values to be persisted
		 * 
		 * @param mapping
		 * @param hippaValue
		 */
		protected void insert(Mapping mapping) {
			String ebo1Value = null,ebo2Value = null,ebo6Value = null,ebo9Value = null,hsd1Value = null,hsd2Value = null,hsd3Value = null,hsd4Value = null,hsd5Value = null,hsd6Value = null,hsd7Value = null,hsd8Value = null,accum = null;
			
			if(null!= mapping.getEb01() &&  null!= mapping.getEb01()
					.getHippaCodeSelectedValues() && null != mapping.getEb01()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getEb01()
					.getHippaCodeSelectedValues().isEmpty())){
			ebo1Value = ((HippaCodeValue) mapping.getEb01()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			if(null!= mapping.getEb02() &&  null!= mapping.getEb02()
					.getHippaCodeSelectedValues() && null != mapping.getEb02()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getEb02()
					.getHippaCodeSelectedValues().isEmpty())){
			ebo2Value = ((HippaCodeValue) mapping.getEb02()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			if(null!= mapping.getEb06() &&  null!= mapping.getEb06()
					.getHippaCodeSelectedValues() && null != mapping.getEb06()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getEb06()
					.getHippaCodeSelectedValues().isEmpty())){
			ebo6Value = ((HippaCodeValue) mapping.getEb06()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			if(null!= mapping.getEb09() &&  null!= mapping.getEb09()
					.getHippaCodeSelectedValues() && null != mapping.getEb09()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getEb09()
					.getHippaCodeSelectedValues().isEmpty())){
			ebo9Value = ((HippaCodeValue) mapping.getEb09()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			if(null!= mapping.getHsd01() &&  null!= mapping.getHsd01()
					.getHippaCodeSelectedValues() && null != mapping.getHsd01()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getHsd01()
					.getHippaCodeSelectedValues().isEmpty())){
			hsd1Value = ((HippaCodeValue) mapping.getHsd01()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			if(null!= mapping.getHsd02() &&  null!= mapping.getHsd02()
					.getHippaCodeSelectedValues() && null != mapping.getHsd02()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getHsd02()
					.getHippaCodeSelectedValues().isEmpty())){
			hsd2Value = ((HippaCodeValue) mapping.getHsd02()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			if(null!= mapping.getHsd03() &&  null!= mapping.getHsd03()
					.getHippaCodeSelectedValues() && null != mapping.getHsd03()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getHsd03()
					.getHippaCodeSelectedValues().isEmpty())){
			hsd3Value = ((HippaCodeValue) mapping.getHsd03()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			if(null!= mapping.getHsd04() &&  null!= mapping.getHsd04()
					.getHippaCodeSelectedValues() && null != mapping.getHsd04()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getHsd04()
					.getHippaCodeSelectedValues().isEmpty())){
			hsd4Value = ((HippaCodeValue) mapping.getHsd04()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			if(null!= mapping.getHsd05() &&  null!= mapping.getHsd05()
					.getHippaCodeSelectedValues() && null != mapping.getHsd05()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getHsd05()
					.getHippaCodeSelectedValues().isEmpty())){
			hsd5Value = ((HippaCodeValue) mapping.getHsd05()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			if(null!= mapping.getHsd06() &&  null!= mapping.getHsd06()
					.getHippaCodeSelectedValues() && null != mapping.getHsd06()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getHsd06()
					.getHippaCodeSelectedValues().isEmpty())){
			hsd6Value = ((HippaCodeValue) mapping.getHsd06()
					.getHippaCodeSelectedValues().get(0)).getValue();
			
			}
			
			if(null!= mapping.getHsd07() &&  null!= mapping.getHsd07()
					.getHippaCodeSelectedValues() && null != mapping.getHsd07()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getHsd07()
					.getHippaCodeSelectedValues().isEmpty())){
			hsd7Value = ((HippaCodeValue) mapping.getHsd07()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			if(null!= mapping.getHsd08() &&  null!= mapping.getHsd08()
					.getHippaCodeSelectedValues() && null != mapping.getHsd08()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getHsd08()
					.getHippaCodeSelectedValues().isEmpty())){
			hsd8Value = ((HippaCodeValue) mapping.getHsd08()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			
			if(null!= mapping.getAccum() &&  null!= mapping.getAccum()
					.getHippaCodeSelectedValues() && null != mapping.getAccum()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getAccum()
					.getHippaCodeSelectedValues().isEmpty())){
			accum = ((HippaCodeValue) mapping.getAccum()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			
			String mappingCompleteInd = mapping.isFinalized() ? "Y" : "N";
			Object[] objs = new Object[] { mapping.getSpsId().getSpsId(),
					ebo1Value, ebo2Value,
					ebo6Value, ebo9Value,
					hsd1Value, hsd2Value,
					hsd3Value, hsd4Value,
					hsd5Value, hsd6Value,
					hsd7Value, hsd8Value,
					mapping.getVariableMappingStatus(), accum,
					mappingCompleteInd,mapping.getUser().getLastUpdatedUserName(), 
					mapping.getUser().getCreatedUserName()

			};

			super.update(objs);
		}

	}

	/**
	 * 
	 * The Inner Spring JDBC class for DeleteTmpSPSIdMappingQuery
	 * 
	 */

	private final class DeleteTmpSPSIdMappingQuery extends SqlUpdate {

		private DeleteTmpSPSIdMappingQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			declareParameter(new SqlParameter(Types.VARCHAR));
			compile();
		}

		/**
		 * sets the values to be persisted
		 * 
		 * @param mapping
		 * @param hippaValue
		 */
		protected void delete(Mapping mapping) {

			Object[] objs = new Object[] { mapping.getSpsId().getSpsId() };
			super.update(objs);
		}
	}

	/**
	 * 
	 * The Inner Spring JDBC class for UpdateTmpSPSIdMappingQuery
	 * 
	 */
	private final class UpdateTmpSPSIdMappingQuery extends SqlUpdate {
		
		private UpdateTmpSPSIdMappingQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.CHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			compile();
		}

		/**
		 * sets the values to be persisted
		 * 
		 * @param mapping
		 * @param hippaValue
		 */
		protected void update(Mapping mapping) {
			
			String ebo1Value = null,ebo2Value = null,ebo6Value = null,ebo9Value = null,hsd1Value = null,hsd2Value = null,hsd3Value = null,hsd4Value = null,hsd5Value = null,hsd6Value = null,hsd7Value = null,hsd8Value = null,accum = null;

			if(null!= mapping.getEb01() &&  null!= mapping.getEb01()
					.getHippaCodeSelectedValues() && null != mapping.getEb01()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getEb01()
					.getHippaCodeSelectedValues().isEmpty())){
			ebo1Value = ((HippaCodeValue) mapping.getEb01()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			if(null!= mapping.getEb02() &&  null!= mapping.getEb02()
					.getHippaCodeSelectedValues() && null != mapping.getEb02()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getEb02()
					.getHippaCodeSelectedValues().isEmpty())){
			ebo2Value = ((HippaCodeValue) mapping.getEb02()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			if(null!= mapping.getEb06() &&  null!= mapping.getEb06()
					.getHippaCodeSelectedValues() && null != mapping.getEb06()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getEb06()
					.getHippaCodeSelectedValues().isEmpty())){
			ebo6Value = ((HippaCodeValue) mapping.getEb06()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			if(null!= mapping.getEb09() &&  null!= mapping.getEb09()
					.getHippaCodeSelectedValues() && null != mapping.getEb09()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getEb09()
					.getHippaCodeSelectedValues().isEmpty())){
			ebo9Value = ((HippaCodeValue) mapping.getEb09()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			if(null!= mapping.getHsd01() &&  null!= mapping.getHsd01()
					.getHippaCodeSelectedValues() && null != mapping.getHsd01()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getHsd01()
					.getHippaCodeSelectedValues().isEmpty())){
			hsd1Value = ((HippaCodeValue) mapping.getHsd01()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			if(null!= mapping.getHsd02() &&  null!= mapping.getHsd02()
					.getHippaCodeSelectedValues() && null != mapping.getHsd02()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getHsd02()
					.getHippaCodeSelectedValues().isEmpty())){
			hsd2Value = ((HippaCodeValue) mapping.getHsd02()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			if(null!= mapping.getHsd03() &&  null!= mapping.getHsd03()
					.getHippaCodeSelectedValues() && null != mapping.getHsd03()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getHsd03()
					.getHippaCodeSelectedValues().isEmpty())){
			hsd3Value = ((HippaCodeValue) mapping.getHsd03()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			if(null!= mapping.getHsd04() &&  null!= mapping.getHsd04()
					.getHippaCodeSelectedValues() && null != mapping.getHsd04()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getHsd04()
					.getHippaCodeSelectedValues().isEmpty())){
			hsd4Value = ((HippaCodeValue) mapping.getHsd04()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			if(null!= mapping.getHsd05() &&  null!= mapping.getHsd05()
					.getHippaCodeSelectedValues() && null != mapping.getHsd05()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getHsd05()
					.getHippaCodeSelectedValues().isEmpty())){
			hsd5Value = ((HippaCodeValue) mapping.getHsd05()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			if(null!= mapping.getHsd06() &&  null!= mapping.getHsd06()
					.getHippaCodeSelectedValues() && null != mapping.getHsd06()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getHsd06()
					.getHippaCodeSelectedValues().isEmpty())){
			hsd6Value = ((HippaCodeValue) mapping.getHsd06()
					.getHippaCodeSelectedValues().get(0)).getValue();
			
			}
			
			if(null!= mapping.getHsd07() &&  null!= mapping.getHsd07()
					.getHippaCodeSelectedValues() && null != mapping.getHsd07()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getHsd07()
					.getHippaCodeSelectedValues().isEmpty())){
			hsd7Value = ((HippaCodeValue) mapping.getHsd07()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			if(null!= mapping.getHsd08() &&  null!= mapping.getHsd08()
					.getHippaCodeSelectedValues() && null != mapping.getHsd08()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getHsd08()
					.getHippaCodeSelectedValues().isEmpty())){
			hsd8Value = ((HippaCodeValue) mapping.getHsd08()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			
			if(null!= mapping.getAccum() &&  null!= mapping.getAccum()
					.getHippaCodeSelectedValues() && null != mapping.getAccum()
					.getHippaCodeSelectedValues().get(0) &&  (!mapping.getAccum()
					.getHippaCodeSelectedValues().isEmpty())){
			accum = ((HippaCodeValue) mapping.getAccum()
					.getHippaCodeSelectedValues().get(0)).getValue();
			}
			
			String mappingCompleteInd = mapping.isFinalized() ? "Y" : "N";
			
			Object[] objs = new Object[] { 
					ebo1Value, ebo2Value,
					ebo6Value, ebo9Value,
					hsd1Value, hsd2Value,
					hsd3Value, hsd4Value,
					hsd5Value, hsd6Value,
					hsd7Value, hsd8Value,
					mapping.getVariableMappingStatus(), accum,
                   	mappingCompleteInd,
					mapping.getUser().getLastUpdatedUserName(),
					mapping.getSpsId().getSpsId() };

			super.update(objs);
		
	}
	}
	/**
	 * The Inner Spring JDBC class for UpdateMainSPSIdStatusQuery
	 */
	private final class UpdateMainSPSIdStatusQuery extends SqlUpdate {
		private UpdateMainSPSIdStatusQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			compile();
		}

		/**
		 * sets the values to be persisted
		 * 
		 * @param mapping
		 * @param hippaValue
		 */
		protected void updateStatus(Mapping mapping) {

			Object[] objs = new Object[] {

			mapping.getVariableMappingStatus(),
					mapping.getUser().getLastUpdatedUserName(),
					mapping.getDataFeedInd(),
					mapping.getSpsId().getSpsId() };
			super.update(objs);
		}
	}

	/**
	 * The Inner Spring JDBC class for UpdateMainInTempQuery
	 */
	private final class UpdateMainInTempQuery extends SqlUpdate {

		private UpdateMainInTempQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			declareParameter(new SqlParameter(Types.CHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			compile();
		}

		/**
		 * sets the values to be persisted
		 * 
		 * @param mapping
		 * @param hippaValue
		 */
		protected void updateInTemp(Mapping mapping, String inTemp) {

			Object[] objs = new Object[] {

			inTemp, mapping.getUser().getLastUpdatedUserName(),
					mapping.getSpsId().getSpsId() };
			super.update(objs);
		}
	}
	
	/**
	 * The Inner Spring JDBC class for UpdateMainInTempQuery
	 */
	private final class UpdateMainInTempAndStatusQuery extends SqlUpdate {

		private UpdateMainInTempAndStatusQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			declareParameter(new SqlParameter(Types.CHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			
			compile();
		}

		/**
		 * sets the values to be persisted
		 * 
		 * @param mapping
		 * @param hippaValue
		 */
		protected void update(Mapping mapping, String inTemp) {

			Object[] objs = new Object[] {

			inTemp, mapping.getUser().getLastUpdatedUserName(),
					 mapping.getVariableMappingStatus(), mapping.getSpsId().getSpsId() };
			
			super.update(objs);
		}
	}
	/**
	 * 
	 * Inner class to persist audit trail
	 * 
	 */
	private final class CreateAuditTrailSPSIdQuery extends SqlUpdate {

		private CreateAuditTrailSPSIdQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			compile();
		}

		protected void insert(AuditTrail auditTrail) {
			Object[] objs = new Object[] { auditTrail.getSpsId(),
					auditTrail.getSystemComments(),
					auditTrail.getUserComments(), auditTrail.getCreatedUser(),
					auditTrail.getMappingStatus() };
			super.update(objs);
		}
	}

	/**
	 * The method to insert into audit trail table
	 * 
	 */
	private boolean insertAuditTrail(AuditTrail auditTrail) {

		if (auditTrail != null) {
			CreateAuditTrailSPSIdQuery persistAuditTrailQuery = new CreateAuditTrailSPSIdQuery(
					dataSource, getInsertSPSAuditTrailQuery());
			persistAuditTrailQuery.insert(auditTrail);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * driver method to log into audit trail
	 * 
	 * @param mapping
	 * @param userComments
	 * @param auditStatus
	 * @return
	 */
	private boolean logMapping(Mapping mapping, String userComments,
			String auditStatus, String systemComments) {

		if (mapping == null || mapping.getVariableMappingStatus() == null) {
			throw new DomainException(
					"Invalid Mapping Object! Cannot log Audit details.");
		}
		AuditTrail auditTrail = createAuditTrailObject(mapping, userComments,
				auditStatus, systemComments);
		return insertAuditTrail(auditTrail);
	}

	private boolean logMappingInDetail(Mapping mapping, String userComments,
			String auditStatus) {
		if (null == mapping|| null == mapping.getVariableMappingStatus()) {
			throw new DomainException(
					"Invalid Mapping Object! Cannot log Audit in detail.");
		}
		HippaSegmentMappingVO hippaSegmentMappingVO = createHippaSegmentMappingVO(mapping);
		XStream stream = new XStream();
		stream.allowTypes(new Class[] {HippaSegmentMappingVO.class});
		stream.alias("mapping", HippaSegmentMappingVO.class);
		String mapingXml = stream.toXML(hippaSegmentMappingVO);
		
		AuditTrail auditTrail = createAuditTrailObject(mapping, userComments,
				auditStatus, mapingXml);
		return insertAuditTrail(auditTrail);
	}

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
		hippaSegmentMappingVO.setAccum(BxUtil
				.getAsCSV(mapping.getAccumValues()));
		hippaSegmentMappingVO.setMessage(mapping.getMessage());
		hippaSegmentMappingVO.setMessageTypeRequired(mapping
				.getMsg_type_required());
		hippaSegmentMappingVO.setSensitiveBenefitIndicator(mapping
				.getSensitiveBenefitIndicator());
		hippaSegmentMappingVO.setNoteTypeCode(mapping.getNoteTypeCodeValue());
		return hippaSegmentMappingVO;
	}

	/**
	 * Method to create and populate AuditTrail object
	 * 
	 * @param mapping
	 * @param userCmnts
	 * @param auditStatus
	 * @param systemComments
	 *            TODO
	 * @return
	 */

	private AuditTrail createAuditTrailObject(Mapping mapping,
			String userCmnts, String auditStatus, String systemComments) {

		AuditTrail auditTrail = new AuditTrail();

		auditTrail.setSpsId(mapping.getSpsId().getSpsId());
		auditTrail.setMappingStatus(auditStatus);
		auditTrail.setUserComments(userCmnts);
		auditTrail.setSystemComments(systemComments);
		String auditUser = mapping.getUser().getLastUpdatedUserName();
		auditTrail.setCreatedUser(auditUser);
		return auditTrail;
	}

	/**
	 * 
	 * @return
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * 
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * 
	 * @return
	 */
	public String getCreateSPSIDMappingQuery() {
		return createSPSIDMappingQuery;
	}

	/**
	 * 
	 * @param CreateSPSIDMappingQuery
	 */
	public void setCreateSPSIDMappingQuery(String createSPSIDMappingQuery) {
		this.createSPSIDMappingQuery = createSPSIDMappingQuery;
	}

	/**
	 * 
	 * @return
	 */
	public String getInsertSPSAuditTrailQuery() {
		return insertSPSAuditTrailQuery;
	}

	/**
	 * 
	 * @param insertSPSAuditTrailQuery
	 */
	public void setInsertSPSAuditTrailQuery(String insertSPSAuditTrailQuery) {
		this.insertSPSAuditTrailQuery = insertSPSAuditTrailQuery;
	}

	/**
	 * 
	 * @return
	 */
	public String getUpdateIsModifiedSPSIDMappingQuery() {
		return updateIsModifiedSPSIDMappingQuery;
	}

	/**
	 * 
	 * @param updateIsModifiedSPSIDMappingQuery
	 */
	public void setUpdateIsModifiedSPSIDMappingQuery(
			String updateIsModifiedSPSIDMappingQuery) {
		this.updateIsModifiedSPSIDMappingQuery = updateIsModifiedSPSIDMappingQuery;
	}

	/**
	 * discarding changes to an already Published mapping
	 * 
	 * @param mapping
	 * @return MappingResult
	 */
	public boolean discardChanges(Mapping mapping, String userComments) {
		updateOperation(mapping, "DELETE_TEMP");
		return true;
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
	 * 
	 * @return
	 */
	public String getDeleteTmpSPSIdMappingQuery() {
		return deleteTmpSPSIdMappingQuery;
	}

	/**
	 * 
	 * @param deleteTmpSPSIdMappingQuery
	 */
	public void setDeleteTmpSPSIdMappingQuery(String deleteTmpSPSIdMappingQuery) {
		this.deleteTmpSPSIdMappingQuery = deleteTmpSPSIdMappingQuery;
	}

	/**
	 * 
	 * @return
	 */
	public String getInsertTmpSPSIdMappingQuery() {
		return insertTmpSPSIdMappingQuery;
	}

	/**
	 * 
	 * @param insertTmpSPSIdMappingQuery
	 */
	public void setInsertTmpSPSIdMappingQuery(String insertTmpSPSIdMappingQuery) {
		this.insertTmpSPSIdMappingQuery = insertTmpSPSIdMappingQuery;
	}

	/**
	 * 
	 * @return
	 */
	public String getUpdateMainSPSIdMappingQuery() {
		return updateMainSPSIdMappingQuery;
	}

	public void setUpdateMainSPSIdMappingQuery(
			String updateMainSPSIdMappingQuery) {
		this.updateMainSPSIdMappingQuery = updateMainSPSIdMappingQuery;
	}

	/**
	 * 
	 * @return
	 */
	public String getUpdateMainSPSIdStatusQuery() {
		return updateMainSPSIdStatusQuery;
	}

	/**
	 * 
	 * @param updateMainSPSIdStatusQuery
	 */
	public void setUpdateMainSPSIdStatusQuery(String updateMainSPSIdStatusQuery) {
		this.updateMainSPSIdStatusQuery = updateMainSPSIdStatusQuery;
	}

	/**
	 * 
	 * @return
	 */
	public String getUpdateTmpSPSIdMappingQuery() {
		return updateTmpSPSIdMappingQuery;
	}

	/**
	 * 
	 * @param updateTmpSPSIdMappingQuery
	 */
	public void setUpdateTmpSPSIdMappingQuery(String updateTmpSPSIdMappingQuery) {
		this.updateTmpSPSIdMappingQuery = updateTmpSPSIdMappingQuery;
	}

	/**
	 * 
	 * @return
	 */
	public String getUpdateTmpSPSIdStatusQuery() {
		return updateTmpSPSIdStatusQuery;
	}

	/**
	 * 
	 * @param updateTmpSPSIdStatusQuery
	 */
	public void setUpdateTmpSPSIdStatusQuery(String updateTmpSPSIdStatusQuery) {
		this.updateTmpSPSIdStatusQuery = updateTmpSPSIdStatusQuery;
	}

	/**
	 * 
	 * @return
	 */
	public String getUpdateInTempQuery() {
		return updateInTempQuery;
	}

	/**
	 * 
	 * @param updateInTempQuery
	 */
	public void setUpdateInTempQuery(String updateInTempQuery) {
		this.updateInTempQuery = updateInTempQuery;
	}
	/**
	 * This method updates the feed_run_flag as �T� after the datafeed to IMSN region is executed 
	 * and to �P� after the datafeed to IMSP region is executed.
	 * @param Mapping
	 * @return boolean
	 */
	public boolean updateDatafeedStatus(Mapping mapping) {
	 return false;
	}
	
	public String getUpdateMainInTempAndStatusQuery() {
		return updateMainInTempAndStatusQuery;
	}
	
	public void setUpdateMainInTempAndStatusQuery(
			String updateMainInTempAndStatusQuery) {
		this.updateMainInTempAndStatusQuery = updateMainInTempAndStatusQuery;
	}
	
	public SPSMappingRetrieveResult getSPSMappingMain(String spsId){
		return null;
	}
}
