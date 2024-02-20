/*
 * <LocateSpsIdRepositoryImpl.java>
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
import java.util.List;

import javax.sql.DataSource;
import org.owasp.esapi.ESAPI;
import org.apache.log4j.Logger;
import org.springframework.jdbc.object.MappingSqlQuery;

import com.wellpoint.ets.bx.mapping.application.security.Lock;
import com.wellpoint.ets.bx.mapping.domain.entity.AuditTrail;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Page;
import com.wellpoint.ets.bx.mapping.domain.entity.SPSDetail;
import com.wellpoint.ets.bx.mapping.domain.entity.SPSId;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.vo.SearchResult;
import com.wellpoint.ets.bx.mapping.domain.vo.User;
import com.wellpoint.ets.bx.mapping.util.BxUtil;

/**
 * @author UST-GLOBAL
 * This is an implementation class for locating or searching a Sps id 
 * or multiple Sps id based on certain search criteria
 */
public class LocateSpsIdRepositoryImpl implements LocateRepository {

	private DataSource dataSource;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	private static final String likeConditionInMain = " and A.SPS_PARAM_ID Like ";
	private static final String likeConditionInTemp = " and D.SPS_PARAM_ID Like ";
	private static final String likePrmryCd = " and c.prmry_cd LIKE ";
	private static final String statuscdNotEqualTo = " and a.status_cd <> ";
	private static final String isFinalizedString = "isFinalized";
	private static Logger logger = Logger.getLogger(LocateSpsIdRepositoryImpl.class);

	/**
	 * For pagination in locate result page
	 * @param mapping
	 * @return int
	 */
	public int getRecordCount(Mapping mapping, List status) {

		
		String mappedRecordsFromMain = " select A.SPS_PARAM_ID  spsId "
				+ "from BX_SPS_MAPNG A, "
				+ " CATALOG B,ITEM C "
				+ "where B.CTLG_ID IN  ("
				+ "select CTLG_ID from CATALOG "
				+ "where upper(CTLG_NAME) = upper('reference')) and B.CTLG_ID = C.CTLG_ID "
				+ "and A.SPS_PARAM_ID =C.PRMRY_CD and a.in_temp_tab = 'N' ";
		String unionCondition = "UNION ALL";
		String mappedRecordsFromTemp = " select D.SPS_PARAM_ID spsId " 
				+ "from TEMP_BX_SPS_MAPG D," 
				+ " CATALOG E,ITEM F  "
				+ "where E.CTLG_ID IN  ( "
				+ "select CTLG_ID from CATALOG where upper(CTLG_NAME)"
				+ " =upper('reference') "
				+ ") and E.CTLG_ID = F.CTLG_ID "
				+ "and D.SPS_PARAM_ID =F.PRMRY_CD ";
		String unmappedRecords = " select  c.prmry_cd  spsId "
				+ "from CATALOG B,ITEM C "
				+ " where  c.prmry_cd NOT IN (select bx.SPS_PARAM_ID from BX_SPS_MAPNG bx "
				+ ") and B.CTLG_ID IN (select CTLG_ID from CATALOG where upper(CTLG_NAME) =upper('reference') "
				+ ") and B.CTLG_ID = C.CTLG_ID ";

		
		String conditionForNotApplicable = null;

		String locateQuery = null;

		String likeSpsid = null;


		String orderBySpsid = " order by spsId";
		String spsId = null;
		
		String selectLock = "select spsBx.*, lk.bolk_bus_obj_lock_usr_id  from ( ";
		
		String lockCondition = ")spsBx left outer join bolk_bus_obj_lock lk on lk.bolk_bus_obj_key_id = spsBx.spsId"
						+ " and lk.bolk_bus_obj_type_nm = 'com.wellpoint.ets.bx.mapping.domain.entity.Mapping'";
		if(null != mapping){
			if (null != mapping.getSpsId() && null !=  mapping.getSpsId().getSpsId()
					&& !mapping.getSpsId().getSpsId().trim().equals("")) {
				spsId = mapping.getSpsId().getSpsId();
				spsId = spsId.replace('\'', ' ');
			}
		}
		if ((null != status) && (!status.isEmpty())){
				// to locate  mapped 
				if ((status.contains(DomainConstants.MAPPED_STATUS))
						&& ((!status
								.contains(DomainConstants.STATUS_NOT_APPLICABLE)))
						&& ((!status.contains(DomainConstants.UNMAPPED_STATUS)))) {
	
				
					// locate mapped from locate section
					if (null != mapping.getSpsId().getSpsId()) {
	
						conditionForNotApplicable = statuscdNotEqualTo + " '"
								+ DomainConstants.STATUS_NOT_APPLICABLE + "'";
						
						locateQuery = mappedRecordsFromMain + conditionForNotApplicable + likeConditionInMain + " '" +
								spsId + "' " + unionCondition
								+ mappedRecordsFromTemp + likeConditionInTemp + " '" +
								spsId + "' " + orderBySpsid;

					}
				}
				// else locate unmapped
				if ((!status.contains(DomainConstants.MAPPED_STATUS))
						&& ((!status
								.contains(DomainConstants.STATUS_NOT_APPLICABLE)))
						&& ((status.contains(DomainConstants.UNMAPPED_STATUS)))) {
	
					if (null != mapping.getSpsId().getSpsId()) {
						
						likeSpsid = likePrmryCd + " '" +
							spsId + "' ";
						
						locateQuery = unmappedRecords + likeSpsid + orderBySpsid;
						

						
					}
	
				}
				// to locate mapped and not applicable
				else if ((status.contains(DomainConstants.MAPPED_STATUS))
						&& ((status.contains(DomainConstants.STATUS_NOT_APPLICABLE)))
						&& ((!status.contains(DomainConstants.UNMAPPED_STATUS)))) {
	
					locateQuery = mappedRecordsFromMain + likeConditionInMain +" '" +
							spsId + "' "  + unionCondition
							+ mappedRecordsFromTemp + likeConditionInTemp + " '" +
							spsId + "' " + orderBySpsid;

					
				}
				// to locate not applicable mapping
				else if ((!status.contains(DomainConstants.MAPPED_STATUS))
						&& ((status.contains(DomainConstants.STATUS_NOT_APPLICABLE)))
						&& ((!status.contains(DomainConstants.UNMAPPED_STATUS)))) {
	
					conditionForNotApplicable = "and a.status_cd = '"
							+ DomainConstants.STATUS_NOT_APPLICABLE + "'";
	
					locateQuery = mappedRecordsFromMain + conditionForNotApplicable
							+ likeConditionInMain+ " '" +
							spsId + "' "  + orderBySpsid;
					

					
				}
				// to locate mapped and unmapped 
				else if ((status.contains(DomainConstants.MAPPED_STATUS))
						&& ((!status
								.contains(DomainConstants.STATUS_NOT_APPLICABLE)))
						&& ((status.contains(DomainConstants.UNMAPPED_STATUS)))) {
	
					conditionForNotApplicable = statuscdNotEqualTo + " '"
							+ DomainConstants.STATUS_NOT_APPLICABLE + "'";
				locateQuery = mappedRecordsFromMain + conditionForNotApplicable + likeConditionInMain + " '" +
						    spsId + "' " + unionCondition
							+ mappedRecordsFromTemp + likeConditionInTemp + " '" +
							spsId + "' " 
							+ unionCondition + unmappedRecords + likePrmryCd + " '"
							+ spsId + "' "
							+ orderBySpsid;

					
				}
				// to locate unmapped and not applicable
				else if ((!status.contains(DomainConstants.MAPPED_STATUS))
						&& ((status.contains(DomainConstants.STATUS_NOT_APPLICABLE)))
						&& ((status.contains(DomainConstants.UNMAPPED_STATUS)))) {
	
					conditionForNotApplicable = " and a.status_cd = '"
							+ DomainConstants.STATUS_NOT_APPLICABLE + "'";
					locateQuery = mappedRecordsFromMain + conditionForNotApplicable + likeConditionInMain + " '" +
								spsId + "' " 
								+ unionCondition + unmappedRecords + likePrmryCd + " '"
								+ spsId + "' "
								+ orderBySpsid;				

					
				}
				// locate mapped , unmapped and not applicable
				else if ((status.contains(DomainConstants.MAPPED_STATUS))
							&& ((status.contains(DomainConstants.STATUS_NOT_APPLICABLE)))
							&& ((status.contains(DomainConstants.UNMAPPED_STATUS)))) {					
			
					locateQuery = mappedRecordsFromMain + likeConditionInMain + " '" +
								spsId + "' " + unionCondition
								+ mappedRecordsFromTemp + likeConditionInTemp + " '" +
								spsId + "' " 
								+ unionCondition + unmappedRecords + likePrmryCd + " '"
								+ spsId + "' "
								+ orderBySpsid;
					
					
				}
				locateQuery = "select count(*) from ("+selectLock+locateQuery+lockCondition+")";

				FindTotalNoOfRecordsQuery totalNoOfRcrdsQry = new FindTotalNoOfRecordsQuery(
						dataSource, locateQuery);
				List totalNoOfRecordsList = totalNoOfRcrdsQry.execute();
				Integer totalNoOfRecords = (Integer) totalNoOfRecordsList.get(0);
				return totalNoOfRecords.intValue();
		}
	
		return 0;
	}
	/**
	 * Inner class for getting the record count for pagination of the results
	 *
	 */
	private static class FindTotalNoOfRecordsQuery extends MappingSqlQuery {

		private FindTotalNoOfRecordsQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}
		/**
		 * sets the values to mapping object
		 * @param ResultSet
		 * @param arg1
		 * @throws SQLException
		 */
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			return Integer.valueOf(rs.getInt(1));
		}

	}
	/**
	 * For getting exact match/retrieving records based on the status and logged in user
	 * @param Mapping
	 * @param status
	 * @param loggedInUser
	 * @return List
	 */

	public List getRecords(Mapping mapping, List status, String loggedInUser,
			int noOfRecords, int auditTrailCount, Page page) {
		List mappings = new ArrayList();
		
		String mappedRecordsFromMain = " select A.SPS_PARAM_ID  spsId,C.CD_DESC_TXT  spsDesc, a.lst_chg_tms updateDate,"
				+ " a.status_cd status, a.lst_chg_usr lastUpdatedUser, a.MAPPNG_CMP_IND isFinalized "
				+ "from BX_SPS_MAPNG A, "
				+ " CATALOG B,ITEM C "
				+ "where B.CTLG_ID IN  ("
				+ "select CTLG_ID "
				+" from CATALOG where upper(CTLG_NAME) =upper('reference') "
				+ ") and B.CTLG_ID = C.CTLG_ID "
				+ "and A.SPS_PARAM_ID =C.PRMRY_CD and a.in_temp_tab = 'N' ";
		String unionCondition = "UNION ALL";
		String mappedRecordsFromTemp = " select D.SPS_PARAM_ID spsId,F.CD_DESC_TXT spsDesc, D.lst_chg_tms updateDate, "
				+ "D.status_cd status , D.lst_chg_usr lastUpdatedUser, D.MAPPNG_CMP_IND isFinalized " 
				+ "from TEMP_BX_SPS_MAPG D," 
				+ " CATALOG E,ITEM F  "
				+ "where E.CTLG_ID IN  ( "
				+ "select CTLG_ID from CATALOG "
				+ " where upper(CTLG_NAME) =upper('reference') "
				+ ") and E.CTLG_ID = F.CTLG_ID "
				+ "and D.SPS_PARAM_ID =F.PRMRY_CD ";
		String unmappedRecords = " select  c.prmry_cd  spsId,C.CD_DESC_TXT  spsDesc,c.creatd_tm_stmp updateDate, "
				+"'UNMAPPED' status,c.CREATD_USER_ID lastUpdatedUser , 'Y' isFinalized "
				+ "from CATALOG B,ITEM C "
				+ "where  c.prmry_cd NOT IN (select bx.SPS_PARAM_ID from BX_SPS_MAPNG bx  "
				+ ") and B.CTLG_ID IN  ( "
				+ "select CTLG_ID from CATALOG where "
				+ " upper(CTLG_NAME) =upper('reference') "
				+ ") and B.CTLG_ID = C.CTLG_ID ";
		String findMapping = null;
		
		String selectQueryForSPSMAPG = "select distinct sps_id, mapg_type_cd , mapg_pva , " +
				"(select d.dtyp_data_type_lgnd from DTYP_DATA_TYPE d where " +
				"d.DTYP_DATA_TYPE_ID = s.mapg_dtyp) mapg_legend from SPS_MAPG s ";
		
		String selectLock = "select spsBx.*, lk.bolk_bus_obj_lock_usr_id  from ( ";
		
		String lockCondition = ")spsBx left outer join bolk_bus_obj_lock lk on lk.bolk_bus_obj_key_id = spsBx.spsId"
						+ " and lk.bolk_bus_obj_type_nm = 'com.wellpoint.ets.bx.mapping.domain.entity.Mapping'";
		
		String conditionForNotApplicable = null;
		String conditionForInProgress = null;
		String locateQuery = null;
		String rowNum = "";
		String likeSpsid = null;
 
		String loggedInUsercnditionMain = "";
		String loggedInUsercnditionTemp = "";
		String inProgresscondition = "";
		String orderBySpsid = " order by spsId";
		String spsId = null;
		
		if(null != mapping){
			if (null != mapping.getSpsId() && null !=  mapping.getSpsId().getSpsId()
					&& !mapping.getSpsId().getSpsId().trim().equals("")) {
				spsId = mapping.getSpsId().getSpsId();
				spsId = spsId.replace('\'', ' ');
			}
		}
		if ((null != status) && (!status.isEmpty())){
				if (null != status && status.contains("IN-PROGRESS")) {
					String orderByUpdatedDate = " ORDER BY updateDate DESC ";
					if (null != loggedInUser) {
						loggedInUsercnditionMain = "and a.lst_chg_usr = '" + loggedInUser + "' ";
					}
					if (noOfRecords != -1) {
						rowNum = " Where rownum < " + noOfRecords;
					}
					if (null != loggedInUser) {
						loggedInUsercnditionTemp = "and D.lst_chg_usr = '" + loggedInUser + "' ";
					}
					conditionForInProgress = statuscdNotEqualTo + " '"
							+ DomainConstants.STATUS_NOT_APPLICABLE + "'"
							+ statuscdNotEqualTo + " '"
							+ DomainConstants.STATUS_PUBLISHED + "'";
					inProgresscondition = "Select * from (";					
					
					locateQuery = inProgresscondition + mappedRecordsFromMain
							+ conditionForInProgress + loggedInUsercnditionMain
							+ unionCondition + mappedRecordsFromTemp
							+ loggedInUsercnditionTemp 
							+ orderByUpdatedDate 
							+ " )"
							+ rowNum;
					
					
				}
				// to locate  mapped 
				else if ((status.contains(DomainConstants.MAPPED_STATUS))
						&& ((!status
								.contains(DomainConstants.STATUS_NOT_APPLICABLE)))
						&& ((!status.contains(DomainConstants.UNMAPPED_STATUS)))) {
	
					if ((null == mapping) && (noOfRecords != -1)
							&& (null != loggedInUser)) {
						
						loggedInUsercnditionMain = "and a.lst_chg_usr = '" + loggedInUser + "' ";
						rowNum = " Where rownum < " + noOfRecords;
						loggedInUsercnditionTemp = "and D.lst_chg_usr = '" + loggedInUser + "' ";
						conditionForInProgress = statuscdNotEqualTo + " '"
								+ DomainConstants.STATUS_NOT_APPLICABLE + "'"
								+ statuscdNotEqualTo + " '"
								+ DomainConstants.STATUS_PUBLISHED + "'";
						inProgresscondition = "Select * from (";
						
						locateQuery = inProgresscondition + mappedRecordsFromMain
								+ conditionForInProgress + loggedInUsercnditionMain
								+ unionCondition + mappedRecordsFromTemp
								+ loggedInUsercnditionTemp 
								+ orderBySpsid 
								+ " )"
								+ rowNum;
					}
					// locate mapped from locate section
					else if (null != mapping && null != mapping.getSpsId() && null != mapping.getSpsId().getSpsId() && (noOfRecords == -1)) {
	
						conditionForNotApplicable = statuscdNotEqualTo + " '"
								+ DomainConstants.STATUS_NOT_APPLICABLE + "'";
						
						locateQuery =  mappedRecordsFromMain + conditionForNotApplicable + likeConditionInMain + " '" +
								spsId + "' " + unionCondition
								+ mappedRecordsFromTemp + likeConditionInTemp + " '" +
								spsId + "' " + orderBySpsid;
					}
				}
				// else locate unmapped
				if ((!status.contains(DomainConstants.MAPPED_STATUS))
						&& ((!status
								.contains(DomainConstants.STATUS_NOT_APPLICABLE)))
						&& ((status.contains(DomainConstants.UNMAPPED_STATUS)))) {
	
					if ((null == mapping) && (noOfRecords != -1)) {
						
						inProgresscondition = "Select * from (";
						rowNum = " Where rownum < " + noOfRecords;
						locateQuery = inProgresscondition + unmappedRecords								
						+ orderBySpsid 
						+ " )" + rowNum;
						

						
					} else if (null != mapping && null != mapping.getSpsId() && null != mapping.getSpsId().getSpsId()
							&& (noOfRecords == -1)) {
						
						likeSpsid = likePrmryCd + " '" +
							spsId + "' ";
						
						locateQuery = unmappedRecords + likeSpsid + orderBySpsid;
						

						
					}//autocomplete of sps id
					else if(null != mapping && null != mapping.getSpsId() && null != mapping.getSpsId().getSpsId()
							&& (noOfRecords != -1)) {
						
						inProgresscondition = "Select * from (";
						rowNum = " Where rownum < " + noOfRecords;
						likeSpsid = likePrmryCd + " '" +
							spsId + "' ";						
						locateQuery = inProgresscondition + unmappedRecords + likeSpsid 
										+ orderBySpsid + " )" + rowNum;
						

						
					}
	
				}
				// to locate mapped and not applicable
				else if ((status.contains(DomainConstants.MAPPED_STATUS))
						&& ((status.contains(DomainConstants.STATUS_NOT_APPLICABLE)))
						&& ((!status.contains(DomainConstants.UNMAPPED_STATUS)))) {
	
					locateQuery =  mappedRecordsFromMain + likeConditionInMain + " '" +
							spsId + "' "  + unionCondition
							+ mappedRecordsFromTemp + likeConditionInTemp + " '" +
							spsId + "' " + orderBySpsid ;
					

					
				}
				// to locate not applicable mapping
				else if ((!status.contains(DomainConstants.MAPPED_STATUS))
						&& ((status.contains(DomainConstants.STATUS_NOT_APPLICABLE)))
						&& ((!status.contains(DomainConstants.UNMAPPED_STATUS)))) {
	
					conditionForNotApplicable = "and a.status_cd = '"
							+ DomainConstants.STATUS_NOT_APPLICABLE + "'";
	
					locateQuery = mappedRecordsFromMain + conditionForNotApplicable
							+ likeConditionInMain + " '" +
							spsId + "' "  + orderBySpsid;
				}
				// to locate mapped and unmapped 
				else if ((status.contains(DomainConstants.MAPPED_STATUS))
						&& ((!status
								.contains(DomainConstants.STATUS_NOT_APPLICABLE)))
						&& ((status.contains(DomainConstants.UNMAPPED_STATUS)))) {
	
					conditionForNotApplicable = statuscdNotEqualTo + " '"
							+ DomainConstants.STATUS_NOT_APPLICABLE + "'";
				locateQuery =  mappedRecordsFromMain + conditionForNotApplicable + likeConditionInMain + " '" +
						    spsId + "' " + unionCondition
							+ mappedRecordsFromTemp + likeConditionInTemp + " '" +
							spsId + "' " 
							+ unionCondition + unmappedRecords + likePrmryCd + " '"
							+ spsId + "' "
							+ orderBySpsid;
				}
				// to locate unmapped and not applicable
				else if ((!status.contains(DomainConstants.MAPPED_STATUS))
						&& ((status.contains(DomainConstants.STATUS_NOT_APPLICABLE)))
						&& ((status.contains(DomainConstants.UNMAPPED_STATUS)))) {
	
					conditionForNotApplicable = " and a.status_cd = '"
							+ DomainConstants.STATUS_NOT_APPLICABLE + "'";
					locateQuery = mappedRecordsFromMain + conditionForNotApplicable + likeConditionInMain + " '" +
								spsId + "' " 
								+ unionCondition + unmappedRecords + likePrmryCd + " '"
								+ spsId + "' "
								+ orderBySpsid;				

					
				}
				// locate mapped , unmapped and not applicable
				else if ((status.contains(DomainConstants.MAPPED_STATUS))
							&& ((status.contains(DomainConstants.STATUS_NOT_APPLICABLE)))
							&& ((status.contains(DomainConstants.UNMAPPED_STATUS)))) {					
			
					locateQuery = mappedRecordsFromMain + likeConditionInMain + " '" +
								spsId + "' " + unionCondition
								+ mappedRecordsFromTemp + likeConditionInTemp + " '" +
								spsId + "' " 
								+ unionCondition + unmappedRecords + likePrmryCd + " '"
								+ spsId + "' "
								+ orderBySpsid;
				}
				
				// Page will have value when calling locate from view landing page
				if (page != null) {
					locateQuery  = " select * from (select EBX.*, ROWNUM rnm from ("
						+ locateQuery 
						+") EBX)  WHERE rnm between "+ page.getStartRowNum() +" and "+ page.getEndRowNum();						
				}
				locateQuery = selectLock + locateQuery + lockCondition;
				if (null != status && status.contains("PRINT")) {
					locateQuery += " ORDER BY spsId";
				}
				log.info(ESAPI.encoder().encodeForHTML(locateQuery));
				long locateMappingStartTime = System.currentTimeMillis();
			LocateMapping locateMapping = new LocateMapping(dataSource,
					locateQuery, status);			
			mappings = locateMapping.execute();
			long locateMappingEndTime = System.currentTimeMillis();
			logger.info("Time taken for executing SPS Query = "+ (locateMappingEndTime - locateMappingStartTime));

		}
		// get Mapping and audit trail
		else {			
				if(null != spsId){
					// this query requires further review
					findMapping = "SELECT  BX_SPS_MAPNG.SPS_PARAM_ID, "+
							"BX_SPS_MAPNG.EB01_VALUE,BX_SPS_MAPNG.EB02_VALUE,BX_SPS_MAPNG.EB06_VALUE, BX_SPS_MAPNG.EB09_VALUE,"+ 
							"BX_SPS_MAPNG.HSD1_VALUE,BX_SPS_MAPNG.HSD2_VALUE,BX_SPS_MAPNG.HSD3_VALUE,BX_SPS_MAPNG.HSD4_VALUE,"+
							"BX_SPS_MAPNG.HSD5_VALUE,BX_SPS_MAPNG.HSD6_VALUE,BX_SPS_MAPNG.HSD7_VALUE,BX_SPS_MAPNG.HSD8_VALUE,"+
							"BX_SPS_MAPNG.ACCUMR_SPS_ID,BX_SPS_MAPNG.status_cd, BX_SPS_MAPNG.in_temp_tab tempFlag , "+
							"BX_SPS_MAPNG.CREATD_TM_STMP, BX_SPS_MAPNG.CREATD_USER_ID, BX_SPS_MAPNG.LST_CHG_USR, BX_SPS_MAPNG.LST_CHG_TMS,BX_SPS_MAPNG.MAPPNG_CMP_IND isFinalized, " +
							
				          "(SELECT CD_DESC_TXT FROM ITEM WHERE PRMRY_CD = SPS_PARAM_ID AND CTLG_ID = 1938 ) SPS_PARAM_DESC,	"+			
				          "(SELECT SCNDRY_CD FROM ITEM WHERE PRMRY_CD = EB01_VALUE AND CTLG_ID = 30160 )EB01_DESC,	"+		
				          "(SELECT SCNDRY_CD FROM ITEM WHERE PRMRY_CD = EB02_VALUE AND CTLG_ID = 30161 )EB02_DESC, "+				
				          "(SELECT SCNDRY_CD FROM ITEM WHERE PRMRY_CD = EB06_VALUE AND CTLG_ID = 30163 )EB06_DESC, "+	
				          "(SELECT SCNDRY_CD FROM ITEM WHERE PRMRY_CD = EB09_VALUE AND CTLG_ID = 30164 )EB09_DESC,	"+		
				          "(SELECT SCNDRY_CD FROM ITEM WHERE PRMRY_CD = HSD1_VALUE AND CTLG_ID = 30165 )HSD1_DESC, "+			
				          "(SELECT SCNDRY_CD FROM ITEM WHERE PRMRY_CD = HSD2_VALUE AND CTLG_ID =30166 )HSD2_DESC, "+		
				          "(SELECT SCNDRY_CD FROM ITEM WHERE PRMRY_CD = HSD3_VALUE AND CTLG_ID = 30167 )HSD3_DESC,	"+	
				          "(SELECT SCNDRY_CD FROM ITEM WHERE PRMRY_CD = HSD4_VALUE AND CTLG_ID = 30168 )HSD4_DESC, "+			
				          "(SELECT SCNDRY_CD FROM ITEM WHERE PRMRY_CD = HSD5_VALUE AND CTLG_ID = 30169 )HSD5_DESC, "+			
				          "(SELECT SCNDRY_CD FROM ITEM WHERE PRMRY_CD = HSD6_VALUE AND CTLG_ID = 30170 )HSD6_DESC, "+
						  "(SELECT SCNDRY_CD FROM ITEM WHERE PRMRY_CD = HSD7_VALUE AND CTLG_ID = 50240 )HSD7_DESC, "+
						  "(SELECT SCNDRY_CD FROM ITEM WHERE PRMRY_CD = HSD8_VALUE AND CTLG_ID = 50241 )HSD8_DESC, "+
				          "(SELECT CD_DESC_TXT FROM ITEM WHERE PRMRY_CD = ACCUMR_SPS_ID AND CTLG_ID = 2605 ) ACCUMR_SPS_DESC, BX_SPS_MAPNG.DATA_FEED_IND "+
    	            	  "FROM BX_SPS_MAPNG " +
    	            	  "WHERE  SPS_PARAM_ID Like  '"+ spsId + "'" +
	    	              " and BX_SPS_MAPNG.in_temp_tab = 'N' " +
						  "union "+
						  "SELECT  T.SPS_PARAM_ID,T.EB01_VALUE,T.EB02_VALUE,T.EB06_VALUE, T.EB09_VALUE, "+ 
							"T.HSD1_VALUE,T.HSD2_VALUE,T.HSD3_VALUE,T.HSD4_VALUE,T.HSD5_VALUE,T.HSD6_VALUE," +
							"T.HSD7_VALUE, T.HSD8_VALUE,T.ACCUMR_SPS_ID,T.status_cd, 'Y' TempFlag, "+
							"T.CREATD_TM_STMP, T.CREATD_USER_ID, T.LST_CHG_USR, T.LST_CHG_TMS, T.MAPPNG_CMP_IND isFinalized, " +
							
				          "(SELECT CD_DESC_TXT FROM ITEM WHERE PRMRY_CD = T.SPS_PARAM_ID AND CTLG_ID = 1938 ) SPS_PARAM_DESC, "+			
				          "(SELECT SCNDRY_CD FROM ITEM WHERE PRMRY_CD = T.EB01_VALUE AND CTLG_ID = 30160) EB01_DESC, "+		
				          "(SELECT SCNDRY_CD FROM ITEM WHERE PRMRY_CD = T.EB02_VALUE AND CTLG_ID = 30161) EB02_DESC, "+				
				          "(SELECT SCNDRY_CD FROM ITEM WHERE PRMRY_CD = T.EB06_VALUE AND CTLG_ID = 30163 ) EB06_DESC, "+	
				          "(SELECT SCNDRY_CD FROM ITEM WHERE PRMRY_CD = T.EB09_VALUE AND CTLG_ID = 30164) EB09_DESC, "+		
				          "(SELECT SCNDRY_CD FROM ITEM WHERE PRMRY_CD = T.HSD1_VALUE AND CTLG_ID = 30165) HSD1_DESC, "+			
				          "(SELECT SCNDRY_CD FROM ITEM WHERE PRMRY_CD = T.HSD2_VALUE AND CTLG_ID = 30166) HSD2_DESC, "+		
				          "(SELECT SCNDRY_CD FROM ITEM WHERE PRMRY_CD = T.HSD3_VALUE AND CTLG_ID = 30167) HSD3_DESC, "+	
				          "(SELECT SCNDRY_CD FROM ITEM WHERE PRMRY_CD = T.HSD4_VALUE AND CTLG_ID = 30168) HSD4_DESC, "+			
				          "(SELECT SCNDRY_CD FROM ITEM WHERE PRMRY_CD = T.HSD5_VALUE AND CTLG_ID = 30169) HSD5_DESC, "+			
				          "(SELECT SCNDRY_CD FROM ITEM WHERE PRMRY_CD = T.HSD6_VALUE AND CTLG_ID = 30170) HSD6_DESC, "+
				          "(SELECT SCNDRY_CD FROM ITEM WHERE PRMRY_CD = T.HSD7_VALUE AND CTLG_ID = 50240) HSD7_DESC, "+			
				          "(SELECT SCNDRY_CD FROM ITEM WHERE PRMRY_CD = T.HSD8_VALUE AND CTLG_ID = 50241) HSD8_DESC, "+
				          "(SELECT CD_DESC_TXT FROM ITEM WHERE PRMRY_CD = T.ACCUMR_SPS_ID AND CTLG_ID = 2605) ACCUMR_SPS_DESC, T.DATA_FEED_IND "+
		            	  "FROM TEMP_BX_SPS_MAPG T " +
		            	  " WHERE  T.SPS_PARAM_ID Like  '"+ spsId + "'" ;		            	 
					
					RetrieveMapping retrieveMapping = new RetrieveMapping(dataSource,
							findMapping);					
					mappings = retrieveMapping.execute();					
									
				}			
		}
		if (null == status || !status.contains("IN-PROGRESS")) {
			// To set the Type, PVA and Data Type
			if ((null != mappings) && (!mappings.isEmpty())) {
				Mapping retrievedMapping = (Mapping) mappings.get(0);
				if (null != retrievedMapping
						&& null != retrievedMapping.getSpsId()
						&& null != retrievedMapping.getSpsId().getSpsId()) {
					selectQueryForSPSMAPG += "Where s.SPS_ID = '"
							+ retrievedMapping.getSpsId().getSpsId() + "'";
					RetrieveSpsMapg retrieveSpsMapg = new RetrieveSpsMapg(
							dataSource, selectQueryForSPSMAPG);
					List spsDetails = retrieveSpsMapg.execute();
					retrievedMapping.getSpsId().setSpsDetail(spsDetails);
				}
			}
			// To set the Audit trail
			if ((null != mappings) && (!mappings.isEmpty())) {

				Mapping retrievedMapping = (Mapping) mappings.get(0);
				List auditTrails = getAuditInfo(spsId, auditTrailCount);
				retrievedMapping.setAuditTrails(auditTrails);
			}
		}	
		
		return mappings;

	}
	/**
	 * Inner class for Retrieve Mapping
	 */
	private static class RetrieveSpsMapg extends MappingSqlQuery {
		private RetrieveSpsMapg(DataSource dataSource, String query) {
			super(dataSource, query);			
			super.compile();
		}
		/**
		 * sets the values to mapping object
		 * @param ResultSet
		 * @param arg1
		 * @throws SQLException
		 */
		protected Object mapRow(ResultSet rs, int arg1) throws SQLException {			
			SPSDetail spsDetail = new SPSDetail();
			if (null != rs.getString("mapg_type_cd")) {
				spsDetail.setSpsType(rs.getString("mapg_type_cd").toUpperCase());
			}
			if (null != rs.getString("mapg_pva")) {
				spsDetail.setSpsPVA(rs.getString("mapg_pva").toUpperCase());
			}
			if (null != rs.getString("mapg_legend")) {
				spsDetail.setSpsDataType(rs.getString("mapg_legend").toUpperCase());
			}
			return spsDetail;
		}
	}
	
	/**
	 * Inner class for Locate Mapping
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
		/**
		 * sets the values to mapping object
		 * @param ResultSet
		 * @param arg1
		 * @throws SQLException
		 */
		protected Object mapRow(ResultSet rs, int arg1) throws SQLException {
			Mapping mapping = new Mapping();
			
			SPSId sPSId = new SPSId();
			sPSId.setSpsId(rs.getString("spsId").trim());
			if(null != rs.getString("spsDesc") && !isPrintFlag) {
				sPSId.setSpsDesc(BxUtil.escapeSpecialCharacters(rs.getString("spsDesc")));
			}
			else {
				sPSId.setSpsDesc(rs.getString("spsDesc"));
			}
			
			mapping.setSpsId(sPSId);
			
			mapping.setVariableMappingStatus(rs.getString("status"));
			
			User user = new User();
			//user.setCreatedUserName(rs.getString("CREATD_USER_ID"));
			user.setLastUpdatedUserName(rs.getString("lastUpdatedUser"));
			mapping.setUser(user);
			// set to created tmstamp
			mapping.setLastChangedTmStamp(rs.getTimestamp("updateDate"));
			mapping.setCreatedTmStamp(rs.getTimestamp("updateDate"));
			
			boolean isFinalized = false;
			if(null != rs.getString(isFinalizedString)){
				if(rs.getString(isFinalizedString).equals("Y")) {
					isFinalized = true;			
				}
			}
			mapping.setFinalized(isFinalized);
			String lockUserId = rs.getString("bolk_bus_obj_lock_usr_id");
			if(null != lockUserId){
				
				Lock lock = new Lock();
				lock.setLockUserId(lockUserId);
				mapping.setLock(lock);
			}
			return mapping;
		}
	}
	/**
	 * Inner class for Retrieve Mapping
	 */
	private final class RetrieveMapping extends MappingSqlQuery {
		private RetrieveMapping(DataSource dataSource, String query) {
			super(dataSource, query);			
			super.compile();
		}
		/**
		 * sets the values to mapping object
		 * @param ResultSet
		 * @param arg1
		 * @throws SQLException
		 */
		protected Object mapRow(ResultSet rs, int arg1) throws SQLException {
			Mapping mapping = new Mapping();
			SPSId sPSId = new SPSId();
			sPSId.setSpsId(rs.getString("SPS_PARAM_ID"));
			if(null != rs.getString("SPS_PARAM_DESC")) {
				sPSId.setSpsDesc(BxUtil.escapeSpecialCharacters(rs.getString("SPS_PARAM_DESC")));
			}
			else {
				sPSId.setSpsDesc(rs.getString("SPS_PARAM_DESC"));
			}
			
			mapping.setSpsId(sPSId);
			
			mapping.setEb01(addHippaSegment(rs.getString("EB01_VALUE"), rs.getString("EB01_DESC")));
			mapping.setEb02(addHippaSegment(rs.getString("EB02_VALUE"), rs.getString("EB02_DESC")));
			mapping.setEb06(addHippaSegment(rs.getString("EB06_VALUE"), rs.getString("EB06_DESC")));
			mapping.setEb09(addHippaSegment(rs.getString("EB09_VALUE"), rs.getString("EB09_DESC")));
			mapping.setHsd01(addHippaSegment(rs.getString("HSD1_VALUE"), rs.getString("HSD1_DESC")));
			mapping.setHsd02(addHippaSegment(rs.getString("HSD2_VALUE"), rs.getString("HSD2_DESC")));
			mapping.setHsd03(addHippaSegment(rs.getString("HSD3_VALUE"), rs.getString("HSD3_DESC")));
			mapping.setHsd04(addHippaSegment(rs.getString("HSD4_VALUE"), rs.getString("HSD4_DESC")));
			mapping.setHsd05(addHippaSegment(rs.getString("HSD5_VALUE"), rs.getString("HSD5_DESC")));
			mapping.setHsd06(addHippaSegment(rs.getString("HSD6_VALUE"), rs.getString("HSD6_DESC")));
			mapping.setHsd07(addHippaSegment(rs.getString("HSD7_VALUE"), rs.getString("HSD7_DESC")));
			mapping.setHsd08(addHippaSegment(rs.getString("HSD8_VALUE"), rs.getString("HSD8_DESC")));
			mapping.setAccum(addHippaSegment(rs.getString("ACCUMR_SPS_ID"), rs.getString("ACCUMR_SPS_DESC")));
			
			mapping.setVariableMappingStatus(rs.getString("status_cd"));
			mapping.setInTempTable(rs.getString("TempFlag"));
			
			User user = new User();
			user.setCreatedUserName(rs.getString("CREATD_USER_ID"));
			user.setLastUpdatedUserName(rs.getString("LST_CHG_USR"));
			mapping.setUser(user);
			mapping.setLastChangedTmStamp(rs.getDate("LST_CHG_TMS"));
			mapping.setCreatedTmStamp(rs.getDate("CREATD_TM_STMP"));
			mapping.setDataFeedInd(rs.getString("DATA_FEED_IND"));
			
			boolean isFinalized = false;
			if(null != rs.getString(isFinalizedString)){
				if(rs.getString(isFinalizedString).equals("Y")) {
					isFinalized = true;			
				}
			}
			mapping.setFinalized(isFinalized);
			
			return mapping;
		}
	}
	/**
	 * Creates the HippaSegment object 
	 * @param value
	 * @param desc
	 * @return HippaSegment
	 */
	private HippaSegment addHippaSegment(String value, String desc) {		
		HippaSegment hippaSegment = new HippaSegment();
		List selectedHippaSegmentValues = new ArrayList();
		HippaCodeValue code = new HippaCodeValue();
		code.setValue(value);
		code.setDescription(desc);	
		selectedHippaSegmentValues.add(code);
		hippaSegment.setHippaCodeSelectedValues(selectedHippaSegmentValues);
		return hippaSegment;
	}
	/**
	 * Inner class for Retrieving audit trail
	 */
	private static class RetrieveAuditTrailQuery extends MappingSqlQuery {

		private RetrieveAuditTrailQuery(DataSource dataSource,String query) {
	        super(dataSource, query);
		        compile();
	    }
		/**
		 * sets the values to AuditTrail object
		 * @param ResultSet
		 * @param arg1
		 * @throws SQLException
		 */
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	AuditTrail auditTrail = new AuditTrail();
	    	String auditTime = BxUtil.getFormattedDate(rs.getTimestamp("CREATD_TM_STMP"));
	    	auditTrail.setCreatedAuditDate(auditTime);
	    	auditTrail.setCreatedUser(rs.getString("CREATD_USER_ID"));
	    	auditTrail.setSystemComments(rs.getString("SYS_CMNTS"));	    	
	    	auditTrail.setUserComments(rs.getString("USR_CMNTS"));	    	
	    	auditTrail.setVariableId(rs.getString("SPS_PARAM_ID"));	    	
	    	auditTrail.setMappingStatus(rs.getString("MAPG_STATUS"));
		  	return auditTrail;
		}		
    }	
	/**
	 * Retrieves the latest audit information of SPS ID
	 * @param spsId
	 * @param recordCountFlag
	 * @return List
	 */
	private List getAuditInfo(String spsId, int auditTrailCount){
			
		StringBuffer query = new StringBuffer();
		query = query.append("SELECT * FROM ");
		query = query.append("(SELECT CREATD_TM_STMP, CREATD_USER_ID, SYS_CMNTS, USR_CMNTS, SPS_PARAM_ID, MAPG_STATUS FROM ");
		query = query.append("BX_SPS_AUDIT_TRAIL WHERE SPS_PARAM_ID = '"
			+ spsId + "'" );	
		query = query.append("ORDER BY CREATD_TM_STMP DESC)");
		if(auditTrailCount != -1){
			query = query.append("WHERE ROWNUM < "
						+ DomainConstants.noOfAuditInfo + " ");
		}	
		
		RetrieveAuditTrailQuery retrieveAuditTrailQuery = new RetrieveAuditTrailQuery(dataSource,query.toString());
		List auditTrailList = retrieveAuditTrailQuery.execute();
		if(null != auditTrailList && auditTrailList.size() > 0){		   
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
		String mappedRecordsFromMain = " select A.SPS_PARAM_ID  spsId,C.CD_DESC_TXT  spsDesc, a.lst_chg_tms CREATD_TM_STMP,"
				+ " a.status_cd status "
				+ "from BX_SPS_MAPNG A, "
				+ " CATALOG B,ITEM C "
				+ "where B.CTLG_ID IN  ("
				+ "select CTLG_ID "
				+ " from CATALOG where upper(CTLG_NAME) =upper('reference') "
				+ ") and B.CTLG_ID = C.CTLG_ID "
				+ "and A.SPS_PARAM_ID =C.PRMRY_CD and a.in_temp_tab = 'N' ";
		String unionCondition = "UNION ALL";
		String mappedRecordsFromTemp = " select D.SPS_PARAM_ID spsId,F.CD_DESC_TXT spsDesc, D.lst_chg_tms CREATD_TM_STMP, "
				+ "D.status_cd status  "
				+ "from TEMP_BX_SPS_MAPG D,"
				+ " CATALOG E,ITEM F  "
				+ "where E.CTLG_ID IN  ( "
				+ "select CTLG_ID from CATALOG "
				+ " where upper(CTLG_NAME) =upper('reference') "
				+ ") and E.CTLG_ID = F.CTLG_ID "
				+ "and D.SPS_PARAM_ID =F.PRMRY_CD ";
		String unmappedRecords = " select  c.prmry_cd  spsId,C.CD_DESC_TXT  spsDesc,c.creatd_tm_stmp CREATD_TM_STMP, "
				+ "'UNMAPPED' status "
				+ "from CATALOG B,ITEM C "
				+ "where  c.prmry_cd NOT IN (select bx.SPS_PARAM_ID from BX_SPS_MAPNG bx  "
				+ ") and B.CTLG_ID IN  ( "
				+ "select CTLG_ID from CATALOG where "
				+ " upper(CTLG_NAME) =upper('reference') "
				+ ") and B.CTLG_ID = C.CTLG_ID ";
		String orderBySpsid = " order by spsId";
		String conditionForNotApplicable = null;
		String locateQuery = null;
		String spsId = null;
		String likeSpsid = null;

		if (null != mapping) {
			if (null != mapping.getSpsId()
					&& null != mapping.getSpsId().getSpsId()
					&& !mapping.getSpsId().getSpsId().trim().equals("")) {
				spsId = mapping.getSpsId().getSpsId();
				spsId = spsId.replace('\'', ' ');
			}
		}

		if ((status.contains(DomainConstants.MAPPED_STATUS))
				&& ((!status.contains(DomainConstants.STATUS_NOT_APPLICABLE)))
				&& ((!status.contains(DomainConstants.UNMAPPED_STATUS)))) {

			if (null != mapping && null != mapping.getSpsId()
					&& null != mapping.getSpsId().getSpsId()) {

				conditionForNotApplicable = statuscdNotEqualTo + " '"
						+ DomainConstants.STATUS_NOT_APPLICABLE + "'";

				locateQuery = mappedRecordsFromMain + conditionForNotApplicable
						+ likeConditionInMain + " '" + spsId + "' "
						+ unionCondition + mappedRecordsFromTemp
						+ likeConditionInTemp + " '" + spsId + "' "
						+ orderBySpsid;
			}
		}

		if ((!status.contains(DomainConstants.MAPPED_STATUS))
				&& ((!status.contains(DomainConstants.STATUS_NOT_APPLICABLE)))
				&& ((status.contains(DomainConstants.UNMAPPED_STATUS)))) {

			if (null != mapping && null != mapping.getSpsId()
					&& null != mapping.getSpsId().getSpsId()) {

				likeSpsid = likePrmryCd + " '" + spsId + "' ";

				locateQuery = unmappedRecords + likeSpsid +orderBySpsid;

			}

		}
		// to locate mapped and not applicable
		if ((status.contains(DomainConstants.MAPPED_STATUS))
				&& ((status.contains(DomainConstants.STATUS_NOT_APPLICABLE)))
				&& ((!status.contains(DomainConstants.UNMAPPED_STATUS)))) {

			locateQuery = mappedRecordsFromMain + likeConditionInMain + " '"
					+ spsId + "' " + unionCondition + mappedRecordsFromTemp
					+ likeConditionInTemp + " '" + spsId + "' " + orderBySpsid;

		}
		// to locate not applicable mapping
		else if ((!status.contains(DomainConstants.MAPPED_STATUS))
				&& ((status.contains(DomainConstants.STATUS_NOT_APPLICABLE)))
				&& ((!status.contains(DomainConstants.UNMAPPED_STATUS)))) {

			conditionForNotApplicable = "and a.status_cd = '"
					+ DomainConstants.STATUS_NOT_APPLICABLE + "'";

			locateQuery = mappedRecordsFromMain + conditionForNotApplicable
					+ likeConditionInMain + " '" + spsId + "' " + orderBySpsid;
		}
		// to locate mapped and unmapped
		if ((status.contains(DomainConstants.MAPPED_STATUS))
				&& ((!status.contains(DomainConstants.STATUS_NOT_APPLICABLE)))
				&& ((status.contains(DomainConstants.UNMAPPED_STATUS)))) {

			conditionForNotApplicable = statuscdNotEqualTo + " '"
					+ DomainConstants.STATUS_NOT_APPLICABLE + "'";
			locateQuery = mappedRecordsFromMain + conditionForNotApplicable
					+ likeConditionInMain + " '" + spsId + "' "
					+ unionCondition + mappedRecordsFromTemp
					+ likeConditionInTemp + " '" + spsId + "' "
					+ unionCondition + unmappedRecords + likePrmryCd + " '"
					+ spsId + "' " +orderBySpsid;
		}
		// to locate unmapped and not applicable
		if ((!status.contains(DomainConstants.MAPPED_STATUS))
				&& ((status.contains(DomainConstants.STATUS_NOT_APPLICABLE)))
				&& ((status.contains(DomainConstants.UNMAPPED_STATUS)))) {

			conditionForNotApplicable = " and a.status_cd = '"
					+ DomainConstants.STATUS_NOT_APPLICABLE + "'";
			locateQuery = mappedRecordsFromMain + conditionForNotApplicable
					+ likeConditionInMain + " '" + spsId + "' "
					+ unionCondition + unmappedRecords + likePrmryCd + " '"
					+ spsId + "' " + orderBySpsid;

		}
		// locate mapped , unmapped and not applicable
		if ((status.contains(DomainConstants.MAPPED_STATUS))
				&& ((status.contains(DomainConstants.STATUS_NOT_APPLICABLE)))
				&& ((status.contains(DomainConstants.UNMAPPED_STATUS)))) {

			locateQuery = mappedRecordsFromMain + likeConditionInMain + " '"
					+ spsId + "' " + unionCondition + mappedRecordsFromTemp
					+ likeConditionInTemp + " '" + spsId + "' "
					+ unionCondition + unmappedRecords + likePrmryCd + " '"
					+ spsId + "' " + orderBySpsid;
		}
		log.info(ESAPI.encoder().encodeForHTML(locateQuery));
		long startTime = System.currentTimeMillis();
		LocateMappingForReport locateMapping = new LocateMappingForReport(
				dataSource, locateQuery);
		long endTime=System.currentTimeMillis() - startTime;
		log.debug("Time to execute Query is "+endTime);
		return locateMapping.execute();
	}
	/**
	 * Inner class for Locate Mapping
	 */
	private static class LocateMappingForReport extends MappingSqlQuery {
		private LocateMappingForReport(DataSource dataSource, String query) {
			super(dataSource, query);
			super.compile();
		}
		/**
		 * sets the values to searchResult object
		 * @param ResultSet
		 * @param arg1
		 * @throws SQLException
		 */
		protected Object mapRow(ResultSet rs, int arg1) throws SQLException {
			SearchResult searchResult = new SearchResult();
			searchResult.setSpsId(rs.getString("spsId"));	
			searchResult.setDescription(rs.getString("spsDesc"));
			searchResult.setFormattedDate(BxUtil.getFormattedDateWithOutTime(rs.getDate("CREATD_TM_STMP")));
			searchResult.setStatus(rs.getString("status"));
			return searchResult;
		}
	}
	/**
	 * @return DataSource
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
}
