package com.wellpoint.ewpd.rest.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.wellpoint.contractcodepool.exception.InvalidInputParameterException;
import com.wellpoint.ewpd.rest.Requests.ReserveRequestForSales;
import com.wellpoint.ewpd.rest.Responses.ReserveForSalesResponse;
import com.wellpoint.ewpd.rest.model.Domain;
import com.wellpoint.ewpd.rest.model.EnquiryResponse;
import com.wellpoint.ewpd.rest.model.Status;
import com.wellpoint.ewpd.rest.service.PropertyGetter;
import com.wellpoint.ewpd.rest.service.ReserveForSalesService;
import com.wellpoint.ewpd.rest.util.ServiceUtil;



@Repository
public class ReserveForSalesDao {
	
private static final Logger logger = Logger.getLogger(ReserveForSalesDao.class); 
	
private NamedParameterJdbcTemplate jdbcTemplate;
	
	
	@Autowired
	private PropertyGetter propertyGetter;
	
	@Autowired
	private DatasourceBean datasourceBean;
	
	
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


	public ReserveForSalesResponse fetchReserveContractForSalestData(ReserveRequestForSales request,String VA_ASO_IND) throws InvalidInputParameterException {

			ReserveForSalesResponse response = new ReserveForSalesResponse();
			logger.info("fetching data : ReserveForSalesDao.fetchReserveContractForSalestData");
			Status status = new Status();
			String userId = propertyGetter.getValue(ServiceUtil.TRANS_USERID);
			
			    SimpleJdbcCall procedureCall = new SimpleJdbcCall(datasourceBean.getDataSource()).withSchemaName(propertyGetter.getValue(ServiceUtil.OWNER_SCHEMA))
			      .withProcedureName(propertyGetter.getValue(ServiceUtil.RESERVE_PROC_NAME));

			    MapSqlParameterSource parameters = new MapSqlParameterSource();
			    parameters.addValue("baseContractCode", request.getBaseContract());
			    parameters.addValue("transUserId", userId);
			    parameters.addValue("vaAsoInd", VA_ASO_IND);
			    	
			    logger.info("executing procedure call");
			    Map resultList = procedureCall.execute(parameters);
			    logger.info("completed executing procedure call");
			    String code = (String)resultList.get(propertyGetter.getValue(ServiceUtil.NEW_CNTRCT_CD));
			    if(code != null && code.contains(propertyGetter.getValue(ServiceUtil.CNTRCT_CD_MANUAL))){
			    	  logger.info("getting manual response back after executing procedure call");
				      response.setContractCode(null);
				      status.setCode(Integer.parseInt(propertyGetter.getValue(ServiceUtil.MAN_MSG_INV_WPD_CD_STTS_CD)));
				      status.setMessage(propertyGetter.getValue(ServiceUtil.MAN_MSG_INV_WPD_CD));
				      response.setStatus(status);
			      
		    	  }else if(code != null && code.contains(propertyGetter.getValue(ServiceUtil.INVALID_CNTRCT_ERR))){
		    		  logger.info("getting invalid contract error response back after executing procedure call");
				      response.setContractCode(null);
				      status.setCode(HttpStatus.CONFLICT.value());
				      status.setMessage(propertyGetter.getValue(ServiceUtil.INV_WGS_CNTCT_MSG));
				      response.setStatus(status);
		    	  }
		    	  else{
		    		  logger.info("getting contract successfully reserved response back  after executing procedure call");
				      response.setContractCode(code);
				      status.setCode(HttpStatus.OK.value());
				      status.setMessage(propertyGetter.getValue(ServiceUtil.SUCCESS_RES_MSG));
				      response.setStatus(status);
		    	  }
				logger.info("returning procedure call response");
			    return response;
			
	}
	
	public long insertRequest(ReserveRequestForSales request,String requestTimestamp) throws InvalidInputParameterException, ParseException{

		logger.info("inseting data into the table RSRV_CNTRCT_SRVC_LOG : ReserveForSalesDao.insertRequest");
		String resReq = new Gson().toJson(request);
		ReserveForSalesService resvService = new ReserveForSalesService();
		
		
			
		String getNextval = "select RSRV_CNTRCT_SRVC_ID_SEQ.NEXTVAL from dual";
		long nextvalue =0;
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date effDate ;*/
		nextvalue = jdbcTemplate.queryForLong(getNextval, new HashMap());
		/*if(request.getEffectiveDate() == null || request.getEffectiveDate().isEmpty()){
			effDate = null;
		}
		else{
			effDate=sdf.parse(request.getEffectiveDate());
		}*/
		
		
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		String insertQuery = "INSERT INTO RSRV_CNTRCT_SRVC_LOG(LOG_ID_SQNC,RSRV_CNTRCT_RQST,RSRV_CNTRCT_RSQT_TM_STMP,RSRV_CNTRCT_RSPNS,RSRV_CNTRCT_RSPNS_TM_STMP,ACTN,TXN_ID,QUOTE_LINE_ITEM,EFFECTIVE_DATE,BASE_CNTRCT) VALUES (:LOG_ID_SQNC,:RSRV_CNTRCT_RQST,:RSRV_CNTRCT_RSQT_TM_STMP,:RSRV_CNTRCT_RSPNS,:RSRV_CNTRCT_RSPNS_TM_STMP,:ACTN,:TXN_ID,:QUOTE_LINE_ITEM,:EFFECTIVE_DATE,:BASE_CNTRCT)";
		paramMap.addValue("LOG_ID_SQNC", nextvalue);
		paramMap.addValue("RSRV_CNTRCT_RQST", resReq);
		paramMap.addValue("RSRV_CNTRCT_RSQT_TM_STMP", resvService.dateConversiontoReqTimestamp(requestTimestamp));
		paramMap.addValue("RSRV_CNTRCT_RSPNS", null);
		paramMap.addValue("RSRV_CNTRCT_RSPNS_TM_STMP", null);
		paramMap.addValue("ACTN", "RESERVE");
		paramMap.addValue("TXN_ID",request.getTransactionId());
		paramMap.addValue("QUOTE_LINE_ITEM",request.getQuoteLineItem());
		paramMap.addValue("EFFECTIVE_DATE",resvService.dateConversion(request.getEffectiveDate()));
		paramMap.addValue("BASE_CNTRCT", request.getBaseContract());
		
		logger.info("insert query "+insertQuery);
		
		int insertedRows = jdbcTemplate.update(insertQuery, paramMap);
		
		return nextvalue;

		
	}
	
	public boolean updateTheResponse(
			ReserveForSalesResponse releaseResponse  , long insertedSeqId) throws Exception {
			
			logger.info("updating response data into the table RSRV_CNTRCT_SRVC_LOG :"+"ReserveForSalesDao.updateTheResponse");
		
			int rowsUpdated= 0;
			
			ReserveForSalesService resvService = new ReserveForSalesService();
		try{
			String resRes = new Gson().toJson(releaseResponse);
			MapSqlParameterSource paramMap = new MapSqlParameterSource();
			String updateQuery = "update RSRV_CNTRCT_SRVC_LOG set RSRV_CNTRCT_RSPNS=:RSRV_CNTRCT_RSPNS, RSRV_CNTRCT_RSPNS_TM_STMP=:RSRV_CNTRCT_RSPNS_TM_STMP,OP_CNTRCT_CD=:OP_CNTRCT_CD where LOG_ID_SQNC=:LOG_ID_SQNC";
			paramMap.addValue("RSRV_CNTRCT_RSPNS", resRes);
			paramMap.addValue("RSRV_CNTRCT_RSPNS_TM_STMP", resvService.dateConversiontoResTimestamp(releaseResponse.getTransactionTimestamp()));
			paramMap.addValue("LOG_ID_SQNC", insertedSeqId);
			paramMap.addValue("OP_CNTRCT_CD", releaseResponse.getContractCode());
			
			rowsUpdated = jdbcTemplate.update(updateQuery, paramMap);
			
			return rowsUpdated==1;
			
		}catch(Exception e){
			throw new Exception("error while response updating");
		}
		
	}
	
	public long getSeqForRecId(){
		
		logger.info("getting recordid sequence");
		String recordId = "select RSRV_CNTRCT_RECRD_ID_SEQ.NEXTVAL from dual";
		long nextvalue =0;
		nextvalue = jdbcTemplate.queryForLong(recordId, new HashMap());
		logger.info(" recordid sequence value :"+nextvalue);
		return nextvalue;
		
	}
	
	public long insertDFJson(Object contReserveBDFPayLoad,ReserveRequestForSales resRequest,ReserveForSalesResponse response) throws Exception{

        
        logger.info("contReserveBDFPayLoad message : "+contReserveBDFPayLoad);
        
        ReserveForSalesService resvService = new ReserveForSalesService();
        
        
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        String insertQuery = "INSERT INTO RSRV_CNTRCT_PBLSH_LOG(DATA_FABRIC_JSON,TRANSACTION_TIMESTAMP,TRANSACTION_ID) VALUES (:DATA_FABRIC_JSON,:TRANSACTION_TIMESTAMP,:TRANSACTION_ID)";
		paramMap.addValue("DATA_FABRIC_JSON", contReserveBDFPayLoad.toString());
		paramMap.addValue("TRANSACTION_TIMESTAMP", resvService.dateConversiontoResTimestamp(response.getTransactionTimestamp()));
		paramMap.addValue("TRANSACTION_ID",resRequest.getTransactionId());
		int insertedRows = jdbcTemplate.update(insertQuery, paramMap);
        
        return insertedRows;
        
 }
	

public String getQuoteLineResponse(ReserveRequestForSales resRequest) throws InvalidInputParameterException{
	
	  String cntrct = null;
	  String sqlQuery = "select OP_CNTRCT_CD from RSRV_CNTRCT_SRVC_LOG where QUOTE_LINE_ITEM = :QUOTE_LINE_ITEM AND OP_CNTRCT_CD IS NOT NULL"; 
	  Map<String,String> paramMap = new HashMap<String,String>();
	  paramMap.put("QUOTE_LINE_ITEM",resRequest.getQuoteLineItem() );
	  
	  List<String> strLst  = jdbcTemplate.query(sqlQuery, paramMap,new fetchDetailsMapper());
			  
			  
	  if(strLst != null && !strLst.isEmpty()){
		  cntrct=strLst.get(0);
	  }
	  return cntrct;
}
 
private final class fetchDetailsMapper implements RowMapper{
	
	@Override
	public String mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		String cntrct_cd = null;
		cntrct_cd = rs.getString("OP_CNTRCT_CD");		
		return cntrct_cd;
	}
	
}

}


