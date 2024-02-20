/**
 * 
 */
package com.wellpoint.ewpd.rest.dao;

import java.util.Date;
import java.util.HashMap;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.wellpoint.ewpd.rest.Requests.ReleaseRequest;
import com.wellpoint.ewpd.rest.Responses.ReleaseResponse;

/**
 * @author AF37766
 *
 */

@Repository
public class ReleaseRequestDao {
	
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



	public long insertRequest(ReleaseRequest relRequest) throws Exception{
		
		String resReq = new Gson().toJson(relRequest);
		
		
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
		paramMap.addValue("ACTN", "RELEASE");
		paramMap.addValue("TXN_ID", relRequest.getTxnId());
		int insertedRows = jdbcTemplate.update(insertQuery, paramMap);
		/*if(insertedRows==1){
			return nextvalue;
		}*/
		return nextvalue;
		
	}



	public boolean updateTheResponse(
			ReleaseResponse releaseResponse  , long insertedSeqId) throws Exception {
		int rowsUpdated= 0;
		try{
			String resReq = new Gson().toJson(releaseResponse);
			MapSqlParameterSource paramMap = new MapSqlParameterSource();
			String updateQuery = "update RSRV_CNTRCT_SRVC_LOG set RSRV_CNTRCT_RSPNS=:RSRV_CNTRCT_RSPNS, RSRV_CNTRCT_RSPNS_TM_STMP=:RSRV_CNTRCT_RSPNS_TM_STMP where LOG_ID_SQNC=:LOG_ID_SQNC";
			paramMap.addValue("RSRV_CNTRCT_RSPNS", resReq);
			paramMap.addValue("RSRV_CNTRCT_RSPNS_TM_STMP", new Date());
			paramMap.addValue("LOG_ID_SQNC", insertedSeqId);
			
			rowsUpdated = jdbcTemplate.update(updateQuery, paramMap);
			
			return rowsUpdated==1;
			
		}catch(Exception e){
			throw new Exception("error while response updating");
		}
		
	}
	
	
	
	
	
}
