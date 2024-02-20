/*
 * <LocateCustomMessageRepositoryImpl.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
*/
package com.wellpoint.ets.ebx.mapping.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.object.MappingSqlQuery;

import com.wellpoint.ets.bx.mapping.application.security.Lock;
import com.wellpoint.ets.bx.mapping.domain.entity.AuditTrail;
import com.wellpoint.ets.bx.mapping.domain.entity.EB03Association;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Page;
import com.wellpoint.ets.bx.mapping.domain.entity.Rule;
import com.wellpoint.ets.bx.mapping.domain.entity.SPSId;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.vo.SearchResult;
import com.wellpoint.ets.bx.mapping.domain.vo.User;
import com.wellpoint.ets.bx.mapping.repository.HippaSegmentRepositoryImpl;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import org.apache.commons.lang.StringUtils;


/**
 * @author UST-GLOBAL
 * This is an implementation class for locating or searching a custom message
 * or multiple custom message based on certain search criteria
 */
public class LocateCustomMessageRepositoryImpl implements LocateRepository {
	
	private DataSource dataSource;
	private static HashMap eb03Map = new HashMap();
	private HippaSegmentRepositoryImpl hippaSegmentRepository;
	private static Logger logger = Logger.getLogger(LocateCustomMessageRepositoryImpl.class);
	private static  List<String> eb03MappedToHeaderRuleList = new ArrayList();

	/**
	 * 
	 * @return dataSource
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
	 * For getting exact match/retrieving records based on the status and logged in user
	 * @param Mapping
	 * @param status
	 * @param loggedInUser
	 * @return List
	 */
	public List getRecords(Mapping mapping, List status, String loggedInUser,int noOfRecords, int auditTrailCount, Page page) {
		List result;
		List updatedResult = new ArrayList();
		
		String selectQuery = "";
		
		// Initial value of ruleId and spsId is set to null
		String ruleId = null;
		String spsId = null;
		boolean editMessageFlag = false;

		
		if (mapping != null && mapping.getRule() != null && mapping.getRule().getHeaderRuleId() != null 
				&& !mapping.getRule().getHeaderRuleId().trim().equals("")) {
			ruleId = mapping.getRule().getHeaderRuleId().replace('\'', ' ');
		}
		if (mapping != null && mapping.getSpsId() != null && mapping.getSpsId().getSpsId() != null 
				&& !mapping.getSpsId().getSpsId().trim().equals("")) {
			spsId = mapping.getSpsId().getSpsId().replace('\'', ' ');
		}
		String selectLock = "select customMsgBx.*, lk.bolk_bus_obj_lock_usr_id  from ( ";		
		String lockCondition = ")customMsgBx left outer join bolk_bus_obj_lock lk on lk.bolk_bus_obj_key_id = customMsgBx.hdr_rule_id || customMsgBx.sps_id "
						+ " and lk.bolk_bus_obj_type_nm = 'com.wellpoint.ets.bx.mapping.domain.entity.Mapping'";
		
		/* Case 1: Create Custom Message Section
		 * When calling from create section, rule id and sps id will be empty.
		 */
		if (null != status && status.contains("CUSTOM_MESSAGE_CREATE")) {
			int count = getQueryToCreateCustomMessage(ruleId, spsId);
			if(count == 0){
				return null;
			}else{
				List mappingList = new ArrayList<Mapping>();
				mappingList.add(mapping);
				return mappingList;
			}
		} 
		
		/* Case 2: View Custom message.
		 * The status flag will "VIEW" and Rule id and SPS Id will be present in the mapping object.
		 */
		else if ((status != null && (status.contains(DomainConstants.VIEW_STATUS) || status.contains(DomainConstants.VIEW_STATUS_FOR_CUSTOM_MESSAGE))) && (ruleId != null && spsId != null)) {
			
			// isExactMatchReq flag is set to True .
			boolean isExactMatchReq = true;
			editMessageFlag = true;
			selectQuery = getLocateQuery(mapping,status,noOfRecords, isExactMatchReq);
		}
		
		/* Case 3: IN PROGRESS Section
		 * When calling from in-progess section, rule id and sps id will be empty.
		 */
		else if (null != status && status.contains("IN-PROGRESS")) {
			selectQuery = getInProgressSectionQuery(loggedInUser, noOfRecords);
			
		} 
		
		/* Case 4: Normal Locate
		 * 
		 */
		else {
			// isExactMatchReq flag is set to false for normal Locate.
			boolean isExactMatchReq = false;
			selectQuery = getLocateQuery(mapping,status,noOfRecords, isExactMatchReq);
		}		
		
//		 Page will have value when calling locate from view landing page
		if (page != null) {
			selectQuery  = "select * from (select EBX.*, ROWNUM rnm from ("
				+ selectQuery 
				+") EBX)  WHERE rnm between "+ page.getStartRowNum() +" and "+ page.getEndRowNum();
		}
		selectQuery = selectLock +  selectQuery + lockCondition;
		if (null != status && status.contains("PRINT")) {
			selectQuery += " ORDER BY hdr_rule_id, sps_id," +
					//" SRVC_TYP_CODE,  " +
					"msg_text, lst_chg_tms";
		}
		
		eb03ListDescription();
		long locateMappingStartTime = System.currentTimeMillis();
		
		LocateMapping locateMapping = new LocateMapping(dataSource,
				selectQuery, status);
		result = locateMapping.execute();
		updatedResult = result;
		
		long locateMappingEndTime = System.currentTimeMillis();
		logger.info("Time taken for executing Custom message Query = "+ (locateMappingEndTime - locateMappingStartTime));
		if (null == status || status.contains("IN-PROGRESS") || status.contains(DomainConstants.VIEW_STATUS)
				|| status
						.contains(DomainConstants.VIEW_STATUS_FOR_CUSTOM_MESSAGE)) {
			Mapping mappingResult;
			Iterator resultIterator = result.iterator();
			List auditTrails;
			if (resultIterator.hasNext()) {
				mappingResult = (Mapping) resultIterator.next();
				auditTrails = getAuditInfo(mappingResult.getRule()
						.getHeaderRuleId(),
						mappingResult.getSpsId().getSpsId(), auditTrailCount);
				mappingResult.setAuditTrails(auditTrails);
			}
		}
		Mapping mappingResult;
		Iterator resultIterator = result.iterator();
		Map<String, Mapping> resultMap = new HashMap<String, Mapping>();
		while (resultIterator.hasNext()) {
			mappingResult = (Mapping) resultIterator.next();
			String displayMessage = "";
			String key = mappingResult.getRule().getHeaderRuleId()
					+ mappingResult.getSpsId().getSpsId();
			if (null != mappingResult.getMessage()
					&& !mappingResult.getMessage().trim()
							.equalsIgnoreCase("")) {
				displayMessage = mappingResult.getMessage().trim().toUpperCase();
			}
			if (!resultMap.keySet().contains(key)) {
				mappingResult.setMessageForDisplay(displayMessage);
				resultMap.put(key, mappingResult);
				
				
			} else {
				Mapping existingCustomMessageMapping = resultMap.get(key);
				HippaCodeValue eb03HippaCodeVal = (null != mappingResult
						.getEb03()
						&& null != mappingResult.getEb03()
								.getHippaCodeSelectedValues()
						&& !mappingResult.getEb03()
								.getHippaCodeSelectedValues().isEmpty() ? (HippaCodeValue) mappingResult
						.getEb03().getHippaCodeSelectedValues().get(0)
						: null);
				EB03Association neweb03Association = new EB03Association();
				if (null != mappingResult.getEb03().getEb03Association()
						&& !mappingResult.getEb03().getEb03Association()
								.isEmpty()
						&& null != mappingResult.getEb03().getEb03Association()
								.get(0)) {
					neweb03Association = (EB03Association) mappingResult
							.getEb03().getEb03Association().get(0);
				}

				List existingEB03Values = existingCustomMessageMapping
						.getEb03().getHippaCodeSelectedValues();
				existingEB03Values.add(eb03HippaCodeVal);
				existingCustomMessageMapping.getEb03()
						.setHippaCodeSelectedValues(existingEB03Values);

				List<EB03Association> existingEB03Association = existingCustomMessageMapping
						.getEb03().getEb03Association();
				existingEB03Association.add(neweb03Association);
				existingCustomMessageMapping.getEb03().setEb03Association(
						existingEB03Association);
				
				//For Custom message Display
				String messageVal = "";
				if(null != existingCustomMessageMapping.getIndvdlEb03AssnIndicator() && 
						existingCustomMessageMapping.getIndvdlEb03AssnIndicator().equals("Y")){
					for(EB03Association existingEB03AssociationObject: existingEB03Association){
						if(null != existingEB03AssociationObject){
							String newMessage = existingEB03AssociationObject.getMessage();
							if(!messageVal.equals(DomainConstants.EMPTY) && null != newMessage && !newMessage.equals(DomainConstants.EMPTY)){
								displayMessage = messageVal+"...";
								break;
							}else{
								messageVal = (null != newMessage && !newMessage.equals(DomainConstants.EMPTY) ? newMessage : messageVal);
							}
						}
						
					}
				}else{
					displayMessage = existingCustomMessageMapping.getMessage();
				}
				
				if(!StringUtils.isBlank(displayMessage)){
					existingCustomMessageMapping
							.setMessageForDisplay(displayMessage);
				}
				resultMap.put(key, existingCustomMessageMapping);
			}

		}

		if (resultMap.values().size() > 0) {
			updatedResult = new ArrayList();
		}
		Iterator mapItertor = resultMap.values().iterator();
		while (mapItertor.hasNext()) {
			Mapping updatedMapping = (Mapping) mapItertor.next();
			if (null != updatedMapping) {
				updatedResult.add(updatedMapping);
			}
		}

		if (updatedResult.isEmpty()
				&& status.contains(DomainConstants.UNMAPPED_STATUS)) {
			Mapping mappingForCustomMessage = new Mapping();
			eb03MappedToHeaderRuleList = new ArrayList<String>();
			List<EB03Association> updatedList = new ArrayList<EB03Association>();
			if (null != mapping && null != mapping.getInTempTable()
					&& mapping.getInTempTable().equalsIgnoreCase("N")) {
				getEb03MappedToHeaderRuleList(ruleId);
			} else {
				getEb03MappedToHeaderRuleList(ruleId);
			}

			if (!eb03MappedToHeaderRuleList.isEmpty()) {
				for (String existingEb03Obj : eb03MappedToHeaderRuleList) {
					EB03Association eb03AssnObj = new EB03Association();
					HippaCodeValue code = new HippaCodeValue();
					code.setValue(existingEb03Obj);
					code.setDescription((String) eb03Map
							.get((null != existingEb03Obj ? existingEb03Obj
									.trim() : "")));
					eb03AssnObj.setEb03(code);
					eb03AssnObj.setMsgReqdInd("N");
					updatedList.add(eb03AssnObj);
				}
			}
			mappingForCustomMessage.setEb03(new HippaSegment());
			mappingForCustomMessage.getEb03().setEb03Association(updatedList);
			
			updatedResult = new ArrayList();
			updatedResult.add(mappingForCustomMessage);
			
		}

		else if (!updatedResult.isEmpty() && editMessageFlag
				&& !status
						.contains(DomainConstants.VIEW_STATUS_FOR_CUSTOM_MESSAGE)) {
			Mapping mappingForCustomMessage = (Mapping) updatedResult.get(0);
			eb03MappedToHeaderRuleList = new ArrayList<String>();
			if (null != mappingForCustomMessage
					&& null != mappingForCustomMessage.getInTempTable()
					&& mappingForCustomMessage.getInTempTable()
							.equalsIgnoreCase("N")) {
				getEb03MappedToHeaderRuleList(ruleId);
			} else {
				getEb03MappedToHeaderRuleList(ruleId);
			}
			if (null != mappingForCustomMessage
					&& null != mappingForCustomMessage.getEb03()
					&& null != mappingForCustomMessage.getEb03()
							.getEb03Association()) {
				List<EB03Association> eb03List = mappingForCustomMessage
						.getEb03().getEb03Association();
				List<EB03Association> updatedList = new ArrayList<EB03Association>();
				boolean individualMappingIndicator = (null != mappingForCustomMessage
						.getIndvdlEb03AssnIndicator() &&  mappingForCustomMessage
						.getIndvdlEb03AssnIndicator().equalsIgnoreCase("Y") ? true : false );
				String noteType = "";
				String msg = "";
				String msgReqd = "";

				for (EB03Association eb03AssnObj : eb03List) {
					if (null != eb03AssnObj && null != eb03AssnObj.getEb03()
							&& null != eb03AssnObj.getEb03().getValue()) {
						if (eb03MappedToHeaderRuleList.contains(eb03AssnObj
								.getEb03().getValue().trim())) {
							updatedList.add(eb03AssnObj);
							eb03MappedToHeaderRuleList.remove(eb03AssnObj
									.getEb03().getValue().trim());
						}
						noteType = (null != eb03AssnObj.getNoteType()
								&& null != eb03AssnObj.getNoteType().getValue()
								&& null != eb03AssnObj.getNoteType().getValue()
										.trim() ? eb03AssnObj.getNoteType()
								.getValue().trim() : "");
						msg = (null != eb03AssnObj.getMessage() ? eb03AssnObj
								.getMessage().trim() : "");
						msgReqd = (null != eb03AssnObj.getMsgReqdInd() ? eb03AssnObj
								.getMsgReqdInd().trim()
								: "N");
					}
				}
				if (!eb03MappedToHeaderRuleList.isEmpty()) {
					for (String existingEb03Obj : eb03MappedToHeaderRuleList) {
						EB03Association eb03AssnObj = new EB03Association();
						HippaCodeValue code = new HippaCodeValue();
						code.setValue(existingEb03Obj);
						code.setDescription((String) eb03Map
								.get((null != existingEb03Obj ? existingEb03Obj
										.trim() : "")));
						eb03AssnObj.setEb03(code);
						eb03AssnObj.setMsgReqdInd("N");
						if (!individualMappingIndicator) {
							code = new HippaCodeValue();
							code.setValue(noteType);
							eb03AssnObj.setNoteType(code);
							eb03AssnObj.setMessage(msg);
							eb03AssnObj.setMsgReqdInd(msgReqd);
						}
						updatedList.add(eb03AssnObj);
					}
				}
				mappingForCustomMessage.getEb03().setEb03Association(
						updatedList);
				updatedResult = new ArrayList();
				updatedResult.add(mappingForCustomMessage);
				
			}
		}
		return updatedResult;
	}
	/**
	 * 
	 * @param ruleId
	 * @param spsId
	 * @param noOfAuditInfo
	 * @return auditTrailList
	 */
	private List getAuditInfo(String ruleId, String spsId, int noOfAuditInfo){
		
		List auditTrailList;		
		String query = "SELECT * FROM " +
				"(SELECT CREATD_TM_STMP, CREATD_USER_ID, SYS_CMNTS, USR_CMNTS, SPS_ID, HDR_RULE_ID, MAPG_STATUS FROM " +
				"BX_CUSTOM_MSG_AUDIT_TRAIL WHERE SPS_ID = '"+spsId+"' and HDR_RULE_ID = '"+ruleId+"' " +
				"ORDER BY CREATD_TM_STMP DESC)";
		if(noOfAuditInfo != -1){
			query += "WHERE ROWNUM < 20";
		}	
		
		RetrieveAuditTrailQuery retrieveAuditTrailQuery = new RetrieveAuditTrailQuery(dataSource,query);
		auditTrailList = retrieveAuditTrailQuery.execute();
		if(null != auditTrailList && auditTrailList.size() > 0){		   
		    BxUtil.setSystemComments(auditTrailList);
		    return auditTrailList;
		}
		
        return null;
	}
	/**
	 * 
	 * Retrieves audit trail information
	 *
	 */
	private static class RetrieveAuditTrailQuery extends MappingSqlQuery {

		private RetrieveAuditTrailQuery(DataSource dataSource,String query) {
	        super(dataSource, query);
		        compile();
	    }		
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	AuditTrail auditTrail = new AuditTrail();
	    	String auditTime = BxUtil.getFormattedDate(rs.getTimestamp("CREATD_TM_STMP"));
	    	auditTrail.setCreatedAuditDate(auditTime);
	    	auditTrail.setCreatedUser(rs.getString("CREATD_USER_ID"));
	    	auditTrail.setSystemComments(rs.getString("SYS_CMNTS"));	    	
	    	auditTrail.setUserComments(rs.getString("USR_CMNTS"));	    	
	    	auditTrail.setSpsId(rs.getString("SPS_ID"));	
	    	auditTrail.setRuleId(rs.getString("HDR_RULE_ID"));	 
	    	auditTrail.setMappingStatus(rs.getString("MAPG_STATUS"));
		  	return auditTrail;
		}		
    }
	/**
	 * 
	 * A private class to locate mapping
	 *
	 */
	private static class LocateMapping extends MappingSqlQuery {
		boolean isPrintFlag = false;
		private LocateMapping(DataSource dataSource, String query, List status) {
			super(dataSource, query);
			super.compile();
			if (null != status && status.contains("PRINT")) {
				isPrintFlag = true;
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet,
		 *      int)
		 */
		protected Object mapRow(ResultSet rs, int arg1) throws SQLException {
			
			Mapping mapping = new Mapping();
			
			// Rule ID
			Rule rule = new Rule();
			String msgReqdInd = "N";
			
			rule.setHeaderRuleId(rs.getString("HDR_RULE_ID".trim()));
			if(null != rs.getString("RULE_SHRT_DESC") && !isPrintFlag) {
				rule.setRuleDesc(BxUtil.escapeSpecialCharacters(rs.getString("RULE_SHRT_DESC")));
			}
			else {
				rule.setRuleDesc(rs.getString("RULE_SHRT_DESC"));
			}
			mapping.setRule(rule);

			// SPS ID
			SPSId spsId = new SPSId();
			
			spsId.setSpsId(rs.getString("SPS_ID").trim());
			if(null != rs.getString("CD_DESC_TXT") && !isPrintFlag) {
				spsId.setSpsDesc(BxUtil.escapeSpecialCharacters(rs.getString("CD_DESC_TXT")));
			}
			else {
				spsId.setSpsDesc(rs.getString("CD_DESC_TXT"));
			}
			mapping.setSpsId(spsId);
			
			mapping.setIndvdlEb03AssnIndicator(rs
					.getString("INDVDL_EB03_ASSN_IND"));
			
			HippaSegment hippaSegment;
			List selectedHippaSegmentValues = new ArrayList();
			HippaCodeValue code = new HippaCodeValue();
			
			if (null != rs.getString("MSG_TEXT")) {
				mapping.setMessage(rs.getString("MSG_TEXT").toUpperCase());
				//mapping.setMessageForDisplay(rs.getString("MSG_TEXT").toUpperCase());
			}
			if (null != rs.getString("MSG_RQRD_IND") &&  rs.getString("MSG_RQRD_IND").equals("Y")) {
				msgReqdInd = "Y";
			}
			
			
			if((null == mapping.getIndvdlEb03AssnIndicator())
					||(null != mapping.getIndvdlEb03AssnIndicator() && mapping.getIndvdlEb03AssnIndicator().equalsIgnoreCase("N"))){
				
				mapping.setMsg_type_required(msgReqdInd);
				
				// Note Type
				hippaSegment = new HippaSegment("NOTE_TYPE_CODE");
				code.setValue(rs.getString("NOTE_TYP_CD"));
				code.setDescription(rs.getString("NOTE_TYP_CD_DESC"));
				
				selectedHippaSegmentValues.add(code);
				hippaSegment.setHippaCodeSelectedValues(selectedHippaSegmentValues);
				
				mapping.setNoteTypeCode(hippaSegment);
			}
			
			
			mapping.setCreatedTmStamp(rs.getDate("CREATD_TM_STMP"));
			
			// In temp Table
			mapping.setInTempTable(rs.getString("IN_TEMP_TAB"));
			
			//Status Code
			mapping.setVariableMappingStatus(rs.getString("STATUS_CD"));
			mapping.setDataFeedInd(rs.getString("DATA_FEED_IND"));
			
			// User
			User user = new User();
			user.setCreatedUserName(rs.getString("CREATD_USER_ID"));
			user.setLastUpdatedUserName(rs.getString("LST_CHG_USR"));
			mapping.setUser(user);
			mapping.setLastChangedTmStamp(rs.getTimestamp("LST_CHG_TMS"));
			
			
			
			String lockUserId = rs.getString("bolk_bus_obj_lock_usr_id");
			if(null != lockUserId){
				
				Lock lock = new Lock();
				lock.setLockUserId(lockUserId);
				mapping.setLock(lock);
			}
			
			//Added as part of SSCR 19537 -- Added service Type code to the mapping.
			hippaSegment = new HippaSegment("EB03");
			selectedHippaSegmentValues = new ArrayList();
			code = new HippaCodeValue();
			code.setValue(rs.getString("EB03"));
			code.setDescription(
					(String) eb03Map.get(
							(null != rs.getString("EB03") ? rs.getString("EB03").trim() : "")));
			selectedHippaSegmentValues.add(code);
			hippaSegment.setHippaCodeSelectedValues(selectedHippaSegmentValues);
			
			List<EB03Association> eb03Association = new ArrayList<EB03Association>();
			EB03Association eb03AssociationObject =  new EB03Association();
			code = new HippaCodeValue();
			code.setValue(rs.getString("EB03"));
			code.setDescription(
					(String) eb03Map.get(
							(null != rs.getString("EB03") ? rs.getString("EB03").trim() : "")));
			eb03AssociationObject.setEb03(code);
			if (null != rs.getString("MSG_TEXT")) {
				eb03AssociationObject.setMessage(rs.getString("MSG_TEXT").toUpperCase());
			}
			eb03AssociationObject.setMsgReqdInd(msgReqdInd);
			code = new HippaCodeValue();
			code.setValue(rs.getString("NOTE_TYP_CD"));
			code.setDescription(rs.getString("NOTE_TYP_CD_DESC"));
			eb03AssociationObject.setNoteType(code);
			eb03Association.add(0, eb03AssociationObject);
			hippaSegment.setEb03Association(eb03Association);
			
			mapping.setEb03(hippaSegment);
			
			return mapping;
		}
	}
	/**
	 * 
	 * @param mapping
	 * @param status
	 * @param noOfRecords
	 * @param isExactMatchReq
	 * @return query
	 */	
	private String getLocateQuery(Mapping mapping, List status, int noOfRecords, boolean isExactMatchReq) {
		
		String condition = "";
		String conditionTemp = "";
		String sqlPercentageOperator = "";
		String query = "";

		
		// sqlPercentageOperator will be an empty string if we need an exact match. 
		// otherwise value of sqlPercentageOperator will be %
		if (!isExactMatchReq) {
			sqlPercentageOperator = "%";
		}		
		
		if (mapping != null && mapping.getRule() != null
				&& mapping.getRule().getHeaderRuleId() != null
				&& !mapping.getRule().getHeaderRuleId().trim().equals("")) {
			String rule = mapping.getRule().getHeaderRuleId().replace('\'', ' ');
			condition = " and a.HDR_RULE_ID LIKE '" 
					+ rule
					+ sqlPercentageOperator + "'";
			conditionTemp = " and t.HDR_RULE_ID LIKE '" 
					+ rule 
					+ sqlPercentageOperator + "'";
		}
		if (mapping != null && mapping.getSpsId() != null 
				&& mapping.getSpsId().getSpsId() != null 
				&& !mapping.getSpsId().getSpsId().trim().equals("")) {
			String spsId = mapping.getSpsId().getSpsId().replace('\'', ' ');
			condition += " and a.SPS_ID LIKE '"  
			+ spsId 
			+ sqlPercentageOperator +"'";
			conditionTemp += " and t.SPS_ID LIKE '"  
			+ spsId
			+ sqlPercentageOperator +"'";
		}		

		if (isExactMatchReq) {
			query = "  select * from (" + getLocateQueryForEditView() +
			condition +" and a.in_temp_tab = 'N' " +
			" UNION " +
			getLocateQueryForEditViewTemp() + conditionTemp +") ORDER BY hdr_rule_id, sps_id, msg_text,NOTE_TYP_CD ";
		}
		else{
			query = "  select * from (" + getLocateQuery() +
			condition +" and a.in_temp_tab = 'N' " +
			" UNION " +
			getLocateQueryForTemp() + conditionTemp +") ORDER BY hdr_rule_id, sps_id,msg_text,NOTE_TYP_CD ";
		}
		
		
		if (noOfRecords != -1) {
			query += " where rownum < " + noOfRecords;
		}
		return query;
	}
	private String getInProgressSectionQuery(String loggedInUser, int noOfRecords) {
		
		
		String query = "select * from (" + getLocateQuery();
		if (null != loggedInUser) {
			query += " and a.LST_CHG_USR='"+loggedInUser+"'";
		}
		query += " and a.in_temp_tab = 'N'"+
		 " and a.status_cd <> '" +
		 DomainConstants.STATUS_NOT_APPLICABLE + "'" +
		 " and a.status_cd <> '" +
		 DomainConstants.STATUS_PUBLISHED + "'" +
		 " UNION " +
		 getLocateQueryForTemp();
		if (null != loggedInUser) {
			query += " and t.LST_CHG_USR='"+loggedInUser+"'";
		}
		query +="   ORDER BY lst_chg_tms DESC)"; 
		if (noOfRecords != -1) {
			query += "where rownum < " + noOfRecords;
		}
		
		return query;
	}
	/**
	 * A private method to return the record Count
	 */
	public int getRecordCount(Mapping mapping, List status) {

		String selectQuery;
		
		selectQuery = getRecordCountQuery(mapping,status);
		
		FindTotalNoOfRecordsQuery totalNoOfRcrdsQry = new FindTotalNoOfRecordsQuery(
				dataSource, selectQuery);
		List totalNoOfRecordsList = totalNoOfRcrdsQry.execute();
		Integer totalNoOfRecords = (Integer) totalNoOfRecordsList.get(0);
		return totalNoOfRecords.intValue();
	}
	/**
	 * 
	 * A private method to retrieve the total number of records
	 *
	 */
	private static class FindTotalNoOfRecordsQuery extends MappingSqlQuery {

		private FindTotalNoOfRecordsQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			return Integer.valueOf(rs.getInt(1));
		}

	}
	/**
	 * 
	 * @param mapping
	 * @param status
	 * @return query
	 */
	private String getRecordCountQuery(Mapping mapping, List status) {
		
		final String getRecordCountQuery =  "SELECT a.hdr_rule_id, a.sps_id " +
		"FROM bx_cstm_msg_text a, rule b, item c WHERE a.sps_id = c.prmry_cd " +
		"AND a.hdr_rule_id = b.rule_id AND c.ctlg_id = 1938";
		
		final String getRecordCountQueryForTemp = "SELECT t.hdr_rule_id, t.sps_id " +	 
		"FROM TEMP_BX_CSTM_MSG_TEXT t, rule b, item c WHERE t.sps_id = c.prmry_cd " +
		"AND t.hdr_rule_id = b.rule_id AND c.ctlg_id = 1938";
		
		String condition = "";
		String conditionTemp = "";
		String sqlPercentageOperator = "%";
		String rule = "";
		String spsId = "";
		
		if (mapping != null && mapping.getRule() != null
				&& mapping.getRule().getHeaderRuleId() != null
				&& !mapping.getRule().getHeaderRuleId().trim().equals("")) {
			rule = mapping.getRule().getHeaderRuleId().replace('\'', ' ');
			condition = " and a.HDR_RULE_ID LIKE '" 
					+ rule
					+ sqlPercentageOperator + "'";
			conditionTemp = " and t.HDR_RULE_ID LIKE '" 
					+ rule 
					+ sqlPercentageOperator + "'";
		} 
		if (mapping != null && mapping.getSpsId() != null 
				&& mapping.getSpsId().getSpsId() != null 
				&& !mapping.getSpsId().getSpsId().trim().equals("")) {
			spsId = mapping.getSpsId().getSpsId().replace('\'', ' ');
			condition += " and a.SPS_ID LIKE '"  
			+ spsId
			+ sqlPercentageOperator +"'";
			conditionTemp += " and t.SPS_ID LIKE '"  
			+ spsId
			+ sqlPercentageOperator +"'";
		}		
		
		String selectLock = "select customMsgBx.*, lk.bolk_bus_obj_lock_usr_id  from ( ";		
		String lockCondition = ")customMsgBx left outer join bolk_bus_obj_lock lk on lk.bolk_bus_obj_key_id = customMsgBx.hdr_rule_id || customMsgBx.sps_id "
						+ " and lk.bolk_bus_obj_type_nm = 'com.wellpoint.ets.bx.mapping.domain.entity.Mapping'";
		
		String query =  "  select count(*) from (" + selectLock +
		"select * from (" + getRecordCountQuery +
		condition +" and a.in_temp_tab = 'N' " +
		" UNION " +
		getRecordCountQueryForTemp + conditionTemp +")" + lockCondition + ")";		
		
		return query;
	}
	/**
	 * 
	 * A private method to locate the records from main table
	 */
	private String getLocateQuery() {
		/*return "SELECT a.hdr_rule_id, b.rule_shrt_desc, a.sps_id, c.cd_desc_txt, a.msg_text," +
		" a.msg_rqrd_ind, a.creatd_user_id, a.creatd_tm_stmp, a.lst_chg_usr, a.lst_chg_tms," +
		" a.NOTE_TYP_CD, a.in_temp_tab, a.status_cd, (select CD_DESC_TXT from item where CTLG_ID = 3887 and PRMRY_CD = a.NOTE_TYP_CD) NOTE_TYP_CD_DESC , a.data_feed_ind " +
		"FROM bx_cstm_msg_text a, rule b, item c WHERE a.sps_id = c.prmry_cd " +
		"AND a.hdr_rule_id = b.rule_id AND c.ctlg_id = 1938";*/
		
		/*return "SELECT a.hdr_rule_id, b.rule_shrt_desc, a.sps_id, c.cd_desc_txt, a.msg_text," +
				"		a.msg_rqrd_ind, a.creatd_user_id, a.creatd_tm_stmp, a.lst_chg_usr, a.lst_chg_tms," +
				"		a.NOTE_TYP_CD, a.in_temp_tab, a.status_cd," +
				"		(select CD_DESC_TXT from item where CTLG_ID = 3887 and PRMRY_CD = a.NOTE_TYP_CD) " +
				"		NOTE_TYP_CD_DESC , a.data_feed_ind , d.SRVC_TYP_CODE, e.VAL_DESC_TXT" +
				"		FROM bx_cstm_msg_text a, rule b, item c, BX_RULE_SRVC_TYP_ASSN d, bx_hippa_segment_val e" +
				"		WHERE a.sps_id = c.prmry_cd" +
				"		AND a.hdr_rule_id = b.rule_id AND" +
				"		a.hdr_rule_id = d.RULE_ID" +
				"		AND c.ctlg_id = 1938" +
				"		AND d.SRVC_TYP_CODE = e.DATA_ELEMENT_VAL" +
				"		AND e.DATA_ELEMENT_ID = 'EB03'";*/
		
		return  "SELECT distinct  a.hdr_rule_id, a.sps_id , b.rule_shrt_desc,   c.cd_desc_txt," +
				"		 a.msg_text, a.msg_rqrd_ind, a.creatd_user_id, a.creatd_tm_stmp, a.lst_chg_usr, a.lst_chg_tms," +
				"		 a.NOTE_TYP_CD, a.in_temp_tab, a.status_cd, " +
				"		 (SELECT CD_DESC_TXT  FROM item  WHERE CTLG_ID = 3887  AND PRMRY_CD  = a.NOTE_TYP_CD)" +
				"		 NOTE_TYP_CD_DESC , a.data_feed_ind , a.EB03 , a.INDVDL_EB03_ASSN_IND  FROM BX_CSTM_MSG_TEXT a" +
				"		   , rule b, item c WHERE a.sps_id    = c.prmry_cd AND a.hdr_rule_id = b.rule_id " +
				"		  AND c.ctlg_id     = 1938";
	}
	/**
	 * 
	 * A private method to locate the records from temp table
	 */
	private String getLocateQueryForTemp() {
		/*return "SELECT t.hdr_rule_id, b.rule_shrt_desc, t.sps_id, c.cd_desc, a.INDVDL_EB03_ASSN_IND _txt, t.msg_text, " +
		"t.msg_rqrd_ind, t.creatd_user_id, t.creatd_tm_stmp, t.lst_chg_usr, t.lst_chg_tms, " +
		"t.NOTE_TYP_CD, 'Y' as in_temp_tab,t.status_cd, (select CD_DESC_TXT from item where CTLG_ID = 3887 and PRMRY_CD = t.NOTE_TYP_CD) NOTE_TYP_CD_DESC , t.data_feed_ind " +
		"FROM TEMP_BX_CSTM_MSG_TEXT t, rule b, item c WHERE t.sps_id = c.prmry_cd " +
		"AND t.hdr_rule_id = b.rule_id AND c.ctlg_id = 1938";*/
		
		/*return "SELECT t.hdr_rule_id, b.rule_shrt_desc, t.sps_id, c.cd_desc_txt, t.msg_text," +
				"	t.msg_rqrd_ind, t.creatd_user_id, t.creatd_tm_stmp, t.lst_chg_usr, t.lst_chg_tms, " +
				"	t.NOTE_TYP_CD, 'Y' as in_temp_tab,t.status_cd, (select CD_DESC_TXT from item " +
				"	where CTLG_ID = 3887 and PRMRY_CD = t.NOTE_TYP_CD) NOTE_TYP_CD_DESC , t.data_feed_ind , d.SRVC_TYP_CODE, e.VAL_DESC_TXT " +
				"	FROM TEMP_BX_CSTM_MSG_TEXT t, rule b, item c, TEMP_BX_RULE_SRVC_TYP_ASSN d, bx_hippa_segment_val e  WHERE t.sps_id = c.prmry_cd " +
				"	AND t.hdr_rule_id = b.rule_id AND t.hdr_rule_id = d.RULE_ID" +
				"	AND c.ctlg_id = 1938 " +
				"	AND d.SRVC_TYP_CODE = e.DATA_ELEMENT_VAL " +
				"	AND e.DATA_ELEMENT_ID = 'EB03'";*/
		
		
	/*	return "SELECT t.hdr_rule_id, b.rule_shrt_desc, t.sps_id,  c.cd_desc_txt," +
				"  t.msg_text, t.msg_rqrd_ind, t.creatd_user_id, t.creatd_tm_stmp, t.lst_chg_usr, t.lst_chg_tms," +
				" t.NOTE_TYP_CD, 'Y' AS in_temp_tab, t.status_cd, " +
				" (SELECT CD_DESC_TXT  FROM item  WHERE CTLG_ID = 3887  AND PRMRY_CD  = t.NOTE_TYP_CD)" +
				" NOTE_TYP_CD_DESC , t.data_feed_ind , d.SRVC_TYP_CODE FROM TEMP_BX_CSTM_MSG_TEXT t" +
				"  LEFT OUTER JOIN TEMP_BX_RULE_SRVC_TYP_ASSN d on t.hdr_rule_id = d.RULE_ID" +
				"  , rule b, item c WHERE t.sps_id    = c.prmry_cd AND t.hdr_rule_id = b.rule_id " +
				"  AND c.ctlg_id     = 1938 AND t.LST_CHG_USR ='USER'   ORDER BY lst_chg_tms DESC";*/
		
		return "SELECT t.hdr_rule_id,   t.sps_id , b.rule_shrt_desc,  c.cd_desc_txt," +
				"		 t.msg_text, t.msg_rqrd_ind, t.creatd_user_id, t.creatd_tm_stmp, t.lst_chg_usr, t.lst_chg_tms," +
				"		 t.NOTE_TYP_CD, 'Y' AS in_temp_tab, t.status_cd, " +
				"		 (SELECT CD_DESC_TXT  FROM item  WHERE CTLG_ID = 3887  AND PRMRY_CD  = t.NOTE_TYP_CD)" +
				"		 NOTE_TYP_CD_DESC , t.data_feed_ind , t.EB03, t.INDVDL_EB03_ASSN_IND  FROM TEMP_BX_CSTM_MSG_TEXT t" +
				"		   , rule b, item c WHERE t.sps_id    = c.prmry_cd AND t.hdr_rule_id = b.rule_id " +
				"		  AND c.ctlg_id     = 1938";
	}
	public List getRecordsForReport(Mapping mapping, List status) {
		String condition = "";
		String conditionTemp = "";
		String sqlPercentageOperator = "%";
		List result;
		String selectQuery = "SELECT a.hdr_rule_id, b.rule_shrt_desc, a.sps_id, c.cd_desc_txt, a.msg_text,"
				+ "  a.creatd_tm_stmp, "
				+ " a.status_cd, (select CD_DESC_TXT from item where CTLG_ID = 3887 and PRMRY_CD = a.NOTE_TYP_CD) NOTE_TYP_CD_DESC , a.data_feed_ind "
				+ "FROM bx_cstm_msg_text a, rule b, item c WHERE a.sps_id = c.prmry_cd "
				+ "AND a.hdr_rule_id = b.rule_id AND c.ctlg_id = 1938";
		String queryForTemp = "SELECT t.hdr_rule_id, b.rule_shrt_desc, t.sps_id, c.cd_desc_txt, t.msg_text, "
				+ " t.creatd_tm_stmp, "
				+ " t.status_cd, (select CD_DESC_TXT from item where CTLG_ID = 3887 and PRMRY_CD = t.NOTE_TYP_CD) NOTE_TYP_CD_DESC , t.data_feed_ind "
				+ "FROM TEMP_BX_CSTM_MSG_TEXT t, rule b, item c WHERE t.sps_id = c.prmry_cd "
				+ "AND t.hdr_rule_id = b.rule_id AND c.ctlg_id = 1938";
		if (mapping != null && mapping.getRule() != null
				&& mapping.getRule().getHeaderRuleId() != null
				&& !mapping.getRule().getHeaderRuleId().trim().equals("")) {
			String rule = mapping.getRule().getHeaderRuleId()
					.replace('\'', ' ');
			condition = " and a.HDR_RULE_ID LIKE '" + rule
					+ sqlPercentageOperator + "'";
			conditionTemp = " and t.HDR_RULE_ID LIKE '" + rule
					+ sqlPercentageOperator + "'";
		}
		if (mapping != null && mapping.getSpsId() != null
				&& mapping.getSpsId().getSpsId() != null
				&& !mapping.getSpsId().getSpsId().trim().equals("")) {
			String spsId = mapping.getSpsId().getSpsId().replace('\'', ' ');
			condition += " and a.SPS_ID LIKE '" + spsId + sqlPercentageOperator
					+ "'";
			conditionTemp += " and t.SPS_ID LIKE '" + spsId
					+ sqlPercentageOperator + "'";
		}

		String query = "  select * from (" + selectQuery + condition
				+ " and a.in_temp_tab = 'N' " + " UNION " + queryForTemp
				+ conditionTemp + ") ORDER BY hdr_rule_id, sps_id";

		LocateMappingForReport locateMapping = new LocateMappingForReport(
				dataSource, query, status);
		result = locateMapping.execute();

		return result;

	}

	/**
	 * 
	 * A private class to locate mapping
	 * 
	 */
	private static class LocateMappingForReport extends MappingSqlQuery {
		//boolean isPrintFlag = false;

		private LocateMappingForReport(DataSource dataSource, String query,
				List status) {
			super(dataSource, query);
			super.compile();
			//Commented as it is not used anywhere.
			/*if (null != status && status.contains("PRINT")) {
				isPrintFlag = true;
			}*/
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet
		 * , int)
		 */
		protected Object mapRow(ResultSet rs, int arg1) throws SQLException {
			SearchResult searchResult = new SearchResult();
			searchResult.setHeaderRuleId(rs.getString("hdr_rule_id").trim());
			searchResult.setSpsId(rs.getString("SPS_ID").trim());
			searchResult.setHeaderRuleDescription(rs.getString("RULE_SHRT_DESC"));
			searchResult.setSpsRuleDescription(rs.getString("CD_DESC_TXT"));
			searchResult.setMessageText(rs.getString("MSG_TEXT"));
			searchResult.setStatus(rs.getString("STATUS_CD"));
			searchResult.setFormattedDate(BxUtil.getFormattedDateWithOutTime(rs.getDate("CREATD_TM_STMP")));
			return searchResult;
		}
	}
	
	private void eb03ListDescription(){
		/* Reference Data Values -  START*/
		String hippaSegmentName = DomainConstants.EB03_NAME;
		int noOfRecords = 0;
		List eb03List = hippaSegmentRepository
		.findMatchingHippaCodeValuesFromDataDictionary(
				hippaSegmentName, null, noOfRecords);
		for (int count = 0; count < eb03List.size(); count++) {

			HippaCodeValue codeValue = (HippaCodeValue) eb03List.get(count);
			eb03Map.put(codeValue.getValue(), codeValue.getDescription());
			
		}	
	}
	public HippaSegmentRepositoryImpl getHippaSegmentRepository() {
		return hippaSegmentRepository;
	}

	public void setHippaSegmentRepository(
			HippaSegmentRepositoryImpl hippaSegmentRepository) {
		this.hippaSegmentRepository = hippaSegmentRepository;
	}

	
	private String getLocateQueryForEditView() {
		
		return "SELECT a.hdr_rule_id, b.rule_shrt_desc, a.sps_id,  c.cd_desc_txt," +
				"  a.msg_text, a.msg_rqrd_ind, a.creatd_user_id, a.creatd_tm_stmp, a.lst_chg_usr, a.lst_chg_tms," +
				" a.NOTE_TYP_CD, a.in_temp_tab, a.status_cd, " +
				" (SELECT CD_DESC_TXT  FROM item  WHERE CTLG_ID = 3887  AND PRMRY_CD  = a.NOTE_TYP_CD)" +
				" NOTE_TYP_CD_DESC , a.data_feed_ind , a.EB03, a.INDVDL_EB03_ASSN_IND FROM BX_CSTM_MSG_TEXT a" +
				"  , rule b, item c WHERE a.sps_id    = c.prmry_cd AND a.hdr_rule_id = b.rule_id " +
				"  AND c.ctlg_id     = 1938 ";

	}
	/**
	 * 
	 * A private method to locate the records from temp table
	 */
	private String getLocateQueryForEditViewTemp() {
		return "SELECT t.hdr_rule_id, b.rule_shrt_desc, t.sps_id,  c.cd_desc_txt," +
				"  t.msg_text, t.msg_rqrd_ind, t.creatd_user_id, t.creatd_tm_stmp, t.lst_chg_usr, t.lst_chg_tms," +
				" t.NOTE_TYP_CD, 'Y' AS in_temp_tab, t.status_cd, " +
				" (SELECT CD_DESC_TXT  FROM item  WHERE CTLG_ID = 3887  AND PRMRY_CD  = t.NOTE_TYP_CD)" +
				" NOTE_TYP_CD_DESC , t.data_feed_ind , t.EB03, t.INDVDL_EB03_ASSN_IND FROM TEMP_BX_CSTM_MSG_TEXT t" +
				"  , rule b, item c WHERE t.sps_id    = c.prmry_cd AND t.hdr_rule_id = b.rule_id " +
				"  AND c.ctlg_id     = 1938 ";
		
	}
	
	private void getEb03MappedToHeaderRuleList(String ruleId){

		String query = "";
		String condition = "";
		String conditionTemp = "";
		List<String> eb03List = new ArrayList<String>();
		
		if (ruleId != null && !ruleId.trim().equals("")) {
			String inTempTabQuery = "SELECT IN_TEMP_TAB from BX_RULE_SRVC_TYP_ASSN Where RULE_ID = '"+ruleId+"'";
			
			InTempTabForRuleQuery inTempTabForRuleQuery = new InTempTabForRuleQuery(dataSource,
					inTempTabQuery);
			List<String> inTempTabValList = inTempTabForRuleQuery.execute();
			String inTempTabVal = "";
			
			if(null != inTempTabValList && inTempTabValList.size() > 0){
				if(null != inTempTabValList.get(0)){
					inTempTabVal = String.valueOf(inTempTabValList.get(0));
				}
				if(null != inTempTabVal && inTempTabVal.equalsIgnoreCase("Y")){
					query  =" SELECT SRVC_TYP_CODE from TEMP_BX_RULE_SRVC_TYP_ASSN Where RULE_ID Like '"+ruleId+"'";
				}else{
					query  =" SELECT SRVC_TYP_CODE from BX_RULE_SRVC_TYP_ASSN Where RULE_ID Like '"+ruleId+"'";
				}
				
			}else{
				query  =" SELECT SRVC_TYP_CODE from BX_RULE_SRVC_TYP_ASSN Where RULE_ID Like '"+ruleId+"'";
			}
			
			ServiceTypeRuleMapping serviceTypeRuleMapping = new ServiceTypeRuleMapping(dataSource,
					query);
			eb03List = serviceTypeRuleMapping.execute();
		}
	}
	
	private static class ServiceTypeRuleMapping extends MappingSqlQuery {
		boolean isPrintFlag = false;
		private ServiceTypeRuleMapping(DataSource dataSource, String query) {
			super(dataSource, query);
			super.compile();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet,
		 *      int)
		 */
		protected Object mapRow(ResultSet rs, int arg1) throws SQLException {
			eb03MappedToHeaderRuleList.add(null != rs.getString("SRVC_TYP_CODE") ? (String)rs.getString("SRVC_TYP_CODE").trim(): "");
			return eb03MappedToHeaderRuleList;
		}
	}
	
	private static class InTempTabForRuleQuery extends MappingSqlQuery {
		boolean isPrintFlag = false;
		private InTempTabForRuleQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			super.compile();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet,
		 *      int)
		 */
		protected Object mapRow(ResultSet rs, int arg1) throws SQLException {
			String val = new String();
			val = (null != rs.getString("IN_TEMP_TAB") ? rs.getString("IN_TEMP_TAB").toString().trim(): "");
			return val;
		}
	}
	public static List<String> getEb03MappedToHeaderRuleList() {
		return eb03MappedToHeaderRuleList;
	}
	public static void setEb03MappedToHeaderRuleList(
			List<String> eb03MappedToHeaderRuleList) {
		LocateCustomMessageRepositoryImpl.eb03MappedToHeaderRuleList = eb03MappedToHeaderRuleList;
	}
	
	private int getQueryToCreateCustomMessage(String ruleId, String spsId){
		
		String query = "Select count(*) from( (Select HDR_RULE_ID from BX_CSTM_MSG_TEXT where HDR_RULE_ID ='"+ruleId+"'" +
				" AND SPS_ID = '"+spsId+"') UNION (Select HDR_RULE_ID from TEMP_BX_CSTM_MSG_TEXT" +
						" where HDR_RULE_ID ='"+ruleId+"' AND SPS_ID = '"+spsId+"'))"; 
		
		CustomMessageMapping customMessageMapping = new CustomMessageMapping(dataSource,
				query);
		List customMessageMappingList = customMessageMapping.execute();
		Integer numOfMessages = (Integer) customMessageMappingList.get(0);
		return numOfMessages.intValue();
		
	}
	private static class CustomMessageMapping extends MappingSqlQuery {
		
		private CustomMessageMapping(DataSource dataSource, String query) {
			super(dataSource, query);
			super.compile();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet,
		 *      int)
		 */
		protected Object mapRow(ResultSet rs, int arg1) throws SQLException {
			return Integer.valueOf(rs.getInt(1));
		}
	}
}
