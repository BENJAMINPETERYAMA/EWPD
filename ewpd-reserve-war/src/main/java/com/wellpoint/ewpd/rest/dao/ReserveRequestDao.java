/**
 * 
 */
package com.wellpoint.ewpd.rest.dao;

import java.util.Date;
import java.util.HashMap;
import org.apache.log4j.Logger;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.wellpoint.ewpd.rest.Requests.ReserveRequest;
import com.wellpoint.ewpd.rest.Responses.ReserveContractResponse;
import com.wellpoint.ewpd.rest.controller.ReserveContractController;

/**
 * @author AF37766
 *
 */

@Repository
public class ReserveRequestDao {
	
	
	private static final Logger logger = Logger.getLogger(ReserveRequestDao.class); 
	
	private NamedParameterJdbcTemplate jdbcTemplate;
    
	
	
	/**
	 * @return the jdbcTemplate
	 */
	public NamedParameterJdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}



	/**
	 * @param jdbcTemplate the jdbcTemplate to set
	 */
	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}



	public long insertRequest(ReserveRequest resRequest) throws Exception{
		
		String resReq = new Gson().toJson(resRequest);
		
		logger.debug(resRequest);
		String getNextval = "select RSRV_CNTRCT_SRVC_ID_SEQ.NEXTVAL from dual";
		long nextvalue =0;
		nextvalue = jdbcTemplate.queryForLong(getNextval, new HashMap());
		
		
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		String insertQuery = "INSERT INTO RSRV_CNTRCT_SRVC_LOG(LOG_ID_SQNC,RSRV_CNTRCT_RQST,RSRV_CNTRCT_RSQT_TM_STMP,RSRV_CNTRCT_RSPNS,RSRV_CNTRCT_RSPNS_TM_STMP,ACTN,TXN_ID) VALUES (:LOG_ID_SQNC,:RSRV_CNTRCT_RQST,:RSRV_CNTRCT_RSQT_TM_STMP,:RSRV_CNTRCT_RSPNS,:RSRV_CNTRCT_RSPNS_TM_STMP,:ACTN,:TXN_ID)";
		paramMap.addValue("LOG_ID_SQNC", nextvalue);
		paramMap.addValue("RSRV_CNTRCT_RQST", resReq);
		paramMap.addValue("RSRV_CNTRCT_RSQT_TM_STMP", new Date());
		paramMap.addValue("RSRV_CNTRCT_RSPNS", null);
		paramMap.addValue("RSRV_CNTRCT_RSPNS_TM_STMP", null);
		paramMap.addValue("ACTN", "RESERVE");
		paramMap.addValue("TXN_ID",resRequest.getTxnId());
		
		logger.debug(insertQuery);
		
		int insertedRows = jdbcTemplate.update(insertQuery, paramMap);
		/*if(insertedRows==1){
			return nextvalue;
		}*/
		return nextvalue;
		
	}



	public boolean updateTheResponse(
			ReserveContractResponse reserveContractResponse  , long insertedSeqId) throws Exception {
		int rowsUpdated= 0;
		try{
			String resReq = new Gson().toJson(reserveContractResponse);
			logger.debug(resReq);
			
			MapSqlParameterSource paramMap = new MapSqlParameterSource();
			String updateQuery = "update RSRV_CNTRCT_SRVC_LOG set RSRV_CNTRCT_RSPNS=:RSRV_CNTRCT_RSPNS, RSRV_CNTRCT_RSPNS_TM_STMP=:RSRV_CNTRCT_RSPNS_TM_STMP where LOG_ID_SQNC=:LOG_ID_SQNC";
			paramMap.addValue("RSRV_CNTRCT_RSPNS", resReq);
			paramMap.addValue("RSRV_CNTRCT_RSPNS_TM_STMP", new Date());
			paramMap.addValue("LOG_ID_SQNC", insertedSeqId);
			
			logger.debug(updateQuery);
			rowsUpdated = jdbcTemplate.update(updateQuery, paramMap);
			
			return rowsUpdated==1;
			
		}catch(Exception e){
			throw new Exception("error while response updating");
		}
		
	}
	
	
	
	
	
}
