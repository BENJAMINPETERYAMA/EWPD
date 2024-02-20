/*
 * <LocateRuleIdRepositoryImpl.java>
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.owasp.esapi.ESAPI;
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
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.vo.SearchResult;
import com.wellpoint.ets.bx.mapping.domain.vo.User;
import com.wellpoint.ets.bx.mapping.repository.HippaSegmentRepositoryImpl;
import com.wellpoint.ets.bx.mapping.util.BxUtil;


/**
 * @author UST-GLOBAL This is an implementation class for locating or searching
 *         a header rule or multiple header rule based on certain search
 *         criteria
 */
public class LocateRuleIdRepositoryImpl implements LocateRepository {
	private DataSource dataSource;

	private String locateHeaderRuleQuery;

	private HashMap eb03Map = new HashMap();
	private HashMap iii02Map = new HashMap();
		
	private ItemRepository itemRepository;

	private static final String locateDistinctMappedRecords = "select AM.* from (select TX.* from (select BX.* from (select DISTINCT b.RULE_ID,"
		+ " b.STATUS_CD AS STATUS_CD "
		+ " from BX_RULE_SRVC_TYP_ASSN b inner join Rule r on b.RULE_ID=r.RULE_ID "
		+ " where"
		+ " b.in_temp_tab ='N'"
		+ " union "
		+ "select DISTINCT t.RULE_ID, t.STATUS_CD as STATUS_CD "
		+ " from BX_RULE_SRVC_TYP_ASSN b,TEMP_BX_RULE_SRVC_TYP_ASSN t,Rule r where b.rule_id=t.rule_id and t.rule_id=r.rule_id and"
		+ " b.in_temp_tab='Y')" + "BX)TX ORDER BY TX.RULE_ID)AM";
	private static final String locateDistinctUnmappedRecords = "SELECT BD.* FROM (SELECT AB.* FROM (select BX.* FROM (SELECT  DISTINCT RULE_ID,"
		+ " null as STATUS_CD "
		+ " FROM  RULE"
		+ " WHERE RULE_TYP_CD = 'WPDAUTOBG' "
		+ "  AND  WPD_DEL_FLAG = 'N' "
		+ "AND RULE_ID NOT IN (SELECT RULE_ID FROM BX_RULE_SRVC_TYP_ASSN))BX"
		+ " ORDER BY BX.RULE_ID)AB)BD";
	
	// Reference Data Values -  START
	private HippaSegmentRepositoryImpl hippaSegmentRepository;
	// Reference Data Values -  END
	
	private static Logger logger = Logger.getLogger(LocateRuleIdRepositoryImpl.class);
	/**
	 * For pagination in locate result page
	 * 
	 * @param mapping
	 * @return int
	 */
	public int getRecordCount(Mapping mapping, List status) {		

		String unionCondition = " UNION ALL ";
		String locateCondition = "select * from(";

		String appendQueryString = "";
		String likeRuleidForMapped = null;
		String likeRuleidForUnmapped = null;

		if (mapping != null && (mapping.getRule().getHeaderRuleId()) != null) {
			String ruleId = mapping.getRule().getHeaderRuleId().replace('\'', ' ');
			likeRuleidForMapped = " WHERE AM.RULE_ID LIKE '"
					+ ruleId + "'";
			likeRuleidForUnmapped = " WHERE BD.RULE_ID LIKE '"
					+ ruleId + "'";
			// locate Section
			if (null != status && status.size() >= 1) {
				// To locate Not Applicable
				if ((status.contains(DomainConstants.STATUS_NOT_APPLICABLE))
						&& ((!status.contains(DomainConstants.MAPPED_STATUS)))
						&& ((!status.contains(DomainConstants.UNMAPPED_STATUS)))) {

					appendQueryString = locateCondition + locateDistinctMappedRecords
							+ likeRuleidForMapped + ")"
							+ "where STATUS_CD='NOT_APPLICABLE'";

				}

				// To locate Mapped
				else if ((status.contains(DomainConstants.MAPPED_STATUS))
						&& ((!status
								.contains(DomainConstants.STATUS_NOT_APPLICABLE)))
						&& ((!status.contains(DomainConstants.UNMAPPED_STATUS)))) {
					/**
					 * the various status is possible for a particular rule id
					 * is also checked in the query
					 */

					appendQueryString = locateCondition
							+ locateDistinctMappedRecords
							+ likeRuleidForMapped
							+ ")"
							+ "where STATUS_CD='BEING_MODIFIED' OR STATUS_CD='PUBLISHED' OR STATUS_CD='BUILDING' OR STATUS_CD='SCHEDULED_TO_TEST' OR STATUS_CD='OBJECT_TRANSFERRED' OR STATUS_CD='SCHEDULED_TO_PRODUCTION'";

				}
				// To locate Unmapped
				else if (((status.contains(DomainConstants.UNMAPPED_STATUS))
								&& ((!status
										.contains(DomainConstants.STATUS_NOT_APPLICABLE))) && ((!status
								.contains(DomainConstants.MAPPED_STATUS))))) {				
						appendQueryString = locateCondition + locateDistinctUnmappedRecords
							+ likeRuleidForUnmapped + ")";	
				}
				// Locate mapped and not applicable

				else if ((status.contains(DomainConstants.MAPPED_STATUS))
						&& ((status
								.contains(DomainConstants.STATUS_NOT_APPLICABLE)))
						&& ((!status.contains(DomainConstants.UNMAPPED_STATUS)))) {
					// The various status that is possible for a particular
					// Ruleid is checked
					appendQueryString = locateCondition
							+ locateDistinctMappedRecords
							+ likeRuleidForMapped
							+ ")"
							+ "where STATUS_CD='BEING_MODIFIED' OR STATUS_CD='PUBLISHED' OR STATUS_CD='BUILDING' OR STATUS_CD='SCHEDULED_TO_TEST' OR STATUS_CD='OBJECT_TRANSFERRED'OR STATUS_CD='NOT_APPLICABLE' OR STATUS_CD='SCHEDULED_TO_PRODUCTION'";

				}
				// Locate unmapped and not applicable
				else if ((status.contains(DomainConstants.UNMAPPED_STATUS))
						&& ((status
								.contains(DomainConstants.STATUS_NOT_APPLICABLE)))
						&& ((!status.contains(DomainConstants.MAPPED_STATUS)))) {
					// The query for unmapped condition is constructed
					String unmappedRecordsForLocate = locateCondition
							+ locateDistinctUnmappedRecords + likeRuleidForUnmapped
							+ ")";//
					// query with where condition being added as not applicable
					appendQueryString = locateCondition + locateDistinctMappedRecords
							+ likeRuleidForMapped + ")"
							+ " where STATUS_CD='NOT_APPLICABLE'"
							+ unionCondition + unmappedRecordsForLocate;

				}
				// Locate mapped and unmapped
				else if ((status.contains(DomainConstants.UNMAPPED_STATUS))
						&& ((status.contains(DomainConstants.MAPPED_STATUS)))
						&& ((!status
								.contains(DomainConstants.STATUS_NOT_APPLICABLE)))) {
					// The query for unmapped condition is constructed
					String unmappedRecordsForLocate = locateCondition
							+ locateDistinctUnmappedRecords + likeRuleidForUnmapped
							+ ")";
					// The various status that is possible for a particular
					// Ruleid is checked
					appendQueryString = locateCondition
							+ locateDistinctMappedRecords
							+ likeRuleidForMapped
							+ ")"
							+ "where STATUS_CD='BEING_MODIFIED' OR STATUS_CD='PUBLISHED' OR STATUS_CD='BUILDING' OR STATUS_CD='SCHEDULED_TO_TEST' OR STATUS_CD='OBJECT_TRANSFERRED' OR STATUS_CD='SCHEDULED_TO_PRODUCTION'"
							+ unionCondition + unmappedRecordsForLocate;

				}
				// Locate mapped,unmapped and not applicable
				else if ((status.contains(DomainConstants.UNMAPPED_STATUS))
						&& ((status.contains(DomainConstants.MAPPED_STATUS)))
						&& ((status
								.contains(DomainConstants.STATUS_NOT_APPLICABLE)))) {
					// The query for unmapped condition is constructed
					String unmappedRecordsForLocate = locateCondition
							+ locateDistinctUnmappedRecords + likeRuleidForUnmapped
							+ ")";
					// The various status that is possible for a particular
					// Ruleid is checked
					appendQueryString = locateCondition
							+ locateDistinctMappedRecords
							+ likeRuleidForMapped
							+ ")"
							+ "where STATUS_CD='BEING_MODIFIED' OR STATUS_CD='PUBLISHED' OR STATUS_CD='BUILDING' OR STATUS_CD='SCHEDULED_TO_TEST' OR STATUS_CD='OBJECT_TRANSFERRED'OR STATUS_CD='NOT_APPLICABLE' OR STATUS_CD='SCHEDULED_TO_PRODUCTION'"
							+ unionCondition + unmappedRecordsForLocate;

				}
			}
			// when nothing is checked(mapped,unmapped and not applicable)
			else {
				appendQueryString = locateCondition + locateDistinctMappedRecords
						+ likeRuleidForMapped + ")";

			}
		}
		String selectLock = "select ruleBx.*, lk.bolk_bus_obj_lock_usr_id  from ( ";		
		String lockCondition = ")ruleBx left outer join bolk_bus_obj_lock lk on lk.bolk_bus_obj_key_id = ruleBx.rule_id"
						+ " and lk.bolk_bus_obj_type_nm = 'com.wellpoint.ets.bx.mapping.domain.entity.Mapping'";

		appendQueryString = "select count(*) from ("+selectLock+appendQueryString+lockCondition+")";		

		FindTotalNoOfRecordsQuery totalNoOfRcrdsQry = new FindTotalNoOfRecordsQuery(
				dataSource, appendQueryString);
		logger.info("totalNoOfRcrdsQry: "+totalNoOfRcrdsQry);
		
		List totalNoOfRecordsList = totalNoOfRcrdsQry.execute();
		Integer totalNoOfRecords = (Integer) totalNoOfRecordsList.get(0);
		return totalNoOfRecords.intValue();

	}
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
	 * For getting exact match/retrieving records based on the status and logged
	 * in user
	 * 
	 * @param Mapping
	 * @param status
	 * @param loggedInUser
	 * @return List
	 */
	public List getRecords(Mapping mapping, List status, String loggedInUser,
			int noOfRecords, int auditTrailCount, Page page) {

	

		String locateMappedRecords = "select AM.* from (select TX.* from (select BX.* from (select b.RULE_ID,r.RULE_SHRT_DESC,"
            + " b.CREATD_TM_STMP as CREATD_DATE,b.LST_CHG_TMS as LST_CHG_TMS,b.STATUS_CD AS STATUS_CD ,b.SRVC_TYP_CODE AS SRVC_TYP_CODE,b.SEND_DYNMC_INFO AS SEND_DYNMC_INFO,b.IN_TEMP_TAB as IN_TEMP_TAB,b.CREATD_USER_ID as CREATD_USER_ID,b.LST_CHG_USR as LST_CHG_USR,b.PROC_EXCLD_IND as PROC_EXCLD_IND,b.III02, b.INDVDL_EB03_ASSN_IND ,u.UM_RULE_ID,"
            + " (select um.RULE_SHRT_DESC from rule um where um.rule_id = u.UM_RULE_ID and um.rule_typ_cd = '"+DomainConstants.UMRULE_NAME+"' )as UM_RULE_DESC, b.DATA_FEED_IND "
            + " from BX_RULE_SRVC_TYP_ASSN b inner join Rule r on b.RULE_ID=r.RULE_ID left outer join BX_RULE_UMRULE_ASSN u on u.rule_id = b.rule_id "
            + " where"
            + " b.in_temp_tab ='N'"
            + " union "
            + " select t.RULE_ID,r.RULE_SHRT_DESC,t.CREATD_TM_STMP as CREATD_DATE,t.LST_CHG_TMS as LST_CHG_TMS,t.STATUS_CD as STATUS_CD,t.SRVC_TYP_CODE AS SRVC_TYP_CODE,t.SEND_DYNMC_INFO AS SEND_DYNMC_INFO,'Y' AS IN_TEMP_TAB,t.CREATD_USER_ID as CREATD_USER_ID,t.LST_CHG_USR as LST_CHG_USR, t.PROC_EXCLD_IND as PROC_EXCLD_IND,t.III02, t.INDVDL_EB03_ASSN_IND, u.UM_RULE_ID,"
            + " (select um.RULE_SHRT_DESC from rule um where um.rule_id = u.UM_RULE_ID and um.rule_typ_cd = '"+DomainConstants.UMRULE_NAME+"' )as UM_RULE_DESC, t.DATA_FEED_IND "
            + " from BX_RULE_SRVC_TYP_ASSN b,Rule r,TEMP_BX_RULE_SRVC_TYP_ASSN t left outer join TEMP_BX_RULE_UMRULE_ASSN u on u.rule_id = t.rule_id  where b.rule_id=t.rule_id and t.rule_id=r.rule_id and"
            + " b.in_temp_tab='Y')" + "BX)TX ORDER BY TX.RULE_ID)AM";
		
		String locateUnmappedRecords = "SELECT BD.* FROM (SELECT AB.* FROM (select BX.* FROM (SELECT  RULE_ID, RULE_SHRT_DESC,"
				+ "RULE_APRVD_DT AS CREATD_DATE ,"
				+ "RULE_APRVD_DT AS LST_CHG_TMS ,'UNMAPPED' as STATUS_CD ,null as SRVC_TYP_CODE,null as SEND_DYNMC_INFO,NULL AS IN_TEMP_TAB,null as CREATD_USER_ID,null as LST_CHG_USR ,null as PROC_EXCLD_IND,null as III02, 'N' as INDVDL_EB03_ASSN_IND, null as UM_RULE_ID, null as UM_RULE_DESC,'N' as DATA_FEED_IND   "
				+ " FROM  RULE"
				+ " WHERE RULE_TYP_CD = 'WPDAUTOBG' "
				+ "  AND  WPD_DEL_FLAG = 'N' "
				+ "AND RULE_ID NOT IN (SELECT RULE_ID FROM BX_RULE_SRVC_TYP_ASSN))BX"
				+ " ORDER BY BX.RULE_ID)AB)BD";
		
		String locateInprogressRule = "SELECT b.RULE_ID, r.RULE_SHRT_DESC, b.LST_CHG_TMS    " +
		" AS LST_CHG_TMS, b.STATUS_CD       AS STATUS_CD , b.LST_CHG_USR     AS LST_CHG_USR " +
		"FROM BX_RULE_SRVC_TYP_ASSN b INNER JOIN Rule r ON b.RULE_ID=r.RULE_ID " +
		" WHERE b.in_temp_tab ='N' UNION  SELECT t.RULE_ID, r.RULE_SHRT_DESC, t.LST_CHG_TMS   " +
		"  AS LST_CHG_TMS, t.STATUS_CD       AS STATUS_CD,  t.LST_CHG_USR  " +
		"   AS LST_CHG_USR FROM BX_RULE_SRVC_TYP_ASSN b,Rule r,TEMP_BX_RULE_SRVC_TYP_ASSN t" +
		" WHERE b.rule_id  =t.rule_id AND t.rule_id    =r.rule_id AND b.in_temp_tab='Y'" +
		" ORDER BY LST_CHG_TMS DESC";

		String unionCondition = " UNION ALL "; 
		String locateCondition = "select * from(";
		String appendQueryString = "";
		String distinctQuery = "";
		String likeRuleidForMapped = null;
		String likeRuleidForUnmapped = null;

		String conditionForInProgress = null;

		if (mapping != null && (mapping.getRule().getHeaderRuleId()) != null) {
			String ruleId = mapping.getRule().getHeaderRuleId().replace('\'', ' ');
			likeRuleidForMapped = " WHERE AM.RULE_ID LIKE '"
					+ ruleId + "'";
			likeRuleidForUnmapped = " WHERE BD.RULE_ID LIKE '"
					+ ruleId + "'";
			// locate Section
			if (null != status && status.size() >= 1) {
				// To locate Not Applicable
				if ((status.contains(DomainConstants.STATUS_NOT_APPLICABLE))
						&& ((!status.contains(DomainConstants.MAPPED_STATUS)))
						&& ((!status.contains(DomainConstants.UNMAPPED_STATUS)))) {

					appendQueryString = locateCondition + locateMappedRecords
							+ likeRuleidForMapped + ")"
							+ "where STATUS_CD='NOT_APPLICABLE'";
					
					distinctQuery = locateCondition + locateDistinctMappedRecords
					+ likeRuleidForMapped + ")"
					+ "where STATUS_CD='NOT_APPLICABLE'";

				}

				// To locate Mapped
				else if ((status.contains(DomainConstants.MAPPED_STATUS))
						&& ((!status
								.contains(DomainConstants.STATUS_NOT_APPLICABLE)))
						&& ((!status.contains(DomainConstants.UNMAPPED_STATUS)))) {
					/**
					 * the various status is possible for a particular rule id
					 * is also checked in the query
					 */

					appendQueryString = locateCondition
							+ locateMappedRecords
							+ likeRuleidForMapped
							+ ")"
							+ "where STATUS_CD='BEING_MODIFIED' OR STATUS_CD='PUBLISHED' OR STATUS_CD='BUILDING' OR STATUS_CD='SCHEDULED_TO_TEST' OR STATUS_CD='OBJECT_TRANSFERRED' OR STATUS_CD='SCHEDULED_TO_PRODUCTION'";

					distinctQuery = locateCondition
					+ locateDistinctMappedRecords
					+ likeRuleidForMapped
					+ ")"
					+ "where STATUS_CD='BEING_MODIFIED' OR STATUS_CD='PUBLISHED' OR STATUS_CD='BUILDING' OR STATUS_CD='SCHEDULED_TO_TEST' OR STATUS_CD='OBJECT_TRANSFERRED' OR STATUS_CD='SCHEDULED_TO_PRODUCTION'";

				}
				// To locate Unmapped
				else if (((status.contains(DomainConstants.UNMAPPED_STATUS))
								&& ((!status
										.contains(DomainConstants.STATUS_NOT_APPLICABLE))) && ((!status
								.contains(DomainConstants.MAPPED_STATUS))))) {				
					if(noOfRecords != -1) {
						appendQueryString = locateCondition + locateUnmappedRecords
						+ likeRuleidForUnmapped + ")" + "Where rownum < " + noOfRecords;
					}
					else {
					appendQueryString = locateCondition + locateUnmappedRecords
							+ likeRuleidForUnmapped + ")";	
					
					distinctQuery = locateCondition + locateDistinctUnmappedRecords
					+ likeRuleidForUnmapped + ")";	
					}
				}
				// Locate mapped and not applicable

				else if ((status.contains(DomainConstants.MAPPED_STATUS))
						&& ((status
								.contains(DomainConstants.STATUS_NOT_APPLICABLE)))
						&& ((!status.contains(DomainConstants.UNMAPPED_STATUS)))) {
					// The various status that is possible for a particular
					// Ruleid is checked
					appendQueryString = locateCondition
							+ locateMappedRecords
							+ likeRuleidForMapped
							+ ")"
							+ "where STATUS_CD='BEING_MODIFIED' OR STATUS_CD='PUBLISHED' OR STATUS_CD='BUILDING' OR STATUS_CD='SCHEDULED_TO_TEST' OR STATUS_CD='OBJECT_TRANSFERRED'OR STATUS_CD='NOT_APPLICABLE' OR STATUS_CD='SCHEDULED_TO_PRODUCTION'";

					distinctQuery = locateCondition
					+ locateDistinctMappedRecords
					+ likeRuleidForMapped
					+ ")"
					+ "where STATUS_CD='BEING_MODIFIED' OR STATUS_CD='PUBLISHED' OR STATUS_CD='BUILDING' OR STATUS_CD='SCHEDULED_TO_TEST' OR STATUS_CD='OBJECT_TRANSFERRED'OR STATUS_CD='NOT_APPLICABLE' OR STATUS_CD='SCHEDULED_TO_PRODUCTION'";

				}
				// Locate unmapped and not applicable
				else if ((status.contains(DomainConstants.UNMAPPED_STATUS))
						&& ((status
								.contains(DomainConstants.STATUS_NOT_APPLICABLE)))
						&& ((!status.contains(DomainConstants.MAPPED_STATUS)))) {
					// The query for unmapped condition is constructed
					String unmappedRecordsForLocate = locateCondition
							+ locateUnmappedRecords + likeRuleidForUnmapped
							+ ")";//
					// query with where condition being added as not applicable
					appendQueryString = locateCondition + locateMappedRecords
							+ likeRuleidForMapped + ")"
							+ " where STATUS_CD='NOT_APPLICABLE'"
							+ unionCondition + unmappedRecordsForLocate;
					
					distinctQuery = locateCondition + locateDistinctMappedRecords
					+ likeRuleidForMapped + ")"
					+ " where STATUS_CD='NOT_APPLICABLE'"
					+ unionCondition + locateCondition
					+ locateDistinctUnmappedRecords + likeRuleidForUnmapped
					+ ")";

				}
				// Locate mapped and unmapped
				else if ((status.contains(DomainConstants.UNMAPPED_STATUS))
						&& ((status.contains(DomainConstants.MAPPED_STATUS)))
						&& ((!status
								.contains(DomainConstants.STATUS_NOT_APPLICABLE)))) {
					// The query for unmapped condition is constructed
					String unmappedRecordsForLocate = locateCondition
							+ locateUnmappedRecords + likeRuleidForUnmapped
							+ ")";
					// The various status that is possible for a particular
					// Ruleid is checked
					appendQueryString = locateCondition
							+ locateMappedRecords
							+ likeRuleidForMapped
							+ ")"
							+ "where STATUS_CD='BEING_MODIFIED' OR STATUS_CD='PUBLISHED' OR STATUS_CD='BUILDING' OR STATUS_CD='SCHEDULED_TO_TEST' OR STATUS_CD='OBJECT_TRANSFERRED' OR STATUS_CD='SCHEDULED_TO_PRODUCTION'"
							+ unionCondition + unmappedRecordsForLocate;
					
					distinctQuery = locateCondition
					+ locateDistinctMappedRecords
					+ likeRuleidForMapped
					+ ")"
					+ "where STATUS_CD='BEING_MODIFIED' OR STATUS_CD='PUBLISHED' OR STATUS_CD='BUILDING' OR STATUS_CD='SCHEDULED_TO_TEST' OR STATUS_CD='OBJECT_TRANSFERRED' OR STATUS_CD='SCHEDULED_TO_PRODUCTION'"
					+ " UNION " 
					+ locateCondition
					+ locateDistinctUnmappedRecords + likeRuleidForUnmapped
					+ ")";


				}
				// Locate mapped,unmapped and not applicable
				else if ((status.contains(DomainConstants.UNMAPPED_STATUS))
						&& ((status.contains(DomainConstants.MAPPED_STATUS)))
						&& ((status
								.contains(DomainConstants.STATUS_NOT_APPLICABLE)))) {
					// The query for unmapped condition is constructed
					String unmappedRecordsForLocate = locateCondition
							+ locateUnmappedRecords + likeRuleidForUnmapped
							+ ")";
					// The various status that is possible for a particular
					// Ruleid is checked
					appendQueryString = locateCondition
							+ locateMappedRecords
							+ likeRuleidForMapped
							+ ")"
							+ "where STATUS_CD='BEING_MODIFIED' OR STATUS_CD='PUBLISHED' OR STATUS_CD='BUILDING' OR STATUS_CD='SCHEDULED_TO_TEST' OR STATUS_CD='OBJECT_TRANSFERRED'OR STATUS_CD='NOT_APPLICABLE' OR STATUS_CD='SCHEDULED_TO_PRODUCTION'"
							+ unionCondition + unmappedRecordsForLocate;
					
					distinctQuery = locateCondition
					+ locateDistinctMappedRecords
					+ likeRuleidForMapped
					+ ")"
					+ "where STATUS_CD='BEING_MODIFIED' OR STATUS_CD='PUBLISHED' OR STATUS_CD='BUILDING' OR STATUS_CD='SCHEDULED_TO_TEST' OR STATUS_CD='OBJECT_TRANSFERRED'OR STATUS_CD='NOT_APPLICABLE' OR STATUS_CD='SCHEDULED_TO_PRODUCTION'"
					+ " UNION " 
					+ locateCondition
					+ locateDistinctUnmappedRecords + likeRuleidForUnmapped
					+ ")";

				}
			}
			// when nothing is checked(mapped,unmapped and not applicable)
			else {
				appendQueryString = locateCondition + locateMappedRecords
						+ likeRuleidForMapped + ")";
				
				distinctQuery = locateCondition + locateDistinctMappedRecords
				+ likeRuleidForMapped + ")";

			}
		}
		// Inprogress Section
		if (null != status && status.contains("IN-PROGRESS")) {
			conditionForInProgress = " and status_cd <> '"
				+ DomainConstants.STATUS_NOT_APPLICABLE + "'"
				+ " and status_cd <> '"
				+ DomainConstants.STATUS_PUBLISHED + "'";
			
			appendQueryString = " SELECT rule_id, MAX(LST_CHG_TMS) as LST_CHG_TMS, " +
					"STATUS_CD, RULE_SHRT_DESC, LST_CHG_USR FROM ("
				+ locateInprogressRule + ") where 1=1 ";
			if (null != loggedInUser) {
				appendQueryString += " and LST_CHG_USR='" + loggedInUser
				+ "'";
			}

			appendQueryString += conditionForInProgress + " GROUP BY rule_id, " +
					"STATUS_CD, RULE_SHRT_DESC, LST_CHG_USR " +
					"ORDER BY LST_CHG_TMS DESC";
			
			if (noOfRecords != -1) {

				appendQueryString = "SELECT * FROM ( "+appendQueryString
				+ " ) Where rownum < " + noOfRecords ;

			} 
			
			if (null != status && status.contains("PRINT")) {
				appendQueryString = "SELECT * FROM ( "+appendQueryString+")  ORDER BY RULE_ID";
			}
		}
		
		// Unmapped Section
		if (null == mapping && null != status && status.contains("UNMAPPED")) {
			if (noOfRecords != -1) {
				appendQueryString = locateCondition + locateUnmappedRecords
						+ ")" + "Where rownum < " + noOfRecords;
			} else if(noOfRecords == -1) {
				appendQueryString = locateCondition + locateUnmappedRecords
						+ ")";
			}
		}
		List result = null;
//		 Page will have value when calling locate from view landing page
		if (page != null) {   
			appendQueryString  = "select * from ("
								+ appendQueryString+ ")where RULE_ID in ("
								+ "select RULE_ID from("
								+"select RULE_ID, ROWNUM rnm from("
								+ distinctQuery
								+ ") ) where rnm between "
								+page.getStartRowNum() +" and "+ page.getEndRowNum()+") ORDER BY RULE_ID";
		}
		String selectLock = "select ruleBx.*, lk.bolk_bus_obj_lock_usr_id  from ( ";		
		String lockCondition = ")ruleBx left outer join bolk_bus_obj_lock lk on lk.bolk_bus_obj_key_id = ruleBx.rule_id"
						+ " and lk.bolk_bus_obj_type_nm = 'com.wellpoint.ets.bx.mapping.domain.entity.Mapping'";
		
		appendQueryString = selectLock + appendQueryString + lockCondition;
		long locateMappingStartTime = System.currentTimeMillis();
		if (null != status && status.contains("IN-PROGRESS")) {
			LocateHeaderRuleInProgressQuery locateHeaderRuleInProgressQuery = new LocateHeaderRuleInProgressQuery(
					dataSource, appendQueryString, status);
			logger.info("locateHeaderRuleInProgressQuery: "+ESAPI.encoder().encodeForHTML(appendQueryString));
			result = locateHeaderRuleInProgressQuery.execute();
		}
		else {
			LocateHeaderRuleQuery locateHeaderRuleQuery = new LocateHeaderRuleQuery(
					dataSource, appendQueryString, status);
			logger.info("locateHeaderRuleQuery: "+ESAPI.encoder().encodeForHTML(appendQueryString));
			eb03ListDescription();
			iii02ListDescription();
			result = locateHeaderRuleQuery.execute();
			/**
			 * Checks whether the result is null and is not empty
			 */
			if (null != result && !result.isEmpty()) {
				
				result = locateMapping(result);
				Mapping ruleMappingObj = (Mapping) result.get(0);
				List auditTrails = getAuditInfo(ruleMappingObj.getRule().getHeaderRuleId(), auditTrailCount);
			
				ruleMappingObj.setAuditTrails(auditTrails);

			}
		}
		long locateMappingEndTime = System.currentTimeMillis();
		
		logger.info("Time taken for executing Rule Query = "+ (locateMappingEndTime - locateMappingStartTime));

		return result;
	}

	/**
	 * Inner class for Retrieve Mapping
	 */

	private final class LocateHeaderRuleQuery extends MappingSqlQuery {
		boolean isPrintFlag = false;
		private LocateHeaderRuleQuery(DataSource dataSource, String query, List status) {
			super(dataSource, query);
			compile();
			if (null != status && status.contains("PRINT")) {
				isPrintFlag = true;
			}
			
		}

		/**
		 * sets the values to mapping object
		 * 
		 * @param ResultSet
		 * @param arg1
		 * @throws SQLException
		 */
		protected Object mapRow(ResultSet rs, int arg1) throws SQLException {
			Mapping mapping = new Mapping();
			Rule rule = new Rule();
			rule.setHeaderRuleId(rs.getString("RULE_ID"));	
			if(null != rs.getString("RULE_SHRT_DESC") && !isPrintFlag){
				rule.setRuleDesc(BxUtil.escapeSpecialCharacters(rs.getString("RULE_SHRT_DESC")));
			}	
			else{
				rule.setRuleDesc(rs.getString("RULE_SHRT_DESC"));
			}	
			mapping.setRule(rule);
			if (isPrintFlag) {
				mapping.setSortField("RULE");
			}
			User user = new User();
			user.setLastUpdatedUserName(rs.getString("LST_CHG_USR"));
			user.setCreatedUserName(rs.getString("CREATD_USER_ID"));
			mapping.setUser(user);
			mapping.setCreatedTmStamp(rs.getDate("CREATD_DATE"));
			mapping.setLastChangedTmStamp(rs.getTimestamp("LST_CHG_TMS"));
			mapping.setVariableMappingStatus(rs.getString("STATUS_CD"));
			mapping.setSensitiveBenefitIndicator(rs
					.getString("SEND_DYNMC_INFO"));
			mapping.setInTempTable(rs.getString("IN_TEMP_TAB"));

			HippaCodeValue hippaCodeValue = new HippaCodeValue();

			mapping.setEb03(new HippaSegment());
			List hippaCodeEB03List = new ArrayList();
			hippaCodeValue.setValue(rs.getString("SRVC_TYP_CODE"));
			hippaCodeValue.setDescription(
					(String) eb03Map.get(
							(null != rs.getString("SRVC_TYP_CODE") ? rs.getString("SRVC_TYP_CODE").trim() : "")));

			
			hippaCodeEB03List.add(hippaCodeValue);
			mapping.getEb03().setName(DomainConstants.EB03_NAME);
			mapping.getEb03().setDescription(
					(String) eb03Map.get(
							(null != rs.getString("SRVC_TYP_CODE") ? rs.getString("SRVC_TYP_CODE").trim() : "")));
			mapping.getEb03().setHippaCodeSelectedValues(hippaCodeEB03List);
			//for SSCR 19537
			
			List<HippaCodeValue> iii02List = new ArrayList<HippaCodeValue>();
			String commaSeparatedIII02Val = rs.getString("III02");
			String commaSeparatedIII02ValForDisplay = "";
			if(null != commaSeparatedIII02Val && !commaSeparatedIII02Val.equalsIgnoreCase("")){
				String[] iii02Array = commaSeparatedIII02Val.split(",");
				for(int i = 0; i < iii02Array.length; i++){
					HippaCodeValue iii02 = new HippaCodeValue();
					String iii02Val = iii02Array[i].trim();
					iii02.setValue(iii02Val);
					String iii02Desc = (String) iii02Map.get(
							(null != iii02Val ? iii02Val.trim() : ""));
					iii02.setDescription(iii02Desc);
					
					iii02List.add(iii02);
					if(commaSeparatedIII02ValForDisplay.equalsIgnoreCase("")){
						commaSeparatedIII02ValForDisplay = iii02Val+"("+iii02Desc+")";
					}else{
						commaSeparatedIII02ValForDisplay = commaSeparatedIII02ValForDisplay+","+iii02Val+"("+iii02Desc+")";
					}
					
				}
			}
			
			
			EB03Association eb03Association = new EB03Association();
			eb03Association.setEb03String(rs.getString("SRVC_TYP_CODE"));
			eb03Association.setEb03(hippaCodeValue);
			eb03Association.setIii02List(iii02List);
			eb03Association.setCommaSeparatedIII02String(commaSeparatedIII02Val);
			eb03Association.setCommaSeparatedIII02StringWithDesc(commaSeparatedIII02ValForDisplay);
			mapping.getEb03().getEb03Association().add(eb03Association);
			
			mapping.setIndvdlEb03AssnIndicator(rs.getString("INDVDL_EB03_ASSN_IND"));
			mapping.setIi02(new HippaSegment());
			if(null != mapping.getIndvdlEb03AssnIndicator() && mapping.getIndvdlEb03AssnIndicator().equalsIgnoreCase("N")){
				List<HippaCodeValue> listForVariablelevelii02 = new ArrayList<HippaCodeValue>();
				hippaCodeValue = new HippaCodeValue();
				hippaCodeValue.setValue(commaSeparatedIII02ValForDisplay);
				listForVariablelevelii02.add(hippaCodeValue);
				mapping.getIi02().setHippaCodeSelectedValues(listForVariablelevelii02);
			}else{
				mapping.getIi02().setHippaCodeSelectedValues(iii02List);
			}
			
			mapping.setUtilizationMgmntRule(new HippaSegment());
			HippaCodeValue uMRuleValue = new HippaCodeValue();
			uMRuleValue.setDescription(rs.getString("UM_RULE_DESC"));
			uMRuleValue.setValue(rs.getString("UM_RULE_ID"));
			List umRuleSelectedValuesList = new ArrayList();
			umRuleSelectedValuesList.add(uMRuleValue);
			mapping.getUtilizationMgmntRule().setHippaCodeSelectedValues(umRuleSelectedValuesList);
			mapping.setDataFeedInd(rs.getString("DATA_FEED_IND"));
			
			String lockUserId = rs.getString("bolk_bus_obj_lock_usr_id");
			if(null != lockUserId){
				
				Lock lock = new Lock();
				lock.setLockUserId(lockUserId);
				mapping.setLock(lock);
			}
			mapping.setProcedureExcludedInd(rs
					.getString("PROC_EXCLD_IND"));
			return mapping;
		}

	}
	private final class LocateHeaderRuleInProgressQuery extends MappingSqlQuery {
		boolean isPrintFlag = false;
		private LocateHeaderRuleInProgressQuery(DataSource dataSource, String query, List status) {
			super(dataSource, query);
			compile();
			if (null != status && status.contains("PRINT")) {
				isPrintFlag = true;
			}
		}

		/**
		 * sets the values to mapping object
		 * 
		 * @param ResultSet
		 * @param arg1
		 * @throws SQLException
		 */
		protected Object mapRow(ResultSet rs, int arg1) throws SQLException {
			Mapping mapping = new Mapping();
			Rule rule = new Rule();
			rule.setHeaderRuleId(rs.getString("RULE_ID"));	
			if(null != rs.getString("RULE_SHRT_DESC") && !isPrintFlag){
				rule.setRuleDesc(BxUtil.escapeSpecialCharacters(rs.getString("RULE_SHRT_DESC")));
			}	
			else{
				rule.setRuleDesc(rs.getString("RULE_SHRT_DESC"));
			}	
			mapping.setRule(rule);
			if (isPrintFlag) {
				mapping.setSortField("RULE");
			}
			User user = new User();
			user.setLastUpdatedUserName(rs.getString("LST_CHG_USR"));

			mapping.setUser(user);

			mapping.setLastChangedTmStamp(rs.getTimestamp("LST_CHG_TMS"));
			mapping.setVariableMappingStatus(rs.getString("STATUS_CD"));
		
			
			String lockUserId = rs.getString("bolk_bus_obj_lock_usr_id");
			if(null != lockUserId){
				
				Lock lock = new Lock();
				lock.setLockUserId(lockUserId);
				mapping.setLock(lock);
			}
			
			return mapping;
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
	
	private void iii02ListDescription(){
		/* Reference Data Values -  START*/
		String hippaSegmentName = DomainConstants.III02_NAME;
		int noOfRecords = 0;
		List eb03List = hippaSegmentRepository
		.findMatchingHippaCodeValuesFromDataDictionary(
				hippaSegmentName, null, noOfRecords);
		for (int count = 0; count < eb03List.size(); count++) {

			HippaCodeValue codeValue = (HippaCodeValue) eb03List.get(count);
			iii02Map.put(codeValue.getValue(), codeValue.getDescription());
			
		}	
	}
	
	public HippaSegmentRepositoryImpl getHippaSegmentRepository() {
		return hippaSegmentRepository;
	}

	public void setHippaSegmentRepository(
			HippaSegmentRepositoryImpl hippaSegmentRepository) {
		this.hippaSegmentRepository = hippaSegmentRepository;
	}
	/* Reference Data Values -  END*/

	/**
	 * The method returns a single mapping object by converting all the the EB03
	 * values into a single List
	 * 
	 * @param resultSet
	 * @return List
	 */
	private List locateMapping(List resultSet) {

		Map resultMap = new LinkedHashMap(); 
		Iterator resultSetIterator = resultSet.iterator();
		Mapping mapping;
		Mapping tempMapping;
		String ruleId;
		HippaCodeValue eb03Value;
		HippaCodeValue umRuleValue;
		Map umRules = new HashMap();//Map<RuleId,Map<umRuleId,HippaCodeValue object of Um rule>>
		Map eb03CodesMap = new HashMap();//Map<RuleId,Map<eb03 value ,HippaCodeValue object of Eb03>>
		Map eb03AssociationMap = new HashMap();//Map<RuleId,Map<eb03 value ,EB03Association object>>
		List umRuleColl;
		List eb03Coll;
		List eb03AssociationColl;
		EB03Association eb03Association;
		String iii02ForDisplay = "";
		
		while (resultSetIterator.hasNext()) {
			eb03Value = null;
			umRuleValue = null;
			eb03Association = null;
			mapping = (Mapping) resultSetIterator.next();

			if (mapping != null && mapping.getEb03() != null
					&& mapping.getEb03().getHippaCodeSelectedValues() != null) {
				eb03Value = (HippaCodeValue) mapping.getEb03()
						.getHippaCodeSelectedValues().get(0);
			}
			
			if (mapping != null && mapping.getEb03() != null
					&& mapping.getEb03().getEb03Association() != null 
					&& mapping.getEb03().getEb03Association().size() > 0) {
				
				eb03Association = (EB03Association) mapping.getEb03()
						.getEb03Association().get(0);
				if(null != mapping.getIndvdlEb03AssnIndicator() && mapping.getIndvdlEb03AssnIndicator().equalsIgnoreCase("N")){
					iii02ForDisplay = eb03Association.getCommaSeparatedIII02StringWithDesc();
				}
			}
			
			if (null != mapping) {
				umRuleValue = (HippaCodeValue)mapping.getUtilizationMgmntRule().getHippaCodeSelectedValues().get(0);
			}
			if (mapping != null && mapping.getRule() != null
					&& mapping.getRule().getHeaderRuleId() != null) {

				ruleId = mapping.getRule().getHeaderRuleId();
				Map map = null;
				
				//setting the Eb03s to the mapping object
				if(eb03CodesMap.containsKey(ruleId)){
					map = (Map)eb03CodesMap.get(ruleId);
				}else{
					map = new HashMap();
				}
				map.put(eb03Value.getValue(), eb03Value);
				eb03CodesMap.put(ruleId, map);
				map = (Map)eb03CodesMap.get(ruleId);
				eb03Coll = new ArrayList(map.values());
				
				//setting the Um rules to the mapping object
				if(umRules.containsKey(ruleId)){
					map = (Map)umRules.get(ruleId);
				}else{
					map = new HashMap();
				}
				map.put(umRuleValue.getValue(), umRuleValue);
				umRules.put(ruleId, map);
				map = (Map)umRules.get(ruleId);
				umRuleColl = new ArrayList(map.values());
				
				//setting the Eb03Associations to the mapping object
				if(eb03AssociationMap.containsKey(ruleId)){
					map = (Map)eb03AssociationMap.get(ruleId);
				}else{
					map = new HashMap();
				}
				map.put(eb03Value.getValue(), eb03Association);
				eb03AssociationMap.put(ruleId, map);
				map = (Map)eb03AssociationMap.get(ruleId);
				eb03AssociationColl = new ArrayList(map.values());
				

				// If the mapping with same rule id exists
				// then we need to add the eb03 value and eb03association object in the list.
				if (resultMap.containsKey(ruleId)) {
					tempMapping = (Mapping) resultMap.get(ruleId);
					if (null != tempMapping) {
						if (tempMapping.getEb03() == null
								|| tempMapping.getEb03()
										.getHippaCodeSelectedValues() == null) {
							tempMapping.setEb03(mapping.getEb03());
						} else {
							tempMapping.getEb03().setHippaCodeSelectedValues(eb03Coll);
						}
						tempMapping.getEb03().setEb03Association(eb03AssociationColl);
						tempMapping.getUtilizationMgmntRule().setHippaCodeSelectedValues(umRuleColl);
					
					}
					//Sort UM rules
					if(null != tempMapping && null != tempMapping.getUtilizationMgmntRule() && null != tempMapping.getUtilizationMgmntRule().getHippaCodeSelectedValues()){
						Collections.sort(tempMapping.getUtilizationMgmntRule().getHippaCodeSelectedValues());
					}
					//Sort EB03 Association
					if(null != tempMapping && null != tempMapping.getEb03() && null != tempMapping.getEb03().getEb03Association()){
						Collections.sort(tempMapping.getEb03().getEb03Association());
					}
					resultMap.put(ruleId, tempMapping);
				} else {
					mapping.getEb03().setHippaCodeSelectedValues(eb03Coll);
					mapping.getUtilizationMgmntRule().setHippaCodeSelectedValues(umRuleColl);
					mapping.getEb03().setEb03Association(eb03AssociationColl);
					//Sort UM rules
					if(null != mapping.getUtilizationMgmntRule() && null != mapping.getUtilizationMgmntRule().getHippaCodeSelectedValues()){
						Collections.sort(mapping.getUtilizationMgmntRule().getHippaCodeSelectedValues());
					}
					//Sort EB03 Association
					if(null != mapping && null != mapping.getEb03() && null != mapping.getEb03().getEb03Association()){
						Collections.sort(mapping.getEb03().getEb03Association());
					}
					resultMap.put(ruleId, mapping);
				}

			}
		}
		//Re-setting individual mapping indicator
		List resultList = new ArrayList(resultMap.values());
		Iterator resultListIterator = resultList.iterator();
		Set<String> individualMapingSet = new HashSet<String>();
		while(resultListIterator.hasNext()){
			Mapping resultMapping = (Mapping)resultListIterator.next();
			List<EB03Association> resultEB03AssnList = resultMapping.getEb03().getEb03Association();
			for(EB03Association resultEB03Assn: resultEB03AssnList){
				//undo commenting after code correction for multiple iii02s
				/*String iii02Val = (null != resultEB03Assn.getIii02() &&
						null != resultEB03Assn.getIii02().getValue() ? resultEB03Assn.getIii02().getValue() : "");*/
				//individualMapingSet.add("III02"+iii02Val.trim());
			}
			if(individualMapingSet.size() > 1){
				resultMapping.setIndvdlEb03AssnIndicator("Y");
			}
			individualMapingSet = new HashSet<String>();
		}
		//Ends here
		return resultList;
	}

	/**
	 * Inner class for Retrieving audit trail
	 */
	public static class RetrieveAuditTrailQuery extends MappingSqlQuery {

		private RetrieveAuditTrailQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		/**
		 * sets the values to AuditTrail object
		 * 
		 * @param ResultSet
		 * @param arg1
		 * @throws SQLException
		 */
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			AuditTrail auditTrail = new AuditTrail();
			String auditTime = BxUtil.getFormattedDate(rs
					.getTimestamp("CREATD_TM_STMP"));
			auditTrail.setCreatedAuditDate(auditTime);
			auditTrail.setCreatedUser(rs.getString("CREATD_USER_ID"));
			auditTrail.setSystemComments(rs.getString("SYS_CMNTS"));
			auditTrail.setUserComments(rs.getString("USR_CMNTS"));
			auditTrail.setVariableId(rs.getString("RULE_ID"));
			auditTrail.setMappingStatus(rs.getString("MAPG_STATUS"));
			return auditTrail;
		}
	}

	/**
	 * Retrieves the latest audit information of Rule ID
	 * 
	 * @param ruleId
	 * @param recordCountFlag
	 * @return List
	 */
	private List getAuditInfo(String headerRuleId, int auditTrailCount) {
		
		StringBuffer query = new StringBuffer();
		query = query.append("SELECT * FROM ");
		query = query
				.append("(SELECT CREATD_TM_STMP, CREATD_USER_ID, SYS_CMNTS, USR_CMNTS, RULE_ID, MAPG_STATUS FROM ");
		query = query.append("BX_SRVC_TYPE_AUDIT_TRAIL WHERE RULE_ID = '"
				+ headerRuleId + "'");
		query = query.append("ORDER BY CREATD_TM_STMP DESC)");
		if (auditTrailCount != -1) {
			query = query.append("WHERE ROWNUM < "
					+ DomainConstants.noOfAuditInfo + " ");
		}

		RetrieveAuditTrailQuery retrieveAuditTrailQuery = new RetrieveAuditTrailQuery(
				dataSource, query.toString());
		List auditTrailList = retrieveAuditTrailQuery.execute();
		if (null != auditTrailList && auditTrailList.size() > 0) {
			BxUtil.setSystemComments(auditTrailList);
			return auditTrailList;
		}

		return null;
	}
	
	/**
	 * for generating reports from locate
	 * @param mapping
	 * @return List
	 */
	public List getRecordsForReport(Mapping mapping, List status) {
		String unionCondition = " UNION ALL "; 
		String locateCondition = "select BX.* from(";
		String appendQueryString = "";


		if (mapping != null && (mapping.getRule().getHeaderRuleId()) != null) {
			String ruleId = mapping.getRule().getHeaderRuleId().replace('\'', ' ');
			
			String locateUnmappedRecords = 
			"SELECT  R.RULE_ID, R.RULE_SHRT_DESC, " +
			"R.RULE_APRVD_DT AS CREATD_DATE , " +
			"'UNMAPPED' as STATUS_CD " +
			"FROM  RULE R " +
			"WHERE R.RULE_TYP_CD = 'WPDAUTOBG' " +
			"AND  R.WPD_DEL_FLAG = 'N' " +
			"AND R.RULE_ID NOT IN (SELECT RULE_ID FROM BX_RULE_SRVC_TYP_ASSN) " +
			"AND R.RULE_ID like '"+ruleId+"' ";
			
			String locateMappedRecords = "SELECT b.RULE_ID, " +
			"r.RULE_SHRT_DESC, " +
			"MIN(b.CREATD_TM_STMP) AS CREATD_DATE, " +
			"b.STATUS_CD      AS STATUS_CD " +
			"FROM BX_RULE_SRVC_TYP_ASSN b " +
			"INNER JOIN Rule r " +
			"ON b.RULE_ID        =r.RULE_ID " +
			"WHERE b.in_temp_tab ='N' " +
			" and b.RULE_ID LIKE '"+ruleId+"' "+
			" GROUP BY b.RULE_ID, r.RULE_SHRT_DESC, b.STATUS_CD "+
			"UNION " +
			"SELECT t.RULE_ID, " +
			"r.RULE_SHRT_DESC, " +
			"MIN(t.CREATD_TM_STMP) AS CREATD_DATE, " +
			"t.STATUS_CD      AS STATUS_CD " +
			"FROM BX_RULE_SRVC_TYP_ASSN b, " +
			"Rule r, " +
			"TEMP_BX_RULE_SRVC_TYP_ASSN t " +
			"WHERE " +
			"t.RULE_ID LIKE '"+ruleId+"' "+
			"AND b.rule_id  =t.rule_id " +
			"AND t.rule_id    =r.rule_id " +
			"AND b.in_temp_tab='Y' "+
			"GROUP BY t.RULE_ID, r.RULE_SHRT_DESC, t.STATUS_CD";
			
			// locate Section

			if (null != status && status.size() >= 1) {
				// To locate Not Applicable
				if ((status.contains(DomainConstants.STATUS_NOT_APPLICABLE))
						&& ((!status.contains(DomainConstants.MAPPED_STATUS)))
						&& ((!status.contains(DomainConstants.UNMAPPED_STATUS)))) {

					appendQueryString = locateCondition + locateMappedRecords
							
							+ ")BX where STATUS_CD='NOT_APPLICABLE'";

				}

				// To locate Mapped
				else if ((status.contains(DomainConstants.MAPPED_STATUS))
						&& ((!status
								.contains(DomainConstants.STATUS_NOT_APPLICABLE)))
						&& ((!status.contains(DomainConstants.UNMAPPED_STATUS)))) {
					/**
					 * the various status is possible for a particular rule id
					 * is also checked in the query
					 */

					appendQueryString = locateCondition
							+ locateMappedRecords
							+ ")BX where STATUS_CD='BEING_MODIFIED' OR STATUS_CD='PUBLISHED' OR STATUS_CD='BUILDING' OR STATUS_CD='SCHEDULED_TO_TEST' OR STATUS_CD='OBJECT_TRANSFERRED' OR STATUS_CD='SCHEDULED_TO_PRODUCTION'";

				}
				// To locate Unmapped
				else if (((status.contains(DomainConstants.UNMAPPED_STATUS))
								&& ((!status
										.contains(DomainConstants.STATUS_NOT_APPLICABLE))) && ((!status
								.contains(DomainConstants.MAPPED_STATUS))))) {				
					
					appendQueryString =  locateUnmappedRecords;		
					
				}
				// Locate mapped and not applicable

				else if ((status.contains(DomainConstants.MAPPED_STATUS))
						&& ((status
								.contains(DomainConstants.STATUS_NOT_APPLICABLE)))
						&& ((!status.contains(DomainConstants.UNMAPPED_STATUS)))) {
					// The various status that is possible for a particular
					// Ruleid is checked
					appendQueryString = locateCondition
							+ locateMappedRecords
							+ " )BX where STATUS_CD='BEING_MODIFIED' OR STATUS_CD='PUBLISHED' OR STATUS_CD='BUILDING' OR STATUS_CD='SCHEDULED_TO_TEST' OR STATUS_CD='OBJECT_TRANSFERRED'OR STATUS_CD='NOT_APPLICABLE' OR STATUS_CD='SCHEDULED_TO_PRODUCTION'";

				}
				// Locate unmapped and not applicable
				else if ((status.contains(DomainConstants.UNMAPPED_STATUS))
						&& ((status
								.contains(DomainConstants.STATUS_NOT_APPLICABLE)))
						&& ((!status.contains(DomainConstants.MAPPED_STATUS)))) {

					// query with where condition being added as not applicable
					appendQueryString = locateCondition + locateMappedRecords

							+ ")BX where STATUS_CD='NOT_APPLICABLE'"
							+ unionCondition + locateUnmappedRecords;

				}
				// Locate mapped and unmapped
				else if ((status.contains(DomainConstants.UNMAPPED_STATUS))
						&& ((status.contains(DomainConstants.MAPPED_STATUS)))
						&& ((!status
								.contains(DomainConstants.STATUS_NOT_APPLICABLE)))) {
					// The various status that is possible for a particular
					// Ruleid is checked
					appendQueryString = locateCondition
							+ locateMappedRecords
							+ ")BX where STATUS_CD='BEING_MODIFIED' OR STATUS_CD='PUBLISHED' OR STATUS_CD='BUILDING' OR STATUS_CD='SCHEDULED_TO_TEST' OR STATUS_CD='OBJECT_TRANSFERRED' OR STATUS_CD='SCHEDULED_TO_PRODUCTION'"
							+ unionCondition + locateUnmappedRecords;
			
				}
				// Locate mapped,unmapped and not applicable
				else if ((status.contains(DomainConstants.UNMAPPED_STATUS))
						&& ((status.contains(DomainConstants.MAPPED_STATUS)))
						&& ((status
								.contains(DomainConstants.STATUS_NOT_APPLICABLE)))) {
					// The various status that is possible for a particular
					// Ruleid is checked
					appendQueryString = locateCondition
							+ locateMappedRecords
							+ ")BX where STATUS_CD='BEING_MODIFIED' OR STATUS_CD='PUBLISHED' OR STATUS_CD='BUILDING' OR STATUS_CD='SCHEDULED_TO_TEST' OR STATUS_CD='OBJECT_TRANSFERRED'OR STATUS_CD='NOT_APPLICABLE' OR STATUS_CD='SCHEDULED_TO_PRODUCTION'"
							+ unionCondition + locateUnmappedRecords;
			
				}
			}
			// when nothing is checked(mapped,unmapped and not applicable)
			else {
				appendQueryString = locateCondition + locateMappedRecords+ ")BX ";

			}
		}
				
		long locateMappingStartTime = System.currentTimeMillis();
		
		List result = null;
			LocateHeaderRuleReportQuery locateHeaderRuleQuery = new LocateHeaderRuleReportQuery(
					dataSource, appendQueryString);

		result = locateHeaderRuleQuery.execute();
		
		
		long locateMappingEndTime = System.currentTimeMillis();
		
		logger.info("Time taken for executing Rule Report Query = "+ (locateMappingEndTime - locateMappingStartTime));

		return result;
	}
	
	/**
	 * Inner class for Retrieve Mapping for Report
	 */

	private final class LocateHeaderRuleReportQuery extends MappingSqlQuery {
		private LocateHeaderRuleReportQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();			
		}

		/**
		 * sets the values to mapping object
		 * 
		 * @param ResultSet
		 * @param arg1
		 * @throws SQLException
		 */
		protected Object mapRow(ResultSet rs, int arg1) throws SQLException {
			SearchResult searchResult = new SearchResult();
			searchResult.setHeaderRuleId(rs.getString("RULE_ID"));

			searchResult.setHeaderRuleDescription(BxUtil
					.escapeSpecialCharacters(rs.getString("RULE_SHRT_DESC")));

			searchResult.setFormattedDate(BxUtil.getFormattedDateWithOutTime(rs
					.getDate("CREATD_DATE")));

			searchResult.setStatus(rs.getString("STATUS_CD"));
			return searchResult;
		}

	}

	/**
	 * 
	 * @return DataSource
	 */
	public DataSource getDataSource() {
		return this.dataSource;
	}

	/**
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * 
	 * @return the result of the query
	 */
	public String getLocateHeaderRuleQuery() {
		return locateHeaderRuleQuery;
	}

	/**
	 * 
	 * @param locateHeaderRuleQuery
	 */
	public void setLocateHeaderRuleQuery(String locateHeaderRuleQuery) {
		this.locateHeaderRuleQuery = locateHeaderRuleQuery;
	}

	public ItemRepository getItemRepository() {
		return itemRepository;
	}

	public void setItemRepository(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}
}
