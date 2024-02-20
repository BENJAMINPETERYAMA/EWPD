/*
 * <MappingCustomMessageRepositoryImpl.java>
 *
 * � 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.mapping.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

import org.apache.log4j.Logger;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.wellpoint.ets.bx.mapping.domain.entity.AuditTrail;
import com.wellpoint.ets.bx.mapping.domain.entity.EB03Association;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.SPSId;
import com.wellpoint.ets.bx.mapping.domain.entity.SPSMappingRetrieveResult;
import com.wellpoint.ets.bx.mapping.domain.exception.DomainException;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.vo.HippaSegmentMappingVO;
import com.wellpoint.ets.bx.mapping.domain.vo.User;
import com.wellpoint.ets.bx.mapping.util.BxUtil;

/**
 * @author UST-GLOBAL This is an implementation class for performing all the life
 *         cycle operations on Custom message mapping
 */
public class MappingCustomMessageRepositoryImpl implements MappingRepository {
	
	private static Logger logger = Logger.getLogger(MappingCustomMessageRepositoryImpl.class);

	private LocateRepository locateCustomMessageRepository;
	
	private DataSource dataSource;

	private String createCustomMessageSQL;

	private String createCustomMessageTempSQL;

	private String insertAuditTrailSQL;

	private String checkInTempFlag;

	private String updateCustomMessageSQL;

	private String updateCustomMessageTempSQL;

	private String updateInTempTableFlagSQL;

	private String deleteCustomMessageTempTableSQL;
	
	private String createCustomMessageDataFeedSQL;
	
	private String deleteCustomMessageMainTableSQL;
	
	private String updateCustomMessageDataFeedSQL;
	
	private String updateCustomMessageStatusSQL;
	
	private String updateCustomMessageStatusTempSQL;

	/**
	 * Creating a new Mapping
	 * 
	 * @param mapping
	 * @param userComments
	 * @return MappingResult
	 */
	public boolean create(Mapping mapping, String userComments) {

		boolean isAuditPersisted = false;
		Date createdDate = new Date();
		mapping.setCreatedTmStamp(createdDate);
		this.insertMapping(mapping);

		String auditStatus = BxUtil.getAuditStatus(mapping
				.getVariableMappingStatus());

		isAuditPersisted = logMapping(mapping, userComments, auditStatus);

		return isAuditPersisted;
	}

	/**
	 * discarding changes to an already Published mapping
	 * @param userComments
	 * @param mapping
	 * @return MappingResult
	 */
	public boolean discardChanges(Mapping mapping, String userComments) {

		boolean isAuditPersisted = false;
		
if(null!=mapping.getRule().getHeaderRuleId()&&null!=mapping.getSpsId().getSpsId()){
	 // Delete the entry from temp table.
	this.deleteMappingTemp(mapping);
       //Update the inTempTable flag to "N"
	this.updateInTempTableFlag(mapping, DomainConstants.IN_TEMP_TAB_FLAG_PERSIST);
	mapping.setVariableMappingStatus(DomainConstants.STATUS_PUBLISHED);
	this.updateStatusForDiscardChanges(mapping);
		
}
		// Creating Audit trail
		String auditStatus = DomainConstants.AUDIT_STATUS_DISCARD_CHANGES;
		
		isAuditPersisted = logMapping(mapping, userComments, auditStatus);
		return isAuditPersisted;

	}

	/**
	 * updating an existing mapping
	 * 
	 * @param mapping
	 * @param userComments
	 * @return MappingResult
	 * @throws ParseException 

	 */
	public boolean update(Mapping mapping, String userComments, String operation) {
		boolean isAuditPersisted = false;

		this.updateOperation(mapping, operation);

		String auditStatus = BxUtil.getAuditStatus(mapping
				.getVariableMappingStatus());

		isAuditPersisted = logMapping(mapping, userComments, auditStatus);

		return isAuditPersisted;
	}

	/**
	 * The methods (sendToTest, approve,notApplicable) in fa�ade will invoke this method for updating the status  of the mapping
	 * @param mapping
	 * @param userComments
	 * @param status
	 * @return MappingResult

	 */
	public boolean updateStatus(Mapping mapping, String userComments,
			String operation) {
		boolean isAuditPersisted = false;

		this.updateOperation(mapping, operation);

		String auditStatus = BxUtil.getAuditStatus(mapping
				.getVariableMappingStatus());
		if ((mapping.getDatafeedStatus()!=null)&&(mapping.getDatafeedStatus().equals(DomainConstants.DATAFEED_STATUS))){
			mapping.setUser(new User());
			mapping.getUser().setLastUpdatedUserName(
					DomainConstants.DATAFEED_UPDTD_USER);
			mapping.getUser().setCreatedUserName(DomainConstants.DATAFEED_UPDTD_USER);
		}
		isAuditPersisted = logMapping(mapping, userComments, auditStatus);

		return isAuditPersisted;

	}
	
	
	/*
	 * The method called by the Datafeed to publish the MAPPINGS IN 'SCHEDULED_TO_PRODUCTION'
	 * @see com.wellpoint.ets.ebx.mapping.repository.MappingRepository#publish(com.wellpoint.ets.bx.mapping.domain.entity.Mapping, java.lang.String)
	 */
	public boolean publish(Mapping mappingToPersist, String userComments) {

		String operation = null;
		if (mappingToPersist.getInTempTable().equals("N")) {

			operation = DomainConstants.UPDATE_MAIN_OPERATION;
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
			mappingToPersist.getUser().setCreatedUserName(DomainConstants.DATAFEED_UPDTD_USER);
		}
		return logMappingInDetail(mappingToPersist, userComments, auditStatus);
	}

	
	/**
	 * Driver method to log the xml string of the mapping
	 * @param mapping
	 * @param userComments
	 * @param auditStatus
	 * @param systemComments
	 * @return
	 */
	private boolean logMappingInDetail(Mapping mapping, String userComments,String auditStatus) {
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
	 * 
	 * 	Created the value object from which the xml is created
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
		hippaSegmentMappingVO.setMessage(mapping.getMessage());
		hippaSegmentMappingVO.setMessageTypeRequired(mapping.getMsg_type_required());
		hippaSegmentMappingVO.setSensitiveBenefitIndicator(mapping.getSensitiveBenefitIndicator());
		hippaSegmentMappingVO.setNoteTypeCode(mapping.getNoteTypeCodeValue());
		return hippaSegmentMappingVO;
	}
	/**
	 * 
	 * A private method for creating a custom message in main table
	 *
	 */
	private final class CreateCustomMessage extends SqlUpdate {

		private CreateCustomMessage(DataSource dataSource) {
			super(dataSource, createCustomMessageSQL);
			declareParameter(new SqlParameter("HDR_RULE_ID", Types.VARCHAR));
			declareParameter(new SqlParameter("SPS_ID", Types.VARCHAR));
			declareParameter(new SqlParameter("MSG_RQRD_IND", Types.CHAR));
			declareParameter(new SqlParameter("MSG_TEXT", Types.VARCHAR));
			declareParameter(new SqlParameter("CREATD_USER_ID", Types.VARCHAR));
			declareParameter(new SqlParameter("CREATD_TM_STMP", Types.DATE));
			declareParameter(new SqlParameter("LST_CHG_USR", Types.VARCHAR));
			declareParameter(new SqlParameter("NOTE_TYP_CD", Types.VARCHAR));
			declareParameter(new SqlParameter("STATUS_CD", Types.VARCHAR));
			declareParameter(new SqlParameter("IN_TEMP_TAB", Types.CHAR));
			//Added as part of SSCR 19537
			declareParameter(new SqlParameter("EB03", Types.VARCHAR));
			declareParameter(new SqlParameter("INDVDL_EB03_ASSN_IND", Types.VARCHAR));
			compile();
		}
	}
/**
 * 
 * A private method for creating a custom message in temp table
 *
 */
	private final class CreateCustomMessageTemp extends SqlUpdate {

		private CreateCustomMessageTemp(DataSource dataSource) {
			super(dataSource, createCustomMessageTempSQL);
			declareParameter(new SqlParameter("HDR_RULE_ID", Types.VARCHAR));
			declareParameter(new SqlParameter("SPS_ID", Types.VARCHAR));
			declareParameter(new SqlParameter("MSG_RQRD_IND", Types.CHAR));
			declareParameter(new SqlParameter("MSG_TEXT", Types.VARCHAR));
			declareParameter(new SqlParameter("CREATD_USER_ID", Types.VARCHAR));
			declareParameter(new SqlParameter("CREATD_TM_STMP", Types.DATE));
			declareParameter(new SqlParameter("LST_CHG_USR", Types.VARCHAR));
			declareParameter(new SqlParameter("NOTE_TYP_CD", Types.VARCHAR));
			declareParameter(new SqlParameter("STATUS_CD", Types.VARCHAR));
			//Added as part of SSCR 19537
			declareParameter(new SqlParameter("EB03", Types.VARCHAR));
			declareParameter(new SqlParameter("INDVDL_EB03_ASSN_IND", Types.VARCHAR));

			compile();
		}
	}
/**
 * 
 * A private method for inserting an audit trail
 *
 */
	private final class InsertAuditTrail extends SqlUpdate {

		private InsertAuditTrail(DataSource dataSource) {
			super(dataSource, insertAuditTrailSQL);
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			compile();
		}

	}
/**
 * 
 * A private method for Updating the  custom message 
 *
 */
	private final class UpdateCustomMessage extends SqlUpdate {

		private UpdateCustomMessage(DataSource dataSource, String query) {
			super(dataSource, query);
			declareParameter(new SqlParameter("MSG_RQRD_IND", Types.CHAR));
			declareParameter(new SqlParameter("MSG_TEXT", Types.VARCHAR));
			declareParameter(new SqlParameter("CREATD_USER_ID", Types.VARCHAR));
			declareParameter(new SqlParameter("LST_CHG_USR", Types.VARCHAR));
			declareParameter(new SqlParameter("NOTE_TYP_CD", Types.VARCHAR));
			declareParameter(new SqlParameter("STATUS_CD", Types.VARCHAR));
			declareParameter(new SqlParameter("DATA_FEED_IND", Types.VARCHAR));
			declareParameter(new SqlParameter("IN_TEMP_TAB", Types.CHAR));
			//Changes added as part of SSCR 19537
			declareParameter(new SqlParameter("EB03", Types.VARCHAR));
			declareParameter(new SqlParameter("INDVDL_EB03_ASSN_IND", Types.VARCHAR));
			//Ends Here
			declareParameter(new SqlParameter("HDR_RULE_ID", Types.VARCHAR));
			declareParameter(new SqlParameter("SPS_ID", Types.VARCHAR));

			compile();
		}
	}
	/**
	 * 
	 * A private method for updating the custom message in temp table
	 *
	 */
	private final class UpdateTempCustomMessage extends SqlUpdate {

		private UpdateTempCustomMessage(DataSource dataSource, String query) {
			super(dataSource, query);
			declareParameter(new SqlParameter("MSG_RQRD_IND", Types.CHAR));
			declareParameter(new SqlParameter("MSG_TEXT", Types.VARCHAR));
			declareParameter(new SqlParameter("CREATD_USER_ID", Types.VARCHAR));
			declareParameter(new SqlParameter("LST_CHG_USR", Types.VARCHAR));
			declareParameter(new SqlParameter("NOTE_TYP_CD", Types.VARCHAR));
			declareParameter(new SqlParameter("STATUS_CD", Types.VARCHAR));
			declareParameter(new SqlParameter("DATA_FEED_IND", Types.VARCHAR));
			//Changes added as part of SSCR 19537
			declareParameter(new SqlParameter("EB03", Types.VARCHAR));
			declareParameter(new SqlParameter("INDVDL_EB03_ASSN_IND", Types.VARCHAR));
			//Ends Here
			declareParameter(new SqlParameter("HDR_RULE_ID", Types.VARCHAR));
			declareParameter(new SqlParameter("SPS_ID", Types.VARCHAR));
			compile();
		}
	}
	/**
	 * 
	 * A private method for updating the inTemp Flag
	 *
	 */
	private final class UpdateInTempTableFlag extends SqlUpdate {

		private UpdateInTempTableFlag(DataSource dataSource, String query) {
			super(dataSource, query);
			declareParameter(new SqlParameter("IN_TEMP_TAB", Types.CHAR));
			declareParameter(new SqlParameter("HDR_RULE_ID", Types.VARCHAR));
			declareParameter(new SqlParameter("SPS_ID", Types.VARCHAR));
			compile();
		}
	}
	/**
	 * 
	 * A private method for deleting the custom Message from temp table
	 *
	 */
private final class DeleteCustomMessageTempTable extends SqlUpdate {

		public DeleteCustomMessageTempTable(DataSource dataSource, String query) {
			super(dataSource, query);
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));

			compile();
		}

	}
	/**
	 * 
	 * A private method for deleting the custom Message from main table when the delete button is clicked
	 *
	 */
	 private final class DeleteCustomMessageMainTable extends SqlUpdate {

		public DeleteCustomMessageMainTable(DataSource dataSource, String query) {
			super(dataSource, query);
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));

			compile();
		}

	}
	 /**
	  * 
	  * A private method for creating an entry of custom Message into the DEL_CSTM_MSG_TXT table when the delete button is clicked
	  *
	  */
	private final class CreateCustomMessageDataFeed extends SqlUpdate {
		CreateCustomMessageDataFeed(DataSource dataSource) {
			super(dataSource, createCustomMessageDataFeedSQL);
			declareParameter(new SqlParameter("HDR_RULE_ID", Types.VARCHAR));
			declareParameter(new SqlParameter("SPS_ID", Types.VARCHAR));
			declareParameter(new SqlParameter("MSG_TEXT", Types.VARCHAR));
			declareParameter(new SqlParameter("CREATD_USER_ID", Types.VARCHAR));
			declareParameter(new SqlParameter("LST_CHG_USR", Types.VARCHAR));
			declareParameter(new SqlParameter("FEED_RUN_FLG", Types.CHAR));
			compile();
		}
	}
	/**
	 * 
	 * A private method for updating the feed run flag in the DEL_CSTM_MSG_TXT table 
	 *
	 */
	private final class UpdateCustomMessageDataFeed extends SqlUpdate {
		UpdateCustomMessageDataFeed(DataSource dataSource,String query) {
			super(dataSource, query);
			declareParameter(new SqlParameter("FEED_RUN_FLG", Types.CHAR));
			declareParameter(new SqlParameter("LST_CHG_USR", Types.VARCHAR));
			declareParameter(new SqlParameter("HDR_RULE_ID", Types.VARCHAR));
			declareParameter(new SqlParameter("SPS_ID", Types.VARCHAR));
			compile();
		}
	}
	/**
	 * 
	 * @param mapping
	 * @param userCmnts
	 * @param auditStatus
	 * @param systemComments
	 * @return auditTrail
	 */
	private  AuditTrail createAuditTrailObject(Mapping mapping,
			String userCmnts, String auditStatus, String systemComments) {

		AuditTrail auditTrail = new AuditTrail();
		String auditUser = null;
		if (mapping.getSpsId() != null) {
			auditTrail.setSpsId(mapping.getSpsId().getSpsId());
		}
		if (mapping.getRule() != null) {
			auditTrail.setRuleId(mapping.getRule().getHeaderRuleId());
		}
		auditTrail.setMappingStatus(auditStatus);
		auditTrail.setUserComments(userCmnts);
		auditTrail.setSystemComments(systemComments);
		auditUser = mapping.getUser().getCreatedUserName();
		if (null == auditUser) {
			throw new DomainException("Cannot log Mapping! User not found");
		}
		auditTrail.setCreatedUser(auditUser);

		return auditTrail;
	}
/**
 * 
 * @param mapping
 */
	private void deleteMappingTemp(Mapping mapping) {
		DeleteCustomMessageTempTable deleteCustomMessageTempTable = new DeleteCustomMessageTempTable(
				dataSource, getDeleteCustomMessageTempTableSQL());
		deleteCustomMessageTempTable.update(new Object[] {
				mapping.getRule().getHeaderRuleId(),
				mapping.getSpsId().getSpsId() });

	}
	
	/**to update the status while clicking on discard changes
	 * 
	 * @param mapping
	 */
		private void updateStatusForDiscardChanges(Mapping mapping) {
			UpdateCustomMessageStatus updateCustomMessageStatusForDiscardChanges = new UpdateCustomMessageStatus(
					dataSource, getUpdateCustomMessageStatusSQL());
			updateCustomMessageStatusForDiscardChanges.update(new Object[] {
					mapping.getVariableMappingStatus(),
					mapping.getDataFeedInd(),
					mapping.getRule().getHeaderRuleId(),
					mapping.getSpsId().getSpsId() });

		}
/**
 * 
 * @param auditTrail
 * @return
 */
	private boolean insertAuditTrail(AuditTrail auditTrail) {

		if (auditTrail != null) {
			InsertAuditTrail insertAuditTrail = new InsertAuditTrail(dataSource);

			insertAuditTrail.update(new Object[] { auditTrail.getRuleId(),
					auditTrail.getSpsId(), auditTrail.getSystemComments(),
					auditTrail.getUserComments(), auditTrail.getCreatedUser(),
					auditTrail.getMappingStatus() });

			return true;
		}

		return false;
	}
/**
 * 
 * @param mapping
 */
	private void insertMapping(Mapping mapping) {
	
		List<EB03Association> eb03AssnList = mapping.getEb03()
				.getEb03Association();
		Iterator eb03AssnIterator = eb03AssnList.iterator();
		while (eb03AssnIterator.hasNext()) {
			EB03Association eb03AssnObj = (EB03Association) eb03AssnIterator
					.next();
			CreateCustomMessage createCustomMessage = new CreateCustomMessage(
					dataSource);
			createCustomMessage.update(new Object[] {
					mapping.getRule().getHeaderRuleId().trim(),
					mapping.getSpsId().getSpsId().trim(),
					eb03AssnObj.getMsgReqdInd(), eb03AssnObj.getMessage(),
					mapping.getUser().getCreatedUserName(),
					mapping.getCreatedTmStamp(),
					mapping.getUser().getLastUpdatedUserName(),
					eb03AssnObj.getNoteType().getValue(),
					mapping.getVariableMappingStatus(),
					mapping.getInTempTable(),
					// Added as part of SSCR 19537
					eb03AssnObj.getEb03().getValue().trim(),
					(mapping.getIndvdlEb03AssnIndicator()),
			// Ends Here
					});
		}
	}
/**
 * 
 * @param mapping
 */
	private void insertTempTable(Mapping mapping) {
		
		List<EB03Association> eb03AssnList = mapping.getEb03()
				.getEb03Association();
		Iterator eb03AssnIterator = eb03AssnList.iterator();
		while (eb03AssnIterator.hasNext()) {
			EB03Association eb03AssnObj = (EB03Association) eb03AssnIterator
					.next();
			CreateCustomMessageTemp createCustomMessageTemp = new CreateCustomMessageTemp(
					dataSource);
			createCustomMessageTemp.update(new Object[] {
					mapping.getRule().getHeaderRuleId().trim(),
					mapping.getSpsId().getSpsId().trim(), eb03AssnObj.getMsgReqdInd(),
					eb03AssnObj.getMessage(),
					mapping.getUser().getCreatedUserName(),
					mapping.getCreatedTmStamp(),
					mapping.getUser().getLastUpdatedUserName(),
					eb03AssnObj.getNoteType().getValue(),
					mapping.getVariableMappingStatus(),
					// Added as part of SSCR 19537
					eb03AssnObj.getEb03().getValue().trim(),
					(mapping.getIndvdlEb03AssnIndicator()),
			// Ends Here
					});
		}
	}
		
/**
 * 
 * @param mapping
 * @param userComments
 * @param auditStatus
 * @return auditTrail
 */
	private boolean logMapping(Mapping mapping, String userComments,
			String auditStatus) {
		
		if(mapping == null || null == mapping.getVariableMappingStatus()) {
			throw new DomainException("Invalid Mapping Object! Cannot log Audit details.");
		}
		AuditTrail auditTrail = createAuditTrailObject( mapping,userComments, auditStatus,null);
		return insertAuditTrail(auditTrail);
	}
/**
 * 
 * @param mapping
 * @param inTempTableFlag
 */
	private void updateInTempTableFlag(Mapping mapping, String inTempTableFlag) {
		UpdateInTempTableFlag updateInTempTableFlag = new UpdateInTempTableFlag(
				dataSource, getUpdateInTempTableFlagSQL());
		updateInTempTableFlag.update(new Object[] { inTempTableFlag,
				mapping.getRule().getHeaderRuleId(),
				mapping.getSpsId().getSpsId() });

	}
/**
 * 
 * @param mapping

 */
	private void updateMapping(Mapping mapping) {

		this.deleteMappingMain(mapping);
		this.insertMapping(mapping);

	}
	/**
	 * 
	 * @param mapping
	 */
	private void deleteMappingMain(Mapping mapping) {
		DeleteCustomMessageMainTable deleteCustomMessageMainTable = new DeleteCustomMessageMainTable(
				dataSource, getDeleteCustomMessageMainTableSQL());
		deleteCustomMessageMainTable.update(new Object[] {
				mapping.getRule().getHeaderRuleId(),
				mapping.getSpsId().getSpsId() });

	}
	/**
	 * 
	 * @param mapping
	 */
private void insertDataFeed(Mapping mapping){
	
	if((null!=mapping.getRule().getHeaderRuleId())&&(null!=mapping.getSpsId().getSpsId())){
		CreateCustomMessageDataFeed createCustomMessageDataFeed = new CreateCustomMessageDataFeed(
				dataSource);
		createCustomMessageDataFeed.update(new Object[] {
				mapping.getRule().getHeaderRuleId(),
				mapping.getSpsId().getSpsId(), 
				mapping.getMessage(), mapping.getUser().getCreatedUserName(),
				mapping.getUser().getLastUpdatedUserName(),
				mapping.getVariableMappingStatus()});
	}
}
/**
 * 
 * @param mapping
 */
	private void updateMappingTemp(Mapping mapping) {

		
		this.deleteMappingTemp(mapping);
		this.insertTempTable(mapping);

	}
/**
 * 
 * @param mapping
 * @param operation
 * @throws ParseException 
 */
	private void updateOperation(Mapping mapping, String operation){
		/*
		 * Update the IN_TEMP_TAB to 'Y' and insert a row in the TEMP table and the status in mapping to BEING_MODIFIED
		 */
		if (DomainConstants.INSERT_TEMP_OPERATION.equals(operation)) {
			
			mapping.setInTempTable(DomainConstants.IN_TEMP_TAB_FLAG_UPDATE);
			mapping.setVariableMappingStatus(DomainConstants.STATUS_BEING_MODIFIED);
			this.updateMapping(mapping);
			String createdDate = mapping.getCreatedDate();
		 	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");     
		 	java.util.Date sd = new Date();
			try {
				sd = new java.util.Date(sdf.parse(createdDate).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			mapping.setCreatedTmStamp(sd);
			this.insertTempTable(mapping);
		}
		/*
		 * Update the mapping in TEMP table
		 */
		if (DomainConstants.UPDATE_TEMP_OPERATION.equals(operation)) {
			this.updateMappingTemp(mapping);
		}
		/*
		 * Delete the mapping from TEMP and update the IN_TEMP_TAB to 'N" in the MAIN table 
		 */
		if (DomainConstants.DELETE_TEMP_OPERATION.equals(operation)) {
			this.deleteMappingTemp(mapping);
			this.updateInTempTableFlag(mapping,
					DomainConstants.IN_TEMP_TAB_FLAG_PERSIST);
		}
		/*
		 * Update the mapping in the main table
		 */
		if (DomainConstants.UPDATE_MAIN_OPERATION.equals(operation)) {
			this.updateMapping(mapping);
		}
		/*
		 * Update the status in the TEMP table
		 */
		if (DomainConstants.UPDATE_STATUS_MAIN_OPERATION.equals(operation)) {
			
			UpdateCustomMessageStatus updateCustomMessageStatus = new UpdateCustomMessageStatus(
					dataSource, getUpdateCustomMessageStatusSQL());

			updateCustomMessageStatus.update(new Object[] {
					mapping.getVariableMappingStatus(),
					mapping.getDataFeedInd(),
					mapping.getRule().getHeaderRuleId(),
					mapping.getSpsId().getSpsId()
			});
		}
		/*
		 * Update the status in the TEMP table
		 */
		if (DomainConstants.UPDATE_STATUS_TEMP_OPERATION.equals(operation)) {
			UpdateCustomMessageStatusTemp updateCustomMessageStatusTemp = new UpdateCustomMessageStatusTemp(
					dataSource, getUpdateCustomMessageStatusTempSQL());
			
			updateCustomMessageStatusTemp.update(new Object[] {
					mapping.getVariableMappingStatus(),
					mapping.getDataFeedInd(),
					mapping.getRule().getHeaderRuleId(),
					mapping.getSpsId().getSpsId()
			});
		}
	}
	
	/**
	 * 
	 * A private method for Updating the custom message status in temp table
	 *
	 */
	private class UpdateCustomMessageStatusTemp extends SqlUpdate {

		private UpdateCustomMessageStatusTemp(DataSource dataSource, String query) {
			super(dataSource, query);
			declareParameter(new SqlParameter("STATUS_CD", Types.VARCHAR));
			declareParameter(new SqlParameter("DATA_FEED_IND", Types.VARCHAR));
			declareParameter(new SqlParameter("SPS_ID", Types.VARCHAR));
			declareParameter(new SqlParameter("HDR_RULE_ID", Types.VARCHAR));
			compile();
		}
	}
	/**
	 * 
	 * A private method for Updating the custom message status
	 *
	 */
	private class UpdateCustomMessageStatus extends SqlUpdate {

		private UpdateCustomMessageStatus(DataSource dataSource, String query) {
			super(dataSource, query);
			declareParameter(new SqlParameter("STATUS_CD", Types.VARCHAR));
			declareParameter(new SqlParameter("DATA_FEED_IND", Types.VARCHAR));
			declareParameter(new SqlParameter("SPS_ID", Types.VARCHAR));
			declareParameter(new SqlParameter("HDR_RULE_ID", Types.VARCHAR));
			compile();
		}
	}
	/**
	 * An existing mapping of custom message is deleted and a datafeed entry is made
	 * @param Mapping
	 * @param userComments
	 * @return MappingResult
	 */
	public boolean delete(Mapping mapping,String userComments) {
		boolean isAuditPersisted = false;
		Mapping retrievedMapping=null;
		List status=new ArrayList();
		status.add(DomainConstants.VIEW_STATUS);
		List retrieveList = locateCustomMessageRepository.getRecords(mapping, status,  null, -1, 21, null);
		if (null!=retrieveList){
			retrievedMapping=(Mapping)retrieveList.get(0);
			mapping.setVariableMappingStatus(DomainConstants.FEED_RUN_FLAG);
			this.insertDataFeed(mapping);
			if(null!=retrievedMapping.getInTempTable()&& retrievedMapping.getInTempTable().equals(DomainConstants.IN_TEMP_TAB_FLAG_UPDATE)){
				this.deleteMappingTemp(mapping);
				
			}
			this.deleteMappingMain(mapping);
			String auditStatus = DomainConstants.DELETE_CSTM_MESSAGE;
			isAuditPersisted = logMapping(mapping, userComments, auditStatus);
		}

		return isAuditPersisted;
	}
/**
 * 
 * @param mapping
 */
	 private void updateDataFeed(Mapping mapping){
			
			UpdateCustomMessageDataFeed updateCustomMessageDataFeed = new UpdateCustomMessageDataFeed(
					dataSource,getUpdateCustomMessageDataFeedSQL());
			updateCustomMessageDataFeed.update(new Object[] {
					mapping.getVariableMappingStatus(),
					mapping.getUser().getLastUpdatedUserName(),
					mapping.getRule().getHeaderRuleId(),
					mapping.getSpsId().getSpsId()});
	}
	 /**
		 * This method updates the feed_run_flag as �T� after the datafeed to IMSN region is executed 
		 * and to �P� after the datafeed to IMSP region is executed.
		 * @param Mapping
		 * @return boolean
		 */
public boolean updateDatafeedStatus(Mapping mapping) {
	 if((null!=mapping.getRule().getHeaderRuleId())&&(null!=mapping.getSpsId().getSpsId())){
	 this.updateDataFeed(mapping);
		 return true;
	 }
	 else
	 {
		 return false;
	 }
}
/**
 * 
 * @return checkInTempFlag
 */
public String getCheckInTempFlag() {
	return checkInTempFlag;
}
/**
 * 
 * @return createCustomMessageSQL
 */
public String getCreateCustomMessageSQL() {
	return createCustomMessageSQL;
}

/**
 * 
 * @return createCustomMessageTempSQL
 */
public String getCreateCustomMessageTempSQL() {
	return createCustomMessageTempSQL;
}
/**
 * 
 * @return dataSource
 */
public DataSource getDataSource() {
	return dataSource;
}
/**
 * 
 * @return deleteCustomMessageTempTableSQL
 */
public String getDeleteCustomMessageTempTableSQL() {
	return deleteCustomMessageTempTableSQL;
}
/**
 * 
 * @return insertAuditTrailSQL
 */
public String getInsertAuditTrailSQL() {
	return insertAuditTrailSQL;
}
/**
 * 
 * @return updateCustomMessageSQL
 */
public String getUpdateCustomMessageSQL() {
	return updateCustomMessageSQL;
}
/**
 * 
 * @return updateCustomMessageTempSQL
 */
public String getUpdateCustomMessageTempSQL() {
	return updateCustomMessageTempSQL;
}
/**
 * 
 * @return updateInTempTableFlagSQL
 */
public String getUpdateInTempTableFlagSQL() {
	return updateInTempTableFlagSQL;
}
/**
 * 
 * @param checkInTempFlag
 */
public void setCheckInTempFlag(String checkInTempFlag) {
	this.checkInTempFlag = checkInTempFlag;
}

/**
 * 
 * @param createCustomMessageSQL
 */
public void setCreateCustomMessageSQL(String createCustomMessageSQL) {
	this.createCustomMessageSQL = createCustomMessageSQL;
}

/**
 * 
 * @param createCustomMessageTempSQL
 */
public void setCreateCustomMessageTempSQL(String createCustomMessageTempSQL) {
	this.createCustomMessageTempSQL = createCustomMessageTempSQL;
}

public void setDataSource(DataSource dataSource) {
	this.dataSource = dataSource;
}

/**
 * 
 * @param deleteCustomMessageTempTableSQL
 */
public void setDeleteCustomMessageTempTableSQL(
		String deleteCustomMessageTempTableSQL) {
	this.deleteCustomMessageTempTableSQL = deleteCustomMessageTempTableSQL;
}
/**
 * 
 * @param insertAuditTrailSQL
 */
public void setInsertAuditTrailSQL(String insertAuditTrailSQL) {
	this.insertAuditTrailSQL = insertAuditTrailSQL;
}
/**
 * 
 * @param updateCustomMessageSQL
 */
public void setUpdateCustomMessageSQL(String updateCustomMessageSQL) {
	this.updateCustomMessageSQL = updateCustomMessageSQL;
}
/**
 * 
 * @param updateCustomMessageTempSQL
 */
public void setUpdateCustomMessageTempSQL(String updateCustomMessageTempSQL) {
	this.updateCustomMessageTempSQL = updateCustomMessageTempSQL;
}
/**
 * 
 * @param updateInTempTableFlagSQL
 */
public void setUpdateInTempTableFlagSQL(String updateInTempTableFlagSQL) {
	this.updateInTempTableFlagSQL = updateInTempTableFlagSQL;
}
/**
 * 
 * @return createCustomMessageDataFeedSQL
 */
	public String getCreateCustomMessageDataFeedSQL() {
		return createCustomMessageDataFeedSQL;
	}
/**
 * 
 * @param createCustomMessageDataFeedSQL
 */
	public void setCreateCustomMessageDataFeedSQL(
			String createCustomMessageDataFeedSQL) {
		this.createCustomMessageDataFeedSQL = createCustomMessageDataFeedSQL;
	}
/**
 * 
 * @return deleteCustomMessageMainTableSQL
 */
	public String getDeleteCustomMessageMainTableSQL() {
		return deleteCustomMessageMainTableSQL;
	}
/**
 * 
 * @param deleteCustomMessageMainTableSQL
 */
	public void setDeleteCustomMessageMainTableSQL(
			String deleteCustomMessageMainTableSQL) {
		this.deleteCustomMessageMainTableSQL = deleteCustomMessageMainTableSQL;
	}
/**
 * 
 * @return locateCustomMessageRepository
 */
	public LocateRepository getLocateCustomMessageRepository() {
		return locateCustomMessageRepository;
	}
/**
 * 
 * @param locateCustomMessageRepository
 */
	public void setLocateCustomMessageRepository(
			LocateRepository locateCustomMessageRepository) {
		this.locateCustomMessageRepository = locateCustomMessageRepository;
	}
/**
 * 
 * @return updateCustomMessageDataFeedSQL
 */
	public String getUpdateCustomMessageDataFeedSQL() {
		return updateCustomMessageDataFeedSQL;
	}
/**
 * 
 * @param updateCustomMessageDataFeedSQL
 */
	public void setUpdateCustomMessageDataFeedSQL(
			String updateCustomMessageDataFeedSQL) {
		this.updateCustomMessageDataFeedSQL = updateCustomMessageDataFeedSQL;
	}
/**
 * 
 * @return updateCustomMessageStatusSQL
 */
	public String getUpdateCustomMessageStatusSQL() {
		return updateCustomMessageStatusSQL;
	}
	/**
	 * 
	 * @param updateCustomMessageStatusSQL
	 */
	public void setUpdateCustomMessageStatusSQL(String updateCustomMessageStatusSQL) {
		this.updateCustomMessageStatusSQL = updateCustomMessageStatusSQL;
	}
/**
 * 
 * @return updateCustomMessageStatusTempSQL
 */
	public String getUpdateCustomMessageStatusTempSQL() {
		return updateCustomMessageStatusTempSQL;
	}
/**
 * 
 * @param updateCustomMessageStatusTempSQL
 */
	public void setUpdateCustomMessageStatusTempSQL(
			String updateCustomMessageStatusTempSQL) {
		this.updateCustomMessageStatusTempSQL = updateCustomMessageStatusTempSQL;
	}
	
	//SSCR 16332
	
		public SPSMappingRetrieveResult getSPSMappingMain(String spsId){
			SPSMappingRetrieveResult mapping = null;
			SPSMappingRetrieveResult mappingNew = null;
			String inTempTab = "";
			String finalQuery = "";
			String query = "Select * from BX_SPS_MAPNG where SPS_PARAM_ID = '"+ spsId +"'";
			SPSMappingQuery spsMappingQuery = new SPSMappingQuery(
					dataSource, query);
			//System.out.println("Query :" + spsMappingQuery);
			logger.info("Query :" + spsMappingQuery);		
			
			List spsMappingResultMstr = spsMappingQuery.execute();	
			if(null != spsMappingResultMstr && !spsMappingResultMstr.isEmpty() && null != spsMappingResultMstr.get(0)){
				mapping = (SPSMappingRetrieveResult) spsMappingResultMstr.get(0);
			}
			
			if(null != mapping && null != mapping.getInTempTab()){
				inTempTab = mapping.getInTempTab();
				
				if(inTempTab.equalsIgnoreCase(DomainConstants.Y)){
					finalQuery = "Select * from TEMP_BX_SPS_MAPG where SPS_PARAM_ID = '"+ spsId +"'";
					SPSMappingQueryTemp spsMappingQueryTemp = new SPSMappingQueryTemp(
							dataSource, finalQuery);
					//System.out.println("Query :" + spsMappingQueryTemp);
					logger.info("Query :" + spsMappingQueryTemp);
				
				
					List spsMappingResultChild = spsMappingQueryTemp.execute();	
					if(null != spsMappingResultChild && !spsMappingResultChild.isEmpty()&& null != spsMappingResultChild.get(0)){
						mappingNew = (SPSMappingRetrieveResult) spsMappingResultChild.get(0);
						return mappingNew;
					}
				}
				
			}
			
			return mapping;
		}
		private class SPSMappingQuery extends MappingSqlQuery {
			
				public SPSMappingQuery(DataSource dataSource, String query) {
					super(dataSource, query);
					compile();
				}

				public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
					SPSMappingRetrieveResult result = new SPSMappingRetrieveResult();
					
					result.setSPSId(rs.getString("SPS_PARAM_ID"));
					result.setEb01Value(rs.getString("EB01_VALUE"));
					result.setEb02Value(rs.getString("EB02_VALUE"));
					result.setEb06Value(rs.getString("EB06_VALUE"));
					result.setEb09Value(rs.getString("EB09_VALUE"));
					result.setHsd01Value(rs.getString("HSD1_VALUE"));
					result.setHsd02Value(rs.getString("HSD2_VALUE"));
					result.setHsd03Value(rs.getString("HSD3_VALUE"));
					result.setHsd04Value(rs.getString("HSD4_VALUE"));
					result.setHsd05Value(rs.getString("HSD5_VALUE"));
					result.setHsd06Value(rs.getString("HSD6_VALUE"));
					result.setHsd07Value(rs.getString("HSD7_VALUE"));
					result.setHsd08Value(rs.getString("HSD8_VALUE"));
					result.setCreatedUser(rs.getString("CREATD_USER_ID"));
					result.setCreatedTimeStamp(rs.getString("CREATD_TM_STMP"));
					result.setLastChangeUser(rs.getString("LST_CHG_USR"));	
					result.setLastChangeTimeStamp(rs.getString("LST_CHG_TMS"));
					result.setAccumSpsId(rs.getString("ACCUMR_SPS_ID"));
					result.setStatusCode(rs.getString("STATUS_CD"));
					result.setMappingCmpInd(rs.getString("MAPPNG_CMP_IND"));
					result.setInTempTab(rs.getString("IN_TEMP_TAB"));
					result.setDataFeedInd(rs.getString("DATA_FEED_IND"));
			
					return result;
				}
			}
		
		private class SPSMappingQueryTemp extends MappingSqlQuery {
			
			public SPSMappingQueryTemp(DataSource dataSource, String query) {
				super(dataSource, query);
				compile();
			}

			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				SPSMappingRetrieveResult result = new SPSMappingRetrieveResult();
				
				result.setSPSId(rs.getString("SPS_PARAM_ID"));
				result.setEb01Value(rs.getString("EB01_VALUE"));
				result.setEb02Value(rs.getString("EB02_VALUE"));
				result.setEb06Value(rs.getString("EB06_VALUE"));
				result.setEb09Value(rs.getString("EB09_VALUE"));
				result.setHsd01Value(rs.getString("HSD1_VALUE"));
				result.setHsd02Value(rs.getString("HSD2_VALUE"));
				result.setHsd03Value(rs.getString("HSD3_VALUE"));
				result.setHsd04Value(rs.getString("HSD4_VALUE"));
				result.setHsd05Value(rs.getString("HSD5_VALUE"));
				result.setHsd06Value(rs.getString("HSD6_VALUE"));
				result.setHsd07Value(rs.getString("HSD7_VALUE"));
				result.setHsd08Value(rs.getString("HSD8_VALUE"));
				result.setCreatedUser(rs.getString("CREATD_USER_ID"));
				result.setCreatedTimeStamp(rs.getString("CREATD_TM_STMP"));
				result.setLastChangeUser(rs.getString("LST_CHG_USR"));	
				result.setLastChangeTimeStamp(rs.getString("LST_CHG_TMS"));
				result.setAccumSpsId(rs.getString("ACCUMR_SPS_ID"));
				result.setStatusCode(rs.getString("STATUS_CD"));
				result.setMappingCmpInd(rs.getString("MAPPNG_CMP_IND"));
				result.setDataFeedInd(rs.getString("DATA_FEED_IND"));
		
				return result;
			}
		}
		
		
}
